package Repello;
//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Applications graphiques
//
// Exemple d'applications interactives non parametrees
//
// Classe PanneauImages	: panneau de visualisation d'images et de dessins
//
// Edition A : visualisation d'une image de fond   : 
//
//    + Version 1.0.0	: version initiale
//	  + Version 2.0.0   : correction de l'erreur
//
// Auteur : A. Thuaire
//

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * La  Class PanneauImages.
 */
public class PanneauImages extends JPanel {

/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

/** Le image fond. */
private BufferedImage imageFond;	

/** Le hamecon. */
private Container hamecon;
  
// ----                                                  Constructeur normal

   /**
 * Constructeur de panneau images.
 *
 * @param cheminImage the chemin image
 * @param cont the cont
 */
public PanneauImages (String cheminImage, Container cont) { 
   
      super();
      hamecon = cont;
      
      setBackground(Color.black);
      if (cheminImage != null) chargerImage(cheminImage);
   }
   
   
   /**
    * Constructeur de panneau images.
    *
    * @param image the image
    * @param cont the cont
    */
   public PanneauImages (BufferedImage image, Container cont) { 
	   
	      super();
	      hamecon = cont;
	      
	      setBackground(Color.black);
	      imageFond = image;
	   }
   
// ----                                                        Methode paint

   /* (non-Javadoc)
 * @see javax.swing.JComponent#paint(java.awt.Graphics)
 */
public void paint (Graphics g) {
   	
      super.paint(g);
      
      if (imageFond != null) {
         g.drawImage(imageFond, 0, 0, getWidth(), getHeight(), null);
      } 	
   }  
   
// ----                                                 Methode chargerImage 
   
   /**
 * Charger image.
 *
 * @param cheminImage the chemin image
 */
private void chargerImage(String cheminImage) {
      	
      // Controler la validite du parametre
   	  //
   	  if (cheminImage == null) return;
   	     
      // Charger une image depuis un fichier de type jpeg
      //
      try {
		imageFond= ImageIO.read(new File(cheminImage));
	} catch (IOException e1) {e1.printStackTrace();}
         	
      // Construire un media tracker pour controler le chargement de l'image
      //
      MediaTracker mt= new MediaTracker(this);
   	  
      // Attendre la fin du chargement effectif de l'image
      //
      mt.addImage(imageFond,0);
      try{mt.waitForAll();}
      catch(Exception e){}
   }    
   
   
   
  // ----												Accesseur
   
   /**
   * Getter de  image.
   *
   * @return le image
   */
  public BufferedImage getImage(){ return imageFond;}
   
   /**
    * Getter de  hamecon.
    *
    * @return le hamecon
    */
   public Container getHamecon(){ return hamecon;}
   
}
