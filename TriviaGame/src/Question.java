import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Question {
	
	static String[] csvLine;
	private String category;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String rightAnswer;
	
	
	public Question(String[] questionArray) throws IOException{
		String temp[] = new String[6];
		this.category = questionArray[0];
		this.question = questionArray[1];
		this.rightAnswer = questionArray[2];
		temp = shuffleAnswers(questionArray);
		this.option1 = temp[2];
		this.option2 = temp[3];
		this.option3 = temp[4];
		this.option4 = temp[5];
		
	}
	public String getCategory(){
		return category;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getOption1(){
		return "a) " + option1;
	}
	
	public String getOption2(){
		return "b) " + option2;
	}
	
	public String getOption3(){
		return "c) " + option3;
	}
	
	public String getOption4(){
		return "d) " + option4;
	}
	
	public String getRightAnswer(){
		if(getOption1().contains(rightAnswer)){
			rightAnswer = "a) " + rightAnswer;
		}
		if(getOption2().contains(rightAnswer)){
			rightAnswer = "b) " + rightAnswer;
		}
		if(getOption3().contains(rightAnswer)){
			rightAnswer = "c) " + rightAnswer;
		}
		if(getOption4().contains(rightAnswer)){
			rightAnswer = "d) " + rightAnswer;
		}
		return rightAnswer;
	}
	
	public String[] shuffleAnswers(String[] questionArray){
		String temp[] = new String[4];
		for(int i = 0; i < temp.length; i++){
			String tempString = questionArray[i + 2];
			temp[i] = tempString;
		}
		Collections.shuffle(Arrays.asList(temp));
		for(int i = 0; i < temp.length; i++){
			String tempString = temp[i];
			questionArray[i + 2] = tempString;
		}
		return questionArray;
	}

}

