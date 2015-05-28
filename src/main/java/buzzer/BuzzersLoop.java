package main.java.buzzer;

import main.java.tts.Buzzer;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;

import java.util.ArrayList;
import java.util.Observable;

public class BuzzersLoop extends Observable implements Runnable {
    long tempsDebut;
    private Controller controller;
    private ArrayList<Integer> activated;
    private ArrayList<Integer> oldActive;
    private Component[] components;
    private ArrayList<Integer> scored;
    private Buzzer[] joueurs;
    public Type type;

    public BuzzersLoop() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        joueurs = new Buzzer[4];
        for (int i = 0; i < 4; i++)
            joueurs[i] = new Buzzer(i);

        controller = null;
        for (int i = 0; i < controllers.length && controller == null; i++) {
            if (controllers[i].getType() == Controller.Type.STICK) {
                // Found a buzz
                type = Controller.Type.STICK;
                controller = controllers[i];
            }
        }

        if (controller == null) {
            for (int i = 0; i < controllers.length && controller == null; i++) {
                if (controllers[i].getType() == Controller.Type.KEYBOARD) {
                    // Found a buzz
                    controller = controllers[i];
                }
            }
            if( controllers == null) {
                System.out.println("Found no buzzers or keyboard.");
                System.exit(0);
            }
        }
        activated = new ArrayList<Integer>();
        oldActive = new ArrayList<Integer>();
        System.out.println("controller found: " + controller.getName());
        scored = new ArrayList<Integer>();

    }

    public void run() {
        while (true) {
            controller.poll();
            components = controller.getComponents();

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
