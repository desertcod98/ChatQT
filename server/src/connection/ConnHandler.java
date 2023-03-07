package connection;

import java.net.Socket;

public class ConnHandler implements Runnable{
    private Socket clientSocket;

    public ConnHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("run works");
    }
}
