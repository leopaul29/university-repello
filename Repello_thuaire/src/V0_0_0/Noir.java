package V0_0_0;
public class Noir extends Repeller {

	/* Constructeurs */
	/* ------------- */
	public Noir() {
		super("noir",1);
	}
	
	/* Methode herit�es de Object */
	/* -------------------------- */
	public Object clone (){
		return new Noir();
	}

}