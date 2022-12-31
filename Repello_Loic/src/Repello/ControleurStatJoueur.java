package Repello;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JFrame;

// TODO: Auto-generated Javadoc

/**
 * La  Class ControleurStatJoueur.
 */
@SuppressWarnings("rawtypes")
public class ControleurStatJoueur extends Observable implements MouseMotionListener, MouseListener{
	
	/** Contient la liste des frames */
	private HashMap<JFrame, PanneauJoueur> listeFrame;
	
	/** Le moteur de la partie joueurs */
	private MoteurStatJoueur moteurStat;

	//drag Pan
	/** Memorise le panneau cible lors d'un drag */
	private PanneauImages panSelect;
	
	/** active le mode drag */
	private boolean estEnDrag = false;
	
	/** Le pion qui est en drag */
	private Repellere repellDrag;
	
	/** Le joueur qui est a la source du drag */
	private Joueur joueurSrcDrag;
	
	/** La config de stat joueur*/
	private HashMap configStatJoueur;
	
	/** Le config de repello */
	private HashMap configRepello;
	
	
	/**
	 * Constructeur de controleur stat joueur.
	 *
	 * @param listeJoueur the liste joueur
	 * @param configRepello the config repello
	 */
	public ControleurStatJoueur(LinkedList<Joueur> listeJoueur, HashMap configRepello) {
		
		//On recup et sauvegarde les config
		//
		this.configRepello = configRepello;
		configStatJoueur = (HashMap) configRepello.get("controleurStat");
		
		//On init le modele de stat joueur
		//
		moteurStat = new MoteurStatJoueur(listeJoueur,configRepello);
		
		//On init les frames
		listeFrame = new HashMap<JFrame, PanneauJoueur>();
		for(int i =0; i< listeJoueur.size(); i++){
			
			//On recup le joueur
			//
			Joueur j = listeJoueur.get(i);
			
			//Init d une des frames
			//
			JFrame fenetre = new JFrame(configStatJoueur.get("nom_fenetre") +j.getNom());
			fenetre.setResizable(false);
			fenetre.setBounds((Integer)configStatJoueur.get("x"), i*(Integer)configStatJoueur.get("y"),
					(Integer) configStatJoueur.get("largeur"),(Integer) configStatJoueur.get("hauteur"));
			fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			//Init d'une des vues
			//
			PanneauJoueur panJ = new PanneauJoueur(j, fenetre, configRepello);
			fenetre.getContentPane().add(panJ);
			
			//On place les ecouteurs
			//
			panJ.getPanImageRepNoir().addMouseMotionListener(this);
			panJ.getPanImageRepNoir().addMouseListener(this);
			
			panJ.getPanImageRepArgent().addMouseMotionListener(this);
			panJ.getPanImageRepArgent().addMouseListener(this);
			
			panJ.getPanImageRepOr().addMouseMotionListener(this);
			panJ.getPanImageRepOr().addMouseListener(this);
			
			panJ.getPanImageStack().addMouseMotionListener(this);
			panJ.getPanImageStack().addMouseListener(this);
			
			
			//on rajoute les observer
			//
			moteurStat.addObserver(panJ);
			
			//on  garde en memoire la fenetre et sa vue
			//
			listeFrame.put(fenetre, panJ);
			
			//on active la fenêtre
			fenetre.setVisible(true);
			
		}
	}

	/**
	 * 
	 * Methode lors de la détection du mouvement de la sourie.
	 * 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		
		//On teste la validite de la source.
		//
		if( ! (e.getSource() instanceof PanneauImages) )return;
		PanneauImages pan = (PanneauImages)e.getSource();
		
		//Le cas d initialisation
		//
		if(panSelect == null){
			//On garde en memoire le pan qui est select
			panSelect =pan;
			//On selectionne le pan en question
			panSelect.setBackground(Color.blue);
			return;
		}
		
		//dans le cas ou le panSelect est deja selectionné
		if( !pan.equals(panSelect)){
			
			//On deselectionne un pan
			//
			panSelect.setBackground(Color.white);
			
			//On garde en memoire le pan ciblé
			panSelect = pan;
			
			//On selectionne le pan
			panSelect.setBackground(Color.blue);
			
		}
		
	}
	
	/**
	 * 
	 * Methode lors de la détection du clique de la sourie
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		//On teste la validite de la source.
		//
		if( (! (e.getSource() instanceof PanneauImages)) ) return;
		
		//Le cas où nous sommes pas en mode drag
		if( ! estEnDrag){
			
			//On active le mode drag
			//
			estEnDrag = true;
			
			//On recupere la source et on drag
			//
			drag( (PanneauImages) e.getSource() );
			
			
		}
		else{
			//on drop
			//
			drop((PanneauImages) e.getSource());
			
			
			//On desactive le drag
			//
			estEnDrag =false;
			
		}
		
	}
	
	/**
	 * L'action associé lors d'un drag
	 * 
	 * @param panImage le panneau src du drag
	 */
	
