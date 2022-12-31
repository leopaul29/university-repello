package Repello;
//
// IUT de Nice / Departement informatique / Module APO-Java
// Annee 2013_2014 - Application Repello
//
// Interface _ModeleRepello - Methodes d'appel du modele Repello  
//
// Edition A   : edition initiale
//
//    + Version 1.0.0   : version initiale
//         
// Auteur : A. Thuaire
//
// Les cellules sur la grille de jeu seront designees en coordonnees joueur
// (1<= ligne <=13 ; 1<= colonne <=13).
//.
// Les joueurs seront identifies par la couleur de leur stack : jaune, bleu,
// vert et rouge..
//
// Les couleurs des repellers seront les suivantes : noir, or et argent.
//

public interface _InterfaceModeleRepello {

// --- Placer un stack ou un repeller sur la grille

public boolean placer (int ligne, int colonne, String couleur);

// --- Deplacer un stack ou un repeller deja present sur la grille

public boolean deplacer  (int ligneOrig, int colonneOrig, 
                          int ligneDest, int colonneDest);
                          
// --- Expulser de la grille un stack ou un repeller

public boolean expulser  (int ligneExpulseur, int colonneExpulseur, 
                          int ligneExpulse,   int colonneExpulse);

// --- Voler un repeller a un adversaire

public boolean voler (String couleurDuVoleur, 
                      String couleurVole, 
                      String couleurRepeller);
                      
// --- Obtenir la designation (couleur) du gagnant
                      
public String getGagnant();

}