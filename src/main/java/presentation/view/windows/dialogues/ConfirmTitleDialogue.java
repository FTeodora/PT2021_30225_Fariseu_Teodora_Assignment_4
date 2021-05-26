package presentation.view.windows.dialogues;

import presentation.view.utility.InputField;
import presentation.view.windows.AppWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConfirmTitleDialogue extends AppWindow {
    InputField title;
    JButton confirm;
    public ConfirmTitleDialogue(){
        JPanel panel=new JPanel();
        setResizable(false);
        panel.setLayout(new GridLayout(0,1));
        panel.setBackground(AppWindow.HIGHLIGHT_COLOR);
        center.add(panel);
        JTextField field=new JTextField();
        field.setPreferredSize(new Dimension(130,30));
        title=new InputField(new JLabel("Title: "),field);
        title.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.FIELD_FONT);
        confirm=new JButton("ADD MENU");
        AppWindow.formatButton(confirm);
        panel.add(title);
        panel.add(confirm);
        setSize(400,300);
        setLocationRelativeTo(null);
    }
    public String retrieveText(){
        return title.getInfo().toString();
    }
    public void addConfirmButtonListener(ActionListener l){
        confirm.addActionListener(l);
    }

}
