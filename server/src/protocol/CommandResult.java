package protocol;

import java.util.Optional;


public class CommandResult {
    private final String result;
    private final ResultType type;

    public CommandResult(ResultType type, String result) {
        this.type = type;
        this.result = result;
    }

    public CommandResult(ResultType type) {
        this(type, null);
    }

    public Optional<String> getResult() {
        return Optional.ofNullable(result);
    }

    public ResultType getType() {
        return type;
    }


}