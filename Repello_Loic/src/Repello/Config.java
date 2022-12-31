package Repello;
//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Gestion des fichiers de configuration
//
// Classe Config - Services de gestion des fichiers de configuration
//
// Edition A 		: enregistrement et chargement d'un fichier de configuration
//
//    + Version 1.0.0	: version initiale, avec controle de la classe d'origine
//                        d'une configuration (HashMap ou LinkedHashMap)
//    + Version 1.1.0	: avec trace de l'execution d'un service dans la console
/**
 *
 * La classe Config fournit deux services destin�s � simplifier et � 
 * uniformiser la gestion des fichiers de configuration.
 *
 * Les services fournis sont :
 *
 * load   : charger un dictionnaire de configuration depuis le repertoire cible,
 * store  : enregistrer un dictionnaire de configuration dans le repertoire cible.
 *
 * ATTENTION : dans le cas ou le repertoire cible de la sauvagarde n'est pas le 
 *             repertoire courant, ce r�pertoire devra avoir �t� cr�� au prealable.
 *
 * @author Alain Thuaire - Universite de Nice/IUT - Departement informatique
**/


import java.io.*;

abstract public class Config {
/**
 *
 * La methode store enregistre dans un fichier du repertoire courant le 
 * dictionnaire de configuration fourni en premier parametre. Le fichier 
 * resultant est cree avec l'extension .conf. Le nom du fichier est forme 
 * automatiquement de la facon suivante : p2-p3.conf, o� p2 et p3 designent 
 * les deux derniers parametres effectifs. 
 * 
**/

   // ------------------------------------------     *** Methode store
   //
   @SuppressWarnings("resource")
public static boolean store (Object config, 
                                String  name  ,
                                String  version) {                          	
   String origine;
   String nomFichier;      
   FileOutputStream f= null;
   ObjectOutputStream out= null;
   
      // Controler l'existence de la configuration
      //
      if (config == null) return false;
      
      // Controler la classe d'origine de la configuration
      //
      origine= config.getClass().getName();
      if (!origine.equals("java.util.HashMap") && 
          !origine.equals("java.util.LinkedHashMap")) return false;
      
      // Construire le nom du fichier de configuration
      //
      nomFichier= name + "-" + version + ".conf";
      
      // Construire un fichier logique et le fichier physique associe
      //
      try {f= new FileOutputStream(nomFichier);}
      catch (Exception e) {return false;}
      
      // Construire un flux de sortie base sur le fichier logique
      //
      try {out= new ObjectOutputStream(f);}
      catch (Exception e) {return false;}
      
      // Serialiser la configuration dans le flux de sortie
      //
      try{out.writeObject(config);}
      catch (Exception e) {return false;}
      
      System.out.println("Enregistrement du fichier " + nomFichier + " : OK");
      return true;
   }
   
/**
 *
 * La methode load charge le contenu d'un fichier de configuration depuis le 
 * repertoire courant. Le dictionnaire resultant est la valeur de retour. Le 
 * fichier origine possede obligatoirement l'extension .conf. Le nom du fichier 
 * est forme automatiquement de la facon suivante : p1-p2.conf, ou p1 et p2 
 * designent les deux parametres effectifs. 
 * 
**/

   // ------------------------------------------     *** Methode load
   //
   @SuppressWarnings("resource")
public static Object load (String  name   ,    
                              String  version) {
   String origine;
   String nomFichier;      
   FileInputStream f= null;
   ObjectInputStream in= null;
   Object resultat;
   
      // Construire le nom du fichier source de la configuration
      //
      nomFichier= name + "-" + version + ".conf";
      
      // Construire un fichier logique correspondant
      //
      try {f= new FileInputStream(nomFichier);}
      catch (Exception e) {return null;}
      
      // Construire un flux d'entree base sur le fichier logique
      //
      try {in= new ObjectInputStream(f);}
      catch (Exception e) {return null;}
      
      // Acquerir et deserialiser le flux d'entree
      //
      try{resultat=in.readObject();}
      catch (Exception e) {return null;}
      
      // Controler la classe d'origine du resultat
      //
      origine= resultat.getClass().getName();
      if (!origine.equals("java.util.HashMap") && 
          !origine.equals("java.util.LinkedHashMap")) return null;
          
      System.out.println("Chargement du fichier " + nomFichier + " : OK");
      return resultat;
   }
}
