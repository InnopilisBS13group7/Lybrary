package test;


import DateBase.DBHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BookingControllerT extends controllers.Controller {

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
            if(cookieUserCode==null) return "false";
            String usersGetQuery = "select * from users where id = " + getIdFromCookie(cookieUserCode.getValue()) + "";
            String ordersGetQuery = "select * from orders where userId = " + getIdFromCookie(cookieUserCode.getValue()) + "";

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
    public String listItems() {
        DBHandler db;
        String divList = "<div id=new_doc_box>" +
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
                        "</div>";
        try {
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "SELECT * FROM documents";
            ResultSet resultSet = statement.executeQuery(getQuery);
            String title, author, description, image, teg, type, id, status;
            int amount;
            while (resultSet.next()) {
                title = resultSet.getString("title");
                author = resultSet.getString("author");
                description = resultSet.getString("description");
                amount = resultSet.getInt("amount");
                teg = resultSet.getString("teg");
                type = resultSet.getString("type");
                id = resultSet.getString("id");
                status = resultSet.getString("status");
                divList = divList + "<div class=books_box><img src=/resources/img/books/1.jpg class=cover width=79px height=121px />" +
                        "<p class=books_text>" +
                            "Title:&nbsp;<input class=books_inputs_title placeholder=\"Title\" value=\"3 Pigs\" /></br>" +
                            "Author:&nbsp;<input class=books_inputs_author placeholder=\"Author\" value=\"I am\" /></br>" +
                            "Publisher:&nbsp;<input class=books_inputs_publisher placeholder=\"Publisher\" value=\"Tvoya djopa\" /></br>" +
                            "Year:&nbsp;<input class=books_inputs_year placeholder=\"Year\" value=\"20!8 ot rozgestva hrestova\" /></br>" +
                            "Edition:&nbsp;<input class=books_inputs_edition placeholder=\"Edition\" value=\"THE BEST\" /></br>" +
                            "Note:&nbsp;<input class=books_inputs_note placeholder=\"Note\" value=\"reference\" /></br>" +
                        "</p>" +
                        "<div class=bookit id=" + id + ">Book</div> <p class=number>There is " +
                        amount +
                        "</p> " +
                        "<div class=modifyit id=" + id + ">Modify</div>" +
                        "</div>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return divList;
    }


}
