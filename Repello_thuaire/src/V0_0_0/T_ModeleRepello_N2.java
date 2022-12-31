package V0_0_0;
import java.util.HashMap;

public class T_ModeleRepello_N2 {
	
	private static String chemin = new String ("ConfigModeleRepello.config");
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("ModeleRepello", "0.0.0");
		
		Tests.Design("Controle de la methode calculerNbRepellerParStack ", 3); {
			
			Tests.Case("Calculer Nombre De Repeller Par Stack "); {
				/* Passer la methode calculerNbRepellerParStack en public pour tester */
				/* ------------------------------------------------------------------ */
				HashMap <String, Integer [][]> config = ConfigModeleRepello.lire(chemin);
				ModeleRepello modele = new ModeleRepello(config);
				Tests.Unit(0, modele.calculerNbRepellerParStack(1));
				Tests.Unit(15, modele.calculerNbRepellerParStack(2));
				Tests.Unit(12, modele.calculerNbRepellerParStack(3));
				Tests.Unit(10, modele.calculerNbRepellerParStack(4));
				Tests.Unit(0, modele.calculerNbRepellerParStack(5));
			}
		}
		Tests.End();
	}
}
