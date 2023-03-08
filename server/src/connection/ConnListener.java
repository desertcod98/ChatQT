package connection;

import io.Logger;

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
            ConnHandler connHandler = null;
            try {
                connHandler = new ConnHandler(clientSocket);
            } catch (IOException e) {
                Logger.error("Failed to connect to: "+clientSocket.getInetAddress().getHostAddress());
                e.printStackTrace();
            }
            Thread newConnThread = new Thread(connHandler);
            newConnThread.start();
        }
    }
}
