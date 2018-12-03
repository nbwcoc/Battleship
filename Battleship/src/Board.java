import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

public class Board {
    private HashMap<Coords, Ship> shipLayer = new HashMap<>();
    private HashMap<Coords, HitMarker> hitLayer = new HashMap<>();

    public boolean addHit(int x, int y) {
        var xy = new Coords(x, y);

        if (hitLayer.containsKey(xy))
            return false;

        Ship hitShip = shipLayer.get(xy);
        if (hitShip != null)
            hitShip.hit();

        hitLayer.put(xy, new HitMarker(hitShip != null));

        return true;
    }

    public void placeShips(Ship[] shipArray) {
    	int xCoord;
    	int yCoord;
    	int direction;
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
        // run our loop for the amount of ships in the ship array (5)
		for (int x = 0; x < shipArray.length; x++) {
			/*
			 * create an x and y pairing for the initial placement of the ship direction
			 * will be used for ship placement. 0 = North, 1 = East, 2 = South, 3 = West
			 */

			xCoord = rand.nextInt(10) + 1;
			yCoord = rand.nextInt(10) + 1;
			direction = rand.nextInt(4);
			var xy = new Coords(xCoord, yCoord);
			/*
			 * cycle through a loop that is the size of the ship we will place the ship in
			 * its initial position then we will find what direction we are going with the
			 * ship, and increment or decrement the coordinates until the ship length has
			 * been reached
			 */
			if (checkIfValid(shipArray[x], xCoord, yCoord, direction) && checkBounds(shipArray[x], xCoord, yCoord, direction)) {
				for (int y = 0; y < shipArray[x].getLength(); y++) {
					// if this specific coordinate on the board does not exist in
					// the hashmap and it is within the bounds, then we will place the ship
					shipLayer.put(xy, shipArray[x]);
					// successfully placed a piece of the ship, increment
					if (direction == 0) {
						xy = xy.incrementY();
					} else if (direction == 1) {
						xy = xy.incrementX();
					} else if (direction == 2) {
						xy = xy.decrementY();
					} else {
						xy = xy.decrementX();
					}
				}
			} else {
				// validation check did not succeed, so we need to lower the x increment to make up for the ship not being placed
				x--;
			}
		}
    }

    // this will make sure that our ship placement will not go out of the bounds of the board
    private boolean checkBounds(Ship ship, int xCoord, int yCoord,int direction) {
        if (ship.getLength() + yCoord <= 10 && direction == 0)
            return true;
        else if (ship.getLength() + xCoord <= 10 && direction == 1)
            return true;
        else if (yCoord - ship.getLength() >= 0 && direction == 2)
            return true;
        else if (xCoord - ship.getLength() >= 0 && direction == 3)
            return true;
        else
            return false;
    }
    
	private boolean checkIfValid(Ship ship, int xCoord, int yCoord, int direction) {
		var xy = new Coords(xCoord, yCoord);
		boolean isValid = false;
		// go length of ship
		// if shipLayer doesn't have the coords on first spot, increment
		// as soon as it finds an invalid coord, break out of loop, return false
		// process to select a new spot for the ship will begin
		for (int x = 0; x < ship.getLength(); x++) {
			if (direction == 0) {
				if (!shipLayer.containsKey(xy)) {
					isValid = true;
					xy = xy.incrementY();
				} else {
					isValid = false;
					break;
				}
			} else if (direction == 1) {
				if (!shipLayer.containsKey(xy)) {
					isValid = true;
					xy = xy.incrementX();
				} else {
					isValid = false;
					break;
				}
			} else if (direction == 2) {
				if (!shipLayer.containsKey(xy)) {
					isValid = true;
					xy = xy.decrementY();
				} else {
					isValid = false;
					break;
				}
			} else {
				if (!shipLayer.containsKey(xy)) {
					isValid = true;
					xy = xy.decrementX();
				} else {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	public void displayBoard() {
		int height = 11;
		int width = 11;
		StringBuilder grid = new StringBuilder(height * (width + 1)); 
			
		System.out.println(shipLayer.entrySet());
		
		for (int row = 1; row < height; row++) {
			for (int column = 1; column < width; column++) {
				if (shipLayer.containsKey(new Coords(column, row))) {
					grid.append(" " + shipLayer.get(new Coords(column, row)).toString() + " ");
				} else {
					grid.append(" # ");
				}
			}
			// next row
			grid.append('\n'); 
		}
		System.out.println(grid.toString());
	}

	public static void main(String Args[]) {
		Board b = new Board();
		Ship[] ships = new Ship[5];
		b.placeShips(ships);
		b.displayBoard();
	}
}
