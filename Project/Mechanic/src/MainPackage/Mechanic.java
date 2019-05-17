package MainPackage;

import EntitiesState.MechanicState;
import Interfaces.MechanicsLounge;
import Interfaces.MechanicsPark;
import Interfaces.MechanicsRepairArea;

import static MainPackage.Constants.TYPE_PARTS;
import genclass.GenericIO;
import java.rmi.RemoteException;


/**
 * @author giselapinto
 * @author danielmartins
 */
public class Mechanic extends Thread {
    /**
     * Identifier of the Manager.
     */
    private int id;
    
    /**
     * The State of the Mechanic
     */
    private MechanicState state;
    
    /**
     * Car currently being serviced
     */
    private String currentService = "";
    private int currentVehicle = 0;
    
    /** 
     * Piece currently being serviced 
     */
    private String currentPiece =  ""+TYPE_PARTS;
    
    
   
    /**
     * Instance of the mechanics interface Repair Area.
     */    
    private MechanicsRepairArea repairArea;
    
    /**
     * Instance of the mechanics interface lounge.
     */
    private MechanicsLounge lounge;

    /**
     * Instance of the mechanics interface park.
     */    
    private MechanicsPark park;
    
    /**
     * Mechanic constructor
     * 
     * @param id identifier of the mechanic
     * @param lounge instance of the lounge
     * @param repairArea instance of the repair area
     * @param park instance of the park
     */ 
    public Mechanic(int id, MechanicsLounge lounge, MechanicsRepairArea repairArea, MechanicsPark park) {
        this.id = id;
        this.lounge = lounge;
        this.repairArea = repairArea;
        this.park = park;
    
    }
    
    /**
     * Implements the life cycle of the broker.
     */ 
    @Override
    public void run() {
        try {
            while((currentService = repairArea.readThePaper(id, MechanicState.WAITING_FOR_WORK.toString())).equals("end") == false ){


                repairArea.startRepairProcedure(id, MechanicState.FIXING_CAR.toString());
                currentVehicle = Integer.parseInt(currentService.split(",")[0]);
                currentPiece = currentService.split(",")[1];

                park.getVehicle(currentVehicle);           

                if (currentPiece.equals("-1")){
                    currentPiece = repairArea.getRequiredPart(id,MechanicState.CHECKING_STOCK.toString());  
                }        

                if (repairArea.partAvailable(currentPiece, currentVehicle)){

                    repairArea.resumeRepairProcedure(currentPiece, id, MechanicState.FIXING_CAR.toString());  
                    repairArea.fixIt();                      
                    park.returnVehicle(currentVehicle);
                    lounge.repairConcluded(currentVehicle, id, MechanicState.ALERTING_MANAGER.toString());               
                }
                else {
                    park.blockVehicle(currentVehicle);
                    if (lounge.checkRequest(currentPiece))
                        lounge.letManagerKnow(currentPiece, id, MechanicState.ALERTING_MANAGER.toString());                          
                }
           }
        }
        catch(RemoteException e){
            GenericIO.writelnString("Remote exception: " +e.getMessage());
            System.exit(1);
        } 
    }
    /**
     * Get the Mechanic ID
     * @return mechanic's id
     */
    public int getID() {
        return id;
    }
    
    /**
     * Get current service
     * @return current Service
     */
    public String getCurrentService() {
        return currentService;
    }
    
    /**
     * Get current piece
     * @return current piece
     */
    public String getCurrentPiece() {
        return currentPiece;
    }
        
} 

