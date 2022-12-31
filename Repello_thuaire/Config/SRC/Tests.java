//
// Annee 2012_2013 - Module Java - Charte tests unitaires
//
// Utilitaires de tests - Classe Tests
//
// Version 2.0.0    : version initiale compl�te
// Version 2.0.1    : formattage des commentaires pour impression
// Version 2.1.0    : correction du bug verboseLevel (ligne 111)
//                    + correction du bug statusDesign (ligne 100)
//                    + la classe devient abstraite
// Version 2.2.0    : modification de la methode Unit pour gerer les
//                    references nulles
//                    + suppression de la methode main (module de tests
//                    unitaires externalise)
// Version 2.3.0    : introduction d'une extension de la methode Case
// Version 2.4.0    : prise en compte des types primitifs int et double
//                    par la methode Unit
// Version 2.5.0    : introduction d'une forme polymorphe de la
//                    methode Unit pour autoriser une marge d'erreur
// Version 2.6.0    : introduction d'une forme polymorphe de la
//                    methode Unit pour autoriser le controle global de
//                    deux tableaux pris dans leur ensemble
// Version 2.7.0    : introduction d'une forme polymorphe de la
//                    methode Unit pour autoriser le controle du type
//                    primitif boolean
// Version 2.8.0    : introduction d'un niveau 4 de visualisation
//                    (correction de l'IE_1 S2T - 2011/2012)
//
/**
 *
 * La classe Tests fournit un ensemble complet de services destin�s � 
 * simplifier et � uniformiser la mise en oeuvre de la charte de 
 * r�alisation des modules de tests unitaires expos�e en cours. La 
 * classe est �galement utilis�e en TD comme un exemple tr�s concret 
 * de codes sources "�crits par d'autres".
 *
 * Les services fournis sont :
 *
 * Begin    : d�but d'ex�cution des tests unitaires de la classe cible,
 * Design   : ex�cuter un "test design",
 * Case     : ex�cuter un "test case",
 * Unit     : ex�cuter un "test unit",
 * End      : fin d'ex�cution des tests unitaires de la classe cible.
 *
 * @author Alain Thuaire
 *
**/
abstract public class Tests {
private static int levelDesign;		// Numero d'ordre du test_design 
private static int levelCase;		// Numero d'ordre du test_case
private static int levelUnit;		// Numero d'ordre du test_unit
private static int verboseLevel;	// Niveau courant de visualisation
private static int statusDesign;	// CR du test_design courant
private static int statusCase;		// CR de test_case courant
private static boolean testBegin;	// Indicateur execution Begin
private static String  designName;	// Nom du test design courant;
private static String  caseName;	// Nom du test case courant;

/**
 *
 * La m�thode Begin visualise la ligne d'ent�te du module de tests 
 * unitaires.
 * 
**/
   public static void Begin (String name, String version) {
      visuEntete(name, version);	// Visualiser entete des tests
      testBegin=true;			// Service Begin execute
   }

/**
 *
 * La m�thode End visualise d'abord le compte rendu (CR) du dernier 
 * "test case" et/ou du dernier "test design" du module, en fonction 
 * du niveau de visualisation courant (attribut verboseLevel).
 *
 * Seuls les CR non nuls sont visualis�s.
 *
 * La m�thode End visualise ensuite la ligne de cl�ture du module de 
 * tests unitaires, si et seulement si ce dernier s'est termin� 
 * globalement avec succ�s. Dans la charte, un "test design" (y compris
 * le dernier) n'est ex�cut� que si le pr�c�dent se termine avec succ�s 
 * (CR=0).
 * 
**/
   public static void End () {
   String lastLine= "\n------------------------------------------------------------\n";
      
      // Visualiser le CR dernier test case et dernier test design
   	  //
      visuCR("Case"); visuCR("Design"); 
      
      // Visualiser la ligne de cloture des tests unitaires
   	  //
      if (statusDesign == 0) System.out.println(lastLine);
      else System.out.println();
      
      // Gerer le cas du dernier test design en anomalie
      //
      if (statusDesign != 0) System.exit(statusDesign);
   }

/**
 *
 * La m�thode Design visualise d'abord le compte rendu (CR) du dernier 
 * "test case" et/ou du dernier "test design" ex�cut�s, en fonction du 
 * niveau de visualisation courant (attribut verboseLevel).
 *
 * La m�thode renseigne ensuite les attributs du "test design" et le 
 * niveau requis de visualisation (second param�tre).
 *
 * La m�thode visualise pour terminer la ligne d'ent�te des "test 
 * design".
 * 
**/
   public static void Design (String name, int level) {
   	
      // Controler les conditions initiales requises par la charte
      //
      if (!valid("Begin")) System.exit(1);
      
      // Visualiser le CR du test case et du test design precedents
      //
      visuCR("Case"); visuCR("Design"); 
      
      // Controler que le status OK du test design precedent
      //
      if (statusDesign != 0) System.exit(1);

      // Initialiser les attributs du test design
      //
      levelCase   =0;
      statusCase  =0;
      statusDesign=0;
      designName  =name;
      levelDesign++;
      levelUnit=0;
      
      // Controler et affecter le nouveau niveau de visualisation
      //
      verboseLevel=level;
      if (level > 4) verboseLevel=4;
      if (level < 0) verboseLevel=0;
      
      // Visualiser la ligne d'entete du nouveau test design
      //   
      visuEntete(name);
   }
/**
 *
 * La m�thode Case visualise d'abord le compte rendu (CR) du dernier
 * "test case" ex�cut�, en fonction du niveau de visualisation courant 
 * (attribut verboseLevel).
 *
 * La m�thode renseigne ensuite les attributs du "test case".
 *
 * La m�thode visualise pour terminer la ligne d'ent�te des "test cases".
 * 
**/
   public static void Case (String name) {
      
      // Controler les conditions initiales requises par la charte
      //
      if (!valid("Begin") || !valid("Design"))  System.exit(1);

      // Visualiser le CR du dernier test case execute
      //  
      visuCR("Case");
      
      // Initialiser les attributs du nouveau test case
      // 
      caseName=name;
      levelCase++;
      
      // Visualiser la ligne d'entete du nouveau test case
      //  
      visuEntete();
   }
   
