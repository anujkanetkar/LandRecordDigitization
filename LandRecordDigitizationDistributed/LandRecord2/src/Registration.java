/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akash
 */
public class Registration extends JFrame
{
    JFrame f;
    String user;
    
    //top panel variables
    JPanel toppanel;
    JLabel logout;
    JLabel username;
    
    //middle panel variables
    JPanel middlepanel;
    
    JLabel landidlabel;
    JLabel pricelabel;
    JLabel buyerpanlabel;
    JLabel sellerpanlabel;
    JLabel sharelabel;
    JLabel buyerlabel;
    JLabel sellerlabel;
    
    JTextField landid;
    JTextField price;
    JTextField buyerpan;
    JTextField sellerpan;
    JTextField share;
    
    JButton addbuyer;
    JButton addseller;
    JButton submit;
    
    JTable buyertable;
    JTable sellertable;
    
    //bottom panel variables
    JPanel bottompanel;
    
    String bpan;
    
    String arr[];
    
    public Registration()
    {
        instantiate();
    }
    
    public Registration(JFrame f , String str)
    {
        this.f = f;
        user = str;
        instantiate();
        username.setText("Logged in as "+user);
    }
    
    void instantiate()
    {
        arr = new String[2];
        arr[0] = "rmi://localhost:5000/land1";
        arr[1] = "rmi://localhost:5001/land2";
        
        //top panel variables
        toppanel = new JPanel();
        toppanel.setBackground(new Color(52, 69, 150));
        toppanel.setPreferredSize(new Dimension(0, 100));
        
        logout = new JLabel(" Log Out ");
        username = new JLabel();
        logout.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        logout.setForeground(Color.WHITE);
        username.setForeground(Color.WHITE);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logout.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        toppanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 40));
        toppanel.add(username);
        toppanel.add(logout);
        
        //middle panel variables
        middlepanel = new JPanel();
   
        landidlabel = new JLabel("Land ID");
        landidlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        landidlabel.setPreferredSize(new Dimension(80, 40));
        
        pricelabel = new JLabel("Price");
        pricelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        pricelabel.setPreferredSize(new Dimension(80, 40));
        
        buyerpanlabel = new JLabel("Aadhar");
        buyerpanlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        buyerpanlabel.setPreferredSize(new Dimension(80, 40));
        
        sellerpanlabel = new JLabel("Aadhar");
        sellerpanlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        sellerpanlabel.setPreferredSize(new Dimension(80, 40));
        
        sharelabel = new JLabel("Share");
        sharelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        sharelabel.setPreferredSize(new Dimension(80, 40));
        
        buyerlabel = new JLabel("Buyer Details");
        buyerlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        buyerlabel.setPreferredSize(new Dimension(150, 40));
        
        sellerlabel = new JLabel("Seller Details");
        sellerlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        sellerlabel.setPreferredSize(new Dimension(150, 40));
        
        landid= new JTextField();
        landid.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        landid.setPreferredSize(new Dimension(200, 30));
        
        price = new JTextField();
        price.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        price.setPreferredSize(new Dimension(200, 30));
        
        buyerpan = new JTextField();
        buyerpan.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        buyerpan.setPreferredSize(new Dimension(200, 30));
        
        sellerpan = new JTextField();
        sellerpan.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        sellerpan.setPreferredSize(new Dimension(200, 30));
        
        share = new JTextField();
        share.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        share.setPreferredSize(new Dimension(200, 30));
        
        addbuyer = new JButton("Add");
        addbuyer.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        addbuyer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        addseller = new JButton("Add");
        addseller.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        addseller.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        submit = new JButton("Submit");
        submit.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        submit.setPreferredSize(new Dimension(150, 40));
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        sellertable = new JTable(new DefaultTableModel(new Object[][]{} , new String[]{"Aadhar","Name","Phone No.","Email ID"}));
        sellertable.setPreferredSize(new Dimension(400, 200));
        sellertable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        sellertable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        
        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(sellertable);
        sp1.setPreferredSize(new Dimension(400, 100));
        
        buyertable = new JTable(new DefaultTableModel(new Object[][]{} , new String[]{"Aadhar","Name","Share","Phone No.","Email ID"}));
        buyertable.setPreferredSize(new Dimension(400, 200));
        buyertable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        buyertable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        
        JScrollPane sp2 = new JScrollPane();
        sp2.setViewportView(buyertable);
        sp2.setPreferredSize(new Dimension(400, 100));
        
        //design middlepanel
        GridBagConstraints g;
   
        middlepanel.setLayout(new GridBagLayout());
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.weighty = 0.5;
        g.weightx = 0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(landidlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 0;
        //g.insets = new Insets(5, 5, 5, 15);
        g.weighty = 0.5;
        g.weightx = 0.5;
        middlepanel.add(landid ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        g.weighty = 0.5;
        g.weightx = 0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(pricelabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        //g.insets = new Insets(5, 15, 5, 5); 
        g.weighty = 0.5;
        g.weightx = 0.5;
        middlepanel.add(price ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 2;
        //g.insets = new Insets(30, 5, 5, 5);
        g.weighty = 1;
        g.weightx = 1;
        g.anchor = GridBagConstraints.SOUTH;
        middlepanel.add(sellerlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 1;
        g.gridwidth = 2;
        //g.insets = new Insets(30, 5, 5, 5);
        g.weighty = 1;
        g.weightx = 1;
        g.anchor = GridBagConstraints.SOUTH;
        middlepanel.add(buyerlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 2;
        g.gridheight = 2;
        g.weighty = 0.5;
        g.weightx =0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(sellerpanlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 2;
        g.gridheight = 2;
        g.weighty = 0.5;
        g.weightx =0.5;
        middlepanel.add(sellerpan ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 2;
        g.weighty = 0.5;
        g.weightx = 0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(buyerpanlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 2;
        g.weighty = 0.5;
        g.weightx = 0.5;
        middlepanel.add(buyerpan ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 3;
        g.weighty = 0.5;
        g.weightx = 0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(sharelabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 3;
        g.weighty = 0.5;
        g.weightx = 0.5;
        middlepanel.add(share ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 2;
        g.weighty = 0.2;
        g.weightx = 1;
        middlepanel.add(addseller ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 4;
        g.gridwidth = 2;
        g.weighty = 0.2;
        g.weightx = 1;
        middlepanel.add(addbuyer ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 2;
        g.weighty = 5;
        g.fill = GridBagConstraints.BOTH;
        g.weightx = 1;
        g.insets = new Insets(15, 15, 15, 15);
        middlepanel.add(sp1 ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 5;
        g.gridwidth = 2;
        g.weighty = 5;
        g.fill = GridBagConstraints.BOTH;
        g.weightx = 1;
        g.insets = new Insets(15, 15, 15, 15);
        middlepanel.add(sp2 ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 4;
        g.weighty = 0.2;
        g.weightx = 1;
        middlepanel.add(submit ,g);
        
        //bottom panel variables
        bottompanel = new JPanel();
        bottompanel.setBackground(new Color(52, 69, 150));
        bottompanel.setPreferredSize(new Dimension(0, 50));
        
        //set Frame layout and add components
        setLayout(new BorderLayout());
        add(toppanel, BorderLayout.PAGE_START);
        add(middlepanel, BorderLayout.CENTER);
        add(bottompanel, BorderLayout.PAGE_END);
    
        //mouse clicked listener for logout
        logout.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me)
            {
                logoutclicked();
            }
        });
        
        //actionlistener for addseller
        addseller.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent a) 
            {
                ServerInterface stub=null;
                String servername = getActiveServer();
                
                if(servername.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "No Active server .Retry Again", "Information",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {
                        stub =  (ServerInterface) Naming.lookup(servername);
                        stub.connectToDB();
                    } catch (NotBoundException ex) {
                        Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
                String lid;
                lid = landid.getText();

                String span;
                span = sellerpan.getText();

                String str = "ERROR";
                
                if(!(lid.equals("") | span.equals("")))
                {
                    try {
                        str = stub.validateSeller(lid, span);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(str.equals("ERROR"))
                {
                    JOptionPane.showMessageDialog(null, "Incorrect Input", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else
                {
                    String[] strlist = str.split(",");
                    
                    Vector <String> v = new Vector<String>();
                    int i=0;
                    while(i < strlist.length)
                    {
                        v.add(strlist[i]);
                        i++;
                    }

                    DefaultTableModel m = (DefaultTableModel) sellertable.getModel();
                    m.addRow(v);
                }
                
                sellerpan.setText("");
                
                //Server.closeConnection();
            }
        });
        
        //actionlistener for addbuyer
        addbuyer.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String pershare;
                bpan = buyerpan.getText();
                pershare = share.getText();
            
                String servername = getActiveServer();
                ServerInterface stub=null;
                if(servername.isEmpty())
                {   
                    
                    JOptionPane.showMessageDialog(null, "No Active server .Retry Again", "Information",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                        stub =  (ServerInterface) Naming.lookup(servername);
                        stub.connectToDB();
                    } catch (NotBoundException ex) {
                        Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                
                boolean valid=false;
                try {
                    valid = stub.checkBuyer(bpan);
                } catch (RemoteException ex) {
                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(valid && !pershare.equals(""))
                {
                    String str=null;
                    try {
                        str = stub.getPersonData(bpan);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String []strlist = str.split(",");
                    Vector <String> v = new Vector<String>();
                    int i=0;
                    while(i<strlist.length)
                    {
                        v.add(strlist[i]);
                        i++;
                    }
                    
                    v.insertElementAt(pershare, 2);
                    DefaultTableModel m = (DefaultTableModel) buyertable.getModel();
                    m.addRow(v);
                }
                
                else
                {
                    if(bpan.equals("") | pershare.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"Invalid Input" ,"Error" ,JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        PersonDialog();
                    }
                }
                
                buyerpan.setText("");
                share.setText("");
            }
        });
        
        //actionlistener for submit button
        submit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                //validate 100% buyer share
                int share = 0;
                int i=0;
                while(i < buyertable.getRowCount())
                {
                    share += Integer.parseInt((String) buyertable.getValueAt(i, 2));
                    ++i;  
                }
                
                if(share == 100)
                {
                    Vector<String> sellerPANs = new Vector<String>();
                    int k = 0;
                    
                    while(k < sellertable.getRowCount())
                    {
                        sellerPANs.add((String) sellertable.getValueAt(k, 0));
                        k++;
                    }

                    ServerInterface stub=null;
                    String servername = getActiveServer();
                    if(servername.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "No Active server .Retry Again", "Information",JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                    try {
                            stub =  (ServerInterface) Naming.lookup(servername);
                            stub.connectToDB();
                        } catch (NotBoundException ex) {
                            Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RemoteException ex) {
                           Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (MalformedURLException ex) {
                          Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    
                    String var_landid = landid.getText();
                            
                    //Transaction t = new Transaction(var_landid);
                    
                    String oldregid=null;
                    try {
                        oldregid = stub.checkSellerValidity(sellerPANs,Integer.parseInt(var_landid));
                    } catch (RemoteException ex) {
                        Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if(!(oldregid == null))
                    {
                        try 
                        {
                            //Server.conn.setAutoCommit(false);
                            String var_price = price.getText();
                            if(var_price.equals(""))
                            {
                                throw new SQLException("Price field is empty");
                            }
                            
                            int newregid=0;
                            
                            try {
                                newregid = stub.register(oldregid ,var_price,Integer.parseInt(var_landid));
                            } catch (RemoteException ex) {
                                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            if(newregid == -1)
                            {
                                throw new SQLException("Failed to allot new registration ID") ;
                            }
                            
                            Vector<String> buyerPANs = new Vector<String>();
                            Vector<String> buyershares = new Vector<String>();
                            k = 0;
                            
                            while(k < buyertable.getRowCount())
                            {
                                buyerPANs.add((String) buyertable.getValueAt(k, 0));
                                buyershares.add((String) buyertable.getValueAt(k, 2));
                                k++;
                            }
                            
                            try {
                                stub.addOwners(buyerPANs , buyershares, Integer.toString(newregid),Integer.parseInt(var_landid));
                            } catch (RemoteException ex) {
                                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //Server.conn.commit();
                            JOptionPane.showMessageDialog(null, "Your RegistrationID for this transaction is " + newregid, "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
                            
                            //clear fields 
                            
                            landid.setText("");
                            price.setText("");
                            
                            int p = sellertable.getRowCount();
                            while(p > 0)
                            {
                                ((DefaultTableModel)sellertable.getModel()).removeRow(p-1);
                                p--;
                            }
                            
                            p = buyertable.getRowCount();
                            while(p > 0)
                            {
                                ((DefaultTableModel)buyertable.getModel()).removeRow(p-1);
                                p--;
                            }
                                    
                        } 
                        catch (SQLException ae) 
                        {
                           /* try 
                            {
                                Server.conn.rollback();
                                
                                JOptionPane.showMessageDialog(null, ae.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                            } 
                            catch (SQLException ex) 
                            {
                    
                            }*/
                        }
                        //Server.closeConnection();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Seller(s) do not match", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Buyer Share Percentage is not 100%", "Error",JOptionPane.ERROR_MESSAGE);  
                }
                
            }
        });
        
    }
    
    String getActiveServer()
    {
         ServerInterface stub = null;
         Random rand = new Random();
         int num = rand.nextInt(2); 
         //System.out.println(num);
         JOptionPane.showMessageDialog(null, arr[num], "Information",JOptionPane.INFORMATION_MESSAGE);
         
        try
        {
            try
            {
                stub =  (ServerInterface) Naming.lookup(arr[num]);
                return arr[num];
            }
            catch (Exception ex) 
            {
                //Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Contacting other server", "Information",JOptionPane.INFORMATION_MESSAGE);
                num=(num+1)%2;
                System.out.println(arr[num]);
                stub =  (ServerInterface) Naming.lookup(arr[num]);  
                return arr[num];
            }
        } 
        catch(Exception e)
        {
            return "";
        }
         
    }

    void logoutclicked()
    {
        f.setVisible(true);
        dispose();
    }
    
    void PersonDialog()
    {
        JDialog d = new JDialog();
        
	JPanel p = new JPanel();
	p.setLayout(new GridLayout(0,2));
		
	JPanel p1 = new JPanel();
		
	//JLabel panlabel = new JLabel("PAN" ,JLabel.CENTER);
	JLabel aadharlabel = new JLabel("Aadhar No" ,JLabel.CENTER);
        JLabel namelabel = new JLabel("Name",JLabel.CENTER);
	JLabel emaillabel = new JLabel("Email ID",JLabel.CENTER);
	JLabel phonelabel = new JLabel("Phone No",JLabel.CENTER);
	JLabel banklabel = new JLabel("Bank Name",JLabel.CENTER);
	JLabel accountlabel = new JLabel("Account No",JLabel.CENTER);

	//JTextField pan = new JTextField();
	JTextField name = new JTextField();
	JTextField aadhar = new JTextField();
	JTextField phone = new JTextField();		
	JTextField email = new JTextField();
	JTextField bank = new JTextField();
	JTextField account = new JTextField();

        JButton submit  = new JButton("Submit");

	p.setLayout(new GridLayout(0,2,10,10));
	//p.add(panlabel);
	//p.add(pan);
        p.add(aadharlabel);
        p.add(aadhar);
	p.add(namelabel);
	p.add(name);
	p.add(phonelabel);
	p.add(phone);
	p.add(emaillabel);
	p.add(email);
	p.add(banklabel);
	p.add(bank);
	p.add(accountlabel);
	p.add(account);
	
	p1.add(submit);
		
	d.setLayout(new BorderLayout());
	d.add(p,BorderLayout.CENTER);
	d.add(p1,BorderLayout.PAGE_END);
        
	aadhar.setText(bpan);	
	submit.addActionListener(new ActionListener()
	{
            public void actionPerformed(ActionEvent a)
            {
               
                HashMap<String ,String> h = new HashMap<>();
                //h.put("PAN", pan.getText());
                h.put("Aadhar", aadhar.getText());
                h.put("Name", name.getText());
                h.put("Bank", bank.getText());
                h.put("Account", account.getText());
                h.put("Email" , email.getText());
                h.put("Phone", phone.getText());
		String var_aadhar = aadhar.getText();
                
                if(!h.containsValue(""))
                {
                    d.dispose();
            
            String servername = getActiveServer();
            ServerInterface stub=null;
            if(servername.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No Active server .Retry Again", "Information",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                stub =  (ServerInterface) Naming.lookup(servername);
                stub.connectToDB();
                stub.addPerson(h , var_aadhar);
        
                } catch (NotBoundException ex) {
                    Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(LandRecord2.class.getName()).log(Level.SEVERE, null, ex);
                }

                    //Server.closeConnection();
                    //JOptionPane.showMessageDialog(null, "Person data added successfully" , "Information" ,JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Input" , "Error" , JOptionPane.ERROR_MESSAGE);
                }
                
            }
	});

	d.setVisible(true);
        d.setSize(400, 300);
        d.setLocation(480, 250);
    }

}
