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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author akash
 */
public class Survey extends JFrame
{
    Login l;
    String str;
    
    //top panel variables
    JPanel toppanel;
    JLabel logout;
    JLabel username;
    
    //middle panel variables
    JPanel middlepanel;
    JPanel container;
    JLabel landidlabel;
    JLabel statelabel;
    JLabel citylabel;
    JLabel addresslabel;
    JLabel typelabel;
    JLabel arealabel;
    JLabel pricelabel;
    
    
    JTextField landid;
    JComboBox<String> state;
    JComboBox<String> city;
    JTextField address;
    JComboBox<String> type;
    JTextField area;
    JTextField price;
    JButton landsubmit;
    
    //bottom panel variables
    JPanel bottompanel;
        
    public Survey()
    {
        instantiate();
    }
    
    public Survey(Login l , String u)
    {
        this.l = l;
        str= u;
        instantiate();
        
        city.setEnabled(false);
        //add states
        try
        {
            FileReader fr = new FileReader("Statelist.txt");
            BufferedReader br = new BufferedReader(fr);

            String statestr;
            statestr = br.readLine();

            do
            {
                String [] statelist;
                statelist = statestr.split(",");
                state.addItem(statelist[0]+"."+statelist[1]);
                statestr = br.readLine();
            }while(statestr!=null);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    void instantiate()
    {
        
        //top panel variables
        toppanel = new JPanel();
        username = new JLabel();
        username.setText("Logged in as "+str);
        logout = new JLabel(" Log Out ");
                
        toppanel.setBackground(new Color(52, 69, 150));
        toppanel.setPreferredSize(new Dimension(0, 100));
        
        //design top panel
        toppanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 40));
        
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        username.setForeground(Color.WHITE);
        
        logout.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        logout.setForeground(Color.WHITE);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logout.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        //add components to top panel
        toppanel.add(username);
        toppanel.add(logout);
        
        
        //middle panel variables
        middlepanel = new JPanel();
        container = new JPanel();
        landidlabel = new JLabel("Land ID");
        statelabel = new JLabel("State");
        citylabel = new JLabel("City");
        addresslabel = new JLabel("Address");
        typelabel = new JLabel("Type");
        arealabel = new JLabel("Area");
        pricelabel = new JLabel("Price");
        
        landid = new JTextField();
        String[] model ={""};
        state = new JComboBox<String>(model);
        city = new JComboBox<String>(model);
        address = new JTextField();
        area = new JTextField();
        price = new JTextField();
        landsubmit = new JButton("Submit");
        
        String []typelist = {"","Forest" , "Argicultural" , "Industrial" , "Residential"};
        type = new JComboBox<>(typelist);
        
        //design middle panel
        
        landidlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,16));
        landid.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,15));
        landid.setPreferredSize(new Dimension(300, 30));
        
        statelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,16));
        state.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        state.setPreferredSize(new Dimension(300, 30));
        
        citylabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,16));
        city.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        city.setPreferredSize(new Dimension(300, 30));
        
        addresslabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,16));
        address.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        address.setPreferredSize(new Dimension(300, 30));
        
        typelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,16));
        type.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        type.setPreferredSize(new Dimension(300, 30));
        
        arealabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,16));
        area.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        area.setPreferredSize(new Dimension(300, 30));
        
        pricelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,16));
        price.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,15));
        price.setPreferredSize(new Dimension(300, 30));
        
        
        landsubmit.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,16));      
        landsubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        container.setLayout(new GridBagLayout());
        GridBagConstraints g;
        
        g=new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(landidlabel ,g);
        
        g=new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 0;        
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(landid,g);
        
        g=new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 1;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(statelabel,g);
       
        g=new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 1;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(state,g);
        
        g=new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 2;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(citylabel,g);
        
        g=new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 2;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(city,g);
        
        g=new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 3;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(addresslabel,g);
        
        g=new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 3;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(address,g);
        
        g=new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 4;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(typelabel,g);
        
        g=new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 4;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(type,g);
        
        g=new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 5;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(arealabel,g);
        
        g=new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 5;        
        g.insets = new Insets(15, 15, 15, 15);
        container.add(area,g);

        g=new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 6;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(pricelabel,g);
        
        g=new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 6;        
        g.insets = new Insets(15, 15, 15, 15);
        container.add(price,g);
        
        g=new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 2;
        g.insets = new Insets(15, 15, 15, 15);
        container.add(landsubmit,g);
        
        middlepanel.setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        middlepanel.add(container,g);
        
        //bottom panel variables
        bottompanel = new JPanel();
        bottompanel.setBackground(new Color(52, 69, 150));
        bottompanel.setPreferredSize(new Dimension(0, 50));
        
        //set Frame layout and add components
        setLayout(new BorderLayout());
        add(toppanel, BorderLayout.PAGE_START);
        add(middlepanel, BorderLayout.CENTER);
        add(bottompanel, BorderLayout.PAGE_END);
    
        //add action listener for submit land button
        landsubmit.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent a)
           {
                Server.connectToDB();
                Map<String,String> hm = new HashMap<String,String>();

                hm.put("LandID", landid.getText());
                hm.put("LandType", type.getSelectedItem().toString());
                hm.put("Area", area.getText());
                hm.put("Address", address.getText());
                hm.put("Price", price.getText());
                
                if(state.getSelectedItem()!="")
                {
                    hm.put("State", ((String) state.getSelectedItem()).substring(3));
                }
                hm.put("City", (String) city.getSelectedItem());
                try
                {
                    if(hm.containsValue(""))
                    {
                        throw new Exception("Empty values not allowed");
                    }
                    Server.connectToDB();
                    SurveyDB.addLand(hm);
                    Server.closeConnection();
                
                    landid.setText("");
                    state.setSelectedIndex(0);
                    city.setSelectedIndex(0);
                    city.setEnabled(false);
                    address.setText("");
                    type.setSelectedIndex(0);
                    area.setText("");
                    price.setText("");
                }
                catch(Exception e)
                {
                    try 
                    {
                        Server.conn.rollback();
                    } 
                    catch (SQLException ex) 
                    {
                    }
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //mouseclicked listener for logout
        logout.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me)
            {
                dispose();
                l.setVisible(true);
            }
        });
        
        //add action listener for state selected
        state.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent a) 
            {
                String statename = (String) state.getSelectedItem();
                
                if(statename!="")
                {
                city.setEnabled(true);
                city.removeAllItems();
                city.addItem("");
                String code = statename.substring(0,2);
                    try
                    {
                        FileReader fr1 = new FileReader("Citylist.txt");
                        BufferedReader br1 = new BufferedReader(fr1);
                        String str1;
                        str1 = br1.readLine();
                        while(str1!=null)
                        {
                            String []cityarr;
                            cityarr = str1.split(",");
                            if(cityarr[1].equals(code))
                            {
                                city.addItem(cityarr[0]);
                            }
                            str1 = br1.readLine();
                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    city.removeAllItems();
                    city.addItem("");
                    city.setEnabled(false);
                }
            }
        });
    }
}
