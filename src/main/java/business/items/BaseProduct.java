package business.items;
import java.io.Serial;
import java.lang.reflect.Field;
import java.math.BigInteger;

public class BaseProduct extends MenuItem{
    @Serial
    private static final long serialVersionUID = -1529665012267692690L;
    public static BaseProduct parse(String source){
        BaseProduct rez=new BaseProduct();
        String[] src=source.split(",");
        Field[] fields=BaseProduct.class.getSuperclass().getDeclaredFields();
        for(int i=0;i<src.length;i++){
            if(fields[i].getDeclaredAnnotationsByType(Serial.class).length==0)
            {
                try{
                    fields[i].setAccessible(true);
                    if(fields[i].getType().equals(BigInteger.class))
                        fields[i].set(rez,new BigInteger(src[i]));
                    else
                    if(fields[i].getType().equals(float.class))
                        fields[i].set(rez,Float.parseFloat(src[i]));
                    else
                        fields[i].set(rez,src[i]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return rez;
    }
    @Override
    public BigInteger computePrice() {
        return price;
    }
}
