<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Catalog</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<header>
		<% String idString = "0"; %>
		<% if(request.getParameter("librarian_id") != null){
						idString = request.getParameter("librarian_id");
					}%>
		<% if(request.getAttribute("librarian_id") != null){
						idString = request.getAttribute("librarian_id").toString();
					}%>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
        
            <ul class="navbar-nav">
            
                <li class="navbar-brand">
                    <a class="nav-link" href="<%= request.getContextPath() %>/listPatrons?librarian_id=<%= idString%>">Patrons</a>
                </li>
                <li class="navbar-brand">
                    <a class="nav-link" href="<%= request.getContextPath() %>/ListBooksLib?librarian_id=<%= idString%>">Books</a>
                </li>
                <li class="navbar-brand">
                    <a class="nav-link" href="<%= request.getContextPath() %>/history?librarian_id=<%= idString%>">My Account</a>
                </li>
                <li class="navbar-brand">
                    <a class="nav-link" href="<%= request.getContextPath() %>/login">Logout</a>
                </li>
                
            </ul>
            
        </div>
    </nav>
</header>