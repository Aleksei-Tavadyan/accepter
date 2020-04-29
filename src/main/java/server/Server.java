package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;

public class Server implements Runnable{
    private static volatile Server instance = null;

    private final int SERVER_PORT = 6789;

    private ServerSocket serverSocket = null;

    private Server() {}

    public static Server getServer() {

        if(instance == null) {
            synchronized (Server.class) {
                if(instance == null) {
                    instance = new Server();
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("START server on port: "+ SERVER_PORT);

            while(true) {

                ConnectionWorker worker = null;

                try {

                    worker = new ConnectionWorker(serverSocket.accept());
                    System.out.println("Get client connection");

                    Thread t = new Thread(worker);
                    t.start();
                }   catch (Exception e){
                    System.out.println("Connection error: " + e.getMessage());

                }
            }
        }   catch (Exception e){
            System.out.println("Connection error: " + e.getMessage());
        }   finally {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
