package io;

public class Logger {
    public static void error(String message){
        System.out.println("[ERROR] "+message);
    }

    public static void notify(String message){
        System.out.println(/*"[!] "+*/message);
    }
}
