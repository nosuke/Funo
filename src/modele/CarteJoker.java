package modele;

import java.util.ArrayList;



/**
 * La classe CarteJoker est une classe fille h�ritant de la classe abstraite CarteSpeciale, elle-m�me h�ritant de la
 * classe abstraite Carte.
 * Les cartes Joker sont pr�sentes au nombre de 4 dans la version classique du jeu.
 * Elles permettent de choisir leur couleur au moment de leur d�p�t sur le talon.
 * Elles peuvent �galement �tre jou�es � tout moment.
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
	// *** M�THODES PRINCIPALES ***
	// ****************************
	
	/**
	 * Red�finition de la m�thode abstraite de la classe m�re Carte.
	 * Cette m�thode permet de savoir si une carte Joker est jouable, ce qui est toujours le cas (contrairement � d'autres
	 * types de cartes), puisque c'est une carte Joker.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert � la comparaison avec une autre carte, pour savoir si cette derni�re est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon) {
		return true;
	}
	
	/**
	 * Red�finition de la m�thode abstraite de la classe m�re CarteSpeciale.
	 * Cette m�thode permet de d�clencher l'effet d'une carte Joker, une fois celle-ci pos�e.
	 * Effet de la carte Joker : le joueur choisit la couleur de la carte Joker.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs) {
		String[] listeCouleurs = {"bleu", "rouge", "jaune", "vert"};
		this.couleur = mancheCourante.joueurCourant(joueurs).choisirCouleurCarte(listeCouleurs, 2);
	}
	
}