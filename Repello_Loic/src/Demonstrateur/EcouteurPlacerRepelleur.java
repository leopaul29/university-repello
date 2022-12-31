/**
 * 

 * Universit� de Nice - D�partement Informatique
 * Ann�e 2013-2014 - S3T
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
 * La  Class EcouteurPlacerRepelleur, permet qu'� la pression sur le bouton "OK" de la partie : "Placer Repelleur",
 * elle r�cup�re les informations de la coordonn�e s�lectionn�e pour l'envoyer � l'observeur leurs coordonn�es.
 */
public class EcouteurPlacerRepelleur implements ActionListener {

	/** Createur au sens, hamecon vers le p�re. */
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
	 *  M�thode actionPerformed, au clic sur le bouton "OK", de la partie : Placer Repelleur",
	 * elle r�cup�re les informations de la coordonn�e s�lectionn� pour envoyer � l'observeur leurs coordonn�es
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		//On r�cup�re les coordonn�es s�lectionn� dans le d�monstrateur 
		//
		String x1 = String.valueOf(createur.getPan_vue().getComboBox_placer_repelleur_x().getSelectedItem());
		String y1 = String.valueOf(createur.getPan_vue().getComboBox_placer_repelleur_y().getSelectedItem());

		//On r�cup�re le pion s�lectionn� dans le d�monstrateur 
		//
		String couleur = (String) createur.getPan_vue().getComboBox_repelleur_pion().getSelectedItem();


		//On cr�� le point du repelleur
		Point p1 = new Point(Double.valueOf(x1)-1,Double.valueOf(y1)-1);
		
		//Cr�ation des HashMap pour le update
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
