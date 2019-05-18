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
public interface GeneralInformationRepoInterfaces extends Remote {
    
    /**
     * Update the state of the manager
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException
     */
    public void setManagerState(String managerState) throws RemoteException;

    /**
     * Update the state of the mechanic
     * @param mechanic the id of the mechanic
     * @param mechanicState the state of the mechanic
     * @throws java.rmi.RemoteException
     */
    public void setMechanicState(int mechanic, String mechanicState) throws RemoteException;
    
    /**
     * Update the number of service requested
     * @param size number of service
     * @throws java.rmi.RemoteException
     **/
    public void setNumberServiceRequest(int size) throws RemoteException;
    
    /**
     * Update of Avaible pieces
     * @param piece number of pieces
     * @param numberOfCustomersWaiting number of customer waiting for a piece
     * @throws java.rmi.RemoteException
     **/
    public void setPiecesAvabal(String piece, int numberOfCustomersWaiting) throws RemoteException;
    
    /**
     * Update of Stored Pieces 0
     * @param pieceA number of pieces stored
     * @throws java.rmi.RemoteException
     **/
    public void setPieces0Stored(int pieceA) throws RemoteException;
    
   /**
    * Update of Stored Pieces 1
    * @param pieceB number of pieces stored
     * @throws java.rmi.RemoteException
    **/
    public void setPieces1Stored(int pieceB) throws RemoteException;
    
    /**
     * Update of Stored Pieces 2
     * @param pieceC number of pieces stored
     * @throws java.rmi.RemoteException
     **/
    public void setPieces2Stored(int pieceC) throws RemoteException;
    
    
    /**
     * Update of Pieces 0 that manager stored
     * @param pieceA number of pieces stored
     * @throws java.rmi.RemoteException
     **/
    public void setPieces0Manager(int pieceA) throws RemoteException;
    
    /**
     * Update of Pieces 1 that manager stored
     * @param pieceB number of pieces stored
     * @throws java.rmi.RemoteException
     **/
    public void setPieces1Manager(int pieceB) throws RemoteException;
    
    /**
     * Update of Pieces 2 that manager stored
     * @param pieceC number of pieces stored
     * @throws java.rmi.RemoteException
     **/
    public void setPieces2Manager(int pieceC) throws RemoteException;
}
