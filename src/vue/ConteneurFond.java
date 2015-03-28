package vue;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;



/**
 * La classe ConteneurFond est une classe h�ritant de Container et permettant de mettre une image de fond pour une fen�tre du mode graphique.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class ConteneurFond extends Container {
	
	// *****************
	// *** ATTRIBUTS ***
	// *****************
	
	/**
	 * Affecte un num�ro de version � la classe.
	 */
	private static final long serialVersionUID = 1;
    
	/**
	 * L'image d'un conteneur de fond.
	 */
	private final Image image;
	/**
	 * La largeur d'un conteneur de fond.
	 */
	private final int largeur;
	/**
	 * La hauteur d'un conteneur de fond.
	 */
	private final int hauteur;
    
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe ConteneurFond.
	 * 
	 * @param fond L'image choisi pour fond du conteneur de fond.
	 */
	public ConteneurFond(Image fond) {
		this.image = fond;
		this.largeur = image.getWidth(this);
		this.hauteur = image.getHeight(this);
	}
	
	
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * M�thode permettant de r�actualiser l'affichage du conteneur de fond.
	 * 
	 * @param graph Partie graphique de l'affichage que l'on souhaite r�actualiser.
	 */
    public void paint(Graphics graph) {
    	super.paint(graph);
    	graph.drawImage(this.image, 0, 0, this.largeur, this.hauteur, this);
    	super.paintComponents(graph);
	}
    
}