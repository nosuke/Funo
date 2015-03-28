package modele;

import java.util.ArrayList;



/**
 * La classe CarteSpeciale est une classe mère abstraite permettant l'instanciation de classes filles ayant pour point
 * commun l'absence de l'attribut numéro et le déclenchement d'un effet, une fois la carte déposée.
 * Les cartes spéciales sont présentes au nombre de 32 dans la version classique du jeu.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public abstract class CarteSpeciale extends Carte {
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe CarteSpeciale.
	 * 
	 * @param couleur La couleur choisie pour la carte spéciale, lors de son instanciation.
	 */
	public CarteSpeciale(String couleur) {
		super(couleur);
	}
	
	
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Méthode abstraite redéfinie dans les classes filles.
	 * En effet, les différents types de cartes spéciales ne déclenchent pas les mêmes effets.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public abstract void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs);
	
	
	// ******************************************
	// *** MÉTHODES DE DÉCLENCHEMENT D'EFFETS ***
	// ******************************************
	
	/**
	 * Méthode permettant de faire passer son tour au joueur suivant.
	 * Est utilisée dans trois classes filles (CartePasserUnTour, CartePlusDeux, CartePlusQuatre), d'où sa présence au sein
	 * de la classe mère abstraite CarteSpeciale.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 */
	protected void fairePasserTourAuJoueurSuivant(Manche mancheCourante) {
		if (mancheCourante.getSens())
			mancheCourante.setNumeroTour(mancheCourante.getNumeroTour()+1);
		else
			mancheCourante.setNumeroTour(mancheCourante.getNumeroTour()-1);
	}
	
	/**
	 * Méthode permettant de faire passer son tour au joueur suivant et de lui faire piocher un certain nombre de cartes
	 * (le nombre précis est indiqué en paramètre).
	 * Est utilisée dans deux classes filles (CartePlusDeux, CartePlusQuatre), d'où sa présence au sein de la classe mère
	 * abstraite CarteSpeciale.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 * @param joueur Le joueur devant piocher des cartes et passer son tour.
	 * @param nombreCartes Le nombre de cartes que le joueur concerné doit piocher.
	 */
	protected void fairePasserTourEtPiocherCartesAuJoueur(Manche mancheCourante, ArrayList<Joueur> joueurs, Joueur joueur, int nombreCartes) {
		if (joueur == mancheCourante.joueurSuivant(joueurs))
			fairePasserTourAuJoueurSuivant(mancheCourante);
		for (int i=0; i<nombreCartes; i++)
			joueur.piocherCarte(mancheCourante);
	}
	
	
	
	// ****************************
	// *** MÉTHODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * Affichage de la couleur et du type d'une carte spéciale.
	 */
	public String toString() {
		return this.getClass().getName() + " [couleur = " + this.couleur + "]";
	}
	
}