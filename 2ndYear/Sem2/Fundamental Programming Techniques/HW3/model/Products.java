package model;

public class Products {
    private int id;
    private String name_product;
    private int quantity;
    private double price_product;
    public Products(int id_product, String name_product, int quantity, double price_product )
    {
        this.id=id_product;
        this.name_product=name_product;
        this.quantity=quantity;
       this.price_product=price_product;
    }
    public Products(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice_product() {
        return price_product;
    }

    public void setPrice_product(double price_product) {
        this.price_product = price_product;
    }
}
