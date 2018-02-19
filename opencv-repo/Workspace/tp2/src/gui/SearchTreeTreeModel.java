package gui;

import java.util.Iterator;
import java.util.Vector;


import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import exceptions.AbsentNodeException;
import tree.SearchLink;
import tree.SearchNode;
import tree.SearchTree;

/**
 * Composant chargé de l'interfaçage entre l'arbre et sa représentation graphique. 
 */
public class SearchTreeTreeModel implements TreeModel
{	private static final long serialVersionUID = 1L;
	// objets à l'écoute des modifications de l'arbre.
	private EventListenerList listenerList;
	// arbre de recherche
	SearchTree tree;

	/**
	 * Constructeur.
	 * @param tree	l'arbre à interfaçer.
	 */
	public SearchTreeTreeModel(SearchTree tree)
	{	this.tree = tree;	
		listenerList = new EventListenerList();
	}

	public SearchNode getRoot()
	{	return tree.getRoot();		
	}

	public Object getChild(Object parent, int index)
	{	SearchNode result = null;
		SearchNode node = (SearchNode) parent;
		try
		{	Vector<SearchLink> children = tree.getChildrenLinks(node);
			result = children.get(index).getTarget();
		}
		catch (AbsentNodeException e)
		{	e.printStackTrace();
		}
		return result;
	}
	
	public int getChildCount(Object parent)
	{	int result = 0;
		SearchNode node = (SearchNode) parent;
		try
		{	Vector<SearchLink> children = tree.getChildrenLinks(node);
			result = children.size();
		}
		catch (AbsentNodeException e)
		{	e.printStackTrace();
		}
		return result;
	}
	
	public int getIndexOfChild(Object parent, Object child)
	{	int result = -1;
		SearchNode parentNode = (SearchNode) parent;
		SearchNode childNode = (SearchNode) child;
		try
		{	Vector<SearchLink> children = tree.getChildrenLinks(parentNode);
			Iterator<SearchLink> i = children.iterator();
			while(i.hasNext() && result==-1)
			{	SearchLink tempLink = i.next();
				if(tempLink.getTarget() == childNode)
					result = children.indexOf(tempLink);
			}
		}
		catch (AbsentNodeException e)
		{	e.printStackTrace();
		}
		return result;
	}
	
	public boolean isLeaf(Object arg0)
	{	return false;
	}
	
	public void addTreeModelListener(TreeModelListener l)
	{	listenerList.add(TreeModelListener.class, l);
	}
	public void removeTreeModelListener(TreeModelListener l)
	{	listenerList.remove(TreeModelListener.class, l);
	}
	public void valueForPathChanged(TreePath arg0, Object arg1)
	{	
	}
	
	/**
	 * Signale aux objets à l'écoute chaque modification d'un
	 * noeud existant dans l'arbre de recherche.
	 * @param node	le noeud modifié.
	 */
    public void fireTreeNodesChanged(SearchNode node)
    {	SearchLink link=null;
		try
		{	link = tree.getParentLink(node);
		}
		catch (AbsentNodeException e2)
		{	e2.printStackTrace();
		}
		if(link!=null)
		{	SearchNode parent = link.getOrigin();
			SearchNode temp = link.getTarget();
			SearchNode[] children = {temp};
			int[] childIndices = {getIndexOfChild(parent,temp)}; 
		    Object[] listeners = listenerList.getListenerList();
		    SearchNode[] path = new SearchNode[parent.getDepth()+1];
		    for(int i=parent.getDepth();i>=0;i--)
		    {	SearchLink tempLink;
				try
				{	tempLink = tree.getParentLink(temp);
		        	temp = tempLink.getOrigin();
		        	path[i] = temp;        	
				}
				catch (AbsentNodeException e1)
				{	e1.printStackTrace();
				} 
		    }
	        TreeModelEvent e = new TreeModelEvent(parent, path, childIndices, children);
	        for (int i=0;i<listeners.length;i++)
	        	if (listeners[i] instanceof TreeModelListener)
	        		((TreeModelListener)listeners[i]).treeNodesChanged(e);
		}
    }

	/**
	 * Signale aux objets à l'écoute chaque ajout d'un
	 * noeud dans l'arbre de recherche.
	 * @param link	le lien vers le noeud rajouté.
	 */
    public void fireTreeNodesInserted(SearchLink link)
    {	SearchNode parent = link.getOrigin();
    	SearchNode temp = link.getTarget();
    	SearchNode[] children = {temp};
    	int[] childIndices = {getIndexOfChild(parent,temp)}; 
        Object[] listeners = listenerList.getListenerList();
        SearchNode[] path = new SearchNode[parent.getDepth()+1];
        for(int i=parent.getDepth();i>=0;i--)
        {	SearchLink tempLink;
			try
			{	tempLink = tree.getParentLink(temp);
	        	temp = tempLink.getOrigin();
	        	path[i] = temp;        	
			}
			catch (AbsentNodeException e1)
			{	e1.printStackTrace();
			} 
        }
        TreeModelEvent e = new TreeModelEvent(parent, path, childIndices, children);
        for (int i=0;i<listeners.length;i++)
        	if (listeners[i] instanceof TreeModelListener)
        		((TreeModelListener)listeners[i]).treeNodesInserted(e);
    }
    
	/**
	 * Signale aux objets à l'écoute une modification structurelle
	 * de l'arbre de recherche.
	 * @param node	le noeud en dessous duquel la modification a eu lieu.
	 */
    public void fireTreeStructureChanged(SearchNode parent)
    {	Object[] listeners = listenerList.getListenerList();
		SearchNode temp = parent;
        SearchNode[] path = new SearchNode[parent.getDepth()+1];
        path[parent.getDepth()] = parent;
        for(int i=parent.getDepth()-1;i>=0;i--)
        {	SearchLink tempLink;
			try
			{	tempLink = tree.getParentLink(temp);
	    		temp = tempLink.getOrigin();
	    		path[i] = temp;        	
			}
			catch (AbsentNodeException e1)
			{	e1.printStackTrace();
			} 
	    }
        TreeModelEvent e = new TreeModelEvent(parent, path);
        for (int i=0;i<listeners.length;i++)
        	if (listeners[i] instanceof TreeModelListener)
        		((TreeModelListener)listeners[i]).treeStructureChanged(e);
    }
    
    /**
     * Renvoie l'arbre interfacé.
     * @return	l'arbre interfacé.
     */
    public SearchTree getTree()
    {	return tree;    
    }
}
