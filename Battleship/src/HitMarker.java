public class HitMarker {
	private boolean hit;
	
	public HitMarker(boolean hit) {
		this.hit = hit;
	}
	
	public boolean getHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	@Override
	public String toString() {
		if(hit == true)
			return "X";
		else
			return "O";
	}
}
