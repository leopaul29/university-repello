package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N7 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode deplacer (rebonds perpendiculaires)", 3); {
			
			Tests.Case("Deplacement avec rebond vers le Nord"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 4, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(4, 4, 3, 4));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(4, 4).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 4).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement avec rebond vers le Sud"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 10, "jaune");
				modele.placer(10, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 10, 11, 10));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 10).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 10).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement avec rebond vers l'Ouest"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 4, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(10, 4, 10, 3));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 4).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 3).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement avec rebond vers l'Est"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(5, 10, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(5, 10, 5, 11));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(5, 10).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(5, 12).isOccupeeParStack());
			}
		}
		Tests.End();
	}
}
