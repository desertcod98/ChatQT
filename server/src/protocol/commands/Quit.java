package protocol.commands;

import connection.ActiveConnections;
import connection.ConnHandler;
import protocol.Command;
import protocol.CommandResult;
import protocol.ResultType;

public class Quit extends Command {
    public Quit() {
        super("quit");
    }

    public CommandResult execute(ConnHandler commander, String[] args) {
        commander.shutdown();
        return new CommandResult(ResultType.COMPLETED);
    }

    @Override
    protected String helpMessage() {
        return "Disconnect from server";
    }
}
