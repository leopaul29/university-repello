package V0_0_0;
public class Coordonnee {

	/* Declaration des attributs */
	/* ------------------------- */
	private int abscisse;
	private int ordonnee;
	
	/* Constructeurs */
	/* ------------- */
	public Coordonnee (){
		this.abscisse = 0;
		this.ordonnee = 0;
	}
	
	public Coordonnee (int abscisse, int ordonnee){
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}
	
	public Coordonnee (Coordonnee coordonnee){
		this.abscisse = coordonnee.abscisse;
		this.ordonnee = coordonnee.ordonnee;
	}
	
	/* Methodes heritees de Object */
	/* --------------------------- */
	public String toString(){
		return "(" + abscisse + "," + ordonnee + ")";
	}
	
	public Object clone (){
		return new Coordonnee (this);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordonnee other = (Coordonnee) obj;
		if (abscisse != other.abscisse)
			return false;
		if (ordonnee != other.ordonnee)
			return false;
		return true;
	}
	
	/* Accesseurs */
	/* ---------- */
	public int getAbscisse() {
		return abscisse;
	}

	public int getOrdonnee() {
		return ordonnee;
	}
	
	/* Methode pour savoir si deux coordonnee sont adjascentes dans Z*/
	/* ------------------------------------------------------------- */
	public boolean isAdjacente (Coordonnee coordonnee){
		if ((Math.abs(coordonnee.abscisse - abscisse) == 1) && ((Math.abs(coordonnee.ordonnee - ordonnee) == 1) || (Math.abs(coordonnee.ordonnee - ordonnee) == 0)) ||
			((Math.abs(coordonnee.abscisse - abscisse) == 1) || (Math.abs(coordonnee.abscisse - abscisse) == 0)) && (Math.abs(coordonnee.ordonnee - ordonnee) == 1))
			return true;
		else
			return false;
	}
}
