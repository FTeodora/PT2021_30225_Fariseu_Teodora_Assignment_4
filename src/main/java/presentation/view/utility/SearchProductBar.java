package presentation.view.utility;

import business.DeliveryService;
import business.items.MenuItem;
import presentation.view.windows.AppWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import java.awt.event.ActionListener;

public class SearchProductBar extends JPanel {
    private JButton searchButton,cancel,advanced;
    private JTextField product;
    public SearchProductBar(){

        setBackground(AppWindow.DARK_COLOR);
        searchButton=new JButton();
        AppWindow.formatButton(searchButton,AppWindow.DARK_COLOR,"searchIcon.png");
        add(searchButton);

        product=new JTextField();
        product.setFont(AppWindow.BUTTON_FONT);
        product.setBackground(AppWindow.DARK_COLOR.darker().darker());
        product.setForeground(AppWindow.MAIN_COLOR);
        product.setPreferredSize(new Dimension(300,30));
        product.setBorder(new LineBorder(AppWindow.MAIN_COLOR));
        add(product);

        cancel=new JButton();
        AppWindow.formatButton(cancel,AppWindow.DARK_COLOR,"cancel.png");
        cancel.setEnabled(false);
        add(cancel);

        advanced=new JButton();
        AppWindow.formatButton(advanced,AppWindow.DARK_COLOR,"advanced.png");
        add(advanced);

    }
    public String getInfo(){ return product.getText(); }
    public void addSearchButtonListener(ActionListener l){ searchButton.addActionListener(l);}
    public void addCancelButtonListener(ActionListener l){ cancel.addActionListener(l);}
    public void addAddvancedButtonListener(ActionListener l){ advanced.addActionListener(l);}
    public void enableCancel(){
        cancel.setEnabled(true);
    }
    public void disableCancel(){
            cancel.setEnabled(false);
    }
}
