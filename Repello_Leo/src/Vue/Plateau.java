/*
 * 
 */
package Vue;

import java.awt.Color;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * Class VuePlateau extends JPanel et implements Observer
 * permet de visualiser le plateau compos� de case qui observent le Modele.
 *
 * @author LeoPaul
 */
public class Plateau extends JPanel implements Observer{

	// ---												Attributs
	//
	/** le plateau. */
	private LinkedHashMap<?, ?> plateau;

	/** la liste case. */
	private HashMap<Integer, Case> listeCase;

	/**
	 * Constructeur normal du plateau.
	 * permet de construire le plateau de jeu en modifiant les caract�ristique de la fenetre qui accueil le plateau,
	 * et en ajoutant les case dedans.
	 *
	 * @param createur la JFrame qui accueil le plateau
	 * @param ConfigPlateau la config plateau
	 * @throws Throwable the throwable
	 */
	// ---												Constructeur normal 1
	public Plateau(JFrame createur, HashMap<?, ?> ConfigPlateau) throws Throwable{

		if(createur == null) throw new Throwable ("-0.5");
		if(ConfigPlateau == null) throw new Throwable ("-0.5");
		
		HashMap <?,?> config = ConfigPlateau;

		// Extraire et appliquer le gestionnaire de presentation de la fenetre
		//

		// Comme c'est le plateau est carr�, je r�cup�re une taille et je l'applique � la fenetre
		//
		int taille= (Integer) config.get("taille");
		if (taille > 0) createur.setSize(taille, taille);
		else throw new Throwable ("-1.0");

		// Je r�cup�re le titre de la fenetre
		//
		String titre= (String) config.get("titre");
		if (titre != null) createur.setTitle(titre);
		else throw new Throwable ("-1.1");

		//Je r�cup�re les coordonn�es ou faire apparaitre le plateau
		//
		int locX= (Integer) config.get("locationX");
		int locY= (Integer) config.get("locationY");
		if (locX >= 0)if (locY >= 0) createur.setLocation(locX, locY);
		else throw new Throwable ("-1.2");

		// Je r�cup�re la donn�e pour savoir si la fenetre du plateau est redimensionable
		//
		boolean resize= (Boolean) config.get("resizable");
		if(resize == true) createur.setResizable(true);
		else createur.setResizable(false);

		// Je r�cup�re la donn�e pour savoir l'�v�nement qui suit la fermeture du plateau
		//
		int fermer= (Integer) config.get("fermer");
		if (fermer > 0) createur.setDefaultCloseOperation(fermer);
		else throw new Throwable ("-1.3");

		// Je r�cup�re le layout du plateau
		//
		Object w= config.get("placement");
		if (w!=null)this.setLayout((LayoutManager)w);
		else throw new Throwable ("-1.4");

		// Je r�cup�re la couleur de fond du plateau
		//
		Color arrierePlan= (Color) config.get("arrierePlan");
		if (arrierePlan != null) setBackground(arrierePlan);
		else throw new Throwable ("-1.5");

		// Je r�cup�re les cases du plateau
		//
		plateau = (LinkedHashMap<?, ?>) config.get("plateau");
		if(plateau == null)throw new Throwable ("-3.1");

		// Je cr�er un HashMap qui va me permettre garder le numero des cases pour les modifier
		//
		listeCase = new HashMap<Integer, Case>();

		// Je r�cup�re chaque donn�es du plateau
		//
		for(int i = 0; i < plateau.size() ; i ++)
		{
			/* Je r�cup�re ensuite plusieurs donn�e consernant les cases :
			 * Le numero de la case,
			 * Le chemin image � charger pour afficher la case
			 * La couleur de fond,
			 */
			HashMap<?, ?> infoCase =  (HashMap<?, ?>) plateau.get(i);
			String cheminImageCase = (String) infoCase.get("image");
			Color fondCase = (Color) infoCase.get("arrierePlan");

			/* Je cr�er une nouvelle case � laquelle j'applique les donn�es correspondantes
			 * (imageCase et couleur de fond)
			 */
			Case c = new Case(cheminImageCase, fondCase);

			// Je met la case cr�e dans la liste des cases
			//
			listeCase.put(i, c);

			// j'ajoute la case au plateau
			//
			this.add(c);

		}

		createur.getContentPane().add(this);

		// Je rend la fenetre visible en fonction des donn�es
		//
		boolean visible= (Boolean) config.get("visible");
		if(visible == true) createur.setVisible(true);
		else createur.setVisible(false);
	}

	/**
	 * M�thode update,
	 * permet de modifier la vue par l'interm�diaire du ModeleGrille qui notofie celle-ci.
	 *
	 * @param Modele the modele
	 * @param modifs the modifs
	 */
	@Override
	public void update(Observable Modele, Object modifs) {
		// TODO Auto-generated method stub

		// En observant le modele, la vue r�cup�re le numero de la case � modifier
		//
		Case modificationCase = (Case) listeCase.get((((HashMap<?, ?>)modifs).get("case")));

		// Si elle doit posseder une piece
		//
		modificationCase.setPossedePiece((Boolean) ((HashMap<?, ?>)modifs).get("gererPiece"));

		// Si oui, le nom de la piece
		//
		modificationCase.gererPiece((String) ((HashMap<?, ?>)modifs).get("piece"));

	}

}
