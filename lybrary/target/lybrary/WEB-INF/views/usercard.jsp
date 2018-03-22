<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv=Content-Type content=text/html; charset=utf-8/>
        <%--<meta http-equiv=X-UA-Compatible content=IE=edge>--%>
        <%--<meta name=viewport content=width=device-width, initial-scale=1>--%>
        <%--<meta name=description content=>--%>
        <%--<meta name=author content=>--%>
        <%--<link rel=shortcut icon href=${contextPath}/resources/img/favicon.ico type=image/x-icon>--%>
        <meta http-equiv=Content-Type content=text/html; charset=utf-8/>
        <script type=text/javascript src=${contextPath}/resources/scripts/jquery.js></script>
        <script type=text/javascript src=${contextPath}/resources/scripts/main20.js></script>
        <script type="text/javascript" src="${contextPath}/resources/scripts/for_all.js"></script>
        <link href=${contextPath}/resources/style/main20.css rel=stylesheet>
        <style id=style20></style>
        <title>BookBooking</title>
    </head>
    <body>
        <div id=main>
            <p id=topic>BookBooking</p>
        </div>
        <div id=more_box20>
            <div id=usercard>
                <div id=usercard_avatar class=blocks></div>
                <div class=blocks id=usercard_info>
                    <p id=name>${name}</p>
                    <p id=settings_bottom>Settings</p>
                    <p class=usercard_info_text1 style=margin-top:-8px>Status:</p>
                    <p class=usercard_info_text1 style=margin-top:22px>fine:</p>
                    <p class=usercard_info_text1 style=margin-top:52px>Address:</p>
                    <p class=usercard_info_text1 style=margin-top:82px>Phone:</p>
                    <p class=usercard_info_text1 style=margin-top:112px>Card Id:</p>
                    <p class=usercard_info_text2 style=margin-top:-8px>${status}</p>
                    <p class=usercard_info_text2 style=margin-top:22px>${fine}</p>
                    <p class=usercard_info_text2 style=margin-top:52px>${address}</p>
                    <p class=usercard_info_text2 style=margin-top:82px>${phone}</p>
                    <p class=usercard_info_text2 style=margin-top:112px>${id}</p>
                </div>
            </div>
            <div class=blocks id=history>
                <div class=line>
                    ${booki}
                </div>
            </div>
        </div>
        <div id=main_menu>
            <div class=menu_blocks id=first_menu_block></div>
            <div class=menu_blocks id=second_menu_block></div>
            <div class=menu_blocks id=third_menu_block></div>
        </div>
        <div id=avatar></div>
        <div class=menu_points id=booking_system>Booking</div>
        <div class="menu_points" id="settings">Settings</div>
        <div class="menu_points" id="exit">Exit</div>
    <!-- /container -->
    <%--<script src=https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js></script>--%>
    <%--<script src=${contextPath}/resources/js/bootstrap.min.js></script>--%>
    </body>
</html>