package modele;

import java.util.ArrayList;



/**
 * La classe JoueurVirtuel est une classe fille héritant de la classe abstraite Joueur.
 * Un joueur virtuel, contrairement à un joueur humain, exécute des algorithmes et ne réalise pas de saisie.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class JoueurVirtuel extends Joueur {
	
	// ****************
	// *** ATTRIBUT ***
	// ****************
	
	/**
	 * La stratégie d'un joueur virtuel.
	 */
	private Strategie strategie;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe JoueurVirtuel.
	 * 
	 * @param nom Le nom choisi pour le joueur virtuel, lors de son instanciation.
	 * @param strategie La stratégie choisie pour le joueur virtuel, lors de son instanciation.
	 */
	public JoueurVirtuel(String nom, Strategie strategie) {
		super(nom);
		this.strategie = strategie;
	}
	
	
	
	// *************************
	// *** MÉTHODES DE CHOIX ***
	// *************************
	
	/**
	 * Méthode rédéfinie servant à un joueur virtuel à choisir la carte qu'il souhaite jouer sur le talon, parmi les
	 * cartes de sa main. Cette méthode suit la stratégie choisie pour le joueur virtuel, c'est-à-dire qu'il choisira
	 * en priorité des cartes différentes si c'est un joueur offensif, ou si c'est un joueur défensif.
	 * 
	 * @param mancheCourante La manche courante, c'est-à-dire celle où le joueur virtuel peut choisir une carte à déposer au sein de sa main.
	 * @return La carte choisie par le joueur virtuel, au sein de sa main.
	 */
	public Carte choisirCarteADeposer(Manche mancheCourante) {
		Carte carteChoisie;
		int choixNumeroCarteMain;
		
		System.out.println("Veuillez choisir la carte que vous souhaitez jouer parmi toutes les cartes de votre main.");
		System.out.println("Pour cela, veuillez saisir son numéro, puis validez. Si vous ne souhaitez pas jouer, saisissez le nombre '0'.");
		
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
	 * Méthode redéfinie servant à un joueur virtuel à saisir la couleur qu'il souhaite donner à la carte sélectionnée.
	 * Cette méthode fonctionne sur la base d'un calcul de fréquences, c'est-à-dire que le joueur virtuel choisira la
	 * couleur de carte qui est la plus représentée au sein de sa main.
	 * 
	 * @param listeCouleurs La liste des couleurs proposées au joueur virtuel lors du choix.
	 * @param situation La situation d'utilisation de la méthode, c'est-à-dire soit lors de la pioche d'une carte Joker en début de manche pour la première situation, soit lors de l'utilisation d'une carte Joker/+4 par un joueur pour la seconde situation.
	 * @return La chaîne de caractères représentant la couleur choisie pour la carte.
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
	// *** MÉTHODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * Méthode redéfinie retournant le nom complet du joueur virtuel, c'est-à-dire son nom uniquement.
	 * 
	 * @return Le nom complet du joueur virtuel.
	 */
	public String getNomComplet() {
		return this.getNom();
	}
	
	
	// **************************
	// *** MÉTHODES DE SAISIE ***
	// **************************
	
	/**
	 * Méthode redéfinie servant à récupérer la saisie d'UNO suite au dépôt de l'avant-dernière de la main d'un joueur
	 * virtuel, qui est considérée toujours réalisée, donc tout le temps vraie.
	 * 
	 * @return Vrai si la saisie a été faite à temps ; faux sinon.
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
	 * @return La stratégie du joueur virtuel.
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
	 * @param strategie La nouvelle stratégie du joueur virtuel.
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}
	
}