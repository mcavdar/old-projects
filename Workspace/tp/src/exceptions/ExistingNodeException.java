package exceptions;

/**
 * Exception levée lorsqu'on tente de créer un noeud dans un graphe
 * et que ce noeud est déjà présent.
 *
 */
public class ExistingNodeException extends Exception
{	private static final long serialVersionUID = 1L;
	// le noeud provoquant l'exeption
	private Object node;

	/**
	 * Constructeur. 
	 * @param node	le noeud provoquant l'exeption
	 */
	public ExistingNodeException(Object node)
	{	this.node = node;	 	
	}
	
	/**
	 * Renvoie le noeud qui a provoqué l'exception 
	 * @return le noeud qui a provoqué l'exception 
	 */
	public Object getNode()
	{	return node;
	}
}
