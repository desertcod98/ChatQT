import connection.ActiveConnections;
import connection.ConnListener;

import java.io.IOException;
import java.util.Scanner;

public class Main {



    //TODO IMPROTANT: When server is closed (either with a command or by force closing) a message should be sent
    //to the clients(https://github.com/desertcod98/OakClient/blob/main/src/main/java/me/leeeaf/oakclient/OakClient.java)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer port = null;
        do{
            System.out.print("Insert port: ");
            String input = scanner.nextLine();
            try{
                port = Integer.parseInt(input);
            }catch(Exception e){
                System.out.println("Insert a number!");
            }
        }while(port==null);

        System.out.println("Starting server on port "+port);
        ConnListener listener = new ConnListener(port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ActiveConnections.getInstance().closeAllConnections();
        }));

        try {
            listener.listen();
        } catch (IOException e) {
            e.printStackTrace(); //TODO handle?
        }
    }
}