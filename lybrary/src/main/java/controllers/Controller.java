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

            String historyQuery = "select * from orders where userId = '" + userId + "' AND status = 'open'";
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
            String fine = usersResultSet.getString("fine");

            String address = usersResultSet.getString("address");
            String phone = usersResultSet.getString("phone");

            String title, time, items = "";
            long keepingTime;
            int i = 0;
            int margin = -5;
            while (ordersResultSet.next()) {
                i++;
                keepingTime = ordersResultSet.getLong("finishTime");
                items = items + "<div class=\"books\" style=\"margin-left:" + margin + "px\"> " +
                        "<div class=books_inside>" + getDate(keepingTime) +
                        "<div class=return_book id="+ordersResultSet.getString("id")+">Return the book</div></div>" +
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
                    "<p class=\"usercard_info_text1\" style=\"margin-top:52px\">Address:</p> " +
                    "<p class=\"usercard_info_text1\" style=\"margin-top:82px\">Phone:</p> " +
                    "<p class=\"usercard_info_text1\" style=\"margin-top:112px\">Card Id:</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:-8px\">" + status + "</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:22px\">"+fine+"</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:52px\">"+address+"</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:82px\">"+phone+"</p> " +
                    "<p class=\"usercard_info_text2\" style=\"margin-top:112px\">"+userId+"</p> " +
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

    protected static boolean addNewUserToTheSystem(String name, String surname, String email, String password,String status) throws SQLException {
        DBHandler db;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from users where email = '" + email + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (resultSet.next()) return false;
        String encodedPassword = encoder.encode(password);
        String query = "insert into users (email,password,name,surname,status) values('"
                + email + "\', \'"
                + encodedPassword + "\', \'"
                + name + "\', \'"
                + surname + "\', \'"
                + status + "\')";
        statement.execute(query);
        statement.close();
        return true;
    }

    protected static List<User> getAllUsers() throws SQLException {
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
                    resultSet.getInt(8),
                    resultSet.getString(9),
                    resultSet.getString(10)));
        }
        return list;
    }

    protected static List<Order> getAllOrders() throws SQLException {
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

    protected static List<Document> getAllDocuments() throws SQLException {
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
                    resultSet.getString(8),
                    resultSet.getString("edition"),
                    resultSet.getString("publisher"),
                    resultSet.getInt("year")));
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

    protected static User getClientUserObject(String id) throws SQLException {
        DBHandler db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String query = "select * from users where id = " + id;
        ResultSet r = statement.executeQuery(query);
        if (!r.next()) System.out.println("PPPPPPPPPPPPPPIZDEC");//return null;
        User user = new User(r.getString(1),
                r.getString(2),
                r.getString(3),
                r.getString(4),
                r.getString(5),
                r.getString(6),
                r.getString(7),
                r.getInt(8),
                r.getString(9),
                r.getString(10));
        statement.close();
        return user;
    }

    protected static Document getDocumentByOrder(Order order) throws SQLException {
        DBHandler db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String query = "select * from documents where id = " + order.getItemId();
        ResultSet r = statement.executeQuery(query);
        if (!r.next()) return null;
        Document document = new Document(r.getString(1),
                r.getString(2),
                r.getString(3),
                r.getString(4),
                r.getInt(5),
                r.getString(6),
                r.getString(7),
                r.getString(8));
        return document;
    }

    protected static String createListOfUsersBlock(List<User> users) {
        String div = "<div class=settings_type_box id=settings_users>" +
                "<div id=new_user><p id=new_user_bottom>+ Add a new user</p>" +
                "<input type=text class=new_user_inputs id=new_user_inputs_name placeholder=\"Name\" />" +
                "<input type=text class=new_user_inputs id=new_user_inputs_surname placeholder=\"Surname\" />" +
                "<input type=text class=new_user_inputs id=new_user_inputs_email placeholder=\"email\" />" +
                "<input type=password class=new_user_inputs id=new_user_inputs_password placeholder=\"Password\" />" +
                "<input type=text class=new_user_inputs id=new_user_inputs_type placeholder=\"Type\" />" +
                "<div id=new_user_save>Save</div>" +
                "</div>";
        for (User u : users) {
            div += "<div class=settings_list_users>" +
                    "<img src=/resources/img/avatars/1.jpg width=106px height=106px class=settings_users_list_avatar />" +
                    "<div class=settings_users_list_specs_box>" +
                    "<b>Name: </b><input type=text class=settings_inputs_users_name placeholder=\"Name\" value=\""+ u.getName() + " " + u.getSurname() +"\" /></br>" +
                    "<b>Adress: </b><input type=text class=settings_inputs_users_adress placeholder=\"Adress\" value=\""+u.getAddress()+"\" /></br>" +
                    "<b>Phone number: </b><input type=text class=settings_inputs_users_phone placeholder=\"Phone number\" value=\""+u.getPhone()+"\" /></br>" +
                    "<b>id: </b>"+u.getId()+"</br>" +
                    "<b>Type: </b><input type=text class=settings_inputs_users_type placeholder=\"Type\" value=\"" + u.getStatus() + "\" /></br>" +
                    "</div>" +
                    "<div class=settings_users_list_modify id=" + u.getId() + ">Save</div>" +
                    "</div>";
        }
        return div + "</div>";
    }

    protected static String createListOfOrdersBlock(List<Order> orders) throws SQLException {
        String div = "<div class=settings_type_box id=settings_orders>";
        Document d;
        User u;
        for (Order or : orders) {
            d = getDocumentByOrder(or);
            u = getClientUserObject(Integer.toString(or.getUserId()));
            div += "<div class=settings_list_orders>" +
                    "<img src=/resources/img/books/1.jpg width=62px height=62px class=settings_orders_list_avatar />" +
                    "<div class=settings_orders_list_specs_box>" +
                    "<b style=\"text-decoration:underline;\"> (" + or.getId() + ")" + d.getTitle()+ "   :" + u.getName() + " " + u.getSurname()  + "</b></br>" +
                    "<b>Status: </b>"+or.getStatus()+"</br>" +
                    "<b>Return date:</b>" + getDate(or.getFinishTime()) +
                    "</div>" +
                    (or.getStatus().equals("closed") ? "":"<div class=settings_orders_list_modify id="+or.getId()+">Close</div>") +
                    "</div>";
        }
        return div + "</div>";
    }



    //----------for tests
    public static int numberOfUsers() throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(getQuery);
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        return num;
    }

    public static int numberOfDocuments() throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "SELECT * FROM documents";
        ResultSet resultSet = statement.executeQuery(getQuery);
        int num = 0;
        while (resultSet.next()) {
            num += resultSet.getInt(5);
        }
        return num;
    }

    public static List<String> getUserInfo(String id) throws SQLException {
        List<String> list = new LinkedList<String>();
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from users where id = '" + id + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
            list.add(resultSet.getString(4));
            list.add(resultSet.getString(5));
            list.add(resultSet.getString(2));
            list.add(resultSet.getString(7));
            list.add(resultSet.getString(8));
        }
        if(list.isEmpty()) return null;
        return list;
    }

    public static String getUserIdByParameters(String name, String surname, String status) throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from users where name = '" + name + "' and surname= '" + surname + "' and status = '" + status + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "false";
        return Integer.toString(resultSet.getInt(1));
    }

    public static boolean addNewUserToTheSystemWithStatus(String name, String surname, String email, String password, String status) throws SQLException {
        DBHandler db;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String page = "[uuue";
        String booki = "";
        String userId = "";
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from users where email = '" + email + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (resultSet.next()) return false;
        String encodedPassword = encoder.encode(password);
        String query = "insert into users (email,password,name,surname,status) values('"
                + email + "\', \'"
                + encodedPassword + "\', \'"
                + name + "\', \'"
                + surname + "\',\'"
                + status + "\')";
        statement.execute(query);
        resultSet = statement.executeQuery(getQuery);
        resultSet.next();
        userId = resultSet.getString("id");

        return true;
    }

    public static Cookie getCookieFromId(String id) throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from users where id = '" + id + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        if(!resultSet.next()) return null;
        Cookie coo = new Cookie("cooka", resultSet.getString(6));
        return coo;
    }
}
