import javax.swing.*; 
import javax.swing.table.DefaultTableModel; 
import java.awt.*; 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import utils.DatabaseConnection; 
 
public class AdminPendingApplicationsPanel extends AbstractPanel { 
    private JTable applicationsTable; 
    private DefaultTableModel tableModel; 
    private JScrollPane scrollPane; 
    private JButton refreshButton; 
    private JDialog detailsDialog; 
    private JButton approveButton; 
    private JButton rejectButton; 
    private JButton backButton; 
 
    public AdminPendingApplicationsPanel(JPanel mainPanel, CardLayout cardLayout) { 
        super(mainPanel, cardLayout); 
    } 
 
    @Override 
    protected void initializeComponents() { 
        // Initialize the table model with column names 
        String[] columnNames = {"ID", "Name", "Email", "Phone", "Pet Type", "Pet Name", "Status"}; 
        tableModel = new DefaultTableModel(columnNames, 0) { 
            @Override 
            public boolean isCellEditable(int row, int column) { 
                return false; // Make table read-only 
            } 
        }; 
         
        // Create and set up the table 
        applicationsTable = new JTable(tableModel); 
        applicationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        scrollPane = new JScrollPane(applicationsTable); 
         
        // Add refresh button 
        refreshButton = new JButton("Refresh"); 
         
        // Initialize approve/reject buttons 
        approveButton = new JButton("Approve"); 
        approveButton.setBackground(new Color(46, 204, 113)); 
        approveButton.setForeground(Color.WHITE); 
        approveButton.setFocusPainted(false);  
         
        rejectButton = new JButton("Reject"); 
        rejectButton.setBackground(new Color(231, 76, 60)); 
        rejectButton.setForeground(Color.WHITE); 
        rejectButton.setFocusPainted(false);  
 
        backButton = new JButton("Back to Admin Panel"); 
        backButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        backButton.setPreferredSize(new Dimension(200, 40)); 
        backButton.setFocusPainted(false);  
         
        // Load initial data 
        loadApplications(); 
    } 
 
    @Override 
    protected void setupLayout() { 
        setLayout(new BorderLayout()); 
         
        JLabel titleLabel = new JLabel("Pending Adoption Applications", SwingConstants.CENTER); 
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        add(titleLabel, BorderLayout.NORTH); 
         
        // Add table 
        add(scrollPane, BorderLayout.CENTER); 
         
        // Create bottom panel for buttons 
        JPanel bottomPanel = new JPanel(new BorderLayout()); 
         
        // Add back button to the left 
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        backButtonPanel.add(backButton); 
        bottomPanel.add(backButtonPanel, BorderLayout.WEST); 
         
        // Add refresh button to the right 
        JPanel refreshButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
        refreshButtonPanel.add(refreshButton); 
        bottomPanel.add(refreshButtonPanel, BorderLayout.EAST); 
         
        add(bottomPanel, BorderLayout.SOUTH); 
    } 
 
    @Override 
    protected void addComponents() { 
        // Components are added in setupLayout() 
    } 
 
    @Override 
    protected void setupListeners() { 
        refreshButton.addActionListener(e -> loadApplications()); 
         
        // Add table row click listener 
        applicationsTable.getSelectionModel().addListSelectionListener(e -> { 
            if (!e.getValueIsAdjusting() && applicationsTable.getSelectedRow() != -1) { 
                showApplicationDetails(applicationsTable.getSelectedRow()); 
            } 
        }); 
 
        backButton.addActionListener(e -> switchPanel("adminPetPanel")); 
    } 
 
