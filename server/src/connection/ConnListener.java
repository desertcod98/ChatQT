package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnListener {
    private int port;

    public ConnListener(int port){
        this.port = port;
    }

    public void listen() throws IOException {
        ServerSocket ss = new ServerSocket(port);
        while (true){
            Socket clientSocket = ss.accept();
            ConnHandler connHandler = new ConnHandler(clientSocket);
            Thread newConnThread = new Thread(connHandler);
            newConnThread.start();
        }
    }
}
