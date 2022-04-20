package bll;
import java.util.List;
import java.util.NoSuchElementException;
import dao.ProductDAO;
import model.Products;
public class ProductBll {
    private ProductDAO product1;

    public ProductBll(){
        this.product1=new ProductDAO();
    }

    public Products findClientById(int id) {
        Products p = product1.findById(id);
        if(p == null){
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return p;
    }

    public void insert(Products p){
        product1.insert(p);
    }

    public void update(Products p){
        product1.update(p);
    }

    public void delete(Products p){
        product1.delete(p);
    }

    public List<Products> selectall(){
        return product1.findAll();
    }
  }
