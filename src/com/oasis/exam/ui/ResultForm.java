package com.oasis.exam.ui;

import javax.swing.*;
import java.awt.*;

public class ResultForm extends JFrame {

    private static final long serialVersionUID = 1L;

    public ResultForm(String user, int score) {

        setTitle("Result");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lbl = new JLabel("Your Score: " + score, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        add(lbl);

        setVisible(true);
    }
}
