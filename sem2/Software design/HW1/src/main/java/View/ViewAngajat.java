package View;

import Model.Floare;
import Presenter.PresenterAngajat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAngajat extends JFrame implements InterfataAngajat {

    private PresenterAngajat presenterAngajat;
    private JPanel panel1;
    private JPanel pan2;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField8;
    private JTextField textField9;
    private JButton adaugareButton;
    private JButton salvareRapoarteButton;
    private JButton logOutButton;
    private JButton stergereButton;
    private JButton actualizareButton;
    private JButton filtrareDupaDisponibilitateButton;
    private JButton filtrareDupaPretButton;
    private JButton filtrareDupaCuloareButton;
    private JButton filtrareDupaCantitateButton;
    private JButton cautareDupaDenumireButton;
    private JButton vizulizareListaDeFloriButton;


    public ViewAngajat() {
        this.setTitle("Angajat");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel1);
        this.setVisible(true);

        presenterAngajat = new PresenterAngajat(this);


        actualizareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFloare(presenterAngajat);
            }
        });
        adaugareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaFloare(presenterAngajat);
            }
        });
        stergereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFloare(presenterAngajat);
            }
        });
        vizulizareListaDeFloriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizareListaFlori(presenterAngajat);
            }
        });
        salvareRapoarteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvareRapoarte(presenterAngajat);
            }
        });

        filtrareDupaDisponibilitateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrareDupaDisponibilitate(presenterAngajat);
            }
        });
        filtrareDupaPretButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrareDupaPret(presenterAngajat);
            }
        });
        filtrareDupaCuloareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrareDupaCuloare(presenterAngajat);
            }
        });
        filtrareDupaCantitateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrareDupaCantitate(presenterAngajat);
            }
        });
        cautareDupaDenumireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cautareDupaDenumire(presenterAngajat);
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

    }

    @Override
    public String selectareFlorarie() {
        String numeFlorarie;
        numeFlorarie = textField6.getText();
        return numeFlorarie;
    }

    public void setareTextAfis(String s) {

        textField8.setText(s);
    }

    @Override
    public void vizualizareListaFlori(PresenterAngajat p) {
        String numeFlorarie;
        numeFlorarie = textField6.getText();
        p.vizualizareListaFlori(numeFlorarie);
    }

    @Override
    public void filtrareDupaDisponibilitate(PresenterAngajat p) {
        Boolean disp;
        disp = Boolean.valueOf(textField2.getText());
        System.out.println("Disponibilitate " + disp);
        p.filtrareDupaDisponibilitate(disp);
    }

    @Override
    public void filtrareDupaPret(PresenterAngajat p) {
        float pmin;
        float pmax;
        pmin = Float.parseFloat(textField3.getText());
        pmax = Float.parseFloat(textField9.getText());
        System.out.println("Pret " + pmin + pmax);
        p.filtrareDupaPret(pmin, pmax);

    }

    @Override
    public void filtrareDupaCuloare(PresenterAngajat p) {
        String culoare;
        culoare = textField4.getText();
        System.out.println("Culoare " + culoare);
        p.filtrareDupaCuloare(culoare);


    }

    @Override
    public void filtrareDupaCantitate(PresenterAngajat p) {
        String cantitate;
        cantitate = textField5.getText();
        System.out.println("Cantitate " + cantitate);
        p.filtrareDupaCantitate(Integer.valueOf(cantitate));

    }

    @Override
    public void cautareDupaDenumire(PresenterAngajat p) {
        String denumire;
        denumire = textField1.getText();
        System.out.println("Denumire " + denumire);
        p.cautareDupaDenumire(denumire);
    }


    @Override
    public void adaugaFloare(PresenterAngajat p) {
        String numeFlorarie;
        String FlowerType;
        Boolean disponibilitateFloare;
        Integer nrFlori;
        String culoareFloare;
        Float pretFloare;
        numeFlorarie = textField6.getText();
        FlowerType = textField1.getText();
        disponibilitateFloare = Boolean.valueOf(textField2.getText());
        nrFlori = Integer.valueOf(textField5.getText());
        culoareFloare = textField4.getText();
        pretFloare = Float.valueOf(textField3.getText());
        p.adaugaFloare(FlowerType, disponibilitateFloare, nrFlori, culoareFloare, pretFloare);
    }

    @Override
    public void updateFloare(PresenterAngajat p) {
        String numeFlorarie;
        String FlowerType;
        Boolean disponibilitateFloare;
        Integer nrFlori;
        String culoareFloare;
        Float pretFloare;
        numeFlorarie = textField6.getText();
        FlowerType = textField1.getText();
        disponibilitateFloare = Boolean.valueOf(textField2.getText());
        nrFlori = Integer.valueOf(textField5.getText());
        culoareFloare = textField4.getText();
        pretFloare = Float.valueOf(textField3.getText());

        presenterAngajat.updateFloare(FlowerType, disponibilitateFloare, nrFlori, culoareFloare, pretFloare);

    }

    @Override
    public void deleteFloare(PresenterAngajat p) {
        String FlowerType;
        FlowerType = textField1.getText();
        p.deleteFloare(FlowerType);
    }

    public void salvareRapoarte(PresenterAngajat p) {
        p.salvareRapoarte();
    }
}
