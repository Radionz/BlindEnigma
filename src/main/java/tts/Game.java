package main.java.tts;

import main.java.buzzer.BuzzersLoop;
import main.java.generator.TTS;
import main.java.generator.TTS.Language;
import main.java.io.Parser;
import main.java.moteur.Question;
import main.java.playback.AudioPlayer;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

public class Game implements Observer {

    private static final String PATH_TO_MP3_TTS_RESOURCES = "../ressources/tts/";
    private static final String PATH_TO_MP3_MUSIC_RESOURCES = "../ressources/music/";
    private HashMap<String, String> constants;
    private Accueil accueil;
    private Buzzer[] joueurs;
    private boolean gameStarted;
    private Question[] questions;
    private FramePlay play;
    private int numQuestion;

    public Game() {
        // Contiendra les chemin vers les indications auditives, en clé le nom
        // du fichier sans extention
        constants = new HashMap<String, String>();

        // créer les indications auditives et lance le SplashScreen
        initGame();

        joueurs = new Buzzer[4];
        for (int i = 0; i < 4; i++)
            joueurs[i] = new Buzzer(i);

        BuzzersLoop loop = new BuzzersLoop();
        questions = new Question[9];
        gameStarted = false;
        numQuestion = 0;

        // Acceuil du jeu permettant de choisir qui joue
        accueil = new Accueil();
        accueil.setExtendedState(JFrame.MAXIMIZED_BOTH);
        accueil.setVisible(true);

        AudioPlayer nombre_joueur = new AudioPlayer(
                constants.get("nombre_joueur"));
        nombre_joueur.play(true);

        loop.addObserver(this);
        new Thread(loop).start();
    }

    private void startGame() {
        gameStarted = true;
        questions[0] = new Question(new String[]{"Somebody That I Used to Know","en"},
                new String[]{"L'Auvergnat","fr"},
                new String[]{"Les lacs du Conemara","fr"},
                new String[]{"Andalouse","fr"},
                "Kendji Girac - Andalouse.mp3");
        questions[1] = new Question(new String[]{"Protect Ya Neck","en"},
                new String[]{"Smells Like Teen Spirit","en"},
                new String[]{"Lose Yourself","en"},
                new String[]{"Call Me Maybe","en"},
                "Carly Rae Jepsen - Call Me Maybe.mp3");
        questions[2] = new Question(new String[]{"Toute la vie","fr"},
                new String[]{"The Sound of Silence","en"},
                new String[]{"Stairway To Heaven","en"},
                new String[]{"Diamonds","en"},
                "Rihanna - Diamonds.mp3");
        questions[3] = new Question(new String[]{"What's My Name","en"},
                new String[]{"Sarbacane","fr"},
                new String[]{"Allumez le Feu","fr"},
                new String[]{"Fresh Prince","fr"},
                "Soprano - Fresh Prince.mp3");
        questions[4] = new Question(new String[]{"What's My Name","en"},
                new String[]{"Sarbacane","fr"},
                new String[]{"Allumez le Feu","fr"},
                new String[]{"Désolé","fr"},
                "Sexion D'Assaut - Désolé.mp3");
        questions[5] = new Question(new String[]{"What's My Name","en"},
                new String[]{"Sarbacane","fr"},
                new String[]{"Allumez le Feu","fr"},
                new String[]{"Cheerleader","en"},
                "OMI - Cheerleader.mp3");
        questions[6] = new Question(new String[]{"What's My Name","en"},
                new String[]{"Sarbacane","fr"},
                new String[]{"Allumez le Feu","fr"},
                new String[]{"Sur Ma Route","fr"},
                "Black M - Sur Ma Route.mp3");
        questions[7] = new Question(new String[]{"What's My Name","en"},
                new String[]{"Sarbacane","fr"},
                new String[]{"Allumez le Feu","fr"},
                new String[]{"Dans Ma Paranoia","fr"},
                "JUL - Dans Ma Paranoia.mp3");
        questions[8] = new Question(new String[]{"What's My Name","en"},
                new String[]{"Sarbacane","fr"},
                new String[]{"Allumez le Feu","fr"},
                new String[]{"Lean On","en"},
                "Major Lazer - Lean On.mp3");
        
        
        
        AudioPlayer nouvelle_partie = new AudioPlayer(
                constants.get("nouvelle_partie"));
        nouvelle_partie.play(true);
        accueil.dispose();
        play = new FramePlay();
        play.getProgressBarMusic().setMaximum(2000);
        play.getProgressBarMusic().setValue(0);
        play.setVisible(true);
        play.setExtendedState(JFrame.MAXIMIZED_BOTH);

        nextQuestion();
    }

