package main;

import controleur.ControleurMenu;
import vue.FenetreMenu;
import modele.Partie;



/**
 * La classe MainGraphique est la classe principale de la partie graphique. Autrement dit, c'est la classe qui sert �
 * d�marrer le jeu en mode graphique. Il existe l'�quivalent pour le mode console.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class MainGraphique {
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * La m�thode principale de la classe principale de la partie graphique. Elle sert � d�marrer le jeu en mode graphique.
	 * 
	 * @param args Le tableau des arguments indiqu�s lors de l'ex�cution du programme, si jamais ces arguments suppl�mentaires ont �t� indiqu�s.
	 */
	public static void main(String[] args) {
		Partie partie = new Partie("modeGraphique");
		FenetreMenu fenetreMenu = new FenetreMenu(partie);
		new ControleurMenu(partie, fenetreMenu);
	}
	
}