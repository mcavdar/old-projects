package algorithms;

import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import exceptions.AbsentNodeException;
import gui.SearchTreeFrame;
import problem.State;
import tree.SearchLink;
import tree.SearchNode;
import tree.SearchTree;

/**
 * Impl�mente le parcours d'arbre en profondeur modifi� d'abord pour un thread.
 */
public class DepthFirstModRunnable implements Runnable
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
	public DepthFirstModRunnable(SearchTreeFrame frame, SearchTree tree)
	{	this.frame = frame;
		this.tree = tree;
		tree.init();
	}
	
	public void run()
	{	makeTree();
		frame.switchButtons(true);
	}
	
	/**
	 * Applique la parcours d'arbre en profondeur d'abord modifi�.
	 */
	public void makeTree()
	{	//TODO � compl�ter par l'�tudiant
	}
}
