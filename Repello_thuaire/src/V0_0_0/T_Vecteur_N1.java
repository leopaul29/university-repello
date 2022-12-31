package V0_0_0;
public class T_Vecteur_N1 {
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("Vecteur", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {
			
			Tests.Case("Constructeur par defaut"); {
				Vecteur vecteur = new Vecteur ();
				
				Tests.Unit("(1,0)", vecteur.toString());
			}
			
			Tests.Case("Constructeur normal"); {
				Vecteur vecteur = new Vecteur (1,2);
				
				Tests.Unit("(1,2)", vecteur.toString());
			}

			Tests.Case("Constructeur normal"); {
				Coordonnee coordonnee1 = new Coordonnee (1,1);
				Coordonnee coordonnee2 = new Coordonnee (1,2);
				Vecteur vecteur = new Vecteur (coordonnee1, coordonnee2);
				
				Tests.Unit("(0,-1)", vecteur.toString());
			}
			
			Tests.Case("Constructeur de copie"); {
				Vecteur vecteur = new Vecteur (1,2);
				Vecteur vecteur2 = new Vecteur (vecteur);
				
				Tests.Unit("(1,2)", vecteur2.toString());
			}
		}
		Tests.End();
	}
}
