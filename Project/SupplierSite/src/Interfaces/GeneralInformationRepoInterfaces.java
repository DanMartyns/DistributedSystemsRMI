/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Logger stub. Class used to communicate with the logger
 * using TCP communication channels.
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

}
