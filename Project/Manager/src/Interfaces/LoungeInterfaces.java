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
      * Choose the next task
      * In theoretical terms, it will confirm if there is a new task to be done.
      * In practical terms, the manager waits if the vectors of each service are empty.
      * As soon as they are not, they perform a task.
      * @param managerState manager's state
      * @return true, when the vectores are not empty
     * @throws java.rmi.RemoteException
      */
    public boolean getNextTask(String managerState) throws RemoteException;
    
    /**
     * In theoretical terms, you will choose one of the services.
     * In practical terms, it will choose one of the vectors,
     * prioritizing to look for new parts, followed by alerting
     * the customers and finally serving the customers.
     * 0 - ATENDING_CUSTOMER
     * 1 - ALERTING_CUSTOMER
     * 2 - GETTING_NEW_PARTS
     * @return 0,1 or 2, which means the service number
     * @throws java.rmi.RemoteException
     */
    public String appraiseSit() throws RemoteException;

    
    /**
     * Synchronization point.
     * In theoretical terms, it will give the key to the replacement car if the customer wants to.
     * In practical terms, synchronization will only be done using the key variable.
     * @param info customer's info
     * @throws java.rmi.RemoteException
     */
    public void handCarKey(String info) throws RemoteException;
    
    /**
     * In theoretical terms, spend some time talking to the customer.
     * In practical terms, it is a synchronization point,
     * where the manager updates the "talkBetweenManCust" variable to true.
     * If the variable is already true, you expect the client to respond
     * before talking to another client.
     * Remove this customer from the service queue.
     * @param customer customer's info
     * @param managerState manager's state
     * @throws java.rmi.RemoteException
     */
    public void talkToCustomer(String customer, String managerState) throws RemoteException;


    /**
     * In theoretical terms, the manager receives payment from the customer for the service rendered.
     * In practical terms, it is a synchronization point where the manager updates the variable "pay" to false,
     * meaning the collection of the payment.
     * @param info customer's info
     * @throws java.rmi.RemoteException
     */
    public void receivePayment(String info) throws RemoteException;

    
    /**
     * Receive shutdown from an entity.
     * If all entities sent a shutdown, send a shutdown to all other shared regions.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
