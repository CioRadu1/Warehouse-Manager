package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsDao extends Abstract<Product>{
    private static final Logger LOGGER = Logger.getLogger(ProductsDao.class.getName());

    public ProductsDao() {
        super();
    }

    public int getStockById(int productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int stock = 0;

        String query = "SELECT stock FROM products WHERE productId = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                stock = resultSet.getInt("productStock");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:getStockById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return stock;
    }
    public Product getProductById(int productId) {
        String query = "SELECT * FROM Product WHERE productId = ?";
        Product product = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String productName = resultSet.getString("productName");
                    double productPrice = resultSet.getDouble("productPrice");
                    int productStock = resultSet.getInt("productStock");

                    product = new Product(productId, productName, productPrice, productStock);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
