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
        String[] dateTimes = {"Select date and time", "30/10/24 at 11:30 AM", "30/10/24 at 3:00 PM", "30/10/24 at 2:00 PM"};
        dateTimeChoices = new JComboBox<>(dateTimes);
        c.gridy = 2;
        panel.add(dateTimeChoices, c);

        // Ticket Count Dropdown
        String[] ticketCounts = {"Select Adult Tickets", "1", "2", "3", "4", "5"};
        ticketCountChoices = new JComboBox<>(ticketCounts);
        c.gridy = 3;
        panel.add(ticketCountChoices, c);

        // Ticket Type Dropdown
        String[] ticketTypes = {"Select type of tickets", "Standard", "Premuim"};
        ticketTypeChoices = new JComboBox<>(ticketTypes);
        c.gridy = 4;
        panel.add(ticketTypeChoices, c);

        // Child Ticket Dropdown
        String[] childTickets = {"Choose child tickets", "0", "1", "2", "3", "4"};
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

        phoneNumberLookupField = createTextFieldWithPlaceholder("Enter phone number to lookup", 16);// Phone Number Lookup Field

        panel.add(phoneNumberLookupField, c);
        c.gridy = 17;
        panel.add(searchButton, c);
        searchButton.setVisible(false);
        phoneNumberLookupField.setVisible(false);

        //Promo code and label 
        promoCodeLabel = new JLabel("Enter Promo Code:");
        promoCodeInput = new JTextField(15); // 15 columns
        c.gridy = 9; // Change this to place it in your desired row
        panel.add(promoCodeLabel, c);
        c.gridy = 10; // Change this to place it in your desired row
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
                        String bookingInfo = "Booking Details:\n";
                        bookingInfo += "Name: " + booking.getFullName() + "\n";
                        bookingInfo += "Email: " + booking.getEmail() + "\n";
                        bookingInfo += "Movie: " + booking.getMovieTitle() + "\n";
                        bookingInfo += "Booking Time: " + booking.getShowTime() + "\n";
                        bookingInfo += "Cinema Type: " + booking.getTicketType() + "\n";
                        bookingInfo += "Total Tickets: " + booking.getTicketQuantity() + "\n";
                        bookingInfo += "Total Paid: $" + booking.getTotalPrice();

                        JOptionPane.showMessageDialog(frame, bookingInfo);
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

        confirmButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
            
                // 1. Gather Booking Information
                String fullName = nameInput.getText().trim();
                String phoneNumber = phoneInput.getText().trim();
                String email = emailInput.getText().trim();
                String movieTitle = (String) movieChoices.getSelectedItem();
                String showTime = (String) dateTimeChoices.getSelectedItem();
                String ticketType = (String) ticketTypeChoices.getSelectedItem();
                int adultTickets = Integer.parseInt((String) ticketCountChoices.getSelectedItem());
                int childTickets = Integer.parseInt((String) childTicketChoices.getSelectedItem());
                int totalTickets = adultTickets + childTickets;
                //double totalPrice = calculateTotalPrice(); 

                // 2. Create a Booking Object
                Booking booking = new Booking();
                booking.setFullName(fullName);
                booking.setPhoneNumber(phoneNumber);
                booking.setEmail(email);
                booking.setShowTime(showTime);
                booking.setMovieTitle(movieTitle);
                booking.setTicketType(ticketType);
                booking.setTicketQuantity(totalTickets);
                booking.setTotalPrice(totalPrice);

                // 3. Save the Booking to the Database
                DatabaseUtility dbUtil = DatabaseUtility.getInstance();
                boolean success = dbUtil.insertBooking(booking);

                // 4. Update the GUI
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Booking successful!");
                    resetToInitialState();
                } else {
                    JOptionPane.showMessageDialog(frame, "Booking failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred while saving the booking.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void loadMoviesIntoComboBox() {
        try {
            DatabaseUtility dbUtil = DatabaseUtility.getInstance();
            List<Movie> movies = dbUtil.getAllMovies();
            if (movies.isEmpty()) {
                System.out.println("No movies found in the database.");
            } else {
                System.out.println("Movies found: " + movies.size());
            }
            List<String> movieTitles = new ArrayList<>();
            movieTitles.add("Select a movie"); // Default option
            for (Movie movie : movies) {
                System.out.println("Adding movie to dropdown: " + movie.getTitle());
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
        c.gridx = 0; // Set the gridx property
        c.gridy = gridy;
        return textField;
    }

    private void applyPromoCode(String code) {
        /*while (true) {
            int result = JOptionPane.showConfirmDialog(frame, "Do you have a promo code?", "Promo Code", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                String code = JOptionPane.showInputDialog(frame, "Please enter the promo code:");*/
        if (promoCodeValidator.isValidPromoCode(code)) {
            appliedPromoCode = promoCodeValidator.getPromoCode(code);
            double discountAmount = appliedPromoCode.discount(totalPrice);
            double discountedTotal = totalPrice - discountAmount;

            String discountDescription = "";
            if (appliedPromoCode instanceof PercentageDiscountPromo) {
                discountDescription = ((PercentageDiscountPromo) appliedPromoCode).getDiscountDescription();
            } else if (appliedPromoCode instanceof FixedDiscountPromo) {
                discountDescription = ((FixedDiscountPromo) appliedPromoCode).getDiscountDescription();
            }
            JOptionPane.showMessageDialog(frame, "Promo Code " + code + " Applied");
            totalCostLabel.setText("Total: $" + totalPrice + " (" + discountDescription + "), Tickets: " + totalTickets + ", Discounted Total: $" + discountedTotal);

            //break; // Exit the loop if the promo code is valid
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Promo Code", "Error", JOptionPane.ERROR_MESSAGE);
            // The loop will continue, asking the user again
        }
        /*  } else {
                break; // Exit the loop if the user chooses not to enter a promo code
            }
        }
        nameInput.setVisible(true);*/
    }

    private double calculateDiscount(double total, double discountPercentage) {
        return total * (discountPercentage / 100);
    }

    private void handleNextButton() {

        if (mainMenuOptions.isVisible()) {
            String selectedOption = (String) mainMenuOptions.getSelectedItem();
            mainMenuOptions.setVisible(false);
            if ("Look up an existing booking".equals(selectedOption)) {
                phoneNumberLookupField.setVisible(true);
                searchButton.setVisible(true);
                nextButton.setVisible(false);
                quitButton.setVisible(false);
                backButton.setVisible(false);
                restartButton.setVisible(true);
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
            if (nameInput.getText().trim().isEmpty() || !nameInput.getText().matches("\\b([A-Za-zÀ-ÿ][-,a-z. ']+[ ]*)")) {
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

    private void handleBackButton() {
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingSystemGUI gui = new BookingSystemGUI();
            gui.display();

            try {
                DatabaseUtility dbUtil = DatabaseUtility.getInstance();
                dbUtil.insertMovie(new Movie("Inception", "A thief who enters the dreams of others must perform the impossible."));
                dbUtil.insertMovie(new Movie("The Matrix", "A computer hacker learns about the true nature of his reality and his role in the war against its controllers."));
                dbUtil.printAllMovies();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        });
    }

}
