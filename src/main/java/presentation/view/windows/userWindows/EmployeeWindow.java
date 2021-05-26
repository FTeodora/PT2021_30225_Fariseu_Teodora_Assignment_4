package presentation.view.windows.userWindows;

import business.order.Order;
import business.users.Employee;
import business.users.User;
import presentation.view.utility.WindowPanel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

public class EmployeeWindow extends UserWindow implements Observer {
    public EmployeeWindow(JFrame prev, Employee user) {
        super(prev, user);
        this.windowContent=new WindowPanel("There are no orders right now","Wait for clients to order something");
    }
    @Override
    public void update(Observable o, Object arg) {
        Order obj=(Order) arg;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        JOptionPane.showMessageDialog(this,"Client "+obj.getClientID()+" has added an order",obj.getOrderDate().format(formatter),JOptionPane.PLAIN_MESSAGE);
    }
}
