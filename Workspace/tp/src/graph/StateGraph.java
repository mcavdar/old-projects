package graph;

import java.util.Iterator;
import java.util.Vector;

import exceptions.AbsentNodeException;
import exceptions.ExistingLinkException;
import problem.Problem;
import problem.State;


/**
 * Représentation d'un graphe d'état
 */
public class StateGraph
{	// problème traité
	private Problem problem;
	// ensemble des noeuds composant le graphe
	private Vector<StateNode> nodes;
	// ensemble des liens composant le graphe
	private Vector<StateLink> links;
	// ensemble noeuds initiaux du graphe
	private Vector<StateNode> initialNodes;
	
	/**
	 * Initialise un graphe d'état à partir du problème
	 * passé en paramètre. Les noeuds initiaux sont 
	 * calculés à partir du problème et rajoutés au graphe.
	 * @param problem	problème à traiter
	 */
	public StateGraph(Problem problem)
	{	this.problem = problem;
		nodes = new Vector<StateNode>();
		initialNodes = new Vector<StateNode>();
		links = new Vector<StateLink>();
		Iterator<State> i = problem.getInitialStatesIterator();
		while(i.hasNext())
		{	StateNode temp = new StateNode(i.next());
			if(!nodes.contains(temp))
			{	nodes.add(temp);
				initialNodes.add(temp);
			}
		}		
	}
	
	/**
	 * Renvoie un noeud présent dans le graphe et correspondant
	 * au noeud passé en paramètre, qui n'est pas (éventuellement)
	 * dans le graphe. Si aucun noeud équivalent n'est présent dans le graphe,
	 * une AbsentNodeException est levée.
	 * 
	 * @param node	le noeud à traiter
	 * @return	un noeud équivalent mais présent dans le graphe
	 * @throws AbsentNodeException 
	 */
	private StateNode getEqualNode(StateNode node) throws AbsentNodeException
	{	StateNode result = null;
		int index = nodes.indexOf(node);
		if(index==-1)
			throw new AbsentNodeException(node);
		result = nodes.get(index);
		return result;
	}
	
	/**
	 * Détermine si le graphe contient le noeud passé en paramètre,
	 * ou au moins un noeud équivalent (i.e. contenant le même état).
	 * @param node	le noeud à rechercher
	 * @return	vrai si le graphe contient le noeud (ou un noeud équivalent)
	 */
	public boolean containsNode(StateNode node)
	{	return nodes.contains(node);		
	}
	
	/**
	 * Détermine si le noeud passé en paramètre contient un état final du problème.
	 * @param node le noeud à traiter
	 * @return	vrai si le noeud est final
	 */
	public boolean isFinalNode(StateNode node)
	{	return problem.isFinalState(node.getState());
	}
	
	/**
	 * Crée un nouveau lien dans le graphe. Le noeud origin doit être présent dans le graphe.
	 * Si un noeud équivalent à target est déjà présent dans le graphe, c'est lui qui 
	 * est utilisé (et non pas celui passé en paramètre).
	 * Si le noeud target n'est pas présent dans le graphe, il est automatiquement rajouté.
	 * Si le noeud origin n'appartient pas au graphe, une AbsentNodeException est levée.
	 * Si le lien existe déjà, une ExistingLinkException est levée.
	 * 
	 * @param link	le lien à rajouter au graphe
	 * @throws AbsentNodeException 
	 * @throws ExistingLinkException 
	 * @throws AbsentNodeException 
	 */
	public void addLink(StateLink link) throws ExistingLinkException, AbsentNodeException
	{	StateNode origin = link.getOrigin();
		StateNode target = link.getTarget();
		StateNode originNode = getEqualNode(origin);
		if(originNode == null)
			throw new AbsentNodeException(origin);
		StateNode targetNode;
		try
		{	// si le noeud existe déjà, on le récupère
			targetNode = getEqualNode(target);
		}
		catch (AbsentNodeException e)
		{	// si le noeud n'existe pas, on l'ajoute
			targetNode = target;
			nodes.add(targetNode);
		}
		StateLink lnk = new StateLink(originNode,targetNode,link.getAction());
		if(links.contains(lnk))
			throw new ExistingLinkException(lnk);
		links.add(lnk);
	}
	
	public String toString()
	{	String result = "####### Graphe #######\n";
		Iterator<StateNode> i = nodes.iterator();
		while(i.hasNext())
		{	StateNode tempNode = i.next();
			Iterator<StateLink> j = links.iterator();
			while(j.hasNext())
			{	StateLink tempLink = j.next();
				if(tempLink.getOrigin().equals(tempNode))
					result = result + "\t"+ tempLink.toString()+"\n";
			}
		}
		return result;
	}
	
	/**
	 * Renvoie un itérateur sur l'ensemble des noeuds initiaux de ce graphe.
	 * @return	un itérateur sur les noeuds initiaux
	 */
	public Iterator<StateNode> getInitialNodesIterator()
	{	return initialNodes.iterator();		
	}
	
	/**
	 * Renvoie le probleme représenté par ce graphe, ce qui permet
	 * notamment d'accéder à l'ensemble des actions possibles.
	 * @return	le problème traité
	 */
	public Problem getProblem()
	{	return problem;		
	}
}
