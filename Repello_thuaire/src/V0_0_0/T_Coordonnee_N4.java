package V0_0_0;
public class T_Coordonnee_N4 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Coordonnee", "0.0.0");
		
		Tests.Design("Controle de isAdjacente", 3); {
			
			Tests.Case("Methode equals"); {
				Coordonnee coordonnee = new Coordonnee (1,2);
				Coordonnee coordonnee2 = new Coordonnee (2,2);
				Coordonnee coordonnee3 = new Coordonnee (2,4);
				
				Tests.Unit(true, coordonnee.isAdjacente(coordonnee2));
				Tests.Unit(false, coordonnee.isAdjacente(coordonnee3));
			}
		}
		Tests.End();
	}
}
