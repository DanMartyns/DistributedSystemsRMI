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
public interface GeneralInformationRepoInterfaces extends Remote  {
    
    
    /**
     * Update the state of the manager
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException
     */
    public void setManagerState(String managerState) throws RemoteException;

    /**
     * Update the state of the customer
     * @param customer the id of the customer
     * @param customerState the state of the customer
     * @throws java.rmi.RemoteException
     */  
    public void setCustomerState(int customer, String customerState)  throws RemoteException;

    /**
     * Update the size of the queue
     * @param size the size of the queue in repair Area
     * @throws java.rmi.RemoteException
     */
    public void setValueQueueIn(int size)  throws RemoteException;
    /**
     * Update the state of the mechanic
     * @param mechanic the id of the mechanic
     * @param mechanicState the state of the mechanic
     * @throws java.rmi.RemoteException
     */
    public void setMechanicState(int mechanic, String mechanicState)  throws RemoteException;

    /**
     * Warning of no C-type parts
     * @param peca piece's type
     * @throws java.rmi.RemoteException
     */    
    public void setFlagAPieces(String peca) throws RemoteException;

    /**
     * Warning of no C-type parts
     * @param peca piece's type
     * @throws java.rmi.RemoteException
     */
    public void setFlagBPieces(String peca) throws RemoteException;

    /**
     * Warning of no C-type parts
     * @param peca piece's type
     * @throws java.rmi.RemoteException
     */
    public void setFlagCPieces(String peca)  throws RemoteException;

    /**
     * Terminate the service.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;

    /**
     * Update the current car of the customer
     * @param parseInt customer's id
     * @param info customer's info
     * @throws java.rmi.RemoteException
     */
    public void setOwnCar(int parseInt, String info)  throws RemoteException;
}

