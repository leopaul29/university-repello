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

/**
 * La  Class EcouteurPrendreOk, permet qu'� la pression sur le bouton "OK" de la partie : "Prendre",
 * elle affiche les boutons permettant de choisir les pions � voler
 */
public class EcouteurPrendreOk implements ActionListener {
	
	/** Createur au sens, hamecon vers le p�re. */
	private DemonstrateurRepello createur;
	
	/**
	 * Constructeur de ecouteur prendre ok.
	 *
	 * @param createur the createur
	 */
	public EcouteurPrendreOk(DemonstrateurRepello createur){
		this.createur = createur;
	}

	
	/**
	 *  M�thode actionPerformed, au clic sur le bouton "OK", de la partie : Prendre",
	 * elle affiche les boutons permettant de choisir les pions � voler
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		//On change la visibilit� d'une liste d�roulante et d'un bouton
		createur.getPan_vue().getComboBox_prendre_pion().setVisible(true);
		createur.getPan_vue().getButton_prendre_prendre().setVisible(true);
	}
	
	
	

}
