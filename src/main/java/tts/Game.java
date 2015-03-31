package main.java.tts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import main.java.generator.TTS;
import main.java.generator.TTS.Language;
import main.java.io.Parser;
import main.java.playback.AudioPlayer;

import org.json.simple.JSONObject;

public class Game {

	private static final String PATH_TO_MP3_TTS_RESOURCES = "src/main/resources/tts/";
	private HashMap<String, String> constants;

	public Game() {
		constants = new HashMap<String, String>();
		initGame();

		Accueil frame = new Accueil();
		frame.setVisible(true);
		
		AudioPlayer nombre_joueur = new AudioPlayer(
				constants.get("nombre_joueur"));
		nombre_joueur.play(true);

		// FramePlay frame = new FramePlay();
		// frame.setVisible(true);
		//
		// ap.setFilename(constants.get("nouvelle_partie"));
		// ap.play(false);
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
		String pathToMP3 = PATH_TO_MP3_TTS_RESOURCES + "lancement_prog.mp3";
		File f = new File(pathToMP3);
		boolean alreadyPlay = false;
		if (f.exists()) {
			alreadyPlay = true;
			AudioPlayer lancement_prog = new AudioPlayer(pathToMP3);
			lancement_prog.play(false);
		}
		SplashScreen sp = new SplashScreen();
		// Création des fihcier mp3 qui contiennent les indications sonores.
		constructSoundIndications("src/main/resources/json/french.json", sp);
		wait(300);
		sp.hideSplashScreen();
		if (!alreadyPlay) {
			AudioPlayer lancement_prog = new AudioPlayer(
					constants.get("lancement_prog"));
			lancement_prog.play(true);
		}
	}
	
	private void wait(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void constructSoundIndications(String pathToJSON, SplashScreen sp) {
		// Parsing du JSON contenant les phrases d'indication
		JSONObject french = null;
		try {
			french = Parser.getFromFile(pathToJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}

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
