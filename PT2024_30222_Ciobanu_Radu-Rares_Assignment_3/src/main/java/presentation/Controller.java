package presentation;

import bll.validators.ClientAgeValidator;
import bll.validators.EmailValidator;
import model.Bill;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.*;


public class Controller implements ActionListener {

    View mv ;
    Client orderClient;
    Product orderProduct;

    public Controller (View view) {

        this.mv = view;
        mv.getLoginButton().addActionListener(this);
        mv.getClientsButton().addActionListener(this);
        mv.getOrdersButton().addActionListener(this);
        mv.getProductsButton().addActionListener(this);
        mv.getOrderArchiveButton().addActionListener(this);
        mv.getSearchByElement().addActionListener(this);
        mv.getPlaceOrder().addActionListener(this);
        mv.getDeleteOrder().addActionListener(this);
        mv.getPlusQuantity().addActionListener(this);
        mv.getMinusQuantity().addActionListener(this);
        mv.getIncreaseStock().addActionListener(this);
        mv.getDecreaseStock().addActionListener(this);
        mv.getViewOrders().addActionListener(this);
        mv.getSendOrder().addActionListener(this);
        mv.getEditElementClient().addActionListener(this);
        mv.getAddElementClient().addActionListener(this);
        mv.getDeleteElementClient().addActionListener(this);
        mv.getEditElementOrder().addActionListener(this);
        mv.getAddElementOrder().addActionListener(this);
        mv.getDeleteElementOrder().addActionListener(this);
        mv.getEditElementProduct().addActionListener(this);
        mv.getAddElementProduct().addActionListener(this);
        mv.getDeleteElementProduct().addActionListener(this);
        mv.getOkAddClient().addActionListener(this);
        mv.getOkEditClient().addActionListener(this);
        mv.getSetClientNewOrderOk().addActionListener(this);
        mv.getOkQuantityButton().addActionListener(this);
        mv.getAddProductToOrderButton().addActionListener(this);
        mv.getOkQuantityChange().addActionListener(this);
        mv.getOkAddProduct().addActionListener(this);
        mv.getOkEditProduct().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mv.getLoginButton()){
            if(!mv.getEmailLoginTF().getText().equals("admin@gmail.com") || !mv.getPasswordLoginTF().getText().equals("admin")){
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Invalid Logger!",
                        "ERROR!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }else {
                mv.getLoginFrame().setVisible(false);
                mv.refreshPanelWhenClients(mv.getTablePanel(), mv.getOptionsForTablePanel());
                mv.getUserFrame().setVisible(true);
            }
        }
        if(e.getSource() == mv.getClientsButton()){
            mv.setTableData(mv.getClientsDao().getAll());
            mv.refreshPanelWhenClients(mv.getTablePanel(), mv.getOptionsForTablePanel());
        }
        if(e.getSource() == mv.getOrdersButton()){
            mv.setTableData(mv.getOrdersDao().getAll());
            mv.refreshPanelWhenOrders(mv.getTablePanel(), mv.getOptionsForTablePanel());
        }
        if(e.getSource() == mv.getProductsButton()){
            mv.setTableData(mv.getProductsDao().getAll());
            mv.refreshPanelWhenProducts(mv.getTablePanel(), mv.getOptionsForTablePanel());
        }
        if(e.getSource() == mv.getOrderArchiveButton()){
            mv.setTableData(mv.getBillDao().getAll());
            mv.refreshPanelWhenArchive(mv.getTablePanel(), mv.getOptionsForTablePanel());

        }
        if(e.getSource() == mv.getAddElementClient()){
            mv.getNameClientTF().setText("");
            mv.getAgeClientTF().setText("");
            mv.getEmailClientTF().setText("");
            mv.getAddClientFrame().setVisible(true);
        }
        if(e.getSource() == mv.getOkAddClient()){
            ClientAgeValidator ageValidator = new ClientAgeValidator();
            EmailValidator emailValidator = new EmailValidator();
            Client addClient = new Client(mv.getNameClientTF().getText(), mv.getEmailClientTF().getText(), parseInt(mv.getAgeClientTF().getText()));
            if(ageValidator.validate(addClient) && emailValidator.validate(addClient)) {

                mv.getClientsDao().add(addClient);
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Client added successfully",
                        "DONE!",
                        JOptionPane.INFORMATION_MESSAGE
                );
                mv.setTableData(mv.getClientsDao().getAll());
                mv.getAddClientFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mv.refreshPanelWhenClients(mv.getTablePanel(), mv.getOptionsForTablePanel());
                    }
                });
            }
            else {
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Email or age are not valid.",
                        "STOP!!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
        if(e.getSource() == mv.getDeleteElementClient()){
            mv.getClientsDao().delete(mv.getIdFromSelectedRow(mv.getClientsTable()));
            mv.setTableData(mv.getClientsDao().getAll());
            mv.refreshPanelWhenClients(mv.getTablePanel(), mv.getOptionsForTablePanel());
        }
        if(e.getSource() == mv.getEditElementClient()){
            if(mv.getClientsTable().getSelectedRow() != -1){
                mv.getEditClientNameTF().setText((String) mv.getClientsTable().getValueAt(mv.getClientsTable().getSelectedRow(), 1));
                mv.getEditClientEmailTF().setText((String) mv.getClientsTable().getValueAt(mv.getClientsTable().getSelectedRow(), 2));
                mv.getEditClientAgeTF().setText((mv.getClientsTable().getValueAt(mv.getClientsTable().getSelectedRow(), 3)).toString());
                mv.getEditClientFrame().setVisible(true);
            }
        }
        if(e.getSource() == mv.getOkEditClient()){

            ClientAgeValidator ageValidator = new ClientAgeValidator();
            EmailValidator emailValidator = new EmailValidator();
            Client selectedClient = mv.getClientFromSelectedRow(mv.getClientsTable());
            Client updatedClient = new Client(
                    selectedClient.getClientId(),
                    mv.getEditClientNameTF().getText(),
                    mv.getEditClientEmailTF().getText(),
                    parseInt(mv.getEditClientAgeTF().getText())
            );
            if(ageValidator.validate(updatedClient) && emailValidator.validate(updatedClient)) {
                mv.getClientsDao().update(selectedClient, updatedClient);
                mv.setTableData(mv.getClientsDao().getAll());
                mv.refreshPanelWhenClients(mv.getTablePanel(), mv.getOptionsForTablePanel());
                mv.getEditClientFrame().setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Email or age are not valid.",
                        "STOP!!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
        if(e.getSource() == mv.getAddElementOrder()){
            mv.setTableData(mv.getClientsDao().getAll());
            mv.refreshPanelWhenAddOrder(mv.getAddOrderTablePanel());
            mv.getAddOrderFrame().setVisible(true);

        }

        if(e.getSource() == mv.getSetClientNewOrderOk()){
            mv.setTableData(mv.getOrdersDao().getAll());
            mv.getAddProductToOrderQuantityTF().setText("");
            orderClient = mv.getClientFromSelectedRow(mv.getClientsTable());
            mv.setTableData(mv.getProductsDao().getAll());
            mv.refreshPanelWhenAddOrderProduct(mv.getAddOrderTablePanel());
        }
        if(e.getSource() == mv.getAddProductToOrderButton()){
            orderProduct = mv.getProductFromSelectedRow(mv.getProductsTable());
            if(parseInt(mv.getAddProductToOrderQuantityTF().getText()) <= 0 || parseInt(mv.getAddProductToOrderQuantityTF().getText()) > orderProduct.getProductStock()){
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Invalid Quantity!",
                        "STOP!!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            else{
                Product updatedProduct = new Product(
                        orderProduct.getProductId(),
                        orderProduct.getProductName(),
                        orderProduct.getProductPrice(),
                        (orderProduct.getProductStock() - parseInt(mv.getAddProductToOrderQuantityTF().getText()))
                );
                double totalPrice = (double) parseInt(mv.getAddProductToOrderQuantityTF().getText()) * orderProduct.getProductPrice();
                mv.getOrdersDao().add(new Orders(orderClient.getClientId(), orderProduct.getProductId(), parseInt(mv.getAddProductToOrderQuantityTF().getText()), totalPrice));
                mv.getProductsDao().update(orderProduct, updatedProduct);
                mv.setTableData(mv.getOrdersDao().getAll());
                mv.refreshPanelWhenOrders(mv.getTablePanel(), mv.getOptionsForTablePanel());
                mv.getAddOrderFrame().setVisible(false);
            }
        }
        if(e.getSource() == mv.getDeleteElementOrder()){
            Product checkProduct = mv.getProductsDao().getProductById((Integer) mv.getOrdersTable().getValueAt(mv.getOrdersTable().getSelectedRow(), 2));
            Orders unchangedOrder = mv.getOrderFromSelectedRow(mv.getOrdersTable());
            Product updatedProduct = new Product(
                    checkProduct.getProductId(),
                    checkProduct.getProductName(),
                    checkProduct.getProductPrice(),
                    (checkProduct.getProductStock() + unchangedOrder.getNumberOfProducts())
            );
            mv.getProductsDao().update(checkProduct, updatedProduct);
            mv.getOrdersDao().delete(mv.getIdFromSelectedRowOrders(mv.getOrdersTable()));
            mv.setTableData(mv.getOrdersDao().getAll());
            mv.refreshPanelWhenOrders(mv.getTablePanel(), mv.getOptionsForTablePanel());

        }
        if(e.getSource() == mv.getEditElementOrder()){
            if(mv.getOrdersTable().getSelectedRow() != -1){
                mv.getEditOrderQuantityTF().setText(mv.getOrdersTable().getValueAt(mv.getOrdersTable().getSelectedRow(), 3).toString());
                mv.getEditOrderFrame().setVisible(true);
            }
        }
        if(e.getSource() == mv.getOkQuantityChange()){
            Product checkProduct = mv.getProductsDao().getProductById((Integer) mv.getOrdersTable().getValueAt(mv.getOrdersTable().getSelectedRow(), 2));
            Orders unchangedOrder = mv.getOrderFromSelectedRow(mv.getOrdersTable());
            if(parseInt(mv.getEditOrderQuantityTF().getText()) <= 0 || parseInt(mv.getEditOrderQuantityTF().getText()) > (checkProduct.getProductStock() + unchangedOrder.getNumberOfProducts())){
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Invalid Quantity!",
                        "STOP!!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            else{
                Product updatedProduct = new Product(
                        checkProduct.getProductId(),
                        checkProduct.getProductName(),
                        checkProduct.getProductPrice(),
                        (checkProduct.getProductStock() + unchangedOrder.getNumberOfProducts() - parseInt(mv.getEditOrderQuantityTF().getText()))
                );
                double totalPrice = (double) parseInt(mv.getEditOrderQuantityTF().getText()) * checkProduct.getProductPrice();
                mv.getProductsDao().update(checkProduct, updatedProduct);
                Orders changedOrder = new Orders(
                        unchangedOrder.getIdClient(),
                        unchangedOrder.getOrderId(),
                        (Integer) mv.getOrdersTable().getValueAt(mv.getOrdersTable().getSelectedRow(), 2),
                        parseInt(mv.getEditOrderQuantityTF().getText()),
                        totalPrice
                );
                mv.getOrdersDao().update(unchangedOrder, changedOrder);
                mv.setTableData(mv.getOrdersDao().getAll());
                mv.refreshPanelWhenOrders(mv.getTablePanel(), mv.getOptionsForTablePanel());
                mv.getEditOrderFrame().setVisible(false);

            }
        }
        if(e.getSource() == mv.getAddElementProduct()){
            mv.getAddProductNameTF().setText("");
            mv.getAddProductPriceTF().setText("");
            mv.getAddProductStockTF().setText("");
            mv.getAddProuctFrame().setVisible(true);
        }
        if(e.getSource() == mv.getOkAddProduct()){
            if(parseDouble(mv.getAddProductPriceTF().getText()) > 0 && parseInt(mv.getAddProductStockTF().getText()) > 0) {
            Product addProduct = new Product(mv.getAddProductNameTF().getText(), parseDouble(mv.getAddProductPriceTF().getText()), parseInt(mv.getAddProductStockTF().getText()));

                mv.getProductsDao().add(addProduct);
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Product added successfully",
                        "DONE!",
                        JOptionPane.INFORMATION_MESSAGE
                );
                mv.setTableData(mv.getProductsDao().getAll());
                mv.getAddProuctFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        mv.refreshPanelWhenProducts(mv.getTablePanel(), mv.getOptionsForTablePanel());
                    }
                });
            }
            else {
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Invalid price or stock value.",
                        "STOP!!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        }
        if(e.getSource() == mv.getDeleteElementProduct()){

            mv.getProductsDao().delete(mv.getIdFromSelectedRow(mv.getProductsTable()));
            mv.setTableData(mv.getProductsDao().getAll());
            mv.refreshPanelWhenProducts(mv.getTablePanel(), mv.getOptionsForTablePanel());
        }
        if(e.getSource() == mv.getEditElementProduct()){

            if(mv.getProductsTable().getSelectedRow() != -1){
                mv.getEditProductNameTF().setText((String) mv.getProductsTable().getValueAt(mv.getProductsTable().getSelectedRow(), 1));
                mv.getEditProductPriceTF().setText(mv.getProductsTable().getValueAt(mv.getProductsTable().getSelectedRow(), 2).toString());
                mv.getEditProductStockTF().setText((mv.getProductsTable().getValueAt(mv.getProductsTable().getSelectedRow(), 3)).toString());
                mv.getEditProductFrame().setVisible(true);
            }

        }
        if(e.getSource() == mv.getOkEditProduct()){

            Product selectedProduct = mv.getProductFromSelectedRow(mv.getProductsTable());
            Product updatedProduct = new Product(
                    selectedProduct.getProductId(),
                    mv.getEditProductNameTF().getText(),
                    parseDouble(mv.getEditProductPriceTF().getText()),
                    parseInt(mv.getEditProductStockTF().getText())
            );
            if(parseDouble(mv.getEditProductPriceTF().getText()) > 0 && parseInt(mv.getEditProductStockTF().getText()) > 0) {
                mv.getProductsDao().update(selectedProduct, updatedProduct);
                mv.setTableData(mv.getProductsDao().getAll());
                mv.refreshPanelWhenProducts(mv.getTablePanel(), mv.getOptionsForTablePanel());
                mv.getEditProductFrame().setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Invalid stock or price value.",
                        "STOP!!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        }
        if(e.getSource() == mv.getViewOrders()){
            mv.setTableData(mv.getOrdersDao().getOrdersByClientId((Integer) mv.getClientsTable().getValueAt(mv.getClientsTable().getSelectedRow(), 0)));
            mv.refreshPanelWhenViewClientOrders(mv.getViewClientOrdersPanel());
            mv.getViewClientOrdersFrame().setVisible(true);
        }
        if(e.getSource() == mv.getSendOrder()){
            if(mv.getOrdersTable().getSelectedRow() != -1) {
                Orders selectedOrder = mv.getOrderFromSelectedRow(mv.getOrdersTable());
                int orderId = (int) mv.getOrdersTable().getValueAt(mv.getOrdersTable().getSelectedRow(), 1);
                Bill newBill = new Bill(orderId, selectedOrder.getIdClient() ,LocalDate.now(), selectedOrder.getTotalPrice());
                mv.getBillDao().add(newBill);
                mv.setTableData(mv.getBillDao().getAll());
                mv.getOrdersDao().delete(mv.getIdFromSelectedRowOrders(mv.getOrdersTable()));
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Order Sent!!.",
                        "Congrats!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            else{
                JOptionPane.showMessageDialog(mv.getUserFrame(),
                        "Failed to send order.",
                        "ERROR!!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }

    }
}

