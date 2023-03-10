package protocol;

public abstract class Command{
    private final String keyword;

    public Command(String keyword) {
        this.keyword = keyword;
    }

    public abstract String execute();

    public String getKeyword(){
        return keyword;
    }
}
