package io;

public class Logger {
    public static void error(String message){
        System.out.println("[ERROR] "+message);
    }

    public static void showCommands(){
        System.out.println("""
                Available commands:
                /users : shows online users
                /m [user] [message] : sends a private message [message] to a specified user [user]
                /quit : closes connection to server""");
    }
}
