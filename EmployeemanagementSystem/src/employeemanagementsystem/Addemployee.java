/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class Addemployee extends javax.swing.JFrame {

    private JTextField tfname, tffname, tfsalary, tfaddress, tfphone, tfdesignation, tfnid, tfempId;
    private JComboBox<String> cbeducation;
    private JButton add, back;
    private JDateChooser dobChooser;

    public Addemployee() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Employee");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 30, 100, 25);
        add(lblName);

        tfname = new JTextField();
        tfname.setBounds(150, 30, 150, 25);
        add(tfname);

        JLabel lblFname = new JLabel("Father's Name:");
        lblFname.setBounds(30, 70, 100, 25);
        add(lblFname);

        tffname = new JTextField();
        tffname.setBounds(150, 70, 150, 25);
        add(tffname);

        JLabel lblDob = new JLabel("Date of Birth:");
        lblDob.setBounds(30, 110, 100, 25);
        add(lblDob);

        dobChooser = new JDateChooser();
        dobChooser.setBounds(150, 110, 150, 25);
        dobChooser.setDateFormatString("yyyy-MM-dd");
        add(dobChooser);

        JLabel lblSalary = new JLabel("Salary:");
        lblSalary.setBounds(30, 150, 100, 25);
        add(lblSalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(150, 150, 150, 25);
        add(tfsalary);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(30, 190, 100, 25);
        add(lblAddress);

        tfaddress = new JTextField();
        tfaddress.setBounds(150, 190, 150, 25);
        add(tfaddress);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(30, 230, 100, 25);
        add(lblPhone);

        tfphone = new JTextField();
        tfphone.setBounds(150, 230, 150, 25);
        add(tfphone);

        JLabel lblEducation = new JLabel("Education:");
        lblEducation.setBounds(30, 270, 100, 25);
        add(lblEducation);

        cbeducation = new JComboBox<>(new String[]{"BBA", "BA", "BSC", "B.COM", "MBA", "MA", "MSC", "PHD"});
        cbeducation.setBounds(150, 270, 150, 25);
        add(cbeducation);

        JLabel lblDesignation = new JLabel("Designation:");
        lblDesignation.setBounds(30, 310, 100, 25);
        add(lblDesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(150, 310, 150, 25);
        add(tfdesignation);

        JLabel lblNID = new JLabel("NID:");
        lblNID.setBounds(30, 350, 100, 25);
        add(lblNID);

        tfnid = new JTextField();
        tfnid.setBounds(150, 350, 150, 25);
        add(tfnid);

        JLabel lblEmpId = new JLabel("Emp ID:");
        lblEmpId.setBounds(30, 390, 100, 25);
        add(lblEmpId);

        tfempId = new JTextField();
        tfempId.setBounds(150, 390, 150, 25);
        add(tfempId);

        add = new JButton("Add Employee");
        add.setBounds(330, 100, 200, 30);
        add(add);

        back = new JButton("Back");
        back.setBounds(330, 140, 200, 30);
        add(back);

        back.addActionListener(evt -> {
            setVisible(false);
            new Home().setVisible(true);
        });

        add.addActionListener(evt -> addActionPerformed(evt));
    }

    private void addActionPerformed(ActionEvent evt) {
        String name = tfname.getText().trim();
        String fname = tffname.getText().trim();
        Date selectedDate = dobChooser.getDate();
        String salary = tfsalary.getText().trim();
        String address = tfaddress.getText().trim();
        String phone = tfphone.getText().trim();
        String education = (String) cbeducation.getSelectedItem();
        String designation = tfdesignation.getText().trim();
        String nid = tfnid.getText().trim();
        String empId = tfempId.getText().trim();

        // Validation
        if (name.isEmpty() || fname.isEmpty() || selectedDate == null || salary.isEmpty()
                || address.isEmpty() || phone.isEmpty() || designation.isEmpty()
                || nid.isEmpty() || empId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String dob = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

        try {
            Conn conn = new Conn();
            String query = String.format(
                    "INSERT INTO employee (name, fname, dob, salary, address, phone, education, designation, nid, empid) " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    name, fname, dob, salary, address, phone, education, designation, nid, empId
            );

            int result = conn.s.executeUpdate(query);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
                setVisible(false);
                new Home().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Addemployee().setVisible(true));
    }
}

