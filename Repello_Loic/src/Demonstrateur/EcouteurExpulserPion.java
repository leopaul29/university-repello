/**
 * @author Loic
 * 
 * @version V0_0_0
 * 
 * Universit� de Nice - D�partement Informatique
 * Ann�e 2013-2014 - S3T
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
 * La  Class EcouteurExpulserPion, permet qu'� la pression sur le bouton "OK" de la partie : "Repousser Pion",
 * elle r�cup�re les informations de l'expuls� et de l'expulseur pour envoyer � l'observeur leurs coordonn�es
 * 
 */
public class EcouteurExpulserPion implements ActionListener {
	
	/** Createur au sens, hamecon vers le p�re. */
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
	 * M�thode actionPerformed, au clic sur le bouton expulser, elle r�cup�rera les donn�es 
	 * de l'expulseur et de l'expuls� et les envois � l'observeur du d�monstrateur
	 */
	public void actionPerformed(ActionEvent e) {
		
		//On r�cup�re les coordonn�es de l'expulseur et de l'expuls� 
		//choisi dans la vue du d�monstrateur
		//
		String x_expulseur = String.valueOf(createur.getPan_vue().getComboBox_expulseur_x().getSelectedItem());
		String y_expulseur = String.valueOf(createur.getPan_vue().getComboBox_expulseur_y().getSelectedItem());
		String x_expulse = String.valueOf(createur.getPan_vue().getComboBox_expulse_x().getSelectedItem());
		String y_expulse = String.valueOf(createur.getPan_vue().getComboBox_expulse_y().getSelectedItem());
		
		//On r�cup�re le joueur s�lectionn�
		String joueur=  createur.getPan_vue().getJoueur();
		
		//On cr�� les HashMap destin�s au update
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
