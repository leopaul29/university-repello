package Repello;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * La  Class PanneauJoueur.
 */
public class PanneauJoueur extends JPanel implements Observer{
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Le label nom joueur. */
	private JLabel labelNomJoueur;
	
	/** Le label nb noir. */
	private JLabel labelNbNoir;
	
	/** Le label nb argent. */
	private JLabel labelNbArgent;
	
	/** Le label nb or. */
	private JLabel labelNbOr;
	
	/** Le label nb repeller stack. */
	private JLabel labelNbRepellerStack;
	
	/** Le label total. */
	private JLabel labelTotal;	
	
	/** Le pan image rep noir. */
	private PanneauImages panImageRepNoir;
	
	/** Le pan image rep argent. */
	private PanneauImages panImageRepArgent;
	
	/** Le pan image rep or. */
	private PanneauImages panImageRepOr;
	
	/** Le pan image stack. */
	private PanneauImages panImageStack;
	
	/** Le hamecon. */
	private JFrame hamecon;
	
	/** Le recup. */
	@SuppressWarnings("rawtypes")
	private HashMap recup;
	
	/**
	 * Constructeur de panneau joueur.
	 *
	 * @param j the j
	 * @param hamecon the hamecon
	 * @param configRepello the config repello
	 */
	@SuppressWarnings("rawtypes")
	public PanneauJoueur(Joueur j , JFrame hamecon, HashMap configRepello) {
		recup = (HashMap) configRepello.get("panneau_joueur");
		this.hamecon = hamecon;
		
		setBackground((Color) recup.get("background"));
		
		setLayout(new BorderLayout());
		
		//Panneau Haut
		//
		JPanel panHaut = new JPanel();
		panHaut.setBackground(j.getCouleur());
		add(panHaut, BorderLayout.NORTH);
		
		labelNomJoueur = new JLabel(j.getNom());
		panHaut.add( labelNomJoueur, JLabel.CENTER); 
		
		//Panneau Centrale
		//
		JPanel panCentre = new JPanel(new BorderLayout());
		add(panCentre);
		
		JPanel panCentreImage = new JPanel(new GridLayout((Integer)recup.get("layout_x"),(Integer)recup.get("layout_y")));
		panCentre.add(panCentreImage, BorderLayout.CENTER);
		
		//image RepNoir
		//
		panImageRepNoir = new PanneauImages((String) recup.get("image_repelleur_noir"), this);
		panImageRepNoir.setBackground((Color) recup.get("background_noir"));
		panCentreImage.add(panImageRepNoir);
		
		//image RepArgent
		//
		panImageRepArgent = new PanneauImages((String) recup.get("image_repelleur_gris"), this);
		panImageRepArgent.setBackground((Color) recup.get("background_argent"));
		panCentreImage.add(panImageRepArgent);
		
		//image RepOr
		//
		panImageRepOr = new PanneauImages((String) recup.get("image_repelleur_or"), this);
		panImageRepOr.setBackground((Color) recup.get("background_or"));
		panCentreImage.add(panImageRepOr);
		
		//image Stack
		//
		panImageStack =  new PanneauImages(j.getStack().getImage(), this);
		panImageStack.setBackground(Color.white);
		panCentreImage.add(panImageStack);
		
		
		//Panneau Centre.Bas
		//
		JPanel panCentreBas = new JPanel(new GridLayout((Integer) recup.get("layout_centre_x"),(Integer) recup.get("layout_centre_y")));
		panCentreBas.setBackground((Color) recup.get("layout__centre_background"));
		panCentre.add(panCentreBas, BorderLayout.SOUTH);
		
		//label Reppel Noir
		//
		labelNbNoir = new JLabel( String.valueOf(j.nbRepell("noir")), JLabel.CENTER);
		panCentreBas.add(labelNbNoir);
		
		//Label Reppel Argent
		//
		labelNbArgent = new JLabel( String.valueOf(j.nbRepell("argent")), JLabel.CENTER );
		panCentreBas.add(labelNbArgent);
		
		//label Reppel Or
		//
		labelNbOr = new JLabel( String.valueOf(j.nbRepell("or")), JLabel.CENTER);
		panCentreBas.add(labelNbOr);
		
		//label Reppel Or
		//
		labelNbRepellerStack = new JLabel( String.valueOf(j.getStack().getListRepeller().size()), JLabel.CENTER);
		panCentreBas.add(labelNbRepellerStack);
		
		
		// PanneauBas
		//
		JPanel panBas = new JPanel(new GridLayout((Integer)recup.get("layout_bas_x"), (Integer) recup.get("layout_bas_y")));
		panBas.setBackground((Color) recup.get("layout_bas_background"));
		add(panBas, BorderLayout.SOUTH);
		
		panBas.add(new JLabel((String) recup.get("total_point")));
		
		labelTotal = new JLabel(String.valueOf(j.calculePoint()));
		panBas.add(labelTotal);
	}
	
	
	
