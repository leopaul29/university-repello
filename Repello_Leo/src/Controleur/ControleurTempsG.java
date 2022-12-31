//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe ControleurTempsG : partie controleur (MVC) d'un compteur/decompteur 
//                           de temps
//
// Edition A        : edition initiale
//
//    + Version 1.0.0   : version initiale
//    + Version 1.1.0   : introduction de la methode setButee
//    + Version 1.2.0   : mise en place de l'observation du modele
//    + Version 1.3.0   : prise en compte d'un nouveau constructeur normal
//                        du modele pour le cas d'un sablier
//    + Version 1.4.0   : correction bug sur la memorisation du modele 
//                        + ajout de controles de validite des parametres de 
//                          configuration
//                        + ajout methode demarrer pour differer le lancement 
//                          du thread du modele
//                        + ajout methode stopper pour arret du fonctionnement
//                          du modele
//    + Version 1.5.0   : ajout des methodes suspendre et reprendre
//    + Version 1.6.0   : ajout methode addObserver pour pouvoir observer le
//                        temps courant a l'exterieur du composant
//    + Version 1.7.0   : ajout methodes getNomModele et setNomModele pour
//                        pouvoir obtenir et/ou modifier dynamiquement le 
//                        nom symbolique externe du modele
//                        + prise en compte de la modification du retour de
//                          la methode setButee du modele
//
// Edition B        : extension des capacites de configuration du composant
//
//    + Version 2.0.0   : modification du constructeur normal pour prendre
//                        en compte la separation de la configuration du 
//                        modele et de celle de la vue
//    + Version 2.1.0   : modification du constructeur normal pour construire
//                        la vue par réflexivité a partir d’un nom de classe 
//                        fourni par le dictionnaire de configuration du 
//                        composant 
//
// Auteur : A. Thuaire
//

package Controleur;

import java.util.*;
import java.awt.*;

import javax.swing.*;

import Modele.ModeleTempsG;

import java.lang.reflect.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ControleurTempsG.
 */
public class ControleurTempsG implements Observer {

	/** The config modele. */
	private HashMap      configModele;

	/** The config vue. */
	private HashMap      configVue;

	/** The vue. */
	private Observer     vue;

	/** The modele. */
	private ModeleTempsG modele;

	/** The mode. */
	private String       mode; 

	// ---                                                   Constructeur normal

	/**
	 * Instantiates a new controleur temps g.
	 *
	 * @param f the f
	 * @param configModele the config modele
	 * @param configVue the config vue
	 * @throws Throwable the throwable
	 */
	public ControleurTempsG(JFrame f, HashMap configModele,
			HashMap configVue) throws Throwable {

		// Memoriser le dictionnaire de configuration du modele
		//
		if (configModele == null) throw new Throwable ("-3.1");
		this.configModele= configModele;

		// Extraire et controler le mode de fonctionnement
		//
		mode= (String)configModele.get("mode");
		if (mode == null) throw new Throwable ("-3.2");

		boolean p1= mode.equals("horloge");
		boolean p2= mode.equals("chronometre");
		boolean p3= mode.equals("sablier");

		if (!p1 && !p2 && !p3) throw new Throwable ("-3.3");

		// Creer un modele configure
		//
		modele= new ModeleTempsG(configModele);

		// Ajouter le controleur comme observateur des modifications du modele 
		//
		modele.addObserver(this);

		// Controler l'absence eventuelle de vue
		//
		if (configVue == null) return;

		// Memoriser le dictionnaire de configuration de la vue
		//
		this.configVue= configVue;

		// Extraire de ce dictionnaire le nom symbolique de la classe 
		// qui construira la vue
		//
		String classeVue= (String)configModele.get("classeVue");
		if (classeVue == null) classeVue= "VueTempsG";

		// Creer la vue cible par usage de la reflexivite
		//
		Class cible= Class.forName("Vue."+classeVue);
		Constructor[] constructeurs= cible.getDeclaredConstructors();

		vue= (Observer) constructeurs[0].newInstance(f, configVue);

		// Ajouter la vue comme observateur des modifications du modele 
		//
		modele.addObserver(vue);
	}

	// ---                                            Accesseurs de consultation

	/**
	 * Gets the nom modele.
	 *
	 * @return the nom modele
	 */
	public String getNomModele () {return modele.getNomModele();}

	// ---                                            Accesseurs de modification

	/**
	 * Sets the butee.
	 *
	 * @param cible the cible
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean setButee(String cible) throws Throwable {

		// Controler la validite du parametre
		//
		if (cible == null) throw new Throwable ("-2.1");

		// Mettre en place la butee de temps dans le modele
		//
		return modele.setButee(cible);
	} 

	/**
	 * Sets the nom modele.
	 *
	 * @param nomS the nom s
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean setNomModele (String nomS) throws Throwable {

		// Controler la validite du parametre
		//
		if (nomS == null) throw new Throwable ("-2.1");

		// Mettre en place le nouveau nom symbolique dans le modele
		//
		return modele.setNomModele(nomS);
	}

	// ---                                                     Methode update

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object modifs) {} 

	// ---                                                    Methode demarrer

	/**
	 * Demarrer.
	 *
	 * @throws Throwable the throwable
	 */
	public void demarrer() throws Throwable {

		// Lancer le thread sous jacent
		//
		new Thread(modele).start();
	}

	/**
	 * Demarrer.
	 *
	 * @param tempsTotal the temps total
	 * @throws Throwable the throwable
	 */
	public void demarrer(int tempsTotal) throws Throwable {

		// Controler la validite du parametre
		//
		if (tempsTotal < 0) throw new Throwable ("-2.1");

		// Fixer le temps total du modele (cas du sablier)
		//
		modele.setTempsTotal(tempsTotal);

		// Lancer le thread sous jacent
		//
		new Thread(modele).start();
	} 

	// ---                                                     Methode stopper

	/**
	 * Stopper.
	 */
	public void stopper() {modele.resetStatus();}  

	// ---                                                   Methode suspendre

	/**
	 * Suspendre.
	 *
	 * @return true, if successful
	 */
	public boolean suspendre() {return modele.suspendre();}  

	// ---                                                   Methode reprendre

	/**
	 * Reprendre.
	 *
	 * @return true, if successful
	 */
	public boolean reprendre() {return modele.reprendre();}

	/**
	 * Reprendre.
	 *
	 * @param tempsTotal the temps total
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean reprendre(int tempsTotal) throws Throwable {

		// Controler la validite du parametre
		//
		if (tempsTotal < 0) throw new Throwable ("-2.1");

		// Controler le mode de fonctionnement courant
		//
		if (mode.equals("horloge")) return false;

		// Reprendre le decomptage (hors mode "horloge" !)
		//
		return modele.reprendre(tempsTotal);
	}

	// ---                                               Methode addObserver

	/**
	 * Adds the observer.
	 *
	 * @param obs the obs
	 */
	public void addObserver(Observer obs) {modele.addObserver(obs);} 
}
