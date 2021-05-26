package presentation.view.utility.cassettes;


import presentation.view.utility.InputField;
import presentation.view.windows.AppWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Map;

public abstract class Cassette<T> extends JPanel {
    protected T item;
    protected JLabel title;
    protected JLabel[] labels;
    protected JPanel viewMode,editMode;
    protected JPanel footer,left,right,center;
    protected JPanel eFooter,eLeft,eRight,eCenter,eHeader;
    protected final Color background= AppWindow.PANEL_COLOR;
    protected final Color titleColor=AppWindow.HIGHLIGHT_COLOR;
    protected final Color textColor=AppWindow.TEXT_COLOR;
    protected ArrayList<OptionButton<T>> actions;
    protected ArrayList<InputField> editableFields;
    protected JPanel header;
    protected OptionButton<T> exitEdit,commitEdit,enterEdit;
    public Cassette(T item){
        this.item=item;
        actions=new ArrayList<>();
        setPreferredSize(new Dimension(700,300));
        setMaximumSize(new Dimension(700,300));
        setBackground(AppWindow.HIGHLIGHT_COLOR);
        setLayout(new CardLayout());
        setBorder(new EmptyBorder(10,0,10,0));
        viewMode=new JPanel();
        viewMode.setLayout(new BorderLayout());
        viewMode.setBackground(background);
        viewMode.setBorder(new EmptyBorder(10,10,10,10));

        header=new JPanel();
        header.setBackground(background);
        viewMode.add(header,BorderLayout.NORTH);

        add(viewMode);

    }
    protected void colorText(){
        for(JLabel i:labels){
            i.setForeground(textColor);
            i.setFont(AppWindow.ITEM_WINDOW_FONT);
        }
        title.setForeground(titleColor);
        title.setFont(AppWindow.ITEM_WINDOW_FONT.deriveFont(AppWindow.ITEM_WINDOW_FONT.getSize()*1.3f));
        header.add(title);
    }
    public void makeEditable(){
        editMode=new JPanel();
        editMode.setLayout(new BorderLayout());
        editMode.setBackground(background);
        editMode.setBorder(new EmptyBorder(10,10,10,10));

        eHeader=new JPanel(new FlowLayout(FlowLayout.LEADING));
        eHeader.setBackground(background);
        editMode.add(eHeader,BorderLayout.NORTH);
        add(editMode);
        editableFields=new ArrayList<>();
        enterEdit=new OptionButton<T>(this,"EDIT");
        enterEdit.addActionListener(e -> {
            CardLayout cl=(CardLayout)this.getLayout();
            cl.last(this);
        });
        exitEdit=new OptionButton<T>(this,"CANCEL");
        exitEdit.addActionListener(e -> {
            CardLayout cl=(CardLayout)this.getLayout();
            cl.first(this);
        });
        commitEdit=new OptionButton(this,"VALIDATE");
        commitEdit.addActionListener(e -> {
            CardLayout cl=(CardLayout)this.getLayout();
            cl.first(this);
        });
        header.add(enterEdit);
        eHeader.add(commitEdit);
        eHeader.add(exitEdit);


    }
    public T getEditValues(){
        return null;
    };
    public void arrangeButton(Map<String,ActionListener> actions){
        actions.entrySet().stream().forEach(i-> header.add(new OptionButton<T>(this,i.getKey(),i.getValue())));
    }
    public void addCommitEditButtonListener(ActionListener l){
        if(commitEdit!=null)
            commitEdit.addActionListener(l);
    }

}
