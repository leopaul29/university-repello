package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;

public class T_ModeleRepello_N3 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle du commencement", 3); {
			
			Tests.Case("Partie à deux joueurs"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				/* Verification de l'initialisation du joueur courant */
				/* -------------------------------------------------- */
				Tests.Unit("jaune", modele.getJoueurCourant());
				/* Verification du placement des repellers de base */
				/* ----------------------------------------------- */
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 12).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 12).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 12).isOccupeeParRepeller());
				/* Verification de l'initialisation de la grille */
				/* --------------------------------------------- */
				for (int i=0; i<modele.getPlateau().getHauteur(); i++){
					for (int j=0; j<modele.getPlateau().getHauteur(); j++){
						Tests.Unit((int)modele.getValeursGrille()[i][j], modele.getPlateau().getCase(j+1, i+1).getValeurMouvement());
					}
				}
				/* Verification des clés du dictionnaire */
				/* ------------------------------------- */
				Tests.Unit(true,modele.getLesJoueurs().containsKey("jaune"));
				Tests.Unit(true,modele.getLesJoueurs().containsKey("bleu"));
				/* Verification de l'association clé valeur du dictionnaire */
				/* -------------------------------------------------------- */
				Tests.Unit("jaune",modele.getLesJoueurs().get("jaune").getCouleur());
				Tests.Unit("bleu",modele.getLesJoueurs().get("bleu").getCouleur());
				/* Verification de l'association entre un stack et son joueur */
				/* ---------------------------------------------------------- */
				Tests.Unit("jaune",modele.getLesJoueurs().get("jaune").getMonStack().getCouleur());
				Tests.Unit("bleu",modele.getLesJoueurs().get("bleu").getMonStack().getCouleur());
			}
			
			Tests.Case("Partie à trois joueurs"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("vert");
				couleurs.add("jaune");
				couleurs.add("bleu");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				/* Verification de l'initialisation du joueur courant */
				/* -------------------------------------------------- */
				Tests.Unit("vert", modele.getJoueurCourant());
				/* Verification du placement des repellers de base */
				/* ----------------------------------------------- */
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 12).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 12).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 12).isOccupeeParRepeller());
				/* Verification de l'initialisation de la grille */
				/* --------------------------------------------- */
				for (int i=0; i<modele.getPlateau().getHauteur(); i++){
					for (int j=0; j<modele.getPlateau().getHauteur(); j++){
						Tests.Unit((int)modele.getValeursGrille()[i][j], modele.getPlateau().getCase(j+1, i+1).getValeurMouvement());
					}
				}
				/* Verification des clés du dictionnaire */
				/* ------------------------------------- */
				Tests.Unit(true,modele.getLesJoueurs().containsKey("vert"));
				Tests.Unit(true,modele.getLesJoueurs().containsKey("jaune"));
				Tests.Unit(true,modele.getLesJoueurs().containsKey("bleu"));
				/* Verification de l'association clé valeur du dictionnaire */
				/* -------------------------------------------------------- */
				Tests.Unit("vert",modele.getLesJoueurs().get("vert").getCouleur());
				Tests.Unit("jaune",modele.getLesJoueurs().get("jaune").getCouleur());
				Tests.Unit("bleu",modele.getLesJoueurs().get("bleu").getCouleur());
				/* Verification de l'association entre un stack et son joueur */
				/* ---------------------------------------------------------- */
				Tests.Unit("vert",modele.getLesJoueurs().get("vert").getMonStack().getCouleur());
				Tests.Unit("jaune",modele.getLesJoueurs().get("jaune").getMonStack().getCouleur());
				Tests.Unit("bleu",modele.getLesJoueurs().get("bleu").getMonStack().getCouleur());
			}
			
			Tests.Case("Partie à quatre joueurs"); {
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ArrayList <String> couleurs = new ArrayList <String> ();
				couleurs.add("vert");
				couleurs.add("jaune");
				couleurs.add("bleu");
				couleurs.add("rouge");
				ModeleRepello modele = new ModeleRepello(config);
				modele.commencer(couleurs);
				/* Verification de l'initialisation du joueur courant */
				/* -------------------------------------------------- */
				Tests.Unit("vert", modele.getJoueurCourant());
				/* Verification du placement des repellers de base */
				/* ----------------------------------------------- */
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(2, 12).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(7, 12).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 2).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 7).isOccupeeParRepeller());
				Tests.Unit(true, modele.getPlateau().getCaseGrille(12, 12).isOccupeeParRepeller());
				/* Verification de l'initialisation de la grille */
				/* --------------------------------------------- */
				for (int i=0; i<modele.getPlateau().getHauteur(); i++){
					for (int j=0; j<modele.getPlateau().getHauteur(); j++){
						Tests.Unit((int)modele.getValeursGrille()[i][j], modele.getPlateau().getCase(j+1, i+1).getValeurMouvement());
					}
				}
				/* Verification des clés du dictionnaire */
				/* ------------------------------------- */
				Tests.Unit(true,modele.getLesJoueurs().containsKey("vert"));
				Tests.Unit(true,modele.getLesJoueurs().containsKey("jaune"));
				Tests.Unit(true,modele.getLesJoueurs().containsKey("bleu"));
				Tests.Unit(true,modele.getLesJoueurs().containsKey("rouge"));
				/* Verification de l'association clé valeur du dictionnaire */
				/* -------------------------------------------------------- */
				Tests.Unit("vert",modele.getLesJoueurs().get("vert").getCouleur());
				Tests.Unit("jaune",modele.getLesJoueurs().get("jaune").getCouleur());
				Tests.Unit("bleu",modele.getLesJoueurs().get("bleu").getCouleur());
				Tests.Unit("rouge",modele.getLesJoueurs().get("rouge").getCouleur());
				/* Verification de l'association entre un stack et son joueur */
				/* ---------------------------------------------------------- */
				Tests.Unit("vert",modele.getLesJoueurs().get("vert").getMonStack().getCouleur());
				Tests.Unit("jaune",modele.getLesJoueurs().get("jaune").getMonStack().getCouleur());
				Tests.Unit("bleu",modele.getLesJoueurs().get("bleu").getMonStack().getCouleur());
				Tests.Unit("rouge",modele.getLesJoueurs().get("rouge").getMonStack().getCouleur());
			}

		}
		Tests.End();
	}
}
