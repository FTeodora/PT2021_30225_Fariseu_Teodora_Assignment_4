package presentation.controller;

import business.DeliveryService;
import business.exceptions.LoginException;
import business.users.Admin;
import business.users.Client;
import business.users.Employee;
import business.users.User;
import data.Serializator;
import presentation.controller.userControllers.AdminController;
import presentation.controller.userControllers.ClientController;
import presentation.controller.userControllers.EmployeeController;
import presentation.view.windows.LogInWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController {
    private DeliveryService service;
    private LogInWindow view;
    public LogInController(){
        service= Serializator.readFile();
        if(service==null)
        {
            service=new DeliveryService();
        }
        view=new LogInWindow();
        view.addLogInListener(new LogInButtonListener());
        view.addNewAccount(new NewAccountListener());
    }
    class LogInButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                User found=service.logIn(view.getUsername(),view.getPassword());
                if(found instanceof Client)
                    new ClientController(LogInController.this,(Client) found);
                if(found instanceof Employee)
                    new EmployeeController(LogInController.this,(Employee) found);
                if(found instanceof Admin)
                    new AdminController(LogInController.this,(Admin) found);
            } catch (LoginException loginException) {
                JOptionPane.showMessageDialog(view,loginException.getMessage(),"Err",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    class NewAccountListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new CreateAccountController(LogInController.this);
            view.setVisible(false);
        }
    }
    public DeliveryService getService() {
        return service;
    }

    public LogInWindow getView() {
        return view;
    }
}
