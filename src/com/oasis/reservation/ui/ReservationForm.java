package com.oasis.reservation.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.oasis.reservation.db.DBConnection;
import com.oasis.reservation.util.PNRGenerator;

public class ReservationForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtPNR, txtName, txtTrain, txtDate;
    private JButton btnSubmit;

    public ReservationForm() {

        setTitle("Make Reservation");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel("PNR:"));
        txtPNR = new JTextField(PNRGenerator.generatePNR());
        txtPNR.setEditable(false);
        add(txtPNR);

        add(new JLabel("Passenger Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Train Name:"));
        txtTrain = new JTextField();
        add(txtTrain);

        add(new JLabel("Journey Date:"));
        txtDate = new JTextField("YYYY-MM-DD");
        add(txtDate);

        btnSubmit = new JButton("Submit");
        add(new JLabel());
        add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection con = DBConnection.getConnection();

                    String sql = "INSERT INTO reservations (pnr, name, train, date) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, txtPNR.getText());
                    ps.setString(2, txtName.getText());
                    ps.setString(3, txtTrain.getText());
                    ps.setString(4, txtDate.getText());

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Reservation Successful!");
                    dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}
