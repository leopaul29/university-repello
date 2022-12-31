package Repello;

/**
 * 
 *  @author Seb
 *  
 *  @V100 Transformation de la classe EcouteurItemSouris en ControleurRepello
 *  
 * 	@V200 
 * 		Ajout de la méthode calculePositionCase
 * 		Modification de la méthode mouseReleased qui permet d'ajouter un repeller sur le plateau
 * 		lors d'un clique droit.
 * 
 *  @V300
 *  	Implementation du keyListener
 *  	Modification de la méthode keyReleased qui permet du supprimer un repeller sur le plateau
 *  	lorsque on appuie sur la touche "echap" ou la touche effacer.
 *  		
 *  	
 *  
 */


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * La  Class ControleurRepello.
 */
@SuppressWarnings("rawtypes")
public class ControleurRepello extends Observable implements MouseListener, MouseMotionListener, KeyListener {

	/** La fenêtre qui contient le plateau */
	private JFrame fenPrincipale;

	/** La vue du Plateau */
	private Plateau vuePlateau;

	/** Le moteur de Repello */
	private MoteurRepello moteurRepello;

	/** La config */
	private HashMap config;

	// Les objets qu on a besoin de garder en mémoire pour accomplir le drag.
	/** Lieu où la case a commencé*/
	private Case caseDrag;

	/** La case sous la sourie. */
	private Case caseSousSourie;

	/** Si le mode drag est activé */
	private boolean estEnDrag = false;

	private Color couleurPreceCase;


	/**
	 * Constructeur de controleur repello.
	 *
	 * @param LinkedList listeJoueur La liste des joueurs
	 * @param Hashmap configRepello La config de repello
	 * @throws Exception the exception
	 */
	public ControleurRepello(LinkedList<Joueur> listeJoueur, HashMap configRepello) throws Exception{

		//On sauvegarde la config
		//
		config = ((HashMap) configRepello.get("controleurRepello"));

		//On init la fenetre
		//
		fenPrincipale = new JFrame((String)  (config.get("nom_fenetre")));

		//On config la fenêtre
		//
		int largeur = (Integer) config.get("largeur_plateau");
		int hauteur = (Integer) config.get("hauteur_plateau");

		fenPrincipale.setResizable(false);
		fenPrincipale.setSize(largeur,hauteur);
		fenPrincipale.setLocation((Integer)config.get("location_x"),(Integer)config.get("location_y"));
		fenPrincipale.setLocationRelativeTo(null);
		fenPrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenPrincipale.addKeyListener(this);

		//On init la vue
		//
		vuePlateau = new Plateau(configRepello);

		//On rajoute la vue daans la fenêtre
		//
		fenPrincipale.getContentPane().add(vuePlateau);

		//On rajoute les ecouteurs sur la vue
		//
		vuePlateau.addMouseListener(this);
		vuePlateau.addMouseMotionListener(this);

		//On init le moteur de repello
		//
		moteurRepello = new MoteurRepello( listeJoueur, configRepello);
		moteurRepello.addObserver(vuePlateau);

		//On demarre la fenêtre
		//
		fenPrincipale.setVisible(true);
	}

	/**
	 * changeImageCurseur
	 *
	 *Elle permet de changer l'image du curseur.
	 *
	 * @param Image  la nouvelle image du curseur.
	 */
	public void changeImageCurseur(Image image){

		Toolkit t =Toolkit.getDefaultToolkit();


		Cursor curseur = t.createCustomCursor(image, new java.awt.Point( (Integer) config.get("curseur_x"), 
				(Integer) config.get("curseur_y")),
				(String) config.get("curseur_nom"));
		fenPrincipale.setCursor(curseur);
	}

	/**
	 * Elle permet de changer l'image du curseur
	 *
	 * @param int i  Elle prend en parametre une constante
	 */
	@SuppressWarnings("deprecation")
	public void  changeImageCurseur(int i){	
		fenPrincipale.setCursor(i);
	}

	///////////////////////////////////////Mouse Listener


