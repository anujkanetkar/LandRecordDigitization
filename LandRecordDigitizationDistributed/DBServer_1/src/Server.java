/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author akash
 */
public class Server extends UnicastRemoteObject implements ServerInterface
{
    Server() throws RemoteException
    {
        super();
    }

    public static Connection conn;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try 
        {
            // TODO code application logic here
            ServerInterface stub = new Server();
            LocateRegistry.createRegistry(5001);
            
            Naming.rebind("rmi://0.0.0.0:5001/land1", stub);
            System.out.println("Waiting for Client");
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public boolean checkLogin(String username, String password) throws SQLException, RemoteException 
    {
        String sql = "SELECT * FROM Login where Username = ? AND Password = ?";        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        
        boolean check = (ps.executeQuery()).next();
        return check;        
    }

    @Override
    public void connectToDB() throws RemoteException 
    {
        final String jdbcdriver = "com.mysql.jdbc.Driver";
        final  String url = "jdbc:mysql://localhost:3306/akashdb";
        
        try
        {
            //configure the server ie PASSSWORD
            Class.forName(jdbcdriver);
            conn = DriverManager.getConnection(url,"akash","akash@13");
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
    
    public void addLand(Map<String,String> hm) throws RemoteException
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
        
    public String checkSellerValidity(Vector<String> sellerPANs,int landid) throws RemoteException
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
    
    public int register(String oldregid , String price,int landid) throws RemoteException
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
   
    public void addOwners(Vector<String> buyerpans, Vector<String> buyershares, String newregid,int landid) throws SQLException,RemoteException
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
    
    public void addPerson(HashMap m ,String aadhar) throws RemoteException
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
       
    @Override
    public String validateSeller(String landid, String pan) throws RemoteException
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

    @Override
    public boolean checkBuyer(String pan) throws RemoteException
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
    
    @Override
    public String getPersonData(String pan) throws RemoteException
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

    @Override
 public Vector<Vector<String>> SearchRecords(String LandID,String State,String City,String Type,String Areamin,String Areamax,String Pricemin,String Pricemax) throws RemoteException
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
