/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author danielmartins
 * @author giselapinto
 */
public interface OutsideWorldInterfaces extends Remote{
     
    /**
     * Synchronization point.
     * Notifies customers that your car is repaired.
     * @param info the information of the customer
     * @param managerState the manager state
     * @throws java.rmi.RemoteException
     */
    public void phoneCustomer(String info, String managerState)  throws RemoteException;
    
    /**
     * Terminate the outsideWorld service.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
