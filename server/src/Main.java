import connection.ConnListener;

import java.io.IOException;

public class Main {
    private static final int port = 5555;


    //TODO IMPROTANT: When server is closed (either with a command or by force closing) a message should be sent
    //to the clients(https://github.com/desertcod98/OakClient/blob/main/src/main/java/me/leeeaf/oakclient/OakClient.java)
    public static void main(String[] args) {
        System.out.println("Starting server on port "+port);
        ConnListener listener = new ConnListener(port);
        try {
            listener.listen();
        } catch (IOException e) {
            e.printStackTrace(); //TODO handle?
        }
    }
}