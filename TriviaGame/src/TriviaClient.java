import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TriviaClient implements Runnable{
	
	BufferedReader in;
	PrintWriter output;
	BufferedReader stdIn;
	Socket clientSocket;
	InetAddress loopback = InetAddress.getLoopbackAddress();
	public TriviaClient() throws IOException
	{
		clientSocket = new Socket(loopback, 6001);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		stdIn = new BufferedReader(new InputStreamReader(System.in));
		output= new PrintWriter( clientSocket.getOutputStream(), true );
		
	}
	
	public void startGame()
	{
		try {
			
			System.out.println("Connected...");
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter output= new PrintWriter( clientSocket.getOutputStream(), true );
			//start game. will be in a loop for multiple questions
			String answer = "nothing";
			
			for (int i = 0; i < 1; i++) {
				System.out.println(in.readLine());

				System.out.println(in.readLine());
		
		
				System.out.println("Enter Response: ");

			
				long d1 = new Date().getTime();
		
				long startTime = System.currentTimeMillis();
				while ((System.currentTimeMillis() - startTime) < 20 * 1000
					&& !stdIn.ready()) {
				}

				if (stdIn.ready()) {
					answer = stdIn.readLine();
					System.out.println("You entered: " + answer);
				
				} 	
				else {
					System.out.println("You did not enter data");
		    
				}
		

				long d2 = new Date().getTime();
				long answer_time = d2 - d1;
				output.println(answer + "," + Integer.toString((int) answer_time));
			}
			
			clientSocket.close();
		
	} catch (UnknownHostException e) {
		System.err.println(e);
		e.printStackTrace();
	} catch (IOException e) {
		System.err.println(e);
		e.printStackTrace();
	}
	}

	public static void main(String[] args) {
		try
		{
		TriviaClient tc = new TriviaClient();
		tc.startGame();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
/*
		int port = 6001;
		InetAddress loopback = InetAddress.getLoopbackAddress();
		
		try {
			Socket clientSocket = new Socket(loopback, port);
			System.out.println("Connected...");
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter output= new PrintWriter( clientSocket.getOutputStream(), true );
			//start game. will be in a loop for multiple questions
			
			System.out.println(in.readLine());

			System.out.println(in.readLine());
			
			
			System.out.println("Enter Response: ");
			// starts timer thread that will sleep 10 seconds
			
			
			long d1 = new Date().getTime();
			
			long startTime = System.currentTimeMillis();
			while ((System.currentTimeMillis() - startTime) < 10 * 1000
			        && !in.ready()) {
			}

			if (in.ready()) {
			    System.out.println("You entered: " + in.readLine());
			} else {
			    System.out.println("You did not enter data");
			}
			

			long d2 = new Date().getTime();
			long answer_time = d2 - d1;
			//output.println(answer + "," + Integer.toString((int) answer_time));
		
			clientSocket.close();
			
		} catch (UnknownHostException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
*/

}
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
