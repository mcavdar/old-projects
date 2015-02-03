package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;


import exceptions.AbsentNodeException;
import tree.SearchNode;
import tree.SearchTree;

/**
 * Composant graphique chargé d'afficher un noeud de l'arbre.
 */
public class SearchTreeTreeCellRenderer extends DefaultTreeCellRenderer
{	private static final long serialVersionUID = 1L;

	public Component getTreeCellRendererComponent(JTree jtree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{	SearchTree tree = ((SearchTreeTreeModel)jtree.getModel()).getTree();
		SearchNode node = (SearchNode) value;
		DefaultTreeCellRenderer result = (DefaultTreeCellRenderer)super.getTreeCellRendererComponent(jtree,value,sel,expanded,leaf,row,hasFocus);
		result.setIcon(null);		
		if(node.isVisited())
			result.setFont(getFont().deriveFont(Font.BOLD));
		else
			result.setFont(getFont().deriveFont(Font.PLAIN));			
		int iteration = tree.getIteration();
		if(iteration>0)
		{	Vector<SearchNode> iterationPath = new Vector<SearchNode>();
			try
			{	iterationPath = tree.getIterationPath();
			}
			catch (AbsentNodeException e)
			{	e.printStackTrace();
			}
			if(node.getIteration()==iteration || iterationPath.contains(node))
				result.setForeground(Color.RED);
			else
				result.setForeground(Color.BLACK);
		}
		return result;
	}

}
