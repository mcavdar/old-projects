package tree;

import problem.State;


/**
 * Repr�sente un noeud dans un arbre de recherche, caract�ris� par :
 * un �tat du probl�me, une profondeur, un co�t, un �tat de visite 
 * et un num�ro unique d'it�ration (qui indique � quelle it�ration
 * ce noeud a �t� visit� par l'algorithme). 
 */
public class SearchNode
{	// �tat du probl�me associ� au noeud
	private State state;
	// profondeur du noeud dans l'arbre
	private int depth;
	// co�t du noeud (calcul� depuis la racine)
	private double cost;
	// �tat de visite
	private boolean visited;
	// it�ration de visite (ou -1 si le noeud n'a pas encore �t� visit�)
	private int iteration;

	/**
	 * Constructeur cr�ant un noeud non visit�, d'it�ration -1, caract�ris� par
	 * les donn�es pass�es en param�tres. 
	 * @param state	�tat du probl�me associ� au noeud de recherche
	 * @param depth	profondeur du noeud
	 * @param cost	co�t calcul� depuis la racine
	 */
	public SearchNode(State state, int depth, double cost)
	{	this.state = state;
		this.depth = depth;
		this.cost = cost;
		this.iteration = -1;
		visited = false;
	}

	/**
	 * Renvoie une repr�sentation textuelle du noeud. 
	 * @return	la repr�sentation textuelle du noeud
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
	 * Renvoie l'�tat associ� au noeud de recherche.
	 * @return	l'�tat
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
	 * Renvoie le co�t du noeud calcul� depuis la racine. 
	 * @return	le co�t
	 */
	public double getCost()
	{	return cost;
	}
	
	/**
	 * Marque le noeud comme ayant �t� visit� � l'it�ration
	 * pass�e en param�tre.
	 * @param iteration	l'it�ration de visite  
	 */
	protected void markVisited(int iteration)
	{	visited = true;	
		this.iteration = iteration;
	}
	
	/**
	 * Renvoie l'�tat de visite du noeud
	 * @return	vrai si le noeud a �t� visit�.
	 */
	public boolean isVisited()
	{	return visited;	
	}

	/**
	 * Renvoie l'it�ration � laquelle le noeud a �t� visit�
	 * (ou -1 s'il n'a jamais �t� visit�). 
	 * @return	l'it�ration de visite
	 */
	public int getIteration()
	{	return iteration;
	}
	
	public boolean equals(Object object)
	{	return object == this;		
	}
}
