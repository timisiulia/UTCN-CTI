package presentationLayer;

import bussinessLayer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordAdmin {
    JFrame frame = new JFrame(("Parola admin "));
    JLabel User = new JLabel("User Name:");
    JTextField user = new JTextField(20);
    JLabel Password = new JLabel("Password:");
    final JPasswordField password = new JPasswordField(20);
    JButton btnPass = new JButton("Display Password");
    JButton btnLogin = new JButton("Login");

    public PasswordAdmin(DeliveryService deliveryService) {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setTitle("Administrator login");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AdimistatorGraphicalUserInterface(deliveryService);


            }
        });

        panel.add(User);
        panel.add(user);
        panel.add(Password);
        panel.add(password);
        panel.add(btnLogin);
        panel.add(btnPass);

        frame.getContentPane().add(panel);
        frame.setVisible(true);


    }

    public void userButtonListener(ActionListener userButton) {
        this.btnLogin.addActionListener(userButton);
    }

    public void passButtonListener(ActionListener passButton) {

        this.btnPass.addActionListener(passButton);
    }

    public String getPassword() {
        return password.getText();
    }

    public void logadminButtonListener(ActionListener administratorButton) {
        this.btnLogin.addActionListener(administratorButton);
    }
}
