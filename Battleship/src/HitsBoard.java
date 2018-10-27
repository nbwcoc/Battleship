import java.util.HashMap;

public class HitsBoard {
	private HashMap<Integer[], HitMarker> hits = new HashMap<>();
	
	public HitMarker tryHit(int x, int y) {
		Integer[] xy = {x, y};
		
		if (hits.containsKey(xy))
			return null;
		
		return hits.put(xy, new HitMarker(false));
	}
}
