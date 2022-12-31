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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * La  Class EcouteurDonnerPion, permet qu'à la pression sur le bouton "Donner", le pion sélectionné
 * dans la liste déroulante soit donné au joueur dans la liste supérieure
 */
public class EcouteurDonnerPion implements ActionListener{
	
	/** Createur au sens, hamecon vers le père  */
	DemonstrateurRepello createur;
	
	/**
	 * Constructeur de ecouteur donner pion.
	 *
	 * @param createur the createur
	 */
	public EcouteurDonnerPion(DemonstrateurRepello createur){
		this.createur = createur;
	}
	
	
	/**
 	 * Méthode actionPerformed, au clic sur le bouton déplacer, elle récupère toutes les informations
	 * dont elle a besoin et les envois à l'observeur du démonstrateur
 	*/
	public void actionPerformed(ActionEvent e) {
		//Récupère le nom du pion sélectionné 
		//
		String pion = (String) createur.getPan_vue().getComboBox_donner().getSelectedItem();
		//Récupère le joueur sélectionné
		//
		String joueur = createur.getPan_vue().getJoueur();
		//Récupère la couleur du joueur sélectionné
		//
		String couleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);
		
		//Construit les HashMap pour le update
		HashMap<String, String> info = new HashMap<String, String>();
		HashMap<String,HashMap<String,String>> action_info = new HashMap<String,HashMap<String,String>>();
		
		//Remplis les HashMap avec les données collectées
		info.put("pion",pion);
		info.put("joueur", couleur);
		
		action_info.put("donner",info);
	
		//Update
		createur.update(action_info);
		
	}

}
