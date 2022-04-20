package presentationLayer;

import bussinessLayer.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

public class Controller {
    protected static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    private RestaurantLogin restaurantlogin;
    private AdimistatorGraphicalUserInterface administratorinterfata;
    private ClientGraphicalUserInterface clientinterfata;
    private DeliveryService deliveryService;

    public Controller(RestaurantLogin restaurantlogin, DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.restaurantlogin = restaurantlogin;


        restaurantlogin.administratorButtonListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PasswordAdmin pass1 = new PasswordAdmin(deliveryService);
                LoginController log = new LoginController(pass1);

            }
        });

        restaurantlogin.clientButtonListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordClient pass = new PasswordClient(deliveryService);
                LoginController log = new LoginController(pass);

            }
        });
        restaurantlogin.employeeButtonListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordEmployee pass2 = new PasswordEmployee();
                LoginController log = new LoginController(pass2);

            }
        });


    }
}


