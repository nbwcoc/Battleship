import java.util.HashMap;

public class HitsBoard {
	private HashMap<Coords, HitMarker> hits = new HashMap<>();
	
	public HitMarker tryHit(int x, int y) {
		var xy = new Coords(x, y);
		
		if (hits.containsKey(xy))
			return null;
		
		return hits.put(xy, new HitMarker(false));
	}
}