    private void loadApplications() { 
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT application_id, applicant_name, email, phone, pet_type, pet_name, status " + 
                          "FROM adoption_applications " + 
                          "WHERE status = 'pending' " + 
                          "ORDER BY application_id DESC"; 
 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                System.out.println("Executing query: " + query); // Debug print 
                ResultSet rs = stmt.executeQuery(); 
                 
                // Clear existing data 
                tableModel.setRowCount(0); 
                 
                int count = 0; 
                while (rs.next()) { 
                    count++; 
                    Object[] row = { 
                        rs.getInt("application_id"), 
                        rs.getString("applicant_name"), 
                        rs.getString("email"), 
                        rs.getString("phone"), 
                        rs.getString("pet_type"), 
                        rs.getString("pet_name"), 
                        rs.getString("status") 
                    }; 
                    tableModel.addRow(row); 
                } 
                System.out.println("Found " + count + " pending applications"); // Debug print 
            } 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            JOptionPane.showMessageDialog(this, 
                "Error loading applications: " + ex.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE); 
        } 
    } 
 
    private void showApplicationDetails(int row) { 
        int applicationId = (int) tableModel.getValueAt(row, 0); 
         
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT * FROM adoption_applications WHERE application_id = ?"; 
            try (PreparedStatement stmt = conn.prepareStatement(query)) { 
                stmt.setInt(1, applicationId); 
                ResultSet rs = stmt.executeQuery(); 
                 
                if (rs.next()) { 
                    // Create and show details dialog 
                    detailsDialog = new JDialog(); 
                    detailsDialog.setTitle("Application Details"); 
                    detailsDialog.setModal(true); 
                    detailsDialog.setLayout(new BorderLayout()); 
                     
                    // Create details panel 
                    JPanel detailsPanel = new JPanel(); 
                    detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); 
                    detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
                     
                    // Add application details 
                    addDetailField(detailsPanel, "Applicant Name", rs.getString("applicant_name")); 
                    addDetailField(detailsPanel, "Email", rs.getString("email")); 
                    addDetailField(detailsPanel, "Phone", rs.getString("phone")); 
                    addDetailField(detailsPanel, "Address", rs.getString("address")); 
                    addDetailField(detailsPanel, "Pet Type", rs.getString("pet_type")); 
                    addDetailField(detailsPanel, "Pet Name", rs.getString("pet_name")); 
                    addDetailField(detailsPanel, "Reason for Adoption", rs.getString("reason_for_adoption")); 
                    addDetailField(detailsPanel, "Pet History", rs.getString("pet_history")); 
                    addDetailField(detailsPanel, "Home Status", rs.getString("home_status")); 
                    addDetailField(detailsPanel, "Household Info", rs.getString("household_info")); 
                    addDetailField(detailsPanel, "Pet Care", rs.getString("pet_care")); 
                    addDetailField(detailsPanel, "Behavioral Acceptance", rs.getString("behavioral_acceptance")); 
                     
                    // Add scroll pane 
                    JScrollPane scrollPane = new JScrollPane(detailsPanel); 
                    scrollPane.setPreferredSize(new Dimension(500, 400)); 
                    detailsDialog.add(scrollPane, BorderLayout.CENTER); 
                     
                    // Add buttons panel 
                    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); 
                    buttonsPanel.add(approveButton); 
                    buttonsPanel.add(rejectButton); 
                    detailsDialog.add(buttonsPanel, BorderLayout.SOUTH); 
                     
                    // Add button listeners 
                    approveButton.addActionListener(e -> updateApplicationStatus(applicationId, "approved")); 
                    rejectButton.addActionListener(e -> updateApplicationStatus(applicationId, "rejected")); 
                     
                    // Show dialog 
                    detailsDialog.pack(); 
                    detailsDialog.setLocationRelativeTo(this); 
                    detailsDialog.setVisible(true); 
                } 
            } 
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
            JOptionPane.showMessageDialog(this, 
                "Error loading application details: " + ex.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE); 
        } 
    } 
 
    private void addDetailField(JPanel panel, String label, String value) { 
        JPanel fieldPanel = new JPanel(new BorderLayout()); 
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); 
         
        JLabel labelComponent = new JLabel(label + ":"); 
        labelComponent.setFont(new Font("Arial", Font.BOLD, 12)); 
        fieldPanel.add(labelComponent, BorderLayout.NORTH); 
         
        JTextArea valueArea = new JTextArea(value); 
        valueArea.setWrapStyleWord(true); 
        valueArea.setLineWrap(true); 
        valueArea.setOpaque(false); 
        valueArea.setEditable(false); 
        valueArea.setFont(new Font("Arial", Font.PLAIN, 12)); 
        fieldPanel.add(valueArea, BorderLayout.CENTER); 
         
        panel.add(fieldPanel); 
    } 
 
    private void updateApplicationStatus(int applicationId, String newStatus) {  
        try (Connection conn = DatabaseConnection.getConnection()) {  
            // First check if the status is already set 
            String checkQuery = "SELECT status FROM adoption_applications WHERE application_id = ?"; 
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) { 
                checkStmt.setInt(1, applicationId); 
                ResultSet rs = checkStmt.executeQuery(); 
                 
                if (rs.next()) { 
                    String currentStatus = rs.getString("status"); 
                    // If status is already approved or rejected, don't allow changes 
                    if ("approved".equals(currentStatus) || "rejected".equals(currentStatus)) { 
                        showMessage("This application has already been " + currentStatus); 
                        return; 
                    } 
                } 
            } 
 
                // If status is still pending, proceed with update 
                String query = "UPDATE adoption_applications SET status = ? WHERE application_id = ?";  
                try (PreparedStatement stmt = conn.prepareStatement(query)) {  
                    stmt.setString(1, newStatus);  
                    stmt.setInt(2, applicationId);  
                    
                    int result = stmt.executeUpdate();  
                    if (result > 0) {  
                        // Get the email associated with this application  
                        String email = getApplicationEmail(applicationId);  
                        
                        // Update UserApplicationStatusPanel if it exists 
                        Component[] components = mainPanel.getComponents();  
                        for (Component comp : components) {  
                            if (comp instanceof UserApplicationStatusPanel) {  
                                UserApplicationStatusPanel userPanel = (UserApplicationStatusPanel) comp; 
                                if (email.equals(userPanel.getUserEmail())) { 
                                    userPanel.refreshApplications();  
                                } 
                            }  
                        }  
        
                        showMessage("Application status updated to: " + newStatus);    
                    
                    // Close the details dialog  
                    if (detailsDialog != null) {  
                        detailsDialog.dispose();  
                    } 
    
                        // Remove the application from the pending applications table 
                    for (int i = 0; i < tableModel.getRowCount(); i++) {  
                        if ((int)tableModel.getValueAt(i, 0) == applicationId) {  
                            tableModel.removeRow(i);  
                            break;  
                        }  
                    }  
                    
                    // Refresh the table  
                    loadApplications();  
                }    
            }    
        } catch (SQLException ex) {    
            ex.printStackTrace();    
            showMessage("Error updating application status: " + ex.getMessage());    
        }    
    } 
 
    private String getApplicationEmail(int applicationId) {  
        try (Connection conn = DatabaseConnection.getConnection()) {  
            String query = "SELECT email FROM adoption_applications WHERE application_id = ?";  
            try (PreparedStatement stmt = conn.prepareStatement(query)) {  
                stmt.setInt(1, applicationId);  
                ResultSet rs = stmt.executeQuery();  
                if (rs.next()) {  
                    return rs.getString("email");  
                }  
            }  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
        }  
        return null;  
    } 
}