package it.westfox5.rmi.project1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {

    //=== START SERVER FIRST ===//
        Server.bind();
        //(new Thread(Server::bind)).start();
        /* java -classpath .\out\production\CloudComputingDistributedSystems it.westfox5.rmi.project1.Server */


    //===  2 CLIENT SEQUENTIAL EXECUTION ===//
        /*
        Client c1 = new Client();
        c1.exec();

        Client c2 = new Client();
        c2.exec();
        */

    //===  N CLIENTS PARALLEL EXECUTION ===//
        final int NUM_THREADS = 4;
        final int NUM_CLIENTS = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS); /* Executors.newCachedThreadPool(); */

        Client.waitForConnection(10);

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

        if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }

        Server.unbound();
    }
}
