/*
 * 
 */
package Modele;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigPlateau.
 */
public class ConfigPlateau implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Decrire les parametres de configuration
	//   	
	/**
	 * Configurer.
	 *
	 * @return the hash map
	 */
	public static HashMap<?, ?> configurer() {
		HashMap<Object, Object> config= new HashMap<Object, Object>();

		config.put("taille", 728);
		config.put("titre", "Plateau de jeu Repello");
		config.put("locationX", 300);
		config.put("locationY", 0);
		config.put("resizable", false);
		config.put("fermer", JFrame.EXIT_ON_CLOSE);
		config.put("placement", new GridLayout(13,13, 1, 1));
		config.put("arrierePlan", Color.BLACK);
		config.put("visible", true);

		LinkedHashMap <Integer, HashMap <String, Object> > plateau= new LinkedHashMap <Integer, HashMap <String, Object> > ();

		//// Renseigner chacun des sous panneaux (niveau 1) 
		//
		HashMap<String, Object> Un= new HashMap<String, Object>();
		HashMap<String, Object> Deux = new HashMap<String, Object>();
		HashMap<String, Object> Trois= new HashMap<String, Object>();
		HashMap<String, Object> Quatre = new HashMap<String, Object>();
		HashMap<String, Object> Cinq= new HashMap<String, Object>();
		HashMap<String, Object> Six = new HashMap<String, Object>();

		// Renseigner le dictionnaire des couleur de fond des sous panneaux (niveau 1)
		//
		HashMap <String, Object> UnVert= new HashMap<String, Object>();
		HashMap <String, Object> DeuxVert = new HashMap<String, Object>();
		HashMap <String, Object> TroisVert= new HashMap<String, Object>();
		HashMap <String, Object> QuatreVert = new HashMap<String, Object>();
		HashMap <String, Object> CinqVert= new HashMap<String, Object>();
		HashMap <String, Object> SixVert = new HashMap<String, Object>();

		HashMap <String, Object> DeuxNoir = new HashMap<String, Object>();
		HashMap <String, Object> QuatreNoir = new HashMap<String, Object>();
		HashMap <String, Object> SixNoir = new HashMap<String, Object>();

		HashMap <String, Object> UnBleu= new HashMap<String, Object>();
		HashMap <String, Object> TroisBleu= new HashMap<String, Object>();
		HashMap <String, Object> CinqBleu= new HashMap<String, Object>();

		HashMap <String, Object> QuatreJaune= new HashMap<String, Object>();
		// --- Initialisation de la grille
		//
		int i = 0, j = 0;
		int dep = 0;
		int dep2 = 0;
		int[] tab = {3,5,1,4,6,2,4,6,2,5,1,3,5};
		for(i = 0; i<13; i++)
		{
			for(j = 0; j<13; j++)
			{				
				int numCase = ((i*13)+j);
				//def le deplacement de la premiere colonne par le contenu du tab
				if (j == 0)
					dep = tab[i];

				//def le deplacement en fonction de celui d'avant
				else 
				{
					dep = dep + 1;
					if(dep>6)
						dep = dep - 6;
				} 

				//exeption de la grille en (6,2), (6,10), (2,9) 
				if((i == 2 || i == 10) && j == 6)
					dep2 = 3;
				if(i == 9 && j == 2)
					dep2 = 5;
				if(i == 11 && j == 6)
					dep2 = 1;

				//Modifier le contenu des image par le deplacement de la case
				if(dep2 == 0)
				{


					if(dep == 1)plateau.put(numCase, Un);
					if(dep == 2)plateau.put(numCase, Deux);
					if(dep == 3)plateau.put(numCase, Trois);
					if(dep == 4)plateau.put(numCase, Quatre);
					if(dep == 5)plateau.put(numCase, Cinq);
					if(dep == 6)plateau.put(numCase, Six);

					//Colorer les cases en vert
					if(i == 3 || i == 9)
						if(j > 2 && j < 10)
						{
							if(dep == 1)plateau.put(numCase, UnVert);
							if(dep == 2)plateau.put(numCase, DeuxVert);
							if(dep == 3)plateau.put(numCase, TroisVert);
							if(dep == 4)plateau.put(numCase, QuatreVert);
							if(dep == 5)plateau.put(numCase, CinqVert);
							if(dep == 6)plateau.put(numCase, SixVert);
						}

					if(j == 3 || j == 9)
						if(i > 2 && i < 10)
						{
							if(dep == 1)plateau.put(numCase, UnVert);
							if(dep == 2)plateau.put(numCase, DeuxVert);
							if(dep == 3)plateau.put(numCase, TroisVert);
							if(dep == 4)plateau.put(numCase, QuatreVert);
							if(dep == 5)plateau.put(numCase, CinqVert);
							if(dep == 6)plateau.put(numCase, SixVert);
						}

					// Colore les case en noir
					if((i==11 && j==1) || (i==1 && j==1) || (i==1 && j==11) || (i==11 && j==11))
					{
						if(dep == 2)plateau.put(numCase, DeuxNoir);
						if(dep == 4)plateau.put(numCase, QuatreNoir);
						if(dep == 6)plateau.put(numCase, SixNoir);
					}

					// Colore les case en bleu
					if((i==1 && j==6)  || (i==6 && j==1) || (i==6 && j==11))
					{
						if(dep == 3)plateau.put(numCase, TroisBleu);
						if(dep == 5)plateau.put(numCase, CinqBleu);
					}

					// Color la case en jaune
					if(i==6 && j==6)
						plateau.put(numCase, QuatreJaune);
				}
				else
				{
					if(dep2 == 3)plateau.put(numCase, Trois);
					if(dep2 == 5)plateau.put(numCase, Cinq);

					// Colore la case en bleu
					if(i==11 && j==6)
						plateau.put(numCase, UnBleu);
				}
				dep2 = 0;

			}
		}

		// Decrire les images de chaque sous panneau
		//
		Un.put("image", "1.png");
		Deux.put("image", "2.png");
		Trois.put("image", "3.png");
		Quatre.put("image", "4.png");
		Cinq.put("image", "5.png");
		Six.put("image", "6.png");

		Un.put("arrierePlan", Color.WHITE);
		Deux.put("arrierePlan", Color.WHITE);
		Trois.put("arrierePlan", Color.WHITE);
		Quatre.put("arrierePlan", Color.WHITE);
		Cinq.put("arrierePlan", Color.WHITE);
		Six.put("arrierePlan", Color.WHITE);

		// Mettre en plus un fond vert
		//
		UnVert.put("image", "1.png");
		DeuxVert.put("image", "2.png");
		TroisVert.put("image", "3.png");
		QuatreVert.put("image", "4.png");
		CinqVert.put("image", "5.png");
		SixVert.put("image", "6.png");

		UnVert.put("arrierePlan", Color.GREEN);
		DeuxVert.put("arrierePlan", Color.GREEN);
		TroisVert.put("arrierePlan", Color.GREEN);
		QuatreVert.put("arrierePlan", Color.GREEN);
		CinqVert.put("arrierePlan", Color.GREEN);
		SixVert.put("arrierePlan", Color.GREEN);

		// Mettre en plus un fond noir
		//
		DeuxNoir.put("image", "2.png");
		QuatreNoir.put("image", "4.png");
		SixNoir.put("image", "6.png");

		DeuxNoir.put("arrierePlan", Color.BLACK);
		QuatreNoir.put("arrierePlan", Color.BLACK);
		SixNoir.put("arrierePlan", Color.BLACK);

		// Mettre en plus un fond bleu
		//
		UnBleu.put("image", "1.png");
		TroisBleu.put("image", "3.png");
		CinqBleu.put("image", "5.png");
		UnBleu.put("arrierePlan", Color.BLUE);
		TroisBleu.put("arrierePlan", Color.BLUE);
		CinqBleu.put("arrierePlan", Color.BLUE);

		// Mettre en plus un fond bleu
		//
		QuatreJaune.put("image", "4.png");

		QuatreJaune.put("arrierePlan", Color.YELLOW);


		config.put("plateau", plateau);
		//
		return config;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		HashMap<?, ?> config= configurer();

		Config.store(config, "Config/ConfigPlateau", "0.6.1");
		Config.load("Config/ConfigPlateau", "0.6.1");
	}
}
