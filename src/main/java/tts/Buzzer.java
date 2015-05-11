package main.java.tts;

/**
 * Created by user on 01/04/2015.
 */
public class Buzzer {

    public int player;
    public boolean selected;
    public boolean ready;
    public int numReponse;
    private boolean isPlaying;

    public Buzzer(int player) {
        this.player = player;
        this.selected = false;
        this.ready = false;
        this.isPlaying = false;
        this.numReponse = -1;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void redButtonStart() {
        isPlaying = ready = selected;
        selected = !selected;
    }

    public int getNumReponse() {
        return numReponse;
    }

    public void setNumReponse(int numReponse) {
        this.numReponse = numReponse;
    }

    public void clearReponse() {
        this.numReponse = -1;
    }

    public boolean haveAswered() {
        return this.numReponse != -1;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
