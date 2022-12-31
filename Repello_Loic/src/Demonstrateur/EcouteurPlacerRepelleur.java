/**
 * 

 * Université de Nice - Département Informatique
 * Année 2013-2014 - S3T
 * 
 * 
 * @version V0_0_0
 * 
 * Edition A : 
 *		+Version V0_0_1 : 
 * 
 * 
 * 
 * @author Loic
 * 
 */
package Demonstrateur;
import Repello.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


/**
 * La  Class EcouteurPlacerRepelleur, permet qu'à la pression sur le bouton "OK" de la partie : "Placer Repelleur",
 * elle récupère les informations de la coordonnée sélectionnée pour l'envoyer à l'observeur leurs coordonnées.
 */
public class EcouteurPlacerRepelleur implements ActionListener {

	/** Createur au sens, hamecon vers le père. */
	private DemonstrateurRepello createur;

	/**
	 * Constructeur de ecouteur placer repelleur.
	 *
	 * @param createur the createur
	 */
	public EcouteurPlacerRepelleur(DemonstrateurRepello createur){
		this.createur = createur;
	}

	/**
	 *  Méthode actionPerformed, au clic sur le bouton "OK", de la partie : Placer Repelleur",
	 * elle récupère les informations de la coordonnée sélectionné pour envoyer à l'observeur leurs coordonnées
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		//On récupère les coordonnées sélectionné dans le démonstrateur 
		//
		String x1 = String.valueOf(createur.getPan_vue().getComboBox_placer_repelleur_x().getSelectedItem());
		String y1 = String.valueOf(createur.getPan_vue().getComboBox_placer_repelleur_y().getSelectedItem());

		//On récupère le pion sélectionné dans le démonstrateur 
		//
		String couleur = (String) createur.getPan_vue().getComboBox_repelleur_pion().getSelectedItem();


		//On créé le point du repelleur
		Point p1 = new Point(Double.valueOf(x1)-1,Double.valueOf(y1)-1);
		
		//Création des HashMap pour le update
		//
		HashMap<String, Object> info= new HashMap<String, Object>();
		HashMap<String, HashMap<String, Object>> action_info = new HashMap<String, HashMap<String,Object>>();

		//On ajoute dans les HashMap
		info.put("point", p1);
		info.put("couleur", couleur);

		action_info.put("placer_repelleur", info);
		
		//Update
		createur.update(action_info);

	}


}
