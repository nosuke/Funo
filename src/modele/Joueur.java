package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;



/**
 * La classe Joueur est une classe m�re abstraite permettant l'instanciation de classes filles ayant pour point commun
 * la pr�sence des attributs nom, score, place et main. Un ensemble assez cons�quent de m�thodes est �galement commun
 * aux deux types de joueurs (joueur humain et joueur virtuel).
 * Les joueurs sont au centre du jeu de Uno puisque ce sont eux qui participent � faire avancer la partie au sein de
 * ses manches.
 * L'implantation de l'interface Comparable<Joueur> sert pour r�aliser des comparaisons entre les scores des diff�rents
 * joueurs, � la fin de chaque manche, pour l'affichage du classement.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public abstract class Joueur implements Comparable<Joueur> {
	
	// *****************
	// *** ATTRIBUTS ***
	// *****************
	
	/**
	 * Le nom d'un joueur.
	 */
	protected String nom;
	/**
	 * Le score d'un joueur.
	 */
	protected int score;
	/**
	 * La place d'un joueur (c'est-�-dire son tour au sein d'une manche).
	 */
	protected int place;
	/**
	 * La main de cartes d'un joueur.
	 */
	protected ArrayList<Carte> main = new ArrayList<Carte>();
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe Joueur.
	 * 
	 * @param nom Le nom choisi pour le joueur, lors de son instanciation.
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.score = 0;
	}
	
	
	
	// ****************************
	// *** M�THODES PRINCIPALES ***
	// ****************************
	
	/**
	 * M�thode permettant � un joueur de jouer son tour.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur joue son tour.
	 * @param joueurs La liste des joueurs de la manche.
	 */
	public void jouerTour(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		Carte carteChoisie;
		
		System.out.println("Joueur actuel : " + this.getNomComplet() + ".");
		System.out.println("Carte sur le dessus du talon : " + mancheCourante.getTalon().peek());
		System.out.println(this.afficherMain());
		
		if (this.pouvoirJouer(mancheCourante.getTalon())) {
			carteChoisie = this.choisirCarteADeposer(mancheCourante);		
			if (carteChoisie != null) {
				this.effacerEcranConsole();
				this.deposerCarte(carteChoisie, mancheCourante, joueurs);
			} else
				this.declencherDeuxiemePartieTourJoueur(mancheCourante, joueurs);
		} else {
			this.declencherDeuxiemePartieTourJoueur(mancheCourante, joueurs);
		}
		
		this.effacerEcranConsole();
	}
	
	/**
	 * M�thode permettant � un joueur de jouer la deuxi�me partie de son tour.
	 * Il n'y a deuxi�me partie de tour que si le joueur ne peut pas ou ne veut pas jouer la premi�re partie de son tour.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur joue son tour.
	 * @param joueurs La liste des joueurs de la manche.
	 */
	public void declencherDeuxiemePartieTourJoueur(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		Carte carteChoisie;
		
		this.piocherCarte(mancheCourante);
		System.out.println("N'ayant pas pu ou pas voulu jouer, le joueur " + mancheCourante.joueurCourant(joueurs).getNomComplet() + " a pioch� une nouvelle carte.\n");
		System.out.println(this.afficherMain());
		 
		if (this.pouvoirJouer(mancheCourante.getTalon())) {
			carteChoisie = this.choisirCarteADeposer(mancheCourante);		
			if (carteChoisie != null) {
				this.effacerEcranConsole();
				this.deposerCarte(carteChoisie, mancheCourante, joueurs);
			}
		}
	}
	
	
	
	// ***************************
	// *** M�THODES DU DONNEUR ***
	// ***************************
	
	/**
	 * M�thode permettant au donneur de m�langer les cartes, lors de la distribution de celles-ci en d�but de manche.
	 * 
	 * @param listeCartes La liste des cartes � m�langer.
	 */
	public void melangerCartes(ArrayList<Carte> listeCartes) {
		Collections.shuffle(listeCartes);
	}
	
	/**
	 * M�thode permettant au donneur de distribuer des cartes � chaque joueur, en d�but de manche.
	 * 
	 * @param joueurs La liste des joueurs qui re�oivent de nouvelles cartes dans leur main.
	 * @param listeCartes La liste des cartes servant pour la distribution.
	 * @param nombreCartes Le nombre de cartes distribu�es � chaque joueur.
	 */
	public void distribuerCartes(ArrayList<Joueur> joueurs, ArrayList<Carte> listeCartes, int nombreCartes) {
		for (int i=0; i<joueurs.size(); i++) {
			for (int j=0; j<nombreCartes; j++) {
				joueurs.get(i).ajouterCarteMain(listeCartes.get(listeCartes.size()-1));
				listeCartes.remove(listeCartes.size()-1);
			}
		}
	}
	
	/**
	 * M�thode permettant au donneur de cr�er la pioche � partir de la liste des cartes de la manche, en d�but de manche.
	 * 
	 * @param listeCartes La liste des cartes de la manche.
	 * @return La pioche cr��e.
	 */
	public TasDeCartes creerPioche(ArrayList<Carte> listeCartes) {
		TasDeCartes pioche = new TasDeCartes();
		
		for (int i=listeCartes.size()-1; i>=0; i--) {
			pioche.push(listeCartes.get(i));
			listeCartes.remove(listeCartes.size()-1);
		}
		
		return pioche;
	}
	
	/**
	 * M�thode permettant au donneur de cr�er le talon � partir de la pioche, en d�but de manche.
	 * 
	 * @param pioche La pioche de la manche.
	 * @return Le talon cr��.
	 */
	public TasDeCartes creerTalon(TasDeCartes pioche) {
		TasDeCartes talon = new TasDeCartes();
		talon.push(pioche.pop());
		return talon;
	}
	
	
	
	// *****************************************************
	// *** M�THODES DE LA POSSIBILIT� DE JOUER UNE CARTE ***
	// *****************************************************
	
	/**
	 * M�thode indiquant si le joueur peut jouer une carte sur le talon ou non.
	 * 
	 * @param talon Le talon de la manche.
	 * @return Vrai si le joueur peut jouer une carte sur le talon ; faux sinon.
	 */
	public boolean pouvoirJouer(TasDeCartes talon) {
		for (Carte carte : this.main)
			if (carte.estJouable(talon))
				return true;
		return false;
	}
	
	/**
	 * M�thode indiquant si le joueur peut jouer une carte sur le talon ou non, en dehors de la carte +4 jou�e et d'autres cartes +4 pr�sentes dans sa main, s'il y en a.
	 * 
	 * @param talon Le talon de la manche.
	 * @return Vrai si le joueur peut jouer une carte autre que +4 sur le talon ; faux sinon.
	 */
	public boolean pouvoirJouerSansPlusQuatre(TasDeCartes talon) {
		Carte cartePlusQuatreJouee = talon.pop();
		
		for (Carte carte : this.main) {
			if (carte.estJouable(talon) && !(carte instanceof CartePlusQuatre)) {
				talon.push(cartePlusQuatreJouee);
				return true;
			}
		}
		
		talon.push(cartePlusQuatreJouee);
		return false;
	}
	
	/**
	 * M�thode permettant de r�cup�rer la liste des cartes jouables sur le talon de la main du joueur.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur joue son tour.
	 * @return La liste des cartes jouables sur le talon de la main du joueur.
	 */
	public ArrayList<Carte> recupererCartesJouablesMain(Manche mancheCourante) {
		ArrayList<Carte> cartesJouablesMain = new ArrayList<Carte>();
		
		for (Carte carte : this.main)
			if (carte.estJouable(mancheCourante.getTalon()))
				cartesJouablesMain.add(carte);
		
		return cartesJouablesMain;
	}
	
	
	// **************************************************
	// *** M�THODES DE D�P�T D'UNE CARTE SUR LE TALON ***
	// **************************************************
	
	/**
	 * M�thode abstraite red�finie dans les classes filles de Joueur.
	 * En effet, selon que le joueur soit humain ou virtuel, le choix d'une carte dans la main du joueur se fera de mani�re diff�rente, c'est-�-dire qu'un joueur humain choisira une carte via saisie alors qu'un joueur virtuel choisira une carte via algorithme.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur peut choisir une carte � d�poser au sein de sa main.
	 * @return La carte choisie par le joueur, au sein de sa main.
	 */
	public abstract Carte choisirCarteADeposer(Manche mancheCourante);
	
	/**
	 * M�thode permettant � un joueur de d�poser une carte sur le talon.
	 * 
	 * @param carte La carte d�pos�e sur le talon.
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur d�pose une carte.
	 * @param joueurs La liste des joueurs de la manche.
	 */
	public void deposerCarte(Carte carte, Manche mancheCourante, ArrayList<Joueur> joueurs) {
		mancheCourante.getTalon().push(carte);
		System.out.println("Le joueur " + this.getNomComplet() + " a d�pos� la carte " + carte + ".");
		
		if (carte instanceof CarteSpeciale)
			((CarteSpeciale) carte).declencherEffet(mancheCourante, joueurs);
		
		if (this.getMain().size() == 1)
			this.gererSaisieUNO(mancheCourante);
	}
	
	
	// ************************************************************
	// *** M�THODES DE PIOCHE D'UNE CARTE � PARTIR DE LA PIOCHE ***
	// ************************************************************
	
	/**
	 * M�thode permettant au donneur de repiocher une carte en d�but de manche.
	 * Cela se produit uniquement si la premi�re carte pioch�e est une carte +4.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le donneur repioche une carte en son d�but.
	 */
	public void repiocherCarteDebutManche(Manche mancheCourante) {
		mancheCourante.getTalon().push(mancheCourante.getPioche().pop());
	}
	
	/**
	 * M�thode permettant � un joueur de piocher une carte dans la pioche.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur pioche une carte. 
	 */
	public void piocherCarte(Manche mancheCourante) {
		if (mancheCourante.getPioche().isEmpty()) {
			System.out.println("La pioche est vide ! Elle va �tre � nouveau remplie gr�ce aux cartes du talon.");
			
			TasDeCartes nouvellePioche = new TasDeCartes();
			TasDeCartes nouveauTalon = new TasDeCartes();
			
			nouveauTalon.add(mancheCourante.getTalon().pop());
			nouvellePioche = mancheCourante.getTalon();
			Collections.shuffle(nouvellePioche);
			
			mancheCourante.setPioche(nouvellePioche);
			mancheCourante.setTalon(nouveauTalon);
			
			System.out.println("La pioche est � nouveau remplie.");
		}
		
		try {
			this.ajouterCarteMain(mancheCourante.getPioche().pop());
		} catch (EmptyStackException e) {
			e.printStackTrace();
			System.out.println("Il n'y a pas assez de cartes dans le talon : la pioche ne peut plus �tre remplie ! La partie est donc annul�e.\n");
			System.exit(0);
		}
	}
	
	/**
	 * M�thode permettant au joueur d'ajouter une carte � sa main.
	 * 
	 * @param carte Carte ajout�e � la main du joueur.
	 */
	public void ajouterCarteMain(Carte carte) {
		this.main.add(carte);
	}
	
	
	
	// **************************************************
	// *** M�THODE DE CHOIX DE LA COULEUR D'UNE CARTE ***
	// **************************************************
	
	/**
	 * M�thode abstraite red�finie dans les classes filles de Joueur.
	 * En effet, selon que le joueur soit humain ou virtuel, le choix de la couleur d'une carte Joker/+4 se fera de mani�re diff�rente, c'est-�-dire qu'un joueur humain choisira la couleur via saisie alors qu'un joueur virtuel choisira la couleur via algorithme (sur base de la couleur de carte la plus repr�sent�e dans sa main).
	 * 
	 * @param listeCouleurs La liste des couleurs propos�es au joueur lors du choix.
	 * @param situation La situation d'utilisation de la m�thode, c'est-�-dire soit lors de la pioche d'une carte Joker en d�but de manche pour la premi�re situation, soit lors de l'utilisation d'une carte Joker/+4 par un joueur pour la seconde situation.
	 * @return La cha�ne de caract�res repr�sentant la couleur choisie pour la carte.
	 */
	public abstract String choisirCouleurCarte(String[] listeCouleurs, int situation);
	
	
	
	// **********************************
	// *** M�THODES DE GESTION DU UNO ***
	// **********************************
	
	/**
	 * M�thode permettant de g�rer la saisie d'UNO en mode console, c'est-�-dire qu'une fois son avant-derni�re carte jou�e, un joueur a cinq secondes pour saisir le mot "UNO".
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur peut d�cider de saisir "UNO".
	 */
	public void gererSaisieUNO(Manche mancheCourante) {
		boolean saisieUNOeffectuee = false;
		saisieUNOeffectuee = saisirUNO();
		interpreterSaisieUNO(saisieUNOeffectuee, mancheCourante);
	}
	
	/**
	 * M�thode abstraite red�finie dans les classes filles de Joueur.
	 * En effet, selon que le joueur soit humain ou virtuel, la saisie du mot "UNO" se fera de mani�re diff�rente, c'est-�-dire qu'un joueur humain devra saisir "UNO" dans les cinq secondes, alors qu'un joueur virtuel saisira syst�matiquement "UNO" � temps.
	 * 
	 * @return Vrai si la saisie a �t� faite � temps ; faux sinon.
	 */
	public abstract boolean saisirUNO();
	
	/**
	 * M�thode permettant d'interpr�ter le r�sultat de la saisie du mot "UNO" par le joueur, c'est-�-dire de provoquer un affichage en fonction et de lui faire piocher 2 cartes s'il a oubli� de saisir "UNO".
	 * 
	 * @param saisieUNOeffectuee Le r�sultat de la saisier du mot "UNO" par le joueur.
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur peut d�cider de saisir "UNO".
	 */
	public void interpreterSaisieUNO(boolean saisieUNOeffectuee, Manche mancheCourante) {
		if (saisieUNOeffectuee)
			System.out.println("Le joueur " + this.getNomComplet() + " a pens� � saisir UNO dans les cinq secondes lui �tant accord�es.");
		else {
			for (int i=0; i<2; i++)
				this.piocherCarte(mancheCourante);
			System.out.println("Le joueur " + this.getNomComplet() + " a oubli� de saisir UNO dans les cinq secondes lui �tant accord�es. Il a donc d� piocher deux cartes.");
		}
	}
	
	
	
	// ******************************
	// *** M�THODE DE COMPARAISON ***
	// ******************************
	
	/**
	 * M�thode utilis�e lors du tri d'une liste de joueurs par ordre d�croissant de leur score, le premier joueur �tant celui ex�cutant la m�thode ; le second servant lors de la comparaison.
	 * 
	 * @param autreJoueur Le joueur servant � la comparaison avec le joueur ex�cutant la m�thode.
	 * @return Le r�sultat de la comparaison. 1 si le score du joueur ex�cutant la m�thode est inf�rieur � celui de l'autre joueur, -1 s'il est sup�rieur, 0 s'ils sont �gaux.
	 */
	public int compareTo(Joueur autreJoueur) {
		int resultat;
		
		if (this.score < autreJoueur.score)
			resultat = 1;
		else if (this.score > autreJoueur.score)
			resultat = -1;
		else
			resultat = 0;
		
		return resultat;
	}
	
	
	
	// ****************************
	// *** M�THODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * M�thode permettant l'affichage d�taill� de la main du joueur.
	 * 
	 * @return La cha�ne de caract�res servant pour l'affichage de la main.
	 */
	public String afficherMain() {
		int i=0;
		String chaineMain = "Contenu de la main :\n[ ";
		for (i=0; i<this.getMain().size()-1; i++)
			chaineMain += "Carte n�" + (i+1) + " : " + this.getMain().get(i) + ",\n";
		chaineMain += "Carte n�" + (i+1) + " : " + this.getMain().get(i) + " ]\n";
		return chaineMain;
	}
	
	/**
	 * M�thode abstraite red�finie dans les classes filles, et retournant le nom complet du joueur.
	 * En effet, si le joueur est humain, son nom complet est compos� de son nom et de son pr�nom ;
	 * tandis que si le joueur est virtuel, son nom complet est compos� uniquement de son nom.
	 * 
	 * @return Le nom complet du joueur.
	 */
	public abstract String getNomComplet();
	
	/**
	 * M�thode d'affichage de la classe Joueur.
	 * Sert pour l'affichage du nom du joueur au sein de la liste de s�lection des joueurs du menu, en mode graphique.
	 */
	public String toString() {
		return this.nom;
	}
	
	/**
	 * M�thode permettant de balayer l'�cran de la console.
	 */
	public void effacerEcranConsole() {
		for (int i=0; i<30; i++)
			System.out.println();
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur de l'attribut nom de la classe Joueur.
	 * 
	 * @return Le nom du joueur.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Accesseur de l'attribut score de la classe Joueur.
	 * 
	 * @return Le score du joueur.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Accesseur de l'attribut place de la classe Joueur.
	 * 
	 * @return La place du joueur.
	 */
	public int getPlace() {
		return place;
	}
	
	/**
	 * Accesseur de l'attribut main de la classe Joueur.
	 * 
	 * @return La main du joueur.
	 */
	public ArrayList<Carte> getMain() {
		return main;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur de l'attribut nom de la classe Joueur.
	 * 
	 * @param nom Le nouveau nom du joueur.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Mutateur de l'attribut score de la classe Joueur.
	 * 
	 * @param score Le nouveau score du joueur.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Mutateur de l'attribut place de la classe Joueur.
	 * 
	 * @param place La nouvelle place du joueur.
	 */
	public void setPlace(int place) {
		this.place = place;
	}
	
	/**
	 * Mutateur de l'attribut main de la classe Joueur.
	 * 
	 * @param main La nouvelle main du joueur.
	 */
	public void setMain(ArrayList<Carte> main) {
		this.main = main;
	}
	
}