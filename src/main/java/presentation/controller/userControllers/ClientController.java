package presentation.controller.userControllers;

import business.items.MenuItem;
import business.users.Client;

import data.Serializator;
import presentation.controller.LogInController;
import presentation.controller.ShoppingCartController;
import presentation.controller.userControllers.UserController;
import presentation.view.utility.cassettes.OptionButton;
import presentation.view.utility.cassettes.ProductWindow;
import presentation.view.windows.userWindows.ClientWindow;
import presentation.view.windows.userWindows.UserWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController extends UserController {
    public ClientController(LogInController prev, Client session) {
        super(prev,session,new ClientWindow(prev.getView(),session));
        ((UserWindow)view).getWindowContent().addAction("ORDER",new AddToOrderButtonListener());
        ((UserWindow)view).addWindowPanelAction("PRODUCTS",new SeeProductsListener());
        ((UserWindow)view).refresh(service.getInventoryItems());
        view.addRefreshButtonListener(new RefreshListener());
        ((UserWindow)view).addLogOutButtonListener(new LogOutListener());
        ((UserWindow)view).addSearchButtonListener(new SearchProductListener());
        ((UserWindow)view).addCancelButtonListener(new CancelSearchListener());
        ((UserWindow)view).getSearchBar().addAddvancedButtonListener(new AdvancedSearchListener());
        ((ClientWindow)view).addBasketListener(new ViewBasketListener());

    }
    class AddToOrderButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            OptionButton<MenuItem> source=(OptionButton<business.items.MenuItem>)e.getSource();
            ((Client)session).addToBasket(source.getItem());
            source.getSource().remove(source);
            Serializator.writeFile(service);
        }
    }
    class ViewBasketListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ShoppingCartController(ClientController.this);
            view.setVisible(false);
        }
    }

}
