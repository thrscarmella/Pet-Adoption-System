import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PetAdopt {

    private static JFrame frame;
    private static JPanel mainPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        frame = new JFrame("Pet Adoption System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel headerLabel = new JLabel("Pet Adoption System", SwingConstants.LEFT);
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 65));
        JLabel taglineLabel = new JLabel("Find Your Perfect Companion – Adopt, Love, and Transform Lives!", SwingConstants.RIGHT);
        taglineLabel.setFont(new Font("Helvetica", Font.ITALIC, 20));
        headerPanel.add(headerLabel);
        headerPanel.add(taglineLabel);

        // Start Panel
        JPanel startPanel = new JPanel(new BorderLayout());
        startPanel.add(headerPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(280));
        JLabel questionLabel = new JLabel("Let’s Find Your Perfect Pet Match – Are You Ready?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC, 40));
        centerPanel.add(questionLabel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton yesButton = new JButton("YES");
        JButton noButton = new JButton("NO");
        yesButton.setFont(new Font("Arial", Font.BOLD, 24));
        yesButton.setPreferredSize(new Dimension(150, 50));
        yesButton.setFocusPainted(false);
        noButton.setFont(new Font("Arial", Font.BOLD, 24));
        noButton.setPreferredSize(new Dimension(150, 50));
        noButton.setFocusPainted(false);
        yesButton.addActionListener(e -> cardLayout.show(mainPanel, "loginPanel"));
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        centerPanel.add(Box.createVerticalStrut(180));
        centerPanel.add(buttonPanel);
        startPanel.add(centerPanel, BorderLayout.CENTER);

        // Login Panel
        LoginPanel loginPanel = new LoginPanel();

        // Main Panel Setup
        mainPanel.add(startPanel, "startPanel");
        mainPanel.add(loginPanel, "loginPanel");

        //Dog Adoption Panel
        JPanel dogAdoptionPanel = new JPanel(new BorderLayout());
        JLabel dogHeaderLabel = new JLabel("Dogs for Adoption", SwingConstants.CENTER);
        dogHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        dogAdoptionPanel.add(dogHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        JPanel mainPanel = new JPanel(new CardLayout());

        // Create a panel for images in rows
        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images
        JPanel secondRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Second row of images
        
        Font descriptionFont = new Font("Arial", Font.BOLD, 22);
        kokoDescription.setFont(descriptionFont);
        rombuDescription.setFont(descriptionFont);
        bambiDescription.setFont(descriptionFont);
        auroraDescription.setFont(descriptionFont);
        lotsoDescription.setFont(descriptionFont);
        juliDescription.setFont(descriptionFont);

        // Create "See More" buttons
        JButton kokoButton = new JButton("See More");
        JButton rombuButton = new JButton("See More");
        JButton bambiButton = new JButton("See More");
        JButton auroraButton = new JButton("See More");
        JButton lotsoButton = new JButton("See More");
        JButton juliButton = new JButton("See More");


        Dimension buttonSize = new Dimension(40, 40); // Example size: 120px width, 40px height
        kokoButton.setPreferredSize(buttonSize);
        rombuButton.setPreferredSize(buttonSize);
        bambiButton.setPreferredSize(buttonSize);
        auroraButton.setPreferredSize(buttonSize);
        lotsoButton.setPreferredSize(buttonSize);
        juliButton.setPreferredSize(buttonSize);
        
        // Load and scale the images
        ImageIcon kokoIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\dog-koko.jpg");
        Image scaledKoko = kokoIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledKokoIcon = new ImageIcon(scaledKoko);
        JLabel kokoLabel = new JLabel(scaledKokoIcon, SwingConstants.CENTER);
        JLabel kokoDescription = new JLabel("<html><center>ID: 2023-0001<br>Name: Koko<br>Age: 4<br>Sex: Male<br>Breed: Aspin</center></html>", SwingConstants.CENTER);
        // Create a panel for each dog (image + description + button)
        JPanel kokoPanel = new JPanel(new BorderLayout());
        kokoPanel.add(kokoLabel, BorderLayout.NORTH); // Image at the top
        kokoPanel.add(kokoDescription, BorderLayout.CENTER); // Description in the middle
        kokoPanel.add(kokoButton, BorderLayout.SOUTH); // Button at the bottom

        ImageIcon rombuIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\dog-rombu.jpg");
        Image scaledRombu = rombuIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledRombuIcon = new ImageIcon(scaledRombu);
        JLabel rombuLabel = new JLabel(scaledRombuIcon, SwingConstants.CENTER);
        JLabel rombuDescription = new JLabel("<html><center>ID: 2023-0002<br>Name: Rombu<br>Age: 3<br>Sex: Male<br>Breed: Aspin</center></html>", SwingConstants.CENTER);
        JPanel rombuPanel = new JPanel(new BorderLayout());
        rombuPanel.add(rombuLabel, BorderLayout.NORTH);
        rombuPanel.add(rombuDescription, BorderLayout.CENTER);
        rombuPanel.add(rombuButton, BorderLayout.SOUTH);
        
        ImageIcon bambiIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\dog-bambi.jpg");
        Image scaledBambi = bambiIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledBambiIcon = new ImageIcon(scaledBambi);
        JLabel bambiLabel = new JLabel(scaledBambiIcon, SwingConstants.CENTER);
        JLabel bambiDescription = new JLabel("<html><center>ID: 2023-0003<br>Name: Bambi<br>Age: 2<br>Sex: Female<br>Breed: Aspin</center></html>", SwingConstants.CENTER);
        JPanel bambiPanel = new JPanel(new BorderLayout());
        bambiPanel.add(bambiLabel, BorderLayout.NORTH);
        bambiPanel.add(bambiDescription, BorderLayout.CENTER);
        bambiPanel.add(bambiButton, BorderLayout.SOUTH);
        
        ImageIcon auroraIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\dog-aurora.jpeg");
        Image scaledAurora = auroraIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledAuroraIcon = new ImageIcon(scaledAurora);
        JLabel auroraLabel = new JLabel(scaledAuroraIcon, SwingConstants.CENTER);
        JLabel auroraDescription = new JLabel("<html><center>ID: 2023-0004<br>Name: Aurora<br>Age: 3<br>Sex: Female<br>Breed: American Pit<br>Bull Terrier</center></html>", SwingConstants.CENTER);
        JPanel auroraPanel = new JPanel(new BorderLayout());
        auroraPanel.add(auroraLabel, BorderLayout.NORTH); // Image at the top
        auroraPanel.add(auroraDescription, BorderLayout.CENTER); // Description in the middle
        auroraPanel.add(auroraButton, BorderLayout.SOUTH); // Button at the bottom

        ImageIcon lotsoIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\dog-lotso.jpeg");
        Image scaledLotso = lotsoIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledLotsoIcon = new ImageIcon(scaledLotso);
        JLabel lotsoLabel = new JLabel(scaledLotsoIcon, SwingConstants.CENTER);
        JLabel lotsoDescription = new JLabel("<html><center>ID: 2023-0002<br>Name: Lotso<br>Age: 8 months<br>Sex: Male<br>Breed: American Pit<br>Bull Terrier</center></html>", SwingConstants.CENTER);
        JPanel lotsoPanel = new JPanel(new BorderLayout());
        lotsoPanel.add(lotsoLabel, BorderLayout.NORTH);
        lotsoPanel.add(lotsoDescription, BorderLayout.CENTER);
        lotsoPanel.add(lotsoButton, BorderLayout.SOUTH);

        ImageIcon juliIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\dog-juli.jpeg");
        Image scaledJuli = juliIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledJuliIcon = new ImageIcon(scaledJuli);
        JLabel juliLabel = new JLabel(scaledJuliIcon, SwingConstants.CENTER);
        JLabel juliDescription = new JLabel("<html><center>ID: 2023-0003<br>Name: Juli<br>Age: 3<br>Sex: Female<br>Breed: Mixed Breed<br>- Mixed Breed</center></html>", SwingConstants.CENTER);
        JPanel juliPanel = new JPanel(new BorderLayout());
        juliPanel.add(juliLabel, BorderLayout.NORTH);
        juliPanel.add(juliDescription, BorderLayout.CENTER);
        juliPanel.add(juliButton, BorderLayout.SOUTH);


        

        firstRowPanel.add(kokoPanel);
        firstRowPanel.add(rombuPanel);
        firstRowPanel.add(bambiPanel);

        secondRowPanel.add(auroraPanel);
        secondRowPanel.add(lotsoPanel);
        secondRowPanel.add(juliPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel2 = new JPanel();
        mainPanel2.setLayout(new BoxLayout(mainPanel2, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel2.add(firstRowPanel); // Add the first row
        mainPanel2.add(secondRowPanel); // Add the second row

        scrollPane.setViewportView(mainPanel2);

        // Add the image row panel to the main panel
        dogAdoptionPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the dog detail panels
        JPanel kokoDetailPanel = createDetailPanel("More Details About Koko");
        JPanel rombuDetailPanel = createDetailPanel("More Details About Rombu");
        JPanel bambiDetailPanel = createDetailPanel("More Details About Bambi");
        JPanel auroraDetailPanel = createDetailPanel("More Details About Aurora");
        JPanel lotsoDetailPanel = createDetailPanel("More Details About Lotso");
        JPanel juliDetailPanel = createDetailPanel("More Details About Juli");

        // Optionally, add action listeners to the buttons
        kokoButton.addActionListener(e -> switchPanel(mainPanel, "KokoDetails"));
        rombuButton.addActionListener(e -> switchPanel(mainPanel, "RombuDetails"));
        bambiButton.addActionListener(e -> switchPanel(mainPanel, "BambiDetails"));
        auroraButton.addActionListener(e -> switchPanel(mainPanel, "AuroraDetails"));
        lotsoButton.addActionListener(e -> switchPanel(mainPanel, "LotsoDetails"));
        juliButton.addActionListener(e -> switchPanel(mainPanel, "JuliDetails"));

        //Cat Adoption Panel
        JPanel catAdoptionPanel = new JPanel(new BorderLayout());
        JLabel catHeaderLabel = new JLabel("Cats for Adoption", SwingConstants.CENTER);
        catHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        catAdoptionPanel.add(catHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel2 = new JPanel(new BorderLayout());
        wrapperPanel2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelCat = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images
        JPanel secondRowPanelCat = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Second row of images

        // Load and scale the images
        ImageIcon chicoIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\cat-chico.jpeg");
        Image scaledChico = chicoIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledChicoIcon = new ImageIcon(scaledChico);

        ImageIcon dunkinIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\cat-dunkin.jpeg");
        Image scaledDunkin = dunkinIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledDunkinIcon = new ImageIcon(scaledDunkin);

        ImageIcon kikayIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\cat-kikay.jpeg");
        Image scaledKikay = kikayIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledKikayIcon = new ImageIcon(scaledKikay);

        ImageIcon maraIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\cat-mara.jpeg");
        Image scaledMara = maraIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledMaraIcon = new ImageIcon(scaledMara);

        ImageIcon ramonIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\cat-ramon.jpeg");
        Image scaledRamon = ramonIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledRamonIcon = new ImageIcon(scaledRamon);

        ImageIcon nanaIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\cat-nana.jpeg");
        Image scaledNana = nanaIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledNanaIcon = new ImageIcon(scaledNana);

        // Create labels for each image
        JLabel chicoLabel = new JLabel(scaledChicoIcon, SwingConstants.CENTER);
        JLabel dunkinLabel = new JLabel(scaledDunkinIcon, SwingConstants.CENTER);
        JLabel kikayLabel = new JLabel(scaledKikayIcon, SwingConstants.CENTER);
        JLabel maraLabel = new JLabel(scaledMaraIcon, SwingConstants.CENTER);
        JLabel ramonLabel = new JLabel(scaledRamonIcon, SwingConstants.CENTER);
        JLabel nanaLabel = new JLabel(scaledNanaIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel chicoDescription = new JLabel("<html><center>ID: 2023-0007<br>Name: Chico<br>Age: 7<br>Sex: Male<br>Breed: Domestic Short Hair</center></html>", SwingConstants.CENTER);
        JLabel dunkinDescription = new JLabel("<html><center>ID: 2023-0008<br>Name: Dunkin<br>Age: 6<br>Sex: Male<br>Breed: Domestic Short<br>Hair</center></html>", SwingConstants.CENTER);
        JLabel kikayDescription = new JLabel("<html><center>ID: 2023-0009<br>Name: Kikay<br>Age: 15<br>Sex: Female<br>Breed: Domestic Short<br>Hair</center></html>", SwingConstants.CENTER);
        JLabel maraDescription = new JLabel("<html><center>ID: 2023-0010<br>Name: Mara<br>Age: 4<br>Sex: Female<br>Breed: Domestic Short<br>Hair</center></html>", SwingConstants.CENTER);
        JLabel ramonDescription = new JLabel("<html><center>ID: 2023-0011<br>Name: Ramon<br>Age: 5<br>Sex: Male<br>Breed: Domestic Short<br>Hair</center></html>", SwingConstants.CENTER);
        JLabel nanaDescription = new JLabel("<html><center>ID: 2023-0012<br>Name: Nana<br>Age: 8<br>Sex: Female<br>Breed: Mixed Breed<br>- Mixed Breed</center></html>", SwingConstants.CENTER);

        Font descriptionFont1 = new Font("Arial", Font.BOLD, 22);
        chicoDescription.setFont(descriptionFont1);
        dunkinDescription.setFont(descriptionFont1);
        kikayDescription.setFont(descriptionFont1);
        maraDescription.setFont(descriptionFont1);
        ramonDescription.setFont(descriptionFont1);
        nanaDescription.setFont(descriptionFont1);

        // Create "See More" buttons
        JButton chicoButton = new JButton("See More");
        JButton dunkinButton = new JButton("See More");
        JButton kikayButton = new JButton("See More");
        JButton maraButton = new JButton("See More");
        JButton ramonButton = new JButton("See More");
        JButton nanaButton = new JButton("See More");

        Dimension buttonSize1 = new Dimension(40, 40); // Example size: 120px width, 40px height
        chicoButton.setPreferredSize(buttonSize1);
        dunkinButton.setPreferredSize(buttonSize1);
        kikayButton.setPreferredSize(buttonSize1);
        maraButton.setPreferredSize(buttonSize1);
        ramonButton.setPreferredSize(buttonSize1);
        nanaButton.setPreferredSize(buttonSize1);

        // Create a panel for each dog (image + description + button)
        JPanel chicoPanel = new JPanel(new BorderLayout());
        chicoPanel.add(chicoLabel, BorderLayout.NORTH); // Image at the top
        chicoPanel.add(chicoDescription, BorderLayout.CENTER); // Description in the middle
        chicoPanel.add(chicoButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel dunkinPanel = new JPanel(new BorderLayout());
        dunkinPanel.add(dunkinLabel, BorderLayout.NORTH);
        dunkinPanel.add(dunkinDescription, BorderLayout.CENTER);
        dunkinPanel.add(dunkinButton, BorderLayout.SOUTH);

        JPanel kikayPanel = new JPanel(new BorderLayout());
        kikayPanel.add(kikayLabel, BorderLayout.NORTH);
        kikayPanel.add(kikayDescription, BorderLayout.CENTER);
        kikayPanel.add(kikayButton, BorderLayout.SOUTH);

        JPanel maraPanel = new JPanel(new BorderLayout());
        maraPanel.add(maraLabel, BorderLayout.NORTH); // Image at the top
        maraPanel.add(maraDescription, BorderLayout.CENTER); // Description in the middle
        maraPanel.add(maraButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel ramonPanel = new JPanel(new BorderLayout());
        ramonPanel.add(ramonLabel, BorderLayout.NORTH);
        ramonPanel.add(ramonDescription, BorderLayout.CENTER);
        ramonPanel.add(ramonButton, BorderLayout.SOUTH);

        JPanel nanaPanel = new JPanel(new BorderLayout());
        nanaPanel.add(nanaLabel, BorderLayout.NORTH);
        nanaPanel.add(nanaDescription, BorderLayout.CENTER);
        nanaPanel.add(nanaButton, BorderLayout.SOUTH);

        firstRowPanelCat.add(chicoPanel);
        firstRowPanelCat.add(dunkinPanel);
        firstRowPanelCat.add(kikayPanel);

        secondRowPanelCat.add(maraPanel);
        secondRowPanelCat.add(ramonPanel);
        secondRowPanelCat.add(nanaPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel3 = new JPanel();
        mainPanel3.setLayout(new BoxLayout(mainPanel3, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel3.add(firstRowPanelCat); // Add the first row
        mainPanel3.add(secondRowPanelCat); // Add the second row

        scrollPane2.setViewportView(mainPanel3);

        // Add the image row panel to the main panel
        catAdoptionPanel.add(scrollPane2, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        chicoButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));
        dunkinButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Rombu"));
        kikayButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bambi"));
        maraButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));
        ramonButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Rombu"));
        nanaButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bambi"));


        //Bird Adoption Panel
        JPanel birdAdoptionPanel = new JPanel(new BorderLayout());
        JLabel birdHeaderLabel = new JLabel("Birds for Adoption", SwingConstants.CENTER);
        birdHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        birdAdoptionPanel.add(birdHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel3 = new JPanel(new BorderLayout());
        wrapperPanel3.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelBird = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images
        JPanel secondRowPanelBird = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Second row of images

        // Load and scale the images
        ImageIcon angelaIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\bird-angela.png");
        Image scaledAngela = angelaIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledAngelaIcon = new ImageIcon(scaledAngela);

        ImageIcon charlieIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\bird-charlie.png");
        Image scaledCharlie = charlieIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledCharlieIcon = new ImageIcon(scaledCharlie);

        ImageIcon cranberryIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\bird-cranberry.jpg");
        Image scaledCranberry = cranberryIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledCranberryIcon = new ImageIcon(scaledCranberry);

        ImageIcon loraxIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\bird-lorax.png");
        Image scaledLorax = loraxIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledLoraxIcon = new ImageIcon(scaledLorax);

        // Create labels for each image
        JLabel angelaLabel = new JLabel(scaledAngelaIcon, SwingConstants.CENTER);
        JLabel charlieLabel = new JLabel(scaledCharlieIcon, SwingConstants.CENTER);
        JLabel cranberryLabel = new JLabel(scaledCranberryIcon, SwingConstants.CENTER);
        JLabel loraxLabel = new JLabel(scaledLoraxIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel angelaDescription = new JLabel("<html><center>ID: 2023-00013<br>Name: Angela<br>Age: 7<br>Sex: Male<br>Breed: Umbrella Cockatoo</center></html>", SwingConstants.CENTER);
        JLabel charlieDescription = new JLabel("<html><center>ID: 2023-0014<br>Name: Charlie<br>Age: 6<br>Sex: Male<br>Breed: Cockatiel</center></html>", SwingConstants.CENTER);
        JLabel cranberryDescription = new JLabel("<html><center>ID: 2023-0015<br>Name: Cranberry<br>Age: 15<br>Sex: Female<br>Breed: Scarlet Macaw</center></html>", SwingConstants.CENTER);
        JLabel loraxDescription = new JLabel("<html><center>ID: 2023-0016<br>Name: Lorax<br>Age: 4<br>Sex: Female<br>Breed: Sun Conure</center></html>", SwingConstants.CENTER);
        
        Font descriptionFont2 = new Font("Arial", Font.BOLD, 22);
        angelaDescription.setFont(descriptionFont2);
        charlieDescription.setFont(descriptionFont2);
        cranberryDescription.setFont(descriptionFont2);
        loraxDescription.setFont(descriptionFont2);

        // Create "See More" buttons
        JButton angelaButton = new JButton("See More");
        JButton charlieButton = new JButton("See More");
        JButton cranberryButton = new JButton("See More");
        JButton loraxButton = new JButton("See More");

        Dimension buttonSize2 = new Dimension(40, 40); // Example size: 120px width, 40px height
        angelaButton.setPreferredSize(buttonSize2);
        charlieButton.setPreferredSize(buttonSize2);
        cranberryButton.setPreferredSize(buttonSize2);
        loraxButton.setPreferredSize(buttonSize2);

        // Create a panel for each dog (image + description + button)
        JPanel angelaPanel = new JPanel(new BorderLayout());
        angelaPanel.add(angelaLabel, BorderLayout.NORTH); // Image at the top
        angelaPanel.add(angelaDescription, BorderLayout.CENTER); // Description in the middle
        angelaPanel.add(angelaButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel charliePanel = new JPanel(new BorderLayout());
        charliePanel.add(charlieLabel, BorderLayout.NORTH);
        charliePanel.add(charlieDescription, BorderLayout.CENTER);
        charliePanel.add(charlieButton, BorderLayout.SOUTH);

        JPanel cranberryPanel = new JPanel(new BorderLayout());
        cranberryPanel.add(cranberryLabel, BorderLayout.NORTH);
        cranberryPanel.add(cranberryDescription, BorderLayout.CENTER);
        cranberryPanel.add(cranberryButton, BorderLayout.SOUTH);

        JPanel loraxPanel = new JPanel(new BorderLayout());
        loraxPanel.add(loraxLabel, BorderLayout.NORTH); // Image at the top
        loraxPanel.add(loraxDescription, BorderLayout.CENTER); // Description in the middle
        loraxPanel.add(loraxButton, BorderLayout.SOUTH); // Button at the bottom

        firstRowPanelBird.add(angelaPanel);
        firstRowPanelBird.add(charliePanel);
        firstRowPanelBird.add(cranberryPanel);

        secondRowPanelBird.add(loraxPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel4 = new JPanel();
        mainPanel4.setLayout(new BoxLayout(mainPanel4, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel4.add(firstRowPanelBird); // Add the first row
        mainPanel4.add(secondRowPanelBird); // Add the second row

        scrollPane3.setViewportView(mainPanel4);

        // Add the image row panel to the main panel
        birdAdoptionPanel.add(scrollPane3, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        angelaButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));
        charlieButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Rombu"));
        cranberryButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bambi"));
        loraxButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));

        //Fish Adoption Panel
        JPanel fishAdoptionPanel = new JPanel(new BorderLayout());
        JLabel fishHeaderLabel = new JLabel("Fishes for Adoption", SwingConstants.CENTER);
        fishHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        fishAdoptionPanel.add(fishHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel4 = new JPanel(new BorderLayout());
        wrapperPanel4.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelFish = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images
        JPanel secondRowPanelFish = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Second row of images

        // Load and scale the images
        ImageIcon brendaIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\fish-brenda.jpg");
        Image scaledBrenda = brendaIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledBrendaIcon = new ImageIcon(scaledBrenda);

        ImageIcon haruIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\fish-haru.jpg");
        Image scaledHaru = haruIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledHaruIcon = new ImageIcon(scaledHaru);

        ImageIcon simoneIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\fish-simone.jpg");
        Image scaledSimone = simoneIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledSimoneIcon = new ImageIcon(scaledSimone);

        ImageIcon toritulaIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\fish-tori-tula.jpg");
        Image scaledToritula = toritulaIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledToritulaIcon = new ImageIcon(scaledToritula);

        ImageIcon kaliIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\fish-kali.jpg");
        Image scaledKali = kaliIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledKaliIcon = new ImageIcon(scaledKali);

        // Create labels for each image
        JLabel brendaLabel = new JLabel(scaledBrendaIcon, SwingConstants.CENTER);
        JLabel haruLabel = new JLabel(scaledHaruIcon, SwingConstants.CENTER);
        JLabel simoneLabel = new JLabel(scaledSimoneIcon, SwingConstants.CENTER);
        JLabel toritulaLabel = new JLabel(scaledToritulaIcon, SwingConstants.CENTER);
        JLabel kaliLabel = new JLabel(scaledKaliIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel brendaDescription = new JLabel("<html><center>ID: 2023-00017<br>Name: Brenda<br>Age: 8<br>Sex: Female<br>Breed: Oranda<br>Goldfish</center></html>", SwingConstants.CENTER);
        JLabel haruDescription = new JLabel("<html><center>ID: 2023-0018<br>Name: Haru<br>Age: 2<br>Sex: Male<br>Breed: Japanese Blue<br>Swordtail Guppy</center></html>", SwingConstants.CENTER);
        JLabel simoneDescription = new JLabel("<html><center>ID: 2023-0019<br>Name: Simone<br>Age: 1<br>Sex: Female<br>Breed: Siamese<br>Fighting Fish</center></html>", SwingConstants.CENTER);
        JLabel toritulaDescription = new JLabel("<html><center>ID: 2023-0020<br>Name: Tori & Tula<br>Age: 7<br>Sex: Female<br>Breed: Ocellaris Clownfish</center></html>", SwingConstants.CENTER);
        JLabel kaliDescription = new JLabel("<html><center>ID: 2023-0021<br>Name: Kali<br>Age: 3<br>Sex: Female<br>Breed: Kuhli Loach</center></html>", SwingConstants.CENTER);


        Font descriptionFont3 = new Font("Arial", Font.BOLD, 22);
        brendaDescription.setFont(descriptionFont3);
        haruDescription.setFont(descriptionFont3);
        simoneDescription.setFont(descriptionFont3);
        toritulaDescription.setFont(descriptionFont3);
        kaliDescription.setFont(descriptionFont3);

        // Create "See More" buttons
        JButton brendaButton = new JButton("See More");
        JButton haruButton = new JButton("See More");
        JButton simoneButton = new JButton("See More");
        JButton toritulaButton = new JButton("See More");
        JButton kaliButton = new JButton("See More");

        Dimension buttonSize3 = new Dimension(40, 40); // Example size: 120px width, 40px height
        brendaButton.setPreferredSize(buttonSize3);
        haruButton.setPreferredSize(buttonSize3);
        simoneButton.setPreferredSize(buttonSize3);
        toritulaButton.setPreferredSize(buttonSize3);
        kaliButton.setPreferredSize(buttonSize3);

        // Create a panel for each dog (image + description + button)
        JPanel brendaPanel = new JPanel(new BorderLayout());
        brendaPanel.add(brendaLabel, BorderLayout.NORTH); // Image at the top
        brendaPanel.add(brendaDescription, BorderLayout.CENTER); // Description in the middle
        brendaPanel.add(brendaButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel haruPanel = new JPanel(new BorderLayout());
        haruPanel.add(haruLabel, BorderLayout.NORTH);
        haruPanel.add(haruDescription, BorderLayout.CENTER);
        haruPanel.add(haruButton, BorderLayout.SOUTH);

        JPanel simonePanel = new JPanel(new BorderLayout());
        simonePanel.add(simoneLabel, BorderLayout.NORTH);
        simonePanel.add(simoneDescription, BorderLayout.CENTER);
        simonePanel.add(simoneButton, BorderLayout.SOUTH);

        JPanel toritulaPanel = new JPanel(new BorderLayout());
        toritulaPanel.add(toritulaLabel, BorderLayout.NORTH); // Image at the top
        toritulaPanel.add(toritulaDescription, BorderLayout.CENTER); // Description in the middle
        toritulaPanel.add(toritulaButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel kaliPanel = new JPanel(new BorderLayout());
        kaliPanel.add(kaliLabel, BorderLayout.NORTH); // Image at the top
        kaliPanel.add(kaliDescription, BorderLayout.CENTER); // Description in the middle
        kaliPanel.add(kaliButton, BorderLayout.SOUTH); // Button at the bottom

        firstRowPanelFish.add(brendaPanel);
        firstRowPanelFish.add(haruPanel);
        firstRowPanelFish.add(simonePanel);

        secondRowPanelFish.add(toritulaPanel);
        secondRowPanelFish.add(kaliPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane4 = new JScrollPane();
        scrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel5 = new JPanel();
        mainPanel5.setLayout(new BoxLayout(mainPanel5, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel5.add(firstRowPanelFish); // Add the first row
        mainPanel5.add(secondRowPanelFish); // Add the second row

        scrollPane4.setViewportView(mainPanel5);

        // Add the image row panel to the main panel
        fishAdoptionPanel.add(scrollPane4, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        brendaButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));
        haruButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Rombu"));
        simoneButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bambi"));
        toritulaButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));
        kaliButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));

        //Rabbit Adoption Panel
        JPanel rabbitAdoptionPanel = new JPanel(new BorderLayout());
        JLabel rabbitHeaderLabel = new JLabel("Rabbits for Adoption", SwingConstants.CENTER);
        rabbitHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        rabbitAdoptionPanel.add(rabbitHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel5 = new JPanel(new BorderLayout());
        wrapperPanel5.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelRabbit = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images
        JPanel secondRowPanelRabbit = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Second row of images

        // Load and scale the images
        ImageIcon dewberryIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\rabbit-dewberry.png");
        Image scaledDewberry = dewberryIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledDewberryIcon = new ImageIcon(scaledDewberry);

        ImageIcon jamalIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\rabbit-jamal.png");
        Image scaledJamal = jamalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledJamalIcon = new ImageIcon(scaledJamal);

        ImageIcon juniaIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\rabbit-junia.png");
        Image scaledJunia = juniaIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledJuniaIcon = new ImageIcon(scaledJunia);

        ImageIcon justinIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\rabbit-justin.png");
        Image scaledJustin = justinIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledJustinIcon = new ImageIcon(scaledJustin);

        ImageIcon mordecaiIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\rabbit-mordecai.png");
        Image scaledMordecai = mordecaiIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledMordecaiIcon = new ImageIcon(scaledMordecai);

        ImageIcon noliIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\rabbit-noli.png");
        Image scaledNoli = noliIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledNoliIcon = new ImageIcon(scaledNoli);

        // Create labels for each image
        JLabel dewberryLabel = new JLabel(scaledDewberryIcon, SwingConstants.CENTER);
        JLabel jamalLabel = new JLabel(scaledJamalIcon, SwingConstants.CENTER);
        JLabel juniaLabel = new JLabel(scaledJuniaIcon, SwingConstants.CENTER);
        JLabel justinLabel = new JLabel(scaledJustinIcon, SwingConstants.CENTER);
        JLabel mordecaiLabel = new JLabel(scaledMordecaiIcon, SwingConstants.CENTER);
        JLabel noliLabel = new JLabel(scaledNoliIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel dewberryDescription = new JLabel("<html><center>ID: 2023-00022<br>Name: Dewberry<br>Age: 4<br>Sex: Female<br>Breed: Rhinelander</center></html>", SwingConstants.CENTER);
        JLabel jamalDescription = new JLabel("<html><center>ID: 2023-0023<br>Name: Jamal<br>Age: 7<br>Sex: Male<br>Breed: Lionhead</center></html>", SwingConstants.CENTER);
        JLabel juniaDescription = new JLabel("<html><center>ID: 2023-0024<br>Name: Junia<br>Age: 5<br>Sex: Female<br>Breed: Harlequin</center></html>", SwingConstants.CENTER);
        JLabel justinDescription = new JLabel("<html><center>ID: 2023-0025<br>Name: Justin<br>Age: 5<br>Sex: Male<br>Breed: New Zealand</center></html>", SwingConstants.CENTER);
        JLabel mordecaiDescription = new JLabel("<html><center>ID: 2023-0026<br>Name: Mordecai<br>Age: 1<br>Sex: Male<br>Breed: Lionhead</center></html>", SwingConstants.CENTER);
        JLabel noliDescription = new JLabel("<html><center>ID: 2023-0027<br>Name: Noli<br>Age: 2<br>Sex: Female<br>Breed: Rhinelander</center></html>", SwingConstants.CENTER);


        Font descriptionFont4 = new Font("Arial", Font.BOLD, 22);
        dewberryDescription.setFont(descriptionFont4);
        jamalDescription.setFont(descriptionFont4);
        juniaDescription.setFont(descriptionFont4);
        justinDescription.setFont(descriptionFont4);
        mordecaiDescription.setFont(descriptionFont4);
        noliDescription.setFont(descriptionFont4);

        // Create "See More" buttons
        JButton dewberryButton = new JButton("See More");
        JButton jamalButton = new JButton("See More");
        JButton juniaButton = new JButton("See More");
        JButton justinButton = new JButton("See More");
        JButton mordecaiButton = new JButton("See More");
        JButton noliButton = new JButton("See More");

        Dimension buttonSize4 = new Dimension(40, 40); // Example size: 120px width, 40px height
        dewberryButton.setPreferredSize(buttonSize4);
        jamalButton.setPreferredSize(buttonSize4);
        juniaButton.setPreferredSize(buttonSize4);
        justinButton.setPreferredSize(buttonSize4);
        mordecaiButton.setPreferredSize(buttonSize4);
        noliButton.setPreferredSize(buttonSize4);

        // Create a panel for each dog (image + description + button)
        JPanel dewberryPanel = new JPanel(new BorderLayout());
        dewberryPanel.add(dewberryLabel, BorderLayout.NORTH); // Image at the top
        dewberryPanel.add(dewberryDescription, BorderLayout.CENTER); // Description in the middle
        dewberryPanel.add(dewberryButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel jamalPanel = new JPanel(new BorderLayout());
        jamalPanel.add(jamalLabel, BorderLayout.NORTH);
        jamalPanel.add(jamalDescription, BorderLayout.CENTER);
        jamalPanel.add(jamalButton, BorderLayout.SOUTH);

        JPanel juniaPanel = new JPanel(new BorderLayout());
        juniaPanel.add(juniaLabel, BorderLayout.NORTH);
        juniaPanel.add(juniaDescription, BorderLayout.CENTER);
        juniaPanel.add(juniaButton, BorderLayout.SOUTH);

        JPanel justinPanel = new JPanel(new BorderLayout());
        justinPanel.add(justinLabel, BorderLayout.NORTH); // Image at the top
        justinPanel.add(justinDescription, BorderLayout.CENTER); // Description in the middle
        justinPanel.add(justinButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel mordecaiPanel = new JPanel(new BorderLayout());
        mordecaiPanel.add(mordecaiLabel, BorderLayout.NORTH); // Image at the top
        mordecaiPanel.add(mordecaiDescription, BorderLayout.CENTER); // Description in the middle
        mordecaiPanel.add(mordecaiButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel noliPanel = new JPanel(new BorderLayout());
        noliPanel.add(noliLabel, BorderLayout.NORTH); // Image at the top
        noliPanel.add(noliDescription, BorderLayout.CENTER); // Description in the middle
        noliPanel.add(noliButton, BorderLayout.SOUTH); // Button at the bottom

        firstRowPanelRabbit.add(dewberryPanel);
        firstRowPanelRabbit.add(jamalPanel);
        firstRowPanelRabbit.add(juniaPanel);

        secondRowPanelRabbit.add(justinPanel);
        secondRowPanelRabbit.add(mordecaiPanel);
        secondRowPanelRabbit.add(noliPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane5 = new JScrollPane();
        scrollPane5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane5.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel6 = new JPanel();
        mainPanel6.setLayout(new BoxLayout(mainPanel6, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel6.add(firstRowPanelRabbit); // Add the first row
        mainPanel6.add(secondRowPanelRabbit); // Add the second row

        scrollPane5.setViewportView(mainPanel6);

        // Add the image row panel to the main panel
        rabbitAdoptionPanel.add(scrollPane5, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        dewberryButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));
        jamalButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Rombu"));
        juniaButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bambi"));
        justinButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));
        mordecaiButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));
        noliButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Koko"));

        //Snake Adoption Panel
        JPanel snakeAdoptionPanel = new JPanel(new BorderLayout());
        JLabel snakeHeaderLabel = new JLabel("Snakes for Adoption", SwingConstants.CENTER);
        snakeHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        snakeAdoptionPanel.add(snakeHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel6 = new JPanel(new BorderLayout());
        wrapperPanel6.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelSnake = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images
        JPanel secondRowPanelSnake = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Second row of images

        // Load and scale the images
        ImageIcon bellaIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\snake-bella.png");
        Image scaledBella = bellaIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledBellaIcon = new ImageIcon(scaledBella);

        ImageIcon tanIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\snake-tan.png");
        Image scaledTan = tanIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledTanIcon = new ImageIcon(scaledTan);

        // Create labels for each image
        JLabel bellaLabel = new JLabel(scaledBellaIcon, SwingConstants.CENTER);
        JLabel tanLabel = new JLabel(scaledTanIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel bellaDescription = new JLabel("<html><center>ID: 2023-00028<br>Name: Bella<br>Age: 2<br>Sex: Female<br>Breed: Corn Snake</center></html>", SwingConstants.CENTER);
        JLabel tanDescription = new JLabel("<html><center>ID: 2023-0029<br>Name: Tan<br>Age: 4<br>Sex: Male<br>Breed: Ball Python</center></html>", SwingConstants.CENTER);

        Font descriptionFont5 = new Font("Arial", Font.BOLD, 22);
        bellaDescription.setFont(descriptionFont5);
        tanDescription.setFont(descriptionFont5);

        // Create "See More" buttons
        JButton bellaButton = new JButton("See More");
        JButton tanButton = new JButton("See More");

        Dimension buttonSize5 = new Dimension(40, 40); // Example size: 120px width, 40px height
        bellaButton.setPreferredSize(buttonSize5);
        tanButton.setPreferredSize(buttonSize5);

        // Create a panel for each dog (image + description + button)
        JPanel bellaPanel = new JPanel(new BorderLayout());
        bellaPanel.add(bellaLabel, BorderLayout.NORTH); // Image at the top
        bellaPanel.add(bellaDescription, BorderLayout.CENTER); // Description in the middle
        bellaPanel.add(bellaButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel tanPanel = new JPanel(new BorderLayout());
        tanPanel.add(tanLabel, BorderLayout.NORTH);
        tanPanel.add(tanDescription, BorderLayout.CENTER);
        tanPanel.add(tanButton, BorderLayout.SOUTH);

        firstRowPanelSnake.add(bellaPanel);
        firstRowPanelSnake.add(tanPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane6 = new JScrollPane();
        scrollPane6.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane6.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel7 = new JPanel();
        mainPanel7.setLayout(new BoxLayout(mainPanel7, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel7.add(firstRowPanelSnake); // Add the first row

        scrollPane6.setViewportView(mainPanel7);

        // Add the image row panel to the main panel
        snakeAdoptionPanel.add(scrollPane6, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        bellaButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bella"));
        tanButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Tan"));

        //Ferret Adoption Panel
        JPanel ferretAdoptionPanel = new JPanel(new BorderLayout());
        JLabel ferretHeaderLabel = new JLabel("Ferrets for Adoption", SwingConstants.CENTER);
        ferretHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        ferretAdoptionPanel.add(ferretHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel7 = new JPanel(new BorderLayout());
        wrapperPanel7.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelFerret = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images

        // Load and scale the images
        ImageIcon robertIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\ferret-robert.jpg");
        Image scaledRobert = robertIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledRobertIcon = new ImageIcon(scaledRobert);

        ImageIcon bridgetIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\ferret-bridget.jpg");
        Image scaledBridget = bridgetIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledBridgetIcon = new ImageIcon(scaledBridget);

        ImageIcon biscuitIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\ferret-biscuit.jpg");
        Image scaledBiscuit = biscuitIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledBiscuitIcon = new ImageIcon(scaledBiscuit);

        // Create labels for each image
        JLabel robertLabel = new JLabel(scaledRobertIcon, SwingConstants.CENTER);
        JLabel bridgetLabel = new JLabel(scaledBridgetIcon, SwingConstants.CENTER);
        JLabel biscuitLabel = new JLabel(scaledBiscuitIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel robertDescription = new JLabel("<html><center>ID: 2023-00030<br>Name: Robert<br>Age: 6<br>Sex: Male<br>Breed: Sable Ferret</center></html>", SwingConstants.CENTER);
        JLabel bridgetDescription = new JLabel("<html><center>ID: 2023-0031<br>Name: Bridget<br>Age: 8<br>Sex: Female<br>Breed: Sable Ferret</center></html>", SwingConstants.CENTER);
        JLabel biscuitDescription = new JLabel("<html><center>ID: 2023-0032<br>Name: Biscuit<br>Age: 5<br>Sex: Male<br>Breed: Light Sable Ferret</center></html>", SwingConstants.CENTER);

        Font descriptionFont6 = new Font("Arial", Font.BOLD, 22);
        robertDescription.setFont(descriptionFont6);
        bridgetDescription.setFont(descriptionFont6);
        biscuitDescription.setFont(descriptionFont6);

        // Create "See More" buttons
        JButton robertButton = new JButton("See More");
        JButton bridgetButton = new JButton("See More");
        JButton biscuitButton = new JButton("See More");

        Dimension buttonSize6 = new Dimension(40, 40); // Example size: 120px width, 40px height
        robertButton.setPreferredSize(buttonSize6);
        bridgetButton.setPreferredSize(buttonSize6);
        biscuitButton.setPreferredSize(buttonSize6);

        // Create a panel for each dog (image + description + button)
        JPanel robertPanel = new JPanel(new BorderLayout());
        robertPanel.add(robertLabel, BorderLayout.NORTH); // Image at the top
        robertPanel.add(robertDescription, BorderLayout.CENTER); // Description in the middle
        robertPanel.add(robertButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel bridgetPanel = new JPanel(new BorderLayout());
        bridgetPanel.add(bridgetLabel, BorderLayout.NORTH);
        bridgetPanel.add(bridgetDescription, BorderLayout.CENTER);
        bridgetPanel.add(bridgetButton, BorderLayout.SOUTH);

        JPanel biscuitPanel = new JPanel(new BorderLayout());
        biscuitPanel.add(biscuitLabel, BorderLayout.NORTH);
        biscuitPanel.add(biscuitDescription, BorderLayout.CENTER);
        biscuitPanel.add(biscuitButton, BorderLayout.SOUTH);

        firstRowPanelFerret.add(robertPanel);
        firstRowPanelFerret.add(bridgetPanel);
        firstRowPanelFerret.add(biscuitPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane7 = new JScrollPane();
        scrollPane7.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane7.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel8 = new JPanel();
        mainPanel8.setLayout(new BoxLayout(mainPanel8, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel8.add(firstRowPanelFerret); // Add the first row

        scrollPane7.setViewportView(mainPanel8);

        // Add the image row panel to the main panel
        ferretAdoptionPanel.add(scrollPane7, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        robertButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Robert"));
        bridgetButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bridget"));
        biscuitButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Biscuit"));

        //Hamster Adoption Panel
        JPanel hamsterAdoptionPanel = new JPanel(new BorderLayout());
        JLabel hamsterHeaderLabel = new JLabel("Hamsters for Adoption", SwingConstants.CENTER);
        hamsterHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        hamsterAdoptionPanel.add(hamsterHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel8 = new JPanel(new BorderLayout());
        wrapperPanel8.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelHamster = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images
        JPanel secondRowPanelHamster = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Load and scale the images
        ImageIcon autumnIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\hamster-autumn.jpg");
        Image scaledAutumn = autumnIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledAutumnIcon = new ImageIcon(scaledAutumn);

        ImageIcon chachaIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\hamster-chacha.jpg");
        Image scaledChacha = chachaIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledChachaIcon = new ImageIcon(scaledChacha);

        ImageIcon jackjillIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\hamster-jack-jill.jpg");
        Image scaledJackjill = jackjillIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledJackjillIcon = new ImageIcon(scaledJackjill);

        ImageIcon oshinneIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\hamster-oshinne.jpg");
        Image scaledOshinne = oshinneIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledOshinneIcon = new ImageIcon(scaledOshinne);

        ImageIcon sausageIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\hamster-sausage.jpg");
        Image scaledSausage = sausageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledSausageIcon = new ImageIcon(scaledSausage);

        ImageIcon timmyIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\hamster-timmy.jpg");
        Image scaledTimmy = timmyIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledTimmyIcon = new ImageIcon(scaledTimmy);

        // Create labels for each image
        JLabel autumnLabel = new JLabel(scaledAutumnIcon, SwingConstants.CENTER);
        JLabel chachaLabel = new JLabel(scaledChachaIcon, SwingConstants.CENTER);
        JLabel jackjillLabel = new JLabel(scaledJackjillIcon, SwingConstants.CENTER);
        JLabel oshinneLabel = new JLabel(scaledOshinneIcon, SwingConstants.CENTER);
        JLabel sausageLabel = new JLabel(scaledSausageIcon, SwingConstants.CENTER);
        JLabel timmyLabel = new JLabel(scaledTimmyIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel autumnDescription = new JLabel("<html><center>ID: 2023-00030<br>Name: Autumn<br>Age: 6 months<br>Sex: Female<br>Breed: Campbells</center></html>", SwingConstants.CENTER);
        JLabel chachaDescription = new JLabel("<html><center>ID: 2023-0031<br>Name: Chacha<br>Age: 8 months<br>Sex: Female<br>Breed: Roborovski</center></html>", SwingConstants.CENTER);
        JLabel jackjillDescription = new JLabel("<html><center>ID: 2023-0032<br>Name: Jack & Jill<br>Age: 5 months<br>Sex: Male & Female<br>Breed: Campbells</center></html>", SwingConstants.CENTER);
        JLabel oshinneDescription = new JLabel("<html><center>ID: 2023-00030<br>Name: Oshinne<br>Age: 6 months<br>Sex: Female<br>Breed: Chinese Hamster</center></html>", SwingConstants.CENTER);
        JLabel sausageDescription = new JLabel("<html><center>ID: 2023-0031<br>Name: Sausage<br>Age: 8 months<br>Sex: Male<br>Breed: Syrian Hamster</center></html>", SwingConstants.CENTER);
        JLabel timmyDescription = new JLabel("<html><center>ID: 2023-0032<br>Name: Timmy<br>Age: 5 months<br>Sex: Male<br>Breed: Winterwhites</center></html>", SwingConstants.CENTER);

        Font descriptionFont7 = new Font("Arial", Font.BOLD, 22);
        autumnDescription.setFont(descriptionFont7);
        chachaDescription.setFont(descriptionFont7);
        jackjillDescription.setFont(descriptionFont7);
        oshinneDescription.setFont(descriptionFont7);
        sausageDescription.setFont(descriptionFont7);
        timmyDescription.setFont(descriptionFont7);

        // Create "See More" buttons
        JButton autumnButton = new JButton("See More");
        JButton chachaButton = new JButton("See More");
        JButton jackjillButton = new JButton("See More");
        JButton oshinneButton = new JButton("See More");
        JButton sausageButton = new JButton("See More");
        JButton timmyButton = new JButton("See More");

        Dimension buttonSize7 = new Dimension(40, 40); // Example size: 120px width, 40px height
        autumnButton.setPreferredSize(buttonSize7);
        chachaButton.setPreferredSize(buttonSize7);
        jackjillButton.setPreferredSize(buttonSize7);
        oshinneButton.setPreferredSize(buttonSize7);
        sausageButton.setPreferredSize(buttonSize7);
        timmyButton.setPreferredSize(buttonSize7);

        // Create a panel for each dog (image + description + button)
        JPanel autumnPanel = new JPanel(new BorderLayout());
        autumnPanel.add(autumnLabel, BorderLayout.NORTH); // Image at the top
        autumnPanel.add(autumnDescription, BorderLayout.CENTER); // Description in the middle
        autumnPanel.add(autumnButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel chachaPanel = new JPanel(new BorderLayout());
        chachaPanel.add(chachaLabel, BorderLayout.NORTH);
        chachaPanel.add(chachaDescription, BorderLayout.CENTER);
        chachaPanel.add(chachaButton, BorderLayout.SOUTH);

        JPanel jackjillPanel = new JPanel(new BorderLayout());
        jackjillPanel.add(jackjillLabel, BorderLayout.NORTH);
        jackjillPanel.add(jackjillDescription, BorderLayout.CENTER);
        jackjillPanel.add(jackjillButton, BorderLayout.SOUTH);

        JPanel oshinnePanel = new JPanel(new BorderLayout());
        oshinnePanel.add(oshinneLabel, BorderLayout.NORTH); // Image at the top
        oshinnePanel.add(oshinneDescription, BorderLayout.CENTER); // Description in the middle
        oshinnePanel.add(oshinneButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel sausagePanel = new JPanel(new BorderLayout());
        sausagePanel.add(sausageLabel, BorderLayout.NORTH);
        sausagePanel.add(sausageDescription, BorderLayout.CENTER);
        sausagePanel.add(sausageButton, BorderLayout.SOUTH);

        JPanel timmyPanel = new JPanel(new BorderLayout());
        timmyPanel.add(timmyLabel, BorderLayout.NORTH);
        timmyPanel.add(timmyDescription, BorderLayout.CENTER);
        timmyPanel.add(timmyButton, BorderLayout.SOUTH);

        firstRowPanelHamster.add(autumnPanel);
        firstRowPanelHamster.add(chachaPanel);
        firstRowPanelHamster.add(jackjillPanel);

        secondRowPanelHamster.add(oshinnePanel);
        secondRowPanelHamster.add(sausagePanel);
        secondRowPanelHamster.add(timmyPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane8 = new JScrollPane();
        scrollPane8.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane8.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel9 = new JPanel();
        mainPanel9.setLayout(new BoxLayout(mainPanel9, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel9.add(firstRowPanelHamster); // Add the first row
        mainPanel9.add(secondRowPanelHamster);

        scrollPane8.setViewportView(mainPanel9);

        // Add the image row panel to the main panel
        hamsterAdoptionPanel.add(scrollPane8, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        autumnButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Robert"));
        chachaButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bridget"));
        jackjillButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Biscuit"));
        oshinneButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Robert"));
        sausageButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Bridget"));
        timmyButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Biscuit"));

        //Turtle Adoption Panel
        JPanel turtleAdoptionPanel = new JPanel(new BorderLayout());
        JLabel turtleHeaderLabel = new JLabel("Turtles for Adoption", SwingConstants.CENTER);
        turtleHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        turtleAdoptionPanel.add(turtleHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel9 = new JPanel(new BorderLayout());
        wrapperPanel9.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelTurtle = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images

        // Load and scale the images
        ImageIcon tacoIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\turtle-taco.png");
        Image scaledTaco = tacoIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledTacoIcon = new ImageIcon(scaledTaco);

        ImageIcon taquitoIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\turtle-taquito.png");
        Image scaledTaquito = taquitoIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledTaquitoIcon = new ImageIcon(scaledTaquito);


        // Create labels for each image
        JLabel tacoLabel = new JLabel(scaledTacoIcon, SwingConstants.CENTER);
        JLabel taquitoLabel = new JLabel(scaledTaquitoIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel tacoDescription = new JLabel("<html><center>ID: 2023-00033<br>Name: Taco<br>Age: 6 months<br>Sex: Male<br>Breed: Green Sea<br>Turtle</center></html>", SwingConstants.CENTER);
        JLabel taquitoDescription = new JLabel("<html><center>ID: 2023-0034<br>Name: Taquito<br>Age: 6 months<br>Sex: Male<br>Breed: Green Sea<br>Turtle</center></html>", SwingConstants.CENTER);
        
        Font descriptionFont8 = new Font("Arial", Font.BOLD, 22);
        tacoDescription.setFont(descriptionFont8);
        taquitoDescription.setFont(descriptionFont8);

        // Create "See More" buttons
        JButton tacoButton = new JButton("See More");
        JButton taquitoButton = new JButton("See More");

        Dimension buttonSize8 = new Dimension(40, 40); // Example size: 120px width, 40px height
        tacoButton.setPreferredSize(buttonSize8);
        taquitoButton.setPreferredSize(buttonSize8);

        // Create a panel for each dog (image + description + button)
        JPanel tacoPanel = new JPanel(new BorderLayout());
        tacoPanel.add(tacoLabel, BorderLayout.NORTH); // Image at the top
        tacoPanel.add(tacoDescription, BorderLayout.CENTER); // Description in the middle
        tacoPanel.add(tacoButton, BorderLayout.SOUTH); // Button at the bottom

        JPanel taquitoPanel = new JPanel(new BorderLayout());
        taquitoPanel.add(taquitoLabel, BorderLayout.NORTH);
        taquitoPanel.add(taquitoDescription, BorderLayout.CENTER);
        taquitoPanel.add(taquitoButton, BorderLayout.SOUTH);

        firstRowPanelTurtle.add(tacoPanel);
        firstRowPanelTurtle.add(taquitoPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane9 = new JScrollPane();
        scrollPane9.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane9.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel10 = new JPanel();
        mainPanel10.setLayout(new BoxLayout(mainPanel10, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel10.add(firstRowPanelTurtle); // Add the first row

        scrollPane9.setViewportView(mainPanel10);

        // Add the image row panel to the main panel
        turtleAdoptionPanel.add(scrollPane9, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        tacoButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Taco"));
        taquitoButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Taquito"));

        //Lizard Adoption Panel
        JPanel lizardAdoptionPanel = new JPanel(new BorderLayout());
        JLabel lizardHeaderLabel = new JLabel("Lizards for Adoption", SwingConstants.CENTER);
        lizardHeaderLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        lizardAdoptionPanel.add(lizardHeaderLabel, BorderLayout.NORTH);

        JPanel wrapperPanel10 = new JPanel(new BorderLayout());
        wrapperPanel10.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add 20px padding at the top

        // Create a panel for images in rows
        JPanel firstRowPanelLizard = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // First row of images

        // Load and scale the images
        ImageIcon liloIcon = new ImageIcon("C:\\Users\\mcdeu\\Documents\\oop\\lizard-lilo.png");
        Image scaledLilo = liloIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledLiloIcon = new ImageIcon(scaledLilo);

        // Create labels for each image
        JLabel liloLabel = new JLabel(scaledLiloIcon, SwingConstants.CENTER);

        // Create description labels
        JLabel liloDescription = new JLabel("<html><center>ID: 2023-00035<br>Name: Lilo<br>Age: 1<br>Sex: Male<br>Breed: Leopard Gecko</center></html>", SwingConstants.CENTER);
        
        Font descriptionFont9 = new Font("Arial", Font.BOLD, 22);
        liloDescription.setFont(descriptionFont8);

        // Create "See More" buttons
        JButton liloButton = new JButton("See More");
        
        Dimension buttonSize9 = new Dimension(40, 40); // Example size: 120px width, 40px height
        liloButton.setPreferredSize(buttonSize9);

        // Create a panel for each dog (image + description + button)
        JPanel liloPanel = new JPanel(new BorderLayout());
        liloPanel.add(liloLabel, BorderLayout.NORTH); // Image at the top
        liloPanel.add(liloDescription, BorderLayout.CENTER); // Description in the middle
        liloPanel.add(liloButton, BorderLayout.SOUTH); // Button at the bottom

        firstRowPanelLizard.add(liloPanel);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane10 = new JScrollPane();
        scrollPane10.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane10.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel11 = new JPanel();
        mainPanel11.setLayout(new BoxLayout(mainPanel11, BoxLayout.Y_AXIS)); // Stack the rows vertically
        mainPanel11.add(firstRowPanelLizard); // Add the first row

        scrollPane10.setViewportView(mainPanel11);

        // Add the image row panel to the main panel
        lizardAdoptionPanel.add(scrollPane10, BorderLayout.CENTER);

        // Optionally, add action listeners to the buttons
        liloButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "More details about Taco"));

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

        mainPanel.add(kokoDetailPanel, "KokoDetails");
        mainPanel.add(rombuDetailPanel, "RombuDetails");
        mainPanel.add(bambiDetailPanel, "BambiDetails");
        mainPanel.add(auroraDetailPanel, "AuroraDetails");
        mainPanel.add(lotsoDetailPanel, "LotsoDetails");
        mainPanel.add(juliDetailPanel, "JuliDetails");
        
        
            panel.add(label, BorderLayout.CENTER);
            panel.add(backButton, BorderLayout.SOUTH);
            return panel;

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createDetailPanel(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Helvetica", Font.BOLD, 30));
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Assuming you want to go back to the dog adoption panel
            cardLayout.show(mainPanel, "dogAdoptionPanel");
        });
    
        panel.add(label, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }

    static class LoginPanel extends JPanel implements ActionListener {

        private JTextField userTextField;

        private JPasswordField passwordField;

        LoginPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            JLabel adoptLabel = new JLabel("ADOPTION SYSTEM LOG IN");
            adoptLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
            add(adoptLabel, gbc);

            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel userLabel = new JLabel("USERNAME:");
            add(userLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            userTextField = new JTextField(15);
            add(userTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel passwordLabel = new JLabel("PASSWORD:");
            add(passwordLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            passwordField = new JPasswordField(15);
            add(passwordField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            JCheckBox showPassword = new JCheckBox("Show Password");
            showPassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (showPassword.isSelected()) {
                        passwordField.setEchoChar((char) 0);
                    } else {
                        passwordField.setEchoChar('*');
                    }
                }
            });
            add(showPassword, gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.CENTER;
            JButton loginButton = new JButton("LOGIN");
            loginButton.addActionListener(this);
            add(loginButton, gbc);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String userText = userTextField.getText();
            String passText = new String(passwordField.getPassword());
            if (userText.equals("hatdog") && passText.equals("pussycat")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                showAvailablePetsPanel();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        }

        private void showAvailablePetsPanel() {
            JPanel availablePetsPanel = new JPanel(new BorderLayout());
        
            JPanel leftPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTHWEST;
        }
        
            String[] petTypes = {"Dog", "Cat", "Bird", "Fish", "Rabbit", "Snake", "Ferret", "Hamster", "Turtle", "Lizard"};
            for (String petType : petTypes) {
                JButton petButton = new JButton(petType);
                petButton.setPreferredSize(new Dimension(200, 50));
                gbc.insets.left = 20; // Padding on the left side
                leftPanel.add(petButton, gbc);
                gbc.gridy++;
        
                if (petType.equals("Dog")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "dogAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Cat")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "catAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Bird")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "birdAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Fish")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "fishAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Rabbit")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "rabbitAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Snake")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "snakeAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Ferret")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "ferretAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Hamster")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "hamsterAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Turtle")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "turtleAdoptionPanel");
                        }
                    });
                } else if (petType.equals("Lizard")) {
                    petButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(mainPanel, "lizardAdoptionPanel");
                        }
                    });
                }
            }
        
            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0)); // Add padding at the top to move the header down
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(10, 20, 10, 20); // Padding on top to match the buttons
            JLabel availablePetsHeader = new JLabel("Available Pets For Adoption");
            availablePetsHeader.setFont(new Font("Helvetica", Font.BOLD, 40));
            availablePetsHeader.setHorizontalAlignment(JLabel.CENTER);
            rightPanel.add(availablePetsHeader, BorderLayout.NORTH);
        
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 20, 10, 20); // Adjust top padding to move text up
        
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
            descriptionText.setPreferredSize(new Dimension(600, 300)); // Increase height to ensure all text is visible
        
            JPanel paragraphPanel = new JPanel(new BorderLayout());
            paragraphPanel.add(descriptionText, BorderLayout.NORTH); // Add description text to the top
        
            rightPanel.add(paragraphPanel, BorderLayout.CENTER);
        
            availablePetsPanel.add(leftPanel, BorderLayout.WEST);
            availablePetsPanel.add(rightPanel, BorderLayout.CENTER);
        
            mainPanel.add(availablePetsPanel, "availablePetsPanel");
            cardLayout.show(mainPanel, "availablePetsPanel");
        }
    }
}    