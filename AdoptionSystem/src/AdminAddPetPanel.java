import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import utils.DatabaseConnection;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAddPetPanel extends AbstractPanel {
    private JTextField petNameField;
    private JTextField petBreedField;
    private JTextField petAgeField;
    private JTextField petWeightField;
    private JTextField petSexField;
    private JTextField petConditionField;
    private JTextField petDescriptionField;
    private JTextField petPreviousOwnerField;
    private JTextField petVaccinatedField;
    private JTextField petEarTypeField;
    private JTextField petDroolingField;
    private JTextField beakLengthField;
    private JTextField finsTypeField;
    private JTextField venomousField;
    private JTextField shellTypeField;
    private JButton submitButton;
    private JButton backButton;
    private JComboBox<String> petTypeComboBox;
    private JPanel dynamicFieldsPanel;
    private JButton uploadImageButton;
    private JLabel imagePreviewLabel;
    private String petId; 
    private String destinationPath;
    private File selectedImageFile;
    private static final int PREVIEW_WIDTH = 200;
    private static final int PREVIEW_HEIGHT = 200;

    public AdminAddPetPanel(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout);

        setBackground(new Color(248, 236, 197));
        setOpaque(true);

        setPreferredSize(new Dimension(mainPanel.getWidth(), mainPanel.getHeight()));

        initializeComponents();
        setupLayout();
        setupListeners();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(248, 236, 197));
        g.fillRect(0, 0, getWidth(), getHeight());
    }


    @Override
    public void initializeComponents() {
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);
        Font labelFont = new Font("Arial", Font.BOLD, 20);

        // Initialize text fields with new font
        petNameField = createStyledTextField(textFieldFont);
        petBreedField = createStyledTextField(textFieldFont);
        petAgeField = createStyledTextField(textFieldFont);
        petWeightField = createStyledTextField(textFieldFont);
        petSexField = createStyledTextField(textFieldFont);
        petConditionField = createStyledTextField(textFieldFont);
        petDescriptionField = createStyledTextField(textFieldFont);
        petPreviousOwnerField = createStyledTextField(textFieldFont);
        petVaccinatedField = createStyledTextField(textFieldFont);
        petEarTypeField = createStyledTextField(textFieldFont);
        petDroolingField = createStyledTextField(textFieldFont);
        beakLengthField = createStyledTextField(textFieldFont);
        finsTypeField = createStyledTextField(textFieldFont);
        venomousField = createStyledTextField(textFieldFont);
        shellTypeField = createStyledTextField(textFieldFont);

        // Initialize buttons
        submitButton = new JButton("SUBMIT");
        submitButton.setPreferredSize(new Dimension(200, 60));
        submitButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        submitButton.setFocusPainted(false); 

        backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(200, 60));
        backButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        backButton.setFocusPainted(false); 

        // Initialize combo box with font
        String[] petTypes = {"Dog", "Cat", "Bird", "Fish", "Rabbit", "Snake", "Ferret", "Hamster", "Turtle", "Lizard"};
        petTypeComboBox = new JComboBox<>(petTypes);
        petTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 20));

        uploadImageButton = new JButton("Upload Pet Image");
        uploadImageButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        uploadImageButton.setPreferredSize(new Dimension(200, 40));
        uploadImageButton.setFocusPainted(false); 

        imagePreviewLabel = new JLabel();
        imagePreviewLabel.setPreferredSize(new Dimension(PREVIEW_WIDTH, PREVIEW_HEIGHT));
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imagePreviewLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePreviewLabel.setText("No image selected");

        // Initialize dynamic fields panel
        dynamicFieldsPanel = new JPanel(new GridBagLayout());
    }

    private JTextField createStyledTextField(Font font) {
        JTextField field = new JTextField(20);
        field.setFont(font);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        return field;
    }

    @Override 
    public void setupLayout() { 
        // Set the main panel's background color 
    setBackground(new Color(248, 236, 197)); // #F8ECC5 
 
    // Create a main panel that will go inside a scroll pane 
    JPanel mainContentPanel = new JPanel(); 
    mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS)); 
    mainContentPanel.setBackground(new Color(248, 236, 197)); // Add this 
     
    // Add padding around the panel 
    mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
 
    // Header Panel 
    JPanel headerPanel = new JPanel(); 
    headerPanel.setBackground(new Color(248, 236, 197)); // Add this 
    JLabel headerLabel = new JLabel("ADD PET"); 
    headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40)); 
    headerPanel.add(headerLabel); 
    mainContentPanel.add(headerPanel); 
    mainContentPanel.add(Box.createVerticalStrut(20)); 
 
    // Create form panel 
    JPanel formPanel = new JPanel(new GridBagLayout()); 
    formPanel.setBackground(new Color(248, 236, 197)); // Add this 
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.weightx = 1.0; 
        gbc.gridwidth = 1; 
        gbc.gridy = 0; 
 
        Font labelFont = new Font("Arial", Font.BOLD, 20); 
 
        // Pet Type 
        addFormField(formPanel, "Pet Type:", petTypeComboBox, gbc, labelFont); 
        petTypeComboBox.addActionListener(e -> updateDynamicFields()); 
 
        // Add all fields with labels 
        addFormField(formPanel, "Pet Name:", petNameField, gbc, labelFont); 
        addFormField(formPanel, "Pet Breed:", petBreedField, gbc, labelFont); 
        addFormField(formPanel, "Pet Age:", petAgeField, gbc, labelFont); 
        addFormField(formPanel, "Pet Weight:", petWeightField, gbc, labelFont); 
        addFormField(formPanel, "Pet Sex:", petSexField, gbc, labelFont); 
        addFormField(formPanel, "Pet Condition:", petConditionField, gbc, labelFont); 
        addFormField(formPanel, "Pet Description:", petDescriptionField, gbc, labelFont); 
        addFormField(formPanel, "Previous Owner:", petPreviousOwnerField, gbc, labelFont); 
        addFormField(formPanel, "Vaccinated:", petVaccinatedField, gbc, labelFont); 
 
        // Add dynamic fields panel 
        gbc.gridx = 0; 
        gbc.gridy++; 
        gbc.gridwidth = 2; 
        formPanel.add(dynamicFieldsPanel, gbc); 
 
        // Add form panel to main content panel 
        mainContentPanel.add(formPanel); 
        mainContentPanel.add(Box.createVerticalStrut(20)); 
 
        // Image Panel 
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(248, 236, 197));
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS)); 
         
        JLabel imageHeaderLabel = new JLabel("Pet Image"); 
        imageHeaderLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
        imageHeaderLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
         
        uploadImageButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        imagePreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
         
        imagePanel.add(imageHeaderLabel); 
        imagePanel.add(Box.createVerticalStrut(10)); 
        imagePanel.add(uploadImageButton); 
        imagePanel.add(Box.createVerticalStrut(10)); 
        imagePanel.add(imagePreviewLabel); 
         
        mainContentPanel.add(imagePanel); 
        mainContentPanel.add(Box.createVerticalStrut(20)); 
 
        // Buttons Panel 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(248, 236, 197)); 
        buttonPanel.add(submitButton); 
        buttonPanel.add(backButton); 
        mainContentPanel.add(buttonPanel); 
 
        // Create scroll pane and add main content panel to it 
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.getViewport().setBackground(new Color(248, 236, 197));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        scrollPane.setBorder(null); 
 
        // Set the layout of this panel and add the scroll pane 
        setLayout(new BorderLayout()); 
        add(scrollPane,BorderLayout.CENTER);

        updateDynamicFields();
    }
    
    // New helper method for adding form fields
    private void addFormField(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc, Font labelFont) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }
    
    private void updateDynamicFields() {
        dynamicFieldsPanel.removeAll();
        dynamicFieldsPanel.setBackground(new Color(248, 236, 197));
        dynamicFieldsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = 0;
    
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        String selectedPet = (String) petTypeComboBox.getSelectedItem();
    
        if ("Dog".equals(selectedPet) || "Cat".equals(selectedPet)) {
            addFormField(dynamicFieldsPanel, "Ear Type: (" + selectedPet + "s only)", petEarTypeField, gbc, labelFont);
        }
        
        if ("Dog".equals(selectedPet)) {
            addFormField(dynamicFieldsPanel, "Drooling Tendency: (Dogs only)", petDroolingField, gbc, labelFont);
        }
        
        if ("Bird".equals(selectedPet)) {
            addFormField(dynamicFieldsPanel, "Beak Length: (Birds only)", beakLengthField, gbc, labelFont);
        }
        
        if ("Fish".equals(selectedPet)) {
            addFormField(dynamicFieldsPanel, "Fins Type: (Fish only)", finsTypeField, gbc, labelFont);
        }
        
        if ("Snake".equals(selectedPet)) {
            addFormField(dynamicFieldsPanel, "Venomous: (Snakes only)", venomousField, gbc, labelFont);
        }
        
        if ("Turtle".equals(selectedPet)) {
            addFormField(dynamicFieldsPanel, "Shell Type: (Turtles only)", shellTypeField, gbc, labelFont);
        }
    
        dynamicFieldsPanel.revalidate();
        dynamicFieldsPanel.repaint();
    }

    private void updateImagePreview(File file) {
        try {
            BufferedImage originalImage = ImageIO.read(file);
            Image resizedImage = originalImage.getScaledInstance(PREVIEW_WIDTH, PREVIEW_HEIGHT, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            imagePreviewLabel.setIcon(imageIcon);
            imagePreviewLabel.setText(""); // Clear the "No image selected" text
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Error loading image preview");
        }
    }

    @Override
    protected void setupListeners() {
        uploadImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);
            
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImageFile = fileChooser.getSelectedFile();
                try {
                    // Verify it's a valid image file
                    BufferedImage testImage = ImageIO.read(selectedImageFile);
                    if (testImage == null) {
                        showMessage("Selected file is not a valid image");
                        selectedImageFile = null;
                        return;
                    }
                    updateImagePreview(selectedImageFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    showMessage("Error loading image: " + ex.getMessage());
                    selectedImageFile = null;
                }
            }
        });

        submitButton.addActionListener(e -> {
            // First check if an image is selected
            if (selectedImageFile == null) {
                showMessage("Please select an image");
                return;
            }
        
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.setAutoCommit(false);  // Start transaction
        
                // Generate pet ID
                petId = generatePetId();
                if (petId == null) {
                    showMessage("Error generating pet ID");
                    return;
                }
        
                // Create destination directory if it doesn't exist
                File destDir = new File("pet_images");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
        
                // Copy image file to destination with pet ID in filename
                String fileExtension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf('.'));
                File destFile = new File(destDir, petId + fileExtension);
                try {
                    Files.copy(selectedImageFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    destinationPath = destFile.getAbsolutePath();
                } catch (IOException ex) {
                    showMessage("Error copying image file");
                    return;
                }
        
                String query = "INSERT INTO pets (pet_id, name, type, breed, age, sex, weight, " +
                            "condition_status, description, previous_owner, vaccinated, " +
                            "ear_type, drooling_level, beak_length, fins_type, " +
                            "venomous, shell_type, status, image_path) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    String petType = petTypeComboBox.getSelectedItem().toString();
                    
                    // Set values with null checks
                    stmt.setString(1, petId);
                    stmt.setString(2, petNameField.getText().trim());
                    stmt.setString(3, petType);
                    stmt.setString(4, getTextOrNull(petBreedField));
                    stmt.setString(5, getTextOrNull(petAgeField));
                    stmt.setString(6, getTextOrNull(petSexField));
                    stmt.setString(7, getTextOrNull(petWeightField));
                    stmt.setString(8, getTextOrNull(petConditionField));
                    stmt.setString(9, getTextOrNull(petDescriptionField));
                    stmt.setString(10, getTextOrNull(petPreviousOwnerField));
                    stmt.setString(11, getTextOrNull(petVaccinatedField));
                    stmt.setString(12, "Dog".equals(petType) || "Cat".equals(petType) ? getTextOrNull(petEarTypeField) : null);
                    stmt.setString(13, "Dog".equals(petType) ? getTextOrNull(petDroolingField) : null);
                    stmt.setString(14, "Bird".equals(petType) ? getTextOrNull(beakLengthField) : null);
                    stmt.setString(15, "Fish".equals(petType) ? getTextOrNull(finsTypeField) : null);
                    stmt.setString(16, "Snake".equals(petType) ? getTextOrNull(venomousField) : null);
                    stmt.setString(17, "Turtle".equals(petType) ? getTextOrNull(shellTypeField) : null);
                    stmt.setString(18, "available");
                    stmt.setString(19, destinationPath);
        
                    int result = stmt.executeUpdate();
                    
                    if (result > 0) { 
                        conn.commit();  // Commit only after successful insert 
                        
                        // Show success message and wait for user acknowledgment 
                        JOptionPane.showMessageDialog( 
                            this, 
                            "Pet added successfully!", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE 
                        ); 
                        
                        // Clear all fields after user clicks OK 
                        clearFields(); 
                        
                        // Refresh the appropriate adoption panel 
                        for (Component comp : mainPanel.getComponents()) { 
                            if (comp instanceof AbstractAnimalAdoptionPanel) { 
                                AbstractAnimalAdoptionPanel animalPanel =  
                                    (AbstractAnimalAdoptionPanel) comp; 
                                if (animalPanel.getAnimalType().equalsIgnoreCase(petType)) { 
                                    animalPanel.loadPets(); 
                                } 
                            } else if (comp instanceof DisplayOnlyPanel){ 
                                ((DisplayOnlyPanel) comp).refreshPanel();
                            }
                        }
                    }     
                } catch (SQLException ex) {
                    conn.rollback();
                    ex.printStackTrace();
                    showMessage("Database error: " + ex.getMessage());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                showMessage("Error connecting to database");
            }
        });

        backButton.addActionListener(e -> switchPanel("adminPetPanel"));
    }

    // Helper method to handle null values
    private String getTextOrNull(JTextField field) {
        String text = field.getText().trim();
        return text.isEmpty() ? null : text;
    }

    private void clearImagePreview() {
        selectedImageFile = null;
        imagePreviewLabel.setIcon(null);
        imagePreviewLabel.setText("No image selected");
    }


    private String generatePetId() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM pets WHERE pet_id LIKE ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                String year = String.valueOf(java.time.Year.now().getValue());
                stmt.setString(1, year + "-%");
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int count = rs.getInt(1) + 1;
                return String.format("%s-%04d", year, count);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void clearFields() {
        petNameField.setText("");
        petBreedField.setText("");
        petAgeField.setText("");
        petWeightField.setText("");
        petSexField.setText("");
        petConditionField.setText("");
        petDescriptionField.setText("");
        petPreviousOwnerField.setText("");
        petVaccinatedField.setText("");
        petEarTypeField.setText("");
        petDroolingField.setText("");
        beakLengthField.setText("");
        finsTypeField.setText("");
        venomousField.setText("");
        shellTypeField.setText("");
        petTypeComboBox.setSelectedIndex(0);
        clearImagePreview();
        updateDynamicFields();
    }

    @Override
    public void addComponents() {
        // Components are added in setupLayout()
    }
}