package main.java.tts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import main.java.buzzer.BuzzersController;
import main.java.buzzer.BuzzersLoop;
import main.java.generator.TTS;
import main.java.generator.TTS.Language;
import main.java.io.Parser;
import main.java.playback.AudioPlayer;

import org.json.simple.JSONObject;

public class Game implements Observer{

	private static final String PATH_TO_MP3_TTS_RESOURCES = "src/main/resources/tts/";
	private HashMap<String, String> constants;
	private Accueil accueil;
	private Buzzer[] joueurs;

	public Game() {
		// Contiendra les chemin vers les indications auditives, en clé le nom
		// du fichier sans extention
		constants = new HashMap<String, String>();

		joueurs = new Buzzer[4];
		for(int i=0 ; i<4 ; i++)
			joueurs[i] = new Buzzer(i);

		BuzzersLoop loop = new BuzzersLoop();

		// créer les indications auditives et lance le SplashScreen
		initGame();

		// Acceuil du jeu permettant de choisir qui joue
		accueil = new Accueil();
		accueil.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		accueil.setVisible(true);

		AudioPlayer nombre_joueur = new AudioPlayer(constants.get("nombre_joueur"));
		nombre_joueur.play(true);
		


		loop.addObserver(this);
		new Thread(loop).start();
	}

	private void startGame(){

		AudioPlayer nouvelle_partie = new AudioPlayer(constants.get("nouvelle_partie"));
		nouvelle_partie.play(true);
		accueil.dispose();
		FramePlay play = new FramePlay();
		play.setExtendedState(JFrame.MAXIMIZED_BOTH);
		play.setVisible(true);
	}

	private void initGame() {
		// Si elle a déjà été crée lire l'indication de bienvuenue
		String pathToMP3 = PATH_TO_MP3_TTS_RESOURCES + "lancement_prog.mp3";
		File f = new File(pathToMP3);
		boolean alreadyPlay = false;
		if (f.exists()) {
			alreadyPlay = true;
			PlayAudioFile lancement_prog = new PlayAudioFile();
			lancement_prog.play(pathToMP3);
		}

		// On lance le splash screen (ecran de présentation)
		SplashScreen sp = new SplashScreen();
		// Création des fihcier mp3 qui contiennent les indications sonores.
		constructSoundIndications("src/main/resources/json/french.json", sp);
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
			wait(400);
			Entry<String, String> entry = (Entry<String, String>) obj;
			String key = (String) entry.getKey(); // Nom de la phrase/fichier
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

	public boolean allPlayerReady(){
		for(Buzzer joueur : joueurs)
			if(joueur.isSelected() && !joueur.isReady())
				return false;
		return true;
	}

	@Override
	public void update(Observable o, Object arg) {
		int num = (Integer) arg;
		switch (num){

			// Tests
			case 0: joueurs[0].redButtonStart();
				if(joueurs[0].isReady()){
					(new AudioPlayer(constants.get("joueur_1_pret"))).play(false);
					accueil.getLblJoueur1().setText("Joueur 1 - Prêt");
				}
				else{
					(new AudioPlayer(constants.get("joueur_1"))).play(false);
					accueil.getLblJoueur1().setText("Joueur 1");
				}
				break;
			case 5: joueurs[1].redButtonStart();
				if(joueurs[1].isReady()){
					(new AudioPlayer(constants.get("joueur_2_pret"))).play(false);
					accueil.getLblJoueur2().setText("Joueur 2 - Prêt");
				}
				else{
					(new AudioPlayer(constants.get("joueur_2"))).play(false);
					accueil.getLblJoueur2().setText("Joueur 2");
				}
				break;
			case 10: joueurs[2].redButtonStart();
				if(joueurs[2].isReady()){
					(new AudioPlayer(constants.get("joueur_3_pret"))).play(false);
					accueil.getLblJoueur3().setText("Joueur 3 - Prêt");
				}
				else{
					(new AudioPlayer(constants.get("joueur_3"))).play(false);
					accueil.getLblJoueur3().setText("Joueur 3");
				}
				break;
			case 15: joueurs[3].redButtonStart();
				if(joueurs[3].isReady()){
					(new AudioPlayer(constants.get("joueur_4_pret"))).play(false);
					accueil.getLblJoueur4().setText("Joueur 4 - Prêt");
				}
				else{
					(new AudioPlayer(constants.get("joueur_4"))).play(false);
					accueil.getLblJoueur4().setText("Joueur 4");
				}
				break;
		}

		if(allPlayerReady()){
			wait(2000);
			startGame();
		}
	}
}
