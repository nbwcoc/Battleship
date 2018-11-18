import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A class representing the opponent, and abstracting out interactions with the
 * opponent. Almost all methods throw an IOException on a network error.
 * 
 * General flow with this should be:
 * <pre>
 *  Opponent opponent = new OpponentVariant();
 *  placeShips();
 *  opponent.reportReady();
 *  while (gameNotOver) {
 *      processMove(opponent.waitMove());
 *      reportHit(lastShotHit);
 *      if (noShipsLeft) {
 *          reportLoss();
 *          gameNotOver = false;
 *      }
 *      reportMove(getPlayersMove());
 *  }
 * </pre>
 */
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

    /**
     * Blocks until the opponent has made a move or reports a loss
     * 
     * @return the opponent's move
     * @throws IOException
     */

    public Move waitMove() throws IOException {
        String input = in.readLine();
        if (input.equals("loss"))
            return new Move(0, 0, Move.Type.LOSS);

        String[] tokens = input.split(" ");
        return new Move(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Move.Type.SHOT);
    }

    /**
     * Reports the player's move to the opponent and blocks until the opponent
     * reports whether or not it hit
     * 
     * @param move the player's move. Must be a normal move (type = Move.Type.MOVE)
     * @return whether or not the move hit
     * @throws IOException
     */

    public boolean reportMove(Move move) throws IOException {
        out.printf("move %d %d\n", move.getCoords()[0], move.getCoords()[1]);

        String input = in.readLine();
        return input.equals("hit");
    }

    /**
     * Reports whether or not the opponent's last move was a hit or a miss
     * 
     * @param hit whether or not the opponent hit
     */

    public void reportHit(boolean hit) {
        out.println((hit) ? "hit" : "miss");
    }

    /**
     * Reports that the player has lost.
     */

    public void reportLoss() {
        out.println("loss");
    }

    /**
     * Reports that the player has placed all his ships and is ready to play. Blocks
     * until the opponent reports the same.
     * 
     * @throws IOException
     */

    public void reportReady() throws IOException {
        out.println("ready");
        // TODO: this might not block
        if (!in.readLine().equals("ready"))
            throw new IOException();
    }
}
