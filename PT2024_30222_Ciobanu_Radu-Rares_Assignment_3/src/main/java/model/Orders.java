package model;

import dao.ProductsDao;

public class Orders {

    private int clientId;
    private int ordersId;
    private int productId;
    private int numberOfProducts;
    private double totalPrice;

    public Orders(int clientId, int ordersId, int productId, int numberOfProducts, double totalPrice) {
        this.clientId = clientId;
        this.ordersId = ordersId;
        this.productId = productId;
        this.numberOfProducts = numberOfProducts;
        this.totalPrice = totalPrice;
    }
    public Orders(int clientId, int productId, int numberOfProducts, double totalPrice) {
        this.clientId = clientId;
        this.productId = productId;
        this.numberOfProducts = numberOfProducts;
        this.totalPrice = totalPrice;
    }

    public int getIdClient() {
        return clientId;
    }

    public int getOrderId() {
        return ordersId;
    }

    public int getIdProduct() {
        return clientId;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getProductStockById(int idProduct) {
        ProductsDao productDAO = new ProductsDao();
        return productDAO.getStockById(idProduct);
    }

    public void setIdClient(int idClient) {
        this.clientId = idClient;
    }

    public void setOrderId(int orderId) {
        this.ordersId = orderId;
    }

    public void setIdProduct(int idProduct) {
        this.productId = idProduct;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
