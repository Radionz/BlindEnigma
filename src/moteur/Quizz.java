package moteur;

import java.util.ArrayList;

public class Quizz {
	ArrayList<Joueur> joueurs;
	ArrayList<Question> questions;
	int currentQuestion;
	
	public Quizz(ArrayList<Joueur> joueurs, ArrayList<Question> questions) {
		this.joueurs = joueurs;
		this.questions = questions;
		currentQuestion = 0;
	}
	
	public void answer(int idjoueur,int choice){
		Question question = questions.get(currentQuestion);
		if(question.getAnswers().get(choice).equals(question.getGoodAns())){
			joueurs.get(idjoueur).incrementScore();
		}
	}
	
	public void goNextQuestion(){
		currentQuestion++;
	}
	
	public Question getCurrentQuestion(){
		return questions.get(currentQuestion);
	}
	
}
