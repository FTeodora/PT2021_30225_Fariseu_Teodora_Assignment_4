package presentation.view.windows.userWindows;

import business.items.BaseProduct;
import business.users.Admin;
import business.users.Client;
import presentation.view.utility.WindowPanel;
import presentation.view.utility.cassettes.ProductWindow;
import presentation.view.windows.AppWindow;
import presentation.view.windows.OptionWindow;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Set;

public class ShoppingCart extends OptionWindow {
    JLabel totalLabel;
    JButton commitButton;
    WindowPanel panel;
    Client client;
    public ShoppingCart(UserWindow prev){
        super(prev);
        panel=new WindowPanel("You haven't added any BaseProducts to a menu yet","Browse through our various selection of items");
        contentPane.add(panel);
        commitButton=new JButton("ADD MENU");
        AppWindow.formatButton(commitButton);

        headerCenter.add(commitButton);
        refreshButton=new JButton();
        AppWindow.formatButton(refreshButton,AppWindow.DARK_COLOR,"refresh.png");
        headerLeft.add(refreshButton);
    }
        public ShoppingCart(Client client,UserWindow prev){
            super(prev);
            this.client=client;
            panel=new WindowPanel("You don't have any items in your cart","Browse through our various selection of items");
            contentPane.add(panel);
            commitButton=new JButton("CONFIRM ORDER");
            AppWindow.formatButton(commitButton);
            totalLabel=new JLabel();
            totalLabel.setForeground(AppWindow.PANEL_COLOR);
            totalLabel.setFont(AppWindow.HEADER_FONT);

            headerCenter.add(totalLabel);
            headerCenter.add(commitButton);
            refreshButton=new JButton();
            AppWindow.formatButton(refreshButton,AppWindow.DARK_COLOR,"refresh.png");
            headerLeft.add(refreshButton);
        }
    public void refresh(){
            panel.refresh(ProductWindow.toCassettes(client.getBasket().getItems()));
            totalLabel.setText(client.getBasket().computePrice().toString());
            center.validate();
            center.repaint();
    }
    public void refresh(Set items){
        panel.refresh(ProductWindow.toCassettes(items));
        center.validate();
        center.repaint();
    }
    public void addCommitButtonListener(ActionListener l){
            commitButton.addActionListener(l);
    }
    public void addPanelAction(String actionName,ActionListener l){
            panel.addAction(actionName,l);
    }
}
