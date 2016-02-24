import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			BufferedReader in = new BufferedReader( 
					 new InputStreamReader(clientSocket.getInputStream()) );
			
			System.out.println(in.readLine());
			String input;
			while ((input = in.readLine()) != null)
			{
				System.out.println(input);
			}
			/*
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			*/
			clientSocket.close();
			
		} catch (UnknownHostException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
