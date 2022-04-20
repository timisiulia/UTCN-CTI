package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import model.*;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import bll.ClientBll;
import bll.OrderBll;
import bll.ProductBll;


public class Controller {
	protected static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    
    private View view;
    private ClientBll client_bll;
    private ProductBll product_bll;
    private OrderBll order_bll;

    public Controller(View view) {
        this.view = view;
        this.client_bll = new ClientBll();
        this.product_bll = new ProductBll();
        this.order_bll = new OrderBll();

        this.ascultatoriClient();
        this.ascultatoriProdus();
        this.ascultatoriComanda();
        this.view.bonTotalListener(new bonTotalActionListener());
    }

    public void ascultatoriClient() {
        view.inserareClientListener(new insertClientActionListener());
        view.editareClientListener(new editClientActionListener());
        view.stergereClientListener(new deleteClientActionListener());
        view.afisareClientListener(new displayClientActionListener());
    }

    public void ascultatoriProdus() {
        view.inserareProductsListener(new insertProductsActionListener());
        view.editareProductsListener(new editProductsListener());
        view.stergereProductsListener(new deleteProductsActionListener());
        view.afisareProductsListener(new displayProductsActionListener());

    }
    public void ascultatoriComanda() {
        view.inserareProductsOrdersListener(new insertProductsordersActionListener());
        view.editareProductsOrdersListener(new editProductsordersActionListener());
        view.stergereOrdersListener(new deleteProductsordersActionListener());
        view.afisareOrdersListener(new displayProductsordersActionListener());

    }

