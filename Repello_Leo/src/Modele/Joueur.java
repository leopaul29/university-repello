/*
 * 
 */
package Modele;

import java.util.HashMap;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * le Class Joueur.
 * permet de cr�er les modeleJoueur qui permet de gere les points de chaque joueurs.
 * 
 * @author LeoPaul MARTIN
 */
public class Joueur extends Observable{

	// ---											Attributs
	//
	/** le joueur r. */
	private ModeleJoueur joueurR;

	/** le joueur b. */
	private ModeleJoueur joueurB;

	/** le joueur v. */
	private ModeleJoueur joueurV;

	/** le joueur j. */
	private ModeleJoueur joueurJ;

	/** le les joueurs. */
	private HashMap <String, ModeleJoueur> lesJoueurs;

	/**
	 * Constructeur du modele de Modelejoueur.
	 * @throws Throwable 
	 */
	// ---											Constructeur par defaut
	public Joueur(HashMap <?,?> configRepello) throws Throwable{

		// Verifie si la config existe
		//
		if(configRepello == null) throw new Throwable ("-2.9");
		
		// Cr�er le HashMap qui va contenir les joueurs
		//
		lesJoueurs = new HashMap <String, ModeleJoueur> ();

		// Je recupere les donn�e des joueur dans la config
		//
		String nomJoueur_Rouge = (String)configRepello.get("Rouge");
		String nomJoueur_Bleu = (String)configRepello.get("Bleu");
		String nomJoueur_Vert = (String)configRepello.get("Vert");
		String nomJoueur_Jaune = (String)configRepello.get("Jaune");
		int valeurStack = (Integer)configRepello.get("stack");

		// Je cr�er les 4 joueur
		//
		joueurR = new ModeleJoueur(nomJoueur_Rouge, valeurStack);
		joueurB = new ModeleJoueur(nomJoueur_Bleu, valeurStack);
		joueurV = new ModeleJoueur(nomJoueur_Vert, valeurStack);
		joueurJ = new ModeleJoueur(nomJoueur_Jaune, valeurStack);
	}

	/**
	 * Initialisation de chaque modeleJoueur.
	 * @throws Throwable 
	 */
	// ---											Initialisation du modeleGrille
	public void initialiser() throws Throwable{

		// Remplis le HashMap avec les joueurs
		//
		lesJoueurs.put("Rouge", joueurR);
		lesJoueurs.put("Bleu", joueurB);
		lesJoueurs.put("Vert", joueurV);
		lesJoueurs.put("Jaune", joueurJ);
	}

	// ---												Getter && Setter
	//
	/**
	 * Gets le gagnant.
	 *
	 * @return le gagnant
	 */
	// ---												M�thode getGagnant
	public String getGagnant(){

		// Je r�cupere le score de chaque joueurs
		//
		int scoreR = getPoint("Rouge");
		int scoreB = getPoint("Bleu");
		int scoreV = getPoint("Vert");
		int scoreJ = getPoint("Jaune");

		// Je d�clare le scoreGagnant
		//
		int scoreGagnant = scoreR;

		/* Je compare le scoreGagnant � chaque joueurs
		 * si il est plus grand que le scoreGagnant
		 * alors le scoreGagnant est celui-ci
		 */
		if(scoreGagnant < scoreB)
			scoreGagnant = scoreB;
		if(scoreGagnant < scoreV)
			scoreGagnant = scoreV;
		if(scoreGagnant < scoreJ)
			scoreGagnant = scoreJ;

		// Je compare le scoreGagnant � celui de chaque joueur pour obtenir le joueur gagnant
		//
		if(scoreGagnant == scoreR)return lesJoueurs.get("Rouge").getJoueur();
		else if(scoreGagnant == scoreB)return lesJoueurs.get("Bleu").getJoueur();
		else if(scoreGagnant == scoreV)return lesJoueurs.get("Vert").getJoueur();
		else return lesJoueurs.get("Jaune").getJoueur();
	}

