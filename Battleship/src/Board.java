import java.util.HashMap;
import java.util.Random;

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
	
	public void placeShips(Ship[] shipArray) {
		// create five new ship objects that represent each ship that can be placed
		Ship carrier = new Ship(5);
		Ship battleship = new Ship(4);
		Ship cruiser = new Ship(3);
		Ship submarine = new Ship(3);
		Ship destroyer = new Ship(2);
		
		// use the shipArray passed in from the player class
		shipArray[0] = carrier;
		shipArray[1] = battleship;
		shipArray[2] = cruiser;
		shipArray[3] = submarine;
		shipArray[4] = destroyer;
		Random rand = new Random();
		// run our loop 5 times for the amount of ships we want placed
		for(int x = 0; x < 5; x++) {
			// create an x and y pairing for the initial placement of the ship
			// direction will be used for ship placement. 0 = North, 1 = East, 2 = South, 3 = West
	
			int  xCoord = rand.nextInt(10) + 1;
			int  yCoord = rand.nextInt(10) + 1;
			int direction = rand.nextInt(4);
			Integer[] xy = {xCoord,yCoord};
			// if this specific coordinate on the board does not exist in the hashmap then we will place the ship
			if(!shipLayer.containsKey(xy)) {
				// cycle through a loop that is the size of the ship
				// we will place the ship in its initial position
				// then we will find what direction we are going with the ship, and increment or decrement the coordinates until the ship length has been reached
				for(int y = 0; y < shipArray[x].getLength(); y++) {
					shipLayer.put(xy, shipArray[x]);
					if(direction == 0) {
						Integer[] newXY = {xCoord,yCoord++};
						shipLayer.put(newXY, shipArray[x]);
					}
					else if(direction == 1) {
						Integer[] newXY = {xCoord++,yCoord};
						shipLayer.put(newXY, shipArray[x]);
					}
					else if(direction == 2) {
						Integer[] newXY = {xCoord,yCoord--};
						shipLayer.put(newXY, shipArray[x]);
					}
					else {
						Integer[] newXY = {xCoord--,yCoord};
						shipLayer.put(newXY, shipArray[x]);
					}
					
				}
				
			}

		}
	}
}
