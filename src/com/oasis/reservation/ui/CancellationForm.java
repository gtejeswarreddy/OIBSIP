package com.oasis.reservation.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.oasis.reservation.db.DBConnection;

public class CancellationForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtPNR;
    private JButton btnCancel;

    public CancellationForm() {

        setTitle("Cancel Reservation");
        setSize(350, 180);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel("Enter PNR:"));
        txtPNR = new JTextField();
        add(txtPNR);

        btnCancel = new JButton("Cancel Reservation");
        add(new JLabel());
        add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pnr = txtPNR.getText().trim();

                if (pnr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "PNR cannot be empty");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to cancel this reservation?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                try {
                    Connection con = DBConnection.getConnection();

                    String sql = "DELETE FROM reservations WHERE pnr = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, pnr);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "Reservation Cancelled Successfully");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "PNR not found");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}
