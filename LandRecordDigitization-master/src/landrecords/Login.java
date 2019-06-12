/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package landrecords;

import databaseutility.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akash
 */
public class Login extends JFrame
{
    //top panel variables
    JPanel toppanel;
    JLabel title;
            
    //middle panel variables
    JPanel middlepanel;
    JPanel choicepanel;
    JPanel choicelist;
    JPanel loginpanel;
    JPanel aboutuspanel;
    JPanel contactuspanel;
    JPanel imagepanel;
    
    JLabel homelabel;
    JLabel loginlabel;
    JLabel contactuslabel;
    JLabel aboutuslabel;
    
    JLabel usernamelabel;
    JLabel passwordlabel;
    JTextField username;
    JPasswordField password;
    JLabel or;
    JLabel guestlogin;
    JButton loginbutton; 
    
    JLabel insidecontactpanel;
    JLabel insideaboutuspanel;
    
    //bottom panel variables
    JPanel bottompanel;
    JLabel gotohome;
    JLabel settings;
    
    public Login()
    {
        instantiate();
    }
    
    void instantiate()
    {
        //top panel variables
        toppanel = new JPanel();
        toppanel.setPreferredSize(new Dimension(0, 100));
        toppanel.setBackground(new Color(52, 69, 150));
 
        ImageIcon i = new ImageIcon("homeicon.jpeg");
        title = new JLabel("   LAND RECORD PORTAL", i,JLabel.LEFT);
        title.setForeground(Color.WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19));
        
        toppanel.setLayout(new BorderLayout());
        toppanel.add(title,BorderLayout.CENTER);
        
        //middle panel variables
        middlepanel = new JPanel();
        choicepanel = new JPanel();
        loginpanel = new JPanel();
        contactuspanel = new JPanel();
        aboutuspanel = new JPanel();
        choicelist = new JPanel();
        imagepanel = new JPanel();
        
        homelabel = new JLabel("Home" , JLabel.CENTER);
        loginlabel = new JLabel("Login" , JLabel.CENTER);
        contactuslabel = new JLabel("Contact Us " , JLabel.CENTER);
        aboutuslabel = new JLabel("About Us" , JLabel.CENTER);
        
        usernamelabel = new JLabel("Username");
        passwordlabel = new JLabel("Password");
        username = new JTextField(null);
        password = new JPasswordField(null);
        loginbutton = new JButton("Login");
        or = new JLabel("OR");
        guestlogin = new JLabel("Login as Guest");
        
        insidecontactpanel = new JLabel();
        insideaboutuspanel = new JLabel();
        
        //design middlepanel
        middlepanel.setLayout(new CardLayout());
        
        //design choicelist
        choicelist.setBackground(new Color(43 , 122, 223));
        
        homelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        homelabel.setForeground(Color.WHITE);
        homelabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homelabel.setBorder(BorderFactory.createLineBorder(new Color(52,69,150), 1 , true));
        
        loginlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        loginlabel.setForeground(Color.WHITE);
        loginlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginlabel.setBorder(BorderFactory.createLineBorder(new Color(52,69,150), 1 , true));
        
        contactuslabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        contactuslabel.setForeground(Color.WHITE);
        contactuslabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contactuslabel.setBorder(BorderFactory.createLineBorder(new Color(52,69,150), 1 , true));
        
        aboutuslabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        aboutuslabel.setForeground(Color.WHITE);
        aboutuslabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aboutuslabel.setBorder(BorderFactory.createLineBorder(new Color(52,69,150), 1 , true));
        
        //add to choicepanel choicelistpanel
        choicelist.setLayout(new GridLayout(0, 1));
        choicelist.add(homelabel);
        choicelist.add(loginlabel);
        choicelist.add(contactuslabel);
        choicelist.add(aboutuslabel);
        
        choicelist.setPreferredSize(new Dimension(350, 0));
        
        choicepanel.setLayout(new BorderLayout());
        choicepanel.add(imagepanel, BorderLayout.CENTER);
        choicepanel.add(choicelist ,BorderLayout.LINE_START);
        
        //design loginpanel
        
        usernamelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        username.setPreferredSize(new Dimension(300, 40));
        
        passwordlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        password.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        password.setPreferredSize(new Dimension(300, 40));
        
        loginbutton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        loginbutton.setPreferredSize(new Dimension(300, 40));
        loginbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        or.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        guestlogin.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        guestlogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        GridBagConstraints g;
        
