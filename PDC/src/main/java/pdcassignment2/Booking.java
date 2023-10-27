/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * Booking.Java - Responsible for assembly of user input
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

import java.sql.SQLException;

public class Booking {

    private int id;
    private String movieTitle;
    private String showTime;
    private String ticketType;
    private int ticketQuantity;
    private String fullName;
    private String phoneNumber;
    private String email;
    private double totalPrice;
    private int childTicketQuantity;
    private int adultTicketQuantity;
    private PromoCode promoCode;
    
    public Booking() {
        // Default constructor
    }

    public Booking(String movieTitle, String showTime, String ticketType, int ticketQuantity,
            String fullName, String phoneNumber, String email, double totalPrice) {
        this.movieTitle = movieTitle;
        this.showTime = showTime;
        this.ticketType = ticketType;
        this.ticketQuantity = ticketQuantity;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.totalPrice = totalPrice;
    }

    public boolean create() throws DatabaseException {
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        return dbUtil.insertBooking(this);
    }

    public static Booking read(int id) throws DatabaseException {
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        return dbUtil.getBookingByID(id);
    }

    public boolean update() throws DatabaseException {
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        return dbUtil.updateBooking(this);
    }

    public boolean delete() throws DatabaseException {
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        return dbUtil.deleteBooking(id);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public int getChildTicketQuantity() {
        return childTicketQuantity;
    }

    public void setChildTicketQuantity(int childTicketQuantity) {
        this.childTicketQuantity = childTicketQuantity;
    }

    public PromoCode getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCode promoCode) {
        this.promoCode = promoCode;
    }
    
    public int getAdultTicketQuantity() {
        return adultTicketQuantity;
    }
    
    public int getTotalTickets() {
        return ticketQuantity;
    }

    @Override
    public String toString() {
        return "Booking ID: " + id + "\n"
                + "Name: " + fullName + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Email: " + email + "\n"
                + ticketQuantity + "x " + ticketType + " Tickets for " + movieTitle + " at " + showTime + "\n"
                + "Total Price: $" + totalPrice;
    }

    public static Booking fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length != 8) {
            return null;
        }
        Booking booking = new Booking();
        booking.id = Integer.parseInt(parts[0].trim());
        booking.fullName = parts[1].trim();
        booking.phoneNumber = parts[2].trim();
        booking.email = parts[3].trim();
        booking.showTime = parts[4].trim();
        booking.movieTitle = parts[5].trim();
        booking.ticketType = parts[6].trim();
        booking.ticketQuantity = Integer.parseInt(parts[7].trim());
        return booking;
    }
}
