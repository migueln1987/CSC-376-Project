import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
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
//		ScoreBoard triviaScore = new ScoreBoard();
		String winner = null;
		StringBuilder strbld = new StringBuilder();

		QuestionManager questionManager = new QuestionManager("questions.csv");
		while (winner == null){
		Question question = questionManager.getQuestion();
		output1.println(question.getQuestion());
		output2.println(question.getQuestion());
		String right = question.getRightAnswer();
			
		strbld.append(question.getOption1().toString() + " " + 
					  question.getOption2().toString() + " " + 
				      question.getOption3().toString() + " " +
				      question.getOption4().toString() + " ");
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
				if (response2[0].equals(right)) winner = "player2";
				
				else if (response1[0].equals(right)) winner = "player1";
				
				else winner = "both wrong";
				
			}
			System.out.println("player one says: " + response1.toString() + "\n"
					+ "player two says: " + response2.toString());
		}
			
			System.out.println("winner: " + winner);		
		
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

