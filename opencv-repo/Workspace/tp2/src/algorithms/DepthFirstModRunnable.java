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
 * Implémente le parcours d'arbre en profondeur modifié d'abord pour un thread.
 */
public class DepthFirstModRunnable implements Runnable
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
	 * Applique la parcours d'arbre en profondeur d'abord modifié.
	 */
	public void makeTree()
	{	//TODO à compléter par l'étudiant
	}
}
