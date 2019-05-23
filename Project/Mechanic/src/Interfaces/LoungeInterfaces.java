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
public interface LoungeInterfaces extends Remote {

    /**
     * Let manager know that the mechanics needs more pieces from supplier site
     * @param peca piece's type
     * @param mechanic mechanic's id
     * @param mechanicState mechanic's state
     * @throws java.rmi.RemoteException
     */
    public void letManagerKnow(String peca, int mechanic, String mechanicState) throws RemoteException;
    
    /**
     * Notify the repair is concluded
     * @param currentCar the atual car of the customer
     * @param mechanic mechanic's id
     * @param mechanicState mechanic's state
     * @throws java.rmi.RemoteException
     */
    public void repairConcluded(int currentCar, int mechanic, String mechanicState) throws RemoteException;
    /**
     * check if a type # part has already been ordered.
     * @param peca piece's type
     * @return if the piece has already been ordered or not
     * @throws java.rmi.RemoteException
     */
    public boolean checkRequest(String peca) throws RemoteException;
    /**
     * Receive shutdown from an entity.
     * If all entities sent a shutdown, send a shutdown to all other shared regions.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
