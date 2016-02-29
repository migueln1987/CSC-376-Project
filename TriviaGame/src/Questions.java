import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Questions {
	
	static String[] csvLine;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;

	public Questions(String[] csvLine){
		this.question = csvLine[1];
		this.option1 = csvLine[2];
		this.option2 = csvLine[3];
		this.option3 = csvLine[4];
		this.option4 = csvLine[5];
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getOption1(){
		return option1;
	}
	
	public String getOption2(){
		return option2;
	}
	
	public String getOption3(){
		return option3;
	}
	
	public String getOption4(){
		return option4;
	}
	
	public String[] readCSV() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("src\\questions.csv"));
		String line = null;
		Random rand = new Random(16);
		int lineNumber = rand.nextInt();
		
		for(int i = 0; i < lineNumber; ++i){
			  br.readLine();
		}
		line =br.readLine();
		csvLine = line.split(",");
		br.close();
		return csvLine;
		}
	}
