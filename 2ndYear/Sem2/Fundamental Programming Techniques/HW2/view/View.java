package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JTextField N = new JTextField("4");
    private JTextField Q = new JTextField("2");
    private JTextField timeLim    = new JTextField("60");
    private JTextField tMinA  = new JTextField("2");
    private JTextField tMaxA  = new JTextField("30");
    private JTextField tMinS  = new JTextField("2");
    private JTextField tMaxS  = new JTextField("4");
    private JButton    summit = new JButton("SUBMIT");
    private JButton    start   = new JButton("START");
    private JTextPane Rezultat = new JTextPane();
    JScrollPane scrollPane = new JScrollPane(Rezultat);

public View() {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(3,0));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(8,0));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(new JLabel("Number of clients= "));
        panel3.add(N);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(new JLabel("Number of queues= "));
        panel4.add(Q);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout());
        panel5.add(new JLabel("Simulation time= "));
        panel5.add(timeLim);

        JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout());
        panel6.add(new JLabel("Time min arrival= "));
        panel6.add(tMinA);

        JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout());
        panel7.add(new JLabel("Time max arrival= "));
        panel7.add(tMaxA);

        JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout());
        panel8.add(new JLabel("Time min service= "));
        panel8.add(tMinS);

        JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout());
        panel9.add(new JLabel("Time max service= "));
        panel9.add(tMaxS);

        panel1.add(panel3);
        panel1.add(panel4);
        panel1.add(panel5);
        panel1.add(panel6);
        panel1.add(panel7);
        panel1.add(panel8);
        panel1.add(panel9);


        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(start);
        panel2.add(summit);


        Rezultat.setEditable(false);
        content.add(panel1);
        content.add(panel2);
        content.add(scrollPane);

        this.setContentPane(content);
        this.pack();
        this.setTitle("RezultateSimulare");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

public void setTotal(String newTotal) {
        Rezultat.setText(newTotal);
        }


public void reset() {
        Rezultat.setText(" ");
        }

        public JTextField getN() {
                return N;
        }

        public JTextField getQ() {
                return Q;
        }

        public JTextField getTimeLim() {
                return timeLim;
        }

        public JTextField gettMinA() {
                return tMinA;
        }

        public JTextField gettMaxA() {
                return tMaxA;
        }

        public JTextField gettMinS() {
                return tMinS;
        }

        public JTextField gettMaxS() {
                return tMaxS;
        }

        public void summitListener(ActionListener summit) {
        this.summit.addActionListener(summit);
        }

        public void startListener(ActionListener start) {
        this.start.addActionListener(start);
        }

        }
