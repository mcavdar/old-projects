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
 * Impl�mente le parcours d'arbre en profondeur d'abord pour un thread.
 */
public class DepthFirstRunnable implements Runnable { // d�lai entre deux
														// it�rations
	private final int DELAY = 500;
	// arbre de recherche � construire
	private SearchTree tree;
	// frame affichant l'arbre
	private SearchTreeFrame frame;

	/**
	 * Constructeur.
	 * 
	 * @param tree
	 *            arbre de recherche � construire
	 */
	public DepthFirstRunnable(SearchTreeFrame frame, SearchTree tree) {
		this.frame = frame;
		this.tree = tree;
		tree.init();
	}

	public void run() {
		makeTree();
		frame.switchButtons(true);
	}

	/**
	 * Applique la parcours d'arbre en profondeur d'abord.
	 */
	public void makeTree() { // TODO � compl�ter par l'�tudiant
		SearchNode solution = null;
		LinkedList<SearchNode> fifo = new LinkedList<SearchNode>();
		fifo.push(tree.getRoot());
		int counter = 1;
		while (!fifo.isEmpty() && solution == null) {
			SearchNode node = fifo.pop();
			tree.markVisitedNode(node, counter);
			System.out.println("Counter:" + counter + node.toString());
			counter++;
			if (tree.isFinalNode(node)) {
				solution = node;
			} else {
				try {
					Iterator<SearchLink> sl = tree.developNode(node);
					while (sl.hasNext()) {
						SearchLink snl = sl.next();
						SearchNode sn = snl.getTarget();
						fifo.push(sn);
					}
				} catch (AbsentNodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		Vector<SearchLink> path = null;
		try {
			path = tree.getPath(solution);
			System.out.println(tree.pathToString(path));
		} catch (AbsentNodeException e) {
			e.printStackTrace();
		}
	}
}
