/*
 * 
 */
package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * Class StatJoueur extend JPanel et implement Observer
 * permet de visualiser les statistique des joueurs.
 *
 * @author LeoPaul MARTIN
 */
public class StatJoueur extends JPanel implements Observer {

	// ---											Attributs
	//
	/** L'image fond. */
	private Image imageFond;

	/** le texte titre. */
	private String texteTitre;

	/** les sous panneaux. */
	private LinkedHashMap<String, StatJoueur> sousPanneaux;



	/**
	 * Constructeur normal de stat joueur.
	 *
	 * @param createur le createur
	 * @param config le config
	 * @throws Throwable the throwable
	 */
	// ---                                          Premier constructeur normal
	public StatJoueur (JFrame createur, final HashMap<?, ?> config) throws Throwable {

		// J'appel le constructeur de JPanel
		//
		super();

		// Je récupere la taille de la fenetre StatJoueur
		//
		int taille= (Integer) config.get("taille");
		if (taille > 0) createur.setSize(taille, taille);
		else throw new Throwable ("-2.0");

		// Je récupère le titre de la fenetre
		//
		String titre= (String) config.get("titreF");
		if (titre != null) createur.setTitle(titre);
		else throw new Throwable ("-1.1");

		//Je récupère les coordonnées ou faire apparaitre le plateau
		//
		int locX= (Integer) config.get("locationX");
		int locY= (Integer) config.get("locationY");
		if (locX > 0 || locX == 0)if (locY >= 0) createur.setLocation(locX, locY);
		else throw new Throwable ("-1.2");

		// Je récupère la donnée pour savoir si la fenetre du plateau est redimensionable
		//
		boolean resize= (Boolean) config.get("resizable");
		if(resize == true) createur.setResizable(true);
		else createur.setResizable(false);

		// Configurer le panneau sous jacent
		//
		configurer(config);

		// Accrocher le panneau a son cadre d'accueil
		//
		createur.getContentPane().add(this);

		// Creer la description des panneaux internes
		//
		sousPanneaux= new LinkedHashMap<String, StatJoueur>();

		// Initialiser cette description avec le panneau principal
		//
		String nomPanneau= (String)config.get("nomPanneau");
		if (nomPanneau != null) sousPanneaux.put(nomPanneau, this);
		else sousPanneaux.put("_", this);

		// Ajouter les sous panneaux eventuels
		//
		LinkedHashMap<?, ?> panneaux= (LinkedHashMap<?, ?>)config.get("sousPanneaux");
		if (panneaux != null) ajouterPanneaux(this, panneaux);

		// Je rend la fenetre visible en fonction des données
		//
		boolean visible= (Boolean) config.get("visible");
		if(visible == true) createur.setVisible(true);
		else createur.setVisible(false);
	}

	/**
	 * Instantiates a new stat joueur.
	 *
	 * @param createur le createur
	 * @param config le config
	 */
	// ---                                          Second constructeur normal
	private StatJoueur (final StatJoueur createur, final HashMap<?, ?> config) {

		super();

		// Configurer le panneau sous jacent
		//
		configurer(config);

		// Accrocher le sous panneau a son panneau d'accueil
		//
		createur.add(this);

		// Creer la description des sous panneaux internes
		//
		sousPanneaux= new LinkedHashMap<String, StatJoueur>();
	}	

	// ---                                            Accesseurs de consultation
	//
	/**
	 * Gets le panneaux.
	 *
	 * @return le panneaux
	 */
	public LinkedHashMap<String, StatJoueur> getPanneaux () {return sousPanneaux;} 

	/**
	 * Gets le panneau.
	 *
	 * @param nomPanneau le nom panneau
	 * @return le panneau
	 */
	public StatJoueur getPanneau (String nomPanneau) {
		if (nomPanneau == null) return null;
		return (StatJoueur)sousPanneaux.get(nomPanneau);
	}

	/**
	 * Configurer.
	 *
	 * @param config le config
	 */
	// ---                                                    Methode configurer
	private void configurer (final HashMap<?, ?> config) {

		setBackground(config);
		setForeground(config);
		setFont(config);
		setLayout(config);
		setImage(config);
		setTitre(config);
	}

	/**
	 * Sets le background.
	 *
	 * @param config le config
	 */
	// ---                                                 Methode setBackground
	private void setBackground(HashMap<?, ?> config) { 
		Object w;

		if (config==null) return;
		w= config.get("arrierePlan");
		if (w==null) return;
		setBackground((Color)w);
	}

	/**
	 * Sets le foreground.
	 *
	 * @param config le config
	 */
	// ---                                                 Methode setForeground
	private void setForeground(HashMap<?, ?> config) {
		Object w;

		if (config==null) return;
		w= config.get("avantPlan");
		if (w==null) return;
		setForeground((Color)w);
	}

	/**
	 * Sets le font.
	 *
	 * @param config le config
	 */
	// ---                                                       Methode setFont
	private void setFont(HashMap<?, ?> config) {
		Object w;

		if (config==null) return;
		w= config.get("police");
		if (w==null) return;
		setFont((Font)w);
	}

	/**
	 * Sets le layout.
	 *
	 * @param config le config
	 */
	// ---                                                     Methode setLayout
	private void setLayout(HashMap<?, ?> config) { 
		Object w;

		if (config==null) return;
		w= config.get("placement");
		if (w==null) {setLayout(new GridLayout(1,0)); return;}
		setLayout((LayoutManager)w);
	}

	/**
	 * Sets le image.
	 *
	 * @param config le config
	 */
	// ---                                                      Methode setImage
	private void setImage(HashMap<?, ?> config) { 
		Object w;

		if (config==null) return;
		w= config.get("image");
		if (w !=null) {chargerImage((String)w);}
	}  

