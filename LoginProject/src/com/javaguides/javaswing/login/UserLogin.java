package com.javaguides.javaswing.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField textField;
    private final JPasswordField passwordField;
    private final JButton btnNewButton;
    private final JLabel label;
    private final JPanel contentPane;

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                     final UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch ( final Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        final JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 49));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        final JLabel lblUsername = new JLabel("User Name");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        final JLabel lblPassword = new JLabel("Password");
        lblPassword.setBackground(Color.BLACK);
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("LOGIN");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        btnNewButton.setBounds(545, 392, 162, 73);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String userName = textField.getText();
                @SuppressWarnings("deprecation")
				final String password = passwordField.getText();

                try {
                    final Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
                        "root", "root");

                    final PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select name, password from student where name=? and password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    final ResultSet rs = st.executeQuery();

                if(rs.next()) {
                    dispose();
                    final UserHome ah = new UserHome(userName);
                    ah.setTitle("Welcome");
                    ah.setVisible(true);
                    JOptionPane.showMessageDialog(btnNewButton, "You have succesfully logged into the system");

                } else {
                    JOptionPane.showMessageDialog(btnNewButton, "You have entered the wrong username and password");
                }
                
            } catch (final SQLException sqlException) {
                    sqlException.printStackTrace();
            }
        }
     }
     );
     
     contentPane.add(btnNewButton);
     label = new JLabel("");
     label.setBounds(0, 0, 1008, 562);
     contentPane.add(label);
     }
    }