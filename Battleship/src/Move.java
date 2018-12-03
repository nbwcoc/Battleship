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
	
	public Coords getCoords() {
		return new Coords(x, y);
	}
	
	public Type getType() {
		return type;
	}
}
