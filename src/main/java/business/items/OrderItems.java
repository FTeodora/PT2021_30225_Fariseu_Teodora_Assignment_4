package business.items;

import java.io.Serial;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class OrderItems extends MenuItem{
    @Serial
    private static final long serialVersionUID = -698423501224202690L;
    Set<MenuItem> items;
    public OrderItems(){
        items=new HashSet<>();
    }
    public OrderItems(Set<MenuItem> src){
        items=new HashSet<>();
        for(MenuItem i:src)
            items.add(i);
    }
    @Override
    public BigInteger computePrice() {
        return items.stream().map(it->it.getPrice()).reduce(new BigInteger("0"),(init,i)-> init=init.add(i));
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof  OrderItems))
            return false;
        OrderItems o=(OrderItems) obj;
        if(o.getItems().size()!=this.items.size())
            return false;
        Iterator<MenuItem> it1=o.items.iterator();
        Iterator<MenuItem> it2=this.items.iterator();
        while(it1.hasNext()){
            if(!it1.next().equals(it2.next()))
                return false;
        }
        return true;
    }
    public boolean addItem(MenuItem item)
    {
        return items.add(item);
    }
    public boolean removeItem(MenuItem item){
        return items.remove(item);
    }
    public Set<MenuItem> getItems(){ return this.items; }
    public int getAmount(){
        return items.size();
    }
}
