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

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import Repello.*;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class DemonstrateurVue extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<?> comboBox_donner;
	private JButton button_donner_ok;
	private JComboBox<String> comboBox_prendre;
	private JButton button_prendre_ok;
	private JComboBox<Integer> comboBox_expulseur_y;
	private JComboBox<Integer> comboBox_expulseur_x;
	private JButton button_expulser_ok ;
	private JButton button_gagne;
	private JComboBox<Integer> comboBox_placer_repelleur_x;
	private JComboBox<Integer> comboBox_placer_repelleur_y;
	private JButton button_placer_repelleur_ok;
	private JComboBox<Integer> comboBox_placer_stack_y;
	private JComboBox<Integer> comboBox_placer_stack_x;
	private JButton button_placer_stack_ok;
	private JComboBox<Integer> comboBox_deplacer_x;
	private JComboBox<Integer> comboBox_deplacer_y;
	private MButton button_deplacer_HG;
	private MButton button_deplacer_H;
	private MButton button_deplacer_HD;
	private MButton button_deplacer_G;
	private MButton button_deplacer_ok;
	private MButton button_deplacer_D;
	private MButton button_deplacer_BG;
	private MButton button_deplacer_B;
	private MButton button_deplacer_BD;
	private JComboBox<Integer> comboBox_deplacer_valeur;
	private JTabbedPane tabbedPane ;
	private DemonstrateurRepello createur;
	private JComboBox<String> comboBox_joueur;
	private JComboBox<?> comboBox_prendre_pion;
	private JButton button_prendre_prendre;
	private LinkedHashMap<String, String> nom_joueur;
	private JComboBox<Integer> comboBox_expulse_x;
	private JComboBox<Integer> comboBox_expulse_y;
	private JLabel lblSupprimer;
	private JComboBox<Integer> comboBox_supprimer_y;
	private JLabel label_2;
	private JLabel label_17;
	private JComboBox<Integer> comboBox_supprimer_x;
	private JButton button_supprimer_ok;
	private JComboBox<Integer> comboBox_repelleur_pion;
	private MButton btn_sablier;


	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DemonstrateurVue(DemonstrateurRepello createur, LinkedList<?> liste_joueur, HashMap enregistrement) {



		HashMap image = (HashMap) enregistrement.get("image");
		HashMap taille = (HashMap) enregistrement.get("taille");
		HashMap nom_item = (HashMap) enregistrement.get("nom_item");

		Integer taille_ordonnee = (Integer) taille.get("taille_ordonnee");
		Integer taille_deplacement = (Integer) taille.get("taille_deplacement");




		this.setCreateur(createur);
		setLayout(null);
		nom_joueur = new LinkedHashMap<String, String>();
		creer_nomJoueur(liste_joueur, nom_joueur);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 50, 265, 441);
		add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab((String) nom_item.get("action"), null, panel, null);
		panel.setLayout(null);

		JLabel label = new JLabel((String) nom_item.get("donner"));
		label.setBounds(14, 11, 60, 14);
		panel.add(label);

		comboBox_donner = new JComboBox();
		comboBox_donner.setModel(new DefaultComboBoxModel(new String[] {"or", "argent", "noir"}));
		comboBox_donner.setBounds(41, 32, 103, 20);
		panel.add(comboBox_donner);

		button_donner_ok = new JButton((String) nom_item.get("button_donner"));
		button_donner_ok.setBounds(168, 25, 82, 35);
		panel.add(button_donner_ok);

		JLabel label_1 = new JLabel((String) nom_item.get("prendre"));
		label_1.setBounds(14, 71, 60, 14);
		panel.add(label_1);

		comboBox_prendre = new JComboBox<String>();
		comboBox_prendre.setBounds(41, 92, 103, 20);
		panel.add(comboBox_prendre);
		remplirListeJoueur(comboBox_prendre, liste_joueur);

		button_prendre_ok = new JButton("OK");
		button_prendre_ok.setBounds(168, 92, 82, 20);
		panel.add(button_prendre_ok);

		JLabel lblRepousserPion = new JLabel((String) nom_item.get("repousser"));
		lblRepousserPion.setBounds(10, 157, 113, 14);
		panel.add(lblRepousserPion);

		comboBox_expulseur_y = new JComboBox<Integer>();
		comboBox_expulseur_y.setBounds(62, 251, 44, 20);
		panel.add(comboBox_expulseur_y);

		comboBox_expulseur_x = new JComboBox<Integer>();
		comboBox_expulseur_x.setBounds(62, 220, 44, 20);
		panel.add(comboBox_expulseur_x);


		JLabel label_3 = new JLabel("x : ");
		label_3.setBounds(37, 223, 21, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("y : ");
		label_4.setBounds(37, 246, 21, 14);
		panel.add(label_4);

		button_expulser_ok = new JButton((String) nom_item.get("button_expulser"));
		button_expulser_ok.setBounds(195, 233, 55, 38);
		panel.add(button_expulser_ok);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab((String) nom_item.get("placer_deplacer"), null, panel_1, null);
		panel_1.setLayout(null);

		JLabel label_5 = new JLabel((String) nom_item.get("placer_repelleur"));
		label_5.setBounds(17, 11, 107, 23);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("x : ");
		label_6.setBounds(42, 67, 21, 14);
		panel_1.add(label_6);

		JLabel label_7 = new JLabel("y : ");
		label_7.setBounds(42, 92, 21, 14);
		panel_1.add(label_7);

		comboBox_placer_repelleur_x = new JComboBox<Integer>();
		comboBox_placer_repelleur_x.setBounds(67, 64, 44, 20);
		panel_1.add(comboBox_placer_repelleur_x);

		comboBox_placer_repelleur_y = new JComboBox<Integer>();
		comboBox_placer_repelleur_y.setBounds(67, 92, 44, 20);
		panel_1.add(comboBox_placer_repelleur_y);

		button_placer_repelleur_ok = new JButton((String) nom_item.get("button_repelleur"));
		button_placer_repelleur_ok.setBounds(179, 67, 55, 38);
		panel_1.add(button_placer_repelleur_ok);

		JLabel label_8 = new JLabel("x : ");
		label_8.setBounds(42, 146, 21, 14);
		panel_1.add(label_8);

		JLabel label_9 = new JLabel("y : ");
		label_9.setBounds(42, 169, 21, 14);
		panel_1.add(label_9);

		comboBox_placer_stack_y = new JComboBox<Integer>();
		comboBox_placer_stack_y.setBounds(67, 166, 44, 20);
		panel_1.add(comboBox_placer_stack_y);

		comboBox_placer_stack_x = new JComboBox<Integer>();
		comboBox_placer_stack_x.setBounds(67, 143, 44, 20);
		panel_1.add(comboBox_placer_stack_x);

		button_placer_stack_ok = new JButton((String) nom_item.get("button_stack"));
		button_placer_stack_ok.setBounds(176, 146, 55, 38);
		panel_1.add(button_placer_stack_ok);

		JLabel label_10 = new JLabel((String) nom_item.get("placer_stack"));
		label_10.setBounds(17, 117, 107, 23);
		panel_1.add(label_10);

		JLabel label_11 = new JLabel((String) nom_item.get("deplacer_pion"));
		label_11.setBounds(10, 194, 94, 14);
		panel_1.add(label_11);

		JLabel label_12 = new JLabel("x : ");
		label_12.setBounds(35, 219, 21, 14);
		panel_1.add(label_12);

		JLabel label_13 = new JLabel("y : ");
		label_13.setBounds(35, 247, 21, 14);
		panel_1.add(label_13);

		comboBox_deplacer_x = new JComboBox<Integer>();
		comboBox_deplacer_x.setBounds(60, 216, 44, 20);
		panel_1.add(comboBox_deplacer_x);

		comboBox_deplacer_y = new JComboBox<Integer>();
		comboBox_deplacer_y.setBounds(60, 244, 44, 20);
		panel_1.add(comboBox_deplacer_y);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(114, 194, 131, 120);
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 3));

		button_deplacer_HG = new MButton("",(String) image.get("image_fleche_hg"));
		panel_2.add(button_deplacer_HG);

		button_deplacer_H = new MButton("",(String) image.get("image_fleche_h"));
		panel_2.add(button_deplacer_H);

		button_deplacer_HD = new MButton("",(String) image.get("image_fleche_hd"));
		panel_2.add(button_deplacer_HD);

		button_deplacer_G = new MButton("",(String) image.get("image_fleche_g"));
		panel_2.add(button_deplacer_G);

		button_deplacer_ok = new MButton("OK","ok.png");
		panel_2.add(button_deplacer_ok);

		button_deplacer_D = new MButton("",(String) image.get("image_fleche_d"));
		panel_2.add(button_deplacer_D);

		button_deplacer_BG = new MButton("",(String) image.get("image_fleche_bg"));
		panel_2.add(button_deplacer_BG);

		button_deplacer_B = new MButton("",(String) image.get("image_fleche_b"));
		panel_2.add(button_deplacer_B);

		button_deplacer_BD = new MButton("",(String) image.get("image_fleche_bd"));
		panel_2.add(button_deplacer_BD);

		comboBox_deplacer_valeur = new JComboBox<Integer>();
		comboBox_deplacer_valeur.setBounds(60, 275, 44, 20);
		panel_1.add(comboBox_deplacer_valeur);

		JLabel label_14 = new JLabel("de :");
		label_14.setBounds(35, 272, 21, 14);
		panel_1.add(label_14);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 43, 286, 489);
		add(panel_3);


		remplirListeNombre(comboBox_placer_repelleur_x,taille_ordonnee);
		remplirListeNombre(comboBox_placer_repelleur_y,taille_ordonnee);
		remplirListeNombre(comboBox_placer_stack_x,taille_ordonnee);
		remplirListeNombre(comboBox_placer_stack_y,taille_ordonnee);
		remplirListeNombre(comboBox_deplacer_x,taille_ordonnee);
		remplirListeNombre(comboBox_deplacer_y,taille_ordonnee);
		remplirListeNombre(comboBox_deplacer_valeur,taille_deplacement);

		comboBox_repelleur_pion = new JComboBox();
		comboBox_repelleur_pion.setModel(new DefaultComboBoxModel(new String[] {"noir", "or", "argent"}));
		comboBox_repelleur_pion.setBounds(66, 33, 107, 23);
		panel_1.add(comboBox_repelleur_pion);

		JLabel lblPion = new JLabel("pion :");
		lblPion.setBounds(27, 37, 36, 14);
		panel_1.add(lblPion);
		remplirListeNombre(comboBox_expulseur_x,taille_ordonnee);
		remplirListeNombre(comboBox_expulseur_y,taille_ordonnee);

		comboBox_prendre_pion = new JComboBox();
		comboBox_prendre_pion.setModel(new DefaultComboBoxModel(new String[] {"or", "argent", "noir"}));
		comboBox_prendre_pion.setBounds(41, 123, 103, 20);
		panel.add(comboBox_prendre_pion);
		comboBox_prendre_pion.setVisible(false);
		button_prendre_prendre = new JButton("Prendre");

		button_prendre_prendre.setBounds(168, 123, 82, 20);
		panel.add(button_prendre_prendre);

		JLabel label_15 = new JLabel("x : ");
		label_15.setBounds(116, 223, 21, 14);
		panel.add(label_15);

		comboBox_expulse_x = new JComboBox<Integer>();
		comboBox_expulse_x.setBounds(141, 220, 44, 20);
		panel.add(comboBox_expulse_x);
		remplirListeNombre(comboBox_expulse_x,taille_ordonnee);

		JLabel label_16 = new JLabel("y : ");
		label_16.setBounds(116, 246, 21, 14);
		panel.add(label_16);

		comboBox_expulse_y = new JComboBox<Integer>();
		comboBox_expulse_y.setBounds(141, 251, 44, 20);
		panel.add(comboBox_expulse_y);
		remplirListeNombre(comboBox_expulse_y,14);


		JLabel lblNewLabel = new JLabel((String) nom_item.get("expulseur"));
		lblNewLabel.setBounds(41, 182, 69, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel((String) nom_item.get("expulse"));
		lblNewLabel_1.setBounds(116, 182, 76, 14);
		panel.add(lblNewLabel_1);

		lblSupprimer = new JLabel((String) nom_item.get("supprimer"));
		lblSupprimer.setBounds(14, 299, 76, 14);
		panel.add(lblSupprimer);

		comboBox_supprimer_y = new JComboBox<Integer>();
		comboBox_supprimer_y.setBounds(66, 351, 44, 20);
		panel.add(comboBox_supprimer_y);

		label_2 = new JLabel("y : ");
		label_2.setBounds(41, 346, 21, 14);
		panel.add(label_2);

		label_17 = new JLabel("x : ");
		label_17.setBounds(41, 323, 21, 14);
		panel.add(label_17);

		comboBox_supprimer_x = new JComboBox<Integer>();
		comboBox_supprimer_x.setBounds(66, 320, 44, 20);
		panel.add(comboBox_supprimer_x);

		button_supprimer_ok = new JButton((String) nom_item.get("button_supprimer"));
		button_supprimer_ok.setBounds(120, 319, 55, 38);
		panel.add(button_supprimer_ok);
		button_prendre_prendre.setVisible(false);

		remplirListeNombre(comboBox_supprimer_x,taille_ordonnee);
		remplirListeNombre(comboBox_supprimer_y,taille_ordonnee);

		comboBox_joueur = new JComboBox<String>();
		comboBox_joueur.setBounds(101, 13, 130, 26);
		add(comboBox_joueur);
		remplirListeJoueur(comboBox_joueur,liste_joueur);

		btn_sablier = new MButton("",(String) image.get("image_sablier"));
		btn_sablier.setBounds(241, 11, 34, 31);
		add(btn_sablier);

		button_gagne = new JButton((String) nom_item.get("button_gagne"));
		button_gagne.setBounds(10, 16, 81, 23);
		add(button_gagne);


	}

	/**
	 * Placement des écouteurs 
	 * 
	 */
	public void placer_ecouteur(){
		button_placer_repelleur_ok.addActionListener(new EcouteurPlacerRepelleur(createur));
		button_placer_stack_ok.addActionListener(new EcouteurPlacerStack(createur));
		button_donner_ok.addActionListener(new EcouteurDonnerPion(createur));
		button_prendre_ok.addActionListener(new EcouteurPrendreOk(createur));
		button_expulser_ok.addActionListener(new EcouteurExpulserPion(createur));
		button_gagne.addActionListener(new EcouteurGagne(createur));	
		button_prendre_prendre.addActionListener(new EcouteurPrendrePion(createur));
		button_supprimer_ok.addActionListener(new EcouteurSupprimer(createur));

		button_deplacer_B.addActionListener(new EcouteurDeplacerPion(createur, 0, 1));
		button_deplacer_G.addActionListener(new EcouteurDeplacerPion(createur, -1, 0));
		button_deplacer_BD.addActionListener(new EcouteurDeplacerPion(createur, 1, 1));
		button_deplacer_BG.addActionListener(new EcouteurDeplacerPion(createur, -1, 1));
		button_deplacer_D.addActionListener(new EcouteurDeplacerPion(createur, 1, 0));
		button_deplacer_H.addActionListener(new EcouteurDeplacerPion(createur, 0, -1));
		button_deplacer_HD.addActionListener(new EcouteurDeplacerPion(createur, 1, -1));
		button_deplacer_HG.addActionListener(new EcouteurDeplacerPion(createur, -1, -1));
		btn_sablier.addActionListener(new EcouteurSablier(createur));
	}


	/**
	 * Retourne le nom du joueur dans la liste déroulante
	 * @return nom joueur
	 */
	public String getJoueur(){return (String) comboBox_joueur.getSelectedItem();}

	/**
	 * Remplis la liste à remplir avec avec une liste contenant des joueurs
	 * @param liste_a_remplir
	 * @param ll
	 */
	public void remplirListeJoueur(JComboBox<String> liste_a_remplir, LinkedList<?> ll ){
		DefaultComboBoxModel<String> dm = new DefaultComboBoxModel<String>();
		for(int i = 0 ; i < ll.size();i++)
			dm.addElement( ((Joueur) ll.get(i)).getNom());

		liste_a_remplir.setModel(dm);
	}

	/**
	 * Remplis un ComboBox de numéros
	 * @param liste_a_remplir
	 * @param jusqua
	 */
	public void remplirListeNombre(JComboBox<Integer> liste_a_remplir, int jusqua){
		for(int i = 1 ; i < jusqua ; i ++) 
			liste_a_remplir.addItem(i);
	}

	/**
	 * Construit un Linked indispensable pour les écouteurs
	 * @param config
	 * @param source
	 */
	public void creer_nomJoueur(LinkedList<?> config, LinkedHashMap<String, String> source){
		for(int i =0; i<config.size();i++){
			source.put(((Joueur) config.get(i)).getNom(), ((Joueur) config.get(i)).getCouleurString());
		}

	}




	/***
	 * 
	 * 
	 * G&T
	 * 
	 * 
	 * @return
	 */
	public JComboBox<?> getComboBox_donner() {return comboBox_donner;}
	public void setComboBox_donner(JComboBox<?> comboBox_donner) {this.comboBox_donner = comboBox_donner;}
	public JButton getButton_donner_ok() {return button_donner_ok;}
	public void setButton_donner_ok(JButton button_donner_ok) {this.button_donner_ok = button_donner_ok;}
	public JComboBox<String> getComboBox_prendre() {return comboBox_prendre;}
	public void setComboBox_prendre(JComboBox<String> comboBox_prendre) {this.comboBox_prendre = comboBox_prendre;}
	public JButton getButton_prendre_ok() {return button_prendre_ok;}
	public void setButton_prendre_ok(JButton button_prendre_ok) {this.button_prendre_ok = button_prendre_ok;}
	public JComboBox<Integer> getComboBox_expulseur_y() {return comboBox_expulseur_y;}
	public void setComboBox_ejecter_y(JComboBox<Integer> comboBox_ejecter_y) {this.comboBox_expulseur_y = comboBox_ejecter_y;}
	public JComboBox<Integer> getComboBox_expulseur_x() {return comboBox_expulseur_x;}
	public void setComboBox_expulseur_x(JComboBox<Integer> comboBox_ejecter_x) {this.comboBox_expulseur_x = comboBox_ejecter_x;}
	public JButton getButton_expulser_ok() {return button_expulser_ok;}
	public void setButton_expulser_ok(JButton button_ejecter_ok) {this.button_expulser_ok = button_ejecter_ok;}
	public JButton getButton_gagne() {return button_gagne;}
	public void setButton_gagne(JButton button_gagne) {this.button_gagne = button_gagne;}
	public JComboBox<Integer> getComboBox_placer_repelleur_x() {return comboBox_placer_repelleur_x;}
	public void setComboBox_placer_repelleur_x(JComboBox<Integer> comboBox_placer_repelleur_x) {this.comboBox_placer_repelleur_x = comboBox_placer_repelleur_x;}
	public JComboBox<Integer> getComboBox_placer_repelleur_y() {return comboBox_placer_repelleur_y;}
	public void setComboBox_placer_repelleur_y(JComboBox<Integer> comboBox_placer_repelleur_y) {this.comboBox_placer_repelleur_y = comboBox_placer_repelleur_y;}
	public JButton getButton_placer_repelleur_ok() {return button_placer_repelleur_ok;}
	public void setButton_placer_repelleur_ok(JButton button_placer_repelleur_ok) {this.button_placer_repelleur_ok = button_placer_repelleur_ok;}
	public JComboBox<Integer> getComboBox_placer_stack_y() {return comboBox_placer_stack_y;}
	public void setComboBox_placer_stack_y(JComboBox<Integer> comboBox_placer_stack_y) {this.comboBox_placer_stack_y = comboBox_placer_stack_y;}
	public JComboBox<Integer> getComboBox_placer_stack_x() {return comboBox_placer_stack_x;}
	public void setComboBox_placer_stack_x(JComboBox<Integer> comboBox_placer_stack_x) {this.comboBox_placer_stack_x = comboBox_placer_stack_x;}
	public JButton getButton_placer_stack_ok() {return button_placer_stack_ok;}
	public void setButton_placer_stack_ok(JButton button_placer_stack_ok) {this.button_placer_stack_ok = button_placer_stack_ok;}
	public JComboBox<Integer> getComboBox_deplacer_x() {return comboBox_deplacer_x;}
	public void setComboBox_deplacer_x(JComboBox<Integer> comboBox_deplacer_x) {this.comboBox_deplacer_x = comboBox_deplacer_x;}
	public JComboBox<Integer> getComboBox_deplacer_y() {return comboBox_deplacer_y;}
	public void setComboBox_deplacer_y(JComboBox<Integer> comboBox_deplacer_y) {this.comboBox_deplacer_y = comboBox_deplacer_y;}
	public JButton getButton_deplacer_HG() {return button_deplacer_HG;}
	public void setButton_deplacer_HG(MButton button_deplacer_HG) {this.button_deplacer_HG = button_deplacer_HG;}
	public JButton getButton_deplacer_H() {return button_deplacer_H;}
	public void setButton_deplacer_H(MButton button_deplacer_H) {this.button_deplacer_H = button_deplacer_H;}
	public JButton getButton_deplacer_HD(){ return button_deplacer_HD;}
	public void setButton_deplacer_HD(MButton button_deplacer_HD) {this.button_deplacer_HD = button_deplacer_HD;}
	public JButton getButton_deplacer_G() {return button_deplacer_G;}
	public void setButton_deplacer_G(MButton button_deplacer_G) {this.button_deplacer_G = button_deplacer_G;}
	public JButton getButton_deplacer_ok() {return button_deplacer_ok;}
	public void setButton_deplacer_ok(MButton button_deplacer_ok) {this.button_deplacer_ok = button_deplacer_ok;}
	public JButton getButton_deplacer_D() {return button_deplacer_D;}
	public void setButton_deplacer_D(MButton button_deplacer_D) {this.button_deplacer_D = button_deplacer_D;}
	public JButton getButton_deplacer_BG() {return button_deplacer_BG;}
	public void setButton_deplacer_BG(MButton button_deplacer_BG) {this.button_deplacer_BG = button_deplacer_BG;}
	public JButton getButton_deplacer_B() {return button_deplacer_B;}
	public void setButton_deplacer_B(MButton button_deplacer_B) {this.button_deplacer_B = button_deplacer_B;}
	public JButton getButton_deplacer_BD() {return button_deplacer_BD;}
	public void setButton_deplacer_BD(MButton button_deplacer_BD) {this.button_deplacer_BD = button_deplacer_BD;}
	public JComboBox<Integer> getComboBox_deplacer_valeur() {return comboBox_deplacer_valeur;}
	public void setComboBox_deplacer_valeur(JComboBox<Integer> comboBox_deplacer_valeur) {this.comboBox_deplacer_valeur = comboBox_deplacer_valeur;}
	public JTabbedPane getTabbedPane() {return tabbedPane;}
	public void setTabbedPane(JTabbedPane tabbedPane) {this.tabbedPane = tabbedPane;}
	public JComboBox<String> getComboBox_joueur(){return comboBox_joueur;}
	public void setComboBox_joueur(JComboBox<String> comboBox_joueur) {this.comboBox_joueur = comboBox_joueur;}
	public LinkedHashMap<String, String> getNom_joueur() {return nom_joueur;}
	public void setNom_joueur(LinkedHashMap<String, String> nom_joueur) {this.nom_joueur = nom_joueur;}
	public JComboBox<?> getComboBox_prendre_pion() {return comboBox_prendre_pion;}
	public void setComboBox_prendre_pion(JComboBox<?> comboBox_prendre_pion) {this.comboBox_prendre_pion = comboBox_prendre_pion;}
	public JButton getButton_prendre_prendre() {return button_prendre_prendre;}
	public void setButton_prendre_prendre(JButton button_prendre_prendre) {this.button_prendre_prendre = button_prendre_prendre;}
	public DemonstrateurRepello getCreateur() {return createur;}
	public void setCreateur(DemonstrateurRepello createur) {this.createur = createur;}
	public JComboBox<Integer> getComboBox_expulse_x() {return comboBox_expulse_x;}
	public void setComboBox_expulse_x(JComboBox<Integer> comboBox_expulse_x) {this.comboBox_expulse_x = comboBox_expulse_x;}

	public JComboBox<Integer> getComboBox_expulse_y() {
		return comboBox_expulse_y;
	}

	public void setComboBox_expulse_y(JComboBox<Integer> comboBox_expulse_y) {
		this.comboBox_expulse_y = comboBox_expulse_y;
	}

	public JComboBox<Integer> getComboBox_supprimer_y() {
		return comboBox_supprimer_y;
	}

	public void setComboBox_supprimer_y(JComboBox<Integer> comboBox_supprimer_y) {
		this.comboBox_supprimer_y = comboBox_supprimer_y;
	}

	public JComboBox<Integer> getComboBox_supprimer_x() {
		return comboBox_supprimer_x;
	}

	public void setComboBox_supprimer_x(JComboBox<Integer> comboBox_supprimer_x) {
		this.comboBox_supprimer_x = comboBox_supprimer_x;
	}

	public JButton getButton_supprimer_ok() {
		return button_supprimer_ok;
	}

	public void setButton_supprimer_ok(JButton button_supprimer_ok) {
		this.button_supprimer_ok = button_supprimer_ok;
	}

	public JComboBox<Integer> getComboBox_repelleur_pion() {
		return comboBox_repelleur_pion;
	}

	public void setComboBox_repelleur_pion(
			JComboBox<Integer> comboBox_repelleur_pion) {
		this.comboBox_repelleur_pion = comboBox_repelleur_pion;
	}

	public MButton getBtn_sablier() {
		return btn_sablier;
	}

	public void setBtn_sablier(MButton btn_sablier) {
		this.btn_sablier = btn_sablier;
	}
}
