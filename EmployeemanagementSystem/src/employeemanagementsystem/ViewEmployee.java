/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId;
    JButton Search, Print, Update, Back, Sort;

    public ViewEmployee() {
        getContentPane().setBackground(Color.gray);
        setLayout(null);

        JLabel searchlbl = new JLabel("Search By Employee Id");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);

        cemployeeId = new Choice();
        cemployeeId.setBounds(180, 20, 150, 20);
        add(cemployeeId);
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while (rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(buildTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        Search = new JButton("Search");
        Search.setBounds(20, 70, 80, 20);
        Search.addActionListener(this);
        add(Search);

        Print = new JButton("Print");
        Print.setBounds(120, 70, 80, 20);
        Print.addActionListener(this);
        add(Print);

        Update = new JButton("Update");
        Update.setBounds(220, 70, 80, 20);
        Update.addActionListener(this);
        add(Update);

        Back = new JButton("Back");
        Back.setBounds(320, 70, 80, 20);
        Back.addActionListener(this);
        add(Back);

        Sort = new JButton("Sort");
        Sort.setBounds(420, 70, 80, 20);
        Sort.addActionListener(this);
        add(Sort);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            Conn c = new Conn();
            if (evt.getSource() == Search) {
                String query = "select * from employee where empId = '" + cemployeeId.getSelectedItem() + "'";
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(buildTableModel(rs));
            } else if (evt.getSource() == Print) {
                table.print();
            } else if (evt.getSource() == Update) {
                setVisible(false);
                new UpdateEmployee(cemployeeId.getSelectedItem()).setVisible(true);
            } else if (evt.getSource() == Back) {
                setVisible(false);
                new Home().setVisible(true);
            } else if (evt.getSource() == Sort) {
                String query = "select * from employee order by salary desc";
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(buildTableModel(rs));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Utility method to convert ResultSet to TableModel
    public static DefaultTableModel buildTableModel(ResultSet rs) throws Exception {
        ResultSetMetaData metaData = rs.getMetaData();

        // Column names
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnLabel(i + 1);
        }

        // Data rows
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = rs.getObject(i + 1);
            }
            model.addRow(rowData);
        }
        return model;
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }

}
