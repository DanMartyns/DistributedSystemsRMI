/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Interfaces.GeneralInformationRepoInterfaces;
import Interfaces.SupplierSiteInterfaces;
import genclass.GenericIO;
import java.rmi.RemoteException;

/**
 * @author danielmartins
 * @author giselapinto
 */
public class SupplierSite implements SupplierSiteInterfaces {

    /**
     * Logger class for debugging.
     */
    private GeneralInformationRepoInterfaces logger;
    
    /**
     * The number of pieces of each type
     */
    private int[] sendingPieces = new int[3];

    public SupplierSite(GeneralInformationRepoInterfaces logger) {
        this.logger = logger;
    }

    /**
     * The choice of quantity to be replenished in the stock of the repair area is random. 
     * Since this quantity is added to a sales record, where each index of the array 
     * corresponds to the id of the part, and its value is the quantity sold so far.
     * @param peca type of the piece
     * @param managerState the state of the manager
     * @return random number of parts of a part type.
     */
    @Override
    public synchronized int goToSupplier(String peca, String managerState) throws RemoteException {
        logger.setManagerState(managerState);
        int min = 1;
        int max = 9;
        int range = max - min + 1;
        int result = (int) (Math.random() * range);
        this.sendingPieces[Integer.parseInt(peca)] = this.sendingPieces[Integer.parseInt(peca)] + result;
        GenericIO.writelnString(" Manager go to supplier. Restore type "+peca+", quantity : "+result); 
        return result;
    }

    /**
     * Terminate the supplier site service.
     */
    @Override
    public synchronized void serviceEnd() throws RemoteException{
        MainProgram.serviceEnd = true;
        notifyAll();
        GenericIO.writelnString(" Supplier Site will end"); 
    }     
        
}
