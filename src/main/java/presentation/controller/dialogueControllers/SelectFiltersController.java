package presentation.controller.dialogueControllers;

import business.DeliveryService;
import presentation.controller.userControllers.UserController;
import presentation.view.windows.dialogues.SelectFilters;
import presentation.view.windows.userWindows.UserWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFiltersController {
    DeliveryService service;
    SelectFilters view;
    public SelectFiltersController(UserController parent){
        this.service=parent.getService();
        view=new SelectFilters((UserWindow) parent.getView());
        view.addSearchButtonListener(new Search());
    }
    class Search implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.updateParent(service.searchItem(view.getValues()));
        }
    }
}
