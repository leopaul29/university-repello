
/**
 * 
 * @author Nicoletti Sébastien
 * @version V1.0.0
 * 		Méthode  -chargementFichierTexte
 * 				 -sauvegardeFichierTexte
 * 
 *@version V2.0.0
 *		Ajout de la méthode - chargementFichierImage
 *
 * Cette objet permet de gérer les flux de fichiers.
 * 
 */
package Repello;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class Fichier {
	
	/**
	 * Permet de charger un fichier txt
	 * 
	 * @param String nomFichier
	 * @return String
	 */
	public static String chargementFichierTexte (String nomFichier){
		
		//On teste la validite du fichier
		//
		if(nomFichier == null || nomFichier.length() == 0)
			return null;
		
		//On cree un fichir virtuel
		//
		File fichier = new File(nomFichier);
		
		//on teste la validité du fichier
		//
		if(!fichier.isFile()) return null;
		
		//Lecture du fichier
		//
		String contenuFichier = new String();
		BufferedReader lecture = null;
		
		try {
			//on ouvre un flux de lecture
			//
			lecture = new BufferedReader(new FileReader(nomFichier));
			String s = new String();
		
			while((s=lecture.readLine())!=null)
			{
				if(s!=null);
					contenuFichier += s+"\r\n";//on recupére le contenu
			}
		} catch (IOException e) {
			System.out.println("Erreur sur la lecture du fichier: "+ nomFichier);
			return null;
		}
		finally{
			
			// on ferme le flux
			//
			try {
				lecture.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return contenuFichier;
	}
	
	/**
	 * Cette méthode permet de sauvegarder un fichier txt.
	 * 
	 * @param String nomFichier
	 * @param Strinf contenu
	 * @return boolean
	 */
	public static boolean sauvegardeFichierTexte (String nomFichier, String contenu ){
		
		//On teste la validité des paramétres
		//
		if(nomFichier == null || nomFichier.length() == 0)
			return false;
		
		//On crée un fichier virtuel
		//
		File fichier = new File(nomFichier);
		
		//On teste le type du fichier
		//
		if(!fichier.isFile()) return false;
		
		//On gére le cas ou le fichier n'existe pas
		//
		if(!fichier.exists()){
			try {
				//On crée un nouveau fichier
				//
				fichier.createNewFile();
			} catch (IOException e) {
				System.out.printf("Impossible de creer le fichier: " + fichier.getName());
				return false;
			}
		}
		

		PrintWriter ecriture = null;
		
		try {
			//on ouvre un flux d'écriture
			//
			ecriture = new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
			ecriture.println(contenu);
			
		} catch (IOException e) {
			System.out.println("Erreur lors de l'ecriture dans le fichier: "+ nomFichier);
			return false;
		}
		finally{
			// on ferme le flux
			//
			ecriture.close();
		}
		
		return true;
	}
	
	
	/**
	 * Permet de charger en memoire une image
	 * 
	 * @param String chemin
	 * @return BufferedImage
	 */
	public static BufferedImage chargementFichierImage(String chemin){
		
		File fichier = new File(chemin);
		
		if(!fichier.isFile()) return null;
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(fichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}

}
