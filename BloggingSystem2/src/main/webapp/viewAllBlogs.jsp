<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Blog" %>
<%@ page import="dao.BlogDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Blog> blogs = BlogDAO.getAllBlogs();
    if (blogs == null) {
        out.println("Failed to retrieve blogs. Please try again later.");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Blogs</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>Blogging System</h1>
        </div>
    </header>
    <div class="container">
        <h2>All Blogs</h2>
        <ul>
            <% for (Blog blog : blogs) { %>
                <li>
                    <a href="ViewBlogServlet?blogId=<%= blog.getId() %>"><%= blog.getTitle() %></a>
                </li>
            <% } %>
        </ul>
    </div>
</body>
</html>
