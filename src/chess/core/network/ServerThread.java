package chess.core.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    private boolean exist;
    private BufferedReader in;
    private PrintWriter out;
    private Server server;

    public ServerThread(Socket socket, Server server){
        this.socket = socket;
        String ip = socket.getInetAddress().toString();
        this.server = server;
        System.out.println("Client [" + ip + "] connect to Server");
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            exist = true;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(exist){
            try{
                String data;
                data = in.readLine();
                String[] parsedData = data.split("|");
                if(parsedData[0] == "SETNAME"){

                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void kill(){
        this.exist = false;
    }

}
