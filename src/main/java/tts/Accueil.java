package main.java.tts;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class Accueil extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil frame = new Accueil();
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
	public Accueil() {
		setTitle("Accueil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBackground(Color.BLACK);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblJoueur = new JLabel("Joueur 1");
		lblJoueur.setForeground(Color.WHITE);
		lblJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur.setFont(new Font("Tahoma", Font.PLAIN, 70));
		panel_2.add(lblJoueur);
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.WHITE);
		panel_3.setBackground(Color.BLACK);
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblJoueur_1 = new JLabel("Joueur 2");
		lblJoueur_1.setForeground(Color.WHITE);
		lblJoueur_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur_1.setFont(new Font("Tahoma", Font.PLAIN, 70));
		panel_3.add(lblJoueur_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.WHITE);
		panel_4.setBackground(Color.BLACK);
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblJoueur_2 = new JLabel("Joueur 3");
		lblJoueur_2.setForeground(Color.WHITE);
		lblJoueur_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur_2.setFont(new Font("Tahoma", Font.PLAIN, 70));
		panel_4.add(lblJoueur_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.BLACK);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblJoueur_3 = new JLabel("Joueur 4");
		lblJoueur_3.setForeground(Color.WHITE);
		lblJoueur_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur_3.setFont(new Font("Tahoma", Font.PLAIN, 70));
		panel_1.add(lblJoueur_3);
	}

}
