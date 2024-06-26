package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import dao.BlogDAO;
import java.util.List;
import model.Blog;

@WebServlet("/viewAllBlogs")
public class ViewAllBlogsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if user is not authenticated
            return;
        }

        List<Blog> blogs = BlogDAO.getAllBlogs();
        request.setAttribute("blogs", blogs);
        request.getRequestDispatcher("viewAllBlogs.jsp").forward(request, response);
    }
}
