package protocol;

public abstract class Command{
    private final String keyword;

    protected Command(String keyword) {
        this.keyword = keyword;
    }

    public abstract String execute();
}
