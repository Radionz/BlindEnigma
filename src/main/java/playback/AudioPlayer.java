package main.java.playback;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class AudioPlayer {
    private String filename;
    private Player player;

    // constructor that takes the name of an MP3 file
    public AudioPlayer(String filename) {
        this.filename = filename;
    }

    public Player getPlayer() {
        return player;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void close() {
        if (player != null)
            player.close();
    }

    // play the MP3 file to the sound card
    public void play(boolean untilTheEnd) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();

        if (untilTheEnd) {
            while (!player.isComplete()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
