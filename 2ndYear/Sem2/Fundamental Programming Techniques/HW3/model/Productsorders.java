package model;

public class Productsorders {

    private int id;
    private int id_order;
    private int id_client;
    private int quantity_order;
    public Productsorders(int id_order, int id_client, int product_order, int quantity_order)
    {
        this.id=product_order;
        this.id_order=id_order;
        this.id_client=id_client;
        this.quantity_order=quantity_order;
    }
    public Productsorders(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getQuantity_order() {
        return quantity_order;
    }

    public void setQuantity_order(int quantity_order) {
        this.quantity_order = quantity_order;
    }
}
