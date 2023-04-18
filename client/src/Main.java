import connection.Connection;
import io.Logger;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        do {
            System.out.print("Enter server IP and port (format: 127.0.0.1:5555) : ");
            String socketInput = scanner.nextLine();
            try {
                String[] splitSocket = socketInput.split(":");
                int port = Integer.parseInt(splitSocket[1]);
                connection = new Connection(splitSocket[0], port, username);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Insert IP:PORT");
            } catch (NumberFormatException e) {
                System.out.println("Port should be a number!");
            } catch (IOException e) {
                System.out.println("Connection to server failed!\nError message:");
                e.printStackTrace();
            }
        } while (connection == null);

        Logger.showCommands();

        Connection finalConnection = connection; //this is to make the lambda expression happy
        Thread getMessagesThread = new Thread(()->{
            while (finalConnection.isActive()){
                try {
                    String message = finalConnection.getMessage();
                    if(message == null){
                        finalConnection.shutdown();
                        break;
                    }
                    System.out.println(message);
                } catch (IOException e) {
                    finalConnection.shutdown();
                    break;
                }
            }
        });

        getMessagesThread.start();

        while (finalConnection.isActive()){
            String message = scanner.nextLine();
            if(message.equalsIgnoreCase("/quit")) {
                finalConnection.shutdown();
                break;
            }
            finalConnection.sendMessage(message);
        }
    }
}