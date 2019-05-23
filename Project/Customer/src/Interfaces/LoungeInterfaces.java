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
     * The costumer go into the Lounge and waits for his turn
     * @param info customer's info
     * @param customerState customer's state
     * @throws java.rmi.RemoteException
     */
    public void queueIn(String info, String customerState) throws RemoteException;
    
    
    /**
     * Synchronization point.
     * In theoretical terms, you will receive the key to the replacement car.
     * In practical terms, synchronization will only be done using the key variable.
     * @param customer customer's id
     * @param customerState customer's state
     * @throws java.rmi.RemoteException
     */
    public void collectKey(int customer, String customerState) throws RemoteException;

   
    /**
     * In theoretical terms, the client spends some time talking to the manager.
     * In practical terms, it is a synchronization point where the client updates the "talkBetweenManCust"
     * variable to false if that client is the client that the manager initiated a conversation.
     * If the variable is already false, it means that the manager has not started a conversation
     * yet and therefore expects the manager to respond.
     * @param customer customer's id
     * @throws java.rmi.RemoteException
     */
    public void talkWithManager(int customer) throws RemoteException;

   
    /**
     * In theoretical terms will effect the payment.
     * In practical terms, it is just a state of transition.
     * @param customer customer's id
     * @throws java.rmi.RemoteException
     */
    public void payForTheService(int customer) throws RemoteException;

    /**
     * Receive shutdown from an entity.
     * If all entities sent a shutdown, send a shutdown to all other shared regions.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;

}
