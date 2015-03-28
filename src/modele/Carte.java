package modele;



/**
 * La classe Carte est une classe mère abstraite permettant l'instanciation de classes filles ayant pour point commun
 * la présence de l'attribut couleur.
 * Les cartes sont au centre du jeu de Uno et sont présentes au nombre de 108 dans la version classique du jeu.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public abstract class Carte implements Jouable {
	
	// ****************
	// *** ATTRIBUT ***
	// ****************
	
	/**
	 * La couleur d'une carte.
	 */
	protected String couleur;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe Carte.
	 * 
	 * @param couleur La couleur choisie pour la carte, lors de son instanciation.
	 */
	public Carte(String couleur) {
		this.couleur = couleur;
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur de l'attribut couleur de la classe Carte.
	 * 
	 * @return La couleur de la carte.
	 */
	public String getCouleur() {
		return couleur;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur de l'attribut couleur de la classe Carte.
	 * 
	 * @param couleur La nouvelle couleur de la carte.
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
}