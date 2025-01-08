import javax.imageio.ImageIO;
import javax.swing.*; 
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import utils.DatabaseConnection;

public class AdminSigninPanel extends AbstractPanel { 
    private JTextField firstNameField; 
    private JTextField lastNameField; 
    private JTextField emailField; 
    private JTextField positionField; 
    private JTextField usernameField; 
    private JPasswordField passwordField; 
    private JPasswordField confirmPasswordField; 
    private JButton createAccountButton; 
    private JButton backButton; 
    private Image backgroundImage;

    public AdminSigninPanel(JPanel mainPanel, CardLayout cardLayout) { 
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
    protected void initializeComponents() { 
        // Initialize all fields with consistent sizing 
        Dimension fieldSize = new Dimension(300, 40); 
        Font fieldFont = new Font("Helvetica", Font.PLAIN, 16); 
 
        firstNameField = createStyledTextField(fieldSize, fieldFont); 
        lastNameField = createStyledTextField(fieldSize, fieldFont); 
        emailField = createStyledTextField(fieldSize, fieldFont); 
        positionField = createStyledTextField(fieldSize, fieldFont); 
        usernameField = createStyledTextField(fieldSize, fieldFont); 
        passwordField = createStyledPasswordField(fieldSize, fieldFont); 
        confirmPasswordField = createStyledPasswordField(fieldSize, fieldFont); 
 
        createAccountButton = new JButton("CREATE ADMIN ACCOUNT"); 
        createAccountButton.setPreferredSize(new Dimension(250, 50)); 
        createAccountButton.setFont(new Font("Helvetica", Font.BOLD, 16)); 
 
        backButton = new JButton("BACK TO LOGIN"); 
        backButton.setPreferredSize(new Dimension(250, 50)); 
        backButton.setFont(new Font("Helvetica", Font.BOLD, 16)); 
    } 
 
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
    protected void setupLayout() {   
        removeAll(); // Add this line to clear any existing components 
        setLayout(new GridBagLayout());   
        GridBagConstraints gbc = new GridBagConstraints();   
        
        // Center panel to hold all components 
        JPanel centerPanel = new JPanel(new GridBagLayout()); 
        centerPanel.setOpaque(false); 
        
        // Header   
        gbc.insets = new Insets(0, 0, 30, 0);   
        gbc.gridx = 0;   
        gbc.gridy = 0;   
        gbc.gridwidth = 2;   
        gbc.anchor = GridBagConstraints.CENTER; 
        JLabel headerLabel = new JLabel("CREATE ADMIN ACCOUNT");   
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 30));   
        headerLabel.setForeground(Color.WHITE); 
        centerPanel.add(headerLabel, gbc);   
    
        // Reset insets for form fields 
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.gridwidth = 1; 
    
        // Add form fields 
        addFormField("FIRST NAME:", firstNameField, gbc, 1, centerPanel);   
        addFormField("LAST NAME:", lastNameField, gbc, 2, centerPanel);   
        addFormField("EMAIL:", emailField, gbc, 3, centerPanel);   
        addFormField("POSITION:", positionField, gbc, 4, centerPanel);   
        addFormField("USERNAME:", usernameField, gbc, 5, centerPanel);   
        addFormField("PASSWORD:", passwordField, gbc, 6, centerPanel);   
        addFormField("CONFIRM PASSWORD:", confirmPasswordField, gbc, 7, centerPanel);   
    
        // Show Password Checkbox   
        gbc.gridx = 1;   
        gbc.gridy = 8;   
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
        centerPanel.add(showPassword, gbc);   
    
