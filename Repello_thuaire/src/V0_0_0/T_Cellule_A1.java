package V0_0_0;
public class T_Cellule_A1 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Cellule", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {
			
			Tests.Case("Constructeur normal"); {
				try {
					Cellule cellule = new Cellule (1,1,-1,true);
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.3", e.toString());
				}
			}
		}
		Tests.End();
	}
}
