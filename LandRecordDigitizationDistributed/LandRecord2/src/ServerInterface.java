/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author akash
 */
public interface ServerInterface extends Remote
{
    public boolean checkLogin(String username ,String password) throws SQLException,RemoteException;
    public void connectToDB() throws RemoteException;   
    public void addLand(Map<String,String> hm) throws RemoteException;
    public void addPerson(HashMap m ,String aadhar) throws RemoteException;
    public void addOwners(Vector<String> buyerpans, Vector<String> buyershares, String newregid,int landid) throws SQLException,RemoteException;
    public int register(String oldregid , String price,int landid) throws RemoteException;
    public String checkSellerValidity(Vector<String> sellerPANs,int landid) throws RemoteException;
    public String validateSeller(String landid, String pan) throws RemoteException;
    public boolean checkBuyer(String pan) throws RemoteException;
    public String getPersonData(String pan) throws RemoteException;
    public Vector<Vector<String>> SearchRecords(String LandID,String State,String City,String Type,String Areamin,String Areamax,String Pricemin,String Pricemax) throws RemoteException;
}
