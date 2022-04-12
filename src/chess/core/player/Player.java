package chess.core.player;

public interface Player {
    public enum Alliance{
        WHITE, BLACK;

        public char alliance2char(){
            if(this.name() == "WHITE") return 'W';
            else return 'B';
        }
    }
}
