package presentation;

import dao.ClientsDao;
import dao.OrdersDao;
import dao.ProductsDao;
import dao.BillDao;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;


public class View {
    private JButton clientsButton;
    private JButton ordersButton;
    private JButton productsButton;
    private JButton orderArchiveButton;
    private JButton searchByElement;
    private JButton loginButton;
    private JButton placeOrder;
    private JButton deleteOrder;
    private JButton plusQuantity;
    private JButton minusQuantity;
    private JButton increaseStock;
    private JButton decreaseStock;
    private JButton viewOrders;
    private JButton sendOrder;
    private JButton editElementClient;
    private JButton addElementClient;
    private JButton deleteElementClient;
    private JButton addElementOrder;
    private JButton deleteElementOrder;
    private JButton editElementOrder;
    private JButton addElementProduct;
    private JButton deleteElementProduct;
    private JButton editElementProduct;


    private JTextField searchTF;
    private JTextField emailLoginTF;
    private JPasswordField passwordLoginTF;

    private JLabel emailLabel;
    private JLabel passwordLabel;

    private JPanel loginPanel;
    private JPanel mainPanel;
    private JPanel userPanel;
    private JPanel tablePanel;
    private JPanel optionsForTablePanel;
    private JPanel optionsForUserPanel;
    private JPanel totalRightPanel;
    private JPanel allPanel;

    private ImagePanel pictureUser;

    private JFrame loginFrame;
    private JFrame userFrame;


    private JFrame addOrderFrame;
    private JPanel addOrderTablePanel;
    private JPanel addOrderTablePanelMain;
    private JButton addProductToOrderButton;
    private JButton setClientNewOrderOk;
    private JButton okQuantityButton;
    private JLabel addProductToOrderQuantityLabel;
    private JTextField addProductToOrderQuantityTF;




    private JFrame addProuctFrame;
    private JLabel addProductNameLabel;
    private JTextField addProductNameTF;
    private JLabel addProductPriceLabel;
    private JTextField addProductPriceTF;
    private JLabel addProductStockLabel;
    private JTextField addProductStockTF;
    private JPanel addProductPanel;
    private JPanel mainAddProductPanel;
    private JButton okAddProduct;



    private JFrame addClientFrame;


    private JFrame editClientFrame;
    private JLabel editClientNameLabel;
    private JTextField editClientNameTF;
    private JLabel editClientEmailLabel;
    private JTextField editClientEmailTF;
    private JLabel editClientAgeLabel;
    private JTextField editClientAgeTF;
    private JPanel editClientPanel;
    private JPanel mainEditClientPanel;
    private JButton okEditClient;


    private JFrame editProductFrame;
    private JLabel editProductNameLabel;
    private JTextField editProductNameTF;
    private JLabel editProductPriceLabel;
    private JTextField editProductPriceTF;
    private JLabel editProductStockLabel;
    private JTextField editProductStockTF;
    private JPanel editProductPanel;
    private JPanel mainEditProductPanel;
    private JButton okEditProduct;



    private JFrame editOrderFrame;
    private JLabel editOrderQuantityLabel;
    private JTextField editOrderQuantityTF;
    private JPanel editOrderPanel;
    private JPanel mainEditOrderPanel;
    private JButton okQuantityChange;


    private JFrame viewClientOrdersFrame;
    private JPanel viewClientOrdersPanel;
    private JPanel mainViewClientOrdersPanel;

    private JLabel nameClientLable;
    private JLabel ageClientLable;
    private JLabel emailClientLable;
    private JPanel addClientPanel;
    private JPanel mainAddClientPanel;

    private JTextField nameClientTF;
    private JTextField ageClientTF;
    private JTextField emailClientTF;
    private JButton okAddClient;


    public Object[][] tableData ;

    private JTable clientsTable;
    private DefaultTableModel cT;
    private JTable ordersTable;
    private DefaultTableModel oT;
    private JTable productsTable;
    private DefaultTableModel pT;
    private JTable archiveTable;
    private DefaultTableModel aT;
    private JScrollPane archiveScrollPane;
    private JScrollPane clientsScrollPane;
    private JScrollPane productsScrollPane;
    private JScrollPane ordersScrollPane;

    private ProductsDao productsDao;
    private ClientsDao clientsDao;
    private OrdersDao ordersDao;
    private BillDao billDao;

