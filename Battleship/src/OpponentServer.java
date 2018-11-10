import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OpponentServer extends Opponent {
    private final int PORT = 25555;
    private ServerSocket socket;
    private Socket client;

    // blocks until client connects
    public OpponentServer() throws IOException {
        socket = new ServerSocket(PORT);
        client = socket.accept();
        
        handshake(client);
    }
}
