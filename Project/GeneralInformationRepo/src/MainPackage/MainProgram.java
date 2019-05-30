/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Interfaces.*;
import static MainPackage.Constants.FILE_NAME;
import genclass.GenericIO;
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
    public static void main(String[] args) throws RemoteException {

        /* get location of the generic registry service */
        String rmiRegHostName = Constants.REGISTRY_HOST_NAME;
        int rmiRegPortNumb = Constants.REGISTRY_PORT;

        /* look for the remote object by name in the remote host registry */
        String nameEntry = Constants.REGISTRY_NAME_ENTRY;
        String nameEntryObject = Constants.LOGGER_NAME_ENTRY;

        /* create and install the security manager */
        if (System.getSecurityManager () == null)
            System.setSecurityManager (new SecurityManager ());


        Registry registry = null;
        IRegistry registerInt = null;


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

        GenericIO.writelnString ("Starting Logger...");

        /* Initialize the shared region */
        GeneralInformationRepo logger = new GeneralInformationRepo(FILE_NAME);
        IGeneral loggerInt = null;

        logger.initStateLog();
        logger.printHeaderLog();

        try
        {
            loggerInt = (IGeneral) UnicastRemoteObject.exportObject (logger, Constants.LOGGER_PORT);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Logger stub generation exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }

        /* register it with the general registry service */
        try
        {
            registerInt = (IRegistry) registry.lookup(nameEntry);
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
        { registerInt.rebind (nameEntryObject, loggerInt);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Logger registration exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
        GenericIO.writelnString ("Logger object was registered!");

        /* Wait for the service to end */
        while(!serviceEnd){
            try {
                synchronized(logger){
                    logger.wait();
                }
            } catch (InterruptedException ex) {
                GenericIO.writelnString("Main thread of Logger was interrupted.");
                System.exit(1);
            }
        }

        GenericIO.writelnString("Logger finished execution.");

        /* Unregister shared region */
        try
        {
            registerInt.unbind (nameEntryObject);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Logger unregistration exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        } catch (NotBoundException ex) {
          GenericIO.writelnString ("Logger unregistration exception: " + ex.getMessage ());
          ex.printStackTrace ();
          System.exit (1);
        }
        GenericIO.writelnString ("Logger object was unregistered!");

        /* Unexport shared region */
        try
        {
            UnicastRemoteObject.unexportObject (logger, false);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Logger unexport exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }

        GenericIO.writelnString ("Logger object was unexported successfully!");

    }

}
