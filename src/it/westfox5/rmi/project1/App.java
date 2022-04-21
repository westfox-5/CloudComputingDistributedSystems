package it.westfox5.rmi.project1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {

        /* START SERVER FIRST */
        /* java -classpath .\out\production\CloudComputingDistributedSystems it.westfox5.rmi.project1.Server */


    //===  2 CLIENT SEQUENTIAL EXECUTION ===//
        /*
        Client c1 = new Client();
        c1.exec();

        Client c2 = new Client();
        c2.exec();
        */

    //===  N CLIENTS PARALLEL EXECUTION ===//
        final int NUM_THREADS = 2;
        final int NUM_CLIENTS = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS); /* Executors.newCachedThreadPool(); */

        try {
            Client.checkConnection();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (int i=0; i<NUM_CLIENTS; ++i) {
            executorService.submit(() -> {

                // spawn new client
                try {
                    Client client = new Client();
                    client.exec();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            });
        }

        executorService.shutdown();
    }
}