    public class bonTotalActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Productsorders> list = order_bll.selectall();
            for (Productsorders order : list) {
                Document document = new Document();
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("order" + order.getId() + ".pdf"));

                } catch (DocumentException | FileNotFoundException eroare) {
                    eroare.printStackTrace();
                }
                document.open();
                Font font = FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK);
                Chunk chunk = new Chunk("Numar bon:  " + order.getId(), font);
                try {
                    Products p = product_bll.findClientById(order.getId_order());
                    Client c = client_bll.findClientById(order.getId_client());
                    document.setPageCount(1);
                    document.add(chunk);
                    document.add(new Paragraph("Client cu numarul:" + c.getId() + " si numele: " + c.getName()));
                    document.add(new Paragraph("Cu domiciliul in: " + c.getAddress()));
                    document.add(new Paragraph("Cu adresa de email: " + c.getEmail()));
                    document.add(new Paragraph("Produs cu numarul: " + p.getId()));
                    document.add(new Paragraph("Nume produs:" + p.getName_product() + "  " + order.getQuantity_order() + "  bucati.\n Are de platit:  " + (order.getQuantity_order() * p.getPrice_product()) + "  lei "));

                } catch (DocumentException ee) {
                    ee.printStackTrace();
                } catch (Exception ex) {
                    ex.getMessage();
                }
                document.close();
            }
        }
    }

    public class insertClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            String name, address, email;

            id = Integer.parseInt(view.getTextFieldIdClient());
            name = view.getTextFieldNumeClient();
            address = view.getTextFieldAdresaClient();
            email = view.getTextFieldEmail();

            Client client = new Client(id, name, address, email);
            client_bll.insert(client);
        }
    }

    public class editClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            String name, address, email;

            id = Integer.parseInt(view.getTextFieldIdClient());
            name = view.getTextFieldNumeClient();
            address = view.getTextFieldAdresaClient();
            email = view.getTextFieldEmail();

            Client client = new Client(id, name, address, email);
            client_bll.update(client);

        }
    }

    public class deleteClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            String name, address, email;

            id = Integer.parseInt(view.getTextFieldIdClient());
            name = view.getTextFieldNumeClient();
            address = view.getTextFieldAdresaClient();
            email = view.getTextFieldEmail();

            Client client = new Client(id, name, address, email);
            client_bll.delete(client);
        }
    }

    public class displayClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] antet = headerTable(Client.class);
            Object[][] data = obtainData(Client.class, client_bll.selectall());
            view.tabelaFinala(data, antet);
        }
    }

    public class insertProductsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id_product, quantity;
            double price_product;
            String name_product;

            id_product = Integer.parseInt(view.getTextFieldIdProducts());
            quantity = Integer.parseInt(view.getTextFieldCantitateProducts());
            price_product = Double.parseDouble(view.getTextFieldPretProducts());
            name_product = view.getTextFieldNumeProducts();

            Products product = new Products(id_product, name_product, quantity, price_product);
            product_bll.insert(product);
        }
    }

    public class editProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id_product, quantity;
            double price_product;
            String name_product;

            id_product = Integer.parseInt(view.getTextFieldIdProducts());
            quantity = Integer.parseInt(view.getTextFieldCantitateProducts());
            price_product = Double.parseDouble(view.getTextFieldPretProducts());
            name_product = view.getTextFieldNumeProducts();

            Products product = new Products(id_product, name_product, quantity, price_product);
            product_bll.update(product);
        }
    }

    public class deleteProductsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id_product, quantity;
            double price_product;
            String name_product;

            id_product = Integer.parseInt(view.getTextFieldIdProducts());
            quantity = Integer.parseInt(view.getTextFieldCantitateProducts());
            price_product = Double.parseDouble(view.getTextFieldPretProducts());
            name_product = view.getTextFieldNumeProducts();

            Products product = new Products(id_product, name_product, quantity, price_product);
            product_bll.delete(product);

        }
    }

    public class displayProductsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] antet = headerTable(Products.class);
            Object[][] data = obtainData(Products.class, product_bll.selectall());
            view.tabelaFinala(data, antet);
        }
    }

    public class insertProductsordersActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id_order, id_client, product_order, quantity_order;

            id_order = Integer.parseInt(view.getTextFieldIdProductsOrders());
            id_client = Integer.parseInt(view.getTextFieldIdClientProductsOrders());
            product_order = Integer.parseInt(view.getTextFieldIdProductOrdersProductsOrders());
            quantity_order = Integer.parseInt(view.getTextFieldCantitateProductsOrders());
            Productsorders order = new Productsorders(id_order, id_client, product_order, quantity_order);
            order_bll.insert(order);
        }
    }

    public class editProductsordersActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id_order, id_client, product_order, quantity_order;

            id_order = Integer.parseInt(view.getTextFieldIdProductsOrders());
            id_client = Integer.parseInt(view.getTextFieldIdClientProductsOrders());
            product_order = Integer.parseInt(view.getTextFieldIdProductOrdersProductsOrders());
            quantity_order = Integer.parseInt(view.getTextFieldCantitateProductsOrders());

            Productsorders order = new Productsorders(id_order, id_client, product_order, quantity_order);
            order_bll.update(order);
        }
    }

    public class deleteProductsordersActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id_order, id_client, product_order, quantity_order;

            id_order = Integer.parseInt(view.getTextFieldIdProductsOrders());
            id_client = Integer.parseInt(view.getTextFieldIdClientProductsOrders());
            product_order = Integer.parseInt(view.getTextFieldIdProductOrdersProductsOrders());
            quantity_order = Integer.parseInt(view.getTextFieldCantitateProductsOrders());

            Productsorders order = new Productsorders(product_order, id_client, id_order, quantity_order);
            order_bll.delete(order);
        }
    }

    public class displayProductsordersActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] antet = headerTable(Productsorders.class);
            Object[][] data = obtainData(Productsorders.class, order_bll.selectall());
            view.tabelaFinala(data, antet);
        }
    }

    private Object[] headerTable(Class<?> type){
        Object[] antet = new Object[type.getDeclaredFields().length];
        Arrays.stream(type.getDeclaredFields()).map(Field::getName).collect(Collectors.toList()).toArray(antet);
        return antet;
    }

    public Object[][] obtainData(Class<?> type, Collection<?> data){
        Field[] fields = type.getDeclaredFields();
        Object[][] table = new Object[data.size()][fields.length];
        AtomicInteger i = new AtomicInteger(0);
        AtomicInteger j = new AtomicInteger(0);
        data.stream().forEach(element -> {
            j.set(-1);
            Arrays.stream(type.getDeclaredFields()).forEach(field -> {
                field.setAccessible(true);
                try {
                    table[i.get()][j.incrementAndGet()] = field.get(element);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            i.incrementAndGet();
        });
        return table;
    }

}
