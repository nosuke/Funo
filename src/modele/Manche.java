package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Observable;



/**
 * La classe Manche est une classe permettant l'instanciation de manches qui prennent part au sein de la partie.
 * En effet, une partie est composée de plusieurs manches chacune comportant un gagnant, un numero de tour, un sens,
 * un ensemble de cartes (par la suite redistribué entre les mains des joueurs, la pioche et le talon), une pioche
 * et un talon.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class Manche extends Observable {
	
	// *****************
	// *** ATTRIBUTS ***
	// *****************
	
	/**
	 * Le joueur gagnant d'une manche.
	 */
	private Joueur gagnant;
	/**
	 * Le numéro de tour d'une manche.
	 */
	private int numeroTour;
	/**
	 * Le sens d'une manche.
	 */
	private boolean sens;
	/**
	 * L'ensemble de cartes d'une manche (par la suite redistribué entre les mains des joueurs, la pioche et le talon).
	 */
	private ArrayList<Carte> cartes = new ArrayList<Carte>();
	/**
	 * La pioche d'une manche.
	 */
	private TasDeCartes pioche = new TasDeCartes();
	/**
	 * Le talon d'une manche.
	 */
	private TasDeCartes talon = new TasDeCartes();
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe Manche.
	 */
	public Manche() {
		this.numeroTour = 2521;
		this.sens = true;
		this.cartes = this.initialiserToutesCartes();
	}
	
	
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Méthode permettant à une manche de se dérouler.
	 * 
	 * @param joueurs Liste des joueurs jouant la manche.
	 * @param modeAttribution Mode d'attribution de points associé à la partie.
	 * @param modeExecution Mode d'exécution choisi au démarrage du logiciel.
	 */
	public void seJouer(ArrayList<Joueur> joueurs, String modeAttribution, String modeExecution) {
		System.out.println("La manche peut commencer !");
		System.out.println("Petit rappel des règles : il est nécessaire que vous saisissiez et validiez UNO (ou uno) dans les cinq secondes suivant le dépôt de votre avant-dernière carte.");
		System.out.println("Si cela n'est pas réalisé à temps, vous serez dans l'obligation de piocher deux cartes de pénalité.\n");
		
		this.attribuerOrdreJoueurs(joueurs);
		this.afficherListeJoueursParOrdre(joueurs);
		this.permettreDistributionParDonneur(joueurs, modeExecution);
		this.effacerEcranConsole();
		
		Joueur joueurCourant;
		
		do {
			joueurCourant = joueurCourant(joueurs);
			joueurCourant.jouerTour(this, joueurs);
			if (this.sens)
				this.numeroTour++;
			else
				this.numeroTour--;
		} while (!joueurCourant.getMain().isEmpty());
		
		this.gagnant = joueurCourant;
		this.attribuerPoints(joueurs, modeAttribution);
	}
	
	
	
	// *********************************
	// *** MÉTHODES D'INITIALISATION ***
	// *********************************
	
	/**
	 * Méthode permettant l'initialisation de toutes les cartes de la manche.
	 * 
	 * @return Ensemble de toutes les cartes de la manche.
	 */
	public ArrayList<Carte> initialiserToutesCartes() {
		ArrayList<Carte> cartes = new ArrayList<Carte>();
		ArrayList<String> couleurs = new ArrayList<String>(Arrays.asList("bleu", "rouge", "jaune", "vert"));
		cartes = initialiserEnsembleCartesNormales(cartes, couleurs);	
		cartes = initialiserEnsembleCartesSpeciales(cartes, couleurs);
		return cartes;
	}
	
	/**
	 * Méthode permettant l'initialisation de toutes les cartes normales de la manche.
	 * 
	 * @param cartes Ensemble de toutes les cartes de la manche.
	 * @param couleurs Tableau des couleurs attribuées aux cartes.
	 * @return Ensemble de toutes les cartes de la manche après ajout des cartes normales.
	 */
	public ArrayList<Carte> initialiserEnsembleCartesNormales(ArrayList<Carte> cartes, ArrayList<String> couleurs) {
		for (int i=0 ; i<couleurs.size() ; i++)
			initialiserEnsembleCartesNormalesPourUneCouleur(cartes, couleurs.get(i), 9);
		return cartes;
	}
	
	/**
	 * Méthode permettant l'initialisation de toutes les cartes normales de la manche.
	 * 
	 * @param cartes Ensemble de toutes les cartes de la manche.
	 * @param couleur Couleur attribuée aux cartes.
	 * @param chiffreMaximum Nombre de cartes normales initialisées.
	 * @return Ensemble de toutes les cartes de la manche après ajout des cartes normales pour une couleur.
	 */
	public ArrayList<Carte> initialiserEnsembleCartesNormalesPourUneCouleur(ArrayList<Carte> cartes, String couleur, int chiffreMaximum) {
		cartes = ajouterCarteListe(cartes, new CarteNormale(couleur, 0));
		for (int i=1 ; i<=chiffreMaximum ; i++)
			cartes = ajouterCartesListe(cartes, new CarteNormale(couleur, i), 2);
		return cartes;
	}
	
	/**
	 * Méthode permettant l'initialisation de toutes les cartes spéciales de la manche.
	 * 
	 * @param cartes Ensemble de toutes les cartes de la manche.
	 * @param couleurs Tableau des couleurs attribuées aux cartes.
	 * @return Ensemble de toutes les cartes de la manche après ajout des cartes spéciales.
	 */
	public ArrayList<Carte> initialiserEnsembleCartesSpeciales(ArrayList<Carte> cartes, ArrayList<String> couleurs) {
		cartes = initialiserEnsembleCartesSpecialesAvecCouleur(cartes, couleurs);
		cartes = initialiserEnsembleCartesSpecialesSansCouleur(cartes);
		return cartes;
	}
	
	/**
	 * Méthode permettant l'initialisation de toutes les cartes spéciales avec couleur de la manche.
	 * 
	 * @param cartes Ensemble de toutes les cartes de la manche.
	 * @param couleurs Tableau des couleurs attribuées aux cartes.
	 * @return Ensemble de toutes les cartes de la manche après ajout des cartes spéciales avec couleur.
	 */
	public ArrayList<Carte> initialiserEnsembleCartesSpecialesAvecCouleur(ArrayList<Carte> cartes, ArrayList<String> couleurs) {
		for (int i=0 ; i<couleurs.size() ; i++) {
			cartes = ajouterCartesListe(cartes, new CartePlusDeux(couleurs.get(i)), 2);
			cartes = ajouterCartesListe(cartes, new CarteInversion(couleurs.get(i)), 2);
			cartes = ajouterCartesListe(cartes, new CartePasserUnTour(couleurs.get(i)), 2);
		}
		return cartes;
	}
	
	/**
	 * Méthode permettant l'initialisation de toutes les cartes spéciales sans couleur de la manche.
	 * 
	 * @param cartes Ensemble de toutes les cartes de la manche.
	 * @return Ensemble de toutes les cartes de la manche après ajout des cartes spéciales sans couleur.
	 */
	public ArrayList<Carte> initialiserEnsembleCartesSpecialesSansCouleur(ArrayList<Carte> cartes) {
		cartes = ajouterCartesListe(cartes, new CarteJoker("neutre"), 4);
		cartes = ajouterCartesListe(cartes, new CartePlusQuatre("neutre"), 4);
		return cartes;
	}
	
	/**
	 * Méthode permettant l'ajout multiple d'une même carte à l'ensemble des cartes.
	 * 
	 * @param cartes Ensemble de toutes les cartes de la manche.
	 * @param carte Carte ajoutée plusieurs fois à l'ensemble des cartes.
	 * @param nbCartes Nombre de cartes ajoutés.
	 * @return Ensemble de toutes les cartes de la manche après ajout multiple de la carte à l'ensemble des cartes.
	 */
	public ArrayList<Carte> ajouterCartesListe(ArrayList<Carte> cartes, Carte carte, int nbCartes) {
		for (int i=1 ; i<=nbCartes ; i++)
			cartes.add(carte);
		return cartes;
	}
	
	/**
	 * Méthode permettant l'ajout d'une carte à l'ensemble des cartes.
	 * 
	 * @param cartes Ensemble de toutes les cartes de la manche.
	 * @param carte Carte ajoutée à l'ensemble des cartes.
	 * @return Ensemble de toutes les cartes de la manche après ajout de la carte à l'ensemble des cartes.
	 */
	public ArrayList<Carte> ajouterCarteListe(ArrayList<Carte> cartes, Carte carte) {
		cartes.add(carte);
		return cartes;
	}
	
	
	// ***********************************
	// *** MÉTHODES DE DÉBUT DE MANCHE ***
	// ***********************************
	
	/**
	 * Méthode permettant d'attribuer l'ordre des joueurs en début de manche.
	 * Cet ordre est décidé au hasard, grâce à la méthode "shuffle" de la classe Collections.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 */
	public void attribuerOrdreJoueurs(ArrayList<Joueur> joueurs) {
		ArrayList<Integer> listeNombres = new ArrayList<Integer>();
		for (int i=0; i<joueurs.size(); i++)
			listeNombres.add(i);
		
		Collections.shuffle(listeNombres);
		
		for (int i=0; i<joueurs.size(); i++)
			joueurs.get(i).setPlace(listeNombres.get(i));
	}
	
	/**
	 * Méthode permettant de déclencher la distribution par le donneur en début de manche.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 * @param modeExecution Mode d'exécution choisi au démarrage du logiciel.
	 */
	public void permettreDistributionParDonneur(ArrayList<Joueur> joueurs, String modeExecution) {
		Joueur donneur = getJoueur(joueurs, 0);
		if (modeExecution.equals("modeConsole")) {
			System.out.println("Le donneur (" + donneur.getNomComplet() + ") peut se mettre à mélanger et distribuer les cartes.");
			System.out.println("Le donneur doit appuyer sur la touche Entrée pour déclencher le mélange et la distribution des cartes.");
			if (donneur instanceof JoueurHumain)
				this.demanderAppuiSurToucheEntree();
		}
			
		donneur.melangerCartes(this.cartes);
		donneur.distribuerCartes(joueurs, this.cartes, 7);
		this.pioche = donneur.creerPioche(this.cartes);
		this.talon = donneur.creerTalon(this.pioche);
		
		do {
			if (this.talon.peek() instanceof CarteJoker) {
				String[] listeCouleurs = {"bleu", "rouge", "jaune", "vert"};
				this.talon.peek().setCouleur(donneur.choisirCouleurCarte(listeCouleurs, 1));
			} else if (this.talon.peek() instanceof CartePlusQuatre)
				donneur.repiocherCarteDebutManche(this);
		} while (this.talon.peek() instanceof CartePlusQuatre);
		
		if (modeExecution.equals("modeGraphique"))
			this.changerModeleEtNotifierObservateurs("Fin de la distribution des cartes");
	}
	
	
	// ***************************
	// *** MÉTHODES DE JOUEURS ***
	// ***************************
	
	/**
	 * Méthode permettant de retrouver le joueur courant de la manche à partir du numéro de tour.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 * @return Joueur courant de la manche.
	 */
	public Joueur joueurCourant(ArrayList<Joueur> joueurs) {
		int placeJoueurCourant = this.numeroTour % joueurs.size();
		return this.getJoueur(joueurs, placeJoueurCourant);
	}
	
	/**
	 * Méthode permettant de retrouver le joueur suivant de la manche à partir du numéro de tour.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 * @return Joueur suivant de la manche.
	 */
	public Joueur joueurSuivant(ArrayList<Joueur> joueurs) {
		int placeJoueurCourant;
		
		if (this.sens)
			placeJoueurCourant = (this.numeroTour+1) % joueurs.size();
		else
			placeJoueurCourant = (this.numeroTour-1) % joueurs.size();
		
		return this.getJoueur(joueurs, placeJoueurCourant);
	}
	
	/**
	 * Méthode permettant de récupérer un joueur de la manche à partir de sa place dans l'enchaînement des tours.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 * @param place Place du joueur dans l'enchaînement des tours.
	 */
	public Joueur getJoueur(ArrayList<Joueur> joueurs, int place) {
		int i=0;
		Joueur joueur = null;
		
		do {
			if (place == joueurs.get(i).getPlace())
				joueur = joueurs.get(i);
			i++;
		} while ((i<joueurs.size()) && (joueur == null));
		
		return joueur;
	}
	
	
	// ****************************************
	// *** MÉTHODES D'ATTRIBUTION DE POINTS ***
	// ****************************************
	
	/**
	 * Méthode permettant d'attribuer les points aux différents joueurs en fin de manche.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 * @param modeAttribution Mode d'attribution de points associé à la partie.
	 */
	public void attribuerPoints(ArrayList<Joueur> joueurs, String modeAttribution) {
		System.out.println("Le joueur " + this.gagnant.getNomComplet() + " a gagné la manche !");
		
		if (modeAttribution == "positif")
			this.attribuerPointsModePositif(joueurs);
		else if (modeAttribution == "negatif")
			this.attribuerPointsModeNegatif(joueurs);
	}
	
	/**
	 * Méthode permettant d'attribuer les points au joueur gagnant en fin de manche, en cas d'attribution positive
	 * des points.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 */
	public void attribuerPointsModePositif(ArrayList<Joueur> joueurs) {
		int pointsAjoutesJoueurGagnant = 0;
		int nouveauScoreJoueurGagnant;
		
		for (Joueur joueur : joueurs) {
			for (Carte carte : joueur.getMain()) {
				if (carte instanceof CarteNormale)
					pointsAjoutesJoueurGagnant += ((CarteNormale) carte).getNumero();
				else if ((carte instanceof CartePlusDeux) || (carte instanceof CarteInversion) || (carte instanceof CartePasserUnTour))
					pointsAjoutesJoueurGagnant += 20;
				else if ((carte instanceof CarteJoker) || (carte instanceof CartePlusQuatre))
					pointsAjoutesJoueurGagnant += 50;
			}
		}
		
		nouveauScoreJoueurGagnant = this.gagnant.getScore() + pointsAjoutesJoueurGagnant;
		this.gagnant.setScore(nouveauScoreJoueurGagnant);
		System.out.println("Le joueur " + this.gagnant.getNomComplet() + " a gagné " + pointsAjoutesJoueurGagnant + " points suite à cette manche.");
	}
	
	/**
	 * Méthode permettant d'attribuer les points aux différents joueurs en fin de manche, en cas d'attribution négative
	 * des points.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 */
	public void attribuerPointsModeNegatif(ArrayList<Joueur> joueurs) {
		int pointsAjoutesJoueur = 0;
		int nouveauScoreJoueur;
		
		for (Joueur joueur : joueurs) {
			for (Carte carte : joueur.getMain()) {
				if (carte instanceof CarteNormale)
					pointsAjoutesJoueur += ((CarteNormale) carte).getNumero();
				else if ((carte instanceof CartePlusDeux) || (carte instanceof CarteInversion) || (carte instanceof CartePasserUnTour))
					pointsAjoutesJoueur += 20;
				else if ((carte instanceof CarteJoker) || (carte instanceof CartePlusQuatre))
					pointsAjoutesJoueur += 50;
			}
			nouveauScoreJoueur = joueur.getScore() + pointsAjoutesJoueur;
			joueur.setScore(nouveauScoreJoueur);
		}
	}
	
	
	
	// ****************************************
	// *** MÉTHODE DE MISE À JOUR DE LA VUE ***
	// ****************************************
	
	/**
	 * Méthode permettant d'indiquer un changement de modèle et de notifier les observateurs de la classe Manche du modèle.
	 * 
	 * @param message Message notifié aux observateurs.
	 */
	public void changerModeleEtNotifierObservateurs(String message) {
		this.setChanged();
		this.notifyObservers(message);
	}
	
	
	
	// ****************************
	// *** MÉTHODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * Méthode permettant d'afficher l'ordre des joueurs en début de manche.
	 * 
	 * @param joueurs Liste des joueurs de la manche.
	 */
	public void afficherListeJoueursParOrdre(ArrayList<Joueur> joueurs) {
		String chaineListeJoueurs = "Liste des joueurs par ordre :\n";
		Joueur joueurCourant;
		for (int i=0; i<joueurs.size(); i++) {
			joueurCourant = this.getJoueur(joueurs, i);
			chaineListeJoueurs += "Place de " + joueurCourant.getNom() + " : " + (joueurCourant.getPlace()+1) + ".\n";
		}
		System.out.println(chaineListeJoueurs);
	}
	
	/**
	 * Méthode permettant l'affichage des caractéristiques d'une manche.
	 */
	public String toString() {
		return "Manche [gagnant = " + this.gagnant + ", numeroTour = " + this.numeroTour
				+ ", sens = " + this.sens + ", cartes = " + this.toString(this.cartes) + ", pioche = "
				+ this.toString(this.pioche) + ", talon = " + this.toString(this.talon) + "]";
	}
	
	/**
	 * Méthode permettant l'affichage du contenu d'une liste de cartes.
	 * 
	 * @param cartes Liste de cartes affichée.
	 * @return Chaîne de caractères comportant chacune des cartes de la liste de cartes.
	 */
	public String toString(ArrayList<Carte> cartes) {
		String chaineCartes = "[" + "\n";
		if (cartes.size() != 0)
			chaineCartes += cartes.get(0);
		for (int i=1 ; i<cartes.size() ; i++)
			chaineCartes += ", " + "\n" + cartes.get(i);
		chaineCartes += "\n" + "]";
		return chaineCartes;
	}
	
	/**
	 * Méthode permettant l'affichage du contenu d'un tas de cartes.
	 * 
	 * @param tasDeCartes Tas de cartes affiché.
	 * @return Chaîne de caractères comportant chacune des cartes du tas de cartes.
	 */
	public String toString(TasDeCartes tasDeCartes) {
		String chaineCartes = "[" + "\n";
		if (!tasDeCartes.isEmpty())
			chaineCartes += tasDeCartes.get(0);
		for (int i=1 ; i<tasDeCartes.size() ; i++)
			chaineCartes += ", " + "\n" + tasDeCartes.get(i);
		chaineCartes += "\n" + "]";
		return chaineCartes;
	}
	
	/**
	 * Méthode permettant de balayer l'écran de la console.
	 */
	public void effacerEcranConsole() {
		for (int i=0; i<20; i++)
			System.out.println();
	}
	
	
	// **************************
	// *** MÉTHODES DE SAISIE ***
	// **************************
	
	/**
	 * Méthode permettant de demander au joueur d'appuyer sur la touche "Entrée".
	 */
	public void demanderAppuiSurToucheEntree() {
		@SuppressWarnings("unused")
		char c;
        try {
        	c = (char) System.in.read();
        	c = (char) System.in.read();
        } catch(IOException ioe) {
			System.out.println("Erreur.");
			return;
		}
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur de l'attribut gagnant de la classe Manche.
	 * 
	 * @return Le gagnant de la manche.
	 */
	public Joueur getGagnant() {
		return gagnant;
	}
	
	/**
	 * Accesseur de l'attribut numeroTour de la classe Manche.
	 * 
	 * @return Le numéro de tour de la manche.
	 */
	public int getNumeroTour() {
		return numeroTour;
	}
	
	/**
	 * Accesseur de l'attribut sens de la classe Manche.
	 * 
	 * @return Le sens de la manche.
	 */
	public boolean getSens() {
		return sens;
	}
	
	/**
	 * Accesseur de l'attribut cartes de la classe Manche.
	 * 
	 * @return L'ensemble de cartes de la manche.
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}
	
	/**
	 * Accesseur de l'attribut pioche de la classe Manche.
	 * 
	 * @return La pioche de la manche.
	 */
	public TasDeCartes getPioche() {
		return pioche;
	}
	
	/**
	 * Accesseur de l'attribut talon de la classe Manche.
	 * 
	 * @return Le talon de la manche.
	 */
	public TasDeCartes getTalon() {
		return talon;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur de l'attribut gagnant de la classe Manche.
	 * 
	 * @param gagnant Le nouveau gagnant de la manche.
	 */
	public void setGagnant(Joueur gagnant) {
		this.gagnant = gagnant;
	}
	
	/**
	 * Mutateur de l'attribut numeroTour de la classe Manche.
	 * 
	 * @param numeroTour Le nouveau numéro de tour de la manche.
	 */
	public void setNumeroTour(int numeroTour) {
		this.numeroTour = numeroTour;
	}
	
	/**
	 * Mutateur de l'attribut sens de la classe Manche.
	 * 
	 * @param sens Le nouveau sens de la manche.
	 */
	public void setSens(boolean sens) {
		this.sens = sens;
	}
	
	/**
	 * Mutateur de l'attribut cartes de la classe Manche.
	 * 
	 * @param cartes Le nouvel ensemble de cartes de la manche.
	 */
	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}
	
	/**
	 * Mutateur de l'attribut pioche de la classe Manche.
	 * 
	 * @param pioche La nouvelle pioche de la manche.
	 */
	public void setPioche(TasDeCartes pioche) {
		this.pioche = pioche;
	}
	
	/**
	 * Mutateur de l'attribut talon de la classe Manche.
	 * 
	 * @param talon Le nouveau talon de la manche.
	 */
	public void setTalon(TasDeCartes talon) {
		this.talon = talon;
	}
	
}