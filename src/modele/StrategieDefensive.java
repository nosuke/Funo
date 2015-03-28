package modele;

import java.util.ArrayList;



/**
 * La classe StrategieDefensive d�finit la strat�gie que suit un joueur virtuel s'il a �t� d�cid� qu'il soit d�fensif.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class StrategieDefensive implements Strategie {
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe StrategieDefensive.
	 */
	public StrategieDefensive() { }
	
	
	
	// **************************
	// *** M�THODE PRINCIPALE ***
	// **************************
	
	/**
	 * D�marche que suit un joueur virtuel d�fensif.
	 * 
	 * @param cartesJouablesMain La liste des cartes jouables de la main du joueur virtuel associ� � la strat�gie.
	 * @return La place dans la main de la carte choisie.
	 */
	public int suivreStrategie(ArrayList<Carte> cartesJouablesMain) {
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CarteNormale)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CarteInversion)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CartePasserUnTour)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CartePlusDeux)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CarteJoker)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CartePlusQuatre)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		return 0;
	}
	
}