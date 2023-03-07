import connection.ConnListener;

import java.io.IOException;

public class Main {
    private static final int port = 5555;

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