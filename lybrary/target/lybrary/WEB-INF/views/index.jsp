<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
        <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
        <%--<meta name="description" content="">--%>
        <%--<meta name="author" content="">--%>
        <%--<link rel="shortcut icon" href="${contextPath}/resources/img/favicon.ico" type="image/x-icon">--%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <script type="text/javascript" src="${contextPath}/resources/scripts/jquery.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/scripts/main.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/scripts/for_all.js"></script>
        <link href="${contextPath}/resources/style/main.css" rel="stylesheet">
        <style id="style20"></style>
        <title>BookBooking</title>
    </head>
    <body>
        <div id="main">
            <p id="topic">BookBooking</p>
            <div id="main_button_box">
                <div id="enter_error">Forgot your password?</div>
                <div id="enter_error_back"></div>
                <div id="enter_inputs_box">
                    <input type="email" class="enter_inputs" id="enter_email" placeholder="email">
                    <input type="password" class="enter_inputs" id="enter_password" placeholder="Password">
                </div>
                <div class="main_buttons" id="enter">Sign in</div>
                <div id="reg_inputs_box">
                    <div class="error" id="reg_name_error"></div>
                    <div class="error" id="reg_surname_error"></div>
                    <div class="error" id="reg_email_error"></div>
                    <div class="error" id="reg_password_error"></div>
                    <div class="error" id="reg_password20_error"></div>
                    <div class="error_more" id="error_more_name">
                        <div class="error_more_arrow"></div>
                        The name must not be longer than 20 characters and must be written in English
                    </div>
                    <div class="error_more" id="error_more_surname">
                        <div class="error_more_arrow"></div>
                        The surname must not be longer than 20 characters and must be written in English
                    </div>
                    <div class="error_more" id="error_more_email">
                        <div class="error_more_arrow"></div>
                        email must end with "@innopolis.ru"
                    </div>
                    <div class="error_more" id="error_more_password">
                        <div class="error_more_arrow"></div>
                        Password must be at least 8 letters, but no more than 21. It can include English letters, digits and special characters
                    </div>
                    <div class="error_more" id="error_more_password20">
                        <div class="error_more_arrow"></div>
                        Both passwords must be the same
                    </div>
                    <input type="text" class="reg_inputs" id="reg_name" placeholder="Name">
                    <input type="text" class="reg_inputs" id="reg_surname" placeholder="Surname">
                    <input type="email" class="reg_inputs" id="reg_email" placeholder="email">
                    <input type="password" class="reg_inputs" id="reg_password" placeholder="Password">
                    <input type="password" class="reg_inputs" id="reg_password20" placeholder="Repeat password">
                </div>
                <div class="main_buttons" id="reg">Sign up</div>
                <div id="reg_error">Ooops! This email already exists!</div>
            </div>
            <div id="more_box">
                Learn more
                <img src="${contextPath}/resources/img/arrow_down.png" id="more_arrow" />
            </div>
        </div>
        <div id="plus_box"></div>
        <div id="more_box20"></div>
        <div id="main_menu">
            <div class="menu_blocks" id="first_menu_block"></div>
            <div class="menu_blocks" id="second_menu_block"></div>
            <div class="menu_blocks" id="third_menu_block"></div>
        </div>
        <div id="avatar"></div>
        <div class="menu_points" id="booking_system">Booking</div>
        <div class="menu_points" id="settings">Settings</div>
        <div class="menu_points" id="exit">Exit</div>
    <!-- /container -->
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
    </body>
</html>