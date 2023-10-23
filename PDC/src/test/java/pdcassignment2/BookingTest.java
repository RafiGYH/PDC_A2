/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * BookingTest.Java - Responsible for testing if booking objects are created correctly
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created October 2023
 */
package pdcassignment2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BookingTest {

    // Checks if the Booking object is created correctly
    @Test
    public void testBookingCreation() {
        Booking booking = new Booking("Movie Title", "Show Time", "Ticket Type", 1, "Full Name", "Phone Number", "Email", 100.0);
        assertEquals("Movie Title", booking.getMovieTitle(), "Movie title should be 'Movie Title'");
        assertEquals("Show Time", booking.getShowTime(), "Show time should be 'Show Time'");
        assertEquals("Ticket Type", booking.getTicketType(), "Ticket type should be 'Ticket Type'");
        assertEquals(100.0, booking.getTotalPrice(), "Total price should be 100.0");
    }
}
