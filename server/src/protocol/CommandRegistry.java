package protocol;

import connection.ConnHandler;
import protocol.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class CommandRegistry {
    private static CommandRegistry instance;
    private static ArrayList<Command> commands = new ArrayList<>();

    private CommandRegistry(){
        fillCommands();
    }

    public static CommandRegistry getInstance(){
        if (instance == null){
            instance = new CommandRegistry();
        }
        return instance;
    }

    public CommandResult tryExecute(ConnHandler commander, String commandMessage){
        String[] commandSplit = commandMessage.substring(1).split(" ");
        Optional<Command> optionalCommand = getCommandByKeyword(commandSplit[0]);
        if(optionalCommand.isPresent()){
            Command command = optionalCommand.get();
            String[] args = Arrays.copyOfRange(commandSplit, 1,commandSplit.length); //TODO big issue, in private message the second arg has spaces
            return command.execute(commander, args);
        }
        return new CommandResult(ResultType.NOT_FOUND);
    }

    private Optional<Command> getCommandByKeyword(String keyword){
        for (Command command : commands){
            if(command.getKeyword().equalsIgnoreCase(keyword)){
                return Optional.of(command);
            }
        }
        return Optional.empty();
    }


    private void fillCommands(){
        commands.add(new Users());
        commands.add(new PrivateMessage());
        commands.add(new Quit());

    }
}
