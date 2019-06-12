/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import static databaseutility.Server.conn;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author akash
 */
public class Transaction 
{
    //private static Connection conn;
    //private static Vector<Vector> v;
    private int landid;
    private double price;
    
    public Transaction(String lid)
    {
        landid = Integer.parseInt(lid);
    }
    
    
    public String checkSellerValidity(Vector<String> sellerPANs)
    {
        String sql = "select AadharNo, RegistrationID from LandOwners where LandID = ? AND OwnerStatus = 'Current'";
        
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, landid);
            
            ResultSet rs = ps.executeQuery();
            
            String oldregid = new String();
            while(rs.next())
            {
                String pan = rs.getString("AadharNo");
                oldregid = null;
                for(String s : sellerPANs)
                {
                    if(pan.equals(s))
                    {
                        oldregid = rs.getString("RegistrationID");
                        break;
                    }
                }
                if(oldregid == null)
                {
                    return oldregid;
                }
                
            }
            
            return oldregid;
        }
        
        catch(SQLException sqe)
        {
            return null;
        }
        
    }
    
    public int register(String oldregid , String price)
    {
        String sql = "insert into Registration values(?,?,?,?,?)";
        
        try 
        {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, null);
           
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);

            ps.setInt(2, landid);
            ps.setDate(3, date);
            ps.setInt(4, Integer.parseInt(oldregid));
            ps.setDouble(5, Double.parseDouble(price));

            ps.executeUpdate();
            
            String helperquery = "select max(RegistrationID) as maxregid from Registration";
            Statement stmt = conn.createStatement();
           
            ResultSet rs = stmt.executeQuery(helperquery);
   
            int newregid = -1;
            if(rs.next())
            {
                newregid= rs.getInt("maxregid");
            }
            return newregid;
        }
        
        catch (SQLException ex) 
        {
            //TODO need to resolve this 
            return -1;
        }
    }
   
    public void addOwners(Vector<String> buyerpans, Vector<String> buyershares, String newregid) throws SQLException
   {
        try
        {
            //update current owners to previous
            String sql = "update LandOwners set OwnerStatus = 'Previous' where LandID = ? AND OwnerStatus = 'Current'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, landid);
            ps.executeUpdate();
            
            //add new owners
            sql = "insert into LandOwners values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            int i = 0;
            while(i < buyerpans.size())
            {
                ps.setLong(1, Long.parseLong(buyerpans.elementAt(i)));
                ps.setInt(2, landid);
                ps.setFloat(3, Float.parseFloat(buyershares.elementAt(i)));
                ps.setString(4, "Current");
                ps.setInt(5, Integer.parseInt(newregid));
                ps.executeUpdate();
                i++;
            }
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.toString() ,"Error",JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Failed to add Owners");
        }
    }  
    
    public static void addPerson(HashMap m ,String aadhar)
    {
        try 
        {
            PreparedStatement ps = conn.prepareStatement("insert into Person values(?,?,?,?,?,?)");
            ps.setLong(1, Long.parseLong((String) m.get("Aadhar")));
            ps.setString(2, (String)m.get("Name"));
            ps.setString(3, (String)m.get("Bank"));
            ps.setLong(4, Long.parseLong((String)m.get("Account")));
            ps.setString(5,(String)m.get("Email"));
            ps.setLong(6, Long.parseLong((String)m.get("Phone")));
            int result = ps.executeUpdate();
            if(result ==  1)
            {
                JOptionPane.showMessageDialog(null, "Data entered successfully" , "Message",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) 
        {
        }
    }
    
}
