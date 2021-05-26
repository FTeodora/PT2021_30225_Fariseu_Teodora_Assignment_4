package presentation.view.windows.userWindows;

import business.items.MenuItem;
import business.users.User;
import presentation.view.utility.SearchProductBar;
import presentation.view.utility.WindowPanel;
import presentation.view.utility.cassettes.ProductWindow;
import presentation.view.windows.AppWindow;
import presentation.view.windows.OptionWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;


public class UserWindow extends OptionWindow {
    private JLabel nameLabel;
    private JButton logOut;
    protected WindowPanel windowContent;
    protected JPanel footer;
    protected User session;
    protected SearchProductBar searchBar;
    public UserWindow(JFrame prev, User user) {
        super();
        session=user;
        setSize(1000,740);
        setLocationRelativeTo(prev);

        refreshButton=new JButton();
        formatButton(refreshButton,AppWindow.DARK_COLOR,"refresh.png");
        headerLeft.add(refreshButton);
        nameLabel=new JLabel(user.getClass().getSimpleName()+" "+user.getUserName());
        nameLabel.setFont(AppWindow.HEADER_FONT);
        nameLabel.setForeground(AppWindow.SECONDARY_COLOR);

        logOut=new JButton();
        formatButton(logOut,AppWindow.DARK_COLOR,"logout.png");

        //notifications=new JButton();
        //formatButton(notifications,AppWindow.DARK_COLOR,"notifications.png");

        headerRight.add(nameLabel,BorderLayout.CENTER);

        headerRight.add(logOut);
        //headerRight.add(notifications);

        footer=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        footer.setBackground(AppWindow.DARK_COLOR);
        add(footer,BorderLayout.SOUTH);


    }
    public void addLogOutButtonListener(ActionListener l) { logOut.addActionListener(l);}
    public String getSearchInfo() { return searchBar.getInfo(); }
    public void addSearchButtonListener(ActionListener l){
        searchBar.addSearchButtonListener(l);
    }
    public void addCancelButtonListener(ActionListener l){
        searchBar.addCancelButtonListener(l);
    }
    public void refresh(Collection<MenuItem> newItems){
        windowContent.refresh(ProductWindow.toCassettes(newItems));
        content.validate();
        content.repaint();
    }
    public void addWindowPanelAction(String actionName,ActionListener l){
        windowContent.addAction(actionName,l);
    }
    public SearchProductBar getSearchBar(){ return this.searchBar; }
    public WindowPanel getWindowContent(){ return this.windowContent;}
}
