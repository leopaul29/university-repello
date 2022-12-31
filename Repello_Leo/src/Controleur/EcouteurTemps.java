package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.Demonstrateur;

// TODO: Auto-generated Javadoc
/**
 * The Class EcouteurTemps.
 */
public class EcouteurTemps implements ActionListener{
	
	/** The createur. */
	private ControleurTempsG createur;
	private Demonstrateur vueD;

	/**
	 * Instantiates a new ecouteur temps.
	 *
	 * @param createur the createur
	 */
	public EcouteurTemps (ControleurTempsG createur, Demonstrateur vueD) {
		this.createur = createur;
		this.vueD = vueD;
	}
	
	/**
	 * Action performed.
	 *
	 * @param arg0 the arg0
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//
		if(e.getSource() == vueD.getLancerLeSablier())
		{
			try {
				createur.demarrer(60);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == vueD.getPause())
		{
			createur.suspendre();
		}
		
		if(e.getSource() == vueD.getReprendre())
		{
			createur.reprendre();
		}
	}

}
