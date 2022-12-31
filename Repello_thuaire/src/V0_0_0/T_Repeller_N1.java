package V0_0_0;
public class T_Repeller_N1 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Repeller", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {
			
			Tests.Case("Constructeur par defaut"); {
				Or or = new Or();
				Argent argent = new Argent ();
				Noir noir = new Noir ();
				
				Tests.Unit("5", or.toString());
				Tests.Unit("3", argent.toString());
				Tests.Unit("1", noir.toString());
			}
		}
		Tests.End();
	}
}
