package connection;

import io.Logger;

import java.io.IOException;
import java.net.Socket;

public class ConnHandler implements Runnable{
    private Socket clientSocket;

    public ConnHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        ActiveConnections.getInstance().addConnection(this);
    }

    @Override
    public void run() {
        System.out.println("run works");
    }

    public void shutdown(){
        ActiveConnections.getInstance().removeConnection(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            Logger.error("Failed to close socket "+clientSocket.getInetAddress().getHostAddress());
        }
    }
}
