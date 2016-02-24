import java.io.IOException;
import java.net.Socket;

public class GameServer implements Runnable{

    public static void main(String[] args) throws IOException {

        int portNumber = 6001;
        String host = "localhost";

        Socket playerSocket = new Socket(host, portNumber);
    }

    @Override
    public void run() {

    }
}
