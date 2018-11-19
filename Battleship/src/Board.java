import java.util.HashMap;
import java.util.Random;

public class Board {
	private HashMap<Integer[], Ship> shipLayer = new HashMap<>();
	private HashMap<Integer[], HitMarker> hitLayer = new HashMap<>();

	public boolean addHit(int x, int y) {
		Integer[] xy = { x, y };

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
		int shipCounter;
		// run our loop for the amount of ships in the ship array (5)
		for (int x = 0; x < shipArray.length; x++) {
			/* create an x and y pairing for the initial placement of the ship
			direction will be used for ship placement. 0 = North, 1 = East, 2 = South, 3 = West */

			int xCoord = rand.nextInt(10) + 1;
			int yCoord = rand.nextInt(10) + 1;
			int direction = rand.nextInt(4);
			Integer[] xy = { xCoord, yCoord };
			shipCounter = 0;
			/*cycle through a loop that is the size of the ship
			we will place the ship in its initial position
			then we will find what direction we are going with the ship, and increment or decrement the coordinates until the ship length has been reached */
			while(shipCounter < shipArray[x].getLength()) {
				// if this specific coordinate on the board does not exist in the hashmap and it is within the bounds, then we will place the ship
				if (!shipLayer.containsKey(xy) && checkBounds(shipArray[x],xCoord,yCoord,direction)) {
					shipLayer.put(xy, shipArray[x]);
					// successfully placed a ship, increment
					shipCounter++;
					if (direction == 0) {
						Integer[] newXY = { xCoord, yCoord++ };
						xy = newXY;
					} else if (direction == 1) {
						Integer[] newXY = { xCoord++, yCoord };
						xy = newXY;
					} else if (direction == 2) {
						Integer[] newXY = { xCoord, yCoord-- };
						xy = newXY;
					} else {
						Integer[] newXY = { xCoord--, yCoord };
						xy = newXY;
					}
				}
				else {
					// if we can not place a ship, create new coordinates and direction
					xCoord = rand.nextInt(10) + 1;
					yCoord = rand.nextInt(10) + 1;
					direction = rand.nextInt(4);
					Integer[] newXY = { xCoord, yCoord };
					xy = newXY;
				}

			}
		}
		// shipLayer.forEach((key, value) -> System.out.println(key.toString() + ":" + value.toString()));
	}
	
	// this will make sure that our ship placement will not go out of the bounds of the board
	private boolean checkBounds(Ship ship, int xCoord, int yCoord, int direction) {
		if(ship.getLength() + yCoord <= 10 && direction == 0)
			return true;
		else if(ship.getLength() + xCoord <= 10 && direction == 1)
			return true;
		else if(ship.getLength() - yCoord >= 0 && direction == 2)
			return true;
		else if(ship.getLength() - xCoord >= 0 && direction == 3)
			return true;
		else
			return false;
	}
}
