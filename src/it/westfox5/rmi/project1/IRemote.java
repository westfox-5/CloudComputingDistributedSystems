package it.westfox5.rmi.project1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemote extends Remote {
    String SERVICE_NAME = "mostbeautifulserviceindaworld";
    String SERVICE_RMIURL = String.format("rmi://%s:%d/%s", Server.HOST, Server.PORT, IRemote.SERVICE_NAME);

    void service1() throws RemoteException;
}
