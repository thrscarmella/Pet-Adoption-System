import javax.swing.*;
import utils.DatabaseConnection;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdoptionFormPanel extends AbstractPanel {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JComboBox<String> petComboBox;
    private JTextField petNameField;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> yearComboBox;
    private JTextArea reasonTextArea;
    private JTextArea petHistoryTextArea;
    private JTextArea homeStatusTextArea;
    private JTextArea householdTextArea;
    private JTextArea petCareTextArea;
    private JRadioButton behavioralYesButton;
    private JRadioButton behavioralNoButton;
    private String previousPanel;
    private JButton submitButton;  // Add this
    private String currentPetId;
    private String userEmail;
    private JTextArea experienceArea;    // Add this 
    private JTextArea environmentArea;    // Add this 
    private JTextArea reasonArea;


    public AdoptionFormPanel(JPanel mainPanel, CardLayout cardLayout, String previousPanel) {
        super(mainPanel, cardLayout);
        this.previousPanel = previousPanel;
        this.userEmail = "";
    }

    public void setUserEmail(String email) { 
        System.out.println("Setting user email to: " + email); // Debug print 
        if (email != null && !email.isEmpty()) { 
            this.userEmail = email; 
            if (emailField != null) { 
                emailField.setText(email); 
                emailField.setEditable(false); 
            } 
        }
    }

    public void setPetDetails(String petId, String petName) { 
        this.currentPetId = petId; 
        petNameField.setText(petName); 
        petNameField.setEditable(false); 
    } 

    @Override
    public void initializeComponents() {
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        addressField = new JTextField(20);
        experienceArea = new JTextArea(5, 20);
        environmentArea = new JTextArea(5, 20);
        reasonArea = new JTextArea(5, 20);
        
        String[] petOptions = {"Dog", "Cat", "Bird", "Fish", "Rabbit", "Snake", "Ferret", "Hamster", "Turtle", "Lizard"};
        petComboBox = new JComboBox<>(petOptions);
        petNameField = new JTextField(20);
        
        String[] months = {"January", "February", "March", "April", "May", "June", 
                          "July", "August", "September", "October", "November", "December"};
        monthComboBox = new JComboBox<>(months);
        
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i-1] = String.valueOf(i);
        }
        dayComboBox = new JComboBox<>(days);
        
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(currentYear - 18 - i);
        }
        yearComboBox = new JComboBox<>(years);
        
        reasonTextArea = new JTextArea(5, 40);
        petHistoryTextArea = new JTextArea(5, 40);
        homeStatusTextArea = new JTextArea(5, 40);
        householdTextArea = new JTextArea(5, 40);
        petCareTextArea = new JTextArea(5, 40);
        
        behavioralYesButton = new JRadioButton("Yes");
        behavioralNoButton = new JRadioButton("No");
    }

    @Override
    public void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header
        JLabel formLabel = new JLabel("Pet Adoption Application Form", SwingConstants.CENTER);
        formLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        add(formLabel, BorderLayout.NORTH);

        // Main content
        JPanel formContentPanel = createFormContentPanel();
        JScrollPane scrollPane = new JScrollPane(formContentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Single button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        submitButton = new JButton("Submit Application");
        submitButton.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createFormContentPanel() {
        JPanel formContentPanel = new JPanel();
        formContentPanel.setLayout(new BoxLayout(formContentPanel, BoxLayout.Y_AXIS));
        formContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Add all sections
        formContentPanel.add(createNamePanel());
        formContentPanel.add(createContactPanel());
        formContentPanel.add(createPetDetailsPanel());
        formContentPanel.add(createAddressPanel());
        formContentPanel.add(createDOBPanel());
        formContentPanel.add(createQuestionPanel("Why Do You Want to Adopt a Pet?", reasonTextArea));
        formContentPanel.add(createQuestionPanel("Have you ever had a pet before? If yes, what happened to them?", petHistoryTextArea));
        formContentPanel.add(createQuestionPanel("Do you rent or own your home? Are there any restrictions on pets?", homeStatusTextArea));
        formContentPanel.add(createQuestionPanel("Who else lives in your household? Are there children or other pets?", householdTextArea));
        formContentPanel.add(createQuestionPanel("Can you afford the costs of pet food, supplies, and veterinary care?", petCareTextArea));
        formContentPanel.add(createBehavioralIssuesPanel());

        return formContentPanel;
    }

    private JPanel createNamePanel() {
        JPanel namePanel = new JPanel(new GridBagLayout());
        namePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 5, 0, 5);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        namePanel.add(firstNameLabel, gbc);

        firstNameField.setPreferredSize(new Dimension(300, 50));
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        namePanel.add(firstNameField, gbc);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 2;
        namePanel.add(lastNameLabel, gbc);

        lastNameField.setPreferredSize(new Dimension(300, 50));
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 3;
        namePanel.add(lastNameField, gbc);

        return namePanel;
    }

    private JPanel createContactPanel() {
        JPanel contactPanel = new JPanel(new GridBagLayout());
        contactPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 5, 0, 5);

        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        contactPanel.add(emailLabel, gbc);

        emailField.setPreferredSize(new Dimension(300, 50));
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        contactPanel.add(emailField, gbc);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 2;
        contactPanel.add(phoneLabel, gbc);

        phoneField.setPreferredSize(new Dimension(300, 50));
        phoneField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 3;
        contactPanel.add(phoneField, gbc);

        return contactPanel;
    }

    private JPanel createPetDetailsPanel() {
        JPanel petDetailsPanel = new JPanel(new GridBagLayout());
        petDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel petTypeLabel = new JLabel("Type of Pet:");
        petTypeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        petDetailsPanel.add(petTypeLabel, gbc);

        petComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        petDetailsPanel.add(petComboBox, gbc);

        JLabel petNameLabel = new JLabel("Name of Pet to Adopt:");
        petNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 2;
        petDetailsPanel.add(petNameLabel, gbc);

        petNameField.setPreferredSize(new Dimension(300, 50));
        petNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 3;
        petDetailsPanel.add(petNameField, gbc);

        return petDetailsPanel;
    }

    private JPanel createAddressPanel() {
        JPanel addressPanel = new JPanel(new GridBagLayout());
        addressPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        addressPanel.add(addressLabel, gbc);

        addressField.setPreferredSize(new Dimension(500, 50));
        addressField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        addressPanel.add(addressField, gbc);

        return addressPanel;
    }

    private JPanel createDOBPanel() {
        JPanel dobPanel = new JPanel(new GridBagLayout());
        dobPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        dobPanel.add(dobLabel, gbc);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        datePanel.add(monthComboBox);
        datePanel.add(dayComboBox);
        datePanel.add(yearComboBox);

        gbc.gridx = 1;
        dobPanel.add(datePanel, gbc);

        return dobPanel;
    }

    private JPanel createQuestionPanel(String question, JTextArea textArea) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel questionLabel = new JLabel(question);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(questionLabel, gbc);

        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 100));
        gbc.gridy = 1;
        panel.add(scrollPane, gbc);

        return panel;
    }

    private JPanel createBehavioralIssuesPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel questionLabel = new JLabel("Are you comfortable handling a pet that may have behavioral issues?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(questionLabel, gbc);

        ButtonGroup group = new ButtonGroup();
        group.add(behavioralYesButton);
        group.add(behavioralNoButton);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(behavioralYesButton);
        radioPanel.add(behavioralNoButton);

        gbc.gridy = 1;
        panel.add(radioPanel, gbc);

        return panel;
    }

    
    private boolean validateForm() {  // Changed to return boolean 
        // Get all field values   
        String firstName = firstNameField.getText().trim();  
        String lastName = lastNameField.getText().trim();  
        String email = emailField.getText().trim();   
        String phone = phoneField.getText().trim();   
        String address = addressField.getText().trim();  
       
        // Create StringBuilder to collect all error messages   
        StringBuilder errorMessage = new StringBuilder("Please correct the following:\n\n");   
        boolean hasErrors = false;   
     
        // Validate name (only letters and spaces allowed)   
        if(firstName.isEmpty() || !firstName.matches("[a-zA-Z ]+")) {  
            errorMessage.append("- First name should only contain letters and spaces\n");  
            hasErrors = true;  
        }  
     
        if (lastName.isEmpty() || !lastName.matches("[a-zA-Z ]+")) {  
            errorMessage.append("- Last name should only contain letters and spaces\n");  
            hasErrors = true;  
        }  
     
        // Validate email (basic email format)   
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$") || !email.toLowerCase().endsWith("@gmail.com")) {   
            errorMessage.append("- Please enter a valid Gmail address\n");   
            hasErrors = true;   
        }   
       
        // Validate phone number (must be numbers only and 11 digits)   
        if (!phone.matches("\\d+") || phone.length() != 11) {   
            errorMessage.append("- Phone number must be 11 digits\n");   
            hasErrors = true;   
        }   
       
        // Validate address (cannot be empty)   
        if (address.isEmpty()) {   
            errorMessage.append("- Address cannot be empty\n");   
            hasErrors = true;   
        }     
     
        // Check if any field is empty   
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {   
            errorMessage.append("- All fields must be filled out\n");   
            hasErrors = true;   
        }   
       
        if (hasErrors) {   
            // Show error message dialog   
            JOptionPane.showMessageDialog(   
                this,   
                errorMessage.toString(),   
                "Form Error",   
                JOptionPane.ERROR_MESSAGE   
            ); 
            return false;  // Return false if there are errors 
        } else {   
            // All validations passed, show success message   
            JOptionPane.showMessageDialog(   
                this,   
                "Form submitted successfully!\nWe will contact you soon.",   
                "Success",   
                JOptionPane.INFORMATION_MESSAGE   
            );   
               
            // Return to previous panel   
            cardLayout.show(mainPanel, previousPanel);   
            return true;  // Return true if validation passes 
        }   
    }

    @Override
    protected void setupListeners() {
        submitButton.addActionListener(e -> {
            if (validateForm()) {
                submitApplication();
            }
        });
    }


    @Override
    public void addComponents() {
        // Components are added in setupLayout()
    }

    private void submitApplication() {

        String email = emailField.getText().trim(); 
        if (!email.isEmpty()) { 
            userEmail = email; 
        } 
 
        if (userEmail == null || userEmail.isEmpty()) { 
            showMessage("Error: User email is not set. Please log in again."); 
            return; 
        }

        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "INSERT INTO adoption_applications " + 
                        "(applicant_name, email, phone, address, " + 
                        "pet_type, pet_name, reason_for_adoption, pet_history, " + 
                        "home_status, household_info, pet_care, behavioral_acceptance, status, pet_id) " + 
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'pending', ?)"; 
                           
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                System.out.println("Debug: Submitting application for email: " + userEmail); 
                 
                stmt.setString(1, firstNameField.getText() + " " + lastNameField.getText()); 
                stmt.setString(2, userEmail); 
                stmt.setString(3, phoneField.getText()); 
                stmt.setString(4, addressField.getText()); 
                stmt.setString(5, petComboBox.getSelectedItem().toString()); 
                stmt.setString(6, petNameField.getText()); 
                stmt.setString(7, reasonTextArea.getText()); 
                stmt.setString(8, petHistoryTextArea.getText()); 
                stmt.setString(9, homeStatusTextArea.getText()); 
                stmt.setString(10, householdTextArea.getText()); 
                stmt.setString(11, petCareTextArea.getText()); 
                stmt.setString(12, behavioralYesButton.isSelected() ? "Yes" : "No");
                stmt.setString(13, currentPetId); 
                 
                int result = stmt.executeUpdate(); 
               
            if (result > 0) { 
                System.out.println("Debug: Application submitted successfully"); 
                 
                // Update UserApplicationStatusPanel before switching panels 
                UserApplicationStatusPanel userPanel = null; 
                Component[] components = mainPanel.getComponents(); 
                for (Component comp : components) { 
                    if (comp instanceof UserApplicationStatusPanel) { 
                        userPanel = (UserApplicationStatusPanel) comp; 
                        break; 
                    } 
                } 
 
                // Show success message 
                JOptionPane.showMessageDialog(this, 
                    "Thank you for submitting your adoption application!\n" + 
                    "We will review your application and contact you soon.", 
                    "Application Submitted", 
                    JOptionPane.INFORMATION_MESSAGE); 
                 
                clearFields(); 
 
                // Switch to UserApplicationStatusPanel and refresh it 
                if (userPanel != null) { 
                    userPanel.setUserEmail(userEmail); 
                    switchPanel("userApplicationStatusPanel"); 
                    userPanel.refreshApplications(); 
                } else { 
                    System.err.println("Debug: UserApplicationStatusPanel not found"); 
                    switchPanel("availablePetsPanel"); 
                } 
            } else { 
                throw new SQLException("Failed to insert record"); 
            } 
        } 
    } catch (SQLException ex) { 
            ex.printStackTrace(); 
            System.err.println("SQL Error: " + ex.getMessage()); 
            JOptionPane.showMessageDialog(this, 
                "Error submitting application: " + ex.getMessage() + "\nPlease try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE); 
        } 
    }


    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        petNameField.setText("");
        reasonTextArea.setText("");
        petHistoryTextArea.setText("");
        homeStatusTextArea.setText("");
        householdTextArea.setText("");
        petCareTextArea.setText("");
        behavioralNoButton.setSelected(true);
    }
}