package controllers;

import DateBase.DBHandler;
import Models.Document;
import Models.Order;
import Models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller {

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy");
        return formatForDate.format(date);
    }

    public static String getDate(long currentTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(cal.getTime());
    }

    public static String getIdFromCookie(String cookieUserCode) {
        return cookieUserCode.substring(6, cookieUserCode.length() - 6);
    }

    public static boolean createNewCookieForUser(String email, HttpServletResponse response) {
        DBHandler db;
        try {
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from users where email = '" + email + "'";
            ResultSet resultSet = statement.executeQuery(getQuery);
            if (!resultSet.next()) return false;

            Random random = new Random();
            String cookieId = (random.nextInt(700000) + 100000) + "" + resultSet.getInt(1) + "" + (random.nextInt(700000) + 100000);
            String query = "update users set cookieId = '"
                    + cookieId + "' where email = '" + email + "';";
            statement.execute(query);
            Cookie cookie = new Cookie("user_code", cookieId);
            cookie.setMaxAge(3600 * 12);
            response.addCookie(cookie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String createUserCardPage(String userId) {
        String page = "";
        DBHandler db;
        try {
            db = new DBHandler();
            Statement historyStatement = db.getConnection().createStatement();
            Statement orderStatement = db.getConnection().createStatement();
            Statement usersStatement = db.getConnection().createStatement();

            String historyQuery = "select * from orders where userId = '" + userId + "'";
            String usersQuery = "select * from users where id = '" + userId + "'";
//            String ordersQuery = "select * from users where id = '" + userId + "'";

            ResultSet ordersResultSet = orderStatement.executeQuery(historyQuery);
            ResultSet usersResultSet = usersStatement.executeQuery(usersQuery);
//            ResultSet historyResultSet = historyStatement.executeQuery(historyQuery);

            if (!usersResultSet.next()) return "error";

            String name = usersResultSet.getString("name");
            String surname = usersResultSet.getString("surname");
            String status = usersResultSet.getString("status");
            String email = usersResultSet.getString("email");

            String title, time, items = "";
            long keepingTime;
            int i = 0;
            int margin = -5;
            while (ordersResultSet.next()) {
                i++;
                keepingTime = ordersResultSet.getLong("finishTime");
                items = items + "<div class=\"books\" style=\"margin-left:" + margin + "px\"> " +
                        "<div class=books_inside>" + getDate(keepingTime) +
                        "<div class=return_book id=228/*Тут номер заказа должен быть*/>Return the book</div></div>" +
                        "<img src=\"/resources/img/books/1.jpg\" width=\"190px\" height=\"289px\" /> " +
                        "<p class=\"bookname\">" + "3 PIGS</p> " +
                        "</div>";
                margin += 198;
                if (i % 4 == 0) margin = -5;

            }

            page = "<div id=\"usercard\">" +
                    "<div id=\"usercard_avatar\" class=\"blocks\"></div>" +
                    "<div class=\"blocks\" id=\"usercard_info\">" +
                    "<p id=\"name\">" + name + " " + surname + "</p> " +
                    "<p id=\"settings_bottom\">Settings</p> " +
                    "<p class=\"usercard_info_text1\" style=\"margin-top:-8px\">Status:</p> " +
                    "<p class=\"usercard_info_text1\" style=\"margin-top:22px\">fine:</p> " +
                    "<p class=\"usercard_info_text1\" style=\"margin-top:52px\">Chlen:</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:-8px\">" + status + "</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:22px\">228$</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:52px\">Bolshoi</p> " +
                    "</div> " +
                    "<div class=\"blocks\" id=\"history\"> " +
                    "<div class=\"line\"> " +
                    items +
                    "</div> " +
                    "</div> " +
                    "</div>";


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    protected boolean isCookieWrong(Cookie cookieUserCode) throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from users where cookieId = '" + cookieUserCode.getValue() + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        return !resultSet.next();
    }

    protected static boolean addNewUserToTheSystem(String name, String surname, String email, String password) throws SQLException {
        DBHandler db;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String status = "", page = "[uuue";
        String booki = "";
        String userId = "";
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from users where email = '" + email + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (resultSet.next()) return false;
        String encodedPassword = encoder.encode(password);
        String query = "insert into users (email,password,name,surname) values('"
                + email + "\', \'"
                + encodedPassword + "\', \'"
                + name + "\', \'"
                + surname + "\')";
        statement.execute(query);
        resultSet = statement.executeQuery(getQuery);
        resultSet.next();
        userId = resultSet.getString("id");

        return true;
    }

    protected static Iterable getAllUsers() throws SQLException {
        List<User> list = new LinkedList<>();
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(getQuery);
        while (resultSet.next()) {
            list.add(new User(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8)));
        }
        return list;
    }

    protected static Iterable getAllOrders() throws SQLException {
        List<Order> list = new LinkedList<>();
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "SELECT * FROM orders";
        ResultSet resultSet = statement.executeQuery(getQuery);
        while (resultSet.next()) {
            list.add(new Order(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getLong(4),
                    resultSet.getLong(5),
                    resultSet.getString(6)));
        }
        return list;
    }

    protected static Iterable getAllFinishedOrders() throws SQLException {
        List<Order> list = new LinkedList<>();
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "SELECT * FROM orders WHERE status = 'finished'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        while (resultSet.next()) {
            list.add(new Order(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getLong(4),
                    resultSet.getLong(5),
                    resultSet.getString(6)));
        }
        return list;
    }

    protected static Iterable getAllDocuments() throws SQLException {
        List<Document> list = new LinkedList<>();
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "SELECT * FROM documents";
        ResultSet resultSet = statement.executeQuery(getQuery);
        while (resultSet.next()) {
            list.add(new Document(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)));
        }
        return list;
    }

    public static String getDocumentIdByParameters(String title, String author, String type) throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from documents where title = '" + title + "' and author = '" + author + "' and type = '" + type + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "false";
        return Integer.toString(resultSet.getInt(1));
    }


}
