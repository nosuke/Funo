package modele;



/**
 * La classe ThreadTemps h�rite de la classe Thread, ce qui en fait un type de Thread.
 * Ici, en l'occurrence, le but est de cr�er un Thread qui s'arr�te au bout de cinq secondes, pour permettre la saisie
 * d'UNO � un joueur quand cela est pr�vu (c'est-�-dire quand il d�pose l'avant-derni�re carte de sa main, au sein d'une
 * manche).
 * Le choix d'un Thread est d� au fait qu'il �tait sinon impossible de permettre � la fois la saisie par le joueur et le
 * d�compte du temps.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class ThreadTemps extends Thread {
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * M�thode du Thread ThreadTemps appel�e � son d�marrage (avec la m�thode start()).
	 * Permet au Thread de rester actif pendant cinq secondes. Ces cinq secondes sont celles au cours desquelles le joueur
	 * est cens� saisir UNO.
	 */
	public void run() {
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() < (start + 5000)) {}
		this.interrupt();
	}
	
}