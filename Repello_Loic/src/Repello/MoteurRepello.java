package Repello;

/**
 * 
 * @author Seb
 * 
 * @version V0_1_0 Création du MoteurRepello
 * 				-Ajout des fonctions _ deplacerSouris
 * 									 _selectCase
 * 									 _deselectCase
 * 									 _getCase
 * 
 * @version V002
 * 		Ajout de l'attibue listeRepellere
 * 		Ajout de la méthode creerRepellere
 * 
 * @version V003
 * 		Ajout de la méthode supprRepellere
 * 
 * 
 */

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;


// TODO: Auto-generated Javadoc
/**
 * La  Class MoteurRepello.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MoteurRepello extends Observable implements _InterfaceModeleRepello{
	
	
	/** La liste des joueurs */
	private LinkedList<Joueur> listeJoueur;
	
	/** La liste des pions */
	private LinkedList<PionRepello> listePion;
	
	/** La config du modele*/
	private HashMap configModele;
	
	/** Le config repello. */
	private HashMap configRepello;
	
	/** Le plateau: null si la case est vide, si non elle contient la couleur du pion*/
	private String [][] plateau;
	
	/** Liste des sons*/
	private HashMap listeSon;
	
	
	/**
	 * Constructeur
	 *
	 * @param listeJoueurs the liste joueurs
	 * @param configRepello the config repello
	 */
	public MoteurRepello( LinkedList<Joueur> listeJoueurs, HashMap configRepello) {
		
		//On garde en memoire les configs
		//
		this.configRepello = configRepello;
		configModele = (HashMap) configRepello.get("moteur");
		
		//On recup la liste des joueurs et des pions
		//
		this.listeJoueur = listeJoueurs;
		listePion = new LinkedList<PionRepello>();
		
		//on initialise notre plateau
		//
		int hauteur = (Integer) configModele.get("nb_case_hauteur_plateau");
		int largeur = (Integer) configModele.get("nb_case_largeur_plateau");
		plateau = new String [hauteur][largeur];
		
		//On init la liste des sons
		//
		listeSon = new HashMap();
		listeSon.put("expulser", new Son((String) configModele.get("son_expulser")) );
		listeSon.put("placer", new Son((String) configModele.get("son_placer")) );
		listeSon.put("victoire", new Son((String) configModele.get("son_victoire")) );

	}
	
	/**
	 * Permet de jouer le son de victoire
	 * 
	 */
	public void jouerSonVictoire(){
		 ( (Son)listeSon.get("victoire")).jouerUnefois();
	}
	
	
	/**
	 * Permet de selectionner des cases depuis le modele
	 *
	 * @param Point p
	 */
	public void selectCase (Point p){
		
		//on crée notre Hashmap de configuration
		//
		HashMap donne = new HashMap();
		donne.put("positionCase", p);
		
		HashMap action = new HashMap();
		action.put("selectCase", donne);
		
		//on met à jour la vue
		//
		setChanged();
		notifyObservers(action);
	}
	

	/**
	 * Permet de deselectionner les cases a partire du modele
	 *
	 * @param Point p
	 */
	public void deselectCase (Point p){

		//On construit notre hashmap de config
		//
		HashMap donne = new HashMap();
		donne.put("positionCase", p);
		
		HashMap action = new HashMap();
		action.put("deselectCase", donne);
		
		//on met a jour la vue
		//
		setChanged();
		notifyObservers(action);
	}

	
	/**
	 * Getter
	 * 
	 * Permet de récupérer un joueur par sa couleur.
	 *
	 * @param String couleur
	 * @return 
	 */
	public Joueur getJoueur (String couleur){
		
		//On parcourt la liste des joueurs
		//
		Iterator<Joueur> i = listeJoueur.iterator();
		Joueur j;
		
		while(i.hasNext()){
			
			j = i.next();
			
			//si on trouve le joueur on le renvoit
			//
			if(j.getCouleurString().equals(couleur))
				return j;
			
		}
		
		return null;
	}

	/**
	 * 
	 * Cette methode permet de placer un pion
	 * 
	 */
	@Override
	public boolean placer(int ligne, int colonne, String couleur) {
		
		//On teste les parametres
		//
		if ( couleur == null || couleur.isEmpty()  )
			return false;
		
		//on teste la validite de la case
		//
		if( !caseEstVide(ligne, colonne) ) return false;
		
		//On recup le pion
		//
		PionRepello pion = null;
		
		if(Stack.estStack(couleur)){
			
			Joueur j = getJoueur(couleur);
			pion = j.getStack();
			
			if( estSurPlateau(couleur))
				return false;
		}
		else if( Repellere.estRepeller(couleur))
			pion = creeRepeller(ligne, colonne, couleur);
		
		if( pion == null) return false;
		
		// on donne la position du pion
		//
		pion.x(ligne);
		pion.y(colonne);
		
		//on rajoute le pion
		//
		listePion.add(pion);
		plateau[colonne][ligne] = pion.getCouleur();
		
		//on creer le hashmap de config
		//
		HashMap donne = new HashMap();
		donne.put("positionCase", new Point(ligne, colonne));
		donne.put("image", pion.getImage());
		
		HashMap action = new HashMap();
		action.put("placerPion", donne);
		
		
		//on met a jour la vue
		//
		setChanged();
		notifyObservers(action);
		
		//On joue le son placer
		//
		((Son)listeSon.get("placer")).jouerUnefois();
		
		return true;
	}
	
	
	
	/**
	 * Cree repeller.
	 *
	 *Permet de creer un Repeller
	 *
	 * @param int x l'abscisse
	 * @param int y l'ordonne
	 * @param String couleur
	 * @return retourne le Repellere
	 */
	@SuppressWarnings("unused")
	private Repellere creeRepeller (int x, int y, String couleur ){
		
		String chemin =null;
		File fichier = null;
		
		if( couleur.equals("noir"))
			chemin = (String) configModele.get("image_repelleur_noir");

		else if( couleur.equals("argent"))
			chemin = (String) configModele.get("image_repelleur_gris");

		else if( couleur.equals("or"))
			chemin = (String) configModele.get("image_repelleur_or");

		return new Repellere(new Point(x, y), chemin, couleur, configRepello);
		
	}
	
	


	/**
	 * 
	 * Permet de deplacer un pion
	 * 
	 */
	@Override
	public boolean deplacer(int ligneOrig, int colonneOrig, int ligneDest,
			int colonneDest) {
		
		//On teste la validite des parametres
		//
		if ( !pointAppartienPlateau(ligneOrig, colonneOrig)
				|| !pointAppartienPlateau( ligneDest, colonneDest))
			return false;
		
		
		//On verifie les cases
		//
		if( caseEstVide(ligneOrig, colonneOrig)) return false;
		if( ! caseEstVide(ligneDest, colonneDest)) return false;
		
		//on echange les pions
		//
		PionRepello pion = getPion(ligneOrig, colonneOrig);
		plateau[colonneOrig][ligneOrig] = null;
		plateau[colonneDest][ligneDest] = pion.getCouleur();
		pion.x(ligneDest);
		pion.y(colonneDest);
		
		//Selection/Deselection
		//
		selectCase(new Point(ligneDest, colonneDest));
		deselectCase(new Point(ligneOrig, colonneOrig));
		
		//creer un hasmap de config pour la vue
		//
		HashMap donne = new HashMap();
		donne.put("positionCaseSrc", new Point(ligneOrig, colonneOrig));
		donne.put("positionCaseDest", new Point(ligneDest, colonneDest));
		
		HashMap action = new HashMap();
		action.put("deplacerPion", donne);
		
		//on met a jour la vue
		//
		setChanged();
		notifyObservers(action);
		
		//Le cas d un stack
		//
		if( Stack.estStack(pion.getCouleur())){
			
			Stack stack = (Stack)pion;
			
			if(stack.getListRepeller().size() > 0){
				Repellere rep = stack.pop();
				placer(ligneOrig, colonneOrig, rep.getCouleur());
			}
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * Permet d'expulser un pion
	 * 
	 */
	@Override
	public boolean expulser(int ligneExpulseur, int colonneExpulseur,
			int ligneExpulse, int colonneExpulse) {
		
		if( !pointAppartienPlateau(ligneExpulse, colonneExpulse) 
				|| !pointAppartienPlateau(ligneExpulseur, colonneExpulseur) ) 
			return false;
		
		int horizontale = ligneExpulse - ligneExpulseur;
		int verticale = colonneExpulse  - colonneExpulseur;

		
		Point pNewPoint = new Point(  ligneExpulse+horizontale, colonneExpulse+verticale);
		
		if ( pointAppartienPlateau( (int)pNewPoint.x(), (int)pNewPoint.y()) ){
			//Sort pas du plateau
			//
			deplacer( ligneExpulse, colonneExpulse, (int)pNewPoint.x(), (int)pNewPoint.y());
			
			((Son)listeSon.get("expulser")).jouerUnefois();
			
			return false;
		}
		else{
			//Expulser du plateau
			//
			PionRepello pion = getPion(ligneExpulse, colonneExpulse);
			
			plateau[pion.y()][pion.x()] = null;
			
			listePion.remove(pion);
			
			//On met a jour la vue
			//
			HashMap action = new HashMap();
			action.put("supprPion", new Point(pion.x(), pion.y()) );
			
			setChanged();
			notifyObservers(action);
			
			((Son)listeSon.get("expulser")).jouerUnefois();
			return true;
		}
		
		
	}
	

	/* (non-Javadoc)
	 * @see Repello._InterfaceModeleRepello#voler(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean voler(String couleurDuVoleur, String couleurVole, String couleurRepeller) {
		
		Joueur voleur = getJoueur(couleurDuVoleur);
		Joueur vole = getJoueur(couleurVole);
		
		if( voleur == null || vole == null) return false;
		
		if(vole.nbRepell(couleurRepeller)  == 0  ) return false;
		
		Repellere rep = vole.popRep(couleurRepeller);
		voleur.pushRep(rep);
	
		return true;
	}
	
	/**
	 * Donner un repeller.
	 *
	 * @param joueur the joueur
	 * @param pion the pion
	 * @return true, si ok
	 */
	public boolean donnerUnRepeller (String joueur, String pion){
		
		if( joueur == null || pion == null || joueur.isEmpty() || pion.isEmpty())
			return false;
		
		
		Joueur j = getJoueur(joueur);
		
		if( j == null) return false;
		
		
		if( ! Repellere.estRepeller(pion)) return false;
		
		Repellere rep = creeRepeller((Integer)configModele.get("creer_x"),(Integer)configModele.get("creer_y"), pion);
		j.pushRep(rep);
		
		return true;
		
	}
	
	
	/**
	 * Suppr pion.
	 *
	 * @param pion the pion
	 */
	public void supprPion (PionRepello pion){
		
		Iterator i = listePion.iterator();
		PionRepello p = null;
		
		while( i.hasNext()){
			
			p = (PionRepello) i.next();
			
			if( p.equals(pion))
				break;	
		}
		
		if( p != null && p.equals(pion) ){
			listePion.remove(p);
			plateau[p.y()][p.x()] = null; 
		
			HashMap action = new HashMap();
			action.put("supprPion", new Point(p.x(), p.y()));
			
			setChanged();
			notifyObservers(action);
		}
	}

	
	/**
	 * Case est vide.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, si ok
	 */
	public boolean caseEstVide( int x, int y){
		
		if( ! pointAppartienPlateau(x, y) ) return false;
		return plateau[y][x] == null;
		
	}


	/* (non-Javadoc)
	 * @see Repello._InterfaceModeleRepello#getGagnant()
	 */
	@Override
	public String getGagnant() {
		
		Iterator<Joueur> i = listeJoueur.iterator();
		Joueur jGagnant = null;
		Joueur j;
		
		while(i.hasNext()){
			
			j = i.next();
			
			if(jGagnant == null) 
				jGagnant = j;
			else if( jGagnant.calculePoint() < j.calculePoint())
				jGagnant = j;
			
		}
		
		
		return jGagnant.getCouleurString();
	}
	
	
	/**
	 * Getter de  pion.
	 *
	 * @param x the x
	 * @param y the y
	 * @return le pion
	 */
	public PionRepello getPion( int x, int y){
		
		Iterator i = listePion.iterator();
		PionRepello pion;
		
		
		while( i.hasNext()){
			
			pion = (PionRepello) i.next();
			
			if( pion.x() == x && pion.y() == y)
				return pion;
		}
		
		return null;
	}
	
	/**
	 * Point appartien plateau.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, si ok
	 */
	public boolean pointAppartienPlateau( int x, int y){
		
		if( x < 0 || y < 0 || y>= plateau.length || x >= plateau[0].length )
			return false;
		else
			return true;
		
	}
	
	
	/**
	 * Est sur plateau.
	 *
	 * @param couleur the couleur
	 * @return true, si ok
	 */
	public boolean estSurPlateau (String couleur){
		
		if( couleur == null)return false;
		
		for( int y = 0; y < plateau.length; y++){
			
			for( int x = 0; x < plateau.length; x++){
				
				if( plateau[y][x] != null &&couleur.equals(plateau[y][x]))
					return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Piece a expulser.
	 *
	 * @return the linked list
	 */
	public LinkedList pieceAExpulser(){
		
		LinkedList liste = new LinkedList();
		
		Iterator i = listePion.iterator();
		PionRepello pion =null;
		
		while( i.hasNext()){
			
			pion = (PionRepello) i.next();
			
			if( pieceEstExpulsable(pion) && !liste.contains(pion))
				liste.add(pion);
			
		}
		return liste;
	}
	
	
	/**
	 * Piece est expulsable.
	 *
	 * @param pion the pion
	 * @return true, si ok
	 */
	public boolean pieceEstExpulsable (PionRepello pion){

		if( pion == null)return false;

		Point coordonnePion = new Point(pion.x(), pion.y());
		Point p = new Point(pion.x()-1, pion.y()-1);
		
		
		for( int y = (int)p.y(); y < p.y()+3; y++){
			
			for( int x = (int)p.x(); x < p.x()+3; x++){
				
				Point point = new Point(x,y);
				
				if ( pointAppartienPlateau((int)point.x(), (int)point.y()) ){
					
					if (!point.equals(coordonnePion)){
						
						if (! caseEstVide(x, y)){
							return true;
						}
					}
					
				}
			
			}
			
		}
		
		return false;
	}
	
	public LinkedList recupLesPionsAutour (PionRepello pion){
		
		if( pion == null)return null;
		
		LinkedList result = new LinkedList();

		Point coordonnePion = new Point(pion.x(), pion.y());
		Point p = new Point(pion.x()-1, pion.y()-1);
		
		
		for( int y = (int)p.y(); y < p.y()+3; y++){
			
			for( int x = (int)p.x(); x < p.x()+3; x++){
				
				Point point = new Point(x,y);
				
				if ( pointAppartienPlateau((int)point.x(), (int)point.y()) ){
					
					if (!point.equals(coordonnePion)){
						
						if (! caseEstVide(x, y)){
							result.add(point);
						}
					}
					
				}
			
			}
		}
			
		
		return result;
	}

}
