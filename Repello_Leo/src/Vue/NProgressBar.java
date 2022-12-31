/**
 *
 * Classe NProgressBar
 * @author Nathaël Noguès
 * @version 1.2.0
 *
 *	
 *	Version 1.0.0
 *		Version initiale
 *		Ajout des règles de couleurs
 *		Ajout d'une variable en conséquence
 *		Modification des constructeurs en conséquence
 *		Ajout d'une démo (main)
 *	
 *	Version 1.0.1
 *		Ajout des règles de dégradés de couleurs
 *		
 *	
 *	Version 1.1.0
 *		Ajout du StringPainted formatable
 *			Ajout d'une variable en conséquence	
 *	
 *	Version 1.2.0
 *		Ajout des règles de dégradés de couleurs pour le texte
 *			Ajout d'une variable en conséquence
 *			Modification des constructeurs en conséquence
 *	
 */

package Vue;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.BoundedRangeModel;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

// TODO: Auto-generated Javadoc
/**
 * The Class NProgressBar.
 */
public class NProgressBar extends JProgressBar
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4946080885310544037L;	//	Automatiquement généré

	/** The coloring rules. */
	private HashSet<HashMap<String, Object>> coloringRules;	// Ensemble des règles de coloration de la barre

	/** The text coloring rules. */
	private HashSet<HashMap<String, Object>> textColoringRules;	// Ensemble des règles de coloration du texte

	/** The str rule. */
	private String strRule = null;	//	Texte à afficher sans remplacement des chaines automatiques

	/** The c in. */
	private Color cIn;	//	Couleur actuelle du texte par dessus la barre

	/** The c out. */
	private Color cOut;	//	Couleur actuelle du texte non dessus la barre

	/**
	 * Creates a horizontal progress bar that displays a border but no progress string. The initial and minimum values are 0, and the maximum is 100. 
	 */
	public NProgressBar()
	{	super();
	this.resetNPB();
	}

	/**
	 * Creates a progress bar with the specified orientation, which can be either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL. By default, a border is painted but a progress string is not. The initial and minimum values are 0, and the maximum is 100. 
	 *
	 * @param orient the desired orientation of the progress bar 
	 * @throws IllegalArgumentException if orient is an illegal value
	 */
	public NProgressBar(final int orient) throws IllegalArgumentException 
	{	super(orient);
	this.resetNPB();
	}

	/**
	 * Creates a horizontal progress bar with the specified minimum and maximum.
	 * Sets the initial value of the progress bar to the specified minimum.
	 * By default, a border is painted but a progress string is not. 
	 * <p>The BoundedRangeModel that holds the progress bar's data handles any issues that may arise from improperly setting the minimum, initial, and maximum values on the progress bar.
	 * See the BoundedRangeModel documentation for details.</p> 
	 *
	 * @param min the minimum value of the progress bar
	 * @param max the maximum value of the progress bar
	 */
	public NProgressBar(final int min, final int max)
	{	super(min, max);
	this.resetNPB();
	}

	/**
	 * Creates a progress bar using the specified orientation, minimum, and maximum.
	 * By default, a border is painted but a progress string is not.
	 * <p>Sets the initial value of the progress bar to the specified minimum.</p>
	 * <p>The BoundedRangeModel that holds the progress bar's data handles any issues that may arise from improperly setting the minimum, initial, and maximum values on the progress bar.
	 * See the BoundedRangeModel documentation for details.</p>
	 *
	 * @param orient the desired orientation of the progress bar
	 * @param min the minimum value of the progress bar
	 * @param max the maximum value of the progress bar 
	 * @throws IllegalArgumentException if orient is an illegal value
	 */
	public NProgressBar(final int orient, final int min, final int max) throws IllegalArgumentException
	{	super(orient, min, max);
	this.resetNPB();
	}

	/**
	 * Creates a horizontal progress bar that uses the specified model to hold the progress bar's data.
	 * By default, a border is painted but a progress string is not. 
	 * 
	 * @param newModel the data model for the progress bar
	 */
	public NProgressBar(final BoundedRangeModel newModel)
	{	super(newModel);
	this.resetNPB();
	}

	/**
	 * Supprime toutes les règles de coloration de la progressBar.
	 */
	private void resetNPB()
	{
		coloringRules = new HashSet<HashMap<String, Object>>();
		textColoringRules = new HashSet<HashMap<String, Object>>();
		cIn = Color.WHITE;
		cOut = new Color(51, 153, 255);
		this.setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return NProgressBar.this.getOutColor(); }
			protected Color getSelectionForeground() { return NProgressBar.this.getInColor(); }
		});
	}

	/**
	 * Gets the in color.
	 *
	 * @return couleur actuelle du texte à afficher par dessus la barre
	 */
	private Color getInColor()
	{	return cIn;	}

	/**
	 * Gets the out color.
	 *
	 * @return couleur actuelle du texte à afficher non par dessus la barre
	 */
	private Color getOutColor()
	{	return cOut;	}


	//	//	//	//	REGLES DE COLORATION DE LA BARRE	//	//	//	//	//

	/**
	 * Ajoute une règle de coloration à la barre de chargement.
	 *
	 * @param min Valeur a partir de laquelle la barre sera de cette couleur
	 * @param max Valeur a partir de laquelle la barre ne sera plus de cette couleur
	 * @param minColor Couleur qu'aura la barre si elle est à la valeur du param min
	 * @param maxColor Couleur qu'aura la barre si elle est à la valeur du param max (entre les deux, ce sera un dégradé)
	 * @param relative True signifie que "min" et "max" sont en pourcents (Sinon en valeur numériques)
	 */
	public void addColoringRule(final double min, final double max, final Color minColor, final Color maxColor, final boolean relative)
	{
		HashMap<String, Object> hmSO = new HashMap<String, Object>();
		hmSO.put("min", min);
		hmSO.put("max", max);
		hmSO.put("minColor", minColor);
		hmSO.put("maxColor", maxColor);
		hmSO.put("relative", relative);
		coloringRules.add(hmSO);
	}

	/**
	 * Équivalent à addColoringRule(min, max, minColor, maxColor, true).
	 *
	 * @param min Valeur a partir de laquelle la barre sera de cette couleur
	 * @param max Valeur a partir de laquelle la barre ne sera plus de cette couleur
	 * @param minColor Couleur qu'aura la barre si elle est à la valeur du param min
	 * @param maxColor Couleur qu'aura la barre si elle est à la valeur du param max (entre les deux, ce sera un dégradé)
	 * @see #addColoringRule(double, double, Color, Color, boolean)
	 */
	public void addColoringRule(final double min, final double max, final Color minColor, final Color maxColor)
	{	if(min == max)
		this.addColoringRule(min, max+Double.MIN_VALUE, minColor, maxColor, true);
	else
		this.addColoringRule(min, max, minColor, maxColor, true);
	}

	/**
	 * Équivalent à addColoringRule(min, max, color, color, relative).
	 *
	 * @param min Valeur a partir de laquelle la barre sera de cette couleur
	 * @param max Valeur a partir de laquelle la barre ne sera plus de cette couleur
	 * @param color Couleur que prendra la barre si elle est entre les bornes spécifiés
	 * @param relative True signifie que "min" et "max" sont en pourcents (Sinon en valeur numériques)
	 * @see #addColoringRule(double, double, Color, Color, boolean)
	 */
	public void addColoringRule(final double min, final double max, final Color color, final boolean relative)
	{	this.addColoringRule(min, max, color, color, relative);	}

	/** Équivalent à addColoringRule(min, max, color, color, true)
	 * 		La couleur de fond ne change pas.
	 * 
	 * @param min Valeur a partir de laquelle la barre sera de cette couleur
	 * @param max Valeur a partir de laquelle la barre ne sera plus de cette couleur
	 * @param color Couleur que prendra la barre si elle est entre les bornes spécifiés
	 * @see #addColoringRule(double, double, Color, Color, boolean)
	 */
	public void addColoringRule(final double min, final double max, final Color color)
	{	this.addColoringRule(min, max, color, color, true);	}

	/** Équivalent à addColoringRule(0, 1, minColor, maxColor, true)
	 * 		La couleur de fond est appliquée quelque soit la valeur de la barre.
	 * 
	 * @param minColor Couleur qu'aura la barre si elle est proche de 0%
	 * @param maxColor Couleur qu'aura la barre si elle est proche de 100% (entre 0 et 100%, ce sera un dégradé)
	 * @see #addColoringRule(double, double, Color, Color, boolean)
	 */
	public void addColoringRule(final Color minColor, final Color maxColor)
	{	this.addColoringRule(0, 1, minColor, maxColor, true);	}

	/** Équivalent à addColoringRule(0, 1, color, color, true)
	 * 		La couleur de fond ne change pas et est appliquée quelque soit la valeur de la barre.
	 * 
	 * @param color Couleur que prendra la barre
	 * @see #addColoringRule(double, double, Color, Color, boolean)
	 */
	public void addColoringRule(final Color color)
	{	this.addColoringRule(0, 1, color, color, true);	}

	//	//	//	//	REGLES DE COLORATION DU TEXTE	//	//	//	//	//
	/**
	 * Ajoute une règle de coloration au texte présent sur la barre de chargement.
	 *
	 * @param min Valeur a partir de laquelle la barre sera de cette couleur
	 * @param max Valeur a partir de laquelle la barre ne sera plus de cette couleur
	 * @param minColorIn Couleur qu'aura le texte par dessus la barre si la barre est à la valeur du param min
	 * @param maxColorIn Couleur qu'aura le texte par dessus la barre si la barre est à la valeur du param max (entre les deux, ce sera un dégradé)
	 * @param minColorOut Couleur qu'aura le texte qui n'est pas par dessus la barre si la barre est à la valeur du param min
	 * @param maxColorOut Couleur qu'aura le texte qui n'est pas par dessus la barre si la barre est à la valeur du param max (entre les deux, ce sera un dégradé)
	 * @param relative True signifie que "min" et "max" sont en pourcents (Sinon en valeur numériques)
	 */
	public void addTextColoringRule(final double min, final double max, final Color minColorIn, final Color maxColorIn, final Color minColorOut, final Color maxColorOut, final boolean relative)
	{
		HashMap<String, Object> hmSO = new HashMap<String, Object>();
		hmSO.put("min", min);
		hmSO.put("max", max);
		hmSO.put("minColorIn", minColorIn);
		hmSO.put("maxColorIn", maxColorIn);
		hmSO.put("minColorOut", minColorOut);
		hmSO.put("maxColorOut", maxColorOut);
		hmSO.put("relative", relative);
		textColoringRules.add(hmSO);
	}


	/**
	 * Adds the text coloring rule.
	 *
	 * @param min the min
	 * @param max the max
	 * @param minColorIn the min color in
	 * @param maxColorIn the max color in
	 * @param minColorOut the min color out
	 * @param maxColorOut the max color out
	 */
	public void addTextColoringRule(final double min, final double max, final Color minColorIn, final Color maxColorIn, final Color minColorOut, final Color maxColorOut)
	{	this.addTextColoringRule(min, max, minColorIn, maxColorIn, minColorOut, maxColorOut, true);	}

	/**
	 * Adds the text coloring rule.
	 *
	 * @param min the min
	 * @param max the max
	 * @param colorIn the color in
	 * @param colorOut the color out
	 * @param relative the relative
	 */
	public void addTextColoringRule(final double min, final double max, final Color colorIn, final Color colorOut, final boolean relative)
	{	this.addTextColoringRule(min, max, colorIn, colorIn, colorOut, colorOut, relative);	}

	/**
	 * Adds the text coloring rule.
	 *
	 * @param minColorIn the min color in
	 * @param maxColorIn the max color in
	 * @param minColorOut the min color out
	 * @param maxColorOut the max color out
	 */
	public void addTextColoringRule(final Color minColorIn, final Color maxColorIn, final Color minColorOut, final Color maxColorOut)
	{	this.addTextColoringRule(0, 1, minColorIn, maxColorIn, minColorOut, maxColorOut, true);	}

	/**
	 * Adds the text coloring rule.
	 *
	 * @param min the min
	 * @param max the max
	 * @param colorIn the color in
	 * @param colorOut the color out
	 */
	public void addTextColoringRule(final double min, final double max, final Color colorIn, final Color colorOut)
	{	this.addTextColoringRule(min, max, colorIn, colorIn, colorOut, colorOut, true);	}

	/**
	 * Adds the text coloring rule.
	 *
	 * @param colorIn the color in
	 * @param colorOut the color out
	 */
	public void addTextColoringRule(final Color colorIn, final Color colorOut)
	{	this.addTextColoringRule(0, 1, colorIn, colorIn, colorOut, colorOut, true);	}

	/**
	 * Sets the string.
	 *
	 * @param s Chaîne à afficher sur la barre
	 * <p>Les chaines suivantes seront remplacées:<br>
	 * "%min%" => minimum de la progressBar<br>
	 * "%max%" => maximum de la progressBar<br>
	 * "%val%" => valeur de la progressBar<br>
	 * "%pct%" => pourcentage de completion de la progressBar
	 * </p>
	 * <p>
	 * <i>exemple:
	 * "%val% (%pct%)" affichera "123 (45.6)" si la valeur est de 123 et la barre remplie de 45.6%</i>
	 * </p>
	 */
	@Override
	public void setString(final String s)
	{	//	Modification de la règle d'écriture
		strRule = s;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JProgressBar#fireStateChanged()
	 */
	@Override
	protected void fireStateChanged()
	{	//	Coloration de la barre
		for(HashMap<String, Object> hmSO : coloringRules)
		{	
			// Calcul de la valeur actuelle en fonction du paramètre "relative"
			double val = (Boolean)hmSO.get("relative")?this.getPercentComplete():this.getValue();

			//	Récupération des conditions d'activation de la règle
			double min = (Double)hmSO.get("min");
			double max = (Double)hmSO.get("max");

			//	Déterminer si la règle doit être suivie ou non
			if(val > max || val < min)
				continue;


			//	Calcul du pourcentage d'appliquation des couleurs
			double pc = (val-min)/(max-min);

			//	Calcul de la couleur
			Color cmin = (Color)hmSO.get("minColor");
			Color cmax = (Color)hmSO.get("maxColor");
			int r = (int) (cmin.getRed()*(1-pc)+cmax.getRed()*pc);
			int g = (int) (cmin.getGreen()*(1-pc)+cmax.getGreen()*pc);
			int b = (int) (cmin.getBlue()*(1-pc)+cmax.getBlue()*pc);

			//	Installation de la couleur
			super.setForeground(new Color(r, g, b));
		}

		//	écriture du texte
		if(strRule != null)
		{	//	Remplacement des chianes "spéciales" par les valeurs correspondantes
			String s = strRule.replaceAll("%min%", ""+this.getMinimum());
			s = s.replaceAll("%max%", ""+this.getMaximum());
			s = s.replaceAll("%val%", ""+this.getValue());
			s = s.replaceAll("%pct%", ""+(int)(100*this.getPercentComplete()));

			//	Installation de la chaîne
			super.setString(s);
		}

		//	Coloration du texte
		for(HashMap<String, Object> hmSO : textColoringRules)
		{
			// Calcul de la valeur actuelle en fonction du paramètre "relative"
			double val = (Boolean)hmSO.get("relative")?this.getPercentComplete():this.getValue();

			//	Récupération des conditions d'activation de la règle
			double min = (Double)hmSO.get("min");
			double max = (Double)hmSO.get("max");

			//	Déterminer si la règle doit être suivie ou non
			if(val > max || val < min)
				continue;

			//	Calcul du pourcentage d'appliquation des couleurs
			double pc = (val-min)/(max-min);

			//	Calcul de la couleur de texte dessus la barre
			Color cminIn = (Color)hmSO.get("minColorIn");
			Color cmaxIn = (Color)hmSO.get("maxColorIn");
			int rIn = (int) (cminIn.getRed()*(1-pc)+cmaxIn.getRed()*pc);
			int gIn = (int) (cminIn.getGreen()*(1-pc)+cmaxIn.getGreen()*pc);
			int bIn = (int) (cminIn.getBlue()*(1-pc)+cmaxIn.getBlue()*pc);

			//	Calcul de la couleur de texte hors de la barre
			Color cminOut = (Color)hmSO.get("minColorOut");
			Color cmaxOut = (Color)hmSO.get("maxColorOut");
			int rOut = (int) (cminOut.getRed()*(1-pc)+cmaxOut.getRed()*pc);
			int gOut = (int) (cminOut.getGreen()*(1-pc)+cmaxOut.getGreen()*pc);
			int bOut = (int) (cminOut.getBlue()*(1-pc)+cmaxOut.getBlue()*pc);

			//	Installation des couleurs
			cIn = new Color(rIn, gIn, bIn);
			cOut = new Color(rOut, gOut, bOut);
		}

		//	Mêtre a jour l'affichage
		super.fireStateChanged();
	}

	//	//	//	//	//	Démonstration	//	//	//	//	//
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args)
	{
		//	Installation d'une Frame contenant une NProgressBar
		javax.swing.JFrame jf = new javax.swing.JFrame();
		jf.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		NProgressBar npb = new NProgressBar(-500, 3000);
		jf.getContentPane().add(npb);
		jf.setVisible(true);
		jf.setSize(new java.awt.Dimension(1000, 100));

		//	Ajout des règles de coloration à la NProgressBar
		npb.addColoringRule(-500, 375, new Color(127, 0, 0), new Color(255, 127, 0), false);
		npb.addColoringRule(0.25, 0.5, new Color(255, 127, 0), new Color(255, 255, 0));
		npb.addColoringRule(0.5, 1, new Color(255, 255, 0), new Color(0, 255, 0));
		npb.addTextColoringRule(-500, 375, Color.BLACK, Color.BLACK, new Color(127, 0, 0), new Color(255, 127, 0), false);
		npb.addTextColoringRule(0.25, 0.5, Color.BLACK, Color.BLACK, new Color(255, 127, 0), new Color(127, 127, 0));
		npb.addTextColoringRule(0.5, 1, Color.BLACK, Color.BLACK, new Color(127, 127, 0), new Color(0, 255, 0));

		//	Ajout de la règle de texte à la NProgressBar
		npb.setString("Valeur: %val% (%pct%%) [%min% < %val% < %max%]");
		npb.setStringPainted(true);

		while(true)
		{
			try{	Thread.sleep(10);	}
			catch(Exception e){}
			if(npb.getPercentComplete()==0.0)
				npb.setValue(npb.getMaximum());
			else
				npb.setValue(npb.getValue()-1);
		}

	}
}
