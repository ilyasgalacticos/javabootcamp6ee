<%@ page import="db.User" %><%
  User currentUser = (User) session.getAttribute("currentUser");
%>
<div class="container">
  <div class="row">
    <div class="col-12">
      <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #1e319b;">
        <div class="container-fluid">
          <a class="navbar-brand" href="/">Techno Shop</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
              </li>
              <%
                if(currentUser!=null){
              %>
              <li class="nav-item">
                <a class="nav-link" href="/add-item"> + Add Item</a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="/profile"> <%=currentUser.getFullName()%></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="/sign-out"> Sign Out</a>
                </li>
              <%
                }else{
              %>
                <li class="nav-item">
                  <a class="nav-link" href="/sign-in"> Sign In</a>
                </li>
              <%
                }
              %>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  </div>
</div>