	/**
	 * Gets le joueur.
	 *
	 * @param joueurCouleur le joueur couleur
	 * @return le Modelejoueur
	 */
	// ---												M�thode getJoueur
	public ModeleJoueur getJoueur(String joueurCouleur){

		// retourne le modeleJoueur du joueur choisi
		//
		return lesJoueurs.get(joueurCouleur);
	}

	/**
	 * Gets la couleurs du joueur.
	 * permet d'obtenir le nom du joueur (ex : Rouge, Bleu, Vert, Jaune)
	 *
	 * @param joueurCouleur le joueur couleur
	 * @return la couleur du joueur
	 */
	// ---												M�thode getColJoueur
	public String getColJoueur(String joueurCouleur){

		// Retourne le nom du joueur choisi
		//
		return lesJoueurs.get(joueurCouleur).getJoueur();
	}

	/**
	 * Gets le point.
	 * permet d'obtenir le score du joueur.
	 *
	 * @param joueurCouleur le joueur couleur
	 * @return le point
	 */
	// ---												M�thode getPoint
	public int getPoint(String joueurCouleur){

		// Retourne le score total du joueur
		//
		return getJoueur(joueurCouleur).getNbPoints();
	}

	/**
	 * Ajouter repeller.
	 * permet d'ajouter un repeller � un joueur.
	 *
	 * @param joueurCouleur le joueur couleur
	 * @param couleurRepeller la couleur repeller
	 */
	// ---												M�thode AjouterRepeller
	public void ajouterRepeller(String joueurCouleur, String couleurRepeller) {

		// Ajoute un repeller au score d'un joueur
		//
		getJoueur(joueurCouleur).ajouterRepeller(couleurRepeller);
	}

	/**
	 * Retirer repeller.
	 * permet de retirer un repeller � un joueur.
	 *
	 * @param joueurCouleur le joueur couleur
	 * @param couleurRepeller le couleur repeller
	 */
	// ---												M�thode retirerRepeller
	public void retirerRepeller(String joueurCouleur, String couleurRepeller) {

		// Retire un repeller au score d'un joueur
		//
		getJoueur(joueurCouleur).retirerRepeller(couleurRepeller);
	}

	/**
	 * Repeller zero.
	 * permet de savoir le le joueur � zero repeller d'une couleur
	 *
	 * @param joueurCouleur le joueur couleur
	 * @param couleurRepeller le couleur repeller
	 * @return true, if successful
	 */
	// ---												M�thode repellerZero
	public boolean repellerZero(String joueurCouleur, String couleurRepeller){

		// Retourne true si le repeller choisi du joueur choisi est egale a zero
		//
		return getJoueur(joueurCouleur).repellerZero(couleurRepeller);
	}

	/**
	 * Voler.
	 * v�rifie si le joueur � voler possede plus de zero repeller de cette couleur.
	 *
	 * @param couleurDuVoleur le couleur du voleur
	 * @param couleurVole le couleur vole
	 * @param couleurRepeller le couleur repeller
	 * @return true, if successful
	 */
	// ---												M�thode voler
	public boolean voler(String couleurDuVoleur, String couleurVole, String couleurRepeller) {

		// Retourne true si on peux voler un repeller � ce joueur
		//
		return !repellerZero(couleurVole, couleurRepeller);
	}

	/**
	 * Voler repeller.
	 * permet de voler un joueur si celui-ci a plus de 0 repeller de cette couleur.
	 *
	 * @param couleurDuVoleur le couleur du voleur
	 * @param couleurVole le couleur vole
	 * @param couleurRepeller le couleur repeller
	 */
	// ---												M�thode volerRepeller
	public void volerRepeller(String couleurDuVoleur, String couleurVole, String couleurRepeller) {

		// Si je peux voler
		//
		if(voler(couleurDuVoleur, couleurVole, couleurRepeller))
		{
			// Je retire le repeller du joueur vol�
			//
			getJoueur(couleurVole).retirerRepeller(couleurRepeller);

			// J'ajoute le repeller au voleur
			//
			getJoueur(couleurDuVoleur).ajouterRepeller(couleurRepeller);
		}
	}