	/**
	 * Getter de  pan image rep noir.
	 *
	 * @return le pan image rep noir
	 */
	public PanneauImages getPanImageRepNoir (){ return panImageRepNoir;}
	
	/**
	 * Getter de  pan image rep argent.
	 *
	 * @return le pan image rep argent
	 */
	public PanneauImages getPanImageRepArgent (){ return panImageRepArgent;}
	
	/**
	 * Getter de  pan image rep or.
	 *
	 * @return le pan image rep or
	 */
	public PanneauImages getPanImageRepOr (){ return panImageRepOr;}
	
	/**
	 * Getter de  pan image stack.
	 *
	 * @return le pan image stack
	 */
	public PanneauImages getPanImageStack (){ return panImageStack;}
	
	/**
	 * Getter de  label nom.
	 *
	 * @return le label nom
	 */
	public JLabel getLabelNom (){ return labelNomJoueur;}
	
	/**
	 * Getter de  hamecon.
	 *
	 * @return le hamecon
	 */
	public JFrame getHamecon (){ return hamecon;}



	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if( !(arg1 instanceof HashMap ) ) return;
		
		HashMap<String, HashMap<String, Integer>> donne = (HashMap<String, HashMap<String, Integer>>) arg1;
		
		Iterator<String> i = donne.keySet().iterator();
		String nom;
		
		while(i.hasNext()){
			nom = i.next();	
			
			if(nom.equals(labelNomJoueur.getText()) )
				miseAjour(donne.get(nom));
		}
		
	}
	
	
	/**
	 * Mise ajour.
	 *
	 * @param donne the donne
	 */
	private void miseAjour( HashMap<String, Integer> donne){
		int valeurdefaut = (Integer) recup.get("valeur_defaut");
		
		int nbRepNoir = valeurdefaut;
		int nbRepArgent = valeurdefaut;
		int nbRepOr =  valeurdefaut;
		int nbRepDeStack = valeurdefaut;
		int point = valeurdefaut;
		
		if( donne.containsKey("RepNoir") ) nbRepNoir = donne.get("RepNoir");
		if( donne.containsKey("RepArgent") ) nbRepArgent = donne.get("RepArgent");
		if( donne.containsKey("RepOr") ) nbRepOr = donne.get("RepOr");
		if( donne.containsKey("RepStack") ) nbRepDeStack = donne.get("RepStack");
		if( donne.containsKey("point") ) point = donne.get("point");

		System.out.println(nbRepArgent);
		
		labelNbNoir.setText( String.valueOf(nbRepNoir));
		labelNbArgent.setText( String.valueOf(nbRepArgent));
		labelNbOr.setText( String.valueOf(nbRepOr));
		labelNbRepellerStack.setText(String.valueOf(nbRepDeStack));
		labelTotal.setText( String.valueOf(point));
		
		
	}
	
	

}
