package presentation.view.windows.userWindows;

import business.users.Client;
import presentation.view.utility.SearchProductBar;
import presentation.view.utility.WindowPanel;
import presentation.view.windows.AppWindow;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientWindow extends UserWindow{
    JButton basket;
    public ClientWindow(JFrame prev, Client user) {
        super(prev, user);
        searchBar=new SearchProductBar();
        headerCenter.add(searchBar);
        windowContent=new WindowPanel("There are no items yet...","Wait for an admin to add items");
        contentPane.add(windowContent);
        basket=new JButton();
        formatButton(basket, AppWindow.DARK_COLOR,"basket.png");
        headerLeft.add(basket);
    }
    public void addBasketListener(ActionListener l){
        basket.addActionListener(l);
    }
}
