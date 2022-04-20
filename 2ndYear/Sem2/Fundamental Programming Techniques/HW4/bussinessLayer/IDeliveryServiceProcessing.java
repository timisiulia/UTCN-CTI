package bussinessLayer;

import model.Cont;

import java.util.*;
import java.util.List;

public interface IDeliveryServiceProcessing {

    HashMap<Order, ArrayList<MenuItem>> getOrders();

    HashSet<MenuItem> getMenuItem();

    /**
     * Importa produse
     *
     * @param
     */
    void importProducts();

    void updateProducts();

    /**
     * Adauga o comanda
     *
     * @param o
     * @param food
     * @post isWellFormed()
     */
    void addOrder(Order o, ArrayList<MenuItem> food);

    /**
     * Adauga un produs un meniu
     *
     * @param m
     * @post isWellFormed()
     */
    void addMenuItem(MenuItem m);

    void addClient(Cont c);

    void addObs(Order O);

    /**
     * @param title
     * @param rating
     * @param calories
     * @param proteins
     * @param fats
     * @param sodium
     * @param price
     * @return
     */

    MenuItem addMenu(String title, float rating, float calories, float proteins, float fats, float sodium, float price);

    void removeMenuItem(MenuItem mm);

    /**
     * editeaza meniul
     *
     * @param mm
     * @param rating
     * @param fats
     * @param calories
     * @param proteins
     * @param sodium
     * @param price
     * @post isWellFormed()
     */
    void editMenuItem(MenuItem mm, String rating, String fats, String calories, String proteins, String sodium, String price);

    int getOrderID();

    /**
     * Cauta produsul
     *
     * @param title
     * @return
     */
    ArrayList<MenuItem> searchProduct(String title);

    /**
     * Cauta produsele dupa anumite criterii
     *
     * @param rating
     * @param fats
     * @param title
     * @param calories
     * @param proteins
     * @param sodium
     * @param price
     * @return
     */
    ArrayList<MenuItem> searchProducts(String rating, String fats, String title, String calories, String proteins, String sodium, String price);

    Object clone() throws CloneNotSupportedException;

    /**
     * Retruneaza raportul 1
     *
     * @param start
     * @param end
     * @return
     */
    String report1(String start, String end);

    /**
     * Retruneaza raportul 2
     *
     * @param numarDeOri
     * @return
     */
    String report2(String numarDeOri);

    /**
     * Calculeaza suma produselor
     *
     * @param list
     * @return
     */
    float sumProd(List<MenuItem> list);

    /**
     * Retruneaza raportul 3
     *
     * @param nOfTimes
     * @param price
     * @return
     */
    String report3(String nOfTimes, String price);

    /**
     * Retruneaza raportul 4
     *
     * @param data
     * @return
     */
    String report4(String data);

    /**
     * Functie pentru a verifica integritatea datelor
     *
     * @return
     */
    boolean isWellFormed();
}
