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
    
    public void incrementX() {
        x++;
    }
    
    public void incrementY() {
        y++;
    }
    
    public void decrementX() {
        x--;
    }
    
    public void decrementY() {
        y--;
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
    
}