	@SuppressWarnings("unchecked")
	private void drag (PanneauImages panImage){
		
		if( panImage == null) return;
		
		//On recupere le panneau du joueur
		//
		PanneauJoueur panJ = (PanneauJoueur) panImage.getHamecon();
		
		//On récupére le nom du joueur
		//
		String nomJoueur = panJ.getLabelNom().getText();
		
		//On récupére le joueur
		joueurSrcDrag = moteurStat.getJoueur(nomJoueur);
		
		//on identifie le repeller qu on veut déplacer
		//
		if(panJ.getPanImageRepNoir().equals(panImage)){
			
			//Le cas du Repeller noir
			//On recup la couleur du Repeller noir 
			//
			String couleurRepNoir = (String) configStatJoueur.get("pion_noir");
			
			//On cree le un repellere
			//
			String cheminRepNoir = (String) configStatJoueur.get("image_repelleur_noir");
			repellDrag = new Repellere(new Point(), cheminRepNoir, couleurRepNoir, configRepello);
			
		}
		else if ( panJ.getPanImageRepArgent().equals(panImage) ){
			
			//Le cas du Repeller Argent
			//
			String couleurRepArgent = (String) configStatJoueur.get("pion_argent");
			String cheminRepArgent = (String) configStatJoueur.get("image_repelleur_gris");
			
			repellDrag = new Repellere(new Point(), cheminRepArgent, couleurRepArgent, configRepello);
		}
		else if ( panJ.getPanImageRepOr().equals(panImage) ){
			
			//Le cas du Repeller Or
			//
			String couleurRepOr = (String) configStatJoueur.get("pion_or");
			String cheminRepOr = (String) configStatJoueur.get("image_repelleur_or");
			
			repellDrag = new Repellere(new Point() , cheminRepOr, couleurRepOr, configRepello);
		}
		else if ( panJ.getPanImageStack().equals(panImage)){
			
			//Dans le cas du stack
			//on fait un drag depuis le l'objet "ObservateurApplie"
			
			//On met a jour l'observateur applie
			//
			HashMap action = new HashMap();
			action.put("drag", joueurSrcDrag.getStack());
			setChanged();
			notifyObservers(action);
			
			//On quitte le mode drag
			//
			estEnDrag = false;
			
			return;	
		}
		
		//On change l'image du curseur
		//
		changeImageCurseur(panImage.getImage());
		
	}
	
	/**
	 * L'action associé lors du drop
	 * 
	 * @param panImage
	 */
	private void drop (PanneauImages panImage){
		
		if( panImage == null) return;

		//On récupére le joueur
		//
		PanneauJoueur panJ = (PanneauJoueur) panImage.getHamecon();
		String nomJoueur = panJ.getLabelNom().getText();
		Joueur jDest = moteurStat.getJoueur(nomJoueur);
		
				
		//On met a jour l'observateur appli
		//
		HashMap donne = creeInfoVolerReppeller(jDest, joueurSrcDrag, repellDrag);
		setChanged();
		notifyObservers(donne);
	
		//Attention!! la mise a jour des vues de stat joueur se fait dans ObservateurApplie
		
		//On remet a null les paramétre du drag
		//
		joueurSrcDrag = null;
		repellDrag = null;
		
		//on change l'image du curseur
		//
		changeImageCurseur(Cursor.DEFAULT_CURSOR);
	}
	
	/**
	 * Permet de creer le hasmap qui va notifie l'observateur applie
	 *
	 * @param Joueur jVoleur le joueur qui vole
	 * @param Joueur jVole le joueur qui est volé
	 * @param Repellere reppelDrag Le repellere qu'il drag
	 * @return Hashmap 
	 */
	@SuppressWarnings( "unchecked" )
	private HashMap creeInfoVolerReppeller(Joueur jVoleur, Joueur jVole, Repellere reppelDrag){
		
		HashMap info = new HashMap();
		
		String voleur =  jVoleur.getCouleurString();
		String vole = jVole.getCouleurString();
		String pion = reppelDrag.getCouleur();
		
		info.put("voleur", voleur);
		info.put("vole", vole);
		info.put("pion", pion);
		
		HashMap donne = new HashMap();
		donne.put("voler", info);
		
		return donne;
	}
	
	
	/**
	 * Getter
	 * 
	 * Permet de récupérer le modele de stat joueur.
	 *
	 * @return MoteurStatJoueur
	 */
	public MoteurStatJoueur getMoteurStatJoueur(){ return moteurStat;}
	

	/**
	 * 
	 * La méthode est appelé lorque la sourie sort de l'une des fenêtre des stats Joueur
	 * 
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		
		if(panSelect != null){
			
			//On deselctionne la case selectionne
			//
			panSelect.setBackground(Color.white);
			panSelect = null;
		}
		
	}
	
	/**
	 * Permet de changer l'image du curseur sur toute les fenêtres
	 *
	 * @param Image l'image du nouveau curseur
	 */
	public void changeImageCurseur(Image image){
		
		//On recupere le toolkit
		//
		Toolkit t =Toolkit.getDefaultToolkit();
		
		//on crée son nouveau curseur
		//
		Cursor curseur = t.createCustomCursor(image, new java.awt.Point( (Integer) configStatJoueur.get("curseur_x"), 
				(Integer) configStatJoueur.get("curseur_y")),
				(String) configStatJoueur.get("curseur_nom"));
		
		//On parcourt toute la liste de frame
		Iterator <JFrame> i = listeFrame.keySet().iterator();
		JFrame frame;
		while(i.hasNext()){
			
			frame = i.next();
			//on change le curseur
			//
			frame.setCursor(curseur);
			
		}
	}
	
	/**
	 * Change l'image du curseur de toute les fenêtre en fct d un int
	 *
	 * @param int nb
	 */
	@SuppressWarnings("deprecation")
	public void  changeImageCurseur(int nb){	
		
		//On parcourt la liste des frames
		//
		Iterator <JFrame> i = listeFrame.keySet().iterator();
		JFrame frame;
		
		while(i.hasNext()){
			
			frame = i.next();
			//on change l'image du curseur
			//
			frame.setCursor(nb);
				
			
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}

}
