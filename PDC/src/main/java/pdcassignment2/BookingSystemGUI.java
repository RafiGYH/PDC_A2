/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * BookingSystemGUI.Java - Assembles the program and handles the graphical user interface
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall #20119318
 * @Created October 2023
 */
package pdcassignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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
    public JLabel promoCodeLabel;
    public JTextField promoCodeInput;
    private JLabel totalCostLabel;

    private BookingCalculator bookingCalculator = new BookingCalculator();
    private PromoCodeValidator promoCodeValidator = new PromoCodeValidator();
    private PromoCode appliedPromoCode;
    private double totalPrice;
    private double discountAmount;
    private int totalTickets;
    private double discountedTotal;

    public BookingSystemGUI() {

        totalCostLabel = new JLabel("Total Cost: $0");

        //Frame setup
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

        // Movie Choices Dropdown
        movieChoices = new JComboBox<>();
        loadMoviesIntoComboBox(); // Load movies into JComboBox
        c.gridy = 1;
        panel.add(movieChoices, c);

        // Date and Time Dropdown
        String[] dateTimes = {"Select date and time", "30/10/24 at 12:00", "30/10/24 at 3:00", "30/10/24 at 6:00"};
        dateTimeChoices = new JComboBox<>(dateTimes);
        c.gridy = 2;
        panel.add(dateTimeChoices, c);

        // Ticket Count Dropdown
        String[] ticketCounts = {"Select Adult Tickets", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ticketCountChoices = new JComboBox<>(ticketCounts);
        c.gridy = 3;
        panel.add(ticketCountChoices, c);

        // Ticket Type Dropdown
        String[] ticketTypes = {"Select type of tickets", "Standard", "Premuim"};
        ticketTypeChoices = new JComboBox<>(ticketTypes);
        c.gridy = 4;
        panel.add(ticketTypeChoices, c);

        // Child Ticket Dropdown
        String[] childTickets = {"Choose child tickets", "0", "1", "2", "3", "4", "6", "7", "8", "9", "10"};
        childTicketChoices = new JComboBox<>(childTickets);
        c.gridy = 5;
        panel.add(childTicketChoices, c);
        childTicketChoices.setVisible(false);

        // Name Input
        nameInput = createTextFieldWithPlaceholder("Enter your name", 6);
        panel.add(nameInput, c);

        // Phone Input
        phoneInput = createTextFieldWithPlaceholder("Enter your phone number", 7);
        c.gridy = 6;
        panel.add(phoneInput, c);

        // Email Input
        emailInput = createTextFieldWithPlaceholder("Enter your email", 8);
        panel.add(emailInput, c);

        // Next Button
        nextButton = new JButton("Next");
        c.gridy = 11;
        panel.add(nextButton, c);

        // Quit Button
        quitButton = new JButton("Quit");
        c.gridy = 12;
        panel.add(quitButton, c);

        // Confirm Button
        confirmButton = new JButton("Confirm");
        c.gridy = 13;
        panel.add(confirmButton, c);
        confirmButton.setVisible(false);

        // Back Button
        backButton = new JButton("Back");
        c.gridy = 14;
        panel.add(backButton, c);
        backButton.setVisible(false);

        // Restart Button
        restartButton = new JButton("Restart");
        c.gridy = 15;
        panel.add(restartButton, c);
        restartButton.setVisible(false);

        // Search Button
        searchButton = new JButton("Search");

        // Booking lookup by number
        phoneNumberLookupField = createTextFieldWithPlaceholder("Enter phone number to lookup", 16);
        panel.add(phoneNumberLookupField, c);
        c.gridy = 17;
        panel.add(searchButton, c);
        searchButton.setVisible(false);
        phoneNumberLookupField.setVisible(false);

        //Promo code and label 
        promoCodeLabel = new JLabel("Enter Promo Code: (Next to skip)");
        promoCodeInput = new JTextField(15);
        c.gridy = 9;
        panel.add(promoCodeLabel, c);
        c.gridy = 10;
        panel.add(promoCodeInput, c);
        promoCodeLabel.setVisible(false);
        promoCodeInput.setVisible(false);

        // Set initial visibility
        movieChoices.setVisible(false);
        dateTimeChoices.setVisible(false);
        ticketCountChoices.setVisible(false);
        nameInput.setVisible(false);
        phoneInput.setVisible(false);
        emailInput.setVisible(false);
        ticketTypeChoices.setVisible(false);

        // Action Listeners
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextButton();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Booking lookup
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneNumberLookupField.getText();

                if (!phoneNumber.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid phone number with digits only.");
                    return;
                }

                // Connect to the database
                try {
                    DatabaseUtility dbUtil = DatabaseUtility.getInstance();
                    Booking booking = dbUtil.findBookingByPhoneNumber(phoneNumber);

                    // Check if a booking is found
                    if (booking != null) {
                        // Display the booking info 
                        JOptionPane.showMessageDialog(frame, booking.toString());
                    } else {
                        // Display an error message
                        JOptionPane.showMessageDialog(frame, "No booking found for the provided phone number.");
                    }
                } catch (DatabaseException ex) {
                    JOptionPane.showMessageDialog(frame, "An error occurred while accessing the database.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBackButton();
            }
        });

        // Confirm / Create booking
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = nameInput.getText();
                String phoneNumber = phoneInput.getText();
                String email = emailInput.getText();
                String movieTitle = (String) movieChoices.getSelectedItem();
                String showTime = (String) dateTimeChoices.getSelectedItem();
                String ticketType = (String) ticketTypeChoices.getSelectedItem();
                int adultTicketCount = Integer.parseInt((String) ticketCountChoices.getSelectedItem());
                int childTicketCount = Integer.parseInt((String) childTicketChoices.getSelectedItem());

                // Create a new booking
                Booking newBooking = new Booking();
                newBooking.setFullName(fullName);
                newBooking.setPhoneNumber(phoneNumber);
                newBooking.setEmail(email);
                newBooking.setMovieTitle(movieTitle);
                newBooking.setShowTime(showTime);
                newBooking.setTicketType(ticketType);
                newBooking.setTicketQuantity(adultTicketCount + childTicketCount);
                newBooking.setChildTicketQuantity(childTicketCount);

                // Calculate total price
                Cinema cinema;
                if (ticketType.equals("Premium")) {
                    cinema = new PremiumCinema("Premium Cinema", 100, 50);
                } else {
                    cinema = new StandardCinema("Standard Cinema", 150);
                }
                double totalPrice = bookingCalculator.calculateTotalPrice(adultTicketCount, childTicketCount, appliedPromoCode, cinema);
                newBooking.setTotalPrice(totalPrice);

                // Insert the new booking into the database
                try {
                    boolean success = newBooking.create();
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Booking added successfully!\n" + newBooking); // Booking details popup
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to add booking. Please try again.");
                    }
                } catch (DatabaseException dbException) {
                    dbException.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "An error occurred while adding the booking: " + dbException.getMessage());
                }
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

    // Movie options
    private void loadMoviesIntoComboBox() {
        try {
            DatabaseUtility dbUtil = DatabaseUtility.getInstance();
            List<Movie> movies = dbUtil.getAllMovies();
            if (movies.isEmpty()) {
                System.out.println("No movies found in the database.");
            } else {
                System.out.println("Movies found: " + movies.size()); // Console output
            }
            List<String> movieTitles = new ArrayList<>();
            movieTitles.add("Select a movie"); // Default option
            for (Movie movie : movies) {
                System.out.println("Adding movie to dropdown: " + movie.getTitle()); // Console output
                movieTitles.add(movie.getTitle());
            }
            movieChoices.setModel(new DefaultComboBoxModel<>(movieTitles.toArray(new String[0])));
        } catch (DatabaseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to load movies from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void display() {
        loadMoviesIntoComboBox();
        frame.setVisible(true);
    }

    // Function to clear placeholder on click
    private JTextField createTextFieldWithPlaceholder(String placeholder, int gridy) {
        JTextField textField = new JTextField(placeholder);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = gridy;
        return textField;
    }

    // Function to apply and validated promocodes
    private void applyPromoCode(String code) {
        // Skip adding a  promo code
        if (code.isEmpty()) {
            return;
        }
        // Validate entered promo code
        if (promoCodeValidator.isValidPromoCode(code)) {

            appliedPromoCode = promoCodeValidator.getPromoCode(code);
            double discountAmount = appliedPromoCode.discount(totalPrice);
            double discountedTotal = totalPrice - discountAmount;

            // Fixed or percentage based promo code/discount
            String discountDescription = "";
            if (appliedPromoCode instanceof PercentageDiscountPromo) {
                discountDescription = ((PercentageDiscountPromo) appliedPromoCode).getDiscountDescription();
            } else if (appliedPromoCode instanceof FixedDiscountPromo) {
                discountDescription = ((FixedDiscountPromo) appliedPromoCode).getDiscountDescription();
            }

            JOptionPane.showMessageDialog(frame, "Promo Code " + code + " Applied");

        } else {
            System.out.println("Invalid promo code");
            JOptionPane.showMessageDialog(frame, "Invalid Promo Code", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // NEXT BUTTON
    private void handleNextButton() {
        if (mainMenuOptions.isVisible()) {
            String selectedOption = (String) mainMenuOptions.getSelectedItem();
            mainMenuOptions.setVisible(false);
            if ("Look up an existing booking".equals(selectedOption)) {
                phoneNumberLookupField.setVisible(true);
                restartButton.setVisible(false);
                searchButton.setVisible(true);
                nextButton.setVisible(false);
                quitButton.setVisible(false);
                backButton.setVisible(true);

            } else if ("Make a new booking".equals(selectedOption)) {
                movieChoices.setVisible(true);
                backButton.setVisible(true);
                restartButton.setVisible(true);
            }
            //backButton.setVisible(true);
            return;
        }

        if (movieChoices.isVisible()) {
            if ("Select a movie".equals(movieChoices.getSelectedItem())) {
                JOptionPane.showMessageDialog(frame, "Please select a valid movie from the list.");
                return;
            }
            movieChoices.setVisible(false);
            dateTimeChoices.setVisible(true);
            backButton.setVisible(true);
            restartButton.setVisible(true);
            return;
        }

        if (dateTimeChoices.isVisible()) {
            if ("Select date and time".equals(dateTimeChoices.getSelectedItem())) {
                JOptionPane.showMessageDialog(frame, "Please select a valid date and time.");
                return;
            }

            dateTimeChoices.setVisible(false);
            ticketCountChoices.setVisible(true);
            backButton.setVisible(true);
            restartButton.setVisible(true);
            return;
        }

        if (ticketCountChoices.isVisible()) {
            if ("Select Adult Tickets".equals(ticketCountChoices.getSelectedItem())) {
                JOptionPane.showMessageDialog(frame, "Please select a valid number of adult tickets.");
                return;
            }
            ticketCountChoices.setVisible(false);
            childTicketChoices.setVisible(true);
            backButton.setVisible(true);
            restartButton.setVisible(true);
            return;
        }

        if (childTicketChoices.isVisible()) {
            if ("Choose child tickets".equals(childTicketChoices.getSelectedItem())) {
                JOptionPane.showMessageDialog(frame, "Please select a valid number of child tickets.");
                return;
            }
            childTicketChoices.setVisible(false);
            ticketTypeChoices.setVisible(true);
            backButton.setVisible(true);
            restartButton.setVisible(true);
            return;
        }

        if (ticketTypeChoices.isVisible()) {
            if ("Select type of tickets".equals(ticketTypeChoices.getSelectedItem())) {
                JOptionPane.showMessageDialog(frame, "Please select a valid type of ticket.");
                return;
            }
            ticketTypeChoices.setVisible(false);
            promoCodeLabel.setVisible(true);
            promoCodeInput.setVisible(true);
            backButton.setVisible(true);
            restartButton.setVisible(true);

            return;
        }

        if (promoCodeLabel.isVisible() && promoCodeInput.isVisible()) {
            String promoCode = promoCodeInput.getText().trim();
            applyPromoCode(promoCode);
            promoCodeLabel.setVisible(false);
            promoCodeInput.setVisible(false);
            nameInput.setVisible(true);
            backButton.setVisible(true);
            restartButton.setVisible(true);
            return;
        }

        if (nameInput.isVisible()) {
            if (nameInput.getText().trim().isEmpty() || !nameInput.getText().matches("\\b([-,A-Za-zÀ-ÿ][-,A-Za-zÀ-ÿ. ']+[ ]*)")) {
                JOptionPane.showMessageDialog(frame, "Please enter your first and last name.");
                return;
            }
            nameInput.setVisible(false);
            phoneInput.setVisible(true); // Display phone input field after entering the name
            frame.revalidate();
            frame.repaint();
            return;
        }

        if (phoneInput.isVisible()) {
            if (phoneInput.getText().trim().isEmpty() || !phoneInput.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid phone number with digits only.");
                return;
            }
            phoneInput.setVisible(false);
            emailInput.setVisible(true);
            frame.revalidate();
            frame.repaint();
            return;
        }

        if (emailInput.isVisible()) {
            if (emailInput.getText().trim().isEmpty() || !emailInput.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid email address.");
                return;
            }
            emailInput.setVisible(false);
            confirmButton.setVisible(true);
            frame.revalidate();
            frame.repaint();
            nextButton.setVisible(false);
        }
    }

    // BACK BUTTON
    private void handleBackButton() {
        if (phoneNumberLookupField.isVisible() && searchButton.isVisible()) {
            phoneNumberLookupField.setVisible(false);
            nextButton.setVisible(true);
            quitButton.setVisible(true);
            searchButton.setVisible(false);
            mainMenuOptions.setVisible(true);
            backButton.setVisible(false);
            restartButton.setVisible(true);
        }

        if (confirmButton.isVisible()) {
            confirmButton.setVisible(false);
            nextButton.setVisible(true);
            emailInput.setVisible(true);
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
            promoCodeLabel.setVisible(true);
            promoCodeInput.setVisible(true);
            return;
        }

        if (promoCodeLabel.isVisible() && promoCodeInput.isVisible()) {
            promoCodeLabel.setVisible(false);
            promoCodeInput.setVisible(false);
            ticketTypeChoices.setVisible(true);
            return;
        }

        if (ticketTypeChoices.isVisible()) {
            ticketTypeChoices.setVisible(false);
            childTicketChoices.setVisible(true);
            return;
        }

        if (childTicketChoices.isVisible()) {
            childTicketChoices.setVisible(false);
            ticketCountChoices.setVisible(true);
            return;
        }

        if (ticketCountChoices.isVisible()) {
            ticketCountChoices.setVisible(false);
            dateTimeChoices.setVisible(true);
            return;
        }

        if (dateTimeChoices.isVisible()) {
            dateTimeChoices.setVisible(false);
            movieChoices.setVisible(true);
            return;
        }

        if (movieChoices.isVisible()) {
            movieChoices.setVisible(false);
            mainMenuOptions.setVisible(true);
            backButton.setVisible(false);
            return;
        }

        if (phoneNumberLookupField.isVisible() && searchButton.isVisible()) {
            phoneNumberLookupField.setVisible(false);
            searchButton.setVisible(false);
            mainMenuOptions.setVisible(true);
            backButton.setVisible(false);
        }
    }

    // RESET
    private void resetToInitialState() {
        mainMenuOptions.setVisible(true);
        mainMenuOptions.setSelectedIndex(0);
        movieChoices.setVisible(false);
        dateTimeChoices.setVisible(false);
        ticketCountChoices.setVisible(false);
        ticketTypeChoices.setVisible(false);
        childTicketChoices.setVisible(false);
        nameInput.setVisible(false);
        phoneInput.setVisible(false);
        emailInput.setVisible(false);
        confirmButton.setVisible(false);
        nextButton.setVisible(true);
        backButton.setVisible(false);
        promoCodeLabel.setVisible(false);
        promoCodeInput.setVisible(false);
        phoneNumberLookupField.setVisible(false);
        searchButton.setVisible(false);
        restartButton.setVisible(false);
        quitButton.setVisible(true);
    }

    // MAIN Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingSystemGUI gui = new BookingSystemGUI();
            gui.display();

            try {
                DatabaseUtility dbUtil = DatabaseUtility.getInstance();
                // Insert movies into the database
                dbUtil.insertMovie(new Movie("Inception", "A thief who enters the dreams of others must perform the impossible."));
                dbUtil.insertMovie(new Movie("The Matrix", "A computer hacker learns about the true nature of his reality and his role in the war against its controllers."));
                dbUtil.printAllMovies();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        });
    }

}
