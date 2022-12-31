package V0_0_0;
public class T_Repeller_N2 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Repeller", "0.0.0");
		
		Tests.Design("Controle des accesseurs", 3); {
			
			Tests.Case("Accesseur de consultation"); {
				Or or = new Or();
				Argent argent = new Argent ();
				Noir noir = new Noir ();
				
				Tests.Unit(5, or.getNbPoint());
				Tests.Unit(3, argent.getNbPoint());
				Tests.Unit(1, noir.getNbPoint());
			}
		}
		Tests.End();
	}
}
