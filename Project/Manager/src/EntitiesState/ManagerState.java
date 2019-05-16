/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesState;

/**
 * Enum with the possible states of the manager on his lifecycle.
 * @author danielmartins
 * @author giselapinto
 */
public enum ManagerState { 
    /**
     * Checking what to do.
     */
    CHECKING_WHAT_TO_DO ("CWTD"),

    /**
     * Attending customer.
     */
    ATTENDING_CUSTOMER ("ATCU"),

    /**
     * Posting Job.
     */
    POSTING_JOB ("POJB"),

    /**
     * Supervising the race.
     */
    ALERTING_CUSTOMER ("ALCU"),

    /**
     * Replenish Stock.
     */
    REPLENISH_STOCK ("REST"),

    /**
     * Getting new parts.
     */
    GETTING_NEW_PARTS ("GENP");

    private final String description;

    private ManagerState(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return this.description;
    }    
}
