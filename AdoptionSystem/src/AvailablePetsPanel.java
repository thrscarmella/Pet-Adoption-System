import javax.swing.*;
import utils.DatabaseConnection;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvailablePetsPanel extends AbstractPanel {
    private JButton[] petButtons;
    private JLabel headerLabel;
    private JEditorPane descriptionText;
    private JLabel welcomeLabel;   
    private String userName;       
    private JButton pendingApplicationsButton;   
    private JButton logoutButton;
    private String userEmail;
    private JPanel headerPanel;

    public AvailablePetsPanel(JPanel mainPanel, CardLayout cardLayout) {
        super(mainPanel, cardLayout);
    }

    public void setUserInfo(String name, String email) { 
        this.userName = name;
        this.userEmail = email;
        updateWelcomeMessage(); 
    } 
 
    private void updateWelcomeMessage() { 
        if (welcomeLabel != null && userName != null) { 
            welcomeLabel.setText("Hi, " + userName + "!");
            welcomeLabel.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 18));
            welcomeLabel.revalidate();
            welcomeLabel.repaint();
        } 
    }

    public void setUserEmail(String email) { 
        this.userEmail = email; 
        updateWelcomeMessage();
          
        Component[] components = mainPanel.getComponents(); 
        for (Component comp : components) { 
            if (comp instanceof UserApplicationStatusPanel) { 
                UserApplicationStatusPanel userAppPanel = (UserApplicationStatusPanel) comp; 
                userAppPanel.setUserEmail(email); 
                userAppPanel.forceRefresh(); 
            } 
        } 
     
        pendingApplicationsButton.addActionListener(e -> { 
        if (userEmail != null && !userEmail.isEmpty()) {  
            for (Component comp : components) { 
                if (comp instanceof UserApplicationStatusPanel) { 
                    UserApplicationStatusPanel userAppPanel = (UserApplicationStatusPanel) comp; 
                    userAppPanel.setUserEmail(userEmail); 
                    userAppPanel.forceRefresh(); 
                    cardLayout.show(mainPanel, "userApplicationStatusPanel"); 
                    break; 
                } 
            } 
        } else { 
            JOptionPane.showMessageDialog(this,  
                "Please log in first",  
                "Login Required",  
                JOptionPane.WARNING_MESSAGE); 
        } 
    });
     
        logoutButton.addActionListener(e -> { 
            this.userEmail = null; 
            this.userName = null; 
            updateWelcomeMessage(); 
            
            for (Component comp : components) { 
                if (comp instanceof UserApplicationStatusPanel) { 
                    UserApplicationStatusPanel userAppPanel = (UserApplicationStatusPanel) comp; 
                    userAppPanel.setUserEmail(null); 
                } 
            } 
            
            switchPanel("startPanel"); 
        });
  
        try (Connection conn = DatabaseConnection.getConnection()) {  
            String query = "SELECT first_name FROM users WHERE email = ?";    
            try (PreparedStatement stmt = conn.prepareStatement(query)) {  
                stmt.setString(1, email);  
                ResultSet rs = stmt.executeQuery();  
                if (rs.next()) {  
                    userName = rs.getString("first_name");   
                    welcomeLabel.setText("Hi, " + userName + "!");   
                }  
            }  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
        }  
           
        for (Component comp : components) {  
            if (comp instanceof AbstractAnimalAdoptionPanel) {  
                System.out.println("Passing email to " + comp.getClass().getSimpleName());  
                ((AbstractAnimalAdoptionPanel) comp).setUserEmail(email);  
            }  
        }
    }

    @Override
    protected void initializeComponents() {
        String[] petTypes = {"Dog", "Cat", "Bird", "Fish", "Rabbit", "Snake", 
                        "Ferret", "Hamster", "Turtle", "Lizard"};
        petButtons = new JButton[petTypes.length];
        
        for (int i = 0; i < petTypes.length; i++) {
            petButtons[i] = new JButton(petTypes[i]);
            petButtons[i].setPreferredSize(new Dimension(200, 50));
        }

        // Initialize new components 
        welcomeLabel = new JLabel(); 
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        headerPanel = new JPanel(new BorderLayout());
 
        pendingApplicationsButton = new JButton("MY PENDING APPLICATIONS"); 
        pendingApplicationsButton.setPreferredSize(new Dimension(250, 50)); 
        pendingApplicationsButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        pendingApplicationsButton.setFocusPainted(false);
 
        logoutButton = new JButton("LOGOUT"); 
        logoutButton.setPreferredSize(new Dimension(250, 50)); 
        logoutButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        logoutButton.setFocusPainted(false);

        headerLabel = new JLabel("Available Pets For Adoption");
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        descriptionText = new JEditorPane();
        descriptionText.setContentType("text/html");
        descriptionText.setEditable(false);
        descriptionText.setBackground(getBackground());
        descriptionText.setMargin(new Insets(5, 10, 5, 10));
        descriptionText.setPreferredSize(new Dimension(600, 300));

    }

    @Override
    protected void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        for (JButton button : petButtons) {
            gbc.insets.left = 20;
            leftPanel.add(button, gbc);
            gbc.gridy++;
        }

        return leftPanel;
    }

    private JPanel createRightPanel() { 
        JPanel rightPanel = new JPanel(new BorderLayout()); 
        rightPanel.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0)); 
 
        JPanel headerArea = new JPanel(new BorderLayout()); 
         
        JLabel headerLabel = new JLabel("Available Pets For Adoption"); 
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 40)); 
        headerLabel.setHorizontalAlignment(JLabel.CENTER); 
        headerArea.add(headerLabel, BorderLayout.NORTH); 
 
        JEditorPane descriptionText = new JEditorPane(); 
        descriptionText.setContentType("text/html");
        descriptionText.setText("<html><div style='text-align: justify; font-family: Helvetica; font-size: 20px;'>" +
                "The animals currently sheltered at our facility are in dire need of new, loving homes. " +
                "Each of these animals carries a unique story, many of which are heart-wrenching. " +
                "Before they found their way to us, some lost their homes due to various unfortunate circumstances. " +
                "Their past might have been filled with hardships and uncertainties, but their spirits remain resilient and hopeful. " +
                "Rescued from the streets, abandoned homes, or unsafe environments, these animals have been given a second chance at life. " +
                "They eagerly await the warmth and security of a permanent home where they can thrive and share their unconditional love. " +
                "Adopting one of these deserving pets not only transforms their lives but " +
                "also brings immeasurable joy and companionship to their new families.</div></html>");
        descriptionText.setEditable(false);
        descriptionText.setBackground(getBackground());
        descriptionText.setMargin(new Insets(5, 10, 5, 10));
        descriptionText.setPreferredSize(new Dimension(600, 300));

        JPanel userSection = new JPanel(); 
        userSection.setLayout(new BoxLayout(userSection, BoxLayout.Y_AXIS)); 
         
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        userSection.add(welcomeLabel); 
        userSection.add(Box.createVerticalStrut(10)); 
         
        pendingApplicationsButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        userSection.add(pendingApplicationsButton); 
        userSection.add(Box.createVerticalStrut(10)); 
         
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        userSection.add(logoutButton); 
 
        rightPanel.add(headerArea, BorderLayout.NORTH); 
        rightPanel.add(descriptionText, BorderLayout.CENTER); 
        rightPanel.add(userSection, BorderLayout.SOUTH); 
 
        return rightPanel;
    }

    @Override
    protected void addComponents() {
        
    }

    @Override     
    protected void setupListeners() {     
        String[] petTypes = {"Dog", "Cat", "Bird", "Fish", "Rabbit", "Snake",      
                            "Ferret", "Hamster", "Turtle", "Lizard"};     
                            
        for (int i = 0; i < petButtons.length; i++) {     
            final String petType = petTypes[i];     
            petButtons[i].addActionListener(e -> switchPanel(petType.toLowerCase() + "AdoptionPanel"));     
        }     
  
        pendingApplicationsButton.addActionListener(e -> {     
            if (userEmail != null && !userEmail.isEmpty()) { 
                Component[] components = mainPanel.getComponents();     
                for (Component comp : components) {     
                    if (comp instanceof UserApplicationStatusPanel) {     
                        UserApplicationStatusPanel userAppPanel = (UserApplicationStatusPanel) comp;   
                        userAppPanel.setUserEmail(userEmail);   
                        userAppPanel.forceRefresh();   
                        cardLayout.show(mainPanel, "userApplicationStatusPanel");   
                        break; 
                    }     
                } 
            } else { 
                JOptionPane.showMessageDialog(this,  
                    "Please log in first",  
                    "Login Required",  
                    JOptionPane.WARNING_MESSAGE); 
            } 
        });   
    
        logoutButton.addActionListener(e -> {     
            this.userName = null;     
            this.userEmail = null; 
            updateWelcomeMessage();     
            switchPanel("startPanel");     
        });     
    }

    @Override 
    public void setVisible(boolean visible) { 
        super.setVisible(visible); 
        if (visible && userEmail != null) { 
            System.out.println("Debug: AvailablePetsPanel became visible with email: " + userEmail); 
            Component[] components = mainPanel.getComponents(); 
            for (Component comp : components) { 
                if (comp instanceof UserApplicationStatusPanel) { 
                    UserApplicationStatusPanel userAppPanel = (UserApplicationStatusPanel) comp; 
                    userAppPanel.setUserEmail(userEmail); 
                    break; 
                } 
            } 
        } 
    }
}