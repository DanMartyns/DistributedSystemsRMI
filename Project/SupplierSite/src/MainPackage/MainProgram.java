/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Interfaces.*;
import genclass.GenericIO;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author giselapinto
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
        String nameEntryObject = Constants.SUPPLIERSITE_NAME_ENTRY;

        Registry registry = null;
        RegisterInterfaces registerInt = null;
        GeneralInformationRepoInterfaces logger = null;
        
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
            System.out.println("Logger is not registered: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit(1);
        } catch (RemoteException ex) {
            System.out.println("Exception thrown while locating Logger: " + ex.getMessage () );
            ex.printStackTrace ();
            System.exit (1);
        }
        
        
        GenericIO.writelnString ("Starting Supplier Site...");
        
        /* Initialize the shared region */
        SupplierSite supplierSite = new SupplierSite(logger);
        SupplierSiteInterfaces supplierSiteInt = null;
        
        try
        { 
            supplierSiteInt = (SupplierSiteInterfaces) UnicastRemoteObject.exportObject (supplierSite, Constants.SUPPLIERSITE_PORT);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Supplier Site stub generation exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        
        /* register it with the general registry service */
        try
        { 
            registerInt = (RegisterInterfaces) registry.lookup(nameEntry);
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
        { registerInt.bind (nameEntryObject, supplierSiteInt);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Supplier Site registration exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        catch (AlreadyBoundException e)
        { GenericIO.writelnString ("Supplier Site already bound exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        GenericIO.writelnString ("Supplier Site object was registered!");
        
        /* Wait for the service to end */
        while(!serviceEnd){
            try {
                synchronized(supplierSite){
                    supplierSite.wait();
                }
            } catch (InterruptedException ex) {
                GenericIO.writelnString("Main thread of Supplier Site was interrupted.");
                System.exit(1);
            }
        }
        
        GenericIO.writelnString("Supplier Site finished execution.");
        
        /* Unregister shared region */
        try
        { 
            registerInt.unbind (nameEntryObject);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Supplier Site unregistration exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        } catch (NotBoundException ex) {
          GenericIO.writelnString ("Supplier Site unregistration exception: " + ex.getMessage ());
          ex.printStackTrace ();
          System.exit (1);
        }
        GenericIO.writelnString ("Supplier Site object was unregistered!");
        
        /* Unexport shared region */
        try
        { 
            UnicastRemoteObject.unexportObject (supplierSite, false);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Supplier Site unexport exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        
        GenericIO.writelnString ("Supplier Site object was unexported successfully!");
        
    }
   
}
