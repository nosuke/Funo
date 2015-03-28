package modele;



/**
 * L'interface Jouable donne la m�thode qu'implante la classe Carte.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public interface Jouable {
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * M�thode abstraite red�finie dans les classes filles de la classe implantant l'interface Jouable.
	 * En effet, les diff�rents types de cartes ne poss�dent pas les m�mes conditions de jouabilit�.
	 * 
	 * @param talon Le tas de cartes dont la carte visible sur le dessus du tas sert � la comparaison avec une autre carte, pour savoir si cette derni�re est jouable.
	 * @return Vrai si la carte est jouable sur le talon, faux sinon.
	 */
	public boolean estJouable(TasDeCartes talon);
	
}