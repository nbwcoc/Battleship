public class Ship {
	private int length;
	private int hitCount = 0;
	
	public Ship(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}
	
	public void hit() {
		hitCount++;
	}
	
	public boolean isSunken() {
		return hitCount == length;
	}
	
	@Override
	public String toString() {
		return "S";
	}
}
