package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_A1 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle du commencement", 3); {
			
			Tests.Case("Partie à un joueur"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				ModeleRepello modele = new ModeleRepello(config);
				try {
					modele.commencer(couleurs);
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.1", e.toString());
				}
			}
			
			Tests.Case("Partie à cinq joueurs"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("vert");
				couleurs.add("bleu");
				couleurs.add("rouge");
				couleurs.add("orange");
				ModeleRepello modele = new ModeleRepello(config);
				try {
					modele.commencer(couleurs);
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.1", e.toString());
				}
			}

		}
		Tests.End();
	}
}
