package presentation.controller;

import business.DeliveryService;
import business.items.BaseProduct;
import business.items.MenuItem;
import business.items.OrderItems;
import business.order.Order;
import business.users.Admin;
import business.users.Client;
import data.Serializator;
import data.StreamFileWriter;
import presentation.controller.dialogueControllers.CreateMenuController;
import presentation.controller.userControllers.AdminController;
import presentation.controller.userControllers.ClientController;
import presentation.view.utility.cassettes.OptionButton;
import presentation.view.windows.OptionWindow;
import presentation.view.windows.userWindows.AdminWindow;
import presentation.view.windows.userWindows.ClientWindow;
import presentation.view.windows.userWindows.ShoppingCart;
import presentation.view.windows.userWindows.UserWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ShoppingCartController extends OptionsController{
    Client session;
    Set<BaseProduct> pending;
    public ShoppingCartController(ClientController prev) {
        super(new ShoppingCart((Client) prev.getSession(),(ClientWindow) prev.getView()), prev.getView(), prev.getService());
        this.session=(Client)prev.getSession();
        view.addRefreshButtonListener(new RefreshClient());
        ((ShoppingCart)view).addPanelAction("REMOVE",new RemoveFromCartListener());
        ((ShoppingCart)view).refresh();
        ((ShoppingCart)view).addCommitButtonListener(new CommitOrderListener());
    }
    public ShoppingCartController(AdminController prev, Set<BaseProduct> productSet) {
        super(new ShoppingCart((UserWindow) prev.getView()), prev.getView(), prev.getService());
        pending=productSet;
        view.addRefreshButtonListener(new RefreshButtonListener());
        (view).addRefreshButtonListener(new RefreshAdmin());
        ((ShoppingCart)view).addPanelAction("TOGGLE",new ToggleButtonListener());
        ((ShoppingCart)view).refresh(productSet);
        ((ShoppingCart)view).addCommitButtonListener(new CommitMenuListener());
    }
    class ToggleButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            OptionButton<MenuItem> source=(OptionButton<MenuItem>)e.getSource();
            if(((AdminWindow)view).getPendingItems().add((BaseProduct) source.getItem())==false)
                ((AdminWindow)view).getPendingItems().remove(source.getItem());
            Serializator.writeFile(service);
        }
    }
    class RemoveFromCartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            OptionButton<MenuItem> source=(OptionButton<business.items.MenuItem>)e.getSource();
            (session).removeFromBasket(source.getItem());
            source.getSource().getParent().remove(source.getSource());
            Serializator.writeFile(service);
        }
    }
    class RefreshAdmin implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ((ShoppingCart)view).refresh(pending);
        }
    }
    class RefreshClient implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ((ShoppingCart)view).refresh(session.getBasket().getItems());
        }
    }
    class CommitOrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            service.commitOrder(session);
            JOptionPane.showMessageDialog(view,"Order confirmed","Info",JOptionPane.INFORMATION_MESSAGE);
            Serializator.writeFile(service);
        }
    }
    class CommitMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new CreateMenuController(ShoppingCartController.this);
        }
    }

    public Set<BaseProduct> getPending() {
        return pending;
    }
}
