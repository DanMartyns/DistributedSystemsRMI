/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author danielmartins
 * @author giselapinto
 */
public interface ManagerLounge extends Remote {
    /**
     * Get a new Task for the Manager (Lounge)
     * @param managerState the state of the manager
     * @return boolean
     * @throws java.rmi.RemoteException  
     */
    public boolean getNextTask(String managerState) throws RemoteException;
    
    /**
     * Choose what is the new task (Lounge)
     * @return int
     * @throws java.rmi.RemoteException  
     */
    public String appraiseSit() throws RemoteException;
    
    /**
     * Talk to Customer (Lounge)
     * @param info the information about customer
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException
     */
    public void talkToCustomer(String info, String managerState) throws RemoteException;
    
    /**
     * Receive Payment (Lounge)
     * @param info the information about customer
     * @throws java.rmi.RemoteException  
     */
    public void receivePayment(String info) throws RemoteException;
    
    /**
     * Hand Car Key (Lounge)
     * @param info the information about customer 
     * @throws java.rmi.RemoteException 
     */
    public void handCarKey(String info) throws RemoteException;
    
}
