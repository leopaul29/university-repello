/*
 * 
 */
package Controleur;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;

import Controleur.ControleurPlateau;
import Modele.Config;
import Modele.Grille;
import Modele.Joueur;
import Vue.Demonstrateur;
import Vue.Plateau;
import Vue.StatJoueur;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Throwable the throwable
	 */
	public static void main(String[] args) throws Throwable {

		// Charger le dictionnaire de configuration du jeu avec les derniere variable a externaliser
		//
		String cheminRepello= "Config/ConfigRepello";
		String versionRepello= "1.0.0";
		HashMap<?, ?> configRepello;

		configRepello= (HashMap<?, ?>) Config.load(cheminRepello, versionRepello);
		if (configRepello == null) throw new Throwable ("-0.9");

		// Créer le cadre du démonstrateur
		//
		JFrame f_demonstrateur = new JFrame();

		// Créer le démonstrateur et le mettre dans la fenetre
		//
		Demonstrateur vueD = new Demonstrateur(f_demonstrateur);


		// Créer et initialiser le modele de la grille
		//
		Grille modele = new Grille();
		modele.initialiser(configRepello);

		// Créer un cadre pour le plateau
		JFrame f_plateau = new JFrame();

		// Charger le dictionnaire de configuration du plateau
		//
		String cheminPlateau= "Config/ConfigPlateau";
		String versionPlateau= "0.6.1";
		HashMap<?, ?> configPlateau;

		configPlateau= (HashMap<?, ?>) Config.load(cheminPlateau, versionPlateau);
		if (configPlateau == null) throw new Throwable ("-1.0");

		// Creer le plateau correspondant
		//
		Plateau vueP = new Plateau(f_plateau, configPlateau);
		ControleurPlateau controleurPlateau =  new ControleurPlateau(modele, vueP, vueD);

		// Fixer les écouteur sur les boutons du démonstrateur
		//
		vueD.getPlacerPiece().addActionListener(controleurPlateau);
		vueD.getRetirerPiece().addActionListener(controleurPlateau);
		vueD.getDeplacerPiece().addActionListener(controleurPlateau);
		vueD.getExpulser().addActionListener(controleurPlateau);



		// Créer et initialiser le modele joueur pour 4 joueurs
		//
		Joueur modeleJ = new Joueur(configRepello);
		modeleJ.initialiser();

		// Créer un cadre pour le tableau des score des joueurs
		//
		final JFrame f_joueur = new JFrame();

		// Charger le dictionnaire de configuration du tableau des scores
		//
		String cheminJoueur= "Config/ConfigJoueur";
		String versionJoueur= "0.3.1";
		HashMap<?, ?> configJoueur;

		configJoueur= (HashMap<?, ?>) Config.load(cheminJoueur, versionJoueur);
		if (configJoueur == null) throw new Throwable ("-2.0");

		// Creer et définire les caractéristique du tableau des scores correspondant
		//
		StatJoueur vueJ = new StatJoueur(f_joueur, configJoueur);
		ControleurStatJoueur controleurJoueur = new ControleurStatJoueur(modeleJ, vueJ, vueD);

		// Fixer les écouteur sur les boutons du démonstrateur
		//
		vueD.getIncPoint().addActionListener(controleurJoueur);
		vueD.getDecPoint().addActionListener(controleurJoueur);
		vueD.getVoler().addActionListener(controleurJoueur);

		// Je créer un écouteur pour faire disparaitre la fenetre
		//
		f_joueur.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e) {f_joueur.setVisible(false);}
		});

		// Je crée un écouteur pour faire apparaitre la fenetre
		//
		vueD.getAfficherLesScores().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f_joueur.setVisible(true);
			}
		});


		// Creer un cadre support du temps
		//
		final JFrame f_temps = new JFrame();
		f_temps.setSize(300, 300);
		f_temps.setTitle ("Timer");
		f_temps.setLocation(0, 300);
		f_temps.setLayout(new GridLayout(1,0));

		// Charger le dictionnaire de configuration fonctionnelle d'un sablier
		//
		String cheminSablier= "Config/ConfigSablier";
		String versionSablier= "3.3.0";
		HashMap<?, ?> configSablier;

		configSablier= (HashMap<?, ?>) Config.load(cheminSablier, versionSablier);
		if (configSablier == null) throw new Throwable ("-3.0");

		// Charger le dictionnaire de configuration de la vue d'un sablier
		//
		String cheminVueSablier= "Config/ConfigVueSablier_A";
		String versionVueSablier= "1.X.0";
		HashMap<?, ?> configVueSablier;

		configVueSablier= (HashMap<?, ?>) Config.load(cheminVueSablier, versionVueSablier);
		if (configVueSablier == null) throw new Throwable ("-3.1");
		// Creer le sablier correspondant
		//
		ControleurTempsG sablier;
		sablier= new ControleurTempsG(f_temps, configSablier, configVueSablier); 

		vueD.getLancerLeSablier().addActionListener(new EcouteurTemps(sablier, vueD));
		vueD.getReprendre().addActionListener(new EcouteurTemps(sablier, vueD));
		vueD.getPause().addActionListener(new EcouteurTemps(sablier, vueD));

		// Demarrer et visualiser le sablier 
		//
		sablier.demarrer(120);

		// Placer une butee intermediaire sur le sablier 
		//
		//sablier.setButee("00 : 00 : 00");

		// Visualiser le cadre support
		//
		f_temps.setVisible(true);

		// Je créer un écouteur pour faire disparaitre la fenetre
		//
		f_temps.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e) {f_temps.setVisible(false);}
		});

		// Je crée un écouteur pour faire apparaitre la fenetre
		//
		vueD.getAfficherLeTemps().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f_temps.setVisible(true);
			}
		});

	}
}
