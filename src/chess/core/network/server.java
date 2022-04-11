package chess.core.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    ServerSocket serverSocket;
    boolean exist = false;
    int port;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            this.port = port;
            exist = true;
            System.out.println("Server on port " + port + " has opened successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start();
    }
    
    @Override
    public void run(){
        while(exist){
            try (Socket socket = serverSocket.accept()) {
                ServerThread thread = new ServerThread(socket);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        try {
            serverSocket.close();
            this.exist = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server on port " + port + " has been closed successfully");
    }
    
}
