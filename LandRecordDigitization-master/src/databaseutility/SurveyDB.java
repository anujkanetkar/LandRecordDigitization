/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import static databaseutility.Server.conn;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author anuj
 */
public class SurveyDB
{
    public static void addLand(Map<String,String> hm)
    {
        String lid = hm.get("LandID");
        String ltype = hm.get("LandType");
        String ar = hm.get("Area");
        String st = hm.get("State");
        String ct = hm.get("City");
        String addr = hm.get("Address");
        String cost = hm.get("Price");
                
        //String sql = String.format("INSERT INTO LandDetailS VALUES(%s,%s,%s,%s,%s,%s)",lid,ar,ltype,addr,ct,st);
        
        String sqlld = "insert into LandDetails values(?,?,?,?,?,?)";
        String sqlreg = "insert into Registration values(?,?,?,?,?)";
        String sqllo = "insert into LandOwners values(?,?,?,?,?)";
        
        try
        {
            PreparedStatement ps = conn.prepareStatement(sqlld);
            ps.setInt(1, Integer.parseInt(lid));
            ps.setDouble(2, Double.parseDouble(ar));
            ps.setString(3, ltype);
            ps.setString(4, addr);
            ps.setString(5, ct);
            ps.setString(6, st);
        
            int resld = ps.executeUpdate();
            
            ps = conn.prepareStatement(sqlreg);
            ps.setString(1, null);
            ps.setInt(2, Integer.parseInt(lid));
            
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);

            ps.setDate(3, date);
            ps.setNull(4, java.sql.Types.INTEGER);
            ps.setDouble(5, Double.parseDouble(cost));
            
            int resreg = ps.executeUpdate();
            
            ps = conn.prepareStatement(sqllo);
            ps.setLong(1, 0);
            ps.setInt(2, Integer.parseInt(lid));
            ps.setFloat(3, 100);
            ps.setString(4, "Current");
            
            String helperquery = "select max(RegistrationID) as maxregid from Registration";
            Statement stmt = conn.createStatement();
           
            ResultSet rs = stmt.executeQuery(helperquery);
   
            int newregid = -1;
            if(rs.next())
            {
                newregid = rs.getInt("maxregid");
            }
            
            ps.setInt(5, newregid);
            
            int reslo = ps.executeUpdate();
            
            
            if(resld != 0 & resreg != 0 & reslo != 0)
            {
                JOptionPane.showMessageDialog(null, "Successfully entered information","Information",JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid input" , "Error" , JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(SQLException sqe)
        {
            JOptionPane.showMessageDialog(null, "Invalid input" , "Error" , JOptionPane.ERROR_MESSAGE);
        }
    }
}
