package V0_0_0;
public class T_Cellule_N2 {
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("Cellule", "0.0.0");
		
		Tests.Design("Controle des accesseurs", 3); {
			
			Tests.Case("Accesseur de consultation"); {
				Cellule cellule = new Cellule (1,2,5,true);
				
				Tests.Unit(1, cellule.getAbscisse());
				Tests.Unit(2, cellule.getOrdonnee());
				Tests.Unit("(1,2)", cellule.getCoordonnee().toString());
				Tests.Unit(null, cellule.getOccupeeParPiece());
				Tests.Unit(5, cellule.getValeurMouvement());
			}
		}
		Tests.End();
	}
}
