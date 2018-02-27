package controllers;

import DateBase.DBHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import sun.tools.tree.ShiftRightExpression;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class EnterController extends controllers.Controller{

    /**
     * searches in database.if exists-checks with saved password
     *
     * @param email
     * @param password
     * @return true-if all correct.false-password doesn`t match. or "Unregistered email"
     */
    @RequestMapping(value = "/enter", method = POST)
    public String enter(@RequestParam(value = "email", required = false, defaultValue = "Not found") String email,
                        @RequestParam(value = "password", required = false, defaultValue = "Not found") String password,
                        HttpServletResponse response) {
        DBHandler db;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String name = "Not", surname = "found:(";
        String status,page = "[uuue";
        String booki = "";
        String userId = "";
        try {
            db = new DBHandler();
            Statement statement = db.getConnection().createStatement();
            String getQuery = "select * from users where email = '" + email + "'";
            Boolean passwordValidation;
            ResultSet resultSet = statement.executeQuery(getQuery);
            //-----catch mistakes
            if (!resultSet.next()) return "false";
            //get parameters
//            name = resultSet.getString("name");
//            surname = resultSet.getString("surname");
//            status = resultSet.getString("status");
            userId = resultSet.getString("id");
            String encodedPassword = resultSet.getString("password");
            passwordValidation = encoder.matches(password, encodedPassword);
            if (!passwordValidation) return "false";
            //create cookie----

            createNewCookieForUser(email, response);



        } catch (SQLException e) {
            e.printStackTrace();
        }
        //-------create page
        return createUserCardPage(userId);
    }

}
