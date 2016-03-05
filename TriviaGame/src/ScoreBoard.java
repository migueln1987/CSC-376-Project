import java.net.Socket;

public class ScoreBoard {
	
	Socket player1;
	Socket player2;
	int player1Score;
	int player2Score;
	
	public ScoreBoard(Socket player1, Socket player2){
		this.player1 = player1;
		this.player2 = player2;
		player1Score = 0;
		player2Score = 0;
	}
	
	public void addPointPlayer1(){
		player1Score++;
	}
	
	public void addPointPlayer2(){
		player2Score++;
	}
	
	public int player1Score(){
		return player1Score;
	}
	
	public int player2Score(){
		return player2Score;
	}

}
