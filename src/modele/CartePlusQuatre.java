package modele;

import java.util.ArrayList;
import java.util.Scanner;



/**
 * La classe CartePlusQuatre est une classe fille héritant de la classe abstraite CarteSpeciale, elle-même héritant de la
 * classe abstraite Carte.
 * Les cartes +4 sont présentes au nombre de 4 dans la version classique du jeu.
 * Elles permettent de faire piocher quatre cartes et passer son tour au prochain joueur de la manche, ainsi que de choisir
 * leur couleur au moment de leur dépôt sur le talon.
 * Elles peuvent également être jouées à tout moment, même si le fait de les jouer à un moment où une autre carte est
 * jouable dans la main du joueur peut donner lieu à une pénalité, si le joueur suivant souhaite vérifier que le joueur
 * ayant joué la carte +4 avait bien le droit de la déposer.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class CartePlusQuatre extends CarteSpeciale {
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe CartePlusQuatre.
	 * 
	 * @param couleur La couleur choisie pour la carte +4, lors de son instanciation.
	 */
	public CartePlusQuatre(String couleur) {
		super(couleur);
	}
	
	
	
	// ****************************
	// *** MÉTHODES PRINCIPALES ***
	// ****************************
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère Carte.
	 * Cette méthode permet de savoir si une carte +4 est jouable, ce qui est toujours le cas (contrairement à d'autres
	 * types de cartes), puisque c'est une carte +4.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert à la comparaison avec une autre carte, pour savoir si cette dernière est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon) {
		return true;
	}
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère CarteSpeciale.
	 * Cette méthode permet de déclencher l'effet d'une carte +4, une fois celle-ci posée.
	 * Effet de la carte Joker : le prochain joueur de la manche pioche quatre cartes et passe son tour, puis le joueur
	 * ayant déposé la carte +4 choisit la couleur de celle-ci.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		String choixVerification;
		String[] listeCouleurs = {"bleu", "rouge", "jaune", "vert"};
		
		System.out.println("Le joueur suivant (" + mancheCourante.joueurSuivant(joueurs).getNomComplet() + ") veut-il vérifier que le joueur courant (" + mancheCourante.joueurCourant(joueurs).getNomComplet() + ") peut bien utiliser cette carte ?");
		System.out.println("Joueur " + mancheCourante.joueurSuivant(joueurs).getNomComplet() + ", veuillez saisir O/o pour oui ou N/n pour non, puis validez.");
		System.out.println("(Condition d'utilisation de la carte : aucune autre carte que cette carte +4 ne peut être jouée dans la main du joueur.)");
		
		if (mancheCourante.joueurSuivant(joueurs) instanceof JoueurHumain) {
			ArrayList<String> listeChainesDeCaracteresPossibles = new ArrayList<String>();
			listeChainesDeCaracteresPossibles.add("O");
			listeChainesDeCaracteresPossibles.add("o");
			listeChainesDeCaracteresPossibles.add("N");
			listeChainesDeCaracteresPossibles.add("n");
			choixVerification = this.saisirChaineDeCaracteres(listeChainesDeCaracteresPossibles);
			
			if (choixVerification.equals("O") || choixVerification.equals("o")) {
				this.declencherVerification(mancheCourante, joueurs);
			} else if (choixVerification.equals("N") || choixVerification.equals("n")) {
				this.couleur = mancheCourante.joueurCourant(joueurs).choisirCouleurCarte(listeCouleurs, 2);
				this.fairePasserTourEtPiocherCartesAuJoueur(mancheCourante, joueurs, mancheCourante.joueurSuivant(joueurs), 4);
				System.out.println("Le prochain joueur (" + mancheCourante.joueurCourant(joueurs).getNomComplet() + ") passe son tour et pioche quatre cartes.");
			}
		} else if (mancheCourante.joueurSuivant(joueurs) instanceof JoueurVirtuel) {
			if (mancheCourante.joueurCourant(joueurs).getMain().size() >= 5) {
				this.declencherVerification(mancheCourante, joueurs);
			} else {
				this.couleur = mancheCourante.joueurCourant(joueurs).choisirCouleurCarte(listeCouleurs, 2);
				this.fairePasserTourEtPiocherCartesAuJoueur(mancheCourante, joueurs, mancheCourante.joueurSuivant(joueurs), 4);
				System.out.println("Le prochain joueur (" + mancheCourante.joueurCourant(joueurs).getNomComplet() + ") passe son tour et pioche quatre cartes.");
			}
		}
	}
	
	/**
	 * La méthode permet de vérifier si le joueur avait le droit de jouer la carte +4, puis d'agir en conséquence.
	 * S'il avait le droit, le joueur suivant pioche six cartes au lieu de seulement quatre.
	 * S'il n'avait pas le droit, le joueur ayant déposé la carte +4 la reprend, pioche quatre cartes et passe son tour.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	private void declencherVerification(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		String[] listeCouleurs = {"bleu", "rouge", "jaune", "vert"};
		
		if (mancheCourante.joueurCourant(joueurs).pouvoirJouerSansPlusQuatre(mancheCourante.getTalon())) {
			System.out.println("La vérification révèle que le joueur n'avait pas le droit de jouer sa carte +4.");
			ArrayList<Carte> nouvelleMain = mancheCourante.joueurCourant(joueurs).getMain();
			nouvelleMain.add(mancheCourante.getTalon().pop());
			mancheCourante.joueurCourant(joueurs).setMain(nouvelleMain);
			this.fairePasserTourEtPiocherCartesAuJoueur(mancheCourante, joueurs, mancheCourante.joueurCourant(joueurs), 4);
			System.out.println("Le joueur actuel (" + mancheCourante.joueurCourant(joueurs).getNomComplet() + ") passe son tour et pioche quatre cartes de pénalité, en plus de reprendre sa carte +4.");
		} else {
			System.out.println("La vérification révèle que le joueur avait bien le droit de jouer sa carte +4.");
			this.couleur = mancheCourante.joueurCourant(joueurs).choisirCouleurCarte(listeCouleurs, 2);
			this.fairePasserTourEtPiocherCartesAuJoueur(mancheCourante, joueurs, mancheCourante.joueurSuivant(joueurs), 6);
			System.out.println("Le prochain joueur (" + mancheCourante.joueurCourant(joueurs).getNomComplet() + ") passe son tour et pioche six cartes (dont deux de pénalité pour avoir vérifié).");
		}
	}
	
	
	
	// *************************
	// *** MÉTHODE DE SAISIE ***
	// *************************
	
	/**
	 * Effectue une saisie et vérifie si celle-ci correspond à la saisie d'un des caractères attendus.
	 * Si la correspondance n'est pas avérée, il y affichage d'un message décrivant le problème et à nouveau appel
	 * à la méthode (système d'appel récursif).
	 * 
	 * @param listeChainesDeCaracteresPossibles La liste des chaines de caractères possibles lors de la saisie.
	 * @return La saisie, une fois que celle-ci correspond à l'une des valeurs attendues.
	 */
	@SuppressWarnings("resource")
	private String saisirChaineDeCaracteres(ArrayList<String> listeChainesDeCaracteresPossibles) {
		String saisieChaineDeCaracteres;
		
		saisieChaineDeCaracteres = new Scanner(System.in).next();
		
		if (!listeChainesDeCaracteresPossibles.contains(saisieChaineDeCaracteres)) {
			System.out.println("Veuillez saisir l'un des caractères demandés.");
			return saisirChaineDeCaracteres(listeChainesDeCaracteresPossibles);
		} else
			return saisieChaineDeCaracteres;
	}
	
}