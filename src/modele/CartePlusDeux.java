package modele;

import java.util.ArrayList;



/**
 * La classe CartePlusDeux est une classe fille h�ritant de la classe abstraite CarteSpeciale, elle-m�me h�ritant de la
 * classe abstraite Carte.
 * Les cartes +2 sont pr�sentes au nombre de 8 dans la version classique du jeu.
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
	// *** M�THODES PRINCIPALES ***
	// ****************************
	
	/**
	 * Red�finition de la m�thode abstraite de la classe m�re Carte.
	 * Cette m�thode permet de savoir si une carte +2 est jouable, c'est-�-dire si sa couleur ou son symbole (c'est-�-dire
	 * le type de la carte sp�ciale, ici +2) est le m�me que celui/celle de la carte visible sur le dessus du talon.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert � la comparaison avec une autre carte, pour savoir si cette derni�re est jouable.
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
	 * Red�finition de la m�thode abstraite de la classe m�re CarteSpeciale.
	 * Cette m�thode permet de d�clencher l'effet d'une carte +2, une fois celle-ci pos�e.
	 * Effet de la carte +2 : le prochain joueur de la manche pioche deux cartes et passe son tour.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		this.fairePasserTourEtPiocherCartesAuJoueur(mancheCourante, joueurs, mancheCourante.joueurSuivant(joueurs), 2);
		System.out.println("Le prochain joueur (" + mancheCourante.joueurCourant(joueurs).getNomComplet() + ") passe son tour et pioche deux cartes.");
	}
	
}