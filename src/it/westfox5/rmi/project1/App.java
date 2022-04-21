package it.westfox5.rmi.project1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    private static final int NUM_THREADS = 10; /* num clients */

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
        ExecutorService executorService = Executors.newFixedThreadPool(4); /* Executors.newCachedThreadPool(); */

        try {
            Client.checkConnection();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (int i=0; i<NUM_THREADS; ++i) {
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
