/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

/**
 *
 * @author raiya
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * Welcome page for Employee Management System.
 * Navigates to the login screen.
 */
public class Start extends JFrame {

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel footerPanel;
    private JLabel titleLabel;
    private JButton continueButton;

    public Start() {
        initComponents();
    }

    private void initComponents() {
        // Panels
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        footerPanel = new JPanel();

        // Label
        titleLabel = new JLabel("Employee Management System");

        // Button
        continueButton = new JButton("Click here to continue...");

        // Main Window setup
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome - Employee Management System");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen

        // Styling
        mainPanel.setBackground(new java.awt.Color(230, 230, 230));
        headerPanel.setBackground(new java.awt.Color(0, 153, 204));
        footerPanel.setBackground(new java.awt.Color(0, 153, 204));

        titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 32));
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        continueButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        continueButton.addActionListener(e -> openLoginPage());

        // Layout: Header
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(Box.createVerticalStrut(30));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(30));

        // Layout: Footer
        footerPanel.setPreferredSize(new java.awt.Dimension(100, 50));

        // Layout: Main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(continueButton, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Set main content
        setContentPane(mainPanel);
    }

    private void openLoginPage() {
        this.setVisible(false);
        new Login().setVisible(true); // Login.java should be implemented
    }

    public static void main(String[] args) {
        // Optional: Set Nimbus Look & Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Failed to set look and feel.");
        }

        SwingUtilities.invokeLater(() -> {
            new Start().setVisible(true);
        });
    }
}
