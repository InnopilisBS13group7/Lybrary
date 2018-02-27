package controllers;

import DateBase.DBHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class AdminController extends Controller {

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST)
    public String toProfile(@RequestParam(value = "title", required = false, defaultValue = "Not found") String title,
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

}