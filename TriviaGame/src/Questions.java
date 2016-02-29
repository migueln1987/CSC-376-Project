import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Questions {
	
	static String[] csvLine;
	private String category;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	
	public Questions() throws IOException{
		String [] questionArray = readCSV();
		this.category = questionArray[0];
		this.question = questionArray[1];
		this.option1 = questionArray[2];
		this.option2 = questionArray[3];
		this.option3 = questionArray[4];
		this.option4 = questionArray[5];
		
	}
	public String getCategory(){
		return category;
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

