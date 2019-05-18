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
     * Decide whether to get the car or not.
     * @param customer the id of the customer
     * @param customerState the customer state
     * @return true or false, if he wants or not
     * @throws java.rmi.RemoteException
     */
    public boolean decideOnRepair(int customer, String customerState)  throws RemoteException;

    /**
     * Synchronization point.
     * Get back to your normal life without a car. 
     * It waits to be notified that your car is repaired.
     * @param customer the id of the customer
     * @param customerState the customer state
     * @throws java.rmi.RemoteException
     */
    public void backToWorkByBus(int customer, String customerState)  throws RemoteException;
    /**
     * Synchronization point.
     * Get back to your normal life with your car after it's fixed 
     * or with a replacement car. If he is with a replacement car, 
     * he waits to be notified that his car is repaired.
     * @param info the information of the customer
     * @param customerState the customer state
     * @throws java.rmi.RemoteException
     */
    public void backToWorkByCar(String info, String customerState)  throws RemoteException;
    
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
