import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Opponent {
    public static final int PORT = 25555;
    protected PrintWriter out;
    protected BufferedReader in;
    
    protected void handshake(Socket sock) throws IOException {
        out = new PrintWriter(sock.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        
        out.println("hello");
        if (!in.readLine().equals("hello"))
            throw new IOException();
    }
    
	public Move waitMove() throws IOException {
        String input = in.readLine();
        if (input.equals("loss"))
            return new Move(0, 0, Move.Type.LOSS);
        
        String[] tokens = input.split(" ");
        return new Move(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Move.Type.SHOT);
	}
	
	public boolean reportMove(Move move) throws IOException {
        out.printf("move %d %d\n", move.getCoords()[0], move.getCoords()[1]);
        
        String input = in.readLine();
        return input.equals("hit");
	}
	
	public void reportHit(boolean hit) {
	    out.println((hit) ? "hit" : "miss");
	}
	
	public void reportLoss() {
	    out.println("loss");
	}
	
	public void reportReady() throws IOException {
	    out.println("ready");
        if (!in.readLine().equals("ready"))
            throw new IOException();
    }
}
