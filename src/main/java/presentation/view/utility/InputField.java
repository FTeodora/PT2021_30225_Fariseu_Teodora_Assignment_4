package presentation.view.utility;

import com.jidesoft.swing.RangeSlider;
import presentation.view.windows.AppWindow;

import javax.swing.*;
import java.awt.*;

public class InputField extends JPanel {
    private JLabel fieldName;
    private JComponent field;
    public InputField(JLabel name,JComponent component){
        this.fieldName=name;
        this.field=component;
        if(!(component instanceof JTextPane)){
            GroupLayout layout=new GroupLayout(this);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addComponent(this.fieldName)
                            .addComponent(this.field)

            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(this.fieldName)
                                    .addComponent(this.field)
                            )
            );
            setLayout(layout);
            this.add(fieldName);
            this.add(field);
            validate();
            repaint();
        }
    }
    public Object getInfo(){
        if(field instanceof JSpinner){
            return ((JSpinner)field).getModel().getValue();
        }
        if(field instanceof RangeSlider)
        {
            Integer[] nums=new Integer[2];
            nums[0]=((RangeSlider)field).getLowValue();
            nums[1]=((RangeSlider)field).getHighValue();
            return nums;

        }
        if(field instanceof JPasswordField)
            return String.valueOf(((JPasswordField) field).getPassword());
        if(field instanceof JTextField)
            return ((JTextField) field).getText();
        if(field instanceof JLabel)
            return ((JLabel)field).getText();
        return null;
    }
    public void colorInputField(Color bg,Color fg,Font font){
        fieldName.setForeground(fg);
        fieldName.setBackground(bg);
        fieldName.setFont(font);
        if(field instanceof  RangeSlider){
            ((RangeSlider)field).setPaintLabels(true);
            (field).setMinimumSize(new Dimension(500,55));
            (field).setPreferredSize(new Dimension(500,55));
        }
        if(field instanceof JSpinner){
            ((JSpinner)field).getEditor().setMaximumSize(new Dimension(50,40));
            ((JSpinner)field).getEditor().setPreferredSize(new Dimension(50,40));
            ((JSpinner)field).getEditor().getComponent(0).setBackground(bg);
            ((JSpinner)field).getEditor().getComponent(0).setForeground(fg);
            ((JSpinner)field).getEditor().setFont(AppWindow.ITEM_WINDOW_FONT);
        }
        field.setForeground(fg);
        field.setBackground(bg);
        field.setFont(font);
        this.setBackground(bg);
    }
}
