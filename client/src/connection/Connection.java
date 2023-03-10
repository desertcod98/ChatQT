package connection;

import io.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private String username;
    private BufferedReader in;
    private PrintWriter out;

    public Connection(String address, int port, String username) throws IOException {
        socket = new Socket(address, port);
        this.username = username;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        sendUsername();
    }

    private void sendUsername(){
        out.println(username);
    }

    public void sendMessage(String message){
        out.println(message);
    }

    public String getMessage() throws IOException {
        return in.readLine();
    }

    public boolean isActive(){
        return !socket.isClosed();
    }

    public void shutdown(){
        try {
            socket.close();
        } catch (IOException e) {
            Logger.error("Failed to close socket "+socket.getInetAddress().getHostAddress());
        }
    }
}
