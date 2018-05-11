package src.main.java.server;

import src.main.java.api.PrimChecker;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

	public static void main(String[] args) throws Exception {
		PrimChecker primCheckerRO = new PrimCheckerImpl();
		Registry reg = LocateRegistry.createRegistry(6666);
		if(reg != null) {
			reg.rebind(PrimChecker.RO_NAME, primCheckerRO);
			System.out.println("Server gestartet");
			new java.util.Scanner(System.in).nextLine();
			reg.unbind(PrimChecker.RO_NAME);
			System.exit(0);
		}
	}

}
