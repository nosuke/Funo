package modele;

import java.util.ArrayList;



/**
 * L'interface Strategie donne la méthode qu'implantent les différents types de stratégies.
 * Cette interface se base sur le patron de conception Strategie, qui sert pour permettre de donner différentes stratégies
 * possibles à une intelligence artificielle.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public interface Strategie {
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Méthode abstraite redéfinie dans les classes implantant l'interface Strategie.
	 * En effet, les différents types de stratégies suivent différentes stratégies (donc démarches).
	 * 
	 * @param cartesJouablesMain La liste des cartes jouables de la main du joueur virtuel associé à la stratégie.
	 * @return La place dans la main de la carte choisie.
	 */
	public int suivreStrategie(ArrayList<Carte> cartesJouablesMain);
	
}