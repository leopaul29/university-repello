/*
 * 
 */
package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import Modele.Grille;
import Vue.Demonstrateur;
import Vue.Plateau;


// TODO: Auto-generated Javadoc
/**
 * le Class Controleur.
 */
public class ControleurPlateau implements Observer, ActionListener {

	// ---												Attributs
	//
	/** le modele g. */
	private Grille modeleG;

	/** le vue d. */
	private Demonstrateur vueD;

	/**
	 * Instantiates a new controleur.
	 *
	 * @param modeleG le modele g
	 * @param vueP le vue p
	 * @param vueD le vue d
	 * @throws Throwable le throwable
	 */
	// ---												Constructeur normal 1
	public ControleurPlateau(Grille modeleG, Plateau vueP, Demonstrateur vueD) throws Throwable {

		// Controler la validite des parametres
		//
		if (modeleG == null) throw new Throwable("-2.1");
		if (vueP == null) throw new Throwable("-2.2");
		if (vueD == null) throw new Throwable("-2.3");

		// Ajouter la vue du demonstrateur au controleur
		//
		this.vueD = vueD;

		// Creer le modele
		//
		this.modeleG = modeleG;

		// Creer la vue
		//
		this.vueD = vueD;

		// Ajouter le controleur comme observateur des modifications du modele 
		//
		modeleG.addObserver(this);

		// Ajouter la vue comme observateur des modifications du modele 
		//
		modeleG.addObserver(vueP);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		// si je click sur le bouton "placer piece"
		//
		if(e.getSource() == vueD.getPlacerPiece())
		{
			// Je recupere les coordonnée choisi dans les combobox
			//
			int X = (Integer) vueD.getCbX_placerPiece().getSelectedItem();
			int Y = (Integer) vueD.getCbY_placerPiece().getSelectedItem();
			
			// Je place le repeller de la case selectionné
			//
			placerPiece((String)vueD.getCbColorStack_placer().getSelectedItem(), (X-1), (Y-1));
		}

		// si je click sur le bouton  "retirer piece"
		//
		if(e.getSource() == vueD.getRetirerPiece())
		{
			// Je recupere les coordonnée choisi dans les combobox
			//
			int X = (Integer) vueD.getCbX_retirerPiece().getSelectedItem();
			int Y = (Integer) vueD.getCbY_retirerPiece().getSelectedItem();
			
			// Je retire le repeller de la case selectionné
			//
			retirerPiece((X-1), (Y-1));
		}

		// si je click sur le bouton "deplacer piece"
		//
		if(e.getSource() == vueD.getDeplacerPiece())
		{
			// Je recupere les coordonnée de la case d'origine choisi dans les combobox
			//
			int Xa = (Integer) vueD.getCbX_AncienDep().getSelectedItem();
			int Ya = (Integer) vueD.getCbY_AncienDep().getSelectedItem();

			// Je recupere les coordonnée de la case d'arrivé choisi dans les combobox
			//
			int Xn = (Integer) vueD.getCbX_NouvelleDep().getSelectedItem();
			int Yn = (Integer) vueD.getCbY_NouvelleDep().getSelectedItem();

			// Je récupère le nom de la piece de la case d'origine
			//
			String laPiece =  obtenirPiece(Xa, Ya);

			// Je déplace la piece de la case d'origine sur la case d'arrivé
			//
			deplacerPiece(laPiece, (Xa-1), (Ya-1), (Xn-1), (Yn-1));
		}

		// si je click sur le bouton "expulser"
		//
		if(e.getSource() == vueD.getExpulser())
		{
			// Je récupère le nom de la piece de la piece a expulser
			//
			int ligneExpulse = (Integer) vueD.getCbX_expulse().getSelectedItem();
			int colonneExpulse = (Integer) vueD.getCbY_expulse().getSelectedItem();

			// Je récupère le nom de la piece de la piece expulseur
			//
			int ligneExpulseur = (Integer) vueD.getCbX_expulseur().getSelectedItem();
			int colonneExpulseur = (Integer) vueD.getCbY_expulseur().getSelectedItem();

			// J'expulse la piece
			//
			expulser((ligneExpulseur-1), (colonneExpulseur-1), (ligneExpulse-1), (colonneExpulse-1));
		}
	}

	/**
	 * Placer piece.
	 * permet de placer une piece sur le plateau
	 *
	 * @param piece le piece
	 * @param ligne la ligne
	 * @param colonne la colonne
	 */
	// ---												Méthode placerPiece
	public void placerPiece(String piece, int ligne, int colonne){
		
		// J'appel la methode placerPiece du modeleGrille
		//
		modeleG.placerPiece(ligne, colonne, piece);
	}

	/**
	 * Retirer piece.
	 * permet de retirer une piece sur le plateau
	 *
	 * @param ligne la ligne
	 * @param colonne la colonne
	 */
	// ---												Méthode retirerPiece
	public void retirerPiece(int ligne, int colonne){
		
		// J'appel la methode retirerPiece du modeleGrille
		//
		modeleG.retirerPiece(ligne, colonne);
	}


	/**
	 * Deplacer piece.
	 * permet de deplacer une piece sur le plateau
	 *
	 * @param piece the piece
	 * @param ligneOrig the ligne orig
	 * @param colonneOrig the colonne orig
	 * @param ligneDest the ligne dest
	 * @param colonneDest the colonne dest
	 */
	// ---												Méthode deplacerPiece
	public void deplacerPiece(String piece, int ligneOrig, int colonneOrig, int ligneDest, int colonneDest){
		
		// J'appel la methode deplacerPiece du modeleGrille
		//
		modeleG.deplacerPiece(piece, ligneOrig, colonneOrig, ligneDest, colonneDest);
	}

	/**
	 * Obtenir piece.
	 * permet la piece sur la case
	 *
	 * @param ligne la ligne
	 * @param colonne la colonne
	 * @return le string
	 */
	// ---												Méthode obtenirPiece
	public String obtenirPiece(int ligne, int colonne){
		
		// J'appel la methode obtenirPiece du modeleGrille
		//
		return modeleG.obtenirPiece(ligne, colonne);
	}

	/**
	 * Case occupee.
	 * permet lde savoir si la case est occupee
	 *
	 * @param ligne la ligne
	 * @param colonne la colonne
	 * @return true, if successful
	 */
	// ---												Méthode caseOccupee
	public boolean caseOccupee(int ligne, int colonne){
		
		// J'appel la methode caseOccupee du modeleGrille
		//
		return modeleG.caseOccupee(ligne, colonne);
	}

	/**
	 * Expulser.
	 * permet d'expulser une piece hors du plateau
	 *
	 * @param ligneExpulseur le ligne expulseur
	 * @param colonneExpulseur le colonne expulseur
	 * @param ligneExpulse le ligne expulse
	 * @param colonneExpulse le colonne expulse
	 */
	// ---												Méthode expulser
	public void expulser(int ligneExpulseur, int colonneExpulseur, int ligneExpulse, int colonneExpulse){
		
		// J'appel la methode expulser du modeleGrille
		//
		modeleG.expulser(ligneExpulseur, colonneExpulseur, ligneExpulse, colonneExpulse);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
