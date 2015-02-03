package graph;
import problem.State;

/**
 * Représente un noeud dans un graphe d'état, caractérisé par :
 * un état du problème. 
 */
public class StateNode
{	// état caractérisant le noeud
	private State state;

	/**
	 * Constructeur.
	 * @param state	état caractérisant le noeud
	 */
	public StateNode(State state)
	{	this.state = state;
	}

	/**
	 * Renvoie une représentation textuelle du noeud. 
	 * @return	la représentation textuelle du noeud
	 */
	public String getName()
	{	return state.getName();
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
	
	public boolean equals(Object object)
	{	boolean result;
		if(object == null)
			result = false;
		else if(!(object instanceof StateNode))
			result = false;
		else
		{	StateNode temp = (StateNode) object;
			result = temp.getState().equals(getState());
		}
		return result;
	}

}
