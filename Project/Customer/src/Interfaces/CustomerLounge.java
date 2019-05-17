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
public interface CustomerLounge extends Remote {
    /**
     * customer is waiting for manager to talk
     * (Class Lounge)
     * @param id from customer
     * @param customerState customer's state
     */
    void queueIn(String id, String customerState) throws RemoteException;
    
    /**
     * customer talks with manager
     * @param customer customer's id
     */
    void talkWithManager(int customer) throws RemoteException;
    
    /**
     * customer wants a replecement car so he need the key
     * (Class Lounge)
     * @param customer customer's id
     * @param customerState customr's state
     */
    void collectKey(int customer, String customerState) throws RemoteException; 
    
    /**
     * customer pay for the service to Manager
     * (Class Lounge)
     * @param customer's id
     */
    void payForTheService(int customer) throws RemoteException;
    
    
}
