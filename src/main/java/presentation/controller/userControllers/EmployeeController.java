package presentation.controller.userControllers;

import business.users.Employee;
import presentation.controller.LogInController;
import presentation.controller.userControllers.UserController;
import presentation.view.windows.userWindows.EmployeeWindow;

public class EmployeeController extends UserController {
    public EmployeeController(LogInController prev, Employee session) {
        super(prev,session,new EmployeeWindow(prev.getView(),session));
        prev.getService().addObserver((EmployeeWindow)view);
    }
}
