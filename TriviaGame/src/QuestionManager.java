import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {

	private String[] csvLine;
	private ArrayList<Question> questions;
	private ArrayList<Question> extraQuestions = new ArrayList<>();
	private Random rand = new Random();

	public QuestionManager(String filename){
		readCSV(filename);		
	}
	
	public Question getQuestion(){
		int questionNum = rand.nextInt(questions.size() - 1);
		Question questionToGet = questions.get(questionNum);
		questions.remove(questionNum);
		extraQuestions.add(questionToGet);
		return questionToGet;
	}
	
	private void readCSV(String filename){
		questions = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = null;
			while ((line = br.readLine()) != null) {
				  csvLine = line.split(",");
				  Question question = new Question(csvLine);
				  questions.add(question);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
