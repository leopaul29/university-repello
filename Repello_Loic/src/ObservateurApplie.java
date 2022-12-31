


import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Demonstrateur.DemonstrateurRepello;
import Repello.*;

// TODO: Auto-generated Javadoc
/**
 * La  Class ObservateurApplie.
 */
@SuppressWarnings("rawtypes")
public class ObservateurApplie implements Observer{

	/** Le controleur repel. */
	private ControleurRepello controleurRepel;
	
	/** Le controleur stat j. */
	private ControleurStatJoueur controleurStatJ;
	
	/** Le controleur demonstrateur. */
	private DemonstrateurRepello controleurDemonstrateur;
	
	/** Le fenetre temps. */
	private JFrame fenetreTemps;
	
	/** Le controleur temps. */
	private ControleurTempsG controleurTemps;
	
	/** Le config repello. */
	private HashMap configRepello;
	
	/** Le recup. */
	private HashMap recup;
	 
	//Drag and drop
	/** Le stack drag. */
	Stack stackDrag = null;
	
	/** Le est en drag. */
	boolean estEnDrag = false;


	/**
	 * Constructeur de observateur applie.
	 *
	 * @param configSablier the config sablier
	 * @param configRepello the config repello
	 */
	public ObservateurApplie(HashMap configSablier, HashMap configRepello) {
		this.configRepello = configRepello;
		recup = (HashMap) configRepello.get("observateurAppli");
	//	HashMap configDemonstrateur = 
		try {
			LinkedList<Joueur> listeJoueur = initJoueur();
			controleurRepel = new ControleurRepello(listeJoueur, configRepello);
			controleurRepel.addObserver(this);
			controleurRepel.getMoteurRepello().addObserver(this);

			controleurStatJ = new ControleurStatJoueur(listeJoueur, configRepello);
			controleurStatJ.addObserver(this);

			controleurDemonstrateur = new DemonstrateurRepello(listeJoueur,null);
			controleurDemonstrateur.addObserver(this);
			
			
			JFrame fPlateau = controleurRepel.getFenetreRepello();
			
			//Fenetre du Sablier
			//
			fenetreTemps = new JFrame("Sablier");
			fenetreTemps.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			fenetreTemps.setLocation( fPlateau .getX() + fPlateau .getWidth() + 5, 0); // decalage de 5 pixel
			fenetreTemps.setSize(300, 150);
			
			//Init du Sablier
			//
			HashMap configModeleSablier = (HashMap)configSablier.get("ParamTempG");
			HashMap configVueSablier = (HashMap)configSablier.get("VueSablier");
			
			controleurTemps = new ControleurTempsG(fenetreTemps, configModeleSablier, configVueSablier);
			
			controleurTemps.demarrer(60);
			controleurTemps.setButee("00 : 00 : 00");
			
			//On position la fenetre du demonstrateur
			//
			JFrame fDemon = controleurDemonstrateur.getFenetre();
			fDemon.setLocation(fenetreTemps.getX(), fenetreTemps.getHeight());
			

		}
		catch (Exception e) {	e.printStackTrace();}
		catch (Throwable e) {	e.printStackTrace();} 

		fenetreTemps.setVisible(true);


	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {

		if( arg0 instanceof ControleurRepello){

			if( !(arg1 instanceof HashMap) )return;

			HashMap donne = (HashMap)arg1;
			updateDeRepello(donne);	

		}
		else if ( arg0 instanceof ControleurStatJoueur){

			if( !(arg1 instanceof HashMap) )return;

			HashMap donne = (HashMap)arg1;
			updateDeStatJoueur(donne);	
		}
		else if (arg0 instanceof DemonstrateurRepello){

			if( !(arg1 instanceof HashMap) )return;

			HashMap donne = (HashMap)arg1;
			updateDeDemonstrateur(donne);	

		}

	}


	/**
	 * Inits the joueur.
	 *
	 * @return the linked list
	 */
	private LinkedList<Joueur> initJoueur(){

		LinkedList<Joueur> listeJoueur = new LinkedList<Joueur>();

		BufferedImage imageBleu = null;
		BufferedImage imageRouge = null;
		BufferedImage imageVert = null;
		BufferedImage imageJaune = null;
		try {
			imageBleu = ImageIO.read(new File((String)recup.get("image_stack_bleu")));
			imageRouge = ImageIO.read(new File((String)recup.get("image_stack_rouge")));
			imageVert = ImageIO.read(new File((String)recup.get("image_stack_vert")));
			imageJaune = ImageIO.read(new File((String)recup.get("image_stack_jaune")));
		} catch (IOException e) {e.printStackTrace();}
		listeJoueur.add(new Joueur((String) recup.get("nom_j1"), new Stack(imageBleu, new Point(), 
										(String) recup.get("s_couleur_j1"),configRepello), (Color) recup.get("couleur_j1")));
		listeJoueur.add(new Joueur((String) recup.get("nom_j2"), new Stack(imageRouge, new Point(),
										(String) recup.get("s_couleur_j2"),configRepello), (Color) recup.get("couleur_j2")));
		listeJoueur.add(new Joueur((String) recup.get("nom_j3"), new Stack(imageVert, new Point(), 
										(String) recup.get("s_couleur_j3"),configRepello), (Color) recup.get("couleur_j3")));
		listeJoueur.add(new Joueur((String) recup.get("nom_j4"), new Stack(imageJaune, new Point(),
										(String) recup.get("s_couleur_j4"),configRepello), (Color) recup.get("couleur_j4")));

		return listeJoueur;

	}


	/**
	 * Update de demonstrateur.
	 *
	 * @param action the action
	 */
	@SuppressWarnings("unchecked" )
	public void updateDeDemonstrateur(HashMap action){

		HashMap contenu = null;
		HashMap contenu2 = null;

		if( action.containsKey("placer_stack")){
			contenu = (HashMap<String, Point>) action.get("placer_stack");
			contenu2 = (HashMap<String,String>) action.get("placer_stack");

			if( contenu == null) return;

			Point dest = (Point) contenu.get("nouveau_point");
			String couleur = (String) contenu2.get("couleur");

			controleurRepel.getMoteurRepello().placer((int)dest.x(), (int) dest.y(), couleur);
			
			afficherPieceAExpulser();

		}

		if( action.containsKey("placer_repelleur")){
			contenu = (HashMap<String, Point>) action.get("placer_repelleur");
			contenu2 = (HashMap<String,String>) action.get("placer_repelleur");

			if( contenu == null) return;

			Point dest = (Point) contenu.get("point");
			String couleur = (String) contenu2.get("couleur");
			
			System.out.println(couleur);

			controleurRepel.getMoteurRepello().placer( (int) dest.x(), (int)dest.y(), couleur);
			
			afficherPieceAExpulser();

		}

		if( action.containsKey("donner")){
			contenu = (HashMap<String, Point>) action.get("donner");

			if( contenu == null) return;

			String pion = (String) contenu.get("pion");
			String joueur = (String) contenu.get("joueur");

			
			//On donne le Repeller
			//
			controleurRepel.getMoteurRepello().donnerUnRepeller(joueur, pion);
			
			//On met a jour les fenetres statJoueurs
			Joueur j = controleurRepel.getMoteurRepello().getJoueur(joueur);
			controleurStatJ.getMoteurStatJoueur().updateJoueur(j);
			

		}

		if( action.containsKey("deplacer")){
			
			//On recup les donnes
			//
			contenu = (HashMap<String, Point>) action.get("deplacer");

			if( contenu == null) return;
			
			Point ancienPoint = (Point) contenu.get("ancien_point");
			Point newPoint = (Point) contenu.get("nouveau_point");
			
			//On recupere le moteur du plateau
			//
			Plateau plateau = controleurRepel.getVuePlateau();
			plateau.deselectTout();
			
			//On deplace le pion
			Case src = plateau.getCase(ancienPoint);
			Case dest = plateau.getCase(newPoint);
			
			controleurRepel.deplacerPion(src, dest);

			afficherPieceAExpulser();
		}

		if( action.containsKey("gagne")){
			contenu = (HashMap<String, Object>) action.get("gagne");
		
			
		}


		if( action.containsKey("voler")){
			contenu = (HashMap<String, Object>) action.get("voler");

			if( contenu == null) return;
			
			String voleur = (String) contenu.get("voleur");
			String vole = (String) contenu.get("vole");
			String pion = (String) contenu.get("pion");

			//On vole
			//
			MoteurRepello moteurRep = controleurRepel.getMoteurRepello();
			moteurRep.voler(voleur, vole, pion);
			
			//On met a jour les fenetres
			//
			MoteurStatJoueur moteurStat = controleurStatJ.getMoteurStatJoueur();
			Joueur jVoleur = moteurRep.getJoueur(voleur);
			Joueur jVole = moteurRep.getJoueur(vole);
			
			moteurStat.updateJoueur(jVoleur);
			moteurStat.updateJoueur(jVole);
			
			
			
		}
		
		if ( action.containsKey("updateSablier")){
			
			try {
				controleurTemps.suspendre();
				controleurTemps.reprendre(60);	
			} catch (Throwable e) {e.printStackTrace();}
			
		}
		
		if ( action.containsKey("supprPion")){
			contenu = (HashMap<String, Object>) action.get("supprPion");

			if( contenu == null) return;
			
			Point p = null;
			if( contenu.containsKey("position") ) p = (Point)contenu.get("position");
			
			if(p == null) return;
			
			MoteurRepello moteur= controleurRepel.getMoteurRepello();
			PionRepello pion = moteur.getPion( (int)p.x(), (int)p.y());
			
			moteur.supprPion(pion);
			
			afficherPieceAExpulser();
			
		}
		
		
		if ( action.containsKey("expulser")){
			
			contenu = (HashMap<String, Object>) action.get("expulser");

			if( contenu == null) return;
			
			Point expulse = null;
			Point expulseur =null;
			
			if( contenu.containsKey("expulseur") ) expulseur = (Point)contenu.get("expulseur");
			if( contenu.containsKey("expulse") ) expulse = (Point)contenu.get("expulse");
			
			if( expulse ==null ||  expulseur == null) return;
			
			controleurRepel.getMoteurRepello().expulser( (int)expulseur.x(), (int)expulseur.y()
					, (int)expulse.x(), (int)expulse.y());
			
			afficherPieceAExpulser();
		}
		
		if ( action.containsKey("gagnant"))
			controleurRepel.afficherGagnant();

	}
	
	

	/**
	 * Update de stat joueur.
	 *
	 * @param action the action
	 */
	@SuppressWarnings("unchecked")
	public void updateDeStatJoueur(HashMap action){

		if( action.containsKey("voler")){
			HashMap contenu = (HashMap<String, Object>) action.get("voler");

			if( contenu == null) return;
			
			String voleur = (String) contenu.get("voleur");
			String vole = (String) contenu.get("vole");
			String pion = (String) contenu.get("pion");

			//On vole
			//
			MoteurRepello moteurRep = controleurRepel.getMoteurRepello();
			moteurRep.voler(voleur, vole, pion);
			
			//On met a jour les fenetres
			//
			MoteurStatJoueur moteurStat = controleurStatJ.getMoteurStatJoueur();
			Joueur jVoleur = moteurRep.getJoueur(voleur);
			Joueur jVole = moteurRep.getJoueur(vole);
			
			moteurStat.updateJoueur(jVoleur);
			moteurStat.updateJoueur(jVole);

		}
		
		if( action.containsKey("drag")){
			
			if( estEnDrag){
				
				stackDrag = null;
				estEnDrag = false;
				
				controleurStatJ.changeImageCurseur(Cursor.DEFAULT_CURSOR);
				controleurRepel.changeImageCurseur(Cursor.DEFAULT_CURSOR);
				
				return;
				
			}
			
			Stack contenu = (Stack) action.get("drag");
			if( contenu == null) return;
			
			stackDrag = contenu;
			if( stackDrag == null) return;
			
			estEnDrag = true;
			
			controleurStatJ.changeImageCurseur(stackDrag.getImage());
			controleurRepel.changeImageCurseur(stackDrag.getImage());
		}

	}

	/**
	 * Update de repello.
	 *
	 * @param action the action
	 */
	public void updateDeRepello(HashMap action){
		
		if(action.containsKey("updateListeDemon")){
			
			Point p = (Point) action.get("updateListeDemon");
			
			if( p == null) return;
			
			controleurDemonstrateur.updateListes((int)p.x(), (int)p.y());
			
		}
		if( action.containsKey("updateJoueur") ){
			
			Joueur j = (Joueur) action.get("updateJoueur");
			
			if( j == null) return;
			
			controleurStatJ.getMoteurStatJoueur().updateJoueur(j);
		}
		
		
		if( action.containsKey("drop") ){
			
			if(! estEnDrag) return;
			
			Point p = (Point) action.get("drop");
			if( p == null) return;
			
			MoteurRepello moteur = controleurRepel.getMoteurRepello();
			
			moteur.placer((int)p.x(),
					(int)p.y(), stackDrag.getCouleur());

			
			afficherPieceAExpulser();
			
			stackDrag = null;
			estEnDrag = false;
			
			controleurStatJ.changeImageCurseur(Cursor.DEFAULT_CURSOR);
			controleurRepel.changeImageCurseur(Cursor.DEFAULT_CURSOR);
			
		}
	
	}
	
	
	private void afficherPieceAExpulser (){
		
		Plateau vuePlateau = controleurRepel.getVuePlateau();
		vuePlateau.deselectTout();
		
		MoteurRepello moteur = controleurRepel.getMoteurRepello();
		LinkedList listePieceAExpulser = moteur.pieceAExpulser();
		controleurRepel.selectPieceAExpulser(listePieceAExpulser);
	}


}
