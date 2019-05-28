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
 * @author giselapinto
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
        
        ILounge loungeInt = null;
        IOutside outsideworldInt = null;
        IRepair repairAreaInt = null;
        ISupplier suppliersiteInt = null;
        
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
            loungeInt = (ILounge) registry.lookup (Constants.LOUNGE_NAME_ENTRY);
        }
        catch (NotBoundException ex) {
            System.out.println("Lounge is not registered: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Lounge: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }

        try
        {
            outsideworldInt = (IOutside) registry.lookup (Constants.OUTSIDEWORLD_NAME_ENTRY);
        }
        catch (NotBoundException ex) {
            System.out.println("Outside World is not registered: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Outside World: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }
        
        try
        {
            repairAreaInt = (IRepair) registry.lookup (Constants.REPAIRAREA_NAME_ENTRY);
        }
        catch (NotBoundException ex) {
            System.out.println("Repair Area is not registered: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Repair Area: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }
        
        try
        {
            suppliersiteInt = (ISupplier) registry.lookup (Constants.SUPPLIERSITE_NAME_ENTRY);
        }
        catch (NotBoundException ex) {
            System.out.println("Supplier Site is not registered: " + ex.getMessage ());
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Supplier Site: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }
        
        GenericIO.writelnString ("Starting Manager...");
        
        /**
         * Manager lifecycle start.
         */
        Manager manager = new Manager(0, loungeInt,  suppliersiteInt, repairAreaInt, outsideworldInt); 
        manager.start();
        try {
            manager.join();
        } catch (InterruptedException ex) {
        }
        
        GenericIO.writelnString ("Manager ended lifecycle.");
        
        try {
            loungeInt.serviceEnd();
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while calling service end: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }
    }
    
}
