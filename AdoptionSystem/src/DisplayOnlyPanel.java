import javax.swing.*;
import utils.DatabaseConnection;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ComponentAdapter; 
import java.awt.event.ComponentEvent; 


public class DisplayOnlyPanel extends AbstractPanel {
    private JPanel wrapperPanel;
    private JPanel mainContentPanel;
    private JScrollPane scrollPane;
    private JButton backButton;
    private Set<String> displayedPetIds;

    public DisplayOnlyPanel(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout);
    }

    @Override
    protected void initializeComponents() {
        displayedPetIds = new HashSet<>();

        wrapperPanel = new JPanel();
        mainContentPanel = new JPanel();
        scrollPane = new JScrollPane();

        // Initialize back button 
        backButton = new JButton("Back"); 
        backButton.setPreferredSize(new Dimension(100, 40)); 
        backButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        backButton.setBackground(new Color(65, 190, 65)); 
        backButton.setForeground(Color.WHITE); 
        backButton.setFocusPainted(false);
    }

    @Override  
    protected void setupLayout() {  
        setLayout(new BorderLayout());  
         
        // Set background color to #F8ECC5 
        setBackground(Color.decode("#F8ECC5")); 
        mainContentPanel.setBackground(Color.decode("#F8ECC5")); 
        wrapperPanel.setBackground(Color.decode("#F8ECC5")); 
      
        // Header Panel 
        JPanel headerPanel = new JPanel(new BorderLayout()); 
        headerPanel.setBackground(Color.decode("#F8ECC5")); 
         
        // Header Label 
        JLabel headerLabel = new JLabel("Pets For Adoption", SwingConstants.CENTER);  
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 24)); 
        headerPanel.add(headerLabel, BorderLayout.CENTER); 
         
        // Add back button to header panel 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        buttonPanel.setBackground(Color.decode("#F8ECC5")); 
        buttonPanel.add(backButton); 
        headerPanel.add(buttonPanel, BorderLayout.WEST); 
         
        add(headerPanel, BorderLayout.NORTH);  
 
        // Main content setup (existing code) 
        mainContentPanel.setLayout(new GridLayout(0, 3, 10, 10)); 
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
  
        scrollPane = new JScrollPane(mainContentPanel);  
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
        scrollPane.getViewport().setBackground(Color.decode("#F8ECC5")); 
        add(scrollPane, BorderLayout.CENTER);  
    }

    private void createAnimalPanel(String imagePath, String id, String name, String age, String gender, String breed) {  
        // Create main panel for each pet 
        JPanel animalPanel = new JPanel();  
        animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS)); 
         
        // Style the pet container 
        animalPanel.setBorder(BorderFactory.createCompoundBorder( 
            BorderFactory.createLineBorder(new Color(139, 69, 19), 2), // Brown border 
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Inner padding 
        )); 
        animalPanel.setBackground(new Color(255, 248, 220)); // Cornsilk color 
         
        // Add shadow effect 
        animalPanel.setBorder(BorderFactory.createCompoundBorder( 
            BorderFactory.createLineBorder(new Color(139, 69, 19), 2), 
            BorderFactory.createCompoundBorder( 
                BorderFactory.createEmptyBorder(5, 5, 5, 5), 
                BorderFactory.createRaisedBevelBorder() 
            ) 
        )); 
     
        // Image  
        ImageIcon imageIcon = new ImageIcon(imagePath);  
        Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);  
        JLabel imageLabel = new JLabel(new ImageIcon(image));  
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
        animalPanel.add(imageLabel);  
     
        // Info Panel  
        JPanel infoPanel = new JPanel();  
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));  
        infoPanel.setBackground(new Color(255, 248, 220)); // Match the animal panel background 
         
        // Create and style labels with new colors 
        Font labelFont = new Font("Arial", Font.BOLD, 14); 
        Color textColor = new Color(139, 69, 19); // Brown text color 
         
        JLabel idLabel = createStyledLabel("ID: " + id, labelFont, textColor); 
        JLabel nameLabel = createStyledLabel("Name: " + name, labelFont, textColor); 
        JLabel ageLabel = createStyledLabel("Age: " + age, labelFont, textColor); 
        JLabel genderLabel = createStyledLabel("Gender: " + gender, labelFont, textColor); 
        JLabel breedLabel = createStyledLabel("Breed: " + breed, labelFont, textColor); 
     
        // Add padding between labels  
        infoPanel.add(Box.createVerticalStrut(5));  
        infoPanel.add(idLabel);  
        infoPanel.add(Box.createVerticalStrut(5));  
        infoPanel.add(nameLabel);  
        infoPanel.add(Box.createVerticalStrut(5));  
        infoPanel.add(ageLabel);  
        infoPanel.add(Box.createVerticalStrut(5));  
        infoPanel.add(genderLabel);  
        infoPanel.add(Box.createVerticalStrut(5));  
        infoPanel.add(breedLabel);  
        infoPanel.add(Box.createVerticalStrut(10)); 
     
        animalPanel.add(infoPanel);  
         
        // Add hover effect 
        animalPanel.addMouseListener(new java.awt.event.MouseAdapter() { 
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                animalPanel.setBackground(new Color(255, 235, 205)); // Blanched Almond color for hover 
                infoPanel.setBackground(new Color(255, 235, 205)); 
            } 
     
            public void mouseExited(java.awt.event.MouseEvent evt) { 
                animalPanel.setBackground(new Color(255, 248, 220)); // Return to original color 
                infoPanel.setBackground(new Color(255, 248, 220)); 
            } 
        }); 
     
        mainContentPanel.add(animalPanel);  
    } 
     
    // Helper method to create styled labels 
    private JLabel createStyledLabel(String text, Font font, Color color) { 
        JLabel label = new JLabel(text); 
        label.setFont(font); 
        label.setForeground(color); 
        label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        return label; 
    }

    @Override
    protected void addComponents() {
        displayedPetIds.clear();
        addHardcodedPets();
        loadPetsFromDatabase();
    }

    private void addHardcodedPets() {

        addHardcodedPet("2023-0013", "C:\\Users\\mcdeu\\Documents\\oop\\bird-angela.png", "Angela", "2", "Female", "Umbrella Cockatoo"); 
        addHardcodedPet("2023-0014", "C:\\Users\\mcdeu\\Documents\\oop\\bird-charlie.png", "Charlie", "1", "Male", "Cockatiel"); 
        addHardcodedPet("2023-0015", "C:\\Users\\mcdeu\\Documents\\oop\\bird-cranberry.jpg", "Cranberry", "4", "Female", "Scarlet Macaw"); 
        addHardcodedPet("2023-0016", "C:\\Users\\mcdeu\\Documents\\oop\\bird-lorax.png", "Lorax", "1", "Male", "Sun Conure"); 
 
        // Cat pets 
        addHardcodedPet("2023-0007", "C:\\Users\\mcdeu\\Documents\\oop\\cat-chico.jpeg", "Chico", "7", "Male", "Domestic Shorthair"); 
        addHardcodedPet("2023-0008", "C:\\Users\\mcdeu\\Documents\\oop\\cat-dunkin.jpeg", "Dunkin", "6", "Male", "Domestic Shorthair"); 
        addHardcodedPet("2023-0009", "C:\\Users\\mcdeu\\Documents\\oop\\cat-kikay.jpeg", "Kikay", "15", "Female", "Domestic Shorthair"); 
        addHardcodedPet("2023-0010", "C:\\Users\\mcdeu\\Documents\\oop\\cat-mara.jpeg", "Mara", "4", "Female", "Domestic Shorthair"); 
        addHardcodedPet("2023-0011", "C:\\Users\\mcdeu\\Documents\\oop\\cat-ramon.jpeg", "Ramon", "5", "Male", "Domestic Shorthair"); 
        addHardcodedPet("2023-0012", "C:\\Users\\mcdeu\\Documents\\oop\\cat-nana.jpeg", "Nana", "8", "Female", "Mixed Breed"); 
 
        // Dog pets 
        addHardcodedPet("2023-0001", "C:\\Users\\mcdeu\\Documents\\oop\\dog-koko.jpg", "Koko", "7", "Male", "Aspin"); 
        addHardcodedPet("2023-0002", "C:\\Users\\mcdeu\\Documents\\oop\\dog-rombu.jpg", "Rombu", "9", "Male", "Aspin"); 
        addHardcodedPet("2023-0003", "C:\\Users\\mcdeu\\Documents\\oop\\dog-bambi.jpg", "Bambi", "4", "Female", "Aspin"); 
        addHardcodedPet("2023-0004", "C:\\Users\\mcdeu\\Documents\\oop\\dog-aurora.jpeg", "Aurora", "2", "Female", "American Pit Bull Terrier"); 
        addHardcodedPet("2023-0005", "C:\\Users\\mcdeu\\Documents\\oop\\dog-lotso.jpeg", "Lotso", "5", "Male", "American Pit Bull Terrier"); 
        addHardcodedPet("2023-0006", "C:\\Users\\mcdeu\\Documents\\oop\\dog-juli.jpeg", "Juli", "5", "Female", "American Pit Bull Terrier");

        addHardcodedPet("2023-0017", "C:\\Users\\mcdeu\\Documents\\oop\\ferret-robert.jpg", "Robert", "1", "Male", "Sable Ferret"); 
        addHardcodedPet("2023-0018", "C:\\Users\\mcdeu\\Documents\\oop\\ferret-bridget.jpg", "Bridget", "1", "Female", "Sable Ferret"); 
        addHardcodedPet("2023-0019", "C:\\Users\\mcdeu\\Documents\\oop\\ferret-biscuit.jpg", "Biscuit", "1", "male", "Light Sable Ferret");

        addHardcodedPet("2023-0020", "C:\\Users\\mcdeu\\Documents\\oop\\fish-brenda.jpg", "Brenda", "3", "Female", "Oranda Goldfish"); 
        addHardcodedPet("2023-0021", "C:\\Users\\mcdeu\\Documents\\oop\\fish-haru.jpg", "Haru", "1", "Male", "'Japanese Blue Swordtail Guppy"); 
        addHardcodedPet("2023-0022", "C:\\Users\\mcdeu\\Documents\\oop\\fish-simone.jpg", "Simone", "2", "Male", "Siamese Fighting Fish"); 
        addHardcodedPet("2023-0023", "C:\\Users\\mcdeu\\Documents\\oop\\fish-tori-tula.jpg", "Tori & Tula", "1.5", ";ale", "Ocellaris Clownfish"); 
        addHardcodedPet("2023-0024", "C:\\Users\\mcdeu\\Documents\\oop\\fish-kali.jpg", "Kali", "2.5", "Male", "Kuhli Loach"); 

        addHardcodedPet("2023-0025", "C:\\Users\\mcdeu\\Documents\\oop\\hamster-autumn.jpg", "Autumn", "1", "Female", "Campbells"); 
        addHardcodedPet("2023-0026", "C:\\Users\\mcdeu\\Documents\\oop\\hamster-chacha.jpg", "Chacha", "1.5", "Female", "Roborovski"); 
        addHardcodedPet("2023-0027", "C:\\Users\\mcdeu\\Documents\\oop\\hamster-jack-jill.jpg", "Jack & Jill", "1.2", "Male & Female", "Campbells"); 
        addHardcodedPet("2023-0028", "C:\\Users\\mcdeu\\Documents\\oop\\hamster-oshinne.jpg", "Oshinne", "1.3", "Female", "Chinese Hamster"); 
        addHardcodedPet("2023-0029", "C:\\Users\\mcdeu\\Documents\\oop\\hamster-sausage.jpg", "Sausage", "1.8", "Male", "Syrian Hamster"); 
        addHardcodedPet("2023-0030", "C:\\Users\\mcdeu\\Documents\\oop\\hamster-timmy.jpg", "Timmy", "1", "Male", "Winterwhites");

        addHardcodedPet("2023-0031", "C:\\Users\\mcdeu\\Documents\\oop\\lizard-lilo.png", "Lilo", "2", "Male", "Leopard Gecko");

        addHardcodedPet("2023-0032", "C:\\Users\\mcdeu\\Documents\\oop\\rabbit-dewberry.png", "Dewberry", "2", "Female", "Rhinelander"); 
        addHardcodedPet("2023-0033", "C:\\Users\\mcdeu\\Documents\\oop\\rabbit-jamal.png", "Jamal", "1.5", "Male", "Lionhead"); 
        addHardcodedPet("2023-0034", "C:\\Users\\mcdeu\\Documents\\oop\\rabbit-junia.png", "Junia", "2", "Female", "Harlequin"); 
        addHardcodedPet("2023-0035", "C:\\Users\\mcdeu\\Documents\\oop\\rabbit-justin.png", "Justin", "1", "Male", "New Zealand"); 
        addHardcodedPet("2023-0036", "C:\\Users\\mcdeu\\Documents\\oop\\rabbit-mordecai.png", "Mordecai", "1.3", "Male", "Lionhead"); 
        addHardcodedPet("2023-0037", "C:\\Users\\mcdeu\\Documents\\oop\\rabbit-noli.png", "Noli", "2.2", "Female", "Rhinelander");

        addHardcodedPet("2023-0038", "C:\\Users\\mcdeu\\Documents\\oop\\snake-bella.png", "Bella", "3", "Female", "Corn Snake"); 
        addHardcodedPet("2023-0039", "C:\\Users\\mcdeu\\Documents\\oop\\snake-tan.png", "Tan", "4", "Male", "Ball Python");
        addHardcodedPet("2023-0040", "C:\\Users\\mcdeu\\Documents\\oop\\turtle-taco.png", "Taco", "20", "Male", "Green Sea Turtle"); 
        addHardcodedPet("2023-0041", "C:\\Users\\mcdeu\\Documents\\oop\\turtle-taquito.png", "Taquito", "18", "Male", "Green Sea Turtle");
    }

    private void addHardcodedPet(String id, String imagePath, String name, String age, String gender, String breed) { 
        System.out.println("Adding hardcoded pet: " + name + " (ID: " + id + ")"); 
        displayedPetIds.add(id); 
        createAnimalPanel(imagePath, id, name, age, gender, breed); 
    } 
     
    private void loadPetsFromDatabase() { 
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT * FROM pets WHERE status = 'available' ORDER BY type"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                ResultSet rs = stmt.executeQuery(); 
                 
                while (rs.next()) { 
                    String petId = rs.getString("pet_id"); 
                    String name = rs.getString("name"); 
                    System.out.println("Found in database: " + name + " (ID: " + petId + ")"); 
                    System.out.println("Already displayed? " + displayedPetIds.contains(petId)); 
                     
                    if (!displayedPetIds.contains(petId)) { 
                        System.out.println("Adding from database: " + name + " (ID: " + petId + ")"); 
                        displayedPetIds.add(petId); 
                        createAnimalPanel( 
                            rs.getString("image_path"), 
                            petId, 
                            name, 
                            rs.getString("age"), 
                            rs.getString("sex"), 
                            rs.getString("breed") 
                        ); 
                    } 
                } 
            } 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            showMessage("Error loading pets: " + ex.getMessage()); 
        } 
    } 
 
    // Add method to refresh the panel 
    public void refreshPanel() { 
        mainContentPanel.removeAll();
        displayedPetIds.clear();
        addComponents();
    }

    @Override 
    protected void setupListeners() { 
        // Add a component listener to refresh when panel becomes visible 
        addComponentListener(new ComponentAdapter() { 
            @Override 
            public void componentShown(ComponentEvent e) { 
                loadPetsFromDatabase(); 
            } 
        }); 
        backButton.addActionListener(e -> switchPanel("startPanel"));
    }
}