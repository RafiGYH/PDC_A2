/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * DatabaseUtility.Java - Repsonsible for managing the database and its associated functions. 
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created October 2023
 */
package pdcassignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {

    private static final String DB_URL = "jdbc:derby:pdc_a2;create=true";
    private static Connection conn = null;

    // Establish connection to the database
    public static Connection connect() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB_URL);
        }
        return conn;
    }

    // Close the database connection
    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static void shutdownDatabase() throws SQLException {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException se) {
            if (!se.getSQLState().equals("XJ015")) {
                throw se;
            }
        }
    }
    
    /* Before exiting the application
    try {
        DatabaseUtility.shutdownDatabase();
    } catch (SQLException e) {
        e.printStackTrace();
    }*/

    // TODO: Add methods for database interactions (CRUD operations)
    
}