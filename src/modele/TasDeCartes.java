package modele;

import java.util.Collections;
import java.util.Stack;



/**
 * La classe TasDeCartes h�rite de la classe Stack<Carte>. Autrement dit, c'est une pile (= LIFO : Last In, First Out) de
 * cartes. Elle permet de regrouper les cartes sous une forme la plus fid�le possible � celle d'une pile de cartes r�elle.
 * De plus, il est tr�s pratique d'utiliser les m�thodes pop, push et peek, qui font quasiment tout ce qu'on souhaite d'un
 * tas de cartes.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class TasDeCartes extends Stack<Carte> {
	
	/**
	 * Attribut li� au statut s�rialisable de la classe Stack, qui implante l'interface Serializable.
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe TasDeCartes.
	 */
	public TasDeCartes() { }
	
	
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * M�thode permettant le m�lange des cartes contenues dans le tas de cartes.
	 */
	public void seMelanger() {
		Collections.shuffle(this);
	}
	
}