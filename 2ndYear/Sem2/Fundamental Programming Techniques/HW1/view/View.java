package view;

import model.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    //casuta text pentru polinoame/rezultat
    private JTextField Polinom1 = new JTextField(25);
    private JTextField Polinom2 = new JTextField(25);
    private JTextField Rezultat = new JTextField(25);

    //butoane de calcul
    private JButton adunare = new JButton("Adunare");
    private JButton scadere = new JButton("Scadere");
    private JButton inmultire = new JButton("Inmultire");
    private JButton derivare = new JButton("Derivare");
    private JButton integrare = new JButton("Integrare");

    //creare interfata
    public Polynomial interfata;

    public View(Polynomial Model) {

        this.interfata = Model;

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(2, 0));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 2));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(adunare);
        panel2.add(scadere);
        panel2.add(inmultire);
        panel2.add(derivare);
        panel2.add(integrare);

        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel3.add(new JLabel("Polinomul1= "));
        panel3.add(Polinom1);
        panel5.add(new JLabel("Polinomul2= "));
        panel5.add(Polinom2);
        panel4.add(new JLabel("Rezultat= "));
        panel4.add(Rezultat);

        panel1.add(panel3);
        panel1.add(panel5);
        panel1.add(panel4);
        content.add(panel1);
        content.add(panel2);

        this.setContentPane(content);

        this.pack();

        this.setTitle("Calculator polinomial");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void reset() {
        Rezultat.setText("");
    }

    public String getUserInput1() {
        return Polinom1.getText();
    }

    public String getUserInput2() {
        return Polinom2.getText();
    }

    public void setTotal(String newTotal) {
        Rezultat.setText(newTotal);
    }

    public void adunareListener(ActionListener adunare) {
        this.adunare.addActionListener(adunare);
    }

    public void ScadereListener(ActionListener scadere) {
        this.scadere.addActionListener(scadere);
    }

    public void InmultireListener(ActionListener inmultire) {
        this.inmultire.addActionListener(inmultire);
    }

    public void DerivareListener(ActionListener derivare) {
        this.derivare.addActionListener(derivare);
    }

    public void IntegrareListener(ActionListener integrare) {
        this.integrare.addActionListener(integrare);
    }
}