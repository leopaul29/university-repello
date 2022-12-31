package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N20 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode getTrajectoire", 3); {
			
			Tests.Case("Calcul d'une trajectoire sans rebond"); {
				/* Passer la methode getTrajectoire en public pour tester */
				/* ------------------------------------------------------ */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				Tests.Unit("[(6,4), (6,5), (6,6), (6,7), (6,8), (6,9)]",modele.getTrajectoire(new Coordonnee(6,4), new Vecteur (0,-1), 5).toString());
			}
			
			Tests.Case("Calcul d'une trajectoire avec rebond"); {
				/* Passer la methode retirerPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(5, 4, "jaune");
				modele.placer(10, 5, "bleu");
				Tests.Unit("[(4,5), (3,4), (2,3), (1,2), (2,1), (3,2), (4,3)]",modele.getTrajectoire(new Coordonnee(4,5), new Vecteur (-1,1), 6).toString());
			}
		}
		Tests.End();
	}
}
