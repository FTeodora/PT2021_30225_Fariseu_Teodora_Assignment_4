package presentation.view.windows.dialogues;

import com.jidesoft.swing.RangeSlider;
import presentation.view.utility.InputField;
import presentation.view.windows.AppWindow;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Hashtable;

public class ReportChooser extends AppWindow {
    JPanel panel;
    JButton[] buttons;
    InputField[] inputs;
    public ReportChooser(){
        panel=new JPanel();
        panel.setBackground(AppWindow.HIGHLIGHT_COLOR);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        center.add(panel);
        setSize(850,700);
        setLocationRelativeTo(null);

        buttons=new JButton[4];
        inputs=new InputField[5];
        Hashtable<Integer,JLabel> timeRangeTable=new Hashtable<>();
        for(int i=0;i<24;i++)
        {
            JLabel label=new JLabel(String.valueOf(i));
            label.setForeground(AppWindow.PANEL_COLOR);
            timeRangeTable.put(i, label);
        }
        buttons[0]=new JButton("Generate type 1");
        RangeSlider slider=new RangeSlider(0,23);
        slider.setLabelTable(timeRangeTable);
        inputs[0]=new InputField(new JLabel("Time interval (0,23) "),slider);
        inputs[0].colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.FIELD_FONT);

        SpinnerNumberModel numberModel=new SpinnerNumberModel(0,0,200,1);
        SpinnerNumberModel numberModel2=new SpinnerNumberModel(0,0,20000,1);
        inputs[1]=new InputField(new JLabel("Items ordered at least "),new JSpinner(numberModel));
        buttons[1]=new JButton("Generate type 2");
        inputs[1].colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.FIELD_FONT);

        SpinnerNumberModel numberModel1=new SpinnerNumberModel(0,0,200,1);
        inputs[2]=new InputField(new JLabel("Clients who ordered at least "),new JSpinner(numberModel1));
        inputs[3]=new InputField(new JLabel("With a value at least "),new JSpinner(numberModel2));
        buttons[2]=new JButton("Generate type 3");
        inputs[2].colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.FIELD_FONT);
        inputs[3].colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.FIELD_FONT);

        SimpleDateFormat model = new SimpleDateFormat("dd.MM.yyyy");
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, model.toPattern()));
        inputs[4]=new InputField(new JLabel("Items ordered on "),spinner);
        buttons[3]=new JButton("Generate type 4");
        inputs[4].colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.FIELD_FONT);

        for(int i=0;i<4;i++)
            AppWindow.formatButton(buttons[i]);
        panel.add(inputs[0]);
        panel.add(buttons[0]);

        panel.add(inputs[1]);
        panel.add(buttons[1]);

        panel.add(inputs[2]);
        panel.add(inputs[3]);
        panel.add(buttons[2]);

        panel.add(inputs[4]);
        panel.add(buttons[3]);

    }
    public void addButtonListener(int n, ActionListener l){
        buttons[n].addActionListener(l);
    }
    public Integer[] getTimeRange(){
        return (Integer[]) inputs[0].getInfo();
    }
    public Integer getMinAmountOrdered(){ return (Integer) inputs[1].getInfo(); }
    public Integer[] getMinAmountAndValue(){ Integer[] vals=new Integer[2];
    vals[0]=(Integer) inputs[2].getInfo();
    vals[1]=(Integer) inputs[3].getInfo();
    return vals;
    }
    public LocalDate getDate(){ return ((Date) inputs[4].getInfo()).toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate(); }
}
