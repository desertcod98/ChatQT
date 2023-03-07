import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        new Socket("127.0.0.1", 5555);
    }
}