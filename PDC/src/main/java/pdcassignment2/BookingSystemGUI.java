/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * BookingSystemGUI.Java - Responsible for the graphical user interface for our system
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookingSystemGUI {
    
    private JFrame frame;
    public JComboBox<String> mainMenuOptions;
    public JComboBox<String> movieChoices;
    public JComboBox<String> dateTimeChoices;
    public JComboBox<String> ticketCountChoices;
    public JComboBox<String> ticketTypeChoices;
    public JTextField ticketType;
    public JTextField nameInput;
    public JTextField phoneInput;
    public JTextField emailInput;
    public JButton nextButton;
    public JButton quitButton;
    public JTextField phoneNumberLookupField;
    public JButton searchButton;
    public JButton confirmButton;
    public JButton backButton;
    public JButton restartButton;
    public JComboBox<String> childTicketChoices;
    
    
    //Promo code addition
    public JLabel promoCodeLabel;
    public JTextField promoCodeInput;

    public BookingSystemGUI() {
        frame = new JFrame("Cinema Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Main Menu Dropdown
        String[] options = {"Look up an existing booking", "Make a new booking"};
        mainMenuOptions = new JComboBox<>(options);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(mainMenuOptions, c);

        
        //Code for cinemas and shows and movies
        StandardCinema cinema1 = new StandardCinema("Cinema 1", 60);
        StandardCinema cinema2 = new StandardCinema("Cinema 2", 90);
        PremiumCinema cinema3 = new PremiumCinema("Cinema 3", 90, 55);
        
        Movie chronoNexu = new Movie("Chrono Nexu", "A physicist becomes trapped in a maze of alternative realities as a result of "
                + "his research on alternate universes. In this surreal sci-fi thriller"
                + " the protagonist races against time to discover the secret of the portals.");
        Movie whispersInTheMist = new Movie("Whispers in the Mist", "Dark secrets in a mountain town are revealed by eerie whispers from an endless mist."
                + "To escape the gripping psychological nightmare, strangers must face their pasts and one another.");
        Movie skywardOdyssey = new Movie("Skyward Odyssey", "A diverse crew navigates cosmic obstacles in pursuit of a new home while the Earth disintegrates."
                + "\"Skyward Odyssey\" is a grand story about cooperation, hope, and interstellar travel.");
        
        Show show1 = new Show(chronoNexu, "11:30 AM", cinema1, 100);
        Show show2 = new Show(whispersInTheMist, "3:00 PM", cinema2, 100);
        Show show3 = new Show(skywardOdyssey, "2:00 PM", cinema3, 100);
        
        
        // Movie Choices Dropdown
        String[] movies = {"Select a movie", chronoNexu.getTitle(), whispersInTheMist.getTitle(), skywardOdyssey.getTitle()};
        movieChoices = new JComboBox<>(movies);
        c.gridy = 1;
        panel.add(movieChoices, c);

        // Date and Time Dropdown
        
        String[] dateTimes = {"Select date and time", "30/10/24 at"+show1.getTime() , "30/10/24 at"+show2.getTime(), "30/10/24 at"+show3.getTime()};
        dateTimeChoices = new JComboBox<>(dateTimes);
        c.gridy = 2;
        panel.add(dateTimeChoices, c);

        // Ticket Count Dropdown 
        String[] ticket = {"Select Adult Tickets", "1", "2", "3", "4","5"};
        ticketCountChoices = new JComboBox<>(ticket);
        c.gridy = 3;
        panel.add(ticketCountChoices, c);
        
        // Ticket Type dropdown
        String[] ticketTypes = {"Select type of tickets", "Standard", "Premuim"};
        ticketTypeChoices = new JComboBox<>(ticketTypes);
        c.gridy = 4;
        panel.add(ticketTypeChoices, c);
        

        // Name Input
        nameInput = new JTextField("Enter your name");
        c.gridy = 5;
        panel.add(nameInput, c);

        // Phone Input
        phoneInput = new JTextField("Enter your phone number");
        c.gridy = 6;
        panel.add(phoneInput, c);

        // Email Input
        emailInput = new JTextField("Enter your email");
        c.gridy = 7;
        panel.add(emailInput, c);

        // Next Button
        nextButton = new JButton("Next");
        c.gridy = 8;
        panel.add(nextButton, c);

        // Quit Button
        quitButton = new JButton("Quit");
        c.gridy = 9;
        panel.add(quitButton, c);

        // Child Ticket Dropdown
        String[] childTickets = {"Choose child tickets", "1", "2", "3", "4"};
        childTicketChoices = new JComboBox<>(childTickets);
        c.gridy = 4;  // Assuming this is the next available grid position
        panel.add(childTicketChoices, c);
        childTicketChoices.setVisible(false);
        
        

        mainMenuOptions.setVisible(true);
        movieChoices.setVisible(false);
        dateTimeChoices.setVisible(false);
        ticketCountChoices.setVisible(false);
        nameInput.setVisible(false);
        phoneInput.setVisible(false);
        emailInput.setVisible(false);
        ticketTypeChoices.setVisible(false);
        phoneNumberLookupField = new JTextField("Enter phone number to lookup", 20);  
        searchButton = new JButton("Search");
        confirmButton = new JButton("Confirm");
        backButton = new JButton("Back");
        
        
        promoCodeLabel = new JLabel("Do you have a promo code?");
        promoCodeInput = new JTextField("Enter promo code here");
        c.gridy = 5;  
        panel.add(promoCodeLabel, c);

        c.gridy = 6;  
        panel.add(promoCodeInput, c);
        
        backButton.setVisible(false);
        c.gridy = 11;
        panel.add(backButton, c);
        
        c.gridy = 10; 
        panel.add(confirmButton, c);
        confirmButton.setVisible(false);
        
        restartButton = new JButton("Restart");
        c.gridy = 12;  
        panel.add(restartButton, c);
        restartButton.setVisible(true);
        
        
        promoCodeLabel.setVisible(false);
        promoCodeInput.setVisible(false);
        
        // Action Listeners
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String selectedOption = (String) mainMenuOptions.getSelectedItem();
            if ("Look up an existing booking".equals(selectedOption)) {
            
            
                
            panel.removeAll();
            c.gridy = 0;
            panel.add(phoneNumberLookupField, c);
            
            // Add Search buttoon
            c.gridy = 1;
            panel.add(searchButton, c);

            // Repaint and rev.
            panel.repaint();
            panel.revalidate();
        }
                
                handleNextButton();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        
        //Should function as the AL for the searchbutton
        searchButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        String phoneNumber = phoneNumberLookupField.getText();

        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid phone number with digits only.");
            return;
        }
        
        // Connect to the database
        try {
            Connection conn = DatabaseUtility.connect();

            // SQL for dind
            String query = "SELECT * FROM Bookings WHERE PhoneNumber = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, phoneNumber);
            ResultSet rs = pstmt.executeQuery();

            // Check if a booking is found
            if (rs.next()) {
                //This will display our booking ingo 
                String fullName = rs.getString("FullName");
                String email = rs.getString("Email");
                // Add more fields as needed
                JOptionPane.showMessageDialog(frame, "Booking found:\nName: " + fullName + "\nEmail: " + email);
            } else {
                //Error message
                JOptionPane.showMessageDialog(frame, "No booking found for the provided phone number.");
            }

        } catch (DatabaseException | SQLException ex) {
            JOptionPane.showMessageDialog(frame, "An error occurred while accessing the database.");
        }
    }
    
    
    
    
});


