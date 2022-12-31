package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_A3 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode voler", 3); {
			
			Tests.Case("Cas d'une couleur de voleur inexistante"); {
				/* Passer la methode ajouterPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				try {
					modele.commencer(couleurs);
				} catch (Throwable e) {}
				try {
					modele.placer(4, 6, "jaune");
				} catch (Throwable e) {}
				try {
					modele.placer(10, 5, "bleu");
				} catch (Throwable e) {}
				try {
					modele.ajouterPointsJoueur("jaune", new Or ());
				} catch (Throwable e) {}
				try {
					Tests.Unit(true,modele.voler("vert", "jaune", "or"));
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.2", e.toString());
				}
			}
			
			Tests.Case("Cas d'une couleur de voleur inexistante"); {
				/* Passer la methode ajouterPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				try {
					modele.commencer(couleurs);
				} catch (Throwable e) {}
				try {
					modele.placer(4, 6, "jaune");
				} catch (Throwable e) {}
				try {
					modele.placer(10, 5, "bleu");
				} catch (Throwable e) {}
				try {
					modele.ajouterPointsJoueur("jaune", new Or ());
				} catch (Throwable e) {}
				try {
					Tests.Unit(true,modele.voler("jaune", "vert", "or"));
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.2", e.toString());
				}
			}
		}
		Tests.End();
	}
}
