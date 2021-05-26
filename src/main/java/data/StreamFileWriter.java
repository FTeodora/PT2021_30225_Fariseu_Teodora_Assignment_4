package data;

import business.items.MenuItem;
import business.items.OrderItems;
import business.order.Order;
import business.users.Client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Pentru scrierea in fisiere .txt
 * Acest lucru cuprinde
 * <li>
 *     <ul> generarea chitantei </ul>
 *     <ul> generarea de rapoarte</ul>
 * </li>
 */
public class StreamFileWriter {
    private StreamFileReader singleInstance=new StreamFileReader();

    /**
     * Generarea chitantei unei comenzi
     * @param i comanda
     * @param items produsele din comanda
     * @return flag care reprezinta daca scrierea s-a realizat cu succes
     */
    public static boolean generateBill(Order i, OrderItems items) {
        try{
            File dir=new File("bills");
            dir.mkdir();

            File saveFile=new File(dir,new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date())+"_client_"+i.getClientID()+"_bill.txt");
            saveFile.createNewFile();
            BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));
            items.getItems().stream().forEach( it->{
                        try{
                            writer.write(it.getTitle()+" "+it.getPrice());
                            writer.newLine();
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            writer.write("Total: "+items.getPrice());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Generarea raportului de comenzi dintr-un interval orar
     * @param orderList lista comenzilor care indeplinesc criteriul
     * @param minHour ora minima
     * @param maxHour ora maxima
     */
    public static void generateOrdersBetweenHoursReport(List<Order> orderList,int minHour,int maxHour){
        try{
        File dir=new File("reports");
        dir.mkdir();
        File dir2=new File(dir,"reports1");
        dir2.mkdir();
        File saveFile=new File(dir2,new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date())+"_report.txt");
        saveFile.createNewFile();
        BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));
        writer.write("Orders between "+minHour+":00 and "+maxHour+":00");
        writer.newLine();
        writer.flush();
        orderList.stream().forEach(it->{
            try{
                writer.write("Order "+it.getOrderID()+" at "+it.getOrderDate());
        writer.newLine();
        writer.flush();
            } catch (IOException e) { }
        });
        writer.close();} catch (IOException e) { }
    }

    /**
     * Generarea raportului pentru produse comandate de un numar minim de ori
     * @param orderList un Map cu produsele si aparitia lor in comenzi
     * @param minAmount numarul minim de aparitii in toate comenzile
     */
    public static void generateItemsOrderedMoreThan(Map<MenuItem,Integer> orderList,int minAmount){
        try{
            File dir=new File("reports");
            dir.mkdir();
            File dir2=new File(dir,"reports2");
            dir2.mkdir();
            File saveFile=new File(dir2,new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date())+"_report.txt");
            saveFile.createNewFile();
            BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));
            writer.write("Items ordered at least "+minAmount+" times ");
            writer.newLine();
            writer.flush();
            orderList.entrySet().stream().forEach(it->{
                try{
                    writer.write(it.getKey().getTitle()+" ordered "+it.getValue()+" times");
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) { }
            });
            writer.close();} catch (IOException e) { }
    }

    /**
     * Generarea raportului pentru clientii care au avut un anumit numar minim de comenzi cu o anumita valoare minima
     * @param clients lista ID-urilor clientilor care indeplinesc criteriul
     * @param minValue pretul minim al comenzilor
     * @param minAmount numarul minim de comenzi
     */
    public static void generateClientsWhoOrderedAtLeast(List<Integer> clients,int minValue,int minAmount){
        try{
            File dir=new File("reports");
            dir.mkdir();
            File dir2=new File(dir,"reports3");
            dir2.mkdir();
            File saveFile=new File(dir2,new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date())+"_report.txt");
            saveFile.createNewFile();
            BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));
            writer.write("Clients who ordered at least "+minAmount+" times  and had at an order of at least "+minValue);
            writer.newLine();
            writer.flush();
            clients.stream().forEach(it->{
                try{
                    writer.write("Client no."+it);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) { }
            });
            writer.close();} catch (IOException e) { }
    }

    /**
     * Generarea raportului pentru produsele comandate intr-o zi
     * @param products lista de produse care indeplinesc criteriul
     * @param day data din care se iau produsele
     */
    public static void generateProductsOrderedInADay(Map<MenuItem,Integer> products, LocalDate day){
        try{
            File dir=new File("reports");
            dir.mkdir();
            File dir2=new File(dir,"reports4");
            dir2.mkdir();
            File saveFile=new File(dir2,new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date())+"_report.txt");
            saveFile.createNewFile();
            BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            writer.write(String.format("Products ordered on " + day.format(formatter)));
            writer.newLine();
            writer.flush();
            products.entrySet().stream().forEach(it->{
                try{
                    writer.write(it.getKey().getTitle()+" "+it.getValue());
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) { }
            });
            writer.close();} catch (IOException e) { }
    }
}
