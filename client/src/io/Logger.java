package io;

public class Logger {
    public static void error(String message){
        System.out.println("[ERROR] "+message);
    }

    public static void showCommands(){
        System.out.println("Comandi disponibili:\n" +
                "/users : mostra gli utenti online al momento\n" +
                "/m [user] [message] : manda un messaggio [message] privato a un utente [user]\n" +
                "/quit : chiude la connessione con il server");
    }
}
