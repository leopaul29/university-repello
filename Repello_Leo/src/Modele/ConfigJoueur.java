package Modele;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigJoueur.
 */
public class ConfigJoueur {

	// Decrire les parametres de configuration
	//   	
	/**
	 * Configurer.
	 *
	 * @return the hash map
	 */
	public static HashMap <String, Object> configurer() {
		HashMap <String, Object> config= new HashMap <String, Object> ();

		config.put("taille", 300);
		config.put("titreF", "Score des Joueurs");
		config.put("locationX", 0);
		config.put("locationY", 0);
		config.put("resizable", true);
		config.put("fermer", JFrame.DO_NOTHING_ON_CLOSE);
		config.put("arrierePlan", Color.BLACK);
		config.put("visible", true);

		config.put("arrierePlan", Color.black);
		config.put("placement", new GridLayout(4,1));
		config.put("sousPanneaux", sousPanneaux());

		return config;
	}

	// Decrire les panneaux internes
	//
	/**
	 * Sous panneaux.
	 *
	 * @return the linked hash map
	 */
	public static LinkedHashMap <String, Object> sousPanneaux() {

		// Les sous panneaux
		//
		LinkedHashMap <String, Object> sousPanneaux= new LinkedHashMap <String, Object> ();

		// Joueur Rouge
		//
		HashMap <String, Object> jetonR = new HashMap <String, Object> ();

		jetonR.put("arrierePlan", Color.red);
		jetonR.put("image",       "jeton.png");

		jetonR.put("avantPlan", Color.white);
		jetonR.put("titre", "0");
		jetonR.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> jetonArgentR = new HashMap <String, Object> ();

		jetonArgentR.put("arrierePlan", Color.red);
		jetonArgentR.put("image",       "jetonARGENT.png");

		jetonArgentR.put("avantPlan", Color.white);
		jetonArgentR.put("titre", "0");
		jetonArgentR.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> jetonOrR= new HashMap <String, Object> ();

		jetonOrR.put("arrierePlan", Color.red);
		jetonOrR.put("image",       "jetonOR.png");

		jetonOrR.put("avantPlan", Color.white);
		jetonOrR.put("titre", "0");
		jetonOrR.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> stackR= new HashMap <String, Object> ();

		stackR.put("arrierePlan", Color.red);
		stackR.put("image",       "PionsR.png");

		stackR.put("avantPlan", Color.white);
		stackR.put("titre", "16");
		stackR.put("police", new Font("Times Roman", Font.BOLD, 24));

		sousPanneaux.put("jetonRouge", jetonR);
		sousPanneaux.put("jetonArgentRouge", jetonArgentR);
		sousPanneaux.put("jetonOrRouge", jetonOrR);
		sousPanneaux.put("stackRouge", stackR);

		// Joueur Bleu
		//
		HashMap <String, Object> jetonB= new HashMap <String, Object> ();

		jetonB.put("arrierePlan", Color.blue);
		jetonB.put("image",       "jeton.png");

		jetonB.put("avantPlan", Color.white);
		jetonB.put("titre", "0");
		jetonB.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> jetonArgentB= new HashMap <String, Object> ();

		jetonArgentB.put("arrierePlan", Color.blue);
		jetonArgentB.put("image",       "jetonARGENT.png");

		jetonArgentB.put("avantPlan", Color.white);
		jetonArgentB.put("titre", "0");
		jetonArgentB.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> jetonOrB= new HashMap <String, Object> ();

		jetonOrB.put("arrierePlan", Color.blue);
		jetonOrB.put("image",       "jetonOR.png");

		jetonOrB.put("avantPlan", Color.white);
		jetonOrB.put("titre", "0");
		jetonOrB.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> stackB= new HashMap <String, Object> ();

		stackB.put("arrierePlan", Color.blue);
		stackB.put("image",       "PionsB.png");

		stackB.put("avantPlan", Color.white);
		stackB.put("titre", "16");
		stackB.put("police", new Font("Times Roman", Font.BOLD, 24));

		sousPanneaux.put("jetonBleu", jetonB);
		sousPanneaux.put("jetonArgentBleu", jetonArgentB);
		sousPanneaux.put("jetonOrBleu", jetonOrB);
		sousPanneaux.put("stackBleu", stackB);

		// Joueur Vert
		//
		HashMap <String, Object> jetonV= new HashMap <String, Object> ();

		jetonV.put("arrierePlan", Color.green);
		jetonV.put("image",       "jeton.png");

		jetonV.put("avantPlan", Color.white);
		jetonV.put("titre", "0");
		jetonV.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> jetonArgentV= new HashMap <String, Object> ();

		jetonArgentV.put("arrierePlan", Color.green);
		jetonArgentV.put("image",       "jetonARGENT.png");

		jetonArgentV.put("avantPlan", Color.white);
		jetonArgentV.put("titre", "0");
		jetonArgentV.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> jetonOrV= new HashMap <String, Object> ();

		jetonOrV.put("arrierePlan", Color.green);
		jetonOrV.put("image",       "jetonOR.png");

		jetonOrV.put("avantPlan", Color.white);
		jetonOrV.put("titre", "0");
		jetonOrV.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> stackV= new HashMap <String, Object> ();

		stackV.put("arrierePlan", Color.green);
		stackV.put("image",       "PionsV.png");

		stackV.put("avantPlan", Color.white);
		stackV.put("titre", "16");
		stackV.put("police", new Font("Times Roman", Font.BOLD, 24));

		sousPanneaux.put("jetonVert", jetonV);
		sousPanneaux.put("jetonArgentVert", jetonArgentV);
		sousPanneaux.put("jetonOrVert", jetonOrV);
		sousPanneaux.put("stackVert", stackV);


		// Joueur Jaune
		//
		HashMap <String, Object> jetonJ= new HashMap <String, Object> ();

		jetonJ.put("arrierePlan", Color.yellow);
		jetonJ.put("image",       "jeton.png");

		jetonJ.put("avantPlan", Color.white);
		jetonJ.put("titre", "0");
		jetonJ.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> jetonArgentJ= new HashMap <String, Object> ();

		jetonArgentJ.put("arrierePlan", Color.yellow);
		jetonArgentJ.put("image",       "jetonARGENT.png");

		jetonArgentJ.put("avantPlan", Color.white);
		jetonArgentJ.put("titre", "0");
		jetonArgentJ.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> jetonOrJ= new HashMap <String, Object> ();

		jetonOrJ.put("arrierePlan", Color.yellow);
		jetonOrJ.put("image",       "jetonOR.png");

		jetonOrJ.put("avantPlan", Color.white);
		jetonOrJ.put("titre", "0");
		jetonOrJ.put("police", new Font("Times Roman", Font.BOLD, 24));

		HashMap <String, Object> stackJ= new HashMap <String, Object> ();

		stackJ.put("arrierePlan", Color.yellow);
		stackJ.put("image",       "PionsJ.png");

		stackJ.put("avantPlan", Color.white);
		stackJ.put("titre", "16");
		stackJ.put("police", new Font("Times Roman", Font.BOLD, 24));

		sousPanneaux.put("jetonJaune", jetonJ);
		sousPanneaux.put("jetonArgentJaune", jetonArgentJ);
		sousPanneaux.put("jetonOrJaune", jetonOrJ);
		sousPanneaux.put("stackJaune", stackJ);

		// retourne la liste de sous panneau
		//
		return sousPanneaux;
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		HashMap<?, ?> config= configurer();

		Config.store(config, "Config/ConfigJoueur", "0.3.1");
		Config.load("Config/ConfigJoueur", "0.3.1");
	}
}
