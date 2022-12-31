package V0_0_0;
public class T_Cellule_N5 {
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("Cellule", "0.0.0");
		
		Tests.Design("Controle des methodes isZoneDepart et isAdjacente", 3); {
			
			Tests.Case("Methode isAdjacente"); {
				Cellule cellule = new Cellule (1,2,5,true);
				Cellule cellule2 = new Cellule (2,2,5,true);
				Cellule cellule3 = new Cellule (5,5,5,false);
				
				Tests.Unit(true, cellule.isAdjacente(cellule2));
				Tests.Unit(false, cellule.isAdjacente(cellule3));
			}
			
			Tests.Case("Methode isZoneDepart"); {
				Cellule cellule = new Cellule (1,2,5,true);
				Cellule cellule3 = new Cellule (5,5,5,false);
				
				Tests.Unit(true, cellule.isZoneDepart());
				Tests.Unit(false, cellule3.isZoneDepart());
			}
		}
		Tests.End();
	}
}
