import java.util.HashMap;

public class Board {
	private HashMap<Integer[], Ship> shipLayer = new HashMap<>();
	private HashMap<Integer[], HitMarker> hitLayer = new HashMap<>();
	
	public boolean addHit(int x, int y) {
		Integer[] xy = {x, y};
	
		if (hitLayer.containsKey(xy))
			return false;
		
		Ship hitShip = shipLayer.get(xy);
		if (hitShip != null)
			hitShip.hit();
			
		hitLayer.put(xy, new HitMarker(hitShip != null));
		
		return true;
	}
}
