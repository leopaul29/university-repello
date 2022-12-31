package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N6 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode deplacer", 3); {
			
			Tests.Case("Deplacement vers le Nord"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 10, "jaune");
				modele.placer(4, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 10, 9, 10));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 10).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(5, 10).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement vers le Nord/Est"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 6, "jaune");
				modele.placer(4, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 6, 9, 7));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 6).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(8, 8).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement vers l'Est"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 10, "jaune");
				modele.placer(4, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 10, 10, 11));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 10).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 13).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement vers le Sud/Est"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 8, "jaune");
				modele.placer(4, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 8, 11, 9));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 8).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(13, 11).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement vers le Sud"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 9, "jaune");
				modele.placer(4, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 9, 11, 9));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 9).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(13, 9).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement vers le Sud/Ouest"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 8, "jaune");
				modele.placer(4, 4, "bleu");
				Tests.Unit(true, modele.deplacer(10, 8, 11, 7));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 8).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(13, 5).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement vers le Ouest"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(6, 10, "jaune");
				modele.placer(4, 4, "bleu");
				Tests.Unit(true, modele.deplacer(6, 10, 6, 9));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(6, 10).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(6, 6).isOccupeeParStack());
			}
			
			Tests.Case("Deplacement vers le Nord/Ouest"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(6, 4, "jaune");
				modele.placer(4, 4, "bleu");
				Tests.Unit(true, modele.deplacer(6, 4, 5, 3));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(6, 4).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(4, 2).isOccupeeParStack());
			}
		}
		Tests.End();
	}
}
