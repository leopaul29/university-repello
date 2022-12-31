package V0_0_0;
import java.util.ArrayList;

public class Joueur {

	/* Declaration des attributs */
	/* ------------------------- */
	private String couleur;
	private Stack monStack;
	private ArrayList <Repeller> mesRepellers;
	
	/* Constructeurs */
	/* ------------- */
	public Joueur (){
		this.couleur = "blanc";
		this.monStack = null;
		mesRepellers = new ArrayList<Repeller> () ;
	}
	
	public Joueur (String couleur, Stack monStack) throws Throwable {
		if (monStack == null)
			throw new Throwable ("-2.1");
		this.couleur = couleur;
		this.monStack = monStack;
		mesRepellers = new ArrayList<Repeller> () ;
	}
	
	public Joueur (Joueur joueur) throws Throwable{
		if (joueur == null)
			throw new Throwable ("-2.1");
		this.couleur = joueur.getCouleur();
		this.monStack = new Stack (joueur.getMonStack());
		this.mesRepellers = new ArrayList <Repeller> (joueur.getMesRepellers());
	}

	/* Methode heritées de Object */
	/* -------------------------- */
	public String toString() {
		return couleur;
	}
	
	public Object clone (){
		try {
			return new Joueur (this);
		} catch (Throwable e) {}
		return couleur;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (couleur == null) {
			if (other.couleur != null)
				return false;
		} else if (!couleur.equals(other.couleur))
			return false;
		if (mesRepellers == null) {
			if (other.mesRepellers != null)
				return false;
		} else if (!mesRepellers.equals(other.mesRepellers))
			return false;
		if (monStack == null) {
			if (other.monStack != null)
				return false;
		} else if (!monStack.equals(other.monStack))
			return false;
		return true;
	}

	/* Accesseurs */
	/* ---------- */
	public String getCouleur() {
		return couleur;
	}

	public Stack getMonStack() {
		return monStack;
	}

	public ArrayList<Repeller> getMesRepellers() {
		return mesRepellers;
	}
	
	/* Methode pour ajouter des points */
	/* ------------------------------- */
	public void ajouterPoint (Repeller r) throws Throwable{
		if (r == null)
			throw new Throwable ("-2.1");
		mesRepellers.add(r);
	}
	
	public void retirerPoint (Repeller r) throws Throwable{
		if (r == null || !mesRepellers.contains(r))
			throw new Throwable ("-2.1");
		mesRepellers.remove(r);
	}
}
