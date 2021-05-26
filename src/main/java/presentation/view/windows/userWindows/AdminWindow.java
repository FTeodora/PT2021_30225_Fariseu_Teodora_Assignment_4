package presentation.view.windows.userWindows;

import business.items.BaseProduct;
import business.users.Admin;
import presentation.view.utility.SearchProductBar;
import presentation.view.utility.WindowPanel;
import presentation.view.windows.AppWindow;
import presentation.view.windows.LogInWindow;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class AdminWindow extends UserWindow{
    private JButton fileSelect,generateReport,cart,addItem;
    private Set<BaseProduct> pendingItems;
    public AdminWindow(LogInWindow prev, Admin user) {
        super(prev, user);
        pendingItems=new HashSet<>();
        searchBar=new SearchProductBar();
        headerCenter.add(searchBar);
        generateReport=new JButton();
        addItem=new JButton("CREATE BASE PRODUCT");
        cart=new JButton("CREATE MENU FROM SELECTED PRODUCTS");
        formatButton(addItem);
        formatButton(cart);
        formatButton(generateReport, AppWindow.DARK_COLOR,"report.png");

        footer.add(generateReport);
        fileSelect=new JButton("UPLOAD FROM FILE");
        formatButton(fileSelect);
        footer.add(fileSelect);
        footer.add(addItem);
        footer.add(cart);
        windowContent=new WindowPanel("There are no items","Try adding a new one or inserting from a .csv");
        contentPane.add(windowContent);


        validate();
        repaint();
    }
    public void addFileChooserListener(ActionListener l){ fileSelect.addActionListener(l);}
    public void addReportListener(ActionListener l) { generateReport.addActionListener(l);}
    public void addCartButtonListener(ActionListener l ){ cart.addActionListener(l);}
    public void addNewItemListener(ActionListener l){ addItem.addActionListener(l);}
    public Set<BaseProduct> getPendingItems(){ return this.pendingItems;}

}
