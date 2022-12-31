package Repello;

/**
 * 
 * @author Seb
 * 
 * @version 100
 * 		Creation de la classe
 * 
 * @version 200
 * 		Transformation de la class PanneauImage en Case
 * 		Ajout des propriete: - Color
 * 							 - x 
 * 							 - Y
 * 		                     -PionRepello
 * 
 * 		Ajouter une methode -setPions
 *							-delPions
 *
 * @version 300
 *		Ajout d'un attribu PionRepello
 *		Modification de la méthode paint
 *		Ajout des méthodes - setPionRepello
 *						   - delPionRepello
 *
 *@version 400
 *			Transformation des attribus x et y en un objet Point
 *			Ajout de la méthode caseEstVide
 *
 *
 * 
 * 
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;


/**
 * La  Class Case.
 */
public class Case extends JPanel{

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** L'image de fond de la case */
	private BufferedImage imageFond;
	
	/** L'image de fond original */
	private Color fondCouleurOrginal;
	
	/** Le valeur (le numeros se trouvant sur la case) */
	private int valeur;
	
	/** la poisition de la case */
	private Point point;
	
	/** L'image du pion.*/
	private BufferedImage imagePion;
	
	/** Le est cache. */
	boolean estCache = false;
  
// ----                                                  Constructeur normal
   /**
 * Constructeur de case.
 *
 * @param BufferedImage l'image de la case
 * @param Point p la position de la case
 * @param Color la couleur du fond
 * @param int valeur la valeur de la case
 */
public Case (BufferedImage image ,Point p, Color couleur, int valeur ) { 
      super();
      
      imageFond = image;
      
      point = p;
      
      //On init et change la couleur du fond
      this.fondCouleurOrginal = couleur;
      this.setBackground(fondCouleurOrginal);
      
      this.valeur = valeur;
      
   }
   
// ----                                                        Methode paint


public void paint (Graphics g) {
   	
	  super.paint(g);
      
      if (imageFond != null) {
         g.drawImage(imageFond, 0, 0, getWidth(), getHeight(), null);
      } 	
      
      if(imagePion != null && !estCache)
    	  g.drawImage(imagePion, 0, 0, getWidth(), getHeight(), null);
   }  
   

   /**
    * Permet de récupérer le fond original
    *
    * @return Color
    */
   public Color getFondColorOriginale (){ return fondCouleurOrginal;}
   
   /**
    * Permet de récupérer l'abscisse.
    *
    * @return int
    */
   public int x (){ return (int) point.x();}
   
   /**
    * Permet de récupérer l'ordonne.
    *
    * @return the int
    */
   public int y (){ return (int) point.y();}
   
   /**
    * Permet de récupérer la valeur.
    *
    * @return le valeur
    */
   public int getValeur(){ return valeur;}
   
   /**
    * Permet de modifier l'image du pion.
    *
    * @param image le nouveau image pion
    */
   public void setImagePion (BufferedImage image ){ imagePion = image;}
   
   /**
    * Permet de récupérer l'image du pion.
    *
    * @return le image pion
    */
   public BufferedImage getImagePion (){ return imagePion;}
   
   /**
    *  Permet de cacher ou non le pion sur la Case.
    *
    * @param bool le nouveau cache
    */
   public void setCache( boolean bool){ estCache = bool;}
   
}
