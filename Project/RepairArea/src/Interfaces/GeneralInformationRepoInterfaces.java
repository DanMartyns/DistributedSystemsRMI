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
     * get the respective mechanics state for all mechaincs alive
     * @param id of mechanic
     * @param mechanicState state
     * @throws java.rmi.RemoteException
     */
    public void setMechanicState(int id, String mechanicState) throws RemoteException;
    
   
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

}
