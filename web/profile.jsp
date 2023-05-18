<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Item" %>
<%@ page import="db.Country" %>
<%@ page import="db.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container mt-3">
            <div class="row mt-5">
                <div class="col-12 mx-auto">
                    <h1 class="text-center">
                        HELLO <%=(currentUser!=null?currentUser.getFullName():"NOBODY")%>
                    </h1>
                </div>
            </div>
        </div>
    </body>
</html>