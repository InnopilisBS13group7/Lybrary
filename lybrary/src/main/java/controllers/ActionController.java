package controllers;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.sql.SQLException;

@RestController
public class ActionController extends controllers.Controller {


    @RequestMapping(value = "/toProfile", method = RequestMethod.POST)
    public String toProfile(@CookieValue(value = "user_code", required = false) Cookie cookieUserCode)
            throws SQLException{
        if (isCookieWrong(cookieUserCode)) return "false";
        String userId = getIdFromCookie(cookieUserCode.getValue());
        return createUserCardPage(userId);
    }
}
