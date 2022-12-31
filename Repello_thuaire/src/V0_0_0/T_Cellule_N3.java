package V0_0_0;
public class T_Cellule_N3 {
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("Cellule", "0.0.0");
		
		Tests.Design("Controle des methodes heritées de Object", 3); {
			
			Tests.Case("Methode clone"); {
				Cellule cellule = new Cellule (1,2,5,true);
				Cellule cellule2 = (Cellule) cellule.clone();
				
				Tests.Unit("(1,2), 5", cellule2.toString());
			}
			
			Tests.Case("Methode equals"); {
				Cellule cellule = new Cellule (1,2,5,true);
				Cellule cellule2 = new Cellule (1,2,5,true);
				Cellule cellule3 = new Cellule (1,3,6,true);
				
				Tests.Unit(true, cellule.equals(cellule2));
				Tests.Unit(false, cellule.equals(cellule3));
			}
		}
		Tests.End();
	}
}
