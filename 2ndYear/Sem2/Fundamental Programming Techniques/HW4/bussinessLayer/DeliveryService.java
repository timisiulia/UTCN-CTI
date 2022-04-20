package bussinessLayer;

import dataLayer.Serializable;
import model.Cont;
import presentationLayer.EmployeeGraphicalUserInterface;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private HashSet<MenuItem> menu;
    private HashMap<Order, ArrayList<MenuItem>> order;
    private HashSet<Cont> client;
    private Observer observer = new EmployeeGraphicalUserInterface();

    Serializable ser = new Serializable();

    public DeliveryService() throws IOException {
        menu = new HashSet<>();
        order = new HashMap<>();
        client = new HashSet<>();
        menu = (HashSet<MenuItem>) ser.deserializare("product.txt");
        order = (HashMap<Order, ArrayList<MenuItem>>) ser.deserializare("order.txt");
        client = (HashSet<Cont>) ser.deserializare("client.txt");
        if (order == null) order = new HashMap<>();

    }

    public HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return order;
    }

    public HashSet<MenuItem> getMenuItem() {
        return menu;
    }

    public void importProducts() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("products.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        menu = (HashSet<MenuItem>) buffer.lines().skip(1).map(p -> {
            String delim = ",";
            String[] info = p.split(delim);
            MenuItem menuitem = new BaseProduct(info[0], Float.parseFloat(info[1]), Float.parseFloat(info[2]), Float.parseFloat(info[3]), Float.parseFloat(info[4]),
                    Float.parseFloat(info[5]), Float.parseFloat(info[6]));
            return menuitem;
        }).collect(Collectors.toSet());
    }

    @Override
    public void updateProducts() {

    }


    public void addOrder(Order o, ArrayList<MenuItem> food) {
        Serializable ser = new Serializable();
        this.order.put(o, food);
        ser.serializare("order.txt", order);
        assert isWellFormed();

    }

    public void addMenuItem(MenuItem m) {
        Serializable ser = new Serializable();
        menu.add(m);
        ser.serializare("product.txt", menu);
        assert isWellFormed();


    }

    public void addClient(Cont c) {
        Serializable ser = new Serializable();
        client.add(c);
        ser.serializare("clients.txt", client);
        assert isWellFormed();


    }

    public void addObs(Order O) {
        observer.update(this, O);
    }

    public MenuItem addMenu(String title, float rating, float calories, float proteins, float fats, float sodium, float price) {
        MenuItem men = new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
        this.addMenuItem(men);
        return men;
    }

    public void removeMenuItem(MenuItem mm) {
        menu.remove(mm);
        Serializable ser = new Serializable();
        ser.serializare("product.txt", menu);
        assert isWellFormed();
    }

    public void editMenuItem(MenuItem mm, String rating, String fats, String calories, String proteins, String sodium, String price) {
        mm.setRating(Float.parseFloat(rating));
        mm.setFats(Float.parseFloat(fats));
        mm.setCalories(Float.parseFloat(calories));
        mm.setProteins(Float.parseFloat(proteins));
        mm.setSodium(Float.parseFloat(sodium));
        mm.setPrice(Float.parseFloat(price));
        Serializable ser = new Serializable();
        ser.serializare("product.txt", menu);
        assert isWellFormed();
    }

    public int getOrderID() {
        return order.size() + 1;
    }

    public ArrayList<MenuItem> searchProduct(String title) {
        ArrayList<MenuItem> list = new ArrayList<MenuItem>();
        if (title.equals("")) list = (ArrayList<MenuItem>) menu.stream().collect(Collectors.toList());
        else
            list = (ArrayList<MenuItem>) menu.stream().filter(m -> (m.getTitle().equals(title))).collect(Collectors.toList());
        return list;
    }

    public ArrayList<MenuItem> searchProducts(String rating, String fats, String title, String calories, String proteins, String sodium, String price) {
        //
        ArrayList<MenuItem> list = new ArrayList<MenuItem>();
        Float fat = 0.0f;
        Float rat = 0.0f;
        String tit = "";
        Float cal = 0.0f;
        Float pr = 0.0f;
        Float sod = 0.0f;
        Float prc = 0.0f;


        try {
            rat = Float.parseFloat(rating);
            fat = Float.parseFloat(fats);
            cal = Float.parseFloat(calories);
            sod = Float.parseFloat(proteins);
            pr = Float.parseFloat(sodium);
            prc = Float.parseFloat(price);
        } catch (Exception exception) {

        }

        Float finalRat = rat;
        Float finalFat = fat;
        Float finalCal = cal;
        Float finalSod = sod;
        Float finalPr = pr;
        Float finalPrc = prc;
        list = (ArrayList<MenuItem>) menu.stream().filter(m -> (m.getRating() == (finalRat) || finalRat == 0) &&
                (m.getFats() == finalFat || finalFat == 0) &&
                (m.getCalories() == finalCal || finalCal == 0) &&
                (m.getSodium() == finalSod || finalSod == 0) &&
                (m.getProteins() == finalPr || finalPr == 0) &&
                (m.getPrice() == finalPrc || finalPrc == 0)
        ).collect(Collectors.toList());
        return list;

    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public String report1(String start, String end) {

        int startHour = Integer.parseInt(start), endHour = Integer.parseInt(end);
        String total = "";
        total = order.keySet().stream().filter(order1 ->
                order1.getOrderDate().getHour() >= startHour &&
                        order1.getOrderDate().getHour() <= endHour)
                .collect(Collectors.toList()).toString();
        return total;
    }

    public String report2(String numarDeOri) {
        int nDO = Integer.parseInt(numarDeOri);

        Map<String, Long> listarap2 = order.entrySet().stream().map(p -> p.getValue())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(MenuItem::getTitle, Collectors.counting()));
        return listarap2.entrySet().stream().filter(stringLongEntry -> stringLongEntry.getValue() >= nDO).collect(Collectors.toList()).toString();

    }

    public float sumProd(List<MenuItem> list) {
        float s = 0.0f;
        for (MenuItem m : list)
            s += m.getPrice();
        return s;
    }

    public String report3(String nOfTimes, String price) {
        int nOT = Integer.parseInt(nOfTimes), pret = Integer.parseInt(price);
        Map<String, Long> listarap3 = order.entrySet().stream().filter(orderArrayListEntry -> sumProd(orderArrayListEntry.getValue()) >= pret)
                .map(orderArrayListEntry -> orderArrayListEntry.getKey())
                .collect(Collectors.groupingBy(Order::getClient, Collectors.counting()));
        return listarap3.entrySet().stream().filter(stringLongEntry -> stringLongEntry.getValue() >= nOT).collect(Collectors.toList()).toString();

    }

    public String report4(String data) {
        int dataDeFolosit = Integer.parseInt(data);

        return order.entrySet().stream().filter(orderArrayListEntry -> orderArrayListEntry.getKey().getOrderDate().getDayOfMonth() == dataDeFolosit)
                .map(orderArrayListEntry -> orderArrayListEntry.getValue()).flatMap(Collection::stream)
                .collect(Collectors.groupingBy(MenuItem::getTitle, Collectors.counting())).toString();
    }

    @Override
    public boolean isWellFormed() {
        return order != null || menu != null || false;
    }
}