//        movieChoices.addActionListener(new ActionListener() {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if ("Select a movie".equals(movieChoices.getSelectedItem())) {
//            JOptionPane.showMessageDialog(frame, "Please select a valid movie from the list.");
//            // Optionally, set it to a default valid index like 1 (first actual movie)
//            movieChoices.setSelectedIndex(1);
//        }
//    }
//});
//        
        


backButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        handleBackButton(); 
    }
});




mainMenuOptions.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedOption = (String) mainMenuOptions.getSelectedItem();
        if ("Quit the program".equals(selectedOption)) {
            System.exit(0);
        }
    }
});

confirmButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int test =1;
        String selectedMovie = (String) movieChoices.getSelectedItem();
        System.out.println(selectedMovie);
        String selectedDateTime = (String) dateTimeChoices.getSelectedItem();
        System.out.println(selectedDateTime);
        String selectedTicketCount = (String) ticketCountChoices.getSelectedItem(); //Adult tickets
        System.out.println(selectedTicketCount);
        String selectedChildTickets = (String) childTicketChoices.getSelectedItem();//Child Tickets
        System.out.println(selectedChildTickets);
        String selectedTicketType = (String) ticketTypeChoices.getSelectedItem();
        System.out.println(selectedTicketType);
        String enteredName = nameInput.getText();
        System.out.println(enteredName);
        String enteredPhone = phoneInput.getText();
        System.out.println(enteredPhone);
        String enteredEmail = emailInput.getText();
        System.out.println(enteredEmail);
        String promoCode = promoCodeInput.getText();
        System.out.println(promoCode);
        
        double childTickets;
        childTickets = Double.parseDouble(selectedChildTickets);

        double adultTickets;
        adultTickets = Double.parseDouble(selectedTicketCount);
        
        Menu menu = new Menu();
        if("Standard".equals(selectedTicketType))
        {
            
        }
                
        
        //Where the critical change is, making a bookingcalculator object is different in the GUI
