package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Partie;
import vue.FenetreMenu;
import vue.FenetrePartie;



/**
 * La classe ControleurPartie est une classe du contr�leur servant � relier le mod�le (et plus particuli�rement la classe Partie) et
 * la vue (et plus particuli�rement la classe FenetrePartie).
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class ControleurPartie {
	
	// *****************
	// *** ATTRIBUTS ***
	// *****************
	
	/**
	 * La partie (mod�le) du contr�leur partie.
	 */
	private Partie partie;
	/**
	 * La fen�tre partie (vue) du contr�leur partie.
	 */
	private FenetrePartie fenetrePartie;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe ControleurPartie.
	 * 
	 * @param partie Partie du contr�leur partie.
	 * @param fenetrePartie Fen�tre partie du contr�leur partie.
	 */
	public ControleurPartie(Partie partie, FenetrePartie fenetrePartie) {
		this.partie = partie;
		this.fenetrePartie = fenetrePartie;
		
		this.ajouterListeners(fenetrePartie);
	}
	
	
	
	// ************************************
	// *** M�THODE D'AJOUT DE LISTENERS ***
	// ************************************
	
	/**
	 * M�thode permettant d'ajouter tous les listeners du contr�leur partie � la fen�tre partie.
	 * 
	 * @param fenetrePartie Fen�tre partie du contr�leur partie.
	 */
	private void ajouterListeners(FenetrePartie fenetrePartie) {
		this.fenetrePartie.ajouterPiocheListener(new PiocheListener());
		this.fenetrePartie.ajouterUnoListener(new UnoListener());
		this.fenetrePartie.ajouterCartesAGaucheListener(new CartesAGaucheListener());
		this.fenetrePartie.ajouterCarte1Listener(new Carte1Listener());
		this.fenetrePartie.ajouterCarte2Listener(new Carte2Listener());
		this.fenetrePartie.ajouterCarte3Listener(new Carte3Listener());
		this.fenetrePartie.ajouterCarte4Listener(new Carte4Listener());
		this.fenetrePartie.ajouterCarte5Listener(new Carte5Listener());
		this.fenetrePartie.ajouterCartesADroiteListener(new CartesADroiteListener());
		this.fenetrePartie.ajouterSauvegarderPartieListener(new SauvegarderPartieListener());
		this.fenetrePartie.ajouterQuitterListener(new QuitterListener());
	}
	
	
	
	// **********************************************************
	// *** LISTENER DE COMPOSANTS GRAPHIQUES DE TAS DE CARTES ***
	// **********************************************************
	
	private class PiocheListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	
	// ***********************************************************************
	// *** LISTENERS DE COMPOSANTS GRAPHIQUES DES INTERACTIONS AVEC LE JEU ***
	// ***********************************************************************
	
	private class UnoListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class CartesAGaucheListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class Carte1Listener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class Carte2Listener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class Carte3Listener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class Carte4Listener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class Carte5Listener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class CartesADroiteListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	
	// ***************************************************************
	// *** LISTENER DE COMPOSANTS GRAPHIQUES DES BOUTONS DE PARTIE ***
	// ***************************************************************
	
	private class SauvegarderPartieListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class QuitterListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			fenetrePartie.dispose();
			FenetreMenu fenetreMenu = new FenetreMenu(partie);
			new ControleurMenu(partie, fenetreMenu);
		}
	}
	
}