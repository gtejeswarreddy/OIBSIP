package com.oasis.exam.ui;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private static final long serialVersionUID = 1L;

    JTextField txtUser;
    JPasswordField txtPass;

    public LoginForm() {

        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Username:"));
        txtUser = new JTextField();
        add(txtUser);

        add(new JLabel("Password:"));
        txtPass = new JPasswordField();
        add(txtPass);

        JButton btnLogin = new JButton("Login");
        add(new JLabel());
        add(btnLogin);

        btnLogin.addActionListener(_ -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if (user.equals("teja") && pass.equals("gteja@123")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new ExamForm(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
