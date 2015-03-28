package modele;

import java.util.ArrayList;



/**
 * La classe JoueurVirtuel est une classe fille h�ritant de la classe abstraite Joueur.
 * Un joueur virtuel, contrairement � un joueur humain, ex�cute des algorithmes et ne r�alise pas de saisie.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class JoueurVirtuel extends Joueur {
	
	// ****************
	// *** ATTRIBUT ***
	// ****************
	
	/**
	 * La strat�gie d'un joueur virtuel.
	 */
	private Strategie strategie;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe JoueurVirtuel.
	 * 
	 * @param nom Le nom choisi pour le joueur virtuel, lors de son instanciation.
	 * @param strategie La strat�gie choisie pour le joueur virtuel, lors de son instanciation.
	 */
	public JoueurVirtuel(String nom, Strategie strategie) {
		super(nom);
		this.strategie = strategie;
	}
	
	
	
	// *************************
	// *** M�THODES DE CHOIX ***
	// *************************
	
	/**
	 * M�thode r�d�finie servant � un joueur virtuel � choisir la carte qu'il souhaite jouer sur le talon, parmi les
	 * cartes de sa main. Cette m�thode suit la strat�gie choisie pour le joueur virtuel, c'est-�-dire qu'il choisira
	 * en priorit� des cartes diff�rentes si c'est un joueur offensif, ou si c'est un joueur d�fensif.
	 * 
	 * @param mancheCourante La manche courante, c'est-�-dire celle o� le joueur virtuel peut choisir une carte � d�poser au sein de sa main.
	 * @return La carte choisie par le joueur virtuel, au sein de sa main.
	 */
	public Carte choisirCarteADeposer(Manche mancheCourante) {
		Carte carteChoisie;
		int choixNumeroCarteMain;
		
		System.out.println("Veuillez choisir la carte que vous souhaitez jouer parmi toutes les cartes de votre main.");
		System.out.println("Pour cela, veuillez saisir son num�ro, puis validez. Si vous ne souhaitez pas jouer, saisissez le nombre '0'.");
		
		ArrayList<Carte> cartesJouablesMain = this.recupererCartesJouablesMain(mancheCourante);
		choixNumeroCarteMain = this.strategie.suivreStrategie(cartesJouablesMain);
		
		if (choixNumeroCarteMain != 0) {
			carteChoisie = cartesJouablesMain.get(choixNumeroCarteMain-1);
			this.getMain().remove(this.getMain().indexOf(carteChoisie));
		} else
			carteChoisie = null;
		
		return carteChoisie;
	}
	
	/**
	 * M�thode red�finie servant � un joueur virtuel � saisir la couleur qu'il souhaite donner � la carte s�lectionn�e.
	 * Cette m�thode fonctionne sur la base d'un calcul de fr�quences, c'est-�-dire que le joueur virtuel choisira la
	 * couleur de carte qui est la plus repr�sent�e au sein de sa main.
	 * 
	 * @param listeCouleurs La liste des couleurs propos�es au joueur virtuel lors du choix.
	 * @param situation La situation d'utilisation de la m�thode, c'est-�-dire soit lors de la pioche d'une carte Joker en d�but de manche pour la premi�re situation, soit lors de l'utilisation d'une carte Joker/+4 par un joueur pour la seconde situation.
	 * @return La cha�ne de caract�res repr�sentant la couleur choisie pour la carte.
	 */
	public String choisirCouleurCarte(String[] listeCouleurs, int situation) {
		int[] frequenceCouleurs = new int[listeCouleurs.length];
		for (int numeroFrequenceCouleur = 0 ; numeroFrequenceCouleur < frequenceCouleurs.length ; numeroFrequenceCouleur++)
			frequenceCouleurs[numeroFrequenceCouleur] = 0;
		
		for (Carte carte : this.main)
			for (int numeroCouleur = 0 ; numeroCouleur < listeCouleurs.length ; numeroCouleur++)
				if (carte.getCouleur() == listeCouleurs[numeroCouleur])
					frequenceCouleurs[numeroCouleur]++;
		
		int iMax = 0;
		int max = frequenceCouleurs[iMax];
		for (int i=1; i<frequenceCouleurs.length; i++) {
			if (frequenceCouleurs[i] > max) {
				iMax = i;
				max = frequenceCouleurs[iMax];
			}
		}
		
		return listeCouleurs[iMax];
	}
	
	
	
	// ****************************
	// *** M�THODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * M�thode red�finie retournant le nom complet du joueur virtuel, c'est-�-dire son nom uniquement.
	 * 
	 * @return Le nom complet du joueur virtuel.
	 */
	public String getNomComplet() {
		return this.getNom();
	}
	
	
	// **************************
	// *** M�THODES DE SAISIE ***
	// **************************
	
	/**
	 * M�thode red�finie servant � r�cup�rer la saisie d'UNO suite au d�p�t de l'avant-derni�re de la main d'un joueur
	 * virtuel, qui est consid�r�e toujours r�alis�e, donc tout le temps vraie.
	 * 
	 * @return Vrai si la saisie a �t� faite � temps ; faux sinon.
	 */
	public boolean saisirUNO() {
		return true;
	}
	
	
	
	// *****************
	// *** ACCESSEUR ***
	// *****************
	
	/**
	 * Accesseur de l'attribut strategie de la classe JoueurVirtuel.
	 * 
	 * @return La strat�gie du joueur virtuel.
	 */
	public Strategie getStrategie() {
		return strategie;
	}
	
	
	// ****************
	// *** MUTATEUR ***
	// ****************
	
	/**
	 * Mutateur de l'attribut strategie de la classe JoueurVirtuel.
	 * 
	 * @param strategie La nouvelle strat�gie du joueur virtuel.
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}
	
}