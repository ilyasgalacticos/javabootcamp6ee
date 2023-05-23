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
    <%
        String name = request.getParameter("name");
        String priceFrom = request.getParameter("priceFrom");
        String priceTo = request.getParameter("priceTo");
    %>
    <form action="/" method="get">
        <div class="row">
            <div class="col-3">
                <input type="search" class="form-control"
                       name="name" placeholder="Insert name" value="<%=(name!=null?name:"")%>">
            </div>
            <div class="col-3">
                <select name="country_id" class="form-select">
                    <option value="0">All Countries</option>
                    <%
                        String countryId = request.getParameter("country_id");
                        ArrayList<Country> countries = (ArrayList<Country>) request.getAttribute("strany");
                        if(countries!=null){
                            for(Country cnt : countries){
                    %>
                        <option value="<%=cnt.getId()%>" <%=(countryId!=null&&countryId.equals(cnt.getId()+"")?"selected":"")%> >
                            <%=cnt.getName() + " / " + cnt.getCode()%>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="col-2">
                <input type="number" class="form-control"
                       name="priceFrom" placeholder="Price from" value="<%=(priceFrom!=null?priceFrom:"")%>">
            </div>
            <div class="col-2">
                <input type="search" class="form-control"
                       name="priceTo" placeholder="Price to" value="<%=(priceTo!=null?priceTo:"")%>">
            </div>
            <div class="col-2">
                <button class="btn btn-success w-100">SEARCH</button>
            </div>
        </div>
    </form>
</div>
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>PRICE</th>
                    <th>COUNTRY</th>
                    <th>CREATED BY</th>
                    <th style="width: 10%;">DETAILS</th>
                </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("tovary");
                        if (items != null){
                            for(Item it : items){
                    %>
                        <tr>
                            <td>
                                <%=it.getId()%>
                            </td>
                            <td>
                                <%=it.getName()%>
                            </td>
                            <td>
                                <%=it.getPrice()%>
                            </td>
                            <td>
                                <%=it.getCountry().getName()+" - " + it.getCountry().getCode()%>
                            </td>
                            <td>
                                <%=it.getUser().getFullName()%>
                            </td>
                            <td>
                                <a href="/details?idshka=<%=it.getId()%>" class="btn btn-success btn-sm">DETAILS</a>
                            </td>
                        </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>