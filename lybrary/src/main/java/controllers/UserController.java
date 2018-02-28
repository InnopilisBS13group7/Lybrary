package controllers;

import DateBase.DBHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class UserController extends Controller {

    @RequestMapping(value = "/returnDocument", method = RequestMethod.POST)
    public String returnDocument(@CookieValue(value = "user_code", required = false) Cookie cookieUserCode,
                                 @RequestParam(value = "documentId", required = false) String documentId,
                                 @RequestParam(value = "orderId", required = true) String orderId)
            throws SQLException {
        if (isCookieWrong(cookieUserCode)) return "false";

        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();

        String query = "UPDATE orders SET " +
                "status ='" + "finished" + "', " +
                "WHERE id = " + orderId;
//        System.out.println(query);
        statement.execute(query);
        statement.close();

//        String userId = getIdFromCookie(cookieUserCode.getValue());
        return "true";
    }
}
