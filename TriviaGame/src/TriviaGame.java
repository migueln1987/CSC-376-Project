import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
		//int count = 0;
		
		//while(count < 10){
			QuestionManager questionManager = new QuestionManager("questions.csv");
			while (winner == null){
				List <String> shuffle = new ArrayList<String>();
				Question question = questionManager.getQuestion();
				output1.println(question.getQuestion());
				output2.println(question.getQuestion());
				String right = question.getRightAnswer();
				
				shuffle.add(question.getOption1().toString());
				shuffle.add(question.getOption2().toString());
				shuffle.add(question.getOption3().toString());
				shuffle.add(question.getOption4().toString());
				
				Collections.shuffle(shuffle);
				
				strbld.append(shuffle.get(0).toString() + " " + "," +
					  shuffle.get(1).toString() + " " + "," +
				      shuffle.get(2).toString() + " " + "," +
				      shuffle.get(3).toString() + " ");
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
				String player1_an = response1[0];
				String player2_an = response2[0];
			
				System.out.println("player one responds: " + player1_an + "\n"
						+ "player two responds: " + player2_an );
				System.out.println("winner: " + winner);
				
			}
			
			//count++;
		//}
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

