/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class RemoveEmployee extends JFrame {

    private Choice cemployeeId;
    private JLabel labName;
    private JLabel labDesignation;
    private JButton btnDelete, btnSearch, btnBack;

    public RemoveEmployee() {
        setTitle("Remove Employee");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        loadEmployeeIds();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 153, 255));
        JLabel headerLabel = new JLabel("Delete Record");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(204, 204, 204));

        JLabel lblSearchId = new JLabel("Search Id:");
        lblSearchId.setBounds(50, 40, 100, 25);
        formPanel.add(lblSearchId);

        cemployeeId = new Choice();
        cemployeeId.setBounds(180, 40, 150, 25);
        formPanel.add(cemployeeId);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(350, 40, 90, 25);
        formPanel.add(btnSearch);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 90, 100, 25);
        formPanel.add(lblName);

        labName = new JLabel(" ");
        labName.setBounds(180, 90, 200, 25);
        labName.setOpaque(true);
        labName.setBackground(Color.WHITE);
        formPanel.add(labName);

        JLabel lblDesignation = new JLabel("Designation:");
        lblDesignation.setBounds(50, 140, 100, 25);
        formPanel.add(lblDesignation);

        labDesignation = new JLabel(" ");
        labDesignation.setBounds(180, 140, 200, 25);
        labDesignation.setOpaque(true);
        labDesignation.setBackground(Color.WHITE);
        formPanel.add(labDesignation);

        btnDelete = new JButton("Delete Record");
        btnDelete.setBounds(100, 220, 130, 30);
        formPanel.add(btnDelete);

        btnBack = new JButton("Back");
        btnBack.setBounds(260, 220, 130, 30);
        formPanel.add(btnBack);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Button listeners
        btnSearch.addActionListener(e -> searchEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnBack.addActionListener(e -> goBack());
    }

    // Load employee IDs into the Choice dropdown
    private void loadEmployeeIds() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT empId FROM employee");
            cemployeeId.removeAll();
            while (rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading employee IDs: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Search employee details by selected empId
    private void searchEmployee() {
        String empId = cemployeeId.getSelectedItem();
        if (empId == null || empId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an Employee ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Conn c = new Conn();
            String query = "SELECT name, designation FROM employee WHERE empId = ?";
            PreparedStatement pst = c.c.prepareStatement(query);
            pst.setString(1, empId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                labName.setText(rs.getString("name"));
                labDesignation.setText(rs.getString("designation"));
            } else {
                labName.setText("Not found");
                labDesignation.setText("Not found");
                JOptionPane.showMessageDialog(this, "Employee not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error retrieving employee details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete employee record
    private void deleteEmployee() {
        String empId = cemployeeId.getSelectedItem();
        if (empId == null || empId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an Employee ID to delete.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete employee ID: " + empId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Conn c = new Conn();
            String query = "DELETE FROM employee WHERE empId = ?";
            PreparedStatement pst = c.c.prepareStatement(query);
            pst.setString(1, empId);

            int rowsAffected = pst.executeUpdate();
            pst.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
                // Refresh UI
                labName.setText(" ");
                labDesignation.setText(" ");
                loadEmployeeIds();
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found or already deleted.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Go back to home screen
    private void goBack() {
        setVisible(false);
        new Home().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RemoveEmployee().setVisible(true);
        });
    }
}

