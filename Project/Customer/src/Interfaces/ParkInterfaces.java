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
     * The customer park the car in the park.
     * In this state you can get in,
     * customers who will repair a car,
     * customers who will get the car after repair and who has a replacement car.
     * @param info customer's info
     * @param customerState customer's state
     * @throws java.rmi.RemoteException
     */
    public void goToRepairShop(String info, String customerState) throws RemoteException;
        
    
    /**
     * Method used by the customer to search for a replacement car
     * Each customer will poll the list of replacement cars, and
     * if there are no cars, they wait.
     * @param id customer's id
     * @param customerState customer's state
     * @return replacement car
     * @throws java.rmi.RemoteException
     */
    public int findCar(int id, String customerState) throws RemoteException;

    /**
     * Method to signal when a car is repaired, signaling the position
     * with the index equal to the car id, with a value of 1.
     * @param info customer's info
     * @param customerState customer's state
     * @throws java.rmi.RemoteException
     */
    public void collectCar( String info , String customerState ) throws RemoteException;
    
}