	// ---			CLASS INTERNE ModeleJoueur
	//
	//
	/**
	 * le Class ModeleJoueur.
	 * permet de cr�er le modeleJoueur.
	 * 
	 * @author Leopaul
	 */
	public class ModeleJoueur {

		/** le joueur couleur. */
		private String joueurCouleur;

		/** le nb stack. */
		private int nbStack;

		/** le nb noir. */
		private int nbNoir;

		/** le nb argent. */
		private int nbArgent;

		/** le nb or. */
		private int nbOr;


		/**
		 * Constructeur du Joueur.
		 *
		 * @param couleur le couleur
		 */
		// ---												Constructeur du joueur
		public ModeleJoueur(String couleur, int stack) {

			// Je d�fini le nom du joueur avec sa couleur
			//
			this.joueurCouleur = couleur;
			this.nbStack = stack;
		}

		// ---												Getter && Setter
		//
		/**
		 * Gets le joueur.
		 * Retourne le nom de la couleur du joueur
		 *
		 * @return le joueur
		 */
		// ---												M�thode getJoueur
		public String getJoueur() {

			// Retourne le nom du joueur
			//
			return this.joueurCouleur;
		}

		/**
		 * Gets le nb points.
		 *
		 * @return le nb points
		 */
		// ---												M�thode getNbPoint
		public int getNbPoints() {

			/* Je retourne les points du joueurs
			 * Repeller noir = 1 point
			 * Repeller argent = 3 points
			 * Repeller or = 5 points
			 */
			return getRepellerCible("jeton") + (getRepellerCible("jetonArgent") * 3) + (getRepellerCible("jetonOr") * 5);
		}

		/**
		 * Sets le joueur couleur.
		 *
		 * @param joueurCouleur la nouvelle couleur du joueur
		 */
		// ---												M�thode setJoueurCouleur
		public void setJoueurCouleur(String joueurCouleur) {

			// Changer le nom du joueur
			//
			this.joueurCouleur = joueurCouleur;
		}

		/**
		 * Gets le nb stack.
		 *
		 * @return le nb stack
		 */
		// ---												M�thode getNbStack
		public int getNbStack() {

			// Retourne le nombre du stack
			//
			return nbStack;
		}

		/**
		 * Sets le nb stack.
		 *
		 * @param nbStack le new nb stack
		 */
		// ---												M�thode setNbStack
		public void setNbStack(int nbStack) {

			// Modifie le nombre du stack
			//
			this.nbStack = nbStack;
		}

		/**
		 * Gets le nb noir.
		 *
		 * @return le nb noir
		 */
		// ---												M�thode getNbNoir
		public int getNbNoir() {

			// Retourne le nombre de repeller noir
			//
			return nbNoir;
		}

		/**
		 * Sets le nb noir.
		 *
		 * @param nbNoir le new nb noir
		 */
		// ---												M�thode setNbNoir
		public void setNbNoir(int nbNoir) {

			// Modifie le nombre de repeller noir
			//
			this.nbNoir = nbNoir;
		}

		/**
		 * Gets le nb argent.
		 *
		 * @return le nb argent
		 */
		// ---												M�thode getNbArgent
		public int getNbArgent() {

			// Retourne le nombre de repeller argent
			//
			return nbArgent;
		}

		/**
		 * Sets le nb argent.
		 *
		 * @param nbArgent le new nb argent
		 */
		// ---												M�thode setNbArgent
		public void setNbArgent(int nbArgent) {

			// Modifier le nombre de repeller argent
			//
			this.nbArgent = nbArgent;
		}

		/**
		 * Gets le nb or.
		 *
		 * @return le nb or
		 */
		// ---												M�thode getNbOr
		public int getNbOr() {

			// Retourne le nombre de repeller or
			//
			return nbOr;
		}

		/**
		 * Sets le nb or.
		 *
		 * @param nbOr le new nb or
		 */
		// ---												M�thode setNbOr
		public void setNbOr(int nbOr) {

			// Modifier le nombre de repeller argent
			//
			this.nbOr = nbOr;
		}

