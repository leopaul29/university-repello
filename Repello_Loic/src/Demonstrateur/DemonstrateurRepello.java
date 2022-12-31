/**
 * 
 * Université de Nice - Département Informatique
 * Année 2013-2014 - S3T
 * 
 * 
 * @version V0_0_0
 * 
 * Edition A : 
 *		+Version V0_0_1 : 
 * 
 * 
 * 
 * @author Loic
 * 
 */
package Demonstrateur;

import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JFrame;


/**
 * La  Class DemonstrateurRepello est le controleur de toute la démonstration de l'application Repello
 */
public class DemonstrateurRepello extends Observable {
	
	/** La vue du démonstrateur (extends JPanel) */
	private DemonstrateurVue pan_vue;
	
	/** La fenetre contenant la vue */
	private JFrame vue;

	
	/**
	 * Constructeur de demonstrateur repello.
	 *
	 * @param liste_joueur la liste des joueurs fournis par l'application Repello
	 */
	public DemonstrateurRepello(LinkedList<?> liste_joueur, HashMap<?, ?> configRepello){

		HashMap<?, ?> enregistrement = (HashMap<?, ?>) Config.load("_Config/config_demonstrateur", "V0_0_8");
		
		//Création de la vue jframe
		//
		vue = new JFrame();
		vue.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vue.setSize((Integer)enregistrement.get("largeur"),(Integer)enregistrement.get("hauteur"));
		vue.setResizable((Boolean) enregistrement.get("resize"));
		
		//Récupération des données de l'écran et placement de la fenetre en fonction
		//
		GraphicsEnvironment ecran =GraphicsEnvironment.getLocalGraphicsEnvironment();
		vue.setLocation((int) (ecran.getMaximumWindowBounds().getWidth() *31/40),
										(Integer) enregistrement.get("location"));
		
		//Création de la vue en lui passant la liste des joueurs
		//
		pan_vue = new DemonstrateurVue(this, liste_joueur, enregistrement);
		
		//Ajout des écouteurs sur la vue
		placer_ecouteur();
		
		//affichage de la vue
		vue.setContentPane(pan_vue);
		vue.setVisible(true);

	}
	
	// -------------------- Méthode main
	public static void main(String args[]) {
		LinkedList a = new LinkedList();
		DemonstrateurRepello dr = new DemonstrateurRepello(a, null);
		dr.vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * Getter de  fenetre.
	 *
	 * @return la fenetre
	 */
	public JFrame getFenetre(){ return vue;}


	// --------------------- Getters & Setters 


	/**
	 * Getter de pan_vue.
	 *
	 * @return la pan_vue
	 */
	public DemonstrateurVue getPan_vue() {return pan_vue;	}
	
	/**
	 * Setter de pan_vue.
	 *
	 * @param pan_vue le nouveau pan_vue
	 */
	public void setPan_vue(DemonstrateurVue pan_vue) {	this.pan_vue = pan_vue;}
	
	/**
	 * Getter de  vue.
	 *
	 * @return le vue
	 */
	public JFrame getVue() {	return vue;}
	
	/**
	 * Setter de vue.
	 *
	 * @param vue le nouveau vue
	 */
	public void setVue(JFrame vue) {	this.vue = vue;}



	// --------------------- placer ecouteur
	/**
	 * Placer_ecouteur, elle place les écouteurs sur tous les boutons fléchès dans la partis
	 * Placer_déplacer du démonstrateur
	 *
	 * @param pan_vue la vue disposant des boutons fléchès.
	 */
	public void placer_ecouteur(){
		pan_vue.placer_ecouteur();
	}

	// --------------------- set liste
	/**
	 * Update listes, change la valeur des listes contenant des coordonnées par la valeur 
	 * du clic souris de l'utilisateur sur l'application Repello
	 *
	 * @param x le x
	 * @param y le y
	 */
	public void updateListes(int x, int y){
		//Change tout les valeurs des listes déroulantes du démonstrateur par 
		//les deux paramètres (ils seront égales à la case sélectionné sur la vue de Repello)
		pan_vue.getComboBox_deplacer_x().setSelectedIndex(x);
		pan_vue.getComboBox_deplacer_y().setSelectedIndex(y);
		pan_vue.getComboBox_expulse_x().setSelectedIndex(x);
		pan_vue.getComboBox_expulse_y().setSelectedIndex(y);
		pan_vue.getComboBox_expulseur_x().setSelectedIndex(x);
		pan_vue.getComboBox_expulseur_y().setSelectedIndex(y);
		pan_vue.getComboBox_placer_repelleur_x().setSelectedIndex(x);
		pan_vue.getComboBox_placer_repelleur_y().setSelectedIndex(y);
		pan_vue.getComboBox_placer_stack_x().setSelectedIndex(x);
		pan_vue.getComboBox_placer_stack_y().setSelectedIndex(y);
		pan_vue.getComboBox_supprimer_x().setSelectedIndex(x);
		pan_vue.getComboBox_supprimer_y().setSelectedIndex(y);
	}

	
	
	/**
	 * Update au sens Observeur Observable
	 *
	 * @param action_info HashMap à transmettre aux observeur
	 */
	public void update(HashMap<?, ?> action_info) {
		setChanged();
		notifyObservers(action_info);
	}
}
