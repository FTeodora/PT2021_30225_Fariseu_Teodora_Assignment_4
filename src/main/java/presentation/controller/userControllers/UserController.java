package presentation.controller.userControllers;

import business.items.CompositeProduct;
import business.items.MenuItem;
import business.users.User;
import presentation.controller.LogInController;
import presentation.controller.MenuProductsController;
import presentation.controller.OptionsController;
import presentation.controller.dialogueControllers.SelectFiltersController;
import presentation.view.utility.cassettes.OptionButton;
import presentation.view.windows.userWindows.UserWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController extends OptionsController {

    protected User session;
    public UserController(LogInController prev, User session, UserWindow view){
        super(view,prev.getView(),prev.getService());
        this.session=session;
    }
    class LogOutListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }
    class SearchProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ((UserWindow)view).refresh(service.searchItem(((UserWindow)view).getSearchInfo()));
            ((UserWindow)view).getSearchBar().enableCancel();
        }
    }
    class CancelSearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ((UserWindow)view).refresh(service.getInventoryItems());
            ((UserWindow)view).getSearchBar().disableCancel();
        }
    }
    class AdvancedSearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            new SelectFiltersController(UserController.this);
        }
    }
    class RefreshListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ((UserWindow)view).refresh(service.getInventoryItems());
        }
    }

    public User getSession() {
        return session;
    }
    class SeeProductsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            OptionButton<MenuItem> source=(OptionButton<MenuItem>)e.getSource();
            new MenuProductsController(UserController.this,(CompositeProduct)source.getItem());
        }
    }
}
