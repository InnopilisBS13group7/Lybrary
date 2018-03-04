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

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String settings(@CookieValue(value = "user_code", required = false) Cookie cookieUserCode)
            throws SQLException{

        String div = "<div id=settings_block>" +
      "<div id=settings_type_menu>" +
      "  <div class=settings_type id=settings_type_profile>Profile</div>" +
      "  <div class=settings_type id=settings_type_users>Users</div>" +
      "  <div class=settings_type id=settings_type_orders>Orders</div>" +
      "  <div id=settings_type_line></div>" +
      "</div>" +
      "<p class=setting_parameter_name><b>Change name</b></p>" +
      "<input type=text class=settings_input id=settings_name placeholder=\"New name\" style=\"margin-top:-8px\" />" +
      "<input type=text class=settings_input id=settings_surname placeholder=\"New surname\" />" +
      "<p class=setting_parameter_name style=\"margin-top:16px\"><b>Change password</b></p>" +
      "<input type=text class=settings_input id=settings_current_password placeholder=\"Current password\" style=\"margin-top:-8px\" />" +
      "<input type=text class=settings_input id=settings_new_password placeholder=\"New password\" />" +
      "<div id=settings_profile_save>Save</div>" +
    "</div>";
    
        return div;
    }
}
