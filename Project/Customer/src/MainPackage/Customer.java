/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import EntitiesState.CustomerState;
import Interfaces.LoungeInterfaces;
import Interfaces.OutsideWorldInterfaces;
import Interfaces.ParkInterfaces;

import static MainPackage.Constants.NUM_CUSTOMERS;
import genclass.GenericIO;
import java.rmi.RemoteException;


/**
 * @author giselapinto
 * @author danielmartins
 */
public class Customer extends Thread {

  
    /**
    * Identifier of the Customer.
    */       
    private int id;
    
    private String info;
    
    /**
    * interface Customer Park.
    */
    private ParkInterfaces park;
    
    /**
     * Instance of the interface from customer Lounge.
     */
    private LoungeInterfaces lounge;
    
    /**
    * Instance of the interface from customer Outside World.
    */ 
    private OutsideWorldInterfaces outsideWorld;
    
  
    /**
     * Variable to decide whether you want car or not
     */
    private boolean wantsReplacementCar = Math.random() > 0.5;
    
    
    /**
     * Mechanic constructor
     *
     * @param id identifier of the customer
     * @param outsideWorld instance of the outside world
     * @param carID customer's car id
     * @param park instance of the Park
     * @param lounge instance of the lounge
     */     
    public Customer(int id, OutsideWorldInterfaces outsideWorld,int carID, ParkInterfaces park, LoungeInterfaces lounge) {
        /**
         * The client id is a string consisting of
         * id = id.hisCar.currentCar.wantsCarOrNot.paidOrNot
         * Example : 
         * 1.1.1.0.0
         */
        /**
        * Current Car
        * If currentCar == carID, it means that the car in your possession is your car.
        * else if currentCar == 0, it means he has no car in his possession.
        * else if currentCar == -1 or -2 or -3, it means he has a replacement car in his possession.
        */
        
        this.id = id;
        this.info = id+","+carID+","+carID+","+(wantsReplacementCar ? 1 : 0)+","+"0";
        this.outsideWorld = outsideWorld;
        this.park = park;
        this.lounge = lounge;
        
    }
    /**
     * Implements the life cycle of the broker.
     */ 
    @Override
    public void run() {
        try{
            while ( outsideWorld.decideOnRepair(id,CustomerState.NORMAL_LIFE_WITH_CAR.toString()) == false){}                         

            park.goToRepairShop(info,CustomerState.PARK.toString());
            setCurrentCar(info,""+NUM_CUSTOMERS);

            lounge.queueIn(info, CustomerState.RECEPTION.toString()); //actualizar carro atual

            lounge.talkWithManager(this.id);

            if(wantsReplacementCar){

                lounge.collectKey(this.id,CustomerState.WAITING_FOR_REPLACE_CAR.toString());

                int replacementCar = park.findCar(this.id,CustomerState.PARK.toString());
                setCurrentCar(info,""+replacementCar);

                outsideWorld.backToWorkByCar(info, CustomerState.NORMAL_LIFE_WITH_CAR.toString());

                park.goToRepairShop(info,CustomerState.PARK.toString());
                setCurrentCar(info,""+NUM_CUSTOMERS);
            }
            else {
                outsideWorld.backToWorkByBus(this.id, CustomerState.NORMAL_LIFE_WITHOUT_CAR.toString());
            }
            setPay(info,"1");
            lounge.queueIn(info, CustomerState.RECEPTION.toString());

            lounge.payForTheService(this.id); 

            setCurrentCar(info,""+this.id);        
            park.collectCar(info, CustomerState.NORMAL_LIFE_WITH_CAR.toString());
        }
        catch(RemoteException e){
            GenericIO.writelnString("Remote exception: " +e.getMessage());
            System.exit(1);
        }             
    }
    
    /**
     * Get id from Customer
     * @return id of the Customer 
     */
    public int getID(){
        return id;
    }
    
    /**
     * Set current car information
     * @param id currentCar id
     * @param value information about customer
     */
    private void setCurrentCar(String id, String value){
        String[] temp = id.split(",");
        temp[2] = value;
        this.info = String.join(",",temp);
    }
    
    /**
     * Set payment information
     * @param id customer id
     * @param value information about customer
     */
    private void setPay(String id, String value){
        String[] temp = id.split(",");
        temp[4] = value;
        this.info = String.join(",",temp);
    }    
   
}
