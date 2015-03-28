package main;

import controleur.ControleurMenu;
import vue.FenetreMenu;
import modele.Partie;



/**
 * La classe MainGraphique est la classe principale de la partie graphique. Autrement dit, c'est la classe qui sert à
 * démarrer le jeu en mode graphique. Il existe l'équivalent pour le mode console.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class MainGraphique {
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * La méthode principale de la classe principale de la partie graphique. Elle sert à démarrer le jeu en mode graphique.
	 * 
	 * @param args Le tableau des arguments indiqués lors de l'exécution du programme, si jamais ces arguments supplémentaires ont été indiqués.
	 */
	public static void main(String[] args) {
		Partie partie = new Partie("modeGraphique");
		FenetreMenu fenetreMenu = new FenetreMenu(partie);
		new ControleurMenu(partie, fenetreMenu);
	}
	
}