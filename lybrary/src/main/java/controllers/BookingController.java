package controllers;


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
public class BookingController extends Controller{

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
            String ordersGetQuery = "select * from orders where userId = " + getIdFromCookie(cookieUserCode.getValue()) + "";
//            String ordersGetQuery = "select * from orders where (userId = " + getIdFromCookie(cookieUserCode.getValue()) + ") and " +
//                    "( itemId = " + documentId + ")";

            System.out.println(ordersGetQuery);
            ResultSet usersResultSet = userStatement.executeQuery(usersGetQuery);
            ResultSet documentsResultSet = documentsStatement.executeQuery(documentGetQuery);
            ResultSet ordersResultSet = ordersStatement.executeQuery(ordersGetQuery);

            boolean check = false;
            while (ordersResultSet.next()){
                if (ordersResultSet.getString("itemId").equals(documentId)) {
                    check = true;
                }
            }
            if (check) return "false";
            usersResultSet.next();
            if (!documentsResultSet.next()) return "false";
            int currentAmount = documentsResultSet.getInt("amount");
            if (currentAmount == 0) return "false";
            //-- some conditions
            long keepingTime = 0;
            String userStatus = usersResultSet.getString("status");
            String documentStatus = documentsResultSet.getString("status");
            if (documentStatus.equals("bestseller")) keepingTime = 1209600000;
            else {
                if (userStatus.equals("disabled") || userStatus.equals("activated")) keepingTime = 1728000000;
                else keepingTime = 2 * 1728000000L;
                System.out.println(keepingTime);
            }
            //--

            String queryForDocument = "update documents set amount = " + (currentAmount - 1) + " where id = " + documentId;
            System.out.println(queryForDocument);
            String queryForOrder = "insert into orders (userId, itemId, startTime, finishTime) values (" +
                    getIdFromCookie(cookieUserCode.getValue()) + ", " +
                    documentId + ", " +
                    date.getTime() + ", " +
                    (date.getTime()+keepingTime)+")";

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
        String divList = "";
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
                divList = divList + "<div class=books_box> <img src=/resources/img/books/1.jpg class=cover width=66px height=100px /> " +
                        "<p class=bookname>" +
                        title +
                        "</p>" +
                        " <p class=type>" +
                        teg + " #" + status +
                        "</p>" +
                        " <p class=description>" +
                        description +
                        "</p> " +
                        "<div class=bookit id=" + id + ">Book</div> <p class=number>There is " +
                        amount +
                        "</p> " +
                        "</div>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return divList;
    }


}
