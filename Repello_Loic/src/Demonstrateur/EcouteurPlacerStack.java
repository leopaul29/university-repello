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

/**
 * La  Class EcouteurPlacerStack, permet qu'� la pression sur le bouton "OK" de la partie : "Placer Stack",
 * elle r�cup�re les informations de la coordonn�e s�lectionn�e pour l'envoyer � l'observeur leurs coordonn�es.
 */
public class EcouteurPlacerStack implements ActionListener {

	/** Createur au sens, hamecon vers le p�re. */
	private DemonstrateurRepello createur;

	/**
	 * Constructeur de ecouteur placer stack.
	 *
	 * @param createur the createur
	 */
	public EcouteurPlacerStack(DemonstrateurRepello createur){
		this.createur = createur;
	}

	
	/**
	 *  M�thode actionPerformed, au clic sur le bouton "OK", de la partie : Placer Stack",
	 * elle r�cup�re les informations de la coordonn�e s�lectionn� pour envoyer � l'observeur leurs coordonn�es
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		//On r�cup�re les coordonn�es s�lectionn� dans le d�monstrateur 
		//
		String x1 = String.valueOf(createur.getPan_vue().getComboBox_placer_stack_x().getSelectedItem());
		String y1 = String.valueOf(createur.getPan_vue().getComboBox_placer_stack_y().getSelectedItem());


		//Cr�ation des HashMap pour le update
		//
		HashMap<String, Object> info= new HashMap<String, Object>();
		HashMap<String, HashMap<String, Object>> action_info = new HashMap<String, HashMap<String,Object>>();

		//On r�cup�re le nom du joueur s�lectionn�
		String joueur = createur.getPan_vue().getJoueur();
		//On r�cup�re sa couleur
		String couleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);

		//On remplis les HashMap avec les informations r�cup�r�es
		info.put("nouveau_point", new Point(Double.valueOf(x1)-1, Double.valueOf(y1)-1));
		info.put("couleur", couleur);

		action_info.put("placer_stack",info);

		
		//Update
		createur.update(action_info);



	}

}
