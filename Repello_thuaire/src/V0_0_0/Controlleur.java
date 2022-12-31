package V0_0_0;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Controlleur {
	@SuppressWarnings("resource")
	public static void main (String[] args) throws Throwable {
		
		/* Tableau a passer en entrée pour determiner les joueurs */
		/* ------------------------------------------------------ */
		String [] mesCouleurs = {"jaune","bleu"};
		
		ArrayList <String> couleurs = new ArrayList <String> ();
		int nbJoueur = mesCouleurs.length;
		int ligne = 0;
		int colonne = 0;
		String piece;
		boolean erreur = false;
		boolean finPartie = false;
		boolean stackVide = false;
		
		/* Dictionnaire de config */
		/* ---------------------- */
		HashMap <String, Integer [][]> config = ConfigModeleRepello.lire("ConfigModeleRepello.config");
		
		/* Saisie du nombre de joueur */
		/* -------------------------- */
		
		for (int i=0; i<mesCouleurs.length; i++){
			couleurs.add(mesCouleurs[i]);
		}

		ModeleRepello partie = new ModeleRepello(config);
		partie.commencer(couleurs);
		
		System.out.println("Placement des joueurs\n");
		/* Placement des stacks sur le terrain */
		/* ----------------------------------- */
		for (int i = 1; i<= mesCouleurs.length ; i++){
			do{
				System.out.println("Joueur "+partie.getLesJoueurs().get(mesCouleurs[i-1]).getCouleur()+"");
				System.out.println("Saisir ligne : ");
				Scanner sc1 = new Scanner(System.in);
				ligne = sc1.nextInt();
				System.out.println("Saisir colonne : ");
				Scanner sc2 = new Scanner(System.in);
				colonne = sc2.nextInt();
				erreur = partie.placer(ligne,colonne, partie.getLesJoueurs().get(mesCouleurs[i-1]).getCouleur());
			}while (erreur != true);
		}
		
		partie.getPlateau().afficher();
		
		/* Phase de jeu */
		/* ------------ */
		while (finPartie != true){
			System.out.println("Phase de mouvement\n");
			System.out.println("Joueur "+partie.getJoueurCourant()+"");
			/* Phase de deplacement */
			/* -------------------- */
			do {
				System.out.println("Choisir stack");
				System.out.println("Saisir ligne : ");
				Scanner sc3 = new Scanner(System.in);
				ligne = sc3.nextInt();
				System.out.println("Saisir colonne : ");
				Scanner sc4 = new Scanner(System.in);
				colonne = sc4.nextInt();
				System.out.println("Choisir direction");
				System.out.println("Saisir ligne : ");
				Scanner sc5 = new Scanner(System.in);
				int ligne2 = sc5.nextInt();
				System.out.println(ligne2);
				System.out.println("Saisir colonne : ");
				Scanner sc6 = new Scanner(System.in);
				int colonne2 = sc6.nextInt();
				System.out.println(colonne2);
				if (partie.getTailleStack(partie.getJoueurCourant()) == 1){
					System.out.println("stack vide");
					break;
				}
				erreur = (partie.deplacer(ligne,colonne, ligne2, colonne2) || partie.getTailleStack(partie.getJoueurCourant()) == 1);
			}while (erreur != true);

			partie.getPlateau().afficher(); 
			
			/* Verification de l'etat du plateau */
			/* --------------------------------- */
			erreur = partie.verifierPlateau();
			
			/* Phase de contre lancée que si le plateau est non reglementaire */
			/* -------------------------------------------------------------- */
			while (erreur != true){
				System.out.println("Phase de contre\n");
				System.out.println("Saisir l'abscisse de la piece qui repousse : ");
				Scanner sc1 = new Scanner(System.in);
				int x1 = sc1.nextInt();
				System.out.println("Saisir l'ordonnee de la piece qui repousse : ");
				Scanner sc2 = new Scanner(System.in);
				int y1 = sc2.nextInt();
				System.out.println("Saisir l'abscisse de la piece repoussee : ");
				Scanner sc3 = new Scanner(System.in);
				int x2 = sc3.nextInt();
				System.out.println("Saisir l'ordonnee de la piece repoussee : ");
				Scanner sc4 = new Scanner(System.in);
				int y2 = sc4.nextInt();
				piece = partie.expulser(x1,y1, x2,y2);
				if (piece != null){
					if (couleurs.contains(piece)){
						System.out.println("Saisir le repeller a voler : ");
						BufferedReader entree = new BufferedReader (new InputStreamReader(System.in));
						String couleur = entree.readLine();
						partie.voler(partie.getJoueurCourant(), piece, couleur);
						do{
							System.out.println("Joueur "+ piece +"");
							System.out.println("Saisir ligne : ");
							Scanner sc6 = new Scanner(System.in);
							ligne = sc6.nextInt();
							System.out.println("Saisir colonne : ");
							Scanner sc7 = new Scanner(System.in);
							colonne = sc7.nextInt();
							erreur = partie.placer(ligne,colonne, partie.getJoueurCourant());
						}while (erreur != true);
					}
				}
				partie.getPlateau().afficher();
				erreur = partie.verifierPlateau();
			}
			
			/* Si le stack du joueur en cours est a un alors la partie s'arrete */
			/* ---------------------------------------------------------------- */
			if (stackVide != true && partie.getLesJoueurs().get(partie.getJoueurCourant()).getMonStack().getNombre() == 1){
				System.out.println("stack vide");
				stackVide = true;
			}
			
			if ((stackVide == true) && (partie.getJoueurCourant() == mesCouleurs[mesCouleurs.length-1])){
				System.out.println("fin de partie");
				finPartie = true;
			}
			
			partie.getPlateau().afficher();
			
			/* Incrementation numero du joueur */
			/* ------------------------------- */
			partie.joueurSuivant();
		}		
		for (int i=0; i<nbJoueur; i++){
			System.out.println(partie.getLesJoueurs().get(mesCouleurs[i]).getCouleur() + " : " + partie.getScore(partie.getLesJoueurs().get(mesCouleurs[i]).getCouleur()));
		}
		System.out.println("Classement : "+ partie.getClassement());
		System.out.println("Le gagnant est : "+ partie.getGagnant());	
	}
}
