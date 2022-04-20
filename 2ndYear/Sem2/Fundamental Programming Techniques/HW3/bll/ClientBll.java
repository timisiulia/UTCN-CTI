package bll;
import java.util.List;
import java.util.NoSuchElementException;
import dao.*;
import model.*;
public class ClientBll {
    private ClientsDAO client;
    public ClientBll() {
        this.client = new ClientsDAO();
    }
    public List<Client>selectall() {
        return client.findAll();
    }
  
    public Client findClientById(int id) {
        Client client1 = client.findById(id);
        if(client1 == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client1;
    }

    public void insert(Client client1){
        client.insert(client1);
    }

    public void update(Client client1){
        client.update(client1);
    }

    public void delete(Client client1){
        client.delete(client1);
    }
}
