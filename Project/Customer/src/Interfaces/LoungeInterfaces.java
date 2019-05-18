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
     * In theoretical terms, the manager receives payment from the customer for the service rendered.
     * In practical terms, it is a synchronization point where the manager updates the variable "pay" to false,
     * meaning the collection of the payment.
     * @param info customer's info
     * @throws java.rmi.RemoteException
     */
    public void receivePayment(String info) throws RemoteException;

    /**
     * In theoretical terms will effect the payment.
     * In practical terms, it is just a state of transition.
     * @param customer customer's id
     * @throws java.rmi.RemoteException
     */
    public void payForTheService(int customer) throws RemoteException;


    /**
     * Let manager know that the mechanics needs more pieces from supplier site
     * @param peca piece's type
     * @param mechanic mechanic's id
     * @param mechanicState mechanic's state
     * @throws java.rmi.RemoteException
     */
    public void letManagerKnow(String peca, int mechanic, String mechanicState) throws RemoteException;
    /**
     * Notify the repair is concluded
     * @param currentCar the atual car of the customer
     * @param mechanic mechanic's id
     * @param mechanicState mechanic's state
     * @throws java.rmi.RemoteException
     */
    public void repairConcluded(int currentCar, int mechanic, String mechanicState) throws RemoteException;
    /**
     * check if a type # part has already been ordered.
     * @param peca piece's type
     * @return if the piece has already been ordered or not
     * @throws java.rmi.RemoteException
     */
    public boolean checkRequest(String peca) throws RemoteException;
    /**
     * Receive shutdown from an entity.
     * If all entities sent a shutdown, send a shutdown to all other shared regions.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
