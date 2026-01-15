package com.oasis.reservation.ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainMenu() {

        setTitle("Main Menu");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 15, 15));

        JButton btnReserve = new JButton("Make Reservation");
        JButton btnCancel = new JButton("Cancel Reservation");
        JButton btnExit = new JButton("Exit");

        panel.add(btnReserve);
        panel.add(btnCancel);
        panel.add(btnExit);

        add(panel);

        // Open Reservation Form
        btnReserve.addActionListener(_ -> new ReservationForm());

        // Open Cancellation Form
        btnCancel.addActionListener(_ -> new CancellationForm());

        // Exit application
        btnExit.addActionListener(_ -> System.exit(0));

        setVisible(true);
    }
}
