package chess.core.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    public Client(String ip, int port){
        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
