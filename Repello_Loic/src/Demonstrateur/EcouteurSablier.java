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

// TODO: Auto-generated Javadoc
/**
 * La  Class EcouteurSablier.
 */
public class EcouteurSablier implements ActionListener{
	
	/** Createur au sens, hamecon vers le père. */
	DemonstrateurRepello createur;
	
	
	/**
	 * Constructeur de ecouteur sablier.
	 *
	 * @param createur the createur
	 */
	public EcouteurSablier(DemonstrateurRepello createur){
		this.createur = createur;
	}


	/**
	 * Méthode actionPerformed, elle envoie des données au modèle pour lancer le sablier
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//On créé le HashMap pour le update
		HashMap donne = new HashMap();
		
		//On ajoute une clé au HashMap (pour : si clé => lancer sablier)
		donne.put("updateSablier", null);
		
		//Update 
		createur.update(donne);
		
	}


}
