<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Item" %>
<%@ page import="db.Country" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container mt-3">
            <div class="row mt-5">
                <div class="col-6 mx-auto">
                    <form action="/sign-in" method="post">
                        <div class="row">
                            <div class="col-12">
                                <label>EMAIL : </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <input type="email" class="form-control" name="email" required placeholder="Email">
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12">
                                <label>PASSWORD : </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <input type="password" class="form-control" name="password" required placeholder="Password">
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12">
                                <button class="btn btn-success">SIGN IN</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>