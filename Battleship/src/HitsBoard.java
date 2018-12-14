import java.util.HashMap;

public class HitsBoard extends AbstractBoard {
	private HashMap<Coords, HitMarker> hits = new HashMap<>();
	
	public HitMarker tryHit(int x, int y) {
		var xy = new Coords(x, y);
		
		if (hits.containsKey(xy))
			return null;
		
		var newHit = new HitMarker(false);
		hits.put(xy,  newHit);
		
		return newHit;
	}
	
	public void displayBoard() {
	    displayImpl((Coords c) -> {return getMarker(hits, c);});
	}
}
