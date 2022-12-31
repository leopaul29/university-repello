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
 * La  Class EcouteurPrendrePion, permet qu'à la pression sur le bouton "Prendre" de la partie : "Prendre",
 * elle récupère les informations, du joueur à prendre, du pion à prendre
 * pour l'envoyer à l'observeur leurs coordonnées.
 */
public class EcouteurPrendrePion implements ActionListener {
	
	/** Createur au sens, hamecon vers le père */
	DemonstrateurRepello createur;
	
	// --------------------- Constructeur normal (1)
	/**
	 * Constructeur de ecouteur prendre pion.
	 *
	 * @param createur the createur
	 */
	public EcouteurPrendrePion(DemonstrateurRepello createur){
		this.createur = createur;
	}
	
	/**
	 * Méthode actionPerformed, récupère les informations du joueur qui prends,
	 * le joueur qui est pris, le pion à prendre
	 */
	public void actionPerformed(ActionEvent arg0) {		
		//On récupère le nom du joueur sélectionné
		String joueur = createur.getPan_vue().getJoueur();
		
		//On récupère la valeur de la liste déroulante contenant le joueur à voler
		String de = (String) createur.getPan_vue().getComboBox_prendre().getSelectedItem();
		
		//On récupère la couleur du joueur prenant le pion
		String voleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);
		//On récupère la couleur du joueur donnant le pion
		String vole = (String) createur.getPan_vue().getNom_joueur().get(de);
		//On récupère la couleur du pion pris
		String pion = (String) createur.getPan_vue().getComboBox_prendre_pion().getSelectedItem();
		
		//On créé les HashMap pour le update
		HashMap <String, String> info = new HashMap <String,String>();
		HashMap<String,HashMap<String,String>> action_info = new HashMap<String,HashMap<String,String>>();
		
		//On remplis les HashMap avec les informations
		info.put("voleur",voleur);
		info.put("vole",vole);
		info.put("pion",pion);
		
		action_info.put("voler",info);
		
		//Update
		createur.update(action_info);
	}

}
