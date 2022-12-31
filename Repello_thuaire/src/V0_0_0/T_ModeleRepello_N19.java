package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N19 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode retirerPointsJoueur", 3); {
			
			Tests.Case("Ajout d'un repeller or"); {
				/* Passer la methode retirerPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				modele.ajouterPointsJoueur("jaune", new Or());
				Tests.Unit(true, modele.getLesJoueurs().get("jaune").getMesRepellers().contains(new Or()));
				modele.retirerPointsJoueur("jaune", new Or());
				Tests.Unit(false, modele.getLesJoueurs().get("jaune").getMesRepellers().contains(new Or()));
			}
			
			Tests.Case("Ajout d'un repeller argent"); {
				/* Passer la methode retirerPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				modele.ajouterPointsJoueur("jaune", new Argent());
				Tests.Unit(true, modele.getLesJoueurs().get("jaune").getMesRepellers().contains(new Argent()));
				modele.retirerPointsJoueur("jaune", new Argent());
				Tests.Unit(false, modele.getLesJoueurs().get("jaune").getMesRepellers().contains(new Argent()));
			}
			
			Tests.Case("Ajout d'un repeller noir"); {
				/* Passer la methode retirerPointsJoueur en public pour tester */
				/* ----------------------------------------------------------- */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				modele.ajouterPointsJoueur("jaune", new Noir());
				Tests.Unit(true, modele.getLesJoueurs().get("jaune").getMesRepellers().contains(new Noir()));
				modele.retirerPointsJoueur("jaune", new Noir());
				Tests.Unit(false, modele.getLesJoueurs().get("jaune").getMesRepellers().contains(new Noir()));
			}
		}
		Tests.End();
	}
}
