import javax.swing.*;
import java.awt.*;

public abstract class AbstractPanel extends JPanel {
    protected final JPanel mainPanel;
    protected final CardLayout cardLayout;

    public AbstractPanel(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        
        setLayout(new BorderLayout());
        
        // Call initialization methods in proper order
        preInitialize();
        initializeComponents();
        setupLayout();
        addComponents();
        setupListeners();
    }

    protected void preInitialize() {
        // Override in subclasses if needed
    }

    protected void switchPanel(String panelName) { 
        cardLayout.show(mainPanel, panelName); 
         
        // Comment out or remove this part 
        /* 
        Component[] components = mainPanel.getComponents(); 
        for (Component comp : components) { 
            if (comp instanceof AbstractAnimalAdoptionPanel) { 
                AbstractAnimalAdoptionPanel panel = (AbstractAnimalAdoptionPanel) comp; 
                if (comp.isVisible()) { 
                    panel.loadPets(); 
                } 
            } 
        } 
        */ 
    }

    protected abstract void initializeComponents();

    protected abstract void setupLayout();

    protected abstract void addComponents();

    protected abstract void setupListeners();

    protected void showMessage(String message) { 
        SwingUtilities.invokeLater(() -> { 
            JOptionPane.showMessageDialog( 
                SwingUtilities.getWindowAncestor(this), 
                message, 
                "Message", 
                JOptionPane.INFORMATION_MESSAGE 
            ); 
        }); 
    }
}