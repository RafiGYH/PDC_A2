import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BookingTest {

    @Test
    public void testBookingCreation() {
        Booking booking = new Booking("Movie Title", "Show Time", "Seat Number", 100);
        assertEquals("Movie Title", booking.getMovieTitle(), "Movie title should be 'Movie Title'");
        assertEquals("Show Time", booking.getShowTime(), "Show time should be 'Show Time'");
        assertEquals("Seat Number", booking.getSeatNumber(), "Seat number should be 'Seat Number'");
        assertEquals(100, booking.getPrice(), "Price should be 100");
    }
    
    // Add more test methods as needed
}
