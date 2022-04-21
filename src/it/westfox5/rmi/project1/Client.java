package it.westfox5.rmi.project1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public final class Client implements java.io.Serializable {
    private static int SEQ_CLIENT_ID = 0;

    private IRemote server;
    private final int clientId;

    public Client() throws RemoteException {
        this.clientId = ++SEQ_CLIENT_ID;
        connect();
    }

    public static void checkConnection() throws RemoteException, NotBoundException {
        try {
            Naming.lookup(IRemote.SERVICE_RMIURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void connect() throws RemoteException {
        if (server == null) {
            try {
                server = (IRemote) Naming.lookup(IRemote.SERVICE_RMIURL);
                System.out.println(this + " Connected to server");
            } catch (MalformedURLException | NotBoundException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void exec() throws RemoteException {
        server.service1();
        System.out.println(this + " Execution of service  successful (" + IRemote.SERVICE_NAME + ")");
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "[CLIENT " + clientId + ']';
    }
}
