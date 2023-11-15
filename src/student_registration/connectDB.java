package student_registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
    
    public static Connection connect(){
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_List;ecrypt=true;trustServerCertificate=true;";
        String username = "dave";
        String password = "1423";

        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(url, username, password);

            // Connection successful
            System.out.println("Connected to the database.");

            // Perform database operations...

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return null;
    }
}