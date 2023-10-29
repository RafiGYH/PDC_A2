/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
Booking Calculator.Java - Responsible for the tickets and pricing
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

public class BookingCalculator {

    // Standard tickets
    private static final double ADULT_TICKET_PRICE = 15.0;
    private static final double CHILD_TICKET_PRICE = 10.0;
    // Premium tickets (+10)
    private static final double PREMIUM_ADULT_TICKET_PRICE = 25.0;
    private static final double PREMIUM_CHILD_TICKET_PRICE = 20.0;

    private double totalPrice;
    private int totalTickets = 0;
    private String ticketType;

    public double calculateTotalPrice(int numAdults, int numChildren, PromoCode promoCode, Cinema cinema) {
        if (numAdults < 0 || numChildren < 0) {
            return 0.0; // Return $0 for negative ticket numbers
        }

        double totalAdultPrice = numAdults * getAdultTicketPrice(cinema);
        double totalChildPrice = numChildren * getChildTicketPrice(cinema);

        totalPrice = totalAdultPrice + totalChildPrice;
        if (promoCode != null) {
            totalPrice = promoCode.discount(totalPrice);
            // Ensure total price does not go below 0
            if (totalPrice < 0) {
                totalPrice = 0.0;
            }
        }

        return totalPrice;
    }

    private double getAdultTicketPrice(Cinema cinema) {
        if (cinema instanceof PremiumCinema) {
            setTicketType("Premium");
            return PREMIUM_ADULT_TICKET_PRICE;
        }
        setTicketType("Standard");
        return ADULT_TICKET_PRICE;
    }

    private double getChildTicketPrice(Cinema cinema) {
        if (cinema instanceof PremiumCinema) {
            return PREMIUM_CHILD_TICKET_PRICE;
        }
        return CHILD_TICKET_PRICE;
    }

    // Getters and Setters
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        if (totalTickets < 0) {
            throw new IllegalArgumentException("Total tickets cannot be negative.");
        }
        this.totalTickets = totalTickets;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
