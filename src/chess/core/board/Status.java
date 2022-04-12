package chess.core.board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Status extends ArrayList<Move>{

    private int lastLeftTime = 30;

    public Status(){
        super();
    }

    public Status(final Status s){
        super();
        for(Move m : s){
            this.add(m);
        }
    }

    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lastLeftTime + "\n");
        for(Move m : this){
            sb.append(m);
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean loadStatus(String path, Board board){
        // return whether load successfully
        BufferedReader in;
        try {
            File file = new File(path);
            in = new BufferedReader(new FileReader(file));
            this.setLastLeftTime(Integer.parseInt(in.readLine()));

            String s;
            while((s = in.readLine()) != null){
                Move m = new Move(s);
                if(m.getType() == null) {
                    in.close();
                    return false;
                }
                this.add(m);
                board.movePiece(m);
            }
            in.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        }
        return false;

    }

    public boolean loadStatus(Status sNew, Board board){
        // return whether load successfully
        for(Move m : sNew){
            if(!board.movePiece(m)) return false;
        }
        return true;
    }

    public void saveStatus(String path, int leftTime){
        try {
            File file = new File(path);
            FileWriter out = new FileWriter(file);
            setLastLeftTime(leftTime);
            out.write(this.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getLastLeftTime() {
        return lastLeftTime;
    }

    public void setLastLeftTime(int lastLeftTime) {
        this.lastLeftTime = lastLeftTime;
    }

    @Override
    public boolean add(Move m){
        if(super.add(m)){
        }else return false;
        return true;
    }
    public String getData(){
        return this.toString();
    }
    public int readData(String file){
        // 
        return 0;
    }
}
