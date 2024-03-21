package presentation.controller;

import businessLogic.ClientBLL;
import businessLogic.InvoiceBLL;
import businessLogic.ProductBLL;
import dataAccess.ClientDAO;
import dataAccess.InvoiceDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Invoice;
import model.Product;
import presentation.view.ClientView;
import presentation.view.InvoiceView;
import presentation.view.OptionView;
import presentation.view.ProductView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class Controller{

    private ClientView clientView;
    private InvoiceView invoiceView;
    private ProductView productView;
    private OptionView optionView;
    private ClientBLL clientBLL;
    private InvoiceBLL invoiceBLL;
    private ProductBLL productBLL;

    public Controller() throws IllegalAccessException {
        //init the view
        optionView = new OptionView();
        clientView = new ClientView();
        invoiceView = new InvoiceView();
        productView = new ProductView();
        //init the bll Classes
        clientBLL = new ClientBLL();
        invoiceBLL = new InvoiceBLL();
        productBLL = new ProductBLL();

        //set the listeners
        clientView.addDeleteListener(new DeleteActionClient());
        clientView.addInsertListener(new CreateNewActionClient());
        clientView.addUpdateListener(new UpdateActionClient());
        productView.addDeleteListener(new DeleteActionProduct());
        productView.addInsertListener(new CreateNewActionProduct());
        productView.addUpdateListener(new UpdateActionProduct());
        invoiceView.addInsertListener(new CreateActionInvoice());
        optionView.addClientListener(new ClientShowAction());
        optionView.addInvoiceListener(new InvoiceShowAction());
        optionView.addProductListener(new ProductShowAction());

        loadTableClient();
        loadTableInvoice();
        loadTableProduct();
    }

    public void makeClientViewVisisble(){
        clientView.makeVisible();
    }
    public void makeProductViewVisible(){
        productView.makeVisible();
    }
    public void makeInvoiceViewVisible(){
        invoiceView.makeVisible();
    }

    //Listeners
    public class CreateNewActionClient implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createNewClientButtonPress();
        }
    }
    public class UpdateActionClient implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            updateClientButtonPress();
        }
    }
    public class DeleteActionClient implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            deleteClientButtonPress();
        }

    }
    public class CreateNewActionProduct implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            createNewProductButtonPress();
        }
    }
    public class UpdateActionProduct implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            updateProductButtonPress();
        }
    }
    public class DeleteActionProduct implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            deleteProductButtonPress();
        }
    }
    public class CreateActionInvoice implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            createInvoiceButtonPress();
        }
    }
    public class ClientShowAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            makeClientViewVisisble();
        }
    }
    public class ProductShowAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            makeProductViewVisible();
        }
    }
    public class InvoiceShowAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            makeInvoiceViewVisible();
        }
    }

    //ButtonPress methods with logic for sending the info from the view to the businessLogicLayer
    public void createNewClientButtonPress(){
        String name = clientView.getNameInput();
        String address = clientView.getAddressInput();
        int age = Integer.parseInt(clientView.getAgeInput());
        Client cus = new Client(0, name, age, address);
        clientBLL.insertClient(cus);
        clientView.clearFields();
        try {
            loadTableClient();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }

    }
    public void updateClientButtonPress(){
        String name = clientView.getNameInput();
        String address = clientView.getAddressInput();
        int age = Integer.parseInt(clientView.getAgeInput());
        int id = Integer.parseInt(clientView.getIDInput());
        Client cus = new Client(id, name, age, address);
        clientBLL.updateClient(cus);
        clientView.clearFields();
        try {
            loadTableClient();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteClientButtonPress(){
        int id = Integer.parseInt(clientView.getIDInput());
        clientBLL.deleteClient(id);
        clientView.clearFields();
        try {
            loadTableClient();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    public void createNewProductButtonPress(){

        String name = productView.getNameInput();
        int price = Integer.parseInt(productView.getPriceInput());
        int stock = Integer.parseInt(productView.getSupplyInput());
        Product pr = new Product(0, name, price, stock);
        productBLL.insertProduct(pr);
        productView.clearFields();
        try {
            loadTableProduct();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    public void updateProductButtonPress(){
        String name = productView.getNameInput();
        int price = Integer.parseInt(productView.getPriceInput());
        int stock = Integer.parseInt(productView.getSupplyInput());
        int id = Integer.parseInt(productView.getIDInput());
        Product pr = new Product(id, name, price, stock);
        productBLL.updateProduct(pr);
        productView.clearFields();
        try {
            loadTableProduct();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteProductButtonPress (){
        int id = Integer.parseInt(productView.getIDInput());
        productBLL.deleteProduct(id);
        productView.clearFields();
        try {
            loadTableProduct();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    public void createInvoiceButtonPress(){
        int idClient = Integer.parseInt(invoiceView.getIDClientInput());
        int idProduct = Integer.parseInt(invoiceView.getIDProductInput());
        int quantity = Integer.parseInt(invoiceView.getQuantityInput());
        Invoice invoice = new Invoice(0, idClient, idProduct, quantity);
        Product pr = productBLL.findProductById(idProduct);
        pr.setSupply(pr.getSupply() - quantity);
        productBLL.updateProduct(pr);
        invoiceBLL.insertInvoice(invoice);
        invoiceView.clearFields();
        try {
            loadTableInvoice();
            loadTableProduct();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    //Load methods for loading the data into the view tables
    public void loadTableClient() throws IllegalAccessException {
        clientView.setTableColumns(getDataClient(clientBLL.findAllClients()), getCols(Client.class));
    }
    public void loadTableProduct() throws IllegalAccessException {
        productView.setTableColumns(getDataProduct(productBLL.findAllProducts()), getCols(Product.class));
    }
    public void loadTableInvoice() throws IllegalAccessException {
        invoiceView.setTableColumns(getDataInvoice(invoiceBLL.findAllInvoices()), getCols(Invoice.class));
    }

    //Reflection methods
    public String[][] getDataClient(List<Client> list) throws IllegalAccessException {
        String[][] data = new String[list.size()][Client.class.getDeclaredFields().length];

        int rowIndex = 0;
        for(Client t:list) {
            int colIndex = 0;
            for (Field field : Client.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = null;
                value = field.get(t);
                data[rowIndex][colIndex] = String.valueOf(value);
                colIndex++;
            }
            rowIndex++;
        }
        return data;
    }
    public String[][] getDataProduct(List<Product> list) throws IllegalAccessException {
        String[][] data = new String[list.size()][Product.class.getDeclaredFields().length];

        int rowIndex = 0;
        for(Product t:list) {
            int colIndex = 0;
            for (Field field : Product.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = null;
                value = field.get(t);
                data[rowIndex][colIndex] = String.valueOf(value);
                colIndex++;
            }
            rowIndex++;
        }
        return data;
    }
    public String[][] getDataInvoice(List<Invoice> list) throws IllegalAccessException {
        String[][] data = new String[list.size()][Invoice.class.getDeclaredFields().length];

        int rowIndex = 0;
        for(Invoice t:list) {
            int colIndex = 0;
            for (Field field : Invoice.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = null;
                value = field.get(t);
                data[rowIndex][colIndex] = String.valueOf(value);
                colIndex++;
            }
            rowIndex++;
        }
        return data;
    }
    public String[] getCols(Class c){
        String[] cols;
        cols = new String[c.getDeclaredFields().length];
        int index = 0;
        for(Field field: c.getDeclaredFields())
        {
            cols[index] = field.getName();
            index++;
        }
        return cols;
    }

}
