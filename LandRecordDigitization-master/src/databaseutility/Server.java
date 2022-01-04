/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import java.io.FileNotFoundException;
import java.sql.*;
import javax.swing.*;
import java.io.*;
import static java.sql.JDBCType.NULL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akash
 */
public class Server
{
    static public Connection conn = null;
    
    public static void connectToDB()
    {
        final String jdbcdriver = "com.mysql.jdbc.Driver";
        final  String url = "jdbc:mysql://localhost:3306/LandRecords";
        
        try
        {
            //configure the server ie PASSSWORD
            Class.forName(jdbcdriver);
            conn = DriverManager.getConnection(url,"root","root123");
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    public static boolean checkLogin(String username ,String password) throws SQLException
    {
        String sql = "SELECT * FROM Login where Username = ? AND Password = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        
        boolean check = (ps.executeQuery()).next();
        return check;
    }
    
    public static String validateSeller(String landid, String pan)
    {
       // System.out.println(pan);
        String str = new String();
        try
        {
            String sql = "select * from Person where EXISTS(select * from LandOwners where LandID = ? AND OwnerStatus = 'Current' AND AadharNo = ?) AND AadharNo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(landid));    
            ps.setLong(2, Long.parseLong(pan));
            ps.setLong(3, Long.parseLong(pan));
            
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                str = rs.getString("AadharNo")+","+rs.getString("Name")+","+rs.getString("Phone")+","+rs.getString("Email");   
            }
            else
            {
                str = "ERROR";
            }
        }
        catch(Exception e)
        {

        }
        
        return str;
    }  
    
    public static boolean checkBuyer(String pan)
    {
        String sql = "select * from Person where AadharNo=?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(pan.equals(""))
            {
                throw new Exception();
            }
            ps.setLong(1,Long.parseLong(pan));
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Thrown");
            return false;
        }    
    }
    
    public static String getPersonData(String pan)
    {
        String str = new String();
        try
        {
            String sql = "select AadharNo,Name,Email,Phone from Person where AadharNo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, Long.parseLong(pan));
            ResultSet rs=ps.executeQuery();

            if(rs.next())
            {
                str = rs.getString("AadharNo")+","+rs.getString("Name")+","+rs.getString("Phone")+","+rs.getString("Email");   
            }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e.toString() ,"Error" ,JOptionPane.ERROR_MESSAGE);
        }
        
        return str;
    }

}
