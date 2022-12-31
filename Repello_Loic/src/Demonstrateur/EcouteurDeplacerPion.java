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
import Repello.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;



/**
 * La  Class EcouteurDeplacerPion, permet qu'� la pression sur une touche fl�ch�s elle pr�vienne l'observeur
 * de DemonstrateurRepello, de l'ancienne et de la nouvelle coordonn�e du point s�lectionn�.
 */
public class EcouteurDeplacerPion implements ActionListener {

	/** Createur au sens, hamecon vers le p�re. */
	private DemonstrateurRepello createur;

	/** L'absisses de d�part du pion. */
	private int x;

	/** L'ordonn�e de d�part du pion. */
	private int y;


	// --------------------- Constructeur normal (1)
	/**
	 * Constructeur de ecouteur deplacer pion.
	 *
	 * @param createur l'hamecon vers le p�re
	 * @param x l'absisses de d�part
	 * @param y l'ordonn�e de d�part
	 */
	public EcouteurDeplacerPion(DemonstrateurRepello createur,int x, int y){
		this.createur = createur;
		this.x = x;
		this.y = y;

	}

	/**
	 * M�thode actionPerformed, au clic sur le bouton fl�ch�s, elle r�cup�re toutes les informations
	 * dont elle � besoin les modifies selon le sens, la valeur qu'� s�lectionn� l'utilisateur
	 * et envois les valeurs ancien_point, nouveau_point, couleur
	 * repr�sentant la coordonn�e de d�part du pion, la coordonn�e d'arriv�e du pion,
	 * et la couleur du joueur d�placement le pion.
	 *
	 * @param e the e
	 */
	public void actionPerformed(ActionEvent e) {
		//On r�cup�re la vue du d�monstrateur
		//
		DemonstrateurVue pan = createur.getPan_vue();
		
		//On r�cup�re le nom du joueur
		//
		String joueur=  createur.getPan_vue().getJoueur();
		
		//On r�cup�re l'absisses pour d�placer et on l'adapte � un tableau 0-12 (x--)
		Integer x = (Integer) pan.getComboBox_deplacer_x().getSelectedItem();
		x--;
		
		//On r�cup�re l'ordonn�e pour d�placer et on l'adapte � un tableau 0-12 (y--)
		Integer y = (Integer) pan.getComboBox_deplacer_y().getSelectedItem();
		y--;

		Integer deplacement = 0;
		
		//On r�cup�re la valeur de d�placement
		Integer value = (Integer) pan.getComboBox_deplacer_valeur().getSelectedItem();
		
		//On r�cup�re la couleur du joueur
		String couleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);

		//Cr�ation des HashMap � envoyer � l'observeur du d�monstrateur
		//
		HashMap<String, Object> info= new HashMap<String, Object>();
		HashMap<String, HashMap<String, Object>> action_info = new HashMap<String, HashMap<String, Object>>();
		
		Integer y1 = y, x1 = x;

		//
		//Diff�rent tests sur les param�tres
		//
		//
		
		//si x == 0 on se d�placera verticalement
		if(this.x == 0) x1=x;
		//si x > 0 on se d�placera vers la droite
		else if (this.x > 0 )  deplacement+=value;
		//sinon on se d�placera vers la gauche
		else deplacement-=value;

		//on ajoute les calculs au x1 qui sera renvoy�
		x1+=deplacement;

		//on remet la valeur d�placement � 0 
		deplacement=0;
		
		//si y == 0, on se d�placera horizontalement
		if(this.y == 0) y1=y;
		//si y > 0 on se d�placera vers le bas
		else if(this.y > 0 ) deplacement+=value;
		//sinon on se d�placera vers le haut 
		else deplacement-=value;
		
		//on ajoute les calculs � y1
		y1+=deplacement;

		
		//On construit les points anciens et nouveaux du pion en fonction des calculs pr�c�dent
		//
		Point ancienPoint =  new Point(Double.valueOf(x),Double.valueOf(y));
		Point newPoint = new Point(Double.valueOf(x1),Double.valueOf(y1));
		
		//On ajoute aux HashMap les donn�es
		//
		info.put("ancien_point", ancienPoint);
		info.put("nouveau_point", newPoint);
		info.put("couleur", couleur);

		action_info.put("deplacer",info);
		
		//Update l'observeur
		createur.update(action_info);

		
		//On r�cup�re et on test les coordonn�es
		if( newPoint.x() > pan.getComboBox_deplacer_x().getItemCount() || newPoint.x() < 0 ||
				newPoint.y() > pan.getComboBox_deplacer_y().getItemCount() || newPoint.y() < 0) 
			return;

		//Mise � jours des listes apr�s un d�placement
		//
		pan.getComboBox_deplacer_x().setSelectedIndex((int) newPoint.x());
		pan.getComboBox_deplacer_y().setSelectedIndex((int) newPoint.y());

	}

}
