package Repello;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;


// TODO: Auto-generated Javadoc
/**
 * La  Class Stack.
 */
public class Stack extends PionRepello{
	
	/** Le list repellere. */
	LinkedList<Repellere> listRepellere;
	
	/** Le recup. */
	@SuppressWarnings("rawtypes")
	private static HashMap recup;

	
	/**
	 * Constructeur de stack.
	 *
	 * @param image the image
	 * @param p the p
	 * @param couleur the couleur
	 * @param configRepello the config repello
	 */
	@SuppressWarnings("rawtypes")
	public Stack( BufferedImage image, Point p, String couleur, HashMap configRepello) {
		recup = (HashMap) configRepello.get("stack");
		
		if( ! estStack(couleur)) new Throwable("1.2: Erreur couleur Stack");
		
		super.couleur = couleur;
		
		this.image = image;
		this.coordonne = p;
		
		listRepellere = new LinkedList<Repellere>();
		
		
		String couleurRepNoir = (String) recup.get("couleur_repelleur");
		String cheminRepNoir = (String) recup.get("image_repelleur_noir");
		for(int i =0; i <16; i++){
			
			listRepellere.add(new Repellere(new Point(), cheminRepNoir,
					couleurRepNoir,configRepello));
		}
		
	}


	/**
	 * Pop.
	 *
	 * @return the repellere
	 */
	public Repellere pop(){
		
		return listRepellere.pop();
		
	}
	
	/**
	 * Est stack.
	 *
	 * @param couleur the couleur
	 * @return true, si ok
	 */
	public static boolean estStack (String couleur){
		
		return couleur.equals(recup.get("s_couleur_j1")) || couleur.equals(recup.get("s_couleur_j2")) ||
				couleur.equals(recup.get("s_couleur_j3")) ||couleur.equals(recup.get("s_couleur_j4")) ;
	}


	/**
	 * Getter de  list repeller.
	 *
	 * @return le list repeller
	 */
	public LinkedList<Repellere> getListRepeller (){ return listRepellere;}
	
	
	
}