	/**
	 * 
	 * mouseDragged (Ecouteur)
	 * 
	 * On l'utilise pour faire les drags
	 * 
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

		//On cherche la position de la sourie
		//
		Point p = calculePositionCase(e.getX(), e.getY());

		//On declenche l'action lors du deplacement de la sourie
		//
		deplacerSouris(p);

		//On recupere la case sous la sourie
		//
		Case caseDest = vuePlateau.getCase(p);

		//On gere le cas où on est pas actuellement en drag.
		if(  caseDest != null && !estEnDrag && !moteurRepello.caseEstVide(caseDest.x(), caseDest.y()) ){

			//on passe en mode drag
			//
			estEnDrag = true;

			//On récupére le pion de la case
			//
			PionRepello pion = moteurRepello.getPion(caseDest.x(), caseDest.y());

			//on change l'image du curseur
			//
			changeImageCurseur(pion.getImage());

			//On drag
			//
			drag(p);
		}



	}


	/**
	 * 
	 * mouseReleased
	 * 
	 * Methode utilise lorsqu'on lache le clique
	 * 
	 */
	@Override
	public void mouseReleased( MouseEvent e) {

		//On gere le cas du drag
		//
		if( estEnDrag ){

			//On calcule la position de la sourie
			//
			Point p = calculePositionCase(e.getX(), e.getY());

			//Le cas où nous somme sur le plateau
			if(  moteurRepello.pointAppartienPlateau( (int)p.x(), (int)p.y()) ){

				//On récup la case
				//
				Case caseDest = vuePlateau.getCase(p);

				//On déplace le pion
				//
				deplacerPion(caseDrag, caseDest);

			}

			//on drop
			//
			drop();

			//On remet l'image du curseur à l'origine
			//
			changeImageCurseur(Cursor.DEFAULT_CURSOR);

			//On eneleve le mode drag
			estEnDrag =false;
		}
	}

	/**
	 * deplacerPion
	 * 
	 * Permet de déplacer un pion
	 *
	 * @param Case src La case source 
	 * @param Case dest la case destination
	 */
	@SuppressWarnings( "unchecked" )
	public void deplacerPion(Case src, Case dest ){

		//On controle la validité des paramétres.
		//
		if( src== null || dest == null) return;

		//On verifie que la case de destination est vide
		//
		if(moteurRepello.caseEstVide(dest.x(), dest.y()) ){

			//On recupere le pion
			//
			PionRepello pionRepello = moteurRepello.getPion(src.x(), src.y());

			//On récupére les coordonnées  des cases
			//
			Point pDest = new Point(dest.x(), dest.y());
			Point pSrc = new Point(src.x(), src.y());

			//On deplace le pion
			//
			boolean  bool = moteurRepello.deplacer( (int)pSrc.x(), (int)pSrc.y(), (int)pDest.x(), (int)pDest.y());

			//On gere le cas du stack
			//
			if ( bool && pionRepello != null  &&  Stack.estStack(pionRepello.getCouleur()) ){

				//On recup le joueur
				//
				Joueur j = moteurRepello.getJoueur(pionRepello.getCouleur());

				//on met a jour la vue
				//
				HashMap donne = new HashMap();
				donne.put("updateJoueur", j);
				setChanged();
				notifyObservers(donne);

			}

			//On affiche sur le plateau les pions 
			//
			LinkedList listePionAExpulser = moteurRepello.pieceAExpulser();
			selectPieceAExpulser(listePionAExpulser);

		}
	}

	/**
	 * 
	 * selectPieceAExpulser
	 * 
	 * Elle permet d'afficher sur la case les pions à expulser.
	 * 
	 * @param LinkedList listePion la liste des pions à expulser.
	 */
	public void selectPieceAExpulser( LinkedList listePion ){

		//On parcours la liste
		Iterator i =listePion.iterator();
		PionRepello pion;

		while(i.hasNext()){
			pion = (PionRepello) i.next();

			//On recup la Case
			Case caseRep = vuePlateau.getCase(new Point(pion.x(), pion.y()));

			//On change le fond de la Case
			caseRep.setBackground(Color.red);
		}

	}

	/**
	 * 
	 * mouseMoved 
	 * 
	 * Elle permet de selectionner les cases sous la sourie.
	 * 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		//On recup la position de la sourie
		//
		Point p = calculePositionCase(e.getX(), e.getY());

		//on gere le deplacement de la sourie
		//
		deplacerSouris(p);

	}

	/**
	 * Elle permet de calculer la position de la sourie.
	 *
	 * @param int xMouse L'abscisse de la sourie
	 * @param int yMouse L'ordonne de la sourie
	 * @return Point 
	 */
	public Point calculePositionCase (int xMouse, int yMouse){

		//On calcule la largeur et la hauteur d'une case
		//
		int largeurCase = vuePlateau.getWidth()/ Plateau.NB_CASE_LARGEUR;
		int hauteurCase = vuePlateau.getHeight() / Plateau.NB_CASE_HAUTEUR;

		return new Point( xMouse/largeurCase ,  yMouse/hauteurCase);
	}

