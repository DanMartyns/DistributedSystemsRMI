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
public interface RepairAreaInterfaces extends Remote {

      /**
     * The manager records the repair of a car.
     * @param info the information about customer
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException
     */
    public void registerService(String info, String managerState) throws RemoteException;
    
    /**
     * Function called by the manager to replace parts, according to a part and quantity.
     * @param peca piece's type
     * @param quantidade total number of pieces
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException
     */
    public void storePart(String peca, int quantidade, String managerState) throws RemoteException;
    
    
    /**
     * Fuction to shutdown the repair area
     * @param managerState the manager state
     * @throws java.rmi.RemoteException
     */
    public void shutdownNow(String managerState) throws RemoteException;

    /**
     * Terminate the outsideWorld service.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
