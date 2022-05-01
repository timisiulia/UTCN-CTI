package Presenter;

import Model.PersistentaUtilizator;
import Model.Utilizator;
import View.ViewAdministrator;

import java.util.ArrayList;
import java.util.List;

public class PresenterAdmin {
    private PersistentaUtilizator persistentaUtilizator ;
    private ViewAdministrator viewAdministrator ;

    public PresenterAdmin(ViewAdministrator view){
        viewAdministrator = view ;
        persistentaUtilizator = new PersistentaUtilizator();
    }

    public void adaugaUtilizator(String username, String pass, String role){
        Utilizator u = new Utilizator(username,pass,role);
        persistentaUtilizator.addUser(u);
        System.out.println("Added");
    }

    public void stergeUtilizator(String username){
        Utilizator u = new Utilizator(username, "","");
        persistentaUtilizator.deleteUser(u);
        System.out.println("deleted");
    }

    public void updateUtilizator(String currentUsername , String newUsername, String newPass, String newRole){
        Utilizator oldUtilizator = new Utilizator(currentUsername , " " , " " );
        persistentaUtilizator.deleteUser(oldUtilizator);

        Utilizator newUtilizator = new Utilizator(newUsername,newPass ,newRole );
        persistentaUtilizator.addUser(newUtilizator);

        System.out.println("updated");
    }

    public List<Utilizator> afisareUtilizatori(){
        List<Utilizator> users;
        users = PersistentaUtilizator.readXmlFile();

        for(Utilizator u : users){
            System.out.println(u + " c");
        }
        return users;
    }
}

