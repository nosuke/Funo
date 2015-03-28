package modele;

import java.util.ArrayList;



/**
 * La classe StrategieDefensive définit la stratégie que suit un joueur virtuel s'il a été décidé qu'il soit défensif.
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
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Démarche que suit un joueur virtuel défensif.
	 * 
	 * @param cartesJouablesMain La liste des cartes jouables de la main du joueur virtuel associé à la stratégie.
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