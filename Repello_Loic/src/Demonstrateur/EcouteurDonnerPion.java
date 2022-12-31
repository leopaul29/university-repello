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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * La  Class EcouteurDonnerPion, permet qu'� la pression sur le bouton "Donner", le pion s�lectionn�
 * dans la liste d�roulante soit donn� au joueur dans la liste sup�rieure
 */
public class EcouteurDonnerPion implements ActionListener{
	
	/** Createur au sens, hamecon vers le p�re  */
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
 	 * M�thode actionPerformed, au clic sur le bouton d�placer, elle r�cup�re toutes les informations
	 * dont elle a besoin et les envois � l'observeur du d�monstrateur
 	*/
	public void actionPerformed(ActionEvent e) {
		//R�cup�re le nom du pion s�lectionn� 
		//
		String pion = (String) createur.getPan_vue().getComboBox_donner().getSelectedItem();
		//R�cup�re le joueur s�lectionn�
		//
		String joueur = createur.getPan_vue().getJoueur();
		//R�cup�re la couleur du joueur s�lectionn�
		//
		String couleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);
		
		//Construit les HashMap pour le update
		HashMap<String, String> info = new HashMap<String, String>();
		HashMap<String,HashMap<String,String>> action_info = new HashMap<String,HashMap<String,String>>();
		
		//Remplis les HashMap avec les donn�es collect�es
		info.put("pion",pion);
		info.put("joueur", couleur);
		
		action_info.put("donner",info);
	
		//Update
		createur.update(action_info);
		
	}

}
