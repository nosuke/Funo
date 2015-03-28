package modele;

import java.util.ArrayList;



/**
 * La classe CarteJoker est une classe fille héritant de la classe abstraite CarteSpeciale, elle-même héritant de la
 * classe abstraite Carte.
 * Les cartes Joker sont présentes au nombre de 4 dans la version classique du jeu.
 * Elles permettent de choisir leur couleur au moment de leur dépôt sur le talon.
 * Elles peuvent également être jouées à tout moment.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class CarteJoker extends CarteSpeciale {
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe CarteJoker.
	 * 
	 * @param couleur La couleur choisie pour la carte Joker, lors de son instanciation.
	 */
	public CarteJoker(String couleur) {
		super(couleur);
	}
	
	
	
	// ****************************
	// *** MÉTHODES PRINCIPALES ***
	// ****************************
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère Carte.
	 * Cette méthode permet de savoir si une carte Joker est jouable, ce qui est toujours le cas (contrairement à d'autres
	 * types de cartes), puisque c'est une carte Joker.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert à la comparaison avec une autre carte, pour savoir si cette dernière est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon) {
		return true;
	}
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère CarteSpeciale.
	 * Cette méthode permet de déclencher l'effet d'une carte Joker, une fois celle-ci posée.
	 * Effet de la carte Joker : le joueur choisit la couleur de la carte Joker.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		String[] listeCouleurs = {"bleu", "rouge", "jaune", "vert"};
		this.couleur = mancheCourante.joueurCourant(joueurs).choisirCouleurCarte(listeCouleurs, 2);
	}
	
}