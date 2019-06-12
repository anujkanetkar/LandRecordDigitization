/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import static databaseutility.Server.conn;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author akash
 */
public class GuestDB 
{
 public static Vector<Vector<String>> SearchRecords(String LandID,String State,String City,String Type,String Areamin,String Areamax,String Pricemin,String Pricemax)
    {
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = new String();
        
        Vector<Vector<String>> ans = null;
        
        try
        {
            if(!(LandID.equals("")))
            {
                sql = "select reg.LandID,ld.State,ld.City,ld.LandType,ld.Area,reg.Price from Registration reg INNER JOIN LandOwners lo ON reg.RegistrationID = lo.RegistrationID INNER JOIN LandDetails ld ON ld.landID = reg.LandID where lo.OwnerStatus = 'Current'" +" AND ld.LandID = ?" + " GROUP BY reg.RegistrationID;";

                ps = conn.prepareStatement(sql);
                
                ps.setInt(1, Integer.parseInt(LandID));
            }
            
            else
            {
                if(Areamin.equals(""))
                    Areamin = "0.0";
                
                if(Pricemin.equals(""))
                    Pricemin = "0.0";
                
                if(Areamax.equals(""))
                {
                    Statement stmt = conn.createStatement();
                    String query = "SELECT MAX(Area) as maxarea from LandDetails;";
                    rs = stmt.executeQuery(query);
                    
                    if(rs.next())
                    {
                        Areamax = rs.getString("maxarea");
                    }
                }
                
                if(Pricemax.equals(""))
                {
                    Statement stmt = conn.createStatement();
                    String query = "SELECT MAX(Price) as maxprice from Registration;";
                    rs = stmt.executeQuery(query);
                    
                    if(rs.next())
                    {
                        Pricemax = rs.getString("maxprice");
                    }
                }
                
                sql = "select reg.LandID,ld.State,ld.City,ld.LandType,ld.Area,reg.Price from Registration reg INNER JOIN LandOwners lo ON reg.RegistrationID = lo.RegistrationID INNER JOIN LandDetails ld ON ld.landID = reg.LandID where lo.OwnerStatus = 'Current'" + " AND (ld.State = ? OR ? = '')" + " AND (ld.City = ? OR ? = '')" + " AND (ld.LandType = ? OR ? = '')" +" AND (ld.Area BETWEEN ? AND ?)" + " AND (reg.Price BETWEEN ? AND ?)"+ "GROUP BY reg.RegistrationID;";
            
                ps = conn.prepareStatement(sql);
          
                ps.setString(1,State);
                ps.setString(2,State);
                ps.setString(3,City);
                ps.setString(4,City);
                ps.setString(5,Type);
                ps.setString(6,Type);
                ps.setDouble(7,Double.parseDouble(Areamin));
                ps.setDouble(8,Double.parseDouble(Areamax));
                ps.setDouble(9,Double.parseDouble(Pricemin));
                ps.setDouble(10,Double.parseDouble(Pricemax));
                
                /*if(!(Areamin.equals("")))
                {
                    ps.setDouble(7,Double.parseDouble(Areamin));
                    ps.setDouble(8,Double.parseDouble(Areamax));
                    ps.setDouble(9,Double.parseDouble(Areamin));
                }
                
                else
                {
                    ps.setString(7,"");
                    ps.setString(8,"");
                    ps.setString(9,"");
                }
                
                if(!(Pricemin.equals("")))
                {
                    ps.setDouble(10,Double.parseDouble(Pricemin));
                    ps.setDouble(11,Double.parseDouble(Pricemax));
                    ps.setDouble(12,Double.parseDouble(Pricemin));
                }
                
                else
                {
                    ps.setString(10,"");
                    ps.setString(11,"");
                    ps.setString(12,"");
                }*/
                
                
            }
            
            rs = ps.executeQuery();
            ans = new Vector<Vector<String>>();
	    	
            while(rs.next())
            {
		Vector<String> record = new Vector<String>();
		    
		record.add(rs.getString("LandID"));
		record.add(rs.getString("State"));
		record.add(rs.getString("City"));
		record.add(rs.getString("LandType"));
                record.add(rs.getString("Area"));
		record.add(rs.getString("Price"));
		     
		ans.add(record);
            }

            return (ans);
            
        }
        
        catch(Exception q)
        {
            JOptionPane.showMessageDialog(null,"INVALID INPUT", "Error", JOptionPane.ERROR_MESSAGE);
            
            return (new Vector<Vector<String>>());
            
            //sqe.printStackTrace();
        }
                
        
    }    
}
