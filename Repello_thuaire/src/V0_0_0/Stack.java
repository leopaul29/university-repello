package V0_0_0;
public class Stack extends Piece {
	
	/* Declaration des attributs */
	/* ------------------------- */
	private int nombre;
	private Joueur joueur;

	/* Constructeurs */
	/* ------------- */
	public Stack () {
		this.nombre = 1;
		this.joueur = null;
	}
	
	public Stack (int nombre) throws Throwable {
		if (nombre < 1)
			throw new Throwable ("-2.1");
		this.nombre = nombre;
		this.joueur = null;
	}
	
	public Stack (Stack stack) throws Throwable {
		if (stack == null)
			throw new Throwable ("-2.1");
		this.nombre = stack.getNombre();
		this.joueur = new Joueur (stack.getJoueur());
	}
	
	/* Methodes heritées de Object */
	/* --------------------------- */
	public String toString (){
		return "" + nombre;
	}
	
	public Object clone (){
		Stack clone = null;
		try {
			clone = new Stack (nombre);
			clone.setJoueur((Joueur)joueur.clone());
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
		Stack other = (Stack) obj;
		if (joueur == null) {
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
			return false;
		if (nombre != other.nombre)
			return false;
		return true;
	}

	/* Accesseurs */
	/* ---------- */
	public int getNombre() {
		return nombre;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setJoueur (Joueur joueur) {
		this.joueur = joueur;
	}
	
	public String getCouleur () {
		return joueur.getCouleur();
	}
	
	/* Methode de suppression d'un repelleur du stack */
	/* ---------------------------------------------- */
	public void retirerUnRepelleur () {
		nombre--;
	}
}
