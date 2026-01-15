package com.oasis.reservation.ui;

import com.oasis.reservation.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {

    private static final long serialVersionUID = 1L;

    JTextField txtUser;
    JPasswordField txtPass;

    public LoginForm() {

        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Username:"));
        txtUser = new JTextField();
        add(txtUser);

        add(new JLabel("Password:"));
        txtPass = new JPasswordField();
        add(txtPass);

        JButton btnLogin = new JButton("Login");
        add(btnLogin);

        btnLogin.addActionListener(_ -> login());

        setVisible(true);
    }

    private void login() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new MainMenu();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
