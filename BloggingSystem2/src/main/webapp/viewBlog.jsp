<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Blog" %>
<%@ page import="dao.BlogDAO" %>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int blogId = Integer.parseInt(request.getParameter("blogId"));
    Blog blog = BlogDAO.getBlogById(blogId);

    if (blog == null) {
        out.println("Blog not found");
        return;
    }

    BlogDAO.incrementViewCount(blogId, user.getId());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= blog.getTitle() %></title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>Blogging System</h1>
        </div>
    </header>
    <div class="container">
        <h2><%= blog.getTitle() %></h2>
        <p><%= blog.getContent() %></p>
    </div>
</body>
</html>
