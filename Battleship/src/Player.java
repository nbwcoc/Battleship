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
	
	private boolean host(String x) {
	    try {
            System.out.printf("Now listening on port %d\n", Opponent.PORT);
            opponent = new OpponentServer();
        } catch (IOException e) {
            System.err.println("failed to start and connect");
            return false;
        }
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
	
	public static void main(String[] args) {
	    Player player = new Player();
	    
	    player.start();
	    
	    System.out.println("\"That's all Folks!\"");
	}
}
