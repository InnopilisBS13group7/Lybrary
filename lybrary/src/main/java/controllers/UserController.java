package controllers;

import DateBase.DBException;
import DateBase.DBHandler;
import Models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class UserController extends Controller {


    @RequestMapping(value = "/profileSettings", method = RequestMethod.POST)
    public static String profileSettings(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                    @RequestParam(value = "surname", required = false, defaultValue = "") String surname,
                                    @RequestParam(value = "address", required = false, defaultValue = "") String address,
                                    @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                    @RequestParam(value = "newPassword", required = false, defaultValue = "") String newPassword,
                                    @RequestParam(value = "currentPassword", required = false, defaultValue = "") String currentPassword,
                                         @CookieValue(value = "user_code", required = false) Cookie cookieUserCode)
            throws SQLException, DBException {
        DBHandler db;
        db = new DBHandler();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Statement statement = db.getConnection().createStatement();
        User u = getClientUserObject(getIdFromCookie(cookieUserCode.getValue()));
        int id = u.getId();
        String getQuery = "select * from users where id = " + id;
        ResultSet resultSet = statement.executeQuery(getQuery);
        if (!resultSet.next()) return "Such user does not exist ";//does not exist such user

        String encodedOldPassword = resultSet.getString("password");

        String encodedNewPassword = encoder.encode(newPassword);
        String query;
        System.out.println(newPassword);
        if (!currentPassword.equals("")) {
            Boolean passwordValidation = encoder.matches(currentPassword, encodedOldPassword);
            if (!passwordValidation) return "false";
            else if (newPassword.length()>=8) {
                query = "UPDATE users SET " +
                        "name ='" + name + "', " +
                        "surname ='" + surname + "', " +
                        "address ='" + address + "', " +
                        "phone ='" + phone + "', " +
                        "password ='" + encodedNewPassword + "' " +
                        "WHERE id = " + id;
            } else {
                query = "UPDATE users SET " +
                        "name ='" + name + "', " +
                        "surname ='" + surname + "', " +
                        "address ='" + address + "', " +
                        "phone ='" + phone + "' " +
                        "WHERE id = " + id;
            }
        } else query = "UPDATE users SET " +
                "name ='" + name + "', " +
                "surname ='" + surname + "', " +
                "address ='" + address + "', " +
                "phone ='" + phone + "' " +
                "WHERE id = " + id;

        System.out.println(query);
        statement.execute(query);
        return "true";
    }

}
