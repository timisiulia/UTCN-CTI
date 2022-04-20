package bll;

import java.util.List;
import java.util.NoSuchElementException;
import dao.OrdersDAO;
import model.Productsorders;


public class OrderBll {
    private OrdersDAO order;

    public OrderBll(){
        this.order = new OrdersDAO();
    }
    public Productsorders findClientById(int id){
        Productsorders order1 = order.findById(id);
        if(order1 == null){
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return order1;
    }

    public void insert(Productsorders order1){
        order.insert(order1);
    }

    public void update(Productsorders order1){
        order.update(order1);
    }

    public void delete(Productsorders order1){
        order.delete(order1);
    }

    public List<Productsorders> selectall(){
        return order.findAll();
    }
}
