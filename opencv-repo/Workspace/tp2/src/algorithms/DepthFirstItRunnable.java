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
 * Implémente le parcours d'arbre en profondeur d'abord itérative pour un thread.
 */
public class DepthFirstItRunnable implements Runnable
{	// délai entre deux itérations
	private final int DELAY = 500;
	// arbre de recherche à construire
	private SearchTree tree;
	// frame affichant l'arbre
	private SearchTreeFrame frame;

	/**
	 * Constructeur.
	 * @param tree	arbre de recherche à construire
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
	 * Applique la parcours d'arbre en profondeur d'abord itérative.
	 */
	public void makeTree()
	{	//TODO à compléter par l'étudiant
	}
}
