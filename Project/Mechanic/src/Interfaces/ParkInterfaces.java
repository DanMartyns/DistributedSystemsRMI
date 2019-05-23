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
public interface ParkInterfaces extends Remote {    
    /**
     * To signal the return of the vehicle to the parking lot, the index of the position
     * of the array corresponds to the id of the car, and the value of that position is set to 1.
     * @param car customer's car id
     * @throws java.rmi.RemoteException
     */
    public void returnVehicle(int car) throws RemoteException;
    /**
     * To signal the output of the parking vehicle, that vehicle is removed from the array of cars
     * @param car customer's car id
     * @throws java.rmi.RemoteException
     */
    public void getVehicle(int car) throws RemoteException;

    /**
     * To signal the block of the parking vehicle, that vehicle is added to array of cars
     * @param car customer's car id
     * @throws java.rmi.RemoteException
     */
    public void blockVehicle(int car) throws RemoteException;

    /**
     * Terminate the park service.
     * @throws java.rmi.RemoteException
     */
    public void serviceEnd() throws RemoteException;
}
