/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Interfaces.ManagerLounge;
import Interfaces.ManagerOutsideWorld;
import Interfaces.ManagerRepairArea;
import Interfaces.ManagerSupplierSite;

import static MainPackage.Constants.ALERTING_CUSTOMER;
import static MainPackage.Constants.ATENDING_CUSTOMER;
import static MainPackage.Constants.GETTING_NEW_PARTS;
import static MainPackage.Constants.NUM_CUSTOMERS;

import EntitiesState.ManagerState;

/**
 * @author giselapinto
 * @author danielmartins
 */
public class Manager extends Thread {
    /**
     * Identifier of the Manager.
     */
    private int id;
    /**
     * Instance of the manager interface Lounge.
     */
    private ManagerLounge lounge;
    
    /**
     * Instance of the manager interface Supplier Site.
     */    
    private ManagerSupplierSite supplierSite;
    
    /**
     * Instance of the manager interface Repair Area.
     */
    private ManagerRepairArea repairArea;
    
    
    /**
     * Instance of the manager interface outside world
     */
    private ManagerOutsideWorld outsideWorld;
    
    /**
     * Give the number of cars to repair
     */
    private int numRepairedClients = 0;  
    
    /**
     * Manager constructor
     * 
     * @param id identifier of the manager
     * @param lounge instance of the lounge
     * @param supplierSite instance of the supplier site
     * @param repairArea instance of the repair area
     * @param outsideWorld instance of the outside world
     */
    
    public Manager(int id, ManagerLounge lounge, ManagerSupplierSite supplierSite, ManagerRepairArea repairArea, ManagerOutsideWorld outsideWorld) {
        this.id = id;
        this.lounge = lounge;
        this.supplierSite = supplierSite;
        this.repairArea = repairArea;
        this.outsideWorld = outsideWorld;
        
    }
    /**
     * Implements the life cycle of the broker.
     */ 
    @Override
    public void run(){
        
        while(lounge.getNextTask(ManagerState.CHECKING_WHAT_TO_DO.toString())){            
            String[] choice = lounge.appraiseSit().split("@"); 

            if(choice[0].equals(ATENDING_CUSTOMER)){
                String[] customer = choice[1].split(",");
                lounge.talkToCustomer(choice[1], ManagerState.ATTENDING_CUSTOMER.toString());

                if(customer[3].equals("0") && customer[4].equals("0")){
                    repairArea.registerService( choice[1] , ManagerState.POSTING_JOB.toString() );
                }
                else if (customer[3].equals("1") && customer[4].equals("0")){               
                    lounge.handCarKey(choice[1]);
                    repairArea.registerService( choice[1] , ManagerState.POSTING_JOB.toString() );
                }               
                else if (customer[4].equals("1")) {                   
                    lounge.receivePayment(choice[1]);                       
                    numRepairedClients++;

                    if( numRepairedClients == NUM_CUSTOMERS){
                        repairArea.shutdownNow(ManagerState.CHECKING_WHAT_TO_DO.toString());               
                        break;    
                    }                    
                }
            }  
            else if(choice[0].equals(ALERTING_CUSTOMER)){
                outsideWorld.phoneCustomer(choice[1], ManagerState.ALERTING_CUSTOMER.toString());            
            }    
            else if (choice[0].equals(GETTING_NEW_PARTS)){    
                int quantidade = supplierSite.goToSupplier(choice[1], ManagerState.GETTING_NEW_PARTS.toString());
                repairArea.storePart(choice[1], quantidade, ManagerState.REPLENISH_STOCK.toString());
            }                  
        }
    }
  
}