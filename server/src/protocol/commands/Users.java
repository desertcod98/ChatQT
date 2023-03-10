package protocol.commands;

import connection.ActiveConnections;
import connection.ConnHandler;
import protocol.Command;
import protocol.CommandResult;
import protocol.ResultType;

public class Users extends Command {

    public Users() {
        super("users");
    }

    public CommandResult execute(ConnHandler commander, String[] args) {
        StringBuilder resultString = new StringBuilder();
        resultString.append("--ONLINE USERS--");

        //add users to result
        ActiveConnections.getInstance().getActiveConnections().forEach((connection) -> {
            resultString.append("\n").append(connection.getClientUsername());
        });
        return new CommandResult(ResultType.COMPLETED, resultString.toString());
    }

    @Override
    protected String helpMessage() {
        return "Show online users";
    }
}
