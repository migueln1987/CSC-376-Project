import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TriviaClient implements Runnable{
	
	static BufferedReader in;
	static PrintStream ps;
	static BufferedReader stdIn;

	public static void main(String[] args) {
		int port = 6001;
		InetAddress loopback = InetAddress.getLoopbackAddress();
		
		try {
			Socket clientSocket = new Socket(loopback, port);
			System.out.println("Connected...");
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			ps = new PrintStream(clientSocket.getOutputStream());
			System.out.println(in.readLine());
			String input;
			Thread readThread = new Thread(new TriviaClient());
			readThread.start();
			while ((input = stdIn.readLine()) != null)
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


	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		String fromServer;
		try {
			while ((fromServer = in.readLine()) != null) {
				System.out.println(fromServer);
			}
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
}
