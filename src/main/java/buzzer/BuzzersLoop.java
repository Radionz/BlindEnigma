package main.java.buzzer;

import main.java.tts.Buzzer;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.ArrayList;
import java.util.Observable;

public class BuzzersLoop extends Observable implements Runnable {
    private Controller buzzer;
    private ArrayList<Integer> activated;
    private ArrayList<Integer> oldActive;
    private Component[] components;

    public BuzzersLoop() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        Buzzer[] joueurs = new Buzzer[4];
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
    }

    public void run() {
        while (true) {
            buzzer.poll();
            components = buzzer.getComponents();

            for (Component component : components) {
                if (!component.isAnalog()) {
                    if (component.getPollData() == 1.0f) {
                        try {
                            Integer number = Integer.parseInt(component.getName().replace("Bouton ", ""));
                            activated.add(number);
                        } catch (Exception e) {}
                    }
                }
            }
            activeProcess(activated);
            activated.clear();
        }
    }

    private void activeProcess(ArrayList<Integer> active) {
        if (!active.equals(oldActive)) {
            System.out.println(active);
            for (int button : active) {
                setChanged();
                notifyObservers(button);
            }
            oldActive.clear();
            oldActive.addAll(active);
        }
    }
}