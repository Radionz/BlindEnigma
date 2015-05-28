package jeu;

import javax.swing.*;
import javax.swing.border.LineBorder;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/** Cette classe montre comment utiliser la synth�se vocale
 * @author helene
 *
 */

public class Regles extends FenetreAbstraite{

    // un label
    // est une variable d'instance car il doit �tre accessible
    // dans la m�thode changeColor, qui g�re les pr�f�rences
    private JTextArea lb1;

    // appel au constructeur de la classe m�re
    public Regles(String title) {
        super(title);
    }

    // renvoie le fichier wave contenant le message d'accueil
    protected  String wavAccueil() {
        return "../ressources/sons/accueilSon.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected  String wavRegleJeu() {
        return "../ressources/sons/aideF1.wav";
    }

    // renvoie le fichier wave contenant la r�gle du jeu
    protected  String wavAide() {
        return "../ressources/sons/aide.wav";
    }

    // d�finition de la m�thode abstraite "init()"
    // initialise le frame 
    protected void init() {
        setLayout(new BorderLayout());

        // premier label
        // ce label est g�r� par les pr�f�rences (cf m�thode changeColor)
        String text = "Pour jouer, branchez les manettes � votre ordinateur. Quand l'ordinateur les aura reconues appuyez sur le bouton jouer du menu principal.\n" +
                "  Vous pouvez jouer jusqu'� quatre joueurs, chaque joueur devra appuyer UNE fois sur le bouton rouge pour dire qu'il participe, puis encore une fois pour lancer la partie.\n" +
                "  Pour r�pondre aux questions utiliser 4 petits boutons";
        lb1 = new JTextArea (text);
        lb1.setLineWrap(true);
        lb1.setEditable(false);
        lb1.setFont(new Font("Georgia",1,30));
        // on r�cup�re les couleurs de base dans la classe Preferences
        Preferences pref = Preferences.getData();
        Color foregroundColor = pref.getCurrentForegroundColor();
        Color backgroundColor = pref.getCurrentBackgroundColor();
        lb1.setBackground(backgroundColor);
        lb1.setForeground(foregroundColor);
        this.add(lb1,BorderLayout.CENTER);

    }

    /**
     * Pour modifier les couleurs de fond et de premier plan de la fen�tre
     * Cette fonction est appel�e par la fonction "changeColor" de la classe "Preferences"
     * � chaque fois que l'on presse F3
     *
     * on change la couleur du texte principal
     **/
    public  void changeColor() {
        // on r�cup�re les couleurs de base dans la classe Preferences
        Preferences pref = Preferences.getData();
        lb1.setBackground(pref.getCurrentBackgroundColor());
        lb1.setForeground(pref.getCurrentForegroundColor());
    }

    @Override
    public void changeSize() {
        // TODO Auto-generated method stub
    }

}
