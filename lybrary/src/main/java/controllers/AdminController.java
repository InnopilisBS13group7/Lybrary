package controllers;

import DateBase.DBHandler;
import Models.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class AdminController extends Controller {

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST)
    public static String addDocument(@RequestParam(value = "title", required = false, defaultValue = "Not found") String title,
                                     @RequestParam(value = "author", required = false, defaultValue = "Not found") String author,
                                     @RequestParam(value = "type", required = false, defaultValue = "Not found") String type,
                                     @RequestParam(value = "description", required = false, defaultValue = "Not found") String description,
                                     @RequestParam(value = "teg", required = false, defaultValue = "Not found") String teg,
                                     @RequestParam(value = "amount", required = false, defaultValue = "Not found") String amount,
                                     @RequestParam(value = "status", required = false, defaultValue = "Not found") String status)
            throws SQLException {
        DBHandler db;
        String document_id = "";
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String getQuery = "select * from documents where title = '" + title + "' and author = '" + author + "' and type = '" + type + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (resultSet.next()) return "false";//already exist such book

        String query = "insert into documents (title,author,status,amount,description,teg,type) values('"
                + title + "\', \'"
                + author + "\', \'"
                + status + "\', \'"
                + amount + "\', \'"
                + description + "\', \'"
                + teg + "\', \'"
                + type + "\')";
        statement.execute(query);
        return "true";
    }

    @RequestMapping(value = "/modifyDocument", method = RequestMethod.POST)
    public static String modifyDocument(@RequestParam(value = "id", required = false, defaultValue = "Not found") String id,
                                        @RequestParam(value = "title", required = false, defaultValue = "Not found") String title,
                                        @RequestParam(value = "author", required = false, defaultValue = "Not found") String author,
                                        @RequestParam(value = "type", required = false, defaultValue = "Not found") String type,
                                        @RequestParam(value = "description", required = false, defaultValue = "Not found") String description,
                                        @RequestParam(value = "teg", required = false, defaultValue = "Not found") String teg,
                                        @RequestParam(value = "amount", required = false, defaultValue = "Not found") String amount,
                                        @RequestParam(value = "status", required = false, defaultValue = "Not found") String status)
            throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String getQuery = "select * from documents where id = " + id;
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "false";//does not exist such book

        String query = "UPDATE documents SET " +
                "title ='" + title + "', " +
                "author ='" + author + "', " +
                "status ='" + status + "', " +
                "amount =" + amount + ", " +
                "description ='" + description + "', " +
                "teg ='" + teg + "', " +
                "type ='" + type + "' " +
                "WHERE id = " + id;
        statement.execute(query);
        return "true";
    }

    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public static String modifyUser(@RequestParam(value = "id", required = false, defaultValue = "Not found") String id,
                                    @RequestParam(value = "name", required = false, defaultValue = "Not found") String name,
                                    @RequestParam(value = "surname", required = false, defaultValue = "Not found") String surname,
                                    @RequestParam(value = "fine", required = false, defaultValue = "Not found") String fine,
                                    @RequestParam(value = "status", required = false, defaultValue = "Not found") String status)
            throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String getQuery = "select * from users where id = " + id;
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "false";//does not exist such user

        String query = "UPDATE users SET " +
                "name ='" + name + "', " +
                "surname ='" + surname + "', " +
                "status ='" + status + "', " +
                "fine =" + fine + ", " +
                "WHERE id = " + id;
//        System.out.println(query);
        statement.execute(query);
        return "true";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public static String deleteUser(@RequestParam(value = "id", required = false, defaultValue = "Not found") String id)
            throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String getQuery = "select * from users where id = " + id;
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "false";//does not exist such user

        String query = "DELETE from users "+
                "WHERE id = " + id;
        statement.execute(query);
        return "true";
    }

    @RequestMapping(value = "/deleteDocumentById", method = RequestMethod.POST)
    public static String deleteDocumentById(@RequestParam(value = "id", required = false, defaultValue = "Not found") String id)
            throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String getQuery = "select * from documents where id = " + id;
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "false";//does not exist such book

        String query = "DELETE from documents "+
                "WHERE id = " + id;
        statement.execute(query);
        return "true";
    }

    @RequestMapping(value = "/deleteDocumentByParameters", method = RequestMethod.POST)
    public static String deleteDocumentByParameters(@RequestParam(value = "title", required = false, defaultValue = "Not found") String title,
                                                    @RequestParam(value = "author", required = false, defaultValue = "Not found") String author,
                                                    @RequestParam(value = "type", required = false, defaultValue = "Not found") String type)
            throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String getQuery = "select * from documents where title = '" + title + "' and author = '" + author + "' and type = '" + type + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "false";//already exist such book

        String query = "DELETE from documents "+
                "where title = '" + title + "' and author = '" + author + "' and type = '" + type + "'";
        statement.execute(query);
        return "true";
    }

    @RequestMapping(value = "/closeOrder", method = RequestMethod.POST)
    public static String closeOrder(@RequestParam(value = "orderId", required = false, defaultValue = "Not found") String orderId)
            throws SQLException {
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String getQuery = "select * from orders where id = " + orderId;
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "false";//does not exist such order

        String query = "UPDATE orders SET " +
                "status ='" + "closed" + "' " +
                "WHERE id = " + orderId;
//        System.out.println(query);
        statement.execute(query);
        statement.close();
        return "true";
    }


    public static void main(String[] args) throws SQLException {
        addDocument( "Книга","Bookkk","Bookkk","Bookkk","Bookkk","10","reference");
//        closeOrder("22");
        for (Object d : getAllDocuments()) {
            System.out.println(d.toString());
        }
    }

}