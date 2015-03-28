package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;

import modele.Joueur;
import modele.JoueurHumain;
import modele.JoueurVirtuel;
import modele.Partie;
import modele.StrategieDefensive;
import modele.StrategieOffensive;



/**
 * La classe FenetreMenu est une classe de la vue servant à afficher l'interface de menu avec laquelle le joueur interagit, avant de
 * démarrer une partie.
 * 
 * @author Haythem BEN MESSAOUD et Florent LUCET
 * @version 1.0
 */
public class FenetreMenu extends JFrame implements Observer {
	
	// *****************
	// *** ATTRIBUTS ***
	// *****************
	
	/**
	 * Affecte un numéro de version à la classe.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * La partie (modèle) correspondant à la fenêtre menu (vue).
	 */
	private Partie partie;
	
	private JLabel lbInformationsGenerales;
	private JLabel lbModeJeu;
	private JComboBox<String> cbModeJeu;
	private JLabel lbAttributionPoints;
	private ButtonGroup gbAttributionPoints;
	private JPanel pAttributionPoints;
	private JRadioButton brAttributionPointsPositif;
	private JRadioButton brAttributionPointsNegatif;
	
	private JLabel lbNouveauJoueur;
	private JLabel lbTypeNouveauJoueur;
	private ButtonGroup gbTypeNouveauJoueur;
	private JPanel pTypeNouveauJoueur;
	private JRadioButton brTypeNouveauJoueurHumain;
	private JRadioButton brTypeNouveauJoueurVirtuel;
	private JLabel lbNomNouveauJoueur;
	private JTextField tfNomNouveauJoueur;
	private JLabel lbPrenomNouveauJoueurHumain;
	private JTextField tfPrenomNouveauJoueurHumain;
	private JLabel lbStrategieNouveauJoueurVirtuel;
	private ButtonGroup gbStrategieNouveauJoueurVirtuel;
	private JPanel pStrategieNouveauJoueurVirtuel;
	private JRadioButton brStrategieNouveauJoueurVirtuelOffensif;
	private JRadioButton brStrategieNouveauJoueurVirtuelDefensif;
	private JLabel lbEquipeNouveauJoueur;
	private JTextField tfEquipeNouveauJoueur;
	private JButton bpAjouterJoueur;
	
	private JLabel lbListeJoueurs;
	private ArrayList<Joueur> alListeJoueurs;
	private JScrollPane spListeJoueurs;
	@SuppressWarnings("rawtypes")
	private JList lsListeJoueurs;
	private JButton bpSupprimerJoueur;
	
	private JLabel lbJoueurSelectionne;
	private int indexNomJoueurSelectionne;
	private JLabel lbTypeJoueurSelectionne;
	private ButtonGroup gbTypeJoueurSelectionne;
	private JPanel pTypeJoueurSelectionne;
	private JRadioButton brTypeJoueurSelectionneHumain;
	private JRadioButton brTypeJoueurSelectionneVirtuel;
	private JLabel lbNomJoueurSelectionne;
	private JTextField tfNomJoueurSelectionne;
	private JLabel lbPrenomJoueurSelectionneHumain;
	private JTextField tfPrenomJoueurSelectionneHumain;
	private JLabel lbStrategieJoueurSelectionneVirtuel;
	private ButtonGroup gbStrategieJoueurSelectionneVirtuel;
	private JPanel pStrategieJoueurSelectionneVirtuel;
	private JRadioButton brStrategieJoueurSelectionneVirtuelOffensif;
	private JRadioButton brStrategieJoueurSelectionneVirtuelDefensif;
	private JLabel lbEquipeJoueurSelectionne;
	private JTextField tfEquipeJoueurSelectionne;
	private JButton bpModifierJoueur;
	
