package protocol.commands;

import connection.ActiveConnections;
import connection.ConnHandler;
import protocol.Command;

public class Users extends Command {

    protected Users() {
        super("USERS");
    }

    public String execute(){
        StringBuilder users = new StringBuilder();
        for (ConnHandler connHandler : ActiveConnections.getInstance().getActiveConnections()){
            users.append(connHandler.getClientUsername()).append("\n");
        }
        return users.toString();
    }
}
