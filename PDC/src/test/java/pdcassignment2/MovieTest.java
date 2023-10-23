/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * MovieTest.Java - Responsible for testing three functions related to the movie class
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created October 2023
 */
package pdcassignment2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MovieTest {

    // Checks if the hasAvailableShowtimes method  identifies correctly that there are showtimes available for a movie.
    @Test
    public void testHasAvailableShowtimes() {
        Movie movie = new Movie("Inception", "A mind-bending thriller");
        Cinema cinema = new StandardCinema("Standard", 100);
        Show show = new Show(movie, "12:00 PM", cinema, 100);
        movie.addShow(show);
        assertTrue(movie.hasAvailableShowtimes(), "Movie should have available showtimes");
    }
    
    // Checks if a show is successfully added to a movies list of showtimes.
    @Test
    public void testAddShow() {
        Movie movie = new Movie("Inception", "A mind-bending thriller");
        Cinema cinema = new StandardCinema("Standard", 100);
        Show show = new Show(movie, "12:00 PM", cinema, 100);
        movie.addShow(show);
        assertEquals(1, movie.getShows().size(), "Movie should have 1 showtime");
    }
    
    // Checks if the getDescription method correctly returns the movie's description.
    @Test
    public void testGetDescription() {
        Movie movie = new Movie("Inception", "A mind-bending thriller");
        assertEquals("A mind-bending thriller", movie.getDescription(), "Description should match the one provided in constructor");
    }
}