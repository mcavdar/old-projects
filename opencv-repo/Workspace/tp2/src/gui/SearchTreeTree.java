package gui;


import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import tree.SearchTree;

/**
 * Composant graphique chargé d'afficher l'arbre de recherche.
 */
public class SearchTreeTree extends JTree implements TreeModelListener
{	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur.
	 */
	public SearchTreeTree(SearchTree tree)
	{	super(tree.getModel());
		getModel().addTreeModelListener(this);
		setCellRenderer(new SearchTreeTreeCellRenderer());
		setRootVisible(true);
		setDragEnabled(false);
		setEditable(false);
		setScrollsOnExpand(true);
		setShowsRootHandles(true);
	}

	public void treeNodesChanged(TreeModelEvent e)
	{	
	}
	public void treeNodesInserted(TreeModelEvent e)
	{	TreePath path = e.getTreePath();
		expandPath(path);
	}
	public void treeNodesRemoved(TreeModelEvent e)
	{	
	}
	public void treeStructureChanged(TreeModelEvent e)
	{	
	}
	
}
