package V0_0_0;
public class T_Stack_N1 {
	
	public static void main (String[] args) throws Throwable {
		
		Tests.Begin("Stack", "0.0.0");
		
		Tests.Design("Controle de la construction", 3); {
			
			Tests.Case("Constructeur par defaut"); {
				Stack stack = new Stack ();
				
				Tests.Unit("1", stack.toString());
			}
			
			Tests.Case("Constructeur normal"); {
				Stack stack = new Stack (5);
				
				Tests.Unit("5", stack.toString());
			}
		}
		Tests.End();
	}
}