   public static void Case (String name, int level) {
      
      // Modifier le niveau verbose courant
      //  
      verboseLevel=level;
      if (level > 4) verboseLevel=4;
      if (level < 0) verboseLevel=0;
      
      // Appliquer la methode d'origine
      // 
      Case(name); 
   }
   
 
 /**
 *
 * La m�thode Unit visualise d'abord les deux messages standard d'un 
 * test elementaire, en fonction du niveau de visualisation courant 
 * (attribut verboseLevel).
 *
 * La m�thode elabore pour terminer les CR du "test case" et du "test
 * design" courant. 
 *
**/ 
   public static void Unit (Object attendu, Object obtenu) {

      // Controler les conditions initiales requises par la charte
      //
      if (!valid("Begin") || !valid("Design") || !valid("Case")) System.exit(1);	  
  
      // Visualiser les messages du test de ce niveau
      // 
      visuEntete(attendu, obtenu);

      // Elaborer le CR du test case courant
      //  
      if (statusCase != 0 ||(obtenu==null && attendu ==null)) return;
      
      if((obtenu==null && attendu !=null) ||
         (obtenu!=null && attendu ==null) || 
         !obtenu.equals(attendu))             statusCase=levelCase;
      
      // Elaborer le CR du test design courant
      //  
      if (statusDesign == 0 && statusCase != 0) statusDesign=statusCase;
      
      // Incrementer le total courant des tests unit
      //  
      levelUnit++;
   }
   
   public static void Unit (int attendu, int obtenu) {

      // Effectuer un "boxing" des deux parametres
      //
      Unit(new Integer(attendu), new Integer(obtenu));	  
   }
   
   public static void Unit (double attendu, double obtenu) {

      // Effectuer un "boxing" des deux parametres
      //
      Unit(new Double(attendu), new Double(obtenu));	
   }

   public static void Unit (double attendu, double obtenu, double epsilon) {

      // Controler les conditions initiales requises par la charte
      //
      if (!valid("Begin") || !valid("Design") || !valid("Case")) System.exit(1);	  
  
      // Visualiser les messages du test de ce niveau
      // 
      visuEntete(new Double(attendu), new Double(obtenu));

      // Elaborer le CR du test case courant
      //  
      if (statusCase != 0) return;
      if (Math.abs(attendu-obtenu) > Math.abs(epsilon)) statusCase=levelCase;

      // Elaborer le CR du test design courant
      //  
      if (statusDesign == 0 && statusCase != 0) statusDesign=statusCase;
      
      // Incrementer le total courant des tests unit
      //  
      levelUnit++;
   }
   
   public static void Unit (Object[] attendu, Object[] obtenu) {

      // Controler les conditions initiales requises par la charte
      //
      if (!valid("Begin") || !valid("Design") || !valid("Case")) System.exit(1);	  
  
      // Visualiser les messages du test de ce niveau
      // 
      visuEntete(attendu, obtenu);

      // Elaborer le CR du test case courant
      // 
      if (statusCase != 0 ||(obtenu==null && attendu ==null)) {
         levelUnit++;
         return;
      }
      
      if((obtenu==null && attendu !=null) ||
         (obtenu!=null && attendu ==null)    )  statusCase=levelCase;
         
      else {
      	      
      	 String classeAttendue, classeObtenue;
      	  
      	 // Traiter le cas de tableaux de tailles differentes
      	 //
      	 if (attendu.length != obtenu.length) statusCase=levelCase;
      	
      	 // Analyser tous les elements deux a deux
      	 //
         else for (int i=0;i <attendu.length; i++) {
         	
                 // Traiter le cas particulier de deux references nulles
                 //
                 if (attendu[i] == null && obtenu[i] == null) continue;
            
                 // Traiter le cas particulier d'une reference nulle seulement
                 //
        	 if ((attendu[i]== null && obtenu[i] != null)  ||
           	     (attendu[i] != null && obtenu[i] == null)) {
                    statusCase=levelCase;
           	    break;
           	 }
           	
           	 // Controler les deux classes d'origine
           	 //
           	 classeAttendue= attendu[i].getClass().getName();
           	 classeObtenue = obtenu[i].getClass().getName();
           	 if (!classeAttendue.equals(classeObtenue)) {
           	    statusCase=levelCase;
           	    break;
           	 }
           	
           	 // Controler les deux valeurs courante
           	 //
                if (!attendu[i].equals(obtenu[i])) {
           	    statusCase=levelCase;
           	    break;
           	 }
          }
      }
      
      // Elaborer le CR du test design courant
      //  
      if (statusDesign == 0 && statusCase != 0) statusDesign=statusCase;
      
      // Incrementer le total courant des tests unit
      //  
      levelUnit++;
   }

