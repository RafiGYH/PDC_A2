/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * Movie.Java - Responsible for the choices of movies
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String title;
    private String description;
    private List<Show> shows;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
        this.shows = new ArrayList<>();
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Method to add a new movie showtime 
    public void addShow(Show show) {
        shows.add(show);
    }

    // Method to get list of showtimes
    public List<Show> getShows() {
        return shows;
    }

    // Method to check if movie has any available showtimes
    public boolean hasAvailableShowtimes() {
        for (Show show : shows) {
            if (show.hasAvailableSeats()) {
                return true;
            }
        }
        return false;
    }

    // Method to get specific showtime
    public Show getShowtime(int index) {
        if (index >= 0 && index < shows.size()) {
            return shows.get(index);
        }
        return null;
    }

    // Method to display available showtimes
    public void displayAvailableShowtimes() {
        System.out.println("Available Showtimes for " + title + ":");
        int index = 1;
        for (Show show : shows) {
            if (show.hasAvailableSeats()) {
                System.out.println(index + ". " + show.getTime() + " (" + show.getAvailableSeats() + " seats available)");
            }
            index++;
        }
    }

    @Override
    public String toString() {
        return "Movie: " + title + "\nDescription: " + description;
    }
}
