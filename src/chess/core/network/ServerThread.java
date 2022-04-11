package chess.core.network;

import java.net.Socket;

public class ServerThread extends Thread{
    Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
        System.out.println("Client [" + socket.getInetAddress() + "] connect to Server");
    }

    @Override
    public void run() {
        // TODO protocol
    }
}
