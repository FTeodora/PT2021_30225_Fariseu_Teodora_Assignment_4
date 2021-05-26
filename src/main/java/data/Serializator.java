package data;

import business.DeliveryService;
import business.IDeliveryServiceProcessing;

import java.io.*;

/**
 * Clasa pentru serializarea si deserializarea clasei de DeliveryService
 */
public class Serializator {
    private Serializator singleInstance=new Serializator();
    public static void writeFile(DeliveryService src){
    try {
        File dir=new File("saves");
        dir.mkdir();
        FileOutputStream fileOut = new FileOutputStream(dir.getAbsolutePath()+File.separator+"delivery.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(src);
        out.close();
        fileOut.close();
    } catch (IOException i) {
    }
}
    public static DeliveryService readFile(){
    DeliveryService obj=null;
    try {
        File dir=new File("saves");
        FileInputStream fileIn = new FileInputStream(dir.getAbsolutePath()+File.separator+"delivery.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        obj = (DeliveryService) in.readObject();
        in.close();
        fileIn.close();
    } catch (IOException| ClassNotFoundException e) {
        return null;
    }
    return obj;
}
    public Serializator getSingleInstance(){ return singleInstance;}
}
