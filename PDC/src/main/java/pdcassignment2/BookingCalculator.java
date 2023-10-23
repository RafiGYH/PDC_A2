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

import java.text.DecimalFormat;
import java.util.*;

public class BookingCalculator {

    //Standard tickets
    private static final double ADULT_TICKET_PRICE = 15.0;
    private static final double CHILD_TICKET_PRICE = 10.0;
    //Premium tickets (+10)
    private static final double PREMIUM_ADULT_TICKET_PRICE = 25.0;
    private static final double PREMIUM_CHILD_TICKET_PRICE = 20.0;

    double totalPrice;
    double discountAmount;
    private int totalTickets = 0;

    // Calculate total price with the number of adults, children, cinema type, and promo code
    public double calculateTotalPrice(int numAdults, int numChildren, PromoCode promoCode, Cinema cinema) {
        
        if (numAdults < 0 || numChildren < 0) {
            return 0.0; // Return $0 for negative ticket numbers
        }

        try {
            if (cinema instanceof StandardCinema) {
                double totalAdultPrice = numAdults * ADULT_TICKET_PRICE;
                double totalChildPrice = numChildren * CHILD_TICKET_PRICE;
                totalPrice = totalAdultPrice + totalChildPrice;
                setTicketType("Standard");
            } else if (cinema instanceof PremiumCinema) {
                PremiumCinema premiumCinema = (PremiumCinema) cinema;
                double premiumAdultSeatPrice = numAdults * PREMIUM_ADULT_TICKET_PRICE;
                double premiumChildSeatPrice = numChildren * PREMIUM_CHILD_TICKET_PRICE;
                totalPrice = premiumAdultSeatPrice + premiumChildSeatPrice;
                setTicketType("Premium");
            }

            if (promoCode != null) {
                totalPrice = promoCode.discount(totalPrice);
                if (totalPrice < 0) {
                    totalPrice = 0; // Ensure total price is not negative after applying discount
                }
            }

            return totalPrice;
        } catch (ArithmeticException e) {
            System.out.println("An error occurred during price calculation: " + e.getMessage());
            return 0.0;
        }
    }


    public String ticketType;

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketType() {
        return ticketType;
    }

// Get user input, then calculate and display total price
    public void getUserInputAndCalculateTotal() {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat currencyFormat = new DecimalFormat("#0.00");

        while (totalTickets == 0) { // Keep looping until at least 1 ticket
            try {
                System.out.print("Number of adult tickets required? ");
                int numAdults = scanner.nextInt();
                System.out.print("Number of child tickets required? ");
                int numChildren = scanner.nextInt();

                // Calculate the total number of tickets
                setTotalTickets(numAdults + numChildren);

                // Cinema typee
                System.out.print("Enter the cinema type (1 for Standard, 2 for Premium): ");
                int cinemaChoice = scanner.nextInt();

                Cinema cinema;
                switch (cinemaChoice) {
                    case 1:
                        cinema = new StandardCinema("Standard Cinema", 90);
                        break;
                    case 2:
                        cinema = new PremiumCinema("Premium Cinema", 90, 55);
                        break;
                    default:
                        cinema = new StandardCinema("Standard Cinema", 90);
                        break;
                }

                int availableSeats = cinema.getAvailableSeats();

                if (totalTickets <= availableSeats) {
                    // Get promo code input from user
                    PromoCode promoCode = getPromoCodeFromUser(scanner);

                    // Calculate total price with promo code
                    double totalPrice = calculateTotalPrice(numAdults, numChildren, promoCode, cinema);

                    // Display results
                    System.out.println("Quantity of ADULT tickets: " + numAdults);
                    System.out.println("Quantity of CHILD tickets: " + numChildren);

                    // Check if a promo code is applied and calculate the discounted price
                    if (promoCode != null) {
                        double discountedPrice = promoCode.discount(totalPrice);
                        System.out.println("Discounted Total Price for " + totalTickets + " tickets is $" + currencyFormat.format(discountedPrice));
                    } else {
                        System.out.println("Total Price for " + totalTickets + " tickets is $" + currencyFormat.format(totalPrice));
                    }
                    setTotalPrice(totalPrice);

                } else {
                    System.out.println("Sorry, there are not enough seats available!");
                    totalTickets = 0; //Reloop
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (NoSuchElementException e) {
                System.out.println("No input provided. Please try again.");
            }
        }
    }

// Valid promo codes
    private boolean isValidPromoCode(String promoCodeValue) {
        List<String> validPromoCodes = Arrays.asList("F5", "F2", "F10", "P10", "P20", "P5", "P2");
        return validPromoCodes.contains(promoCodeValue);
    }

// Request promo code
    private PromoCode getPromoCodeFromUser(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Do you have a promo code? (yes/no): ");
                String promoCodeAnswer = scanner.next();

                if (promoCodeAnswer.equalsIgnoreCase("yes")) { // If they have a promo code
                    System.out.print("Please enter the promo code: ");
                    String code = scanner.next().toUpperCase();

                    if (isValidPromoCode(code)) {
                        char codeType = code.charAt(0);
                        double discountAmount = Double.parseDouble(code.substring(1));

                        System.out.println("Promo Code " + code + " Added");

                        // Fixed dollar discount
                        if (codeType == 'F') {
                            return new FixedDiscountPromo(discountAmount);
                        } // Percentage discount
                        else if (codeType == 'P') {
                            return new PercentageDiscountPromo(discountAmount);
                        }
                    } else {
                        System.out.println("\n--------------------\n Invalid Promo Code \n--------------------\n");
                    }
                } else if (promoCodeAnswer.equalsIgnoreCase("no")) {
                    // No promo code - Exit loop
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid response.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid promo code format. Please enter a valid promo code.");
            }
        }

        return null;
    }

    //Getters and Setters
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int quantity) {
        this.totalTickets = quantity;
    }

}
