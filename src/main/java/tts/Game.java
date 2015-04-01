package main.java.tts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;

import main.java.generator.TTS;
import main.java.generator.TTS.Language;
import main.java.io.Parser;
import main.java.playback.AudioPlayer;

import org.json.simple.JSONObject;

public class Game {

	private static final String PATH_TO_MP3_TTS_RESOURCES = "src/main/resources/tts/";
	private HashMap<String, String> constants;

	public Game() {
		// Contiendra les chemin vers les indications auditives, en clé le nom
		// du fichier sans extention
		constants = new HashMap<String, String>();
		// créer les indications auditives et lance le SplashScreen
		initGame();

		// Acceuil du jeu permettant de choisir qui joue
		Accueil accueil = new Accueil();
		accueil.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		accueil.setVisible(true);

		AudioPlayer nombre_joueur = new AudioPlayer(
				constants.get("nombre_joueur"));
		nombre_joueur.play(true);
		
		accueil.getLblJoueur1().setText("Joueur 1");
		accueil.getLblJoueur2().setText("Joueur 2");
		accueil.getLblJoueur3().setText("Joueur 3");
		accueil.getLblJoueur4().setText("Joueur 4");
		
		while(!accueil.gameReady()){
			//Il faut savoir quand les joueur cliquent sur les buzzers
			//buzzer 1 btn de 0 à 4, buzzer 2 btn de 5 à 9, buzzer 3 btn de 10 à 14, buzzer 4 btn de 15 à 19
			
			//if(le buzzer est appuyé) 		Pour tester
			wait(1500);
			accueil.getLblJoueur1().setText("Es-tu prêt - Joueur 1 ?");
			wait(1500);
			accueil.getLblJoueur3().setText("Es-tu prêt - Joueur 2 ?");
			wait(1500);
			accueil.getLblJoueur1().setText("Prêt - Joueur 1");
			wait(1500);
			accueil.getLblJoueur3().setText("Prêt - Joueur 2");
		}
		
		AudioPlayer nouvelle_partie = new AudioPlayer(
				constants.get("nouvelle_partie"));
		nouvelle_partie.play(true);

		accueil.dispose();
		
		FramePlay play = new FramePlay();
		play.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		play.setVisible(true);
		//
		// int i = 0;
		// while (i <= 100) {
		// frame.getProgressBarMusic().setValue(i++);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
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
}
