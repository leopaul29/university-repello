package V0_0_0;
public class Or extends Repeller {
	
	/* Constructeurs */
	/* ------------- */
	public Or() {
		super("or",5);
	}
	
	/* Methode herit�es de Object */
	/* -------------------------- */
	public Object clone (){
		return new Or();
	}
}