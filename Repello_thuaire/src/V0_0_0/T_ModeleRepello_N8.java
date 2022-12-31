package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N8 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode deplacer (rebonds cotés)", 3); {
			
			Tests.Case("Deplacement coté Ouest avec rebond vers le Nord/Ouest"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(7, 4, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(7, 4, 6, 3));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 4).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(3, 2).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement coté Ouest avec rebond vers le Sud/Ouest"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(6, 4, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(6, 4, 7, 3));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(6, 4).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 4).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement coté Est avec rebond vers le Nord/Est"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 10, "jaune");
				modele.placer(10, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 10, 9, 11));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 10).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(4, 10).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement coté Est avec rebond vers le Sud/Est"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 10, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(4, 10, 5, 11));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(4, 10).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(8, 12).isOccupeeParStack());
			}
		}
		Tests.End();
	}
}
