package Repello;

// TODO: Auto-generated Javadoc
// IUT de Nice / Departement informatique / Module APO Java
// Annee 2012_2013 - DUT/S2T
//
// Classe Fraction - Modelisation des point sur Z*Z
//
// Edition "Draft" : le point d'entree simule les tests unitaires
//
// + Version 0.0.0

// Auteur : S. Nicoletti


/**
 * La  Class Point.
 */
public class Point {
	
	/** Le m_nom. */
	char m_nom;
	
	/** Le m_x. */
	double m_x;
	
	/** Le m_y. */
	double m_y;
	
	/** Le epislon. */
	final double EPISLON = 1/10000; 
	
	
	//										Constructeur par defaut
	
	/**
	 * Constructeur de point.
	 */
	public Point ()
	{
		this.m_nom = '_';
		this.m_x=0.0d;
		this.m_y=0.0d;
		
	}
	
	/**
	 * Constructeur de point.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Point (double x, double y)
	{
		this.m_nom = '_';
		this.m_x=x;
		this.m_y=y;
		
	}
	
	/**
	 * Constructeur de point.
	 *
	 * @param nom the nom
	 * @param x the x
	 * @param y the y
	 */
	public Point (char nom,double x, double y)
	{
		this.m_nom = nom;
		this.m_x=x;
		this.m_y=y;
		
	}
	
	
	//										Accesseur de consultation
	
	
	/**
	 * Getter de  nom.
	 *
	 * @return le nom
	 */
	public char getNom ()
	{
		return this.m_nom;
	}
	
	/**
	 * X.
	 *
	 * @return the double
	 */
	public double x ()
	{
		return this.m_x;
	}
	
	/**
	 * Y.
	 *
	 * @return the double
	 */
	public double y ()
	{
		return this.m_y;
	}
	
	//										Accesseur de modification
	
	
	/**
	 * X.
	 *
	 * @param x the x
	 */
	public void x(double x){ this.m_x = x;}
	
	/**
	 * Y.
	 *
	 * @param y the y
	 */
	public void y(double y){ this.m_y = y;}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		
		Point p = (Point) obj;
		
		return (p.x() == m_x) && (p.y() == m_y) && (p.getNom() == m_nom);
	}
	
	
	//											Methode toString
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString ()
	{
		return this.m_nom + " (" + this.m_x + ", " + this.m_y + ")";
	}
	
	/**
	 * Confondus.
	 *
	 * @param op2 the op2
	 * @return true, si ok
	 */
	public boolean confondus (Point op2)
	{
		return ( (Math.abs(this.x() - op2.x()) <= EPISLON) &&  (Math.abs(this.y() - op2.y()) <= EPISLON) );
	}
	
	//											Methode projX
	/**
	 * Proj x.
	 *
	 * @return the point
	 */
	public Point projX ()
	{
		return new Point (this.x(),0);
	}
	//											Methode projY
	/**
	 * Proj y.
	 *
	 * @return the point
	 */
	public Point projY ()
	{
		return new Point (0,this.y());
	}
	//											Methode distance
	/**
	 * Distance.
	 *
	 * @param op2 the op2
	 * @return the double
	 */
	public double distance (final Point op2)
	{
		return Math.sqrt( Math.pow(op2.x() - this.x(), 2) + Math.pow(op2.y() - this.y(), 2) );
	}

}
