package presentation.view.utility;

import presentation.view.utility.cassettes.Cassette;
import presentation.view.windows.AppWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class WindowPanel extends JScrollPane {
    JPanel content;
    JLabel errMessage;
    JLabel errProcedure;
    Collection<Cassette> cassettes;
    Iterator<Cassette> currentlyDisplayed;
    Map<String,ActionListener> actions;
    ActionListener editModeListener=null;
    JLabel loadMore;
    int remaining;
    private static final int MAX_CASSETTES=50;
    public WindowPanel(String errMessage,String errProcedure){
        actions=new HashMap<>();
        content=new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        loadMore=new JLabel();
        loadMore.setForeground(AppWindow.PANEL_COLOR);
        loadMore.setFont(AppWindow.ITEM_WINDOW_FONT);
        loadMore.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadMore();
                getParent().validate();
                getParent().repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        setViewportView(content);
        this.errMessage=new JLabel(errMessage);
        this.errMessage.setForeground(AppWindow.PANEL_COLOR);
        this.errMessage.setFont(AppWindow.ITEM_WINDOW_FONT);
        this.errProcedure=new JLabel(errProcedure);
        this.errProcedure.setForeground(AppWindow.PANEL_COLOR);
        this.errProcedure.setFont(AppWindow.ITEM_WINDOW_FONT);
        setBorder(new EmptyBorder(0,0,0,0));
        content.setBackground(AppWindow.HIGHLIGHT_COLOR);
        content.setBorder(new EmptyBorder(10,10,10,10));
        content.setMinimumSize(new Dimension(850,400));
    }
    public void refresh(Collection<Cassette> newCassettes){
        content.removeAll();
        content.revalidate();

        cassettes=newCassettes;
        currentlyDisplayed= cassettes.iterator();
        remaining=cassettes.size();
        if(cassettes!=null&&cassettes.size()!=0)
            loadMore();
        else
        {
            content.add(errMessage);
            content.add(errProcedure);
        }

    }
    public void loadMore(){
        remove(loadMore);
        int i=0;
        while(currentlyDisplayed.hasNext()&&i<MAX_CASSETTES)
        {
            Cassette c=currentlyDisplayed.next();
            if(this.editModeListener!=null){
                c.makeEditable();
                c.addCommitEditButtonListener(editModeListener);
            }
            c.arrangeButton(actions);
            content.add(c);
            remaining--;
            i++;
        }
        if(currentlyDisplayed.hasNext())
        {
            loadMore.setText(remaining+" remaining ... Click to load more");
            content.add(loadMore);
        }
    }
    public void addItem(Cassette obj){
        content.remove(errMessage);
        content.remove(errProcedure);
        content.add(obj);
        if(this.editModeListener!=null){
            obj.makeEditable();
            obj.addCommitEditButtonListener(editModeListener);
        }
        obj.arrangeButton(actions);
        validate();
        repaint();
        remaining++;
    }
public void remove(Cassette obj){
    if(cassettes.remove(obj)){
        content.remove(obj);
        if(remaining>0)
        {
            remaining--;
            loadMore.setText(remaining+" remaining ... Click to load more");
        }
    }
}
public void addAction(String name,ActionListener l){
        actions.put(name,l);
}
public void setEditModeListener(ActionListener l){ editModeListener=l;}
}
