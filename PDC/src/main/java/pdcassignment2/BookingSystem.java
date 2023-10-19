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

        // Create Movie objects
        Movie chronoNexu = new Movie("Chrono Nexu", "A physicist becomes trapped in a maze of alternative realities as a result of "
                + "his research on alternate universes. In this surreal sci-fi thriller"
                + " the protagonist races against time to discover the secret of the portals.");
        Movie whispersInTheMist = new Movie("Whispers in the Mist", "Dark secrets in a mountain town are revealed by eerie whispers from an endless mist."
                + "To escape the gripping psychological nightmare, strangers must face their pasts and one another.");
        Movie skywardOdyssey = new Movie("Skyward Odyssey", "A diverse crew navigates cosmic obstacles in pursuit of a new home while the Earth disintegrates."
                + "\"Skyward Odyssey\" is a grand story about cooperation, hope, and interstellar travel.");

        // Create Show objects using the Movie objects
        Show show1 = new Show(chronoNexu, "11:30 AM", cinema1, 100);
        Show show2 = new Show(whispersInTheMist, "3:00 PM", cinema2, 100);
        Show show3 = new Show(skywardOdyssey, "2:00 PM", cinema3, 100);

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
                        List<Movie> movies = DatabaseUtility.getAllMovies(); // Retrieve movies from the database
                        int movieChoice = menu.displayMovieChoices(movies);  // Pass the movies list to the method
                        Movie selectedMovie = movies.get(movieChoice - 1);

                        // Retrieve the list of shows for the selected movie
                        List<Show> availableShows = DatabaseUtility.getShowsByMovie(selectedMovie);

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
                        String cinemaType = selectedShow.getCinema().getClass().getSimpleName();
                        int ticketQuantity = bookingCalculator.getTotalTickets();
                        Booking newBooking = new Booking(
                                selectedMovie.getTitle(),
                                selectedShow.getTime(),
                                cinemaType,
                                ticketQuantity,
                                menu.getFullName(),
                                menu.getPhoneNumber(),
                                menu.getEmail(),
                                bookingCalculator.getTotalPrice()
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
            }
        }
    }
}
