package View;

import Presenter.PresenterAdmin;
import Presenter.PresenterAngajat;

public interface InterfataAngajat {

    //selectare florarie
    //vizualizare lista flori
    //filtrare dupa disponibilitare
    //filtrare dupa pret
    //filtrare dupa culoare
    //filtrare dupa cantitate
    //cautare floare dupa denumire
    //adaugare
    //stergere
    //update

    String selectareFlorarie();
    void vizualizareListaFlori(PresenterAngajat p);
    void filtrareDupaDisponibilitate(PresenterAngajat p);
    void filtrareDupaPret(PresenterAngajat p);
    void filtrareDupaCuloare(PresenterAngajat p);
    void filtrareDupaCantitate(PresenterAngajat p);
    void cautareDupaDenumire(PresenterAngajat p);
    void adaugaFloare(PresenterAngajat p);
    void updateFloare(PresenterAngajat p);
    void deleteFloare(PresenterAngajat p);





}
