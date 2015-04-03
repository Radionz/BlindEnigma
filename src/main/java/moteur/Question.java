package main.java.moteur;

import java.util.ArrayList;
import java.util.Collections;

public class Question {
	private String ans0,ans1,ans2,goodAns,urlMusic;
	ArrayList<String> answers = new ArrayList<String>();
	
	public Question(String ans0, String ans1, String ans2, String goodAns,
			String urlMusic) {
		super();
		this.ans0 = ans0;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.goodAns = goodAns;
		this.urlMusic = urlMusic;
		answers.add(ans0);answers.add(ans1);answers.add(ans2);answers.add(goodAns);
		Collections.shuffle(answers);
		
	}

	public String getAns0() {
		return ans0;
	}

	public String getAns1() {
		return ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public String getGoodAns() {
		return goodAns;
	}

	public String getUrlMusic() {
		return urlMusic;
	}

	public void setUrlMusic(String urlMusic) {
		this.urlMusic = urlMusic;
	}
	
	public ArrayList<String> getAnswers(){
		return answers;
	}
	
	public int getBonneReponse(){
		for(int i=0; i<answers.size() ; i++)
			if(answers.get(i).equals(goodAns))
				return i;
		return 0;
	}
}
