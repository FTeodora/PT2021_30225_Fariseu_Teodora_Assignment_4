package presentation.controller.dialogueControllers;

import business.DeliveryService;
import business.items.BaseProduct;
import business.items.CompositeProduct;
import data.Serializator;
import presentation.controller.ShoppingCartController;
import presentation.view.windows.dialogues.ConfirmTitleDialogue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class CreateMenuController {
    ConfirmTitleDialogue view;
    DeliveryService service;
    Set<BaseProduct> items;
    public CreateMenuController(ShoppingCartController prev){
        items=prev.getPending();
        service= prev.getService();
        view=new ConfirmTitleDialogue();
        view.addConfirmButtonListener(new ConfirmTitle());
    }
    class ConfirmTitle implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CompositeProduct item=new CompositeProduct(items,view.retrieveText());
            item.setPrice(item.computePrice());
            if(service.addProduct(item)) {
                items.clear();
                JOptionPane.showMessageDialog(view, "Item added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(view,"Error adding item!","Err",JOptionPane.ERROR_MESSAGE);
            Serializator.writeFile(service);
        }
    }
}
