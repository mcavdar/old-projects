package problem;

import exceptions.ImpossibleActionException;

/**
 * Classe représentant une action applicable à un état
 * d'origine et permettant d'obtenir un état cible.
 */
public class Action
{	// indique si la chèvre traverse
	private boolean chevre;
	// indique si le loup traverse
	private boolean loup;
	// indique si la salade traverse
	private boolean salade;
	// cout de l'action
	private double cost;
	
	/**
	 * Constructeur (le berger n'est pas représenté, car il traverse
	 * systématiquement). Le coût de l'action est nul.
	 * @param chevre	indique si la chèvre doit traverser
	 * @param loup	indique si le loup doit traverser
	 * @param salade	indique si la salade doit traverser
	 */
	public Action(boolean chevre, boolean loup, boolean salade)
	{	this.chevre = chevre;
		this.loup = loup;
		this.salade = salade;
		this.cost = 0;
	}
	/**
	 * Constructeur (le berger n'est pas représenté, car il traverse
	 * systématiquement). 
	 * @param chevre	indique si la chèvre doit traverser
	 * @param loup	indique si le loup doit traverser
	 * @param salade	indique si la salade doit traverser
	 * @param cost	coût de l'action.
	 */
	public Action(boolean chevre, boolean loup, boolean salade, double cost)
	{	this.chevre = chevre;
		this.loup = loup;
		this.salade = salade;
		this.cost = cost;		
	}

	/**
	 * Méthode utilisée lors de la construction de la représentation
	 * textuelle de l'action. 
	 * @param name	représentation textuelle de l'action
	 * @param element	élément à traiter (berger, chèvre, loup ou salade)
	 * @param rep	représentation textuelle de cet élément
	 */
	private void placeElement(StringBuffer name, boolean element, String rep)
	{	if(element)
			name.append(" "+rep);
	}
	/**
	 * Renvoie une représentation textuelle de l'action, sous la forme d'un ensemble
	 * de lettres indiquant quels éléments traversent la rivière au cours de l'action.
	 * B représente le berger, C la chevre, L le loup et S la salade. 
	 *  
	 * @return	la représentation textuelle de l'action
	 */
	public String getName()
	{	StringBuffer name = new StringBuffer("B");
		placeElement(name,chevre,"C");
		placeElement(name,loup,"L");
		placeElement(name,salade,"S");
		return name.toString();
	}
	public String toString()
	{	return getName();	
	}

	/**
	 * Renvoie le coût de l'action. 
	 * @return	le coût de l'action.
	 */
	public double getCost()
	{	return cost;
	}

	/**
	 * Renvoie l'état obtenu si on applique l'action à l'état passé
	 * en paramètre.
	 * @param state	l'état d'origine
	 * @return	l'état cible obtenu en appliquant l'action
	 * @throws ImpossibleActionException
	 */
	public State apply(State state) throws ImpossibleActionException
	{	State result = null;
		// on vérifie qu'un seul élément est déplacé
		// (sans compter le berger)
		if((chevre && loup) || (chevre && salade) || (loup && salade))
			throw new ImpossibleActionException(state,this);
		boolean oBerger = state.getBerger();
		boolean oChevre = state.getChevre();
		boolean oLoup = state.getLoup();
		boolean oSalade = state.getSalade();
		// on vérifie que le berger se trouve du même côté
		// que les éléments à déplacer
		if((chevre && (oChevre!=oBerger))
			|| (loup && (oLoup!=oBerger))
			|| (salade && (oSalade!=oBerger)))
			throw new ImpossibleActionException(state,this);
		// l'opérateur XOR permet de calculer facilement le changement d'état
		boolean tBerger = state.getBerger()^true;
		boolean tChevre = state.getChevre()^chevre;
		boolean tLoup = state.getLoup()^loup;
		boolean tSalade = state.getSalade()^salade;
		// on vérifie que les couples chevre/salade ou chevre/loup 
		// ne sont pas laissés seuls sans berger
		if(tChevre!=tBerger && (tChevre==tSalade  || tChevre==tLoup))
			throw new ImpossibleActionException(state,this);
		else
			result = new State(tBerger,tChevre,tLoup,tSalade);
		return result;
	}
}
