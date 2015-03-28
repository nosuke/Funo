package modele;

import java.util.ArrayList;



/**
 * La classe StrategieOffensive définit la stratégie que suit un joueur virtuel s'il a été décidé qu'il soit offensif.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class StrategieOffensive implements Strategie {
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe StrategieOffensive.
	 */
	public StrategieOffensive() { }
	
	
	
	// **************************
	// *** MÉTHODE PRINCIPALE ***
	// **************************
	
	/**
	 * Démarche que suit un joueur virtuel offensif.
	 * 
	 * @param cartesJouablesMain La liste des cartes jouables de la main du joueur virtuel associé à la stratégie.
	 * @return La place dans la main de la carte choisie.
	 */
	public int suivreStrategie(ArrayList<Carte> cartesJouablesMain) {
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CartePlusQuatre)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CarteJoker)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CartePlusDeux)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CartePasserUnTour)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CarteInversion)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		for (Carte carte : cartesJouablesMain)
			if (carte instanceof CarteNormale)
				return (cartesJouablesMain.indexOf(carte)+1);
		
		return 0;
	}
	
}