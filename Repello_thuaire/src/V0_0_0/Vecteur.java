package V0_0_0;
public class Vecteur {
	
	/* Declaration des attributs */
	/* ------------------------- */
	private Coordonnee coordonnee;
	
	/* Constructeurs */
	/* ------------- */
	public Vecteur (){
		this.coordonnee = new Coordonnee(1,0);
	}
	
	public Vecteur (int abscisse, int ordonnee){
		this.coordonnee = new Coordonnee (abscisse, ordonnee);
	}
	
	public Vecteur (Vecteur vecteur) throws Throwable{
		if (vecteur == null)
			throw new Throwable ("-2.1");
		this.coordonnee = new Coordonnee (vecteur.coordonnee);
	}
	
	public Vecteur (Coordonnee origine, Coordonnee destination) throws Throwable{
		if (origine == null || destination == null)
			throw new Throwable ("-2.2");
		int abscisse = destination.getAbscisse() - origine.getAbscisse();
		int ordonnee = origine.getOrdonnee() - destination.getOrdonnee();
		this.coordonnee = new Coordonnee (abscisse, ordonnee);
	}
	
	/* Methodes héritées de Object */
	/* --------------------------- */
	public String toString () {
		return coordonnee.toString();
	}
	
	public Object clone(){
		try {
			return new Vecteur (this);
		} catch (Throwable e) {}
		return null;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecteur other = (Vecteur) obj;
		if (coordonnee == null) {
			if (other.coordonnee != null)
				return false;
		} else if (!coordonnee.equals(other.coordonnee))
			return false;
		return true;
	}
	
	/* Accesseurs */
	/* ---------- */
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}
	
	public int getAbscisse () {
		return coordonnee.getAbscisse();
	}
	
	public int getOrdonnee () {
		return coordonnee.getOrdonnee();
	}
	
	/* Methode pour verifier si le vecteur est unitaire */
	/* ------------------------------------------------ */
	public boolean isVecteurUnitaire () {
		if ((getAbscisse() == 0 && getOrdonnee() == 1) || (getAbscisse() == 0 && getOrdonnee() == -1) ||
			(getAbscisse() == 1 && getOrdonnee() == 1) || (getAbscisse() == 1 && getOrdonnee() == -1) ||
			(getAbscisse() == 1 && getOrdonnee() == 0) || (getAbscisse() == -1 && getOrdonnee() == 0) ||
			(getAbscisse() == -1 && getOrdonnee() == -1) || (getAbscisse() == -1 && getOrdonnee() == 1))
			return true;
		return false;
	}
}
