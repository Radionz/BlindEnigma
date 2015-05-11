package main.java.tts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerSelection extends JFrame {

    public MyPanel[] joueurs;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public PlayerSelection() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1270, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel selectionLabel = new JLabel("SELECTION DES JOUEURS", SwingConstants.CENTER);
        selectionLabel.setPreferredSize(new Dimension(100, 100));
        selectionLabel.setFont(new Font("Calibri", Font.ITALIC, 120));
        contentPane.add(selectionLabel, BorderLayout.NORTH);

        joueurs = new MyPanel[4];

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4, 10, 10));

        joueurs[0] = new MyPanel("../ressources/images/graySelector.png", true);
        panel.add(joueurs[0]);

        joueurs[1] = new MyPanel("../ressources/images/graySelector.png", true);
        panel.add(joueurs[1]);

        joueurs[2] = new MyPanel("../ressources/images/graySelector.png", true);
        panel.add(joueurs[2]);

        joueurs[3] = new MyPanel("../ressources/images/graySelector.png", true);
        panel.add(joueurs[3]);

        for (int i = 0; i < 4; i++)
            joueurs[i].addMouseListener(new ListenerSelector(this, i));

        this.setLocationRelativeTo(null);
        contentPane.add(panel, BorderLayout.CENTER);

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlayerSelection frame = new PlayerSelection();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class ListenerSelector implements MouseListener {
        int num;
        PlayerSelection vue;

        public ListenerSelector(PlayerSelection vue, int num) {
            this.vue = vue;
            this.num = num;
        }

        @Override
        public void mouseClicked(MouseEvent arg0) {
            String image = vue.joueurs[num].getImgs();
            String nouvelleImage = "src/main/resources/images/";
            if (image.contains("gray")) {
                switch (num) {
                    case 0:
                        nouvelleImage += "greenSelector.png";
                        break;
                    case 1:
                        nouvelleImage += "yellowSelector.png";
                        break;
                    case 2:
                        nouvelleImage += "redSelector.png";
                        break;
                    case 3:
                        nouvelleImage += "violetSelector.png";
                        break;

                    default:
                        break;
                }
            } else nouvelleImage += "graySelector.png";
            vue.joueurs[num].setImgs(nouvelleImage);
            vue.joueurs[num].repaint();
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
        }

    }

}
