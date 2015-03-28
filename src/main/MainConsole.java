package main;

import modele.Partie;



/**
 * La classe MainConsole est la classe principale de la partie console. Autrement dit, c'est la classe qui sert �
 * d�marrer le jeu en mode console. Il existe l'�quivalent pour le mode graphique.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class MainConsole {
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * La m�thode principale de la classe principale de la partie console. Elle sert � d�marrer le jeu en mode console.
	 * 
	 * @param args Le tableau des arguments indiqu�s lors de l'ex�cution du programme, si jamais ces arguments suppl�mentaires ont �t� indiqu�s.
	 */
	public static void main(String[] args) {
		Partie partie = new Partie("modeConsole");
		partie.seJouer("modeConsole");
	}

}