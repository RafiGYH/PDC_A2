/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * Menu.Java - Responsible for displaying messages and gathering input from the user
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
*/
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
    
    public int displayMainMenu()
    {
        System.out.println("Cinema Booking System\n---------------------");
        System.out.println("Press 1 to look up an existing booking");
        System.out.println("Press 2 to Make a new Booking");
        System.out.println("Press 3 to quit the program");
        return userInputOptions(3);
    }
    
    public int displayBookingChoice()
    {
        System.out.println("Query an existing booking\n------------------------- ");
        System.out.println("What is the phone number on the booking?");
        return userInputOptions(3);
    }
    
    public int displayMovieChoices(List<Movie> movies) {
    System.out.println("\nPlease select a movie to book:");

    // Display available movies and their descriptions
    for (int i = 0; i < movies.size(); i++) {
        Movie movie = movies.get(i);
        System.out.println("Press " + (i + 1) + " for " + movie.getTitle());
        System.out.println("Description: " + movie.getDescription());
        System.out.println();
    }
    
    System.out.println("Press " + (movies.size() + 1) + " to quit");

    // Get user input
    return userInputOptions(movies.size() + 1);
    }

    
    public void displayTicketMenu()
    {
        System.out.println("\nTicket Type and Quantity\n");
    }
    
    public void displayBookingConfirmation(BookingCalculator bookingCalculator)
    {
        
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

    public void askForPhoneNumber()
    {
        System.out.print("What is your Phone Number? ");
        String number = scan.nextLine();
        setPhoneNumber(number);
    }
    
    public void askForName()
    {
        System.out.print("What is your full name? ");
        String test = scan.nextLine();
        setFullName(test);
    }
    
    public void askForEmail()
    {
        System.out.print("What is your email address? ");
        String Email = scan.nextLine();
        setEmail(Email);
    }
    
    public void setFullName(String name)
    {
        this.fullName = name;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getFullName()
    {
        return fullName;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    
    private int userInputOptions(int optionAmount)
    {
        boolean validUserInput = false;
        int userValue = 0;
          
        while(validUserInput!=true)
        { 
            try{
                System.out.print("Your choice: ");
                int userInput = scan.nextInt();
                scan.nextLine(); 
                if(userInput > 0 && userInput < optionAmount)
                {
                    userValue = userInput;
                    validUserInput = true;
                    return userValue;
                }
                else if(userInput == optionAmount)
                {
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
}