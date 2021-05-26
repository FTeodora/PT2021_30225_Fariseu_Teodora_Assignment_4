package presentation.view.utility.cassettes;

import business.items.BaseProduct;
import business.items.CompositeProduct;
import business.items.MenuItem;
import presentation.view.utility.InputField;
import presentation.view.windows.AppWindow;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

public class ProductWindow extends Cassette<MenuItem> {

    public ProductWindow(MenuItem item){
        super(item);
        labels=new JLabel[6];
        title=new JLabel(item.getTitle());
        labels[0]=new JLabel("Rating: "+item.getRating());
        labels[1]=new JLabel("Price: "+item.getPrice());
        labels[2]=new JLabel(item.getCalories().toString());
        labels[3]=new JLabel(item.getProtein().toString());
        labels[4]=new JLabel(item.getFat().toString());
        labels[5]=new JLabel(item.getSodium().toString());

        center=new JPanel(new GridLayout(2,4));
        JLabel[] middleLabels=new JLabel[4];
        middleLabels[0]=new JLabel("calories");
        middleLabels[1]=new JLabel("protein");
        middleLabels[2]=new JLabel("fat");
        middleLabels[3]=new JLabel("sodium");
        center.setBackground(background);
        colorText();
        for(JLabel i:middleLabels){
            i.setForeground(AppWindow.TEXT_COLOR);
            i.setFont(AppWindow.ITEM_WINDOW_FONT);
            center.add(i);
        }
        viewMode.add(center,BorderLayout.CENTER);
        for(int i=2;i<6;i++)
            center.add(labels[i]);

        footer=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        footer.setBackground(background);
        viewMode.add(footer,BorderLayout.SOUTH);
        footer.add(labels[1]);
        footer.add(labels[0]);
        add(viewMode);
        validate();
        repaint();
    }
    @Override
    public void makeEditable(){
        if(item instanceof CompositeProduct)
            return;
        super.makeEditable();
        labels=new JLabel[6];
        title=new JLabel(item.getTitle());
        JTextField textField=new JTextField(item.getTitle());
        textField.setMinimumSize(new Dimension(400,30));
        textField.setBorder(new LineBorder(AppWindow.SECONDARY_COLOR));
        InputField i1=new InputField(new JLabel("Title:"),textField);
        i1.colorInputField(background,titleColor,AppWindow.ITEM_WINDOW_FONT);
        eHeader.add(i1);

        editableFields.add(i1);
        editableFields.add(new InputField(new JLabel("Rating: "),new JLabel(String.valueOf(item.getRating()))));

        SpinnerNumberModel model5=new SpinnerNumberModel(item.getPrice().intValue(),0,Integer.MAX_VALUE,5);
        editableFields.add(new InputField(new JLabel("Price: "),new JSpinner(model5)));

        SpinnerNumberModel model1=new SpinnerNumberModel(item.getCalories().intValue(),1,Integer.MAX_VALUE,1);
        JLabel caloriesLabel=new JLabel("");
        JSpinner caloriesField=new JSpinner(model1);
        editableFields.add(new InputField(caloriesLabel,caloriesField));

        SpinnerNumberModel model2=new SpinnerNumberModel(item.getProtein().intValue(),0,Integer.MAX_VALUE,1);
        editableFields.add(new InputField(new JLabel(""),new JSpinner(model2)));

        SpinnerNumberModel model3=new SpinnerNumberModel(item.getFat().intValue(),0,Integer.MAX_VALUE,1);
        editableFields.add(new InputField(new JLabel(""),new JSpinner(model3)));

        SpinnerNumberModel model4=new SpinnerNumberModel(item.getSodium().intValue(),0,Integer.MAX_VALUE,1);
        editableFields.add(new InputField(new JLabel(""),new JSpinner(model4)));


        eCenter=new JPanel(new GridLayout(2,4));
        JLabel[] middleLabels=new JLabel[4];
        middleLabels[0]=new JLabel("calories");
        middleLabels[1]=new JLabel("protein");
        middleLabels[2]=new JLabel("fat");
        middleLabels[3]=new JLabel("sodium");
        eCenter.setBackground(background);
        for(JLabel i:middleLabels){
            i.setForeground(AppWindow.TEXT_COLOR);
            i.setFont(AppWindow.ITEM_WINDOW_FONT);
            eCenter.add(i);
        }
        editMode.add(eCenter,BorderLayout.CENTER);
        for(InputField a:editableFields)
            a.colorInputField(background,textColor,AppWindow.ITEM_WINDOW_FONT);
        for(int i=3;i<7;i++)
            eCenter.add(editableFields.get(i));

        eFooter=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        eFooter.setBackground(background);
        editMode.add(eFooter,BorderLayout.SOUTH);
        eFooter.add(editableFields.get(2));
        eFooter.add(editableFields.get(1));
    }
    public static List toCassettes(Collection items){
        if(items==null)
            return null;
        List rez=new ArrayList<ProductWindow>();
        for(Object i:items)
            rez.add(new ProductWindow((MenuItem) i));
        return rez;
    }

    @Override
    public void arrangeButton(Map<String, ActionListener> actions) {
        Map<String,ActionListener> act=new HashMap<>();
        act.putAll(actions);
        if(item instanceof CompositeProduct)
        {
            act.remove("TOGGLE");
        }
        else{
            if(item instanceof BaseProduct)
                act.remove("PRODUCTS");
        }
        super.arrangeButton(act);
    }

    @Override
    public MenuItem getEditValues() {
        BaseProduct product=new BaseProduct();
        product.setTitle((String) editableFields.get(0).getInfo());
        product.setRating(item.getRating());
        product.setPrice(BigInteger.valueOf((Integer)editableFields.get(2).getInfo()));
        product.setCalories(BigInteger.valueOf((Integer)editableFields.get(3).getInfo()));
        product.setProtein(BigInteger.valueOf((Integer)editableFields.get(4).getInfo()));
        product.setFat(BigInteger.valueOf((Integer)editableFields.get(5).getInfo()));
        product.setSodium(BigInteger.valueOf((Integer)editableFields.get(6).getInfo()));
        return product;
    }
}
