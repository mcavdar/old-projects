package tree;

import java.util.Iterator;
import java.util.Vector;

import exceptions.AbsentNodeException;
import exceptions.ExistingNodeException;
import exceptions.ImpossibleActionException;
import gui.SearchTreeTreeModel;
import problem.Action;
import problem.Problem;
import problem.State;


/**
 * Représentation d'un arbre de recherche.
 */
public class SearchTree
{	private static final long serialVersionUID = 1L;
	// problème traité
	private Problem problem;
	// ensemble des noeuds composant l'arbre
	private Vector<SearchNode> nodes;
	// ensemble des liens composant l'arbre
	private Vector<SearchLink> links;
	// interface entre l'arbre et sa représentation graphique
	private SearchTreeTreeModel model;
	// dernier noeud traité lors de la recherche
	private SearchNode lastNode;
	// itération courante lors de la recherche
	private int iteration;
	
	/**
	 * Constructeur. 
	 * @param problem	le problème à traiter
	 */
	public SearchTree(Problem problem)
	{	this.problem = problem;
		model = new SearchTreeTreeModel(this);
		init();
	}
	
	/**
	 * Initialise l'arbre (à faire avant de commencer une recherche)
	 */
	public void init()
	{	nodes = new Vector<SearchNode>();
		iteration = 0;
		SearchNode initialNode = new SearchNode(problem.getInitialState(),0,0);
		nodes.add(initialNode);
		lastNode = initialNode;
		links = new Vector<SearchLink>();
		model.fireTreeStructureChanged(getRoot());
	}
	
	/**
	 * Renvoie la racine de l'arbre (un noeud contenant un état initial)
	 * @return	la racine de l'arbre de recherche
	 */
	public SearchNode getRoot()
	{	return nodes.get(0);		
	}
	
	/**
	 * Détermine si l'arbre contient le noeud passé en paramètre.
	 * @param node	le noeud à rechercher
	 * @return	vrai si l'arbre contient le noeud
	 */
	public synchronized boolean containsNode(SearchNode node)
	{	boolean result=false;
		Iterator<SearchNode> i = nodes.iterator();
		while(i.hasNext() && !result)
			result = node == i.next();
		return result;		
	}
	
	/**
	 * Détermine si le noeud passé en paramètre contient un état final du problème.
	 * @param node le noeud à traiter
	 * @return	vrai si le noeud est final
	 */
	public boolean isFinalNode(SearchNode node)
	{	return problem.isFinalState(node.getState());
	}
	
	/**
	 * Renvoie le lien liant le noeud passé en paramètre à son père.
	 * @param node	le noeud à traiter
	 * @return	un lien contenant le noeud en position target
	 * @throws AbsentNodeException
	 */
	public SearchLink getParentLink(SearchNode node) throws AbsentNodeException
	{	SearchLink result=null;
		if(!containsNode(node))
			throw new AbsentNodeException(node);
		if(node != getRoot())
		{	Iterator<SearchLink> i = links.iterator();
			while(i.hasNext() && result==null)
			{	SearchLink temp = i.next();
				if(temp.getTarget() == node)
					result = temp;
			}
		}
		return result;
	}

	/**
	 * Renvoie tous les liens partant du noeud passé en paramètre.
	 * @param node	le noeud à traiter
	 * @return	tous les liens où ce noeud est en position origin
	 * @throws AbsentNodeException
	 */
	public synchronized Vector<SearchLink> getChildrenLinks(SearchNode node) throws AbsentNodeException
	{	Vector<SearchLink> result = new Vector<SearchLink>();
		if(!containsNode(node))
			throw new AbsentNodeException(node);
		Iterator<SearchLink> i = links.iterator();
		while(i.hasNext())
		{	SearchLink temp = i.next();
			if(temp.getOrigin() == node)
				result.add(temp);
		}
		return result;
	}

	/**
	 * Renvoie une séquence de liens représentant un chemin allant de la racine
	 * au noeud passé en paramètre.
	 * @param node	le noeud à traiter
	 * @return	un vecteur de liens représentant le chemin de puis la racine
	 * @throws AbsentNodeException
	 */
	public Vector<SearchLink> getPath(SearchNode node) throws AbsentNodeException
	{	Vector<SearchLink> result;
		SearchLink parentLink = getParentLink(node);
		if(parentLink==null)
			result = new Vector<SearchLink>();
		else
		{	result = getPath(parentLink.getOrigin());
			result.add(parentLink);
		}
		return result;
	}
	
