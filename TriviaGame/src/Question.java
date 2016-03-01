import java.io.IOException;

public class Question {
	
	static String[] csvLine;
	private String category;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	
	public Question(String[] questionArray) throws IOException{
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
	
	public String getRightAnswer(){
		return option1;
	}

}

