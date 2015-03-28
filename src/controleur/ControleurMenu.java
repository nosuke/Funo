package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vue.FenetreMenu;
import vue.FenetrePartie;
import modele.Partie;



/**
 * La classe ControleurMenu est une classe du contrôleur servant à relier le modèle (et plus particulièrement la classe Partie) et
 * la vue (et plus particulièrement la classe FenetreMenu).
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class ControleurMenu {
	
	// *****************
	// *** ATTRIBUTS ***
	// *****************
	
	/**
	 * La partie (modèle) du contrôleur menu.
	 */
	private Partie partie;
	/**
	 * La fenêtre menu (vue) du contrôleur menu.
	 */
	private FenetreMenu fenetreMenu;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe ControleurMenu.
	 * 
	 * @param partie Partie du contrôleur menu.
	 * @param fenetreMenu Fenêtre menu du contrôleur menu.
	 */
	public ControleurMenu(Partie partie, FenetreMenu fenetreMenu) {
		this.partie = partie;
		this.fenetreMenu = fenetreMenu;
		
		this.ajouterListeners(fenetreMenu);
	}
	
	
	
	// ************************************
	// *** MÉTHODE D'AJOUT DE LISTENERS ***
	// ************************************
	
	/**
	 * Méthode permettant d'ajouter tous les listeners du contrôleur menu à la fenêtre menu.
	 * 
	 * @param fenetreMenu Fenêtre menu du contrôleur menu.
	 */
	private void ajouterListeners(FenetreMenu fenetreMenu) {
		this.fenetreMenu.ajouterModeJeuEquipesListener(new ModeJeuEquipesListener());
		this.fenetreMenu.ajouterTypeNouveauJoueurHumainListener(new TypeNouveauJoueurHumainListener());
		this.fenetreMenu.ajouterTypeNouveauJoueurVirtuelListener(new TypeNouveauJoueurVirtuelListener());
		this.fenetreMenu.ajouterAjouterJoueurListener(new AjouterJoueurListener());
		this.fenetreMenu.ajouterListeJoueursListSelectionListener(new ListeJoueursListSelectionListener());
		this.fenetreMenu.ajouterSupprimerJoueurListener(new SupprimerJoueurListener());
		this.fenetreMenu.ajouterTypeJoueurSelectionneHumainListener(new TypeJoueurSelectionneHumainListener());
		this.fenetreMenu.ajouterTypeJoueurSelectionneVirtuelListener(new TypeJoueurSelectionneVirtuelListener());
		this.fenetreMenu.ajouterModifierJoueurListener(new ModifierJoueurListener());
		this.fenetreMenu.ajouterChargerPartieListener(new ChargerPartieListener());
		this.fenetreMenu.ajouterLancerPartieListener(new LancerPartieListener());
		this.fenetreMenu.ajouterQuitterListener(new QuitterListener());
	}
	
	
	
	// ************************************************************
	// *** LISTENER DE COMPOSANTS GRAPHIQUES DE CHOIX DES MODES ***
	// ************************************************************
	
	private class ModeJeuEquipesListener implements ItemListener {
		public void itemStateChanged(ItemEvent ie) {
			if (ie.getItem() == "Équipes") {
				fenetreMenu.getLbEquipeNouveauJoueur().setVisible(true);
				fenetreMenu.getTfEquipeNouveauJoueur().setVisible(true);
			} else {
				fenetreMenu.getLbEquipeNouveauJoueur().setVisible(false);
				fenetreMenu.getTfEquipeNouveauJoueur().setVisible(false);
			}
		}
	}
	
	
	// ************************************************************
	// *** LISTENERS DE COMPOSANTS GRAPHIQUES DE NOUVEAU JOUEUR ***
	// ************************************************************
	
	private class TypeNouveauJoueurHumainListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			fenetreMenu.getLbPrenomNouveauJoueurHumain().setVisible(true);
			fenetreMenu.getTfPrenomNouveauJoueurHumain().setVisible(true);
			fenetreMenu.getLbStrategieNouveauJoueurVirtuel().setVisible(false);
			fenetreMenu.getpStrategieNouveauJoueurVirtuel().setVisible(false);
		}
	}
	
	private class TypeNouveauJoueurVirtuelListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			fenetreMenu.getLbStrategieNouveauJoueurVirtuel().setVisible(true);
			fenetreMenu.getpStrategieNouveauJoueurVirtuel().setVisible(true);
			fenetreMenu.getLbPrenomNouveauJoueurHumain().setVisible(false);
			fenetreMenu.getTfPrenomNouveauJoueurHumain().setVisible(false);
		}
	}
	
	private class AjouterJoueurListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (fenetreMenu.getListeNomsJoueurs().contains(fenetreMenu.getTfNomNouveauJoueur().getText()))
				fenetreMenu.afficherMessageDErreur("Erreur : nom du nouveau joueur", "Veuillez choisir un autre nom de joueur. Celui-ci est déjà pris !");
			else if (fenetreMenu.getTfNomNouveauJoueur().getText().equals(null) || fenetreMenu.getTfNomNouveauJoueur().getText().equals(""))
				fenetreMenu.afficherMessageDErreur("Erreur : nom du nouveau joueur", "Vous devez saisir un nom pour ce joueur.");
			else if (fenetreMenu.getBrTypeNouveauJoueurHumain().isSelected() && (fenetreMenu.getTfPrenomNouveauJoueurHumain().getText().equals(null) || fenetreMenu.getTfPrenomNouveauJoueurHumain().getText().equals("")))
				fenetreMenu.afficherMessageDErreur("Erreur : prénom du nouveau joueur humain", "En tant que joueur humain, vous devez saisir un prénom.");
			else
				fenetreMenu.ajouterJoueurDansListe();
		}
	}
	
	
	// ****************************************************************************
	// *** LISTENERS DE COMPOSANTS GRAPHIQUES DE LISTE DE SÉLECTION DES JOUEURS ***
	// ****************************************************************************
	
	private class ListeJoueursListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {
			if (fenetreMenu.getLsListeJoueurs().getSelectedValue() != null)
				fenetreMenu.afficherJoueurSelectionne();
		}
	}
	
	private class SupprimerJoueurListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			fenetreMenu.supprimerJoueurDeListe();
		}
	}
	
	
	// ****************************************************************
	// *** LISTENERS DE COMPOSANTS GRAPHIQUES DE JOUEUR SÉLECTIONNÉ ***
	// ****************************************************************
	
	private class TypeJoueurSelectionneHumainListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			fenetreMenu.getLbPrenomJoueurSelectionneHumain().setVisible(true);
			fenetreMenu.getTfPrenomJoueurSelectionneHumain().setVisible(true);
			fenetreMenu.getLbStrategieJoueurSelectionneVirtuel().setVisible(false);
			fenetreMenu.getpStrategieJoueurSelectionneVirtuel().setVisible(false);
		}
	}
	
	private class TypeJoueurSelectionneVirtuelListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			fenetreMenu.getLbStrategieJoueurSelectionneVirtuel().setVisible(true);
			fenetreMenu.getpStrategieJoueurSelectionneVirtuel().setVisible(true);
			fenetreMenu.getLbPrenomJoueurSelectionneHumain().setVisible(false);
			fenetreMenu.getTfPrenomJoueurSelectionneHumain().setVisible(false);
		}
	}
	
	private class ModifierJoueurListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (fenetreMenu.getListeNomsJoueurs().contains(fenetreMenu.getTfNomJoueurSelectionne().getText()) && !(fenetreMenu.getTfNomJoueurSelectionne().getText().equals(fenetreMenu.getAlListeJoueurs().get(fenetreMenu.getIndexNomJoueurSelectionne()).getNom())))
				fenetreMenu.afficherMessageDErreur("Erreur : nouveau nom du joueur sélectionné", "Veuillez choisir un autre nom de joueur. Celui-ci est déjà pris !");
			else if (fenetreMenu.getTfNomJoueurSelectionne().getText().equals(null) || fenetreMenu.getTfNomJoueurSelectionne().getText().equals(""))
				fenetreMenu.afficherMessageDErreur("Erreur : nouveau nom du joueur sélectionné", "Vous devez saisir un nom pour ce joueur.");
			else if (fenetreMenu.getBrTypeJoueurSelectionneHumain().isSelected() && (fenetreMenu.getTfPrenomJoueurSelectionneHumain().getText().equals(null) || fenetreMenu.getTfPrenomJoueurSelectionneHumain().getText().equals("")))
				fenetreMenu.afficherMessageDErreur("Erreur : nouveau nom du joueur humain sélectionné", "En tant que joueur humain, vous devez saisir un prénom.");
			else
				fenetreMenu.modifierJoueurDeListe();
		}
	}
	
	
	// ***************************************************************
	// *** LISTENER DE COMPOSANTS GRAPHIQUES DES BOUTONS DE PARTIE ***
	// ***************************************************************
	
	private class ChargerPartieListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// TODO
		}
	}
	
	private class LancerPartieListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (fenetreMenu.getAlListeJoueurs().size() < 2) {
				fenetreMenu.afficherMessageDErreur("Erreur : nombre de joueurs", "Pour jouer une partie de Funo, le minimum de joueurs doit être de 2 ! Veuillez en ajouter suffisamment pour atteindre cette somme.");
			} else if (fenetreMenu.getAlListeJoueurs().size() > 10) {
				fenetreMenu.afficherMessageDErreur("Erreur : nombre de joueurs", "Pour jouer une partie de Funo, le maximum de joueurs doit être de 10 ! Veuillez en enlever suffisamment pour atteindre cette somme.");
			} else {
				String modeJeuChoisi = (String) fenetreMenu.getCbModeJeu().getSelectedItem();
				if (modeJeuChoisi.equals("Traditionnel"))
					partie.setModeJeu("traditionnel");
				else if (modeJeuChoisi.equals("Uno Challenge"))
					partie.setModeJeu("unoChallenge");
				else if (modeJeuChoisi.equals("Équipes"))
					partie.setModeJeu("equipes");
				
				if (fenetreMenu.getBrAttributionPointsPositif().isSelected())
					partie.setModeAttribution("positif");
				else if (fenetreMenu.getBrAttributionPointsNegatif().isSelected())
					partie.setModeAttribution("negatif");
				
				partie.setJoueurs(fenetreMenu.getAlListeJoueurs());
				
				fenetreMenu.dispose();
				FenetrePartie fenetrePartie = new FenetrePartie(partie);
				new ControleurPartie(partie, fenetrePartie);
				// partie.seJouer("modeGraphique");
			}
		}
	}
	
	private class QuitterListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			System.exit(0);
		}
	}
	
}