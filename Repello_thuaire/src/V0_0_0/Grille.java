package V0_0_0;
import java.util.ArrayList;

public class Grille {
	private int longueur;
	private int hauteur;
	private Cellule [][] plateau;
	
	/* Constructeurs */
	/* ------------- */
	public Grille () {
		this.longueur = 13;
		this.hauteur = 13;
		this.plateau = new Cellule[hauteur][longueur];
	}
	
	public Grille (int longueur, int hauteur) throws Throwable{
		if (longueur < 1 || hauteur < 1)
			throw new Throwable ("-2.2");
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.plateau = new Cellule[hauteur][longueur];
	}
	
	/* Methode bordureNord */
	/* ------------------- */
	public boolean bordureNord (Coordonnee coordonnee) throws Throwable {
		if (coordonnee == null) 
			throw new Throwable("-2.1");
		if (coordonnee.getOrdonnee() == 1) 
			return true;
		return false;
	}
	
	/* Methode bordureEst */
	/* ------------------ */
	public boolean bordureEst (Coordonnee coordonnee) throws Throwable {	
		if (coordonnee == null) 
			throw new Throwable("-2.1");
		
		if (coordonnee.getAbscisse() == longueur) 
			return true;
		return false;
	}
	
	/* Methode bordureSud */
	/* ------------------ */
	public boolean bordureSud (Coordonnee coordonnee) throws Throwable {
		
		if (coordonnee == null) 
			throw new Throwable("-2.1");
		
		if (coordonnee.getOrdonnee() == hauteur) 
			return true;
		return false;
	}
	
	/* Methode bordureOuest */
	/* -------------------- */
	public boolean bordureOuest (Coordonnee coordonnee) throws Throwable {
		
		if (coordonnee == null) 
			throw new Throwable("-2.1");
		
		if (coordonnee.getAbscisse() == 1) 
			return true;
		return false;
	}
	
	/* Methode bordure */
	/* --------------- */
	public boolean bordure (Coordonnee coordonnee) throws Throwable {
		
		if (bordureNord(coordonnee)) 
			return true;
		if (bordureEst(coordonnee)) 
			return true;
		if (bordureSud(coordonnee)) 
			return true;
		if (bordureOuest(coordonnee)) 
			return true;
		return false;
	}
	
	/* Methode coinNordOuest */
	/* --------------------- */
	public boolean coinNordOuest (Coordonnee coordonnee) throws Throwable {
		return bordureNord(coordonnee) && bordureOuest(coordonnee);
	}
	
	/* Methode coinNordEst */
	/* ------------------- */
	public boolean coinNordEst (Coordonnee coordonnee) throws Throwable {
		return bordureNord(coordonnee) && bordureEst(coordonnee);
	}
	
	/* Methode coinSudEst */
	/* ------------------ */
	public boolean coinSudEst (Coordonnee coordonnee) throws Throwable {
		return bordureSud(coordonnee) && bordureEst(coordonnee);
	}
	
	/* Methode coinSudOuest */
	/* -------------------- */
	public boolean coinSudOuest (Coordonnee coordonnee) throws Throwable {
		return bordureSud(coordonnee) && bordureOuest(coordonnee);
	}
	
	/* Methode coin */
	/* ------------ */
	public boolean coin (Coordonnee coordonnee) throws Throwable {
		
		if (coinNordOuest(coordonnee)) 
			return true;
		if (coinNordEst(coordonnee)) 
			return true;
		if (coinSudEst(coordonnee)) 
			return true;
		if (coinSudOuest(coordonnee)) 
			return true;
		return false;
	}
	
	/* Methode pour obtenir le nombre de voisines */
	/* ------------------------------------------ */
	public int nbVoisine (Coordonnee coordonnee) {
		return getPiecesVoisines(coordonnee).size();
	}

	/* Methode pour determiner les cases occupées adjascentes */
	/* ------------------------------------------------------ */
	public ArrayList<Coordonnee> getPiecesVoisines(Coordonnee coordonnee) {
		int ouest = Math.max(0, coordonnee.getAbscisse()-2);
		int est = Math.min(coordonnee.getAbscisse() , longueur - 1);
		int nord = Math.max(0, coordonnee.getOrdonnee() - 2);
		int sud = Math.min(hauteur - 1, coordonnee.getOrdonnee());
		
		ArrayList<Coordonnee> lesVoisins = new ArrayList<Coordonnee>(); 
		for (int j = nord; j <= sud; j++) {
			for (int i = ouest; i <= est; i++) {
				if (plateau[j][i].getOccupeeParPiece() != null) {
					lesVoisins.add(new Coordonnee(i+1,j+1));
				}
			}
		}
		return lesVoisins;
	}
	
