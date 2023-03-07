package connection;

import java.io.IOException;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private String username;
    public Connection(String address, int port, String username) throws IOException {
        socket = new Socket(address, port);
        this.username = username;
    }

}
