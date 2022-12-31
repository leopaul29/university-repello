//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe ModeleTempsG : partie Modele (MVC) d'un compteur/decompteur de 
//                       temps
//
// Edition A        : mise en place du thread sous jacent et du design pattern
//                    Observer/Observable
//
//    + Version 1.0.0   : version initiale
//    + Version 1.1.0   : ajout d'une butee cible eventuelle pour fin  
//                        d'execution du thread, definie par parametre 
//                        de configuration et modifiable dynamiquement
//    + Version 1.2.0   : le modele de donnees transmet un dictionnaire de
//                        modifications a chaque notification pour pouvoir
//                        supporter des observations multiples eventuelles
//
// Edition B        : gestion de plusieurs modes de fonctionnement choisis par
//                    fichier de configuration (parametre mode)
//
//    + Version 2.0.0   :  introduction methode privee executerHorloge
//    + Version 2.1.0   :  introduction methode privee executerChronometre
//    + Version 2.2.0   :  introduction methode privee executerSablier
//                         + ajout d'un second constructeur normal
//    + Version 2.3.0   :  suppression du second constructeur normal
//                         + ajout accesseur setTempsTotal
//    + Version 2.4.0   :  introduction methodes suspendre et reprendre
//                         + ajout attribut statusModele
//    + Version 2.5.0   :  surcharge methode toString
//
// Edition C        : extensions de la parametrisation du composant
//
//    + Version 3.0.0   :  introduction du nom symbolique du modele dans
//                         le fichier de configuration du composant
//                         + ajout de l'attribut nomModele
//                         + ajout methodes getNomModele et setNomModele
//                         + modifications du constructeur normal et de la
//                           methode toString pour prendre en compte le 
//                           nouvel attribut
//                         + modification methode setButee pour controler
//                           la validite du parametre
//
// Auteur : A. Thuaire
//

package Modele;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ModeleTempsG.
 */
public class ModeleTempsG extends Observable implements Runnable {

	/** The temps courant. */
	private String tempsCourant= "0 : 00 : 00";

	/** The separateur. */
	private String separateur;

	/** The increment. */
	private Integer increment;

	/** The status thread. */
	private boolean statusThread= true;

	/** The butee cible. */
	private String buteeCible;

	/** The mode. */
	private String mode;

	/** The status modele. */
	private boolean statusModele= true;

	/** The nom modele. */
	private String nomModele;

	// ---                                                  Constructeur normal

	/**
	 * Instantiates a new modele temps g.
	 *
	 * @param config the config
	 * @throws Throwable the throwable
	 */
	public ModeleTempsG(HashMap config) throws Throwable {

		// Controler la valeur du parametre
		//
		if (config == null) config= new HashMap();

		// Extraire et memoriser le mode de fonctionnement
		//
		mode= (String)config.get("mode");
		if (mode == null) mode= "horloge";

		// Controler la validite du mode cible
		//
		boolean p1= mode.equals("horloge");
		boolean p2= mode.equals("chronometre");
		boolean p3= mode.equals("sablier");

		if (!p1 && !p2 && !p3) throw new Throwable ("-3.1");

		// Extraire et memoriser le separateur de champs
		//
		separateur= (String)config.get("separateur");
		if (separateur == null) separateur= " : ";

		// Extraire et memoriser l'unite de temps
		//
		increment= (Integer)config.get("increment");
		if (increment == null) increment= new Integer(1);

		// Extraire et memoriser la butee cible eventuelle
		//
		buteeCible= (String)config.get("butee");

		// Extraire et memoriser le nom symbolique du modele
		//
		nomModele= (String)config.get("nomModele");
		if (nomModele == null) nomModele= "ModeleTempsG/"+mode;  
	}

	// ---                                          Accesseurs de consultation

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()        {return nomModele;}   

	/**
	 * Gets the temps courant.
	 *
	 * @return the temps courant
	 */
	public String getTempsCourant() {return tempsCourant;}

	/**
	 * Gets the nom modele.
	 *
	 * @return the nom modele
	 */
	public String getNomModele()    {return nomModele;}

	// ---                                          Accesseurs de modification

	/**
	 * Reset status.
	 */
	public void resetStatus()       {statusThread= false;}

	/**
	 * Sets the butee.
	 *
	 * @param cible the cible
	 * @return true, if successful
	 */
	public boolean setButee(String cible) {

		if (cible != null) {buteeCible= cible; return true;}
		return false;
	}

