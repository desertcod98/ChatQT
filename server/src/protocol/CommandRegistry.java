package protocol;

import java.util.ArrayList;
import java.util.Optional;

public class CommandRegistry {
    private static CommandRegistry instance;
    private static ArrayList<Command> commands = new ArrayList<>();

    private CommandRegistry(){}

    public static CommandRegistry getInstance(){
        if (instance == null){
            instance = new CommandRegistry();
        }
        return instance;
    }

    public Optional<String> tryExecute(String command){

        return Optional.empty();
    }
}
