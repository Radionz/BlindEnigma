package main.java.buzzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import javax.swing.JLabel;
import main.java.tts.Buzzer;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class BuzzersLoop extends Observable implements Runnable {
	private Controller firstBuzz;
	private ArrayList<Integer> activated;
	private ArrayList<Integer> oldActive;
	private Component[] components;
	private ArrayList<Integer> scored;
	long tempsDebut;
	private Buzzer[] joueurs;

	public BuzzersLoop() {
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		joueurs = new Buzzer[4];
		for(int i=0 ; i<4 ; i++)
			joueurs[i] = new Buzzer(i);

		firstBuzz = null;
		for (int i = 0; i < controllers.length && firstBuzz == null; i++) {
			if (controllers[i].getType() == Controller.Type.KEYBOARD) {
				// Found a buzz
				firstBuzz = controllers[i];
			}
		}
		if (firstBuzz == null) {
			// Couldn't find a buzz
			System.out.println("Found no buzz.");
			System.exit(0);
		}
		activated = new ArrayList<Integer>();
		oldActive = new ArrayList<Integer>();
		System.out.println("First buzz is: " + firstBuzz.getName());
		scored = new ArrayList<Integer>();

	}

	public void run() {
		while (true) {
			firstBuzz.poll();
			components = firstBuzz.getComponents();

			for (int i = 0; i < components.length; i++) {
				if (!components[i].isAnalog()) {
					if (components[i].getPollData() == 1.0f) {
						try{
							Integer number = Integer.parseInt(components[i].getName().replace("Bouton ", ""));
							activated.add(number);
						}
						catch (Exception e){}
					}
				}
			}
			activeProcess(activated);
			activated.clear();
			// try {
			// Thread.sleep(1);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
	}

	private void activeProcess(ArrayList<Integer> active) {

		if (!active.equals(oldActive)) {
			tempsDebut = System.currentTimeMillis();
			threadMessage(active.toString());
			System.out.println(active);
			for(int button : active) {
				setChanged();
				notifyObservers(button);
			}

			scored.clear();
			oldActive.clear();
			oldActive.addAll(active);
		}

	}

	private void scored(ArrayList<Integer> scoredList) {
		long tempsDebut = System.currentTimeMillis();

		long tempsFin = System.currentTimeMillis();
		float seconds = (tempsFin - tempsDebut) / 1000F;
	}

	// Display a message, preceded by
	// the name of the current thread
	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}
}
