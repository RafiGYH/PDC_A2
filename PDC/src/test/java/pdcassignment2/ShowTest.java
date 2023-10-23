package pdcassignment2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShowTest {

    // Checks if the getAvailableSeats method initially returns the correct number of available seats for a show
    @Test
    public void testGetAvailableSeats() {
        Movie movie = new Movie("Inception", "A mind-bending thriller");
        Cinema cinema = new StandardCinema("Standard", 100);
        Show show = new Show(movie, "12:00 PM", cinema, 100);
        assertEquals(100, show.getAvailableSeats(), "Initially, all 100 seats should be available");
    }

    // Checks if the bookSeats method updates the number of seats available after booking correctly
    @Test
    public void testBookSeats() throws BookingException {
        Movie movie = new Movie("Inception", "A mind-bending thriller");
        Cinema cinema = new StandardCinema("Cinema 1", 100);
        Show show = new Show(movie, "12:00 PM", cinema, 100);
        show.bookSeats(10);
        assertEquals(90, show.getAvailableSeats(), "After booking 10 seats, 90 should be available");
    }
    
    // Checks if the isFullyBooked method identifies when a show is fully booked correctly
    @Test
    public void testIsFullyBooked() throws BookingException {
        Movie movie = new Movie("Inception", "A mind-bending thriller");
        Cinema cinema = new StandardCinema("Cinema 1", 100);
        Show show = new Show(movie, "12:00 PM", cinema, 100);
        assertFalse(show.isFullyBooked(), "Initially, the show should not be fully booked");
        show.bookSeats(60); // Book up to the limit
        assertTrue(show.isFullyBooked(), "The show should now be fully booked");
    }

    // Checks if the bookSeats method throws an exception when trying to book more than the booking limit
    @Test
    public void testBookingLimit() throws BookingException {
        Movie movie = new Movie("Inception", "A mind-bending thriller");
        Cinema cinema = new StandardCinema("Cinema 1", 100);
        Show show = new Show(movie, "12:00 PM", cinema, 100);

        assertThrows(BookingException.class, () -> show.bookSeats(70), "Should throw BookingException when trying to book more than the limit");
    }
}
