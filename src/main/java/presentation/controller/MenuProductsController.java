package presentation.controller;

import business.items.CompositeProduct;
import presentation.controller.userControllers.UserController;

import presentation.view.windows.userWindows.MenuProductsWindow;

public class MenuProductsController extends OptionsController{
    public MenuProductsController(UserController prev, CompositeProduct product) {
        super(new MenuProductsWindow(product), prev.view, prev.service);
        view.addBackButtonListener(new BackButtonListener());
        view.setSize(prev.view.getSize());
        view.setLocationRelativeTo(prev.view);
        ((MenuProductsWindow)view).refresh(product.getItems());
    }

}
