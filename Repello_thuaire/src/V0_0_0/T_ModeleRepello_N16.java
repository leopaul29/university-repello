package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N16 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode getScore", 3); {
			
			Tests.Case("Cas d'un classement standard"); {
				/* Passer la methode ajouterPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				couleurs.add("vert");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				modele.placer(10, 10, "vert");
				modele.ajouterPointsJoueur("vert", new Or ());
				modele.ajouterPointsJoueur("jaune", new Argent ());
				modele.ajouterPointsJoueur("bleu", new Noir ());
				Tests.Unit(5, modele.getScore("vert"));
				Tests.Unit(3, modele.getScore("jaune"));
				Tests.Unit(1, modele.getScore("bleu"));
			}
		}
		Tests.End();
	}
}
