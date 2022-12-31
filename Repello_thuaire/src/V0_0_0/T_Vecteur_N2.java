package V0_0_0;
public class T_Vecteur_N2 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Vecteur", "0.0.0");
		
		Tests.Design("Controle des accesseurs", 3); {
			
			Tests.Case("Accesseurs de consultation"); {
				Vecteur vecteur = new Vecteur (1,2);
				
				Tests.Unit("(1,2)", vecteur.getCoordonnee().toString());
				Tests.Unit(1, vecteur.getAbscisse());
				Tests.Unit(2, vecteur.getOrdonnee());
			}
		}
		Tests.End();
	}
}
