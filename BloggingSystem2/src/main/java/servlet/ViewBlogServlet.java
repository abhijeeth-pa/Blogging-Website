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

@WebServlet("/viewBlog")

public class ViewBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

       
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        Blog blog = BlogDAO.getBlogById(blogId);

        if (blog == null) {
            response.getWriter().println("Blog not found");
            return;
        }

        BlogDAO.incrementViewCount(blogId, user.getId());
        request.setAttribute("blog", blog);
        request.getRequestDispatcher("viewBlog.jsp").forward(request, response);
    }
}
