package Presenter;

import Model.*;
import View.ViewAngajat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PresenterAngajat {
    private PersistentaFlorarie persistentaFlorarie;
    private ViewAngajat viewAngajat;

    public PresenterAngajat(ViewAngajat view) {
        this.viewAngajat = view;
        persistentaFlorarie = new PersistentaFlorarie();
    }

    public void adaugaFloare(String FlowerType, Boolean disponibilitateFloare, Integer nrFlori, String culoareFloare, Float pretFloare) {
        Floare f = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, FlowerType);
        persistentaFlorarie.addFlower(f);
        System.out.println("Added");

    }

    public void deleteFloare(String FlowerType) {
        Floare f = new Floare(1.0F, "alb", 1, true, FlowerType);
        persistentaFlorarie.deleteFlower(f);
        System.out.println("Deleted");

    }

    public void updateFloare(String FlowerType, Boolean disponibilitateFloare, Integer nrFlori, String culoareFloare, Float pretFloare) {
        Floare f = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, FlowerType);
        persistentaFlorarie.deleteFlower(f);
        persistentaFlorarie.addFlower(f);
        System.out.println("updated");
    }


    public String vizualizareListaFlori(String nume) {
        List<Floare> f;
        String result = new String();
        f = PersistentaFlorarie.cautareFlorarie(nume);

        for (Floare ff : f) {

            result = result + " " + ff.toString();
        }
        System.out.println(result);
        viewAngajat.setareTextAfis(result);
        return result;
    }

    public String filtrareDupaDisponibilitate(Boolean disp) {
        List<Floare> f;
        String result = new String();
        f = PersistentaFlorarie.cautareDisponibilitate(disp);

        for (Floare ff : f) {

            result = result + " " + ff.toString();
        }
        System.out.println(result);
        viewAngajat.setareTextAfis(result);
        return result;
    }

    public String filtrareDupaPret(Float pmin, Float pmax) {
        List<Floare> f;
        String result = new String();
        f = PersistentaFlorarie.cautareDupaPret(pmin, pmax);

        for (Floare ff : f) {

            result = result + " " + ff.toString();
        }
        System.out.println(result);
        viewAngajat.setareTextAfis(result);
        return result;
    }

    public String filtrareDupaCuloare(String culoare) {
        List<Floare> f;
        String result = new String();
        f = PersistentaFlorarie.cautareDupaCuloare(culoare);

        for (Floare ff : f) {

            result = result + " " + ff.toString();
        }
        System.out.println(result);
        viewAngajat.setareTextAfis(result);
        return result;
    }

    public String filtrareDupaCantitate(Integer cant) {
        List<Floare> f;
        String result = new String();
        f = PersistentaFlorarie.cautareDupaCantitate(cant);

        for (Floare ff : f) {

            result = result + " " + ff.toString();
        }
        System.out.println(result);
        viewAngajat.setareTextAfis(result);
        return result;
    }

    public String cautareDupaDenumire(String denume) {
        List<Floare> f;
        String result = new String();
        f = PersistentaFlorarie.cautareDupaDenumire1(denume);

        for (Floare ff : f) {

            result = result + " " + ff.toString();
        }
        System.out.println(result);
        viewAngajat.setareTextAfis(result);
        return result;
    }

    public void salvareRapoarte() {
        List<Floare> floriList = new ArrayList<>();
        floriList = PersistentaFlorarie.readXmlFileFlowers();
        persistentaFlorarie.exportToJSON(floriList);
        try {
            persistentaFlorarie.exportToCSV(floriList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Rapoarte generate!");
    }

}
