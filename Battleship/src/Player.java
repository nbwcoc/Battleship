import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

public class Player {
	private Opponent opponent;
	private HitsBoard hitBoard = new HitsBoard();
	private Board shipBoard = new Board();
	private Ship[] ships = new Ship[5];
	private boolean isHost = false;
	
	private boolean host(String x) {
	    try {
            System.out.printf("Now listening on port %d\n", Opponent.PORT);
            opponent = new OpponentServer();
        } catch (IOException e) {
            System.err.println("failed to start and connect");
            return false;
        }
	    isHost = true;
        return true;
	}
	
	private boolean join(String ip) {
	    if (!ip.matches("^((((\\d{1,3}\\.)){3}\\d{1,3})|(([\\da-f]{0,4}:){5}[\\da-f]{0,4}))$")) {
            System.out.println("Invalid IP");
            return false;
        }
        
        try {
            opponent = new OpponentClient(InetAddress.getByName(ip));
        } catch (IOException e) {
            System.out.println("failed to connect");
            return false;
        }
        
        return true;
	}
	
	private boolean invalidCommand(String x) {
	    System.out.println("invalid command");
        return false;
	}
	
	private void start() {
	    HashMap<String, Function<String, Boolean>> commands = new HashMap<>();
	    commands.put("host", this::host);
	    commands.put("join", this::join);
	    
	    System.out.print("BATTLESHIP\nhost or join\n>");
	    while (true) {
	        Scanner s = new Scanner(System.in);
	        String[] argv = s.nextLine().split(" ");
	        Function<String, Boolean> cmd = commands.getOrDefault(argv[0], this::invalidCommand);
	        if (cmd.apply((argv.length > 1) ? argv[1] : ""))
	                break;
	    }
	}
	
	private void shoot() {
	    System.out.print("Enter coords (x y)");
	    var s = new Scanner(System.in);
        int coords[] = new int[2];
        
	    // I'm getting flashbacks to using GOTOs in BASIC
	    while (true) {
	        System.out.print("> ");
	        var rawCoords = s.nextLine().split("[ ,]");
	        
	        if (rawCoords.length != 2) {
	            System.out.println("Invalid length");
	            continue;
	        }

	        boolean haveValidCoords = true;
	        
	        for (int i = 0; i < 2; i++) {
	            try {
	                coords[i] = Integer.parseInt(rawCoords[i]);
	                assert coords[i] <= 10 && coords[i] > 0; 
	            } catch (Exception e) {
	                System.out.println("Invalid input");
	                haveValidCoords = false;
	                break;
	            }
	        }
	        
	        if (!haveValidCoords)
	            continue;
	        
	        if (hitBoard.tryHit(coords[0], coords[1]) == null) {
	            System.out.println("You already tried there");
	            continue;
	        }
	        
	        try {
	            opponent.reportMove(new Move(coords[0], coords[1], Move.Type.SHOT));
	        } catch (IOException e) {
	            System.out.println("Something happened :(");
	        }
	        
	        break;
	    }
	}
	
	private void mainLoop() {
	    while (true) {
	        shoot();
	    }
	}
	
	public static void main(String[] args) {
	    Player player = new Player();
	    
	    player.start();
	    player.mainLoop();
	    
	    System.out.println("\"That's all Folks!\"");
	}
}
