package V0_0_0;
public class T_Repeller_N3 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Repeller", "0.0.0");
		
		Tests.Design("Controle des methodes heritées de Object", 3); {
			
			Tests.Case("Methode clone"); {
				Or or = new Or();
				Or or2 = (Or) or.clone();
				Argent argent = new Argent ();
				Argent argent2 = (Argent) argent.clone();
				Noir noir = new Noir ();
				Noir noir2 = (Noir) noir.clone();
				
				Tests.Unit("5", or2.toString());
				Tests.Unit("3", argent2.toString());
				Tests.Unit("1", noir2.toString());
			}
			
			Tests.Case("Methode equals"); {
				Or or = new Or();
				Or or2 = new Or();
				Argent argent = new Argent ();
				Argent argent2 = new Argent ();
				Noir noir = new Noir ();
				Noir noir2 = new Noir ();
				
				Tests.Unit(true, or.equals(or2));
				Tests.Unit(true, argent.equals(argent2));
				Tests.Unit(true, noir.equals(noir2));
				Tests.Unit(false, or.equals(argent2));
			}
		}
		Tests.End();
	}
}
