/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * DatabaseUtility.Java - Repsonsible for managing the database and its associated functions. 
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created October 2023
 */

//INSERT INTO Bookings (FullName, PhoneNumber, Email, BookingTime, Movie, CinemaType, TotalTickets, TotalPaid)
//VALUES ('John Doe', '123456789', 'john@example.com', '2023-10-03 14:00:00', 'Movie Title', 'Standard', 3, 30.00);

package pdcassignment2;

import java.sql.*;

public class DatabaseUtility {

    private static final String DB_URL = "jdbc:derby:pdc_a2;create=true";
    private static Connection conn = null;

    // Establish connection to the database
    public static Connection connect() throws DatabaseException {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DB_URL);
            }
            return conn;
        } catch (SQLException e) {
            throw new DatabaseException("Error connecting to the database", e);
        }
    }
    
    public static void initialiseDatabase() throws DatabaseException {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            
            // Check if the Bookings table exists
            ResultSet rs = conn.getMetaData().getTables(null, null, "BOOKINGS", null);
            
            if (!rs.next()) {
                // Table does not exist, create it
                String createTableSQL = "CREATE TABLE Bookings (" +
                        "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                        "FullName VARCHAR(255)," +
                        "PhoneNumber VARCHAR(15)," +
                        "Email VARCHAR(255)," +
                        "BookingTime TIMESTAMP," +
                        "Movie VARCHAR(255)," +
                        "CinemaType VARCHAR(50)," +
                        "TotalTickets INT," +
                        "TotalPaid DECIMAL(10, 2)" +
                        ")";
                stmt.executeUpdate(createTableSQL);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error initializing the database", e);
        }
    }

    // Close the database connection
    public static void closeConnection() throws DatabaseException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error closing the database connection", e);
        }
    }

    public static void shutdownDatabase() throws DatabaseException {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException se) {
            if (!se.getSQLState().equals("XJ015")) {
                throw new DatabaseException("Error shutting down the database", se);
            }
        }
    }
    
    // TODO: Add methods for database interactions (CRUD operations)
    
}