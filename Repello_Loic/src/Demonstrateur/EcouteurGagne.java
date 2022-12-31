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
 * La  Class EcouteurGagne, permet qu'� la pression sur le bouton "Gagnat" 
 * elle r�cup�re le joueur s�lectionn� pour envoyer � l'observeur 
 * 
 */
public class EcouteurGagne implements ActionListener{
	
	/** Createur au sens, hamecon vers le p�re */
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
	 * M�thode actionPerformed, au clic sur le bouton gagner, elle r�cup�rera la donn�e
	 * du joueur et l'envois � l'observeur du d�monstrateur
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {
		//On r�cup�re le joueur s�lectionn�
		//
		String joueur = createur.getPan_vue().getJoueur();
		//On r�cup�re la couleur du joueur s�lectionn� 
		//
		String couleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);
		
		//On cr�� le HashMap pour le update
		HashMap action_info = new HashMap();
		//On met dans le HashMap la couleur du gagnant
		action_info.put("gagnant", couleur);
		
		//Update
		createur.update(action_info);
	}

}
