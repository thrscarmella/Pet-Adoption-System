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

public class AdminRemovePetPanel extends AbstractPanel {
    private JTextField petIdField;
    private JButton removeButton;
    private JButton backButton;
    private Image backgroundImage;

    public AdminRemovePetPanel(JPanel mainPanel, CardLayout cardLayout) {
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
        petIdField = new JTextField(20);
        petIdField.setPreferredSize(new Dimension(300, 40));
        petIdField.setFont(new Font("Helvetica", Font.PLAIN, 16));

        removeButton = new JButton("REMOVE");
        removeButton.setPreferredSize(new Dimension(200, 60));
        removeButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        removeButton.setFocusPainted(false);

        backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(200, 60));
        backButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        backButton.setFocusPainted(false);
    }

    @Override
    public void setupLayout() {
        removeAll();
        setLayout(new GridBagLayout());
        JPanel centerPanel = new JPanel(new GridBagLayout()); 
        centerPanel.setOpaque(false); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel headerLabel = new JLabel("REMOVE PET");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel petIdLabel = new JLabel("Pet ID:");
        petIdLabel.setFont(new Font("Helvetica", Font.BOLD, 22));
        petIdLabel.setForeground(Color.WHITE);
        add(petIdLabel, gbc);

        gbc.gridx = 1;
        add(petIdField, gbc);

        // Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        buttonPanel.setOpaque(false);
        add(buttonPanel, gbc);

        add(centerPanel); 

        revalidate();
        repaint();
    }

    @Override 
    protected void setupListeners() { 
        removeButton.addActionListener(e -> { 
            String petId = petIdField.getText(); 
            if (petId.isEmpty()) { 
                showMessage("Please enter a Pet ID"); 
                return; 
            } 
 
            try (Connection conn = DatabaseConnection.getConnection()) { 
                // First check if pet exists and get its status 
                String checkQuery = "SELECT status FROM pets WHERE pet_id = ?"; 
                try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) { 
                    checkStmt.setString(1, petId); 
                    ResultSet rs = checkStmt.executeQuery(); 
                     
                    if (!rs.next()) { 
                        showMessage("Pet ID not found"); 
                        return; 
                    } 
                     
                    String status = rs.getString("status"); 
                    if (!"available".equals(status)) { 
                        showMessage("Cannot remove pet - current status is: " + status); 
                        return; 
                    } 
                }

                String updateQuery = "UPDATE pets SET status = 'removed' WHERE pet_id = ?"; 
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) { 
                    updateStmt.setString(1, petId); 
                    int rowsAffected = updateStmt.executeUpdate(); 
                     
                    if (rowsAffected > 0) { 
                        showMessage("Pet removed successfully!"); 
                        petIdField.setText(""); 
                         
                        // Refresh any visible adoption panels 
                        refreshAdoptionPanels(); 
                    } else { 
                        showMessage("Error removing pet"); 
                    } 
                } 
            } catch (SQLException ex) { 
                ex.printStackTrace(); 
                showMessage("Error removing pet from database: " + ex.getMessage()); 
            } 
        }); 
 
        backButton.addActionListener(e -> switchPanel("adminPetPanel")); 
    }

    private void refreshAdoptionPanels() { 
        Component[] components = mainPanel.getComponents(); 
        for (Component comp : components) { 
            if (comp instanceof AbstractAnimalAdoptionPanel) { 
                AbstractAnimalAdoptionPanel panel = (AbstractAnimalAdoptionPanel) comp; 
                panel.refreshPanel(); 
                panel.loadPets(); 
            } 
        } 
    }

    @Override
    public void addComponents() {
        // Components are added in setupLayout()
    }
}