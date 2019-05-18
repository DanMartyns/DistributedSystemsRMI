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
public interface GeneralInformationRepoInterfaces extends Remote{
    
    public void setAlreadyRepaired(int car, int[] repairedCars) throws RemoteException;

    public void setOwnCar(int parseInt, String info) throws RemoteException;

    /**
     * Update the state of the customer
     * @param customer the id of the customer
     * @param customerState the state of the customer
     * @throws java.rmi.RemoteException
     */  
    public void setCustomerState(int customer, String customerState) throws RemoteException;
    
    /**
     * Updating number of customer waiting for a replacement vehicle
     * @param wait number of replacement car
     * @throws java.rmi.RemoteException
     */
    public void setNumberWaitingReplece(int wait) throws RemoteException;
    
    
    /**
     * Updating number of cars that have already been repaired
     * @param numberRepair number of cars repaired
     * @throws java.rmi.RemoteException
     */
    public void setNumberRepair(int numberRepair) throws RemoteException;
    
    
    /**
     * update number of customer vehicles presently parked at the repair shop park
     * @param numberParkCars number of cars in the park
     * @throws java.rmi.RemoteException
     */
    public void setNumberParkCars(int numberParkCars) throws RemoteException;
    
    /**
     * Update number of replacement vehicles presently parked at the repair shop park
     * @param numberReplacementPark number of replacement cars in the park
     * @throws java.rmi.RemoteException
     */
    public void setNumberReplacementPark(int numberReplacementPark) throws RemoteException;
}
