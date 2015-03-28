package modele;

import java.util.ArrayList;



/**
 * La classe CarteInversion est une classe fille héritant de la classe abstraite CarteSpeciale, elle-même héritant de la
 * classe abstraite Carte.
 * Les cartes Inversion sont présentes au nombre de 8 dans la version classique du jeu.
 * Elles permettent de changer le sens de la manche, c'est-à-dire l'enchaînement des tours entre les différents joueurs de
 * la partie.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class CarteInversion extends CarteSpeciale {
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe CarteInversion.
	 * 
	 * @param couleur La couleur choisie pour la carte Inversion, lors de son instanciation.
	 */
	public CarteInversion(String couleur) {
		super(couleur);
	}
	
	
	
	// ****************************
	// *** MÉTHODES PRINCIPALES ***
	// ****************************
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère Carte.
	 * Cette méthode permet de savoir si une carte Inversion est jouable, c'est-à-dire si sa couleur ou son symbole
	 * (c'est-à-dire le type de la carte spéciale, ici Inversion) est le même que celui/celle de la carte visible sur le
	 * dessus du talon.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert à la comparaison avec une autre carte, pour savoir si cette dernière est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon) {
		if (this.getCouleur() == talon.peek().getCouleur())
			return true;
		
		if (talon.peek() instanceof CarteInversion)
			return true;
		
		return false;
	}
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère CarteSpeciale.
	 * Cette méthode permet de déclencher l'effet d'une carte Inversion, une fois celle-ci posée.
	 * Effet de la carte Inversion : le sens de la manche change, c'est-à-dire que le sens de l'enchaînement des tours
	 * entre les différents joueurs de la partie change.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		if (mancheCourante.getSens())
			mancheCourante.setSens(false);
		else
			mancheCourante.setSens(true);
		System.out.println("L'enchaînement des tours au sein des joueurs a changé de sens. Le prochain joueur à jouer est donc " + mancheCourante.joueurSuivant(joueurs).getNomComplet() + ".");
	}
	
}