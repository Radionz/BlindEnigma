package main.java.tts;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class FramePlay extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBarMusic;
	private JLabel labelRep1;
	private JLabel labelRep4;
	private JLabel labelRep3;
	private JLabel labelRep2;
	private JLabel[][] joueurParRep;
	private JLabel[] reponses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePlay frame = new FramePlay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePlay() {
		setTitle("BlindEnigma !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		joueurParRep = new JLabel[4][4];
		reponses = new JLabel[4];

		progressBarMusic = new JProgressBar();
		progressBarMusic.setForeground(Color.WHITE);
		progressBarMusic.setBackground(Color.BLACK);
		progressBarMusic.setFont(new Font("Tahoma", Font.BOLD, 50));
		progressBarMusic.setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() {
				return Color.white;
			}

			protected Color getSelectionForeground() {
				return Color.black;
			}
		});
		progressBarMusic.setStringPainted(true);
		progressBarMusic.setValue(45);
		contentPane.add(progressBarMusic, BorderLayout.NORTH);

		JPanel panelReponses = new JPanel();
		panelReponses.setBorder(new LineBorder(Color.WHITE, 2));
		contentPane.add(panelReponses, BorderLayout.CENTER);
		panelReponses.setLayout(new GridLayout(4, 1, 10, 0));

		labelRep1 = new JLabel("Réponse 1");
		labelRep1.setOpaque(true);
		labelRep1.setBackground(Color.BLACK);
		labelRep1.setForeground(Color.WHITE);
		labelRep1.setFont(new Font("Tahoma", Font.BOLD, 70));
		labelRep1.setHorizontalAlignment(SwingConstants.CENTER);
		panelReponses.add(labelRep1);
		reponses[0] = labelRep1;

		labelRep2 = new JLabel("Réponse 2");
		labelRep2.setOpaque(true);
		labelRep2.setBackground(Color.BLACK);
		labelRep2.setForeground(Color.WHITE);
		labelRep2.setFont(new Font("Tahoma", Font.BOLD, 70));
		labelRep2.setHorizontalAlignment(SwingConstants.CENTER);
		panelReponses.add(labelRep2);
		reponses[1] = labelRep2;

		labelRep3 = new JLabel("Réponse 3");
		labelRep3.setOpaque(true);
		labelRep3.setBackground(Color.BLACK);
		labelRep3.setForeground(Color.WHITE);
		labelRep3.setFont(new Font("Tahoma", Font.BOLD, 70));
		labelRep3.setHorizontalAlignment(SwingConstants.CENTER);
		panelReponses.add(labelRep3);
		reponses[2] = labelRep3;

		labelRep4 = new JLabel("Réponse 4");
		labelRep4.setOpaque(true);
		labelRep4.setBackground(Color.BLACK);
		labelRep4.setForeground(Color.WHITE);
		labelRep4.setFont(new Font("Tahoma", Font.BOLD, 70));
		labelRep4.setHorizontalAlignment(SwingConstants.CENTER);
		panelReponses.add(labelRep4);
		reponses[3]=labelRep4;
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panel_1.setBackground(Color.BLACK);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel label = new JLabel("Joueur 1");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.YELLOW);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		label.setBackground(Color.BLACK);
		panel_1.add(label);
		joueurParRep[0][0]=label;

		JLabel label_1 = new JLabel("Joueur 2");
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_1.setBackground(Color.BLACK);
		panel_1.add(label_1);
		joueurParRep[0][1]=label_1;

		JLabel label_2 = new JLabel("Joueur 3");
		label_2.setOpaque(true);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_2.setBackground(Color.BLACK);
		panel_1.add(label_2);
		joueurParRep[0][2]=label_2;

		JLabel label_3 = new JLabel("Joueur 4");
		label_3.setOpaque(true);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(Color.GREEN);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_3.setBackground(Color.BLACK);
		panel_1.add(label_3);
		joueurParRep[0][3]=label_3;

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.WHITE, 2));
		panel_2.setBackground(Color.BLACK);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel label_4 = new JLabel("Joueur 1");
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setForeground(Color.YELLOW);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_4.setBackground(Color.BLACK);
		panel_2.add(label_4);
		joueurParRep[1][0]=label_4;

		JLabel label_5 = new JLabel("Joueur 2");
		label_5.setOpaque(true);
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setForeground(Color.BLUE);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_5.setBackground(Color.BLACK);
		panel_2.add(label_5);
		joueurParRep[1][1]=label_5;

		JLabel label_6 = new JLabel("Joueur 3");
		label_6.setOpaque(true);
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_6.setBackground(Color.BLACK);
		panel_2.add(label_6);
		joueurParRep[1][2]=label_6;

		JLabel label_7 = new JLabel("Joueur 4");
		label_7.setOpaque(true);
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setForeground(Color.GREEN);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_7.setBackground(Color.BLACK);
		panel_2.add(label_7);
		joueurParRep[1][3]=label_7;

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.WHITE, 2));
		panel_3.setBackground(Color.BLACK);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel label_8 = new JLabel("Joueur 1");
		label_8.setOpaque(true);
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(Color.YELLOW);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_8.setBackground(Color.BLACK);
		panel_3.add(label_8);
		joueurParRep[2][0]=label_8;

		JLabel label_9 = new JLabel("Joueur 2");
		label_9.setOpaque(true);
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setForeground(Color.BLUE);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_9.setBackground(Color.BLACK);
		panel_3.add(label_9);
		joueurParRep[2][1]=label_9;

		JLabel label_10 = new JLabel("Joueur 3");
		label_10.setOpaque(true);
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setForeground(Color.RED);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_10.setBackground(Color.BLACK);
		panel_3.add(label_10);
		joueurParRep[2][2]=label_10;

		JLabel label_11 = new JLabel("Joueur 4");
		label_11.setOpaque(true);
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setForeground(Color.GREEN);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_11.setBackground(Color.BLACK);
		panel_3.add(label_11);
		joueurParRep[2][3]=label_11;

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.WHITE, 2));
		panel_4.setBackground(Color.BLACK);
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel label_12 = new JLabel("Joueur 1");
		label_12.setOpaque(true);
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setForeground(Color.YELLOW);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_12.setBackground(Color.BLACK);
		panel_4.add(label_12);
		joueurParRep[3][0]=label_12;

		JLabel label_13 = new JLabel("Joueur 2");
		label_13.setOpaque(true);
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setForeground(Color.BLUE);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_13.setBackground(Color.BLACK);
		panel_4.add(label_13);
		joueurParRep[3][1]=label_13;

		JLabel label_14 = new JLabel("Joueur 3");
		label_14.setOpaque(true);
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		label_14.setForeground(Color.RED);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_14.setBackground(Color.BLACK);
		panel_4.add(label_14);
		joueurParRep[3][2]=label_14;

		JLabel label_15 = new JLabel("Joueur 4");
		label_15.setOpaque(true);
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		label_15.setForeground(Color.GREEN);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_15.setBackground(Color.BLACK);
		panel_4.add(label_15);
		joueurParRep[3][3]=label_15;

		for(int i=0; i<joueurParRep.length; i++)
			for (int j=0; j<joueurParRep[i].length; j++)
				joueurParRep[i][j].setVisible(false);
	}

	public JLabel[][] getJoueurParRep() {
		return joueurParRep;
	}

	public void setJoueurParRep(JLabel[][] joueurParRep) {
		this.joueurParRep = joueurParRep;
	}

	public JLabel[] getReponses() {
		return reponses;
	}

	public void setReponses(JLabel[] reponses) {
		this.reponses = reponses;
	}

	public JProgressBar getProgressBarMusic() {
		return progressBarMusic;
	}
	public JLabel getLabelRep1() {
		return labelRep1;
	}
	public JLabel getLabelRep4() {
		return labelRep4;
	}
	public JLabel getLabelRep3() {
		return labelRep3;
	}
	public JLabel getLabelRep2() {
		return labelRep2;
	}
}
