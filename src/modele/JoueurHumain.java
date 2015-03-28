package modele;

import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * La classe JoueurHumain est une classe fille héritant de la classe abstraite Joueur.
 * Un joueur humain, contrairement à un joueur virtuel, a besoin d'affichages et réalise des saisies.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class JoueurHumain extends Joueur {
	
	// ****************
	// *** ATTRIBUT ***
	// ****************
	
	/**
	 * Le prénom d'un joueur humain.
	 */
	private String prenom;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe JoueurHumain.
	 * 
	 * @param nom Le nom choisi pour le joueur humain, lors de son instanciation.
	 * @param prenom Le prénom choisi pour le joueur humain, lors de son instanciation.
	 */
	public JoueurHumain(String nom, String prenom) {
		super(nom);
		this.prenom = prenom;
	}
	
	
	// *************************
	// *** MÉTHODES DE CHOIX ***
	// *************************
	
	/**
	 * Méthode rédéfinie servant à un joueur humain à saisir le numéro de la carte qu'il souhaite jouer sur le talon,
	 * parmi les cartes de sa main.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où le joueur humain peut choisir une carte à déposer au sein de sa main.
	 * @return La carte choisie par le joueur humain, au sein de sa main.
	 */
	public Carte choisirCarteADeposer(Manche mancheCourante) {
		Carte carteChoisie;
		int choixNumeroCarteMain;
		
		System.out.println("Veuillez choisir la carte que vous souhaitez jouer parmi toutes les cartes de votre main.");
		System.out.println("Pour cela, veuillez saisir son numéro, puis validez. Si vous ne souhaitez pas jouer, saisissez le nombre '0'.");
		choixNumeroCarteMain = saisirEntier(0, this.getMain().size());
		
		if (choixNumeroCarteMain != 0) {
			carteChoisie = this.getMain().get(choixNumeroCarteMain-1);
		
			if (!carteChoisie.estJouable(mancheCourante.getTalon())) {
				do {
					System.out.println("Veuillez saisir le numéro d'une carte jouable sur le talon. Si vous ne souhaitez pas jouer, saisissez le nombre '0'.");
					choixNumeroCarteMain = saisirEntier(0, this.getMain().size());
					if (choixNumeroCarteMain != 0)
						carteChoisie = this.getMain().get(choixNumeroCarteMain-1);
				} while ((!carteChoisie.estJouable(mancheCourante.getTalon())) && (choixNumeroCarteMain != 0));
			}
			
			if (choixNumeroCarteMain != 0)
				this.getMain().remove(choixNumeroCarteMain-1);
			else
				carteChoisie = null;
		} else
			carteChoisie = null;
		
		return carteChoisie;
	}
	
	/**
	 * Méthode redéfinie servant à un joueur humain à saisir le numéro de la couleur qu'il souhaite donner à la carte
	 * sélectionnée.
	 * 
	 * @param listeCouleurs La liste des couleurs proposées au joueur humain lors du choix.
	 * @param situation La situation d'utilisation de la méthode, c'est-à-dire soit lors de la pioche d'une carte Joker en début de manche pour la première situation, soit lors de l'utilisation d'une carte Joker/+4 par un joueur pour la seconde situation.
	 * @return La chaîne de caractères représentant la couleur choisie pour la carte.
	 */
	public String choisirCouleurCarte(String[] listeCouleurs, int situation) {
		int choixNumeroCouleurListe;
		
		this.afficherChoixCouleurCarte(situation);
		choixNumeroCouleurListe = this.saisirEntier(1, listeCouleurs.length);
		
		return listeCouleurs[choixNumeroCouleurListe-1];
	}
	
	
	
	// ****************************
	// *** MÉTHODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * Méthode affichant la possibilité de choisir la couleur de la carte en fonction de la situation.
	 * 
	 * @param situation La situation d'utilisation de la méthode, c'est-à-dire soit lors de la pioche d'une carte Joker en début de manche pour la première situation, soit lors de l'utilisation d'une carte Joker/+4 par un joueur pour la seconde situation.
	 */
	public void afficherChoixCouleurCarte(int situation) {
		if (situation == 1)
			System.out.println("Donneur " + this.getNomComplet() + ", veuillez choisir la couleur que vous attribuez à la carte joker visible sur le dessus du talon.");
		else if (situation == 2)
			System.out.println("Joueur " + this.getNomComplet() + ", votre dernier dépôt implique que vous choisissiez la couleur de votre carte.");
		
		System.out.println("Pour cela, veuillez saisir son numéro dans la liste suivante, puis validez.");
		System.out.println("Liste des couleurs : [ Couleur n°1 (bleu), Couleur n°2 (rouge), Couleur n°3 (jaune), Couleur n°4 (vert) ]");
	}
	
	/**
	 * Méthode redéfinie retournant le nom complet du joueur humain, c'est-à-dire son nom et son prénom.
	 * 
	 * @return Le nom complet du joueur humain.
	 */
	public String getNomComplet() {
		return this.getPrenom() + " " + this.getNom();
	}
	
	
	// **************************
	// *** MÉTHODES DE SAISIE ***
	// **************************
	
	/**
	 * Méthode redéfinie déclenchant la création d'un Thread ThreadTemps obligeant au joueur humain concerné à saisir
	 * le mot "UNO" dans les cinq secondes suivant le dépôt de l'avant-dernière carte de sa main sur le talon, s'il
	 * ne souhaite pas devoir piocher deux cartes de pénalité.
	 * 
	 * @return Vrai si la saisie a été faite à temps ; faux sinon.
	 */
	@SuppressWarnings("resource")
	public boolean saisirUNO() {
		ThreadTemps threadTemps = new ThreadTemps();
		String saisieUNO;
		boolean saisieUNOeffectuee = false;
		threadTemps.start();
		
		do {
			saisieUNO = new Scanner(System.in).next();
			if (saisieUNO.equals("UNO") || saisieUNO.equals("uno")) {
				saisieUNOeffectuee = true;
				threadTemps.interrupt();
			}
		} while (threadTemps.isAlive() && !saisieUNOeffectuee);
		
		return saisieUNOeffectuee;
	}
	
	/**
	 * Méthode permettant de traiter la saisie d'un entier par un joueur humain, lors des demandes de saisie d'entier
	 * (choix de carte dans la main, choix de couleur pour une carte).
	 * 
	 * @param min Entier minimum que le joueur humain puisse saisir.
	 * @param max Entier maximum que le joueur humain puisse saisir.
	 * @return Le nombre saisi par le joueur humain et contenu entre min et max.
	 */
	public int saisirEntier(int min, int max) {
		int saisieEntier;
		
		try {
			saisieEntier = new Scanner(System.in).nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("Veuillez saisir un nombre plutôt qu'un caractère.");
			return saisirEntier(min, max);
		}
		
		if ((saisieEntier<min)||(saisieEntier>max)) {
			System.out.println("Veuillez saisir un nombre compris entre " + min + " et " + max + ".");
			return saisirEntier(min, max);
		} else
			return saisieEntier;
	}
	
	
	
	// *****************
	// *** ACCESSEUR ***
	// *****************
	
	/**
	 * Accesseur de l'attribut prenom de la classe JoueurHumain.
	 * 
	 * @return Le prénom du joueur humain.
	 */
	public String getPrenom() {
		return prenom;
	}
	
	
	// ****************
	// *** MUTATEUR ***
	// ****************
	
	/**
	 * Mutateur de l'attribut prenom de la classe JoueurHumain.
	 * 
	 * @param prenom Le nouveau prénom du joueur humain.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}