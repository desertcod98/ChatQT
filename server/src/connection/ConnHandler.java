package connection;

import io.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnHandler implements Runnable{
    private Socket clientSocket;
    private String clientUsername;
    private DataOutputStream dataOutputStream;

    public ConnHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        ActiveConnections.getInstance().addConnection(this);
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("run works");
    } //TODO first message should be username

    public void shutdown(){
        ActiveConnections.getInstance().removeConnection(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            Logger.error("Failed to close socket "+clientSocket.getInetAddress().getHostAddress());
        }
    }

    public String getClientUsername(){
        return clientUsername;
    }
}