        loginpanel.setLayout(new GridBagLayout());
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.anchor = GridBagConstraints.WEST;
        g.insets = new Insets(5, 5, 5, 5);
        loginpanel.add(usernamelabel, g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 1;
        g.insets = new Insets(5, 5, 5, 5);
        loginpanel.add(username,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 2;
        g.anchor = GridBagConstraints.WEST;
        g.insets = new Insets(5, 5, 5, 5);
        loginpanel.add(passwordlabel,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 3;
        g.insets = new Insets(5, 5, 5, 5);
        loginpanel.add(password,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 4;
        g.insets = new Insets(5, 5, 5, 5);
        loginpanel.add(loginbutton,g);
                
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 5;
        g.anchor = GridBagConstraints.CENTER;
        g.insets = new Insets(5, 5, 5, 5);
        loginpanel.add(or,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 6;
        g.anchor = GridBagConstraints.CENTER;
        g.insets = new Insets(5, 5, 5, 5);
        loginpanel.add(guestlogin,g);
        
        //design aboutuspanel
        insideaboutuspanel.setText("3241.Akash Kale akashale1303@gmail.com\n3242.Anuj Kanetkar anujkanetkar@gmail.com\n3256 Shriniwas Nayak shriniwasnayak1@gmail.com");
        
        
        aboutuspanel.setLayout(new BorderLayout());
        aboutuspanel.add(insideaboutuspanel , BorderLayout.CENTER);
        
        //design contactuspanel
        insidecontactpanel.setText("For any queries contact us on: \n landrecorddigitization@gmail.com");
        
        contactuspanel.setLayout(new BorderLayout());
        contactuspanel.add(insidecontactpanel , BorderLayout.CENTER);
        
        //adding cards to middlepanel
        middlepanel.add(choicepanel,"card1");
        middlepanel.add(loginpanel,"card2");
        middlepanel.add(aboutuspanel,"card3");
        middlepanel.add(contactuspanel,"card4");
        
        //bottom panel variables
        bottompanel = new JPanel();
        gotohome = new JLabel();
        //settings  = new JLabel(" Settings ");
        
        bottompanel.setPreferredSize(new Dimension(0, 50));
        bottompanel.setBackground(new Color(52,  69, 150));
        bottompanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 10));
        
        gotohome.setText("Go To Home");
        gotohome.setForeground(new Color(255, 255, 255));
        gotohome.setFont(new Font(Font.SANS_SERIF, Font.PLAIN , 18));
        gotohome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        /*settings.setForeground(Color.WHITE);
        settings.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));*/
        
        bottompanel.add(gotohome);
        //bottompanel.add(settings);
        
        //set JFrame Layout and add components
        setLayout(new BorderLayout());
        
        add(toppanel , BorderLayout.PAGE_START);
        add(middlepanel , BorderLayout.CENTER);
        add(bottompanel , BorderLayout.PAGE_END);
     //   pack();
     
        //add listeners for middle panel choice selector
        loginlabel.addMouseListener(new MouseAdapter() 
        { 
            public void mouseClicked(MouseEvent me)
            {
                CardLayout c = (CardLayout) middlepanel.getLayout();
                c.show(middlepanel, "card2");
            }
        });
        
        //add listener to Login page button
        loginbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try 
                { 
                    checkLogin();
                }
                catch (SQLException ex) 
                {
                    //Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        guestlogin.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                guestloginlistener();
            }
        });
        
        gotohome.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me)
            {
                CardLayout c = (CardLayout) middlepanel.getLayout();
                c.show(middlepanel, "card1");
            }
        });
        
        //settings clicked mouse listener
        /*settings.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me)
            {
            }
        });*/
        
        aboutuslabel.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me)
            {
                CardLayout c = (CardLayout) middlepanel.getLayout();
                c.show(middlepanel, "card3");
            }
        });
        
        contactuslabel.addMouseListener(new MouseAdapter() 
        {
              public void mouseClicked(MouseEvent me)
              {
                  CardLayout c = (CardLayout) middlepanel.getLayout();
                  c.show(middlepanel, "card4");
              }
        });
    }
    
    void guestloginlistener()
    {
            Guest g= new Guest(this);
            g.setSize(700, 700);
            g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            g.setExtendedState(MAXIMIZED_BOTH);
            g.setVisible(true);
            setVisible(false);
    }
    
    void checkLogin() throws SQLException
    {
        
        String var_username = new String();
        String var_password = new String(password.getPassword());
        var_username = username.getText();
        
        Server.connectToDB();
        boolean valid = Server.checkLogin(var_username, var_password);
        
        if(valid)
        {
            String dept = var_username.substring(0, 3);
            switch(dept)
            {
                case "REG":
                    Registration r = new Registration(this , var_username);
                    r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    r.setSize(700, 700);
                    r.setExtendedState(MAXIMIZED_BOTH);
                    r.setVisible(true);
                    setVisible(false);
                    break;
                    
                case "SUR":
                    Survey s = new Survey(this , var_username);
                    s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    s.setSize(700, 700);
                    s.setExtendedState(MAXIMIZED_BOTH);
                    s.setVisible(true);
                    setVisible(false);
                    break;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error",JOptionPane.ERROR_MESSAGE);
        }
        username.setText("");
        password.setText("");
               
        Server.closeConnection();
    }	
    
    public static void main(String args[])
    {
        Login l = new Login();
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setSize(1000, 1000);
        l.setExtendedState(MAXIMIZED_BOTH);
        l.setVisible(true);
    }
}
