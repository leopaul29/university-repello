//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe VueTempsG : partie Vue (MVC) d'un compteur/decompteur de temps
//
// Edition A    : mise en oeuvre de l'interface Observer
//                    
//    + Version 1.0.0	: version initiale
//    + Version 1.1.0   : le modele observe transmet un dictionnaire de modifs
//                        a chaque notification de changement du temps
//    + Version 1.2.0   : correction bug sur la couleur de texte par defaut
//                        + possibilite d'ajouter la vue dans un conteneur 
//                          (JPanel par exemple et pas seulement dans JFrame)
//                        + ajout methode showButee pour transfert dans la
//                          vue de la modification eventuelle de l'arriere 
//                          plan dans le cas butee atteinte
//    + Version 1.3.0   : memorisation de la configuration comme attribut
//                        + modification methode update pour transfert dans 
//                          la vue de la modification eventuelle de l'arriere 
//                          plan dans le cas butee atteinte
// Auteur : A. Thuaire
//

package Vue;

import java.awt.*;
import javax.swing.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class VueTempsG.
 */
public class VueTempsG extends JPanel implements Observer {

	/** The hamecon. */
	private Object hamecon;

	/** The label. */
	private JLabel label;

	/** The config vue. */
	private HashMap configVue;

	// ---                                               Constructeur normal  

	/**
	 * Instantiates a new vue temps g.
	 *
	 * @param hamecon the hamecon
	 * @param config the config
	 * @throws Throwable the throwable
	 */
	public VueTempsG (Object hamecon, HashMap config) throws Throwable {

		super();

		// Controler la validite du premier parametre
		//
		if (hamecon == null) throw new Throwable ("-2.1");

		// Controler la valeur du second parametre
		//
		if (config == null) config= new HashMap();

		// Memoriser l'hamecon comme attribut
		//
		this.hamecon= hamecon;

		// Memoriser la configuration de la vue
		//
		configVue= config;

		// Installer le gestionnaire de positionnement
		//
		LayoutManager layout= (LayoutManager)config.get("placement");
		if (layout == null) layout= new GridLayout(1,0);
		setLayout(layout);

		// Ajouter la vue dans le conteneur d'accueil
		//
		String typeConteneur= hamecon.getClass().getName();

		if (typeConteneur.equals("javax.swing.JFrame")) {

			((JFrame)hamecon).getContentPane().add(this);
		}
		else ((Container)hamecon).add(this);

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
		if (couleurTexte == null) couleurTexte= Color.black;
		label.setForeground(couleurTexte);

		// Ajouter le champ de visualisation au panneau
		//
		add(label);
	}

	// ---                                                    Methode update

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object modifs) {

		// Extraire du second parametre la valeur du temps courant
		//
		String tempsCourant= (String)((HashMap)modifs).get("tempsCourant");

		// Rafraichir la valeur du temps courant
		//   	
		label.setText(tempsCourant);

		// Obtenir la notification eventuelle de butee atteinte
		//
		Boolean buteeAtteinte= (Boolean)((HashMap)modifs).get("buteeAtteinte");

		// Modifier la couleur d'arriere plan de la vue si la butee est atteinte
		//   	
		if (buteeAtteinte) showButee();   
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
