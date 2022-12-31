package Repello;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


// TODO: Auto-generated Javadoc
/**
 * Attention pour l'initialisation des images, on impose que le premier caractere du nom est le numero que l'image affiche
 * ex: pour l'image qui affiche le 1  1nom.jpg
 * 
 * @author Seb
 * 
 * @version V000
 * 		-Création de la class
 * 		-Affiche une grille de Label numéroté
 * 
 * @version V001 Affiche les nombres sur la grille
 * 		-Creation de la methode initPlateau
 *
 * @version V002 Affiche les images au lieu des nombres
 * 		-Creation de la methode initImage
 * 		-Methode getImage
 * 		-Modification de la méthode initPlateau
 * 
 * @version V003
 * 		Modification du constructeur
 * 			le contructeur prend en parametre un objet ConfigRepello
 * 
 * @version V004
 * 		Ajout d un getter:  getContenuGrillePlateau
 * 		Ajout du MouseListener EcouteurItemSouris
 * 
 * 
 * @version V010 
 * 			Passage en MVC 
 * 			Suppression de la méthode  getContenuGrillePlateau
 * 			Suppression de l'EcouteurItemSouris
 * 			Modification de la méthode initPlateau
 * 			Ajout d'un attribut listeCase
 * 			Ajout d'un guetter listeCase
 *
 *
 */
@SuppressWarnings("rawtypes")
public class Plateau extends JPanel implements Observer {
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le nb case largeur. */
	public static int NB_CASE_LARGEUR;
	
	/** Le nb case hauteur. */
	public static int NB_CASE_HAUTEUR;
	
	/** Le recup. */
	private HashMap recup;
	
	/** Le liste case. */
	private LinkedHashMap<Point, Case> listeCase;// < y, x, Case>
	
	/** Le liste image. */
	private LinkedHashMap< Integer, BufferedImage> listeImage;
	
	
	/**
	 * Constructeur de plateau.
	 *
	 * @param configRepello the config repello
	 * @throws Exception the exception
	 */
	public Plateau(HashMap configRepello) throws Exception{
		
		recup = (HashMap) configRepello.get("controleurRepello");
		
		NB_CASE_LARGEUR = (Integer) recup.get("nb_case_largeur_plateau");
		NB_CASE_HAUTEUR = (Integer) recup.get("nb_case_hauteur_plateau");
		
		if(NB_CASE_LARGEUR <1 || NB_CASE_HAUTEUR <1 ) throw new Exception();
		
		//la grille du plateau
		int [][] grillePlateau = (int[][]) recup.get("grille_plateau");
		
		if( grillePlateau == null || grillePlateau.length != NB_CASE_HAUTEUR 
				|| grillePlateau[0].length != NB_CASE_LARGEUR) 
			throw new Exception();
		
		initPlateau(grillePlateau);
		
	}
	

	
	/*
	 *													InitPlateau 
	 */
	/**
	 * Inits the plateau.
	 *
	 * @param grillePlateau the grille plateau
	 * @throws Exception the exception
	 */
	private void initPlateau(int [][] grillePlateau) throws Exception{
		
		if(grillePlateau == null) throw new Exception();
		
		//initialisation des images
		initImagePlateau();
		
		setLayout(new GridLayout(NB_CASE_LARGEUR, NB_CASE_HAUTEUR));
		
		//mise en place des images
		Case panCase;
		int cles;
		listeCase = new LinkedHashMap<Point, Case>();
		
		Color couleur;
		
		for(int y = 0; y < NB_CASE_HAUTEUR; y++){
			for(int x= 0; x <NB_CASE_LARGEUR; x++){
				
				cles = grillePlateau[y][x];
				
				if( ((x>2 && y == 3 ) || (x==3 && y > 2) || (x == 9 && y > 2) || (y == 9 && x >2))
						&& (x<10 && y < 10) )
					couleur = new Color(34,177,76);
				else if( (x ==1 && y ==1) || (x==1 && y ==11) ||(x==11 && y ==1) || (x==11 && y ==11))
					couleur = Color.gray;
				else if (x==6 && y==6)
					couleur = Color.yellow;
				else if ((x ==6 && y ==1) || (x==1 && y ==6) ||(x==6 && y ==11) || (x==11 && y ==6) )
					couleur = Color.blue;
				else 
					couleur = Color.white;
				
				panCase = new Case(getImage(cles),new Point(x,y),couleur, grillePlateau[y][x]);
				add(panCase);
				
				listeCase.put(  new Point(x, y) , panCase);
			}
		}
		

	}
	
	/*
	 * 													Méthode initImagePlateau
	 */
	/**
	 * Inits the image plateau.
	 *
	 * @throws Exception the exception
	 */
	private void initImagePlateau() throws Exception{
		
		File dossierImagePlateau = new File("_Config\\_Images\\Plateau");
		
		
		if(!dossierImagePlateau.isDirectory())throw new Exception();
		
		listeImage = new LinkedHashMap<Integer, BufferedImage>();
		BufferedImage image = null;
		char lettreNomFichier;
		
		for( File fichier :  dossierImagePlateau.listFiles()){
			
			if(fichier.isFile() && (fichier.getName().contains(".jpg") || fichier.getName().contains(".png")) ){
				
				lettreNomFichier = fichier.getName().charAt(0);
				
				if(caractEstNb(lettreNomFichier)){
					image = Fichier.chargementFichierImage(fichier.getAbsolutePath());
					
					if(image != null)
						listeImage.put(Integer.valueOf(lettreNomFichier) - 48, image);
				}
				
			}
			
		}
	}
	
	
	/*
	 * 								Méthode caractEstNb
	 */
	
