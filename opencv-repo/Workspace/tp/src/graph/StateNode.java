package graph;
import problem.State;

/**
 * Repr�sente un noeud dans un graphe d'�tat, caract�ris� par :
 * un �tat du probl�me. 
 */
public class StateNode
{	// �tat caract�risant le noeud
	private State state;

	/**
	 * Constructeur.
	 * @param state	�tat caract�risant le noeud
	 */
	public StateNode(State state)
	{	this.state = state;
	}

	/**
	 * Renvoie une repr�sentation textuelle du noeud. 
	 * @return	la repr�sentation textuelle du noeud
	 */
	public String getName()
	{	return state.getName();
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
