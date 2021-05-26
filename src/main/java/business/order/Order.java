package business.order;

import business.users.Client;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {
    public Order(Client client,int orderID){
        orderDate=LocalDateTime.now();
        clientID=client.getID();
        status=OrderStatus.CONFIRMED;
        this.orderID=orderID;

    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public long getOrderID() {
        return orderID;
    }
    public int getClientID() {
        return clientID;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public OrderStatus getStatus() {
        return status;
    }
    @Serial
    private static final long serialVersionUID = 1129611198243217690L;
    private long orderID;
    private int clientID;
    private LocalDateTime orderDate;
    private OrderStatus status;
    @Override
    public int hashCode() {
        return orderDate.getDayOfMonth()*31+orderDate.getMonthValue()*12+orderDate.getDayOfYear()+orderDate.getHour()*24;
    }
}