    private void nextQuestion() {
        if (numQuestion >= questions.length) {
            int meilleurJoueur = 0;
            for (int i = 0; i < 4; i++) {
                System.out.println(i + " --> " + joueurs[i].getScore());
                if (meilleurJoueur < joueurs[i].getScore()) {
                    meilleurJoueur = i;
                }
            }
            System.out.println("Le gagnant est: " + meilleurJoueur + " avec " + joueurs[meilleurJoueur].getScore() + " points");
        }
        play.getProgressBarMusic().setValue(0);
        AudioPlayer question = new AudioPlayer(
                constants.get("question_generale"));
        question.play(true);
        AudioPlayer premiereChanson = new AudioPlayer(
                PATH_TO_MP3_MUSIC_RESOURCES + questions[numQuestion].getUrlMusic());
        premiereChanson.play(false);

        for (int i = 0; i <= 2000; i++) {
            wait(10);
            play.getProgressBarMusic().setValue(i);
        }
        for (int i = 0; i < 4; i++) {
            if(questions[numQuestion].getAnswers().get(i)[1] == "fr"){
                TTS.ttsFromString(questions[numQuestion].getAnswers().get(i)[0], PATH_TO_MP3_TTS_RESOURCES + questions[numQuestion].getAnswers().get(i)[0].replace(" ", "") + ".mp3", Language.FRENCH);
            }
            else{
                TTS.ttsFromString(questions[numQuestion].getAnswers().get(i)[0], PATH_TO_MP3_TTS_RESOURCES + questions[numQuestion].getAnswers().get(i)[0].replace(" ", "") + ".mp3", Language.ENGLISH);
            }
            System.out.println(PATH_TO_MP3_TTS_RESOURCES + questions[numQuestion].getAnswers().get(i)[0].replace(" ", "") + ".mp3");
            wait(300);
            AudioPlayer proposition = new AudioPlayer(PATH_TO_MP3_TTS_RESOURCES + questions[numQuestion].getAnswers().get(i)[0].replace(" ", "") + ".mp3");
            proposition.play(true);
            play.getReponses()[i].setText(questions[numQuestion].getAnswers().get(i)[0]);
        }
    }

    private void initGame() {
        // Si elle a déjà été crée lire l'indication de bienvuenue
        String pathToMP3 = PATH_TO_MP3_TTS_RESOURCES + "lancement_prog.mp3";
        File f = new File(pathToMP3);
        boolean alreadyPlay = false;
        if (f.exists()) {
            alreadyPlay = true;
            AudioPlayer lancement_prog = new AudioPlayer(pathToMP3);
            lancement_prog.play(false);
        }

        // On lance le splash screen (ecran de présentation)
        SplashScreen sp = new SplashScreen();
        // Création des fihcier mp3 qui contiennent les indications sonores.
        for(String s : new File("..").list())
            System.out.println(s);
        constructSoundIndications("../ressources/json/french.json", sp);
        wait(300);
        // Quand on a terminé on cache le splash screen
        sp.hideSplashScreen();

        // Si on l'a pas déjà lue, on lit l'indication de bienvuenue
        if (!alreadyPlay) {
            AudioPlayer lancement_prog = new AudioPlayer(
                    constants.get("lancement_prog"));
            lancement_prog.play(true);
        }
    }