//        if("Standard".equals(selectedTicketType) & "Chrono Nexu".equals(selectedMovie)){
//            BookingCalculator BC = new BookingCalculator();
//            BC.calculateTotalPrice(adultTickets, childTickets, ,);
//        }
       
        
        int totalprice = 0;
        
        
        
        
        // "Standard", "Premuim"};

//        "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
//                        "FullName VARCHAR(255)," +
//                        "PhoneNumber VARCHAR(15)," +
//                        "Email VARCHAR(255)," +
//                        "BookingTime TIMESTAMP," +
//                        "Movie VARCHAR(255)," +
//                        "CinemaType VARCHAR(50)," +
//                        "TotalTickets INT," +
//                        "TotalPaid DECIMAL(10, 2)" +
//                        ")";
        
        
        Booking newBooking = new Booking();
        newBooking.setFullName(enteredName);//Done
        newBooking.setPhoneNumber(enteredPhone);//current string
        newBooking.setEmail(enteredEmail);//Done
        newBooking.setShowTime(selectedDateTime);//Booking time change SQL
        newBooking.setMovieTitle(selectedMovie);//Done
        newBooking.setTicketType(selectedTicketType);//Done
        newBooking.setTicketQuantity(Integer.parseInt(selectedTicketCount));//Done
        newBooking.setTotalPrice(totalprice);//Needs to be a int
        
        BookingSystem.insertNewBooking(newBooking);
        System.out.println("added");

        JOptionPane.showMessageDialog(frame, "Booking successfully created!");
    }
});

        
    restartButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        resetToInitialState();
    }
});    
        
        frame.add(panel);
 
    }
     
    public String getSelectedMainMenuOption() {
        return (String) mainMenuOptions.getSelectedItem();
    }
    
   private void handleNextButton() {
    if (mainMenuOptions.isVisible()) {
        String selectedOption = (String) mainMenuOptions.getSelectedItem();
        mainMenuOptions.setVisible(false);
        if ("Look up an existing booking".equals(selectedOption)) {
            phoneInput.setVisible(true);
        } else if ("Make a new booking".equals(selectedOption)) {
            movieChoices.setVisible(true);
        }
        return;
    }

    if (movieChoices.isVisible()) {
        if ("Select a movie".equals(movieChoices.getSelectedItem())) {
            JOptionPane.showMessageDialog(frame, "Please select a valid movie from the list.");
            return; // Do not proceed to next step
        }
        movieChoices.setVisible(false);
        dateTimeChoices.setVisible(true);
        backButton.setVisible(true);
        return;
    }

    if (dateTimeChoices.isVisible()) {
        if ("Select date and time".equals(dateTimeChoices.getSelectedItem())) {
            JOptionPane.showMessageDialog(frame, "Please select a valid date and time.");
            return; // Do not proceed to next step
        }
        dateTimeChoices.setVisible(false);
        ticketCountChoices.setVisible(true);
        backButton.setVisible(true);
        return;
    }

     if (ticketCountChoices.isVisible()) {
         if ("Select Adult Tickets".equals(ticketCountChoices.getSelectedItem())) {
            JOptionPane.showMessageDialog(frame, "Please select a valid number of adult tickets.");
            return; // Do not proceed to next step
        }
        ticketCountChoices.setVisible(false);
        childTicketChoices.setVisible(true);  // Display childTicketChoices after ticketCountChoices
        backButton.setVisible(true);
        return;
    }
    
     if (childTicketChoices.isVisible()) {
        childTicketChoices.setVisible(false);
        ticketTypeChoices.setVisible(true);  // Display ticketTypeChoices after childTicketChoices
        backButton.setVisible(true);
        return;
    }
     
    if (ticketTypeChoices.isVisible()) {
         if ("Select type of tickets".equals(ticketTypeChoices.getSelectedItem())) {
            JOptionPane.showMessageDialog(frame, "Please select a valid type of ticket.");
            return; // Do not proceed to next step
        }
        ticketTypeChoices.setVisible(false);
        nameInput.setVisible(true);  // Should proceed to name input instead of looping back to child tickets
        backButton.setVisible(true);
        return;
    }

    if (childTicketChoices.isVisible()) {
         if ("Choose child tickets".equals(childTicketChoices.getSelectedItem())) {
            JOptionPane.showMessageDialog(frame, "Please select a valid number of child tickets.");
            return; // Do not proceed to next step
        }
    childTicketChoices.setVisible(false);
    nameInput.setVisible(true);  // Proceed to name input
    backButton.setVisible(true);
    return;
}

    if (nameInput.isVisible()) {
        nameInput.setVisible(false);
        phoneInput.setVisible(true);
        backButton.setVisible(true);
        frame.revalidate();
        frame.repaint();
        return;
    }

    if (phoneInput.isVisible()) {
        String enteredPhone = phoneInput.getText();
    try {
        Long.parseLong(enteredPhone);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(frame, "Please enter a valid phone number (numeric only).");
        return;
    }
        
        phoneInput.setVisible(false);
        emailInput.setVisible(true);
        backButton.setVisible(true);
        frame.revalidate();
        frame.repaint();
        return;
    }

    if (emailInput.isVisible()) {
        emailInput.setVisible(false);
        promoCodeLabel.setVisible(true);
        promoCodeInput.setVisible(true);
        backButton.setVisible(true);
        frame.revalidate();
        frame.repaint();
        return;
    }

    if (promoCodeLabel.isVisible() && promoCodeInput.isVisible()) {
        promoCodeLabel.setVisible(false);
        promoCodeInput.setVisible(false);
        confirmButton.setVisible(true);
        nextButton.setVisible(false);
        backButton.setVisible(false);
        frame.revalidate();
        frame.repaint();
    }
}

    
    
    
    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        
    }
    
    
