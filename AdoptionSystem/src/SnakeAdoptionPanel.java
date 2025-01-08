import javax.swing.*;
import utils.DatabaseConnection;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SnakeAdoptionPanel extends AbstractAnimalAdoptionPanel {
    private JButton bellaButton;
    private JButton tanButton;
    private JButton backButton;
    private Set<String> addedPetIds = new HashSet<>();

    public SnakeAdoptionPanel(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout, "Snake");
        setLayout(new BorderLayout()); // Ensure this panel uses BorderLayout
        initializeComponents();
    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();

        // Create header panel with BorderLayout  
        JPanel headerPanel = new JPanel(new BorderLayout());   
    
        // Create back button panel (left side)  
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
    
        backButton = new JButton("BACK");  
        backButton.setFont(new Font("Arial", Font.BOLD, 14));  
        backButton.setPreferredSize(new Dimension(100, 35));  
        backButton.addActionListener(e -> switchPanel("availablePetsPanel"));  
        backButtonPanel.add(backButton);  
    
        // Create center panel for header label  
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));  
    
        // Initialize header label with center alignment  
        headerLabel = new JLabel("Available Snakes for Adoption");  
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40));  
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);  
        centerPanel.add(headerLabel);  
    
        // Add components to header panel  
        headerPanel.add(backButtonPanel, BorderLayout.WEST);  
        headerPanel.add(centerPanel, BorderLayout.CENTER);  
    
        // Initialize other components  
        wrapperPanel = new JPanel(new BorderLayout()); // Set layout here 
        firstRowPanel = new JPanel(new GridLayout(0, 3, 20, 20)); 
        scrollPane = new JScrollPane(firstRowPanel); // Add firstRowPanel directly to scrollPane 
    
        // Add the components 
        add(headerPanel, BorderLayout.NORTH); 
        add(wrapperPanel, BorderLayout.CENTER); 
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);

        bellaButton = createButtonWithListener("See More", e -> BellaDetails());
        tanButton = createButtonWithListener("See More", e -> TanDetails());
        
    }

    private JButton createButtonWithListener(String text, ActionListener listener) { 
        JButton button = new JButton(text); 
        button.setPreferredSize(new Dimension(150, 40)); 
        button.setFont(new Font("Arial", Font.BOLD, 16)); 
        button.addActionListener(listener); 
        return button; 
    }

    @Override
    protected void setupLayout() {
        super.setupLayout();
        // Add any additional layout setup specific to CatAdoptionPanel
    }

    @Override
    protected void addComponents() {
        super.addComponents();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {

            refreshPetDisplay();
        }
    }

    private void refreshPetDisplay() {
        firstRowPanel.removeAll();
        addedPetIds.clear();
        
        addHardcodedPets();
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT COUNT(*) FROM pets WHERE type = 'Snake' AND status = 'available'"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                try (ResultSet rs = stmt.executeQuery()) { 
                    if (rs.next() && rs.getInt(1) > 0) { 
                        loadDatabasePets(); 
                    } 
                } 
            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
         
        firstRowPanel.revalidate(); 
        firstRowPanel.repaint(); 
    }

    @Override
    protected void setupListeners() {
        bellaButton.addActionListener(e -> BellaDetails());
        tanButton.addActionListener(e -> TanDetails());
    }
    
    protected void setupDatabasePetButton(JButton button, String petId, String name, String age, String sex, String breed, String imagePath) { 
        button.addActionListener(e -> { 
            System.out.println("Database pet button clicked: " + name); // Debug print 
            showDatabasePetDetails(petId, name, age, sex, breed, imagePath); 
        }); 
    }

    private void loadDatabasePets() { 
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT * FROM pets WHERE type = ? AND status = 'available'"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                stmt.setString(1, "Snake"); 
                try (ResultSet rs = stmt.executeQuery()) { 
                    while (rs.next()) { 
                        String petId = rs.getString("pet_id"); 

                        if (addedPetIds.contains(petId)) { 
                            System.out.println("Skipping duplicate pet: " + petId); // Debug print 
                            continue; 
                        }

                        addedPetIds.add(petId);
                        String name = rs.getString("name"); 
                        String age = rs.getString("age"); 
                        String sex = rs.getString("sex"); 
                        String breed = rs.getString("breed"); 
                        String imagePath = rs.getString("image_path"); 
 
                    // Create and setup the button 
                    JButton seeMoreButton = new JButton("See More"); 
                    seeMoreButton.setPreferredSize(new Dimension(150, 40)); 
                    seeMoreButton.setFont(new Font("Arial", Font.BOLD, 16)); 
                     
                    // Add the action listener directly here 
                    final String finalName = name; // Need final variable for lambda 
                    seeMoreButton.addActionListener(e -> { 
                        System.out.println("Database pet button clicked: " + finalName); // Debug print 
                        showDatabasePetDetails(petId, finalName, age, sex, breed, imagePath); 
                    }); 
 
                    // Create the panel for this pet 
                    createAnimalPanel(imagePath, petId, name, age, sex, breed, seeMoreButton); 
                } 
            } 
        } 
    } catch (SQLException e) { 
        e.printStackTrace(); 
        System.out.println("Error loading database pets: " + e.getMessage()); 
    } 
}

    private void showDatabasePetDetails(String petId, String name, String age, String sex, String breed, String imagePath) { 
        // Create a new panel for details 
        JPanel detailsPanel = new JPanel(); 
        headerLabel = new JLabel("More Details About " + name); 
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40)); 
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); 
        detailsPanel.setLayout(new BorderLayout(20, 20)); 
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
     
        JPanel contentPanel = new JPanel(new GridBagLayout()); 
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40)); 
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(5, 30, 5, 5); 
     
        // Add image 
        ImageIcon icon = new ImageIcon(imagePath); 
        Image scaledImage = icon.getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH); 
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage)); 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridheight = 1; 
        gbc.anchor = GridBagConstraints.WEST; 
        contentPanel.add(imageLabel, gbc); 
     
        JPanel infoPanel = new JPanel();   
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));   
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 20));   
           
        // Use createPetDetailsPanel to fetch and display all details from database 
        createPetDetailsPanel(petId, name, detailsPanel, infoPanel); 
           
        // Add info panel to content panel   
        gbc.gridx = 1;   
        gbc.gridy = 0;   
        gbc.weightx = 1.0;   
        gbc.weighty = 1.0;   
        gbc.fill = GridBagConstraints.BOTH;   
        contentPanel.add(infoPanel, gbc); 
     
        // Button panel 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); 
         
        // Back button 
        JButton backButton = new JButton("Back to Snakes"); 
        backButton.setPreferredSize(new Dimension(150, 40)); 
        backButton.addActionListener(e -> switchPanel("snakeAdoptionPanel")); 
         
        // Adopt Me button 
        JButton adoptButton = new JButton("Adopt Me!"); 
        adoptButton.setPreferredSize(new Dimension(120, 40)); 
        adoptButton.setBackground(new Color(65, 190, 65)); 
        adoptButton.setForeground(Color.WHITE); 
        adoptButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        adoptButton.addActionListener(e -> { 
            String currentPanel = name.toLowerCase() + "Details"; 
            AdoptionFormPanel adoptionForm = new AdoptionFormPanel(mainPanel, cardLayout, currentPanel); 
            mainPanel.add(adoptionForm, "adoptionFormPanel"); 
            cardLayout.show(mainPanel, "adoptionFormPanel"); 
        }); 
     
        buttonPanel.add(backButton); 
        buttonPanel.add(adoptButton); 
     
        // Add all components to the details panel 
        detailsPanel.add(headerLabel, BorderLayout.NORTH); 
        detailsPanel.add(contentPanel, BorderLayout.CENTER); 
        detailsPanel.add(buttonPanel, BorderLayout.SOUTH); 
     
        // Add the details panel to the main panel and show it 
        String detailsPanelName = name.toLowerCase() + "Details"; 
        mainPanel.add(detailsPanel, detailsPanelName); 
        cardLayout.show(mainPanel, detailsPanelName); 
    }

    private void addHardcodedPets() {
         
    }

    
    

    private void BellaDetails() {
        // Create a new panel for details
        JPanel detailsPanel = new JPanel();
        headerLabel = new JLabel("More Details About Bella");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        detailsPanel.setLayout(new BorderLayout(20, 20));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 30, 5, 5);

        // Add image (make it slightly smaller to give more room for description)
        ImageIcon icon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\object\\snake-bella.png");
        Image scaledImage = icon.getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH);  // Reduced size
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;  // Changed to 1
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(imageLabel, gbc);

        JPanel infoPanel = new JPanel(); 
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); 
    infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 20)); 
 
    // Add all details from database 
    createPetDetailsPanel("2023-0038", "Bella", detailsPanel, infoPanel); 
 
    // Add info panel to content panel 
    gbc.gridx = 1; 
    gbc.gridy = 0; 
    gbc.weightx = 1.0; 
    gbc.weighty = 1.0; 
    gbc.fill = GridBagConstraints.BOTH; 
    contentPanel.add(infoPanel, gbc);
        
        // Modify the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));  // Added spacing between buttons
        
        // Back button
        JButton backButton = new JButton("Back to Snakes");
        backButton.setPreferredSize(new Dimension(150, 40));  // Set size for consistency
        backButton.addActionListener(e -> switchPanel("snakeAdoptionPanel"));
        
        // Adopt Me button
        JButton adoptButton = new JButton("Adopt Me!");
        adoptButton.setPreferredSize(new Dimension(120, 40));
        adoptButton.setBackground(new Color(65, 190, 65));  // Green background
        adoptButton.setForeground(Color.WHITE);  // White text
        adoptButton.setFont(new Font("Arial", Font.BOLD, 16));
        adoptButton.addActionListener(e -> {
            String currentPanel = "bellaDetails";
            AdoptionFormPanel adoptionForm = new AdoptionFormPanel(mainPanel, cardLayout, currentPanel);
            mainPanel.add(adoptionForm, "adoptionFormPanel");
            cardLayout.show(mainPanel, "adoptionFormPanel");
        });

            // Add buttons to panel
            buttonPanel.add(backButton);
            buttonPanel.add(adoptButton);

            // Add all components to the details panel
            detailsPanel.add(headerLabel, BorderLayout.NORTH);
            detailsPanel.add(contentPanel, BorderLayout.CENTER);
            detailsPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Add the details panel to the main panel and show it
            mainPanel.add(detailsPanel, "BellaDetails");
            cardLayout.show(mainPanel, "BellaDetails");
    }

    private void TanDetails() {
        // Create a new panel for details
        JPanel detailsPanel = new JPanel();
        headerLabel = new JLabel("More Details About Tan");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        detailsPanel.setLayout(new BorderLayout(20, 20));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 30, 5, 5);

        // Add image (make it slightly smaller to give more room for description)
        ImageIcon icon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\object\\snake-tan.png");
        Image scaledImage = icon.getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH);  // Reduced size
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;  // Changed to 1
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(imageLabel, gbc);

        JPanel infoPanel = new JPanel(); 
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); 
    infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 20)); 
 
    // Add all details from database 
    createPetDetailsPanel("2023-0039", "Tan", detailsPanel, infoPanel); 
 
    // Add info panel to content panel 
    gbc.gridx = 1; 
    gbc.gridy = 0; 
    gbc.weightx = 1.0; 
    gbc.weighty = 1.0; 
    gbc.fill = GridBagConstraints.BOTH; 
    contentPanel.add(infoPanel, gbc);
        
        // Modify the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));  // Added spacing between buttons
        
        // Back button
        JButton backButton = new JButton("Back to Snakes");
        backButton.setPreferredSize(new Dimension(150, 40));  // Set size for consistency
        backButton.addActionListener(e -> switchPanel("snakeAdoptionPanel"));
        
        // Adopt Me button
        JButton adoptButton = new JButton("Adopt Me!");
        adoptButton.setPreferredSize(new Dimension(120, 40));
        adoptButton.setBackground(new Color(65, 190, 65));  // Green background
        adoptButton.setForeground(Color.WHITE);  // White text
        adoptButton.setFont(new Font("Arial", Font.BOLD, 16));
        adoptButton.addActionListener(e -> {
            String currentPanel = "tanDetails";
            AdoptionFormPanel adoptionForm = new AdoptionFormPanel(mainPanel, cardLayout, currentPanel);
            mainPanel.add(adoptionForm, "adoptionFormPanel");
            cardLayout.show(mainPanel, "adoptionFormPanel");
        });

            // Add buttons to panel
            buttonPanel.add(backButton);
            buttonPanel.add(adoptButton);

            // Add all components to the details panel
            detailsPanel.add(headerLabel, BorderLayout.NORTH);
            detailsPanel.add(contentPanel, BorderLayout.CENTER);
            detailsPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Add the details panel to the main panel and show it
            mainPanel.add(detailsPanel, "TanDetails");
            cardLayout.show(mainPanel, "TanDetails");
    }

    public void createPetDetails(String petId, String name, String imagePath, String age, String sex, String breed) {
        // Create a new panel for details
        JPanel detailsPanel = new JPanel();
        JLabel detailsHeaderLabel = new JLabel("More Details About " + name);
        detailsHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        detailsHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsHeaderLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        detailsPanel.setLayout(new BorderLayout(20, 20));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 30, 5, 5);
    
        // Add image
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(imageLabel, gbc);
    
        // Create info panel   
    JPanel infoPanel = new JPanel();   
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));   
    infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 20));   
       
    // Use createPetDetailsPanel to fetch and display all details from database 
    createPetDetailsPanel(petId, name, detailsPanel, infoPanel); 
       
    // Add info panel to content panel   
    gbc.gridx = 1;   
    gbc.gridy = 0;   
    gbc.weightx = 1.0;   
    gbc.weighty = 1.0;   
    gbc.fill = GridBagConstraints.BOTH;   
    contentPanel.add(infoPanel, gbc);
    
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton backButton = new JButton("Back to Snakes");
        backButton.setPreferredSize(new Dimension(120, 40));
        backButton.addActionListener(e -> switchPanel("snakeAdoptionPanel"));
        
        JButton adoptButton = new JButton("Adopt Me!");
        adoptButton.setPreferredSize(new Dimension(120, 40));
        adoptButton.setBackground(new Color(65, 190, 65));
        adoptButton.setForeground(Color.WHITE);
        adoptButton.setFont(new Font("Arial", Font.BOLD, 16));
        adoptButton.addActionListener(e -> {
            String currentPanel = name.toLowerCase() + "Details";
            AdoptionFormPanel adoptionForm = new AdoptionFormPanel(mainPanel, cardLayout, currentPanel);
            mainPanel.add(adoptionForm, "adoptionFormPanel");
            cardLayout.show(mainPanel, "adoptionFormPanel");
        });
    
        buttonPanel.add(backButton);
        buttonPanel.add(adoptButton);
    
        // Add all components to the details panel
        detailsPanel.add(detailsHeaderLabel, BorderLayout.NORTH);
        detailsPanel.add(contentPanel, BorderLayout.CENTER);
        detailsPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        // Add the details panel to the main panel
        String detailsPanelName = name.toLowerCase() + "Details";
        mainPanel.add(detailsPanel, detailsPanelName);
    }
}