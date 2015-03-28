package modele;

import java.util.ArrayList;



/**
 * La classe CarteInversion est une classe fille h�ritant de la classe abstraite CarteSpeciale, elle-m�me h�ritant de la
 * classe abstraite Carte.
 * Les cartes Inversion sont pr�sentes au nombre de 8 dans la version classique du jeu.
 * Elles permettent de changer le sens de la manche, c'est-�-dire l'encha�nement des tours entre les diff�rents joueurs de
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
	// *** M�THODES PRINCIPALES ***
	// ****************************
	
	/**
	 * Red�finition de la m�thode abstraite de la classe m�re Carte.
	 * Cette m�thode permet de savoir si une carte Inversion est jouable, c'est-�-dire si sa couleur ou son symbole
	 * (c'est-�-dire le type de la carte sp�ciale, ici Inversion) est le m�me que celui/celle de la carte visible sur le
	 * dessus du talon.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert � la comparaison avec une autre carte, pour savoir si cette derni�re est jouable.
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
	 * Red�finition de la m�thode abstraite de la classe m�re CarteSpeciale.
	 * Cette m�thode permet de d�clencher l'effet d'une carte Inversion, une fois celle-ci pos�e.
	 * Effet de la carte Inversion : le sens de la manche change, c'est-�-dire que le sens de l'encha�nement des tours
	 * entre les diff�rents joueurs de la partie change.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		if (mancheCourante.getSens())
			mancheCourante.setSens(false);
		else
			mancheCourante.setSens(true);
		System.out.println("L'encha�nement des tours au sein des joueurs a chang� de sens. Le prochain joueur � jouer est donc " + mancheCourante.joueurSuivant(joueurs).getNomComplet() + ".");
	}
	
}