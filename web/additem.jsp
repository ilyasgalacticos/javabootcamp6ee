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
    <div class="container mt-5">
        <div class="row">
            <div class="col-12">
                <h3 class="text-center">
                    <% siteName = "ELECTRONICS KZ"; %>
                    <%=siteName%>
                </h3>
            </div>
        </div>
        <div class="row mt-5">
            <div class="col-6 mx-auto">
                <form action="/add-item" method="post">
                    <div class="row">
                        <div class="col-12">
                            <label>NAME : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <input type="text" class="form-control" name="item_name" required placeholder="Item Name">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>PRICE : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <input type="text" class="form-control" name="item_price" required placeholder="Item Price">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <label>COUNTRY : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <select name="country_id" class="form-select">
                                <%
                                    ArrayList<Country> countries = (ArrayList<Country>) request.getAttribute("strany");
                                    if(countries!=null){
                                        for(Country cnt : countries){
                                %>
                                    <option value="<%=cnt.getId()%>">
                                        <%=cnt.getName() + " / " + cnt.getCode()%>
                                    </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <button class="btn btn-success">ADD ITEM</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>