	/**
	 * Caract est nb.
	 *
	 * @param lettre the lettre
	 * @return true, si ok
	 */
	private boolean caractEstNb (char lettre){
		return ((int)lettre >= (int)'0')  && ((int) lettre <= (int)'9');
	}
	
	/*
	 * 								Methode getImage
	 */
	
	/**
	 * Getter de  image.
	 *
	 * @param nb the nb
	 * @return le image
	 */
	public BufferedImage getImage(int nb){
		
		if(!listeImage.containsKey(nb)) 
			return null;
		
		return listeImage.get(nb);
	}
	
	/*
	 * 								Accesseur
	 */

	/**
	 * Getter de  liste case.
	 *
	 * @return le liste case
	 */
	public LinkedHashMap<Point, Case> getListeCase(){ return listeCase;}
	
	
	/**
	 * Getter de  case.
	 *
	 * @param p the p
	 * @return le case
	 */
	public Case getCase(Point p){
		
		if( ! appartienPlateau(p))
			return null;
		
		Iterator i = listeCase.keySet().iterator();
		Point point;
		
		while(i.hasNext()){
			
			point = (Point) i.next();
			
			if( point.equals(p))
				return listeCase.get(point);
		}
		
		return null;
		
	}
	
	//											Uptade De l'affichage

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		
		if( !(arg instanceof HashMap) )return;
		
		HashMap action = (HashMap) arg;
		
		if( action.containsKey("selectCase")){
			HashMap contenu = (HashMap) action.get("selectCase");
			selectCase(contenu);
		}
		
		if( action.containsKey("deselectCase")){
			HashMap contenu = (HashMap) action.get("deselectCase");
			deselectCase(contenu);
		}
		if( action.containsKey("placerPion")){
			HashMap contenu = (HashMap) action.get("placerPion");
			placerPion(contenu);
		}
		
		if( action.containsKey("deplacerPion") ){
			HashMap contenu = (HashMap) action.get("deplacerPion");
			deplacerPion(contenu);
		}
		
		if( action.containsKey("supprPion") ){
			Point p = (Point) action.get("supprPion");
			
			if( p==null) return;
			
			Case casePion = getCase(p);
			casePion.setImagePion(null);
		}
		
		this.repaint();
	}
	
	/**
	 * Select case.
	 *
	 * @param contenu the contenu
	 */
	private void selectCase ( HashMap contenu){
		
		if( contenu  == null) return;
		
		Point positionCase = null;
		Color couleur = new Color((Integer)recup.get("color_r"),(Integer)recup.get("color_g"),(Integer)recup.get("color_b"));
		
		if( contenu.containsKey("positionCase")) 
			positionCase = (Point) contenu.get("positionCase");
		
		if( couleur == null || positionCase == null ) return;
		
		
		Case caseRep = getCase(positionCase);
		caseRep.setBackground(couleur);
		
	}
	
	/**
	 * Deselect case.
	 *
	 * @param contenu the contenu
	 */
	private void deselectCase (HashMap contenu){
		
		if( contenu  == null) return;
		
		Point positionCase = null;
		
		if( contenu.containsKey("positionCase")) 
			positionCase = (Point) contenu.get("positionCase");
		
		if(positionCase == null) return; 
		
		Case caseRep = getCase(positionCase);
		caseRep.setBackground( caseRep.getFondColorOriginale());
		
	}
	

	
	/**
 * Placer pion.
 *
 * @param contenu the contenu
 */
private void placerPion (HashMap  contenu){
		
		if( contenu  == null) return;
		
		Point positionCase = null;
		BufferedImage image = null;
		
		if( contenu.containsKey("positionCase")) 
			positionCase = (Point) contenu.get("positionCase");
		
		if( contenu.containsKey("image"))
			image = (BufferedImage) contenu.get("image");
		
		if(positionCase == null || image == null) return; 
		
		Case caseRep = getCase(positionCase);
		caseRep.setImagePion(image);
		
	}
	
	/**
 * Deplacer pion.
 *
 * @param contenu the contenu
 */
private void deplacerPion (HashMap  contenu){
		
		if( contenu  == null) return;
		
		Point positionCaseSrc = null;
		Point positionCaseDest = null;
		
		if( contenu.containsKey("positionCaseSrc")) 
			positionCaseSrc = (Point) contenu.get("positionCaseSrc");
		
		if( contenu.containsKey("positionCaseDest"))
			positionCaseDest = (Point) contenu.get("positionCaseDest");
		
		if(positionCaseSrc == null || positionCaseDest == null) return; 
		
		Case caseSrc = getCase(positionCaseSrc);
		Case caseDest = getCase(positionCaseDest);
		
		BufferedImage image = caseSrc.getImagePion();
		caseSrc.setImagePion(null);
		caseDest.setImagePion(image);
		
	}
	
	/**
 * Affiche gagnant.
 *
 * @param nomJoueur the nom joueur
 */
public void afficheGagnant(String nomJoueur){
		if( nomJoueur == null) return;
		
		JOptionPane.showMessageDialog(this, "Félicitation le joueur "+ nomJoueur + " a gagné !!!!" );
	}
	
	/**
 * Appartien plateau.
 *
 * @param p the p
 * @return true, si ok
 */
public boolean appartienPlateau(Point p){
		
		if( p.x() <0 || p.y() <0 || p.x()>=NB_CASE_LARGEUR || p.y()>=NB_CASE_HAUTEUR )
			return false;
		else
			return true;
		
	}
	
	
	public void deselectTout(){
		
		
		for( int y = 0; y < NB_CASE_HAUTEUR; y++){
			
			for( int x = 0; x < NB_CASE_HAUTEUR; x++){
				
				Case c = getCase(new Point(x,y));
				c.setBackground(c.getFondColorOriginale());
			}
			
		}
		
	}
	
}
