package model;

import java.sql.Date;
import java.time.LocalDate;

public class Bill {

    private final int ordersId;
    private final int clientId;
    private final LocalDate timeOfEmision;
    private final double total;

    public Bill(int idOrder, int clientId, LocalDate timeOfEmision, double total) {
        if (idOrder < 0 || total < 0) {
            throw new IllegalArgumentException("Order id, or total price invalid.");
        }
        if (timeOfEmision == null) {
            throw new IllegalArgumentException("No date registered.");
        }
        this.ordersId = idOrder;
        this.clientId = clientId;
        this.timeOfEmision = timeOfEmision;
        this.total = total;
    }
}
