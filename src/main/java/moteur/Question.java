package main.java.moteur;

import java.util.ArrayList;
import java.util.Collections;

public class Question {
    ArrayList<String[]> answers = new ArrayList<String[]>();
    private String ans0, lang0, ans1, lang1, ans2, lang2, goodAns, langGoodAns, urlMusic;

    public Question(String[] ans0, String[] ans1, String[] ans2, String[] goodAns,
                    String urlMusic) {
        super();
        this.ans0 = ans0[0];
        this.ans1 = ans1[0];
        this.ans2 = ans2[0];
        this.goodAns = goodAns[0];
        this.urlMusic = urlMusic;
        answers.add(ans0);
        answers.add(ans1);
        answers.add(ans2);
        answers.add(goodAns);
        Collections.shuffle(answers);

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

    public ArrayList<String[]> getAnswers() {
        return answers;
    }

    public int getBonneReponse() {
        System.out.println(answers);
        for (int i = 0; i < answers.size(); i++)
            if (answers.get(i).equals(goodAns))
                return i;
        return 0;
    }
}
