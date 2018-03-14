<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="name" value="${pageContext.request.getParameter('name')}"/>

<!DOCTYPE html>
<html>
    <link href="${contextPath}/resources/style/more.css" rel="stylesheet">
    <body>
        <div id="usercard">
            <div id="usercard_avatar" class="blocks"></div>
            <div class="blocks" id="usercard_info">
                <p id="name">${name}</p>
                <p id="settings_bottom">Settings</p>
                <p class="usercard_info_text1" style="margin-top:-8px">Status:</p>
                <p class="usercard_info_text1" style="margin-top:22px">fine:</p>
                <p class="usercard_info_text1" style="margin-top:52px">Chlen:</p>
                <p class="usercard_info_text2" style="margin-top:-8px">Student</p>
                <p class="usercard_info_text2" style="margin-top:22px">228$</p>
                <p class="usercard_info_text2" style="margin-top:52px">Bolshoi</p>
            </div>
            <div class="blocks" id="history">
                <div class="line">
                    <div class="books" style="margin-left:-5px">
                        <img src="/resources/img/books/1.jpg" width="190px" height="289px" />
                        <p class="bookname">3 pigs</p>
                    </div>
                    <div class="books" style="margin-left:193px">
                        <img src="/resources/img/books/1.jpg" width="190px" height="289px" />
                        <p class="bookname">3 pigs</p>
                    </div>
                    <div class="books" style="margin-left:391px">
                        <img src="/resources/img/books/1.jpg" width="190px" height="289px" />
                        <p class="bookname">3 pigs</p>
                    </div>
                    <div class="books" style="margin-left:589px">
                        <img src="/resources/img/books/1.jpg" width="190px" height="289px" />
                        <p class="bookname">3 pigs</p>
                    </div>
                </div>
            </div>
        </div>
    <!-- /container -->
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
    </body>
</html>