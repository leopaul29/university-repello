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
 * La  Class EcouteurGagne, permet qu'à la pression sur le bouton "Gagnat" 
 * elle récupère le joueur sélectionné pour envoyer à l'observeur 
 * 
 */
public class EcouteurGagne implements ActionListener{
	
	/** Createur au sens, hamecon vers le père */
	DemonstrateurRepello createur;
	
	/**
	 * Constructeur de ecouteur gagne.
	 *
	 * @param createur the createur
	 */
	public EcouteurGagne(DemonstrateurRepello createur){
		this.createur = createur;
	}
	
	
	/** 
	 * Méthode actionPerformed, au clic sur le bouton gagner, elle récupèrera la donnée
	 * du joueur et l'envois à l'observeur du démonstrateur
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {
		//On récupère le joueur sélectionné
		//
		String joueur = createur.getPan_vue().getJoueur();
		//On récupère la couleur du joueur sélectionné 
		//
		String couleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);
		
		//On créé le HashMap pour le update
		HashMap action_info = new HashMap();
		//On met dans le HashMap la couleur du gagnant
		action_info.put("gagnant", couleur);
		
		//Update
		createur.update(action_info);
	}

}
