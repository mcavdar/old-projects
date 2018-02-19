package tree;

import problem.State;


/**
 * Représente un noeud dans un arbre de recherche, caractérisé par :
 * un état du problème, une profondeur, un coût, un état de visite 
 * et un numéro unique d'itération (qui indique à quelle itération
 * ce noeud a été visité par l'algorithme). 
 */
public class SearchNode
{	// état du problème associé au noeud
	private State state;
	// profondeur du noeud dans l'arbre
	private int depth;
	// coût du noeud (calculé depuis la racine)
	private double cost;
	// état de visite
	private boolean visited;
	// itération de visite (ou -1 si le noeud n'a pas encore été visité)
	private int iteration;

	/**
	 * Constructeur créant un noeud non visité, d'itération -1, caractérisé par
	 * les données passées en paramètres. 
	 * @param state	état du problème associé au noeud de recherche
	 * @param depth	profondeur du noeud
	 * @param cost	coût calculé depuis la racine
	 */
	public SearchNode(State state, int depth, double cost)
	{	this.state = state;
		this.depth = depth;
		this.cost = cost;
		this.iteration = -1;
		visited = false;
	}

	/**
	 * Renvoie une représentation textuelle du noeud. 
	 * @return	la représentation textuelle du noeud
	 */
	public String getName()
	{	String result;
		result = "<";
		result = result + state.getName() + ",";
		result = result + depth + ",";
		result = result + cost + " ";
		result = result + "(" + iteration + ")";
		result = result + ">";
		return result;
	}
	
	public String toString()
	{	return getName();		
	}
	
	/**
	 * Renvoie l'état associé au noeud de recherche.
	 * @return	l'état
	 */
	public State getState()
	{	return state;
	}

	/**
	 * Renvoie la profondeur du noeud de recherche dans l'arbre. 
	 * @return	la profondeur
	 */
	public int getDepth()
	{	return depth;
	}

	/**
	 * Renvoie le coût du noeud calculé depuis la racine. 
	 * @return	le coût
	 */
	public double getCost()
	{	return cost;
	}
	
	/**
	 * Marque le noeud comme ayant été visité à l'itération
	 * passée en paramètre.
	 * @param iteration	l'itération de visite  
	 */
	protected void markVisited(int iteration)
	{	visited = true;	
		this.iteration = iteration;
	}
	
	/**
	 * Renvoie l'état de visite du noeud
	 * @return	vrai si le noeud a été visité.
	 */
	public boolean isVisited()
	{	return visited;	
	}

	/**
	 * Renvoie l'itération à laquelle le noeud a été visité
	 * (ou -1 s'il n'a jamais été visité). 
	 * @return	l'itération de visite
	 */
	public int getIteration()
	{	return iteration;
	}
	
	public boolean equals(Object object)
	{	return object == this;		
	}
}
