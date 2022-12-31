package V0_0_0;
public class T_Cellule_N1 {
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("Cellule", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {
			
			Tests.Case("Constructeur par defaut"); {
				Cellule cellule = new Cellule ();
				
				Tests.Unit("(0,0), 0", cellule.toString());
			}
			
			Tests.Case("Constructeur normal"); {
				Cellule cellule = new Cellule (1,1,5,true);
				
				Tests.Unit("(1,1), 5", cellule.toString());
			}
		}
		Tests.End();
	}
}
