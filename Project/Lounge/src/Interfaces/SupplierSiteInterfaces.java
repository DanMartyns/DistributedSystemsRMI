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
public interface SupplierSiteInterfaces extends Remote {

    /**
     * The choice of quantity to be replenished in the stock of the repair area is random. 
     * Since this quantity is added to a sales record, where each index of the array 
     * corresponds to the id of the part, and its value is the quantity sold so far.
     * @param peca type of the piece
     * @param managerState the state of the manager
     * @return random number of parts of a part type.
     * @throws java.rmi.RemoteException
     */
    public int goToSupplier(String peca, String managerState) throws RemoteException;

    /**
     * Terminate the supplier site service.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
        
}