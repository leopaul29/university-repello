package V0_0_0;
import java.util.HashMap;

public class T_ModeleRepello_N1 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {
			
			Tests.Case("Constructeur normal"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ModeleRepello modele = new ModeleRepello(config);
				Tests.Unit(null, modele.getJoueurCourant());
				Tests.Unit(new HashMap <String,Joueur> (), modele.getLesJoueurs());
				Tests.Unit(13, modele.getPlateau().getLongueur());
				Tests.Unit(13, modele.getPlateau().getHauteur());
			}
		}
		Tests.End();
	}
}
