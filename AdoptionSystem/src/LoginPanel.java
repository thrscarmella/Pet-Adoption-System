import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseConnection;

public class LoginPanel extends AbstractPanel { 
    private JTextField usernameField; 
    private JPasswordField passwordField; 
    private JButton loginSubmitButton; 
    private JButton signInButton; 
    private JLabel headerLabel; 
    private JCheckBox showPassword;
    private Image backgroundImage;  
 
    public LoginPanel(JPanel mainPanel, CardLayout cardLayout) { 
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
        // Initialize fields with consistent sizing 
        Dimension fieldSize = new Dimension(300, 40); 
        Font fieldFont = new Font("Helvetica", Font.PLAIN, 16); 
 
        headerLabel = new JLabel("PET ADOPTION LOGIN"); 
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 30)); 
 
        usernameField = new JTextField(20); 
        usernameField.setPreferredSize(fieldSize); 
        usernameField.setFont(fieldFont); 
 
        passwordField = new JPasswordField(20); 
        passwordField.setPreferredSize(fieldSize); 
        passwordField.setFont(fieldFont); 
 
        showPassword = new JCheckBox("Show Password"); 
 
        loginSubmitButton = new JButton("LOGIN"); 
        loginSubmitButton.setPreferredSize(new Dimension(250, 50)); 
        loginSubmitButton.setFont(new Font("Helvetica", Font.BOLD, 16)); 
 
        signInButton = new JButton("SIGN IN"); 
        signInButton.setPreferredSize(new Dimension(250, 50)); 
        signInButton.setFont(new Font("Helvetica", Font.BOLD, 16)); 
    } 
 
    @Override   
    protected void setupLayout() {
        removeAll();
        setLayout(new GridBagLayout());   
        
        // Create a center panel to hold all components 
        JPanel centerPanel = new JPanel(new GridBagLayout()); 
        centerPanel.setOpaque(false); 
        
        GridBagConstraints gbc = new GridBagConstraints();   
        gbc.insets = new Insets(10, 10, 10, 10);   
    
        // Header   
        gbc.gridx = 0;   
        gbc.gridy = 0;   
        gbc.gridwidth = 2;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(0, 0, 30, 0);  // More space below header 
        headerLabel.setForeground(Color.WHITE);  // Make header visible 
        centerPanel.add(headerLabel, gbc);   
    
        // Reset insets for form fields 
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.gridwidth = 1; 
    
        // Username   
        gbc.gridx = 0;   
        gbc.gridy = 1;   
        gbc.anchor = GridBagConstraints.EAST;   
        JLabel usernameLabel = new JLabel("USERNAME:"); 
        usernameLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); 
        usernameLabel.setForeground(Color.WHITE); 
        centerPanel.add(usernameLabel, gbc);   
    
        gbc.gridx = 1;   
        gbc.anchor = GridBagConstraints.WEST;   
        centerPanel.add(usernameField, gbc);   
    
        // Password   
        gbc.gridx = 0;   
        gbc.gridy = 2;   
        gbc.anchor = GridBagConstraints.EAST;   
        JLabel passwordLabel = new JLabel("PASSWORD:"); 
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); 
        passwordLabel.setForeground(Color.WHITE); 
        centerPanel.add(passwordLabel, gbc);   
    
        gbc.gridx = 1;   
        gbc.anchor = GridBagConstraints.WEST;   
        centerPanel.add(passwordField, gbc);   
    
        // Show Password Checkbox   
        gbc.gridx = 1;   
        gbc.gridy = 3;   
        gbc.anchor = GridBagConstraints.WEST;   
        showPassword.setFont(new Font("Helvetica", Font.PLAIN, 16)); 
        showPassword.setForeground(Color.WHITE); 
        showPassword.setOpaque(false); 
        showPassword.setFocusPainted(false); 
        centerPanel.add(showPassword, gbc);   
    
        // Button Panel 
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 10)); 
        buttonPanel.setOpaque(false); 
    
        // Add buttons 
        buttonPanel.add(loginSubmitButton); 
        buttonPanel.add(signInButton); 
    
        // Add button panel 
        gbc.gridx = 0;   
        gbc.gridy = 4;   
        gbc.gridwidth = 2;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(20, 0, 0, 0);  // Space above buttons 
        centerPanel.add(buttonPanel, gbc); 
    
        // Add the center panel to the main panel 
        add(centerPanel); 

        revalidate();
        repaint();
    }
 
    @Override 
    protected void addComponents() { 
     
    }
 
    @Override   
    protected void setupListeners() {   
        // Add show password listener 
        showPassword.addActionListener(e -> { 
            if (showPassword.isSelected()) { 
                passwordField.setEchoChar((char) 0); 
            } else { 
                passwordField.setEchoChar('•'); 
            } 
        }); 
 
    loginSubmitButton.addActionListener(e -> {   
        String username = usernameField.getText().trim();   
        String password = new String(passwordField.getPassword());   
   
        try (Connection conn = DatabaseConnection.getConnection()) {   
            String query = "SELECT email, username FROM users WHERE username = ? AND password = ?";   
            try (PreparedStatement stmt = conn.prepareStatement(query)) {   
                stmt.setString(1, username);   
                stmt.setString(2, password);   
                ResultSet rs = stmt.executeQuery();   
   
                if (rs.next()) {   
                    String email = rs.getString("email");   
                    System.out.println("LoginPanel - Login successful for email: " + email); 
                    Component[] components = mainPanel.getComponents();   
                    for (Component comp : components) {   
                        if (comp instanceof AvailablePetsPanel) {   
                            System.out.println("LoginPanel - Setting email in AvailablePetsPanel");   
                            ((AvailablePetsPanel) comp).setUserEmail(email);   
                            break;   
                        }   
                    }   
                    clearFields();  // Clear fields after successful login 
                    switchPanel("availablePetsPanel");   
                } else {   
                    showMessage("Invalid username or password");   
                }   
            }   
        } catch (SQLException ex) {   
            ex.printStackTrace();   
            showMessage("Database error: " + ex.getMessage());   
        }   
    });   
   
    signInButton.addActionListener(e -> switchPanel("SigninPanel"));   
}

private void handleSuccessfulLogin(String email) { 
    // Get both panels that need the email 
    Component[] components = mainPanel.getComponents(); 
    for (Component comp : components) { 
        // Set email for AvailablePetsPanel 
        if (comp instanceof AvailablePetsPanel) { 
            AvailablePetsPanel petsPanel = (AvailablePetsPanel) comp; 
            petsPanel.setUserEmail(email); 
        } 
        // Set email for UserApplicationStatusPanel 
        if (comp instanceof UserApplicationStatusPanel) { 
            UserApplicationStatusPanel applicationsPanel = (UserApplicationStatusPanel) comp; 
            applicationsPanel.setUserEmail(email); 
            applicationsPanel.forceRefresh(); // Load applications immediately 
        } 
    } 
     
    // Switch to available pets panel 
    switchPanel("availablePetsPanel"); 
}
 
    private void clearFields() { 
        usernameField.setText(""); 
        passwordField.setText(""); 
    } 
}