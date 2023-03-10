package protocol;

import protocol.commands.Users;

import java.util.ArrayList;
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

    public Optional<String> tryExecute(String commandMessage){
        String[] commandParts = commandMessage.substring(1).split(" ");
        Optional<Command> optionalCommand = getCommandByKeyword(commandParts[0]);
        if(optionalCommand.isPresent()){
            Command command = optionalCommand.get();
            return Optional.of(command.execute()); //TODO implement args ecc
        }
        return Optional.empty();
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
    }
}