	/**
	 * mouseCliked
	 * 
	 * Permet de gérer les evenement lors du clique gauche de la sourie.
	 * 
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public void mouseClicked(MouseEvent e) {

		//On detecte le clique gauche
		if(SwingUtilities.isLeftMouseButton(e)){

			//On recup la position de la sourie
			//
			Point p = calculePositionCase(e.getX(), e.getY());

			//On recup le pion se trouvant a cette position
			//
			PionRepello pion = moteurRepello.getPion( (int)p.x(), (int)p.y());

			//Dans le cas d un stack on affiche ses possibilité de déplacement
			//
			LinkedList listePieceArepousser = moteurRepello.pieceAExpulser();

			if(pion!= null && listePieceArepousser.contains(pion) ){

				LinkedList listePieceAutour = moteurRepello.recupLesPionsAutour(pion);
				repouserLesPion(listePieceAutour, pion);
			}
			else if( pion!= null && Stack.estStack(pion.getCouleur()))
				afficherDeplacementStack( (Stack) pion);
			else //si non on deselectionne tout.
				vuePlateau.deselectTout();

			//on met a jour les listes du demonstrateur
			//
			HashMap donne = new HashMap();
			donne.put("updateListeDemon", p);
			donne.put("drop", p);
			setChanged();
			notifyObservers(donne);

		}

	}


	/**
	 * Cette methode permet au pion expulseur, de repousser tous les pions de la liste.
	 * 
	 * @param listePion
	 * @param expulseur
	 */
	public void repouserLesPion(LinkedList listePion, PionRepello expulseur){

		//On teste la validite de la liste
		//
		if( listePion == null) return;


		//On parcours la liste
		//
		Iterator i = listePion.iterator();
		Point p;

		while( i.hasNext()){

			p = (Point)i.next();

			//On expulse le pion
			//
			moteurRepello.expulser(expulseur.x(), expulseur.y(), (int)p.x(), (int)p.y());

		}

	}

	/**
	 * afficherDeplacementStack
	 * 
	 * Elle permet d'afficher les possibilités du déplacement du stack séléctionné
	 *
	 * @param p the p
	 */
	public void afficherDeplacementStack(Stack stack){

		//On recupere la position du stack
		//
		Point p = new Point(stack.x(), stack.y());

		int valDessus = -1;
		int valDessous = -1;
		int valDroite = -1;
		int valGauche= -1;
		int valHD = -1;
		int valHG = -1;
		int valBD = -1;
		int valBG = -1;

		//On récupére les cases autours du stack
		//
		Case dessus = vuePlateau.getCase(new Point(p.x(), p.y()+1));
		Case dessous = vuePlateau.getCase(new Point(p.x(), p.y()-1));
		Case droite = vuePlateau.getCase(new Point(p.x()+1, p.y()));
		Case gauche = vuePlateau.getCase(new Point(p.x()-1, p.y()));
		Case hautDroit = vuePlateau.getCase(new Point(p.x()+1, p.y()-1));
		Case hautGauche = vuePlateau.getCase(new Point(p.x()-1, p.y()-1));
		Case basDroit = vuePlateau.getCase(new Point(p.x()+1, p.y()+1));
		Case basGauche = vuePlateau.getCase(new Point(p.x()-1, p.y()+1));

		//On récupére les valeurs de chacune des cases
		//
		if( dessus != null) valDessus = dessus.getValeur();
		if( dessous != null) valDessous = dessous.getValeur();
		if( droite != null) valDroite = droite.getValeur();
		if( gauche != null) valGauche = gauche.getValeur();
		if( hautDroit != null) valHD = hautDroit.getValeur();
		if( hautGauche != null) valHG = hautGauche.getValeur();
		if( basDroit != null) valBD = basDroit.getValeur();
		if( basGauche != null) valBG = basGauche.getValeur();


		//On selectionne les cases
		//
		Point pointDeplacement = null;
		Case caseDeplacement = null;

		Color couleur = new Color((Integer) config.get("color_r"),(Integer) config.get("color_g"),(Integer) config.get("color_b"));

		if( valDessus >0){
			pointDeplacement = new Point(p.x(), p.y()+valDessus);
			caseDeplacement = vuePlateau.getCase(pointDeplacement);

			if( caseDeplacement != null)
				caseDeplacement.setBackground(couleur);
		}

		if( valDessous >0){
			pointDeplacement = new Point(p.x(), p.y()-valDessous);
			caseDeplacement = vuePlateau.getCase(pointDeplacement);

			if( caseDeplacement != null)
				caseDeplacement.setBackground(couleur);
		}

		if( valDroite >0){
			pointDeplacement = new Point(p.x()+valDroite, p.y());
			caseDeplacement = vuePlateau.getCase(pointDeplacement);

			if( caseDeplacement != null)
				caseDeplacement.setBackground(couleur);
		}

		if( valGauche >0){
			pointDeplacement = new Point(p.x()-valGauche, p.y());
			caseDeplacement = vuePlateau.getCase(pointDeplacement);

			if( caseDeplacement != null)
				caseDeplacement.setBackground(couleur);
		}

		if( valHD >0){
			pointDeplacement = new Point(p.x()+valHD, p.y()-valHD);
			caseDeplacement = vuePlateau.getCase(pointDeplacement);

			if( caseDeplacement != null)
				caseDeplacement.setBackground(couleur);
		}

		if( valBD >0){
			pointDeplacement = new Point(p.x()+valBD, p.y()+valBD);
			caseDeplacement = vuePlateau.getCase(pointDeplacement);

			if( caseDeplacement != null)
				caseDeplacement.setBackground(couleur);
		}

		if( valBG >0){
			pointDeplacement = new Point(p.x()-valBG, p.y()+valBG);
			caseDeplacement = vuePlateau.getCase(pointDeplacement);

			if( caseDeplacement != null)
				caseDeplacement.setBackground(couleur);
		}

		if( valHG >0){
			pointDeplacement = new Point(p.x()-valGauche, p.y()-valGauche);
			caseDeplacement = vuePlateau.getCase(pointDeplacement);

			if( caseDeplacement != null)
				caseDeplacement.setBackground(couleur);
		}

	}

