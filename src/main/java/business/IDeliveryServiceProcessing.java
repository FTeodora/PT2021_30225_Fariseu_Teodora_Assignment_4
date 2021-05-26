package business;

import business.exceptions.LoginException;
import business.items.MenuItem;
import business.order.Order;
import business.users.Client;
import business.users.User;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IDeliveryServiceProcessing {
    List<MenuItem> searchItem(MenuItem[] filters);
    List<MenuItem> searchItem(String name);
    List<Order>  timeIntervalReport(int startHour, int finalHour);
    Map<MenuItem,Integer> mostOrderedProducts(BigInteger min);
    List<Integer> clientsWithMostOrders(BigInteger minOrders,BigInteger minValue);
    Map<MenuItem,Integer> productsOrderedInADay(LocalDate date);
    void importProductsFromFile(File file) throws IOException;
    boolean createAccount(Client newAccount);
    boolean deleteProduct(MenuItem object);
    boolean addProduct(MenuItem newProduct);
    void editProduct(MenuItem oldProduct,MenuItem newProduct);
    boolean commitOrder(Client client);
    User logIn(String username, String password) throws LoginException;
}
