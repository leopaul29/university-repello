/*
 * 
 */
package Vue;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;


// TODO: Auto-generated Javadoc
/**
 * The Class Demonstrateur.
 */
public class Demonstrateur extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cb x_placer piece. */
	private JComboBox<Integer> cbX_placerPiece;

	/** The cb y_placer piece. */
	private JComboBox<Integer> cbY_placerPiece;

	/** The cb color stack_placer. */
	private JComboBox<String> cbColorStack_placer;

	/** The placer piece. */
	private JButton placerPiece;

	/** The retirer piece. */
	private JButton retirerPiece;

	/** The cb x_retirer piece. */
	private JComboBox<Integer> cbX_retirerPiece;

	/** The cb y_retirer piece. */
	private JComboBox<Integer> cbY_retirerPiece;

	/** The deplacer piece. */
	private JButton deplacerPiece;

	/** The cb y_ ancien dep. */
	private JComboBox<Integer> cbY_AncienDep;

	/** The cb x_ ancien dep. */
	private JComboBox<Integer> cbX_AncienDep;

	/** The cb y_ nouvelle dep. */
	private JComboBox<Integer> cbY_NouvelleDep;

	/** The cb x_ nouvelle dep. */
	private JComboBox<Integer> cbX_NouvelleDep;

	/** The dec point. */
	private JButton decPoint;

	/** The inc point. */
	private JButton incPoint;

	/** The cb_jeton cible. */
	private JComboBox<String> cb_jetonCible;

	/** The cb_ stack point. */
	private JComboBox<String> cb_StackPoint;

	/** The cb_ stack voleur. */
	private JComboBox<String> cb_StackVoleur;

	/** The cb_ jeton vole. */
	private JComboBox<String> cb_JetonVole;

	/** The cb_ stack vole. */
	private JComboBox<String> cb_StackVole;

	/** The voler. */
	private JButton voler;

	/** The cb x_expulseur. */
	private JComboBox<Integer> cbX_expulseur;

	/** The cb y_expulseur. */
	private JComboBox<Integer> cbY_expulseur;

	/** The expulser. */
	private JButton expulser;

	/** The cb x_expulse. */
	private JComboBox<Integer> cbX_expulse;

	/** The cb y_expulse. */
	private JComboBox<Integer> cbY_expulse;

	/** The lbl joueur gagnant. */
	private JLabel lblJoueurGagnant;

	/** The Afficher les scores. */
	private JButton AfficherLesScores;

	/** The Afficher le temps. */
	private JButton AfficherLeTemps;

	/** The Reprendre. */
	private JButton Reprendre;

	/** The Pause. */
	private JButton Pause;

	/** The Lancer le sablier. */
	private JButton LancerLeSablier;

	/**
	 * Create the panel.
	 *
	 * @param createur the createur
	 */
	public Demonstrateur(JFrame createur) {

		createur.getContentPane().add(this);
		createur.setLocation(800, 20);
		createur.setSize(480, 550);
		createur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createur.setTitle("Démonstrateur Repello");

		setLayout(null);
		/*
		 *  stack entrain de jouer ?
		 *  
		 */

		cbX_placerPiece = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_placerPiece_X = new DefaultComboBoxModel<Integer>();
		for(int i  = 1 ; i < 14; i ++){
			mod_placerPiece_X.addElement((Integer)i);
		}
		cbX_placerPiece.setModel(mod_placerPiece_X);
		cbX_placerPiece.setBounds(23, 27, 39, 20);
		add(cbX_placerPiece);

		cbY_placerPiece = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_placerPiece_Y = new DefaultComboBoxModel<Integer>();
		for(int i  = 1 ; i < 14; i ++){
			mod_placerPiece_Y.addElement((Integer)i);
		}
		cbY_placerPiece.setModel(mod_placerPiece_Y);
		cbY_placerPiece.setBounds(72, 27, 39, 20);
		add(cbY_placerPiece);

		cbColorStack_placer = new JComboBox<String>();
		DefaultComboBoxModel<String> mod_placerPiece_col = new DefaultComboBoxModel<String>();
		mod_placerPiece_col.addElement("PionsR.png");
		mod_placerPiece_col.addElement("PionsB.png");
		mod_placerPiece_col.addElement("PionsV.png");
		mod_placerPiece_col.addElement("PionsJ.png");
		mod_placerPiece_col.addElement("jeton.png");
		mod_placerPiece_col.addElement("jetonARGENT.png");
		mod_placerPiece_col.addElement("jetonOR.png");
		cbColorStack_placer.setModel(mod_placerPiece_col);
		cbColorStack_placer.setBounds(121, 27, 108, 20);
		add(cbColorStack_placer);

		placerPiece = new JButton("Placer piece");
		placerPiece.setBounds(288, 26, 141, 23);
		add(placerPiece);
		//
		cbX_NouvelleDep = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_deplacerPiece_Xn = new DefaultComboBoxModel<Integer>();
		for(int i  = 1 ; i < 14; i ++){
			mod_deplacerPiece_Xn.addElement((Integer)i);
		}
		cbX_NouvelleDep.setModel(mod_deplacerPiece_Xn);
		cbX_NouvelleDep.setBounds(175, 102, 39, 20);
		add(cbX_NouvelleDep);

		cbY_NouvelleDep = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_deplacerPiece_Yn = new DefaultComboBoxModel<Integer>();
		for(int i  = 1 ; i < 14; i ++){
			mod_deplacerPiece_Yn.addElement((Integer)i);
		}
		cbY_NouvelleDep.setModel(mod_deplacerPiece_Yn);
		cbY_NouvelleDep.setBounds(224, 102, 39, 20);
		add(cbY_NouvelleDep);

		cbX_AncienDep = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_deplacerPiece_Xa = new DefaultComboBoxModel<Integer>();
		for(int i  = 1 ; i < 14; i ++){
			mod_deplacerPiece_Xa.addElement((Integer)i);
		}
		cbX_AncienDep.setModel(mod_deplacerPiece_Xa);
		cbX_AncienDep.setBounds(175, 71, 39, 20);
		add(cbX_AncienDep);

		cbY_AncienDep = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_deplacerPiece_Ya = new DefaultComboBoxModel<Integer>();
		for(int i  = 1 ; i < 14; i ++){
			mod_deplacerPiece_Ya.addElement((Integer)i);
		}
		cbY_AncienDep.setModel(mod_deplacerPiece_Ya);
		cbY_AncienDep.setBounds(224, 71, 39, 20);
		add(cbY_AncienDep);

		deplacerPiece = new JButton("Déplacer Piece");
		deplacerPiece.setBounds(288, 101, 141, 23);
		add(deplacerPiece);
		//		
		cbX_retirerPiece = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_retirerPiece_X = new DefaultComboBoxModel<Integer>();
		for(int i  = 1; i < 14; i ++){
			mod_retirerPiece_X.addElement((Integer)i);
		}
		cbX_retirerPiece.setModel(mod_retirerPiece_X);
		cbX_retirerPiece.setBounds(23, 160, 39, 20);
		add(cbX_retirerPiece);

		cbY_retirerPiece = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_retirerPiece_Y = new DefaultComboBoxModel<Integer>();
		for(int i  = 1; i < 14; i ++){
			mod_retirerPiece_Y.addElement((Integer)i);
		}
		cbY_retirerPiece.setModel(mod_retirerPiece_Y);
		cbY_retirerPiece.setBounds(72, 160, 39, 20);
		add(cbY_retirerPiece);

		retirerPiece = new JButton("Retirer Piece");
		retirerPiece.setBounds(288, 159, 141, 23);
		add(retirerPiece);

		JSeparator separator = new JSeparator();
		separator.setBounds(23, 202, 406, 2);
		add(separator);

		voler = new JButton("Voler");
		voler.setBounds(329, 266, 100, 23);
		add(voler);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(23, 58, 406, 2);
		add(separator_1);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(23, 147, 406, 2);
		add(separator_4);

		JLabel lblAncienneCoordonee = new JLabel("Anciennes coordon\u00E9ees :");
		lblAncienneCoordonee.setBounds(23, 74, 157, 17);
		add(lblAncienneCoordonee);

		JLabel lblNouvelleCoordonee = new JLabel("Nouvelles coordon\u00E9ees :");
		lblNouvelleCoordonee.setBounds(23, 105, 157, 14);
		add(lblNouvelleCoordonee);

		decPoint = new JButton("-");
		decPoint.setBounds(379, 215, 50, 23);
		add(decPoint);

		cb_jetonCible = new JComboBox<String>();
		DefaultComboBoxModel<String> mod_jetonCible = new DefaultComboBoxModel<String>();
		mod_jetonCible.addElement("jeton");
		mod_jetonCible.addElement("jetonArgent");
		mod_jetonCible.addElement("jetonOr");
		mod_jetonCible.addElement("stack");
		cb_jetonCible.setModel(mod_jetonCible);
		cb_jetonCible.setBounds(229, 215, 80, 20);
		add(cb_jetonCible);

		incPoint = new JButton("+");
		incPoint.setBounds(319, 215, 50, 23);
		add(incPoint);

		cb_StackPoint = new JComboBox<String>();
		DefaultComboBoxModel<String> mod_JoueurCible = new DefaultComboBoxModel<String>();
		mod_JoueurCible.addElement("Rouge");
		mod_JoueurCible.addElement("Bleu");
		mod_JoueurCible.addElement("Vert");
		mod_JoueurCible.addElement("Jaune");
		cb_StackPoint.setModel(mod_JoueurCible);
		cb_StackPoint.setBounds(151, 215, 68, 20);
		add(cb_StackPoint);

		JLabel lblModifierLesScores = new JLabel("Modifier les scores :");
		lblModifierLesScores.setBounds(23, 219, 118, 14);
		add(lblModifierLesScores);

		cb_StackVoleur = new JComboBox<String>();
		DefaultComboBoxModel<String> mod_StackVoleur = new DefaultComboBoxModel<String>();
		mod_StackVoleur.addElement("Rouge");
		mod_StackVoleur.addElement("Bleu");
		mod_StackVoleur.addElement("Vert");
		mod_StackVoleur.addElement("Jaune");
		cb_StackVoleur.setModel(mod_StackVoleur);
		cb_StackVoleur.setBounds(23, 267, 68, 20);
		add(cb_StackVoleur);

		JLabel lblVole = new JLabel("vole");
		lblVole.setBounds(101, 270, 46, 14);
		add(lblVole);

		cb_JetonVole = new JComboBox<String>();
		DefaultComboBoxModel<String> mod_JetonVole = new DefaultComboBoxModel<String>();
		mod_JetonVole.addElement("jeton");
		mod_JetonVole.addElement("jetonArgent");
		mod_JetonVole.addElement("jetonOr");
		cb_JetonVole.setModel(mod_JetonVole);
		cb_JetonVole.setBounds(139, 267, 80, 20);
		add(cb_JetonVole);

		cb_StackVole = new JComboBox<String>();
		DefaultComboBoxModel<String> mod_StackVole = new DefaultComboBoxModel<String>();
		mod_StackVole.addElement("Rouge");
		mod_StackVole.addElement("Bleu");
		mod_StackVole.addElement("Vert");
		mod_StackVole.addElement("Jaune");
		cb_StackVole.setModel(mod_StackVole);
		cb_StackVole.setBounds(252, 267, 68, 20);
		add(cb_StackVole);

		JLabel label = new JLabel("\u00E0");
		label.setBounds(232, 270, 46, 14);
		add(label);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(23, 300, 406, 2);
		add(separator_5);

		JLabel lblLePions = new JLabel("Le pions");
		lblLePions.setBounds(23, 327, 80, 14);
		add(lblLePions);

		JLabel lblExpulse = new JLabel("expulse");
		lblExpulse.setBounds(118, 359, 68, 14);
		add(lblExpulse);

		cbX_expulse = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_expulse_X = new DefaultComboBoxModel<Integer>();
		for(int i  = 1 ; i < 14; i ++){
			mod_expulse_X.addElement((Integer)i);
		}
		cbX_expulse.setModel(mod_expulse_X);
		cbX_expulse.setBounds(224, 355, 39, 20);
		add(cbX_expulse);

		cbY_expulse = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_expulse_Y = new DefaultComboBoxModel<Integer>();
		for(int i  = 1; i < 14; i ++){
			mod_expulse_Y.addElement((Integer)i);
		}
		cbY_expulse.setModel(mod_expulse_Y);
		cbY_expulse.setBounds(175, 355, 39, 20);
		add(cbY_expulse);

		expulser = new JButton("Expulser");
		expulser.setBounds(288, 352, 100, 23);
		add(expulser);

		cbX_expulseur = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_expulseur_X = new DefaultComboBoxModel<Integer>();
		for(int i  = 1; i < 14; i ++){
			mod_expulseur_X.addElement((Integer)i);
		}
		cbX_expulseur.setModel(mod_expulseur_X);
		cbX_expulseur.setBounds(74, 323, 39, 20);
		add(cbX_expulseur);

		cbY_expulseur = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> mod_expulseur_Y = new DefaultComboBoxModel<Integer>();
		for(int i  = 1; i < 14; i ++){
			mod_expulseur_Y.addElement((Integer)i);
		}
		cbY_expulseur.setModel(mod_expulseur_Y);
		cbY_expulseur.setBounds(123, 323, 39, 20);
		add(cbY_expulseur);

		JLabel lblLeJoueurGagnant = new JLabel("Le joueur gagnant est le joueur");
		lblLeJoueurGagnant.setBounds(23, 441, 196, 14);
		add(lblLeJoueurGagnant);

		lblJoueurGagnant = new JLabel();
		lblJoueurGagnant.setBounds(201, 441, 46, 14);
		add(lblJoueurGagnant);

		AfficherLeTemps = new JButton("Afficher le temps");
		AfficherLeTemps.setBounds(23, 466, 157, 23);
		add(AfficherLeTemps);

		AfficherLesScores = new JButton("Afficher les scores");
		AfficherLesScores.setBounds(224, 466, 145, 23);
		add(AfficherLesScores);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(23, 428, 406, 2);
		add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(23, 384, 406, 2);
		add(separator_3);

		LancerLeSablier = new JButton("Lancer le sablier");
		LancerLeSablier.setBounds(23, 394, 140, 23);
		add(LancerLeSablier);

		Pause = new JButton("Pause");
		Pause.setBounds(174, 394, 89, 23);
		add(Pause);

		Reprendre = new JButton("Reprendre");
		Reprendre.setBounds(273, 394, 115, 23);
		add(Reprendre);


		createur.setVisible(true);
	}

	/**
	 * Gets the afficher les scores.
	 *
	 * @return the afficher les scores
	 */
	public JButton getAfficherLesScores() {
		return AfficherLesScores;
	}

	/**
	 * Sets the afficher les scores.
	 *
	 * @param afficherLesScores the new afficher les scores
	 */
	public void setAfficherLesScores(JButton afficherLesScores) {
		AfficherLesScores = afficherLesScores;
	}

	/**
	 * Gets the afficher le temps.
	 *
	 * @return the afficher le temps
	 */
	public JButton getAfficherLeTemps() {
		return AfficherLeTemps;
	}

	/**
	 * Sets the afficher le temps.
	 *
	 * @param afficherLeTemps the new afficher le temps
	 */
	public void setAfficherLeTemps(JButton afficherLeTemps) {
		AfficherLeTemps = afficherLeTemps;
	}

	/**
	 * Gets the cb x_placer piece.
	 *
	 * @return the cb x_placer piece
	 */
	public JComboBox<Integer> getCbX_placerPiece() {
		return cbX_placerPiece;
	}

	/**
	 * Sets the cb x_placer piece.
	 *
	 * @param cbX_placerPiece the new cb x_placer piece
	 */
	public void setCbX_placerPiece(JComboBox<Integer> cbX_placerPiece) {
		this.cbX_placerPiece = cbX_placerPiece;
	}

	/**
	 * Gets the cb y_placer piece.
	 *
	 * @return the cb y_placer piece
	 */
	public JComboBox<Integer> getCbY_placerPiece() {
		return cbY_placerPiece;
	}

	/**
	 * Sets the cb y_placer piece.
	 *
	 * @param cbY_placerPiece the new cb y_placer piece
	 */
	public void setCbY_placerPiece(JComboBox<Integer> cbY_placerPiece) {
		this.cbY_placerPiece = cbY_placerPiece;
	}

	/**
	 * Gets the placer piece.
	 *
	 * @return the placer piece
	 */
	public JButton getPlacerPiece() {
		return placerPiece;
	}

	/**
	 * Sets the placer piece.
	 *
	 * @param placerPiece the new placer piece
	 */
	public void setPlacerPiece(JButton placerPiece) {
		this.placerPiece = placerPiece;
	}

	/**
	 * Gets the retirer piece.
	 *
	 * @return the retirer piece
	 */
	public JButton getRetirerPiece() {
		return retirerPiece;
	}

	/**
	 * Sets the retirer piece.
	 *
	 * @param retirerPiece the new retirer piece
	 */
	public void setRetirerPiece(JButton retirerPiece) {
		this.retirerPiece = retirerPiece;
	}

	/**
	 * Gets the cb x_retirer piece.
	 *
	 * @return the cb x_retirer piece
	 */
	public JComboBox<Integer> getCbX_retirerPiece() {
		return cbX_retirerPiece;
	}

	/**
	 * Sets the cb x_retirer piece.
	 *
	 * @param cbX_retirerPiece the new cb x_retirer piece
	 */
	public void setCbX_retirerPiece(JComboBox<Integer> cbX_retirerPiece) {
		this.cbX_retirerPiece = cbX_retirerPiece;
	}

	/**
	 * Gets the cb y_retirer piece.
	 *
	 * @return the cb y_retirer piece
	 */
	public JComboBox<Integer> getCbY_retirerPiece() {
		return cbY_retirerPiece;
	}

	/**
	 * Sets the cb y_retirer piece.
	 *
	 * @param cbY_retirerPiece the new cb y_retirer piece
	 */
	public void setCbY_retirerPiece(JComboBox<Integer> cbY_retirerPiece) {
		this.cbY_retirerPiece = cbY_retirerPiece;
	}

	/**
	 * Gets the deplacer piece.
	 *
	 * @return the deplacer piece
	 */
	public JButton getDeplacerPiece() {
		return deplacerPiece;
	}

	/**
	 * Sets the deplacer piece.
	 *
	 * @param deplacerPiece the new deplacer piece
	 */
	public void setDeplacerPiece(JButton deplacerPiece) {
		this.deplacerPiece = deplacerPiece;
	}

	/**
	 * Gets the cb y_ ancien dep.
	 *
	 * @return the cb y_ ancien dep
	 */
	public JComboBox<Integer> getCbY_AncienDep() {
		return cbY_AncienDep;
	}

	/**
	 * Sets the cb y_ ancien dep.
	 *
	 * @param cbY_AncienDep the new cb y_ ancien dep
	 */
	public void setCbY_AncienDep(JComboBox<Integer> cbY_AncienDep) {
		this.cbY_AncienDep = cbY_AncienDep;
	}

	/**
	 * Gets the cb x_ ancien dep.
	 *
	 * @return the cb x_ ancien dep
	 */
	public JComboBox<Integer> getCbX_AncienDep() {
		return cbX_AncienDep;
	}

	/**
	 * Sets the cb x_ ancien dep.
	 *
	 * @param cbX_AncienDep the new cb x_ ancien dep
	 */
	public void setCbX_AncienDep(JComboBox<Integer> cbX_AncienDep) {
		this.cbX_AncienDep = cbX_AncienDep;
	}

	/**
	 * Gets the cb y_ nouvelle dep.
	 *
	 * @return the cb y_ nouvelle dep
	 */
	public JComboBox<Integer> getCbY_NouvelleDep() {
		return cbY_NouvelleDep;
	}

	/**
	 * Sets the cb y_ nouvelle dep.
	 *
	 * @param cbY_NouvelleDep the new cb y_ nouvelle dep
	 */
	public void setCbY_NouvelleDep(JComboBox<Integer> cbY_NouvelleDep) {
		this.cbY_NouvelleDep = cbY_NouvelleDep;
	}

	/**
	 * Gets the cb x_ nouvelle dep.
	 *
	 * @return the cb x_ nouvelle dep
	 */
	public JComboBox<Integer> getCbX_NouvelleDep() {
		return cbX_NouvelleDep;
	}

	/**
	 * Sets the cb x_ nouvelle dep.
	 *
	 * @param cbX_NouvelleDep the new cb x_ nouvelle dep
	 */
	public void setCbX_NouvelleDep(JComboBox<Integer> cbX_NouvelleDep) {
		this.cbX_NouvelleDep = cbX_NouvelleDep;
	}

	/**
	 * Gets the cb color stack_placer.
	 *
	 * @return the cb color stack_placer
	 */
	public JComboBox<String> getCbColorStack_placer() {
		return cbColorStack_placer;
	}

	/**
	 * Sets the cb color stack_placer.
	 *
	 * @param cbColorStack_placer the new cb color stack_placer
	 */
	public void setCbColorStack_placer(JComboBox<String> cbColorStack_placer) {
		this.cbColorStack_placer = cbColorStack_placer;
	}

	/**
	 * Gets the dec point.
	 *
	 * @return the dec point
	 */
	public JButton getDecPoint() {
		return decPoint;
	}

	/**
	 * Sets the dec point.
	 *
	 * @param decPoint the new dec point
	 */
	public void setDecPoint(JButton decPoint) {
		this.decPoint = decPoint;
	}

	/**
	 * Gets the inc point.
	 *
	 * @return the inc point
	 */
	public JButton getIncPoint() {
		return incPoint;
	}

	/**
	 * Sets the inc point.
	 *
	 * @param incPoint the new inc point
	 */
	public void setIncPoint(JButton incPoint) {
		this.incPoint = incPoint;
	}

	/**
	 * Gets the cb_ stack point.
	 *
	 * @return the cb_ stack point
	 */
	public JComboBox<String> getCb_StackPoint() {
		return cb_StackPoint;
	}

	/**
	 * Sets the cb_ stack point.
	 *
	 * @param cb_StackPoint the new cb_ stack point
	 */
	public void setCb_StackPoint(JComboBox<String> cb_StackPoint) {
		this.cb_StackPoint = cb_StackPoint;
	}

	/**
	 * Gets the cb_jeton cible.
	 *
	 * @return the cb_jeton cible
	 */
	public JComboBox<String> getCb_jetonCible() {
		return cb_jetonCible;
	}

	/**
	 * Sets the cb_jeton cible.
	 *
	 * @param cb_jetonCible the new cb_jeton cible
	 */
	public void setCb_jetonCible(JComboBox<String> cb_jetonCible) {
		this.cb_jetonCible = cb_jetonCible;
	}

	/**
	 * Gets the cb_ stack voleur.
	 *
	 * @return the cb_ stack voleur
	 */
	public JComboBox<String> getCb_StackVoleur() {
		return cb_StackVoleur;
	}

	/**
	 * Sets the cb_ stack voleur.
	 *
	 * @param cb_StackVoleur the new cb_ stack voleur
	 */
	public void setCb_StackVoleur(JComboBox<String> cb_StackVoleur) {
		this.cb_StackVoleur = cb_StackVoleur;
	}

	/**
	 * Gets the cb_ jeton vole.
	 *
	 * @return the cb_ jeton vole
	 */
	public JComboBox<String> getCb_JetonVole() {
		return cb_JetonVole;
	}

	/**
	 * Sets the cb_ jeton vole.
	 *
	 * @param cb_JetonVole the new cb_ jeton vole
	 */
	public void setCb_JetonVole(JComboBox<String> cb_JetonVole) {
		this.cb_JetonVole = cb_JetonVole;
	}

	/**
	 * Gets the cb_ stack vole.
	 *
	 * @return the cb_ stack vole
	 */
	public JComboBox<String> getCb_StackVole() {
		return cb_StackVole;
	}

	/**
	 * Sets the cb_ stack vole.
	 *
	 * @param cb_StackVole the new cb_ stack vole
	 */
	public void setCb_StackVole(JComboBox<String> cb_StackVole) {
		this.cb_StackVole = cb_StackVole;
	}

	/**
	 * Gets the voler.
	 *
	 * @return the voler
	 */
	public JButton getVoler() {
		return voler;
	}

	/**
	 * Sets the voler.
	 *
	 * @param voler the new voler
	 */
	public void setVoler(JButton voler) {
		this.voler = voler;
	}

	/**
	 * Gets the cb x_expulseur.
	 *
	 * @return the cb x_expulseur
	 */
	public JComboBox<Integer> getCbX_expulseur() {
		return cbX_expulseur;
	}

	/**
	 * Sets the cb x_expulseur.
	 *
	 * @param cbX_expulseur the new cb x_expulseur
	 */
	public void setCbX_expulseur(JComboBox<Integer> cbX_expulseur) {
		this.cbX_expulseur = cbX_expulseur;
	}

	/**
	 * Gets the cb y_expulseur.
	 *
	 * @return the cb y_expulseur
	 */
	public JComboBox<Integer> getCbY_expulseur() {
		return cbY_expulseur;
	}

	/**
	 * Sets the cb y_expulseur.
	 *
	 * @param cbY_expulseur the new cb y_expulseur
	 */
	public void setCbY_expulseur(JComboBox<Integer> cbY_expulseur) {
		this.cbY_expulseur = cbY_expulseur;
	}

	/**
	 * Gets the expulser.
	 *
	 * @return the expulser
	 */
	public JButton getExpulser() {
		return expulser;
	}

	/**
	 * Sets the expulser.
	 *
	 * @param expulser the new expulser
	 */
	public void setExpulser(JButton expulser) {
		this.expulser = expulser;
	}

	/**
	 * Gets the cb x_expulse.
	 *
	 * @return the cb x_expulse
	 */
	public JComboBox<Integer> getCbX_expulse() {
		return cbX_expulse;
	}

	/**
	 * Sets the cb x_expulse.
	 *
	 * @param cbX_expulse the new cb x_expulse
	 */
	public void setCbX_expulse(JComboBox<Integer> cbX_expulse) {
		this.cbX_expulse = cbX_expulse;
	}

	/**
	 * Gets the cb y_expulse.
	 *
	 * @return the cb y_expulse
	 */
	public JComboBox<Integer> getCbY_expulse() {
		return cbY_expulse;
	}

	/**
	 * Sets the cb y_expulse.
	 *
	 * @param cbY_expulse the new cb y_expulse
	 */
	public void setCbY_expulse(JComboBox<Integer> cbY_expulse) {
		this.cbY_expulse = cbY_expulse;
	}

	/**
	 * Gets the lbl joueur gagnant.
	 *
	 * @return the lbl joueur gagnant
	 */
	public JLabel getLblJoueurGagnant() {
		return lblJoueurGagnant;
	}

	/**
	 * Sets the lbl joueur gagnant.
	 *
	 * @param string the new lbl joueur gagnant
	 */
	public void setLblJoueurGagnant(String string) {
		this.lblJoueurGagnant.setText(string);
	}

	/**
	 * Gets the reprendre.
	 *
	 * @return the reprendre
	 */
	public JButton getReprendre() {
		return Reprendre;
	}

	/**
	 * Sets the reprendre.
	 *
	 * @param reprendre the new reprendre
	 */
	public void setReprendre(JButton reprendre) {
		Reprendre = reprendre;
	}

	/**
	 * Gets the pause.
	 *
	 * @return the pause
	 */
	public JButton getPause() {
		return Pause;
	}

	/**
	 * Sets the pause.
	 *
	 * @param pause the new pause
	 */
	public void setPause(JButton pause) {
		Pause = pause;
	}

	/**
	 * Gets the lancer le sablier.
	 *
	 * @return the lancer le sablier
	 */
	public JButton getLancerLeSablier() {
		return LancerLeSablier;
	}

	/**
	 * Sets the lancer le sablier.
	 *
	 * @param lancerLeSablier the new lancer le sablier
	 */
	public void setLancerLeSablier(JButton lancerLeSablier) {
		LancerLeSablier = lancerLeSablier;
	}
}
