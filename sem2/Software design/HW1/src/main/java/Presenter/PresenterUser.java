package Presenter;

import Model.PersistentaUtilizator;
import Model.Utilizator;
import View.ViewAdministrator;
import View.ViewAngajat;
import View.ViewUserLogin;

import javax.swing.*;

public class PresenterUser {
    private PersistentaUtilizator persistentaUtilizator;
    private ViewUserLogin viewUserLogin;


    public PresenterUser(ViewUserLogin viewUserLogin) {
        this.viewUserLogin = viewUserLogin;
        persistentaUtilizator = new PersistentaUtilizator();
    }

    public void checkIfExist(String username) {
        Utilizator u = new Utilizator(username, "", "");

        if (persistentaUtilizator.existaUtilizator(u) == true) {
            System.out.println("Utilizatorul s-a conectat");
        }

    }

    public void checkIfExistsPass(String pass) {
        Utilizator u = new Utilizator("", pass, "");

        if (persistentaUtilizator.existaUtilizator(u) == true) {
            System.out.println("Parola e buna");
        }
    }

    public void checkIfExistsRole(String rol) {
        Utilizator u = new Utilizator("", "", rol);

        if (persistentaUtilizator.existaUtilizator(u) == true) {
            System.out.println("Exista rolul");
        }
    }

    public boolean goodUser(Utilizator u) {
        if (persistentaUtilizator.existaUtilizatorComplet(u)) {
            if (u.getRole().equals("Administrator")) {

                new ViewAdministrator();

            } else {
                new ViewAngajat();
            }
            return true;
        } else {
            System.out.println("Utilizatorul nu e bun");
        }

        return false;
    }

}