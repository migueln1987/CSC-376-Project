
public class ScoreBoard {
	
	String player1;
	String player2;
	int player1Score;
	int player2Score;
	
	public ScoreBoard(String player1, String player2){
		this.player1 = player1;
		this.player2 = player2;
		player1Score = 0;
		player2Score = 0;
	}
	
	public void addPointsPlayer1(int points){
		player1Score += points;
	}
	
	public void addPointsPlayer2(int points){
		player2Score += points;
	}
	
	public int player1Score(){
		return player1Score;
	}
	
	public int player2Score(){
		return player2Score;
	}

}