        // Button panel 
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 10)); 
        buttonPanel.setOpaque(false); 
    
        buttonPanel.add(createAccountButton); 
        buttonPanel.add(backButton); 
    
        // Add button panel 
        gbc.gridx = 0;   
        gbc.gridy = 9;   
        gbc.gridwidth = 2;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(20, 0, 0, 0);   
        centerPanel.add(buttonPanel, gbc); 
    
        // Add the center panel to the main panel 
        add(centerPanel); 
        
        revalidate(); // Add this line to refresh the layout 
        repaint();    // Add this line to refresh the painting 
    }
 
    private void addFormField(String label, JComponent field, GridBagConstraints gbc, int row, JPanel panel) {   
        gbc.gridx = 0;   
        gbc.gridy = row;  
        gbc.anchor = GridBagConstraints.EAST;   
        JLabel fieldLabel = new JLabel(label); 
        fieldLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); 
        fieldLabel.setForeground(Color.WHITE); 
        panel.add(fieldLabel, gbc);   
    
        gbc.gridx = 1;   
        gbc.anchor = GridBagConstraints.WEST;   
        panel.add(field, gbc);   
    } 
 
    @Override 
    protected void setupListeners() { 
        createAccountButton.addActionListener(e -> { 
            if (validateFields()) { 
                createAdminAccount(); 
            } 
        }); 
 
        backButton.addActionListener(e -> switchPanel("AdminLoginPanel")); 
    } 
 
    private boolean validateFields() { 
        if (firstNameField.getText().trim().isEmpty() || 
            lastNameField.getText().trim().isEmpty() || 
            emailField.getText().trim().isEmpty() || 
            positionField.getText().trim().isEmpty() || 
            usernameField.getText().trim().isEmpty() || 
            passwordField.getPassword().length == 0 || 
            confirmPasswordField.getPassword().length == 0) { 
             
            showMessage("All fields are required!"); 
            return false; 
        } 
 
        String password = new String(passwordField.getPassword()); 
        String confirmPassword = new String(confirmPasswordField.getPassword()); 
         
        if (!password.equals(confirmPassword)) { 
            showMessage("Passwords do not match!"); 
            return false; 
        } 
 
        // Add email validation if needed 
        if (!emailField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) { 
            showMessage("Please enter a valid email address!"); 
            return false; 
        } 
 
        return true; 
    } 
 
    private void createAdminAccount() { 
        String firstName = firstNameField.getText().trim(); 
        String lastName = lastNameField.getText().trim(); 
        String email = emailField.getText().trim(); 
        String position = positionField.getText().trim(); 
        String username = usernameField.getText().trim(); 
        String password = new String(passwordField.getPassword()); 
     
        try (Connection conn = DatabaseConnection.getConnection()) { 
            // Check if username already exists 
            String checkQuery = "SELECT username FROM admins WHERE username = ?"; 
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) { 
                checkStmt.setString(1, username); 
                ResultSet rs = checkStmt.executeQuery(); 
                 
                if (rs.next()) { 
                    showMessage("Username already exists!"); 
                    return; 
                } 
            } 
     
            // Insert new admin with auto-generated ID 
            String insertQuery = "INSERT INTO admins (first_name, last_name, email, position, username, password) VALUES (?, ?, ?, ?, ?, ?)"; 
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) { 
                insertStmt.setString(1, firstName); 
                insertStmt.setString(2, lastName); 
                insertStmt.setString(3, email); 
                insertStmt.setString(4, position); 
                insertStmt.setString(5, username); 
                insertStmt.setString(6, password); 
                 
                int rowsAffected = insertStmt.executeUpdate(); 
                if (rowsAffected > 0) { 
                    // Get the generated admin ID 
                    ResultSet rs = insertStmt.getGeneratedKeys(); 
                    if (rs.next()) { 
                        String adminId = "ADM" + String.format("%04d", rs.getInt(1)); 
                        // Update the admin record with the formatted ID 
                        String updateQuery = "UPDATE admins SET admin_id = ? WHERE id = ?"; 
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) { 
                            updateStmt.setString(1, adminId); 
                            updateStmt.setInt(2, rs.getInt(1)); 
                            updateStmt.executeUpdate(); 
                        } 
                         
                        // Show message with admin ID and credentials 
                        String message = "Account created successfully!\n\n" + 
                                       "Please save these credentials:\n" + 
                                       "Admin ID: " + adminId + "\n" + 
                                       "Username: " + username + "\n" + 
                                       "Password: " + password; 
                        JOptionPane.showMessageDialog(this, message, "Account Created", JOptionPane.INFORMATION_MESSAGE); 
                         
                        clearFields(); 
                        switchPanel("adminLoginPanel"); 
                    } 
                } else { 
                    showMessage("Error creating admin account"); 
                } 
            } 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            showMessage("Error creating admin account: " + ex.getMessage()); 
        } 
    } 
 
    private void clearFields() { 
        firstNameField.setText(""); 
        lastNameField.setText(""); 
        emailField.setText(""); 
        positionField.setText(""); 
        usernameField.setText(""); 
        passwordField.setText(""); 
        confirmPasswordField.setText(""); 
    }
    
    @Override
    public void addComponents() {
        
    }
}