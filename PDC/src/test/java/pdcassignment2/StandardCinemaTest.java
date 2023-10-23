package pdcassignment2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StandardCinemaTest {
    
    // Check if the getCinemaName() method returns the correct cinema name
    @Test
    public void testGetCinemaName() {   
        StandardCinema cinema = new StandardCinema("Standard 1", 100);
        assertEquals("Standard 1", cinema.getCinemaName(), "Cinema name should be 'Standard 1'");
    }

    // Check if the getTotalSeats() method returns the correct total number of seats
    @Test
    public void testGetTotalSeats() {
        StandardCinema cinema = new StandardCinema("Standard 1", 100);
        assertEquals(100, cinema.getTotalSeats(), "Total seats should be 100");
    }

    @Test
    public void testGetBookingLimit() {
        // Check if the getBookingLimit() method returns the correct booking limit for a known cinema
        StandardCinema cinema1 = new StandardCinema("Cinema 1", 100);
        assertEquals(60, cinema1.getBookingLimit(), "Booking limit for Cinema 1 should be 60");

        // Check if the getBookingLimit() method throws an exception for an unknown cinema
        StandardCinema unknownCinema = new StandardCinema("Unknown Cinema", 100);
        assertThrows(IllegalArgumentException.class, unknownCinema::getBookingLimit, "Should throw IllegalArgumentException for unknown cinema");
    }

    // Check if the bookSeats() method works correctly
    @Test
    public void testBookSeats() {
        StandardCinema cinema = new StandardCinema("Standard 1", 100);
        assertDoesNotThrow(() -> cinema.bookSeats(10), "Should be able to book 10 seats");
        assertEquals(10, cinema.getBookedSeats(), "Booked seats should be 10");

        assertThrows(BookingException.class, () -> cinema.bookSeats(-10), "Should throw BookingException for negative number of seats");
        assertThrows(BookingException.class, () -> cinema.bookSeats(200), "Should throw BookingException when trying to book more than available seats");
    }
}