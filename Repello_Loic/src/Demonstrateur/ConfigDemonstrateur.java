/**
 * 
 * Université de Nice - Département Informatique
 * Année 2013-2014 - S3T
 * 
 * 
 * @version V0_0_0
 * 
 * Edition A : 
 *		+Version V0_0_1 : Externalisation des textes contenus dans les boutons 
 * 		+Version V0_0_2 : Externalisation des textes contenus dans les JLabels
 * 		+Version V0_0_3 : Externalisation des informations chiffrées tel que la taille etc..
 * 		+Version V0_0_4 : Externalisation des liens des images 
 * 
 * 
 * 
 * @author Loic
 * 
 */
package Demonstrateur;

import java.util.HashMap;


/**
 * La  Class ConfigDemonstrateur, elle a pour objectif de creer le fichier de configuration
 * contenant toutes les informations dont le demonstrateur aura besoin
 */
public class ConfigDemonstrateur {
	
	
	/**
	 * Méthode main, la seule méthode de cette classe
	 *
	 * @param args the arguments
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		//Création du HashMap principal
		HashMap enregistrement = new HashMap();
		
		//Création du HashMap contenant toute les images
		HashMap image = new HashMap();
		
		//ajout des différentes images
		image.put("image_fleche_b", "fleche_b.png");
		image.put("image_fleche_bd", "fleche_bd.png");
		image.put("image_fleche_bg", "fleche_bg.png");
		image.put("image_fleche_h", "fleche_h.png");
		image.put("image_fleche_hd", "fleche_hd.png");
		image.put("image_fleche_hg", "fleche_hg.png");
		image.put("image_fleche_d", "fleche_d.png");
		image.put("image_fleche_g", "fleche_g.png");
		image.put("image_sablier", "sablier.png");

		
		//Création du HashMap contenant toutees les tailles
		HashMap taille = new HashMap();
		
		//ajout des tailles
		taille.put("taille_ordonnee",14);
		taille.put("taille_deplacement", 7);
		
		//Création du HashMap contenant le text des items
		HashMap nom_item = new HashMap();
		
		//ajout des text 
		nom_item.put("action", "Action");
		nom_item.put("placer_deplacer", "Placer/Déplacer");
		nom_item.put("donner", "Donner : ");
		nom_item.put("prendre", "Prendre : ");
		nom_item.put("button_donner", "OK");
		nom_item.put("button_prendre", "OK");
		nom_item.put("repousser", "Repousser Pion :");
		nom_item.put("expulseur", "Expulseur : ");
		nom_item.put("expulse", "Expulsé : ");
		nom_item.put("button_expulser", "OK");
		nom_item.put("supprimer", "Supprimer : ");
		nom_item.put("button_supprimer", "OK");
		nom_item.put("button_gagne", "Gagnant");
		nom_item.put("placer_repelleur", "Placer Repelleur : ");
		nom_item.put("button_repelleur", "OK");
		nom_item.put("placer_stack", "Placer Stack : ");
		nom_item.put("button_stack", "OK");
		nom_item.put("deplacer_pion", "Déplacer Pion : ");
		nom_item.put("button_quitter", "Quitter");



		//ajout au HashMap principal
		enregistrement.put("image", image);
		enregistrement.put("taille",taille);
		enregistrement.put("nom_item", nom_item);
		enregistrement.put("resize", false);
		enregistrement.put("hauteur", 550);
		enregistrement.put("largeur", 300);
		enregistrement.put("location",0);

		
		//store
		Config.store(enregistrement, "_Config/config_demonstrateur", "V0_0_8");
	}
	
}
