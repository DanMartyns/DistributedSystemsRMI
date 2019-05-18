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
     * This function initiate de header log from the logger file, without deleting anything important
     * @throws java.rmi.RemoteException
     */
    public void initStateLog() throws RemoteException;
    
    /**
     * print all the relevante information from logger file, States, and all updateVariables
     * @throws java.rmi.RemoteException
     */
    public void printHeaderLog() throws RemoteException;
         
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
     * customer requires replacement vehicle: T or F 
     * @param id customer id
     * @param replecementCar if requeires replecment car or not
     * @throws java.rmi.RemoteException
     */
    public void setReplecementCar(int id, String replecementCar) throws RemoteException;
    
    /**
     * customer vehicle has already been repaired
     * @param id customer id
     * @param repairedCar if car is repaired or not
     * @throws java.rmi.RemoteException
     */
    public void setAlreadyRepaired(int id, int repairedCar[]) throws RemoteException;
    
    /**
     * number of customers presently queueing to talk to the manager
     * @param valueQueueIn value of customer on queue
     * @throws java.rmi.RemoteException
     */
    public void setValueQueueIn(int valueQueueIn) throws RemoteException;
       
    /**
     *number of customer waiting for a replacement vehicle
     * @param numberWaitingReplece number of replacement car
     * @throws java.rmi.RemoteException
     */
    public void setNumberWaitingReplece(int numberWaitingReplece) throws RemoteException;
    
    /**
     * number of cars that have already been repaired
     * @param numberRepair number of cars repaired
     * @throws java.rmi.RemoteException
     */
    public void setNumberRepair(int numberRepair) throws RemoteException;

    /**
     * number of customer vehicles presently parked at the repair shop park
     * @param numberParkCars number of cars in the park
     * @throws java.rmi.RemoteException
     */
    public void setNumberParkCars(int numberParkCars) throws RemoteException;

    /**
     * number of replacement vehicles presently parked at the repair shop park
     * @param numberReplacementPark number of replacement cars in the park
     * @throws java.rmi.RemoteException
     */
    public void setNumberReplacementPark(int numberReplacementPark) throws RemoteException;
   
    
    /**
     * number of service requests made by the manager to the repair area
     * @param numberServiceRequest number of registed services by manager
     * @throws java.rmi.RemoteException
     */
    public void setNumberServiceRequest(int numberServiceRequest)  throws RemoteException;

    /**
     * number of parts of type 0 presently in storage at the repair area
     * @param pieces0Stored number of A-pieces in the repair area
     * @throws java.rmi.RemoteException
     */
    public void setPieces0Stored(int pieces0Stored) throws RemoteException;

    /**
     * number of parts of type 1 presently in storage at the repair area
     * @param pieces1Stored number of B-pieces in the repair area
     * @throws java.rmi.RemoteException
     */
    public void setPieces1Stored(int pieces1Stored) throws RemoteException;

    /**
     * number of parts of type 2 presently in storage at the repair area
     * @param pieces2Stored number of C-pieces in the repair area
     * @throws java.rmi.RemoteException
     */
    public void setPieces2Stored(int pieces2Stored) throws RemoteException;

    /**
     * number of customer vehicles waiting for part # to be available so that the repair may resume 
     * @param piece piece's type
     * @param quantityOfCustomersWaiting quantity on waiting
     * @throws java.rmi.RemoteException
     */
    public void setPiecesAvabal(String piece, int quantityOfCustomersWaiting) throws RemoteException;
    
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
     * number of parts of type 0 which have been purchased so far by the manager
     * @param pieces0Manager A-pieces returned by the manager
     * @throws java.rmi.RemoteException
     */
    public void setPieces0Manager(int pieces0Manager) throws RemoteException;

    /**
     *number of parts of type 1 which have been purchased so far by the manager
     * @param pieces1Manager B-pieces returned by the manager
     * @throws java.rmi.RemoteException
     */
    public void setPieces1Manager(int pieces1Manager) throws RemoteException;

    /**
     * number of parts of type 2 which have been purchased so far by the manager
     * @param pieces2Manager C-pieces returned by the manager
     * @throws java.rmi.RemoteException
     */
    public void setPieces2Manager(int pieces2Manager) throws RemoteException;

    /**
     * Terminate the logger service.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
