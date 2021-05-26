package business.items;

import java.io.Serial;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompositeProduct extends MenuItem{
    @Serial
    private static final long serialVersionUID = -1529665012267665690L;
    List<BaseProduct> items;
    public CompositeProduct(Collection<BaseProduct> src,String title){
        items=new ArrayList<>();
        for(BaseProduct i:src)
            addItem(i);
        this.title=title;
    }
    @Override
    public BigInteger computePrice() {
        return items.stream().reduce(new BaseProduct(),(ans,i)->{ans.price=ans.price.add(i.price);
        return ans;}).getPrice();
    }
    public void addItem(BaseProduct item){
        items.add(item);
        calories=calories.add(item.calories);
        protein=protein.add(item.protein);
        fat=fat.add(item.fat);
        sodium=sodium.add(item.sodium);
    }
    public void removeItem(BaseProduct item) {items.remove(item);
        calories=calories.subtract(item.calories);
        protein=protein.subtract(item.protein);
        fat=fat.subtract(item.fat);
        sodium=sodium.subtract(item.sodium);
    }
    public List<BaseProduct> getItems(){ return this.items;}
}
