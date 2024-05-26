package dao;

import connection.ConnectionFactory;
import model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersDao extends Abstract<Orders>{
    private static final Logger LOGGER = Logger.getLogger(OrdersDao.class.getName());

    public Object[][] getOrdersByClientId(int clientId) {
        List<Object[]> ordersList = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE clientId = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int clientIdd = resultSet.getInt("clientId");
                int orderId = resultSet.getInt("ordersId");
                int productId = resultSet.getInt("productId");
                int numberOfProducts = resultSet.getInt("numberOfProducts");
                double totalPrice = resultSet.getDouble("totalPrice");

                Object[] orderData = {clientIdd, orderId, productId, numberOfProducts, totalPrice};
                ordersList.add(orderData);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error fetching orders for client ID: " + clientId, e);
            e.printStackTrace();
        }

        Object[][] ordersArray = new Object[ordersList.size()][];
        for (int i = 0; i < ordersList.size(); i++) {
            ordersArray[i] = ordersList.get(i);
        }

        return ordersArray;
    }
}
