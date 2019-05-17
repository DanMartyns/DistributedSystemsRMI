/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainProgram;


import Interfaces.GeneralInformationRepoInterfaces;
import Interfaces.LoungeInterfaces;
import Interfaces.OutsideWorldInterfaces;
import Interfaces.ParkInterfaces;
import Interfaces.RepairAreaInterfaces;
import Interfaces.SupplierSiteInterfaces;

import genclass.GenericIO;
import java.util.*;

import static MainProgram.Constants.ALERTING_CUSTOMER;
import static MainProgram.Constants.ATENDING_CUSTOMER;
import static MainProgram.Constants.GETTING_NEW_PARTS;
import static MainProgram.Constants.NUM_CUSTOMERS;
import java.rmi.RemoteException;


/**
 * @author danielmartins
 * @author giselapinto
 */
public class Lounge implements LoungeInterfaces {

    /**
     * Logger class for debugging.
     */
    private GeneralInformationRepoInterfaces logger;

    /**
     * OutsideWorld stub class.
     */
    private final OutsideWorldInterfaces outsideWorld;

    /**
     * Park stub class.
     */
    private final ParkInterfaces park;

    /**
     * RepairArea stub class.
     */
    private final RepairAreaInterfaces repairArea;

    /**
     * SupplierSite stub class.
     */
    private final SupplierSiteInterfaces supplierSite;

    /**
     * Queue dedicated the service "ATENDING CUSTOMER".
     */
    Queue<String> atending_customer = new LinkedList<>();

    /**
     * Queue dedicated the service "ALERTING CUSTOMER".
     */
    Queue<Integer> alerting_customer = new LinkedList<Integer>();

    /**
     * Queue dedicated the service "GETING NEW PARTS".
     */
    Queue<String> getting_new_parts = new LinkedList<>();

    /**
     * Number of entities that have sent a shutdown message.
     */
    private int shutdownNumber = 0;

    /**
     * Lounge constructor
     * @param logger GeneralInformationRepo stub instance
     * @param out out stub instance
     * @param park park stub instance
     * @param repairArea repairArea stub instance
     * @param supplierSite supplierSite stub instance
     */
    public Lounge(GeneralInformationRepoInterfaces logger, OutsideWorldInterfaces out,SupplierSiteInterfaces supplierSite, RepairAreaInterfaces repairArea,  ParkInterfaces park) {
        this.logger = logger;
        this.outsideWorld = out;
        this.park = park;
        this.repairArea = repairArea;
        this.supplierSite = supplierSite;
    }

    /******************************************************************************************
     *                              Synchronization Array
     *****************************************************************************************/

     private boolean[] clients = new boolean[NUM_CUSTOMERS];

    /******************************************************************************************
     *
     *****************************************************************************************/
     /**
      * Choose the next task
      * In theoretical terms, it will confirm if there is a new task to be done.
      * In practical terms, the manager waits if the vectors of each service are empty.
      * As soon as they are not, they perform a task.
      * @param managerState manager's state
      * @return true, when the vectores are not empty
     * @throws java.rmi.RemoteException
      */
    public synchronized boolean getNextTask(String managerState) throws RemoteException {
        logger.setManagerState(managerState);
        GenericIO.writelnString("I'm waiting for a new task");
        while(atending_customer.isEmpty() && alerting_customer.isEmpty() && getting_new_parts.isEmpty()){
            try {
                wait();
            } catch (InterruptedException ex) {
                GenericIO.writelnString("getNextTask - Manager thread was interrupted.");
                System.exit(1);
            }
        }
        GenericIO.writelnString(" Manager get next task ");
        return true;

    }

    /**
     * In theoretical terms, you will choose one of the services.
     * In practical terms, it will choose one of the vectors,
     * prioritizing to look for new parts, followed by alerting
     * the customers and finally serving the customers.
     * 0 - ATENDING_CUSTOMER
     * 1 - ALERTING_CUSTOMER
     * 2 - GETTING_NEW_PARTS
     * @return 0,1 or 2, which means the service number
     * @throws java.rmi.RemoteException
     */
    public synchronized String appraiseSit() throws RemoteException {
        assert(!atending_customer.isEmpty() || !alerting_customer.isEmpty() || !getting_new_parts.isEmpty());
        logger.setValueQueueIn(atending_customer.size());
        logger.setFlagAPieces(getting_new_parts.contains("0") ? "0" : "--");
        logger.setFlagBPieces(getting_new_parts.contains("1") ? "1" : "--");
        logger.setFlagCPieces(getting_new_parts.contains("2") ? "2" : "--");
        GenericIO.writelnString("The state of the queues\n Atending customer : "+atending_customer+"\n Alerting customer : "+alerting_customer+"\n Getting new parts : "+getting_new_parts+";");
        return (!getting_new_parts.isEmpty() ? GETTING_NEW_PARTS+"@"+getting_new_parts.poll() : ( !alerting_customer.isEmpty() ? ALERTING_CUSTOMER+"@"+alerting_customer.poll() : ATENDING_CUSTOMER+"@"+atending_customer.poll() ));


    }

