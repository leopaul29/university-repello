package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N13 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode voler", 3); {
			
			Tests.Case("Cas d'un vol de repeller"); {
				/* Passer la methode ajouterPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				modele.ajouterPointsJoueur("jaune", new Or ());
				Tests.Unit(5, modele.getScore("jaune"));
				Tests.Unit(0, modele.getScore("bleu"));
				Tests.Unit(15, modele.getTailleStack("jaune"));
				Tests.Unit(true,modele.voler("bleu", "jaune", "or"));
				Tests.Unit(0, modele.getScore("jaune"));
				Tests.Unit(6, modele.getScore("bleu"));
				Tests.Unit(14, modele.getTailleStack("jaune"));
			}
			
			Tests.Case("Cas d'une liste vide"); {
				/* Passer la methode ajouterPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				Tests.Unit(0, modele.getScore("jaune"));
				Tests.Unit(0, modele.getScore("bleu"));
				Tests.Unit(15, modele.getTailleStack("jaune"));
				Tests.Unit(true,modele.voler("bleu", "jaune", "or"));
				Tests.Unit(0, modele.getScore("jaune"));
				Tests.Unit(1, modele.getScore("bleu"));
				Tests.Unit(14, modele.getTailleStack("jaune"));
			}
		}
		Tests.End();
	}
}
