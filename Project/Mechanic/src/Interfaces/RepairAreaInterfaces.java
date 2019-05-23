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
     * The mechanic remains in the "read paper" state, while the lists is empty. If not, continue
     * @param mechanic mechanic id
     * @param mechanicState the state of the mecaninc
     * @return the service who'll do
     * @throws java.rmi.RemoteException
     */
    public String readThePaper(int mechanic, String mechanicState) throws RemoteException;

    
    /**
     * In terms of simulation, indicates the service to be done
     * Transition state
     * @param mechanic mechanic id
     * @param mechanicState the state of the mecaninc
     * @throws java.rmi.RemoteException
     */
    public void startRepairProcedure(int mechanic, String mechanicState) throws RemoteException;

    /**
    * Theoretically the mechanic will find out which part is missing from the car
    * A random value is generated to indicate the part missing from the car
    * @param mechanic mechanic id
    * @param mechanicState the state of the mecaninc
    * @return a random value generated to indicate the part missing from the car
     * @throws java.rmi.RemoteException
    **/
    public String getRequiredPart(int mechanic, String mechanicState) throws RemoteException;

    /**
    * Checking the stock
    * If there are no parts, the car is locked and is passed to a next service
    * @param piece type of piece
    * @param car car id
    * @return true or false, if mechanics has parts with him or not
     * @throws java.rmi.RemoteException
    **/
    public boolean partAvailable(String piece, int car) throws RemoteException;
   
    /**
    * Decreases the number of pieces after verifying that they have
    * @param piece pieces in use
    * @param mechanic mechanic id
    * @param mechanicState current state of mechanic
    * @throws java.rmi.RemoteException
    **/
    public void resumeRepairProcedure(String piece, int mechanic, String mechanicState) throws RemoteException;
    
    
    /**
    * Process the fix the car from customer
    * Transitional State
    * @throws java.rmi.RemoteException
    **/
    public void fixIt() throws RemoteException;
 
    
    /**
     * Terminate the outsideWorld service.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
