package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N22 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode joueurSuivant", 3); {
			
			Tests.Case("Changement correct"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				couleurs.add("vert");
				couleurs.add("rouge");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				modele.placer(10, 10, "vert");
				modele.placer(10, 8, "rouge");
				Tests.Unit("jaune",modele.getJoueurCourant());
				modele.joueurSuivant();
				Tests.Unit("bleu",modele.getJoueurCourant());
				modele.joueurSuivant();
				Tests.Unit("vert",modele.getJoueurCourant());
				modele.joueurSuivant();
				Tests.Unit("rouge",modele.getJoueurCourant());
				modele.joueurSuivant();
				Tests.Unit("jaune",modele.getJoueurCourant());
				modele.joueurSuivant();
			}
		}
		Tests.End();
	}
}
