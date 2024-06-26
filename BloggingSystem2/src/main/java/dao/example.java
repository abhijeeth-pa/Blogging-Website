package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class example {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/blogging_system";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Abhi@2005";

    // SQL query to insert data into users table
    private static final String SQL_INSERT = "INSERT INTO users (username, password) VALUES (?, ?)";

    public static void main(String[] args) {
        // Initialize connection, statement, and result set
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to MySQL database
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Prepare the statement to execute the SQL INSERT query
            pstmt = conn.prepareStatement(SQL_INSERT);
            
            // Set parameters for the INSERT statement
            pstmt.setString(1, "john_doe2"); // Replace with actual username
            pstmt.setString(2, "password1234"); // Replace with actual password
            // Replace with actual email
            
            // Execute the INSERT statement
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close the PreparedStatement and Connection
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
