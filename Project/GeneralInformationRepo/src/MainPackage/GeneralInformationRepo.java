/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;


import Interfaces.GeneralInformationRepoInterfaces;
import MainPackage.Constants;
import static MainPackage.Constants.NUM_CUSTOMERS;
import static MainPackage.Constants.NUM_MECHANICS;
import genclass.GenericIO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;


/**
 *
 * @author danielmartins
 * @author giselapinto
 */
public class GeneralInformationRepo implements GeneralInformationRepoInterfaces{
    
    private String fileName;
       

    private String managerState = "CWTD";
    private String[] customerState = new String[NUM_CUSTOMERS];
    private String[] mechanicState = new String[NUM_MECHANICS];
    
    private String[] ownCar = new String[NUM_CUSTOMERS];
    private String[] replecementCar = new String[NUM_CUSTOMERS];
    private String[] repairedCar = new String[NUM_CUSTOMERS];
    
    private String valueQueueIn;
    
    private String numberWaitingReplece;
    
    private String numberRepair;
    
    private String numberParkCars;
    
    private String numberReplacementPark;
    
    
    private String numberServiceRequest;
    
    private String pieces0Stored;
    
    private String pieces1Stored;

    private String pieces2Stored;

    private String piecesAAvabal;
    private String piecesBAvabal;
    private String piecesCAvabal;

    private String flagAPieces;
    private String flagBPieces;
    private String flagCPieces;
    
    private String pieces0Manager;
    
    private String pieces1Manager;

    private String pieces2Manager;

    
    
    /**
     * GeneralInformationRepo constructor
     * @param fileName name of the logger file
     */
   
    public GeneralInformationRepo(String fileName) throws RemoteException {
        this.fileName = fileName;
        
        for(int i = 0; i<customerState.length; i++){
            
            customerState[i] = "NLC";
            ownCar[i] = "--";
            replecementCar[i] = "F";
            valueQueueIn = "00";
            repairedCar[i]="F";
            
            
        }

        for(int i = 0; i<mechanicState.length; i++){
            mechanicState[i] = "WFW";
        }
        
        numberRepair = "00";
        numberParkCars = "00";
        numberReplacementPark="00";
        numberWaitingReplece = "00";
        numberServiceRequest = "00";
        
        pieces0Stored=""+Constants.pieceA;
        pieces1Stored=""+Constants.pieceB;
        pieces2Stored=""+Constants.pieceC;
        
        piecesAAvabal = "00";
        piecesBAvabal = "00";
        piecesCAvabal = "00";
        
        flagAPieces = "F";
        flagBPieces = "F";
        flagCPieces = "F";
        
        pieces0Manager ="00";
        pieces1Manager ="00";
        pieces2Manager ="00";



    }

