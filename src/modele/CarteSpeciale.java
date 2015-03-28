package modele;

import java.util.ArrayList;



/**
 * La classe CarteSpeciale est une classe m�re abstraite permettant l'instanciation de classes filles ayant pour point
 * commun l'absence de l'attribut num�ro et le d�clenchement d'un effet, une fois la carte d�pos�e.
 * Les cartes sp�ciales sont pr�sentes au nombre de 32 dans la version classique du jeu.
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
	 * @param couleur La couleur choisie pour la carte sp�ciale, lors de son instanciation.
	 */
	public CarteSpeciale(String couleur) {
		super(couleur);
	}
	
	
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * M�thode abstraite red�finie dans les classes filles.
	 * En effet, les diff�rents types de cartes sp�ciales ne d�clenchent pas les m�mes effets.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 */
	public abstract void declencherEffet(Manche mancheCourante, ArrayList<Joueur> joueurs);
	
	
	// ******************************************
	// *** M�THODES DE D�CLENCHEMENT D'EFFETS ***
	// ******************************************
	
	/**
	 * M�thode permettant de faire passer son tour au joueur suivant.
	 * Est utilis�e dans trois classes filles (CartePasserUnTour, CartePlusDeux, CartePlusQuatre), d'o� sa pr�sence au sein
	 * de la classe m�re abstraite CarteSpeciale.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� aura effet la carte.
	 */
	protected void fairePasserTourAuJoueurSuivant(Manche mancheCourante) {
		if (mancheCourante.getSens())
			mancheCourante.setNumeroTour(mancheCourante.getNumeroTour()+1);
		else
			mancheCourante.setNumeroTour(mancheCourante.getNumeroTour()-1);
	}
	
	/**
	 * M�thode permettant de faire passer son tour au joueur suivant et de lui faire piocher un certain nombre de cartes
	 * (le nombre pr�cis est indiqu� en param�tre).
	 * Est utilis�e dans deux classes filles (CartePlusDeux, CartePlusQuatre), d'o� sa pr�sence au sein de la classe m�re
	 * abstraite CarteSpeciale.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� aura effet la carte.
	 * @param joueurs La liste des joueurs de la partie courante.
	 * @param joueur Le joueur devant piocher des cartes et passer son tour.
	 * @param nombreCartes Le nombre de cartes que le joueur concern� doit piocher.
	 */
	protected void fairePasserTourEtPiocherCartesAuJoueur(Manche mancheCourante, ArrayList<Joueur> joueurs, Joueur joueur, int nombreCartes) {
		if (joueur == mancheCourante.joueurSuivant(joueurs))
			fairePasserTourAuJoueurSuivant(mancheCourante);
		for (int i=0; i<nombreCartes; i++)
			joueur.piocherCarte(mancheCourante);
	}
	
	
	
	// ****************************
	// *** M�THODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * Affichage de la couleur et du type d'une carte sp�ciale.
	 */
	public String toString() {
		return this.getClass().getName() + " [couleur = " + this.couleur + "]";
	}
	
}