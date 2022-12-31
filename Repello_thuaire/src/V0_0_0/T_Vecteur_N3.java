package V0_0_0;
public class T_Vecteur_N3 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Vecteur", "0.0.0");
		
		Tests.Design("Controle des methodes heritées de Object", 3); {
			
			Tests.Case("Methode clone"); {
				Vecteur vecteur = new Vecteur (1,2);
				Vecteur vecteur2 = (Vecteur) vecteur.clone();
				
				Tests.Unit("(1,2)", vecteur2.toString());
			}
			
			Tests.Case("Methode equals"); {
				Vecteur vecteur = new Vecteur (1,2);
				Vecteur vecteur2 = new Vecteur (1,2);
				Vecteur vecteur3 = new Vecteur (2,2);
				
				Tests.Unit(true, vecteur.equals(vecteur2));  
				Tests.Unit(false, vecteur.equals(vecteur3));
			}
		}
		Tests.End();
	}
}
