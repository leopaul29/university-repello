package V0_0_0;
public abstract class Repeller extends Piece{

	/* Declaration des attributs */
	/* ------------------------- */
	private String couleur;
	private int nbPoint;
	
	/* Constructeurs */
	/* ------------- */
	protected Repeller (String couleur, int nbPoint) {
		this.couleur = couleur;
		this.nbPoint = nbPoint;
	}
	
	/* Methode heritées de Object */
	/* -------------------------- */
	public String toString (){
		return "" + nbPoint;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repeller other = (Repeller) obj;
		if (nbPoint != other.nbPoint)
			return false;
		return true;
	}

	/* Accesseurs */
	/* ---------- */
	public int getNbPoint() {
		return nbPoint;
	}
	
	public String getCouleur () {
		return couleur;
	}
}