    public void beautifyButton(JButton button){

        button.setFocusPainted(false);
        button.setBackground(new Color(124, 77, 147));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(212, 135, 231));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(124, 77, 147));
            }
        });
        button.setMaximumSize(new Dimension( 100, 25));
        button.setFont(button.getFont().deriveFont(Font.BOLD));
    }

    public void tableProperties(JTable table){

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setColumnSelectionAllowed(false);

        table.setBackground(new Color(139, 95, 147, 215));
        table.setSelectionBackground(new Color(236, 217, 217, 215));
        table.setForeground(Color.WHITE);

        table.setShowGrid(true);
        table.setGridColor(new Color(139, 95, 147, 215));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(98, 59, 115));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD));

    }


    public void refreshPanelWhenAddOrderProduct(JPanel panel1){
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        panel1.setLayout(new BoxLayout(addOrderTablePanel,BoxLayout.Y_AXIS));
        String[] productsCl = {"Product ID", "Product Name", "Price", "Stock"};
        pT = new DefaultTableModel(tableData,productsCl);
        productsTable = new JTable(pT);
        tableProperties(productsTable);
        productsScrollPane = new JScrollPane(productsTable);
        productsScrollPane.setBorder(null);
        productsScrollPane.setViewportBorder(null);
        productsScrollPane.setBackground(new Color(139, 95, 147, 215));
        productsScrollPane.getViewport().setBackground(new Color(123, 63, 136, 215));
        productsScrollPane.setPreferredSize(new Dimension(490, 380));
        panel1.add(productsScrollPane);
        panel1.add(Box.createRigidArea(new Dimension(0, 20)));
        panel1.add(addProductToOrderQuantityLabel);
        panel1.add(Box.createRigidArea(new Dimension(0, 20)));
        addProductToOrderQuantityTF.setSize(new Dimension(100, 20));
        panel1.add(addProductToOrderQuantityTF);
        panel1.add(Box.createRigidArea(new Dimension(0, 20)));
        panel1.add(addProductToOrderButton);

    }

    public void refreshPanelWhenViewClientOrders(JPanel panel1){
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] ordersCl = {"ClientID", "Order ID", "Product ID", "Number Of Products", "Total Price"};
        oT = new DefaultTableModel(tableData,ordersCl);
        ordersTable = new JTable(oT);
        tableProperties(ordersTable);
        ordersScrollPane = new JScrollPane(ordersTable);
        ordersScrollPane.setBorder(null);
        ordersScrollPane.setViewportBorder(null);
        ordersScrollPane.setBackground(new Color(139, 95, 147, 215));
        ordersScrollPane.getViewport().setBackground(new Color(123, 63, 136, 215));
        ordersScrollPane.setPreferredSize(new Dimension(500, 500));
        panel1.add(ordersScrollPane);
    }

    public void refreshPanelWhenAddOrder(JPanel panel1){
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        panel1.setLayout(new BoxLayout(addOrderTablePanel,BoxLayout.Y_AXIS));
        String[] clientsCl = {"Client ID", "Client Name", "Email", "Age"};
        cT = new DefaultTableModel(tableData,clientsCl);
        clientsTable = new JTable(cT);
        tableProperties(clientsTable);
        clientsScrollPane = new JScrollPane(clientsTable);
        clientsScrollPane.setBorder(null);
        clientsScrollPane.setViewportBorder(null);
        clientsScrollPane.setBackground(new Color(139, 95, 147, 215));
        clientsScrollPane.getViewport().setBackground(new Color(123, 63, 136, 215));
        clientsScrollPane.setPreferredSize(new Dimension(490, 380));
        panel1.add(clientsScrollPane);
        panel1.add(Box.createRigidArea(new Dimension(0, 20)));
        panel1.add(setClientNewOrderOk);
    }

    public void refreshPanelWhenClients(JPanel panel1, JPanel panel2){
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] clientsCl = {"Client ID", "Client Name", "Email", "Age"};
        cT = new DefaultTableModel(tableData,clientsCl);
        clientsTable = new JTable(cT);
        tableProperties(clientsTable);
        clientsScrollPane = new JScrollPane(clientsTable);
        clientsScrollPane.setBorder(null);
        clientsScrollPane.setViewportBorder(null);
        clientsScrollPane.setBackground(new Color(139, 95, 147, 215));
        clientsScrollPane.getViewport().setBackground(new Color(123, 63, 136, 215));
        clientsScrollPane.setPreferredSize(new Dimension(490, 380));
        panel1.add(clientsScrollPane);
        panel1.add(viewOrders);

        panel2.removeAll();
        panel2.revalidate();
        panel2.repaint();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.add(addElementClient);
        panel2.add(deleteElementClient);
        panel2.add(editElementClient);
        panel2.add(searchTF);
        panel2.add(searchByElement);
    }

    public void refreshPanelWhenOrders(JPanel panel1, JPanel panel2){
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] ordersCl = {"Client ID", "Order ID", "Product ID", "Number Of Products", "Total Price"};
        oT = new DefaultTableModel(tableData,ordersCl);
        ordersTable = new JTable(oT);
        tableProperties(ordersTable);
        ordersScrollPane = new JScrollPane(ordersTable);
        ordersScrollPane.setBorder(null);
        ordersScrollPane.setViewportBorder(null);
        ordersScrollPane.setBackground(new Color(139, 95, 147, 215));
        ordersScrollPane.getViewport().setBackground(new Color(123, 63, 136, 215));
        ordersScrollPane.setPreferredSize(new Dimension(490, 380));
        panel1.add(ordersScrollPane);
        panel1.add(sendOrder);

        panel2.removeAll();
        panel2.revalidate();
        panel2.repaint();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.add(addElementOrder);
        panel2.add(deleteElementOrder);
        panel2.add(editElementOrder);
        panel2.add(searchTF);
        panel2.add(searchByElement);
    }

    public void refreshPanelWhenProducts(JPanel panel1, JPanel panel2){

        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] productsCl = {"Product ID", "Product Name", "Price", "Stock"};
        pT = new DefaultTableModel(tableData,productsCl);
        productsTable = new JTable(pT);
        tableProperties(productsTable);
        productsScrollPane = new JScrollPane(productsTable);
        productsScrollPane.setBorder(null);
        productsScrollPane.setViewportBorder(null);
        productsScrollPane.setBackground(new Color(139, 95, 147, 215));
        productsScrollPane.getViewport().setBackground(new Color(123, 63, 136, 215));
        productsScrollPane.setPreferredSize(new Dimension(490, 380));
        panel1.add(productsScrollPane);

        panel2.removeAll();
        panel2.revalidate();
        panel2.repaint();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.add(addElementProduct);
        panel2.add(deleteElementProduct);
        panel2.add(editElementProduct);
        panel2.add(searchTF);
        panel2.add(searchByElement);
    }

    public void refreshPanelWhenArchive(JPanel panel1, JPanel panel2){
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] archiveCl = {"Order ID", "ClientId","Date", "Total"};
        aT = new DefaultTableModel(tableData,archiveCl);
        archiveTable = new JTable(aT);
        tableProperties(archiveTable);
        archiveScrollPane = new JScrollPane(archiveTable);
        archiveScrollPane.setBorder(null);
        archiveScrollPane.setViewportBorder(null);
        archiveScrollPane.setBackground(new Color(139, 95, 147, 215));
        archiveScrollPane.getViewport().setBackground(new Color(123, 63, 136, 215));
        panel1.add(archiveScrollPane);
        archiveScrollPane.setPreferredSize(new Dimension(490, 380));

        panel2.removeAll();
        panel2.revalidate();
        panel2.repaint();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.add(searchTF);
        panel2.add(searchByElement);

    }

    public View (ClientsDao clientsDao, OrdersDao ordersDao, ProductsDao productsDao, BillDao billDao){
        this.clientsDao = clientsDao;
        this.ordersDao = ordersDao;
        this.productsDao = productsDao;
        this.billDao = billDao;

        initializeComponents();
    }

    public void initializeComponents (){

        loginFrame = new JFrame("Warehouse Management");
        addOrderFrame = new JFrame("Add Order");
        editOrderFrame = new JFrame("Edit Order");
        addOrderTablePanel = new JPanel();
        addOrderTablePanelMain = new JPanel();
        editClientFrame = new JFrame("Edit Client!");
        loginPanel = new JPanel();
        mainPanel = new JPanel();
        userFrame = new JFrame("User Activities");
        addClientFrame = new JFrame("Add Client");
        userPanel = new JPanel();
        addClientPanel = new JPanel();
        mainAddClientPanel = new JPanel();
        tablePanel = new JPanel();
        optionsForTablePanel = new JPanel();
        optionsForUserPanel = new JPanel();
        totalRightPanel = new JPanel();
        allPanel = new JPanel();
        mainEditClientPanel = new JPanel();
        editClientPanel = new JPanel();
        okEditClient = new JButton("Ok");
        addProuctFrame = new JFrame("Add Product");
        addProductPanel = new JPanel();
        mainAddProductPanel = new JPanel();
        editProductFrame = new JFrame("Edit Product");
        editProductPanel = new JPanel();
        mainEditProductPanel = new JPanel();
        viewClientOrdersPanel = new JPanel();
        mainViewClientOrdersPanel = new JPanel();
        viewClientOrdersFrame = new JFrame("Orders");


        pictureUser = new ImagePanel("C:\\Users\\fdrff\\IdeaProjects\\PT2024_30222_Ciobanu_Radu-Rares_Assignment_2\\Queue.png", 200, 100);

        emailLabel = new JLabel("Enter Email");
        passwordLabel = new JLabel("Enter Password");


        nameClientLable = new JLabel("Nume");
        ageClientLable = new JLabel("Age");
        emailClientLable = new JLabel("Email");
        nameClientTF = new JTextField();
        ageClientTF = new JTextField();
        emailClientTF = new JTextField();

        nameClientLable.setPreferredSize(new Dimension(100, 25));
        nameClientLable.setForeground(Color.WHITE);
        nameClientLable.setBackground(new Color(101, 8, 145));
        ageClientLable.setPreferredSize(new Dimension(100, 25));
        ageClientLable.setBackground(new Color(101, 8, 145));
        ageClientLable.setForeground(Color.WHITE);
        emailClientLable.setPreferredSize(new Dimension(100, 25));
        emailClientLable.setBackground(new Color(101, 8, 145));
        emailClientLable.setForeground(Color.WHITE);

        nameClientTF.setPreferredSize(new Dimension(150, 25));
        nameClientTF.setBorder(null);
        nameClientTF.setBackground(new Color(167, 126, 180));
        ageClientTF.setPreferredSize(new Dimension(150, 25));
        ageClientTF.setBorder(null);
        ageClientTF.setBackground(new Color(167, 126, 180));
        emailClientTF.setPreferredSize(new Dimension(150, 25));
        emailClientTF.setBorder(null);
        emailClientTF.setBackground(new Color(167, 126, 180));

        editClientNameLabel = new JLabel("Nume");
        editClientAgeLabel = new JLabel("Age");
        editClientEmailLabel = new JLabel("Email");
        editClientNameTF = new JTextField();
        editClientAgeTF  = new JTextField();
        editClientEmailTF = new JTextField();

        editClientNameLabel.setPreferredSize(new Dimension(100, 25));
        editClientNameLabel.setForeground(Color.WHITE);
        editClientNameLabel.setBackground(new Color(101, 8, 145));
        editClientAgeLabel.setPreferredSize(new Dimension(100, 25));
        editClientAgeLabel.setBackground(new Color(101, 8, 145));
        editClientAgeLabel.setForeground(Color.WHITE);
        editClientEmailLabel.setPreferredSize(new Dimension(100, 25));
        editClientEmailLabel.setBackground(new Color(101, 8, 145));
        editClientEmailLabel.setForeground(Color.WHITE);

        editClientNameTF.setPreferredSize(new Dimension(150, 25));
        editClientNameTF.setBorder(null);
        editClientNameTF.setBackground(new Color(167, 126, 180));
        editClientAgeTF.setPreferredSize(new Dimension(150, 25));
        editClientAgeTF.setBorder(null);
        editClientAgeTF.setBackground(new Color(167, 126, 180));
        editClientEmailTF.setPreferredSize(new Dimension(150, 25));
        editClientEmailTF.setBorder(null);
        editClientEmailTF.setBackground(new Color(167, 126, 180));


        loginButton = new JButton("Login");
        clientsButton = new JButton("Clients");
        ordersButton = new JButton("Orders");
        productsButton = new JButton("Products");
        orderArchiveButton = new JButton("Achive");
        searchByElement = new JButton("Search");
        placeOrder = new JButton("Place Order");
        deleteOrder = new JButton("Delete Order");
        plusQuantity = new JButton("+");
        minusQuantity = new JButton("-");
        increaseStock = new JButton("+");
        decreaseStock = new JButton("-");
        viewOrders = new JButton("View Orders");
        sendOrder = new JButton("Send Order");
        editElementClient = new JButton("Edit");
        addElementClient = new JButton("Add");
        deleteElementClient = new JButton("Delete");
        editElementOrder = new JButton("Edit");
        addElementOrder = new JButton("Add");
        deleteElementOrder = new JButton("Delete");
        editElementProduct = new JButton("Edit");
        addElementProduct = new JButton("Add");
        deleteElementProduct = new JButton("Delete");
        okAddClient = new JButton("Ok");
        okAddProduct = new JButton("OK");
        okEditProduct = new JButton("OK");

        emailLoginTF = new JTextField();
        passwordLoginTF = new JPasswordField();
        searchTF = new JTextField();

        beautifyButton(loginButton);
        beautifyButton(okAddProduct);
        beautifyButton(okEditProduct);
        beautifyButton(clientsButton);
        beautifyButton(ordersButton);
        beautifyButton(productsButton);
        beautifyButton(orderArchiveButton);
        beautifyButton(searchByElement);
        beautifyButton(placeOrder);
        beautifyButton(deleteOrder);
        beautifyButton(plusQuantity);
        beautifyButton(minusQuantity);
        beautifyButton(increaseStock);
        beautifyButton(decreaseStock);
        beautifyButton(viewOrders);
        beautifyButton(sendOrder);
        beautifyButton(editElementClient);
        beautifyButton(addElementClient);
        beautifyButton(deleteElementClient);
        beautifyButton(editElementOrder);
        beautifyButton(addElementOrder);
        beautifyButton(deleteElementOrder);
        beautifyButton(editElementProduct);
        beautifyButton(addElementProduct);
        beautifyButton(deleteElementProduct);
        beautifyButton(okAddClient);
        beautifyButton(okEditClient);

        emailLabel.setPreferredSize(new Dimension(100, 25));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBackground(new Color(101, 8, 145));
        emailLoginTF.setPreferredSize(new Dimension(150, 25));
        emailLoginTF.setBorder(null);
        emailLoginTF.setBackground(new Color(167, 126, 180));
        passwordLabel.setPreferredSize(new Dimension(100, 25));
        passwordLabel.setBackground(new Color(101, 8, 145));
        passwordLabel.setForeground(Color.WHITE);
        passwordLoginTF.setPreferredSize(new Dimension(150, 25));
        passwordLoginTF.setBorder(null);
        passwordLoginTF.setBackground(new Color(167, 126, 180));

        searchTF.setPreferredSize(new Dimension(150, 25));
        searchTF.setBackground(new Color(167, 126, 180));

        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = GridBagConstraints.RELATIVE;
        gbc1.insets = new Insets(2, 0, 2, 0);
        gbc1.anchor = GridBagConstraints.CENTER;
        gbc2.gridx = 0;
        gbc2.gridy = GridBagConstraints.RELATIVE;
        gbc2.insets = new Insets(5, 0, 0, 0);
        gbc2.anchor = GridBagConstraints.CENTER;

        loginPanel.add(emailLabel, gbc2);
        loginPanel.add(emailLoginTF, gbc1);
        loginPanel.add(passwordLabel, gbc2);
        loginPanel.add(passwordLoginTF, gbc1);
        loginPanel.add(loginButton, gbc1);
        loginPanel.setBackground(new Color(101, 8, 145));

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1.0;
        mainGbc.weighty = 1.0;
        mainGbc.fill = GridBagConstraints.CENTER;


        ///
        addProductToOrderButton = new JButton("Add");
        setClientNewOrderOk = new JButton("Ok");
        okQuantityButton = new JButton("OK");
        beautifyButton(addProductToOrderButton);
        beautifyButton(setClientNewOrderOk);
        beautifyButton(okQuantityButton);
        addProductToOrderQuantityLabel = new JLabel("Quantity");
        addProductToOrderQuantityTF = new JTextField();

        addProductToOrderQuantityLabel.setPreferredSize(new Dimension(100, 25));
        addProductToOrderQuantityLabel.setForeground(Color.WHITE);
        addProductToOrderQuantityLabel.setBackground(new Color(101, 8, 145));
        addProductToOrderQuantityTF.setPreferredSize(new Dimension(150, 25));
        addProductToOrderQuantityTF.setBorder(null);
        addProductToOrderQuantityTF.setBackground(new Color(167, 126, 180));

        addOrderTablePanel.setBackground(new Color(101, 8, 145));
        addOrderTablePanel.setLayout(new BoxLayout(addOrderTablePanel,BoxLayout.Y_AXIS));
        addOrderTablePanel.setBorder(new EmptyBorder(50,0,0,0));

        addOrderTablePanelMain.setSize(new Dimension(575,700));
        addOrderTablePanelMain.setBackground(new Color(101, 8, 145));
        addOrderTablePanelMain.add(addOrderTablePanel,gbc2);

        addOrderFrame.add(addOrderTablePanelMain);
        addOrderFrame.setSize(new Dimension(575, 700));
        addOrderFrame.setLocationRelativeTo(null);
        addOrderFrame.setBackground(Color.BLACK);
        addOrderFrame.setResizable(false);
        addOrderFrame.setVisible(false);
        ///

        /// view Orders for Client

        viewClientOrdersPanel.setBackground(new Color(101, 8, 145));
        viewClientOrdersPanel.setLayout(new BoxLayout(viewClientOrdersPanel,BoxLayout.Y_AXIS));
        viewClientOrdersPanel.setBorder(new EmptyBorder(0,0,0,0));

        mainViewClientOrdersPanel.setSize(new Dimension(600,600));
        mainViewClientOrdersPanel.setBackground(new Color(101, 8, 145));
        mainViewClientOrdersPanel.add(viewClientOrdersPanel,gbc2);

        viewClientOrdersFrame.add(mainViewClientOrdersPanel);
        viewClientOrdersFrame.setSize(new Dimension(600, 600));
        viewClientOrdersFrame.setLocationRelativeTo(null);
        viewClientOrdersFrame.setBackground(Color.BLACK);
        viewClientOrdersFrame.setResizable(false);
        viewClientOrdersFrame.setVisible(false);
        ///


        ////
        editOrderPanel = new JPanel();
        mainEditOrderPanel = new JPanel();
        okQuantityChange = new JButton("Ok");
        beautifyButton(okQuantityChange);

        editOrderQuantityLabel = new JLabel("Quantity");
        editOrderQuantityTF = new JTextField();

        editOrderQuantityLabel.setPreferredSize(new Dimension(100, 25));
        editOrderQuantityLabel.setForeground(Color.WHITE);
        editOrderQuantityLabel.setBackground(new Color(101, 8, 145));
        editOrderQuantityTF.setPreferredSize(new Dimension(150, 25));
        editOrderQuantityTF.setBorder(null);
        editOrderQuantityTF.setBackground(new Color(167, 126, 180));

        editOrderPanel.setBackground(new Color(101, 8, 145));
        editOrderPanel.setLayout(new BoxLayout(editOrderPanel,BoxLayout.Y_AXIS));
        editOrderPanel.setBorder(new EmptyBorder(5,0,0,0));
        editOrderPanel.add(editOrderQuantityLabel);
        editOrderPanel.add(editOrderQuantityTF);
        editOrderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        editOrderPanel.add(okQuantityChange);

        mainEditOrderPanel.setSize(new Dimension(300,200));
        mainEditOrderPanel.setBackground(new Color(101, 8, 145));
        mainEditOrderPanel.add(editOrderPanel,gbc2);

        editOrderFrame.add(mainEditOrderPanel);
        editOrderFrame.setSize(new Dimension(300, 200));
        editOrderFrame.setLocationRelativeTo(null);
        editOrderFrame.setBackground(Color.BLACK);
        editOrderFrame.setResizable(false);
        editOrderFrame.setVisible(false);

        ////


        ////add Product

        addProductNameLabel = new JLabel("Produs");
        addProductPriceLabel = new JLabel("Price");
        addProductStockLabel = new JLabel("Stock");
        addProductNameTF = new JTextField();
        addProductPriceTF  = new JTextField();
        addProductStockTF = new JTextField();

        addProductNameLabel.setPreferredSize(new Dimension(100, 25));
        addProductNameLabel.setForeground(Color.WHITE);
        addProductNameLabel.setBackground(new Color(101, 8, 145));
        addProductPriceLabel.setPreferredSize(new Dimension(100, 25));
        addProductPriceLabel.setBackground(new Color(101, 8, 145));
        addProductPriceLabel.setForeground(Color.WHITE);
        addProductStockLabel.setPreferredSize(new Dimension(100, 25));
        addProductStockLabel.setBackground(new Color(101, 8, 145));
        addProductStockLabel.setForeground(Color.WHITE);

        addProductNameTF.setPreferredSize(new Dimension(150, 25));
        addProductNameTF.setBorder(null);
        addProductNameTF.setBackground(new Color(167, 126, 180));
        addProductPriceTF.setPreferredSize(new Dimension(150, 25));
        addProductPriceTF.setBorder(null);
        addProductPriceTF.setBackground(new Color(167, 126, 180));
        addProductStockTF.setPreferredSize(new Dimension(150, 25));
        addProductStockTF.setBorder(null);
        addProductStockTF.setBackground(new Color(167, 126, 180));

        addProductPanel.add(addProductNameLabel,gbc1);
        addProductPanel.add(addProductNameTF,gbc2);
        addProductPanel.add(addProductPriceLabel,gbc1);
        addProductPanel.add(addProductPriceTF,gbc2);
        addProductPanel.add(addProductStockLabel,gbc1);
        addProductPanel.add(addProductStockTF,gbc2);
        addProductPanel.add(okAddProduct,gbc2);
        addProductPanel.setBackground(new Color(101, 8, 145));
        addProductPanel.setLayout(new BoxLayout(addProductPanel,BoxLayout.Y_AXIS));
        addProductPanel.setBorder(new EmptyBorder(50,0,0,0));

        mainAddProductPanel.setSize(new Dimension(100,200));
        mainAddProductPanel.setBackground(new Color(101, 8, 145));
        mainAddProductPanel.add(addProductPanel,gbc2);

        addProuctFrame.add(mainAddProductPanel);
        addProuctFrame.setSize(new Dimension(300, 400));
        addProuctFrame.setLocationRelativeTo(null);
        addProuctFrame.setBackground(Color.BLACK);
        addProuctFrame.setResizable(false);
        addProuctFrame.setVisible(false);

        ////

        ////edit Product

        editProductNameLabel = new JLabel("Produs");
        editProductPriceLabel = new JLabel("Price");
        editProductStockLabel = new JLabel("Stock");
        editProductNameTF = new JTextField();
        editProductPriceTF  = new JTextField();
        editProductStockTF = new JTextField();

        editProductNameLabel.setPreferredSize(new Dimension(100, 25));
        editProductNameLabel.setForeground(Color.WHITE);
        editProductNameLabel.setBackground(new Color(101, 8, 145));
        editProductPriceLabel.setPreferredSize(new Dimension(100, 25));
        editProductPriceLabel.setBackground(new Color(101, 8, 145));
        editProductPriceLabel.setForeground(Color.WHITE);
        editProductStockLabel.setPreferredSize(new Dimension(100, 25));
        editProductStockLabel.setBackground(new Color(101, 8, 145));
        editProductStockLabel.setForeground(Color.WHITE);

        editProductNameTF.setPreferredSize(new Dimension(150, 25));
        editProductNameTF.setBorder(null);
        editProductNameTF.setBackground(new Color(167, 126, 180));
        editProductPriceTF.setPreferredSize(new Dimension(150, 25));
        editProductPriceTF.setBorder(null);
        editProductPriceTF.setBackground(new Color(167, 126, 180));
        editProductStockTF.setPreferredSize(new Dimension(150, 25));
        editProductStockTF.setBorder(null);
        editProductStockTF.setBackground(new Color(167, 126, 180));

        editProductPanel.add(editProductNameLabel,gbc1);
        editProductPanel.add(editProductNameTF,gbc2);
        editProductPanel.add(editProductPriceLabel,gbc1);
        editProductPanel.add(editProductPriceTF,gbc2);
        editProductPanel.add(editProductStockLabel,gbc1);
        editProductPanel.add(editProductStockTF,gbc2);
        editProductPanel.add(okEditProduct,gbc2);
        editProductPanel.setBackground(new Color(101, 8, 145));
        editProductPanel.setLayout(new BoxLayout(editProductPanel,BoxLayout.Y_AXIS));
        editProductPanel.setBorder(new EmptyBorder(50,0,0,0));

        mainEditProductPanel.setSize(new Dimension(100,200));
        mainEditProductPanel.setBackground(new Color(101, 8, 145));
        mainEditProductPanel.add(editProductPanel,gbc2);

        editProductFrame.add(mainEditProductPanel);
        editProductFrame.setSize(new Dimension(300, 400));
        editProductFrame.setLocationRelativeTo(null);
        editProductFrame.setBackground(Color.BLACK);
        editProductFrame.setResizable(false);
        editProductFrame.setVisible(false);

        ////


        addClientPanel.add(nameClientLable,gbc1);
        addClientPanel.add(nameClientTF,gbc2);
        addClientPanel.add(emailClientLable,gbc1);
        addClientPanel.add(emailClientTF,gbc2);
        addClientPanel.add(ageClientLable,gbc1);
        addClientPanel.add(ageClientTF,gbc2);
        addClientPanel.add(okAddClient,gbc2);
        addClientPanel.setBackground(new Color(101, 8, 145));
        addClientPanel.setLayout(new BoxLayout(addClientPanel,BoxLayout.Y_AXIS));
        addClientPanel.setBorder(new EmptyBorder(50,0,0,0));

        editClientPanel.add(editClientNameLabel,gbc1);
        editClientPanel.add(editClientNameTF,gbc2);
        editClientPanel.add(editClientEmailLabel,gbc1);
        editClientPanel.add(editClientEmailTF,gbc2);
        editClientPanel.add(editClientAgeLabel,gbc1);
        editClientPanel.add(editClientAgeTF,gbc2);
        editClientPanel.add(okEditClient,gbc2);
        editClientPanel.setBackground(new Color(101, 8, 145));
        editClientPanel.setLayout(new BoxLayout(editClientPanel,BoxLayout.Y_AXIS));
        editClientPanel.setBorder(new EmptyBorder(50,0,0,0));

        mainEditClientPanel.setSize(new Dimension(100,200));
        mainEditClientPanel.setBackground(new Color(101, 8, 145));
        mainEditClientPanel.add(editClientPanel,gbc2);
        mainAddClientPanel.setSize(new Dimension(100,200));
        mainAddClientPanel.setBackground(new Color(101, 8, 145));
        mainAddClientPanel.add(addClientPanel,gbc2);


        String[] clientsCl = {"Client ID", "Client Name", "Email", "Age"};
        setTableData(clientsDao.getAll());
        cT = new DefaultTableModel(tableData,clientsCl);
        clientsTable = new JTable(cT);
        tableProperties(clientsTable);
        clientsScrollPane = new JScrollPane(clientsTable);
        clientsScrollPane.setBorder(null);
        clientsScrollPane.setViewportBorder(null);
        clientsScrollPane.setBackground(new Color(139, 95, 147, 215));
        clientsScrollPane.getViewport().setBackground(new Color(123, 63, 136, 215));
        clientsScrollPane.setPreferredSize(new Dimension(490, 380));
        tablePanel.add(clientsScrollPane);

        addClientFrame.add(mainAddClientPanel);
        addClientFrame.setSize(new Dimension(300, 400));
        addClientFrame.setLocationRelativeTo(null);
        addClientFrame.setBackground(Color.BLACK);
        addClientFrame.setResizable(false);
        addClientFrame.setVisible(false);

        editClientFrame.add(mainEditClientPanel);
        editClientFrame.setSize(new Dimension(300, 400));
        editClientFrame.setLocationRelativeTo(null);
        editClientFrame.setBackground(Color.BLACK);
        editClientFrame.setResizable(false);
        editClientFrame.setVisible(false);

        mainPanel.add(loginPanel, mainGbc);
        mainPanel.setBorder(new EmptyBorder(0, 0, 50, 0));

        mainPanel.setBackground(new Color(101, 8, 145));

        loginFrame.add(mainPanel);
        loginFrame.setSize(new Dimension(500, 300));
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setBackground(new Color(101, 8, 145));
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);

        userPanel.setPreferredSize(new Dimension(200, 500));
        tablePanel.setPreferredSize(new Dimension(500, 430));
        optionsForTablePanel.setPreferredSize(new Dimension(500, 70));
        totalRightPanel.setPreferredSize(new Dimension(500,500));

        userPanel.setBackground(new Color(68, 22, 91));
        tablePanel.setBackground(new Color(134, 95, 138));
        optionsForUserPanel.setBackground(new Color(68, 22, 91));
        optionsForTablePanel.setBackground(new Color(99, 3, 150));
        totalRightPanel.setBackground(new Color(68, 22, 91));
        optionsForTablePanel.setBorder(new EmptyBorder(20,0,0,0));
        optionsForTablePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        optionsForUserPanel.add(clientsButton);
        optionsForUserPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsForUserPanel.add(ordersButton);
        optionsForUserPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsForUserPanel.add(productsButton);
        optionsForUserPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsForUserPanel.add(orderArchiveButton);
        optionsForUserPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsForUserPanel.setLayout(new BoxLayout(optionsForUserPanel, BoxLayout.Y_AXIS));
        optionsForUserPanel.setBorder(new EmptyBorder(60, 0,0,0));

        userPanel.add(pictureUser);
        userPanel.add(optionsForUserPanel);
        userPanel.setBorder(new EmptyBorder(30,0,0,0));
        totalRightPanel.add(optionsForTablePanel);
        totalRightPanel.add(tablePanel);
        allPanel.setBackground(new Color(68, 22, 91));
        allPanel.add(userPanel);
        allPanel.add(totalRightPanel);

        userFrame.add(allPanel);
        userFrame.setSize(new Dimension(730, 550));
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setLocationRelativeTo(null);
        userFrame.setBackground(new Color(68, 22, 91));
        userFrame.setResizable(false);
        userFrame.setVisible(false);

    }

    public int getIdFromSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Object idObject = table.getValueAt(selectedRow, 0);
            if (idObject instanceof Integer) {
                return (int) idObject;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public Client getClientFromSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int clientId = (int) table.getValueAt(selectedRow, 0);
            String clientName = (String) table.getValueAt(selectedRow, 1);
            String email = (String) table.getValueAt(selectedRow, 2);
            int age = (int) table.getValueAt(selectedRow, 3);
            return new Client(clientId, clientName, email, age);
        } else {
            return null;
        }
    }

    public Orders getOrderFromSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int clientId = (int) table.getValueAt(selectedRow, 0);
            int ordersId = (int) table.getValueAt(selectedRow, 1);
            int productsId = (int) table.getValueAt(selectedRow, 2);
            int numberOfProducts = (int) table.getValueAt(selectedRow, 3);
            double totalPrice = (double) table.getValueAt(selectedRow, 4);
            return new Orders(clientId, ordersId, productsId, numberOfProducts, totalPrice);
        } else {
            return null;
        }
    }

    public Product getProductFromSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int productId = (int) table.getValueAt(selectedRow, 0);
            String productName = (String) table.getValueAt(selectedRow, 1);
            Object priceObj = table.getValueAt(selectedRow, 2);
            double productPrice = 0.0;
            if (priceObj instanceof Number) {
                productPrice = ((Number) priceObj).doubleValue();
            } else if (priceObj instanceof String) {
                try {
                    productPrice = Double.parseDouble((String) priceObj);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            int productStock = (int) table.getValueAt(selectedRow, 3);
            return new Product(productId, productName, productPrice, productStock);
        } else {
            return null;
        }
    }

    public int getIdFromSelectedRowOrders(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Object idObject = table.getValueAt(selectedRow, 1);
            if (idObject instanceof Integer) {
                return (int) idObject;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }


    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public JFrame getUserFrame() {
        return userFrame;
    }

    public JButton getClientsButton() {
        return clientsButton;
    }

    public JButton getOrdersButton() {
        return ordersButton;
    }

    public JButton getProductsButton() {
        return productsButton;
    }

    public JButton getOrderArchiveButton() {
        return orderArchiveButton;
    }

    public JButton getSearchByElement() {
        return searchByElement;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getPlaceOrder() {
        return placeOrder;
    }

    public JButton getDeleteOrder() {
        return deleteOrder;
    }

    public JButton getPlusQuantity() {
        return plusQuantity;
    }

    public JFrame getViewClientOrdersFrame() {
        return viewClientOrdersFrame;
    }

    public JPanel getViewClientOrdersPanel() {
        return viewClientOrdersPanel;
    }

    public JPanel getMainViewClientOrdersPanel() {
        return mainViewClientOrdersPanel;
    }

    public JTable getClientsTable() {
        return clientsTable;
    }

    public JLabel getAddProductNameLabel() {
        return addProductNameLabel;
    }

    public JTextField getAddProductNameTF() {
        return addProductNameTF;
    }

    public JLabel getAddProductPriceLabel() {
        return addProductPriceLabel;
    }

    public JTextField getAddProductPriceTF() {
        return addProductPriceTF;
    }

    public JLabel getAddProductStockLabel() {
        return addProductStockLabel;
    }

    public JTextField getAddProductStockTF() {
        return addProductStockTF;
    }

    public JPanel getAddProductPanel() {
        return addProductPanel;
    }

    public JPanel getMainAddProductPanel() {
        return mainAddProductPanel;
    }

    public JButton getOkAddProduct() {
        return okAddProduct;
    }

    public JLabel getEditOrderQuantityLabel() {
        return editOrderQuantityLabel;
    }

    public JTable getOrdersTable() {
        return ordersTable;
    }

    public JTable getProductsTable() {
        return productsTable;
    }

    public JTable getArchiveTable() {
        return archiveTable;
    }

    public JButton getMinusQuantity() {
        return minusQuantity;
    }

    public JButton getIncreaseStock() {
        return increaseStock;
    }

    public JButton getDecreaseStock() {
        return decreaseStock;
    }

    public JButton getViewOrders() {
        return viewOrders;
    }

    public JTextField getEditClientNameTF() {
        return editClientNameTF;
    }

    public JTextField getEditClientEmailTF() {
        return editClientEmailTF;
    }

    public JTextField getEditClientAgeTF() {
        return editClientAgeTF;
    }

    public JButton getOkEditClient() {
        return okEditClient;
    }

    public JFrame getEditProductFrame() {
        return editProductFrame;
    }

    public JFrame getEditOrderFrame() {
        return editOrderFrame;
    }

    public JButton getSendOrder() {
        return sendOrder;
    }

    public JTextField getSearchTF() {
        return searchTF;
    }

    public JTextField getEmailLoginTF() {
        return emailLoginTF;
    }

    public JTextField getPasswordLoginTF() {
        return passwordLoginTF;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public JButton getEditElementClient() {
        return editElementClient;
    }

    public JButton getAddElementClient() {
        return addElementClient;
    }

    public JButton getDeleteElementClient() {
        return deleteElementClient;
    }

    public JButton getAddElementOrder() {
        return addElementOrder;
    }

    public JButton getDeleteElementOrder() {
        return deleteElementOrder;
    }

    public JButton getEditElementOrder() {
        return editElementOrder;
    }

    public JButton getAddElementProduct() {
        return addElementProduct;
    }

    public JButton getDeleteElementProduct() {
        return deleteElementProduct;
    }

    public JButton getEditElementProduct() {
        return editElementProduct;
    }

    public JScrollPane getArchiveScrollPane() {
        return archiveScrollPane;
    }

    public JFrame getEditClientFrame() {
        return editClientFrame;
    }

    public JScrollPane getClientsScrollPane() {
        return clientsScrollPane;
    }

    public JScrollPane getProductsScrollPane() {
        return productsScrollPane;
    }

    public JScrollPane getOrdersScrollPane() {
        return ordersScrollPane;
    }

    public JPanel getOptionsForTablePanel() {
        return optionsForTablePanel;
    }

    public Object[][] getTableData() {
        return tableData;
    }

    public void setTableData(Object[][] tableData) {
        this.tableData = tableData;
    }

    public ProductsDao getProductsDao() {
        return productsDao;
    }

    public ClientsDao getClientsDao() {
        return clientsDao;
    }

    public OrdersDao getOrdersDao() {
        return ordersDao;
    }

    public BillDao getBillDao() {
        return billDao;
    }

    public JTextField getNameClientTF() {
        return nameClientTF;
    }

    public JTextField getAgeClientTF() {
        return ageClientTF;
    }

    public JTextField getEmailClientTF() {
        return emailClientTF;
    }

    public JButton getOkAddClient() {
        return okAddClient;
    }

    public JFrame getAddProuctFrame() {
        return addProuctFrame;
    }

    public JFrame getAddClientFrame() {
        return addClientFrame;
    }

    public JFrame getAddOrderFrame() {
        return addOrderFrame;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getUserPanel() {
        return userPanel;
    }

    public JPanel getOptionsForUserPanel() {
        return optionsForUserPanel;
    }

    public JPanel getTotalRightPanel() {
        return totalRightPanel;
    }

    public JPanel getAllPanel() {
        return allPanel;
    }

    public ImagePanel getPictureUser() {
        return pictureUser;
    }

    public JPanel getAddOrderTablePanel() {
        return addOrderTablePanel;
    }

    public JPanel getAddOrderTablePanelMain() {
        return addOrderTablePanelMain;
    }

    public JButton getAddProductToOrderButton() {
        return addProductToOrderButton;
    }

    public JButton getSetClientNewOrderOk() {
        return setClientNewOrderOk;
    }


    public JButton getOkQuantityButton() {
        return okQuantityButton;
    }

    public JLabel getAddProductToOrderQuantityLabel() {
        return addProductToOrderQuantityLabel;
    }

    public JTextField getAddProductToOrderQuantityTF() {
        return addProductToOrderQuantityTF;
    }

    public JLabel getEditClientNameLabel() {
        return editClientNameLabel;
    }

    public JLabel getEditClientEmailLabel() {
        return editClientEmailLabel;
    }

    public JLabel getEditClientAgeLabel() {
        return editClientAgeLabel;
    }

    public JPanel getEditClientPanel() {
        return editClientPanel;
    }

    public JPanel getMainEditClientPanel() {
        return mainEditClientPanel;
    }

    public JTextField getEditOrderQuantityTF() {
        return editOrderQuantityTF;
    }

    public JPanel getEditOrderPanel() {
        return editOrderPanel;
    }

    public JPanel getMainEditOrderPanel() {
        return mainEditOrderPanel;
    }

    public JButton getOkQuantityChange() {
        return okQuantityChange;
    }

    public JLabel getNameClientLable() {
        return nameClientLable;
    }

    public JLabel getAgeClientLable() {
        return ageClientLable;
    }

    public JLabel getEmailClientLable() {
        return emailClientLable;
    }

    public JPanel getAddClientPanel() {
        return addClientPanel;
    }

    public JPanel getMainAddClientPanel() {
        return mainAddClientPanel;
    }

    public DefaultTableModel getcT() {
        return cT;
    }

    public DefaultTableModel getoT() {
        return oT;
    }

    public DefaultTableModel getpT() {
        return pT;
    }

    public DefaultTableModel getaT() {
        return aT;
    }

    public JLabel getEditProductNameLabel() {
        return editProductNameLabel;
    }

    public JTextField getEditProductNameTF() {
        return editProductNameTF;
    }

    public JLabel getEditProductPriceLabel() {
        return editProductPriceLabel;
    }

    public JTextField getEditProductPriceTF() {
        return editProductPriceTF;
    }

    public JLabel getEditProductStockLabel() {
        return editProductStockLabel;
    }

    public JTextField getEditProductStockTF() {
        return editProductStockTF;
    }

    public JPanel getEditProductPanel() {
        return editProductPanel;
    }

    public JPanel getMainEditProductPanel() {
        return mainEditProductPanel;
    }

    public JButton getOkEditProduct() {
        return okEditProduct;
    }
}