	/**
	 * Sets the nom modele.
	 *
	 * @param nomS the nom s
	 * @return true, if successful
	 */
	public boolean setNomModele(String nomS) {

		if (nomS != null) {nomModele= nomS; return true;}
		return false; 
	}

	/**
	 * Sets the temps total.
	 *
	 * @param tempsTotal the new temps total
	 * @throws Throwable the throwable
	 */
	public void setTempsTotal(int tempsTotal) throws Throwable {

		// Controler la validite du parametre
		//
		if (tempsTotal < 0) throw new Throwable ("-2.1");

		// Decomposer le second parametre en heures, minutes et secondes
		//
		int heures= 0, minutes=0, secondes= 0;

		secondes= tempsTotal;

		if (secondes >= 60) { 	
			minutes= secondes / 60;
			secondes -= minutes*60; 
		}

		if (minutes >= 60) {
			heures= minutes / 60;
			minutes -= heures*60; 
		}

		// Memoriser le resultat dans l'attribut "tempsCourant"
		//
		tempsCourant= timeToString(separateur, heures, minutes, secondes); 
	}    

	// ---                                                         Methode run

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		boolean buteeAtteinte= false;

		while(statusThread) {

			// Controler le status du modele
			//
			if (statusModele) {

				// Acquerir et controler le mode de fonctionnement du modele
				//
				if (mode.equals("horloge")) 

					// Executer le mode "horloge"
					//
					executerHorloge();

				else if (mode.equals("chronometre"))

					// Executer le mode "chronometre"
					//
					executerChronometre();

				else 
					// Executer le mode "sablier"
					//
					executerSablier();

				// Etablir si la butee eventuelle a ete atteinte ou pas
				//
				buteeAtteinte= controleButee(separateur, tempsCourant, 
						buteeCible, increment);

				// Construire le dictionnaire des modifications
				//
				HashMap modifs= new HashMap();
				modifs.put("tempsCourant", tempsCourant);
				modifs.put("buteeAtteinte", new Boolean(buteeAtteinte));

				// Avertir les observateurs de toutes les modifications
				//            
				setChanged();
				notifyObservers(modifs);

				// Controler si la butee cible a ete atteinte
				//
				if (buteeAtteinte) {

					// Arreter le thread
					//
					resetStatus();
					break;
				}
			}

			// Suspendre le thread courant de la duree specifiee par le
			// parametre de configuration "increment"
			//
			try {Thread.sleep(increment.intValue()*1000);} 
			catch (InterruptedException e) {}
		}
	}

	// ---                                            Methode timeToString

	/**
	 * Time to string.
	 *
	 * @param separateur the separateur
	 * @param heures the heures
	 * @param minutes the minutes
	 * @param secondes the secondes
	 * @return the string
	 */
	private static String timeToString (String separateur,
			int heures, 
			int minutes,
			int secondes) {
		String resultat= "";

		resultat=  heures + separateur;	

		if (minutes < 10) resultat += "0" + minutes;
		else resultat += minutes;

		resultat += separateur;

		if (secondes < 10) resultat += "0" + secondes;
		else resultat += secondes;

		return resultat;	
	}

	// ---                                             Methode stringToTime

	/**
	 * String to time.
	 *
	 * @param separateur the separateur
	 * @param label the label
	 * @return the int
	 */
	private static int stringToTime (String separateur,
			String label) {
		int heures, minutes, secondes;

		// Fractionner le label en trois champs suivant le separateur
		// et convertir en numerique
		//
		String[] champsLabel= label.split(" : ");

		heures  = new Integer(champsLabel[0]).intValue();
		minutes = new Integer(champsLabel[1]).intValue();
		secondes= new Integer(champsLabel[2]).intValue();

		// Restituer le resultat
		//
		return 3600*heures + 60*minutes + secondes;	
	}

	// ---                                            Methode controleButee

	/**
	 * Controle butee.
	 *
	 * @param separateur the separateur
	 * @param temps the temps
	 * @param butee the butee
	 * @param increment the increment
	 * @return true, if successful
	 */
	private static boolean controleButee (String separateur,
			String temps,
			String butee,
			int increment) {

		// Controler la validite du second parametre
		//
		if (butee == null) return false;

		// Convertir le label du temps en numerique
		//
		int op1= stringToTime(separateur, temps);

		// Convertir le label de la butee en numerique
		//
		int op2= stringToTime(separateur, butee);

		// Restituer le resultat
		//
		return Math.abs(op1-op2) < increment;                                   	
	}

	// ---                                              Methode executerHorloge

	/**
	 * Executer horloge.
	 */
	private void executerHorloge() {
		Calendar calendrier; 
		int heures= 0, minutes=0, secondes= 0;

		// Acquerir et fractionner la description du temps
		//
		calendrier = Calendar.getInstance();	

		heures= calendrier.get(Calendar.HOUR_OF_DAY);
		minutes= calendrier.get(Calendar.MINUTE);
		secondes= calendrier.get(Calendar.SECOND);

		// Mettre a jour le temps courant
		//
		tempsCourant= timeToString(separateur, heures, minutes, secondes);
	} 

	// ---                                          Methode executerChronometre

	/**
	 * Executer chronometre.
	 */
	private void executerChronometre() {
		int heures= 0, minutes=0, secondes= 0;

		// Fractionner le label du temps courant en trois champs et
		// convertir en numerique
		//
		String[] champsTempsCourant= tempsCourant.split(" : ");

		heures  = new Integer(champsTempsCourant[0]).intValue();
		minutes = new Integer(champsTempsCourant[1]).intValue();
		secondes= new Integer(champsTempsCourant[2]).intValue();

		// Determiner le rafraichissement du champ de droite (secondes) 
		// du label tempsCourant
		//
		int deltaMinutes = 0;

		secondes += increment;

		if (secondes >= 60) { 	
			deltaMinutes= secondes / 60;
			secondes -= deltaMinutes*60; 
		}

		// Determiner le rafraichissement du champ du centre (minutes) 
		// du label tempsCourant
		//
		int deltaHeures = 0;

		minutes += deltaMinutes;
		if (minutes >= 60) {
			deltaHeures= minutes / 60;
			minutes -= deltaHeures*60; 
		}

		// Determiner le rafraichissement du champ de gauche (heures) 
		// du label tempsCourant
		//
		heures += deltaHeures;

		// Mettre a jour le temps courant
		//
		tempsCourant= timeToString(separateur, heures, minutes, secondes);
	}

	// ---                                             Methode executerSablier

	/**
	 * Executer sablier.
	 */
	private void executerSablier() {
		int heures= 0, minutes=0, secondes= 0;

		// Fractionner le label du temps courant en trois champs et
		// convertir en numerique
		//
		String[] champsTempsCourant= tempsCourant.split(" : ");

		heures  = new Integer(champsTempsCourant[0]).intValue();
		minutes = new Integer(champsTempsCourant[1]).intValue();
		secondes= new Integer(champsTempsCourant[2]).intValue();

		// Traiter le cas de l'arret du sablier pour temps disponible
		// epuise
		//
		if (heures == 0 && minutes == 0 && secondes == 0) resetStatus();

		// Determiner le rafraichissement du champ de droite (secondes) 
		// du label tempsCourant
		//
		int deltaMinutes = 0;

		secondes -= increment;

		if (secondes < 0) { 	
			deltaMinutes= 1;
			secondes= 59; 
		}

		// Determiner le rafraichissement du champ du centre (minutes) 
		// du label tempsCourant
		//
		int deltaHeures = 0;

		minutes -= deltaMinutes;
		if (minutes < 0) {
			deltaHeures= 1;
			minutes= 59; 
		}

		// Determiner le rafraichissement du champ de gauche (heures) 
		// du label tempsCourant
		//
		heures -= deltaHeures; 

		// Mettre a jour le temps courant
		//
		tempsCourant= timeToString(separateur, heures, minutes, secondes);
	}

	// ---                                                   Methode suspendre

	/**
	 * Suspendre.
	 *
	 * @return true, if successful
	 */
	public boolean suspendre() {

		// Controler l'etat courant
		//
		if (!statusModele) return false;

		// Placer le modele en etat inactif(suspendu)
		//
		statusModele= false;
		return true;
	}  

	// ---                                                   Methode reprendre

	/**
	 * Reprendre.
	 *
	 * @return true, if successful
	 */
	public boolean reprendre() {

		// Controler l'etat courant du modele
		//
		if (statusModele) return false;

		// Replacer le modele en etat actif
		//
		statusModele= true;
		return true;
	}

	/**
	 * Reprendre.
	 *
	 * @param tempsTotal the temps total
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean reprendre(int tempsTotal) throws Throwable {

		// Controler l'etat courant du modele
		//
		if (statusModele) return false;

		// Modifier le temps total
		//
		setTempsTotal(tempsTotal);

		// Replacer le modele en etat actif
		//
		statusModele= true;
		return true;
	}            
}
