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
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtility {

    private static final String DB_URL = "jdbc:derby:pdc_a2;create=true";
    private static Connection conn = null;

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

            // Create the Movies table
            rs = conn.getMetaData().getTables(null, null, "MOVIES", null);
            if (!rs.next()) {
                String createMoviesTableSQL = "CREATE TABLE Movies (" +
                        "MovieID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                        "Title VARCHAR(255)," +
                        "Description CLOB" +
                        ")";
                stmt.executeUpdate(createMoviesTableSQL);
            }

            // Create the Shows table
            rs = conn.getMetaData().getTables(null, null, "SHOWS", null);
            if (!rs.next()) {
                String createShowsTableSQL = "CREATE TABLE Shows (" +
                        "ShowID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                        "MovieID INT," +
                        "Time VARCHAR(20)," +
                        "CinemaType VARCHAR(50)," +
                        "AvailableTickets INT," +
                        "FOREIGN KEY (MovieID) REFERENCES Movies(MovieID)" +
                        ")";
                stmt.executeUpdate(createShowsTableSQL);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error initializing the database", e);
        }
    }

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

    public static void insertBooking(Booking booking) throws DatabaseException {
        try (Connection conn = connect()) {
            String sql = "INSERT INTO Bookings(FullName, PhoneNumber, Email, BookingTime, Movie, CinemaType, TotalTickets, TotalPaid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, booking.getFullName());
            stmt.setString(2, booking.getPhoneNumber());
            stmt.setString(3, booking.getEmail());
            stmt.setString(4, booking.getShowTime());
            stmt.setString(5, booking.getMovieTitle());
            stmt.setString(6, booking.getTicketType());
            stmt.setInt(7, booking.getTicketQuantity());
            stmt.setDouble(8, booking.getTotalPrice());
            stmt.executeUpdate(); // Execute the INSERT operation
        } catch (SQLException e) {
            throw new DatabaseException("Error adding a booking", e);
        }
    }

    public static Booking findBookingByPhoneNumber(String phoneNumber) throws DatabaseException {
        try (Connection conn = connect()) {
            String sql = "SELECT * FROM Bookings WHERE PhoneNumber = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phoneNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("ID"));
                booking.setFullName(rs.getString("FullName"));
                booking.setPhoneNumber(rs.getString("PhoneNumber"));
                booking.setEmail(rs.getString("Email"));
                booking.setShowTime(rs.getString("BookingTime"));
                booking.setMovieTitle(rs.getString("Movie"));
                booking.setTicketType(rs.getString("CinemaType"));
                booking.setTicketQuantity(rs.getInt("TotalTickets"));
                booking.setTotalPrice(rs.getDouble("TotalPaid"));
                return booking;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving booking by phone number", e);
        }
    }

    public static void insertMovie(Movie movie) throws DatabaseException {
        if (!movieExists(movie.getTitle())) {
            try (Connection conn = connect()) {
                String sql = "INSERT INTO Movies(Title, Description) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getDescription());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new DatabaseException("Error adding a movie", e);
            }
        } else {
            System.out.println("Movie '" + movie.getTitle() + "' already exists in the database.");
        }
    }

    private static boolean movieExists(String title) throws DatabaseException {
        try (Connection conn = connect()) {
            String sql = "SELECT * FROM Movies WHERE Title = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DatabaseException("Error checking if movie exists", e);
        }
    }

    public static List<Movie> getAllMovies() throws DatabaseException {
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            String selectSQL = "SELECT * FROM Movies";
            ResultSet rs = stmt.executeQuery(selectSQL);
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieID(rs.getInt("MovieID"));
                movie.setTitle(rs.getString("Title"));
                movie.setDescription(rs.getString("Description"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving movies", e);
        }
        return movies;
    }

    public static void insertShow(Show show) throws DatabaseException {
        try (Connection conn = connect()) {
            String sql = "INSERT INTO Shows(MovieID, Time, CinemaType, AvailableTickets) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, show.getMovie().getMovieID());
            stmt.setString(2, show.getTime());
            stmt.setString(3, show.getCinemaType());
            stmt.setInt(4, show.getAvailableTickets());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding a show", e);
        }
    }

    public static List<Show> getShowsByMovie(Movie movie) throws DatabaseException {
        List<Show> shows = new ArrayList<>();
        try (Connection conn = connect()) {
            String sql = "SELECT * FROM Shows WHERE MovieID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, movie.getMovieID());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Show show = new Show(movie);
                show.setShowID(rs.getInt("ShowID"));
                show.setTime(rs.getString("Time"));
                show.setCinemaType(rs.getString("CinemaType"));
                show.setAvailableTickets(rs.getInt("AvailableTickets"));
                shows.add(show);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving shows for a movie", e);
        }
        return shows;
    }
}