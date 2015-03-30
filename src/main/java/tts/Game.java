package main.java.tts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import main.java.generator.TTS;
import main.java.generator.TTS.Language;
import main.java.io.Parser;
import main.java.playback.AudioPlayer;

public class Game {

	private static final String PATH_TO_MP3_RESOURCES = "src/main/resources/tts/";
	private HashMap<String, String> constants;

	public Game() {
		constants = new HashMap<String, String>();
		// Création des fihcier mp3 qui contiennent les indications sonores.
		constructSoundIndications("src/main/resources/json/french.json");

		AudioPlayer ap = new AudioPlayer(constants.get("lancement_prog"));
		ap.play();

		FramePlay frame = new FramePlay();
		frame.setVisible(true);

		while (!ap.getPlayer().isComplete()) {
			waitFor1S();
		}

		ap.setFilename(constants.get("nouvelle_partie"));
		ap.play();
		
		int i = 0;
		while (i <= 100) {
			frame.getProgressBarMusic().setValue(i++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void waitFor1S() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void constructSoundIndications(String pathToJSON) {
		// Parsing du JSON contenant les phrases d'indication
		JSONObject french = null;
		try {
			french = Parser.getFromFile(pathToJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Pour chaque phrase d'indication on créer le mp3 correspondant
		for (Object obj : french.entrySet()) {
			Entry<String, String> entry = (Entry<String, String>) obj;
			String key = (String) entry.getKey(); // Nom de la phrase/fichier
			String value = entry.getValue(); // Phrase de description

			String pathToMP3;
			if (!(pathToMP3 = TTS.ttsFromString(value, PATH_TO_MP3_RESOURCES
					+ key, Language.FRENCH)).equals("err")) {
				constants.put(key, pathToMP3);
			}
		}
	}
}