   public static void Unit (boolean attendu, boolean obtenu) {

      // Effectuer un "boxing" des deux parametres
      //
      Unit(new Boolean(attendu), new Boolean(obtenu));	  
   }
   
/**
 *
 * La m�thode priv�e valid v�rifie les conditions d'ex�cution du niveau 
 * de tests pass� en param�tre.
 * 
**/
   private static boolean valid (String level) {
   String headText   = "\n*** Erreur fatale (Tests) : ";
   String errorBegin = "Begin requis au prealable\n";
   String errorDesign= "Design requis au prealable\n";
   String errorCase  = "Case requis au prealable\n";
   
      // Controler l'execution prealable du service Begin
      //
      if (level.equals("Begin") && !testBegin) {
         System.out.println( headText + errorBegin);
         return false;
      }
      
      // Controler l'execution prealable du service Design
      //   
      if (level.equals("Design") && levelDesign == 0) {
         System.out.println(headText + errorDesign);
         return false;
      }
      
      // Controler l'execution prealable du service Case
      // 
      if (level.equals("Case") && levelCase == 0) {
         System.out.println(headText + errorCase);
         return false;
      }
      
      return true;  
   }

/**
 *
 * La m�thode priv�e visuEntete visualise la ligne d'entete du niveau
 * de description fix� par sa signature, en fonction du niveau de 
 * visualisation courant (attribut verboseLevel).
 * 
**/
   private static void visuEntete (String name, String version) {
   String beginPart  = "\n--- Tests unitaires de la classe ";
   String versionPart= " - Version ";
   String endPart    = " ---\n\n";
      
      System.out.print(beginPart);
      System.out.print(name + versionPart + version);
      System.out.print(endPart);
   }
  
   private static void visuEntete (String name) {
   String beginPart = "\n** [";
   String endPart   = "]\n";
   
      if (verboseLevel > 0) System.out.print(beginPart + name + endPart);
   }
   
   private static void visuEntete () {
   String beginPart = "\n   + (";
   String endPart   = ")\n";
   
      if (verboseLevel > 1) System.out.print(beginPart + caseName + endPart);
   }
   
   private static void visuEntete (Object attendu, Object obtenu) {
   String beginPart = "\n     Valeur attendue : ";
   String middlePart= "\n     Valeur obtenue  : ";
   String LF        = "\n";
   
      if (verboseLevel > 2) {
         System.out.print(beginPart + attendu);
         System.out.print(middlePart + obtenu + LF);
      }
   }
   
 /**
 *
 * La m�thode priv�e visuCR visualise le compte rendu courant du niveau
 * de tests fourni en param�tre.
 * 
**/
   private static void visuCR (String level) {
   String beginPartCase ="     => CR= ";
   String beginPartDesign = "=> CR= ";
   String LF   = "\n";
   String msgDesign_1= LF + "     Nombre total de \"tests case\" : ";
   String msgDesign_2= "     Nombre total de \"tests unit\" : ";
   
      // Visualiser le CR du dernier test case execute
      //
      if (level.equals("Case") && levelCase > 0 && verboseLevel > 1 && statusCase != 0) {
         if (verboseLevel == 2) System.out.print(beginPartCase + statusCase + LF);
         else System.out.print(LF + beginPartCase + statusCase + LF);
      }  	
      
      // Visualiser le CR du dernier test design execute
      //
      if (level.equals("Design")&&verboseLevel >0 &&statusDesign != 0) {
      	 if (verboseLevel == 1 && statusDesign != 0) System.out.print(beginPartDesign + statusDesign + LF);
         else System.out.print(LF + beginPartDesign + statusDesign + LF);
      }
      
      // Visualiser les informations de niveau 4
      //
      if (verboseLevel > 3 && levelDesign > 0 && 
          level.equals("Design")) {
         System.out.println(msgDesign_1 + levelCase);
         System.out.println(msgDesign_2 + levelUnit);
      }
   }
}
