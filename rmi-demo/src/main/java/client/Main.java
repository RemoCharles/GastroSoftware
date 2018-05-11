package src.main.java.client;

import src.main.java.api.PrimChecker;

import java.rmi.Naming;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        String rmiServerIP = "localhost";
        int rmiPort = 6666;
        // policy-Datei angeben und SecurityManager installieren
        System.setProperty("java.security.policy", "file:/C:/Users/joshu/Documents/Personal Projects/GastroSoftware/rmi-demo/checker.policy");
        //System.setProperty("java.security.policy", "checker.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        // URL definieren und die Referenz auf das entfernte Objekt holen (Stub)
        String url = "rmi://" + rmiServerIP + ":" + rmiPort + "/" + PrimChecker.RO_NAME;
        PrimChecker stub = (PrimChecker) Naming.lookup(url);
        // Eine ganze Zahl einlesen und die entfernte Methode 'isPrim' aufrufen
        System.out.print("\nEine positive ganze Zahl eingeben: ");
        int n = new Scanner(System.in).nextInt();
        boolean prim = stub.isPrim(n);
        System.out.println("Die Zahl " + n + " ist eine Primzahl: " + prim);

    }

}
