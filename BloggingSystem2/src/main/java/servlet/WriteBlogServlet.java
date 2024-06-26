package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.Blog;
import dao.BlogDAO;
@WebServlet("/writeBlog")

public class WriteBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Blog blog = new Blog();
        blog.setUserId(user.getId());
        blog.setTitle(title);
        blog.setContent(content);

        boolean success = BlogDAO.addBlog(blog);

        if (success) {
            response.sendRedirect("viewAllBlogs.jsp");
        } else {
            response.sendRedirect("writeBlog.jsp");
        }
    }
}
