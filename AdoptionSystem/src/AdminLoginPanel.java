import javax.imageio.ImageIO;
import javax.swing.*;
import utils.DatabaseConnection;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginPanel extends JPanel implements PanelInterface{ 
    private JPanel mainPanel; 
    private CardLayout cardLayout; 
    private Image backgroundImage;  
    private JTextField adminIdField; 
    private JTextField adminUsernameField; 
    private JPasswordField adminPasswordField; 
    private JButton adminLoginSubmitButton; 
  
    public AdminLoginPanel(JPanel mainPanel, CardLayout cardLayout) { 
        this.mainPanel = mainPanel; 
        this.cardLayout = cardLayout;
        
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
    
        // Initialize fields with consistent style  
        adminIdField = createStyledTextField(fieldSize, fieldFont);  
        adminUsernameField = createStyledTextField(fieldSize, fieldFont);  
        adminPasswordField = createStyledPasswordField(fieldSize, fieldFont);  
        
        // Initialize button with consistent style  
        adminLoginSubmitButton = new JButton("LOGIN");  
        adminLoginSubmitButton.setPreferredSize(new Dimension(250, 50));  
        adminLoginSubmitButton.setFont(new Font("Helvetica", Font.BOLD, 16));  
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
        setLayout(new GridBagLayout());    
        GridBagConstraints gbc = new GridBagConstraints();    
    
        // Header    
        gbc.insets = new Insets(10, 10, 30, 10);  // More space below header  
        gbc.gridx = 0;    
        gbc.gridy = 0;    
        gbc.gridwidth = 2;    
        gbc.anchor = GridBagConstraints.CENTER;    
        JLabel adoptLabel = new JLabel("ADMIN PET ADOPTION LOGIN");    
        adoptLabel.setFont(new Font("Helvetica", Font.BOLD, 30));  
        adoptLabel.setForeground(Color.WHITE);  
        add(adoptLabel, gbc);    
    
        // Reset insets for other components  
        gbc.insets = new Insets(10, 10, 10, 10);  
        gbc.gridwidth = 1;  
    
        // Add form fields using helper method  
        addFormField("ADMIN ID:", adminIdField, gbc, 1);  
        addFormField("USERNAME:", adminUsernameField, gbc, 2);  
        addFormField("PASSWORD:", adminPasswordField, gbc, 3);  
    
        // Show Password checkbox    
        gbc.gridx = 1;    
        gbc.gridy = 4;    
        gbc.anchor = GridBagConstraints.WEST;    
        JCheckBox showPassword = new JCheckBox("Show Password");    
        showPassword.setFont(new Font("Helvetica", Font.PLAIN, 16));
        showPassword.setForeground(Color.WHITE);  // Make text white 
        showPassword.setOpaque(false);  // Make background transparent 
        showPassword.setFocusPainted(false);  // Remove focus border 
        showPassword.addActionListener(e -> {    
            if (showPassword.isSelected()) {    
                adminPasswordField.setEchoChar((char) 0);    
            } else {    
                adminPasswordField.setEchoChar('•');    
            }    
        });    
        add(showPassword, gbc);    
    
        // Login button    
        gbc.gridx = 0;    
        gbc.gridy = 5;    
        gbc.gridwidth = 2;  
        gbc.anchor = GridBagConstraints.CENTER;   
        add(adminLoginSubmitButton, gbc);    
    
        // Sign in button    
        gbc.gridy = 6;    
        JButton signInButton = new JButton("SIGN IN");    
        signInButton.setPreferredSize(new Dimension(250, 50));    
        signInButton.setFont(new Font("Helvetica", Font.BOLD, 16));    
        signInButton.addActionListener(e -> switchPanel("adminSigninPanel"));   
        signInButton.setFocusPainted(false);    
        add(signInButton, gbc);  
    }   
  
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
    public void addComponents() {   
        // Not needed as components are added in setupLayout  
    }   
   
    @Override     
    public void setupListeners() {     
        adminLoginSubmitButton.addActionListener(e -> {     
            String adminId = adminIdField.getText().trim();     
            String username = adminUsernameField.getText().trim();
            String password = new String(adminPasswordField.getPassword());     
                
            if (adminId.isEmpty()||username.isEmpty()||password.isEmpty()) {     
                showMessage("Please enter all crendetials"); 
                return;     
            }     
                
            try (Connection conn = DatabaseConnection.getConnection()) {     
                String query = "SELECT * FROM admins WHERE admin_id = ? AND username = ? AND password = ?";     
                try (PreparedStatement stmt = conn.prepareStatement(query)) {     
                    stmt.setString(1, adminId);     
                    stmt.setString(2, username);     
                    stmt.setString(3, password);     
                        
                    ResultSet rs = stmt.executeQuery();     
                    if (rs.next()) {    
                        String adminName = rs.getString("first_name") + " " + rs.getString("last_name");    
                            
                        Component[] components = mainPanel.getComponents();    
                        for (Component comp : components) {    
                            if (comp instanceof AdminPetPanel) {    
                                ((AdminPetPanel) comp).setAdminInfo(adminName, adminId);    
                                break;    
                            }    
                        }    
                            
                        showMessage("Admin Login Successful");     
                        clearFields();     
                        switchPanel("adminPetPanel");     
                    } else {     
                        showMessage("Invalid admin credentials");     
                    }     
                }     
            } catch (SQLException ex) {     
                ex.printStackTrace();     
                showMessage("Error during login: " + ex.getMessage());     
            }     
        });     
    }   

    // Add these missing methods 
    private void showMessage(String message) { 
        JOptionPane.showMessageDialog(this, message); 
    } 
 
    private void switchPanel(String panelName) { 
        cardLayout.show(mainPanel, panelName); 
    }
   
    private void clearFields() {       
        adminIdField.setText("");   
        adminUsernameField.setText("");   
        adminPasswordField.setText("");   
    }   
}