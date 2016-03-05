import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class TriviaGame implements Runnable {
	Socket player1, player2;
	int score1, score2;
	private BufferedReader input1, input2;
	private PrintWriter output1, output2;
	
//	public HashMap<String, ArrayList<String>> questions;
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
				if (response1[0].charAt(0) == right.charAt(0)) {
					winner = "player1";
					triviaScore.addPointPlayer1();
//					score1++;
				}
				
				else if (response2[0].charAt(0) == right.charAt(0)){
					winner = "player2";
					triviaScore.addPointPlayer2();
//					score2++;
				}
				
				else winner = "both wrong";
			}
			else
			{
				if (response2[0].charAt(0) == right.charAt(0)) {
					winner = "player2";
					triviaScore.addPointPlayer2();
//					score2++;
				}
				
				else if (response1[0].charAt(0) == right.charAt(0)) {
					winner = "player1";
					triviaScore.addPointPlayer1();
//					score1++;
				}
				
				else winner = "both wrong";
				
			}
			System.out.println("player one says: " + response1[0] + "\n"
					+ "player two says: " + response2[0]);
			
			
			output1.println("Correct Answer: " + right + ". Your Score: " + triviaScore.player1Score());//score1);
			output2.println("Correct Answer: " + right + ". Your Score: " + triviaScore.player2Score);//score2);
			/*
			if (winner.equals("player1"))
			{
				output1.println("you (player1) ansewered: " + response1[0] + "in " + response1[1] + " seconds"+ "\n" 
						+ "player2 ansewered: " + response2[0] + " in " + response2[1] + " seconds" + "\n"
						+ "YOU WIN THE ROUND! YOUR CURRENT SCORE IS " + score1);
						
				output2.println("you (player2) ansewered: " + response2[0] + "in " + response2[1] + " seconds"+ "\n" 
						+ "player1 ansewered: " + response1[0] + " in " + response1[1] + " seconds" + "\n"
						+ "YOU LOSE THE ROUND! YOUR CURRENT SCORE IS " + score2);
			}
			else if (winner.equals("player2"))
			{
				output1.println("you (player1) ansewered: " + response1[0] + " in " + response1[1] + " seconds"+ "\n" 
						+ "player2 ansewered: " + response2[0] + " in " + response2[1] + " seconds" + "\n"
						+ "YOU LOSE THE ROUND! YOUR CURRENT SCORE IS " + score1);
						
				output2.println("you (player2) ansewered: " + response2[0] + "in " + response2[1] + " seconds"+ "\n" 
						+ "player1 ansewered: " + response1[0] + "in " + response1[1] + " seconds" + "\n"
						+ "YOU WIN THE ROUND! YOUR CURRENT SCORE IS " + score2);
			}
			else 
			{
				output1.println("you (player1) ansewered: " + response1[0] + "in " + response1[1] + " seconds"+ "\n" 
						+ "player2 ansewered: " + response2[0] + "in " + response2[1] + " seconds" + "\n"
						+ "YOU LOSE THE ROUND! YOUR CURRENT SCORE IS " + score1);
						
				output2.println("you (player2) ansewered: " + response2[0] + "in " + response2[1] + " seconds"+ "\n" 
						+ "player1 ansewered: " + response1[0] + "in " + response1[1] + " seconds" + "\n"
						+ "YOU LOSE THE ROUND! YOUR CURRENT SCORE IS " + score2);
			}
			*/
			//output1.flush();
			//output2.flush();
			strbld = new StringBuilder();
			count++;
			
		}
			if (triviaScore.player1Score() > triviaScore.player2Score())
//			if (score1 > score2)
			{
				output1.println("you win, congrats you're smarter than your opponent.\n"
						+ " Your Final Score: " + triviaScore.player1Score() + "/n"
						+  "Opponents Final Score: " + triviaScore.player2Score()); 
						
				output2.println("You Lose, Better Luck Next Time.\n"
						+ " Your Final Score: " + triviaScore.player2Score() + "/n"
						+  "Opponents Final Score: " + triviaScore.player1Score());
//				output1.println("you win, congrats you're smarter than your opponent.\n"
//						+ " Your Final Score: " + score1 + "/n"
//						+  "Opponents Final Score: " + score2); 
//						
//				output2.println("You Lose, Better Luck Next Time.\n"
//						+ " Your Final Score: " + score2 + "/n"
//						+  "Opponents Final Score: " + score1);
			}
			
			if (triviaScore.player2Score() > triviaScore.player1Score())
//			if (score2 > score1)
			{
				output2.println("you win, congrats you're smarter than your opponent.\n"
						+ " Your Final Score: " + triviaScore.player1Score() + "/n"
						+  "Opponents Final Score: " + triviaScore.player2Score()); 
						
				output1.println("You Lose, Better Luck Next Time.\n"
						+ " Your Final Score: " + triviaScore.player2Score() + "/n"
						+  "Opponents Final Score: " + triviaScore.player1Score());
//				output2.println("you win, congrats you're smarter than your opponent.\n"
//						+ " Your Final Score: " + score1 + "/n"
//						+  "Opponents Final Score: " + score2); 
//						
//				output1.println("You Lose, Better Luck Next Time.\n"
//						+ " Your Final Score: " + score2 + "/n"
//						+  "Opponents Final Score: " + score1);
			}
			else
			{
				output2.println("Tie\n"
						+ " Your Final Score: " + score2 + "/n"
						+  "Opponents Final Score: " + score1);
				output1.println("Tie\n"
						+ " Your Final Score: " + score2 + "/n"
						+  "Opponents Final Score: " + score1);
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

