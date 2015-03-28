package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import modele.Carte;
import modele.CarteInversion;
import modele.CarteJoker;
import modele.CarteNormale;
import modele.CartePasserUnTour;
import modele.CartePlusDeux;
import modele.CartePlusQuatre;
import modele.Partie;



/**
 * La classe FenetrePartie est une classe de la vue servant à afficher l'interface de partie avec laquelle le joueur interagit, après
 * avoir sélectionné les paramètres souhaités sur l'interface de menu.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class FenetrePartie extends JFrame implements Observer {
	
	// *****************
	// *** ATTRIBUTS ***
	// *****************
	
	/**
	 * Affecte un numéro de version à la classe.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * La partie (modèle) correspondant à la fenêtre partie (vue).
	 */
	private Partie partie;
	
	private JLabel lbJoueurCourant;
	private JButton bpPioche;
	private JLabel lbTalon;
	private JLabel lbJoueurSuivant;
	
	private JButton bpUno;
	private JButton bpCartesAGauche;
	private JButton bpCarte1;
	private JButton bpCarte2;
	private JButton bpCarte3;
	private JButton bpCarte4;
	private JButton bpCarte5;
	private JButton bpCartesADroite;
	
	private JButton bpSauvegarderPartie;
	private JButton bpQuitter;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe FenetrePartie.
	 * 
	 * @param partie Partie correspondant à la fenêtre partie.
	 */
	public FenetrePartie(Partie partie) {
		this.partie = partie;
		this.partie.addObserver(this);
		
		this.initialiserComposantsGraphiques();
		this.placerComposantsGraphiques();
		
		this.setTitle("Funo - Fenêtre de jeu");
		this.setLocation(200, 200);
		this.setPreferredSize(new Dimension (800, 460));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	
	
	// **********************************************************
	// *** MÉTHODES D'INITIALISATION DE COMPOSANTS GRAPHIQUES ***
	// **********************************************************
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques de la fenêtre partie.
	 */
	public void initialiserComposantsGraphiques() {
		this.initialiserComposantsGraphiquesTasDeCartes();
		this.initialiserComposantsGraphiquesInteractionsJeu();
		this.initialiserComposantsGraphiquesBoutonsPartie();
	}
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques de tas de cartes de la fenêtre partie.
	 */
	public void initialiserComposantsGraphiquesTasDeCartes() {
		UIManager.put("Label.font", new Font("Calibri", Font.PLAIN, 13));
		this.lbJoueurCourant = new JLabel("Joueur ..., à vous de jouer !");
		this.bpPioche = new JButton(new ImageIcon("img/Cartes/Arrière.JPG"));
		this.lbTalon = new JLabel(new ImageIcon("img/Cartes/Bleues/6.png"));
		this.lbJoueurSuivant = new JLabel("Prochain joueur : ...");
	}
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques des interactions avec le jeu de la fenêtre partie.
	 */
	public void initialiserComposantsGraphiquesInteractionsJeu() {
		this.bpUno = new JButton("UNO !");
		this.bpCartesAGauche = new JButton("<<");
		this.bpCarte1 = new JButton(new ImageIcon("img/Cartes/Bleues/1.png"));
		this.bpCarte2 = new JButton(new ImageIcon("img/Cartes/Jaunes/PasserUnTour.png"));
		this.bpCarte3 = new JButton(new ImageIcon("img/Cartes/Neutres/+4.png"));
		this.bpCarte4 = new JButton(new ImageIcon("img/Cartes/Rouges/6.png"));
		this.bpCarte5 = new JButton(new ImageIcon("img/Cartes/Vertes/+2.png"));
		this.bpCartesADroite = new JButton(">>");
	}
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques des boutons de partie de la fenêtre partie.
	 */
	public void initialiserComposantsGraphiquesBoutonsPartie() {
		this.bpSauvegarderPartie = new JButton("Sauvegarder partie");
		this.bpQuitter = new JButton("Quitter");
	}
	
	
	// ******************************************************
	// *** MÉTHODES DE PLACEMENT DE COMPOSANTS GRAPHIQUES ***
	// ******************************************************
	
	/**
	 * Méthode permettant de placer les composants graphiques de la fenêtre partie.
	 */
	public void placerComposantsGraphiques() {
		ImageIcon fond = new ImageIcon("img/Fonds/FondCiel.png");
		this.setContentPane(new ConteneurFond(fond.getImage()));
	    this.getContentPane().setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets (5, 5, 5, 5);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    
	    gbc = this.placerComposantsGraphiquesTasDeCartes(gbc);
	    gbc = this.placerComposantsGraphiquesInteractionsJeu(gbc);
	    gbc = this.placerComposantsGraphiquesBoutonsPartie(gbc);
	}
	
	/**
	 * Méthode permettant de placer les composants graphiques de tas de cartes de la fenêtre partie.
	 * 
	 * @param gbc Contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 * @return Nouvelles contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 */
	public GridBagConstraints placerComposantsGraphiquesTasDeCartes(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.CENTER;
	    gbc.weightx = 1.0;
	    gbc.weighty = 0.0;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    this.getContentPane().add(this.lbJoueurCourant, gbc);
	    gbc.gridwidth = 1;
	    gbc.gridx = 2;
	    gbc.gridy = 0;
		this.getContentPane().add(this.bpPioche, gbc);
		gbc.gridx = 4;
		this.getContentPane().add(this.lbTalon, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 5;
		gbc.gridy = 1;
		this.getContentPane().add(this.lbJoueurSuivant, gbc);
		
		return gbc;
	}
	
	/**
	 * Méthode permettant de placer les composants graphiques des interactions avec le jeu de la fenêtre partie.
	 * 
	 * @param gbc Contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 * @return Nouvelles contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 */
	public GridBagConstraints placerComposantsGraphiquesInteractionsJeu(GridBagConstraints gbc) {
		gbc.gridwidth = 1;
	    gbc.gridx = 3;
	    gbc.gridy = 2;
	    this.getContentPane().add(this.bpUno, gbc);
	    gbc.gridx = 0;
	    gbc.gridy = 3;
		this.getContentPane().add(this.bpCartesAGauche, gbc);
		gbc.gridx = 1;
		this.getContentPane().add(this.bpCarte1, gbc);
		gbc.gridx = 2;
		this.getContentPane().add(this.bpCarte2, gbc);
		gbc.gridx = 3;
		this.getContentPane().add(this.bpCarte3, gbc);
		gbc.gridx = 4;
		this.getContentPane().add(this.bpCarte4, gbc);
		gbc.gridx = 5;
		this.getContentPane().add(this.bpCarte5, gbc);
		gbc.gridx = 6;
		this.getContentPane().add(this.bpCartesADroite, gbc);
		
		return gbc;
	}
	
	/**
	 * Méthode permettant de placer les composants graphiques des boutons de partie de la fenêtre partie.
	 * 
	 * @param gbc Contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 * @return Nouvelles contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 */
	public GridBagConstraints placerComposantsGraphiquesBoutonsPartie(GridBagConstraints gbc) {
		gbc.gridwidth = 2;
		gbc.gridx = 0;
	    gbc.gridy = 4;
	    this.getContentPane().add(this.bpSauvegarderPartie, gbc);
	    gbc.gridwidth = 1;
	    gbc.gridx = 6;
		this.getContentPane().add(this.bpQuitter, gbc);
		
		return gbc;
	}
	
	
	
	// ****************************************
	// *** MÉTHODE DE MISE À JOUR DE LA VUE ***
	// ****************************************
	
	/**
	 * Méthode permettant de mettre à jour la vue si une notification provient du modèle, la fenêtre de partie étant la vue
	 * et la partie étant le modèle.
	 */
	public void update(Observable modele, Object objet) {
		if (objet.equals("Création de la manche"))
			this.partie.getManche(this.partie.getManches().size()-1).addObserver(this);
		else if (objet.equals("Fin de la distribution des cartes") || objet.equals("Fin du tour du joueur"))
			this.lbTalon = new JLabel(new ImageIcon(this.getCheminImageCarte(this.partie.getManche(this.partie.getManches().size()-1).getTalon().peek())));
		repaint();
	}
	
	
	
	// ***************************
	// *** MÉTHODE D'AFFICHAGE ***
	// ***************************
	
	/**
	 * Méthode permettant de récupérer le chemin de l'image d'une carte à partir des données de la carte.
	 * 
	 * @param carte Carte dont on souhaite récupérer l'image.
	 * @return Chemin de l'image de la carte que l'on souhaite récupérer.
	 */
	public String getCheminImageCarte(Carte carte) {
		String chemin = "img/Cartes/";
		
		if (carte instanceof CarteNormale) {
			if (carte.getCouleur() == "bleu")
				chemin += "Bleues/" + ((CarteNormale) carte).getNumero() + ".png";
			else if (carte.getCouleur() == "rouge")
				chemin += "Rouges/" + ((CarteNormale) carte).getNumero() + ".png";
			else if (carte.getCouleur() == "jaune")
				chemin += "Jaunes/" + ((CarteNormale) carte).getNumero() + ".png";
			else if (carte.getCouleur() == "vert")
				chemin += "Vertes/" + ((CarteNormale) carte).getNumero() + ".png";
		} else {
			if (carte instanceof CartePlusDeux) {
				if (carte.getCouleur() == "bleu")
					chemin += "Bleues/+2.png";
				else if (carte.getCouleur() == "rouge")
					chemin += "Rouges/+2.png";
				else if (carte.getCouleur() == "jaune")
					chemin += "Jaunes/+2.png";
				else if (carte.getCouleur() == "vert")
					chemin += "Vertes/+2.png";
			} else if (carte instanceof CarteInversion) {
				if (carte.getCouleur() == "bleu")
					chemin += "Bleues/Inversion.png";
				else if (carte.getCouleur() == "rouge")
					chemin += "Rouges/Inversion.png";
				else if (carte.getCouleur() == "jaune")
					chemin += "Jaunes/Inversion.png";
				else if (carte.getCouleur() == "vert")
					chemin += "Vertes/Inversion.png";
			} else if (carte instanceof CartePasserUnTour) {
				if (carte.getCouleur() == "bleu")
					chemin += "Bleues/PasserUnTour.png";
				else if (carte.getCouleur() == "rouge")
					chemin += "Rouges/PasserUnTour.png";
				else if (carte.getCouleur() == "jaune")
					chemin += "Jaunes/PasserUnTour.png";
				else if (carte.getCouleur() == "vert")
					chemin += "Vertes/PasserUnTour.png";
			} else if (carte instanceof CarteJoker) {
				chemin += "Neutres/Joker.png";
			} else if (carte instanceof CartePlusQuatre) {
				chemin += "Neutres/+4.png";
			}
		}
		return chemin;
	}
	
	
	
	// ************************************
	// *** MÉTHODES D'AJOUT DE LISTENER ***
	// ************************************
	
	public void ajouterPiocheListener(ActionListener al) {
		this.bpPioche.addActionListener(al);
	}
	
	public void ajouterUnoListener(ActionListener al) {
		this.bpUno.addActionListener(al);
	}
	
	public void ajouterCartesAGaucheListener(ActionListener al) {
		this.bpCartesAGauche.addActionListener(al);
	}
	
	public void ajouterCarte1Listener(ActionListener al) {
		this.bpCarte1.addActionListener(al);
	}
	
	public void ajouterCarte2Listener(ActionListener al) {
		this.bpCarte2.addActionListener(al);
	}
	
	public void ajouterCarte3Listener(ActionListener al) {
		this.bpCarte3.addActionListener(al);
	}
	
	public void ajouterCarte4Listener(ActionListener al) {
		this.bpCarte4.addActionListener(al);
	}
	
	public void ajouterCarte5Listener(ActionListener al) {
		this.bpCarte5.addActionListener(al);
	}
	
	public void ajouterCartesADroiteListener(ActionListener al) {
		this.bpCartesADroite.addActionListener(al);
	}
	
	public void ajouterSauvegarderPartieListener(ActionListener al) {
		this.bpSauvegarderPartie.addActionListener(al);
	}
	
	public void ajouterQuitterListener(ActionListener al) {
		this.bpQuitter.addActionListener(al);
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Partie getPartie() {
		return partie;
	}

	public JLabel getLbJoueurCourant() {
		return lbJoueurCourant;
	}

	public JButton getBpPioche() {
		return bpPioche;
	}

	public JLabel getLbTalon() {
		return lbTalon;
	}

	public JLabel getLbJoueurSuivant() {
		return lbJoueurSuivant;
	}

	public JButton getBpUno() {
		return bpUno;
	}

	public JButton getBpCartesAGauche() {
		return bpCartesAGauche;
	}

	public JButton getBpCarte1() {
		return bpCarte1;
	}

	public JButton getBpCarte2() {
		return bpCarte2;
	}

	public JButton getBpCarte3() {
		return bpCarte3;
	}

	public JButton getBpCarte4() {
		return bpCarte4;
	}

	public JButton getBpCarte5() {
		return bpCarte5;
	}

	public JButton getBpCartesADroite() {
		return bpCartesADroite;
	}

	public JButton getBpSauvegarderPartie() {
		return bpSauvegarderPartie;
	}

	public JButton getBpQuitter() {
		return bpQuitter;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public void setLbJoueurCourant(JLabel lbJoueurCourant) {
		this.lbJoueurCourant = lbJoueurCourant;
	}

	public void setBpPioche(JButton bpPioche) {
		this.bpPioche = bpPioche;
	}

	public void setLbTalon(JLabel lbTalon) {
		this.lbTalon = lbTalon;
	}

	public void setLbJoueurSuivant(JLabel lbJoueurSuivant) {
		this.lbJoueurSuivant = lbJoueurSuivant;
	}

	public void setBpUno(JButton bpUno) {
		this.bpUno = bpUno;
	}

	public void setBpCartesAGauche(JButton bpCartesAGauche) {
		this.bpCartesAGauche = bpCartesAGauche;
	}

	public void setBpCarte1(JButton bpCarte1) {
		this.bpCarte1 = bpCarte1;
	}

	public void setBpCarte2(JButton bpCarte2) {
		this.bpCarte2 = bpCarte2;
	}

	public void setBpCarte3(JButton bpCarte3) {
		this.bpCarte3 = bpCarte3;
	}

	public void setBpCarte4(JButton bpCarte4) {
		this.bpCarte4 = bpCarte4;
	}

	public void setBpCarte5(JButton bpCarte5) {
		this.bpCarte5 = bpCarte5;
	}

	public void setBpCartesADroite(JButton bpCartesADroite) {
		this.bpCartesADroite = bpCartesADroite;
	}

	public void setBpSauvegarderPartie(JButton bpSauvegarderPartie) {
		this.bpSauvegarderPartie = bpSauvegarderPartie;
	}

	public void setBpQuitter(JButton bpQuitter) {
		this.bpQuitter = bpQuitter;
	}
	
}