package presentation.view.windows;

import presentation.view.utility.InputField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInWindow extends AppWindow{
    private final JPanel footer;
    private final JButton login,create;
    private final JPanel fields;
    private final InputField user,pass;
    public LogInWindow() {
        super();
        setResizable(false);
        setSize(600,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        center.setBorder(new EmptyBorder(30,20,20,10));
        fields=new JPanel();
        fields.setBackground(AppWindow.HIGHLIGHT_COLOR);
        fields.setPreferredSize(new Dimension(490,280));
        fields.setBorder(new EmptyBorder(50,25,10,30));
        //fields.setLayout(new BoxLayout(fields,BoxLayout.Y_AXIS));

        JLabel userLabel=new JLabel("Username: ");
        JTextField userField=new JTextField();
        userField.setPreferredSize(new Dimension(300,30));
        userField.setBorder(new LineBorder(AppWindow.PANEL_COLOR,1));
        user=new InputField(userLabel,userField);
        user.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        user.setBackground(AppWindow.HIGHLIGHT_COLOR);

        JLabel passLabel=new JLabel("Password: ");
        JPasswordField passField=new JPasswordField();
        passField.setPreferredSize(new Dimension(300,30));
        passField.setBorder(new LineBorder(AppWindow.PANEL_COLOR,1));
        pass=new InputField(passLabel,passField);
        pass.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        pass.setBackground(AppWindow.HIGHLIGHT_COLOR);

        fields.add(user);
        fields.add(pass);
        center.add(fields);

        footer=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        footer.setBackground(AppWindow.TEXT_COLOR);

        login=new JButton("LOG IN");
        formatButton(login);
        create=new JButton("NEW ACCOUNT");
        formatButton(create);
        footer.setBackground(AppWindow.DARK_COLOR);
        footer.add(login);
        footer.add(create);
        this.add(footer,BorderLayout.SOUTH);

        getContentPane().validate();
        getContentPane().repaint();
    }
    public String getUsername(){ return (String)user.getInfo();}
    public String getPassword(){ return (String)pass.getInfo();}
    public void addLogInListener(ActionListener l){ this.login.addActionListener(l);}
    public void addNewAccount(ActionListener l){ this.create.addActionListener(l);}
}
