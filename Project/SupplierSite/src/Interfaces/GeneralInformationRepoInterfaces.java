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

}
