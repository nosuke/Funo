package modele;



/**
 * La classe CarteNormale est une classe fille héritant de la classe abstraite Carte.
 * Les cartes normales sont présentes au nombre de 76 dans la version classique du jeu.
 * Contrairement aux cartes spéciales, ce sont des cartes sans effet et avec un numéro.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class CarteNormale extends Carte {
	
	// ****************
	// *** ATTRIBUT ***
	// ****************
	
	/**
	 * Le numéro de la carte normale.
	 */
	private int numero;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe CarteNormale.
	 * 
	 * @param couleur La couleur choisie pour la carte normale, lors de son instanciation.
	 * @param numero Le numéro choisi pour la carte normale, lors de son instanciation.
	 */
	public CarteNormale(String couleur, int numero) {
		super(couleur);
		this.numero = numero;
	}
	
	
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Redéfinition de la méthode abstraite de la classe mère Carte.
	 * Cette méthode permet de savoir si une carte normale est jouable, c'est-à-dire si sa couleur ou son numéro
	 * est le même que celui/celle de la carte visible sur le dessus du talon.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert à la comparaison avec une
	 * autre carte, pour savoir si cette dernière est jouable.
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
	 * Accesseur de l'attribut numéro de la classe CarteNormale.
	 * 
	 * @return Le numéro de la carte normale.
	 */
	public int getNumero() {
		return numero;
	}
	
	
	// ****************
	// *** MUTATEUR ***
	// ****************
	
	/**
	 * Mutateur de l'attribut numéro de la classe CarteNormale.
	 * 
	 * @param numero Le nouveau numéro de la carte normale.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
	// ****************************
	// *** MÉTHODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * Affichage de la couleur et du numéro d'une carte normale.
	 */
	public String toString() {
		return "CarteNormale [couleur = " + couleur + ", numero = " + numero + "]";
	}
	
}