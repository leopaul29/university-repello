//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe ConfigVueSablier_D - Description de la configuration de la vue 
//                             digitale d'un sablier 
//
// Auteur : A. Thuaire
//

package Modele;

import java.awt.*;
import java.util.*;

public class ConfigVueSablier_D {
	
   // Decrire les parametres de configuration
   //   	
   public static HashMap<String, Object> configurer() {
   HashMap<String, Object> config= new HashMap<String, Object>();
      
      config.put("arrierePlan",  Color.pink);
      config.put("avantPlan",    Color.black);
      config.put("police",       new Font("DS-digital", 
                                 Font.TYPE1_FONT, 60));
      config.put("separateur",   " : ");
      config.put("labelInitial", "XX : XX : XX");
      config.put("placement",    new GridLayout(1,0));
      
      config.put("arrierePlanButee",  Color.red);
                                   
      return config;
   }
   
   public static void main(String[] args) {
   HashMap<?, ?> config= configurer();
   
      Config.store(config, "Config/ConfigVueSablier_D", "1.X.0");
      Config.load("Config/ConfigVueSablier_D", "1.X.0");
   }
}
