package V0_0_0;
public class Cellule {

	/* Declaration des attributs */
	/* ------------------------- */
	private Coordonnee coordonnee;
	private int valeurMouvement;
	private Piece occupeeParPiece;
	private boolean zoneDepart;
	
	/* Constructeurs */
	/* ------------- */
	public Cellule () {
		this.coordonnee = new Coordonnee (0,0);
		this.valeurMouvement = 0;
		this.occupeeParPiece = null;
		this.zoneDepart = false;
	}
	
	public Cellule (int abscisse, int ordonnee, int valeurMouvement, boolean zoneDepart) throws Throwable {
		if (valeurMouvement < 1)
			throw new Throwable ("-2.3");
		this.coordonnee = new Coordonnee(abscisse,ordonnee);
		this.valeurMouvement = valeurMouvement;
		this.occupeeParPiece = null;
		this.zoneDepart = zoneDepart;
	}
	
	/* Methodes heritées de Object */
	/* --------------------------- */
	public String toString (){
		return coordonnee.toString() + ", " + valeurMouvement;
	}
	
	public Object clone (){
		Cellule clone = null;
		try {
			clone = new Cellule (coordonnee.getAbscisse(), coordonnee.getOrdonnee(), valeurMouvement, zoneDepart);
			clone.occupeeParPiece = occupeeParPiece;
		} catch (Throwable e) {}
		return clone;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cellule other = (Cellule) obj;
		if (coordonnee == null) {
			if (other.coordonnee != null)
				return false;
		} else if (!coordonnee.equals(other.coordonnee))
			return false;
		if (occupeeParPiece == null) {
			if (other.occupeeParPiece != null)
				return false;
		} else if (!occupeeParPiece.equals(other.occupeeParPiece))
			return false;
		if (valeurMouvement != other.valeurMouvement)
			return false;
		if (zoneDepart != other.zoneDepart)
			return false;
		return true;
	}
	
	/* Accesseurs */
	/* ---------- */
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public int getValeurMouvement() {
		return valeurMouvement;
	}
	
	public Piece getOccupeeParPiece() {
		return occupeeParPiece;
	}

	public void setOccupeeParPiece(Piece occupeeParPiece) {
		this.occupeeParPiece = occupeeParPiece;
	}
	
	public int getAbscisse () {
		return coordonnee.getAbscisse();
	}
	
	public int getOrdonnee () {
		return coordonnee.getOrdonnee();
	}
	
	/* Methode pour savoir si la cellule est occupée */
	/* --------------------------------------------- */
	public boolean isOccupee() {
		return getOccupeeParPiece() != null;
	}
	
	public boolean isOccupeeParStack() {
		if (occupeeParPiece != null)
			if (occupeeParPiece instanceof Stack)
				return true;
		return false;
	}

	public boolean isOccupeeParRepeller() {
		if (occupeeParPiece != null)
			if (occupeeParPiece instanceof Repeller)
				return true;
		return false;
	}
	
	/* Methode pour savoir si la case fait partie de la zone de départ */
	/* --------------------------------------------------------------- */
	public boolean isZoneDepart() {
		return zoneDepart;
	}
	
	/* Methode pour savoir si deux cases sont adjacentes */
	/* ------------------------------------------------- */
	public boolean isAdjacente(Cellule c) {
		if (getCoordonnee().isAdjacente(c.getCoordonnee()))
			return true;
		else
			return false;
	}
	
	/* Methode pour vider une case */
	/* --------------------------- */
	public void vider () {
		setOccupeeParPiece(null);
	}
	
	/* Methode pour avoir la couleur de la piece */
	/* ----------------------------------------- */
	public String couleurPieceSurCase () {
		if (isOccupeeParStack()){
			Stack stack = (Stack) occupeeParPiece;
			return stack.getCouleur();
		}
		if (isOccupeeParRepeller()){
			if (occupeeParPiece instanceof Noir)
				return "noir";
			if (occupeeParPiece instanceof Argent)
				return "argent";
			if (occupeeParPiece instanceof Or)
				return "or";
		}
		return null;
	}
}
