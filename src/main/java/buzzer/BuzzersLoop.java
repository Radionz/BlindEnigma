package main.java.buzzer;

import main.java.tts.Buzzer;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.ArrayList;
import java.util.Observable;

public class BuzzersLoop extends Observable implements Runnable {
    long tempsDebut;
    private Controller buzzer;
    private ArrayList<Integer> activated;
    private ArrayList<Integer> oldActive;
    private Component[] components;
    private ArrayList<Integer> scored;
    private Buzzer[] joueurs;

    public BuzzersLoop() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        joueurs = new Buzzer[4];
        for (int i = 0; i < 4; i++)
            joueurs[i] = new Buzzer(i);

        buzzer = null;
        for (int i = 0; i < controllers.length && buzzer == null; i++) {
            if (controllers[i].getType() == Controller.Type.STICK) {
                // Found a buzz
                buzzer = controllers[i];
            }
        }
        if (buzzer == null) {
            // Couldn't find a buzz
            System.out.println("Found no buzz.");
            System.exit(0);
        }
        activated = new ArrayList<Integer>();
        oldActive = new ArrayList<Integer>();
        System.out.println("buzzer found: " + buzzer.getName());
        scored = new ArrayList<Integer>();

    }

    public void run() {
        while (true) {
            buzzer.poll();
            components = buzzer.getComponents();

            for (int i = 0; i < components.length; i++) {
                if (!components[i].isAnalog()) {
                    if (components[i].getPollData() == 1.0f) {
                        try {
                            Integer number = Integer.parseInt(components[i].getName().replace("Bouton ", ""));
                            activated.add(number);
                        } catch (Exception e) {
                        }
                    }
                }
            }
            activeProcess(activated);
            activated.clear();
        }
    }

    private void activeProcess(ArrayList<Integer> active) {

        if (!active.equals(oldActive)) {
            tempsDebut = System.currentTimeMillis();
            System.out.println(active);
            for (int button : active) {
                setChanged();
                notifyObservers(button);
            }

            scored.clear();
            oldActive.clear();
            oldActive.addAll(active);
        }
    }
}
