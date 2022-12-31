package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N4 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode placer", 3); {
			
			Tests.Case("Placement possible"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);				
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				Tests.Unit(true, modele.placer(8, 4, "jaune"));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(8, 4).isOccupeeParStack());
				Tests.Unit(true, modele.placer(10, 6, "bleu"));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 6).isOccupeeParStack());
			}
			
			Tests.Case("Placement en dehors terrain"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);			
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				Tests.Unit(false, modele.placer(-1, 4, "jaune"));
				Tests.Unit(true, modele.placer(10, 10, "bleu"));
			}
			
			Tests.Case("Placement sur case deja occupée"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);				
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				Tests.Unit(true, modele.placer(4, 4, "jaune"));
				Tests.Unit(false, modele.placer(4, 4, "bleu"));
			}
			
			Tests.Case("Placement sur case adjacente a une case occupée"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);				
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				Tests.Unit(true, modele.placer(4, 4, "jaune"));
				Tests.Unit(false, modele.placer(4, 5, "bleu"));
			}
		}
		Tests.End();
	}
}
