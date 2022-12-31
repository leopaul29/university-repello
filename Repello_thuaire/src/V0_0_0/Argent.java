package V0_0_0;
public class Argent extends Repeller {

	/* Constructeurs */
	/* ------------- */
	public Argent() {
		super("argent",3);
	}
	
	/* Methode heritées de Object */
	/* -------------------------- */
	public Object clone (){
		return new Argent();
	}
}