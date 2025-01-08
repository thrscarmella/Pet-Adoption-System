import javax.swing.*;
import java.awt.*;

public class AdoptionSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pet Adoption System");
        JPanel mainPanel = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

        // Create start panel first
        JPanel startPanel = createStartPanel(mainPanel, cardLayout);
        mainPanel.add(startPanel, "startPanel");

        // Create panel instances
        LoginPanel loginPanel = new LoginPanel(mainPanel, cardLayout);
        AdminLoginPanel adminLoginPanel = new AdminLoginPanel(mainPanel, cardLayout);
        SigninPanel signinPanel = new SigninPanel(mainPanel, cardLayout);
        AdminSigninPanel adminSigninPanel = new AdminSigninPanel(mainPanel, cardLayout);
        
        // Create admin panels
        AdminPetPanel adminPetPanel = new AdminPetPanel(mainPanel, cardLayout);
        AdminAddPetPanel adminAddPetPanel = new AdminAddPetPanel(mainPanel, cardLayout);
        AdminRemovePetPanel adminRemovePetPanel = new AdminRemovePetPanel(mainPanel, cardLayout);
        AvailablePetsPanel availablePetsPanel = new AvailablePetsPanel(mainPanel, cardLayout);
        AdminPendingApplicationsPanel adminPendingApplicationsPanel = new AdminPendingApplicationsPanel(mainPanel, cardLayout);

        DisplayOnlyPanel displayOnlyPanel = new DisplayOnlyPanel(mainPanel, cardLayout); 

        // Create animal adoption panels
        DogAdoptionPanel dogAdoptionPanel = new DogAdoptionPanel(mainPanel, cardLayout);
        CatAdoptionPanel catAdoptionPanel = new CatAdoptionPanel(mainPanel, cardLayout);
        BirdAdoptionPanel birdAdoptionPanel = new BirdAdoptionPanel(mainPanel, cardLayout);
        FishAdoptionPanel fishAdoptionPanel = new FishAdoptionPanel(mainPanel, cardLayout);
        RabbitAdoptionPanel rabbitAdoptionPanel = new RabbitAdoptionPanel(mainPanel, cardLayout);
        SnakeAdoptionPanel snakeAdoptionPanel = new SnakeAdoptionPanel(mainPanel, cardLayout);
        FerretAdoptionPanel ferretAdoptionPanel = new FerretAdoptionPanel(mainPanel, cardLayout);
        HamsterAdoptionPanel hamsterAdoptionPanel = new HamsterAdoptionPanel(mainPanel, cardLayout);
        TurtleAdoptionPanel turtleAdoptionPanel = new TurtleAdoptionPanel(mainPanel, cardLayout);
        LizardAdoptionPanel lizardAdoptionPanel = new LizardAdoptionPanel(mainPanel, cardLayout);

        UserApplicationStatusPanel userApplicationStatusPanel = new UserApplicationStatusPanel(mainPanel, cardLayout);

        // Add authentication panels to main panel
        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(adminLoginPanel, "AdminLoginPanel");
        mainPanel.add(signinPanel, "SigninPanel");
        mainPanel.add(adminSigninPanel, "adminSigninPanel");

        // Add admin panels to main panel
        mainPanel.add(adminPetPanel, "adminPetPanel");
        mainPanel.add(adminAddPetPanel, "adminAddPetPanel");
        mainPanel.add(adminRemovePetPanel, "adminRemovePetPanel");
        mainPanel.add(availablePetsPanel, "availablePetsPanel");
        mainPanel.add(adminPendingApplicationsPanel, "adminPendingApplicationsPanel");

        mainPanel.add(displayOnlyPanel, "displayOnlyPanel");

        // Add animal adoption panels to main panel
        mainPanel.add(dogAdoptionPanel, "dogAdoptionPanel");
        mainPanel.add(catAdoptionPanel, "catAdoptionPanel");
        mainPanel.add(birdAdoptionPanel, "birdAdoptionPanel");
        mainPanel.add(fishAdoptionPanel, "fishAdoptionPanel");
        mainPanel.add(rabbitAdoptionPanel, "rabbitAdoptionPanel");
        mainPanel.add(snakeAdoptionPanel, "snakeAdoptionPanel");
        mainPanel.add(ferretAdoptionPanel, "ferretAdoptionPanel");
        mainPanel.add(hamsterAdoptionPanel, "hamsterAdoptionPanel");
        mainPanel.add(turtleAdoptionPanel, "turtleAdoptionPanel");
        mainPanel.add(lizardAdoptionPanel, "lizardAdoptionPanel");

        mainPanel.add(userApplicationStatusPanel, "userApplicationStatusPanel");

        // In your constructor or initialization method
        mainPanel.add(new AdoptionFormPanel(mainPanel, cardLayout, "previousPanelName"), "adoptionFormPanel");

        // Set up the frame
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Show start panel first
        cardLayout.show(mainPanel, "startPanel");
        cardLayout.addLayoutComponent(adminPetPanel, "adminPetPanel");
    }

    private static JPanel createStartPanel(JPanel mainPanel, CardLayout cardLayout) {  
        JPanel startPanel = new JPanel(new BorderLayout());  
     
        ImageIcon imageIcon = new ImageIcon("C:/Users/mcdeu/Documents/object/o.png");
        JLabel background = new JLabel() { 
            @Override 
            public void paintComponent(Graphics g) { 
                super.paintComponent(g); 
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this); 
            } 
        }; 
        background.setLayout(new BorderLayout());
     
        // Header Panel  
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));  
        headerPanel.setOpaque(false);  // Make transparent to show background 
        JLabel headerLabel = new JLabel("Pet Adoption System", SwingConstants.LEFT);  
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 65));  
        JLabel taglineLabel = new JLabel("Find Your Perfect Companion – Adopt, Love, and Transform Lives!", SwingConstants.RIGHT);  
        taglineLabel.setFont(new Font("Helvetica", Font.ITALIC, 20));  
        headerPanel.add(headerLabel);  
        headerPanel.add(taglineLabel);  
     
        // Center Panel  
        JPanel centerPanel = new JPanel();  
        centerPanel.setOpaque(false);  // Make transparent to show background 
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));  
        centerPanel.add(Box.createVerticalStrut(150));  
     
        JLabel questionLabel = new JLabel("Let's Find Your Perfect Pet Match – Are You Ready?");  
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  
        questionLabel.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 40));  
        centerPanel.add(questionLabel);  
     
        // Button Panel  
        JPanel buttonPanel = new JPanel(new FlowLayout());  
        buttonPanel.setOpaque(false);  // Make transparent to show background 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));   
     
        JButton yesButton = new JButton("YES");  
        yesButton.setFont(new Font("Arial", Font.BOLD, 24));  
        yesButton.setPreferredSize(new Dimension(150, 50));  
        yesButton.setFocusPainted(false);
        yesButton.addActionListener(e -> cardLayout.show(mainPanel, "LoginPanel"));  
     
        JButton noButton = new JButton("NO");  
        noButton.setFont(new Font("Arial", Font.BOLD, 24));  
        noButton.setPreferredSize(new Dimension(150, 50));  
        noButton.setFocusPainted(false); 
        noButton.addActionListener(e -> {  
            cardLayout.show(mainPanel, "displayOnlyPanel");   
             
            // Find and refresh the DisplayOnlyPanel   
            for (Component comp : mainPanel.getComponents()) {   
                if (comp instanceof DisplayOnlyPanel) {   
                    ((DisplayOnlyPanel) comp).refreshPanel();   
                    break;   
                }   
            }   
        });  
     
        buttonPanel.add(yesButton);  
        buttonPanel.add(Box.createHorizontalStrut(20));  
        buttonPanel.add(noButton);  
     
        centerPanel.add(Box.createVerticalStrut(180));  
        centerPanel.add(buttonPanel);  
     
        // Admin Panel  
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));  
        adminPanel.setOpaque(false);  // Make transparent to show background 
        JButton adminButton = new JButton("ADMIN");  
        adminButton.setFont(new Font("Arial", Font.BOLD, 24));  
        adminButton.setPreferredSize(new Dimension(150, 80));  
        adminButton.setFocusPainted(false);  
        adminButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminLoginPanel"));  
        adminPanel.add(adminButton);  
     
        centerPanel.add(Box.createVerticalStrut(100));  
        centerPanel.add(adminPanel);  
     
        // Add components to background 
        background.add(headerPanel, BorderLayout.NORTH);  
        background.add(centerPanel, BorderLayout.CENTER);  
     
        // Add background to startPanel 
        startPanel.add(background); 
     
        return startPanel;  
    }
}