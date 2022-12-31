/*
 * Class Case
 */

package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;

// TODO: Auto-generated Javadoc
/**
 * Class Case extends Panel
 * permet de mettre une image de case et de mettre ou enlever une image de piece.
 *
 * @author LeoPaul MARTIN
 */

public class Case extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// ---											Attributs
	//
	/** Image de la case. */
	private Image 								imageCase;

	/** Image de la piece à placer sur la case (stack ou repeller). */
	private Image 								imagePiece;

	/** Si la Case doit posseder une piece. */
	private boolean 							possedePiece;

	// ---												Getter & Setter	
	//
	/**
	 * Vérifie si la case doit posseder une pièce.
	 *
	 * @return true, si la case doit posseder une pièce
	 */
	public boolean isPossedePiece() {

		// obtenir la valeur de "possedePiece"
		//
		return possedePiece;
	}

	/**
	 * Modifie la valeur de possedePiece.
	 *
	 * @param possedePiece the new possede piece
	 */
	public void setPossedePiece(boolean possedePiece) {

		// Modifier la valeur de "possederPiece"
		//
		this.possedePiece = possedePiece;
	}


	/**
	 * Constructeur normal 2,
	 * créer un Panel avec une image de case et une couleur de fond choisi en parametre.
	 *
	 * @param cheminImageCase le chemin de l'image de la case
	 * @param background la couleur du fond de la case
	 * @param numCase the num case
	 */
	//----                                              Constructeur normal 1
	public Case (String cheminImageCase, Color background) { 

		// J'appel le constructeur de Panel
		//
		super();

		// Je change le fond du panel avec la couleur choisi en parametre si il n'est pas null
		//
		if (background != null) setBackground(background);

		// Je charge l'image de la case choisi si le chemin image n'est pas null
		//
		if (cheminImageCase != null) chargerImageCase(cheminImageCase);
	}

	/**
	 * Méthode dessinerCase,
	 * permet de dessiner une case.
	 *
	 * @param g le dessin
	 */
	// --- 												Methode dessinerCase
	public void dessinerCase (Graphics g) {

		// Si l'image chargée n'est pas null, je defini la methode à utiliser dans paint
		//
		if (imageCase != null) 
			g.drawImage(imageCase, 0, 0, getWidth(), getHeight(), null);
	}

	/**
	 * Méthode dessinerPiece,
	 * permet de dessiner une piece.
	 *
	 * @param g le dessin
	 */
	// --- 												Methode dessinerRep
	public void dessinerPiece (Graphics g) {

		// Si l'image chargée n'est pas null, je defini la methode à utiliser dans paint
		//
		if (imagePiece != null) 
			g.drawImage(imagePiece, 0, 0, getWidth(), getHeight(), null);
	}

	/**
	 * Méthode paint,
	 * qui redéfini paint pour dessiner une case et une piece si la case en possede un.
	 *
	 * @param g le dessin
	 */
	// --- 											 	Methode paint
	public void paint(Graphics g) {

		// Je dessine l'image de la case
		//
		super.paint(g);
		dessinerCase(g);

		// Si la case doit posseder une piece, je la dessine
		//
		if (possedePiece) dessinerPiece(g);
	}

	/**
	 * Méthode chargerImageCase,
	 * permet de charger les images de case dans le dossier Images.
	 *
	 * @param cheminImageCase the chemin image case
	 */
	// ---                                           	Methode chargerImageCase
	private void chargerImageCase (String cheminImageCase) {

		// Controler la validite du parametre
		//
		if (cheminImageCase == null) return;

		// Charger une image depuis un fichier de type jpeg
		//
		imageCase = Toolkit.getDefaultToolkit().getImage("Images/"+cheminImageCase);

		// Construire un media tracker pour controler le chargement de l'image
		//
		MediaTracker mt= new MediaTracker(this);

		// Attendre la fin du chargement effectif de l'image
		//
		mt.addImage(imageCase,0);
		try{mt.waitForAll();}
		catch(Exception e){}
	}

	/**
	 * Méthode chargerImagePiece,
	 * permet de charger les images d'une piece dans le dossier Images.
	 *
	 * @param cheminImagePiece the chemin image piece
	 */
	// ---                                         		Methode chargerImageElement
	private void chargerImagePiece(String cheminImagePiece) {

		// Controler la validite du parametre
		//
		if (cheminImagePiece == null) return;

		// Charger une image depuis un fichier de type jpeg
		//
		imagePiece = Toolkit.getDefaultToolkit().getImage("Images/"+cheminImagePiece);

		// Construire un media tracker pour controler le chargement de l'image
		//
		MediaTracker mt= new MediaTracker(this);

		// Attendre la fin du chargement effectif de l'image
		//
		mt.addImage(imagePiece,0);
		try{mt.waitForAll();}
		catch(Exception e){}
	}

	/**
	 * Méthode ajouterPiece,
	 * permet d'ajouter l'image d'une piece sur une case.
	 *
	 * @param cheminImagePiece the chemin image piece
	 */
	// --- 												Methode ajouterElement
	public void ajouterPiece(String cheminImagePiece) {

		// Charger la nouvelle image
		//
		chargerImagePiece(cheminImagePiece);

		// Met le champ possedePiece a true pour pouvoir dessiner la piece
		//
		this.possedePiece = true;

		// Repeindre le panneau
		//	   
		repaint();
	}

	/**
	 * Méthode gererPiece,
	 * permet d'ajouter ou de retirer une piece sur une case si elle est occupée ou pas.
	 *
	 * @param cheminImagePiece the chemin image piece
	 */
	// ---												Méthode gererPiece
	public void gererPiece(String cheminImagePiece) {

		if(isPossedePiece())
		{	// Si la case possede une piece, alors on la dessine
			// Charger la nouvelle image
			//
			chargerImagePiece(cheminImagePiece);

			// Repeindre le panneau
			//	
			repaint();
		}
		else// sinon on redessine la case pour effacer la piece
			// Repeindre le panneau
			//	
			repaint();
	}

	/**
	 * Méthode retirerPiece,
	 * permet de retirer un element sur une case.
	 */
	// ---												Methode retirerElement
	public void retirerPiece() {

		this.possedePiece = false;

		// Repeindre le panneau
		//
		repaint();
	}
}
