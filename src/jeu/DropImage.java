package jeu;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import devintAPI.FenetreAbstraite;

import java.awt.*;
import java.awt.event.*;

/** Etend DevintFrame pour avoir un Frame et réagir aux évènements claviers
 * Contient un exemple d'affichage d'image proportionnel à la taille de l'écran
 * 
 * implémente MouseInputListener pour pouvoir faire un drag and drop
 * 
 * @author helene
 *
 */

public class DropImage extends FenetreAbstraite implements MouseInputListener  {

	// taille de la fenêtre
	protected int largeur;
	protected int hauteur;

	// les marges associées au panel
	Insets ins;

	// position de la souris sur Théo au moment où l'on presse
	// NOTA : Il y a une seule image, si vous voulez gérer plusieurs images, 
	// il faut avoir un tableau pour les 3 informations ci-dessous
	Point positionTheo ; // pour sauver la position de Théo
	Color couleurTheo; // pour sauver la couleur initiale du fond de Théo

	// l'image de Théo
	JLabel theo ;
	
	// label résultat
	JLabel labelGagne; 
	
	// texte du lab
    public DropImage(String title) {
    	super(title);
     }

	// renvoie le fichier wave contenant le message d'accueil
	protected  String wavAccueil() {
		return "../ressources/sons/accueilImage.wav";
	}
	
	// renvoie le fichier wave contenant la régle du jeu
	protected  String wavRegleJeu() {
		return "../ressources/sons/accueilImage.wav";
	}
	
	// renvoie le fichier wave contenant la régle du jeu
	protected  String wavAide() {
		return "../ressources/sons/aide.wav";
	}
    
    // initialise le frame 
    protected void init() {
    	// layout est null pour pouvoir positionner les éléments à la main et éviter les problèmes de repaint()
    	// il faut positionner les éléments avec setBounds 
    	this.setLayout(null);
    	ins = this.getInsets();
    	
    	// la largeur et la hauteur actuelle de la fenêtre
    	// si vous fixez la taille des éléments graphiques 
    	// faites le en utilisant des valeurs proportionnelles à la taille
    	// de la fenêtre pour que différentes résolutions d'écran soient possibles
    	Dimension taille = this.getToolkit().getScreenSize();
    	largeur = taille.width-ins.left-ins.right;
    	hauteur = taille.height-ins.bottom-ins.top;
    	
    	// label d'explication de la fenêtre
    	String  texte = "\nLe layout est null pour pouvoir positionner les éléments à la main et éviter les problèmes de repaint().";
    	texte += "\nLa taille des composants est celle de \"setPreferredSize\" ou bien la taille optimale pour obtenir un frame le plus petit possible.";
    	texte += "\n\nVoici Théo du jeu Léa et Théo, 2007.";
    	texte += "\nThéo est dans un label a un fond vert qui occupe le tiers de la largeur de la fenêtre et la moitié de sa hauteur.";
    	texte += "\n\nESSAYEZ DE DEPLACER THEO !!!";
    	JTextArea labelTexte = new JTextArea(texte);
    	labelTexte.setEditable(false);
    	labelTexte.setLineWrap(true);
    	// taille : (largeur de la fenetre)/3 et (hauteur fenetre)/2
    	labelTexte.setPreferredSize(new Dimension(largeur/3,hauteur/2));
    	this.add(labelTexte);
    	Dimension sizeTexte = labelTexte.getPreferredSize();
    	// on le positionne dans l'angle en haut à gauche avec 
    	// une marge de 5 en plus de la marge du panel
    	labelTexte.setBounds(5+ins.left,5+ins.top,sizeTexte.width,sizeTexte.height);

    	// label pour afficher où est placé Théo quand on fait un drag
    	// (gauche / milieu / droite)
    	labelGagne = new JLabel("Place de Théo");
    	labelGagne.setBackground(Color.YELLOW);
    	labelGagne.setOpaque(true);
    	this.add(labelGagne);
    	labelGagne.setPreferredSize(new Dimension(200,100));
    	Dimension sizeGagne = labelGagne.getPreferredSize();
    	// on le positionne en haut après le label de texte
    	labelGagne.setBounds(ins.left+sizeTexte.width+100,5+ins.top,sizeGagne.width,sizeGagne.height);

    	// Théo
    	ImageIcon icon = new ImageIcon("../ressources/images/theo.JPG");
    	// on met l'image dans un label
    	theo = new JLabel(icon);
    	// fond vert
    	theo.setBackground(Color.GREEN);
    	//composant opaque pour voir le fond vert
    	theo.setOpaque(true); 
    	// taille : (largeur de la fenetre)/3 et (hauteur fenetre)/2
    	theo.setPreferredSize(new Dimension(largeur/3,hauteur/2));
    	this.add(theo);
    	Dimension theoSize = theo.getPreferredSize();
    	// on le positionne après le label de position de Théo
    	theo.setBounds(sizeTexte.width + sizeGagne.width +ins.left+100,100+ins.top,theoSize.width,theoSize.height);

    	// séparateur pour marquer le milieu de la fenêtre
    	JSeparator js = new JSeparator(JSeparator.VERTICAL);
    	this.add(js);
    	int x =largeur/2+ins.left;
    	js.setBounds(x,ins.top,x+5,ins.top+hauteur);
    	
    	// c'est la classe DropImage qui réagit elle-même aux évènements souris
    	theo.addMouseListener(this);
    	theo.addMouseMotionListener(this);
    	}

	@Override
	/** 
	 * pour cette fen�tre, changer la couleur n'a pas de sens, alors la m�thode
	 * ne fait rien
	 */
	public void changeColor() {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Component source = (Component) e.getSource();
		couleurTheo = source.getBackground(); // sauve la couleur du fond de l'image
		source.setBackground(Color.yellow); // met la couleur jaune pendant le drag
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Component source = (Component) e.getSource();
		source.setBackground(couleurTheo); // remet le background initial quand on lache la souris
	}

	@Override
	public void mousePressed(MouseEvent e) {
		positionTheo = e.getPoint(); 
	}

	@Override
	// quand on lache la souris on regarde où est Théo, à droite
	// ou à gauche du milieu
	public void mouseReleased(MouseEvent e) {
		Component source = (Component) e.getSource();
		// source.getX() est la coordonnée x la plus à gauche du composant sélectionné
		int bordGauche = source.getX();
		int bordDroit = bordGauche + source.getWidth();
		String texteLabelGagne;
		if (bordDroit<largeur/2+ins.left)
			texteLabelGagne="gauche";
		else
			if (bordGauche > largeur/2+ins.left)
				texteLabelGagne="droite";
			else
				texteLabelGagne="milieu";
		labelGagne.setText(texteLabelGagne);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// on déplace l'image
		// newP : position actuelle de la souris enfoncée
		Point newP = e.getPoint();
		// image qu'on déplace
		Component source = (Component) e.getSource();
		// newP.x - positionTheo.x : distance du déplacement suivant x 
		int x = source.getX()+newP.x-positionTheo.x;
		int y = source.getY()+newP.y-positionTheo.y;
		source.setLocation(x,y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void changeSize() {
		// TODO Auto-generated method stub
	}


}

