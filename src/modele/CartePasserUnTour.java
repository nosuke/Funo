package modele;

import java.util.ArrayList;



/**
 * La classe CartePasserUnTour est une classe fille héritant de la classe abstraite CarteSpeciale, elle-même héritant de la
 * classe abstraite Carte.
 * Les cartes Passer un tour sont présentes au nombre de 8 dans la version classique du jeu.
 * Elles permettent de faire passer son tour au prochain joueur de la manche.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class CartePasserUnTour extends CarteSpeciale {
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe CartePasserUnTour.
	 * 
	 * @param couleur La couleur choisie pour la carte Passer un tour, lors de son instanciation.
	 */
	public CartePasserUnTour(String couleur) {
		super(couleur);
	}
	
	
	
	// ****************************
	// *** MÉTHODES PRINCIPALES ***
	// ****************************
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère Carte.
	 * Cette méthode permet de savoir si une carte Passer un tour est jouable, c'est-à-dire si sa couleur ou son symbole
	 * (c'est-à-dire le type de la carte spéciale, ici Passer un tour) est le même que celui/celle de la carte visible sur
	 * le dessus du talon.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert à la comparaison avec une autre carte, pour savoir si cette dernière est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon) {
		if (this.getCouleur() == talon.peek().getCouleur())
			return true;
		
		if (talon.peek() instanceof CartePasserUnTour)
			return true;
		
		return false;
	}
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère CarteSpeciale.
	 * Cette méthode permet de déclencher l'effet d'une carte Passer un tour, une fois celle-ci posée.
	 * Effet de la carte Passer un tour : le prochain joueur de la manche passe son tour.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		this.fairePasserTourAuJoueurSuivant(mancheCourante);
		System.out.println("Le prochain joueur (" + mancheCourante.joueurCourant(joueurs).getNomComplet() + ") passe son tour.");
	}
	
}