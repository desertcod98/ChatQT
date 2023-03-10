package connection;

import io.Logger;
import protocol.CommandRegistry;

import java.io.*;
import java.net.Socket;

public class ConnHandler implements Runnable{
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ConnHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        ActiveConnections.getInstance().addConnection(this);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            username = in.readLine();
            Logger.notify(String.format("%s (%s) %s", getClientUsername(), getIpString(), "connected"));
            ActiveConnections.getInstance().broadcast(getClientUsername() + " connected");
        } catch (IOException e) {
            Logger.error("Could not get username of "+clientSocket.getInetAddress().getHostAddress());
            shutdown();
        }

        while (true){
            try {
                String message = in.readLine();
                if(message == null){
                    shutdown();
                    break;
                }
                if(message.startsWith("/")) {
                    CommandRegistry.getInstance().tryExecute(message);
                }else{
                    ActiveConnections.getInstance().broadcast(String.format("[%s] %s", username, message));
                }
            } catch (IOException e) {
                Logger.error(String.format("I/O error when reading message from %s (%s)", getIpString(), getClientUsername()));
                shutdown();
                break;
            }
        }

    }

    public void sendMessage(String message){
        out.println(message);
    }

    public void shutdown(){
        ActiveConnections.getInstance().removeConnection(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            Logger.error("Failed to close socket "+clientSocket.getInetAddress().getHostAddress());
        }
    }

    public String getClientUsername(){
        return username;
    }

    public String getIpString(){
        return clientSocket.getInetAddress().getHostAddress();
    }
}
