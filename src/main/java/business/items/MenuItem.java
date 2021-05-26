package business.items;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;

public abstract class MenuItem implements Serializable {

    protected String title;
    protected float rating;
    protected BigInteger calories=new BigInteger("0");
    protected BigInteger protein=new BigInteger("0");
    protected BigInteger fat=new BigInteger("0");
    protected BigInteger sodium=new BigInteger("0");
    protected BigInteger price=new BigInteger("0");
    @Serial
    private static final long serialVersionUID=-1425490445904594561L;
    public abstract BigInteger computePrice();
    public void setTitle(String title) {
        this.title = title;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public void setCalories(BigInteger calories) {
        this.calories = calories;
    }
    public void setProtein(BigInteger protein) {
        this.protein = protein;
    }
    public void setFat(BigInteger fat) {
        this.fat = fat;
    }
    public void setSodium(BigInteger sodium) {
        this.sodium = sodium;
    }
    public void setPrice(BigInteger price) {
        this.price = price;
    }
    public String getTitle() {
        return title;
    }
    public float getRating() {
        return rating;
    }
    public BigInteger getCalories() {
        return calories;
    }
    public BigInteger getProtein() {
        return protein;
    }
    public BigInteger getFat() {
        return fat;
    }
    public BigInteger getSodium() {
        return sodium;
    }
    public BigInteger getPrice() {
        return price;
    }
    public boolean applyFilter(MenuItem[] items){
        Field[] fields=this.getClass().getSuperclass().getDeclaredFields();
        return Arrays.stream(fields).map((field)->{
            if(!field.getName().equals("serialVersionUID"))
            {
                try {
                    if(field.getType().equals(BigInteger.class)) {
                    return ((BigInteger)field.get(this)).compareTo((BigInteger)field.get((items[0])))>=0
                            && ((BigInteger)field.get(this)).compareTo((BigInteger)field.get((items[1])))<=0;
                }
                    if(field.getType().equals(float.class)) {
                        return ((Float)field.get(this)) >= ((Float)field.get(items[0])) &&
                                ((Float)field.get(this)) <= ((Float)field.get(items[1]));
                    }
                    if(field.getType().equals(String.class)) {
                        return ((String) field.get(this)).toLowerCase(Locale.ROOT).contains(((String)field.get(items[0])).toLowerCase(Locale.ROOT));

                    }
                } catch (IllegalAccessException e) { return false; }
            }
            return true;
        }
        ).reduce(true, Boolean::logicalAnd);
    }
    public void editItem(MenuItem item){
        Field[] fields=this.getClass().getSuperclass().getDeclaredFields();
        Arrays.stream(fields).forEach((field)-> {
            if (!Modifier.isFinal(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    field.set(this, field.get(item));
                } catch (IllegalAccessException e) { e.printStackTrace();}
            }
        });

    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MenuItem))
            return false;
        MenuItem o=(MenuItem) obj;
        if(o==this)
            return true;
        if(!this.getClass().equals(obj.getClass()))
            return false;
        return title.equals(o.title);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        for(Field i:MenuItem.class.getDeclaredFields()){
            i.setAccessible(true);
            stringBuilder.append(i.getName());
            stringBuilder.append(": ");
            try {
                stringBuilder.append(i.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    @Override
    public int hashCode() {
       if(this instanceof BaseProduct)
            return title.hashCode();
       return 31*title.hashCode();
    }
}
