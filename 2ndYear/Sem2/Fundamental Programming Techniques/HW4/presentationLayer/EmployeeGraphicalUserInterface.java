package presentationLayer;

import bussinessLayer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGraphicalUserInterface extends JFrame implements Observer {
    JFrame frame = new JFrame("Interfata angajat");
    JTextArea area = new JTextArea("Comanda dumneavoastra este in curs de procesare !");
    JButton notify = new JButton("Get notified");
    JTextField items = new JTextField();

    public EmployeeGraphicalUserInterface() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setTitle("Employee");
        JPanel panel = new JPanel();
        panel.add(area);
        panel.add(notify);
        frame.add(panel);
        notify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new JFrame();
                fr.setVisible(true);
                fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                fr.setSize(800, 800);
                JPanel pan = new JPanel();
                pan.setLayout(new GridLayout(1, 1));
                pan.add(items);
                fr.add(pan);
            }
        });

    }

    public void notifylientButtonListener(ActionListener orderemployeeButton) {
        this.notify.addActionListener(orderemployeeButton);
    }


    @Override
    public void update(Observable o, Object arg) {

        new EmployeeGraphicalUserInterface();
        frame.setVisible(true);
    }
}
