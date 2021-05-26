package presentation.controller.dialogueControllers;

import business.DeliveryService;
import presentation.controller.userControllers.UserController;
import presentation.view.windows.dialogues.CreateItem;
import presentation.view.windows.dialogues.SelectFilters;
import presentation.view.windows.userWindows.UserWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateItemController {
    DeliveryService service;
    CreateItem view;
    public CreateItemController(UserController parent){
        this.service=parent.getService();
        view=new CreateItem((UserWindow) parent.getView());
        view.addAddButtonListener(new AddButtonListener());
    }
    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(service.addProduct(view.getValues())) {

                JOptionPane.showMessageDialog(view, "Item added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(view,"Error adding item!","Err",JOptionPane.ERROR_MESSAGE);

        }
    }
}
