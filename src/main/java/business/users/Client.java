package business.users;

import business.items.MenuItem;
import business.items.OrderItems;

import java.io.Serial;


public class Client extends User{
    @Serial
    private static final long serialVersionUID = 6529420098267757690L;
    private final String phone;
    private final String eMail;
    private final OrderItems basket;
    public Client(int ID,String username, String password,String phone,String eMail) {
        super(ID,username, password);
        this.phone=phone;
        this.eMail=eMail;
        basket=new OrderItems();
    }
    public boolean addToBasket(MenuItem i){
        return basket.addItem(i);
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public String getPhone() {
        return phone;
    }
    public String geteMail() {
        return eMail;
    }
    public OrderItems getBasket() {
        return basket;
    }
    public boolean removeFromBasket(MenuItem i){
        return basket.removeItem(i);
    }
}
