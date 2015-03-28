package modele;



/**
 * La classe CarteNormale est une classe fille h�ritant de la classe abstraite Carte.
 * Les cartes normales sont pr�sentes au nombre de 76 dans la version classique du jeu.
 * Contrairement aux cartes sp�ciales, ce sont des cartes sans effet et avec un num�ro.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class CarteNormale extends Carte {
	
	// ****************
	// *** ATTRIBUT ***
	// ****************
	
	/**
	 * Le num�ro de la carte normale.
	 */
	private int numero;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe CarteNormale.
	 * 
	 * @param couleur La couleur choisie pour la carte normale, lors de son instanciation.
	 * @param numero Le num�ro choisi pour la carte normale, lors de son instanciation.
	 */
	public CarteNormale(String couleur, int numero) {
		super(couleur);
		this.numero = numero;
	}
	
	
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * Red�finition de la m�thode abstraite de la classe m�re Carte.
	 * Cette m�thode permet de savoir si une carte normale est jouable, c'est-�-dire si sa couleur ou son num�ro
	 * est le m�me que celui/celle de la carte visible sur le dessus du talon.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert � la comparaison avec une
	 * autre carte, pour savoir si cette derni�re est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon) {
		if (this.getCouleur() == talon.peek().getCouleur())
			return true;
		
		if (talon.peek() instanceof CarteNormale)
			if (this.getNumero() == ((CarteNormale) talon.peek()).getNumero())
				return true;
		
		return false;
	}
	
	
	
	// *****************
	// *** ACCESSEUR ***
	// *****************
	
	/**
	 * Accesseur de l'attribut num�ro de la classe CarteNormale.
	 * 
	 * @return Le num�ro de la carte normale.
	 */
	public int getNumero() {
		return numero;
	}
	
	
	// ****************
	// *** MUTATEUR ***
	// ****************
	
	/**
	 * Mutateur de l'attribut num�ro de la classe CarteNormale.
	 * 
	 * @param numero Le nouveau num�ro de la carte normale.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
	// ****************************
	// *** M�THODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * Affichage de la couleur et du num�ro d'une carte normale.
	 */
	public String toString() {
		return "CarteNormale [couleur = " + couleur + ", numero = " + numero + "]";
	}
	
}