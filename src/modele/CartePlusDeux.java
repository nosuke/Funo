package modele;

import java.util.ArrayList;



/**
 * La classe CartePlusDeux est une classe fille héritant de la classe abstraite CarteSpeciale, elle-même héritant de la
 * classe abstraite Carte.
 * Les cartes +2 sont présentes au nombre de 8 dans la version classique du jeu.
 * Elles permettent de faire piocher deux cartes et passer son tour au prochain joueur de la manche.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class CartePlusDeux extends CarteSpeciale {
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe CartePlusDeux.
	 * 
	 * @param couleur La couleur choisie pour la carte +2, lors de son instanciation.
	 */
	public CartePlusDeux(String couleur) {
		super(couleur);
	}
	
	
	
	// ****************************
	// *** MÉTHODES PRINCIPALES ***
	// ****************************
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère Carte.
	 * Cette méthode permet de savoir si une carte +2 est jouable, c'est-à-dire si sa couleur ou son symbole (c'est-à-dire
	 * le type de la carte spéciale, ici +2) est le même que celui/celle de la carte visible sur le dessus du talon.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert à la comparaison avec une autre carte, pour savoir si cette dernière est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon) {
		if (this.getCouleur() == talon.peek().getCouleur())
			return true;
		
		if (talon.peek() instanceof CartePlusDeux)
			return true;
		
		return false;
	}
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère CarteSpeciale.
	 * Cette méthode permet de déclencher l'effet d'une carte +2, une fois celle-ci posée.
	 * Effet de la carte +2 : le prochain joueur de la manche pioche deux cartes et passe son tour.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		this.fairePasserTourEtPiocherCartesAuJoueur(mancheCourante, joueurs, mancheCourante.joueurSuivant(joueurs), 2);
		System.out.println("Le prochain joueur (" + mancheCourante.joueurCourant(joueurs).getNomComplet() + ") passe son tour et pioche deux cartes.");
	}
	
}