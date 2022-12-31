package Repello;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * La  Class MoteurStatJoueur.
 */
public class MoteurStatJoueur extends Observable {
	
	
	/** Le liste joueur. */
	private LinkedList<Joueur> listeJoueur;
	
	/** Le recup. */
	@SuppressWarnings("rawtypes")
	private HashMap recup;
	
	
	/**
	 * Constructeur de moteur stat joueur.
	 *
	 * @param listeJoueur the liste joueur
	 * @param configRepello the config repello
	 */
	@SuppressWarnings("rawtypes")
	public MoteurStatJoueur(LinkedList<Joueur> listeJoueur, HashMap configRepello) {
		
		this.listeJoueur = listeJoueur;
		recup = (HashMap)  configRepello.get("moteur_stat");
	}
	
	
	/**
	 * Update joueur.
	 *
	 * @param src the src
	 */
	public void updateJoueur(Joueur src){
		
		HashMap<String, HashMap<String, Integer>> update = new HashMap<String, HashMap<String,Integer>>();
		
		update.put(src.getNom(), joueurToHashMap(src));
		
		setChanged();
		notifyObservers(update);
	}
	
	/**
	 * Getter de  joueur.
	 *
	 * @param nom the nom
	 * @return le joueur
	 */
	public Joueur getJoueur (String nom){
		
		Iterator<Joueur> i = listeJoueur.iterator();
		Joueur j;
		
		while(i.hasNext()){
			j = i.next();
			
			if( j.getNom().equals(nom))
				return j;
		}
		
		return null;
	}
	
	
	/**
	 * Joueur to hash map.
	 *
	 * @param j the j
	 * @return the hash map
	 */
	private HashMap<String, Integer> joueurToHashMap(Joueur j){
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		
		int nbRepNoir = j.nbRepell((String) recup.get("pion_noir"));
		int nbRepArgent = j.nbRepell((String) recup.get("pion_argent"));
		int nbRepOr = j.nbRepell((String) recup.get("pion_or"));
		int nbRepDeStack = j.getStack().getListRepeller().size();
		int point = j.calculePoint();
		
		result.put("RepNoir", nbRepNoir);
		result.put("RepArgent", nbRepArgent);
		result.put("RepOr", nbRepOr);
		result.put("RepStack", nbRepDeStack);
		result.put("point", point);
		
		return result;
	}
	

}