    /**
     * Attendre pour temporiser le jeu
     *
     * @param time
     */
    private void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer toutes les indication sonores à partir d'un fihcier de
     * configuration json Gère aussi l'avancement du SplashScreen afin de faire
     * avancer la progress bar et aussi d'écrire ce qui est en train d'être
     * réalisé
     *
     * @param pathToJSON
     * @param sp
     */
    private void constructSoundIndications(String pathToJSON, SplashScreen sp) {
        // Parsing du JSON contenant les phrases d'indication
        JSONObject french = null;
        try {
            french = Parser.getFromFile(pathToJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Pour chaque indication sonor on va créer le fihcier mp3 correspondant
        int i = 0;
        for (Object obj : french.entrySet()) {
            i++;
            wait(100);
            Entry<String, String> entry = (Entry<String, String>) obj;
            String key = entry.getKey(); // Phrase de description
            String value = entry.getValue(); // Phrase de description

            String pathToMP3 = PATH_TO_MP3_TTS_RESOURCES + key + ".mp3";
            File f = new File(pathToMP3);
            if (!f.exists()) {
                sp.notify("Création de l'indication vocale: " + key,
                        french.size(), i);
                if (!TTS.ttsFromString(value, pathToMP3, Language.FRENCH)) {
                    System.out
                            .println("Impossible de créer le fichier de synthèse vocale.");
                }
            } else {
                sp.notify("Indication vocale: " + key + " trouvée.",
                        french.size(), i);
            }
            constants.put(key, pathToMP3);
        }
        sp.notify("Indications vocales prêtes.", french.size(), i);
    }

    public boolean allPlayerReady() {
        for (Buzzer joueur : joueurs)
            if (joueur.isSelected() && !joueur.isReady())
                return false;
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {
        int num = (Integer) arg;
        System.out.println(num);
        switch (num) {

            // Tests
            case 0:
                joueurs[0].redButtonStart();
                if (joueurs[0].isReady() && !gameStarted) {
                    (new AudioPlayer(constants.get("joueur_1_pret"))).play(false);
                    accueil.getLblJoueur1().setText("Joueur 1 - Prêt");
                } else if (!gameStarted) {
                    (new AudioPlayer(constants.get("joueur_1"))).play(false);
                    accueil.getLblJoueur1().setText("Joueur 1");
                }
                break;
            case 5:
                joueurs[1].redButtonStart();
                if (joueurs[1].isReady() && !gameStarted) {
                    (new AudioPlayer(constants.get("joueur_2_pret"))).play(false);
                    accueil.getLblJoueur2().setText("Joueur 2 - Prêt");
                } else if (!gameStarted) {
                    (new AudioPlayer(constants.get("joueur_2"))).play(false);
                    accueil.getLblJoueur2().setText("Joueur 2");
                }
                break;
            case 10:
                joueurs[2].redButtonStart();
                if (joueurs[2].isReady() && !gameStarted) {
                    (new AudioPlayer(constants.get("joueur_3_pret"))).play(false);
                    accueil.getLblJoueur3().setText("Joueur 3 - Prêt");
                } else if (!gameStarted) {
                    (new AudioPlayer(constants.get("joueur_3"))).play(false);
                    accueil.getLblJoueur3().setText("Joueur 3");
                }
                break;
            case 15:
                joueurs[3].redButtonStart();
                if (joueurs[3].isReady() && !gameStarted) {
                    (new AudioPlayer(constants.get("joueur_4_pret"))).play(false);
                    accueil.getLblJoueur4().setText("Joueur 4 - Prêt");
                } else if (!gameStarted) {
                    (new AudioPlayer(constants.get("joueur_4"))).play(false);
                    accueil.getLblJoueur4().setText("Joueur 4");
                }
                break;
            default:
                if (gameStarted) repondreQuestion(num / 5, 4 - (num % 5));
        }

        if (allPlayerReady() && !gameStarted) {
            wait(2000);
            startGame();
        }
    }

    public boolean allPlayerAnswered() {
        for (Buzzer joueur : joueurs) {
            if (!joueur.haveAswered() && joueur.isPlaying())
                return false;
        }
        return true;
    }

    public ArrayList<Buzzer> getGagnants() {
        int numQuestionCorrecte = questions[numQuestion].getBonneReponse();
        ArrayList<Buzzer> winners = new ArrayList<Buzzer>();
        for (Buzzer joueur : joueurs)
            if (joueur.getNumReponse() == numQuestionCorrecte)
                winners.add(joueur);
        System.out.println("Num question " + numQuestionCorrecte);
        System.out.println("Joueur gagnant " + winners);
        return winners;
    }

    private void resetAnswers() {
        for (Buzzer joueur : joueurs)
            joueur.clearReponse();
    }

    private void resetGUI() {
        for (JLabel label : play.getReponses())
            label.setText("");
        for (JLabel[] labelTab : play.getJoueurParRep())
            for (JLabel label : labelTab)
                label.setVisible(false);
    }

    private void repondreQuestion(int joueur, int reponse) {
        System.out.println("joueur " + joueur + " reponse " + reponse);
        if (!joueurs[joueur].haveAswered() && joueurs[joueur].isPlaying()) {
            play.getJoueurParRep()[reponse][joueur].setVisible(true);
            joueurs[joueur].setNumReponse(reponse);
        }
        if (allPlayerAnswered()) {
            for (Buzzer b : getGagnants()) {
                if (getGagnants().size() > 1) {
                    AudioPlayer joueur_1 = new AudioPlayer(
                            constants.get("les_joueurs"));
                    joueur_1.play(true);
                } else {
                    AudioPlayer joueur_1 = new AudioPlayer(
                            constants.get("le_joueur"));
                    joueur_1.play(true);
                }
                switch (b.getPlayer()) {
                    case 0:
                        AudioPlayer joueur_1 = new AudioPlayer(
                                constants.get("joueur_1"));
                        joueur_1.play(true);
                        joueurs[0].winAPoint();
                        break;
                    case 1:
                        AudioPlayer joueur_2 = new AudioPlayer(
                                constants.get("joueur_2"));
                        joueur_2.play(true);
                        joueurs[1].winAPoint();
                        break;
                    case 2:
                        AudioPlayer joueur_3 = new AudioPlayer(
                                constants.get("joueur_3"));
                        joueur_3.play(true);
                        joueurs[2].winAPoint();
                        break;
                    case 3:
                        AudioPlayer joueur_4 = new AudioPlayer(
                                constants.get("joueur_4"));
                        joueur_4.play(true);
                        joueurs[3].winAPoint();
                        break;
                    default:
                        break;
                }
            }
            AudioPlayer reponse_juste = new AudioPlayer(
                    constants.get("reponse_juste"));
            reponse_juste.play(true);

            // passage à la prochaine question
            numQuestion++;
            resetAnswers();
            resetGUI();
            nextQuestion();
        }
    }


}
