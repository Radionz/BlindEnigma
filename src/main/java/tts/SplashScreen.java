package main.java.tts;

import javax.swing.*;
import java.awt.*;

public class SplashScreen {

    JLabel text;
    private JDialog dialog;
    private JProgressBar progress;

    public SplashScreen() {
        showSplashScreen();
    }

    public void hideSplashScreen() {
        dialog.setVisible(false);
        dialog.dispose();
    }

    protected void showSplashScreen() {
        dialog = new JDialog((Frame) null);
        dialog.setModal(false);
        dialog.setUndecorated(true);
        JLabel background = new JLabel(new ImageIcon(
                "../ressources/images/Splash_Screen.jpg"));
        background.setLayout(new BorderLayout());
        dialog.add(background);
        text = new JLabel("Chargement...");
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Tahoma", Font.BOLD, 17));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.BOTTOM);
        background.add(text);
        progress = new JProgressBar();
        progress.setMinimum(0);
        progress.setMaximum(100);
        background.add(progress, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setAlwaysOnTop(true);
    }

    public void notify(String string, int total, int actual) {
        text.setText(string);

        progress.setValue((int) (((double) actual / (double) total) * 100));
    }
}