    /**
     * This function initiate de header log from the logger file, without deleting anything important
     */
    public synchronized void initStateLog() throws RemoteException{
        
        try {
            FileWriter fw = new FileWriter(this.fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("\t\t\t\t REPAIR SHOP ACTIVITIES - Description of the internal state of the problem\n");
            bw.newLine();
            bw.write("MAN   MECHANIC \t\t\t\t\t\t\t\t\t\t Customers");
            bw.newLine();
            bw.write("Stat  St0 St1  S00 C00 P00 R00 S01 C01 P01 R01 S02 C02 P02 R02 S03 C03 P03 R03 S04 C04 P04 R04 S05 C05 P05 R05 S06 C06 P06 R06 S07 C07 P07 R07 S08 C08 P08 R08 S09 C09 P09 R09");
            bw.newLine();
            bw.write("               S10 C10 P10 R10 S11 C11 P11 R11 S12 C12 P12 R12 S13 C13 P13 R13 S14 C14 P14 R14 S15 C15 P15 R15 S16 C16 P16 R16 S17 C17 P17 R17 S18 C18 P18 R18 S19 C19 P19 R19");
            bw.newLine();
            bw.write("               S20 C20 P20 R20 S21 C21 P21 R21 S22 C22 P22 R22 S23 C23 P23 R23 S24 C24 P24 R24 S25 C25 P25 R25 S26 C26 P26 R26 S27 C27 P27 R27 S28 C28 P28 R28 S29 C29 P29 R29");
            bw.newLine();
            bw.write("               \t LOUNGE\t\tPARK\t\t\t\t\tREPAIR AREA\t\t\t\t\tSUPPLIERES SITE");
            bw.newLine();
            bw.write("               InQ Wtk NRV     NCV NPV          NSRQ    Prt0  NV0  S0  Prt1  NV1  S1  Prt2  NV2  S2                     PP0  PP1  PP2                                          ");
            bw.newLine();

            
            bw.close();
            fw.close();
        } catch (IOException ex) {
            GenericIO.writelnString("initStateLog error - Could not write to logger file.");
            System.exit(1);
        }
        
    }
    
    /**
     * print all the relevante information from logger file, States, and all updateVariables
     */
    public synchronized void printHeaderLog() throws RemoteException{
        try{
            FileWriter fw = new FileWriter(this.fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(""+ managerState);
            
            for(int i = 0; i<mechanicState.length; i++){
                bw.write(" "+ mechanicState[i]);
            }
            
            for(int i = 0; i<customerState.length; i++){
                bw.write("   "+customerState[i]+"  "+ownCar[i]+"  "+ replecementCar[i]+"   "+repairedCar[i]);
                if((i+1)%10 == 0){
                    bw.newLine();
                    bw.write("            ");
                }
                
            }
            bw.write("    "+valueQueueIn+"  "+numberWaitingReplece+"   "+numberRepair+"      "+numberParkCars+"   "+numberReplacementPark+"           "+numberServiceRequest+"      "+pieces0Stored+"    "+piecesAAvabal
                    +"   "+flagAPieces+"    "+pieces1Stored+"   "+piecesBAvabal+"   "+flagBPieces+"    "+pieces2Stored+"   "+piecesCAvabal+"    "+flagCPieces+"                    "
                    +pieces0Manager+"    "+pieces1Manager+"    "+pieces2Manager);
            bw.newLine();
            bw.newLine();
            bw.close();
            fw.close();
        }
        catch(IOException e){
            GenericIO.writelnString("printHeaderLog error - Could not write to logger file.");
            System.exit(1);
        }
    }
 
    /**
     * Get the state from manager
     * @param manager_state the state of the manager
     */
    public synchronized void setManagerState(String manager_state) throws RemoteException {
        this.managerState = manager_state;
        printHeaderLog();
    }

    /**
     * get the respective customer state for all customers alive
     * @param id from customer
     * @param customerState state of customers
     */
    public synchronized void setCustomerState(int id, String customerState) throws RemoteException {
        this.customerState[id] = customerState;
        printHeaderLog();
    }

    /**
     * get the respective mechanics state for all mechaincs alive
     * @param id of mechanic
     * @param mechanicState state
     */
    public synchronized void setMechanicState(int id, String mechanicState) throws RemoteException {
        this.mechanicState[id] = mechanicState;
        printHeaderLog();
    }
    
    /**
     * vehicle driven by customer: 
     * own car  customer id; 
     * replacement car - R0, R1, R2 ; 
     * none - '-'
     * @param id customer Id
     * @param ownCar it own car or replecment car
     */
    public synchronized void setOwnCar(int id, String ownCar) throws RemoteException {
        String[] inf = ownCar.split(",");
    
        this.ownCar[id] = inf[1].equals(inf[2]) ? String.format("%02d",Integer.parseInt(inf[2]))
                        : inf[2].equals("-1") ? "R0"
                        : inf[2].equals("-2") ? "R1"
                        : inf[2].equals("-3") ? "R2"
                        : "--";

        printHeaderLog();
    }

    /**
     * customer requires replacement vehicle: T or F 
     * @param id customer id
     * @param replecementCar if requeires replecment car or not
     */
    public synchronized void setReplecementCar(int id, String replecementCar) throws RemoteException{
        String[] inf = replecementCar.split(",");
        this.replecementCar[id] = inf[3].equals("1") ? "T" : "F";
        
        printHeaderLog();

    }
    
    /**
     * customer vehicle has already been repaired
     * @param id customer id
     * @param repairedCar if car is repaired or not
     */
    public synchronized void setAlreadyRepaired(int id, int repairedCar[]) throws RemoteException {
        this.repairedCar[id] = repairedCar[id]==1 ? "T" : "F";
        printHeaderLog();

    }
    
    /**
     * number of customers presently queueing to talk to the manager
     * @param valueQueueIn value of customer on queue
     */
    public synchronized void setValueQueueIn(int valueQueueIn) throws RemoteException {

        this.valueQueueIn = String.format("%02d",valueQueueIn);
        printHeaderLog();
    }
       
    /**
     *number of customer waiting for a replacement vehicle
     * @param numberWaitingReplece number of replacement car
     */
    public synchronized void setNumberWaitingReplece(int numberWaitingReplece) throws RemoteException {

        this.numberWaitingReplece = String.format("%02d",numberWaitingReplece);
        printHeaderLog();
        
    }
    
    /**
     * number of cars that have already been repaired
     * @param numberRepair number of cars repaired
     */
    public synchronized void setNumberRepair(int numberRepair) throws RemoteException {

        this.numberRepair = String.format("%02d",numberRepair);
        printHeaderLog();
    }

    /**
     * number of customer vehicles presently parked at the repair shop park
     * @param numberParkCars number of cars in the park
     */
    public synchronized void setNumberParkCars(int numberParkCars) throws RemoteException {
        this.numberParkCars = String.format("%02d",numberParkCars);
        printHeaderLog();
    }

    /**
     * number of replacement vehicles presently parked at the repair shop park
     * @param numberReplacementPark number of replacement cars in the park
     */
    public synchronized void setNumberReplacementPark(int numberReplacementPark) throws RemoteException {
      
        this.numberReplacementPark = String.format("%02d",numberReplacementPark);    
        printHeaderLog();
    }

    /**
     * number of service requests made by the manager to the repair area
     * @param numberServiceRequest number of registed services by manager
     */
    public synchronized void setNumberServiceRequest(int numberServiceRequest) throws RemoteException {
        
        this.numberServiceRequest = String.format("%02d",numberServiceRequest);
        printHeaderLog();
    }

    /**
     * number of parts of type 0 presently in storage at the repair area
     * @param pieces0Stored number of A-pieces in the repair area
     */
    public synchronized void setPieces0Stored(int pieces0Stored) throws RemoteException {
        this.pieces0Stored = String.format("%02d",pieces0Stored);
        printHeaderLog();
    }

    /**
     * number of parts of type 1 presently in storage at the repair area
     * @param pieces1Stored number of B-pieces in the repair area
     */
    public synchronized void setPieces1Stored(int pieces1Stored) throws RemoteException {
        this.pieces1Stored = String.format("%02d",pieces1Stored);
        printHeaderLog();
    }

    /**
     * number of parts of type 2 presently in storage at the repair area
     * @param pieces2Stored number of C-pieces in the repair area
     */
    public synchronized void setPieces2Stored(int pieces2Stored) throws RemoteException {
        this.pieces2Stored = String.format("%02d",pieces2Stored);
        printHeaderLog();
    }

    /**
     * number of customer vehicles waiting for part # to be available so that the repair may resume 
     * @param piece piece's type
     * @param quantityOfCustomersWaiting quantity on waiting
     */
    public synchronized void setPiecesAvabal(String piece, int quantityOfCustomersWaiting) throws RemoteException {
        if (piece.equals("0")) this.piecesAAvabal = String.format("%02d",quantityOfCustomersWaiting) ;
        if (piece.equals("1")) this.piecesBAvabal = String.format("%02d",quantityOfCustomersWaiting) ;
        if (piece.equals("2")) this.piecesCAvabal = String.format("%02d",quantityOfCustomersWaiting) ;
        printHeaderLog();
    }
    
    /**
     * flag signaling the manager has been adviced that part # is missing at the repair area: T or F
     * @param flagPieces A-piece type
     */
    public synchronized void setFlagAPieces(String flagPieces) throws RemoteException{
        this.flagAPieces = flagPieces.equals("0") ? "T" : "F";
        printHeaderLog();
    }
    
    /**
     * flag signaling the manager has been adviced that part # is missing at the repair area: T or F
     * @param flagPieces B-piece type 
     */
    public synchronized void setFlagBPieces(String flagPieces) throws RemoteException{
        this.flagBPieces = flagPieces.equals("1") ? "T" : "F";
        printHeaderLog();
    }
    
    /**
     * flag signaling the manager has been adviced that part # is missing at the repair area: T or F
     * @param flagPieces C-piece type
     */
    public synchronized void setFlagCPieces(String flagPieces) throws RemoteException{
        this.flagCPieces = flagPieces.equals("2") ? "T" : "F";
        printHeaderLog();
    }
   
    /**
     * number of parts of type 0 which have been purchased so far by the manager
     * @param pieces0Manager A-pieces returned by the manager
     */
    public synchronized void setPieces0Manager(int pieces0Manager) throws RemoteException {
        this.pieces0Manager = String.format("%02d",pieces0Manager) ;
        printHeaderLog();
    }

    /**
     *number of parts of type 1 which have been purchased so far by the manager
     * @param pieces1Manager B-pieces returned by the manager
     */
    public synchronized void setPieces1Manager(int pieces1Manager) throws RemoteException {
        this.pieces1Manager = String.format("%02d",pieces1Manager) ;        
        printHeaderLog();
    }

    /**
     * number of parts of type 2 which have been purchased so far by the manager
     * @param pieces2Manager C-pieces returned by the manager
     */
    public synchronized void setPieces2Manager(int pieces2Manager) throws RemoteException {
        this.pieces2Manager = String.format("%02d",pieces2Manager) ;
        printHeaderLog();
    }

    /**
     * Terminate the logger service.
     */
    public synchronized void serviceEnd() throws RemoteException{
        MainProgram.serviceEnd = true;
    }    
}
