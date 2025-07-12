/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

/**
 *
 * @author raiya
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Login extends JFrame implements ActionListener {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton loginBtn;

    public Login() {
        setTitle("Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 100, 25);
        add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(150, 50, 180, 25);
        add(tfUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 90, 100, 25);
        add(lblPassword);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(150, 90, 180, 25);
        add(pfPassword);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 140, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = tfUsername.getText().trim();
        String password = new String(pfPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Conn conn = new Conn();
            String sql = "SELECT password FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.c.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String hashedPasswordFromDB = rs.getString("password");
                String hashedInput = hashPassword(password);

                if (hashedPasswordFromDB.equals(hashedInput)) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    setVisible(false);
                    new Home().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "User not found!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

            pstmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes("UTF-8"));

            // Convert byte array to hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
