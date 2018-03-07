package controllers;

import Models.User;
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
        User u = getClientUserObject(getIdFromCookie(cookieUserCode.getValue()));

        String div = "<div id=settings_block>" +
                (u.getStatus().equals("admin") ? "<div id=settings_type_menu>" +
                        "<div class=settings_type id=settings_type_profile>Profile</div>" +
                        "<div class=settings_type id=settings_type_users>Users</div>" +
                        "<div class=settings_type id=settings_type_orders>Orders</div>" +
                        "<div id=settings_type_line></div>" +
                    "</div>" : "") +
                    "<div class=settings_type_box id=settings_profile>" +
                        "<p class=setting_parameter_name><b>Change name</b></p>" +
                        "<input type=text class=settings_input id=settings_name placeholder=\"New name\" style=\"margin-top:-8px\" value=\"Ilia\" />" +
                        "<input type=text class=settings_input id=settings_surname placeholder=\"New surname\" value=\"Pro\" />" +
                        "<p class=setting_parameter_name style=\"margin-top:16px\"><b>Change password</b></p>" +
                        "<input type=password class=settings_input id=settings_current_password placeholder=\"Current password\" style=\"margin-top:-8px\" />" +
                        "<input type=password class=settings_input id=settings_new_password placeholder=\"New password\" />" +
                        "<p class=setting_parameter_name style=\"margin-top:16px\"><b>Change adress</b></p>" +
                        "<input type=text class=settings_input id=settings_adress placeholder=\"Your adress\" style=\"margin-top:-8px\" value=\"Moi adress ne dom i ne ulitsa\" />" +
                        "<p class=setting_parameter_name style=\"margin-top:16px\"><b>Change phone number</b></p>" +
                        "<input type=text class=settings_input id=settings_phone placeholder=\"Phone number\" style=\"margin-top:-8px\" value=\"8 800 555 35 35\" />" +
                        "<p class=setting_parameter_name style=\"margin-top:16px\"><b>Your id is 228</b></p>" +
                        "<p class=setting_parameter_name style=\"margin-top:16px\"><b>Your type is ilososka</b></p>" +
                        "<div id=settings_profile_save>Save</div>" +
                    "</div>" +
                    createListOfUsersBlock(getAllUsers()) +
                    /*"<div class=settings_type_box id=settings_users>" +
				    "<div id=new_user>+ Add a new user</div>" +
				        "<div class=settings_list_users>" +
					        "<img src=1.jpg width=100px height=100px class=settings_users_list_avatar />" +
					        "<div class=settings_users_list_specs_box>" +
						        "<b style=\"text-decoration:underline;\">Ilia Pro</b></br>" +
						        "<b>Status:</b> Pizdat</br>" +
						        "<b>Chlen:</b> Bolshoi" +
                            "</div>" +
					        "<div class=settings_users_list_modify id=228>Modify</div>" +
				        "</div>" +
			        "</div>" +*/
                    createListOfOrdersBlock(getAllOrders()) +
                    /*"<div class=settings_type_box id=settings_orders>" +
                        "<div class=settings_list_orders>" +
                            "<img src=1.jpg width=100px height=100px class=settings_orders_list_avatar />" +
                            "<div class=settings_orders_list_specs_box>" +
                                "<b style=\"text-decoration:underline;\">3 pigs</b></br>" +
                                "#book #chtenie #hashtag</br>" +
                                "<b>script:</b> Zaebis" +
                            "</div>" +
                            "<div class=settings_orders_list_modify id=228>Modify</div>" +
                        "</div>" +
                    "</div>" +*/
                "</div>";
    
        return div;
    }
}
