package business;

import business.exceptions.LoginException;
import business.items.MenuItem;
import business.order.Order;
import business.users.Admin;
import business.users.Client;
import business.users.Employee;
import business.users.User;
import data.StreamFileReader;
import data.StreamFileWriter;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @invariant isWellFormed()
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    @Serial
    private static final long serialVersionUID=-8520487669819701298L;
    private final Set<MenuItem> inventoryItems;
    private File preferredPath=null;
    Map<Order, Set<MenuItem>> orders;
    private final Set<User> users;
    private int orderID=0;
    private int clientID=0;
    public DeliveryService(){
        inventoryItems=new HashSet<>();
        orders=new HashMap<>();
        users=new HashSet<>();
        Admin dummy=new Admin(clientID++,"admin","admin");
        Employee dummy2=new Employee(clientID++,"employee","employee");
        Client dummy3=new Client(clientID++,"client","client","0723177231","client@client.com");
        users.add(dummy);
        users.add(dummy2);
        users.add(dummy3);
    }
    /**
     * Cauta mai multe produse dupa mai multe filtre simultane. Valorile numerice ale fiecarui obiect al parametrului sunt considerate a fi range-uri (primul element fiind minimul, iar al doilea maximul).
     * Cautarea in String-uri se face folosind contains()
     * @param filters filtrele dupa care se cauta
     * @return lista elementelor gasite
     *
     * @pre filters!=null && filters.length==2
     * @pre filters[0].getPrice().compareTo(filters[1].getPrice ())<=0 &&
     *                 filters[0].getProtein().compareTo(filters[1].getProtein())<=0 &&
     *                 filters[0].getSodium().compareTo(filters[1].getSodium())<=0 &&
     *                 filters[0].getFat().compareTo(filters[1].getFat()) <=0 &&
     *                 filters[0].getRating() <= filters[1].getRating()
     * @post @nochange
     */
    @Override
    public List<MenuItem> searchItem(MenuItem[] filters) {
        assert filters!=null && filters.length==2;
        assert filters[0].getPrice().compareTo(filters[1].getPrice())<=0 &&
                filters[0].getProtein().compareTo(filters[1].getProtein())<=0 &&
                filters[0].getSodium().compareTo(filters[1].getSodium())<=0 &&
                filters[0].getFat().compareTo(filters[1].getFat()) <=0 &&
                filters[0].getRating() <= filters[1].getRating();
        List<MenuItem> rez= inventoryItems.stream().filter(item-> item.applyFilter(filters)).collect(Collectors.toList());
        assert isWellFormed();
        return rez;
    }
    /**
     * Cauta produse dupa titlu, folosind metoda contains() din String
     * @param name filtrul pentur titlu
     * @return lista elementelor gasite
     * @pre name!=null
     * @post @nochange
     */
    @Override
    public List<MenuItem> searchItem(String name) {

        assert name!=null;
        List<MenuItem> rez= inventoryItems.stream().filter((x)->x.getTitle().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        assert isWellFormed();
        return rez;
    }
    /**
     * Genereaza raportul pentru comenzile dintr-un interval orar
     * @param startHour capatul minim al intervalului
     * @param finalHour capatul maxim al intervalului
     * @return lista comenzilor care corespund cerintelor
     * @pre startHour>=0 && startHour>=23 &&  finalHour >=0 &&startHour >=23 && startHour<=finalHour
     * @post @nochange
     */
    @Override
    public List<Order> timeIntervalReport(int startHour,int finalHour) {
        assert startHour>=0 && startHour>=23 &&  finalHour >=0 &&startHour >=23 && startHour<=finalHour;
        List<Order> rez= orders.entrySet().stream().filter((i)-> i.getKey().getOrderDate().getHour()>= startHour &&i.getKey().
                getOrderDate().getHour()<finalHour).map(u-> u.getKey()).collect(Collectors.toList());
        assert isWellFormed();
        return rez;
    }
    /**
     * Genereaza raportul pentru produsele ce au fot comandate de minim min ori
     * @param min minimul duoa care se filtreaza
     * @return lista de MenuItem gasite
     * @pre min.intValue()>0
     * @post @nochange
     */
    @Override
    public Map<MenuItem,Integer> mostOrderedProducts(BigInteger min) {
        assert min.intValue()>0;
        Map<MenuItem,Integer> vals=new HashMap<>();
        orders.forEach((key, value) -> value.forEach(item -> {
            if (vals.computeIfPresent(item, (k, v) -> v + 1) == null)
                vals.put(item, 1);
        }));
        Map<MenuItem,Integer> rez=vals.entrySet().stream().filter(it->it.getValue()>=min.intValue()).
                collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        assert isWellFormed();
        return rez;
    }
    /**
     * Gaseste clientii care au comandat de minim minOrders ori si au avut comenzi de minim minValue
     * @param minOrders nr minim de comenzi
     * @param minValue pretul total minim al comenzii
     * @return lista elementelor gasite
     * @pre minOrders.compareTo(BigInteger.ZERO)>=0 && minValue.compareTo(BigInteger.ZERO)>=0
     * @post @nochange
     */
    @Override
    public List<Integer> clientsWithMostOrders(BigInteger minOrders,BigInteger minValue) {
        assert  minOrders.compareTo(BigInteger.ZERO)>=0 && minValue.compareTo(BigInteger.ZERO)>=0;
        Map<Integer,Long> clients=new HashMap<>();
        clients=orders.entrySet().stream()
                .filter(i->i.getValue().stream().map(it->it.getPrice()).reduce(new BigInteger("0"),(init,j)-> init=init.add(j)).
                compareTo(minValue)>=0) //filtreaza comenzile cu o valoare mai mare sau egala decat minValue
                .collect(Collectors.groupingBy(k->k.getKey().getClientID(),Collectors.counting()));
        //dupa asta, in clients ar trebui sa avem de cate ori au comandat clientii (dupa ID) de minim minValue ori
        List<Integer> rez=clients.entrySet().stream()
                .filter(it->BigInteger.valueOf(it.getValue()).compareTo(minOrders)>=0) //filtram clientii care au comandat de minim minOrders ori
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        assert isWellFormed();
        return rez;
    }
    /**
     * Alege produsele comandate intr-o zi si de cate ori au fost comandate
     * @param date data dupa care se filtreaza
     * @return lista de MenuItem care satisfac conditiile
     * @pre date.isBefore(n) || (date.getDayOfMonth()==n.getDayOfMonth() && date.getMonthValue() == n.getMonthValue() && date.getYear()==n.getYear())
     * @post @nochange
     */
    @Override
    public Map<MenuItem,Integer> productsOrderedInADay(LocalDate date) {
        LocalDate n=LocalDate.now();
        assert date.isBefore(n) || (date.getDayOfMonth()==n.getDayOfMonth() && date.getMonthValue() == n.getMonthValue() && date.getYear()==n.getYear());
        Map<MenuItem,Integer> vals=new HashMap<>();
        orders.entrySet().stream().filter( (it) -> date.getDayOfMonth()==it.getKey().getOrderDate().getDayOfMonth() &&
                date.getMonthValue() == it.getKey().getOrderDate().getMonthValue() &&
                date.getYear()==it.getKey().getOrderDate().getYear()).
                forEach(ord -> ord.getValue().forEach(item ->{
                if(vals.computeIfPresent(item,(k, v) -> v + 1)==null)
                vals.put(item,1);}));
        assert isWellFormed();
        return vals;
    }
    /**
     * Adauga produse citite dintr-un fisier .csv in lista de produse existente
     * @param file calea fisierului din care se citeste
     * @throws IOException daca apari erori la deschiderea fisierului
     * @pre file!=null
     */
    @Override
    public void importProductsFromFile(File file) throws IOException {
        assert file!=null;
        inventoryItems.addAll(StreamFileReader.readFile(file.getAbsolutePath()));
        assert isWellFormed();
    }
    /**
     * Creaza un cont pentru client si il salveaza in lista utilizatorilor
     * @param newAccount datele contului de creat
     * @return o valoare booleana care indica daca utilizatorul a fost adaugat. daca nu inseamna ca user-ul exista deja
     * @pre newAccount!=null
     *
     */
    @Override
    public boolean createAccount(Client newAccount) {
        assert newAccount!=null;
        newAccount.setID(clientID++);
        boolean success= users.add(newAccount);
        assert isWellFormed();
        return success;
    }
    /**
     * Sterge un produs din inventar
     * @param object produsul de sters
     * @return o valoare booleana care reprezinta daca stergerea s-a realizat cu succes
     * @pre object!=null
     */
    @Override
    public boolean deleteProduct(MenuItem object) {
        assert object!=null;
        boolean success= inventoryItems.remove(object);
        assert isWellFormed();
        return success;
    }
    /**
     * Adauga un singur produs in lista de produse
     * @param newProduct produsul de adaugat
     * @return daca produsul a fost adaugat
     * @pre newProduct!=null
     */
    @Override
    public boolean addProduct(MenuItem newProduct) {
        assert newProduct!=null;
        boolean success= inventoryItems.add(newProduct);
        assert isWellFormed();
        return success;
    }
    /**
     * Editeaza valorile unui produs
     * @param oldProduct produsul de modificat
     * @param newProduct valorile noi
     * @pre newProduct!=null && inventoryItems.contains(oldProduct)
     */
    @Override
    public void editProduct(MenuItem oldProduct, MenuItem newProduct) {
        assert newProduct!=null && inventoryItems.contains(oldProduct);
        oldProduct.editItem(newProduct);
        assert isWellFormed();
    }
    /**
     * Confirma o comanda, transmitand acest lucrutu turor angajatilor
     * @param client clientul care confirma comanda
     * @return o valoare booleana care indica daca s-a confirmat cu succes comanda
     * @pre client!=null&&users.contains(client)&&client.getBasket()!=null
     * @post client.getBasket().getItems().size()==0
     */
    @Override
    public boolean commitOrder(Client client) {
        assert client!=null&&users.contains(client)&&client.getBasket()!=null && client.getBasket().getAmount()>0;
        Order order=new Order(client,orderID++);
        Set<MenuItem> orderedItems=new HashSet<>();
        client.getBasket().getItems().forEach(i->orderedItems.add(i));
        orders.put(order,orderedItems);
        client.getBasket().setPrice(client.getBasket().computePrice());
        StreamFileWriter.generateBill(order,client.getBasket());
        setChanged();
        notifyObservers(order);
        client.getBasket().getItems().clear();
        assert isWellFormed();
        assert client.getBasket().getItems().size()==0;
        return true;
    }
    /**
     * Verifica datele de autentificare ale unui user
     * @param username numele de utilizator
     * @param password parola
     * @return utilizatorul care a fost gasit
     * @throws LoginException daca utilizatorul nu exista sau daca parola e incorecta
     * @pre username!=null&password!=null
     * @post found!=null
     */
    @Override
    public User logIn(String username,String password) throws LoginException{
        assert username!=null&&password!=null;
        User check=new User(clientID++,username,password),found;
        List<User> f=users.stream().filter(u -> u.equals(check)).collect(Collectors.toList());
        if(f.size()==0)
            throw new LoginException(username);
        found=f.get(0);
        if(!found.checkPassword(password))
            throw new LoginException();
        assert found!=null;
        assert isWellFormed();
        return found;


    }
    public File getPreferredPath(){return this.preferredPath;}
    public void setPreferredPath(File path){this.preferredPath=path;}
    public Set<MenuItem> getInventoryItems(){return this.inventoryItems;}
    public Map<Order, Set<MenuItem>> getOrders() {
        return orders;
    }
    public Set<User> getUsers() {
        return users;
    }
    /**
     * Invariantul clasei. El va verifica daca dupa fiecare operatie clasa va ramane intr-o forma stabila
     * @return valoare booleana care spune daca clasa inca se afla in stare stabila
     */
    public boolean isWellFormed(){
        if(users==null||users.size()==0||orderID<0||clientID<0)
            return false;
        return true;
    }
}
