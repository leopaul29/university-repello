/**
 * 
 * Université de Nice - Département Informatique
 * Année 2013-2014 - S3T
 * 
 * 
 * @version V0_0_0
 * 
 * Edition A : 
 *		+Version V0_0_1 : Ajout d'une image au JButton, MButton peux ajouter une image de fond sur le bouton
 *							grace à paint de la class Grapics
 * 
 * 
 * 
 * @author Loic
 * 
 */
package Demonstrateur;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;




public class MButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Image qui sera placé sur le bouton cachant de ce faite le titre de celui-ci
	 */
	private Image imagefond;
	/**
	 * Titre du bouton au sens JButton(title)
	 */
	private String chemin;
	
	/**
	 * Getter de l'image du bouton
	 * @return 	l'image(Image) du bouton 
	 */
	public Image getImagefond() {return imagefond;}
	/**
	 * Setter de l'image du bouton
	 * @param imagefond image(Image) à placer sur le bouton
	 */
	public void setImagefond(Image imagefond) {this.imagefond = imagefond;}
	
	
	/**
	 * Constructeur normal du bouton
	 * 
	 * @param title Titre du bouton, si il y a une image, le titre ne s'affichera pas
	 * @param image Lien vers l'image placé après _Images/_Bouton/ 
	 */
	// --------------------- Constructeur normal (1)
	public MButton(String title, String image){
		super();
		this.setText(title);
		chemin = "_Images/_Bouton/"+image;
	}
	
	
	/**
	 * Méthode redéfinie de Graphics, qui affiche l'image du bouton sur le bouton
	 */
	// --------------------- Méthode paint
	public void paint (Graphics g) {
		super.paint(g);
		Image imagefond = null;
		
		try {imagefond = ImageIO.read(new File("_Config/"+chemin));}
		catch (IOException e) {e.printStackTrace();}
		
		if (imagefond != null) 
			g.drawImage(imagefond, 0, 0, getWidth(), getHeight(), null);
	  
	}  


}
