package start;

import bll.validators.Validator;
import dao.*;
import model.Client;
import presentation.Controller;
import presentation.View;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, <a href="http://dsrl.coned.utcluj.ro/">...</a>
 * @Since: Apr 03, 2017
 */
public class Start {

    public static void main(String[] args) throws SQLException {
            ClientsDao clientsDao = new ClientsDao();
            OrdersDao ordersDao = new OrdersDao();
            ProductsDao productsDao = new ProductsDao();
            BillDao billDao = new BillDao();
            View mv = new View(clientsDao, ordersDao, productsDao, billDao);
            Controller cont = new Controller(mv);
    }
}