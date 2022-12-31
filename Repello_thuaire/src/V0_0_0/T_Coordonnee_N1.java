package V0_0_0;
public class T_Coordonnee_N1 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Coordonnee", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {
			
			Tests.Case("Constructeur par defaut"); {
				Coordonnee coordonnee = new Coordonnee ();
				Tests.Unit("(0,0)", coordonnee.toString());
			}
			
			Tests.Case("Constructeur normal"); {
				Coordonnee coordonnee1 = new Coordonnee (1,2);
				Coordonnee coordonnee2 = new Coordonnee (-1,2);
				
				Tests.Unit("(1,2)", coordonnee1.toString());
				Tests.Unit("(-1,2)", coordonnee2.toString());
			}
			
			Tests.Case("Constructeur de copie"); {
				Coordonnee coordonnee1 = new Coordonnee (1,2);
				Coordonnee coordonnee2 = new Coordonnee (coordonnee1);
				
				Tests.Unit("(1,2)", coordonnee1.toString());
				Tests.Unit("(1,2)", coordonnee2.toString());
			}
		}
		Tests.End();
	}
}
