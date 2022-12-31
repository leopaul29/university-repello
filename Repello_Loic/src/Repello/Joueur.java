package Repello;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * La  Class Joueur.
 */
public class Joueur {
	
	/** Le nom du joueur */
	private String nom;
	
	/** Le stack du joueur*/
	private Stack stack;
	
	/** Le couleur par lequel il est identifi� */
	private Color couleur;
	
	/** Le liste de ses repellere */
	private LinkedList<Repellere> listeRepellereRecup;
	
	
	/**
	 * Constructeur normal
	 *
	 * @param String nom  le nom du joueur.
	 * @param Stack stack  le stack du joueur
	 * @param Color couleur  Le couleur a laquel est associ� un joueur
	 */
	public Joueur(String nom, Stack stack, Color couleur) {
	
		// on affecte les variables
		//
		this.nom = nom;
		this.stack = stack;
		this.couleur = couleur;
		
		//init de la liste des repellers
		//
		listeRepellereRecup = new LinkedList<Repellere>();
		
	}
	
	
	/**
	 * Permet de calculer les points du joueur
	 *
	 * @return int
	 */
	public int calculePoint(){
		
		int result = 0;
		
		//On parcourt la liste des Repellere
		//
		Iterator<Repellere> i = listeRepellereRecup.iterator();
		Repellere repel;
		
		while(i.hasNext()){
			
			repel = i.next();
			
			//on additionne les points de chaque pion
			//
			result += repel.getValeur();
			
		}
		
		return result;
	}
	
	/**
	 * Permet de r�cup�rer le nombre de repeller dans la liste
	 * selon une couleur.
	 *
	 * @param Color couleur  Couleur du type du repeller qu'on veut compter.
	 * @return int
	 */
	public int nbRepell (String couleur){
		
		if( couleur == null || ! Repellere.estRepeller(couleur) ) return -1;
		
		//on init le compteur
		//
		int compteur = 0;
		
		//on parcourt la liste
		//
		Iterator<Repellere> i = listeRepellereRecup.iterator();
		Repellere repel;
		
		while(i.hasNext()){
			repel = i.next();
			
			//on incremente le compteur si le repelleur poss�de la bonne couleur.
			//
			if(repel.getCouleur().equals(couleur))
				compteur++;
		}
		
		return compteur;
	}
	
	/**
	 * Getter
	 * 
	 * Permet de r�cup�rer le stack du joueur
	 *
	 * @return Stack
	 */
	public Stack getStack(){ return stack;}
	
	
	/**
	 * Getter
	 * 
	 * Permet de r�cup�rer le nom du joueur
	 *
	 * @return String
	 */
	public String getNom(){ return nom;}
	
	
	/**
	 * Getter
	 * 
	 * Permet de r�cup�rer la couleur du joueur
	 *
	 * @return Color
	 */
	public Color getCouleur(){ return couleur;}
	
	
	/**
	 * Getter
	 * 
	 * Permet de r�cup�rer la couleur du Joueur
	 * sous format String
	 *
	 * @return String
	 */
	public String getCouleurString() { return stack.getCouleur();}
	
	
	/**
	 * Getter
	 * 
	 * Permet de r�cup�rer la liste des Repellers
	 *
	 * @return LinkedList<Repellere>
	 */
	public LinkedList<Repellere> getListeRep(){ return listeRepellereRecup;}
	
	
	/**
	 * 
	 * Permet de retirer un Repeller de la liste
	 *
	 * @param String couleur
	 * @return Repeller
	 */
	public Repellere popRep (String couleur){
		
		//On parcourt la liste
		//
		Iterator<Repellere> i = listeRepellereRecup.iterator();
		Repellere rep = null;
		
		while(i.hasNext()){
			
			rep = i.next();
			
			//on sort de la boucle lorsqu'on a trouve un repeller
			//
			if( rep.getCouleur().equals(couleur)) break;
			
			//On reinitialise rep pour recuperer seulement le repellere qui nous faut.
			//
			rep = null;
			
		}
		
		//on supprime le Repeller
		//
		if( rep != null){
			listeRepellereRecup.remove(rep);
			return rep;
		}
		else
			return null;
		
	}
	
	
	/**
	 * Permet d'ajouter un Repeller � la liste
	 *
	 * @param 
	 */
	public void pushRep (Repellere rep){
		listeRepellereRecup.add(rep);
	}
	 
	
}
