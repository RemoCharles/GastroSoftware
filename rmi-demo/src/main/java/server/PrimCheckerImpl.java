package src.main.java.server;

import src.main.java.api.PrimChecker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrimCheckerImpl extends UnicastRemoteObject implements PrimChecker {

	protected PrimCheckerImpl() throws RemoteException {
	}
	public boolean isPrim(int nummer) throws RemoteException {

		for (int i = 2; i * i <= nummer; i++) {
			if (nummer % i == 0) {
				return false;
			}
		}
		return true;
	}
}