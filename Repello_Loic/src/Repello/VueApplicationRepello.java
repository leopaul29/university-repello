package Repello;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * La  Class VueApplicationRepello.
 */
public class VueApplicationRepello extends JPanel{
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le demarrer. */
	private JButton demarrer;
	
	/** Le charger. */
	private JButton charger;
	
	/** Le configurer. */
	private JButton configurer;
	
	/** Le quitter. */
	private JButton quitter;
	
	/** Le recup. */
	@SuppressWarnings("rawtypes")
	private HashMap recup;
	
	/**
	 * Constructeur de vue application repello.
	 *
	 * @param configRepello the config repello
	 */
	@SuppressWarnings("rawtypes")
	public VueApplicationRepello(HashMap configRepello) {
		
		recup = (HashMap) configRepello.get("vueapplication");
		//On change le layout
		//
		setLayout(new BorderLayout());
		
		//PanneauCentral
		//
		PanneauImages panImage = new PanneauImages((String) recup.get("fond"), this);
		add(panImage);
		
		//PanneauBas
		JPanel panBas = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(panBas, BorderLayout.SOUTH);
		
		demarrer = new JButton((String)recup.get("button_demarrer"));
		charger = new JButton((String)recup.get("button_charger"));
		configurer = new JButton((String)recup.get("button_configurer"));
		quitter = new JButton((String)recup.get("button_quitter"));
		
		panBas.add(demarrer);
		panBas.add(charger);
		panBas.add(configurer);
		panBas.add(quitter);
	}
	
	
	/**
	 * Getter de  bouton demarrer.
	 *
	 * @return le bouton demarrer
	 */
	public JButton getBoutonDemarrer(){ return demarrer;}
	
	/**
	 * Getter de  bouton charger.
	 *
	 * @return le bouton charger
	 */
	public JButton getBoutonCharger(){ return charger;}
	
	/**
	 * Getter de  bouton configurer.
	 *
	 * @return le bouton configurer
	 */
	public JButton getBoutonConfigurer(){ return configurer;}
	
	/**
	 * Getter de  bouton quitter.
	 *
	 * @return le bouton quitter
	 */
	public JButton getBoutonQuitter(){ return quitter;}
}
