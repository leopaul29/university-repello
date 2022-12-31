package V0_0_0;
public class T_Coordonnee_N2 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Coordonnee", "0.0.0");
		
		Tests.Design("Controle des accesseurs", 3); {
			
			Tests.Case("Accesseurs de consultation"); {
				Coordonnee coordonnee = new Coordonnee (1,2);
				
				Tests.Unit(1, coordonnee.getAbscisse());
				Tests.Unit(2, coordonnee.getOrdonnee());
			}
		}
		Tests.End();
	}
}
