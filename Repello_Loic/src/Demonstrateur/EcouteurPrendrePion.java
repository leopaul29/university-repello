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
 * La  Class EcouteurPrendrePion, permet qu'� la pression sur le bouton "Prendre" de la partie : "Prendre",
 * elle r�cup�re les informations, du joueur � prendre, du pion � prendre
 * pour l'envoyer � l'observeur leurs coordonn�es.
 */
public class EcouteurPrendrePion implements ActionListener {
	
	/** Createur au sens, hamecon vers le p�re */
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
	 * M�thode actionPerformed, r�cup�re les informations du joueur qui prends,
	 * le joueur qui est pris, le pion � prendre
	 */
	public void actionPerformed(ActionEvent arg0) {		
		//On r�cup�re le nom du joueur s�lectionn�
		String joueur = createur.getPan_vue().getJoueur();
		
		//On r�cup�re la valeur de la liste d�roulante contenant le joueur � voler
		String de = (String) createur.getPan_vue().getComboBox_prendre().getSelectedItem();
		
		//On r�cup�re la couleur du joueur prenant le pion
		String voleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);
		//On r�cup�re la couleur du joueur donnant le pion
		String vole = (String) createur.getPan_vue().getNom_joueur().get(de);
		//On r�cup�re la couleur du pion pris
		String pion = (String) createur.getPan_vue().getComboBox_prendre_pion().getSelectedItem();
		
		//On cr�� les HashMap pour le update
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
