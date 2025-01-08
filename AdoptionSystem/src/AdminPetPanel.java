import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AdminPetPanel extends AbstractPanel {
    private JButton addPetButton;
    private JButton removePetButton;
    private JButton pendingApplicationsButton;
    private JButton logoutButton;
    private String adminName;
    private String adminId;
    private JLabel welcomeLabel;
    private JPanel headerPanel;
    private Image backgroundImage;

    public AdminPetPanel(JPanel mainPanel, CardLayout cardLayout) {
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

    public void setAdminInfo(String name, String id) { 
        this.adminName = name; 
        this.adminId = id; 
        updateWelcomeMessage(); 
    } 
 
    private void updateWelcomeMessage() { 
        if (welcomeLabel != null && adminName != null && adminId != null) { 
            welcomeLabel.setText("Hi, " + adminName + "! (" + adminId + ")"); 
        } 
    }

    @Override
    public void initializeComponents() {
 
        headerPanel = new JPanel();
        headerPanel.setLayout((new BorderLayout()));

        welcomeLabel = new JLabel(); 
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 18));

        addPetButton = new JButton("ADD PET");
        addPetButton.setPreferredSize(new Dimension(450, 60));
        addPetButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        addPetButton.setFocusPainted(false);

        removePetButton = new JButton("REMOVE PET");
        removePetButton.setPreferredSize(new Dimension(450, 60));
        removePetButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        removePetButton.setFocusPainted(false);

         // Initialize the new pending applications button
         pendingApplicationsButton = new JButton("PENDING APPLICATIONS");
         pendingApplicationsButton.setPreferredSize(new Dimension(450, 60));
         pendingApplicationsButton.setFont(new Font("Helvetica", Font.BOLD, 18));
         pendingApplicationsButton.setFocusPainted(false);

        logoutButton = new JButton("LOGOUT");
        logoutButton.setPreferredSize(new Dimension(450, 60));
        logoutButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        logoutButton.setFocusPainted(false);
    }

    @Override 
    public void setupLayout() {
        removeAll();
        setLayout(new GridBagLayout());
        JPanel centerPanel = new JPanel(new GridBagLayout()); 
        centerPanel.setOpaque(false); 
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(10, 10, 10, 10); 
    
        // Welcome label (top) 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.WEST;
        welcomeLabel.setForeground(Color.WHITE); 
        add(welcomeLabel, gbc); 
    
        // ADMIN label (below welcome) 
        gbc.gridy = 1; 
        gbc.anchor = GridBagConstraints.CENTER; 
        JLabel headerLabel = new JLabel("ADMIN"); 
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        headerLabel.setForeground(Color.WHITE); 
        add(headerLabel, gbc); 
    
        // Buttons (start from gridy = 2) 
        gbc.gridy = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        add(addPetButton, gbc); 
    
        gbc.gridy = 3; 
        add(removePetButton, gbc); 
    
        gbc.gridy = 4; 
        add(pendingApplicationsButton, gbc); 
    
        gbc.gridy = 5; 
        add(logoutButton, gbc);

        // Add the center panel to the main panel 
        add(centerPanel); 

        revalidate();
        repaint();
    }

    @Override
    public void setupListeners() {
        addPetButton.addActionListener(e -> switchPanel("adminAddPetPanel"));
        removePetButton.addActionListener(e -> switchPanel("adminRemovePetPanel"));
        pendingApplicationsButton.addActionListener(e -> switchPanel("adminPendingApplicationsPanel"));
        logoutButton.addActionListener(e -> switchPanel("startPanel"));
    }

    @Override
    public void addComponents() {
        // Components are added in setupLayout()
    }
}