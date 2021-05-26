package presentation.controller;

import business.DeliveryService;
import business.IDeliveryServiceProcessing;
import presentation.view.windows.LogInWindow;
import presentation.view.windows.OptionWindow;
import presentation.view.windows.userWindows.UserWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsController {
    protected OptionWindow view;
    protected JFrame prev;
    protected DeliveryService service;
    public OptionsController(OptionWindow window,JFrame prev,DeliveryService service){
        view=window;
        view.addBackButtonListener(new BackButtonListener());
        this.service=service;
        this.prev=prev;

    }
    class BackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            prev.setVisible(true);
        }
    }
    protected class RefreshButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    public DeliveryService getService() {
        return service;
    }

    public OptionWindow getView() {
        return view;
    }
}
