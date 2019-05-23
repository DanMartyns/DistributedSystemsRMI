/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Interfaces.GeneralInformationRepoInterfaces;
import Interfaces.IOutside;
import static MainPackage.Constants.NUM_CUSTOMERS;
import genclass.GenericIO;
import java.rmi.RemoteException;

/**
 * @author danielmartins
 * @author giselapinto
 */
public class OutsideWorld implements IOutside {
    /**
    * Logger class for debugging.
    */
    private GeneralInformationRepoInterfaces logger;
    
    /**
    * Boolean variable "phoneCustomer" to wake the Customer from his normal life and go get the car.
    */
    private boolean phoneCustomer = false;
    
    /**
     * variable that corresponds to the current customer
     */
    private String currentCustomer = "";
    
    /**
     * Boolean array, index = carID, and the value = true or false. It means if the car of carID was repaired or not.
     */
    private boolean[] carRepaired = new boolean[NUM_CUSTOMERS];

    public OutsideWorld(GeneralInformationRepoInterfaces logger) {
        this.logger = logger;
    }

   

    /**
     * Decide whether to get the car or not.
     * @param customer the id of the customer
     * @param customerState the customer state
     * @return true or false, if he wants or not
     */
    
    @Override
    public synchronized boolean decideOnRepair(int customer, String customerState) throws RemoteException {
        logger.setCustomerState(customer, customerState);
        GenericIO.writelnString(" Customer "+customer+" decideOnRepair ");   
        return Math.random() > 0.5;

    }

    /**
     * Synchronization point.
     * Get back to your normal life without a car. 
     * It waits to be notified that your car is repaired.
     * @param customer the id of the customer
     * @param customerState the customer state
     */
    
    @Override
    public synchronized void backToWorkByBus(int customer, String customerState) throws RemoteException{
        logger.setCustomerState(customer, customerState);    
        notifyAll();     
        while(carRepaired [customer] == false){
            try {              
                wait();
            } catch (InterruptedException ex) {
                GenericIO.writelnString("talkWithManager - One Customer thread was interrupted.");
                System.exit(1);                                        
            }
        }

        carRepaired [customer] = false;
        notifyAll();
        GenericIO.writelnString(" Customer "+customer+" back to work by bus ");   
    }
    /**
     * Synchronization point.
     * Get back to your normal life with your car after it's fixed 
     * or with a replacement car. If he is with a replacement car, 
     * he waits to be notified that his car is repaired.
     * @param info the information of the customer
     * @param customerState the customer state
     */
    @Override
    public synchronized void backToWorkByCar(String info, String customerState) throws RemoteException{
        
        notifyAll();
        String[] inf = info.split(",");
        int customer = Integer.parseInt(inf[0]);
        logger.setReplecementCar(Integer.parseInt(inf[0]), info);
        logger.setCustomerState(customer, customerState);
        logger.setOwnCar(Integer.parseInt(inf[0]), info);
        while(carRepaired [customer] == false){
            try {
                wait();
            } catch (InterruptedException ex) {
                GenericIO.writelnString("talkWithManager - One Customer thread was interrupted.");
                System.exit(1);                                        
            }
        }
        carRepaired [customer] = false;
        GenericIO.writelnString(" Customer "+customer+" back to work by car ");         
    }
    /**
     * Synchronization point.
     * Notifies customers that your car is repaired.
     * @param info the information of the customer
     * @param managerState the manager state
     */
    @Override
    public synchronized void phoneCustomer(String info, String managerState) throws RemoteException {
        logger.setManagerState(managerState);
        carRepaired [Integer.parseInt(info.split(",")[0])] = true;  
        notifyAll();
        GenericIO.writelnString(" Manager will call the customer "+info.split(",")[0]);      
    }
    
    /**
     * Terminate the outsideWorld service.
     */
    @Override
    public synchronized void serviceEnd() throws RemoteException{
        MainProgram.serviceEnd = true;
        notifyAll();
        GenericIO.writelnString(" Outside world will end ");
    }             
}
