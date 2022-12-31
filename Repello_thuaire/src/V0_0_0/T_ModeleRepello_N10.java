package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N10 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode expulser", 3); {
			
			Tests.Case("Expulsion impossible (expulsion de soit meme)"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 5, "jaune");
				modele.placer(10, 10, "bleu");
				modele.deplacer(10, 5, 11, 4);
				Tests.Unit(null, modele.expulser(12, 1, 12, 1));
			}
			
			Tests.Case("Expulsion impossible (coordonnee hors plateau)"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 5, "jaune");
				modele.placer(10, 10, "bleu");
				modele.deplacer(10, 5, 11, 4);
				Tests.Unit(null, modele.expulser(-1, 1, 12, 1));
				Tests.Unit(null, modele.expulser(1, 1, -12, 1));
			}	
			
			Tests.Case("Expulsion impossible (coordonnee non adjacentes)"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 5, "jaune");
				modele.placer(10, 10, "bleu");
				modele.deplacer(10, 5, 11, 4);
				Tests.Unit(null, modele.expulser(1, 1, 12, 1));
			}
			
			Tests.Case("Expulsion impossible (case repousseur non occupée)"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 5, "jaune");
				modele.placer(10, 10, "bleu");
				modele.deplacer(10, 5, 11, 4);
				Tests.Unit(null, modele.expulser(1, 1, 1, 2));
			}
			
			Tests.Case("Expulsion impossible (case repoussé non occupée)"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 5, "jaune");
				modele.placer(10, 10, "bleu");
				modele.deplacer(10, 5, 11, 4);
				Tests.Unit(null, modele.expulser(12, 1, 11, 2));
			}
			
			Tests.Case("Expulsion impossible (sortir soit meme)"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(10, 5, "jaune");
				modele.placer(10, 10, "bleu");
				modele.deplacer(10, 5, 11, 4);
				Tests.Unit(null, modele.expulser(12, 2, 12, 1));
			}
			
			Tests.Case("Expulsion impossible (case d'arrivée déjà occupée)"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				modele.deplacer(4, 6, 4, 7);
				modele.joueurSuivant();
				modele.deplacer(10, 5, 10, 6);
				modele.joueurSuivant();
				modele.deplacer(4, 10, 5, 10);
				modele.joueurSuivant();
				modele.deplacer(10, 9, 9, 8);
				modele.expulser(7, 6, 7, 7);
				modele.joueurSuivant();
				modele.deplacer(7, 10, 8, 10);
				modele.expulser(10, 10, 10, 9);
				modele.joueurSuivant();
				modele.deplacer(7, 6, 8, 6);
				modele.expulser(12, 6, 12, 7);
				modele.joueurSuivant();
				modele.deplacer(10, 10, 11, 10);
				modele.joueurSuivant();
				modele.deplacer(12, 6, 12, 5);
				modele.expulser(12, 5, 12, 6);
				modele.expulser(12, 7, 12, 8);
				modele.expulser(12, 9, 12, 10);	
				modele.expulser(12, 11, 12, 12);	
				modele.joueurSuivant();
				modele.deplacer(12, 11, 11, 11);
				modele.expulser(7, 11, 7, 12);
				modele.expulser(7, 10, 7, 11);
				modele.expulser(7, 12, 7, 13);
				modele.joueurSuivant();
				modele.deplacer(12, 5, 11, 4);
				modele.expulser(8, 1, 7, 2);
				modele.joueurSuivant();
				modele.deplacer(7, 12, 6, 12);
				modele.expulser(6, 12, 7, 12);
				modele.joueurSuivant();
				modele.deplacer(8, 1, 7, 1);
				modele.joueurSuivant();
				modele.deplacer(6, 12, 6, 11);
				modele.expulser(6, 6, 7, 6);
				modele.joueurSuivant();
				modele.deplacer(4, 1, 3, 2);
				modele.expulser(2, 3, 2, 2);
				modele.joueurSuivant();
				modele.deplacer(6, 6, 7, 5);
				modele.joueurSuivant();
				modele.deplacer(2, 3, 2, 4);
				modele.joueurSuivant();
				modele.deplacer(8, 4, 9, 4);
				modele.expulser(13, 4, 12, 5);
				modele.getPlateau().afficher();
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 5).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(11, 6).isOccupeeParRepeller());
				Tests.Unit(null, modele.expulser(12, 7, 11, 6));
				Tests.Unit(true, modele.getPlateau().getCaseGrille(10, 5).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(11, 6).isOccupeeParRepeller());
			}
		}
		Tests.End();
	}
}
