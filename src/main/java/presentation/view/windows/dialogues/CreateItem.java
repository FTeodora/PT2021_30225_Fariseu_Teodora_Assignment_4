package presentation.view.windows.dialogues;

import business.items.BaseProduct;
import com.jidesoft.swing.RangeSlider;
import presentation.view.utility.InputField;
import presentation.view.utility.cassettes.ProductWindow;
import presentation.view.windows.AppWindow;
import presentation.view.windows.userWindows.UserWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Hashtable;

public class CreateItem extends AppWindow {
    JTabbedPane tabbedPane;
    InputField title,calories,sodium,protein,fat,price;
    JButton add;
    UserWindow parent;
    public CreateItem(UserWindow parent){
        super();
        this.parent=parent;

        setResizable(false);
        JPanel cont=new JPanel();
        cont.setPreferredSize(new Dimension(600,700));
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

        SpinnerNumberModel model=new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1);
        JLabel caloriesLabel=new JLabel("Calories : ");
        JSpinner caloriesField=new JSpinner(model);
        calories=new InputField(caloriesLabel,caloriesField);
        calories.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        calories.setBackground(AppWindow.HIGHLIGHT_COLOR);

        SpinnerNumberModel model2=new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1);
        JLabel sodiumLabel=new JLabel("Sodium: ");
        JSpinner sodiumField=new JSpinner(model2);
        sodium=new InputField(sodiumLabel,sodiumField);
        sodium.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        sodium.setBackground(AppWindow.HIGHLIGHT_COLOR);

        SpinnerNumberModel model3=new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1);
        JLabel proteinLabel=new JLabel("Protein : ");
        JSpinner proteinField=new JSpinner(model3);
        protein=new InputField(proteinLabel,proteinField);
        protein.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        protein.setBackground(AppWindow.HIGHLIGHT_COLOR);

        SpinnerNumberModel model4=new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1);
        JLabel fatLabel=new JLabel("Fat: ");
        JSpinner fatField=new JSpinner(model4);
        fat=new InputField(fatLabel,fatField);
        fat.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        fat.setBackground(AppWindow.HIGHLIGHT_COLOR);

        SpinnerNumberModel model5=new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1);
        JLabel priceLabel=new JLabel("Price: ");
        JSpinner priceField=new JSpinner(model5);
        price=new InputField(priceLabel,priceField);
        price.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        price.setBackground(AppWindow.HIGHLIGHT_COLOR);

        add=new JButton("ADD");
        AppWindow.formatButton(add);

        headerRight.add(add);
        cont.add(title);
        cont.add(calories);
        cont.add(sodium);
        cont.add(protein);
        cont.add(fat);
        cont.add(price);
        setSize(700,800);
        setLocationRelativeTo(parent);

    }
    public BaseProduct getValues(){
       BaseProduct items=new BaseProduct();
        items.setTitle((String) title.getInfo());
        items.setCalories(new BigInteger(String.valueOf(calories.getInfo())));
        items.setProtein(new BigInteger(String.valueOf(protein.getInfo())));
        items.setFat(new BigInteger(String.valueOf(fat.getInfo())));
        items.setSodium(new BigInteger(String.valueOf(sodium.getInfo())));
        items.setPrice(new BigInteger(String.valueOf(price.getInfo())));
        return items;
    }
    public void addAddButtonListener(ActionListener l){
        add.addActionListener(l);
    }
    public void updateParent(BaseProduct newItem){
        parent.getWindowContent().add(new ProductWindow(newItem));
    }

}