    /**
     * The costumer go into the Lounge and waits for his turn
     * @param info customer's info
     * @param customerState customer's state
     * @throws java.rmi.RemoteException
     */
    public synchronized void queueIn(String info, String customerState) throws RemoteException{
        int customer = Integer.parseInt(info.split(",")[0]);
        logger.setCustomerState(customer, customerState);
        logger.setOwnCar(customer, info);
        logger.setValueQueueIn(atending_customer.size());
        atending_customer.add(info);
        notifyAll();
        GenericIO.writelnString(" Customer "+customer+" queue in ");
    }
    /**
     * Synchronization point.
     * In theoretical terms, you will receive the key to the replacement car.
     * In practical terms, synchronization will only be done using the key variable.
     * @param customer customer's id
     * @param customerState customer's state
     * @throws java.rmi.RemoteException
     */
    public synchronized void collectKey(int customer, String customerState) throws RemoteException {
        logger.setCustomerState(customer, customerState);
        while(clients [customer] == false){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.exit(1);
            }
        }
        clients [customer] = false;
        notifyAll();
        GenericIO.writelnString(" Customer "+customer+" collect car key ");
    }

    /**
     * Synchronization point.
     * In theoretical terms, it will give the key to the replacement car if the customer wants to.
     * In practical terms, synchronization will only be done using the key variable.
     * @param info customer's info
     * @throws java.rmi.RemoteException
     */
    public synchronized void handCarKey(String info) throws RemoteException{
        int id = Integer.parseInt(info.split(",")[0]);
        while(clients [id] == true){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.exit(1);
            }
        }
        clients [Integer.parseInt(info.split(",")[0])] = true;
        notifyAll();

    }
    /**
     * In theoretical terms, spend some time talking to the customer.
     * In practical terms, it is a synchronization point,
     * where the manager updates the "talkBetweenManCust" variable to true.
     * If the variable is already true, you expect the client to respond
     * before talking to another client.
     * Remove this customer from the service queue.
     * @param customer customer's info
     * @param managerState manager's state
     * @throws java.rmi.RemoteException
     */
    public synchronized void talkToCustomer(String customer, String managerState) throws RemoteException {

        /**
         * The manager starts a conversation by putting the "talk" variable to true
         */
        logger.setManagerState(managerState);
        int id = Integer.parseInt(customer.split(",")[0]);
        while(clients [id] == true){
            try {
                wait();

            } catch (InterruptedException ex) {
                GenericIO.writelnString("talkWithManager - One Customer thread was interrupted.");
                System.exit(1);
            }
        }
        clients [Integer.parseInt(customer.split(",")[0])] = true;
        notifyAll();
        GenericIO.writelnString(" Manager talk to customer "+id);
    }

    /**
     * In theoretical terms, the client spends some time talking to the manager.
     * In practical terms, it is a synchronization point where the client updates the "talkBetweenManCust"
     * variable to false if that client is the client that the manager initiated a conversation.
     * If the variable is already false, it means that the manager has not started a conversation
     * yet and therefore expects the manager to respond.
     * @param customer customer's id
     * @throws java.rmi.RemoteException
     */
    public synchronized void talkWithManager(int customer) throws RemoteException {

        /**
         * If the manager has started a conversation and is the client to be answered,
         * it sets the "talkBetweenManCust" variable to false,
         * which means that it responded, and wakes up the other threads.
         */

        while(clients [customer] == false){
            try {
                wait();

            } catch (InterruptedException ex) {
                GenericIO.writelnString("talkWithManager - One Customer thread was interrupted.");
                System.exit(1);
            }
        }
        clients [customer] = false;
        notifyAll();
        GenericIO.writelnString(" Customer "+customer+" talk with Manager ");
    }

    /**
     * In theoretical terms, the manager receives payment from the customer for the service rendered.
     * In practical terms, it is a synchronization point where the manager updates the variable "pay" to false,
     * meaning the collection of the payment.
     * @param info customer's info
     * @throws java.rmi.RemoteException
     */
    public synchronized void receivePayment(String info) throws RemoteException {
        int id = Integer.parseInt(info.split(",")[0]);
        while(clients [id] == true){
            try {
                wait();

            } catch (InterruptedException ex) {
                GenericIO.writelnString("talkWithManager - One Customer thread was interrupted.");
                System.exit(1);
            }
        }
        clients [Integer.parseInt(info.split(",")[0])] = true;
        notifyAll();
        GenericIO.writelnString(" Manager receive payment from "+id);

    }

    /**
     * In theoretical terms will effect the payment.
     * In practical terms, it is just a state of transition.
     * @param customer customer's id
     * @throws java.rmi.RemoteException
     */
    public synchronized void payForTheService(int customer) throws RemoteException {
        while(clients [customer] == false){
            try {
                wait();
            } catch (InterruptedException ex) {
                GenericIO.writelnString("talkWithManager - One Customer thread was interrupted.");
                System.exit(1);
            }
        }
        clients [customer] = false;
        notifyAll();
        GenericIO.writelnString(" Customer "+customer+" pay for the service ");

    }


    /**
     * Let manager know that the mechanics needs more pieces from supplier site
     * @param peca piece's type
     * @param mechanic mechanic's id
     * @param mechanicState mechanic's state
     * @throws java.rmi.RemoteException
     */
    public synchronized void letManagerKnow(String peca, int mechanic, String mechanicState) throws RemoteException {
        logger.setMechanicState(mechanic, mechanicState);
        if (!getting_new_parts.contains(peca))
            getting_new_parts.add(peca);
        logger.setFlagAPieces(getting_new_parts.contains("0") ? "0" : "--");
        logger.setFlagBPieces(getting_new_parts.contains("1") ? "1" : "--");
        logger.setFlagCPieces(getting_new_parts.contains("2") ? "2" : "--");

        notifyAll();
        GenericIO.writelnString(" Mechanic let Manager Know the piece "+peca);
    }

    /**
     * Notify the repair is concluded
     * @param currentCar the atual car of the customer
     * @param mechanic mechanic's id
     * @param mechanicState mechanic's state
     * @throws java.rmi.RemoteException
     */
    public synchronized void repairConcluded(int currentCar, int mechanic, String mechanicState) throws RemoteException {

        logger.setMechanicState(mechanic, mechanicState);
        alerting_customer.add(currentCar);
        notifyAll();
        GenericIO.writelnString(" Mechanic conclud the repair. Car "+currentCar);
    }

    /**
     * check if a type # part has already been ordered.
     * @param peca piece's type
     * @return if the piece has already been ordered or not
     * @throws java.rmi.RemoteException
     */
    public synchronized boolean checkRequest(String peca) throws RemoteException{
        GenericIO.writelnString(" Mechanic check the piece "+peca);
        logger.setFlagAPieces(getting_new_parts.contains("0") ? "0" : "--");
        logger.setFlagBPieces(getting_new_parts.contains("1") ? "1" : "--");
        logger.setFlagCPieces(getting_new_parts.contains("2") ? "2" : "--");
        return getting_new_parts.contains(peca) ? false : true;
    }

    /**
     * Receive shutdown from an entity.
     * If all entities sent a shutdown, send a shutdown to all other shared regions.
     * @throws java.rmi.RemoteException
     */
    public synchronized void serviceEnd() throws RemoteException{
        logger.setValueQueueIn(atending_customer.size());
        logger.setFlagAPieces(getting_new_parts.contains("0") ? "0" : "--");
        logger.setFlagBPieces(getting_new_parts.contains("1") ? "1" : "--");
        logger.setFlagCPieces(getting_new_parts.contains("2") ? "2" : "--");
        shutdownNumber++;
        if(shutdownNumber==3){
            repairArea.serviceEnd();
            supplierSite.serviceEnd();
            park.serviceEnd();
            outsideWorld.serviceEnd();
            logger.serviceEnd();
            MainProgram.serviceEnd = true;
            notifyAll();
            GenericIO.writelnString(" Lounge will end all servers, include yourself ");
        }
    }
}
