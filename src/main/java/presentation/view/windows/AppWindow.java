package presentation.view.windows;

import presentation.view.utility.GradientPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class AppWindow extends JFrame {
    public static final Color MAIN_COLOR=new Color(34,140,128);
    public static final Color SECONDARY_COLOR=new Color(253,204,14);
    public static final Color PANEL_COLOR=new Color(222,128,2);
    public static final Color TEXT_COLOR=new Color(253, 246, 228);
    public static final Color HIGHLIGHT_COLOR=new Color(255,225,158);
   // public static final Color DARK_COLOR=new Color(71,68,75);
    public static final Color DARK_COLOR=new Color(0, 11, 20);
   /* public static final Color MAIN_COLOR=new Color(143,26,110);
    public static final Color SECONDARY_COLOR=new Color(41,11,232);
    public static final Color TEXT_COLOR=new Color(237,237,237);
    public static final Color DARK_COLOR=new Color(20,0,16);*/

    public static final Font FIELD_FONT=new Font("Tahoma",Font.BOLD,14);
    public static final Font BUTTON_FONT=new Font("Tahoma",Font.PLAIN,14);
    public static final Font ITEM_WINDOW_FONT=new Font("Bahnschrift",Font.PLAIN,16);
    public static final Font HEADER_FONT=new Font("Courier New",Font.PLAIN,18);
    //public static final Font ITEM_WINDOW_FONT=new Font("Tahoma",Font.PLAIN,14);
    private final JPanel header;
    protected final JPanel headerRight,headerLeft,headerCenter;
    protected final JScrollPane content;
    protected JPanel center;

    public AppWindow(){
        super("Food delivery");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        header=new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(5,5,5,5));
        headerRight=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        headerRight.setBackground(AppWindow.DARK_COLOR);
        headerCenter=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        headerCenter.setBackground(AppWindow.DARK_COLOR);
        headerLeft=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        headerLeft.setBackground(AppWindow.DARK_COLOR);

        center=new GradientPanel(AppWindow.MAIN_COLOR,AppWindow.SECONDARY_COLOR);
        content=new JScrollPane(center);
        content.setBorder(new EmptyBorder(0,0,0,0));
        center.setBorder(new EmptyBorder(60,25,20,25));

        header.setBackground(AppWindow.DARK_COLOR);
        header.add(headerRight,BorderLayout.EAST);
        header.add(headerCenter,BorderLayout.CENTER);
        header.add(headerLeft,BorderLayout.WEST);
        add(header,BorderLayout.NORTH);
        add(content,BorderLayout.CENTER);
        setVisible(true);
    }
    public AppWindow(JFrame prev){
        this();
        setSize(prev.getSize());
        setLocationRelativeTo(prev);
    }

    public static void formatButton(JButton button,Color color,String imageName){
        button.setBorder(new EmptyBorder(0,0,0,0));
        button.setBackground(color);
        try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(imageName);
            BufferedImage image = ImageIO.read(input);
            button.setIcon(new ImageIcon(image));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
    public static void formatButton(JButton button){
        button.setBackground(AppWindow.DARK_COLOR);
        button.setForeground(AppWindow.TEXT_COLOR);
        button.setFont(AppWindow.BUTTON_FONT);
        button.setBorder(BorderFactory.createCompoundBorder(new LineBorder(AppWindow.TEXT_COLOR,1),new EmptyBorder(7,7,7,7)));
    }

}
