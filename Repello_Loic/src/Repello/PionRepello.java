package Repello;

import java.awt.image.BufferedImage;


// TODO: Auto-generated Javadoc
/**
 * La  Class PionRepello.
 */
public abstract class PionRepello {
	
	/** Le coordonne. */
	protected Point coordonne;
	
	/** Le image. */
	protected BufferedImage image;
	
	/** Le couleur. */
	protected String couleur;
	
	
	/**
	 * X.
	 *
	 * @return the int
	 */
	public int x () {return (int)coordonne.x();}
	
	/**
	 * Y.
	 *
	 * @return the int
	 */
	public int y () {return (int)coordonne.y();}
	
	/**
	 * X.
	 *
	 * @param x the x
	 */
	protected void x (int x) { coordonne.x(x);}
	
	/**
	 * Y.
	 *
	 * @param y the y
	 */
	public void y (int y) { coordonne.y(y);}
	
	/**
	 * Getter de  couleur.
	 *
	 * @return le couleur
	 */
	public String getCouleur(){ return couleur;}
	
	/**
	 * Getter de  image.
	 *
	 * @return le image
	 */
	public BufferedImage getImage (){ return image;}

}
