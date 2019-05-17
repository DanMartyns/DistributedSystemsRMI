/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainProgram;

import Interfaces.*;
import genclass.GenericIO;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author danielmartins
 */
public class MainProgram {
    
    /**
     * Used to check if the service must terminate.
     */
    public static boolean serviceEnd = false;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* get location of the generic registry service */
        String rmiRegHostName = Constants.REGISTRY_HOST_NAME;
        int rmiRegPortNumb = Constants.REGISTRY_PORT;

        /* look for the remote object by name in the remote host registry */
        String nameEntry = Constants.REGISTRY_NAME_ENTRY;
        String nameEntryObject = Constants.LOUNGE_NAME_ENTRY;

        Registry registry = null;
        RegisterInterfaces registerInt = null;
        GeneralInformationRepoInterfaces logger = null;
        OutsideWorldInterfaces outsideWorld = null;
        ParkInterfaces park = null;
        RepairAreaInterfaces repairArea = null;
        SupplierSiteInterfaces supplierSite = null;
        
       /* create and install the security manager */
        if (System.getSecurityManager () == null)
            System.setSecurityManager (new SecurityManager ());
        
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
            logger = (GeneralInformationRepoInterfaces) registry.lookup (Constants.LOGGER_NAME_ENTRY);
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
            outsideWorld = (OutsideWorldInterfaces) registry.lookup (Constants.OUTSIDEWORLD_NAME_ENTRY);
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
            park = (ParkInterfaces) registry.lookup (Constants.PARK_NAME_ENTRY);
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
        
        try
        {
            repairArea = (RepairAreaInterfaces) registry.lookup (Constants.REPAIRAREA_NAME_ENTRY);
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
        
        try
        {
            supplierSite = (SupplierSiteInterfaces) registry.lookup (Constants.SUPPLIERSITE_NAME_ENTRY);
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
        
        GenericIO.writelnString ("Starting Lounge...");
        
        /* Initialize the shared region */
        Lounge lounge = new Lounge(logger, outsideWorld, supplierSite, repairArea, park);
        LoungeInterfaces loungInt = null;
        
        try
        { 
            loungInt = (LoungeInterfaces) UnicastRemoteObject.exportObject (lounge, Constants.LOUNGE_PORT);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Paddock stub generation exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        
        /* register it with the general registry service */
        try
        { registerInt = (RegisterInterfaces) registry.lookup(nameEntry);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Register lookup exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        catch (NotBoundException e)
        { GenericIO.writelnString ("Register not bound exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }

        try
        { registerInt.bind (nameEntryObject, loungInt);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Paddock registration exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        catch (AlreadyBoundException e)
        { GenericIO.writelnString ("Paddock already bound exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        GenericIO.writelnString ("Paddock object was registered!");
        
        /* Wait for the service to end */
        while(!serviceEnd){
            try {
                synchronized(lounge){
                    lounge.wait();
                }
            } catch (InterruptedException ex) {
                GenericIO.writelnString("Main thread of paddock was interrupted.");
                System.exit(1);
            }
        }
        
        GenericIO.writelnString("Paddock finished execution.");
        
        /* Unregister shared region */
        try
        { 
            registerInt.unbind (nameEntryObject);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Paddock unregistration exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        } catch (NotBoundException ex) {
          GenericIO.writelnString ("Paddock unregistration exception: " + ex.getMessage ());
          ex.printStackTrace ();
          System.exit (1);
        }
        GenericIO.writelnString ("Paddock object was unregistered!");
        
        /* Unexport shared region */
        try
        { 
            UnicastRemoteObject.unexportObject (lounge, false);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Paddock unexport exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        
        GenericIO.writelnString ("Paddock object was unexported successfully!");
        
    }
   
}
