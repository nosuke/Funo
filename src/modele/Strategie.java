package modele;

import java.util.ArrayList;



/**
 * L'interface Strategie donne la m�thode qu'implantent les diff�rents types de strat�gies.
 * Cette interface se base sur le patron de conception Strategie, qui sert pour permettre de donner diff�rentes strat�gies
 * possibles � une intelligence artificielle.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public interface Strategie {
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * M�thode abstraite red�finie dans les classes implantant l'interface Strategie.
	 * En effet, les diff�rents types de strat�gies suivent diff�rentes strat�gies (donc d�marches).
	 * 
	 * @param cartesJouablesMain La liste des cartes jouables de la main du joueur virtuel associ� � la strat�gie.
	 * @return La place dans la main de la carte choisie.
	 */
	public int suivreStrategie(ArrayList<Carte> cartesJouablesMain);
	
}