package modele;

import java.util.Collections;
import java.util.Stack;



/**
 * La classe TasDeCartes hérite de la classe Stack<Carte>. Autrement dit, c'est une pile (= LIFO : Last In, First Out) de
 * cartes. Elle permet de regrouper les cartes sous une forme la plus fidèle possible à celle d'une pile de cartes réelle.
 * De plus, il est très pratique d'utiliser les méthodes pop, push et peek, qui font quasiment tout ce qu'on souhaite d'un
 * tas de cartes.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class TasDeCartes extends Stack<Carte> {
	
	/**
	 * Attribut lié au statut sérialisable de la classe Stack, qui implante l'interface Serializable.
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
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Méthode permettant le mélange des cartes contenues dans le tas de cartes.
	 */
	public void seMelanger() {
		Collections.shuffle(this);
	}
	
}