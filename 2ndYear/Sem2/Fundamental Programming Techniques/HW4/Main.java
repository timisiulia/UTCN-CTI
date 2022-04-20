import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;
import presentationLayer.Controller;
import presentationLayer.LoginController;
import presentationLayer.PasswordClient;
import presentationLayer.RestaurantLogin;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        DeliveryService del = null;
        try {
            del = new DeliveryService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        del.importProducts();
        RestaurantLogin restaurant = new RestaurantLogin();
        Controller controller = new Controller(restaurant, del);

        System.out.println(del.getMenuItem());


    }
}
