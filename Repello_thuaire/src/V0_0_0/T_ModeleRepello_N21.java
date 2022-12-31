package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N21 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode verifierStack", 3); {
			
			Tests.Case("Verifier la bonne appartenance d'un stack"); {
				/* Passer la methode verifierStack en public pour tester */
				/* ----------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				Tests.Unit(true,modele.verifierStack("jaune", 6, 4));
			}
			
			Tests.Case("Verifier la mauvaise appartenance d'un stack"); {
				/* Passer la methode verifierStack en public pour tester */
				/* ----------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				Tests.Unit(false,modele.verifierStack("jaune", 5, 10));
			}
		}
		Tests.End();
	}
}
