package controller;

import model.Polynomial;
import model.Rejex;
import view.View;

import java.awt.event.*;

public class Controller {
    private View v;
    private Polynomial p;

    public Controller(Polynomial p, View v) {
        this.v = v;
        this.p = p;

        v.adunareListener(new adunareActionListener());
        v.ScadereListener(new scadereActionListener());
        v.InmultireListener(new inmultireActionListener());
        v.DerivareListener(new derivareActionListener());
        v.IntegrareListener(new integrareActionListener());

    }

    public class adunareActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.reset();
            String a = v.getUserInput1();
            String b = v.getUserInput2();
            Rejex r1 = new Rejex();
            Polynomial pol1 = r1.createPolynomial(a);
            Rejex r2 = new Rejex();
            Polynomial pol2 = r2.createPolynomial(b);
            pol1 = pol1.adunare(pol2);
            v.setTotal(pol1.toString());

        }
    }

    public class scadereActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.reset();
            String a = v.getUserInput1();
            String b = v.getUserInput2();
            Rejex r1 = new Rejex();
            Polynomial pol1 = r1.createPolynomial(a);
            Rejex r2 = new Rejex();
            Polynomial pol2 = r2.createPolynomial(b);
            pol1 = pol1.scadere(pol2);
            v.setTotal(pol1.toString());

        }
    }

    public class inmultireActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.reset();
            String a = v.getUserInput1();
            String b = v.getUserInput2();
            Rejex r1 = new Rejex();
            Polynomial pol1 = r1.createPolynomial(a);
            Rejex r2 = new Rejex();
            Polynomial pol2 = r2.createPolynomial(b);
            pol1 = pol1.inmultire(pol2);
            v.setTotal(pol1.toString());
        }
    }

    public class derivareActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.reset();
            String a = v.getUserInput1();
            Rejex r1 = new Rejex();
            Polynomial pol1 = r1.createPolynomial(a);
            pol1 = pol1.derivare();
            v.setTotal(pol1.toString());
        }
    }

    public class integrareActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.reset();
            String a = v.getUserInput1();
            Rejex r1 = new Rejex();
            Polynomial pol1 = r1.createPolynomial(a);
            pol1 = pol1.integrare();
            v.setTotal(pol1.toString());
        }
    }
}
