package presentationLayer;

import bussinessLayer.BaseProduct;
import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;
import bussinessLayer.Order;
import dataLayer.Bon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ClientGraphicalUserInterface extends JFrame {
    JFrame frame = new JFrame("Products");
    JTextArea area = new JTextArea();
    JScrollPane parcurgere = new JScrollPane(area);
    JFrame frame1 = new JFrame("Orders");
    JTextArea area1 = new JTextArea();
    JScrollPane parcurgere1 = new JScrollPane(area1);

    JTextField title = new JTextField();
    JTextField rating = new JTextField("0.0");
    JTextField calories = new JTextField("0.0");
    JTextField proteins = new JTextField("0.0");
    JTextField fats = new JTextField("0.0");
    JTextField sodium = new JTextField("0.0");
    JTextField price = new JTextField("0.0");
    JTextArea items = new JTextArea();

    JButton search = new JButton("Search by");
    JButton order = new JButton("Place Order");
    JButton searchTitle = new JButton("Search by Title");
    List<MenuItem> prodComanda = new ArrayList<>();


    private DeliveryService deliveryService;


    public ClientGraphicalUserInterface(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setTitle("Client");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(title);
        panel.add(searchTitle);
        panel.add(search);
        panel.add(order);
        panel.add(parcurgere);
        panel.add(items);

        frame.add(panel);
        searchTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MenuItem> intermediar = new ArrayList<>();
                intermediar = deliveryService.searchProduct(title.getText());
                if (intermediar.size() == 1) {
                    for (MenuItem m : intermediar) {
                        prodComanda.add(m);
                    }
                }
                area.setText(intermediar.toString());
            }
        });
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order o = new Order(deliveryService.getOrderID(), 1, LocalDateTime.now());
                deliveryService.addOrder(o, (ArrayList<MenuItem>) prodComanda);
                items.setText(Bon.creareBon(o, prodComanda));
                deliveryService.addObs(o);

            }
        });
        search.addActionListener(new ActionListener() {
            JList<Object> list = new JList<>();
            JPanel pan = new JPanel();

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton searchcat = new JButton("searchcat");
                JFrame fr = new JFrame();

                JTable tabel = new JTable(0, 0);
                fr.setVisible(true);
                fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                fr.setSize(800, 800);

                pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
                pan.add(new JLabel("Rating"));
                pan.add(rating);
                pan.add(new JLabel("Calories"));
                pan.add(calories);
                pan.add(new JLabel("Proteins"));
                pan.add(proteins);
                pan.add(new JLabel("Fats"));
                pan.add(fats);
                pan.add(new JLabel("Sodium"));
                pan.add(sodium);
                pan.add(new JLabel("Price"));
                pan.add(price);
                //pan.add(items);
                pan.add(parcurgere1);
                pan.add(searchcat);

                searchcat.addActionListener(p -> {
                    List<MenuItem> products = deliveryService.searchProducts(rating.getText(), fats.getText(),
                            title.getText(), calories.getText(), proteins.getText(), sodium.getText(), price.getText());
                    area1.setText(products.toString());
//                    list=new JList<>(products.toArray());
//                    pan.add();
                    pan.revalidate();
                });
                fr.add(pan);
            }
        });

    }

    //    public void addButtonListener(ActionListener addadminButton) {
//        this.add.addActionListener(addadminButton);
//    }
    public void orderclientButtonListener(ActionListener orderclientButton) {
        this.order.addActionListener(orderclientButton);
    }

    public void searchclientButtonListener(ActionListener searchclientButton) {
        this.search.addActionListener(searchclientButton);
    }


}
