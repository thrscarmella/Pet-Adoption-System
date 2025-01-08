import javax.swing.*; 
import java.awt.*; 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import utils.DatabaseConnection; 
 
public class UserApplicationStatusPanel extends AbstractPanel { 
    private JLabel headerLabel; 
    private JPanel applicationPanel; 
    private JButton backButton; 
    private String userEmail; 
    private JScrollPane scrollPane; 
    private Timer refreshTimer; 
 
    public UserApplicationStatusPanel(JPanel mainPanel, CardLayout cardLayout) { 
        super(mainPanel, cardLayout); 
    } 
 
    @Override 
    protected void initializeComponents() { 
        headerLabel = new JLabel("My Applications"); 
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER); 
 
        applicationPanel = new JPanel(); 
        applicationPanel.setLayout(new BoxLayout(applicationPanel, BoxLayout.Y_AXIS)); 
        applicationPanel.setBackground(Color.WHITE); 
 
        scrollPane = new JScrollPane(applicationPanel); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        scrollPane.setBackground(Color.WHITE); 
 
        backButton = new JButton("Back"); 
        backButton.setPreferredSize(new Dimension(100, 40)); 
        backButton.setFont(new Font("Arial", Font.BOLD, 14)); 
    } 
 
    @Override 
    protected void setupLayout() { 
        setLayout(new BorderLayout(10, 10)); 
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
    } 
 
    @Override 
    protected void addComponents() { 
        add(headerLabel, BorderLayout.NORTH); 
        add(scrollPane, BorderLayout.CENTER); 
        add(backButton, BorderLayout.SOUTH); 
    } 
 
    @Override   
    protected void setupListeners() {   
        backButton.addActionListener(e -> { 
            // Stop the refresh timer before switching 
            if (refreshTimer != null && refreshTimer.isRunning()) { 
                refreshTimer.stop(); 
            } 
            
            // Pass the email back to AvailablePetsPanel 
            Component[] components = mainPanel.getComponents(); 
            for (Component comp : components) { 
                if (comp instanceof AvailablePetsPanel) { 
                    AvailablePetsPanel availablePetsPanel = (AvailablePetsPanel) comp; 
                    availablePetsPanel.setUserEmail(userEmail); // Make sure email is maintained 
                    break; 
                } 
            } 
            
            switchPanel("availablePetsPanel"); 
        });   
        setupAutoRefresh();   
    }
 
 
    private void addApplicationCard(int applicationId, String applicantName, 
                              String petType, String petName, String status, 
                              String submissionDate) { 
        JPanel card = new JPanel(); 
        card.setLayout(new BorderLayout(5, 5)); 
        card.setBorder(BorderFactory.createCompoundBorder( 
            BorderFactory.createEmptyBorder(3, 3, 3, 3), 
            BorderFactory.createLineBorder(Color.GRAY) 
        )); 
        card.setBackground(Color.WHITE); 
 
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 3, 3)); 
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
        infoPanel.setBackground(Color.WHITE); 
 
        // Add basic information 
        addInfoField(infoPanel, "Application ID:", String.valueOf(applicationId)); 
        addInfoField(infoPanel, "Applicant:", applicantName); 
        addInfoField(infoPanel, "Pet Type:", petType); 
        addInfoField(infoPanel, "Pet Name:", petName); 
        addInfoField(infoPanel, "Submitted:", submissionDate); 
 
        // Add status with special formatting 
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0)); 
    statusPanel.setBackground(Color.WHITE); 
     
    JLabel statusLabelTitle = createBoldLabel("Status: "); 
    String displayStatus = (status != null && !status.isEmpty()) ? status : "pending"; 
    JLabel statusLabel = new JLabel(displayStatus.toUpperCase()); 
    statusLabel.setFont(new Font("Arial", Font.BOLD, 13)); 
 
    // Color code based on status 
    switch (displayStatus.toLowerCase()) { 
        case "pending": 
            statusLabel.setForeground(new Color(255, 165, 0)); // Orange 
            break; 
        case "approved": 
            statusLabel.setForeground(new Color(0, 150, 0)); // Green 
            break; 
        case "rejected": 
            statusLabel.setForeground(Color.RED); 
            break; 
    } 
 
        statusPanel.add(statusLabelTitle); 
        statusPanel.add(statusLabel); 
        infoPanel.add(statusPanel); 
        infoPanel.add(new JLabel("")); 
 
        // Get additional application details 
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT * FROM adoption_applications WHERE application_id = ?"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                stmt.setInt(1, applicationId); 
                ResultSet rs = stmt.executeQuery(); 
 
                if (rs.next()) { 
                    addInfoField(infoPanel, "Phone:", rs.getString("phone")); 
                    addInfoField(infoPanel, "Address:", rs.getString("address")); 
                    addInfoField(infoPanel, "Reason for Adoption:", rs.getString("reason_for_adoption")); 
                    addInfoField(infoPanel, "Pet History:", rs.getString("pet_history")); 
                    addInfoField(infoPanel, "Home Status:", rs.getString("home_status")); 
                    addInfoField(infoPanel, "Household Info:", rs.getString("household_info")); 
                    addInfoField(infoPanel, "Pet Care Plan:", rs.getString("pet_care")); 
                } 
            } 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
        } 
 
        card.add(infoPanel, BorderLayout.CENTER); 
 
        JPanel wrapperPanel = new JPanel(new BorderLayout()); 
        wrapperPanel.setBackground(Color.WHITE); 
        wrapperPanel.add(card, BorderLayout.CENTER); 
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); 
 
        applicationPanel.add(wrapperPanel); 
    } 
 
    private void addInfoField(JPanel panel, String label, String value) { 
        panel.add(createBoldLabel(label)); 
        panel.add(createLabel(value != null ? value : "")); 
    } 
 
    private JLabel createBoldLabel(String text) { 
        JLabel label = new JLabel(text); 
        label.setFont(new Font("Arial", Font.BOLD, 12)); 
        return label; 
    } 
 
    private JLabel createLabel(String text) { 
        JLabel label = new JLabel(text); 
        label.setFont(new Font("Arial", Font.PLAIN, 12)); 
        return label; 
    } 
 
    public void forceRefresh() { 
        System.out.println("Debug: Force refresh called with email: " + userEmail); 
        applicationPanel.removeAll(); // Clear existing applications 
        loadApplications();          // Load applications again 
        applicationPanel.revalidate(); 
        applicationPanel.repaint(); 
    } 
     
    private void loadApplications() { 
        System.out.println("\n=== Loading Applications for " + userEmail + " ==="); 
        applicationPanel.removeAll(); 
     
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT * FROM adoption_applications WHERE email = ? ORDER BY created_at DESC"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                stmt.setString(1, userEmail); 
                ResultSet rs = stmt.executeQuery(); 
                 
                while (rs.next()) { 
                    int appId = rs.getInt("application_id"); 
                    String status = rs.getString("status"); 
                    String petName = rs.getString("pet_name"); 
                     
                    System.out.println("Loading application - ID: " + appId +  
                                     ", Pet: " + petName +  
                                     ", Status: " + status); 
                     
                    addApplicationCard( 
                        appId, 
                        rs.getString("applicant_name"), 
                        rs.getString("pet_type"), 
                        petName, 
                        status, 
                        rs.getTimestamp("created_at").toString() 
                    ); 
                } 
            } 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            showMessage("Error loading applications: " + ex.getMessage()); 
        } 
     
        applicationPanel.revalidate(); 
        applicationPanel.repaint(); 
        System.out.println("=== Finished Loading Applications ===\n"); 
    } 
     
    // Add this method to verify the email is set 
    public String getUserEmail() { 
        return userEmail; 
    }

    private void verifyDatabaseConnection() { 
        try (Connection conn = DatabaseConnection.getConnection()) { 
            if (conn != null && !conn.isClosed()) { 
                System.out.println("Debug: Database connection successful"); 
                // Test query 
                String query = "SELECT COUNT(*) FROM adoption_applications WHERE email = ?"; 
                try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                    stmt.setString(1, userEmail); 
                    ResultSet rs = stmt.executeQuery(); 
                    if (rs.next()) { 
                        System.out.println("Debug: Found " + rs.getInt(1) + " applications for " + userEmail); 
                    } 
                } 
            } 
        } catch (SQLException ex) { 
            System.out.println("Debug: Database connection error - " + ex.getMessage()); 
            ex.printStackTrace(); 
        } 
    }
    
    public void checkApplications() { 
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT application_id, status FROM adoption_applications WHERE email = ?"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                stmt.setString(1, userEmail); 
                ResultSet rs = stmt.executeQuery(); 
                System.out.println("Debug: Checking applications for email: " + userEmail); 
                while (rs.next()) { 
                    System.out.println("Debug: Found application ID: " + rs.getInt("application_id") +  
                                     " with status: " + rs.getString("status")); 
                } 
            } 
        } catch (SQLException ex) { 
            System.out.println("Debug: Error checking applications - " + ex.getMessage()); 
            ex.printStackTrace(); 
        } 
    }
 
    private void showNoApplicationsMessage() { 
        JPanel messagePanel = new JPanel(new BorderLayout()); 
        messagePanel.setBackground(Color.WHITE); 
        JLabel messageLabel = new JLabel("You haven't submitted any adoption applications yet."); 
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        messagePanel.add(messageLabel, BorderLayout.CENTER); 
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); 
         
        applicationPanel.add(messagePanel); 
    } 

    public void setUserEmail(String email) { 
        System.out.println("Debug: Setting email in UserApplicationStatusPanel: " + email); 
        this.userEmail = email; 
        if (email != null && !email.isEmpty()) { 
            loadApplications(); 
            setupAutoRefresh(); 
        } else { 
            if (refreshTimer != null && refreshTimer.isRunning()) { 
                refreshTimer.stop(); 
            } 
            showNoApplicationsMessage(); 
        } 
    } 
     
    @Override 
    public void setVisible(boolean visible) { 
        super.setVisible(visible); 
        if (visible && userEmail != null && !userEmail.isEmpty()) { 
            System.out.println("Debug: Panel becoming visible, refreshing applications"); 
            forceRefresh(); 
        } 
    } 
     
    private void setupAutoRefresh() { 
        if (refreshTimer != null && refreshTimer.isRunning()) { 
            refreshTimer.stop(); 
        } 
         
        refreshTimer = new Timer(5000, e -> { 
            if (isVisible() && userEmail != null && !userEmail.isEmpty()) { 
                System.out.println("Debug: Auto-refreshing applications"); 
                loadApplications(); 
            } 
        }); 
        refreshTimer.setRepeats(true); 
        refreshTimer.start(); 
    }
 
    

    public void refreshApplications() { 
        System.out.println("Debug: Manual refresh triggered"); 
        applicationPanel.removeAll(); 
        loadApplications(); 
        applicationPanel.revalidate(); 
        applicationPanel.repaint(); 
    } 
 
    @Override 
    public void removeNotify() { 
        if (refreshTimer != null && refreshTimer.isRunning()) { 
            refreshTimer.stop(); 
        } 
        super.removeNotify(); 
    }

}