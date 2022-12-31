package Repello;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


// TODO: Auto-generated Javadoc
/**
 * La  Class Repellere.
 */
public class Repellere extends PionRepello{
	
	/** Le valeur. */
	private int valeur;
	
	/** Le recup. */
	@SuppressWarnings("rawtypes")
	private static  HashMap recup;
	
	/**
	 * Constructeur de repellere.
	 *
	 * @param p the p
	 * @param image the image
	 * @param couleur the couleur
	 * @param configRepello the config repello
	 */
	@SuppressWarnings("rawtypes")
	public Repellere(Point p, String chemin, String couleur, HashMap configRepello) {
		
		
		
		
		recup = (HashMap) configRepello.get("repellere");
		
		if(! estRepeller(couleur)) new Throwable("1.0: Erreur couleur Repellere");
		super.couleur = couleur;
		
		//on associe une valeur
		//
		if( couleur.equals("noir")) valeur = (Integer) recup.get("valeur_noir");
		else if(couleur.equals("argent"))valeur =(Integer) recup.get("valeur_argent");
		else if(couleur.equals("or"))valeur =(Integer) recup.get("valeur_or");
		
		
		coordonne = p;
		
		try {
			image = ImageIO.read(new File(chemin));
		} catch (IOException e) {e.printStackTrace();}

	}
	
	
	
	/**
	 * Getter de  valeur.
	 *
	 * @return le valeur
	 */
	public int getValeur(){ return valeur;}
	
	/**
	 * Est repeller.
	 *
	 * @param couleur the couleur
	 * @return true, si ok
	 */
	public static boolean estRepeller (String couleur){
		
		if( couleur == null)
			return false;
		
		return couleur.equals( (String)recup.get("pion_noir")) || couleur.equals( (String) recup.get("pion_or")) ||
				couleur.equals( (String) recup.get("pion_argent")) ;
	}
}
