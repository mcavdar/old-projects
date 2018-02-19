package algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import exceptions.AbsentNodeException;
import gui.SearchTreeFrame;
import tree.SearchLink;
import tree.SearchNode;
import tree.SearchTree;

/**
 * Impl�mente le parcours d'arbre en profondeur d'abord it�rative pour un thread.
 */
public class DepthFirstItRunnable implements Runnable
{	// d�lai entre deux it�rations
	private final int DELAY = 500;
	// arbre de recherche � construire
	private SearchTree tree;
	// frame affichant l'arbre
	private SearchTreeFrame frame;

	/**
	 * Constructeur.
	 * @param tree	arbre de recherche � construire
	 */
	public DepthFirstItRunnable(SearchTreeFrame frame, SearchTree tree)
	{	this.frame = frame;
		this.tree = tree;
		tree.init();
	}
	
	public void run()
	{	makeTree();
		frame.switchButtons(true);
	}
	
	/**
	 * Applique la parcours d'arbre en profondeur d'abord it�rative.
	 */
	public void makeTree()
	{	//TODO � compl�ter par l'�tudiant
	}
}
