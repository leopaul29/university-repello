/**
 * @author Loic
 * 
 * @version V0_0_0
 * 
 * Université de Nice - Département Informatique
 * Année 2013-2014 - S3T
 * 
 * 
 * Edition A : 
 *		+Version V0_0_1 : 
 * 
 * 
 */

package Demonstrateur;
import Repello.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;



/**
 * La  Class EcouteurExpulserPion, permet qu'à la pression sur le bouton "OK" de la partie : "Repousser Pion",
 * elle récupère les informations de l'expulsé et de l'expulseur pour envoyer à l'observeur leurs coordonnées
 * 
 */
public class EcouteurExpulserPion implements ActionListener {
	
	/** Createur au sens, hamecon vers le père. */
	DemonstrateurRepello createur;
	
	// --------------------- Constructeur normal (1)
	/**
	 * Constructeur de ecouteur expulser pion.
	 *
	 * @param createur the createur
	 */
	public EcouteurExpulserPion(DemonstrateurRepello createur){
		this.createur = createur;
	}
	
	
	/** 
	 * Méthode actionPerformed, au clic sur le bouton expulser, elle récupèrera les données 
	 * de l'expulseur et de l'expulsé et les envois à l'observeur du démonstrateur
	 */
	public void actionPerformed(ActionEvent e) {
		
		//On récupère les coordonnées de l'expulseur et de l'expulsé 
		//choisi dans la vue du démonstrateur
		//
		String x_expulseur = String.valueOf(createur.getPan_vue().getComboBox_expulseur_x().getSelectedItem());
		String y_expulseur = String.valueOf(createur.getPan_vue().getComboBox_expulseur_y().getSelectedItem());
		String x_expulse = String.valueOf(createur.getPan_vue().getComboBox_expulse_x().getSelectedItem());
		String y_expulse = String.valueOf(createur.getPan_vue().getComboBox_expulse_y().getSelectedItem());
		
		//On récupère le joueur sélectionné
		String joueur=  createur.getPan_vue().getJoueur();
		
		//On créé les HashMap destinés au update
		HashMap<String, Object> info = new HashMap<String, Object>  ();
		HashMap<String, HashMap<String,Object>> action_info = new HashMap<String, HashMap<String,Object>>();
		
		//On remplis les HashMap
		info.put("expulseur", new Point(Double.valueOf(x_expulseur)-1,Double.valueOf(y_expulseur)-1));
		info.put("expulse", new Point(Double.valueOf(x_expulse)-1,Double.valueOf(y_expulse)-1));
		info.put("joueur", joueur);
		
		action_info.put("expulser",info);
		
		//Update
		createur.update(action_info);
		
	}

}
