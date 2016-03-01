import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TriviaGame implements Runnable {
	Socket player1, player2;
	private BufferedReader input1, input2;
	private PrintWriter output1, output2;
	
	public HashMap<String, ArrayList<String>> questions;
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
	
	/**
	 * for testing... Actually game will have more complex questions
	 * @throws IOException 
	 */
	public void beginGame() throws IOException
	{
		String winner;
		createQuestion();
		StringBuilder strbld = new StringBuilder();
//		for (String question : questions.keySet())
//		{
		
		Questions question = new Questions();
		output1.println(question.getQuestion());
		output2.println(question.getQuestion());
		String right = question.getOption1();
			
//			Collections.shuffle(questions.get(question));

//			for (String option : questions.get(question))
//			{
			strbld.append(question.getOption1().toString() + " " + 
						  question.getOption2().toString() + " " + 
					      question.getOption3().toString() + " " +
					      question.getOption4().toString() + " ");
//			}
			output1.println(strbld.toString());
			output2.println(strbld.toString());
			
			String[] response1 = input1.readLine().split(",");
			String[] response2 = input2.readLine().split(",");
		
			if (Integer.valueOf(response1[1]) < Integer.valueOf(response2[1]) )
			{
				if (response1[0].equals(right)) winner = "player1";
				
				else if (response2[0].equals(right)) winner = "player2";
				
				else winner = "both wrong";
			}
			else
			{
				if (response2[0].equals(right)) winner = "player1";
				
				else if (response1[0].equals(right)) winner = "player2";
				
				else winner = "both wrong";
				
			}
			String player1_an = response1[0];
			String player2_an = response2[0];
			
			System.out.println("player one says: " + player2_an + "\n"
					+ "player two says: " + player1_an);
			
			System.out.println("winner: " + winner);
//		}
		
		
	}
	
	/**
	 * placeholder for the questions class
	 */
	public void createQuestion()
	{
		questions = new HashMap<String, ArrayList<String>>();
		String q = "What company created Java?";
		ArrayList<String> options = new ArrayList<String>();
		options.add("Sun Microsystems");
		options.add("Oracle");
		options.add("Microsoft");
		options.add("Google");
		
		questions.put(q, options);
		
	}
	@Override
	public void run() {
		try {
			beginGame();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

