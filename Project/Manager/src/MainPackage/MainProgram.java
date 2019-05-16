/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Interfaces.*;
import genclass.GenericIO;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author danielmartins
 */
public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* get location of the generic registry service */
        String rmiRegHostName = Constants.REGISTRY_HOST_NAME;
        int rmiRegPortNumb = Constants.REGISTRY_PORT;

        /* look for the remote object by name in the remote host registry */
        String nameEntry = Constants.REGISTRY_NAME_ENTRY;
        Registry registry = null;
        
        ManagerLounge lounge = null;
        ManagerOutsideWorld outsideworld = null;
        ManagerRepairArea repairArea = null;
        ManagerSupplierSite suppliersite = null;
        try
        {
            registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("RMI registry creation exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        GenericIO.writelnString ("RMI registry was created!");
        
                /* Look for the other entities in the registry */
        try
        {
            lounge = (ManagerLounge) registry.lookup (Constants.LOUNGE_NAME_ENTRY);
        }
        catch (NotBoundException ex) {
            System.out.println("Racing Track is not registered: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Racing Track: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }

        try
        {
            outsideworld = (ManagerOutsideWorld) registry.lookup (Constants.OUTSIDEWORLD_NAME_ENTRY);
        }
        catch (NotBoundException ex) {
            System.out.println("Control Center is not registered: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Control Center: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }
        
        try
        {
            repairArea = (ManagerRepairArea) registry.lookup (Constants.REPAIRAREA_NAME_ENTRY);
        }
        catch (NotBoundException ex) {
            System.out.println("Betting Center is not registered: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Betting Center: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }
        
        try
        {
            suppliersite = (ManagerSupplierSite) registry.lookup (Constants.SUPPLIERSITE_NAME_ENTRY);
        }
        catch (NotBoundException ex) {
            System.out.println("Stable is not registered: " + ex.getMessage ());
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Stable: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }
        
        GenericIO.writelnString ("Starting Manager...");
        
        /**
         * Manager lifecycle start.
         */
        Manager manager = new Manager(0, lounge,  suppliersite, repairArea,outsideworld); 
        manager.start();
        try {
            manager.join();
        } catch (InterruptedException ex) {
        }
        
        GenericIO.writelnString ("Broker ended lifecycle.");
    }
    
}
