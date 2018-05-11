package src.main.java.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrimChecker extends Remote {
	boolean isPrim(int nummer) throws RemoteException;

	String RO_NAME = "PRIM_CHECKER_RO";

}