package V0_0_0;
public class T_Vecteur_A1 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Vecteur", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {

			Tests.Case("Constructeur normal"); {
				Coordonnee coordonnee1 = null;
				Coordonnee coordonnee2 = new Coordonnee (1,2);
				try {
					Vecteur vecteur = new Vecteur (coordonnee1, coordonnee2);
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.2", e.toString());
				}
			}
		}
		Tests.End();
	}
}
