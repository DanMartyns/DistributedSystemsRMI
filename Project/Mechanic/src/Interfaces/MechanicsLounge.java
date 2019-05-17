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
 * @author giselapinto
 * @author danielmartins
 */
public interface MechanicsLounge extends Remote{
    
    /**
    * Let manager know that the mechanics needs more pieces from supplier site
    * @param currentPiece piece's type
    * @param mechanic mechanic's id
    * @param mechanicState mechanic's state
    */
    void letManagerKnow(String currentPiece, int mechanic, String mechanicState)  throws RemoteException;
    
    /**
    * Notify the repair is concluded
    * @param currentCar customer's car id
    * @param mechanic mechanic's id
    * @param mechanicState mechanic's state
    */
    void repairConcluded(int currentCar, int mechanic, String mechanicState)  throws RemoteException;
    
    /**
     * Check if a part order has already been ordered
     * @param peca piece's type
     * @return true or false
     */
    boolean checkRequest(String peca)  throws RemoteException;
}
