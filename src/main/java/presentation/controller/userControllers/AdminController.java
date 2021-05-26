package presentation.controller.userControllers;

import business.items.BaseProduct;
import business.items.MenuItem;
import business.users.Admin;
import data.Serializator;
import presentation.controller.LogInController;
import presentation.controller.MenuProductsController;
import presentation.controller.ShoppingCartController;
import presentation.controller.dialogueControllers.CreateItemController;
import presentation.controller.dialogueControllers.ReportController;
import presentation.view.utility.cassettes.OptionButton;
import presentation.view.windows.userWindows.AdminWindow;
import presentation.view.windows.userWindows.UserWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminController extends UserController {
    public AdminController(LogInController prev, Admin user){
      super(prev,user,new AdminWindow(prev.getView(),user));
        ((UserWindow)view).getWindowContent().setEditModeListener(new EditProductListener());
        ((UserWindow)view).addWindowPanelAction("DELETE",new DeleteProductListener());
        ((UserWindow)view).addWindowPanelAction("TOGGLE",new ToggleButtonListener());
        ((UserWindow)view).addWindowPanelAction("PRODUCTS",new SeeProductsListener());
        ((AdminWindow)view).addFileChooserListener(new ChooseFileButtonListener());
        ((AdminWindow)view).addReportListener(new CreateReportListener());
        ((UserWindow)view).refresh(service.getInventoryItems());
        view.addRefreshButtonListener(new RefreshListener());
        ((UserWindow)view).addLogOutButtonListener(new LogOutListener());
        ((UserWindow)view).addSearchButtonListener(new SearchProductListener());
        ((UserWindow)view).addCancelButtonListener(new CancelSearchListener());
        ((AdminWindow)view).addNewItemListener(new NewItemListener());
        ((UserWindow)view).getSearchBar().addAddvancedButtonListener(new AdvancedSearchListener());
        ((AdminWindow)view).addCartButtonListener(new GetToCartListener());

    }
    class ChooseFileButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser i=new JFileChooser();
            i.setFileFilter(new FileNameExtensionFilter("Comma Separated Values File (*.csv)","csv"));
            if(service.getPreferredPath()!=null)
                i.setCurrentDirectory(service.getPreferredPath());
            int returnValue=i.showOpenDialog(view);
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                try{
                    service.importProductsFromFile(i.getSelectedFile());
                    ((UserWindow)view).refresh(service.getInventoryItems());

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                service.setPreferredPath(i.getSelectedFile().getParentFile());
                Serializator.writeFile(service);
            }
        }
    }
    class EditProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            OptionButton<MenuItem> source=(OptionButton<MenuItem>)e.getSource();
            service.editProduct(source.getItem(),(source.getSource()).getEditValues());
            Serializator.writeFile(service);
            JOptionPane.showMessageDialog(view,"Item edited. Please refresh to see the changes","Success",JOptionPane.PLAIN_MESSAGE);
        }
    }
    class DeleteProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            OptionButton<MenuItem> source=(OptionButton<MenuItem>)e.getSource();
            if(service.deleteProduct(source.getItem())){
                source.getSource().getParent().remove(source.getSource());
                Serializator.writeFile(service);
                JOptionPane.showMessageDialog(view,"Item deleted successfully","Success",JOptionPane.PLAIN_MESSAGE);
            }else
                JOptionPane.showMessageDialog(view,"Error deleting item","Err",JOptionPane.ERROR_MESSAGE);
        }
    }
    class CreateReportListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new ReportController(AdminController.this);
        }
    }
    class ToggleButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            OptionButton<MenuItem> source=(OptionButton<MenuItem>)e.getSource();
            if(!((AdminWindow) view).getPendingItems().add((BaseProduct) source.getItem())){
                ((AdminWindow)view).getPendingItems().remove(source.getItem());
                JOptionPane.showMessageDialog(view,"Item removed from pending Composite Menu","Info",JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(view,"Item added to pending Composite Menu","Info",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    class GetToCartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            new ShoppingCartController(AdminController.this,((AdminWindow)view).getPendingItems());
        }
    }
    class NewItemListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            new CreateItemController(AdminController.this);
        }
    }

}
