package protocol.commands;

import connection.ActiveConnections;
import connection.ConnHandler;
import protocol.Command;
import protocol.CommandResult;
import protocol.ResultType;

import java.util.Optional;

public class PrivateMessage extends Command {
    public PrivateMessage() {
        super("m"); //TODO implement multiple aliases
    }

    @Override
    public CommandResult execute(ConnHandler commander, String[] args) {
        if(args.length!=2){
            return new CommandResult(ResultType.FAILED, helpMessage());
        }
        String username = args[0];
        String message = args[1];
        Optional<ConnHandler> optionalUser = ActiveConnections.getInstance().getUser(username);
        if(optionalUser.isPresent()){
            ConnHandler user = optionalUser.get();
            user.sendMessage(String.format("{%s} %s",commander.getClientUsername(), message));
        }
        return new CommandResult(ResultType.COMPLETED);
    }

    @Override
    protected String helpMessage() {
        return "Send a private message to another user\n" +
                "Usage: /m [user] [message]";
    }


}
