package presentation.controller;

import business.exceptions.BadInputException;
import data.Serializator;
import presentation.view.windows.CreateAccountWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountController extends OptionsController {
    public CreateAccountController(LogInController prev) {
        super(new CreateAccountWindow(prev.getView()), prev.getView(), prev.getService());
        ((CreateAccountWindow) view).addCreateListener(new AddAccountListener());
    }

    class AddAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (service.createAccount(((CreateAccountWindow) view).extractInfo()))
                {
                    JOptionPane.showMessageDialog(view, "Account created!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Serializator.writeFile(service);
                }
                else
                    JOptionPane.showMessageDialog(view, "Account already exists!", "Err", JOptionPane.ERROR_MESSAGE);
                Serializator.writeFile(service);
            } catch (BadInputException badInputException) {
                JOptionPane.showMessageDialog(view, badInputException.getMessage(), "Err", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
