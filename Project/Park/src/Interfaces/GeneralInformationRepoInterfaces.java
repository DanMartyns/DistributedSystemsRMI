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
    * get the respective customer state for all customers alive
    * @param id from customer
    * @param customerState state of customers
    * @throws java.rmi.RemoteException
    */
    public void setCustomerState(int id, String customerState) throws RemoteException;

   
    /**
     * vehicle driven by customer: 
     * own car  customer id; 
     * replacement car - R0, R1, R2 ; 
     * none - '-'
     * @param id customer Id
     * @param ownCar it own car or replecment car
     * @throws java.rmi.RemoteException
     */
    public void setOwnCar(int id, String ownCar) throws RemoteException;

     
    /**
     * customer vehicle has already been repaired
     * @param id customer id
     * @param repairedCar if car is repaired or not
     * @throws java.rmi.RemoteException
     */
    public void setAlreadyRepaired(int id, int repairedCar[]) throws RemoteException;
      
    /**
     *number of customer waiting for a replacement vehicle
     * @param numberWaitingReplece number of replacement car
     * @throws java.rmi.RemoteException
     */
    public void setNumberWaitingReplece(int numberWaitingReplece) throws RemoteException;
    
    /**
     * number of cars that have already been repaired
     * @param numberRepair number of cars repaired
     * @throws java.rmi.RemoteException
     */
    public void setNumberRepair(int numberRepair) throws RemoteException;

    /**
     * number of customer vehicles presently parked at the repair shop park
     * @param numberParkCars number of cars in the park
     * @throws java.rmi.RemoteException
     */
    public void setNumberParkCars(int numberParkCars) throws RemoteException;

    /**
     * number of replacement vehicles presently parked at the repair shop park
     * @param numberReplacementPark number of replacement cars in the park
     * @throws java.rmi.RemoteException
     */
    public void setNumberReplacementPark(int numberReplacementPark) throws RemoteException;
   
}
