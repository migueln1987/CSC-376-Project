import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class TriviaGame implements Runnable {
	Socket player1, player2;
	private BufferedReader input1, input2;
	private PrintWriter output1, output2;
	
	
	public TriviaGame(ArrayList<Socket> clients) throws IOException
	{
		player1 = clients.get(0);
		input1 = new BufferedReader( 
				 new InputStreamReader(player1.getInputStream()) );
		output1= new PrintWriter( player1.getOutputStream(), true );
		
		
		player2 = clients.get(1);
		input2 = new BufferedReader( 
				 new InputStreamReader(player2.getInputStream()) );
		output2 = new PrintWriter( player2.getOutputStream(), true );
	}
	
	public void beginGame()
	{
		output1.println("player 1 entered game");
		output2.println("player 2 entered game");
	}

	@Override
	public void run() {
		beginGame();
		
	}
}