	private JButton bpChargerPartie;
	private JButton bpLancerPartie;
	private JButton bpQuitter;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * L'unique constructeur de la classe FenetreMenu.
	 * 
	 * @param partie Partie correspondant à la fenêtre menu.
	 */
	public FenetreMenu(Partie partie) {
		this.partie = partie;
		this.partie.addObserver(this);
		
		this.initialiserComposantsGraphiques();
		this.placerComposantsGraphiques();
		
		this.setTitle("Funo - Fenêtre du menu");
		this.setLocation(200, 200);
		this.setPreferredSize(new Dimension (800, 460));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	
	
	// **********************************************************
	// *** MÉTHODES D'INITIALISATION DE COMPOSANTS GRAPHIQUES ***
	// **********************************************************
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques de la fenêtre menu.
	 */
	public void initialiserComposantsGraphiques() {
		this.initialiserComposantsGraphiquesChoixModes();
		this.initialiserComposantsGraphiquesNouveauJoueur();
		this.initialiserComposantsGraphiquesListeJoueurs();
		this.initialiserComposantsGraphiquesJoueurSelectionne();
		this.initialiserComposantsGraphiquesBoutonsPartie();
	}
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques de choix des modes de la fenêtre menu.
	 */
	public void initialiserComposantsGraphiquesChoixModes() {
		UIManager.put("Label.font", new Font("Calibri", Font.BOLD, 15));
		this.lbInformationsGenerales = new JLabel("Informations générales");
		UIManager.put("Label.font", new Font("Calibri", Font.PLAIN, 13));
		this.lbModeJeu = new JLabel("Mode de jeu :");
		String[] tableauModeJeu = {"Traditionnel", "Uno Challenge", "Équipes"};
		this.cbModeJeu = new JComboBox<String>(tableauModeJeu);
		this.lbAttributionPoints = new JLabel("Attribution des points :");
		this.brAttributionPointsPositif = new JRadioButton("Positif");
		this.brAttributionPointsNegatif = new JRadioButton("Négatif");
		this.brAttributionPointsPositif.setSelected(true);
		this.gbAttributionPoints = new ButtonGroup();
		this.gbAttributionPoints.add(this.brAttributionPointsPositif);
		this.gbAttributionPoints.add(this.brAttributionPointsNegatif);
		this.pAttributionPoints = new JPanel(new GridLayout(0, 1));
		this.pAttributionPoints.add(this.brAttributionPointsPositif);
		this.pAttributionPoints.add(this.brAttributionPointsNegatif);
	}
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques de nouveau joueur de la fenêtre menu.
	 */
	public void initialiserComposantsGraphiquesNouveauJoueur() {
		UIManager.put("Label.font", new Font("Calibri", Font.BOLD, 15));
		this.lbNouveauJoueur = new JLabel("Nouveau joueur");
		UIManager.put("Label.font", new Font("Calibri", Font.PLAIN, 13));
		this.lbTypeNouveauJoueur = new JLabel("Type de joueur :");
		this.brTypeNouveauJoueurHumain = new JRadioButton("Humain");
		this.brTypeNouveauJoueurVirtuel = new JRadioButton("Virtuel");
		this.brTypeNouveauJoueurHumain.setSelected(true);
		this.gbTypeNouveauJoueur = new ButtonGroup();
		this.gbTypeNouveauJoueur.add(this.brTypeNouveauJoueurHumain);
		this.gbTypeNouveauJoueur.add(this.brTypeNouveauJoueurVirtuel);
		this.pTypeNouveauJoueur = new JPanel(new GridLayout(0, 1));
		this.pTypeNouveauJoueur.add(this.brTypeNouveauJoueurHumain);
		this.pTypeNouveauJoueur.add(this.brTypeNouveauJoueurVirtuel);
		this.lbNomNouveauJoueur = new JLabel("Nom :");
		this.tfNomNouveauJoueur = new JTextField();
		this.tfNomNouveauJoueur.setPreferredSize(new Dimension(120, 24));
		this.lbPrenomNouveauJoueurHumain = new JLabel("Prénom :");
		this.tfPrenomNouveauJoueurHumain = new JTextField();
		this.tfPrenomNouveauJoueurHumain.setPreferredSize(new Dimension(120, 24));
		this.lbStrategieNouveauJoueurVirtuel = new JLabel("Stratégie :");
		this.lbStrategieNouveauJoueurVirtuel.setVisible(false);
		this.brStrategieNouveauJoueurVirtuelOffensif = new JRadioButton("Offensif");
		this.brStrategieNouveauJoueurVirtuelDefensif = new JRadioButton("Défensif");
		this.brStrategieNouveauJoueurVirtuelOffensif.setSelected(true);
		this.gbStrategieNouveauJoueurVirtuel = new ButtonGroup();
		this.gbStrategieNouveauJoueurVirtuel.add(this.brStrategieNouveauJoueurVirtuelOffensif);
		this.gbStrategieNouveauJoueurVirtuel.add(this.brStrategieNouveauJoueurVirtuelDefensif);
		this.pStrategieNouveauJoueurVirtuel = new JPanel(new GridLayout(0, 1));
		this.pStrategieNouveauJoueurVirtuel.add(this.brStrategieNouveauJoueurVirtuelOffensif);
		this.pStrategieNouveauJoueurVirtuel.add(this.brStrategieNouveauJoueurVirtuelDefensif);
		this.pStrategieNouveauJoueurVirtuel.setVisible(false);
		this.lbEquipeNouveauJoueur = new JLabel("Équipe :");
		this.lbEquipeNouveauJoueur.setVisible(false);
		this.tfEquipeNouveauJoueur = new JTextField();
		this.tfEquipeNouveauJoueur.setPreferredSize(new Dimension(120, 24));
		this.tfEquipeNouveauJoueur.setVisible(false);
		this.bpAjouterJoueur = new JButton("Ajouter joueur");
	}
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques de liste de sélection des joueurs de la fenêtre menu.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialiserComposantsGraphiquesListeJoueurs() {
		UIManager.put("Label.font", new Font("Calibri", Font.BOLD, 15));
		this.lbListeJoueurs = new JLabel("Liste des joueurs");
		UIManager.put("Label.font", new Font("Calibri", Font.PLAIN, 13));
		this.alListeJoueurs = new ArrayList<Joueur>();
		this.lsListeJoueurs = new JList(this.alListeJoueurs.toArray());
		this.lsListeJoueurs.setSelectedIndex(0);
		this.lsListeJoueurs.setVisibleRowCount(8);
		this.spListeJoueurs = new JScrollPane(this.lsListeJoueurs);
		this.spListeJoueurs.setPreferredSize(new Dimension(200, 100));
		this.bpSupprimerJoueur = new JButton("Supprimer joueur");
	}
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques de joueur sélectionné de la fenêtre menu.
	 */
	public void initialiserComposantsGraphiquesJoueurSelectionne() {
		UIManager.put("Label.font", new Font("Calibri", Font.BOLD, 15));
		this.lbJoueurSelectionne = new JLabel("Joueur sélectionné");
		UIManager.put("Label.font", new Font("Calibri", Font.PLAIN, 13));
		this.lbTypeJoueurSelectionne = new JLabel("Type de joueur :");
		this.brTypeJoueurSelectionneHumain = new JRadioButton("Humain");
		this.brTypeJoueurSelectionneVirtuel = new JRadioButton("Virtuel");
		this.brTypeJoueurSelectionneHumain.setSelected(true);
		this.gbTypeJoueurSelectionne = new ButtonGroup();
		this.gbTypeJoueurSelectionne.add(this.brTypeJoueurSelectionneHumain);
		this.gbTypeJoueurSelectionne.add(this.brTypeJoueurSelectionneVirtuel);
		this.pTypeJoueurSelectionne = new JPanel(new GridLayout(0, 1));
		this.pTypeJoueurSelectionne.add(this.brTypeJoueurSelectionneHumain);
		this.pTypeJoueurSelectionne.add(this.brTypeJoueurSelectionneVirtuel);
		this.lbNomJoueurSelectionne = new JLabel("Nom :");
		this.tfNomJoueurSelectionne = new JTextField();
		this.tfNomJoueurSelectionne.setPreferredSize(new Dimension(120, 24));
		this.lbPrenomJoueurSelectionneHumain = new JLabel("Prénom :");
		this.tfPrenomJoueurSelectionneHumain = new JTextField();
		this.tfPrenomJoueurSelectionneHumain.setPreferredSize(new Dimension(120, 24));
		this.lbStrategieJoueurSelectionneVirtuel = new JLabel("Stratégie :");
		this.brStrategieJoueurSelectionneVirtuelOffensif = new JRadioButton("Offensif");
		this.brStrategieJoueurSelectionneVirtuelDefensif = new JRadioButton("Défensif");
		this.brStrategieJoueurSelectionneVirtuelOffensif.setSelected(true);
		this.gbStrategieJoueurSelectionneVirtuel = new ButtonGroup();
		this.gbStrategieJoueurSelectionneVirtuel.add(this.brStrategieJoueurSelectionneVirtuelOffensif);
		this.gbStrategieJoueurSelectionneVirtuel.add(this.brStrategieJoueurSelectionneVirtuelDefensif);
		this.pStrategieJoueurSelectionneVirtuel = new JPanel(new GridLayout(0, 1));
		this.pStrategieJoueurSelectionneVirtuel.add(this.brStrategieJoueurSelectionneVirtuelOffensif);
		this.pStrategieJoueurSelectionneVirtuel.add(this.brStrategieJoueurSelectionneVirtuelDefensif);
		this.lbEquipeJoueurSelectionne = new JLabel("Équipe :");
		this.tfEquipeJoueurSelectionne = new JTextField();
		this.tfEquipeJoueurSelectionne.setPreferredSize(new Dimension(120, 24));
		this.bpModifierJoueur = new JButton("Modifier joueur");
		this.afficherComposantsGraphiquesJoueurSelectionne(false);
	}
	
	/**
	 * Méthode permettant d'initialiser les composants graphiques des boutons de partie de la fenêtre menu.
	 */
	public void initialiserComposantsGraphiquesBoutonsPartie() {
		this.bpChargerPartie = new JButton("Charger partie");
		this.bpLancerPartie = new JButton("Lancer partie");
		this.bpQuitter = new JButton("Quitter");
	}
	
	
	// ******************************************************
	// *** MÉTHODES DE PLACEMENT DE COMPOSANTS GRAPHIQUES ***
	// ******************************************************
	
	/**
	 * Méthode permettant de placer les composants graphiques de la fenêtre menu.
	 */
	public void placerComposantsGraphiques() {
		ImageIcon fond = new ImageIcon("img/Fonds/FondCiel.png");
		this.setContentPane(new ConteneurFond(fond.getImage()));
	    this.getContentPane().setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets (5, 5, 5, 5);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    
	    gbc = this.placerComposantsGraphiquesChoixModes(gbc);
	    gbc = this.placerComposantsGraphiquesNouveauJoueur(gbc);
	    gbc = this.placerComposantsGraphiquesListeJoueurs(gbc);
	    gbc = this.placerComposantsGraphiquesJoueurSelectionne(gbc);
	    gbc = this.placerComposantsGraphiquesBoutonsPartie(gbc);
	}
	
	/**
	 * Méthode permettant de placer les composants graphiques de choix des modes de la fenêtre menu.
	 * 
	 * @param gbc Contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 * @return Nouvelles contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 */
	public GridBagConstraints placerComposantsGraphiquesChoixModes(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.CENTER;
	    gbc.weightx = 1.0;
	    gbc.weighty = 0.0;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    this.getContentPane().add(this.lbInformationsGenerales, gbc);
	    gbc.anchor = GridBagConstraints.LINE_START;
	    gbc.gridwidth = 1;
	    gbc.gridy = 1;
		this.getContentPane().add(this.lbModeJeu, gbc);
		gbc.gridx = 1;
		this.getContentPane().add(this.cbModeJeu, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.getContentPane().add(this.lbAttributionPoints, gbc);
		gbc.gridx = 1;
		this.getContentPane().add(this.pAttributionPoints, gbc);
		
		return gbc;
	}
	
	/**
	 * Méthode permettant de placer les composants graphiques de nouveau joueur de la fenêtre menu.
	 * 
	 * @param gbc Contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 * @return Nouvelles contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 */
	public GridBagConstraints placerComposantsGraphiquesNouveauJoueur(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.getContentPane().add(this.lbNouveauJoueur, gbc);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 1;
		gbc.gridy = 4;
		this.getContentPane().add(this.lbTypeNouveauJoueur, gbc);
		gbc.gridx = 1;
		this.getContentPane().add(this.pTypeNouveauJoueur, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.getContentPane().add(this.lbNomNouveauJoueur, gbc);
		gbc.gridx = 1;
		this.getContentPane().add(this.tfNomNouveauJoueur, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		this.getContentPane().add(this.lbPrenomNouveauJoueurHumain, gbc);
		this.getContentPane().add(this.lbStrategieNouveauJoueurVirtuel, gbc);
		gbc.gridx = 1;
		this.getContentPane().add(this.tfPrenomNouveauJoueurHumain, gbc);
		this.getContentPane().add(this.pStrategieNouveauJoueurVirtuel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		this.getContentPane().add(this.lbEquipeNouveauJoueur, gbc);
		gbc.gridx = 1;
		this.getContentPane().add(this.tfEquipeNouveauJoueur, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 8;
		this.getContentPane().add(this.bpAjouterJoueur, gbc);
		
		return gbc;
	}
	
	/**
	 * Méthode permettant de placer les composants graphiques de liste de sélection des joueurs de la fenêtre menu.
	 * 
	 * @param gbc Contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 * @return Nouvelles contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 */
	public GridBagConstraints placerComposantsGraphiquesListeJoueurs(GridBagConstraints gbc) {
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 3;
		this.getContentPane().add(this.lbListeJoueurs, gbc);
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridheight = 4;
		gbc.gridy = 4;
		this.getContentPane().add(this.spListeJoueurs, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridheight = 1;
		gbc.gridy = 8;
		this.getContentPane().add(this.bpSupprimerJoueur, gbc);
		
		return gbc;
	}
	
	/**
	 * Méthode permettant de placer les composants graphiques de joueur sélectionné de la fenêtre menu.
	 * 
	 * @param gbc Contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 * @return Nouvelles contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 */
	public GridBagConstraints placerComposantsGraphiquesJoueurSelectionne(GridBagConstraints gbc) {
		gbc.gridwidth = 2;
		gbc.gridx = 3;
		gbc.gridy = 3;
		this.getContentPane().add(this.lbJoueurSelectionne, gbc);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 1;
		gbc.gridy = 4;
		this.getContentPane().add(this.lbTypeJoueurSelectionne, gbc);
		gbc.gridx = 4;
		this.getContentPane().add(this.pTypeJoueurSelectionne, gbc);
		gbc.gridx = 3;
		gbc.gridy = 5;
		this.getContentPane().add(this.lbNomJoueurSelectionne, gbc);
		gbc.gridx = 4;
		this.getContentPane().add(this.tfNomJoueurSelectionne, gbc);
		gbc.gridx = 3;
		gbc.gridy = 6;
		this.getContentPane().add(this.lbPrenomJoueurSelectionneHumain, gbc);
		this.getContentPane().add(this.lbStrategieJoueurSelectionneVirtuel, gbc);
		gbc.gridx = 4;
		this.getContentPane().add(this.tfPrenomJoueurSelectionneHumain, gbc);
		this.getContentPane().add(this.pStrategieJoueurSelectionneVirtuel, gbc);
		gbc.gridx = 3;
		gbc.gridy = 7;
		this.getContentPane().add(this.lbEquipeJoueurSelectionne, gbc);
		gbc.gridx = 4;
		this.getContentPane().add(this.tfEquipeJoueurSelectionne, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 3;
		gbc.gridy = 8;
		this.getContentPane().add(this.bpModifierJoueur, gbc);
		
		return gbc;
	}
	
	/**
	 * Méthode permettant de placer les composants graphiques des boutons de partie de la fenêtre menu.
	 * 
	 * @param gbc Contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 * @return Nouvelles contraintes du GridBagLayout utilisées pour placer les différents composants graphiques.
	 */
	public GridBagConstraints placerComposantsGraphiquesBoutonsPartie(GridBagConstraints gbc) {
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 10;
		this.getContentPane().add(this.bpChargerPartie, gbc);
		gbc.gridx = 1;
		gbc.gridy = 10;
		this.getContentPane().add(this.bpLancerPartie, gbc);
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 4;
		gbc.gridy = 10;
		this.getContentPane().add(this.bpQuitter, gbc);
		
		return gbc;
	}
	
	
	
	// ****************************************
	// *** MÉTHODE DE MISE À JOUR DE LA VUE ***
	// ****************************************
	
	/**
	 * Méthode permettant de mettre à jour la vue si une notification provient du modèle, la fenêtre de menu étant la vue
	 * et la partie étant le modèle.
	 */
	public void update(Observable modele, Object objet) { }
	
	
	
	// **************************************************
	// *** MÉTHODES DE LISTE DE SÉLECTION DES JOUEURS ***
	// **************************************************
	
	/**
	 * Méthode permettant d'ajouter un joueur dans la liste de sélection des joueurs à partir de l'espace réservé à un nouveau joueur.
	 */
	@SuppressWarnings("unchecked")
	public void ajouterJoueurDansListe() {
		Joueur nouveauJoueur;
		
		if (this.brTypeNouveauJoueurHumain.isSelected()) {
			nouveauJoueur = new JoueurHumain(this.tfNomNouveauJoueur.getText(), this.tfPrenomNouveauJoueurHumain.getText());
		} else {
			if (this.brStrategieNouveauJoueurVirtuelOffensif.isSelected()) {
				nouveauJoueur = new JoueurVirtuel(this.tfNomNouveauJoueur.getText(), new StrategieOffensive());
			} else {
				nouveauJoueur = new JoueurVirtuel(this.tfNomNouveauJoueur.getText(), new StrategieDefensive());
			}
		}
		
		this.alListeJoueurs.add(nouveauJoueur);
		this.lsListeJoueurs.setListData(this.alListeJoueurs.toArray());
		
		this.brTypeNouveauJoueurHumain.setSelected(true);
		this.tfNomNouveauJoueur.setText(null);
		this.tfPrenomNouveauJoueurHumain.setText(null);
		this.lbPrenomNouveauJoueurHumain.setVisible(true);
		this.tfPrenomNouveauJoueurHumain.setVisible(true);
		this.brStrategieNouveauJoueurVirtuelOffensif.setSelected(true);
		this.lbStrategieNouveauJoueurVirtuel.setVisible(false);
		this.pStrategieNouveauJoueurVirtuel.setVisible(false);
		this.tfEquipeNouveauJoueur.setText(null);
	}
	
	/**
	 * Méthode permettant de modifier un joueur de la liste de sélection des joueurs à partir de l'espace réservé au joueur sélectionné.
	 */
	@SuppressWarnings("unchecked")
	public void modifierJoueurDeListe() {
		Joueur joueurSelectionne;
		
		if (this.brTypeJoueurSelectionneHumain.isSelected()) {
			joueurSelectionne = new JoueurHumain(this.tfNomJoueurSelectionne.getText(), this.tfPrenomJoueurSelectionneHumain.getText());
		} else {
			if (this.brStrategieJoueurSelectionneVirtuelOffensif.isSelected()) {
				joueurSelectionne = new JoueurVirtuel(this.tfNomJoueurSelectionne.getText(), new StrategieOffensive());
			} else {
				joueurSelectionne = new JoueurVirtuel(this.tfNomJoueurSelectionne.getText(), new StrategieDefensive());
			}
		}
		
		int indexJoueur = this.alListeJoueurs.indexOf(this.lsListeJoueurs.getSelectedValue());
		this.alListeJoueurs.remove(indexJoueur);
		this.alListeJoueurs.add(indexJoueur, joueurSelectionne);
		this.lsListeJoueurs.setListData(this.alListeJoueurs.toArray());
		
		this.brTypeJoueurSelectionneHumain.setSelected(true);
		this.tfNomJoueurSelectionne.setText(null);
		this.tfPrenomJoueurSelectionneHumain.setText(null);
		this.lbPrenomJoueurSelectionneHumain.setVisible(true);
		this.tfPrenomJoueurSelectionneHumain.setVisible(true);
		this.brStrategieJoueurSelectionneVirtuelOffensif.setSelected(true);
		this.lbStrategieJoueurSelectionneVirtuel.setVisible(false);
		this.pStrategieJoueurSelectionneVirtuel.setVisible(false);
		this.tfEquipeJoueurSelectionne.setText(null);
	}
	
	/**
	 * Méthode permettant de supprimer un joueur de la liste de sélection des joueurs après l'avoir sélectionné.
	 */
	@SuppressWarnings("unchecked")
	public void supprimerJoueurDeListe() {
		this.afficherComposantsGraphiquesJoueurSelectionne(false);
		this.alListeJoueurs.remove(this.lsListeJoueurs.getSelectedIndex());
		this.lsListeJoueurs.setListData(this.alListeJoueurs.toArray());
	}
	
	
	// ****************************
	// *** MÉTHODES D'AFFICHAGE ***
	// ****************************
	
	/**
	 * Méthode permettant d'affecter les données du joueur sélectionné dans la liste aux différents champs de l'espace réservé au joueur
	 * sélectionné.
	 */
	public void afficherJoueurSelectionne() {
		Joueur joueurSelectionne;
		joueurSelectionne = (Joueur) this.lsListeJoueurs.getSelectedValue();
		
		this.afficherComposantsGraphiquesJoueurSelectionne(true);
		
		this.indexNomJoueurSelectionne = this.alListeJoueurs.indexOf(joueurSelectionne);
		this.tfNomJoueurSelectionne.setText(joueurSelectionne.getNom());
		this.tfPrenomJoueurSelectionneHumain.setText(null);
		this.brStrategieJoueurSelectionneVirtuelOffensif.setSelected(true);
		if (joueurSelectionne instanceof JoueurHumain) {
			this.brTypeJoueurSelectionneHumain.setSelected(true);
			this.tfPrenomJoueurSelectionneHumain.setText(((JoueurHumain) joueurSelectionne).getPrenom());
		} else if (joueurSelectionne instanceof JoueurVirtuel) {
			this.brTypeJoueurSelectionneVirtuel.setSelected(true);
			if (((JoueurVirtuel) joueurSelectionne).getStrategie() instanceof StrategieOffensive)
				this.brStrategieJoueurSelectionneVirtuelOffensif.setSelected(true);
			else if (((JoueurVirtuel) joueurSelectionne).getStrategie() instanceof StrategieDefensive)
				this.brStrategieJoueurSelectionneVirtuelDefensif.setSelected(true);
			this.lbPrenomJoueurSelectionneHumain.setVisible(false);
			this.tfPrenomJoueurSelectionneHumain.setVisible(false);
			this.lbStrategieJoueurSelectionneVirtuel.setVisible(true);
			this.pStrategieJoueurSelectionneVirtuel.setVisible(true);
		}
		this.tfEquipeJoueurSelectionne.setText(null);
	}
	
	/**
	 * Méthode permettant d'afficher ou de cacher les composants de l'espace réservé au joueur sélectionné en fonction de la valeur de son
	 * paramètre.
	 * 
	 * @param afficher Vrai si on souhaite afficher les composants de l'espace réservé au joueur sélectionné ; faux sinon.
	 */
	public void afficherComposantsGraphiquesJoueurSelectionne(boolean afficher) {
		this.lbTypeJoueurSelectionne .setVisible(afficher);
		this.pTypeJoueurSelectionne.setVisible(afficher);
		this.lbNomJoueurSelectionne.setVisible(afficher);
		this.tfNomJoueurSelectionne.setVisible(afficher);
		this.lbPrenomJoueurSelectionneHumain.setVisible(afficher);
		this.tfPrenomJoueurSelectionneHumain.setVisible(afficher);
		this.lbStrategieJoueurSelectionneVirtuel.setVisible(false);
		this.pStrategieJoueurSelectionneVirtuel.setVisible(false);
		this.lbEquipeJoueurSelectionne.setVisible(false);
		this.tfEquipeJoueurSelectionne.setVisible(false);
		this.bpModifierJoueur.setVisible(afficher);
	}
	
	
	
	// ********************************
	// *** MÉTHODES DE VÉRIFICATION ***
	// ********************************
	
	/**
	 * Méthode permettant d'afficher une boîte de dialogue d'erreur avec un message personnalisé.
	 * 
	 * @param titreErreur Titre de la boîte de dialogue d'erreur.
	 * @param messageErreur Message personnalisé contenu au sein de la boîte de dialogue d'erreur.
	 */
	public void afficherMessageDErreur(String titreErreur, String messageErreur) {
		JOptionPane.showMessageDialog(null, messageErreur, titreErreur, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Méthode permettant de récupérer la liste des noms des joueurs via une liste de chaînes de caractères.
	 * Sert pour vérifier si un nom de joueur est déjà pris.
	 * 
	 * @return Liste des noms des joueurs ajoutés dans la liste de sélection des joueurs.
	 */
	public ArrayList<String> getListeNomsJoueurs() {
		ArrayList<String> listeNomsJoueurs = new ArrayList<String>();
		
		for (Joueur joueur : this.alListeJoueurs)
			listeNomsJoueurs.add(joueur.getNom());
		
		return listeNomsJoueurs;
	}
	
	
	
	// ************************************
	// *** MÉTHODES D'AJOUT DE LISTENER ***
	// ************************************
	
	public void ajouterModeJeuEquipesListener(ItemListener il) {
		this.cbModeJeu.addItemListener(il);
	}
	
	public void ajouterTypeNouveauJoueurHumainListener(ActionListener al) {
		this.brTypeNouveauJoueurHumain.addActionListener(al);
	}
	
	public void ajouterTypeNouveauJoueurVirtuelListener(ActionListener al) {
		this.brTypeNouveauJoueurVirtuel.addActionListener(al);
	}
	
	public void ajouterAjouterJoueurListener(ActionListener al) {
		this.bpAjouterJoueur.addActionListener(al);
	}
	
	public void ajouterListeJoueursListSelectionListener(ListSelectionListener lsl) {
		this.lsListeJoueurs.addListSelectionListener(lsl);
	}
	
	public void ajouterSupprimerJoueurListener(ActionListener al) {
		this.bpSupprimerJoueur.addActionListener(al);
	}
	
	public void ajouterTypeJoueurSelectionneHumainListener(ActionListener al) {
		this.brTypeJoueurSelectionneHumain.addActionListener(al);
	}
	
	public void ajouterTypeJoueurSelectionneVirtuelListener(ActionListener al) {
		this.brTypeJoueurSelectionneVirtuel.addActionListener(al);
	}
	
	public void ajouterModifierJoueurListener(ActionListener al) {
		this.bpModifierJoueur.addActionListener(al);
	}
	
	public void ajouterChargerPartieListener(ActionListener al) {
		this.bpChargerPartie.addActionListener(al);
	}
	
	public void ajouterLancerPartieListener(ActionListener al) {
		this.bpLancerPartie.addActionListener(al);
	}
	
	public void ajouterQuitterListener(ActionListener al) {
		this.bpQuitter.addActionListener(al);
	}

	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	public Partie getPartie() {
		return partie;
	}

	public JLabel getLbInformationsGenerales() {
		return lbInformationsGenerales;
	}

	public JLabel getLbModeJeu() {
		return lbModeJeu;
	}

	public JComboBox<String> getCbModeJeu() {
		return cbModeJeu;
	}

	public JLabel getLbAttributionPoints() {
		return lbAttributionPoints;
	}

	public ButtonGroup getGbAttributionPoints() {
		return gbAttributionPoints;
	}

	public JPanel getpAttributionPoints() {
		return pAttributionPoints;
	}

	public JRadioButton getBrAttributionPointsPositif() {
		return brAttributionPointsPositif;
	}

	public JRadioButton getBrAttributionPointsNegatif() {
		return brAttributionPointsNegatif;
	}

	public JLabel getLbNouveauJoueur() {
		return lbNouveauJoueur;
	}

	public JLabel getLbTypeNouveauJoueur() {
		return lbTypeNouveauJoueur;
	}

	public ButtonGroup getGbTypeNouveauJoueur() {
		return gbTypeNouveauJoueur;
	}

	public JPanel getpTypeNouveauJoueur() {
		return pTypeNouveauJoueur;
	}

	public JRadioButton getBrTypeNouveauJoueurHumain() {
		return brTypeNouveauJoueurHumain;
	}

	public JRadioButton getBrTypeNouveauJoueurVirtuel() {
		return brTypeNouveauJoueurVirtuel;
	}

	public JLabel getLbNomNouveauJoueur() {
		return lbNomNouveauJoueur;
	}

	public JTextField getTfNomNouveauJoueur() {
		return tfNomNouveauJoueur;
	}

	public JLabel getLbPrenomNouveauJoueurHumain() {
		return lbPrenomNouveauJoueurHumain;
	}

	public JTextField getTfPrenomNouveauJoueurHumain() {
		return tfPrenomNouveauJoueurHumain;
	}

	public JLabel getLbStrategieNouveauJoueurVirtuel() {
		return lbStrategieNouveauJoueurVirtuel;
	}

	public ButtonGroup getGbStrategieNouveauJoueurVirtuel() {
		return gbStrategieNouveauJoueurVirtuel;
	}

	public JPanel getpStrategieNouveauJoueurVirtuel() {
		return pStrategieNouveauJoueurVirtuel;
	}

	public JRadioButton getBrStrategieNouveauJoueurVirtuelOffensif() {
		return brStrategieNouveauJoueurVirtuelOffensif;
	}

	public JRadioButton getBrStrategieNouveauJoueurVirtuelDefensif() {
		return brStrategieNouveauJoueurVirtuelDefensif;
	}

	public JLabel getLbEquipeNouveauJoueur() {
		return lbEquipeNouveauJoueur;
	}

	public JTextField getTfEquipeNouveauJoueur() {
		return tfEquipeNouveauJoueur;
	}

	public JButton getBpAjouterJoueur() {
		return bpAjouterJoueur;
	}

	public JLabel getLbListeJoueurs() {
		return lbListeJoueurs;
	}

	public ArrayList<Joueur> getAlListeJoueurs() {
		return alListeJoueurs;
	}
	
	public JScrollPane getSpListeJoueurs() {
		return spListeJoueurs;
	}

	@SuppressWarnings("rawtypes")
	public JList getLsListeJoueurs() {
		return lsListeJoueurs;
	}

	public JButton getBpSupprimerJoueur() {
		return bpSupprimerJoueur;
	}
	
	public JLabel getLbJoueurSelectionne() {
		return lbJoueurSelectionne;
	}

	public int getIndexNomJoueurSelectionne() {
		return indexNomJoueurSelectionne;
	}
	
	public JLabel getLbTypeJoueurSelectionne() {
		return lbTypeJoueurSelectionne;
	}

	public ButtonGroup getGbTypeJoueurSelectionne() {
		return gbTypeJoueurSelectionne;
	}

	public JPanel getpTypeJoueurSelectionne() {
		return pTypeJoueurSelectionne;
	}

	public JRadioButton getBrTypeJoueurSelectionneHumain() {
		return brTypeJoueurSelectionneHumain;
	}

	public JRadioButton getBrTypeJoueurSelectionneVirtuel() {
		return brTypeJoueurSelectionneVirtuel;
	}

	public JLabel getLbNomJoueurSelectionne() {
		return lbNomJoueurSelectionne;
	}

	public JTextField getTfNomJoueurSelectionne() {
		return tfNomJoueurSelectionne;
	}

	public JLabel getLbPrenomJoueurSelectionneHumain() {
		return lbPrenomJoueurSelectionneHumain;
	}

	public JTextField getTfPrenomJoueurSelectionneHumain() {
		return tfPrenomJoueurSelectionneHumain;
	}

	public JLabel getLbStrategieJoueurSelectionneVirtuel() {
		return lbStrategieJoueurSelectionneVirtuel;
	}

	public ButtonGroup getGbStrategieJoueurSelectionneVirtuel() {
		return gbStrategieJoueurSelectionneVirtuel;
	}

	public JPanel getpStrategieJoueurSelectionneVirtuel() {
		return pStrategieJoueurSelectionneVirtuel;
	}

	public JRadioButton getBrStrategieJoueurSelectionneVirtuelOffensif() {
		return brStrategieJoueurSelectionneVirtuelOffensif;
	}

	public JRadioButton getBrStrategieJoueurSelectionneVirtuelDefensif() {
		return brStrategieJoueurSelectionneVirtuelDefensif;
	}

	public JLabel getLbEquipeJoueurSelectionne() {
		return lbEquipeJoueurSelectionne;
	}

	public JTextField getTfEquipeJoueurSelectionne() {
		return tfEquipeJoueurSelectionne;
	}

	public JButton getBpModifierJoueur() {
		return bpModifierJoueur;
	}

	public JButton getBpChargerPartie() {
		return bpChargerPartie;
	}

	public JButton getBpLancerPartie() {
		return bpLancerPartie;
	}

	public JButton getBpQuitter() {
		return bpQuitter;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public void setLbInformationsGenerales(JLabel lbInformationsGenerales) {
		this.lbInformationsGenerales = lbInformationsGenerales;
	}

	public void setLbModeJeu(JLabel lbModeJeu) {
		this.lbModeJeu = lbModeJeu;
	}

	public void setCbModeJeu(JComboBox<String> cbModeJeu) {
		this.cbModeJeu = cbModeJeu;
	}

	public void setLbAttributionPoints(JLabel lbAttributionPoints) {
		this.lbAttributionPoints = lbAttributionPoints;
	}

	public void setGbAttributionPoints(ButtonGroup gbAttributionPoints) {
		this.gbAttributionPoints = gbAttributionPoints;
	}

	public void setpAttributionPoints(JPanel pAttributionPoints) {
		this.pAttributionPoints = pAttributionPoints;
	}

	public void setBrAttributionPointsPositif(
			JRadioButton brAttributionPointsPositif) {
		this.brAttributionPointsPositif = brAttributionPointsPositif;
	}

	public void setBrAttributionPointsNegatif(
			JRadioButton brAttributionPointsNegatif) {
		this.brAttributionPointsNegatif = brAttributionPointsNegatif;
	}

	public void setLbNouveauJoueur(JLabel lbNouveauJoueur) {
		this.lbNouveauJoueur = lbNouveauJoueur;
	}

	public void setLbTypeNouveauJoueur(JLabel lbTypeNouveauJoueur) {
		this.lbTypeNouveauJoueur = lbTypeNouveauJoueur;
	}

	public void setGbTypeNouveauJoueur(ButtonGroup gbTypeNouveauJoueur) {
		this.gbTypeNouveauJoueur = gbTypeNouveauJoueur;
	}

	public void setpTypeNouveauJoueur(JPanel pTypeNouveauJoueur) {
		this.pTypeNouveauJoueur = pTypeNouveauJoueur;
	}

	public void setBrTypeNouveauJoueurHumain(JRadioButton brTypeNouveauJoueurHumain) {
		this.brTypeNouveauJoueurHumain = brTypeNouveauJoueurHumain;
	}

	public void setBrTypeNouveauJoueurVirtuel(
			JRadioButton brTypeNouveauJoueurVirtuel) {
		this.brTypeNouveauJoueurVirtuel = brTypeNouveauJoueurVirtuel;
	}

	public void setLbNomNouveauJoueur(JLabel lbNomNouveauJoueur) {
		this.lbNomNouveauJoueur = lbNomNouveauJoueur;
	}

	public void setTfNomNouveauJoueur(JTextField tfNomNouveauJoueur) {
		this.tfNomNouveauJoueur = tfNomNouveauJoueur;
	}

	public void setLbPrenomNouveauJoueurHumain(JLabel lbPrenomNouveauJoueurHumain) {
		this.lbPrenomNouveauJoueurHumain = lbPrenomNouveauJoueurHumain;
	}

	public void setTfPrenomNouveauJoueurHumain(
			JTextField tfPrenomNouveauJoueurHumain) {
		this.tfPrenomNouveauJoueurHumain = tfPrenomNouveauJoueurHumain;
	}

	public void setLbStrategieNouveauJoueurVirtuel(
			JLabel lbStrategieNouveauJoueurVirtuel) {
		this.lbStrategieNouveauJoueurVirtuel = lbStrategieNouveauJoueurVirtuel;
	}

	public void setGbStrategieNouveauJoueurVirtuel(
			ButtonGroup gbStrategieNouveauJoueurVirtuel) {
		this.gbStrategieNouveauJoueurVirtuel = gbStrategieNouveauJoueurVirtuel;
	}

	public void setpStrategieNouveauJoueurVirtuel(
			JPanel pStrategieNouveauJoueurVirtuel) {
		this.pStrategieNouveauJoueurVirtuel = pStrategieNouveauJoueurVirtuel;
	}

	public void setBrStrategieNouveauJoueurVirtuelOffensif(
			JRadioButton brStrategieNouveauJoueurVirtuelOffensif) {
		this.brStrategieNouveauJoueurVirtuelOffensif = brStrategieNouveauJoueurVirtuelOffensif;
	}

	public void setBrStrategieNouveauJoueurVirtuelDefensif(
			JRadioButton brStrategieNouveauJoueurVirtuelDefensif) {
		this.brStrategieNouveauJoueurVirtuelDefensif = brStrategieNouveauJoueurVirtuelDefensif;
	}

	public void setLbEquipeNouveauJoueur(JLabel lbEquipeNouveauJoueur) {
		this.lbEquipeNouveauJoueur = lbEquipeNouveauJoueur;
	}

	public void setTfEquipeNouveauJoueur(JTextField tfEquipeNouveauJoueur) {
		this.tfEquipeNouveauJoueur = tfEquipeNouveauJoueur;
	}

	public void setBpAjouterJoueur(JButton bpAjouterJoueur) {
		this.bpAjouterJoueur = bpAjouterJoueur;
	}

	public void setLbListeJoueurs(JLabel lbListeJoueurs) {
		this.lbListeJoueurs = lbListeJoueurs;
	}

	public void setAlListeJoueurs(ArrayList<Joueur> alListeJoueurs) {
		this.alListeJoueurs = alListeJoueurs;
	}
	
	public void setSpListeJoueurs(JScrollPane spListeJoueurs) {
		this.spListeJoueurs = spListeJoueurs;
	}

	@SuppressWarnings("rawtypes")
	public void setLsListeJoueurs(JList lsListeJoueurs) {
		this.lsListeJoueurs = lsListeJoueurs;
	}

	public void setBpSupprimerJoueur(JButton bpSupprimerJoueur) {
		this.bpSupprimerJoueur = bpSupprimerJoueur;
	}

	public void setLbJoueurSelectionne(JLabel lbJoueurSelectionne) {
		this.lbJoueurSelectionne = lbJoueurSelectionne;
	}

	public void setIndexNomJoueurSelectionne(int indexNomJoueurSelectionne) {
		this.indexNomJoueurSelectionne = indexNomJoueurSelectionne;
	}

	public void setLbTypeJoueurSelectionne(JLabel lbTypeJoueurSelectionne) {
		this.lbTypeJoueurSelectionne = lbTypeJoueurSelectionne;
	}

	public void setGbTypeJoueurSelectionne(ButtonGroup gbTypeJoueurSelectionne) {
		this.gbTypeJoueurSelectionne = gbTypeJoueurSelectionne;
	}

	public void setpTypeJoueurSelectionne(JPanel pTypeJoueurSelectionne) {
		this.pTypeJoueurSelectionne = pTypeJoueurSelectionne;
	}

	public void setBrTypeJoueurSelectionneHumain(
			JRadioButton brTypeJoueurSelectionneHumain) {
		this.brTypeJoueurSelectionneHumain = brTypeJoueurSelectionneHumain;
	}

	public void setBrTypeJoueurSelectionneVirtuel(
			JRadioButton brTypeJoueurSelectionneVirtuel) {
		this.brTypeJoueurSelectionneVirtuel = brTypeJoueurSelectionneVirtuel;
	}

	public void setLbNomJoueurSelectionne(JLabel lbNomJoueurSelectionne) {
		this.lbNomJoueurSelectionne = lbNomJoueurSelectionne;
	}

	public void setTfNomJoueurSelectionne(JTextField tfNomJoueurSelectionne) {
		this.tfNomJoueurSelectionne = tfNomJoueurSelectionne;
	}

	public void setLbPrenomJoueurSelectionneHumain(
			JLabel lbPrenomJoueurSelectionneHumain) {
		this.lbPrenomJoueurSelectionneHumain = lbPrenomJoueurSelectionneHumain;
	}

	public void setTfPrenomJoueurSelectionneHumain(
			JTextField tfPrenomJoueurSelectionneHumain) {
		this.tfPrenomJoueurSelectionneHumain = tfPrenomJoueurSelectionneHumain;
	}

	public void setLbStrategieJoueurSelectionneVirtuel(
			JLabel lbStrategieJoueurSelectionneVirtuel) {
		this.lbStrategieJoueurSelectionneVirtuel = lbStrategieJoueurSelectionneVirtuel;
	}

	public void setGbStrategieJoueurSelectionneVirtuel(
			ButtonGroup gbStrategieJoueurSelectionneVirtuel) {
		this.gbStrategieJoueurSelectionneVirtuel = gbStrategieJoueurSelectionneVirtuel;
	}

	public void setpStrategieJoueurSelectionneVirtuel(
			JPanel pStrategieJoueurSelectionneVirtuel) {
		this.pStrategieJoueurSelectionneVirtuel = pStrategieJoueurSelectionneVirtuel;
	}

	public void setBrStrategieJoueurSelectionneVirtuelOffensif(
			JRadioButton brStrategieJoueurSelectionneVirtuelOffensif) {
		this.brStrategieJoueurSelectionneVirtuelOffensif = brStrategieJoueurSelectionneVirtuelOffensif;
	}

	public void setBrStrategieJoueurSelectionneVirtuelDefensif(
			JRadioButton brStrategieJoueurSelectionneVirtuelDefensif) {
		this.brStrategieJoueurSelectionneVirtuelDefensif = brStrategieJoueurSelectionneVirtuelDefensif;
	}

	public void setLbEquipeJoueurSelectionne(JLabel lbEquipeJoueurSelectionne) {
		this.lbEquipeJoueurSelectionne = lbEquipeJoueurSelectionne;
	}

	public void setTfEquipeJoueurSelectionne(JTextField tfEquipeJoueurSelectionne) {
		this.tfEquipeJoueurSelectionne = tfEquipeJoueurSelectionne;
	}

	public void setBpModifierJoueur(JButton bpModifierJoueur) {
		this.bpModifierJoueur = bpModifierJoueur;
	}

	public void setBpChargerPartie(JButton bpChargerPartie) {
		this.bpChargerPartie = bpChargerPartie;
	}

	public void setBpLancerPartie(JButton bpLancerPartie) {
		this.bpLancerPartie = bpLancerPartie;
	}

	public void setBpQuitter(JButton bpQuitter) {
		this.bpQuitter = bpQuitter;
	}
	
}