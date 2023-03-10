package protocol.commands;

import connection.ActiveConnections;
import connection.ConnHandler;
import protocol.Command;

public class Users extends Command {

    public Users() {
        super("USERS");
    }

    public String execute() {
        StringBuilder result = new StringBuilder();
        result.append("--ONLINE USERS--");

        //add users to result
        ActiveConnections.getInstance().getActiveConnections().forEach((connection) -> {
            result.append("\n").append(connection.getClientUsername());
        });
        return result.toString();
    }
}
