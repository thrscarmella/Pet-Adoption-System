import javax.imageio.ImageIO;
import javax.swing.*;

import utils.DatabaseConnection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SigninPanel extends AbstractPanel {
    private JTextField emailTextField;
    private JTextField userTextField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signinButton;
    private Image backgroundImage;

    public SigninPanel(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout);

        try { 
            backgroundImage = ImageIO.read(new File("C:\\Users\\mcdeu\\Documents\\object\\o.png\\"));
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
 
        // Make panel transparent 
        setOpaque(false); 
        initializeComponents(); 
        setupLayout(); 
        setupListeners(); 
    }

    @Override 
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g); 
         
        if (backgroundImage != null) { 
            Graphics2D g2d = (Graphics2D) g; 
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,  
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
             
            double scaleFactor = Math.max( 
                (double) getWidth() / backgroundImage.getWidth(null), 
                (double) getHeight() / backgroundImage.getHeight(null) 
            ); 
             
            int scaledWidth = (int) (backgroundImage.getWidth(null) * scaleFactor); 
            int scaledHeight = (int) (backgroundImage.getHeight(null) * scaleFactor); 
             
            int x = (getWidth() - scaledWidth) / 2; 
            int y = (getHeight() - scaledHeight) / 2; 
             
            g2d.drawImage(backgroundImage, x, y, scaledWidth, scaledHeight, null); 
             
            // Semi-transparent overlay 
            g2d.setColor(new Color(0, 0, 0, 120)); 
            g2d.fillRect(0, 0, getWidth(), getHeight()); 
        } 
    }

    @Override  
    public void initializeComponents() {  
        // Initialize fields with consistent sizing   
        Dimension fieldSize = new Dimension(300, 40);   
        Font fieldFont = new Font("Helvetica", Font.PLAIN, 16);   
    
        // Initialize all fields with consistent style 
        emailTextField = createStyledTextField(fieldSize, fieldFont); 
        userTextField = createStyledTextField(fieldSize, fieldFont); 
        firstNameField = createStyledTextField(fieldSize, fieldFont); 
        lastNameField = createStyledTextField(fieldSize, fieldFont); 
        passwordField = createStyledPasswordField(fieldSize, fieldFont); 
        confirmPasswordField = createStyledPasswordField(fieldSize, fieldFont); 
        
        // Initialize buttons with consistent style 
        signinButton = new JButton("CREATE ACCOUNT"); 
        signinButton.setPreferredSize(new Dimension(250, 50)); 
        signinButton.setFont(new Font("Helvetica", Font.BOLD, 16)); 
    }  
 
    // Add these helper methods 
    private JTextField createStyledTextField(Dimension size, Font font) {   
        JTextField field = new JTextField(20);   
        field.setPreferredSize(size);   
        field.setFont(font);   
        return field;   
    }   
 
    private JPasswordField createStyledPasswordField(Dimension size, Font font) {   
        JPasswordField field = new JPasswordField(20);   
        field.setPreferredSize(size);   
        field.setFont(font);   
        return field;   
    } 
 
    @Override  
    public void setupLayout() {  
        removeAll(); // Add this line to clear any existing components 
        setLayout(new GridBagLayout());   
        GridBagConstraints gbc = new GridBagConstraints(); 
        
        JPanel centerPanel = new JPanel(new GridBagLayout()); 
        centerPanel.setOpaque(false);
        
        // Header 
        gbc.insets = new Insets(0, 0, 30, 0);  
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        gbc.gridwidth = 2;  
        gbc.anchor = GridBagConstraints.CENTER;  
        JLabel signInLabel = new JLabel("CREATE AN ACCOUNT");  
        signInLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        signInLabel.setForeground(Color.WHITE);
        centerPanel.add(signInLabel, gbc);  
        add(signInLabel, gbc);  
    
        // Reset insets for other components 
        gbc.insets = new Insets(5, 5, 5,5); 
        gbc.gridwidth = 1; 
    
        // Add form fields using helper method 
        addFormField("FIRST NAME:", firstNameField, gbc, 1); 
        addFormField("LAST NAME:", lastNameField, gbc, 2); 
        addFormField("EMAIL:", emailTextField, gbc, 3); 
        addFormField("USERNAME:", userTextField, gbc, 4); 
        addFormField("PASSWORD:", passwordField, gbc, 5); 
        addFormField("CONFIRM PASSWORD:", confirmPasswordField, gbc, 6); 
    
        // Show Password checkbox   
        gbc.gridx = 1;  
        gbc.gridy = 7;  
        gbc.anchor = GridBagConstraints.WEST;  
        JCheckBox showPassword = new JCheckBox("Show Password");  
        showPassword.setFont(new Font("Helvetica", Font.PLAIN, 16));
        showPassword.setForeground(Color.WHITE); 
        showPassword.setOpaque(false); 
        showPassword.setFocusPainted(false); 
        showPassword.addActionListener(e -> {  
            if (showPassword.isSelected()) {  
                passwordField.setEchoChar((char) 0);  
                confirmPasswordField.setEchoChar((char) 0);  
            } else {  
                passwordField.setEchoChar('•');  
                confirmPasswordField.setEchoChar('•');  
            }  
        });  
        add(showPassword, gbc);  
    
        // Create Account Button  
        gbc.gridx = 0;  
        gbc.gridy = 8;  
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;  
        add(signinButton, gbc);  
    
        // Back to Login Button  
        gbc.gridy = 9;  
        JButton backToLoginButton = new JButton("BACK TO LOGIN");  
        backToLoginButton.setPreferredSize(new Dimension(250, 50)); 
        backToLoginButton.setFont(new Font("Helvetica", Font.BOLD, 16)); 
        backToLoginButton.addActionListener(e -> switchPanel("LoginPanel"));  
        add(backToLoginButton, gbc);
        
        add(centerPanel);
        revalidate(); // Add this line to refresh the layout 
        repaint();    // Add this line to refresh the painting
    }  
 
    // Add this helper method 
    private void addFormField(String label, JComponent field, GridBagConstraints gbc, int row) {   
        gbc.gridx = 0;   
        gbc.gridy = row;   
        gbc.anchor = GridBagConstraints.EAST;   
        JLabel fieldLabel = new JLabel(label); 
        fieldLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        fieldLabel.setForeground(Color.WHITE);  // Add this line 
        add(fieldLabel, gbc);   
    
        gbc.gridx = 1;   
        gbc.anchor = GridBagConstraints.WEST;   
        add(field, gbc);   
    }

    @Override
    public void setupListeners() {
        signinButton.addActionListener(e -> {
            if (validateFields()) {
                handleSignIn();
            }
        });
    }

    private boolean validateFields() {
        if (firstNameField.getText().trim().isEmpty() ||
            lastNameField.getText().trim().isEmpty() ||
            emailTextField.getText().trim().isEmpty() ||
            userTextField.getText().trim().isEmpty() ||
            passwordField.getPassword().length == 0 ||
            confirmPasswordField.getPassword().length == 0) {
            
            JOptionPane.showMessageDialog(this, "All fields are required!");  // Changed this line
            return false;
        }

        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");  // Changed this line
            return false;
        }

        return true;
    }

    private void handleSignIn() { 
    String firstName = firstNameField.getText().trim(); 
    String lastName = lastNameField.getText().trim(); 
    String email = emailTextField.getText().trim(); 
    String username = userTextField.getText().trim(); 
    String password = new String(passwordField.getPassword()); 
     
    try (Connection conn = DatabaseConnection.getConnection()) { 
        // First check if username already exists 
        String checkQuery = "SELECT username FROM users WHERE username = ?"; 
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) { 
            checkStmt.setString(1, username); 
            ResultSet rs = checkStmt.executeQuery(); 
             
            if (rs.next()) { 
                showMessage("Username already exists. Please choose another."); 
                return; 
            } 
        } 
         
        // Insert new user 
        String insertQuery = "INSERT INTO users (first_name, last_name, email, username, password) VALUES (?, ?, ?, ?, ?)"; 
        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) { 
            insertStmt.setString(1, firstName); 
            insertStmt.setString(2, lastName); 
            insertStmt.setString(3, email); 
            insertStmt.setString(4, username); 
            insertStmt.setString(5, password); 
             
            int rowsAffected = insertStmt.executeUpdate(); 
            if (rowsAffected > 0) { 
                showMessage("Account created successfully!"); 
                clearFields(); 
                switchPanel("loginPanel"); 
            } else { 
                showMessage("Error creating account"); 
            } 
        } 
    } catch (SQLException ex) { 
        ex.printStackTrace(); 
        showMessage("Error creating account: " + ex.getMessage()); 
    } 
}

    private void clearFields() { 
        firstNameField.setText(""); 
        lastNameField.setText(""); 
        emailTextField.setText(""); 
        userTextField.setText(""); 
        passwordField.setText(""); 
        confirmPasswordField.setText(""); 
    }

    @Override
    public void addComponents() {
        
    }
}