	/**
	 * Charger image.
	 * permet de charger une image dans le dossiers Images.
	 *
	 * @param cheminImage le chemin image
	 */
	// ----                                                 Methode chargerImage 
	private void chargerImage(String cheminImage) {

		// Controler la validite du parametre
		//
		if (cheminImage == null) return;

		// Charger une image depuis un fichier de type jpeg
		//
		imageFond= Toolkit.getDefaultToolkit().getImage("Images/" + cheminImage);

		// Construire un media tracker pour controler le chargement de l'image
		//
		MediaTracker mt= new MediaTracker(this);

		// Attendre la fin du chargement effectif de l'image
		//
		mt.addImage(imageFond,0);
		try{mt.waitForAll();}
		catch(Exception e){}
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	// ----                                                        Methode paint
	public void paint (Graphics g) {

		super.paint(g);

		// Dessiner l'image de fond eventuelle
		//
		if (imageFond != null) {
			g.drawImage(imageFond, 0, 0, getWidth(), getHeight(), null);
		}

		// Dessiner le titre eventuel
		//
		if (texteTitre != null) dessinerTitre(g);  	
	} 

	/**
	 * Sets le titre.
	 * permet de modifier le titre
	 *
	 * @param config le config
	 */
	// ---                                                       Methode setTitre
	private void setTitre(HashMap<?, ?> config) {
		Object w;

		if (config==null) return;
		w= config.get("titre");
		if (w==null) return;
		ajouterTitre((String)w);
	}

	/**
	 * Ajouter titre.
	 * permet d'ajouter un titre
	 *
	 * @param titre le titre
	 * @return true, if successful
	 */
	// ---                                                   Methode ajouterTitre
	public boolean ajouterTitre(String titre) {

		// Controler la validite des parametres
		//
		if (titre == null) return false;

		// Renseigner les attributs associes au titre
		//
		texteTitre  = titre;

		// Repeindre le panneau
		//
		repaint();
		return true;
	}

	/**
	 * Dessiner titre.
	 * permet de dessiner le titre
	 *
	 * @param g le g
	 */
	// ---                                                 Methode dessinerTitre
	private void dessinerTitre(Graphics g) {

		// Controler la presence d'un titre
		//
		if (texteTitre == null) return;

		// Calculer les dimensions du titre
		//    
		FontMetrics fm= g.getFontMetrics();

		int largeurTitre= fm.stringWidth(texteTitre);
		int hauteurTitre= fm.getHeight();

		// Traiter le cas de l'absence d'image de fond
		//
		if (imageFond == null) {

			// Dessiner le texte
			//
			g.drawString(texteTitre, (getWidth()-largeurTitre)/2, 
					(getHeight()-hauteurTitre)/2);
		}
		else {

			// Traiter le cas de la presence d'une image de fond
			//
			g.drawString(texteTitre, (getWidth()-largeurTitre)/2, 
					getHeight()-10 );
		}
	}

	/**
	 * Ajouter panneaux.
	 * permet d'ajouter des panneaux
	 *
	 * @param hamecon le hamecon
	 * @param panneaux le panneaux
	 */
	// ---                                              Methode ajouterPanneaux
	private void ajouterPanneaux(StatJoueur createur, LinkedHashMap<?, ?> panneaux) { 

		// Controler la validite des parametres
		//
		if (panneaux == null) return;

		// Parcourir la description des sous panneaux
		// 
		Iterator<?> i=panneaux.keySet().iterator();
		String cle=null;
		HashMap<?, ?> associe= null;

		while(i.hasNext()) {

			// Acquerir la cle courante
			//
			cle=(String)i.next();

			// Acquerir l'associe courant
			//
			associe= (HashMap<?, ?>)panneaux.get(cle);

			// Ajouter le sous panneau courant au panneau cible
			//
			ajouterPanneau(createur, cle, associe);
		}
	} 

	/**
	 * Ajouter panneau.
	 *
	 * @param createur le createur
	 * @param nomPanneau le nom panneau
	 * @param description le description
	 * @return true, if successful
	 */
	// ---                                             Methode ajouterPanneau
	private boolean ajouterPanneau (StatJoueur createur, String nomPanneau, HashMap<?, ?> description) {

		StatJoueur panneau;

		// Controler la validite du premier parametre
		//
		if (nomPanneau == null) return false;

		// Creer le panneau cible
		//
		panneau= new StatJoueur(createur, description);             	

		// Memoriser le panneau cible dans le dictionnaire des panneaux
		//
		sousPanneaux.put(nomPanneau, panneau);

		return true;
	}

	/**
	 * Modifier point.
	 * permet de modifier les points du score des joueurs
	 *
	 * @param point le point
	 */
	// ---												Méthode modifierPoints
	public void gererPoint(int point){

		// Je vérifie la validité du paramètre
		//
		if (point == 0) return;

		// Si le score est égale à zero alors je ne peux plus retirer de point
		//
		if(texteTitre.equals("0") && point == -1) return;

		// Sinon je modifie le score du panneau
		//
		else {
			int nbPoint = Integer.valueOf(texteTitre);
			nbPoint += point; 
			texteTitre = String.valueOf(nbPoint);
			repaint();
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object modifstat) {
		// TODO Auto-generated method stub

		// Je récupère le a ajouter ou a retirer
		//
		int point = (Integer) ((HashMap<?, ?>)modifstat).get("pts");

		// Je récupère le panneau du jeton a modifier
		//
		String panCible = (String) ((HashMap<?, ?>)modifstat).get("panCible");

		// Je modifie le score du panneau ciblé
		//
		getPanneau(panCible).gererPoint(point);
	}

}
