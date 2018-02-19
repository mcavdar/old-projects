package graph;

import java.util.Iterator;
import java.util.Vector;

import exceptions.AbsentNodeException;
import exceptions.ExistingLinkException;
import problem.Problem;
import problem.State;


/**
 * Repr�sentation d'un graphe d'�tat
 */
public class StateGraph
{	// probl�me trait�
	private Problem problem;
	// ensemble des noeuds composant le graphe
	private Vector<StateNode> nodes;
	// ensemble des liens composant le graphe
	private Vector<StateLink> links;
	// ensemble noeuds initiaux du graphe
	private Vector<StateNode> initialNodes;
	
	/**
	 * Initialise un graphe d'�tat � partir du probl�me
	 * pass� en param�tre. Les noeuds initiaux sont 
	 * calcul�s � partir du probl�me et rajout�s au graphe.
	 * @param problem	probl�me � traiter
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
	 * Renvoie un noeud pr�sent dans le graphe et correspondant
	 * au noeud pass� en param�tre, qui n'est pas (�ventuellement)
	 * dans le graphe. Si aucun noeud �quivalent n'est pr�sent dans le graphe,
	 * une AbsentNodeException est lev�e.
	 * 
	 * @param node	le noeud � traiter
	 * @return	un noeud �quivalent mais pr�sent dans le graphe
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
	 * D�termine si le graphe contient le noeud pass� en param�tre,
	 * ou au moins un noeud �quivalent (i.e. contenant le m�me �tat).
	 * @param node	le noeud � rechercher
	 * @return	vrai si le graphe contient le noeud (ou un noeud �quivalent)
	 */
	public boolean containsNode(StateNode node)
	{	return nodes.contains(node);		
	}
	
	/**
	 * D�termine si le noeud pass� en param�tre contient un �tat final du probl�me.
	 * @param node le noeud � traiter
	 * @return	vrai si le noeud est final
	 */
	public boolean isFinalNode(StateNode node)
	{	return problem.isFinalState(node.getState());
	}
	
	/**
	 * Cr�e un nouveau lien dans le graphe. Le noeud origin doit �tre pr�sent dans le graphe.
	 * Si un noeud �quivalent � target est d�j� pr�sent dans le graphe, c'est lui qui 
	 * est utilis� (et non pas celui pass� en param�tre).
	 * Si le noeud target n'est pas pr�sent dans le graphe, il est automatiquement rajout�.
	 * Si le noeud origin n'appartient pas au graphe, une AbsentNodeException est lev�e.
	 * Si le lien existe d�j�, une ExistingLinkException est lev�e.
	 * 
	 * @param link	le lien � rajouter au graphe
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
		{	// si le noeud existe d�j�, on le r�cup�re
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
	 * Renvoie un it�rateur sur l'ensemble des noeuds initiaux de ce graphe.
	 * @return	un it�rateur sur les noeuds initiaux
	 */
	public Iterator<StateNode> getInitialNodesIterator()
	{	return initialNodes.iterator();		
	}
	
	/**
	 * Renvoie le probleme repr�sent� par ce graphe, ce qui permet
	 * notamment d'acc�der � l'ensemble des actions possibles.
	 * @return	le probl�me trait�
	 */
	public Problem getProblem()
	{	return problem;		
	}
}
