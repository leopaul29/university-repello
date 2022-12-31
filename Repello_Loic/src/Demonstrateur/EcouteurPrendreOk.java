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

/**
 * La  Class EcouteurPrendreOk, permet qu'à la pression sur le bouton "OK" de la partie : "Prendre",
 * elle affiche les boutons permettant de choisir les pions à voler
 */
public class EcouteurPrendreOk implements ActionListener {
	
	/** Createur au sens, hamecon vers le père. */
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
	 *  Méthode actionPerformed, au clic sur le bouton "OK", de la partie : Prendre",
	 * elle affiche les boutons permettant de choisir les pions à voler
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		//On change la visibilité d'une liste déroulante et d'un bouton
		createur.getPan_vue().getComboBox_prendre_pion().setVisible(true);
		createur.getPan_vue().getButton_prendre_prendre().setVisible(true);
	}
	
	
	

}
