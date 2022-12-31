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
import Repello.*;

/**
 * La  Class EcouteurPlacerStack, permet qu'à la pression sur le bouton "OK" de la partie : "Placer Stack",
 * elle récupère les informations de la coordonnée sélectionnée pour l'envoyer à l'observeur leurs coordonnées.
 */
public class EcouteurPlacerStack implements ActionListener {

	/** Createur au sens, hamecon vers le père. */
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
	 *  Méthode actionPerformed, au clic sur le bouton "OK", de la partie : Placer Stack",
	 * elle récupère les informations de la coordonnée sélectionné pour envoyer à l'observeur leurs coordonnées
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		//On récupère les coordonnées sélectionné dans le démonstrateur 
		//
		String x1 = String.valueOf(createur.getPan_vue().getComboBox_placer_stack_x().getSelectedItem());
		String y1 = String.valueOf(createur.getPan_vue().getComboBox_placer_stack_y().getSelectedItem());


		//Création des HashMap pour le update
		//
		HashMap<String, Object> info= new HashMap<String, Object>();
		HashMap<String, HashMap<String, Object>> action_info = new HashMap<String, HashMap<String,Object>>();

		//On récupère le nom du joueur sélectionné
		String joueur = createur.getPan_vue().getJoueur();
		//On récupère sa couleur
		String couleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);

		//On remplis les HashMap avec les informations récupérées
		info.put("nouveau_point", new Point(Double.valueOf(x1)-1, Double.valueOf(y1)-1));
		info.put("couleur", couleur);

		action_info.put("placer_stack",info);

		
		//Update
		createur.update(action_info);



	}

}
