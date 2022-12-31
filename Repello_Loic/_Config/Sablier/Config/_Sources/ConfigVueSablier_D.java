//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe ConfigVueSablier_D - Description de la configuration de la vue 
//                             digitale d'un sablier 
//
// Auteur : A. Thuaire
//

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class ConfigVueSablier_D {
	
   // Decrire les parametres de configuration
   //   	
   public static HashMap configurer() {
   HashMap config= new HashMap();
      
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
   HashMap config= configurer();
   
      Config.store(config, "../ConfigVueSablier_D", "1.X.0");
      HashMap w= (HashMap)Config.load("../ConfigVueSablier_D", "1.X.0");
   }
}
