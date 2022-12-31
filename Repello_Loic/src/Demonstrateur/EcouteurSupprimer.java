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
import Repello.*;
// TODO: Auto-generated Javadoc
/**
 * La  Class EcouteurSupprimer.
 */
public class EcouteurSupprimer implements ActionListener {
	
	/** Createur au sens, hamecon vers le p�re. */
	DemonstrateurRepello createur;

	/**
	 * Constructeur de ecouteur supprimer.
	 *
	 * @param createur the createur
	 */
	public EcouteurSupprimer( DemonstrateurRepello createur){
		this.createur = createur;
	}



	/**
	 * M�thode actionPerformed, au clic sur le bouton supprimer, elle r�cup�rera les coordonn�es 
	 *  de la case � supprimer et l'envois � l'observeur du d�monstrateur
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//On r�cup�re les coordonn�es s�lectionn� dans le d�monstrateur 
		//
		String x1 = String.valueOf(createur.getPan_vue().getComboBox_supprimer_x().getSelectedItem());
		String y1 = String.valueOf(createur.getPan_vue().getComboBox_supprimer_y().getSelectedItem());

		//On cr�� un point avec les informations s�lectionn�es
		Point p1 = new Point(Double.valueOf(x1)-1,Double.valueOf(y1)-1);
		
		//On cr�� les HashMap
		HashMap<String, Object> info= new HashMap<String, Object>();
		HashMap<String, HashMap<String, Object>> action_info = new HashMap<String, HashMap<String,Object>>();

		//On ajoute les informations au HashMap
		info.put("position", p1);

		action_info.put("supprPion", info);
		
		
		//Update
		createur.update(action_info);
	}

}
