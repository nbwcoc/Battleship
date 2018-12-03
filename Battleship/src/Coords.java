import java.util.HashMap;

public class Coords {
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }
    private int x;
    private int y;
    
    @Override
    public int hashCode() {
        return x + y * 100;
    }
    
    public Coords incrementX() {
        return new Coords(x + 1, y);
    }
    
    public Coords incrementY() {
        return new Coords(x, y + 1);
    }
    
    public Coords decrementX() {
        return new Coords(x - 1, y);
    }
    
    public Coords decrementY() {
        return new Coords(x, y - 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object thatRaw) {
        var that = (Coords)thatRaw;
        return that.x == this.x && that.y == this.y;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
