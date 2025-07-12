/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateEmployee extends JFrame {
    String EmpId;

    JTextField tfname, tffname, tfsalary, tfaddress, tfphone, tfdesignation, tfnid;
    JDateChooser Dob;
    JComboBox<String> cbeducation;
    JLabel tfempId;
    JButton add, back;

    public UpdateEmployee(String EmpId) {
        this.EmpId = EmpId;
        setLayout(null);
        setTitle("Update Employee Details");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel heading = new JLabel("Update Employee Record");
        heading.setBounds(180, 10, 250, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(heading);

        JLabel lblempId = new JLabel("Emp ID:");
        lblempId.setBounds(50, 60, 100, 25);
        add(lblempId);

        tfempId = new JLabel();
        tfempId.setBounds(160, 60, 150, 25);
        add(tfempId);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(50, 100, 100, 25);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(160, 100, 150, 25);
        add(tfname);

        JLabel lblfname = new JLabel("Father's Name:");
        lblfname.setBounds(50, 140, 100, 25);
        add(lblfname);

        tffname = new JTextField();
        tffname.setBounds(160, 140, 150, 25);
        add(tffname);

        JLabel lbldob = new JLabel("Date of Birth:");
        lbldob.setBounds(50, 180, 100, 25);
        add(lbldob);

        Dob = new JDateChooser();
        Dob.setDateFormatString("yyyy-MM-dd");
        Dob.setBounds(160, 180, 150, 25);
        add(Dob);

        JLabel lblsalary = new JLabel("Salary:");
        lblsalary.setBounds(50, 220, 100, 25);
        add(lblsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(160, 220, 150, 25);
        add(tfsalary);

        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(50, 260, 100, 25);
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(160, 260, 150, 25);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone:");
        lblphone.setBounds(50, 300, 100, 25);
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(160, 300, 150, 25);
        add(tfphone);

        JLabel lbleducation = new JLabel("Education:");
        lbleducation.setBounds(50, 340, 100, 25);
        add(lbleducation);

        cbeducation = new JComboBox<>(new String[]{"BBA", "BA", "BSC", "B.COM", "MBA", "MA", "MSC", "PHD"});
        cbeducation.setBounds(160, 340, 150, 25);
        add(cbeducation);

        JLabel lbldesignation = new JLabel("Designation:");
        lbldesignation.setBounds(50, 380, 100, 25);
        add(lbldesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(160, 380, 150, 25);
        add(tfdesignation);

        JLabel lblnid = new JLabel("NID:");
        lblnid.setBounds(50, 420, 100, 25);
        add(lblnid);

        tfnid = new JTextField();
        tfnid.setBounds(160, 420, 150, 25);
        add(tfnid);

        add = new JButton("Update");
        add.setBounds(350, 420, 100, 25);
        add(add);

        back = new JButton("Back");
        back.setBounds(460, 420, 80, 25);
        add(back);

        back.addActionListener(e -> {
            setVisible(false);
            new Home().setVisible(true);
        });

        add.addActionListener(e -> handleUpdate());

        loadEmployeeData();

        setVisible(true);
    }

    private void loadEmployeeData() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee WHERE empId='" + EmpId + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                tfempId.setText(rs.getString("empId"));
                tfname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                Dob.setDate(rs.getDate("dob"));
                tfsalary.setText(rs.getString("salary"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                cbeducation.setSelectedItem(rs.getString("education"));
                tfdesignation.setText(rs.getString("designation"));
                tfnid.setText(rs.getString("nid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate() {
        String name = tfname.getText().trim();
        String fname = tffname.getText().trim();
        String dob = ((JTextField) Dob.getDateEditor().getUiComponent()).getText().trim();
        String salary = tfsalary.getText().trim();
        String address = tfaddress.getText().trim();
        String phone = tfphone.getText().trim();
        String education = (String) cbeducation.getSelectedItem();
        String designation = tfdesignation.getText().trim();
        String nid = tfnid.getText().trim();

        if (name.isEmpty() || fname.isEmpty() || dob.isEmpty() || salary.isEmpty() ||
            address.isEmpty() || phone.isEmpty() || designation.isEmpty() || nid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "UPDATE employee SET name='" + name + "', fname='" + fname +
                    "', dob='" + dob + "', salary='" + salary + "', address='" + address +
                    "', phone='" + phone + "', education='" + education + "', designation='" +
                    designation + "', nid='" + nid + "' WHERE empId='" + EmpId + "'";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Details updated successfully.");
            setVisible(false);
            new ViewEmployee().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}