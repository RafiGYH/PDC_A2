/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * BookingSystem.Java - Assembles the program and contains the main method
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall #20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

import java.sql.SQLException;
import java.util.*;

public class BookingSystem {

    public static void main(String[] args) throws SQLException, DatabaseException {

        Scanner scan = new Scanner(System.in);
        // Initialize cinemas, movies, shows, menu
        StandardCinema cinema1 = new StandardCinema("Cinema 1", 60);
        StandardCinema cinema2 = new StandardCinema("Cinema 2", 90);
        PremiumCinema cinema3 = new PremiumCinema("Cinema 3", 90, 55);

        Show show1 = new Show("Chrono Nexu", "11:30 AM", cinema1, 100);
        Show show2 = new Show("Whispers in the Mist", "3:00 PM", cinema2, 100);
        Show show3 = new Show("Skyward Odyssey", "2:00 PM", cinema3, 100);

        // Initialize the menu
        Menu menu = new Menu();
        
        // Initialize the database
        DatabaseUtility.initialiseDatabase();

        while (true) {
            try {
                // First Screen: Welcome screen
                int mainChoice = menu.displayMainMenu();
                switch (mainChoice) {
                    case 1:
                        // Second Screen: Existing booking Query
                        menu.askForPhoneNumber();

                        Booking foundBooking = DatabaseUtility.findBookingByPhoneNumber(menu.getPhoneNumber());
                        if (foundBooking != null) {
                            // Display booking details
                            System.out.println("Booking found: " + foundBooking.toString());
                        } else {
                            System.out.println("No booking found for the given phone number.");
                        }
                        break;
                    case 2:
                        // Third Screen: Show selection
                        int movieChoice = menu.displayMovieChoices();
                        Movie selectedMovie = DatabaseUtility.getAllMovies().get(movieChoice - 1);

                        List<Show> availableShows = new ArrayList<>(DatabaseUtility.getShowsByMovie(selectedMovie));

                        // Fourth Screen: Ticket type
                        menu.displayTicketMenu();
                        BookingCalculator bookingCalculator = new BookingCalculator();
                        bookingCalculator.getUserInputAndCalculateTotal();

                        menu.askForName();
                        menu.askForPhoneNumber();
                        menu.askForEmail();

                        // Fifth Screen: Booking Details
                        menu.displayBookingConfirmation(bookingCalculator);

                        // Sixth Screen: Save/confirm booking
                        Show selectedShow = menu.selectShow(availableShows);
                        Booking newBooking = new Booking(
                                menu.getFullName(), menu.getPhoneNumber(), menu.getEmail(),
                                selectedShow.getTime(), selectedMovie.getTitle(),
                                selectedShow.getCinemaType(), bookingCalculator.getTotalPrice()
                        );
                        DatabaseUtility.insertBooking(newBooking);
                        System.out.println("\n------------------\nBooking Confirmed\nSee you soon \n------------------\n");
                        System.exit(0);
                        break;
                    case 3:
                        // Quit program
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (DatabaseException e) {
                e.printStackTrace(); // Log the exception
                // Handle the exception, e.g., show an error message to the user
                System.out.println("A database error occurred: " + e.getMessage());
            } catch (BookingException e) {
                System.out.println("Booking error: " + e.getMessage());
            }
        }
    }
}