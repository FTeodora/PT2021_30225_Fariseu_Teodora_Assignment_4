package presentation.view.utility.cassettes;

import presentation.view.windows.AppWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;

public class OptionButton<T> extends JButton {
    Cassette<T> source;
    public OptionButton(Cassette src,String optionName){
        super(optionName);
        source=src;
        setBackground(AppWindow.SECONDARY_COLOR);
        setForeground(AppWindow.PANEL_COLOR);
        setBorder(BorderFactory.createCompoundBorder(new LineBorder(AppWindow.TEXT_COLOR),new EmptyBorder(7,7,7,7)));
        setFont(AppWindow.BUTTON_FONT);
    }
    public OptionButton(Cassette src, String optionName, ActionListener l){
        this(src,optionName);
        addActionListener(l);
    }
    public T getItem(){
        return source.item;
    }
    public Cassette<T> getSource(){ return source;}
}