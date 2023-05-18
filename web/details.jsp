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
        <div class="col-6 mx-auto">
            <%
                Item item = (Item) request.getAttribute("tovar");
                if (item != null){
            %>
                <div class="row">
                    <div class="col-12">
                        <label>NAME : </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <input type="text" class="form-control"
                               name="item_name" required
                               placeholder="Item Name" value="<%=item.getName()%>" readonly>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>PRICE : </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <input type="text" class="form-control"
                               name="item_price" required
                               placeholder="Item Price" value="<%=item.getPrice()%>" readonly>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>COUNTRY : </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <input type="text" class="form-control"
                               name="item_country" required
                               placeholder="Item Country" value="<%=item.getCountry().getName() + " - " + item.getCountry().getCode()%>" readonly>
                    </div>
                </div>
                <%
                    if(currentUser!=null){
                %>
                    <div class="row mt-3">
                        <div class="col-12">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">
                                EDIT ITEM
                            </button>
                            <button type="button" class="btn btn-danger ms-2" data-bs-toggle="modal" data-bs-target="#deleteModal">
                                DELETE ITEM
                            </button>
                        </div>
                    </div>
                <%
                    }
                %>
                <%
                    if(currentUser!=null){
                %>
                    <div class="modal fade" id="deleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="/delete-item" method="post">
                                    <input type="hidden" name="item_id" value="<%=item.getId()%>">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5">Confirm Delete</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h4 class="text-center">
                                            ARE YOU SURE?
                                        </h4>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">CANCEL</button>
                                        <button class="btn btn-danger">YES</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Modal -->
                    <div class="modal fade" id="editModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="/save-item" method="post">
                                    <input type="hidden" name="item_id" value="<%=item.getId()%>">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit Item</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-12">
                                                <label>NAME : </label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12">
                                                <input type="text" class="form-control"
                                                       name="item_name" required
                                                       placeholder="Item Name" value="<%=item.getName()%>">
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-12">
                                                <label>PRICE : </label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12">
                                                <input type="text" class="form-control"
                                                       name="item_price" required
                                                       placeholder="Item Price" value="<%=item.getPrice()%>">
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
                                                        <option value="<%=cnt.getId()%>" <%=(cnt.getId()==item.getCountry().getId()?"selected":"")%> >
                                                            <%=cnt.getName() + " / " + cnt.getCode()%>
                                                        </option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">CANCEL</button>
                                        <button class="btn btn-primary">UPDATE</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                <%
                    }
                %>
            <%
                }else{
            %>
                <h4 class="text-center">ITEM NOT FOUND</h4>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>