	/**
	 *
	 *Permet de gerer le drag
	 *
	 * @param Point p
	 */
	public void drag(Point p){

		//On Teste la validite du Point
		//
		if( !moteurRepello.pointAppartienPlateau( (int)p.x(), (int)p.y()) )return;

		//On recupére la Case
		caseDrag = vuePlateau.getCase(p);

		//On cache le pion se trouvant sur la case
		//
		caseDrag.setCache(true);
	}

	/**
	 * Permet de gérer le drop 
	 */
	public void drop (){

		//On decache la case
		//
		caseDrag.setCache(false);
		caseDrag = null;

		//On deselect tout
		//
		vuePlateau.deselectTout();

		//On affiche les pieces qui devront être expulser
		//
		selectPieceAExpulser(moteurRepello.pieceAExpulser());

		//On garde en memoire la couleur qui se trouve sous la sourie.
		//Cela permet d'eviter de deselectionner la case.
		//
		couleurPreceCase = caseSousSourie.getBackground();

		//On met a jours le plateau
		vuePlateau.repaint();

	}

	/**
	 * Permet de gérer le déplacement de la sourie
	 * 
	 * @param Point p
	 */
	public void deplacerSouris(Point p ){

		//On teste la validité du point
		//
		if( ! moteurRepello.pointAppartienPlateau( (int)p.x(), (int)p.y()) )return;

		//On récupére la case
		//
		Case destCase = vuePlateau.getCase(p);

		if(caseSousSourie == null){

			//On garde en mémoire la case
			//
			caseSousSourie = destCase;

			//On garde en mémoire la couleur du fond en mémoire
			//
			couleurPreceCase = caseSousSourie.getBackground();

			//On selectionne la nouvelle Case
			//
			moteurRepello.selectCase(p);
			return;
		}

		if( !destCase.equals(caseSousSourie)){

			//On restaure le fond de la précédente case
			//
			if( couleurPreceCase != null)
				caseSousSourie.setBackground(couleurPreceCase);

			//On garde en memoire la nouvelle case
			//
			caseSousSourie = destCase;

			//on garde en memoire la couleur du fond de la nouvelle case
			//
			couleurPreceCase = caseSousSourie.getBackground();

			//On selectionne la nouvelle Case
			//
			moteurRepello.selectCase (p);
		}

	}

	/**
	 * Elle permet d'afficher le gagnant
	 * 
	 */
	public void afficherGagnant (){

		//On récupére le nom du gagnant
		//
		String nom = moteurRepello.getGagnant();
		if( nom == null )return;

		//On joue le son de victoire
		//
		moteurRepello.jouerSonVictoire();

		//On affiche le joueur qui a gagné
		//
		vuePlateau.afficheGagnant(nom);


	}

	//												Accesseur
	//

	/**
	 *	Permet de récupérer le moteur de repello
	 * @return MoteurRepello
	 */
	public MoteurRepello getMoteurRepello (){ return moteurRepello;}

	/**
	 * Permet de récupérer la fenêtre
	 *
	 * @return JFrame
	 */
	public JFrame getFenetreRepello (){ return fenPrincipale;}

	/**
	 * Permet de récupérer la vue du plateau
	 *
	 * @return Plateau
	 */
	public Plateau getVuePlateau(){ return vuePlateau;}


	@Override
	public void mouseEntered(MouseEvent e) {}


	@Override
	public void mouseExited(MouseEvent e) {}


	@Override
	public void mousePressed(MouseEvent e) {}


	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

}