		/**
		 * Gets le repeller cible.
		 * permet de recuperer le nombre de repeller de la couleur choisi
		 *
		 * @param couleurRepeller le couleur repeller
		 * @return le repeller cible
		 */
		// ---												M�thode getRepellerCible
		public int getRepellerCible(String couleurRepeller) {

			// Retourne la valeur du repeller choisi
			//
			if(couleurRepeller == "stack")return this.nbStack;
			else if(couleurRepeller == "jeton") return this.nbNoir;
			else if(couleurRepeller == "jetonArgent") return this.nbArgent;
			else if(couleurRepeller == "jetonOr") return this.nbOr;

			else return 0;
		}

		/**
		 * Sets le repeller cible.
		 * permet de modifier le nombre de repeller de la couleur choisi
		 *
		 * @param point le point
		 * @param couleurRepeller le couleur repeller
		 */
		// ---												M�thode setRepellerCible
		public void setRepellerCible(int point, String couleurRepeller) {

			// Modifie la valeur du repeller choisi
			//
			if(couleurRepeller == "stackR" || couleurRepeller == "stackB" || couleurRepeller == "stackV" || couleurRepeller == "stackJ") this.nbStack += point;

			else if(couleurRepeller == "jeton") this.nbNoir += point;
			else if(couleurRepeller == "jetonArgent") this.nbArgent += point;
			else if(couleurRepeller == "jetonOr") this.nbOr += point;

			else return;
		}

		/**
		 * Ajouter repeller.
		 * permet d'ajouter un repeller de la couleur choisi au score du joueur
		 *
		 * @param couleurRepeller le couleur repeller
		 */
		// ---												M�thode ajouterRepeller
		public void ajouterRepeller(String couleurRepeller) {

			// Je rajoute 1 repeller dans le score du ModeleJoueur
			//
			setRepellerCible(1, couleurRepeller);

			// Je d�clare le nom du panneau cible de la vue a modifier
			//
			String panCible = couleurRepeller + getJoueur();

			// Je cr�er le HashMap pour modifier la vue des statJoueur
			//
			HashMap <String, Object> modifstat = new HashMap <String, Object> ();

			// J'ajoute a ce HashMap le nombre de point que je veux ajouter au panneauCible
			//
			modifstat.put("pts", 1);
			modifstat.put("panCible", panCible);

			// Je notify la vue statJoueur
			//
			setChanged();
			notifyObservers(modifstat);
		}

		/**
		 * Repeller zero.
		 * permet de retourner true si le repeller de la couleur choisi est � 0
		 *
		 * @param couleurRepeller le couleur repeller
		 * @return true, if successful
		 */
		// ---												M�thode repellerZero
		public boolean repellerZero(String couleurRepeller) {

			// Je retourne true si le repeller de la couleur choisi est � 0
			//
			return this.getRepellerCible(couleurRepeller) == 0;
		}

		/**
		 * Retirer repeller.
		 *
		 * @param couleurRepeller le couleur repeller
		 */
		// ---												M�thode retirerRepeller
		public void retirerRepeller(String couleurRepeller) {

			// Je v�rifie si le repeller de la couleur choisi est superieur � 0
			//
			if(!repellerZero(couleurRepeller))
			{
				// J'enleve 1 repeller dans le score du ModeleJoueur
				//
				setRepellerCible(-1, couleurRepeller);

				// Je d�clare le nom du panneau cible de la vue a modifier
				//
				String panCible = couleurRepeller + getJoueur();

				// Je cr�er le HashMap pour modifier la vue des statJoueur
				//
				HashMap <String, Object> modifstat = new HashMap <String, Object> ();

				// J'ajoute a ce HashMap le nombre de point que je veux ajouter au panneauCible
				//
				modifstat.put("pts", -1);
				modifstat.put("panCible", panCible);

				// Je notify la vue statJoueur
				//
				setChanged();
				notifyObservers(modifstat);
			}
		}
	}
}