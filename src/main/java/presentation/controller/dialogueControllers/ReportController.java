package presentation.controller.dialogueControllers;

import business.DeliveryService;
import data.StreamFileWriter;
import presentation.controller.userControllers.AdminController;
import presentation.view.windows.dialogues.ReportChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class ReportController {
    DeliveryService service;
    ReportChooser view;
    public ReportController(AdminController parent){
        service=parent.getService();
        view=new ReportChooser();
        view.addButtonListener(0,new HourIntervalListener());
        view.addButtonListener(1,new MinTimesOrdered());
        view.addButtonListener(2,new ClientsMinTimeOrdered());
        view.addButtonListener(3,new ProductsOrderedInADayListener());
    }
    class HourIntervalListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Integer[] edge=view.getTimeRange();
            StreamFileWriter.generateOrdersBetweenHoursReport(service.timeIntervalReport(edge[0],edge[1]),edge[0],edge[1]);
        }
    }
    class MinTimesOrdered implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            StreamFileWriter.generateItemsOrderedMoreThan(service.mostOrderedProducts(new BigInteger(view.getMinAmountOrdered().toString())),view.getMinAmountOrdered());
        }
    }
    class ClientsMinTimeOrdered implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer[] edge=view.getMinAmountAndValue();
            StreamFileWriter.generateClientsWhoOrderedAtLeast(service.clientsWithMostOrders(new BigInteger(edge[0].toString()),new BigInteger(edge[1].toString())),edge[0],edge[1]);
        }
    }
    class ProductsOrderedInADayListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            StreamFileWriter.generateProductsOrderedInADay(service.productsOrderedInADay(view.getDate()),view.getDate());
        }
    }
}
