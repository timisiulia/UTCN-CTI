package View;

import Presenter.PresenterAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAdministrator extends JFrame implements InterfataAdmin {


    private PresenterAdmin presenterAdmin;
    private JPanel panel1;
    private JButton actualizareButton;
    private JButton afisareButton;
    private JButton salvareButton;
    private JButton stergereButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton logOutButton;

    public ViewAdministrator() {
        this.setTitle("Administrator");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel1);
        this.setVisible(true);

        presenterAdmin = new PresenterAdmin(this);

        actualizareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser(presenterAdmin);
            }
        });

        afisareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afisareUtilizatori(presenterAdmin);
            }
        });

        salvareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaUtilizator(presenterAdmin);
            }
        });
        stergereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser(presenterAdmin);
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
    public void afisareUtilizatori(PresenterAdmin p) {
        p.afisareUtilizatori();
    }

    @Override
    public void adaugaUtilizator(PresenterAdmin p) {
        String username, password, role;
        username = textField5.getText();
        password = textField4.getText();
        role = textField1.getText();
        p.adaugaUtilizator(username, password, role);
    }

    @Override
    public void updateUser(PresenterAdmin p) {
        String username, password, role;
        username = textField5.getText();
        password = textField4.getText();
        role = textField1.getText();

        String newUsername, newPassword, newRole;
        newUsername = textField6.getText();
        newPassword = textField2.getText();
        newRole = textField3.getText();
        // nu trrebuie sa fie goale fieldurile
        p.updateUtilizator(username, newUsername, newPassword, newRole);
    }


    @Override
    public void deleteUser(PresenterAdmin p) {
        String username, password, role;
        username = textField5.getText();
        //password = textField4.getText();
        //role = textField1.getText();
        p.stergeUtilizator(username);
    }
}
