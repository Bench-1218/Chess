package chess.core.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server extends Thread{
    /* Protocols:
    C: Challenge
        CT|<name>：challenge to
            C2S
            challenge to <name>
            ATTENTION: please ensure name is not in battle. check it locally
    	CF|<name>: challenge from
            S2C
            recieve a challenge from <name>
    	CAG|<name>: challenge agree
            C2S
            take the challenge from <name>
    	CRF|<name>: challenge refuse
            C2S
            refuse the challenge from <name>
    	COK|: challenge OK
            S2C
            your challenge is accepted
    	CNO|: challenge NO
            S2C
            your challenge is refused
    
    B: Battle
        BC1|<move>: battle move TO
            C2S or S2C
            client make a move
            the server should send the move to enermy and watcher
        BC2|<move>: battle move FROM
            C2S or S2C
            send the move to the other client
        BGETSTA|<name>: battle get status
            C2S
            get battle status of name
        BOVER|: battle over
            S2C
            the server send the msg to all players that the battle is over
        




    */
    private ServerSocket serverSocket;
    private boolean exist = false;
    private int port;
    private Map<String, Client> clients;

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
                ServerThread thread = new ServerThread(socket, this);
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
    
    public void addClient(String name, Client c){
        clients.put(name, c);
    }   
}
