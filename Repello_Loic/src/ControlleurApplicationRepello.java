
import Repello.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * La  Class ControlleurApplicationRepello.
 */
@SuppressWarnings("rawtypes")
public class ControlleurApplicationRepello implements ActionListener{
	
	/** Le fenetre de demarrage*/
	JFrame fenetre; 
	
	/** La vue */
	VueApplicationRepello vue;

	/** La config du controleur Application */
	private HashMap configControleurAppli;
	
	/** La config de Repello */
	private HashMap configRepello;
	
	
	
	/**
	 * Le constructeur par default
	 */
	public ControlleurApplicationRepello() {
		
		//On stock en mémoire les configs
		//
		configRepello = (HashMap)  Config.load("_Config/config_repello", "V0_0_8");
		configControleurAppli = (HashMap) configRepello.get("controleurApplicationRepello");
		
		//On init la frame
		//
		fenetre = new JFrame((String)configControleurAppli.get("nom_fenetre"));
		fenetre.setSize((Integer)configControleurAppli.get("largeur"),(Integer)configControleurAppli.get("hauteur"));
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		
		//On init la vue
		//
		vue = new VueApplicationRepello(configRepello);
		fenetre.getContentPane().add(vue);
		
		//On met en place les ecouteurs
		//
		vue.getBoutonDemarrer().addActionListener(this);
		vue.getBoutonCharger().addActionListener(this);
		vue.getBoutonConfigurer().addActionListener(this);
		vue.getBoutonQuitter().addActionListener(this);
		
		//On active la fenêtre
		//
		fenetre.setVisible(true);
	}
	
	/**
	 * 
	 * Permet de gérer l'événement lors d'un clique sur un objet de la vue.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//On teste la validité de la source
		//
		if( ! (e.getSource() instanceof JButton) ) return;
		
		//On récupére la source de l'evenement
		//
		JButton bouton = (JButton) e.getSource();
		
		//On gére le cas où l'on clique sur le bouton demarrer
		//
		if(bouton.equals(vue.getBoutonDemarrer()))
			actionDemarrer();
		
		//On gére le cas où l'on clique sur le bouton quitter
		//
		else if(bouton.equals( vue.getBoutonQuitter() ) )	
			System.exit(0);
		
		//On gére le cas où l'on clique sur le bouton configurer
		//
		else if(bouton.equals( vue.getBoutonConfigurer() ) )
			actionConfigurer();
	}
	
	/**
	 * 
	 * On gére le clique sur le bouton démarrer
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void actionDemarrer(){
		
		//On ferme la fenêtre
		//
		fenetre.dispose();
		
		//On charge les configs de repello
		//
		HashMap configSablier = new HashMap();
		HashMap configTempG  = (HashMap)Config.load( "_Config/Sablier/ConfigSablier", "3.3.0");
		HashMap configVue =  (HashMap)Config.load("_Config/Sablier/Config/VueSablier_A", "1.X.0");
		
		if( configTempG == null || configVue == null) new Throwable("Erreur chargement config sablier");
		
		configSablier.put("ParamTempG", configTempG);
		configSablier.put("VueSablier", configVue);
		
		//On lance le jeu
		//
		new ObservateurApplie(configSablier, configRepello);
	}
	
	/**
	 * 
	 * On gére le clique sur le bouton configurer
	 * 
	 */
	private void actionConfigurer(){
		
		//On ouvre une nouvelle fenêtre
		//
		/*JFrame fenetre_configurer = new JFrame((String) configControleurAppli.get("configurer_nom"));
		fenetre_configurer.setContentPane(new ConfigurerVue());
		fenetre_configurer.setSize((Integer)configControleurAppli.get("configurer_largeur"),(Integer)configControleurAppli.get("configurer_hauteur"));
		fenetre_configurer.setVisible(true);
		*/
	}
	
	
	/**
	 * Le main principale
	 *
	 * @param args the arguments
	 */
	public static void main (String [] args){
		
		new ControlleurApplicationRepello();
		
	}

}
