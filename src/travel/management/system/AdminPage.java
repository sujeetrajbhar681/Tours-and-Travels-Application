
package travel.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPage extends javax.swing.JFrame {
    // Components
    private JTextField tourNameField;
    private JTextField destinationField;
    private JTextField priceField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JTable toursTable;
    private JScrollPane scrollPane;

    // Database connection
    private Conn conn;

    public AdminPage() {
        conn = new Conn(); // Initialize the database connection
        initComponents();
    }

    private void initComponents() {
        // Initialize components
        tourNameField = new JTextField(20);
        destinationField = new JTextField(20);
        priceField = new JTextField(20);
        addButton = new JButton("Add Tour");
        updateButton = new JButton("Update Tour");
        deleteButton = new JButton("Delete Tour");
        viewButton = new JButton("View Tours");
        toursTable = new JTable();
        scrollPane = new JScrollPane(toursTable);

        // Set layout
        setLayout(new java.awt.FlowLayout());

        // Add components to the frame
        add(new JLabel("Admin Dashboard"));
        add(new JLabel("Tour Name:"));
        add(tourNameField);
        add(new JLabel("Destination:"));
        add(destinationField);
        add(new JLabel("Price:"));
        add(priceField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);
        add(scrollPane);

        // Set up table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tour Name");
        model.addColumn("Destination");
        model.addColumn("Price");
        toursTable.setModel(model);

        // Add button listeners
        addButton.addActionListener(e -> addTour());
        updateButton.addActionListener(e -> updateTour());
        deleteButton.addActionListener(e -> deleteTour());
        viewButton.addActionListener(e -> viewTours());

        // Auto-fill fields when a tour is selected
        toursTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = toursTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = toursTable.getValueAt(selectedRow, 0).toString();
                String destination = toursTable.getValueAt(selectedRow, 1).toString();
                String price = toursTable.getValueAt(selectedRow, 2).toString();
                tourNameField.setText(name);
                destinationField.setText(destination);
                priceField.setText(price);
            }
        });

        // Set frame properties
        setTitle("Admin Page - Tours and Travel Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
    }

    private void addTour() {
        String name = tourNameField.getText();
        String destination = destinationField.getText();
        double price = Double.parseDouble(priceField.getText());

        String query = "INSERT INTO Tour (name, destination, price) VALUES ('" + name + "', '" + destination + "', " + price + ")";
        try {
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Tour added successfully!");
            refreshTable();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding tour!");
        }
    }

    private void updateTour() {
        int selectedRow = toursTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a tour to update.");
            return;
        }

        String name = tourNameField.getText();
        String destination = destinationField.getText();
        double price = Double.parseDouble(priceField.getText());
        String selectedName = toursTable.getValueAt(selectedRow, 0).toString();

        String query = "UPDATE Tour SET name = '" + name + "', destination = '" + destination + "', price = " + price + " WHERE name = '" + selectedName + "'";
        try {
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Tour updated successfully!");
            refreshTable();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating tour!");
        }
    }

    private void deleteTour() {
        int selectedRow = toursTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a tour to delete.");
            return;
        }

        String name = toursTable.getValueAt(selectedRow, 0).toString();
        String query = "DELETE FROM Tour WHERE name = '" + name + "'";
        try {
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Tour deleted successfully!");
            refreshTable();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting tour!");
        }
    }

    private void viewTours() {
        refreshTable();
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) toursTable.getModel();
        model.setRowCount(0); // Clear existing data

        String query = "SELECT * FROM Tour";
        try {
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String destination = rs.getString("destination");
                double price = rs.getDouble("price");
                model.addRow(new Object[]{name, destination, price});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading tours!");
        }
    }

    private void clearFields() {
        tourNameField.setText("");
        destinationField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new AdminPage().setVisible(true));
    }
}