/*
 * 
 */
package Modele;

import java.util.HashMap;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * Class ModeleGrille extends Observable
 * permet de cr�er une grille de ModeleCase, qui sont chacune observ�e par la vue.
 *
 * @author LeoPaul
 */
public class Grille extends Observable {

	// ---											Attributs
	//
	/** Tableau de ModeleCase. */
	private ModeleCase[][]	m_grille;

	// ---											Constructeur par defaut
	/**
	 * Constructeur par defaut,
	 * permet de construire un tableau de ModeleCase.
	 */
	public Grille() {m_grille = new ModeleCase[13][13];}//configuration

	/**
	 * M�thode deplacerPiece,
	 * permet de d�placer une piece d'une case � une autre
	 * combinaison des m�thodes retirerPiece et deplacerPiece.
	 *
	 * @param laPiece le la piece
	 * @param ligneOrig the ligne orig
	 * @param colonneOrig the colonne orig
	 * @param ligneDest the ligne dest
	 * @param colonneDest the colonne dest
	 */
	// ---											M�thode deplacerPiece
	public void deplacerPiece(String laPiece, int ligneOrig, int colonneOrig, int ligneDest, int colonneDest){

		/* Je v�rifie que l'ancienne case possede bien une piece
		 * et que la nouvelle case n'en possede pas
		 */
		if(!caseOccupee(ligneDest, colonneDest) && caseOccupee(ligneOrig, colonneOrig))
		{
			// je retirer la piece de l'ancienne case
			getCase(ligneOrig, colonneOrig).retirerPiece(ligneOrig, colonneOrig);
			/* je recupere la piece de l'ancienne case
			 * et je la place sur la nouvelle case
			 */
			getCase(ligneDest, colonneDest).placerPiece(ligneDest, colonneDest, obtenirPiece(ligneOrig, colonneOrig));
		}
	}

	/**
	 * M�thode placerPiece,
	 * permet de placer une piece d'un ModeleCase.
	 *
	 * @param ligne le ligne
	 * @param colonne le colonne
	 * @param couleur la couleur
	 */
	// ---											M�thode placerPiece
	public void placerPiece( int ligne, int colonne, String couleur){

		// ajoute une piece sur la case
		//
		getCase(ligne, colonne).placerPiece(ligne, colonne, couleur);
	}

	/**
	 * M�thode retirerPiece,
	 * permet de retirer une piece d'un ModeleCase.
	 *
	 * @param ligne la ligne
	 * @param colonne la colonne
	 */
	// ---											M�thode retirerPiece
	public void retirerPiece(int ligne, int colonne){

		// retire une piece sur la case
		//
		getCase(ligne, colonne).retirerPiece(ligne, colonne);
		getCase(ligne, colonne).setNomPiece(null);
	}

	/**
	 * Expulsable.
	 * verifie si la piece est expulsable
	 *
	 * @param ligneExpulseur le ligne expulseur
	 * @param colonneExpulseur le colonne expulseur
	 * @param ligneExpulse le ligne expulse
	 * @param colonneExpulse le colonne expulse
	 * @return true, if successful
	 */
	// ---											M�thode expulsable
	public boolean expulsable(int ligneExpulseur, int colonneExpulseur, int ligneExpulse, int colonneExpulse){

		//configuration
		if(ligneExpulse == 0 && ligneExpulseur == 1)return true;
		if(ligneExpulse == 12 && ligneExpulseur == 11)return true;
		if(colonneExpulse == 0 && colonneExpulseur == 1)return true;
		if(colonneExpulse == 12 && colonneExpulseur == 11)return true;

		return false;
	}

	/**
	 * Expulser.
	 * expulser la piece
	 *
	 * @param ligneExpulseur le ligne expulseur
	 * @param colonneExpulseur le colonne expulseur
	 * @param ligneExpulse le ligne expulse
	 * @param colonneExpulse le colonne expulse
	 */
	// ---											M�thode expulser
	public void expulser(int ligneExpulseur, int colonneExpulseur, int ligneExpulse, int colonneExpulse){

		// Verifie si une piece peut etre expuls�
		//
		if(expulsable(ligneExpulseur, colonneExpulseur, ligneExpulse, colonneExpulse))
			retirerPiece(ligneExpulse, colonneExpulse);
	}

	// ---											Getter && Setter
	//
	/**
	 * M�thode caseOccupee,
	 * permet de savoir si une case de la grille est occup�e.
	 *
	 * @param ligne la ligne
	 * @param colonne la colonne
	 * @return true, if successful
	 */
	// ---												M�thode caseOccupee
	public boolean caseOccupee(int ligne, int colonne){

		return getCase(ligne, colonne).isOccupee();
	}

	/**
	 * M�thode obtenirPiece,
	 * permet d'obtenir le nom de la piece d'un ModeleCase.
	 *
	 * @param ligne la ligne
	 * @param colonne la colonne
	 * @return le string
	 */
	// ---												M�thode obtenirPiece
	public String obtenirPiece(int ligne, int colonne){

		return getCase(ligne, colonne).getNomPiece();
	}

	/**
	 * Gets le largeur.
	 *
	 * @return le largeur
	 */
	// ---												M�thode getLargeur
	public int getLargeur(){

		return m_grille.length-1;
	}

	/**
	 * Gets le hauteur.
	 *
	 * @return le hauteur
	 */
	// ---												M�thode getHauteur
	public int getHauteur(){

		return m_grille[0].length-1;
	}

	/**
	 * Gets le grille.
	 *
	 * @return le grille
	 */
	// ---												M�thode getGrille
	public ModeleCase[][] getGrille(){

		return m_grille;
	}

