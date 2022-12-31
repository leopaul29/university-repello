package Repello;
//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe ConfigSablier - Description de la configuration fonctionnelle
//                        d'un sablier, avec choix de la vue 
//
// Auteur : A. Thuaire
//

import java.awt.*;
import java.util.*;

public class ConfigSablier {
	
   // Decrire les parametres de configuration
   //   	
   @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap configurer() {
   HashMap config= new HashMap();
      
      config.put("mode",         "sablier");
      config.put("butee",        "0 : 00 : 00");
      config.put("arrierePlanButee",  Color.red);
      
      config.put("nomModele", "sablier");
      config.put("classeVue", "VueSablier"); 
                                   
      return config;
   }
   
   @SuppressWarnings({ "rawtypes", "unused" })
public static void main(String[] args) {
   HashMap config= configurer();
   
      Config.store(config, "_Config/Sablier/ConfigSablier", "3.3.0");
      HashMap w= (HashMap)Config.load("_Config/Sablier/ConfigSablier", "3.3.0");
   }
}
