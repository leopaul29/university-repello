//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe VueSablier : partie Vue (MVC) d'un sablier
//
// Edition A    : mise en oeuvre de l'interface Observer
//                    
//    + Version 1.0.0	: version initiale
//
// Auteur : Manuel Pavone
//

package Vue;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class VueSablier.
 */
public class VueSablier extends JPanel implements Observer {

	/** The hamecon. */
	private JFrame hamecon;

	/** The label. */
	private JLabel label;

	/** The npb. */
	private NProgressBar npb;

	/** The img. */
	private Image img;

	/** The npb2. */
	private NProgressBar npb2;

	/** The config vue. */
	private HashMap configVue;

	// ---                                               Constructeur normal  

	/**
	 * Instantiates a new vue sablier.
	 *
	 * @param hamecon the hamecon
	 * @param config the config
	 * @throws Throwable the throwable
	 */
	public VueSablier (JFrame hamecon, HashMap config) 
			throws Throwable {

		super();

		// Instancier les 2 progress bar à null
		//
		npb = null;
		npb2 = null;

		// Controler la validite du premier parametre
		//
		if (hamecon == null) throw new Throwable ("-2.1");

		// Controler la valeur du second parametre
		//
		if (config == null) config= new HashMap();

		// Memoriser l'hamecon comme attribut
		//
		this.hamecon= hamecon;

		// Memoriser la configuration 
		//
		this.configVue = config;

		// Installer le gestionnaire de positionnement
		//
		LayoutManager layout= new GridLayout(8,1);
		setLayout(layout);

		// Fixer la couleur de fond du panneau
		//
		Color couleurFond= (Color)config.get("arrierePlan");
		if (couleurFond == null) couleurFond= Color.yellow;
		setBackground(couleurFond);

		// Construire le champ de visualisation des donnees (JLabel)
		//
		String labelInitial= (String)config.get("labelInitial");
		if (labelInitial == null) labelInitial= "";
		label= new JLabel(labelInitial); 

		Font police= (Font)config.get("police");
		if (police == null) police= new Font("DS-digital", 
				Font.TYPE1_FONT, 60);
		label.setFont(police);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);

		// Fixer la couleur du champ de visualisation
		//
		Color couleurTexte= (Color)config.get("avantPlan");
		if (couleurTexte == null) couleurFond= Color.black;
		label.setForeground(couleurTexte);

		// Ajout de panneaux vides pour remplir la grille
		//
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());

		// Charger une image qui servira de masque aux progess bar
		//
		img = ImageIO.read(new File((String) config.get("imageSablier")));

		// Ajout de notre Pannel dans le GlassPane
		//
		((JPanel)hamecon.getGlassPane()).add(this);
		((JPanel)hamecon.getGlassPane()).setLayout(new GridLayout(1,1));
		((JPanel)hamecon.getGlassPane()).setVisible(true);

	}

	// ---                                                    Methode update

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object modifs) {


		// Extraire du second parametre la valeur du temps courant
		//
		String tempsCourant= (String)((HashMap)modifs).get("tempsCourant");

		// Actualiser le temps
		//
		label.setText(tempsCourant);

		// On split en fonction de ':' pour récupérer que les chiffres
		//
		String[] temps = tempsCourant.split(" : ");

		// On récupère l'heure en secondes
		//   	
		int nbHeures = Integer.parseInt(temps[0])*3600;
		int nbMinutes = Integer.parseInt(temps[1])*60;
		int nbSecondes = Integer.parseInt(temps[2]);

		// Lors du premier update on initialise les progressbar à la valeur initiale
		//
		if( npb == null && npb2 == null) {
			// Creation de la NProgressBar du haut
			//
			npb = new NProgressBar(JProgressBar.VERTICAL,0, nbHeures+nbMinutes+nbSecondes+1);

			// Ajout des règles de coloration à la NProgressBar 
			//
			npb.addColoringRule(0,1, (Color) configVue.get("couleurFin"),(Color) configVue.get("couleurDebut") );

			// Creation de la NProgressBar du bas
			//
			npb2 = new NProgressBar(JProgressBar.VERTICAL,0, nbHeures+nbMinutes+nbSecondes+1);

			// Ajout des règles de coloration à la NProgressBar
			//
			npb2.addColoringRule(0,1,  (Color) configVue.get("couleurDebut"), (Color) configVue.get("couleurFin"));

			// Initialisation de la NProgressBar à 0
			//
			npb2.setValue(0);

			// Creation d'un nouveau JPanel contenant les progress bar
			//
			JPanel lePan = new JPanel();
			lePan.setLayout(new GridLayout(4, 1));
			lePan.add(new JPanel());
			lePan.add(npb);
			lePan.add(npb2);
			lePan.add(new JPanel());

			// Ajout du panneau à l'hamecon
			//
			hamecon.getContentPane().add(lePan);
		}

		npb.setValue(nbHeures+nbMinutes+nbSecondes);
		npb2.setValue(npb2.getValue()+1); 

		// On rafraichit le panneau contenant le temps
		//
		((JPanel)hamecon.getGlassPane()).repaint();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {

		String texte = label.getText();

		// Dessiner l'image du sablier
		//
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),null);

		// Creer une taille de police pour que celle ci soit proportionelle à la taille
		//
		Font a = new Font("Arial", Font.BOLD, (int) (((this.getWidth()*0.08)+(this.getHeight()*0.08))/2));

		g.setFont(a);

		// Calculer les dimensions du texte
		//    
		FontMetrics fm= g.getFontMetrics(a);

		int largeurTexte= fm.stringWidth(texte);

		// Dessiner le texte
		//
		g.drawString(texte,(int) (this.getWidth()/2-largeurTexte/2), (int) (this.getHeight()/1.05));
	}

	// ---                                                 Methode showButee

	/**
	 * Show butee.
	 */
	public void showButee() {

		// Modifier la couleur d'arriere plan
		//   	
		Color arrierePlan= (Color)configVue.get("arrierePlanButee");
		if (arrierePlan != null) setBackground(arrierePlan);
	} 

}
