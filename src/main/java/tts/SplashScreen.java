package main.java.tts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SplashScreen {

	private JDialog dialog;
	private JProgressBar progress;
	JLabel text;

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
				"src/main/resources/images/Splash_Screen.jpg"));
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
	}

	public void notify(String string, int total, int actual) {
		text.setText(string);
		
		progress.setValue((int) (((double)actual/(double)total)*100));
	}
}