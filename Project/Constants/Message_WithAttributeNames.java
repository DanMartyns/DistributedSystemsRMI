/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import java.io.Serializable;

/**
 *
 * @author danielmartins
 * @author giselapinto
 */
public class Message implements Serializable {

    /**
     * Serial version of the class. Format used is XXYYZZ
     * (classNumber-praticClassNumber-groupNumber)
     */
    private final long serialVersionUID = 010401L;

    /**
     * Type of the message.
     */
    private MessageType type;

    /**
     * State of an entity
     */
    private String state;

    /**
     * The Information about Customer
     */
    private String infoCustomer;

    /**
     * Customer id
     */
    private int customerID;

    /**
     * Mechanic id
     */
    private int mechanicID;

    /**
     * The service chosen
     */
    private String apraiseSit;

    /**
     * Type of the piece
     */
    private String piece;

    /**
     * Number of pieces
     */
    private int quantity;

    /**
     * Result from checkRequest function
     */
    private boolean checkRequest;

    /**
     * Result from readThePaper function
     */
    private String readPaper;

    /**
     * Result from partAvailable function
     */
    private boolean partAvailable;

    /**
     * Result from decideOnRepair function
     */
    private boolean decideOnRepair;

    /**
     * Result from findCar function
     */
    private int replacementCar;

    /**
     * Size of the queue in Lounge
     */
    private int queueIn;

    /**
     * Number of cars waiting
     */
    private int waits;

    /**
     * Cars Repaired
     */
    private int[] repairedCars;

    /**
     * Empty constructor for the message that initializes the default
     * values for all the variables.
     */
    public Message(){
        type = null;
        state = null;
        infoCustomer = null;
        customerID = -1;
        apraiseSit = null;
        piece = null;
        quantity = -1;
        checkRequest = false;
        readPaper = null;
        partAvailable = false;
        decideOnRepair = false;
        queueIn = -1;
        waits = -1;
        repairedCars = null;
    }

    /**
     * Constructor with only the type of the message.
     * @param type type of the Message
     */
    public Message(MessageType type){
        this();
        this.type = type;
    }

    /**
     * Constructor with the type of the message and two String arguments.
     * @param type type of the Message
     * @param value String argument
     * @param state state of the entity
     */
    public Message(MessageType type, String value, String state){
        this();
        this.type = type;
        switch(type){
            case QUEUE_IN:
                this.infoCustomer = value;
                this.state = state;
            case TALK_TO_CUSTOMER :
                this.infoCustomer = value;
                this.state = state;
            case REGISTER_SERVICE :
                this.infoCustomer = value;
                this.state = state;
            case BACK_TO_WORK_BY_CAR :
                this.infoCustomer = value;
                this.state = state;
                break;
            case PHONE_CUSTOMER :
                this.infoCustomer = value;
                this.state = state;
                break;
            case GO_TO_REPAIR_SHOP :
                this.infoCustomer = value;
                this.state = state;
                break;
            case GO_TO_SUPPLIER :
                this.piece = value;
                this.state = state;
                break;

        }
    }

    /**
     * Constructor with the type of the message and an integer argument.
     * @param type type of the Message
     * @param value Integer argument
     */
    public Message(MessageType type, int value){
        this();
        this.type = type;
        switch(type) {
            case TALK_WITH_MANAGER:
                this.customerID = value;
                break;
            case PAY_FOR_THE_SERVICE:
                this.customerID = value;
                break;
            case RETURN_FIND_CAR :
                this.replacementCar = value;
                break;
            case RETURN_VEHICLE :
                this.customerID = value;
                break;
            case GET_VEHICLE :
                this.customerID = value;
                break;
            case BLOCK_VEHICLE :
                this.customerID = value;
                break;
            case RETURN_GO_TO_SUPPLIER :
                this.quantity = value;
                break;
            case SET_SIZE_QUEUE :
                this.queueIn = value;
                break;
            case SET_WAITING_CARS :
                this.waits = value;
                break;
            case SET_WAIT_REPLACEMENT:
                this.waits = value;
                break;
            case SET_SERVICE :
                this.quantity = value;
                break;
            case PIECES_A_STORED :
                this.quantity = value;
                break;
            case PIECES_B_STORED :
                this.quantity = value;
                break;
            case PIECES_C_STORED :
                this.quantity = value;
                break;
            case PIECES_A_STORED_MANAGER:
                this.quantity = value;
                break;
            case PIECES_B_STORED_MANAGER:
                this.quantity = value;
                break;
            case PIECES_C_STORED_MANAGER:
                this.quantity = value;
                break;
            }
    }

