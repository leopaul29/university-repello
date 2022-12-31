package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N9 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode deplacer (rebonds cotés)", 3); {
			
			Tests.Case("Deplacement coté Nord avec rebond vers le Nord/Ouest"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 7, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(4, 7, 3, 6));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(4, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(4, 1).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement coté Nord avec rebond vers le Nord/Est"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 4, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(4, 4, 3, 5));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(4, 4).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(3, 9).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement coté Sud avec rebond vers le Sud/Est"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 9, "jaune");
				modele.placer(10, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 9, 11, 10));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 9).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 13).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement coté Sud avec rebond vers le Sud/Ouest"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 7, "jaune");
				modele.placer(10, 10, "bleu");
				Tests.Unit(true, modele.deplacer(10, 7, 11, 6));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 1).isOccupeeParStack());
			}
		}
		Tests.End();
	}
}
