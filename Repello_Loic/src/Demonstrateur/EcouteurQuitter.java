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

// TODO: Auto-generated Javadoc
/**
 * La  Class EcouteurQuitter, permet de g�rer la fermeture d'une vue
 */
public class EcouteurQuitter implements ActionListener{
	
	/** Createur au sens, hamecon vers le p�re. */
	private DemonstrateurRepello createur;
	
	/**
	 * Constructeur de ecouteur quitter.
	 *
	 * @param createur the createur
	 */
	public EcouteurQuitter(DemonstrateurRepello createur){
		this.createur = createur;
	}
	
	
	/**
	 * M�thode actionPerfomed, elle ferme la vue de l'hamecon 
	 */
	public void actionPerformed(ActionEvent arg0) {
		//Ferme la vue du cr�ateur
		createur.getVue().dispose();
	}

}
