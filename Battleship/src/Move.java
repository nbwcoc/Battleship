public class Move {
	public enum Type {
		SHOT,
		LOSS
	};
	
	private int x;
	private int y;
	private Type type;
	
	public Move(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int[] getCoords() {
		int[] xy = {x, y};
		return xy;
	}
	
	public Type getType() {
		return type;
	}
}
