/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesState;

/**
 *
 * @author giselapinto
 * @author danielmartins
 */
public enum MechanicState {
    /**
    * WAITING FOR WORK.
    */
    WAITING_FOR_WORK ("WFW"),

   /**
    * FIXING THE CAR.
    */
   FIXING_CAR ("FIX"),

   /**
    * CHECKING THE STOCK.
    */
   CHECKING_STOCK ("CHS"),

   /**
    * ALEARTING THE STOCK.
    */
   ALERTING_MANAGER ("ALM");
   
   /**
   * String with a description of the enum
   */
   private final String description;
   
   
   /**
   * Get the state of the mechanic
   * @param description string with description
   */
   private MechanicState(String description){
       this.description = description;
   }

   /**
   * To string method
   * @return description string with description
   */
   @Override
   public String toString(){
       return this.description;
   }     
}
