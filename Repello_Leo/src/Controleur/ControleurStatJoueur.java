/*
 * 
 */
package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;

import Modele.Joueur;
import Vue.Demonstrateur;
import Vue.StatJoueur;

// TODO: Auto-generated Javadoc
/**
 * The Class ControleurStatJoueur.
 */
public class ControleurStatJoueur implements ActionListener {

	// ---														Attributs
	//
	/** The vue j. */
	private StatJoueur vueJ;

	/** The vue d. */
	private Demonstrateur vueD;

	/** The modele j. */
	private Joueur modeleJ;


	/**
	 * Constructeur controleur stat joueur.
	 * permet de créer le controleur pour gerer la vue avec le demonstrateur en modifiant le modele
	 *
	 * @param modeleJ the modele j
	 * @param vueJ the vue j
	 * @param vueD the vue d
	 * @throws Throwable 
	 */
	//---                                                  Constructeur normal 1
	public ControleurStatJoueur(Joueur modeleJ, StatJoueur vueJ, Demonstrateur vueD) throws Throwable{
		
		// Controler la validite des parametres
		//
		if (modeleJ == null) throw new Throwable("-2.1");
		if (vueJ == null) throw new Throwable("-2.2");
		if (vueD == null) throw new Throwable("-2.3");

		// Ajouter la vue du demonstrateur au controleur
		//
		this.vueD = vueD;

		// Creer la vue
		//
		this.vueJ = vueJ;
		
		// Creer le modele
		//
		this.modeleJ = modeleJ;
		
		// Ajouter la vue statJoueur comme observateur des modifications du modele 
		//
		modeleJ.addObserver(vueJ);
	}

	/**
	 * Ajouter repeller.
	 * permet d'ajouter un repeller au score du joueur
	 *
	 * @param joueurCouleur the joueur couleur
	 * @param couleurRepeller the couleur repeller
	 */
	//---                                                  Méthode ajouterRepeller
	public void ajouterRepeller(String joueurCouleur, String couleurRepeller) {
		
		// J'appel la methode ajouterRepeller du modeleJoueur
		//
		modeleJ.ajouterRepeller(joueurCouleur, couleurRepeller);
	}

	/**
	 * Retirer repeller.
	 * permet de retirer un repeller au score du joueur
	 *
	 * @param joueurCouleur the joueur couleur
	 * @param couleurRepeller the couleur repeller
	 */
	//---                                                  Méthode retirerRepeller
	public void retirerRepeller(String joueurCouleur, String couleurRepeller) {
		
		// J'appel la methode repellerZero pour vérifier si sa valeur est superieur à 0
		//
		if(!repellerZero(joueurCouleur, couleurRepeller))
			
			// J'appel la methode retirerRepeller du modeleJoueur
			//
			modeleJ.retirerRepeller(joueurCouleur, couleurRepeller);
	}

	/**
	 * Repeller zero.
	 * permet de verifier si la valeur du repeller cible est égale à zero
	 *
	 * @param joueurCouleur the joueur couleur
	 * @param couleurRepeller the couleur repeller
	 * @return true, if successful
	 */
	//---                                                  Méthode repellerZero
	public boolean repellerZero(String joueurCouleur, String couleurRepeller) {
		
		// J'appel la methode repellerZero du modeleJoueur
		//
		return modeleJ.repellerZero(joueurCouleur, couleurRepeller);
	}

	/**
	 * Voler.
	 * permet de savoir si un joueur peux voler un repeller dans les scores
	 *
	 * @param couleurDuVoleur the couleur du voleur
	 * @param couleurVole the couleur vole
	 * @param couleurRepeller the couleur repeller
	 */
	//---                                                  Méthode voler
	public boolean voler(String couleurDuVoleur, String couleurVole, String couleurRepeller){
		
		// J'appel la methode voler du modeleJoueur
		//
		return modeleJ.voler(couleurDuVoleur, couleurVole, couleurRepeller);
	}

	/**
	 * Voler repeller.
	 * permet de voler un repeller à un joueur
	 *
	 * @param couleurDuVoleur the couleur du voleur
	 * @param couleurVole the couleur vole
	 * @param couleurRepeller the couleur repeller
	 */
	//---                                                  Méthode volerRepeller
	public void volerRepeller(String couleurDuVoleur, String couleurVole, String couleurRepeller) {
		
		// J'appel la methode voler pour vérifier si un joueur peux voler un repeller à un autre
		//
		if(voler(couleurDuVoleur, couleurVole, couleurRepeller))
			
			// J'appel la methode volerRepeller du modeleJoueur
			//
			modeleJ.volerRepeller(couleurDuVoleur, couleurVole, couleurRepeller);
	}

	/**
	 * Gets the gagnant.
	 *
	 * @return the gagnant
	 */
	// ---												Méthode getGagnant
	public String getGagnant(){
		
		return modeleJ.getGagnant();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// si je click sur le bouton "+"
		//
		if(e.getSource() == vueD.getIncPoint())
		{
			// Je récupère le nom du joueur et celui de la piece a incrementer
			//
			String joueurCouleur = (String) vueD.getCb_StackPoint().getSelectedItem();
			String couleurRepeller = (String) vueD.getCb_jetonCible().getSelectedItem();

			// J'ajoute un repeller de la couleur choisi au score du joueur selectionné
			//
			ajouterRepeller(joueurCouleur, couleurRepeller);
			
			// Je modifie le label du demonstrateur pour afficher le gagnant
			//
			vueD.setLblJoueurGagnant(getGagnant());
		}
		
		// si je click sur le bouton "-"
		//
		if(e.getSource() == vueD.getDecPoint())
		{
			// Je récupère le nom du joueur et celui de la piece a incrementer
			//
			String joueurCouleur = (String) vueD.getCb_StackPoint().getSelectedItem();
			String couleurRepeller = (String) vueD.getCb_jetonCible().getSelectedItem();

			// Je retire un repeller de la couleur choisi au score du joueur selectionné
			//
			retirerRepeller(joueurCouleur, couleurRepeller);
			
			// Je modifie le label du demonstrateur pour afficher le gagnant
			//
			vueD.setLblJoueurGagnant(getGagnant());
		}
		
		// si je click sur le bouton "voler"
		//
		if(e.getSource() == vueD.getVoler())
		{
			// Je récupère le nom du voleur, celui du volé et le nom de la piece a incrementer
			//
			String couleurDuVoleur = (String) vueD.getCb_StackVoleur().getSelectedItem();
			String couleurRepeller = (String) vueD.getCb_JetonVole().getSelectedItem();
			String couleurVole = (String) vueD.getCb_StackVole().getSelectedItem();

			// Je vole un repeller de la couleur choisi au score du joueur selectionné
			//
			volerRepeller(couleurDuVoleur, couleurVole, couleurRepeller);
			
			// Je modifie le label du demonstrateur pour afficher le gagnant
			//
			vueD.setLblJoueurGagnant(getGagnant());
		}
	}
}