	/**
	 * Crée un nouveau lien dans l'arbre. Le noeud origin doit être présent dans le graphe.
	 * Le noeud target doit être absent de l'arbre.
	 * Si le noeud origin n'appartient pas au graphe, une AbsentNodeException est levée.
	 * Si le noeud target appartient déjà au graphe, une ExistingNodeException est levée.
	 * @param	link	le lien à rajouter dans l'arbre
	 * @throws AbsentNodeException
	 * @throws ExistingNodeException
	 */
	public void addLink(SearchLink link) throws AbsentNodeException, ExistingNodeException
	{	addLinkSynch(link);
		model.fireTreeNodesInserted(link);
	}
	/**
	 * Méthode utilisée par addLink pour des opérations devant être effectuées
	 * en mode synchronisé.  
	 * @param link	le lien à ajouter
	 * @throws AbsentNodeException
	 * @throws ExistingNodeException
	 */
	private synchronized void addLinkSynch(SearchLink link) throws AbsentNodeException, ExistingNodeException
	{	SearchNode target = link.getTarget();
		if(containsNode(target))
			throw new ExistingNodeException(target);
		links.add(link);
		nodes.add(target);
	}
	
	
	public synchronized String toString()
	{	String result = "####### Arbre #######\n";
		Iterator<SearchNode> i = nodes.iterator();
		while(i.hasNext())
		{	SearchNode tempNode = i.next();
			Iterator<SearchLink> j = links.iterator();
			while(j.hasNext())
			{	SearchLink tempLink = j.next();
				if(tempLink.getOrigin().equals(tempNode))
					result = result + "\t"+ tempLink.toString()+"\n";
			}
		}
		return result;
	}
	
	/**
	 * Développe le noeud passé en paramètre, c'est à dire :
	 * 1) détermine les actions applicables au noeud passé en paramètre,
	 * 2) applique ces actions de manière à obtenir les noeuds fils correspondants,
	 * 3) crée les liens adéquats, et 4) ajoute ces liens à l'arbre.
	 * La liste des liens créés est renvoyée par la fonction. Une 
	 * AbsentNodeException est levée si le noeud passé en paramètre n'appartient pas à l'arbre.
	 * 
	 * @param node	le noeud à développé
	 * @return	un itérateur sur les liens vers les fils du noeud. 
	 * @throws AbsentNodeException 
	 */
	public Iterator<SearchLink> developNode(SearchNode node) throws AbsentNodeException
	{	if(!containsNode(node))
			throw new AbsentNodeException(node);
		Vector<SearchLink> result = new Vector<SearchLink>();
		if(!isFinalNode(node))
		{	Iterator<Action> i = problem.getActionsIterator();
			while(i.hasNext())
			{	Action action = i.next();
				try
				{	State targetState = action.apply(node.getState());
					int targetDepth = node.getDepth()+1;
					double targetCost = node.getCost()+action.getCost();
					SearchNode target = new SearchNode(targetState,targetDepth,targetCost);
					SearchLink link = new SearchLink(node,target,action);
					result.add(link);
					addLink(link);
				}
				catch (ImpossibleActionException e)
				{	// l'action n'est pas applicable : 
					// on passe à l'action suivante
				}
				catch (ExistingNodeException e)
				{	e.printStackTrace();
				}
			}
		}
		return result.iterator();
	}
	
	/**
	 * Renvoie une représentation textuelle du chemin passé en paramètre.
	 * Ce chemin doit prendre la forme d'un vecteur de liens.
	 * @param path	le chemin à représenter
	 * @return	la représentation textuelle de ce chemin
	 */
	public String pathToString(Vector<SearchLink> path)
	{	StringBuffer result = new StringBuffer();
		if(path.size()>0)
		{	result.append(path.get(0).getOrigin().toString());
			Iterator<SearchLink> i = path.iterator();
			while(i.hasNext())
			{	SearchLink temp = i.next();
				result.append("["+temp.getAction().toString());
				result.append("]"+temp.getTarget().toString());				
			}
		}
		return result.toString();
	}

	/**
	 * Renvoie l'interface entre cet arbre et sa représentation graphique
	 * @return	l'interface de l'arbre avec sa représentation graphique
	 */
	public SearchTreeTreeModel getModel()
	{	return model;
	}
	
	/**
	 * Effectue les modifications nécessaires sur un noeud pour indiquer
	 * qu'il a été visité par un algorithme de recherche.
	 * @param node	le noeud visité
	 * @param iteration	l'itération lors de laquelle le noeud a été visité
	 */
	public void markVisitedNode(SearchNode node, int iteration)
	{	this.iteration = iteration;
		node.markVisited(iteration);
		model.fireTreeNodesChanged(lastNode);
		model.fireTreeNodesChanged(node);
		lastNode = node; 
	}

	/**
	 * Renvoie l'itération courante de l'algorithme qui parcourt l'arbre.
	 * Si l'itération vaut 0, c'est que le parcours n'a pas encore commencé.
	 * @return	l'itération courante
	 */
	public synchronized int getIteration()
	{	return iteration;
	}
	
	/**
	 * Renvoie le chemin du dernier noeud visité.
	 * Ce chemin prend la forme d'un vecteur de noeuds de recherche.
	 * @return	le chemin du dernier noeud visité
	 * @throws AbsentNodeException
	 */
	public synchronized Vector<SearchNode> getIterationPath() throws AbsentNodeException
	{	Vector<SearchNode> result = new Vector<SearchNode>();
		SearchNode node = null;
		Iterator<SearchNode> i = nodes.iterator();
		while(i.hasNext() && node==null)
		{	SearchNode temp = i.next();
			if(temp.getIteration()==iteration)
				node = temp;
		}
		Vector<SearchLink> temp = getPath(node);
		Iterator<SearchLink> j = temp.iterator();
		while(j.hasNext())
			result.add(j.next().getOrigin());
		result.add(node);
		return result;
	}
}
