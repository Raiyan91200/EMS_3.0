/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

import javax.swing.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Home extends JFrame {

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JButton addBtn, viewBtn, updateBtn, deleteBtn;

    public Home() {
        initComponents();
    }

    private void initComponents() {
        // Initialize components
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        titleLabel = new JLabel("Welcome To The Dashboard");
        addBtn = new JButton("Add Employee Record");
        viewBtn = new JButton("View Employee Record");
        updateBtn = new JButton("Update Employee Record");
        deleteBtn = new JButton("Delete Employee Record");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard - Employee Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Panel Styling
        mainPanel.setBackground(new java.awt.Color(230, 230, 230));
        headerPanel.setBackground(new java.awt.Color(51, 153, 255));

        // Title Styling
        titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        titleLabel.setForeground(java.awt.Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.setPreferredSize(new java.awt.Dimension(600, 70));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(Box.createVerticalStrut(20));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(20));

        // Button Styling
        JButton[] buttons = {addBtn, viewBtn, updateBtn, deleteBtn};
        for (JButton btn : buttons) {
            btn.setBackground(new java.awt.Color(153, 153, 153));
            btn.setForeground(java.awt.Color.BLACK);
            btn.setPreferredSize(new java.awt.Dimension(200, 35));
        }

        // Action Listeners
        addBtn.addActionListener(e -> {
            setVisible(false);
            new Addemployee().setVisible(true);
        });

        viewBtn.addActionListener(e -> {
            setVisible(false);
            new ViewEmployee().setVisible(true);
        });

        updateBtn.addActionListener(e -> {
            setVisible(false);
            new ViewEmployee().setVisible(true); // Consider making UpdateEmployee.java separately
        });

        deleteBtn.addActionListener(e -> {
            setVisible(false);
            new RemoveEmployee().setVisible(true);
        });

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new java.awt.GridLayout(2, 2, 20, 20));
        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        mainPanel.setLayout(new java.awt.BorderLayout(20, 20));
        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);
        mainPanel.add(buttonPanel, java.awt.BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }

}

