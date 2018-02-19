package graph;
import problem.Action;

/**
 * Repr�sente un lien orient� dans un graphe d'�tat.
 * Ce lien est d�fini par les deux noeuds reli�s : l'origine (parent)
 * et la cible (fils) ainsi que par l'action � r�aliser pour passer 
 * d'un �tat � l'autre.
 */
public class StateLink
{	// noeud parent
	private StateNode origin;
	// noeud fils
	private StateNode target;
	// action de transition
	private Action action;

	/**
	 * Constructeur.
	 * 
	 * @param origin	noeud parent
	 * @param target	noeud fils
	 * @param action	action de transition
	 */
	public StateLink(StateNode origin,StateNode target, Action action)
	{	this.origin = origin;
		this.target = target;
		this.action = action;
	}

	/**
	 * Renvoie le noeud parent du lien 
	 * @return	le noeud parent
	 */
	public StateNode getOrigin()
	{	return origin;
	}

	/**
	 * Renvoie le noeud fils du lien
	 * @return	le noeud fils
	 */
	public StateNode getTarget()
	{	return target;
	}

	/**
	 * Renvoie l'action de transition
	 * @return	l'action associ�e au lien
	 */
	public Action getAction()
	{	return action;
	}

	public boolean equals(Object object)
	{	boolean result;
		if(object == null)
			result = false;
		else if(!(object instanceof StateLink))
			result = false;
		else
		{	StateLink temp = (StateLink) object;
			result = temp.getOrigin() == getOrigin()
				&& temp.getTarget() == getTarget();
		}
		return result;
	}
	
	public String toString()
	{	String result = "[";
		result = result + getOrigin().getName();
		result = result + "]-(";
		result = result + getAction().getName();
		result = result + ")->";
		result = result + "[";
		result = result + getTarget().getName();
		result = result + "]";
		return result;
	}
}
