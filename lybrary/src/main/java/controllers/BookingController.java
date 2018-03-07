package controllers;


import DateBase.DBHandler;
import Models.Document;
import Models.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BookingController extends Controller {

    @RequestMapping(value = "/takeItem", method = POST)
    public String takeItem(@RequestParam(value = "documentId", required = true, defaultValue = "Not found") String documentId,
                           @CookieValue(value = "user_code", required = false) Cookie cookieUserCode
    ) {

        Date date = new Date();
        DBHandler db;
        try {
            db = new DBHandler();
            Statement userStatement = db.getConnection().createStatement();
            Statement documentsStatement = db.getConnection().createStatement();
            Statement ordersStatement = db.getConnection().createStatement();
            String documentGetQuery = "select * from documents where id = " + documentId + "";
            String usersGetQuery = "select * from users where id = " + getIdFromCookie(cookieUserCode.getValue()) + "";
            String ordersGetQuery = "select * from orders where userId = " + getIdFromCookie(cookieUserCode.getValue()) + " AND status = 'open'";

            System.out.println(ordersGetQuery);
            ResultSet usersResultSet = userStatement.executeQuery(usersGetQuery);
            ResultSet documentsResultSet = documentsStatement.executeQuery(documentGetQuery);
            ResultSet ordersResultSet = ordersStatement.executeQuery(ordersGetQuery);

            boolean check = false;
            while (ordersResultSet.next()) {
                if (ordersResultSet.getString("itemId").equals(documentId)) {
                    check = true;
                }
            }
            if (check) return "false";
            usersResultSet.next();
            if (!documentsResultSet.next()) return "false";
            int currentAmount = documentsResultSet.getInt("amount");
            if (currentAmount == 0 || documentsResultSet.getString("status").equals("reference")) return "false";
            //-- some conditions
            long keepingTime = 0;
            String userStatus = usersResultSet.getString("status");
            String documentStatus = documentsResultSet.getString("status");
            if (documentStatus.equals("bestseller")) keepingTime = 1209600000;
            else {
                if (userStatus.equals("disabled") || userStatus.equals("activated")) keepingTime = 1728000000;
                else keepingTime = 2 * 1728000000L;
//                System.out.println(keepingTime);
            }
            //--

            String queryForDocument = "update documents set amount = " + (currentAmount - 1) + " where id = " + documentId;
//            System.out.println(queryForDocument);
            String queryForOrder = "insert into orders (userId, itemId, startTime, finishTime) values (" +
                    getIdFromCookie(cookieUserCode.getValue()) + ", " +
                    documentId + ", " +
                    date.getTime() + ", " +
                    (date.getTime() + keepingTime) + ")";

            documentsStatement.execute(queryForDocument);
            userStatement.execute(queryForOrder);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "true";
    }

    @RequestMapping(value = "/listItems", method = POST)
    public String listItems(@CookieValue(value = "user_code", required = false) Cookie cookieUserCode) throws SQLException {
        if (isCookieWrong(cookieUserCode)) return "false";
        User u = getClientUserObject(getIdFromCookie(cookieUserCode.getValue()));
        DBHandler db;
        String divList = (u.getStatus().equals("admin")?"<div id=new_doc_box>" +
                "<div class=new_doc id=new_book>+ Add a new book</div>" +
                "<div class=new_doc id=new_av>+ Add a new audio/video</div>" +
                "<div class=add_block id=add_block_book>" +
                "<input class=add_inputs id=add_book_title placeholder=Title />" +
                "<input class=add_inputs id=add_book_author placeholder=Authors />" +
                "<input class=add_inputs id=add_book_publisher placeholder=Publisher />" +
                "<input class=add_inputs id=add_book_year placeholder=Year />" +
                "<input class=add_inputs id=add_book_edition placeholder=Edition />" +
                "<input class=add_inputs id=add_book_note placeholder=Note />" +
                "<div class=add_save id=add_save_book>Add</div>" +
                "</div>" +
                "<div class=add_block id=add_block_av>" +
                "<input class=add_inputs id=add_av_title placeholder=Title />" +
                "<input class=add_inputs id=add_av_author placeholder=Authors />" +
                "<div class=add_save id=add_save_av>Add</div>" +
                "</div>" +
                "</div>":"");

        List<Document> list = getAllDocuments();
        for (Document d: getAllDocuments()){
            divList = divList + "<div class=books_box><img src=/resources/img/books/"+(d.getType().equals("book")?"1.jpg":"2.jpg")+" class=cover width=79px height=121px />" +
                    "<p class=books_text>" +
                    "Title:&nbsp;<input class=books_inputs_title placeholder=\"Title\" value=\""+d.getTitle()+"\" /></br>" +
                    "Author:&nbsp;<input class=books_inputs_author placeholder=\"Author\" value=\""+d.getAuthor()+"\" /></br>" +

                    (d.getType().equals("book")?"Publisher:&nbsp;<input class=books_inputs_publisher placeholder=\"Publisher\" value=\""+d.getPublisher()+"\" /></br>" +
                    "Year:&nbsp;<input class=books_inputs_year placeholder=\"Year\" value=\""+ d.getYear() +"\" /></br>" +
                    "Edition:&nbsp;<input class=books_inputs_edition placeholder=\"Edition\" value=\""+d.getEdition()+"\" /></br>" +
                    "Note:&nbsp;<input class=books_inputs_note placeholder=\"Note\" value=\""+d.getDescription()+"\" /></br>":"") +
                    "</p>" +
                    "<div class=bookit id=" + d.getId() + ">Book</div> <p class=number>There is " +
                    d.getAmount() +
                    "</p> " +
                    "<div class=modifyit id=" + d.getId()+ ">Modify</div>" +
                    "</div>";
        }


        return divList;
    }


    @RequestMapping(value = "/returnDocument", method = RequestMethod.POST)
    public String returnDocument(@CookieValue(value = "user_code", required = false) Cookie cookieUserCode,
                                 @RequestParam(value = "orderId") String orderId)
            throws SQLException {
        if (isCookieWrong(cookieUserCode)) return "false";

        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String query = "UPDATE orders SET " +
                "status ='" + "finished" + "' " +
                "WHERE id = " + orderId;
        System.out.println(query);
        statement.execute(query);
        statement.close();

        return "true";
    }

}
