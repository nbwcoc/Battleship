import java.util.HashMap;

public class HitsBoard {
	private HashMap<Integer[], HitMarker> hits = new HashMap<>();
	
	public HitMarker tryHit(int x, int y) {
		Integer[] xy = {x, y};
		
		System.out.printf("%d, %d %b", xy[0], xy[1], hits.containsKey(xy));
		
		if (hits.containsKey(xy))
			return null;
		
		var newHit = new HitMarker(false);
		hits.put(xy,  newHit);
		
		return newHit;
	}
}
