import connection.Connection;
import io.Logger;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        Connection conn = new Connection("127.0.0.1", 5555, username);

        Thread getMessagesThread = new Thread(()->{
            while (conn.isActive()){
                try {
                    String message = conn.getMessage();
                    System.out.println(message);
                } catch (IOException e) {
                    conn.shutdown();
                    break;
                }
            }
        });

        getMessagesThread.start();

        while (true){
            String message = scanner.nextLine();
            if(message.equalsIgnoreCase("/quit")){
                conn.shutdown();
                break;
            }
            conn.sendMessage(message);
        }
    }
}