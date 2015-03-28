package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;



/**
 * La classe Partie est une classe permettant l'instanciation de la partie.
 * Une partie est surtout compos�e de plusieurs joueurs et de plusieurs manches.
 * Elle comporte aussi un gagnant, d�sign�e en fin de partie, et on lui attribue un mode de jeu et un mode d'attribution
 * des points, choisis en d�but de partie.
 * De plus, cette classe est observable, �tant la classe principale du mod�le. Elle peut ainsi notifier les observateurs,
 * c'est-�-dire la vue, lors de changements du mod�le.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class Partie extends Observable {
	
	// *****************
	// *** ATTRIBUTS ***
	// *****************
	
	/**
	 * Le joueur gagnant d'une partie.
	 */
	private Joueur gagnant;
	/**
	 * Le mode de jeu d'une partie.
	 */
	private String modeJeu;
	/**
	 * Le mode d'attribution de points d'une partie.
	 */
	private String modeAttribution;
	/**
	 * La liste de joueurs d'une partie.
	 */
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	/**
	 * La liste de manches d'une partie.
	 */
	private ArrayList<Manche> manches = new ArrayList<Manche>();
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe Partie.
	 * 
	 * @param modeExecution Mode d'ex�cution choisi au d�marrage du logiciel.
	 */
	public Partie(String modeExecution) {
		if (modeExecution.equals("modeConsole"))
			this.commencerPartieModeConsole();
		this.effacerEcranConsole();
	}
	
	
	
	// ****************************
	// *** M�THODES PRINCIPALES ***
	// ****************************
	
	/**
	 * M�thode se d�clenchant pour indiquer le commencement d'une partie.
	 * Fonctionne uniquement pour le mode console.
	 */
	public void commencerPartieModeConsole() {
		int nombreJoueurs = 0;
		
		System.out.println("Bienvenue sur le jeu Funo !");
		System.out.println("Avant de commencer, il est n�cessaire que vous selectionniez les diff�rents param�tres d'une partie.\n");
		System.out.println("En premier lieu, veuillez saisir le nombre de joueurs qui joueront la partie. (Valeurs possibles : de 2 � 10.)");
		nombreJoueurs = this.saisirEntier(2, 10);
		
		this.ajouterJoueurs(nombreJoueurs);
		System.out.println();
		this.saisirModeAttribution();
		System.out.println();
		this.saisirModeJeu();
	}
	
	/**
	 * M�thode permettant � une partie de se d�rouler.
	 * 
	 * @param modeExecution Mode d'ex�cution choisi au d�marrage du logiciel.
	 */
	public void seJouer(String modeExecution) {
		if (this.modeJeu.equals("traditionnel")) {
			do {
				this.manches.add(new Manche());
				this.changerModeleEtNotifierObservateurs("Cr�ation de la manche");
				this.manches.get(manches.size()-1).seJouer(this.joueurs, this.modeAttribution, modeExecution);
				this.terminerMancheTraditionnel();
			} while (!unJoueurAAtteintUnScore(500));
			this.terminerPartie();
		} else if (this.modeJeu.equals("unoChallenge")) {
			ArrayList<Joueur> joueursElimines = new ArrayList<Joueur>();
			do {
				do {
					this.manches.add(new Manche());
					this.changerModeleEtNotifierObservateurs("Cr�ation de la manche");
					this.manches.get(manches.size()-1).seJouer(this.joueurs, this.modeAttribution, modeExecution);
					this.terminerMancheUnoChallenge(joueursElimines);
				} while (!unJoueurAAtteintUnScore(500));
				joueursElimines.add(this.joueurMeilleurScore());
				this.joueurs.remove(this.joueurMeilleurScore());
			} while (this.joueurs.size() > 1);
			this.terminerPartie();
		}
	}
	
	/**
	 * M�thode se d�clenchant pour indiquer la fin de la partie.
	 */
	public void terminerPartie() {
		if (this.modeAttribution.equals("positif")) {
			System.out.println("***************************************");
			System.out.println("Le joueur " + this.joueurMeilleurScore().getNomComplet() + " a gagn� la partie avec " + this.joueurMeilleurScore().getScore() + " points ! F�licitations !");
			System.out.println("***************************************");
			this.effacerEcranConsole();
		} else if (this.modeAttribution.equals("negatif")) {
			System.out.println("***************************************");
			System.out.println("Le joueur " + this.joueurMoinsBonScore().getNomComplet() + " a gagn� la partie avec " + this.joueurMoinsBonScore().getScore() + " points ! F�licitations !");
			System.out.println("***************************************");
			this.effacerEcranConsole();
		}
	}
	
	
	
	// ***********************************
	// *** M�THODES D'AJOUT DE JOUEURS ***
	// ***********************************
	
	/**
	 * M�thode permettant d'ajouter des joueurs un � un, en d�but de partie.
	 * Fonctionne uniquement pour le mode console.
	 * 
	 * @param nombreJoueurs Nombre de joueurs � ajouter.
	 */
	public void ajouterJoueurs(int nombreJoueurs) {
		System.out.println("� pr�sent, vous allez pouvoir ajouter chaque joueur un � un.\n");
		for (int i=0; i<nombreJoueurs; i++) {
			this.ajouterJoueur(i+1);
			System.out.println();
		}
	}
	
	/**
	 * M�thode permettant d'ajouter un joueur, en d�but de partie.
	 * Fonctionne uniquement pour le mode console.
	 * 
	 * @param numeroJoueur Num�ro du joueur � ajouter.
	 */
	public void ajouterJoueur(int numeroJoueur) {
		String typeJoueur = null;
		String nomJoueur = null;
		ArrayList<String> listeChainesDeCaracteresPossibles = new ArrayList<String>();
		
		System.out.println("Joueur num�ro " + numeroJoueur + " : joueur humain ou joueur virtuel ? (Valeurs possibles : H ou h pour humain ; V ou v pour ordinateur.)");
		listeChainesDeCaracteresPossibles.add("H");
		listeChainesDeCaracteresPossibles.add("h");
		listeChainesDeCaracteresPossibles.add("V");
		listeChainesDeCaracteresPossibles.add("v");
		typeJoueur = this.saisirChaineDeCaracteres(listeChainesDeCaracteresPossibles);
		listeChainesDeCaracteresPossibles.clear();
		
		System.out.println("Joueur num�ro " + numeroJoueur + " : nom du joueur ?");
		nomJoueur = this.saisirChaineDeCaracteres();
		
		if ((typeJoueur.equals("H")) || (typeJoueur.equals("h"))) {
			String prenomJoueurHumain;
			System.out.println("Joueur num�ro " + numeroJoueur + " : pr�nom du joueur humain ?");
			prenomJoueurHumain = this.saisirChaineDeCaracteres();
			
			this.joueurs.add(new JoueurHumain(nomJoueur, prenomJoueurHumain));
			
		} else if ((typeJoueur.equals("V")) || (typeJoueur.equals("v"))) {
			String strategieJoueurVirtuel;
			System.out.println("Joueur num�ro " + numeroJoueur + " : strat�gie du joueur virtuel ? (Valeurs possibles : O ou o pour offensive, D ou d pour d�fensive.)");
			listeChainesDeCaracteresPossibles.add("O");
			listeChainesDeCaracteresPossibles.add("o");
			listeChainesDeCaracteresPossibles.add("D");
			listeChainesDeCaracteresPossibles.add("d");
			strategieJoueurVirtuel = this.saisirChaineDeCaracteres(listeChainesDeCaracteresPossibles);
			
			if ((strategieJoueurVirtuel.equals("O")) || (strategieJoueurVirtuel.equals("o")))
				this.joueurs.add(new JoueurVirtuel(nomJoueur, new StrategieOffensive()));
			else if ((strategieJoueurVirtuel.equals("D")) || (strategieJoueurVirtuel.equals("d")))
				this.joueurs.add(new JoueurVirtuel(nomJoueur, new StrategieDefensive()));
		}
	}
	
	
	// *********************************
	// *** M�THODES DE FIN DE MANCHE ***
	// *********************************
	
	/**
	 * M�thode se d�clenchant pour indiquer la fin d'une manche traditionnel.
	 */
	public void terminerMancheTraditionnel() {
		this.viderMainsJoueurs();
		this.afficherClassementJoueurs();
		this.effacerEcranConsole();
	}
	
	/**
	 * M�thode se d�clenchant pour indiquer la fin d'une manche Uno Challenge.
	 * 
	 * @param joueursElimines Liste des joueurs �limin�s de la partie.
	 */
	public void terminerMancheUnoChallenge(ArrayList<Joueur> joueursElimines) {
		this.viderMainsJoueurs();
		this.afficherClassementJoueurs(joueursElimines);
		this.effacerEcranConsole();
	}
	
	/**
	 * M�thode permettant de vider les mains de tous les joueurs, en fin de manche.
	 */
	public void viderMainsJoueurs() {
		for (Joueur joueur : this.joueurs)
			joueur.getMain().clear();
	}
	
	
	// **************************
	// *** M�THODES DE SCORES ***
	// **************************
	
	/**
	 * M�thode permettant de savoir si un joueur de la partie a atteint un score pr�cis ou l'a d�pass�.
	 * 
	 * @param score Score dont on souhaite savoir si un joueur l'a d�pass�.
	 * @return Vrai si un joueur a d�pass� le score indique ; faux sinon.
	 */
	public boolean unJoueurAAtteintUnScore(int score) {
		if (this.joueurMeilleurScore().getScore() >= score)
			return true;
		else
			return false;
	}
	
	/**
	 * M�thode permettant de conna�tre le joueur ayant le meilleur score au sein de la partie.
	 * 
	 * @return Joueur ayant le meilleur score au sein de la partie.
	 */
	public Joueur joueurMeilleurScore() {
		Joueur joueurMeilleurScore = this.joueurs.get(0);
		int meilleurScore = 0;
		
		for (int i=0; i<this.joueurs.size(); i++)
			if (this.joueurs.get(i).getScore() > meilleurScore) {
				joueurMeilleurScore = this.joueurs.get(i);
				meilleurScore = joueurMeilleurScore.getScore();
			}
		
		return joueurMeilleurScore;
	}
	
	/**
	 * M�thode permettant de conna�tre le joueur ayant le moins bon score au sein de la partie.
	 * 
	 * @return Joueur ayant le moins bon score au sein de la partie.
	 */
	public Joueur joueurMoinsBonScore() {
		Joueur joueurMoinsBonScore = this.joueurs.get(0);
		int moinsBonScore = 1000;
		
		for (int i=0; i<this.joueurs.size(); i++)
			if (this.joueurs.get(i).getScore() < moinsBonScore) {
				joueurMoinsBonScore = this.joueurs.get(i);
				moinsBonScore = joueurMoinsBonScore.getScore();
			}
		
		return joueurMoinsBonScore;
	}
	
	
	
	// ****************************************
	// *** M�THODE DE MISE � JOUR DE LA VUE ***
	// ****************************************
	
	/**
	 * M�thode permettant d'indiquer un changement de mod�le et de notifier les observateurs de la classe Partie du mod�le.
	 * 
	 * @param message Message notifi� aux observateurs.
	 */
	public void changerModeleEtNotifierObservateurs(String message) {
		this.setChanged();
		this.notifyObservers(message);
	}
	
	
	
	// ****************************
	// *** M�THODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * M�thode permettant d'afficher le classement des joueurs en fonction du mode d'attribution de points de la partie.
	 * Uniquement utilisable en mode de jeu traditionnel.
	 */
	public void afficherClassementJoueurs() {
		ArrayList<Joueur> classementJoueurs = this.joueurs;
		Collections.sort(classementJoueurs);
		
		System.out.println("Le classement des joueurs � la fin de la manche n�" + this.manches.size() + " sont les suivants :");
		if (this.modeAttribution.equals("positif")) {
			for (int i=0; i<classementJoueurs.size(); i++)
				System.out.println((i+1) + ") " + classementJoueurs.get(i).getNomComplet() + " avec " + classementJoueurs.get(i).getScore() + " point(s).");
		} else if (this.modeAttribution.equals("negatif")) {
			int j = 1;
			for (int i=classementJoueurs.size(); i>0; i--) {
				System.out.println(j + ") " + classementJoueurs.get(i).getNomComplet() + " avec " + classementJoueurs.get(i).getScore() + " point(s).");
				j++;
			}
		}
	}
	
	/**
	 * M�thode permettant d'afficher le classement des joueurs en prenant tout aussi bien en compte les joueurs encore
	 * en course que les joueurs �limin�s au sein de la partie.
	 * Uniquement utilisable en mode de jeu Uno Challenge.
	 * 
	 * @param joueursElimines Liste des joueurs �limin�s de la partie.
	 */
	public void afficherClassementJoueurs(ArrayList<Joueur> joueursElimines) {
		ArrayList<Joueur> classementJoueurs = this.joueurs;
		Collections.sort(classementJoueurs);
		
		System.out.println("Le classement des joueurs � la fin de la manche n�" + this.manches.size() + " sont les suivants :");
		int j = 1;
		
		for (int i=classementJoueurs.size(); i>0; i--) {
			System.out.println(j + ") " + classementJoueurs.get(i).getNomComplet() + " avec " + classementJoueurs.get(i).getScore() + " point(s).");
			j++;
		}
		
		for (int i=joueursElimines.size(); i>0; i--) {
			System.out.println(j + ") " + classementJoueurs.get(i).getNomComplet() + " avec " + classementJoueurs.get(i).getScore() + " point(s).");
			j++;
		}
	}
	
	/**
	 * M�thode permettant de balayer l'�cran de la console.
	 */
	public void effacerEcranConsole() {
		for (int i=0; i<20; i++)
			System.out.println();
	}
	
	
	// **************************
	// *** M�THODES DE SAISIE ***
	// **************************
	
	/**
	 * M�thode permettant de saisir le mode d'attribution de points.
	 * Fonctionne uniquement pour le mode console.
	 */
	public void saisirModeAttribution() {
		String modeAttribution;
		ArrayList<String> listeChainesDeCaracteresPossibles = new ArrayList<String>();
		
		System.out.println("Mode d'attribution des points ? (Valeurs possibles : P ou p pour positif ; N ou n pour n�gatif.)");
		listeChainesDeCaracteresPossibles.add("P");
		listeChainesDeCaracteresPossibles.add("p");
		listeChainesDeCaracteresPossibles.add("N");
		listeChainesDeCaracteresPossibles.add("n");
		modeAttribution = this.saisirChaineDeCaracteres(listeChainesDeCaracteresPossibles);
		
		if ((modeAttribution.equals("P")) || (modeAttribution.equals("p")))
			this.modeAttribution = "positif";
		else if ((modeAttribution.equals("N")) || (modeAttribution.equals("n")))
			this.modeAttribution = "negatif";
	}
	
	/**
	 * M�thode permettant de saisir le mode de jeu.
	 * Fonctionne uniquement pour le mode console.
	 */
	public void saisirModeJeu() {
		String modeJeu;
		ArrayList<String> listeChainesDeCaracteresPossibles = new ArrayList<String>();
		
		if (this.modeAttribution.equals("positif")) {
			System.out.println("Mode de jeu ? (Valeur possible : T ou t pour traditionnel.)");
			listeChainesDeCaracteresPossibles.add("T");
			listeChainesDeCaracteresPossibles.add("t");
		} else if (this.modeAttribution.equals("negatif")) {
			System.out.println("Mode de jeu ? (Valeurs possibles : T ou t pour traditionnel ; U ou u pour Uno Challenge.)");
			listeChainesDeCaracteresPossibles.add("T");
			listeChainesDeCaracteresPossibles.add("t");
			listeChainesDeCaracteresPossibles.add("U");
			listeChainesDeCaracteresPossibles.add("u");
		}
		modeJeu = this.saisirChaineDeCaracteres(listeChainesDeCaracteresPossibles);
		
		if ((modeJeu.equals("T")) || (modeJeu.equals("t")))
			this.modeJeu = "traditionnel";
		else if ((modeJeu.equals("U")) || (modeJeu.equals("u")))
			this.modeJeu = "unoChallenge";
	}
	
	/**
	 * M�thode permettant de traiter la saisie d'un entier, lors des demandes de saisie d'entier (nombre de joueurs
	 * d�sir�).
	 * 
	 * @param min Entier minimum que le joueur puisse saisir.
	 * @param max Entier maximum que le joueur puisse saisir.
	 * @return Le nombre saisi par le joueur et contenu entre min et max.
	 */
	public int saisirEntier(int min, int max) {
		int saisieEntier;
		
		try {
			saisieEntier = new Scanner(System.in).nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("Veuillez saisir un nombre plut�t qu'un caract�re.");
			return saisirEntier(min, max);
		}
		
		if ((saisieEntier<min)||(saisieEntier>max)) {
			System.out.println("Veuillez saisir un nombre compris entre " + min + " et " + max + ".");
			return saisirEntier(min, max);
		} else
			return saisieEntier;
	}
	
	/**
	 * M�thode permettant de traiter la saisie d'une cha�ne de caract�res, lors des demandes de saisie de cha�ne de
	 * caract�res (nom de joueur, pr�nom de joueur humain, strat�gie de joueur virtuel, mode d'attribution de points,
	 * mode de jeu) en prenant en compte une liste de cha�nes de caract�res possibles et en n'en acceptant pas d'autre.
	 * 
	 * @param listeChainesDeCaracteresPossibles La liste de cha�nes de caract�res possibles.
	 * @return La cha�ne de caract�res saisie par le joueur et comprise dans la liste de cha�nes de caract�res possibles.
	 */
	@SuppressWarnings("resource")
	public String saisirChaineDeCaracteres(ArrayList<String> listeChainesDeCaracteresPossibles) {
		String saisieChaineDeCaracteres;
		
		saisieChaineDeCaracteres = new Scanner(System.in).next();
		
		if (!listeChainesDeCaracteresPossibles.contains(saisieChaineDeCaracteres)) {
			System.out.println("Veuillez saisir l'un des caract�res demand�s.");
			return saisirChaineDeCaracteres(listeChainesDeCaracteresPossibles);
		} else
			return saisieChaineDeCaracteres;
	}
	
	/**
	 * M�thode permettant de traiter la saisie d'une cha�ne de caract�res, lors des demandes de saisie de cha�ne de
	 * caract�res (nom de joueur, pr�nom de joueur humain, strat�gie de joueur virtuel, mode d'attribution de points,
	 * mode de jeu).
	 * 
	 * @return La cha�ne de caract�res saisie par le joueur.
	 */
	@SuppressWarnings("resource")
	public String saisirChaineDeCaracteres() {
		return new Scanner(System.in).next();
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur de l'attribut gagnant de la classe Partie.
	 * 
	 * @return Le gagnant de la partie.
	 */
	public Joueur getGagnant() {
		return gagnant;
	}
	
	/**
	 * Accesseur de l'attribut modeJeu de la classe Partie.
	 * 
	 * @return Le mode de jeu de la partie.
	 */
	public String getModeJeu() {
		return modeJeu;
	}
	
	/**
	 * Accesseur de l'attribut modeAttribution de la classe Partie.
	 * 
	 * @return Le mode d'attribution de points de la partie.
	 */
	public String getModeAttribution() {
		return modeAttribution;
	}
	
	/**
	 * Accesseur d'un objet de l'attribut joueurs de la classe Partie.
	 * 
	 * @param joueur Num�ro dans la liste du joueur que l'on souhaite r�cup�rer.
	 * @return Le gagnant de la partie.
	 */
	public Joueur getJoueur(int joueur) {
		return joueurs.get(joueur);
	}
	
	/**
	 * Accesseur de l'attribut joueurs de la classe Partie.
	 * 
	 * @return La liste de joueurs de la partie.
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Accesseur d'un objet de l'attribut gagnant de la classe Partie.
	 * 
	 * @param manche Num�ro dans la liste de la manche que l'on souhaite r�cup�rer.
	 * @return Le gagnant de la partie.
	 */
	public Manche getManche(int manche) {
		return manches.get(manche);
	}
	
	/**
	 * Accesseur de l'attribut manches de la classe Partie.
	 * 
	 * @return La liste de manches de la partie.
	 */
	public ArrayList<Manche> getManches() {
		return manches;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur de l'attribut gagnant de la classe Partie.
	 * 
	 * @param gagnant Le nouveau gagnant de la partie.
	 */
	public void setGagnant(Joueur gagnant) {
		this.gagnant = gagnant;
	}
	
	/**
	 * Mutateur de l'attribut modeJeu de la classe Partie.
	 * 
	 * @param modeJeu Le nouveau mode de jeu de la partie.
	 */
	public void setModeJeu(String modeJeu) {
		this.modeJeu = modeJeu;
	}
	
	/**
	 * Mutateur de l'attribut modeAttribution de la classe Partie.
	 * 
	 * @param modeAttribution Le nouveau mode d'attribution de points de la partie.
	 */
	public void setModeAttribution(String modeAttribution) {
		this.modeAttribution = modeAttribution;
	}
	
	/**
	 * Mutateur de l'attribut joueurs de la classe Partie.
	 * 
	 * @param joueurs La nouvelle liste de joueurs de la partie.
	 */
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	/**
	 * Mutateur de l'attribut manches de la classe Partie.
	 * 
	 * @param manches La nouvelle liste de manches de la partie.
	 */
	public void setManches(ArrayList<Manche> manches) {
		this.manches = manches;
	}
	
}