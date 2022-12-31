package V0_0_0;
public class T_Stack_A1 {
	
	public static void main (String[] args) {
		
		Tests.Begin("Stack", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {
			
			Tests.Case("Constructeur normal"); {
				try {
					Stack stack = new Stack (-1);
				} catch (Throwable e) {
					Tests.Unit("java.lang.Throwable: -2.1", e.toString());
				}
			}
		}
		Tests.End();
	}
}
