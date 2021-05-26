package presentation.view.windows.userWindows;

import business.items.CompositeProduct;
import presentation.view.utility.WindowPanel;
import presentation.view.utility.cassettes.ProductWindow;
import presentation.view.windows.AppWindow;
import presentation.view.windows.OptionWindow;

import javax.swing.*;
import java.util.Collection;

public class MenuProductsWindow extends OptionWindow {
    protected final JLabel menuName;
    protected final WindowPanel panel;
    public MenuProductsWindow(CompositeProduct menu){
        menuName=new JLabel(menu.getTitle());
        menuName.setForeground(AppWindow.PANEL_COLOR);
        menuName.setFont(AppWindow.HEADER_FONT);
        headerLeft.add(menuName);

        panel=new WindowPanel("This menu doesn't have any products","This should not happen in any circumstance wtf");
        this.contentPane.add(panel);
    }
    public void refresh(Collection src){
        panel.refresh(ProductWindow.toCassettes(src));
    }
}
