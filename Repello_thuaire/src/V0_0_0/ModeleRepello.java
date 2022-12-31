package V0_0_0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class ModeleRepello extends Observable{
	
	/* Declaration des attributs */
	/* ------------------------- */
	private Integer [][] valeursGrille;
	private String joueurCourant;
	private ArrayList <String> couleursJoueurs;
	private HashMap <String,Joueur> lesJoueurs;
	private Grille plateau;
	
	/* Constructeurs */
	/* ------------- */
	public ModeleRepello (HashMap <String, Integer [][]> config) {
		valeursGrille = config.get("valeursGrille");
		joueurCourant = null;
		couleursJoueurs = new ArrayList<String> ();
		lesJoueurs = new HashMap <String,Joueur> ();
		try {
			plateau = new Grille (13,13);
		} catch (Throwable e) {}
	}

	/* Methode pour commencer la partie */
	/* -------------------------------- */
	@SuppressWarnings("unchecked")
	public void commencer (ArrayList <String> couleurs) throws Throwable{
		int nbJoueur = couleurs.size();
		if (nbJoueur < 2 || nbJoueur > 4)
			throw new Throwable ("-2.1");
		couleursJoueurs = (ArrayList<String>) couleurs.clone();
		joueurCourant = couleursJoueurs.get(0);
		initPlateauRepello();
		initPartie(nbJoueur);
	}
	
	/* Methode d'initialisation du plateau */
	/* ----------------------------------- */
	private void initPlateauRepello() throws Throwable {
		int x;
		int y;
		/* Initialisation de la valeur de deplacement pour chaque case */
		/* ----------------------------------------------------------- */
		for(y=0;y<plateau.getHauteur();y++){
			for(x=0;x<plateau.getLongueur();x++) {
				/* Delimitation de la zone de depart */
				/* --------------------------------- */
				if ((y==3 && x>2 && x<10) || (y==9 && x>2 && x<10) || (x==3 && y>3 && y<9) || (x==9 && y>3 && y<9))
					plateau.getPlateau()[y][x] = new Cellule(x,y,valeursGrille[y][x],true);
				else
					plateau.getPlateau()[y][x] = new Cellule(x,y,valeursGrille[y][x],false);
			}
		}
		/* Placement des Repellers de base */
		/* ------------------------------- */
		plateau.getCase(7, 7).setOccupeeParPiece(new Or());
		plateau.getCase(2, 7).setOccupeeParPiece(new Argent());
		plateau.getCase(7, 2).setOccupeeParPiece(new Argent());
		plateau.getCase(7, 12).setOccupeeParPiece(new Argent());
		plateau.getCase(12, 7).setOccupeeParPiece(new Argent());
		plateau.getCase(2, 2).setOccupeeParPiece(new Noir());
		plateau.getCase(2, 12).setOccupeeParPiece(new Noir());
		plateau.getCase(12, 2).setOccupeeParPiece(new Noir());
		plateau.getCase(12, 12).setOccupeeParPiece(new Noir());
	}
	
	/* Methode d'initialisation de la partie */
	/* ------------------------------------- */
	private void initPartie (int nbJoueur) throws Throwable{
		int nbRepeller = calculerNbRepellerParStack (nbJoueur);
		for (int i = 0; i < nbJoueur ; i++){
			Stack stack = new Stack (nbRepeller);
			/* Affectation du stack au joueur puis du joueur au stakc */
			/* ------------------------------------------------------ */
			lesJoueurs.put(couleursJoueurs.get(i), new Joueur(couleursJoueurs.get(i),stack));
			stack.setJoueur(lesJoueurs.get(couleursJoueurs.get(i)));
		}
	}
	
	/* Methode de placement des stacks dans la zone de départ
	/* ------------------------------------------------------ */
	public boolean placer (int ligne, int colonne, String couleur) throws Throwable{
		HashMap <String, Object> dictionnaireObservers = new HashMap <String, Object> ();
		Stack stack = null;
		/* Test pour savoir si la couleur passée existe */
		/* -------------------------------------------- */
		if (!lesJoueurs.containsKey(couleur))
			throw new Throwable ("-2.3");
		stack = lesJoueurs.get(couleur).getMonStack();
		Coordonnee coordonnee = new Coordonnee (colonne,ligne);
		if (plateau.isSurLePlateau(coordonnee)){
			if (!plateau.getCase(colonne, ligne).isOccupee()){
				if (plateau.getCase(colonne, ligne).isZoneDepart()){
					if (plateau.getPiecesVoisines(coordonnee).isEmpty()){
						plateau.getCase(colonne, ligne).setOccupeeParPiece(stack);
						setChanged();
						dictionnaireObservers.put("nom", "placer");
						dictionnaireObservers.put("ligne", ligne);
						dictionnaireObservers.put("colonne", colonne);
						dictionnaireObservers.put("couleur", couleur);
						notifyObservers(dictionnaireObservers);
						return true;
					}
				}
			}
		}
		return false;		
	}
	
	/* Methode pour deplacer un stack */
	/* ------------------------------ */
	public boolean deplacer (int ligneOrig, int colonneOrig, int ligneDest, int colonneDest){
		HashMap <String, Object> dictionnaireObservers = new HashMap <String, Object> ();
		Coordonnee position = new Coordonnee (colonneOrig,ligneOrig);
		Coordonnee arrivee = new Coordonnee (colonneDest, ligneDest);
		Vecteur direction = null;
		try {
			direction = new Vecteur (position, arrivee);
		} catch (Throwable e) {}
		int mouvement;
		ArrayList<Coordonnee> trajectoire = new ArrayList<Coordonnee> ();
		/* Test pour verifier que le vecteur est unitaire */
		/* ---------------------------------------------- */
		if (direction.isVecteurUnitaire()){
			/* Test pour verifier que les coordonnees sont bien sur le plateau */
			/* --------------------------------------------------------------- */
			if (plateau.isSurLePlateau(position) && plateau.isSurLePlateau(arrivee)) {
				/* Test pour savoir si la case d'origine est occupée par un stack */
				/* -------------------------------------------------------------- */
				try {
					if (plateau.getCase(colonneOrig, ligneOrig).isOccupeeParStack()){
						Stack stack = (Stack) plateau.getCase(colonneOrig, ligneOrig).getOccupeeParPiece();
						/* Test pour savoir si le stack appartient bien au joueur courant */
						/* -------------------------------------------------------------- */
						if (verifierStack(joueurCourant, colonneOrig, ligneOrig)){
							/* Test pour savoir si le stack contient assez de repelleur pour etre deplacer */
							/* --------------------------------------------------------------------------- */
							if (stack.getNombre() > 1){
								mouvement = plateau.getCase(colonneDest, ligneDest).getValeurMouvement();
								trajectoire = getTrajectoire(position, direction, mouvement);
								/* Test pour verifier que la trajectoire est empreintable */
								/* ------------------------------------------------------ */
								if (verifierTrajectoire(trajectoire)){
									stack.retirerUnRepelleur();
									plateau.getPlateau()[trajectoire.get(mouvement).getOrdonnee()-1][trajectoire.get(mouvement).getAbscisse()-1].setOccupeeParPiece(stack);
									plateau.getCase(colonneOrig, ligneOrig).setOccupeeParPiece(new Noir());
									setChanged();
									dictionnaireObservers.put("nom", "deplacer");
									dictionnaireObservers.put("ligneOrig", ligneOrig);
									dictionnaireObservers.put("colonneOrig", colonneOrig);
									dictionnaireObservers.put("ligneDest", ligneDest);
									dictionnaireObservers.put("colonneDest", colonneDest);
									notifyObservers(dictionnaireObservers);
									return true;
								}
							}
						}
					}
				} catch (Throwable e) {}
			}
		}
		return false;
	}	
	
	/* Methode d'expulsion */
	/* ------------------- */
	public String expulser(int ligneExpulseur, int colonneExpulseur, int ligneExpulse, int colonneExpulse){
		HashMap <String, Object> dictionnaireObservers1 = new HashMap <String, Object> ();
		HashMap <String, Object> dictionnaireObservers2 = new HashMap <String, Object> ();
		Coordonnee repousseur = new Coordonnee (colonneExpulseur, ligneExpulseur);
		Coordonnee repoussee = new Coordonnee (colonneExpulse, ligneExpulse);
		String couleurExpulseur = null;
		String couleurExpulse = null;
		try {
			couleurExpulse = plateau.getCase(colonneExpulse, ligneExpulse).couleurPieceSurCase();
		} catch (Throwable e2) {}
		try {
			couleurExpulseur = plateau.getCase(colonneExpulseur, ligneExpulseur).couleurPieceSurCase();
		} catch (Throwable e1) {}
		
		/* Test pour savoir si les deux positions sont identiques */
		/* ------------------------------------------------------ */
		if (!repousseur.equals(repoussee)){
			/* Test pour savoir si les positions sont sur le plateau */
			/* ----------------------------------------------------- */
			if (plateau.isSurLePlateau(repousseur) && plateau.isSurLePlateau(repoussee)){
				/* Test pour savoir si les positions sont adjacentes */
				/* -------------------------------------------------- */
				if (repousseur.isAdjacente(repoussee)){
					/* Test pour savoir si la case selectionner est occupée */
					/* ---------------------------------------------------- */
						try {
							if (plateau.getCase(repousseur.getAbscisse(),repousseur.getOrdonnee()).isOccupee()){
								/* Test pour savoir si la case ou l'on veut faire le contre est occupée */
								/* -------------------------------------------------------------------- */
								if (plateau.getCase(repoussee.getAbscisse(),repoussee.getOrdonnee()).isOccupee()){
									int decalageX = repoussee.getAbscisse() - repousseur.getAbscisse();
									int decalageY = repoussee.getOrdonnee() - repousseur.getOrdonnee();
									int newAbscisse = repoussee.getAbscisse() + decalageX;
									int newOrdonnee = repoussee.getOrdonnee() + decalageY;
									/* Si les nouvelles coordonnees de la pieces sont en dehors du plateau la piece sort */
									/* --------------------------------------------------------------------------------- */
									if (!plateau.isSurLePlateau(new Coordonnee(newAbscisse,newOrdonnee))) {
										/* Si la piece sortie est un stack */
										/* ------------------------------- */
										if (plateau.getCase(colonneExpulse, ligneExpulse).isOccupeeParStack()){
											Stack stack = (Stack) plateau.getCase(colonneExpulse, ligneExpulse).getOccupeeParPiece();
											/* Test pour savoir si le stack n'appartient pas au joueur courant */
											/* --------------------------------------------------------------- */
											if (verifierStack(joueurCourant, colonneExpulse, ligneExpulse))
												/* Lever un exception pour signaler qu'on ne peut pas sortir son stack */
												/* ------------------------------------------------------------------- */
												throw new Throwable ("-2.1");
											/* Si le stack est different du stack du joueur en cours */
											/* ----------------------------------------------------- */
											else{
												/* On notifie à l'observer que l'expulsion est possible */
												/* ---------------------------------------------------- */
												setChanged();
												dictionnaireObservers1.put("nom", "expulser");
												dictionnaireObservers1.put("ligne", ligneExpulse);
												dictionnaireObservers1.put("colonne", colonneExpulse);
												notifyObservers(dictionnaireObservers1);
												/* On notifie à l'observer que l'expulsion est validée */
												/* --------------------------------------------------- */
												setChanged();
												dictionnaireObservers2.put("nom", "expulser");
												dictionnaireObservers2.put("ligneExpulseur", ligneExpulseur);
												dictionnaireObservers2.put("colonneExpulseur", colonneExpulseur);
												dictionnaireObservers2.put("ligneExpulse", ligneExpulse);
												dictionnaireObservers2.put("colonneExpulse", colonneExpulse);
												dictionnaireObservers2.put("expulse", stack.getJoueur().getCouleur());
												dictionnaireObservers2.put("expulseur", couleurExpulseur);
												notifyObservers(dictionnaireObservers2);
												plateau.getCase((repoussee.getAbscisse()),(repoussee.getOrdonnee())).vider();
												return stack.getJoueur().getCouleur();
											}
										}
										else
											if (plateau.getCase(colonneExpulse, ligneExpulse).isOccupeeParRepeller()){
												/* On notifie à l'observer que l'expulsion est possible */
												/* ---------------------------------------------------- */
												setChanged();
												dictionnaireObservers1.put("nom", "expulser");
												dictionnaireObservers1.put("ligne", ligneExpulse);
												dictionnaireObservers1.put("colonne", colonneExpulse);
												notifyObservers(dictionnaireObservers1);
												/* Si la piece repousse est un repeller noir */
												/* ----------------------------------------- */
												if (plateau.getCase(colonneExpulse, ligneExpulse).getOccupeeParPiece() instanceof Noir){
													plateau.getCase(colonneExpulse, ligneExpulse).vider();
													ajouterPointsJoueur(joueurCourant, new Noir());
													/* On notifie à l'observer que l'expulsion est validée */
													/* --------------------------------------------------- */
													setChanged();
													dictionnaireObservers2.put("nom", "expulser");
													dictionnaireObservers2.put("ligneExpulseur", ligneExpulseur);
													dictionnaireObservers2.put("colonneExpulseur", colonneExpulseur);
													dictionnaireObservers2.put("ligneExpulse", ligneExpulse);
													dictionnaireObservers2.put("colonneExpulse", colonneExpulse);
													dictionnaireObservers2.put("expulse", "noir");
													dictionnaireObservers2.put("expulseur", couleurExpulseur);
													notifyObservers(dictionnaireObservers2);
													return "noir";
												}
												/* Si la piece repousse est un repeller argent */
												/* ------------------------------------------- */
												if (plateau.getCase(colonneExpulse, ligneExpulse).getOccupeeParPiece() instanceof Argent){
													plateau.getCase(colonneExpulse, ligneExpulse).vider();
													ajouterPointsJoueur(joueurCourant, new Argent());
													/* On notifie à l'observer que l'expulsion est validée */
													/* --------------------------------------------------- */
													setChanged();
													dictionnaireObservers2.put("nom", "expulser");
													dictionnaireObservers2.put("ligneExpulseur", ligneExpulseur);
													dictionnaireObservers2.put("colonneExpulseur", colonneExpulseur);
													dictionnaireObservers2.put("ligneExpulse", ligneExpulse);
													dictionnaireObservers2.put("colonneExpulse", colonneExpulse);
													dictionnaireObservers2.put("expulse", "argent");
													dictionnaireObservers2.put("expulseur", couleurExpulseur);
													notifyObservers(dictionnaireObservers2);
													return "argent";
												}
												/* Si la piece repousse est un repeller or */
												/* --------------------------------------- */
												if (plateau.getCase(colonneExpulse, ligneExpulse).getOccupeeParPiece() instanceof Or){
													plateau.getCase(colonneExpulse, ligneExpulse).vider();
													ajouterPointsJoueur(joueurCourant, new Or());
													/* On notifie à l'observer que l'expulsion est validée */
													/* --------------------------------------------------- */
													setChanged();
													dictionnaireObservers2.put("nom", "expulser");
													dictionnaireObservers2.put("ligneExpulseur", ligneExpulseur);
													dictionnaireObservers2.put("colonneExpulseur", colonneExpulseur);
													dictionnaireObservers2.put("ligneExpulse", ligneExpulse);
													dictionnaireObservers2.put("colonneExpulse", colonneExpulse);
													dictionnaireObservers2.put("expulse", "or");
													dictionnaireObservers2.put("expulseur", couleurExpulseur);
													notifyObservers(dictionnaireObservers2);
													return "or";
												}
											}
									}
									else{
										/* Test pour savoir si la case d'arrivee est occupée */
										/* ------------------------------------------------- */
										if (!plateau.getCase(newAbscisse, newOrdonnee).isOccupee()){
											/* Deplacement de la piece a repousser */
											/* ----------------------------------- */
											plateau.getCase((newAbscisse),(newOrdonnee)).setOccupeeParPiece(plateau.getCase(repoussee.getAbscisse(),repoussee.getOrdonnee()).getOccupeeParPiece());
											plateau.getCase((repoussee.getAbscisse()),(repoussee.getOrdonnee())).vider();
											/* On notifie que l'expulsion est possible */
											/* --------------------------------------- */
											setChanged();
											dictionnaireObservers1.put("nom", "expulser");
											dictionnaireObservers1.put("couleurExpulseur", couleurExpulseur);
											dictionnaireObservers1.put("couleurExpulse", couleurExpulse);
											dictionnaireObservers1.put("couleurExpulse", colonneExpulse);
											dictionnaireObservers1.put("ligneExpulseur", ligneExpulseur);
											dictionnaireObservers1.put("colonneExpulseur", colonneExpulseur);
											dictionnaireObservers1.put("ligneExpulse", ligneExpulse);
											dictionnaireObservers1.put("colonneExpulse", colonneExpulse);
											notifyObservers(dictionnaireObservers1);
											return null;
										}
									}
								}
							}
						} 
						catch (Throwable e) {}
					} 
				}
			}
		return null;
		}
	
	/* Methode verifiant si aucune piece n'a de voisin */
	/* ----------------------------------------------- */
	public boolean verifierPlateau (){
		for (int i=1; i<=plateau.getHauteur(); i++){
			for (int j=1; j<=plateau.getLongueur(); j++){
				Coordonnee coordonnee = new Coordonnee (j,i);
				try {
					ArrayList <Coordonnee> test = plateau.getPiecesVoisines(coordonnee);
					if (plateau.getCase(j, i).isOccupee() && test.size()!=1){
						return false;
					}
					else
						continue;
				} catch (Throwable e) {}
			}
		}
		return true;
	}

	/* Methode pour voler un repeller à un autre joueur */
	/* ------------------------------------------------ */
	public boolean voler(final String couleurDuVoleur,final String couleurDuVole,final String couleurRepeller) throws Throwable {
		HashMap <String, Object> dictionnaireObservers1 = new HashMap <String, Object> ();
		HashMap <String, Object> dictionnaireObservers2 = new HashMap <String, Object> ();
		HashMap <String, Object> dictionnaireObservers3 = new HashMap <String, Object> ();
		Joueur voleur = null;
		Joueur vole = null;

		if (!lesJoueurs.containsKey(couleurDuVoleur) || !lesJoueurs.containsKey(couleurDuVole))
			throw new Throwable ("-2.2");
		/* Iterateur pour recuperer le voleur et le vole */
		/* --------------------------------------------- */
		voleur = lesJoueurs.get(couleurDuVoleur);
		vole = lesJoueurs.get(couleurDuVole);
		
		/* Retrait d'un repeller du stack du sortant */
		/* ----------------------------------------- */
		if (vole.getMonStack().getNombre() > 0){
			vole.getMonStack().retirerUnRepelleur();
			setChanged ();
			dictionnaireObservers1.put("score", getScore(vole.getCouleur()));
			dictionnaireObservers1.put("nbRepellers", vole.getMonStack().getNombre());
			dictionnaireObservers1.put("couleur", vole.getCouleur());
			notifyObservers (dictionnaireObservers1);
		}
		
		/* Ajour du repeller a la liste du voleur */
		/* -------------------------------------- */
		ajouterPointsJoueur(couleurDuVoleur, new Noir());
		setChanged ();
		dictionnaireObservers2.put("score", getScore(voleur.getCouleur()));
		dictionnaireObservers2.put("nbRepellers", voleur.getMonStack().getNombre());
		dictionnaireObservers2.put("couleur", voleur.getCouleur());
		notifyObservers (dictionnaireObservers2);
		
		/* Si la liste des repellers de l'adversaire est vide sortir */
		/* --------------------------------------------------------- */
		if (vole.getMesRepellers().size()==0)
			return true;
		
		/* Iterateur pour transferer le repeller d'un joueur à l'autre */
		/* ----------------------------------------------------------- */
		for (Repeller repeller : vole.getMesRepellers()){
			if (repeller.getCouleur().equals(couleurRepeller)){
				ajouterPointsJoueur(couleurDuVoleur, repeller);
				retirerPointsJoueur(couleurDuVole, repeller);
				setChanged ();
				dictionnaireObservers3.put("nom", "voler");
				dictionnaireObservers3.put("couleurVoleur", couleurDuVoleur);
				dictionnaireObservers3.put("couleurVole", couleurDuVole);
				dictionnaireObservers3.put("scoreVoleur", getScore(couleurDuVoleur));
				dictionnaireObservers3.put("scoreVole", getScore(couleurDuVole));
				dictionnaireObservers3.put("nbRepellersVoleur", voleur.getMonStack().getNombre());
				dictionnaireObservers3.put("nbRepellersVole", vole.getMonStack().getNombre());
				notifyObservers (dictionnaireObservers3);
				return true;
			}
		}
		throw new Throwable ("-2.3");
	}

	/* Methode permettant d'obtenir le gagnant de la partie */
	/* ---------------------------------------------------- */
	public String getGagnant() {
		String gagnant = new String ();
		int pointsDesJoueurs [] = new int [lesJoueurs.size()];
		/* Calcul des point de chaque joueur */
		/* --------------------------------- */
		for (int i=0; i<pointsDesJoueurs.length; i++){
			try {
				String couleur = lesJoueurs.get(couleursJoueurs.get(i)).getCouleur();
				pointsDesJoueurs[i]=getScore(couleur);
			} catch (Throwable e) {}
		}
		int max = pointsDesJoueurs [0];
		gagnant = lesJoueurs.get(couleursJoueurs.get(0)).getCouleur();
		/* Trouver le gagnant */
		/* ------------------ */
		for (int i=1; i<pointsDesJoueurs.length; i++){
			if (pointsDesJoueurs[i]>max){
				gagnant = "";
				gagnant = lesJoueurs.get(couleursJoueurs.get(i)).getCouleur();
				max = pointsDesJoueurs[i];
				continue;
			}
			/* Gestion de l'egalité */
			/* -------------------- */
			if (pointsDesJoueurs[i]==max){
				gagnant += "," + lesJoueurs.get(couleursJoueurs.get(i)).getCouleur();
			}
		}
		return gagnant;
	}
	
	/* Methode pour obtenir le classement */
	/* ---------------------------------- */
	public ArrayList <String> getClassement () {
		int index = 0;
		HashMap <String, ArrayList <String>> dictionnaireObserver = new HashMap <String, ArrayList <String>> ();
		ArrayList <String> classement = new ArrayList <String> ();
		classement.add(lesJoueurs.get(couleursJoueurs.get(0)).getCouleur());
		for (int i=1; i<lesJoueurs.size();i++){
			for (int j=0; j<classement.size(); j++){
				try {
					if (getScore(couleursJoueurs.get(i)) > getScore(classement.get(j))){
						classement.add(index, couleursJoueurs.get(i));
						break;
					}
					else 
						index ++;
				} catch (Throwable e) {}
			}
			if (index == classement.size())
				classement.add(couleursJoueurs.get(i));
			index = 0;
		}
		setChanged();
		dictionnaireObserver.put("classement", classement);
		return classement;
	}

	/* Methode permettant d'obtenir le score d'un joueur */
	/* ------------------------------------------------- */
	public int getScore(final String couleurJoueur) throws Throwable {
		Joueur joueur = null;
		int score = 0;
		/* Verification de la couleur */
		/* -------------------------- */
		if (!lesJoueurs.containsKey(couleurJoueur))
			throw new Throwable ("-2.1");
		joueur = lesJoueurs.get(couleurJoueur);
		
		for (Repeller r : joueur.getMesRepellers())
			score += r.getNbPoint();
		return score;
	}

	/* Methode pour obtenir le nombre de repeller dans un stack */
	/* -------------------------------------------------------- */
	public int getTailleStack(final String couleurJoueur) throws Throwable {
		Joueur joueur = null;
		/* Si la couleur n'existe pas */
		/* -------------------------- */
		if (!lesJoueurs.containsKey(couleurJoueur))
			throw new Throwable ("-2.1");
		
		/* Recherche du joueur correspondant a la couleur */
		/* ---------------------------------------------- */
		joueur = lesJoueurs.get(couleurJoueur);
		
		return joueur.getMonStack().getNombre();
	}
	
	/* Methode pour ajouter des points au joueur */
	/* ----------------------------------------- */
	private void ajouterPointsJoueur (String couleurJoueur, Repeller repeller) throws Throwable{
		HashMap <String,Object> dictionnaireObservers = new HashMap <String,Object> ();
		if (!lesJoueurs.containsKey(couleurJoueur))
			throw new Throwable ("-2.1");
		try {
			lesJoueurs.get(couleurJoueur).ajouterPoint(repeller);
		} catch (Throwable e) {}
		setChanged();
		dictionnaireObservers.put("score", getScore(couleurJoueur));
		dictionnaireObservers.put("nbRepellers", getTailleStack(couleurJoueur));
		dictionnaireObservers.put("couleur", couleurJoueur);
		notifyObservers (dictionnaireObservers);
	}
	
	private void retirerPointsJoueur (String couleurJoueur, Repeller repeller) throws Throwable{
		HashMap <String,Object> dictionnaireObservers = new HashMap <String,Object> ();
		if (!lesJoueurs.containsKey(couleurJoueur))
			throw new Throwable ("-2.1");
		try {
			lesJoueurs.get(couleurJoueur).retirerPoint(repeller);
		} catch (Throwable e) {}
		setChanged();
		dictionnaireObservers.put("score", getScore(couleurJoueur));
		dictionnaireObservers.put("nbRepellers", getTailleStack(couleurJoueur));
		dictionnaireObservers.put("couleur", couleurJoueur);
		notifyObservers (dictionnaireObservers);
	}
	
	/* Methode de calcul de la trajectoire */
	/* ----------------------------------- */
	private ArrayList<Coordonnee> getTrajectoire (final Coordonnee position, final Vecteur direction, int mouvement){
		ArrayList<Coordonnee> trajectoire = new ArrayList<Coordonnee> ();
		Vecteur newDirection = null;
		try {
			newDirection = new Vecteur(direction);
		} catch (Throwable e) {}
		int abscisse=0, ordonnee=0 ;
		trajectoire.add(position);
		for (int i=1; i<= mouvement; i++){
			/* Si le rebond se fait sur un des cotés de facon perpendiculaire */
			/* -------------------------------------------------------------- */
			try {
				if ((plateau.bordureOuest(trajectoire.get(i-1)) && (newDirection.getAbscisse()==-1 && newDirection.getOrdonnee()==0)) || 
					(plateau.bordureSud(trajectoire.get(i-1)) && (newDirection.getAbscisse()==0 && newDirection.getOrdonnee()==-1)) || 
					(plateau.bordureEst(trajectoire.get(i-1)) && (newDirection.getAbscisse()==1 && newDirection.getOrdonnee()==0)) || 
					(plateau.bordureNord(trajectoire.get(i-1)) && (newDirection.getAbscisse()==0 && newDirection.getOrdonnee()==1)))
				{
					newDirection.setCoordonnee(new Coordonnee(-newDirection.getAbscisse(),-newDirection.getOrdonnee()));
					abscisse = trajectoire.get(i-1).getAbscisse()+newDirection.getAbscisse();
					ordonnee = trajectoire.get(i-1).getOrdonnee()-newDirection.getOrdonnee();
					trajectoire.add(new Coordonnee (abscisse,ordonnee));
				}
				else{
					/* Si le rebond se fait dans un angle */
					/* ---------------------------------- */
					if (((plateau.coinNordOuest(trajectoire.get(i-1))) && (newDirection.getAbscisse()==-1 && newDirection.getOrdonnee()==1)) || 
						((plateau.coinNordEst(trajectoire.get(i-1))) && (newDirection.getAbscisse()==1 && newDirection.getOrdonnee()==1)) || 
						((plateau.coinSudEst(trajectoire.get(i-1))) && (newDirection.getAbscisse()==1 && newDirection.getOrdonnee()==-1)) || 
						((plateau.coinSudOuest(trajectoire.get(i-1))) && (newDirection.getAbscisse()==-1 && newDirection.getOrdonnee()==-1)))
					{
						newDirection.setCoordonnee(new Coordonnee(-newDirection.getAbscisse(),-newDirection.getOrdonnee()));
						abscisse = trajectoire.get(i-1).getAbscisse()+newDirection.getAbscisse();
						ordonnee = trajectoire.get(i-1).getOrdonnee()-newDirection.getOrdonnee();
						trajectoire.add(new Coordonnee (abscisse,ordonnee));
					}
					else{
						/* Si le rebond se fait en haut ou en bas avec un angle */
						/* ---------------------------------------------------- */
						if ((plateau.bordureNord(trajectoire.get(i-1)) && ((newDirection.getAbscisse()==1 && newDirection.getOrdonnee()==1) || (newDirection.getAbscisse()==-1 && newDirection.getOrdonnee()==1))) || 
							(plateau.bordureSud(trajectoire.get(i-1)) && ((newDirection.getAbscisse()==-1 && newDirection.getOrdonnee()==-1) || (newDirection.getAbscisse()==1 && newDirection.getOrdonnee()==-1))))
						{
							newDirection.setCoordonnee(new Coordonnee(newDirection.getAbscisse(),-newDirection.getOrdonnee()));
							abscisse = trajectoire.get(i-1).getAbscisse()+newDirection.getAbscisse();
							ordonnee = trajectoire.get(i-1).getOrdonnee()-newDirection.getOrdonnee();
							trajectoire.add(new Coordonnee (abscisse,ordonnee));
						}
						else{
							/* Si le rebond se fait à gauche ou à droite avec un angle */
							/* ------------------------------------------------------- */
							if ((plateau.bordureOuest(trajectoire.get(i-1)) && ((newDirection.getAbscisse()==-1 && newDirection.getOrdonnee()==1) || (newDirection.getAbscisse()==-1 && newDirection.getOrdonnee()==-1))) || 
								(plateau.bordureEst(trajectoire.get(i-1)) && ((newDirection.getAbscisse()==1 && newDirection.getOrdonnee()==1) || (newDirection.getAbscisse()==1 && newDirection.getOrdonnee()==-1))))
							{
								newDirection.setCoordonnee(new Coordonnee(-newDirection.getAbscisse(),newDirection.getOrdonnee()));
								abscisse = trajectoire.get(i-1).getAbscisse()+newDirection.getAbscisse();
								ordonnee = trajectoire.get(i-1).getOrdonnee()-newDirection.getOrdonnee();
								trajectoire.add(new Coordonnee (abscisse,ordonnee));
							}
							else {
								abscisse = trajectoire.get(i-1).getAbscisse()+newDirection.getAbscisse();
								ordonnee = trajectoire.get(i-1).getOrdonnee()-newDirection.getOrdonnee();
								trajectoire.add(new Coordonnee (abscisse,ordonnee));
							}
						}
					}
				}
			} 
			catch (Throwable e) {}
		}
		return trajectoire;
	}
	
	/* Methode pour vérifier que la trajectoire est bien empruntable */
	/* ------------------------------------------------------------- */
	private boolean verifierTrajectoire (final ArrayList<Coordonnee> trajectoire){
		if (trajectoire.get(0).equals(trajectoire.get(trajectoire.size()-1)))
				return false;
		for (Coordonnee c : trajectoire){	
			try {
				if (plateau.getCase(c.getAbscisse(),c.getOrdonnee()).isOccupee() == true){
					if ((c.getOrdonnee()==trajectoire.get(0).getOrdonnee() && c.getAbscisse()==trajectoire.get(0).getAbscisse()))
						continue;
					else
						return false;
				}
				else
					continue;
			} catch (Throwable e) {}
		}
		return true;
	}
	
	/* Methode de verification d'appartenance entre le stack et le joueur */
	/* ------------------------------------------------------------------ */
	private boolean verifierStack (final String couleurJoueur, int x, int y){
		try {
			if (plateau.getCase(x,y).isOccupeeParStack()){
				Stack stack = (Stack) plateau.getCase(x,y).getOccupeeParPiece();
				if (couleurJoueur.equals(stack.getJoueur().getCouleur()))
					return true;
			}
		} catch (Throwable e) {}
		return false;
	}

	/* Methode pour savoir le nombre de repeller dans le stack */
	/* ------------------------------------------------------- */
	//TODO verifier que la methode a pas ete modifier pour des tests
	private int calculerNbRepellerParStack  (int nbJoueur){
		if (nbJoueur == 2)
			return 15;
		if (nbJoueur == 3)
			return 12;
		if (nbJoueur == 4)
			return 10;
		else
			return 0;
	}
	
	/* Methode changer joueur courant */
	/* ------------------------------ */
	public void joueurSuivant () {
		HashMap <String,String> dictionnaireObservers = new HashMap <String,String> ();
		String ancienJoueur = joueurCourant;
		if (joueurCourant.equals(couleursJoueurs.get(couleursJoueurs.size()-1)))
			joueurCourant = couleursJoueurs.get(0);
		else{
			int index = couleursJoueurs.indexOf(joueurCourant);
			joueurCourant = couleursJoueurs.get(index+1);
		}
		setChanged();
		dictionnaireObservers.put("nouvelle", joueurCourant);
		dictionnaireObservers.put("ancienne", ancienJoueur);
		notifyObservers(dictionnaireObservers);
	}

	/* Accesseurs */
	/* ---------- */
	public Grille getPlateau() {
		return plateau;
	}
	
	public HashMap<String, Joueur> getLesJoueurs() {
		return lesJoueurs;
	}

	public String getJoueurCourant () {
		return joueurCourant;
	}
	
	public Integer[][] getValeursGrille() {
		return valeursGrille;
	}
	
	/* Methodes héritées de Object */
	/* --------------------------- */
	public String toString (){
		return "" + lesJoueurs.size();
	}
}
