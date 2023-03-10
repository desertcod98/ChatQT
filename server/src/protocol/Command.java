package protocol;

import connection.ConnHandler;

public abstract class Command{
    private final String keyword;

    public Command(String keyword) {
        this.keyword = keyword;
    }

    public abstract CommandResult execute(ConnHandler commander, String[] args);

    public String getKeyword(){
        return keyword;
    }

    protected abstract String helpMessage();
}