	/* Methode pour determiner les cases vides adjascentes */
	/* --------------------------------------------------- */
	public ArrayList<Coordonnee> getCasesVoisines(Coordonnee coordonnee) {
		int ouest = Math.max(0, coordonnee.getAbscisse()-2);
		int est = Math.min(coordonnee.getAbscisse() , longueur - 1);
		int nord = Math.max(0, coordonnee.getOrdonnee() - 2);
		int sud = Math.min(hauteur - 1, coordonnee.getOrdonnee());
		
		ArrayList<Coordonnee> lesVoisins = new ArrayList<Coordonnee>(); 
		for (int j = nord; j <= sud; j++) {
			for (int i = ouest; i <= est; i++) {
				if (plateau[j][i].getOccupeeParPiece() == null) {
					lesVoisins.add(new Coordonnee(i+1,j+1));
				}
			}
		}
		return lesVoisins;
	}
	
	/* Methode pour savoir si les coordonnees passées sont sur le plateau */
	/* ------------------------------------------------------------------ */
	public boolean isSurLePlateau (final Coordonnee coordonnee){
		return ((coordonnee.getAbscisse()>=1 && coordonnee.getAbscisse()<=longueur) && 
				(coordonnee.getOrdonnee()>=1 && coordonnee.getOrdonnee()<=hauteur));
	}
	
	/* Methode pour calculer la distance entre deux cellule */
	/* ---------------------------------------------------- */
	public int distance (Cellule c1, Cellule c2) throws Throwable {
		
		int deltaX, deltaY;
		
		/* Controler la validite des parametres */
		/* ------------------------------------ */
		if (c1 == null) throw new Throwable ("-2.1");
		if (c2 == null) throw new Throwable ("-2.2");
		
		/* Traiter le cas particulier de l'egalite de c1 et c2 */
		/* --------------------------------------------------- */
		if(c1.equals(c2))return 0;
		
		/* Calculer l'ecart d'abscisse et d'ordonnee entre les deux cellules */
		/* ----------------------------------------------------------------- */
		deltaX= Math.abs(c1.getOrdonnee()-c2.getOrdonnee());
		deltaY= Math.abs(c1.getAbscisse()-c2.getAbscisse());
		
		/* Traiter le cas de la direction Nord/Sud */
		/* --------------------------------------- */
		if(deltaX == 0)
		{
			return deltaY;
		}
		
		/* Traiter le cas de la direction Est/Ouest */
		/* ---------------------------------------- */
		if(deltaY == 0)
		{
			return deltaX;
		}
		
		/* Traiter le cas d'une diagonale */
		/* ------------------------------ */
		if(deltaX == deltaY)
		{
			return deltaX;
		}
		
		/* Traiter les autres cas ou la distance ne peut pas etre exprimee */
		/* par un nombre entier de cellules */
		/* --------------------------------------------------------------- */
		return -1;
	}
	
	/* Methode pour connaitre le nombre de cases voisine */
	/* ------------------------------------------------- */
	public int getNbVoisines(Cellule cible) throws Throwable {
		
		/* Controler la validite du parametre */
		/* ---------------------------------- */
		if (cible == null) throw new Throwable ("-2.1");
		
		cible.getOrdonnee();
		cible.getAbscisse();
		
		/* Traiter les cas particuliers des quatre coins */
		if(coin(cible.getCoordonnee()))
		{
			return 3;
		}
		
		/* Traiter les cas particuliers des quatre bordures */
		if(bordure(cible.getCoordonnee()))
		{
			return 5;
		}
		
		return 8;
	}
	
	/* Getters et Setters */
	/* ------------------ */
	public Cellule getCase (int x, int y) throws Throwable{
		if (x < 1 || x > longueur) 
			throw new Throwable ("-2.1");
		if (y < 1 || y > hauteur) 
			throw new Throwable ("-2.2");
		return plateau[y-1][x-1];
	}
	
	public Cellule getCaseGrille (int ligne, int colonne) throws Throwable{
		if (ligne < 1 || ligne > longueur) 
			throw new Throwable ("-2.1");
		if (colonne < 1 || colonne > hauteur) 
			throw new Throwable ("-2.2");
		return plateau[ligne-1][colonne-1];
	}

	public int getLongueur() {
		return longueur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public Cellule[][] getPlateau() {
		return plateau;
	}

	/* Methode pour afficher le plateau */
	/* -------------------------------- */
	public void afficher() throws Throwable {
		for (int i = 1; i <=getHauteur(); i++){
			for (int j = 1; j<=getLongueur(); j++){
				if (getCase(j, i).isOccupee() == false)
					System.out.print("|   ");
				else{
					if (getCase(j, i).isOccupeeParRepeller() == true){
						if (getCase(j, i).getOccupeeParPiece() instanceof Noir)
							System.out.print("| N ");
						if (getCase(j, i).getOccupeeParPiece() instanceof Or)
							System.out.print("| O ");
						if (getCase(j, i).getOccupeeParPiece() instanceof Argent)
							System.out.print("| A ");
					}
					else
						if (getCase(j, i).isOccupeeParStack() == true)
							System.out.print("| S ");
				}
			}
			System.out.println("|");
		}
		System.out.println("");
		System.out.println("");
	}
}
