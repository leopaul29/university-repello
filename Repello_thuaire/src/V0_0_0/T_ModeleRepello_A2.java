package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_A2 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode placer", 3); {
			
			Tests.Case("Placement d'une couleur null"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				try {
					modele.commencer(couleurs);
				} catch (Throwable e) {}
				try {
					Tests.Unit(true, modele.placer(4, 4, null));
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.3",e.toString());
				}
			}
			
			Tests.Case("Placement d'une couleur inexistante"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				try {
					modele.commencer(couleurs);
				} catch (Throwable e) {}
				try {
					Tests.Unit(true, modele.placer(4, 4, "orange"));
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.3",e.toString());
				}
			}
		}
		Tests.End();
	}
}
