package main.java.tts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Accueil extends JFrame {

    private JPanel contentPane;
    private JLabel lblJoueur3;
    private JLabel lblJoueur2;
    private JLabel lblJoueur1;
    private JLabel lblJoueur4;
    private ArrayList<JLabel> lblJoueurs;

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
        lblJoueurs = new ArrayList<JLabel>();
        setAlwaysOnTop(true);
        JPanel panelJoueurs = new JPanel();
        panelJoueurs.setBackground(Color.BLACK);
        contentPane.add(panelJoueurs);
        panelJoueurs.setLayout(new GridLayout(4, 1, 0, 0));

        JPanel panel_j1 = new JPanel();
        panel_j1.setForeground(Color.WHITE);
        panel_j1.setBackground(Color.BLACK);
        panelJoueurs.add(panel_j1);
        panel_j1.setLayout(new BorderLayout(0, 0));

        lblJoueur1 = new JLabel("Ecoutez les instructions");
        lblJoueurs.add(lblJoueur1);
        lblJoueur1.setForeground(Color.YELLOW);
        lblJoueur1.setHorizontalAlignment(SwingConstants.CENTER);
        lblJoueur1.setFont(new Font("Tahoma", Font.PLAIN, 70));
        panel_j1.add(lblJoueur1);

        JPanel panel_j2 = new JPanel();
        panel_j2.setForeground(Color.WHITE);
        panel_j2.setBackground(Color.BLACK);
        panelJoueurs.add(panel_j2);
        panel_j2.setLayout(new BorderLayout(0, 0));

        lblJoueur2 = new JLabel("Ecoutez les instructions");
        lblJoueurs.add(lblJoueur2);
        lblJoueur2.setForeground(Color.BLUE);
        lblJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
        lblJoueur2.setFont(new Font("Tahoma", Font.PLAIN, 70));
        panel_j2.add(lblJoueur2);

        JPanel panel_j3 = new JPanel();
        panel_j3.setForeground(Color.WHITE);
        panel_j3.setBackground(Color.BLACK);
        panelJoueurs.add(panel_j3);
        panel_j3.setLayout(new BorderLayout(0, 0));

        lblJoueur3 = new JLabel("Ecoutez les instructions");
        lblJoueurs.add(lblJoueur3);
        lblJoueur3.setForeground(Color.RED);
        lblJoueur3.setHorizontalAlignment(SwingConstants.CENTER);
        lblJoueur3.setFont(new Font("Tahoma", Font.PLAIN, 70));
        panel_j3.add(lblJoueur3);

        JPanel panel_j4 = new JPanel();
        panel_j4.setForeground(Color.WHITE);
        panel_j4.setBackground(Color.BLACK);
        panelJoueurs.add(panel_j4);
        panel_j4.setLayout(new BorderLayout(0, 0));

        lblJoueur4 = new JLabel("Ecoutez les instructions");
        lblJoueurs.add(lblJoueur4);
        lblJoueur4.setForeground(Color.GREEN);
        lblJoueur4.setHorizontalAlignment(SwingConstants.CENTER);
        lblJoueur4.setFont(new Font("Tahoma", Font.PLAIN, 70));
        panel_j4.add(lblJoueur4);
    }

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

    public JLabel getLblJoueur3() {
        return lblJoueur3;
    }

    public JLabel getLblJoueur2() {
        return lblJoueur2;
    }

    public JLabel getLblJoueur1() {
        return lblJoueur1;
    }

    public JLabel getLblJoueur4() {
        return lblJoueur4;
    }

    public boolean gameReady() {
        boolean gameCanBeReady = false;
        for (JLabel jLabel : lblJoueurs) {
            if (jLabel.getText().startsWith("Es-tu prêt")) {
                return false;
            } else if (jLabel.getText().startsWith("Prêt")) {
                gameCanBeReady = true;
            }
        }
        return gameCanBeReady;
    }
}
