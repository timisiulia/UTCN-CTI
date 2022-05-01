package View;

import Presenter.PresenterAdmin;

public interface InterfataAdmin
{
    // afisare
    //adaugare
    //stergere
    //update
    void afisareUtilizatori(PresenterAdmin p);
    void adaugaUtilizator(PresenterAdmin p);
    void updateUser(PresenterAdmin p);
    void deleteUser(PresenterAdmin p);

}
