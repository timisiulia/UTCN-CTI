package Model;

import java.util.ArrayList;

public class Florarie {
    private String numeFlorarie;
    private ArrayList<Floare> listaFlori;
    public Florarie(String numeFlorarie, ArrayList<Floare> listaFlori){
        this.listaFlori=listaFlori;
        this.numeFlorarie=numeFlorarie;

    }

    public String getNumeFlorarie() {
        return numeFlorarie;
    }

    public void setNumeFlorarie(String numeFlorarie) {
        this.numeFlorarie = numeFlorarie;
    }

    public ArrayList<Floare> getListaFlori() {
        return listaFlori;
    }

    public void setListaFlori(ArrayList<Floare> listaFlori) {
        this.listaFlori = listaFlori;
    }

    @Override
    public String toString() {
        return "Florarie{" +
                "numeFlorarie='" + numeFlorarie + '\'' +
                ", listaFlori=" + listaFlori +
                '}';
    }
}
