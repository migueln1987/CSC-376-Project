import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class TriviaGame implements Runnable {
	Socket player1, player2;
	int score1, score2;
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
	

	public void beginGame() throws IOException
	{
		ScoreBoard triviaScore = new ScoreBoard(player1, player2);
		String winner = null;
		StringBuilder strbld = new StringBuilder();
		int count = 0;
		QuestionManager questionManager = new QuestionManager("questions.csv");
		
		while (count < 10){
	
		Question question = questionManager.getQuestion();
		output1.println(question.getQuestion());
		output2.println(question.getQuestion());
		String right = question.getRightAnswer();
			
		strbld.append(question.getOption1().toString() + "\n" +
					  question.getOption2().toString() + "\n" +
				      question.getOption3().toString() + "\n" +
				      question.getOption4().toString() + "\n");
			output1.println(strbld.toString());
			output2.println(strbld.toString());
			
			String[] response1 = input1.readLine().split(",");
			String[] response2 = input2.readLine().split(",");
			

			
			if (Integer.valueOf(response1[1]) < Integer.valueOf(response2[1]) )
			{
				if (response1[0].toLowerCase().charAt(0) == right.charAt(0)) {
					winner = "player1";
					triviaScore.addPointPlayer1();
				}
				
				else if (response2[0].toLowerCase().charAt(0) == right.charAt(0)){
					winner = "player2";
					triviaScore.addPointPlayer2();
				}
				
				else winner = "both wrong";
			}
			else
			{
				if (response2[0].toLowerCase().charAt(0) == right.charAt(0)) {
					winner = "player2";
					triviaScore.addPointPlayer2();
				}
				
				else if (response1[0].toLowerCase().charAt(0) == right.charAt(0)) {
					winner = "player1";
					triviaScore.addPointPlayer1();
				}
				
				else winner = "both wrong";
				
			}
			System.out.println("player one says: " + response1[0] + "\n"
					+ "player two says: " + response2[0]);
			
			
			output1.println("Correct Answer: " + right + ". Your Score: " + triviaScore.player1Score());
			output2.println("Correct Answer: " + right + ". Your Score: " + triviaScore.player2Score);
			strbld = new StringBuilder();
			count++;
			
		}
			if (triviaScore.player1Score() > triviaScore.player2Score())
			{
				output1.println("you win, congrats you're smarter than your opponent.\n"
						+ " Your Final Score: " + triviaScore.player1Score() + "/n"
						+  "Opponents Final Score: " + triviaScore.player2Score()); 
						
				output2.println("You Lose, Better Luck Next Time.\n"
						+ " Your Final Score: " + triviaScore.player2Score() + "/n"
						+  "Opponents Final Score: " + triviaScore.player1Score());
				System.out.println("winner: " + winner);	
			}
			
			if (triviaScore.player2Score() > triviaScore.player1Score())
			{
				output2.println("you win, congrats you're smarter than your opponent.\n"
						+ " Your Final Score: " + triviaScore.player1Score() + "/n"
						+  "Opponents Final Score: " + triviaScore.player2Score()); 
						
				output1.println("You Lose, Better Luck Next Time.\n"
						+ " Your Final Score: " + triviaScore.player2Score() + "/n"
						+  "Opponents Final Score: " + triviaScore.player1Score());
				System.out.println("winner: " + winner);	
			}
			else
			{
				output2.println("Tie\n"
						+ " Your Final Score: " + score2 + "/n"
						+  "Opponents Final Score: " + score1);
				output1.println("Tie\n"
						+ " Your Final Score: " + score2 + "/n"
						+  "Opponents Final Score: " + score1);
				//System.out.println("winner: " + "Tie");	
			}
			//System.out.println("winner: " + "Tie");		
		
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

