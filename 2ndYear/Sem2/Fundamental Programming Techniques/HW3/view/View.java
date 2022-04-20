package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame = new JFrame();
    private JPanel panelClients = new JPanel();
    private JPanel panelProductsOrders = new JPanel();
    private JPanel panelProducts = new JPanel();
    private JFrame FrameAfisareTabela = new JFrame();
    private JButton bon = new JButton("BON");

    private JTextField TextFieldIdClient = new JTextField();
    private JTextField TextFieldNumeClient = new JTextField();
    private JTextField TextFieldAdresaClient = new JTextField();
    private JTextField TextFieldEmail = new JTextField();
    private JButton inserareClient = new JButton("Inserare");
    private JButton editareClient = new JButton("Editare");
    private JButton stergereClient = new JButton("Stergere");
    private JButton tablaClient = new JButton("Afisare tabela");

    private JTextField TextFieldIdProducts = new JTextField();
    private JTextField TextFieldPretProducts = new JTextField();
    private JTextField TextFieldCantitateProducts = new JTextField();
    private JTextField TextFieldNumeProducts = new JTextField();
    private JButton inserareProducts = new JButton("Inserare");
    private JButton editareProducts = new JButton("Editare");
    private JButton stergereProducts = new JButton("Stergere");
    private JButton tabelaProducts = new JButton("Afisare tabela");

    private JTextField TextFieldIdProductsOrders = new JTextField();
    private JTextField TextFieldIdClientProductsOrders = new JTextField();
    private JTextField TextFieldIdProductOrdersProductsOrders = new JTextField();
    private JTextField TextFieldCantitateProductsOrders = new JTextField();
    private JButton inserareProductsOrders = new JButton("Inserare");
    private JButton editareProductsOrders  = new JButton("Editare");
    private JButton stergereProductsOrders  = new JButton("Stergere");
    private JButton TabelaProductsOrders = new JButton("Afisare tabela");
    //Products


    private JTable tabel = new JTable();

    public View() {

        frame.setLayout(new GridLayout(4, 0));
        frame.setSize(500, 1000);
        panelClients.setLayout(new GridLayout(3, 0));
        panelClients.add(new JLabel("Client"));
        panelClients.setBackground(Color.PINK);
        JPanel textFieldsClient = new JPanel();
        textFieldsClient.setLayout(new GridLayout(4, 2));
        textFieldsClient.add(new JLabel("id client= "));
        textFieldsClient.add(TextFieldIdClient);
        textFieldsClient.add(new JLabel("nume client= "));
        textFieldsClient.add(TextFieldNumeClient);
        textFieldsClient.add(new JLabel("adresa client= "));
        textFieldsClient.add(TextFieldAdresaClient);
        textFieldsClient.add(new JLabel("email client= "));
        textFieldsClient.add(TextFieldEmail);

        panelClients.add(textFieldsClient);
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(inserareClient );
        buttons.add(editareClient);
        buttons.add(stergereClient);
        buttons.add(tablaClient);
        panelClients.add(buttons);



        panelProducts.setLayout(new GridLayout(3, 0));
        panelProducts.setBackground(Color.PINK);
        panelProducts.add(new JLabel("Products"));
        JPanel textFieldsProducts = new JPanel();
        textFieldsProducts.setLayout(new GridLayout(4, 2));
        textFieldsProducts.add(new JLabel("id produs= "));
        textFieldsProducts.add(TextFieldIdProducts);
        textFieldsProducts.add(new JLabel("pret produs= "));
        textFieldsProducts.add(TextFieldPretProducts);
        textFieldsProducts.add(new JLabel("cantitate= "));
        textFieldsProducts.add(TextFieldCantitateProducts);
        textFieldsProducts.add(new JLabel("nume produs= "));
        textFieldsProducts.add(TextFieldNumeProducts);
        panelProducts.add(textFieldsProducts);
        JPanel buttons1 = new JPanel(new FlowLayout());
        buttons1.add(inserareProducts);
        buttons1.add(editareProducts );
        buttons1.add(stergereProducts);
        buttons1.add(tabelaProducts);
        panelProducts.add(buttons1);


        panelProductsOrders.setLayout(new GridLayout(3, 0));
        panelProductsOrders.setBackground(Color.PINK);
        panelProductsOrders.add(new JLabel("Productsorders"));
        JPanel textFieldsProductsOrders = new JPanel();
        textFieldsProductsOrders.setLayout(new GridLayout(4, 2));
        textFieldsProductsOrders.add(new JLabel("id produs = "));
        textFieldsProductsOrders.add(TextFieldIdProductsOrders);
        textFieldsProductsOrders.add(new JLabel("id client= "));
        textFieldsProductsOrders.add(TextFieldIdClientProductsOrders);
        textFieldsProductsOrders.add(new JLabel("id comanda= "));
        textFieldsProductsOrders.add(TextFieldIdProductOrdersProductsOrders);
        textFieldsProductsOrders.add(new JLabel("cantitate= "));
        textFieldsProductsOrders.add(TextFieldCantitateProductsOrders);
        panelProductsOrders.add(textFieldsProductsOrders);
        JPanel buttons2 = new JPanel(new FlowLayout());
        buttons2.add(inserareProductsOrders );
        buttons2.add(editareProductsOrders );
        buttons2.add(stergereProductsOrders);
        buttons2.add(TabelaProductsOrders);
        panelProductsOrders.add(buttons2);


        panelClients.setVisible(true);
        panelProducts.setVisible(true);
        panelProductsOrders.setVisible(true);

        panelClients.setSize(300, 300);
        panelProducts.setSize(300, 300);
        panelProductsOrders.setSize(300, 300);

        frame.setTitle("BAZA DE DATE");

        frame.add(panelClients);
        frame.add(panelProducts);
        frame.add(panelProductsOrders);

        JPanel buttons3 = new JPanel(new FlowLayout());
        buttons3.add(bon);
        buttons3.setBackground(Color.CYAN);
        buttons3.setSize(20, 20);
        frame.add(buttons3);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void tabelaFinala(Object[][] data, Object[] column) {
        FrameAfisareTabela = new JFrame();
        FrameAfisareTabela.setTitle("Afisare tabel");
        tabel = new JTable(data, column);
        JScrollPane sp = new JScrollPane(tabel);
        FrameAfisareTabela.add(sp);
        FrameAfisareTabela.setSize(500, 200);
        FrameAfisareTabela.setVisible(true);
    }


    public String getTextFieldIdClient() {
        return
                TextFieldIdClient.getText();
    }

    public String getTextFieldNumeClient() {
        return
                TextFieldNumeClient.getText();
    }

    public String getTextFieldAdresaClient() {

        return TextFieldAdresaClient.getText();
    }
    public String getTextFieldEmail() {

        return TextFieldEmail.getText();
    }

    public String getTextFieldIdProducts() {

        return TextFieldIdProducts.getText();
    }

    public String getTextFieldPretProducts() {

        return TextFieldPretProducts.getText();
    }

    public String getTextFieldCantitateProducts() {

        return TextFieldCantitateProducts.getText();
    }

    public String getTextFieldNumeProducts() {

        return TextFieldNumeProducts.getText();
    }

    public String getTextFieldIdProductsOrders() {

        return TextFieldIdProductsOrders.getText();
    }

    public String getTextFieldIdClientProductsOrders() {
        return TextFieldIdClientProductsOrders.getText();
    }

    public String getTextFieldIdProductOrdersProductsOrders() {

        return TextFieldIdProductOrdersProductsOrders.getText();
    }

    public String getTextFieldCantitateProductsOrders() {

        return TextFieldCantitateProductsOrders.getText();
    }

    public void inserareClientListener(ActionListener inserareClient) {
        this.inserareClient .addActionListener(inserareClient);
    }

    public void editareClientListener(ActionListener editareClient) {

        this.editareClient.addActionListener(editareClient);
    }

    public void stergereClientListener(ActionListener stergereClient) {
        this.stergereClient.addActionListener(stergereClient);
    }

    public void afisareClientListener(ActionListener afisareClient) {
        this.tablaClient.addActionListener(afisareClient);
    }

    public void inserareProductsListener(ActionListener inserareProducts) {
        this.inserareProducts.addActionListener(inserareProducts);
    }

    public void editareProductsListener(ActionListener editareProducts) {

        this.editareProducts .addActionListener(editareProducts);
    }

    public void stergereProductsListener(ActionListener stergereProducts) {
        this.stergereProducts.addActionListener(stergereProducts);
    }

    public void afisareProductsListener(ActionListener afisareProducts) {
        this.tabelaProducts.addActionListener(afisareProducts);
    }

    public void inserareProductsOrdersListener(ActionListener inserareProductsOrders) {
        this.inserareProductsOrders .addActionListener(inserareProductsOrders);
    }

    public void editareProductsOrdersListener(ActionListener editareProductsOrders) {

        this.editareProductsOrders .addActionListener(editareProductsOrders);
    }

    public void stergereOrdersListener(ActionListener stergereProductsOrders) {

        this.stergereProductsOrders.addActionListener(stergereProductsOrders);
    }

    public void afisareOrdersListener(ActionListener afisareProductsOrders) {
        this.TabelaProductsOrders.addActionListener(afisareProductsOrders);
    }

    public void bonTotalListener(ActionListener bon) {

        this.bon.addActionListener(bon);
    }


}