	/**
	 * Gets le case.
	 *
	 * @param ligne la ligne
	 * @param colonne la colonne
	 * @return le case
	 */
	// ---												M�thode getCase
	public ModeleCase getCase(int ligne, int colonne){

		return m_grille[ligne][colonne];
	}

	/**
	 * Gets le num case.
	 *
	 * @param ligne la ligne
	 * @param colonne la colonne
	 * @return le num case
	 */
	// ---												M�thode getNumCase
	public int getNumCase(int ligne, int colonne){

		return ((ligne*13)+colonne);
	}

	/**
	 * M�thode initialiser,
	 * permet d'initialiser la grille avec un ModeleCase par case.
	 *
	 * @param configRepello the config repello
	 */
	// ---												M�thode initialiser
	public void initialiser(HashMap<?,?> configRepello){

		int Largeur = (Integer) configRepello.get("Largeur");
		int Hauteur = (Integer) configRepello.get("Hauteur");

		for(int i = 0; i<Largeur; i++)
		{
			for(int j = 0; j<Hauteur; j++)
			{
				m_grille[j][i]= new ModeleCase(j,i);
			}
		}
	}

	// ---			CLASS INTERNE ModeleCase
	//
	//
	/**
	 * Class interne ModeleCase,
	 * permet de definir un ModeleCase.
	 *
	 * @author LeoPaul
	 */
	private class ModeleCase{

		// ---												Attributs
		//
		/** Si le ModeleCase est occup� ou pas. */
		private boolean m_occupee;

		/** Le nom de la piece qui occupe le ModeleCase. */
		private String nomPiece;

		// ---										Getter & Setter
		//
		/**
		 * Checks if is occupee.
		 *
		 * @return true, if is occupee
		 */
		// ---												M�thode isOccupee
		public boolean isOccupee() {
			
			return m_occupee;
		}

		/**
		 * Sets le occupee.
		 *
		 * @param m_occupee le new occupee
		 */
		// ---												M�thode setOccupee
		public void setOccupee(boolean m_occupee) {
			
			this.m_occupee = m_occupee;
		}

		/**
		 * Gets le nom piece.
		 *
		 * @return le nom piece
		 */
		// ---												M�thode getNomPiece
		public String getNomPiece() {
			
			return nomPiece;
		}

		/**
		 * Sets le nom piece.
		 *
		 * @param nomPiece le new nom piece
		 */
		// ---												M�thode setNomPiece
		public void setNomPiece(String nomPiece) {
			
			this.nomPiece = nomPiece;
		}

		/**
		 * Constructeur de ModeleCase.
		 *
		 * @param ligne le ligne
		 * @param colonne le colonne
		 */
		// ---												M�thode ModeleCase
		public ModeleCase(int ligne, int colonne) {
			
		}

		/**
		 * M�thode isPlacerPiece,
		 * permet de savoir si on peut placer un piece sur le ModeleCase.
		 *
		 * @return true, if is placer piece
		 */
		// ---												M�thode isPlacerPiece
		public boolean isPlacerPiece(){
			/* Si le ModeleCase est occup� alors on ne peut pas placer de piece
			 * sinon on peut placer une piece sur le ModeleCase 
			 */
			return !isOccupee();
		}

		/**
		 * M�thode placerPiece,
		 * permet de placer une piece sur le ModeleCase.
		 *
		 * @param ligne le ligne
		 * @param colonne le colonne
		 * @param couleur la couleur
		 */
		// ---												M�thode placerPiece
		public void placerPiece(int ligne, int colonne, String couleur){

			// Si isPlacerPiece return true alors on peux placer une piece
			//
			if(isPlacerPiece())
			{
				// Je cr�e un HahsMap qui me sert � notifier la vuePlateau
				//
				HashMap <String, Object> modifs = new HashMap <String, Object> ();

				/* Dans ce HashMap, je met :
				 * Si je peux placer une piece dessus,
				 * Le nom de la piece,
				 * La case � modifier
				 */
				modifs.put("gererPiece", isPlacerPiece());
				modifs.put("piece", couleur);
				modifs.put("case", Grille.this.getNumCase(ligne,colonne));

				// Je notifi la vue qui observe
				//
				setChanged();
				notifyObservers(modifs);

				// Le ModeleCase est occup� et possede une piece
				//
				this.setOccupee(isPlacerPiece());
				this.nomPiece = couleur;
			}
		}

		/**
		 * M�thode isRetirerPiece,
		 * permet de savoir si je peux retirer la piece du ModeleCase.
		 *
		 * @return true, if is retirer piece
		 */	
		// ---												M�thode isRetirerPiece
		public boolean isRetirerPiece(){

			// Retourne si la case est occupee ou non
			//
			return isOccupee();
		}

		/**
		 * M�thode retirerPiece,
		 * permet de retirer une piece sur le ModeleCase.
		 *
		 * @param ligne la ligne
		 * @param colonne la colonne
		 */
		// ---												M�thode retirerPiece
		public void retirerPiece(int ligne, int colonne){

			// Si isRetirerPiece return true alors on peux retirer la piece
			//
			if(isRetirerPiece())
			{
				// Je cr�e un HahsMap qui me sert � notifier la vuePlateau
				//
				HashMap <String, Object> modifs = new HashMap <String, Object> ();

				/* Dans ce HashMap je met :
				 * Si je peux retirer la piece,
				 * La case � modifier
				 */
				modifs.put("gererPiece", !isRetirerPiece());
				modifs.put("case", Grille.this.getNumCase(ligne,colonne));

				// Je notifi la vue qui observe
				//
				setChanged();
				notifyObservers(modifs);

				// Le ModeleCase n'est plus ocup�e
				//
				this.setOccupee(!isRetirerPiece());
			}
		}
	}

}
