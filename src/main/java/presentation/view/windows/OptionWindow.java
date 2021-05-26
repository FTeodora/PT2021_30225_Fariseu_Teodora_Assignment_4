package presentation.view.windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class OptionWindow extends AppWindow{
    protected JPanel contentPane;
    protected JButton backButton,refreshButton;
    public OptionWindow(JFrame prev){
        this();
        setSize(prev.getSize());
        setLocationRelativeTo(prev);
    }
    public OptionWindow(){
        contentPane=new JPanel();
        contentPane.setBackground(AppWindow.PANEL_COLOR);
        contentPane.setBorder(new EmptyBorder(10,10,10,10));

        center.add(contentPane);
        backButton=new JButton();
        AppWindow.formatButton(backButton,AppWindow.DARK_COLOR,"back.png");
        headerLeft.add(backButton, BorderLayout.WEST);

    }
    public void addBackButtonListener(ActionListener l){
        backButton.addActionListener(l);
    }
    public void addRefreshButtonListener(ActionListener l){
        refreshButton.addActionListener(l);
    }
}
