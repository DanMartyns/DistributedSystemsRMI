/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author danielmartins
 * @author giselapinto
 */
public interface ManagerRepairArea extends Remote {
    /**
     * Register a Service (RepairArea)
     * @param info the information about customer
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException  
     */
    public void registerService(String info, String managerState) throws RemoteException;  
    
    /**
     * Replace the pieces (RepairArea)
     * @param peca, string argument, type of the piece
     * @param quantidade, integer argument, number os pieces
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException  
     */
    public void storePart(String peca, int quantidade, String managerState) throws RemoteException;
    
    /**
     * Role responsible for telling mechanics that work is over (RepairArea)
     * @param managerState the state of the manager
     * @throws java.rmi.RemoteException  
     */
    public void shutdownNow(String managerState) throws RemoteException;
}
