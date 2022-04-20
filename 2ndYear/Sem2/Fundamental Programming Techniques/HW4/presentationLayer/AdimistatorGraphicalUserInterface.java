package presentationLayer;

import bussinessLayer.BaseProduct;
import bussinessLayer.CompositeProduct;
import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdimistatorGraphicalUserInterface extends JFrame {
    JFrame frame = new JFrame("Products");
    JTextArea area1 = new JTextArea();
    JScrollPane parcurgere1 = new JScrollPane(area1);
    List<MenuItem> prod;
    List<MenuItem> produse;
    JTextField title = new JTextField();
    JTextArea items = new JTextArea();
    JScrollPane par = new JScrollPane(items);
    JButton add = new JButton("Add food");
    JButton delete = new JButton("Delete food");
    JButton modify = new JButton("Modify the menu");
    JButton searchby = new JButton("Search the list");
    JButton showproduct = new JButton("View the list");
    JButton reports = new JButton("Reports");

    DeliveryService deliveryService;

    public AdimistatorGraphicalUserInterface(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setTitle("Administrator");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 7));
        panel.add(title);
        panel.add(add);
        panel.add(delete);
        panel.add(modify);
        panel.add(searchby);
        panel.add(showproduct);
        panel.add(reports);

        JTextField title = new JTextField();
        JTextField rating = new JTextField("0.0");
        JTextField calories = new JTextField("0.0");
        JTextField proteins = new JTextField("0.0");
        JTextField fats = new JTextField("0.0");
        JTextField sodium = new JTextField("0.0");
        JTextField price = new JTextField("0.0");

        frame.add(panel);
        add.addActionListener(new ActionListener() {
            JList<Object> list = new JList<>();
            JPanel pan = new JPanel();

            @Override
            public void actionPerformed(ActionEvent e) {
                produse = new ArrayList<>();
                JButton addMenu = new JButton("Add menu");
                JButton addToList = new JButton("Add to compose");
                JButton addMCompose = new JButton("Add compose prod");
                JFrame fr = new JFrame();
                fr.setVisible(true);
                fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                fr.setSize(800, 800);
                pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
                pan.add(new JLabel("Title"));
                pan.add(title);
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
                pan.add(parcurgere1);
                pan.add(addMenu);
                pan.add(addToList);
                pan.add(addMCompose);

                addToList.addActionListener(e1 -> {
                    for (MenuItem m : deliveryService.searchProduct(title.getText())) {
                        produse.add(m);
                    }
                    System.out.println(produse);
                });
                addMCompose.addActionListener(e1 -> {
                    CompositeProduct cp = new CompositeProduct(title.getText(), produse);


                    area1.setText(cp.toString());
                    pan.revalidate();
                    produse = new ArrayList<>();
                });
                addMenu.addActionListener(p -> {
                    MenuItem nou = deliveryService.addMenu(title.getText(), Float.parseFloat(rating.getText()), Float.parseFloat(calories.getText()),
                            Float.parseFloat(proteins.getText()), Float.parseFloat(fats.getText()), Float.parseFloat(sodium.getText()), Float.parseFloat(price.getText()));

                    area1.setText(nou.toString());
                    pan.revalidate();
                });

                fr.add(pan);
            }
        });
        reports.addActionListener(new ActionListener() {
            JButton buton1 = new JButton("Buton 1");
            JButton buton2 = new JButton("Buton 2");
            JButton buton3 = new JButton("Buton 3");
            JButton buton4 = new JButton("Buton 4");

            JTextField startHour = new JTextField("10");
            JTextField endHour = new JTextField("13");
            JTextField productNo = new JTextField();
            JTextField specificNo = new JTextField();
            JTextField amount = new JTextField();
            JTextField specificDate = new JTextField();

            JPanel pan = new JPanel();

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new JFrame();
                fr.setVisible(true);
                fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                fr.setSize(800, 800);
                pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
                pan.add(parcurgere1);

                pan.add(new JLabel("Start hour:"));
                pan.add(startHour);
                pan.add(new JLabel("End hour:"));
                pan.add(endHour);
                pan.add(new JLabel("Products ordered more: "));
                pan.add(productNo);
                pan.add(new JLabel("Specified number of times:"));
                pan.add(specificNo);
                pan.add(new JLabel("Amount:"));
                pan.add(amount);
                pan.add(new JLabel("Specified date: "));
                pan.add(specificDate);

                pan.add(buton1);
                pan.add(buton2);
                pan.add(buton3);
                pan.add(buton4);
                String tot = "";
                int i = 1;
                for (Map.Entry m : deliveryService.getOrders().entrySet()) {
                    tot += i + ". " + m.getKey() + "\n" + m.getValue() + "\n";
                    i++;
                }
                area1.setText(tot);

                buton1.addActionListener(e1 -> {
                    area1.setText(deliveryService.report1(startHour.getText(), endHour.getText()));
                });
                buton2.addActionListener(e1 -> {
                    area1.setText(deliveryService.report2(productNo.getText()));
                });
                buton3.addActionListener(e1 -> {
                    area1.setText(deliveryService.report3(specificNo.getText(), amount.getText()));
                });
                buton4.addActionListener(e1 -> {
                    area1.setText(deliveryService.report4(specificDate.getText()));
                });


                fr.add(pan);

            }
        });
        showproduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new JFrame();
                fr.setVisible(true);
                fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                fr.setSize(800, 800);
                JPanel pan = new JPanel();
                pan.setLayout(new GridLayout(1, 1));
                items.setText(deliveryService.getMenuItem().toString());
                pan.add(par);

                fr.add(pan);
            }
        });
        delete.addActionListener(new ActionListener() {
            JList<Object> list = new JList<>();
            JPanel pan = new JPanel();

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton searchTitle = new JButton("Search by title");
                JButton del = new JButton("Delete");
                JFrame fr = new JFrame();
                fr.setVisible(true);
                fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                fr.setSize(800, 800);
                pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
                pan.add(new JLabel("Title"));
                pan.add(title);
                pan.add(parcurgere1);
                pan.add(del);
                pan.add(searchTitle);

                del.addActionListener(p -> {
                    for (MenuItem m : prod) {
                        deliveryService.removeMenuItem(m);
                    }
                });

                searchTitle.addActionListener(p -> {
                    List<MenuItem> products = deliveryService.searchProduct(title.getText());
                    prod = new ArrayList<>();
                    prod = products;
                    area1.setText(products.toString());
//
                    pan.revalidate();
                });
                fr.add(pan);

            }
        });
        modify.addActionListener(new ActionListener() {
            JList<Object> list = new JList<>();
            JPanel pan = new JPanel();

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton modif = new JButton("Modify");
                JButton searchTitle = new JButton("Search by title");
                JFrame fr = new JFrame();
                fr.setVisible(true);
                fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                fr.setSize(800, 800);
                pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
                pan.add(new JLabel("Title"));
                pan.add(title);
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
                pan.add(parcurgere1);
                pan.add(modif);
                pan.add(searchTitle);

                modif.addActionListener(p -> {
                    for (MenuItem m : prod) {
                        deliveryService.editMenuItem(m, rating.getText(), fats.getText(),
                                calories.getText(), proteins.getText(), sodium.getText(), price.getText());
                    }
                    area1.setText(prod.toString());
                    pan.revalidate();
                });

                searchTitle.addActionListener(p -> {
                    List<MenuItem> products = deliveryService.searchProduct(title.getText());
                    area1.setText(products.toString());
                    prod = new ArrayList<>();
                    prod = products;
                    pan.revalidate();
                });
                fr.add(pan);

            }
        });
        searchby.addActionListener(new ActionListener() {
            JList<Object> list = new JList<>();
            JPanel pan = new JPanel();

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton searchcat = new JButton("searchcat");
                JButton searchTitle = new JButton("Search by title");
                JFrame fr = new JFrame();
                fr.setVisible(true);
                fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                fr.setSize(800, 800);
                pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
                pan.add(new JLabel("Title"));
                pan.add(title);
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
                pan.add(parcurgere1);
                pan.add(searchcat);
                pan.add(searchTitle);

                searchcat.addActionListener(p -> {
                    List<MenuItem> products = deliveryService.searchProducts(rating.getText(), fats.getText(),
                            title.getText(), calories.getText(), proteins.getText(), sodium.getText(), price.getText());
                    area1.setText(products.toString());
                    pan.revalidate();
                });

                searchTitle.addActionListener(p -> {
                    List<MenuItem> products = deliveryService.searchProduct(title.getText());
                    area1.setText(products.toString());
                    pan.revalidate();
                });
                fr.add(pan);
            }
        });


    }

}
