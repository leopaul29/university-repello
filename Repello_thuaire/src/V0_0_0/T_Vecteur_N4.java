package V0_0_0;
public class T_Vecteur_N4 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Vecteur", "0.0.0");
		
		Tests.Design("Controle de la methode isVecteurUnitaire", 3); {
			
			Tests.Case("Methode isVecteurUnitaire"); {
				Vecteur vecteur = new Vecteur (0,1);
				Vecteur vecteur2 = new Vecteur (1,1);
				Vecteur vecteur3 = new Vecteur (1,0);
				Vecteur vecteur4 = new Vecteur (1,-1);
				Vecteur vecteur5 = new Vecteur (0,-1);
				Vecteur vecteur6 = new Vecteur (-1,-1);
				Vecteur vecteur7 = new Vecteur (-1,0);
				Vecteur vecteur8 = new Vecteur (-1,1);
				Vecteur vecteur9 = new Vecteur (2,2);
				
				Tests.Unit(true, vecteur.isVecteurUnitaire());
				Tests.Unit(true, vecteur2.isVecteurUnitaire());
				Tests.Unit(true, vecteur3.isVecteurUnitaire());
				Tests.Unit(true, vecteur4.isVecteurUnitaire());
				Tests.Unit(true, vecteur5.isVecteurUnitaire());
				Tests.Unit(true, vecteur6.isVecteurUnitaire());
				Tests.Unit(true, vecteur7.isVecteurUnitaire());
				Tests.Unit(true, vecteur8.isVecteurUnitaire());
				Tests.Unit(false, vecteur9.isVecteurUnitaire());
			}
		}
		Tests.End();
	}
}
