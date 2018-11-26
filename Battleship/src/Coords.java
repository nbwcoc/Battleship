public class Coords {
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }
    private int x;
    private int y;
    
    public Integer[] getCoords() {
        Integer[] xy = {x, y};
        return xy;
    }
    
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
    
}
