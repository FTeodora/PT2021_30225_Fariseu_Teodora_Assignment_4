package presentation.view.windows.dialogues;

import business.items.BaseProduct;
import presentation.view.utility.InputField;
import presentation.view.windows.AppWindow;
import presentation.view.windows.userWindows.UserWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Hashtable;

import com.jidesoft.swing.RangeSlider;
public class SelectFilters extends AppWindow {
    InputField title,calories,sodium,protein,fat,price,rating;
    JButton search;
    Hashtable labelTable = new Hashtable();
    Hashtable rangeLabelTable = new Hashtable();
    int valueMax=1000;
    UserWindow parent;
    public SelectFilters(UserWindow parent){
        super();
        this.parent=parent;
        for(int i=0;i<valueMax;i+=100)
        {
            JLabel label=new JLabel(String.valueOf(i));
            label.setForeground(AppWindow.PANEL_COLOR);
            labelTable.put(i, label);
        }

        for(int i=0;i<5;i++)
        {
            JLabel label=new JLabel(String.valueOf(i));
            label.setFont(AppWindow.FIELD_FONT);
            label.setForeground(AppWindow.PANEL_COLOR);
            rangeLabelTable.put(i, label);
        }

        setResizable(false);
        JPanel cont=new JPanel();
        cont.setLayout(new GridLayout(0,1));
        cont.setBackground(AppWindow.HIGHLIGHT_COLOR);
        center.add(cont);

        JLabel titleLabel=new JLabel("Title: ");
        JTextField titleField=new JTextField();
        titleField.setPreferredSize(new Dimension(300,30));
        titleField.setBorder(new LineBorder(AppWindow.PANEL_COLOR,1));
        title=new InputField(titleLabel,titleField);
        title.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        title.setBackground(AppWindow.HIGHLIGHT_COLOR);

        JLabel caloriesLabel=new JLabel("Calories range: ");
        RangeSlider caloriesField=new RangeSlider(0,valueMax);
        //caloriesField.setPreferredSize(new Dimension(300,60));
        caloriesField.setLabelTable(labelTable);
        calories=new InputField(caloriesLabel,caloriesField);
        calories.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        calories.setBackground(AppWindow.HIGHLIGHT_COLOR);

        JLabel sodiumLabel=new JLabel("Sodium range: ");
        RangeSlider sodiumField=new RangeSlider(0,valueMax);
        //sodiumField.setPreferredSize(new Dimension(300,60));
        sodiumField.setLabelTable(labelTable);
        sodium=new InputField(sodiumLabel,sodiumField);
        sodium.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        sodium.setBackground(AppWindow.HIGHLIGHT_COLOR);

        JLabel proteinLabel=new JLabel("Protein range: ");
        RangeSlider proteinField=new RangeSlider(0,valueMax);
        //proteinField.setPreferredSize(new Dimension(300,60));
        protein=new InputField(proteinLabel,proteinField);
        proteinField.setLabelTable(labelTable);
        protein.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        protein.setBackground(AppWindow.HIGHLIGHT_COLOR);

        JLabel fatLabel=new JLabel("Fat range: ");
        RangeSlider fatField=new RangeSlider(0,valueMax);
       // fatField.setPreferredSize(new Dimension(300,50));
        fatField.setLabelTable(labelTable);
        fat=new InputField(fatLabel,fatField);
        fat.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        fat.setBackground(AppWindow.HIGHLIGHT_COLOR);

        JLabel priceLabel=new JLabel("Price range: ");
        RangeSlider priceField=new RangeSlider(0,valueMax);
        //priceField.setPreferredSize(new Dimension(300,50));
        priceField.setLabelTable(labelTable);
        price=new InputField(priceLabel,priceField);
        price.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        price.setBackground(AppWindow.HIGHLIGHT_COLOR);

        JLabel ratingLabel=new JLabel("Rating range: ");
        RangeSlider ratingField=new RangeSlider(0,5);
        //priceField.setPreferredSize(new Dimension(300,50));
        ratingField.setLabelTable(rangeLabelTable);
        rating=new InputField(ratingLabel,ratingField);
        rating.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        rating.setBackground(AppWindow.HIGHLIGHT_COLOR);

        search=new JButton("SEARCH");
        AppWindow.formatButton(search);

        headerRight.add(search);
        cont.add(title);
        cont.add(calories);
        cont.add(sodium);
        cont.add(protein);
        cont.add(fat);
        cont.add(price);
        cont.add(rating);
        setSize(850,700);
        setLocationRelativeTo(parent);

    }
    public business.items.MenuItem[] getValues(){
        business.items.MenuItem[] items=new business.items.MenuItem[2];
        String t=(String) title.getInfo();
        Integer[] c,s,f,p,pr,r;
        c=(Integer[]) calories.getInfo();
        s=(Integer[]) sodium.getInfo();
        f=(Integer[]) fat.getInfo();
        p=(Integer[]) protein.getInfo();
        pr=(Integer[]) price.getInfo();
        r=(Integer[]) rating.getInfo();
        items[0]=new BaseProduct();
        items[0].setTitle(t);
        items[0].setCalories(new BigInteger(String.valueOf(c[0])));
        items[0].setSodium(new BigInteger(String.valueOf(s[0])));
        items[0].setFat(new BigInteger(String.valueOf(f[0])));
        items[0].setProtein(new BigInteger(String.valueOf(p[0])));
        items[0].setPrice(new BigInteger(String.valueOf(pr[0])));
        items[0].setRating(Float.valueOf(r[0]));

        items[1]=new BaseProduct();
        items[1].setTitle(t);
        items[1].setCalories(new BigInteger(String.valueOf(c[1])));
        items[1].setSodium(new BigInteger(String.valueOf(s[1])));
        items[1].setFat(new BigInteger(String.valueOf(f[1])));
        items[1].setProtein(new BigInteger(String.valueOf(p[1])));
        items[1].setPrice(new BigInteger(String.valueOf(pr[1])));
        items[1].setRating(Float.valueOf(r[1]));
        System.out.println(items[0]);
        System.out.println(items[1]);
        return items;
    }
    public void addSearchButtonListener(ActionListener l){
        search.addActionListener(l);
    }
    public void updateParent(Collection newItems){
        parent.refresh(newItems);
        parent.getSearchBar().enableCancel();
    }
}
