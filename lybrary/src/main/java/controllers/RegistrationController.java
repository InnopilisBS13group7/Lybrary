package controllers;


import DateBase.DBHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RegistrationController extends controllers.Controller {
    /**
     * Checks if there is already an account in database
     *
     * @param name
     * @param surname
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "/registration", method = POST)
    public String registration(@RequestParam(value = "name", required = false, defaultValue = "Not found") String name,
                               @RequestParam(value = "surname", required = false, defaultValue = "Not found") String surname,
                               @RequestParam(value = "email", required = false, defaultValue = "Not found") String email,
                               @RequestParam(value = "password", required = false, defaultValue = "Not found") String password,
                               HttpServletResponse response) throws SQLException {

        Boolean check = addNewUserToTheSystem(name, surname, email, password);

        if (!check) return "false";

        DBHandler db;
        String userId = "";

        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery = "select * from users where email = '" + email + "'";
        ResultSet resultSet = statement.executeQuery(getQuery);
        resultSet.next();
        userId = resultSet.getString("id");
        //-------create Cookie

        createNewCookieForUser(email, response);

        //------create page
        return createUserCardPage(userId);
    }


}
