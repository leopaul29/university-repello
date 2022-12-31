package V0_0_0;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ConfigModeleRepello {

	public static void main(String[] args) {
		String chemin = new String ("ConfigModeleRepello.config");
		Integer [][] valeursGrille = {{3,4,5,6,1,2,3,4,5,6,1,2,3},
									  {5,6,1,2,3,4,5,6,1,2,3,4,5},
									  {1,2,3,4,5,6,3,2,3,4,5,6,1},
									  {4,5,6,1,2,3,4,5,6,1,2,3,4},
									  {6,1,2,3,4,5,6,1,2,3,4,5,6},
									  {2,3,4,5,6,1,2,3,4,5,6,1,2},
									  {4,5,6,1,2,3,4,5,6,1,2,3,4},
									  {6,1,2,3,4,5,6,1,2,3,4,5,6},
									  {2,3,4,5,6,1,2,3,4,5,6,1,2},
									  {5,6,5,2,3,4,5,6,1,2,3,4,5},
									  {1,2,3,4,5,6,3,2,3,4,5,6,1},
									  {3,4,5,6,1,2,1,4,5,6,1,2,3},
									  {5,6,1,2,3,4,5,6,1,2,3,4,5},
									};
		HashMap <String, Integer[][]> config = new HashMap <String, Integer[][]> ();
		config.put("valeursGrille", valeursGrille);
		ecrire (chemin, config);
	}
	
	public static void ecrire (String chemin, HashMap <String, Integer[][]> config){
		try{
			File fichier = new File(chemin);
			FileOutputStream f = new FileOutputStream(fichier);
			ObjectOutputStream s = new ObjectOutputStream (f);
			s.writeObject(config);
			s.close();
		}
		catch(Exception e){
			e.printStackTrace();
		};
	}
	
	public static HashMap <String, Integer[][]> lire (String chemin) {
		HashMap <String, Integer[][]> config = null;
		try{
			File fichier = new File(chemin);
			FileInputStream f = new FileInputStream(fichier);
			ObjectInputStream s = new ObjectInputStream (f);
			config = (HashMap <String, Integer[][]>) s.readObject();
			s.close();
		}
		catch(Exception e){};
		return config;
	}

}