private void handleBackButton() {
    
    if (confirmButton.isVisible()) {
        confirmButton.setVisible(false);
        emailInput.setVisible(true);
        backButton.setVisible(true);
        return;
    }

    if (emailInput.isVisible()) {
        emailInput.setVisible(false);
        phoneInput.setVisible(true);
        return;
    }

    if (phoneInput.isVisible()) {
        phoneInput.setVisible(false);
        nameInput.setVisible(true);
        return;
    }

    if (nameInput.isVisible()) {
        nameInput.setVisible(false);
        childTicketChoices.setVisible(true);
        return;
    }

    if (childTicketChoices.isVisible()) {
    childTicketChoices.setVisible(false);
    ticketTypeChoices.setVisible(true); 
    return;
}
    
     if (ticketTypeChoices.isVisible()) {
        ticketTypeChoices.setVisible(false);
        childTicketChoices.setVisible(true);  // Navigate back to child ticket choices
        return;
    }
     
     if (childTicketChoices.isVisible()) {
        childTicketChoices.setVisible(false);
        ticketCountChoices.setVisible(true);  // Navigate back to adult ticket choices
        return;
    }
    
    if (ticketCountChoices.isVisible()) {
        ticketCountChoices.setVisible(false);
        dateTimeChoices.setVisible(true);
        backButton.setVisible(true);
        return;
    }

    if (dateTimeChoices.isVisible()) {
        dateTimeChoices.setVisible(false);
        movieChoices.setVisible(true);
        backButton.setVisible(false);
        return;
    }

    if (movieChoices.isVisible()) {
        movieChoices.setVisible(false);
        mainMenuOptions.setVisible(true);
        return;
    }

    if (promoCodeLabel.isVisible() && promoCodeInput.isVisible()) {
        promoCodeLabel.setVisible(false);
        promoCodeInput.setVisible(false);
        emailInput.setVisible(true);
        backButton.setVisible(true);
        return;
    }

    
}

    private void resetToInitialState() {//Resseter should function via  reset 
    mainMenuOptions.setVisible(true);
    mainMenuOptions.setSelectedIndex(0); 
    
    movieChoices.setVisible(false);
    movieChoices.setSelectedIndex(0);
    
    dateTimeChoices.setVisible(false);
    dateTimeChoices.setSelectedIndex(0);
    
    ticketCountChoices.setVisible(false);
    ticketCountChoices.setSelectedIndex(0);
    
    ticketTypeChoices.setVisible(false);
    ticketTypeChoices.setSelectedIndex(0);
    
    nameInput.setVisible(false);
    nameInput.setText("Enter your name");
    
    phoneInput.setVisible(false);
    phoneInput.setText("Enter your phone number");
    
    emailInput.setVisible(false);
    emailInput.setText("Enter your email");
    
    nextButton.setVisible(true);
    confirmButton.setVisible(false);
    backButton.setVisible(false);
    
    promoCodeLabel.setVisible(false);
    promoCodeInput.setVisible(false);
    
    childTicketChoices.setVisible(false);
    childTicketChoices.setSelectedIndex(0);  

    promoCodeLabel.setVisible(false);  
    promoCodeInput.setVisible(false);  
    promoCodeInput.setText("Enter promo code here");
    
}
    
}