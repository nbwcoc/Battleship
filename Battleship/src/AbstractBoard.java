import java.util.HashMap;
import java.util.function.Function;

public abstract class AbstractBoard {
    protected void displayImpl(Function<Coords, String>... boardFuncs) {
        int height = 11;
        int width = 11;
        StringBuilder grid = new StringBuilder(height * (width + 1)); 
                        
        for (int row = 1; row < height; row++) {
                for (int column = 1; column < width; column++) {
                    boolean shouldPrintHash = true;
                    for (var board : boardFuncs) {
                        var res = board.apply(new Coords(column, row));
                        if (res != null) {
                            grid.append(" " + res + " ");
                            shouldPrintHash = false;
                            break;
                        }
                    }
                    if (shouldPrintHash)
                        grid.append(" # ");
                }
                // next row
                grid.append(" | " + row + "\n"); 
        }
        System.out.println(" 1  2  3  4  5  6  7  8  9  10");
        System.out.println("-------------------------------");
        System.out.println(grid.toString());
    }
    
    protected <T> String getMarker(HashMap<Coords, T> map, Coords key) {
        var rv = map.get(key);
        return (rv != null) ? rv.toString() : null;
    }
    
    public abstract void displayBoard();
}
