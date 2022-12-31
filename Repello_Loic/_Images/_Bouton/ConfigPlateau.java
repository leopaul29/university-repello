package TryGrillConf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JFrame;

public class ConfigPlateau implements Serializable{

	// Decrire les parametres de configuration
	//   	
	public static HashMap<?, ?> configurer() {
		HashMap<Object, Object> config= new HashMap<Object, Object>();

		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		config.put("taille", 728);
		config.put("titre", "Plateau de jeu Repello");
		config.put("locationX", 230);
		config.put("locationY", 0);
		config.put("resizable", false);
		config.put("fermer", JFrame.EXIT_ON_CLOSE);
		config.put("placement", new GridLayout(13,13, 1, 1));
		config.put("arrierePlan", Color.BLACK);
		config.put("visible", true);

		LinkedHashMap<String, Serializable> plateau= new LinkedHashMap<String, Serializable>();
		LinkedHashMap<Object, HashMap<String, Serializable>> sousPanneaux= new LinkedHashMap<Object, HashMap<String, Serializable>>();
		//plateau.put("sousPanneaux", sousPanneaux);

		//// Renseigner chacun des sous panneaux (niveau 1) 
		//
		HashMap<String, Serializable> Un= new HashMap<String, Serializable>();
		HashMap<String, Serializable> Deux = new HashMap<String, Serializable>();
		HashMap<String, Serializable> Trois= new HashMap<String, Serializable>();
		HashMap<String, Serializable> Quatre = new HashMap<String, Serializable>();
		HashMap<String, Serializable> Cinq= new HashMap<String, Serializable>();
		HashMap<String, Serializable> Six = new HashMap<String, Serializable>();

		// Renseigner le dictionnaire des couleur de fond des sous panneaux (niveau 1)
		//
		HashMap<String, Serializable> UnVert= new HashMap<String, Serializable>();
		HashMap<String, Serializable> DeuxVert = new HashMap<String, Serializable>();
		HashMap<String, Serializable> TroisVert= new HashMap<String, Serializable>();
		HashMap<String, Serializable> QuatreVert = new HashMap<String, Serializable>();
		HashMap<String, Serializable> CinqVert= new HashMap<String, Serializable>();
		HashMap<String, Serializable> SixVert = new HashMap<String, Serializable>();

		HashMap<String, Serializable> DeuxNoir = new HashMap<String, Serializable>();
		HashMap<String, Serializable> QuatreNoir = new HashMap<String, Serializable>();
		HashMap<String, Serializable> SixNoir = new HashMap<String, Serializable>();

		HashMap<String, Serializable> UnBleu= new HashMap<String, Serializable>();
		HashMap<String, Serializable> TroisBleu= new HashMap<String, Serializable>();
		HashMap<String, Serializable> CinqBleu= new HashMap<String, Serializable>();

		HashMap<String, Serializable> QuatreJaune= new HashMap<String, Serializable>();
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
				System.out.println("nbCase ="+numCase);
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


					if(dep == 1)plateau.put(""+numCase, Un);
					if(dep == 2)plateau.put(""+numCase, Deux);
					if(dep == 3)plateau.put(""+numCase, Trois);
					if(dep == 4)plateau.put(""+numCase, Quatre);
					if(dep == 5)plateau.put(""+numCase, Cinq);
					if(dep == 6)plateau.put(""+numCase, Six);

					//Colorer les cases en vert
					if(i == 3 || i == 9)
						if(j > 2 && j < 10)
						{
							if(dep == 1)plateau.put(""+numCase, UnVert);
							if(dep == 2)plateau.put(""+numCase, DeuxVert);
							if(dep == 3)plateau.put(""+numCase, TroisVert);
							if(dep == 4)plateau.put(""+numCase, QuatreVert);
							if(dep == 5)plateau.put(""+numCase, CinqVert);
							if(dep == 6)plateau.put(""+numCase, SixVert);
						}

					if(j == 3 || j == 9)
						if(i > 2 && i < 10)
						{
							if(dep == 1)plateau.put(""+numCase, UnVert);
							if(dep == 2)plateau.put(""+numCase, DeuxVert);
							if(dep == 3)plateau.put(""+numCase, TroisVert);
							if(dep == 4)plateau.put(""+numCase, QuatreVert);
							if(dep == 5)plateau.put(""+numCase, CinqVert);
							if(dep == 6)plateau.put(""+numCase, SixVert);
						}

					// Colore les case en noir
					if((i==11 && j==1) || (i==1 && j==1) || (i==1 && j==11) || (i==11 && j==11))
					{
						if(dep == 2)plateau.put(""+numCase, DeuxNoir);
						if(dep == 4)plateau.put(""+numCase, QuatreNoir);
						if(dep == 6)plateau.put(""+numCase, SixNoir);
					}

					// Colore les case en bleu
					if((i==1 && j==6)  || (i==6 && j==1) || (i==6 && j==11))
					{
						if(dep == 3)plateau.put(""+numCase, TroisBleu);
						if(dep == 5)plateau.put(""+numCase, CinqBleu);
					}

					// Color la case en jaune
					if(i==6 && j==6)
						plateau.put(""+numCase, QuatreJaune);
				}
				else
				{
					if(dep2 == 3)plateau.put(""+numCase, Trois);
					if(dep2 == 5)plateau.put(""+numCase, Cinq);

					// Colore la case en bleu
					if(i==11 && j==6)
						plateau.put(""+numCase, UnBleu);
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

	public static void main(String[] args) {
		HashMap<?, ?> config= configurer();
		HashMap<?, ?> w= null;

		Config.store(config, "Config/ConfigPlateau", "0.5.0");
		w= (HashMap<?, ?>)Config.load("Config/ConfigPlateau", "0.5.0");
	}
}
