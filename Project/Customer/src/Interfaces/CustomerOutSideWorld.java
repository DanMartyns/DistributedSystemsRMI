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
public interface CustomerOutSideWorld extends Remote {
    
    /**
     * customer decide repair car
     * (Class OutsideWorld)
     * @param customer customer's id
     * @param customerState customer's state
     * @return true if customer decide Repair the car, false if they still on outside world activity
     */
    boolean decideOnRepair(int customer, String customerState) throws RemoteException;
    
    /**
     * if customer decide on repair car and choose leave with replecement car, 
     * he goes back to outside world activitys by replecement car
     * @param customer customer's id 
     * @param customerState customer's state 
     * (Class OutsideWorld)
     */
    void backToWorkByCar(String customer, String customerState) throws RemoteException;
    
    /**
     * if customer decide on repair car and choose not leaving with replecement car, 
     * he goes back to outside world activitys by replecement bus
     * (Class OutsideWorld)
     * @param customer customer's id 
     * @param customerState customer's state
     */
    void backToWorkByBus(int customer, String customerState) throws RemoteException;
  
}
