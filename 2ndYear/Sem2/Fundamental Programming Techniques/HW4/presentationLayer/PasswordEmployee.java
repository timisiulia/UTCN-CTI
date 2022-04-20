package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordEmployee {
    JFrame frame = new JFrame(("Parola angajat"));
    JLabel User = new JLabel("User Name:");
    JTextField user = new JTextField(20);
    JLabel Password = new JLabel("Password:");
    final JPasswordField password = new JPasswordField(20);
    JButton btnPass = new JButton("Display Password");
    JButton btnLogin = new JButton("Login");

    public PasswordEmployee() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setTitle("Employee  login");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(User);
        panel.add(user);
        panel.add(Password);
        panel.add(password);
        panel.add(btnLogin);
        panel.add(btnPass);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JFrame fr=new JFrame();
                EmployeeGraphicalUserInterface employeescreen = new EmployeeGraphicalUserInterface();
                employeescreen.setVisible(true);
                employeescreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                employeescreen.setSize(800, 800);
                JPanel pan = new JPanel();
                pan.setLayout(new GridLayout(1, 1));
                employeescreen.add(pan);
            }
        });
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
}
