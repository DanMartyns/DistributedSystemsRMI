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
public interface CustomerPark extends Remote {
    
    /**
     * Customer collects the car that is already done
     * (Class Park)
     * @param info customer's info
     * @param customerState customer's state
     */
    void collectCar(String info, String customerState)  throws RemoteException;
    
    
    /**
     * customer go to repair shop
     * (Class Park)
     * @param info customer's info
     * @param customerState customer's state
    */
    void goToRepairShop(String info, String customerState)  throws RemoteException;
    
    /**
     * customer find the replecment car on park
     * (Class Park)
     * @param id customer's id
     * @param customerState customer's state
     * @return Each customer will poll the list of replacement cars, and 
     * if there are no cars, they wait.
    */
    int findCar(int id, String customerState)  throws RemoteException;
}
