package Communication;

import java.io.Serializable;
/**
 * Generic message that will pass through the socket
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
     * String argument
     */
    private String str1;
    /**
     * String argument
     */
    private String str2;

    /**
     * Integer argument
     */
    private int int1;

    /**
     * Integer argument
     */
    private int int2;

    private boolean boolean1;

    /**
     * Array argument
     */
    private int[] array;

    /**
     * Empty constructor for the message that initializes the default
     * values for all the variables.
     */
    public Message(){
        type = null;

        str1 = null;
        str2 = null;

        int1 = -1;
        int2 = -1;

        boolean1 = false;

        array = null;
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
     * @param value1 String argument
     * @param value2 state of the entity
     */
    public Message(MessageType type, String value1, String value2){
        this();
        this.type = type;
        switch(type){
            case QUEUE_IN:
            case TALK_TO_CUSTOMER:
            case REGISTER_SERVICE:
            case BACK_TO_WORK_BY_CAR:
            case PHONE_CUSTOMER:
            case GO_TO_REPAIR_SHOP:
            case GO_TO_SUPPLIER :
           case COLLECT_CAR : {
                this.str1 = value1;
                this.str2 = value2;
                break;
            }
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
            case PAY_FOR_THE_SERVICE:
            case RETURN_FIND_CAR :
            case RETURN_VEHICLE :
            case GET_VEHICLE :
            case BLOCK_VEHICLE :
            case RETURN_GO_TO_SUPPLIER :
            case SET_SIZE_QUEUE :
            case SET_WAITING_CARS :
            case SET_WAIT_REPLACEMENT:
            case SET_NUMBER_REPAIR:
            case SET_NUMBER_PARKED_CARS:
            case SET_NUMBER_REPLACEMENT_CAR_PARKED:
            case SET_SERVICE :
            case PIECES_A_STORED :
            case PIECES_B_STORED :
            case PIECES_C_STORED :
            case PIECES_A_STORED_MANAGER:
            case PIECES_B_STORED_MANAGER:
            case PIECES_C_STORED_MANAGER:
                this.int1 = value;
                break;
            }
    }

    /**
     * Constructor with the type of the message and two String arguments.
     * @param type type of the Message
     * @param value1 integer argument
     * @param value2 string argument
     */
    public Message(MessageType type, int value1, String value2){
        this();
        this.type = type;
        switch(type){
            case COLLECT_KEY:
            case READ_THE_PAPER:
            case START_REPAIR_PROCEDURE :
            case GET_REQUIRED_PART :
            case PART_AVAILABLE :
            case DECIDE_ON_REPAIR :
            case BACK_TO_WORK_BY_BUS :
            case FIND_CAR :
            case SET_CUSTOMER_STATE :
            case SET_MECHANIC_STATE :
            case SET_REPLACEMENT_CAR :
            case SET_OWN_CAR :
            case SET_PIECE_AVAILABLE :	
                this.int1 = value1;
                this.str1 = value2;
                break;
        }
    }

    /**
     * Constructor with the type of the message and a String argument.
     * @param type type of the Message
     * @param value String argument
     */
    public Message(MessageType type, String value){
        this();
        this.type = type;
        switch(type) {
            case GET_NEXT_TASK:
            case RETURN_APRAISE_SIT :
            case RECEIVE_PAYMENT :
            case HAND_CAR_KEY :
            case CHECK_REQUEST :
            case SHUTDOWN :
            case RETURN_READ_THE_PAPER :
            case RETURN_GET_REQUIRED_PART :
            case SET_MANAGER_STATE :
            case SET_FLAG_A:
            case SET_FLAG_B:
            case SET_FLAG_C:
                this.str1 = value;
                break;
            }
    }

    /**
     * Constructor with the type of the message and two String arguments.
     * @param type type of the Message
     * @param value1 string argument
     * @param value2 integer argument
     * @param value3 string argument
     */
    public Message(MessageType type, String value1, int value2, String value3){
        this();
        this.type = type;
        switch(type){
            case LET_MANAGER_KNOW :
            case STORE_PART :
            case RESUME_REPAIR_PROCEDURE :
                this.str1 = value1;
                this.int1 = value2;
                this.str2 = value3;
                break;

        }
    }
    /**
     * Constructor with the type of the message and two String arguments.
     * @param type type of the Message
     * @param value1 integer argument
     * @param value2 integer argument
     * @param value3 string argument
     */
    public Message(MessageType type, int value1, int value2, String value3){
        this();
        this.type = type;
        switch(type){
            case REPAIR_CONCLUDED:
                 this.int1 = value1;
                 this.int2 = value2;
                 this.str1 = value3;
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
            case RETURN_PART_AVAILABLE :
            case RETURN_DECIDE_ON_REPAIR :
                this.boolean1 = value;
                break;

        }
    }

    /**
     * Constructor with the type of the message, one int argument and one int array.
     * @param type type of the Message
     * @param value1 integer argument
     * @param array array argument
     */
    public Message(MessageType type, int value1, int[] array){
        this();
        this.type = type;
        switch(type){
            case SET_ALREADY_REPAIRED:
                 this.int1 = value1;
                 this.array = array;
                 break;
        }
    }

    public MessageType getType() {
        return type;
    }

    public String getStr1() {
        return str1;
    }

    public String getStr2() {
        return str2;
    }

    public int getInt1() {
        return int1;
    }

    public int getInt2() {
        return int2;
    }

    public boolean isBoolean1() {
        return boolean1;
    }

    public int[] getArray() {
        return array;
    }

}
