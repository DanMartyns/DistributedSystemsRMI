/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesState;

/**
 * @author danielmartins
 * @author giselapinto
 */
public enum CustomerState {
    /**
         * Normal life with car.
         */
        NORMAL_LIFE_WITH_CAR ("NLC"),
        
        /**
         * Park
         */
        PARK ("PAR"),
        
        /**
         * Waiting for replace car
         */
        WAITING_FOR_REPLACE_CAR ("WFR"),
        
        /**
         * Reception
         */
        RECEPTION ("REC"),
        
        /**
         * Normal life without car.
         */
        NORMAL_LIFE_WITHOUT_CAR ("NLB");
        
        private final String description;
        
        private CustomerState(String description){
            this.description = description;
        }
        
        @Override
        public String toString(){
            return this.description;
        }
    
}
