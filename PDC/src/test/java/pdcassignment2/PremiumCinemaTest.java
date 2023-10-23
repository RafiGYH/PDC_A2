/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * PremiumCinemaTest.Java - Responsible for testing numerus functions related to the premium cinema class
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created October 2023
 */
package pdcassignment2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PremiumCinemaTest {

    // Checks if the getCinemaName method returns the correct cinema name
    @Test
    public void testGetCinemaName() {
        PremiumCinema cinema = new PremiumCinema("Premium 1", 80, 20);
        assertEquals("Premium 1", cinema.getCinemaName(), "Cinema name should be 'Premium 1'");
    }
    
    // Check if the getTotalSeats() method returns the correct total number of seats
    @Test
    public void testGetTotalSeats() {
        PremiumCinema cinema = new PremiumCinema("Premium 1", 80, 20);
        assertEquals(100, cinema.getTotalSeats(), "Total seats should be 100");
    }

    // Check if the getTotalPremiumSeats() method returns the correct total number of premium seats
    @Test
    public void testGetTotalPremiumSeats() {
        PremiumCinema cinema = new PremiumCinema("Premium 1", 80, 20);
        assertEquals(20, cinema.getTotalPremiumSeats(), "Total premium seats should be 20");
    }

    // Check if the getBookingLimit() method returns the correct booking limit
    @Test
    public void testGetBookingLimit() {
        PremiumCinema cinema = new PremiumCinema("Premium 1", 80, 20);
        assertEquals(145, cinema.getBookingLimit(), "Booking limit for Premium cinema should be 145");
    }
    
    // Check if the bookPremiumSeats() method works correctly
    @Test
    public void testBookPremiumSeats() {
        PremiumCinema cinema = new PremiumCinema("Premium 1", 80, 20);
        assertDoesNotThrow(() -> cinema.bookPremiumSeats(10), "Should be able to book 10 premium seats");
        assertEquals(10, cinema.getBookedPremiumSeats(), "Booked premium seats should be 10");

        assertThrows(BookingException.class, () -> cinema.bookPremiumSeats(30), "Should throw BookingException when trying to book more than available premium seats");
    }
}