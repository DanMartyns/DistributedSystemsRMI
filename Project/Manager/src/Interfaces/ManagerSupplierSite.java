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
public interface ManagerSupplierSite extends Remote {
    
    /**
     * Go to Supplier (SupplierSite)
     * @param peca, string argumento, type of the piece
     * @param managerState the state of the manager
     * @return the quantity of this kind of piece
     * @throws java.rmi.RemoteException
     */
    public int goToSupplier(String peca, String managerState) throws RemoteException;
    
    
}
