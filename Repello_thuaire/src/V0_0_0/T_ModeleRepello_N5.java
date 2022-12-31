package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N5 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode deplacer", 3); {
			
			Tests.Case("Deplacement impossible car vecteur non unitaire"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);				
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 4, "jaune");
				modele.placer(4, 10, "bleu");
				Tests.Unit(false, modele.deplacer(4, 4, 4, 6));
			}
			
			Tests.Case("Deplacement impossible car coordonnee en dehors du plateau"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);				
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 4, "jaune");
				modele.placer(4, 10, "bleu");
				Tests.Unit(false, modele.deplacer(0, 0, 1, 1));
			}
			
			Tests.Case("Deplacement impossible car case vide"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);	
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 4, "jaune");
				modele.placer(4, 10, "bleu");
				Tests.Unit(false, modele.deplacer(5, 4, 5, 5));
			}
			
			Tests.Case("Deplacement impossible car mauvais stack"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);		
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 4, "jaune");
				modele.placer(4, 10, "bleu");
				Tests.Unit(false, modele.deplacer(4, 10, 5, 10));
			}
			
			Tests.Case("Deplacement impossible car chemin impossible"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				modele.placer(4, 4, "jaune");
				modele.placer(4, 10, "bleu");
				Tests.Unit(true, modele.deplacer(4, 4, 5, 4));
				Tests.Unit(false, modele.deplacer(4, 10, 4, 9));
			}
		}
		Tests.End();
	}
}
