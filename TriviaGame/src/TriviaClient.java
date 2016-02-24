import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TriviaClient {

	public static void main(String[] args) {
		int port = 6001;
		InetAddress loopback = InetAddress.getLoopbackAddress();
		
		try {
			Socket clientSocket = new Socket(loopback, port);
			System.out.println("Connected...");
		} catch (UnknownHostException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
