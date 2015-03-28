package main;

import modele.Partie;



/**
 * La classe MainConsole est la classe principale de la partie console. Autrement dit, c'est la classe qui sert à
 * démarrer le jeu en mode console. Il existe l'équivalent pour le mode graphique.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class MainConsole {
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * La méthode principale de la classe principale de la partie console. Elle sert à démarrer le jeu en mode console.
	 * 
	 * @param args Le tableau des arguments indiqués lors de l'exécution du programme, si jamais ces arguments supplémentaires ont été indiqués.
	 */
	public static void main(String[] args) {
		Partie partie = new Partie("modeConsole");
		partie.seJouer("modeConsole");
	}

}