package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N12 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode expulser", 3); {
			
			Tests.Case("Expulsion d'un repeller"); {
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
				Tests.Unit("argent", modele.expulser(7, 12, 7, 13));
				Tests.Unit(3, modele.getScore(modele.getJoueurCourant()));
			}
			
			Tests.Case("Expulsion d'un stack"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 6, "jaune");
				modele.placer(10, 5, "bleu");
				//TODO joueur pour trouver une partie qui marche 
			}
		}
		Tests.End();
	}
}
