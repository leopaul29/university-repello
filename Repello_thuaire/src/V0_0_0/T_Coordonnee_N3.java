package V0_0_0;
public class T_Coordonnee_N3 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Coordonnee", "0.0.0");
		
		Tests.Design("Controle des methodes heritées de Object", 3); {
			
			Tests.Case("Methode clone"); {
				Coordonnee coordonnee = new Coordonnee (1,2);
				Coordonnee coordonnee2 = (Coordonnee) coordonnee.clone();
				
				Tests.Unit("(1,2)", coordonnee2.toString());
			}
			
			Tests.Case("Methode equals"); {
				Coordonnee coordonnee = new Coordonnee (1,2);
				Coordonnee coordonnee2 = new Coordonnee (1,2);
				Coordonnee coordonnee3 = new Coordonnee (2,2);
				
				Tests.Unit(true, coordonnee.equals(coordonnee2));
				Tests.Unit(false, coordonnee2.equals(coordonnee3));
			}
		}
		Tests.End();
	}
}
