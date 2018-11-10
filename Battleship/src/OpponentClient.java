import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class OpponentClient extends Opponent {
    private Socket server;
    
    public OpponentClient(InetAddress addr) throws IOException {
        server = new Socket(addr, PORT);
        
        handshake(server);
    }
}
