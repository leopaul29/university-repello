//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Composants generiques
//
// Classe ConfigVueSablier_A - Description de la configuration de la vue 
//                             analogique d'un sablier 
//
// Auteur : M. Pavone
//

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class ConfigVueSablier_A {
	
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
      
      config.put("imageSablier", "./Config/sablier.png");
      config.put("couleurDebut", Color.GREEN);
      config.put("couleurFin", Color.RED);
      config.put("arrierePlanButee",  Color.red);
                                   
      return config;
   }
   
   public static void main(String[] args) {
   HashMap config= configurer();
   
      Config.store(config, "../ConfigVueSablier_A", "1.X.0");
      HashMap w= (HashMap)Config.load("../ConfigVueSablier_A", "1.X.0");
   }
}
