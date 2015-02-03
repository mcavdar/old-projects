package graph;

import java.util.Iterator;

import exceptions.AbsentNodeException;
import exceptions.ExistingLinkException;
import exceptions.ExistingNodeException;
import exceptions.ImpossibleActionException;
import problem.Action;
import problem.Problem;
import problem.State;

/**
 * Classe de lancement r�alisant la construction automatique du graphe d'�tats.
 */
public class GraphLaunch { // graphe d'�tats
	private static StateGraph graph;

	// programme principal
	public static void main(String[] args) throws ExistingNodeException { // initialisation
		Problem problem = new Problem();
		// construction du graphe
		graph = new StateGraph(problem);
		makeGraph();
		// affichage du graphe
		System.out.println(graph);
	}

	/**
	 * D�veloppe le noeud pass� en param�tre, c'est � dire : 1) d�termine les
	 * actions applicables au noeud pass� en param�tre, 2) applique ces actions
	 * de mani�re � obtenir les noeuds fils correspondants, 3) cr�e les liens
	 * ad�quats, et 4) ajoute ces liens au graphe. Chaque noeud fils est lui
	 * m�me d�velopp� r�cursivement jusqu'� ce qu'aucune action ne soit plus
	 * applicable. Une AbsentNodeException est lev�e si le noeud pass� en
	 * param�tre n'appartient pas au grapge.
	 * 
	 * @param node
	 *            le noeud � d�velopp�
	 * @throws AbsentNodeException
	 */
	public static void developRecNode(StateNode node)
			throws AbsentNodeException {
		if (!graph.containsNode(node))
			throw new AbsentNodeException(node);
		Iterator<Action> i = graph.getProblem().getActionsIterator();
		while (i.hasNext()) {
			Action action = i.next();
			boolean already = false;
			try {
				State targetState = action.apply(node.getState());
				StateNode target = new StateNode(targetState);
				already = graph.containsNode(target);
				StateLink link = new StateLink(node, target, action);
				graph.addLink(link);
				// on continue récursivement avec le noeud fils (target)
				// sauf s'il existait déjà (already)
				if (!already)
					developRecNode(target);
			} catch (ExistingLinkException e) {
				// le lien existe déjà :
				// on passe à l'action suivante
			} catch (ImpossibleActionException e) {
				// l'action n'est pas applicable :
				// on passe à l'action suivante
			}
		}
	}

	/**
	 * Construit le graphe, c'est � dire que tous les noeuds initiaux sont
	 * d�velopp�s r�cursivement et exhaustivement. A la fin du traitement, le
	 * graphe contient l'espace complet des �tats
	 */
	public static void makeGraph() {
		Iterator<StateNode> i = graph.getInitialNodesIterator();
		while (i.hasNext()) {
			StateNode temp = i.next();
			try {
				developRecNode(temp);
			} catch (AbsentNodeException e) {
				e.printStackTrace();
			}
		}
	}
}
