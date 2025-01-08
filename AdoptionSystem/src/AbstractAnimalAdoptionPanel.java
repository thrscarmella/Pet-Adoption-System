import javax.swing.*;
import utils.DatabaseConnection;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractAnimalAdoptionPanel extends AbstractPanel {
    protected JLabel headerLabel;
    protected JPanel wrapperPanel;
    protected JPanel firstRowPanel;
    protected JScrollPane scrollPane;
    protected JPanel mainContentPanel;
    protected String animalType;
    protected String userEmail;

    public AbstractAnimalAdoptionPanel(JPanel mainPanel, CardLayout cardLayout, String animalType) {
        super(mainPanel, cardLayout);  
        this.animalType = animalType;
        this.userEmail = "";
    }
    
    @Override
    protected void initializeComponents() {
        headerLabel = new JLabel();
        wrapperPanel = new JPanel();
        firstRowPanel = new JPanel();
        mainContentPanel = new JPanel();
        scrollPane = new JScrollPane();
        
    }

    public void setUserEmail(String email) { 
        System.out.println("AbstractAnimalAdoptionPanel - Setting email: " + email); 
        this.userEmail = email; 
    } 
 
    protected void showAdoptionForm(String petId, String petName) { 
        System.out.println("AbstractAnimalAdoptionPanel - showAdoptionForm"); 
        System.out.println("Email: " + userEmail); 
        System.out.println("PetId: " + petId); 
        System.out.println("PetName: " + petName); 
 
        if (userEmail == null || userEmail.isEmpty()) { 
            showMessage("Please log in first"); 
            return; 
        } 
 
        Component[] components = mainPanel.getComponents(); 
        for (Component comp : components) { 
            if (comp instanceof AdoptionFormPanel) { 
                AdoptionFormPanel adoptionForm = (AdoptionFormPanel) comp; 
                adoptionForm.setUserEmail(userEmail); 
                adoptionForm.setPetDetails(petId, petName); 
                break; 
            } 
        } 
        switchPanel("adoptionFormPanel"); 
    } 

    protected void setupDatabasePetButton(JButton button, String petId, String name,  
                                      String age, String sex, String breed, String imagePath) { 
        button.addActionListener(e -> { 
            int choice = JOptionPane.showConfirmDialog( 
                this, 
                "Would you like to adopt " + name + "?", 
                "Confirm Adoption", 
                JOptionPane.YES_NO_OPTION 
            ); 
 
            if (choice == JOptionPane.YES_OPTION) { 
                showAdoptionForm(petId, name); 
            } 
        }); 
    }

    @Override 
    protected void setupLayout() { 
        setLayout(new BorderLayout());  
        wrapperPanel.setLayout(new BorderLayout());  
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));  
        
        firstRowPanel.setLayout(new GridLayout(0, 3, 20, 20));  
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
    }

    public void refreshPanel() { 
        firstRowPanel.removeAll(); 

        revalidate(); 
        repaint(); 
    }

    @Override 
    protected void addComponents() { 
        add(headerLabel, BorderLayout.NORTH); 
        add(wrapperPanel, BorderLayout.CENTER); 
        
        wrapperPanel.add(scrollPane, BorderLayout.CENTER); 
        scrollPane.setViewportView(firstRowPanel);  // Display firstRowPanel directly in scroll pane 
    }

    protected void createAnimalPanel(String imagePath, String id, String name, String age, 
                               String sex, String breed, JButton seeMoreButton) {

        // Create panel for the animal with less vertical spacing
        JPanel animalPanel = new JPanel();
        animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS));
        animalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Load and scale image
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create description panel with less spacing
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        
        // Add each detail with less vertical spacing
        String[] details = {
            "ID: " + id,
            "Name: " + name,
            "Age: " + age,
            "Sex: " + sex,
            "Breed: " + breed
        };
        
        for (String detail : details) {
            JLabel label = new JLabel(detail);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));  // Reduced vertical spacing
            descriptionPanel.add(label);
        }
        
        // Center the button
        seeMoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components with proper spacing
        animalPanel.add(imageLabel);
        animalPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Space after image
        animalPanel.add(descriptionPanel);
        animalPanel.add(Box.createRigidArea(new Dimension(0, 5)));   // Space before button
        animalPanel.add(seeMoreButton);
        
        // Add the animal panel to the first row panel
        firstRowPanel.add(animalPanel);
    }
    
    public String getAnimalType() {
        return animalType;
    }

    protected void loadPets() { 
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT * FROM pets WHERE type = ? AND status = 'available'"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                stmt.setString(1, animalType); 
                try (ResultSet rs = stmt.executeQuery()) { 
                    while (rs.next()) { 
                        String petId = rs.getString("pet_id"); 
                        String name = rs.getString("name"); 
                        String age = rs.getString("age"); 
                        String sex = rs.getString("sex"); 
                        String breed = rs.getString("breed"); 
                        String imagePath = rs.getString("image_path"); 
     
                        JButton seeMoreButton = new JButton("See More"); 
                        seeMoreButton.setPreferredSize(new Dimension(150, 40)); 
                        seeMoreButton.setFont(new Font("Arial", Font.BOLD, 16)); 
                         
                        // Use the new method to set up the button 
                        if (this instanceof DogAdoptionPanel) { 
                            ((DogAdoptionPanel) this).setupDatabasePetButton( 
                                seeMoreButton, petId, name, age, sex, breed, imagePath 
                            ); 
                        } 
     
                        createAnimalPanel(imagePath, petId, name, age, sex, breed, seeMoreButton);
                    } 
                } 
            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
            showMessage("Error loading pets: " + e.getMessage()); 
        } 
    }

    // Add this method to refresh the panel when it becomes visible
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }
    
    @Override
    protected abstract void setupListeners();  // Child classes must implement this

    protected void createPetDetailsPanel(String petId, String name, JPanel detailsPanel, JPanel infoPanel) { 
        Font labelFont = new Font("Arial", Font.BOLD, 16); 
        Font valueFont = new Font("Arial", Font.PLAIN, 16); 
     
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT * FROM pets WHERE pet_id = ?"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                stmt.setString(1, petId); 
                ResultSet rs = stmt.executeQuery(); 
     
                if (rs.next()) { 
                    // Basic info 
                    addDetailRow(infoPanel, "ID:", rs.getString("pet_id"), labelFont, valueFont); 
                    addDetailRow(infoPanel, "Name:", rs.getString("name"), labelFont, valueFont); 
                    addDetailRow(infoPanel, "Age:", rs.getString("age"), labelFont, valueFont); 
                    addDetailRow(infoPanel, "Sex:", rs.getString("sex"), labelFont, valueFont); 
                    addDetailRow(infoPanel, "Breed:", rs.getString("breed"), labelFont, valueFont); 
     
                    // Get pet type for specific details 
                    String petType = rs.getString("type"); 
     
                    // Type-specific details 
                    switch (petType.toLowerCase()) { 
                        case "dog": 
                            addTypeSpecificDetails(infoPanel, rs, "ear_type", "Ear Type", labelFont, valueFont); 
                            addTypeSpecificDetails(infoPanel, rs, "drooling_level", "Drooling Tendency", labelFont, valueFont); 
                            break; 
                        case "cat": 
                            addTypeSpecificDetails(infoPanel, rs, "ear_type", "Ear Type", labelFont, valueFont); 
                            break; 
                        case "bird": 
                            addTypeSpecificDetails(infoPanel, rs, "beak_length", "Beak Length", labelFont, valueFont); 
                            break; 
                        case "turtle": 
                            addTypeSpecificDetails(infoPanel, rs, "shell_type", "Shell Type", labelFont, valueFont); 
                            break; 
                        case "fish": 
                            addTypeSpecificDetails(infoPanel, rs, "fins_type", "Fins Type", labelFont, valueFont); 
                            break; 
                        case "snake": 
                            addTypeSpecificDetails(infoPanel, rs, "venomous", "Venomous", labelFont, valueFont); 
                            break; 
                    } 
     
                    // Vaccination status 
                    addTypeSpecificDetails(infoPanel, rs, "vaccinated", "Vaccinated", labelFont, valueFont); 

                    String previousOwner = rs.getString("previous_owner");
                    if (previousOwner != null && !previousOwner.isEmpty()) {
                        addDetailRow(infoPanel, "Previous Owner:", previousOwner, labelFont, valueFont);
                    }
     
                    // Description 
                    JPanel descriptionPanel = new JPanel(); 
                    descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS)); 
                     
                    JLabel descLabel = new JLabel("Description:"); 
                    descLabel.setFont(labelFont); 
                    descriptionPanel.add(descLabel); 
                    descriptionPanel.add(Box.createVerticalStrut(5)); 
                     
                    String description = rs.getString("description"); 
                    JTextArea descText = new JTextArea(description != null ? description : "No description available."); 
                    descText.setWrapStyleWord(true); 
                    descText.setLineWrap(true); 
                    descText.setOpaque(false); 
                    descText.setEditable(false); 
                    descText.setFont(valueFont); 
                    descText.setPreferredSize(new Dimension(300, 100)); 
                    descriptionPanel.add(descText); 
                     
                    infoPanel.add(descriptionPanel); 
                } 
            } 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            showMessage("Error loading pet details: " + ex.getMessage()); 
        } 
    } 
     
    private void addTypeSpecificDetails(JPanel panel, ResultSet rs, String columnName, String label,  
                                      Font labelFont, Font valueFont) throws SQLException { 
        String value = rs.getString(columnName); 
        if (value != null && !value.isEmpty()) { 
            addDetailRow(panel, label + ":", value, labelFont, valueFont); 
        } 
    }

    protected void addDetailRow(JPanel panel, String label, String value, Font labelFont, Font valueFont) { 
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); 
         
        JLabel labelComp = new JLabel(label); 
        labelComp.setFont(labelFont); 
        rowPanel.add(labelComp); 
         
        JLabel valueComp = new JLabel(value); 
        valueComp.setFont(valueFont); 
        rowPanel.add(valueComp); 
         
        panel.add(rowPanel); 
        panel.add(Box.createVerticalStrut(5));  // Add spacing between rows 
    }
}