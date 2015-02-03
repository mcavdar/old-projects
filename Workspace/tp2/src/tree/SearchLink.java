package tree;

import problem.Action;


/**
 * Représente un lien orienté dans un arbre de recherche.
 * Ce lien est défini par les deux noeuds reliés : l'origine (parent)
 * et la cible (fils) ainsi que par l'action à réaliser pour passer 
 * d'un état à l'autre.
 */
public class SearchLink
{	// noeud parent
	private SearchNode origin;
	// noeud fils
	private SearchNode target;
	// action de transition
	private Action action;

	/**
	 * Constructeur.
	 * 
	 * @param origin	noeud parent
	 * @param target	noeud fils
	 * @param action	action de transition
	 */
	public SearchLink(SearchNode origin,SearchNode target, Action action)
	{	this.origin = origin;
		this.target = target;
		this.action = action;
	}

	/**
	 * Renvoie le noeud parent du lien 
	 * @return	le noeud parent
	 */
	public SearchNode getOrigin()
	{	return origin;
	}

	/**
	 * Renvoie le noeud fils du lien
	 * @return	le noeud fils
	 */
	public SearchNode getTarget()
	{	return target;
	}

	/**
	 * Renvoie l'action de transition
	 * @return	l'action associée au lien
	 */
	public Action getAction()
	{	return action;
	}

	public boolean equals(Object object)
	{	boolean result;
		if(object == null)
			result = false;
		else if(!(object instanceof SearchLink))
			result = false;
		else
		{	SearchLink temp = (SearchLink) object;
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
