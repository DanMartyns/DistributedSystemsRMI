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
public interface ManagerOutsideWorld extends Remote  {

    /**
     * Call the Customer (Outside World)
     * @param info the information about customer
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException  
     */
    public void phoneCustomer(String info, String managerState) throws RemoteException;        
}
