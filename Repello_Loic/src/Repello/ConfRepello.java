/**
 * 
 * Université de Nice - Département Informatique
 * Année 2013-2014 - S3T
 * 
 * 
 * @version V0_0_0
 * 
 * Edition A : 
 *		+Version V0_0_1 :  Construction d'un HashMap dans la méthode main
 * 		+Version V0_0_2 :  Séparation en plusieurs HashMap
 * 		+Version V0_0_3 : Copie de certaines clé dans différent package afin
 * 						  de garder l'objectif : Une classe dispose d'une clé qui
//						  lui reseignera tout ce quelle doit savoir
 * @author Loic
 * 
 */

package Repello;

import java.awt.Color;
import java.util.HashMap;


/**
 * La  Class ConfRepello.
 */
public class ConfRepello {
	
	
	/**
	 * La Méthode main, elle construit entièrement le hashmap de configuration 
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		HashMap enregistrement = new HashMap();
		
		HashMap controleurRepello = new HashMap();
		
		controleurRepello.put("nom_fenetre", "Repello");
		controleurRepello.put("location_x", 0);
		controleurRepello.put("location_y",0);
		controleurRepello.put("curseur_x", 1);
		controleurRepello.put("curseur_y", 1);
		controleurRepello.put("curseur_nom", "Pointeur");
		controleurRepello.put("largeur_plateau", 747);
		controleurRepello.put("hauteur_plateau", 717);
		controleurRepello.put("nb_case_largeur_plateau", 13);
		controleurRepello.put("nb_case_hauteur_plateau", 13);
		controleurRepello.put("color_r", 10);
		controleurRepello.put("color_g", 255);
		controleurRepello.put("color_b", 10);
		int [][] grille = lireNumPlateau(13,13,"_Config/Config");
		controleurRepello.put("grille_plateau", grille);

		HashMap observateurAppli = new HashMap();
		
		observateurAppli.put("image_stack_bleu", "_Config/_Images/StackBleu.png");
		observateurAppli.put("image_stack_rouge", "_Config/_Images/StackRouge.png");
		observateurAppli.put("image_stack_vert", "_Config/_Images/StackVert.png");
		observateurAppli.put("image_stack_jaune", "_Config/_Images/StackJaune.png");
		observateurAppli.put("nom_j1", "Joueur1");
		observateurAppli.put("nom_j2", "Joueur2");
		observateurAppli.put("nom_j3", "Joueur3");
		observateurAppli.put("nom_j4", "Joueur4");
		observateurAppli.put("s_couleur_j1", "bleu");
		observateurAppli.put("s_couleur_j2", "rouge");
		observateurAppli.put("s_couleur_j3", "vert");
		observateurAppli.put("s_couleur_j4", "jaune");
		observateurAppli.put("couleur_j1", Color.blue);
		observateurAppli.put("couleur_j2", Color.red);
		observateurAppli.put("couleur_j3", Color.green);
		observateurAppli.put("couleur_j4", Color.yellow);
		
		
		HashMap controleurStat = new HashMap();
		
		controleurStat.put("nom_fenetre", "StatJoueur_");
		controleurStat.put("x", 0);
		controleurStat.put("y", 155);
		controleurStat.put("largeur", 300);
		controleurStat.put("hauteur", 150);
		controleurStat.put("pion_or", "or");
		controleurStat.put("pion_noir", "noir");
		controleurStat.put("pion_argent", "argent");
		controleurStat.put("curseur_x", 1);
		controleurStat.put("curseur_y", 1);
		controleurStat.put("curseur_nom", "Pointeur");
		controleurStat.put("drag","drag");
		controleurStat.put("image_repelleur_noir","_Config/_Images/Repellere.png");
		controleurStat.put("image_repelleur_gris","_Config/_Images/RepellereGris.png");
		controleurStat.put("image_repelleur_or","_Config/_Images/RepellereDore.png");
		
		
		HashMap controleurApplicationRepello = new HashMap();
		
		controleurApplicationRepello.put("nom_fenetre", "Repello");
		controleurApplicationRepello.put("largeur", 600);
		controleurApplicationRepello.put("hauteur", 300);
		controleurApplicationRepello.put("configurer_nom","Configuration");
		controleurApplicationRepello.put("configurer_largeur",600);
		controleurApplicationRepello.put("configurer_hauteur",550);

		
		HashMap joueur = new HashMap();
		
		joueur.put("nom_j1", "Joueur1");
		joueur.put("nom_j2", "Joueur2");
		joueur.put("nom_j3", "Joueur3");
		joueur.put("nom_j4", "Joueur4");
		joueur.put("couleur_j1", "bleu");
		joueur.put("couleur_j2", "rouge");
		joueur.put("couleur_j3", "vert");
		joueur.put("couleur_j4", "jaune");
		
		HashMap moteur = new HashMap();
		
		moteur.put("color_r", 0);
		moteur.put("color_g", 128);
		moteur.put("color_b",255);
		moteur.put("image_repelleur_noir","_Config/_Images/Repellere.png");
		moteur.put("image_repelleur_gris","_Config/_Images/RepellereGris.png");
		moteur.put("image_repelleur_or","_Config/_Images/RepellereDore.png");
		moteur.put("creer_x", 0);
		moteur.put("creer_y", 0);
		moteur.put("nb_case_largeur_plateau", 13);
		moteur.put("nb_case_hauteur_plateau", 13);
		moteur.put("pion_or", "or");
		moteur.put("pion_noir", "noir");
		moteur.put("pion_argent", "argent");
		moteur.put("son_expulser", "_Config/_Son/expulser_son.mp3");
		moteur.put("son_placer", "_Config/_Son/placer_son.mp3");
		moteur.put("son_victoire", "_Config/_Son/victoire_son.mp3");

		
		HashMap moteur_stat = new HashMap();
		
		moteur_stat.put("pion_or", "or");
		moteur_stat.put("pion_noir", "noir");
		moteur_stat.put("pion_argent", "argent");
		
		HashMap panneau_joueur = new HashMap();
		
		panneau_joueur.put("background",Color.white);
		panneau_joueur.put("layout_x",1);
		panneau_joueur.put("layout_y",4);
		panneau_joueur.put("image_repelleur_noir","_Config/_Images/Repellere.png");
		panneau_joueur.put("image_repelleur_gris","_Config/_Images/RepellereGris.png");
		panneau_joueur.put("image_repelleur_or","_Config/_Images/RepellereDore.png");
		panneau_joueur.put("background_or",Color.white);
		panneau_joueur.put("background_noir",Color.white);
		panneau_joueur.put("background_argent",Color.white);
		panneau_joueur.put("background_stack",Color.white);
		panneau_joueur.put("layout_centre_x",1);
		panneau_joueur.put("layout_centre_y",3);
		panneau_joueur.put("layout_centre_background", Color.white);
		panneau_joueur.put("layout_bas_x",1);
		panneau_joueur.put("layout_bas_y",2);
		panneau_joueur.put("layout_bas_background", Color.white);
		panneau_joueur.put("layout_centre_x",1);
		panneau_joueur.put("layout_centre_y",3);
		panneau_joueur.put("total_point", "Total de point : ");
		panneau_joueur.put("valeur_defaut",-1);
		
		HashMap plateau = new HashMap();
		
		plateau.put("image","_Config/_Images/Plateau");
		
		
		HashMap repellere = new HashMap();
		
		repellere.put("pion_or", "or");
		repellere.put("pion_noir", "noir");
		repellere.put("pion_argent", "argent");
		repellere.put("valeur_or", 5);
		repellere.put("valeur_noir", 1);
		repellere.put("valeur_argent", 3);
		
			
		HashMap stack = new HashMap();
		
		stack.put("image_repelleur_noir","_Config/_Images/Repellere.png");
		stack.put("couleur_repelleur","noir");
		stack.put("s_couleur_j1", "bleu");
		stack.put("s_couleur_j2", "rouge");
		stack.put("s_couleur_j3", "vert");
		stack.put("s_couleur_j4", "jaune");

		HashMap vueapplication = new HashMap();
		
		vueapplication.put("fond", "_Config/_Images/RepelloTitre.png");
		vueapplication.put("button_demarrer","Demarrer");
		vueapplication.put("button_charger","Charger");
		vueapplication.put("button_configurer","Configurer");
		vueapplication.put("button_quitter","Quitter");

		
		enregistrement.put("controleurRepello", controleurRepello);
		enregistrement.put("controleurStat", controleurStat);
		enregistrement.put("observateurAppli", observateurAppli);
		enregistrement.put("controleurApplicationRepello", controleurApplicationRepello);
		enregistrement.put("joueur", joueur);
		enregistrement.put("moteur", moteur);
		enregistrement.put("moteur_stat", moteur_stat);
		enregistrement.put("panneau_joueur",panneau_joueur);
		enregistrement.put("plateau", plateau);
		enregistrement.put("repellere", repellere);
		enregistrement.put("stack",stack);
		enregistrement.put("vueapplication",vueapplication);
		
		
		Config.store(enregistrement, "_Config/config_repello", "V0_0_8");
		
	}
	
	
	public static int [][] lireNumPlateau (int nbCaseHauteurPlateau, int nbCaseLargeurPlateau, String contenue){
		
		String contenu = Fichier.chargementFichierTexte(contenue);
		
		if(nbCaseHauteurPlateau <1 || nbCaseLargeurPlateau<1)
			return null;
		
		int [][] nbPlateau = new int [nbCaseHauteurPlateau] [nbCaseLargeurPlateau];
		String motCles = new String("Ligne ");
		
		for(int i= 0; i < nbCaseHauteurPlateau; i++){
			
			nbPlateau[i] = lireLigneTab(motCles+(i+1), nbCaseLargeurPlateau, nbCaseHauteurPlateau, contenu);
		}
		
		return nbPlateau;
		
	}
	
	
	/*
	 * Lit une ligne du plateau
	 */
	/**
	 * Lire ligne tab.
	 *
	 * @param motCles the mot cles
	 * @return the int[]
	 */
	public static int [] lireLigneTab (String motCles, int nbCaseLargeurPlateau,int nbCaseHauteurPlateau, String contenu){
		
		int [] nb = new int [nbCaseLargeurPlateau];
		char lettreLu = '\0';
		
		int position = contenu.indexOf(motCles);
		position += motCles.length();
		
		int compteur = 0;
		
		while( lettreLu != '\n' && compteur < nbCaseLargeurPlateau){
			
			lettreLu = contenu.charAt(position);

			if(caractEstNb(lettreLu)){
				nb[compteur] = Integer.valueOf(lettreLu)-48;
				compteur++;
			}
			
			position++;
		}
		
		return nb;
	}
	
	/**
	 * Caract est nb.
	 *
	 * @param lettre the lettre
	 * @return true, si ok
	 */
	private static boolean caractEstNb (char lettre){
		return ((int)lettre >= (int)'0')  && ((int) lettre <= (int)'9');
	}
	
	
	
	
	
	

}
