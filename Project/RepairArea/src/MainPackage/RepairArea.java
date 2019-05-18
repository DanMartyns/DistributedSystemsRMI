/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Interfaces.GeneralInformationRepoInterfaces;
import Interfaces.RepairAreaInterfaces;
import MainPackage.Constants;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import genclass.GenericIO;
import java.rmi.RemoteException;
/**
 * @author danielmartins
 * @author giselapinto
 */
public class RepairArea implements RepairAreaInterfaces {

    /**
     * Logger class for debugging.
     */
    private GeneralInformationRepoInterfaces logger;

    /**
     * The queue of services registered by the manager
     */
    private Queue<Integer> services = new LinkedList<>();

    /**
     * Blocked services
     **/

    private Map<Integer,String> blockedServices = new HashMap<>();

    /**
     * Count completed jobs
     */
    private boolean shutdown = false;


    public RepairArea(GeneralInformationRepoInterfaces logger) {
        this.logger = logger;
    }

    /**
     * The mechanic remains in the "read paper" state, while the lists is empty. If not, continue
     * @param mechanic mechanic id
     * @param mechanicState the state of the mecaninc
     * @return the service who'll do
     */
    @Override
    public synchronized String readThePaper(int mechanic, String mechanicState) throws RemoteException {

        logger.setMechanicState(mechanic, mechanicState);
        logger.setNumberServiceRequest(services.size());

        while ( blockedServices.isEmpty() && services.isEmpty() && shutdown == false ){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.exit(1);
            }
        }
        String service;
        if (!blockedServices.isEmpty()){
            Map.Entry<Integer,String> entry = blockedServices.entrySet().iterator().next();
            int key = entry.getKey();
            String value = entry.getValue();
            service = key+","+value;
            blockedServices.remove(key,value);
            logger.setPiecesAvabal("0", (int) blockedServices.values().stream().filter(x -> x.equals("0")).count() );
            logger.setPiecesAvabal("1", (int) blockedServices.values().stream().filter(x -> x.equals("1")).count() );
            logger.setPiecesAvabal("2", (int) blockedServices.values().stream().filter(x -> x.equals("2")).count() );
        } else {
            service = services.poll()+",-1";
        }
        if(shutdown){
            service = "end";
        }
       notifyAll();
       GenericIO.writelnString(" Mechanic "+mechanic+" read the paper ");
       return service;
    }

    /**
     * The manager records the repair of a car.
     * @param info the information about customer
     * @param managerState the state of the manager
     */
    @Override
    public synchronized void registerService(String info, String managerState) throws RemoteException {
        int customer = Integer.parseInt(info.split(",")[0]);
        /**;
         * Register a service, means register the customer id
         * */
        services.add( customer );
        logger.setManagerState(managerState);
        notifyAll();
        GenericIO.writelnString(" Manager register service "+customer);
    }

    /**
     * In terms of simulation, indicates the service to be done
     * Transition state
     * @param mechanic mechanic id
     * @param mechanicState the state of the mecaninc
     */
    @Override
    public synchronized void startRepairProcedure(int mechanic, String mechanicState) throws RemoteException {
        logger.setMechanicState(mechanic,mechanicState);
        GenericIO.writelnString(" Mechanic "+mechanic+" start repair procedure ");
    }

    /**
    * Theoretically the mechanic will find out which part is missing from the car
    * A random value is generated to indicate the part missing from the car
    * @param mechanic mechanic id
    * @param mechanicState the state of the mecaninc
    * @return a random value generated to indicate the part missing from the car
    **/
    @Override
    public synchronized String getRequiredPart(int mechanic, String mechanicState) throws RemoteException {
        logger.setMechanicState(mechanic, mechanicState);
        /**
          * 0 - piece A
          * 1 - piece B
          * 2 - piece C
          */
        int min = 0;
        int max = 2;
        GenericIO.writelnString(" Mechanic "+mechanic+" get required part ");
        return ""+(int)(Math.random() * ((max) + 1));
    }

    /**
    * Checking the stock
    * If there are no parts, the car is locked and is passed to a next service
    * @param piece type of piece
    * @param car car id
    * @return true or false, if mechanics has parts with him or not
    **/
    @Override
    public synchronized boolean partAvailable(String piece, int car) throws RemoteException {

        GenericIO.writelnString(" Mechanic check part available ");
        GenericIO.writelnString(" A-type pieces quantity : "+Constants.pieceA);
        GenericIO.writelnString(" B-type pieces quantity : "+Constants.pieceB);
        GenericIO.writelnString(" C-type pieces quantity : "+Constants.pieceC);

        if (piece.equals("0") && Constants.pieceA <= 1 || piece.equals("1") && Constants.pieceB <= 1 || piece.equals("2") && Constants.pieceC <= 1){
            blockedServices.put(car, piece);          
            logger.setPiecesAvabal("0", (int) blockedServices.values().stream().filter(x -> x.equals("0")).count() );
            logger.setPiecesAvabal("1", (int) blockedServices.values().stream().filter(x -> x.equals("1")).count() );
            logger.setPiecesAvabal("2", (int) blockedServices.values().stream().filter(x -> x.equals("2")).count() );
            return false;
        } else {

            return true;
        }
    }
    /**
    * Decreases the number of pieces after verifying that they have
    * @param piece pieces in use
    * @param mechanic mechanic id
    * @param mechanicState current state of mechanic
    **/
    @Override
    public synchronized void resumeRepairProcedure(String piece, int mechanic, String mechanicState)  throws RemoteException {
        logger.setMechanicState(mechanic, mechanicState);
        logger.setPieces0Stored(Constants.pieceA);
        logger.setPieces1Stored(Constants.pieceB);
        logger.setPieces2Stored(Constants.pieceC);

        if (piece.equals("0") )
            Constants.pieceA--;
        if (piece.equals("1") )
            Constants.pieceB--;
        if (piece.equals("2") )
            Constants.pieceC--;
        GenericIO.writelnString(" Mechanic "+mechanic+" resume repair procedure ");
    }

    /**
    * Process the fix the car from customer
    * Transitional State
    **/
    @Override
    public synchronized void fixIt()  throws RemoteException{
    }

    /**
     * Function called by the manager to replace parts, according to a part and quantity.
     * @param peca piece's type
     * @param quantidade total number of pieces
     * @param managerState the state of the manager
     */
    @Override
    public synchronized void storePart(String peca, int quantidade, String managerState)  throws RemoteException{
        logger.setManagerState(managerState);
        if ( peca.equals("0") ){
            Constants.pieceA = Constants.pieceA + quantidade;
            logger.setPieces0Manager(Constants.pieceA);
        }
        if ( peca.equals("1") ) {
            Constants.pieceB = Constants.pieceB + quantidade;
            logger.setPieces1Manager(Constants.pieceB);
        }

        if ( peca.equals("2") ){
            Constants.pieceC = Constants.pieceC + quantidade;
            logger.setPieces2Manager(Constants.pieceC);
        }
        GenericIO.writelnString(" Manager store part ");
    }
    /**
     * Fuction to shutdown the repair area
     * @param managerState the manager state
     */
    @Override
    public synchronized void shutdownNow(String managerState) throws RemoteException{
        logger.setManagerState(managerState);
        this.shutdown = true;
        notifyAll();
        GenericIO.writelnString(" Manager shutdown Mechanics ");
    }

    /**
     * Terminate the outsideWorld service.
     */
    @Override
    public synchronized void serviceEnd() throws RemoteException{
        MainProgram.serviceEnd = true;
        notifyAll();
        GenericIO.writelnString(" Repair Area will end ");
    }
}
