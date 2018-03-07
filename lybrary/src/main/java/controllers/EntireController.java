package controllers;

import DateBase.DBHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class EntireController extends controllers.Controller{


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(//@RequestParam(value = "name", required = false, defaultValue = "ITP_Project") String name,
                        HttpServletRequest request,
                        @CookieValue(value = "user_code", required = false) Cookie cookieUserCode,
                        Model model) throws SQLException {
        System.out.println(getClientIpAddress(request));
        DBHandler db;
        db = new DBHandler();
        Statement statement = db.getConnection().createStatement();
        String getQuery;
        String name = "Not", surname = "found:(", chlen = "My dick is very very big", fine = "";
        String status,page = "[uuue";
        String booki = "";
        String userId = "",address,phone;
        if (cookieUserCode != null) {
            getQuery = "select * from users where cookieId = '" + cookieUserCode.getValue() + "'";
            ResultSet resultSet = statement.executeQuery(getQuery);
            boolean check = resultSet.next();
            if (!check) return "index";
            name = resultSet.getString("name") + " " + resultSet.getString("surname"); 
            status = resultSet.getString("status");
            userId = resultSet.getString("id");
            fine = resultSet.getString("fine");
            address = resultSet.getString("address");
            phone = resultSet.getString("phone");

            //create page-----
            Statement historyStatement = db.getConnection().createStatement();

            String historyQuery = "select * from orders where userId = '" + userId + "'  AND status = 'open'";
            ResultSet ordersResultSet = statement.executeQuery(historyQuery);
            String title,time;
            long keepingTime;
            int i = 0;
            int margin = -5;
            while (ordersResultSet.next()) {
                i++;
                keepingTime = ordersResultSet.getLong("finishTime");
                booki = booki + "<div class=\"books\" style=\"margin-left:" + margin + "px\"> " +
                        "<div class=books_inside>" + getDate(keepingTime) +
                        "<div class=return_book id="+ordersResultSet.getString("id")+">Return the book</div></div>" +
                        "<img src=\"/resources/img/books/1.jpg\" width=\"190px\" height=\"289px\" /> " +
                        "<p class=\"bookname\">" + "3 PIGS</p> " +
                        "</div>";
                margin += 198;
                if (i % 4 == 0) margin = -5;

            }


            model.addAttribute("name", name);
            model.addAttribute("status", status);
            model.addAttribute("fine", fine+"$");
            model.addAttribute("booki", booki);
            model.addAttribute("address", address);
            model.addAttribute("id", userId);
            model.addAttribute("phone", phone);
            /*if <check>    */

            return "usercard";
        }
//        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String exit(@CookieValue(value = "user_code", required = false) Cookie cookieUserCode,
                       HttpServletResponse response){

        cookieUserCode.setValue("0");
        cookieUserCode.setMaxAge(0);

        return "index";
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "ITP_Project") String name, Model model) {
        model.addAttribute("name", name);
        return "welcome";
    }
}
