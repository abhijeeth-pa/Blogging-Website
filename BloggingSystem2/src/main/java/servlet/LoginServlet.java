package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // MySQL database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/blogging_system";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Abhi@2005";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Prepare SQL query to retrieve username and password from users table
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query
            rs = stmt.executeQuery();

            // Check if user exists
            if (rs.next()) {
                // Create a User object and set it in the session
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password")); // Ideally, you wouldn't store the password in the session

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Redirect to the write blog page
                response.sendRedirect("writeBlog.jsp");
            } else {
                // Display error message if authentication fails
                out.println("<html><body><p>Authentication failed. Invalid username or password.</p></body></html>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle any errors
            e.printStackTrace();
            out.println("<html><body><p>An error occurred while processing your request.</p></body></html>");
        } finally {
            // Close all database resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
