package modele;



/**
 * La classe ThreadTemps hérite de la classe Thread, ce qui en fait un type de Thread.
 * Ici, en l'occurrence, le but est de créer un Thread qui s'arrête au bout de cinq secondes, pour permettre la saisie
 * d'UNO à un joueur quand cela est prévu (c'est-à-dire quand il dépose l'avant-dernière carte de sa main, au sein d'une
 * manche).
 * Le choix d'un Thread est dû au fait qu'il était sinon impossible de permettre à la fois la saisie par le joueur et le
 * décompte du temps.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class ThreadTemps extends Thread {
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Méthode du Thread ThreadTemps appelée à son démarrage (avec la méthode start()).
	 * Permet au Thread de rester actif pendant cinq secondes. Ces cinq secondes sont celles au cours desquelles le joueur
	 * est censé saisir UNO.
	 */
	public void run() {
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() < (start + 5000)) {}
		this.interrupt();
	}
	
}