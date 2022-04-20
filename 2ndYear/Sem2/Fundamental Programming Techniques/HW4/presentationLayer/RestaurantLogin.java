package presentationLayer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantLogin {
    JFrame Restaurant = new JFrame();
    JButton btn1 = new JButton("Login for client");
    JButton btn2 = new JButton("Login for administrator");
    JButton btn3 = new JButton("Login for employee");


    public RestaurantLogin() {
        Restaurant.setVisible(true);
        Restaurant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Restaurant.setSize(1000, 800);
        Restaurant.setTitle("Restaurant");
        Restaurant.setLayout(new GridLayout(2, 1));
        JPanel pp = new JPanel();
        JLabel label = new JLabel("Warm welcome to our restaurant");
        label.setFont(new Font("Serif", Font.PLAIN, 40));
        label.setSize(200, 200);
        pp.add(label);
        Restaurant.add(pp, BorderLayout.CENTER);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        p.add(btn1);
        p.add(btn2);
        p.add(btn3);

        Restaurant.add(p, BorderLayout.CENTER);


    }

    public void administratorButtonListener(ActionListener administratorButton) {
        this.btn2.addActionListener(administratorButton);
    }

    public void clientButtonListener(ActionListener clientButton) {

        this.btn1.addActionListener(clientButton);
    }

    public void employeeButtonListener(ActionListener employeeButton) {

        this.btn3.addActionListener(employeeButton);
    }


}

