package main.java.buzzer;

import net.java.games.input.*;

public class BuzzersController {

    public static void main(String[] args) {
        // testInputs();
        testBuzz();
        //testEventBuzz();
    }

    public static void testEventBuzz() {
        Controller[] controllers = ControllerEnvironment
                .getDefaultEnvironment().getControllers();
        Controller firstBuzz = null;
        for (int i = 0; i < controllers.length && firstBuzz == null; i++) {
            if (controllers[i].getType() == Controller.Type.STICK) {
                // Found a buzzer
                firstBuzz = controllers[i];
            }
        }
        if (firstBuzz == null) {
            // Couldn't find a mouse
            System.out.println("Found no buzzer");
            System.exit(0);
        }

        firstBuzz.poll();
        EventQueue queue = firstBuzz.getEventQueue();
        Event event = new Event();
        while (queue.getNextEvent(event)) {
            StringBuffer buffer = new StringBuffer();
            Component comp = event.getComponent();
            buffer.append(comp.getName()).append(" changed to ");
            float value = event.getValue();
            if (comp.isAnalog()) {
                buffer.append(value);
            } else {
                if (value == 1.0f) {
                    buffer.append("On");
                } else {
                    buffer.append("Off");
                }
            }
            System.out.println(buffer.toString());
        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testBuzz() {


    }

    public static void testInputs() {

        Controller[] ca = ControllerEnvironment.getDefaultEnvironment()
                .getControllers();

        for (int i = 0; i < ca.length; i++) {

			/* Get the name of the controller */
            System.out.println(ca[i].getName());
            System.out.println("Type: " + ca[i].getType().toString());

			/* Get this controllers components (buttons and axis) */
            Component[] components = ca[i].getComponents();
            System.out.println("Component Count: " + components.length);
            for (int j = 0; j < components.length; j++) {

				/* Get the components name */
                System.out.println("Component " + j + ": "
                        + components[j].getName());
                System.out.println("    Identifier: "
                        + components[j].getIdentifier().getName());
                System.out.print("    ComponentType: ");
                if (components[j].isRelative()) {
                    System.out.print("Relative");
                } else {
                    System.out.print("Absolute");
                }
                if (components[j].isAnalog()) {
                    System.out.print(" Analog");
                } else {
                    System.out.print(" Digital");
                }
            }
        }
    }
}
