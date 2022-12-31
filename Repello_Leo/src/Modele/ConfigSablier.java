//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe ConfigSablier - Description de la configuration fonctionnelle
//                        d'un sablier, avec choix de la vue 
//
// Auteur : A. Thuaire
//

package Modele;

import java.awt.*;
import java.io.Serializable;
import java.util.*;

public class ConfigSablier {
	
   // Decrire les parametres de configuration
   //   	
   public static HashMap<String, Object> configurer() {
   HashMap<String, Object> config= new HashMap<String, Object>();
      
      config.put("mode",         "sablier");
      config.put("butee",        "0 : 00 : 00");
      config.put("arrierePlanButee",  Color.red);
      
      config.put("nomModele", "sablier");
      config.put("classeVue", "VueSablier"); 
                                   
      return config;
   }
   
   public static void main(String[] args) {
   HashMap<?, ?> config= configurer();
   
      Config.store(config, "Config/ConfigSablier", "3.3.0");
      Config.load("Config/ConfigSablier", "3.3.0");
   }
}
