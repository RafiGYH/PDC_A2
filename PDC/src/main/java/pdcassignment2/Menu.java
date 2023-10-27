/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * Menu.Java - Responsible for displaying messages and gathering input from the user
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023

package pdcassignment2;

import java.util.*;

public class Menu {

    //Sidenote: I chose to make this class into methods and not hard code into main due to reuseability
    public Scanner scan;

    String fullName;
    String phoneNumber;
    String email;

   public Menu(){
        scan = new Scanner(System.in);//Side note: Will allow any instantiation to use this when taking a method
    }
    
    public int displayMainMenu(BookingSystemGUI gui)
    {
        String selectedOption = (String) gui.mainMenuOptions.getSelectedItem();
        if("Look up an existing booking".equals(selectedOption)){
            return 1;
        }else if ("Make a new booking".equals(selectedOption)) {
            return 2;
        } else if ("Quit the program".equals(selectedOption)) {
            return 3;
        } else {
            return 0;
        }
    }
    
    //Movie Choices
    public String displayMovieChoices(BookingSystemGUI gui) {
        return (String) gui.movieChoices.getSelectedItem();
    }

    //Date and Time Choices
    public String displayDateTimeChoices(BookingSystemGUI gui) {
        return (String) gui.dateTimeChoices.getSelectedItem();
    }

    //Ticket Count Choices
    public String displayTicketCountChoices(BookingSystemGUI gui) {
        return (String) gui.ticketCountChoices.getSelectedItem();
    }

    //Name Input
    public String getNameInput(BookingSystemGUI gui) {
        return gui.nameInput.getText();
    }

    //Phone Input
    public String getPhoneInput(BookingSystemGUI gui) {
        return gui.phoneInput.getText();
    }

    //Email Input
    public String getEmailInput(BookingSystemGUI gui) {
        return gui.emailInput.getText();
    }
    
    public String displayTicketTypeChoices(BookingSystemGUI gui) {
        return (String) gui.ticketTypeChoices.getSelectedItem();
    }
    
    // Child Ticket Choices
    public String displayChildTicketChoices(BookingSystemGUI gui) {
    return (String) gui.childTicketChoices.getSelectedItem();
}
    public Show selectShow(List<Show> availableShows) {
        System.out.println("\nPlease select a show time:");

        for (int i = 0; i < availableShows.size(); i++) {
            Show show = availableShows.get(i);
            System.out.println("Press " + (i + 1) + " for " + show.getTime() + " in " + show.getCinema().getClass().getSimpleName());
        }

        int choice = userInputOptions(availableShows.size());
        return availableShows.get(choice - 1);
    }

    public void displayTicketMenu() {
        System.out.println("\nTicket Type and Quantity\n");
    }

    public void displayBookingConfirmation(BookingCalculator bookingCalculator) {

        System.out.println("--------------------------");
        System.out.println("Please confirm your details:\n");

        System.out.println("Full Name: " + getFullName());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Email: " + getEmail());
        System.out.println("Total Tickets: " + bookingCalculator.getTotalTickets());

        System.out.println("\n\n");
        System.out.println("Press 1 to confirm & booking");
        System.out.println("Press 2 to cancel & quit Program");
        userInputOptions(2);
    }

    public int getNumberOfTickets(String ticketType) {
        while (true) {
            try {
                System.out.print("Number of " + ticketType + " tickets required? ");
                int numTickets = scan.nextInt();
                if (numTickets >= 0) {
                    scan.nextLine(); // Clear the buffer
                    return numTickets;
                }
                System.out.println("Invalid input. Number of " + ticketType + " tickets can't be negative.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine(); // Clear the buffer
            }
        }
    }

    public Cinema getCinemaFromUserInput() {
        while (true) {
            try {
                System.out.print("Enter the cinema type (1 for Standard, 2 for Premium): ");
                int cinemaChoice = scan.nextInt();
                scan.nextLine(); // Clear the buffer
                switch (cinemaChoice) {
                    case 1:
                        return new StandardCinema("Standard Cinema", 90);
                    case 2:
                        return new PremiumCinema("Premium Cinema", 90, 55);
                    default:
                        System.out.println("Invalid input. Please enter 1 for Standard or 2 for Premium.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine(); // Clear the buffer
            }
        }
    }

    public PromoCode getPromoCodeFromUser() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you have a promo code? (yes/no): ");
            String promoCodeAnswer = scanner.next();

            if ("yes".equalsIgnoreCase(promoCodeAnswer)) {
                System.out.print("Please enter the promo code: ");
                String code = scanner.next().toUpperCase().trim();

                if (isValidPromoCode(code)) {
                    char codeType = code.charAt(0);
                    double discountAmount = Double.parseDouble(code.substring(1));

                    System.out.println("Promo Code " + code + " Added");
                    if (codeType == 'F') {
                        return new FixedDiscountPromo(discountAmount);
                    } else if (codeType == 'P') {
                        return new PercentageDiscountPromo(discountAmount);
                    }
                } else {
                    System.out.println("\n--------------------\n Invalid Promo Code \n--------------------\n");
                }
            } else if ("no".equalsIgnoreCase(promoCodeAnswer)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        return null;
    }

    private boolean isValidPromoCode(String promoCodeValue) {
        List<String> validPromoCodes = Arrays.asList("F5", "F2", "F10", "P10", "P20", "P5", "P2");
        return validPromoCodes.contains(promoCodeValue);
    }

    public void askForPhoneNumber() {
        setPhoneNumber(getInputWithValidation("What is your Phone Number? ", "\\d{10}", "Invalid phone number. Please enter a 10-digit number."));
    }

    public void askForName() {
        setFullName(getInputWithValidation("What is your full name? ", "[a-zA-Z]+ [a-zA-Z]+", "Invalid name. Please enter your first and last name."));
    }

    public void askForEmail() {
        setEmail(getInputWithValidation("What is your email address? ", "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", "Invalid email address. Please enter a valid email."));
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    private int userInputOptions(int optionAmount) {
        boolean validUserInput = false;
        int userValue = 0;

        while (validUserInput != true) {
            try {
                System.out.print("Your choice: ");
                int userInput = scan.nextInt();
                scan.nextLine();
                if (userInput > 0 && userInput < optionAmount) {
                    userValue = userInput;
                    validUserInput = true;
                    return userValue;
                } else if (userInput == optionAmount) {
                    System.exit(0);
                }

            } catch (InputMismatchException e) {
                System.out.println("Not valid input, please try again");
                scan.nextInt(); // Clears the input buffer   
            } catch (NoSuchElementException e) {
                System.out.println("No input provided, please try again");
                scan.nextInt(); // Clears the input buffer
            }
        }
        return userValue;
    }

    public String getInputWithValidation(String prompt, String regex, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scan.nextLine();
            if (input.matches(regex)) {
                break;
            } else {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    int displayMainMenu() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
 */