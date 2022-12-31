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
import Repello.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;



/**
 * La  Class EcouteurDeplacerPion, permet qu'à la pression sur une touche fléchès elle prévienne l'observeur
 * de DemonstrateurRepello, de l'ancienne et de la nouvelle coordonnée du point sélectionné.
 */
public class EcouteurDeplacerPion implements ActionListener {

	/** Createur au sens, hamecon vers le père. */
	private DemonstrateurRepello createur;

	/** L'absisses de départ du pion. */
	private int x;

	/** L'ordonnée de départ du pion. */
	private int y;


	// --------------------- Constructeur normal (1)
	/**
	 * Constructeur de ecouteur deplacer pion.
	 *
	 * @param createur l'hamecon vers le père
	 * @param x l'absisses de départ
	 * @param y l'ordonnée de départ
	 */
	public EcouteurDeplacerPion(DemonstrateurRepello createur,int x, int y){
		this.createur = createur;
		this.x = x;
		this.y = y;

	}

	/**
	 * Méthode actionPerformed, au clic sur le bouton fléchès, elle récupère toutes les informations
	 * dont elle à besoin les modifies selon le sens, la valeur qu'à sélectionné l'utilisateur
	 * et envois les valeurs ancien_point, nouveau_point, couleur
	 * représentant la coordonnée de départ du pion, la coordonnée d'arrivée du pion,
	 * et la couleur du joueur déplacement le pion.
	 *
	 * @param e the e
	 */
	public void actionPerformed(ActionEvent e) {
		//On récupère la vue du démonstrateur
		//
		DemonstrateurVue pan = createur.getPan_vue();
		
		//On récupère le nom du joueur
		//
		String joueur=  createur.getPan_vue().getJoueur();
		
		//On récupère l'absisses pour déplacer et on l'adapte à un tableau 0-12 (x--)
		Integer x = (Integer) pan.getComboBox_deplacer_x().getSelectedItem();
		x--;
		
		//On récupère l'ordonnée pour déplacer et on l'adapte à un tableau 0-12 (y--)
		Integer y = (Integer) pan.getComboBox_deplacer_y().getSelectedItem();
		y--;

		Integer deplacement = 0;
		
		//On récupère la valeur de déplacement
		Integer value = (Integer) pan.getComboBox_deplacer_valeur().getSelectedItem();
		
		//On récupère la couleur du joueur
		String couleur = (String) createur.getPan_vue().getNom_joueur().get(joueur);

		//Création des HashMap à envoyer à l'observeur du démonstrateur
		//
		HashMap<String, Object> info= new HashMap<String, Object>();
		HashMap<String, HashMap<String, Object>> action_info = new HashMap<String, HashMap<String, Object>>();
		
		Integer y1 = y, x1 = x;

		//
		//Différent tests sur les paramètres
		//
		//
		
		//si x == 0 on se déplacera verticalement
		if(this.x == 0) x1=x;
		//si x > 0 on se déplacera vers la droite
		else if (this.x > 0 )  deplacement+=value;
		//sinon on se déplacera vers la gauche
		else deplacement-=value;

		//on ajoute les calculs au x1 qui sera renvoyé
		x1+=deplacement;

		//on remet la valeur déplacement à 0 
		deplacement=0;
		
		//si y == 0, on se déplacera horizontalement
		if(this.y == 0) y1=y;
		//si y > 0 on se déplacera vers le bas
		else if(this.y > 0 ) deplacement+=value;
		//sinon on se déplacera vers le haut 
		else deplacement-=value;
		
		//on ajoute les calculs à y1
		y1+=deplacement;

		
		//On construit les points anciens et nouveaux du pion en fonction des calculs précédent
		//
		Point ancienPoint =  new Point(Double.valueOf(x),Double.valueOf(y));
		Point newPoint = new Point(Double.valueOf(x1),Double.valueOf(y1));
		
		//On ajoute aux HashMap les données
		//
		info.put("ancien_point", ancienPoint);
		info.put("nouveau_point", newPoint);
		info.put("couleur", couleur);

		action_info.put("deplacer",info);
		
		//Update l'observeur
		createur.update(action_info);

		
		//On récupère et on test les coordonnées
		if( newPoint.x() > pan.getComboBox_deplacer_x().getItemCount() || newPoint.x() < 0 ||
				newPoint.y() > pan.getComboBox_deplacer_y().getItemCount() || newPoint.y() < 0) 
			return;

		//Mise à jours des listes après un déplacement
		//
		pan.getComboBox_deplacer_x().setSelectedIndex((int) newPoint.x());
		pan.getComboBox_deplacer_y().setSelectedIndex((int) newPoint.y());

	}

}
