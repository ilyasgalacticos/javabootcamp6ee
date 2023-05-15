<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <%
        for (int i = 0; i < 10; i++) {
    %>
    <h1>
        Hello Java EE
    </h1>
    <%
        }
    %>
    <%
        for (int i = 0; i < 10; i++) {
    %>
        <h3>Hello my <%=i+"-"+i%></h3>
    <%
        }
    %>
</div>
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>NAME</th>
                    <th>PRICE</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>