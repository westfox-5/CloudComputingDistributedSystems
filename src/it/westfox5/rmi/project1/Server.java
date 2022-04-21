package it.westfox5.rmi.project1;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public final class Server extends UnicastRemoteObject implements IRemote {

    public final static String HOST = "localhost";
    public final static int PORT = 5678;

    private Server() throws RemoteException {
        System.out.println("[SERVER] Instantiated a new server"); }

    @Override
    public void service1() throws RemoteException {
        System.out.println("[SERVER] Serving a client ...");
    }

    public static void bind() {
        try {
            Remote server = new Server();
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.rebind(IRemote.SERVICE_NAME, server);

            String rmi_url = String.format("rmi://%s:%d/%s", HOST, PORT, IRemote.SERVICE_NAME);
            System.out.println("[SERVER] Bounded @ " + rmi_url);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void unbound() {
        try {
            Registry registry = LocateRegistry.getRegistry(PORT);
            registry.unbind(IRemote.SERVICE_NAME);

            System.out.println("[SERVER] Unbounded");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
