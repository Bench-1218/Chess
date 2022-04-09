package chess.core.board;

public class Position {
    private final int x, y;
    public Position(int x, int y){
        if(x < 0) System.out.println("Error: x < 0");
        if(x >= Board.WIDTH) System.out.println("Error: x > width of the board");
        if(y < 0) System.out.println("Error: y < 0");
        if(y >=Board.HEIGHT) System.out.println("Error: x > height of the board");

        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
    
}
