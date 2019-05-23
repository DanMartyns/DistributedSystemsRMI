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
     * get the respective mechanics state for all mechaincs alive
     * @param id of mechanic
     * @param mechanicState state
     * @throws java.rmi.RemoteException
     */
    public void setMechanicState(int id, String mechanicState) throws RemoteException;
    
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
     * number of customers presently queueing to talk to the manager
     * @param valueQueueIn value of customer on queue
     * @throws java.rmi.RemoteException
     */
    public void setValueQueueIn(int valueQueueIn) throws RemoteException;
  
    /**
     * flag signaling the manager has been adviced that part # is missing at the repair area: T or F
     * @param flagPieces A-piece type
     * @throws java.rmi.RemoteException
     */
    public void setFlagAPieces(String flagPieces) throws RemoteException;
    
    /**
     * flag signaling the manager has been adviced that part # is missing at the repair area: T or F
     * @param flagPieces B-piece type 
     * @throws java.rmi.RemoteException 
     */
    public void setFlagBPieces(String flagPieces) throws RemoteException;
    
    /**
     * flag signaling the manager has been adviced that part # is missing at the repair area: T or F
     * @param flagPieces C-piece type
     * @throws java.rmi.RemoteException
     */
    public void setFlagCPieces(String flagPieces) throws RemoteException;

    /**
     * Terminate the logger service.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
