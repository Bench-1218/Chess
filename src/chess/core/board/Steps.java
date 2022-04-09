package chess.core.board;

import java.util.ArrayList;

public class Steps extends ArrayList<Move>{
    
    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        for(Move m : this){
            sb.append(m);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean add(Move m){

        if(super.add(m)){
            // TODO
        }else return false;

    }
    public String getData(){
        return this.toString();
    }
    public int readData(String file){
        // 
        return 0;
    }
}
