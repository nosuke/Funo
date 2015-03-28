package modele;



/**
 * L'interface Jouable donne la méthode qu'implante la classe Carte.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public interface Jouable {
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Méthode abstraite redéfinie dans les classes filles de la classe implantant l'interface Jouable.
	 * En effet, les différents types de cartes ne possèdent pas les mêmes conditions de jouabilité.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert à la comparaison avec une autre carte, pour savoir si cette dernière est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon);
	
}