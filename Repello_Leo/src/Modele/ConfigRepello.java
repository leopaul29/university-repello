/*
 * 
 */
package Modele;

import java.awt.Color;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * Class ConfigRepello
 * 
 * @author LeoPaul
 *
 */
public class ConfigRepello {

	// Decrire les parametres de configuration
	//   	
	/**
	 * Configurer.
	 *
	 * @return the hash map
	 */
	public static HashMap<String, Object> configurer() {
		HashMap<String, Object> config= new HashMap<String, Object>();

		// pour le modele Grille
		config.put("Hauteur",         13);
		config.put("Largeur",         13);

		// pour le modele Joueur
		config.put("Rouge", "Rouge");
		config.put("Bleu", "Bleu");
		config.put("Vert", "Vert");
		config.put("Jaune", "Jaune");
		config.put("stack", 16);
		
		return config;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		HashMap<?, ?> config= configurer();

		Config.store(config, "Config/ConfigRepello", "1.0.0");
		Config.load("Config/ConfigRepello", "1.0.0");
	}
}
