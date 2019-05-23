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
public interface GeneralInformationRepoInterfaces extends Remote{
   
    /**
     * Get the state from manager
     * @param manager_state the state of the manager
     * @throws java.rmi.RemoteException
     */
    public void setManagerState(String manager_state)  throws RemoteException;

    /**
     * get the respective customer state for all customers alive
     * @param id from customer
     * @param customerState state of customers
     * @throws java.rmi.RemoteException
     */
    public void setCustomerState(int id, String customerState) throws RemoteException;

     /**
     * vehicle driven by customer: 
     * own car  customer id; 
     * replacement car - R0, R1, R2 ; 
     * none - '-'
     * @param id customer Id
     * @param ownCar it own car or replecment car
     * @throws java.rmi.RemoteException
     */
    public void setOwnCar(int id, String ownCar) throws RemoteException;

    /**
     * customer requires replacement vehicle: T or F 
     * @param id customer id
     * @param replecementCar if requeires replecment car or not
     * @throws java.rmi.RemoteException
     */
    public void setReplecementCar(int id, String replecementCar) throws RemoteException;
    
    
}
