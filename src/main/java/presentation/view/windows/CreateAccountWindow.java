package presentation.view.windows;

import business.exceptions.BadInputException;
import business.users.Client;
import presentation.view.utility.InputField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class CreateAccountWindow extends OptionWindow{
    InputField username, password, phone, eMail;
    JButton createAccount;
    public CreateAccountWindow(JFrame prev){
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(500,500));
        contentPane.setBorder(new EmptyBorder(60,30,20,30));
        contentPane.setBackground(AppWindow.HIGHLIGHT_COLOR);
        JLabel userLabel=new JLabel("Username: ");
        JTextField userField=new JTextField();
        userField.setPreferredSize(new Dimension(300,30));
        userField.setBorder(new LineBorder(AppWindow.PANEL_COLOR,1));
        username=new InputField(userLabel,userField);
        username.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        username.setBackground(AppWindow.HIGHLIGHT_COLOR);

        JLabel passLabel=new JLabel("Password: ");
        JPasswordField passField=new JPasswordField();
        passField.setPreferredSize(new Dimension(300,30));
        passField.setBorder(new LineBorder(AppWindow.PANEL_COLOR,1));
        password=new InputField(passLabel,passField);
        password.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        password.setBackground(AppWindow.HIGHLIGHT_COLOR);

        try{
        MaskFormatter mf1 = new MaskFormatter("(07)##-###-###");
        mf1.setPlaceholderCharacter(' ');
            JLabel phoneLabel=new JLabel("Phone number: ");
            JFormattedTextField phoneField=new JFormattedTextField(mf1);
            phoneField.setPreferredSize(new Dimension(300,30));
            phoneField.setBorder(new LineBorder(AppWindow.PANEL_COLOR,1));
            phone=new InputField(phoneLabel,phoneField);
            phone.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
            phone.setBackground(AppWindow.HIGHLIGHT_COLOR);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JLabel emailLabel=new JLabel("e-mail: ");
        JTextField emailField=new JTextField();
        emailField.setPreferredSize(new Dimension(300,30));
        emailField.setBorder(new LineBorder(AppWindow.PANEL_COLOR,1));
        eMail=new InputField(emailLabel,emailField);
        eMail.colorInputField(AppWindow.HIGHLIGHT_COLOR,AppWindow.PANEL_COLOR,AppWindow.BUTTON_FONT);
        eMail.setBackground(AppWindow.HIGHLIGHT_COLOR);

        createAccount=new JButton("CONFIRM");
        formatButton(createAccount);

        headerRight.add(createAccount);

        contentPane.add(username);
        contentPane.add(password);
        contentPane.add(phone);
        contentPane.add(eMail);

        contentPane.setBackground(AppWindow.HIGHLIGHT_COLOR);
        setSize(600,750);
        setLocationRelativeTo(prev);

    }
public Client extractInfo() throws BadInputException{
        String userName=(String) username.getInfo();
        if(userName.trim().equals(""))
            throw new BadInputException("username","field is empty");
        String passWord=(String) password.getInfo();
    if(passWord.trim().equals(""))
        throw new BadInputException("password","field is empty");
        String phone=(String) this.phone.getInfo();
    if(phone.trim().equals(""))
        throw new BadInputException("phone","field is empty");
        String eMail=(String) this.eMail.getInfo();
    if(eMail.trim().equals(""))
        throw new BadInputException("e-mail","field is empty");
        return new Client(-1,userName,passWord,phone,eMail);
}
public void addCreateListener(ActionListener l){ createAccount.addActionListener(l); }
}