    /**
     * Constructor with the type of the message and two String arguments.
     * @param type type of the Message
     * @param value the id of the customer
     * @param state the customer state
     */
    public Message(MessageType type, int value, String state){
        this();
        this.type = type;
        switch(type){
            case COLLECT_KEY:
                this.customerID = value;
                this.state = state;
                break;
            case READ_THE_PAPER:
                this.mechanicID = value;
                this.state = state;
                break;
            case START_REPAIR_PROCEDURE :
                this.mechanicID = value;
                this.state = state;
                break;
            case GET_REQUIRED_PART :
                this.mechanicID = value;
                this.state = state;
                break;
            case PART_AVAILABLE :
                this.customerID = value;
                this.piece = state;
                break;
            case DECIDE_ON_REPAIR :
                this.customerID = value;
                this.state = state;
                break;
            case BACK_TO_WORK_BY_BUS :
                this.customerID = value;
                this.state = state;
                break;
            case FIND_CAR :
                this.customerID = value;
                this.state = state;
                break;
            case COLLECT_CAR :
                this.customerID = value;
                this.state = state;
                break;
            case SET_CUSTOMER_STATE :
                this.customerID = value;
                this.state = state;
                break;
            case SET_MECHANIC_STATE :
                this.mechanicID = value;
                this.state = state;
                break;
            case SET_REPLACEMENT_CAR :
                this.replacementCar = value;
                this.infoCustomer = state;
                break;
            case SET_OWN_CAR :
                this.customerID = value;
                this.infoCustomer = state;
                break;

        }
    }

    /**
     * Constructor with the type of the message and a String argument.
     * @param type type of the Message
     * @param value String argument, the string can assume multiple definitions
     */
    public Message(MessageType type, String value){
        this();
        this.type = type;
        switch(type) {
            case GET_NEXT_TASK:
                this.state = value;
                break;
            case RETURN_APRAISE_SIT :
                this.apraiseSit = value;
                break;
            case RECEIVE_PAYMENT :
                this.infoCustomer = value;
                break;
            case HAND_CAR_KEY :
                this.infoCustomer = value;
                break;
            case CHECK_REQUEST :
                this.piece = value;
                break;
            case SHUTDOWN :
                this.state = value;
                break;
            case RETURN_READ_THE_PAPER :
                this.readPaper = value;
                break;
            case RETURN_GET_REQUIRED_PART :
                this.piece = value;
                break;
            case SET_MANAGER_STATE :
                this.state = value;
                break;
            case SET_FLAG_A:
                this.piece = value;
                break;
            case SET_FLAG_B:
                this.piece = value;
                break;
            case SET_FLAG_C:
                this.piece = value;
                break;
            case SET_PIECE_AVAILABLE :
                this.piece = value;
                break;
            }
    }

    /**
     * Constructor with the type of the message and two String arguments.
     * @param type type of the Message
     * @param piece the piece who'll be stored
     * @param value integer argument
     * @param state the state of an entity
     */
    public Message(MessageType type, String piece, int value, String state){
        this();
        this.type = type;
        switch(type){
            case LET_MANAGER_KNOW :
                this.piece = piece;
                this.mechanicID = value;
                this.state = state;
                break;
            case STORE_PART :
                this.piece = piece;
                this.quantity = value;
                this.state = state;
                break;
            case RESUME_REPAIR_PROCEDURE :
                this.piece = piece;
                this.mechanicID = value;
                this.state = state;

        }
    }
    /**
     * Constructor with the type of the message and two String arguments.
     * @param type type of the Message
     * @param currentCar the current car of the customer
     * @param quantity the number of pieces who'll be stored
     * @param state the state of an entity
     */
    public Message(MessageType type, int currentCar, int quantity, String state){
        this();
        this.type = type;
        switch(type){
            case REPAIR_CONCLUDED:
                 this.customerID = currentCar;
                 this.quantity = quantity;
                 this.state = state;
                 break;
        }
    }

    /**
     * Constructor with the type of the message and a boolean argument.
     * @param type type of the Message
     * @param value boolean argument
     */
    public Message(MessageType type, boolean value){
        this();
        this.type = type;
        switch(type){
            case RETURN_CHECK_REQUEST :
                this.checkRequest = value;
                break;
            case RETURN_PART_AVAILABLE :
                this.partAvailable = value;
                break;
            case RETURN_DECIDE_ON_REPAIR :
                this.decideOnRepair = value;
        }
    }

    /**
     * Constructor with the type of the message, one int argument and one int array.
     * @param type type of the Message
     * @param car the current car of the customer
     * @param repairedCar cars already repaired
     */
    public Message(MessageType type, int car, int[] repairedCars){
        this();
        this.type = type;
        switch(type){
            case SET_ALREADY_REPAIRED:
                 this.customerID = car;
                 this.repairedCars = repairedCars;
                 break;
        }
    }

    public MessageType getType() {
        return type;
    }

    public String getInfoCustomer() {
        return infoCustomer;
    }

    public String getState() {
        return state;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getApraiseSit() {
        return apraiseSit;
    }

    public int getMechanicID() {
        return mechanicID;
    }

    public String getPiece() {
        return piece;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isCheckRequest() {
        return checkRequest;
    }

    public String getReadPaper() {
        return readPaper;
    }

    public boolean isPartAvailable() {
        return partAvailable;
    }

    public boolean isDecideOnRepair() {
        return decideOnRepair;
    }

    public int getQueueIn() {
        return queueIn;
    }

    public int getWaits() {
        return waits;
    }
    public int getReplacementCar() {
        return replacementCar;
    }
    public int[] getRepairedCars(){
        return repairedCars;
    }
}
